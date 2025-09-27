package io.github.philbone.javadocmd.extractor;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.RecordDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.ast.comments.JavadocComment;
import io.github.philbone.javadocmd.model.*;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

public class JavadocExtractorVisitor extends VoidVisitorAdapter<DocPackage>
{

    @Override
    public void visit(ClassOrInterfaceDeclaration n, DocPackage docPackage) {
        super.visit(n, docPackage);

        String description = n.getComment()
                .filter(c -> c instanceof JavadocComment)
                .map(c -> ((JavadocComment) c).parse().getDescription().toText())
                .orElse("");

        Kind kind;
        if (n.isInterface()) {
            kind = Kind.INTERFACE;
        } else if (n.isAbstract()) {
            kind = Kind.ABSTRACT_CLASS;
        } else {
            kind = Kind.CLASS;
        }

        DocClass docClass = new DocClass(n.getNameAsString(), description, kind);
        docPackage.addClass(docClass);

        n.getMethods().forEach(m -> visitMethod(m, docClass));
    }

    @Override
    public void visit(EnumDeclaration n, DocPackage docPackage) {
        super.visit(n, docPackage);

        String description = n.getComment()
                .filter(c -> c instanceof JavadocComment)
                .map(c -> ((JavadocComment) c).parse().getDescription().toText())
                .orElse("");

        DocClass docClass = new DocClass(n.getNameAsString(), description, Kind.ENUM);
        docPackage.addClass(docClass);

        n.getMembers().stream()
                .filter(m -> m instanceof MethodDeclaration)
                .map(m -> (MethodDeclaration) m)
                .forEach(m -> visitMethod(m, docClass));
    }

    @Override
    public void visit(RecordDeclaration n, DocPackage docPackage) {
        super.visit(n, docPackage);

        String description = n.getComment()
                .filter(c -> c instanceof JavadocComment)
                .map(c -> ((JavadocComment) c).parse().getDescription().toText())
                .orElse("");

        DocClass docClass = new DocClass(n.getNameAsString(), description, Kind.RECORD);
        docPackage.addClass(docClass);

        n.getMembers().stream()
                .filter(m -> m instanceof MethodDeclaration)
                .map(m -> (MethodDeclaration) m)
                .forEach(m -> visitMethod(m, docClass));
    }

    private void visitMethod(MethodDeclaration n, DocClass docClass) {
        String description = "";
        List<DocParameter> docParams = new ArrayList<>();
        String returnDescription = null;
        List<DocException> exceptions = new ArrayList<>();

        Optional<JavadocComment> javadocComment = n.getComment()
                .filter(c -> c instanceof JavadocComment)
                .map(c -> (JavadocComment) c);

        if (javadocComment.isPresent()) {
            Javadoc javadoc = javadocComment.get().parse();
            description = javadoc.getDescription().toText();

            // Procesar etiquetas con for clásico (más flexible a futuro)
            for (var tag : javadoc.getBlockTags()) {
                switch (tag.getType()) {
                    case PARAM -> {
                        String paramName = tag.getName().orElse("unnamed");
                        String paramDesc = tag.getContent().toText();
                        docParams.add(new DocParameter(paramName, paramDesc));
                    }
                    case RETURN -> {
                        returnDescription = tag.getContent().toText();
                    }
                    case THROWS, EXCEPTION -> {
                        String excName = tag.getName().orElse("Exception");
                        String excDesc = tag.getContent().toText();
                        exceptions.add(new DocException(excName, excDesc));
                    }
                    default -> {
                        // otras etiquetas se manejarán más adelante
                    }
                }
            }
        }

        // Crear DocMethod con la info básica
        DocMethod docMethod = new DocMethod(
                n.getNameAsString(),
                n.getType().toString(),
                n.getParameters().stream()
                        .map(p -> p.getType() + " " + p.getName())
                        .toList(),
                description
        );

        // Agregar detalles documentados
        docParams.forEach(docMethod::addDocParameter);
        if (returnDescription != null) {
            docMethod.setReturnDescription(returnDescription);
        }
        exceptions.forEach(docMethod::addException);

        docClass.addMethod(docMethod);
    }

}
