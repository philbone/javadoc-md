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

- `public ` **void** `visit(ClassOrInterfaceDeclaration n, DocPackage docPackage)`
- `public ` **void** `visit(EnumDeclaration n, DocPackage docPackage)`
- `public ` **void** `visit(RecordDeclaration n, DocPackage docPackage)`
- `private ` **void** `visitMethod(MethodDeclaration n, DocClass docClass)`
- `private ` **void** `visitConstructor(ConstructorDeclaration n, DocClass docClass)`
- `private ` **void** `visitField(FieldDeclaration n, DocClass docClass)`
- `private `Javadoc `extractJavadoc(BodyDeclaration<?> n)`
- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `extractVisibility(BodyDeclaration<?> n)`
> Extrae la visibilidad de un nodo que posea modificadores.

> - *@param* **n** nodo del AST.
> - *@return* "public", "protected", "private" o "package-private".
- `private `boolean `extractIsStatic(BodyDeclaration<?> n)`
> Verifica si un nodo tiene el modificador {@code static}.

> - *@param* **n** nodo del AST.
> - *@return* {@code true} si el nodo es estático, {@code false} en caso contrario.
- `private `boolean `extractIsVoid(MethodDeclaration n)`
- `private ` **void** `extractProjectNameAndDescription(ClassOrInterfaceDeclaration n, DocPackage docPackage, DocClass docClass)`
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

- `private static` [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<String> `TECHNICAL_TAGS`
### 🧮 Métodos

- `private  static`Javadoc `parseCleaning(JavadocComment comment)`
- `public  static`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `extractDescription(Optional<JavadocComment> maybeComment)`
> Extrae la descripción "preferida" desde un JavadocComment.
> <p>
>  - Si existe texto libre en la parte de descripción (antes de las etiquetas), lo devuelve.
>  - Si no, intenta devolver el primer blockTag que no sea técnico
>    (por ejemplo: @since, @note, etc., siempre que no esté en TECHNICAL_TAGS).
>  - Si no encuentra nada, devuelve cadena vacía.

- `public  static`[Optional](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Optional.html)<String> `extractProjectTag(Optional<JavadocComment> maybeComment)`
> Extrae el contenido del primer tag {@code @project} (si existe).
> Devuelve Optional.empty() si no está presente.

- `public  static`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `extractFullDescription(Optional<JavadocComment> maybeComment)`
> Devuelve la "descripción completa" del Javadoc, incluyendo los block tags
> en forma textual, pero ignorando {@code @project}.

- `public  static`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `normalizeImages(String description)`
> Normaliza etiquetas HTML de imagen dentro de una descripción Javadoc.
> <p>
> Convierte etiquetas <img> en sintaxis Markdown:
> <pre>{@code
> ![Diagrama](docs/diagrama.png)
> →
> ![Diagrama](docs/diagrama.png)
> }</pre>
> 
> Si no se encuentra atributo {@code alt}, se usa cadena vacía.

> - *@param* **description** Texto a procesar (puede ser null)
> - *@return* descripción con imágenes convertidas a sintaxis Markdown.
