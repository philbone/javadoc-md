# `io.github.philbone.javadocmd.extractor`

## 📘 Public Class JavadocExtractorVisitor

```java
public class JavadocExtractorVisitor
extends VoidVisitorAdapter
```
> **Descripción**
> Visitor encargado de recorrer los nodos del AST de JavaParser y construir el
> modelo intermedio para la documentación en Markdown.
> 
> <p>Este visitor:</p>
> <ul>
>   <li>Extrae descripciones de Javadoc.</li>
>   <li>Determina visibilidad y si un miembro es {@code static}.</li>
>   <li>Procesa herencia ({@code extends}) e interfaces ({@code implements}).</li>
>   <li>Registra métodos, constructores y campos de cada clase.</li>
> </ul>

## 🧮 Métodos

- #### `public void visit(ClassOrInterfaceDeclaration n, DocPackage docPackage)`
- #### `public void visit(EnumDeclaration n, DocPackage docPackage)`
- #### `public void visit(RecordDeclaration n, DocPackage docPackage)`
- #### `private void visitMethod(MethodDeclaration n, DocClass docClass)`
- #### `private void visitConstructor(ConstructorDeclaration n, DocClass docClass)`
- #### `private void visitField(FieldDeclaration n, DocClass docClass)`
- #### `private String extractDescription(BodyDeclaration<?> n)`
- #### `private Javadoc extractJavadoc(BodyDeclaration<?> n)`
- #### `private String extractVisibility(BodyDeclaration<?> n)`
> Extrae la visibilidad de un nodo que posea modificadores.

> - *@param* **n** nodo del AST.
> *@return* "public", "protected", "private" o "package-private".

- #### `private boolean extractIsStatic(BodyDeclaration<?> n)`
> Verifica si un nodo tiene el modificador {@code static}.

> - *@param* **n** nodo del AST.
> *@return* {@code true} si el nodo es estático, {@code false} en caso contrario.

