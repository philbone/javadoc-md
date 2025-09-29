package io.github.philbone.javadocmd.extractor;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.nodeTypes.NodeWithModifiers;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.ast.comments.JavadocComment;
import io.github.philbone.javadocmd.model.*;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

/**
 * Visitor encargado de recorrer los nodos del AST de JavaParser y construir el
 * modelo intermedio para la documentación en Markdown.
 * <p>
 * Extrae descripciones, visibilidad, modificadores, herencia, interfaces,
 * métodos, constructores y campos.
 * </p>
 */
public class JavadocExtractorVisitor extends VoidVisitorAdapter<DocPackage>
{

    // ========== CLASES E INTERFACES ========== //
    @Override
    public void visit(ClassOrInterfaceDeclaration n, DocPackage docPackage) {
        super.visit(n, docPackage);

        String description = extractDescription(n);
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

        // herencia e interfaces
        // Procesar extends
        if (!n.getExtendedTypes().isEmpty()) {
            // Java no soporta múltiples extends en clases, pero en interfaces sí
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
    }

    // ========== ENUMS ========== //
    @Override
    public void visit(EnumDeclaration n, DocPackage docPackage) {
        super.visit(n, docPackage);

        String description = extractDescription(n);
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
        // Procesar implements
        if (!n.getImplementedTypes().isEmpty()) {
            n.getImplementedTypes().forEach(t -> docClass.addInterface(t.getNameAsString()));
        }

        docPackage.addClass(docClass);

        n.getMembers().forEach(m -> {
            if (m instanceof MethodDeclaration method) {
                visitMethod(method, docClass);
            }
            if (m instanceof ConstructorDeclaration cons) {
                visitConstructor(cons, docClass);
            }
            if (m instanceof FieldDeclaration field) {
                visitField(field, docClass);
            }
        });
    }

    // ========== RECORDS ========== //
    @Override
    public void visit(RecordDeclaration n, DocPackage docPackage) {
        super.visit(n, docPackage);

        String description = extractDescription(n);
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
        // Procesar implements
        if (!n.getImplementedTypes().isEmpty()) {
            n.getImplementedTypes().forEach(t -> docClass.addInterface(t.getNameAsString()));
        }

        docPackage.addClass(docClass);

        n.getMembers().forEach(m -> {
            if (m instanceof MethodDeclaration method) {
                visitMethod(method, docClass);
            }
            if (m instanceof ConstructorDeclaration cons) {
                visitConstructor(cons, docClass);
            }
            if (m instanceof FieldDeclaration field) {
                visitField(field, docClass);
            }
        });
    }

    // ========== MÉTODOS PRIVADOS ========== //
    private void visitMethod(MethodDeclaration n, DocClass docClass) {
        String description = "";
        List<DocParameter> docParams = new ArrayList<>();
        String returnDescription = null;
        List<DocException> exceptions = new ArrayList<>();

        Javadoc javadoc = extractJavadoc(n);

        if (javadoc != null) {
            description = javadoc.getDescription().toText();
            for (var tag : javadoc.getBlockTags()) {
                switch (tag.getType()) {
                    case PARAM -> {
                        String paramName = tag.getName().orElse("unnamed");
                        String paramDesc = tag.getContent().toText();
                        docParams.add(new DocParameter(paramName, paramDesc));
                    }
                    case RETURN ->
                        returnDescription = tag.getContent().toText();
                    case THROWS, EXCEPTION -> {
                        String excName = tag.getName().orElse("Exception");
                        String excDesc = tag.getContent().toText();
                        exceptions.add(new DocException(excName, excDesc));
                    }
                    default -> {
                    }
                }
            }
        }

        String visibility = extractVisibility(n);
        boolean isStatic = extractIsStatic(n);

        DocMethod docMethod = new DocMethod(
                n.getNameAsString(),
                n.getType().toString(),
                n.getParameters().stream().map(p -> p.getType() + " " + p.getName()).toList(),
                description,
                visibility,
                isStatic
        );

        docParams.forEach(docMethod::addDocParameter);
        if (returnDescription != null) {
            docMethod.setReturnDescription(returnDescription);
        }
        exceptions.forEach(docMethod::addException);

        docClass.addMethod(docMethod);
    }

    private void visitConstructor(ConstructorDeclaration n, DocClass docClass) {
        String description = "";
        List<DocParameter> docParams = new ArrayList<>();
        List<DocException> exceptions = new ArrayList<>();

        Javadoc javadoc = extractJavadoc(n);
        if (javadoc != null) {
            description = javadoc.getDescription().toText();
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
                    default -> {
                    }
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
        String description = extractDescription(n);
        String visibility = extractVisibility(n);
        boolean isStatic = extractIsStatic(n);

        n.getVariables().forEach(var -> {
            DocField docField = new DocField(
                    var.getNameAsString(),
                    n.getElementType().toString(),
                    description,
                    visibility,
                    isStatic
            );
            docClass.addField(docField);
        });
    }

    // ========== UTILIDADES ========== //
    private String extractDescription(BodyDeclaration<?> n) {
        return n.getComment()
                .filter(c -> c instanceof JavadocComment)
                .map(c -> ((JavadocComment) c).parse().getDescription().toText())
                .orElse("");
    }

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
            if (withMods.hasModifier(Modifier.Keyword.PUBLIC)) {
                return "public";
            }
            if (withMods.hasModifier(Modifier.Keyword.PROTECTED)) {
                return "protected";
            }
            if (withMods.hasModifier(Modifier.Keyword.PRIVATE)) {
                return "private";
            }
        }
        return "package-private";
    }

    /**
     * Verifica si un nodo tiene el modificador {@code static}.
     *
     * @param n nodo del AST.
     * @return {@code true} si el nodo es estático, {@code false} en caso
     * contrario.
     */
    private boolean extractIsStatic(BodyDeclaration<?> n) {
        if (n instanceof NodeWithModifiers<?>) {
            NodeWithModifiers<?> withMods = (NodeWithModifiers<?>) n;
            return withMods.hasModifier(Modifier.Keyword.STATIC);
        }
        return false;
    }
}
