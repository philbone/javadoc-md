# JavadocMd

## io.github.philbone.javadocmd.extractor

## Resumen de Clases


|CLASE|DESCRIPCIÓN|
|---|---|
|[public class JavadocExtractorVisitor](#-public-class-javadocextractorvisitor)|Visitor encargado de recorrer los nodos del AST de JavaParser y construir el modelo intermedio para la documentación en Markdown.
|[public class JavadocUtils](#-public-class-javadocutils)|Utilidades para trabajar con JavadocComment de JavaParser.
## 📘 Public Class JavadocExtractorVisitor

```java
public class JavadocExtractorVisitor
extends VoidVisitorAdapter
```
> **Descripción:**
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

### 🧮 Métodos

- `public void visit(ClassOrInterfaceDeclaration n, DocPackage docPackage)`
- `public void visit(EnumDeclaration n, DocPackage docPackage)`
- `public void visit(RecordDeclaration n, DocPackage docPackage)`
- `private void visitMethod(MethodDeclaration n, DocClass docClass)`
- `private void visitConstructor(ConstructorDeclaration n, DocClass docClass)`
- `private void visitField(FieldDeclaration n, DocClass docClass)`
- `private Javadoc extractJavadoc(BodyDeclaration<?> n)`
- `private String extractVisibility(BodyDeclaration<?> n)`
> Extrae la visibilidad de un nodo que posea modificadores.

> - *@param* **n** nodo del AST.
> - *@return* "public", "protected", "private" o "package-private".
- `private boolean extractIsStatic(BodyDeclaration<?> n)`
> Verifica si un nodo tiene el modificador {@code static}.

> - *@param* **n** nodo del AST.
> - *@return* {@code true} si el nodo es estático, {@code false} en caso contrario.
- `private void extractProjectNameAndDescription(ClassOrInterfaceDeclaration n, DocPackage docPackage, DocClass docClass)`
## 📘 Public Class JavadocUtils

```java
public class JavadocUtils
```
> **Descripción:**
> Utilidades para trabajar con JavadocComment de JavaParser.
> <p>
> Provee métodos para:
> <ul>
>   <li>extraer una descripción "limpia" (heurística configurable),</li>
>   <li>extraer la etiqueta @project (si existe),</li>
>   <li>obtener una "descripción completa" que incluye los block tags (útil para debugging / fallback).</li>
> </ul>

### 📦 Campos

- `private static List<String> TECHNICAL_TAGS`
### 🧮 Métodos

- `private static Javadoc parseCleaning(JavadocComment comment)`
- `public static String extractDescription(Optional<JavadocComment> maybeComment)`
> Extrae la descripción "preferida" desde un JavadocComment.
> <p>
>  - Si existe texto libre en la parte de descripción (antes de las etiquetas), lo devuelve.
>  - Si no, intenta devolver el primer blockTag que no sea técnico
>    (por ejemplo: @since, @note, etc., siempre que no esté en TECHNICAL_TAGS).
>  - Si no encuentra nada, devuelve cadena vacía.

- `public static Optional<String> extractProjectTag(Optional<JavadocComment> maybeComment)`
> Extrae el contenido del primer tag {@code @project} (si existe).
> Devuelve Optional.empty() si no está presente.

- `public static String extractFullDescription(Optional<JavadocComment> maybeComment)`
> Devuelve la "descripción completa" del Javadoc, incluyendo los block tags
> en forma textual, pero ignorando {@code @project}.

