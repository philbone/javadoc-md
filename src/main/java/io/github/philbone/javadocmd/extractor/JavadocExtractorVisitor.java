package io.github.philbone.javadocmd.extractor;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.nodeTypes.NodeWithModifiers;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.ast.comments.JavadocComment;
import io.github.philbone.javadocmd.model.*;

import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.ArrayInitializerExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MemberValuePair;

import java.util.Optional;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * Visitor encargado de recorrer los nodos del AST de JavaParser y construir el
 * modelo intermedio para la documentación en Markdown.
 *
 * <p>Este visitor:</p>
 * <ul>
 *   <li>Extrae descripciones de Javadoc.</li>
 *   <li>Determina visibilidad y si un miembro es {@code static}.</li>
 *   <li>Procesa herencia ({@code extends}) e interfaces ({@code implements}).</li>
 *   <li>Registra métodos, constructores y campos de cada clase.</li>
 * </ul>
 *
 * @author Felipe M.
 * @project JavadocMd
 */
public class JavadocExtractorVisitor extends VoidVisitorAdapter<DocPackage>
{
    // ========== CLASES E INTERFACES ========== //
    @Override
    public void visit(ClassOrInterfaceDeclaration n, DocPackage docPackage) {
        super.visit(n, docPackage);

        String description = JavadocUtils.extractDescription(
                n.getComment().filter(c -> c instanceof JavadocComment)
                              .map(JavadocComment.class::cast)
        );

        Kind kind = n.isInterface()
                ? Kind.INTERFACE
                : (n.isAbstract() ? Kind.ABSTRACT_CLASS : Kind.CLASS);

        String visibility = extractVisibility(n);
        boolean isStatic = extractIsStatic(n);

        DocClass docClass = new DocClass(
                n.getNameAsString(),
                description,
                kind,
                visibility,
                isStatic
        );
        
        for (AnnotationExpr ae : n.getAnnotations()) {
            docClass.addAnnotation(toDocAnnotation(ae));
        }

        // Procesar extends (una sola clase para Class, múltiples para Interface)
        if (!n.getExtendedTypes().isEmpty()) {
            n.getExtendedTypes().forEach(t -> docClass.setSuperClass(t.getNameAsString()));
        }

        // Procesar implements
        if (!n.getImplementedTypes().isEmpty()) {
            n.getImplementedTypes().forEach(t -> docClass.addInterface(t.getNameAsString()));
        }

        docPackage.addClass(docClass);

        // miembros
        n.getMethods().forEach(m -> visitMethod(m, docClass));
        n.getConstructors().forEach(c -> visitConstructor(c, docClass));
        n.getFields().forEach(f -> visitField(f, docClass));

        extractProjectNameAndDescription(n, docPackage, docClass);
    }

    // ========== ENUMS ========== //
    @Override
    public void visit(EnumDeclaration n, DocPackage docPackage) {
        super.visit(n, docPackage);

        String description = JavadocUtils.extractDescription(
                n.getComment().filter(c -> c instanceof JavadocComment)
                              .map(JavadocComment.class::cast)
        );

        String visibility = extractVisibility(n);
        boolean isStatic = extractIsStatic(n);

        DocClass docClass = new DocClass(
                n.getNameAsString(),
                description,
                Kind.ENUM,
                visibility,
                isStatic
        );

        // enums pueden implementar interfaces
        if (!n.getImplementedTypes().isEmpty()) {
            n.getImplementedTypes().forEach(t -> docClass.addInterface(t.getNameAsString()));
        }

        docPackage.addClass(docClass);

        n.getMembers().forEach(m -> {
            if (m instanceof MethodDeclaration method) visitMethod(method, docClass);
            if (m instanceof ConstructorDeclaration cons) visitConstructor(cons, docClass);
            if (m instanceof FieldDeclaration field) visitField(field, docClass);
        });
    }

    // ========== RECORDS ========== //
    @Override
    public void visit(RecordDeclaration n, DocPackage docPackage) {
        super.visit(n, docPackage);

        String description = JavadocUtils.extractDescription(
                n.getComment().filter(c -> c instanceof JavadocComment)
                              .map(JavadocComment.class::cast)
        );

        String visibility = extractVisibility(n);
        boolean isStatic = extractIsStatic(n);

        DocClass docClass = new DocClass(
                n.getNameAsString(),
                description,
                Kind.RECORD,
                visibility,
                isStatic
        );

        // records siempre extienden java.lang.Record
        docClass.setSuperClass("Record");

        // pueden implementar interfaces
        if (!n.getImplementedTypes().isEmpty()) {
            n.getImplementedTypes().forEach(t -> docClass.addInterface(t.getNameAsString()));
        }

        docPackage.addClass(docClass);

        n.getMembers().forEach(m -> {
            if (m instanceof MethodDeclaration method) visitMethod(method, docClass);
            if (m instanceof ConstructorDeclaration cons) visitConstructor(cons, docClass);
            if (m instanceof FieldDeclaration field) visitField(field, docClass);
        });
    }

    // ========== MÉTODOS PRIVADOS ========== //
    private void visitMethod(MethodDeclaration n, DocClass docClass) {
        String description = JavadocUtils.extractDescription(
                n.getComment().filter(c -> c instanceof JavadocComment)
                              .map(JavadocComment.class::cast)
        );

        List<DocParameter> docParams = new ArrayList<>();
        String returnDescription = null;
        List<DocException> exceptions = new ArrayList<>();

        Javadoc javadoc = extractJavadoc(n);

        if (javadoc != null) {
            for (var tag : javadoc.getBlockTags()) {
                switch (tag.getType()) {
                    case PARAM -> {
                        String paramName = tag.getName().orElse("unnamed");
                        String paramDesc = tag.getContent().toText();
                        docParams.add(new DocParameter(paramName, paramDesc));
                    }
                    case RETURN -> returnDescription = tag.getContent().toText();
                    case THROWS, EXCEPTION -> {
                        String excName = tag.getName().orElse("Exception");
                        String excDesc = tag.getContent().toText();
                        exceptions.add(new DocException(excName, excDesc));
                    }
                    default -> {}
                }
            }
        }

        String visibility = extractVisibility(n);
        boolean isStatic = extractIsStatic(n);
        boolean  isVoid = extractIsVoid(n);

        DocMethod docMethod = new DocMethod(
                n.getNameAsString(),
                n.getType().toString(),
                n.getParameters().stream().map(p -> p.getType() + " " + p.getName()).toList(),
                description,
                visibility,
                isStatic,
                isVoid
        );
        
        for (AnnotationExpr ae : n.getAnnotations()) {
            docMethod.addAnnotation(toDocAnnotation(ae));
        }

        docParams.forEach(docMethod::addDocParameter);
        if (returnDescription != null) docMethod.setReturnDescription(returnDescription);
        exceptions.forEach(docMethod::addException);

        docClass.addMethod(docMethod);
    }

    private void visitConstructor(ConstructorDeclaration n, DocClass docClass) {
        String description = JavadocUtils.extractDescription(
                n.getComment().filter(c -> c instanceof JavadocComment)
                              .map(JavadocComment.class::cast)
        );

        List<DocParameter> docParams = new ArrayList<>();
        List<DocException> exceptions = new ArrayList<>();

        Javadoc javadoc = extractJavadoc(n);
        if (javadoc != null) {
            for (var tag : javadoc.getBlockTags()) {
                switch (tag.getType()) {
                    case PARAM -> {
                        String paramName = tag.getName().orElse("unnamed");
                        String paramDesc = tag.getContent().toText();
                        docParams.add(new DocParameter(paramName, paramDesc));
                    }
                    case THROWS, EXCEPTION -> {
                        String excName = tag.getName().orElse("Exception");
                        String excDesc = tag.getContent().toText();
                        exceptions.add(new DocException(excName, excDesc));
                    }
                    default -> {}
                }
            }
        }

        List<String> params = n.getParameters().stream()
                .map(p -> p.getType().asString() + " " + p.getNameAsString())
                .toList();

        String visibility = extractVisibility(n);
        boolean isStatic = extractIsStatic(n);

        DocConstructor docConstructor = new DocConstructor(
                n.getNameAsString(),
                params,
                description,
                visibility,
                isStatic
        );

        docParams.forEach(docConstructor::addDocParameter);
        exceptions.forEach(docConstructor::addException);

        docClass.addConstructor(docConstructor);
    }

    private void visitField(FieldDeclaration n, DocClass docClass) {
        String description = JavadocUtils.extractDescription(
                n.getComment().filter(c -> c instanceof JavadocComment)
                              .map(JavadocComment.class::cast)
        );

        String visibility = extractVisibility(n);
        boolean isStatic = extractIsStatic(n);

        n.getVariables().forEach(var -> {
            String type = n.getElementType().asString().trim(); // 👈 aseguramos el formato limpio
            DocField docField = new DocField(
                    var.getNameAsString(),
                    type,
                    description,
                    visibility,
                    isStatic
            );
            docClass.addField(docField);
        });
    }

    // ========== UTILIDADES ========== //
    private Javadoc extractJavadoc(BodyDeclaration<?> n) {
        Optional<JavadocComment> javadocComment = n.getComment()
                .filter(c -> c instanceof JavadocComment)
                .map(JavadocComment.class::cast);
        return javadocComment.map(JavadocComment::parse).orElse(null);
    }

    /**
     * Extrae la visibilidad de un nodo que posea modificadores.
     *
     * @param n nodo del AST.
     * @return "public", "protected", "private" o "package-private".
     */
    private String extractVisibility(BodyDeclaration<?> n) {
        if (n instanceof NodeWithModifiers<?>) {
            NodeWithModifiers<?> withMods = (NodeWithModifiers<?>) n;
            if (withMods.hasModifier(Modifier.Keyword.PUBLIC)) return "public";
            if (withMods.hasModifier(Modifier.Keyword.PROTECTED)) return "protected";
            if (withMods.hasModifier(Modifier.Keyword.PRIVATE)) return "private";
        }
        return "package-private";
    }

    /**
     * Verifica si un nodo tiene el modificador {@code static}.
     *
     * @param n nodo del AST.
     * @return {@code true} si el nodo es estático, {@code false} en caso contrario.
     */
    private boolean extractIsStatic(BodyDeclaration<?> n) {
        if (n instanceof NodeWithModifiers<?>) {
            NodeWithModifiers<?> withMods = (NodeWithModifiers<?>) n;
            return withMods.hasModifier(Modifier.Keyword.STATIC);
        }
        return false;
    }
    
    private boolean extractIsVoid(MethodDeclaration n) {
        if (n.getType().isVoidType()) {            
            return true;
        }
        return false;
    }

    private void extractProjectNameAndDescription(ClassOrInterfaceDeclaration n, DocPackage docPackage, DocClass docClass) {
        Optional<JavadocComment> maybe = n.getComment()
                .filter(c -> c instanceof JavadocComment)
                .map(JavadocComment.class::cast);

        // 1) extraer @project completo (si existe)
        Optional<String> projectContentOpt = JavadocUtils.extractProjectTag(maybe);

        // 2) si hay @project, separar primera línea → projectName, resto → candidateDescription
        String candidateFromProject = null;
        if (projectContentOpt.isPresent()) {
            String projectContent = projectContentOpt.get(); // puede tener \n
            String[] parts = projectContent.split("\\R", 2); // primer salto de línea
            String projectName = parts[0].trim();
            if (!projectName.isEmpty() && (docPackage.getProjectName() == null || docPackage.getProjectName().isEmpty())) {
                docPackage.setProjectName(projectName);
            }
            if (parts.length > 1) {
                candidateFromProject = parts[1].trim();
            }
        }

        // 3) extraer descripción "normal" (preferida)
        String description = JavadocUtils.extractDescription(maybe);

        // 4) fallback: si no hay descripción y había texto extra en @project, usarlo
        if ((description == null || description.isEmpty()) && candidateFromProject != null && !candidateFromProject.isEmpty()) {
            description = candidateFromProject;
        }

        // 5) si aún vacío, ya que tenemos extractFullDescription, podemos considerar usarlo
        //    como último recurso (opcional). Aquí lo dejamos como no obligatorio.
        if (description == null || description.isEmpty()) {
            // opcional: comentar o activar según prefieras
            description = JavadocUtils.extractFullDescription(maybe);
        }

        if (description != null && !description.isEmpty()) {
            docClass.setDescription(description);
        }
    }    
    
    /**
     * Helper, ayuda a convertir AnnotationExpr -> DocAnnotation
     * 
     * @param a
     * @return 
     */
    private DocAnnotation toDocAnnotation(AnnotationExpr a) {
        String name = a.getNameAsString();
        String raw = a.toString();
        boolean marker = a instanceof MarkerAnnotationExpr;
        Map<String, String> members = new LinkedHashMap<>();
        List<String> values = new ArrayList<>();

        if (a instanceof SingleMemberAnnotationExpr) {
            SingleMemberAnnotationExpr s = (SingleMemberAnnotationExpr) a;
            values.add(renderExprAsString(s.getMemberValue()));
        } else if (a instanceof NormalAnnotationExpr) {
            NormalAnnotationExpr n = (NormalAnnotationExpr) a;
            for (MemberValuePair pair : n.getPairs()) {
                Expression v = pair.getValue();
                // Si el valor es un array inicializador, convertimos cada elemento
                if (v instanceof ArrayInitializerExpr) {
                    ArrayInitializerExpr arr = (ArrayInitializerExpr) v;
                    List<String> items = arr.getValues().stream()
                            .map(this::renderExprAsString)
                            .collect(Collectors.toList());
                    members.put(pair.getNameAsString(), "[" + String.join(", ", items) + "]");
                } else {
                    members.put(pair.getNameAsString(), renderExprAsString(v));
                }
            }
        }
        // Nota: fqName se deja a null por ahora (no resolvemos símbolos)
        return new DocAnnotation(name, raw, members, values, marker, null);
    }

// -------------------------------------------------------------
// Pequeña utilidad para normalizar Expression -> String
// -------------------------------------------------------------
    private String renderExprAsString(Expression expr) {
        if (expr == null) {
            return "";
        }
        // Tratar literales comunes para producir comillas limpias
        if (expr.isStringLiteralExpr()) {
            return "\"" + expr.asStringLiteralExpr().getValue() + "\"";
        } else if (expr.isCharLiteralExpr()) {
            return "'" + expr.asCharLiteralExpr().getValue() + "'";
        } else if (expr.isBooleanLiteralExpr()
                || expr.isIntegerLiteralExpr()
                || expr.isLongLiteralExpr()
                || expr.isDoubleLiteralExpr()) {
            return expr.toString();
        } else if (expr.isArrayInitializerExpr()) {
            ArrayInitializerExpr arr = expr.asArrayInitializerExpr();
            return "[" + arr.getValues().stream()
                    .map(this::renderExprAsString)
                    .collect(Collectors.joining(", ")) + "]";
        } else if (expr.isAnnotationExpr()) {
            // Renderizar anidada recursivamente (puede devolver @Inner(...))
            return toDocAnnotation(expr.asAnnotationExpr()).renderCompact();
        }
        // fallback: usar toString() para mantener la expresión tal como aparece
        return expr.toString();
    }   
    
}
