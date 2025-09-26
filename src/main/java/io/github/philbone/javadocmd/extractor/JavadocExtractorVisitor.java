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

public class JavadocExtractorVisitor extends VoidVisitorAdapter<DocPackage>
{

    @Override
    public void visit(ClassOrInterfaceDeclaration n, DocPackage docPackage) {
        super.visit(n, docPackage);

        String description = n.getComment()
                .filter(c -> c instanceof JavadocComment)
                .map(c -> ((JavadocComment) c).parse().getDescription().toText())
                .orElse("");

        String kind;
        if (n.isInterface()) {
            kind = "interface";
        } else if (n.isAbstract()) {
            kind = "abstract class";
        } else {
            kind = "class";
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

        DocClass docClass = new DocClass(n.getNameAsString(), description, "enum");
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

        DocClass docClass = new DocClass(n.getNameAsString(), description, "record");
        docPackage.addClass(docClass);

        n.getMembers().stream()
                .filter(m -> m instanceof MethodDeclaration)
                .map(m -> (MethodDeclaration) m)
                .forEach(m -> visitMethod(m, docClass));
    }

    private void visitMethod(MethodDeclaration n, DocClass docClass) {
        // Extraer Javadoc del método
        String description = "";
        Optional<JavadocComment> javadocComment = n.getComment()
                .filter(c -> c instanceof JavadocComment)
                .map(c -> (JavadocComment) c);
        if (javadocComment.isPresent()) {
            Javadoc javadoc = javadocComment.get().parse();
            description = javadoc.getDescription().toText();
        }

        DocMethod docMethod = new DocMethod(
                n.getNameAsString(),
                description,
                n.getParameters().stream()
                        .map(p -> p.getType() + " " + p.getName())
                        .toList(),
                n.getType().toString()
        );

        docClass.addMethod(docMethod);
    }
}
