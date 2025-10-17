# JavadocMd

## io.github.philbone.javadocmd.exporter

## Resumen de Clases


|#|CLASE|DESCRIPCIÓN|
|---|---|---|
|**1**|[public interface DocExporter](#1-public-interface-docexporter)|
|**2**|[public class MarkdownExporter](#2-public-class-markdownexporter)|Exportador que genera documentación en formato Markdown a partir del modelo intermedio construido con {@link io.
|**3**|[public class MarkdownBuilder](#3-public-class-markdownbuilder)|
|**4**|[public class InternalLinker](#4-public-class-internallinker)|InternalLinker: convierte nombres de tipo en enlaces internos a la documentación generada por JavadocMd.
|**5**|[public class JavaApiLinker](#5-public-class-javaapilinker)|Utilidad para convertir nombres de tipos de Java en enlaces a la documentación oficial de la API de Java SE.
<details>
<summary> <strong> 📗 Public Interface DocExporter</strong> </summary>

## #1 📗 Public Interface DocExporter

```java
public interface DocExporter
```
### 🧮 Métodos

<details open><summary>Public</summary>

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `export(DocPackage docPackage)`
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>


</details>
<details>
<summary> <strong> 📘 Public Class MarkdownExporter</strong> </summary>

## #2 📘 Public Class MarkdownExporter

```java
public class MarkdownExporter
implements DocExporter
```
> **Descripción:**
> Exportador que genera documentación en formato Markdown
> a partir del modelo intermedio construido con
> {@link io.github.philbone.javadocmd.extractor.JavadocExtractorVisitor}.
> 
> <p>Renderiza:</p>
> <ul>
>     <li>Firma de la clase (visibilidad, static, tipo, nombre).</li>
>     <li>Extensiones ({@code extends}) e implementaciones ({@code implements}).</li>
>     <li>Descripción general de la clase.</li>
>     <li>Campos, constructores y métodos con sus firmas y documentación Javadoc.</li>
> </ul>

### 🧮 Métodos

<details open><summary>Public</summary>

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `export(DocPackage docPackage)`
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

- `private `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isPrintable(String visibility)`
> Determina si la visibilidad es imprimible según la configuración.

> - *@param* **visibility** la visiblidad a evaluar
> - *@return* true si la visibilidad es imprimible
- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `formatCodeOrLink(String type)`
> Si el tipo tiene enlace conocido, devuelve el link Markdown. Si no, lo envuelve en `code`.

- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `formatKind(Kind kind)`
- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `capitalize(String s)`
- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `formatEmoji(Kind kind)`
- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `printFields(DocClass docClass, String text)`
- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `printMethods(DocClass docClass, String text)`
- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `printIndexNumber(int indexOrder, boolean foreSign)`
</details>

### 🛠️ Constructores

- `public MarkdownExporter(Config config, InternalLinker internalLinker)`
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private static` [int](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Integer.html) `COLLAPSE_THRESHOLD`
> Número mínimo de clases dentro de un paquete para activar el modo colapsable.
> Si el paquete tiene más de este número, cada clase se renderiza dentro de un bloque `<details>`.

- `private static` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `VISIBILITY_PUBLIC`
- `private static` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `VISIBILITY_PRIVATE`
- `private static` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `VISIBILITY_PROTECTED`
- `private` [Config](Config.md) `config`
- `private` [int](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Integer.html) `totalMethodsCount`
- `private` [int](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Integer.html) `totalFieldsCount`
- `private` [JavaApiLinker](JavaApiLinker.md) `apiLinker`
- `private` [InternalLinker](InternalLinker.md) `internalLinker`
- `private` [DocPackage](DocPackage.md) `docPackage`
</details>


</details>
<details>
<summary> <strong> 📘 Public Class MarkdownBuilder</strong> </summary>

## #3 📘 Public Class MarkdownBuilder

```java
public class MarkdownBuilder
```
### 🧮 Métodos

<details open><summary>Public</summary>

- `public `[MarkdownBuilder](MarkdownBuilder.md) `title(String text)`
- `public `[MarkdownBuilder](MarkdownBuilder.md) `subtitle(String text)`
- `public `[MarkdownBuilder](MarkdownBuilder.md) `h3(String text)`
- `public `[MarkdownBuilder](MarkdownBuilder.md) `h4(String text)`
- `public `[MarkdownBuilder](MarkdownBuilder.md) `paragraph(String text)`
- `public `[MarkdownBuilder](MarkdownBuilder.md) `listItem(String text)`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `build()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `codeBlock(String content, String codeLang)`
- `public `[MarkdownBuilder](MarkdownBuilder.md) `blockquote(String text)`
- `public `[MarkdownBuilder](MarkdownBuilder.md) `tag(String tag)`
> Inyecta una etiqueta arbitraria directamente en el flujo del Markdown.
> <p>
> Se utiliza principalmente como auxiliar para aplicar prefijos en las
> siguientes líneas, por ejemplo:
> <ul>
> <li>{@code "> "} para iniciar un bloque de cita.</li>
> <li>{@code "- "} para forzar un ítem de lista.</li>
> <li>{@code ">> "} para una cita anidada.</li>
> </ul>
> </p>
> <p>
> Este método no agrega saltos de línea ni contenido adicional, únicamente
> inserta el texto indicado de forma literal.
> </p>

> - *@param* **tag** la etiqueta o prefijo a inyectar (se añade tal cual al
buffer).
> - *@return* la instancia actual de {@code MarkdownBuilder}, para encadenar
llamadas.
- `public `[MarkdownBuilder](MarkdownBuilder.md) `toc(DocPackage docPackage)`
- `public `[MarkdownBuilder](MarkdownBuilder.md) `insertAt(int index, String text)`
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `sanitizeDescription(String raw)`
</details>

### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private` StringBuilder `outPrint`
</details>


</details>
<details>
<summary> <strong> 📘 Public Class InternalLinker</strong> </summary>

## #4 📘 Public Class InternalLinker

```java
public class InternalLinker
```
> **Descripción:**
> InternalLinker: convierte nombres de tipo en enlaces internos a la
> documentación generada por JavadocMd.
> 
> - Soporta tipos simples y FQCNs.
> - Normaliza genéricos y arrays: List<Config> -> Config
> - Mapea simpleName -> fqn(s) para búsquedas rápidas.

### 🧮 Métodos

<details open><summary>Public</summary>

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `linkIfInternalType(String typeName)`
> Devuelve un enlace Markdown si el tipo pertenece al proyecto JavadocMd.
> Si no hay coincidencia, retorna null.

- `public `[int](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Integer.html) `size()`
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `normalizeTypeName(String raw)`
- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `extractSimpleName(String fqnOrSimple)`
- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `buildLink(String fqn)`
</details>

### 🛠️ Constructores

- `public InternalLinker(Set<String> internalClasses, String extension)`
- `public InternalLinker(Set<String> internalClasses, String extension, boolean debug)`
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private` [Set](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Set.html)<String> `internalClasses`
- `private` [Map](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Map.html)<String,List<String>> `simpleToFqns`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `extension`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `debug`
</details>


</details>
<details>
<summary> <strong> 📘 Public Class JavaApiLinker</strong> </summary>

## #5 📘 Public Class JavaApiLinker

```java
public class JavaApiLinker
```
> **Descripción:**
> Utilidad para convertir nombres de tipos de Java en enlaces
> a la documentación oficial de la API de Java SE.
> 
> <p>Ejemplo:</p>
> <pre>{@code
> JavaApiLinker.linkIfJavaType("List<String>");
> // → [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<String>
> }</pre>

### 🧮 Métodos

<details open><summary>Public</summary>

- `public  static`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `linkIfJavaType(String type)`
> Si el tipo pertenece al paquete estándar de Java (java.* o javax.*),
> devuelve un enlace Markdown al Javadoc oficial.
> De lo contrario, devuelve el tipo original sin enlace.

</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

- `private  static`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `fqcnToUrl(String fqcn)`
> Convierte un nombre de clase totalmente calificado en URL al Javadoc.

- `private  static`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `determineModule(String pkg)`
> Determina el módulo de Java donde reside un paquete.
> Esto cubre los módulos más usados en Java SE 17.

- `private  static`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `resolveToFQCN(String type)`
> Intenta mapear un tipo simple (como "List") a su nombre de clase completo.
> Solo incluye clases comunes de la API estándar.

</details>

### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private static` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `BASE_URL`
> Versión base de la documentación de Java.

- `private static` [Pattern](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Pattern.html) `GENERIC_PATTERN`
> Patrón para detectar tipos genéricos (por ejemplo, List<String>)

</details>


</details>


---

# JavadocMd

## io.github.philbone.javadocmd.extractor

## Resumen de Clases


|#|CLASE|DESCRIPCIÓN|
|---|---|---|
|**1**|[public class JavadocExtractorVisitor](#1-public-class-javadocextractorvisitor)|Visitor encargado de recorrer los nodos del AST de JavaParser y construir el modelo intermedio para la documentación en Markdown.
|**2**|[public class JavadocUtils](#2-public-class-javadocutils)|Utilidades para trabajar con JavadocComment de JavaParser.
## #1 📘 Public Class JavadocExtractorVisitor

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

<details open><summary>Public</summary>

- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `visit(ClassOrInterfaceDeclaration n, DocPackage docPackage)`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `visit(EnumDeclaration n, DocPackage docPackage)`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `visit(RecordDeclaration n, DocPackage docPackage)`
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

- `private ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `visitMethod(MethodDeclaration n, DocClass docClass)`
- `private ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `visitConstructor(ConstructorDeclaration n, DocClass docClass)`
- `private ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `visitField(FieldDeclaration n, DocClass docClass)`
- `private `Javadoc `extractJavadoc(BodyDeclaration<?> n)`
- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `extractVisibility(BodyDeclaration<?> n)`
> Extrae la visibilidad de un nodo que posea modificadores.

> - *@param* **n** nodo del AST.
> - *@return* "public", "protected", "private" o "package-private".
- `private `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `extractIsStatic(BodyDeclaration<?> n)`
> Verifica si un nodo tiene el modificador {@code static}.

> - *@param* **n** nodo del AST.
> - *@return* {@code true} si el nodo es estático, {@code false} en caso contrario.
- `private `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `extractIsVoid(MethodDeclaration n)`
- `private ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `extractProjectNameAndDescription(ClassOrInterfaceDeclaration n, DocPackage docPackage, DocClass docClass)`
</details>

## #2 📘 Public Class JavadocUtils

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

### 🧮 Métodos

<details open><summary>Public</summary>

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
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

- `private  static`Javadoc `parseCleaning(JavadocComment comment)`
</details>

### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private static` [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<String> `TECHNICAL_TAGS`
</details>



---

# JavadocMd

## io.github.philbone.javadocmd.model

## Resumen de Clases


|#|CLASE|DESCRIPCIÓN|
|---|---|---|
|**1**|[public class DocConstructor](#1-public-class-docconstructor)|Representa un constructor documentado dentro de una clase.
|**2**|[public class DocClass](#2-public-class-docclass)|Representa la definición de una clase, interfaz, enum o record dentro del modelo intermedio de documentación.
|**3**|[public class DocMethod](#3-public-class-docmethod)|
|**4**|[public class DocParameter](#4-public-class-docparameter)|@author Felipe M.
|**5**|[public class DocPackage](#5-public-class-docpackage)|Representa un paquete de Java dentro del modelo intermedio de documentación.
|**6**|[public enum Kind](#6-public-enum-kind)|
|**7**|[public class DocException](#7-public-class-docexception)|
|**8**|[public class DocField](#8-public-class-docfield)|Representa un campo (atributo) documentado dentro de una clase.
<details>
<summary> <strong> 📘 Public Class DocConstructor</strong> </summary>

## #1 📘 Public Class DocConstructor

```java
public class DocConstructor
```
> **Descripción:**
> Representa un constructor documentado dentro de una clase.

### 🧮 Métodos

<details open><summary>Public</summary>

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getName()`
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<String> `getParameters()`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getDescription()`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getVisibility()`
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isStatic()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `addDocParameter(DocParameter param)`
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocParameter> `getDocParameters()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `addException(DocException exception)`
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocException> `getExceptions()`
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

### 🛠️ Constructores

- `public DocConstructor(String name, List<String> parameters, String description, String visibility, boolean isStatic)`
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `name`
- `private` [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<String> `parameters`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `description`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `visibility`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isStatic`
- `private` [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocParameter> `docParameters`
- `private` [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocException> `exceptions`
</details>


</details>
<details>
<summary> <strong> 📘 Public Class DocClass</strong> </summary>

## #2 📘 Public Class DocClass

```java
public class DocClass
```
> **Descripción:**
> Representa la definición de una clase, interfaz, enum o record dentro del modelo intermedio de documentación.
> <p>
> Esta entidad encapsula la información esencial que puede obtenerse de una declaración de tipo en código fuente Java, incluyendo:
> </p>
> <ul>
>   <li>Nombre, visibilidad y tipo (clase, interfaz, enum, record, abstracta).</li>
>   <li>Descripción proveniente de la documentación Javadoc asociada.</li>
>   <li>Lista de campos, métodos y constructores.</li>
>   <li>Clase padre extendida y/o interfaces implementadas o extendidas.</li>
>   <li>Indicador de si la clase es estática.</li>
> </ul>
> <p>
> La información contenida en esta clase es utilizada por los exportadores (por ejemplo, {@code MarkdownExporter}) para generar documentación en distintos formatos.
> </p>

### 🧮 Métodos

<details open><summary>Public</summary>

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getName()`
> - *@return* el nombre simple de la clase.
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getDescription()`
> - *@return* la descripción tomada del Javadoc.
- `public `[Kind](Kind.md) `getKind()`
> - *@return* el tipo de elemento representado.
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getVisibility()`
> - *@return* la visibilidad del tipo (public, protected, etc.).
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isStatic()`
> - *@return* {@code true} si la clase fue declarada como estática.
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocField> `getFields()`
> - *@return* lista inmutable de campos de la clase.
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocMethod> `getMethods()`
> - *@return* lista inmutable de métodos de la clase.
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocConstructor> `getConstructors()`
> - *@return* lista inmutable de constructores de la clase.
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `addField(DocField field)`
> Agrega un campo al modelo de la clase.

> - *@param* **field** definición del campo
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `addMethod(DocMethod method)`
> Agrega un método al modelo de la clase.

> - *@param* **method** definición del método
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `addConstructor(DocConstructor constructor)`
> Agrega un constructor al modelo de la clase.

> - *@param* **constructor** definición del constructor
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getSuperClass()`
> - *@return* el nombre de la superclase, o {@code null} si no tiene.
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setSuperClass(String superClass)`
> Define la superclase de este tipo.

> - *@param* **superClass** nombre de la clase padre
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<String> `getInterfaces()`
> - *@return* lista de interfaces implementadas (clases) o extendidas (interfaces).
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `addInterface(String iface)`
> Agrega una interfaz implementada o extendida.

> - *@param* **iface** nombre de la interfaz
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setDescription(String description)`
- `public `[int](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Integer.html) `getIndexOrder()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setIndexOrder(int indexOrder)`
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

### 🛠️ Constructores

- `public DocClass(String name, String description, Kind kind, String visibility, boolean isStatic)`
> **Descripción:**
> Crea una nueva representación de clase en el modelo intermedio.

> - *@param* `name` nombre simple de la clase
> - *@param* `description` descripción principal (desde Javadoc)
> - *@param* `kind` tipo del elemento (clase, interfaz, enum, record)
> - *@param* `visibility` nivel de visibilidad (public, protected, package-private, private)
> - *@param* `isStatic` indica si la clase fue declarada como {@code static}
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private` [int](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Integer.html) `indexOrder`
> El número que tomará en la tabla de contenido

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `name`
> Nombre simple de la clase, interfaz, enum o record.

- `private` [Kind](Kind.md) `kind`
> Tipo de elemento representado (clase, interfaz, enum, record, abstracta).

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `visibility`
> Nivel de visibilidad del tipo (public, protected, package-private, private).

- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isStatic`
> Indica si el tipo ha sido declarado como {@code static}.

- `private` [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocField> `fields`
> Campos declarados dentro de la clase.

- `private` [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocMethod> `methods`
> Métodos declarados dentro de la clase.

- `private` [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocConstructor> `constructors`
> Constructores declarados dentro de la clase.

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `superClass`
> Nombre de la clase padre (superclase), si existe.

- `private` [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<String> `interfaces`
> Interfaces implementadas (clases) o extendidas (interfaces).

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `description`
> Descripción principal tomada del comentario Javadoc asociado.

</details>


</details>
<details>
<summary> <strong> 📘 Public Class DocMethod</strong> </summary>

## #3 📘 Public Class DocMethod

```java
public class DocMethod
```
### 🧮 Métodos

<details open><summary>Public</summary>

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getName()`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getReturnType()`
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<String> `getParameters()`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getDescription()`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getVisibility()`
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isStatic()`
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isVoid()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setReturnDescription(String returnDescription)`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getReturnDescription()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `addDocParameter(DocParameter param)`
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocParameter> `getDocParameters()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `addException(DocException exception)`
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocException> `getExceptions()`
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

### 🛠️ Constructores

- `public DocMethod(String name, String returnType, List<String> parameters, String description, String visibility, boolean isStatic, boolean isVoid)`
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `name`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `returnType`
- `private` [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<String> `parameters`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `description`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `visibility`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isStatic`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isVoid`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `returnDescription`
- `private` [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocParameter> `docParameters`
- `private` [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocException> `exceptions`
</details>


</details>
<details>
<summary> <strong> 📘 Public Class DocParameter</strong> </summary>

## #4 📘 Public Class DocParameter

```java
public class DocParameter
```
> **Descripción:**
> @author Felipe M. philbone@focused.cl

### 🧮 Métodos

<details open><summary>Public</summary>

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getName()`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getDescription()`
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

### 🛠️ Constructores

- `public DocParameter(String name, String description)`
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `name`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `description`
</details>


</details>
<details>
<summary> <strong> 📘 Public Class DocPackage</strong> </summary>

## #5 📘 Public Class DocPackage

```java
public class DocPackage
```
> **Descripción:**
> Representa un paquete de Java dentro del modelo intermedio de documentación.
> <p>
> Esta clase agrupa todas las {@link DocClass} (clases, interfaces, enums y records)
> pertenecientes a un mismo paquete, junto con su nombre.
> Es utilizada como unidad base por los exportadores para generar la documentación.
> </p>
> 
> <h2>Responsabilidades:</h2>
> <ul>
>   <li>Almacenar el nombre del paquete analizado.</li>
>   <li>Contener la colección de clases, interfaces, enums y records del paquete.</li>
>   <li>Proveer métodos para acceder y agregar clases al paquete.</li>
> </ul>
> 
> <h2>Uso típico:</h2>
> Un {@code DocPackage} se crea durante la fase de extracción de Javadoc
> y posteriormente es consumido por un {@code DocExporter} para generar la salida
> (por ejemplo, en formato Markdown).
> 
> <pre>{@code
> DocPackage pkg = new DocPackage("io.github.philbone.javadocmd.exporter");
> pkg.addClass(new DocClass("MarkdownExporter", "...", Kind.CLASS, "public", false));
> }</pre>

### 🧮 Métodos

<details open><summary>Public</summary>

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getName()`
> Obtiene el nombre del paquete.

> - *@return* nombre completo del paquete.
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocClass> `getClasses()`
> Devuelve la lista de clases, interfaces, enums y records que pertenecen al paquete.
> <p>
> La lista devuelta es la instancia interna; se recomienda usar
> {@link #addClass(DocClass)} para agregar elementos en lugar de modificarla directamente.
> </p>

> - *@return* lista de clases del paquete.
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `addClass(DocClass docClass)`
> Agrega una nueva clase, interfaz, enum o record al paquete.

> - *@param* **docClass** instancia de {@link DocClass} a agregar.
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setProjectName(String projectName)`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getProjectName()`
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

### 🛠️ Constructores

- `public DocPackage(String name)`
> **Descripción:**
> Crea un nuevo descriptor de paquete.

> - *@param* `name` nombre del paquete en notación estándar de Java.
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `name`
> Nombre completo del paquete (ejemplo: {@code io.github.philbone.javadocmd.exporter}).

- `private` [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocClass> `classes`
> Conjunto de clases, interfaces, enums y records pertenecientes al paquete.

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `projectName`
</details>


</details>
<details>
<summary> <strong> 📙 Public Enum Kind</strong> </summary>

## #6 📙 Public Enum Kind

```java
public enum Kind
```

</details>
<details>
<summary> <strong> 📘 Public Class DocException</strong> </summary>

## #7 📘 Public Class DocException

```java
public class DocException
```
### 🧮 Métodos

<details open><summary>Public</summary>

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getName()`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getDescription()`
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

### 🛠️ Constructores

- `public DocException(String name, String description)`
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `name`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `description`
</details>


</details>
<details>
<summary> <strong> 📘 Public Class DocField</strong> </summary>

## #8 📘 Public Class DocField

```java
public class DocField
```
> **Descripción:**
> Representa un campo (atributo) documentado dentro de una clase.

### 🧮 Métodos

<details open><summary>Public</summary>

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getName()`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getType()`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getDescription()`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getVisibility()`
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isStatic()`
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

### 🛠️ Constructores

- `public DocField(String name, String type, String description, String visibility, boolean isStatic)`
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `name`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `type`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `description`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `visibility`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isStatic`
</details>


</details>


---

# JavadocMD

## io.github.philbone.javadocmd.config

## Resumen de Clases


|#|CLASE|DESCRIPCIÓN|
|---|---|---|
|**1**|[public class ConfigLoader](#1-public-class-configloader)|Esta clase se encarga de detectar el fichero de configuración y cargar los datos si son encontrados.
|**2**|[public class Config](#2-public-class-config)|@author Felipe M.
## #1 📘 Public Class ConfigLoader

```java
public class ConfigLoader
```
> **Descripción:**
> Esta clase se encarga de detectar el fichero de configuración
> y cargar los datos si son encontrados.
> De otra manera cargará los valores por defecto.

### 🧮 Métodos

<details open><summary>Public</summary>

- `public  static`[Config](Config.md) `loadConfig()`
> Carga la configuración desde el archivo config.yml por defecto

> - *@return* un objeto con los datos de configuración iniciales.
- `public  static`[Config](Config.md) `loadConfig(String filePath)`
> Carga la configuración desde un archivo específico

> - *@param* **filePath** ruta del archivo de configuración
> - *@return* objeto Config con los datos cargados
- `public  static` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `saveConfig(Config config, String filePath)`
> Guarda la configuración en un archivo YAML

> - *@param* **config** objeto Config a guardar
> - *@param* **filePath** ruta del archivo destino
> - *@throws* **IOException** si ocurre error de escritura
- `public  static` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `saveConfig(Config config)`
> Guarda la configuración en el archivo por defecto config.yml

> - *@param* **config** objeto Config a guardar
> - *@throws* **IOException** si ocurre error de escritura
- `public  static`[Config](Config.md) `createDefaultConfig(String filePath)`
> Crea una configuración por defecto y la guarda en un archivo

> - *@param* **filePath** ruta del archivo destino
> - *@return* objeto Config creado
> - *@throws* **IOException** si ocurre error de escritura
- `public  static`[Config](Config.md) `createDefaultConfig()`
> Crea una configuración por defecto y la guarda en config.yml

> - *@return* objeto Config creado
> - *@throws* **IOException** si ocurre error de escritura
- `public  static`[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `configExists(String filePath)`
> Verifica si existe el archivo de configuración

> - *@param* **filePath** ruta a verificar
> - *@return* true si el archivo existe
- `public  static`[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `configExists()`
> Verifica si existe el archivo de configuración por defecto

> - *@return* true si config.yml existe
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

- `private  static`ObjectMapper `createObjectMapper()`
> Crea un ObjectMapper configurado para YAML con formato legible

> - *@return* ObjectMapper configurado
</details>

### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private static` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `DEFAULT_CONFIG_FILE`
</details>

## #2 📘 Public Class Config

```java
public class Config
```
> **Descripción:**
> @author Felipe M. <philbone@focused.cl>

### 🧮 Métodos

<details open><summary>Public</summary>

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getSourcePath()`
> Obtiene la ruta del código fuente a documentar.

> - *@return* el directorio donde se encuentran el código fuente.
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setSourcePath(String sourcePath)`
> - *@param* **sourcePath** 
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getOutputPath()`
> - *@return* 
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setOutputPath(String outputPath)`
> - *@param* **outputPath** 
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getOutFileName()`
> - *@return* 
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setOutFileName(String outFileName)`
> - *@param* **outFileName** 
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isDebugMode()`
> - *@return* 
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setDebugMode(boolean debugMode)`
> - *@param* **debugMode** 
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isCombinePackagesMode()`
> - *@return* 
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setCombinePackagesMode(boolean combinePackages)`
> - *@param* **combinePackages** 
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isIncludePrivate()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setIncludePrivate(boolean includePrivate)`
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isIncludeProtected()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setIncludeProtected(boolean includeProtected)`
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isIncludePublic()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setIncludePublic(boolean includePublic)`
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isTableOfContent()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setTableOfContent(boolean tableOfContent)`
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isPrintEmptyNotify()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setPrintEmptyNotify(boolean printEmptyNotify)`
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isPrintClassIndex()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setPrintClassIndex(boolean printClassIndex)`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getForeSignClassIndex()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setForeSignClassIndex(String foreSignClassIndex)`
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isForeSignClassIndexOnDetails()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setForeSignClassIndexOnDetails(boolean foreSignClassIndexOnDetails)`
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isForeSignClassIndexOnSubtitle()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setForeSignClassIndexOnSubtitle(boolean foreSignClassIndexOnSubtitle)`
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

### 🛠️ Constructores

- `public Config()`
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `sourcePath`
> Directorio de entrada donde se encuentran las clases a documentar. *

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `outputPath`
> Directorio base donde se escribirá la documentación generada.

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `outFileName`
> Nombre del archivo de salida que contendrá la documentación en cada
> paquete.

- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `debugMode`
> Bandera de depuración para imprimir trazas adicionales.

- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `combinePackagesMode`
> Bandera para definir el modo de exportación
> false exportar un fichero por cada paquete
> true exportar un fichero de forma global.

- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `includePrivate`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `includeProtected`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `includePublic`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `tableOfContent`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `printEmptyNotify`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `printClassIndex`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `foreSignClassIndex`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `foreSignClassIndexOnDetails`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `foreSignClassIndexOnSubtitle`
</details>



---

## io.github.philbone.javadocmd.cli

## Resumen de Clases


|#|CLASE|DESCRIPCIÓN|
|---|---|---|
|**1**|[public class JavadocmdCLI](#1-public-class-javadocmdcli)|
|**2**|[public class InitCommand](#2-public-class-initcommand)|
## #1 📘 Public Class JavadocmdCLI

```java
public class JavadocmdCLI
implements Callable
```
### 🧮 Métodos

<details open><summary>Public</summary>

- `public `Integer `call()`
- `public  static` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `main(String[] args)`
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

## #2 📘 Public Class InitCommand

```java
public class InitCommand
implements Callable
```
### 🧮 Métodos

<details open><summary>Public</summary>

- `public `Integer `call()`
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `helpRequested`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `sourcePath`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `outputPath`
</details>



---

# JavadocMD

## io.github.philbone.javadocmd

## Resumen de Clases


|#|CLASE|DESCRIPCIÓN|
|---|---|---|
|**1**|[public abstract class JavadocMd](#1-public-abstract-class-javadocmd)|Punto de entrada principal del programa javadoc-md .
## #1 📕 Public Abstract Class JavadocMd

```java
public abstract class JavadocMd
```
> **Descripción:**
> Punto de entrada principal del programa <b>javadoc-md</b>.
> <p>
> Esta clase se encarga de:
> <ul>
> <li>Recorrer los archivos fuente de un proyecto Java.</li>
> <li>Analizar sus clases, interfaces, enums y records.</li>
> <li>Extraer la documentación Javadoc mediante
> {@link JavadocExtractorVisitor}.</li>
> <li>Generar documentación en formato Markdown usando un
> {@link DocExporter}.</li>
> </ul>
> 
> <p>
> Actualmente soporta la exportación de documentación hacia un archivo
> <code>README.md</code> por cada paquete encontrado en el proyecto.</p>

### 🧮 Métodos

<details open><summary>Public</summary>

- `public  static` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `main(String[] args)`
> Método principal que inicia el proceso de generación de documentación.

> - *@param* **args** argumentos opcionales (no utilizados actualmente). Se planea
en futuras versiones aceptar <code>sourcePath</code> y
<code>outputPath</code> como parámetros desde consola.
- `public  static` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `generatePackageDocs(Config config)`
- `public  static` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `generateCombinedDocs(Config config)`
- `public  static` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `forceJavaLevel(ParserConfiguration.LanguageLevel languageLevel)`
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

### 🛠️ Constructores

- `protected JavadocMd()`
> **Descripción:**
> Constructor protegido por defecto.
> <p>
> Inicializa valores de configuración básicos.

> - *@throws* **IllegalStateException** si la configuración inicial es inválida.
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private static` [int](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Integer.html) `executionCount`
> Contador global de ejecuciones del generador de documentación.

</details>



---

