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

public class JavadocExtractorVisitor extends VoidVisitorAdapter<DocPackage> {

    @Override
    public void visit(ClassOrInterfaceDeclaration n, DocPackage docPackage) {
        super.visit(n, docPackage);

        String description = extractDescription(n);
        Kind kind = n.isInterface()
                ? Kind.INTERFACE
                : (n.isAbstract() ? Kind.ABSTRACT_CLASS : Kind.CLASS);

        String visibility = extractVisibility(n);
        boolean isStatic = extractIsStatic(n);

        DocClass docClass = new DocClass(n.getNameAsString(), description, kind, visibility, isStatic);
        docPackage.addClass(docClass);

        // métodos, constructores y campos
        n.getMethods().forEach(m -> visitMethod(m, docClass));
        n.getConstructors().forEach(c -> visitConstructor(c, docClass));
        n.getFields().forEach(f -> visitField(f, docClass));
    }

    @Override
    public void visit(EnumDeclaration n, DocPackage docPackage) {
        super.visit(n, docPackage);

        String description = extractDescription(n);
        String visibility = extractVisibility(n);
        boolean isStatic = extractIsStatic(n);

        DocClass docClass = new DocClass(n.getNameAsString(), description, Kind.ENUM, visibility, isStatic);
        docPackage.addClass(docClass);

        n.getMembers().forEach(m -> {
            if (m instanceof MethodDeclaration method) visitMethod(method, docClass);
            if (m instanceof ConstructorDeclaration cons) visitConstructor(cons, docClass);
            if (m instanceof FieldDeclaration field) visitField(field, docClass);
        });
    }

    @Override
    public void visit(RecordDeclaration n, DocPackage docPackage) {
        super.visit(n, docPackage);

        String description = extractDescription(n);
        String visibility = extractVisibility(n);
        boolean isStatic = extractIsStatic(n);

        DocClass docClass = new DocClass(n.getNameAsString(), description, Kind.RECORD, visibility, isStatic);
        docPackage.addClass(docClass);

        n.getMembers().forEach(m -> {
            if (m instanceof MethodDeclaration method) visitMethod(method, docClass);
            if (m instanceof ConstructorDeclaration cons) visitConstructor(cons, docClass);
            if (m instanceof FieldDeclaration field) visitField(field, docClass);
        });
    }

    // ---------- Métodos privados ---------- //
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

        DocMethod docMethod = new DocMethod(
                n.getNameAsString(),
                n.getType().toString(),
                n.getParameters().stream().map(p -> p.getType() + " " + p.getName()).toList(),
                description,
                visibility,
                isStatic
        );

        docParams.forEach(docMethod::addDocParameter);
        if (returnDescription != null) docMethod.setReturnDescription(returnDescription);
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
                    default -> {}
                }
            }
        }

        List<String> params = n.getParameters().stream()
                .map(p -> p.getType().asString() + " " + p.getNameAsString())
                .toList();

        String visibility = extractVisibility(n);
        boolean isStatic = extractIsStatic(n); // constructors normally are not static, but consistent API

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

    // ---------- Utilidades ---------- //
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
     * 
     * @param n
     * @return String la visibilidad del nodo. En nodos que no implementen NodeWithModifiers (casos raros), devuelve "package-private"
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
     * 
     * @param n
     * @return boolean En nodos que no implementen NodeWithModifiers (casos raros), devuelve false para static
     */
    private boolean extractIsStatic(BodyDeclaration<?> n) {
        if (n instanceof NodeWithModifiers<?>) {
            NodeWithModifiers<?> withMods = (NodeWithModifiers<?>) n;
            return withMods.hasModifier(Modifier.Keyword.STATIC);
        }
        return false;
    }
}
