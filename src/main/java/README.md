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

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `export(DocPackage docPackage)`<br>
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

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `export(DocPackage docPackage)`<br><sub>@Override</sub>
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

- `private `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isPrintable(String visibility)`<br>
> Determina si la visibilidad es imprimible según la configuración.

> - *@param* **visibility** la visiblidad a evaluar
> - *@return* true si la visibilidad es imprimible
- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `formatCodeOrLink(String type)`<br>
> Si el tipo tiene enlace conocido, devuelve el link Markdown. Si no, lo envuelve en `code`.

- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `formatKind(Kind kind)`<br>
- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `capitalize(String s)`<br>
- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `formatEmoji(Kind kind)`<br>
- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `printFields(DocClass docClass, String text)`<br>
- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `printMethods(DocClass docClass, String text)`<br>
- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `printIndexNumber(int indexOrder, boolean foreSign)`<br>
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
- `private` **Config** `config`
- `private` [int](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Integer.html) `totalMethodsCount`
- `private` [int](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Integer.html) `totalFieldsCount`
- `private` **JavaApiLinker** `apiLinker`
- `private` **InternalLinker** `internalLinker`
- `private` **DocPackage** `docPackage`
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

- `public `**MarkdownBuilder** `title(String text)`<br>
- `public `**MarkdownBuilder** `subtitle(String text)`<br>
- `public `**MarkdownBuilder** `h3(String text)`<br>
- `public `**MarkdownBuilder** `h4(String text)`<br>
- `public `**MarkdownBuilder** `paragraph(String text)`<br>
- `public `**MarkdownBuilder** `listItem(String text)`<br>
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `build()`<br>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `codeBlock(String content, String codeLang)`<br>
- `public `**MarkdownBuilder** `blockquote(String text)`<br>
- `public `**MarkdownBuilder** `sub(String text)`<br>
- `public `**MarkdownBuilder** `tag(String tag)`<br>
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
- `public `**MarkdownBuilder** `toc(DocPackage docPackage)`<br>
- `public `**MarkdownBuilder** `insertAt(int index, String text)`<br>
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `sanitizeDescription(String raw)`<br>
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

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `linkIfInternalType(String typeName)`<br>
> Devuelve un enlace Markdown si el tipo pertenece al proyecto JavadocMd.
> Si no hay coincidencia, retorna null.

- `public `[int](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Integer.html) `size()`<br>
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `normalizeTypeName(String raw)`<br><sub>@Deprecated</sub>
- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `extractSimpleName(String fqnOrSimple)`<br>
- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `buildLink(String fqn)`<br>
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
- `private` ResourceBundle `appMessages`
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

- `public  static`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `linkIfJavaType(String type)`<br>
> Si el tipo pertenece al paquete estándar de Java (java.* o javax.*),
> devuelve un enlace Markdown al Javadoc oficial.
> De lo contrario, devuelve el tipo original sin enlace.

</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

- `private  static`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `fqcnToUrl(String fqcn)`<br>
> Convierte un nombre de clase totalmente calificado en URL al Javadoc.

- `private  static`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `determineModule(String pkg)`<br>
> Determina el módulo de Java donde reside un paquete.
> Esto cubre los módulos más usados en Java SE 17.

- `private  static`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `resolveToFQCN(String type)`<br>
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

## io.github.philbone.javadocmd.i18n

## Resumen de Clases


|#|CLASE|DESCRIPCIÓN|
|---|---|---|
|**1**|[public class LanguageManager](#1-public-class-languagemanager)|Gestiona la carga y acceso a textos traducidos para la documentación.
## #1 📘 Public Class LanguageManager

```java
public class LanguageManager
```
> **Descripción:**
> Gestiona la carga y acceso a textos traducidos para la documentación.
> Usa archivos YAML externos ubicados en la carpeta "langs/".
> 
> Si no se encuentra el idioma solicitado, carga inglés por defecto.
> Si falta alguna clave, devuelve la clave original como fallback.
> 
> Ejemplo de uso:
> LanguageManager lang = new LanguageManager("es", Paths.get("."));
> System.out.println(lang.get("classes")); // → "Clases"

### 🧮 Métodos

<details open><summary>Public</summary>

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `get(String key)`<br>
> Obtiene el texto traducido asociado a una clave.
> Si no existe, devuelve la propia clave.

- `public `ResourceBundle `getAppMessages()`<br>
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

- `private `[Map](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Map.html)<String, String> `loadLanguage(String langCode, Path basePath)`<br><sub>@SuppressWarnings("unchecked")</sub>
- `private `[Map](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Map.html)<String, String> `loadDefaultLanguage(ObjectMapper mapper, Path basePath)`<br><sub>@SuppressWarnings("unchecked")</sub>
- `private `ObjectMapper `createObjectMapper()`<br>
</details>

### 🛠️ Constructores

- `public LanguageManager(String langCode, Path basePath)`
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private static` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `DEFAULT_LANG`
- `private static` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `LANG_DIR`
- `private` [Map](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Map.html)<String,String> `labels`
- `private` ResourceBundle `appMessages`
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

- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `visit(ClassOrInterfaceDeclaration n, DocPackage docPackage)`<br><sub>@Override</sub>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `visit(EnumDeclaration n, DocPackage docPackage)`<br><sub>@Override</sub>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `visit(RecordDeclaration n, DocPackage docPackage)`<br><sub>@Override</sub>
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

- `private ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `visitMethod(MethodDeclaration n, DocClass docClass)`<br>
- `private ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `visitConstructor(ConstructorDeclaration n, DocClass docClass)`<br>
- `private ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `visitField(FieldDeclaration n, DocClass docClass)`<br>
- `private `Javadoc `extractJavadoc(BodyDeclaration<?> n)`<br>
- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `extractVisibility(BodyDeclaration<?> n)`<br>
> Extrae la visibilidad de un nodo que posea modificadores.

> - *@param* **n** nodo del AST.
> - *@return* "public", "protected", "private" o "package-private".
- `private `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `extractIsStatic(BodyDeclaration<?> n)`<br>
> Verifica si un nodo tiene el modificador {@code static}.

> - *@param* **n** nodo del AST.
> - *@return* {@code true} si el nodo es estático, {@code false} en caso contrario.
- `private `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `extractIsVoid(MethodDeclaration n)`<br>
- `private ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `extractProjectNameAndDescription(ClassOrInterfaceDeclaration n, DocPackage docPackage, DocClass docClass)`<br>
- `private `**DocAnnotation** `toDocAnnotation(AnnotationExpr a)`<br>
> Helper, ayuda a convertir AnnotationExpr -> DocAnnotation

> - *@param* **a** 
> - *@return* 
- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `renderExprAsString(Expression expr)`<br>
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

- `public  static`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `extractDescription(Optional<JavadocComment> maybeComment)`<br>
> Extrae la descripción "preferida" desde un JavadocComment.
> <p>
>  - Si existe texto libre en la parte de descripción (antes de las etiquetas), lo devuelve.
>  - Si no, intenta devolver el primer blockTag que no sea técnico
>    (por ejemplo: @since, @note, etc., siempre que no esté en TECHNICAL_TAGS).
>  - Si no encuentra nada, devuelve cadena vacía.

- `public  static`[Optional](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Optional.html)<String> `extractProjectTag(Optional<JavadocComment> maybeComment)`<br>
> Extrae el contenido del primer tag {@code @project} (si existe).
> Devuelve Optional.empty() si no está presente.

- `public  static`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `extractFullDescription(Optional<JavadocComment> maybeComment)`<br>
> Devuelve la "descripción completa" del Javadoc, incluyendo los block tags
> en forma textual, pero ignorando {@code @project}.

- `public  static`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `normalizeImages(String description)`<br>
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

- `private  static`Javadoc `parseCleaning(JavadocComment comment)`<br>
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
|**1**|[public class DocAnnotation](#1-public-class-docannotation)|Representación ligera de una anotación Java extraída del AST.
|**2**|[public class DocConstructor](#2-public-class-docconstructor)|Representa un constructor documentado dentro de una clase.
|**3**|[public class DocClass](#3-public-class-docclass)|Representa la definición de una clase, interfaz, enum o record dentro del modelo intermedio de documentación.
|**4**|[public class DocMethod](#4-public-class-docmethod)|Representa un método en el modelo intermedio de documentación.
|**5**|[public class DocParameter](#5-public-class-docparameter)|@author Felipe M.
|**6**|[public class DocPackage](#6-public-class-docpackage)|Representa un paquete de Java dentro del modelo intermedio de documentación.
|**7**|[public enum Kind](#7-public-enum-kind)|
|**8**|[public class DocException](#8-public-class-docexception)|
|**9**|[public class DocField](#9-public-class-docfield)|Representa un campo (atributo) documentado dentro de una clase.
<details>
<summary> <strong> 📘 Public Class DocAnnotation</strong> </summary>

## #1 📘 Public Class DocAnnotation

```java
public class DocAnnotation
```
> **Descripción:**
> Representación ligera de una anotación Java extraída del AST.
> Se guarda la información mínima necesaria para renderizar y para
> posibles mejoras futuras (resolución de FQNs, etc).
> 
> Diseñada para integrarse en DocClass, DocMethod, DocField, ...
> (para la primera entrega se añadirá en DocClass y DocMethod).

### 🧮 Métodos

<details open><summary>Public</summary>

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getName()`<br>
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getRaw()`<br>
- `public `[Map](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Map.html)<String, String> `getMembers()`<br>
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<String> `getValues()`<br>
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isMarker()`<br>
- `public `[Optional](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Optional.html)<String> `getFqName()`<br>
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `renderCompact()`<br>
> Render compacto (inline) por defecto, ej:

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `toString()`<br><sub>@Override</sub>
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `equals(Object o)`<br><sub>@Override</sub>
- `public `[int](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Integer.html) `hashCode()`<br><sub>@Override</sub>
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

### 🛠️ Constructores

- `public DocAnnotation(String name, String raw, Map<String,String> members, List<String> values, boolean marker, String fqName)`
- `public DocAnnotation(String name, String raw)`
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `name`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `raw`
- `private` [Map](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Map.html)<String,String> `members`
- `private` [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<String> `values`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `marker`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `fqName`
</details>


</details>
<details>
<summary> <strong> 📘 Public Class DocConstructor</strong> </summary>

## #2 📘 Public Class DocConstructor

```java
public class DocConstructor
```
> **Descripción:**
> Representa un constructor documentado dentro de una clase.

### 🧮 Métodos

<details open><summary>Public</summary>

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getName()`<br>
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<String> `getParameters()`<br>
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getDescription()`<br>
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getVisibility()`<br>
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isStatic()`<br>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `addDocParameter(DocParameter param)`<br>
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocParameter> `getDocParameters()`<br>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `addException(DocException exception)`<br>
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocException> `getExceptions()`<br>
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

## #3 📘 Public Class DocClass

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

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getName()`<br>
> - *@return* el nombre simple de la clase.
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getDescription()`<br>
> - *@return* la descripción tomada del Javadoc.
- `public `**Kind** `getKind()`<br>
> - *@return* el tipo de elemento representado.
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getVisibility()`<br>
> - *@return* la visibilidad del tipo (public, protected, etc.).
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isStatic()`<br>
> - *@return* {@code true} si la clase fue declarada como estática.
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocField> `getFields()`<br>
> - *@return* lista inmutable de campos de la clase.
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocMethod> `getMethods()`<br>
> - *@return* lista inmutable de métodos de la clase.
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocConstructor> `getConstructors()`<br>
> - *@return* lista inmutable de constructores de la clase.
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocAnnotation> `getAnnotations()`<br>
> - *@return* lista de anotaciones aplicadas sobre la clase.
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `addAnnotation(DocAnnotation annotation)`<br>
> Agrega una anotación al modelo de la clase.

> - *@param* **annotation** anotación a añadir
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `addField(DocField field)`<br>
> Agrega un campo al modelo de la clase.

> - *@param* **field** definición del campo
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `addMethod(DocMethod method)`<br>
> Agrega un método al modelo de la clase.

> - *@param* **method** definición del método
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `addConstructor(DocConstructor constructor)`<br>
> Agrega un constructor al modelo de la clase.

> - *@param* **constructor** definición del constructor
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getSuperClass()`<br>
> - *@return* el nombre de la superclase, o {@code null} si no tiene.
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setSuperClass(String superClass)`<br>
> Define la superclase de este tipo.

> - *@param* **superClass** nombre de la clase padre
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<String> `getInterfaces()`<br>
> - *@return* lista de interfaces implementadas (clases) o extendidas (interfaces).
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `addInterface(String iface)`<br>
> Agrega una interfaz implementada o extendida.

> - *@param* **iface** nombre de la interfaz
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setDescription(String description)`<br>
- `public `[int](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Integer.html) `getIndexOrder()`<br>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setIndexOrder(int indexOrder)`<br>
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

- `private` **Kind** `kind`
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

- `private` [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocAnnotation> `annotations`
> Anotaciones aplicadas directamente a la clase (p. ej. @Deprecated, @RequestMapping).

</details>


</details>
<details>
<summary> <strong> 📘 Public Class DocMethod</strong> </summary>

## #4 📘 Public Class DocMethod

```java
public class DocMethod
```
> **Descripción:**
> Representa un método en el modelo intermedio de documentación.

### 🧮 Métodos

<details open><summary>Public</summary>

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getName()`<br>
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getReturnType()`<br>
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<String> `getParameters()`<br>
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getDescription()`<br>
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getVisibility()`<br>
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isStatic()`<br>
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isVoid()`<br>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setReturnDescription(String returnDescription)`<br>
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getReturnDescription()`<br>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `addDocParameter(DocParameter param)`<br>
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocParameter> `getDocParameters()`<br>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `addException(DocException exception)`<br>
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocException> `getExceptions()`<br>
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocAnnotation> `getAnnotations()`<br>
> - *@return* lista de anotaciones aplicadas sobre el método.
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `addAnnotation(DocAnnotation annotation)`<br>
> Agrega una anotación al método.

> - *@param* **annotation** anotación a añadir
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
- `private` [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocAnnotation> `annotations`
</details>


</details>
<details>
<summary> <strong> 📘 Public Class DocParameter</strong> </summary>

## #5 📘 Public Class DocParameter

```java
public class DocParameter
```
> **Descripción:**
> @author Felipe M. philbone@focused.cl

### 🧮 Métodos

<details open><summary>Public</summary>

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getName()`<br>
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getDescription()`<br>
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

## #6 📘 Public Class DocPackage

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

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getName()`<br>
> Obtiene el nombre del paquete.

> - *@return* nombre completo del paquete.
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocClass> `getClasses()`<br>
> Devuelve la lista de clases, interfaces, enums y records que pertenecen al paquete.
> <p>
> La lista devuelta es la instancia interna; se recomienda usar
> {@link #addClass(DocClass)} para agregar elementos en lugar de modificarla directamente.
> </p>

> - *@return* lista de clases del paquete.
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `addClass(DocClass docClass)`<br>
> Agrega una nueva clase, interfaz, enum o record al paquete.

> - *@param* **docClass** instancia de {@link DocClass} a agregar.
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setProjectName(String projectName)`<br>
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getProjectName()`<br>
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

## #7 📙 Public Enum Kind

```java
public enum Kind
```

</details>
<details>
<summary> <strong> 📘 Public Class DocException</strong> </summary>

## #8 📘 Public Class DocException

```java
public class DocException
```
### 🧮 Métodos

<details open><summary>Public</summary>

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getName()`<br>
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getDescription()`<br>
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

## #9 📘 Public Class DocField

```java
public class DocField
```
> **Descripción:**
> Representa un campo (atributo) documentado dentro de una clase.

### 🧮 Métodos

<details open><summary>Public</summary>

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getName()`<br>
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getType()`<br>
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getDescription()`<br>
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getVisibility()`<br>
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isStatic()`<br>
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
|**2**|[public class ConfigManager](#2-public-class-configmanager)|Gestiona las rutas y directorios de configuración
|**3**|[public class ConfigurationService](#3-public-class-configurationservice)|Servicio para gestionar la creación, validación y corrección de configuración
|**4**|[public class Config](#4-public-class-config)|@author Felipe M.
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

- `public  static`**Config** `loadConfig()`<br>
> Carga la configuración usando ConfigManager para obtener la ruta por
> defecto

> - *@return* un objeto con los datos de configuración iniciales.
- `public  static`**Config** `loadConfig(String filePath, boolean quietMode)`<br>
> Carga la configuración desde un archivo específico

> - *@param* **filePath** ruta del archivo de configuración
> - *@return* objeto Config con los datos cargados
- `public  static` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `saveConfig(Config config, String filePath)`<br>
> Guarda la configuración en un archivo YAML

> - *@param* **config** objeto Config a guardar
> - *@param* **filePath** ruta del archivo destino
> - *@throws* **IOException** si ocurre error de escritura
- `public  static` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `saveConfig(Config config)`<br>
> Guarda la configuración usando ConfigManager para obtener la ruta por
> defecto

> - *@param* **config** objeto Config a guardar
> - *@throws* **IOException** si ocurre error de escritura
- `public  static`**Config** `createDefaultConfig(String filePath)`<br>
> Crea una configuración por defecto y la guarda en un archivo

> - *@param* **filePath** ruta del archivo destino
> - *@return* objeto Config creado
> - *@throws* **IOException** si ocurre error de escritura
- `public  static`**Config** `createDefaultConfig()`<br>
> Crea una configuración por defecto y la guarda en la ruta por defecto
> usando ConfigManager

> - *@return* objeto Config creado
> - *@throws* **IOException** si ocurre error de escritura
- `public  static`[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `configExists(String filePath)`<br>
> Verifica si existe el archivo de configuración

> - *@param* **filePath** ruta a verificar
> - *@return* true si el archivo existe
- `public  static`[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `configExists()`<br>
> Verifica si existe el archivo de configuración por defecto usando
> ConfigManager

> - *@return* true si el archivo de configuración por defecto existe
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

- `private  static`ObjectMapper `createObjectMapper()`<br>
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

- `private static` ResourceBundle `appMessages`
</details>

## #2 📘 Public Class ConfigManager

```java
public class ConfigManager
```
> **Descripción:**
> Gestiona las rutas y directorios de configuración

### 🧮 Métodos

<details open><summary>Public</summary>

- `public `Path `getConfigDir()`<br>
- `public `Path `getConfigFilePath()`<br>
- `public `Path `getLangsDir()`<br>
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `configDirExists()`<br>
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `configFileExists()`<br>
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `langsDirExists()`<br>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `ensureConfigDir()`<br>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `ensureLangsDir()`<br>
- `public `Path `getBaseDirectory()`<br>
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

### 🛠️ Constructores

- `public ConfigManager()`
- `public ConfigManager(Path baseDirectory)`
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private static` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `CONFIG_DIR`
- `private static` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `DEFAULT_CONFIG_FILE`
- `private static` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `LANGS_DIR`
- `private` Path `baseDirectory`
</details>

## #3 📘 Public Class ConfigurationService

```java
public class ConfigurationService
```
> **Descripción:**
> Servicio para gestionar la creación, validación y corrección de configuración

### 🧮 Métodos

<details open><summary>Public</summary>

- `public `**Config** `createWithParameters(String sourcePath, String outputPath, String outFileName)`<br>
> Crea una configuración con parámetros específicos (modo no-interactivo)

- `public `**Config** `createInteractively(Scanner scanner)`<br>
> Crea una configuración interactivamente (modo asistido)

- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isValid(Config config)`<br>
> Valida si una configuración es válida

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getValidPathFromUser(Scanner scanner, String fieldName, String currentValue, String defaultValue, boolean mustExist, boolean muteMode)`<br>
> Obtiene una ruta válida del usuario de manera interactiva

- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isUsingDefaultValues(Config config)`<br>
> Detecta si una configuración está usando valores por defecto

</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `expandHomeDirectory(String path)`<br>
> Expande el directorio home del usuario (~)

</details>

### 🛠️ Constructores

- `public ConfigurationService()`
- `public ConfigurationService(ResourceBundle messages)`
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private` ResourceBundle `messages`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `shortPositive`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `longPositive`
</details>

## #4 📘 Public Class Config

```java
public class Config
```
> **Descripción:**
> @author Felipe M. <philbone@focused.cl>

### 🧮 Métodos

<details open><summary>Public</summary>

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getSourcePath()`<br>
> Obtiene la ruta del código fuente a documentar.

> - *@return* el directorio donde se encuentran el código fuente.
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setSourcePath(String sourcePath)`<br>
> - *@param* **sourcePath** 
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getOutputPath()`<br>
> - *@return* 
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setOutputPath(String outputPath)`<br>
> - *@param* **outputPath** 
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getOutFileName()`<br>
> - *@return* 
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setOutFileName(String outFileName)`<br>
> - *@param* **outFileName** 
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isDebugMode()`<br>
> - *@return* 
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setDebugMode(boolean debugMode)`<br>
> - *@param* **debugMode** 
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isCombinePackagesMode()`<br>
> - *@return* 
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setCombinePackagesMode(boolean combinePackages)`<br>
> - *@param* **combinePackages** 
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isIncludePrivate()`<br>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setIncludePrivate(boolean includePrivate)`<br>
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isIncludeProtected()`<br>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setIncludeProtected(boolean includeProtected)`<br>
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isIncludePublic()`<br>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setIncludePublic(boolean includePublic)`<br>
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isTableOfContent()`<br>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setTableOfContent(boolean tableOfContent)`<br>
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isPrintEmptyNotify()`<br>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setPrintEmptyNotify(boolean printEmptyNotify)`<br>
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isPrintClassIndex()`<br>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setPrintClassIndex(boolean printClassIndex)`<br>
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getForeSignClassIndex()`<br>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setForeSignClassIndex(String foreSignClassIndex)`<br>
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isForeSignClassIndexOnDetails()`<br>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setForeSignClassIndexOnDetails(boolean foreSignClassIndexOnDetails)`<br>
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isForeSignClassIndexOnSubtitle()`<br>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setForeSignClassIndexOnSubtitle(boolean foreSignClassIndexOnSubtitle)`<br>
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getMarkdownLanguage()`<br>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setMarkdownLanguage(String markdownLanguage)`<br>
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
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `markdownLanguage`
</details>



---

## io.github.philbone.javadocmd.cli

## Resumen de Clases


|#|CLASE|DESCRIPCIÓN|
|---|---|---|
|**1**|[public class ValidationIssue](#1-public-class-validationissue)|Representa un problema de validación con su corrección asociada
|**2**|[public class JavadocmdCLI](#2-public-class-javadocmdcli)|
|**3**|[public class ValidateCommand](#3-public-class-validatecommand)|
|**4**|[public class SetCommand](#4-public-class-setcommand)|
|**5**|[public class GetCommand](#5-public-class-getcommand)|
|**6**|[public class InitCommand](#6-public-class-initcommand)|
|**7**|[public class ShowCommand](#7-public-class-showcommand)|
|**8**|[public class AliasCommand](#8-public-class-aliascommand)|
<details>
<summary> <strong> 📘 Public Class ValidationIssue</strong> </summary>

## #1 📘 Public Class ValidationIssue

```java
public class ValidationIssue
```
> **Descripción:**
> Representa un problema de validación con su corrección asociada

### 🧮 Métodos

<details open><summary>Public</summary>

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getProblem()`<br>
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getSuggestion()`<br>
- `public `Runnable `getCorrection()`<br>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `applyCorrection()`<br>
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

### 🛠️ Constructores

- `public ValidationIssue(String problem, String suggestion, Runnable correction)`
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `problem`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `suggestion`
- `private` Runnable `correction`
</details>


</details>
<details>
<summary> <strong> 📘 Public Class JavadocmdCLI</strong> </summary>

## #2 📘 Public Class JavadocmdCLI

```java
@Command(name = "javadocmd", mixinStandardHelpOptions = true, version = "1.0.0", description = "${description.app}", resourceBundle = "messages", subcommands = [InitCommand.class, ShowCommand.class, GetCommand.class, SetCommand.class, ValidateCommand.class, AliasCommand.class])public class JavadocmdCLI
implements Callable
```
### 🧮 Métodos

<details open><summary>Public</summary>

- `public `Integer `call()`<br><sub>@Override</sub>
- `public  static` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `main(String[] args)`<br>
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>


</details>
<details>
<summary> <strong> 📘 Public Class ValidateCommand</strong> </summary>

## #3 📘 Public Class ValidateCommand

```java
@Command(name = "validate", description = "${usage.validate}", resourceBundle = "messages")public class ValidateCommand
implements Callable
```
### 🧮 Métodos

<details open><summary>Public</summary>

- `public `Integer `call()`<br><sub>@Override</sub>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setConfigFile(String configFile)`<br>
> Permite configurar el archivo de configuración desde fuera

- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setInteractive(boolean interactive)`<br>
> Permite configurar el modo interactivo desde fuera

- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setMuteMode(boolean mode)`<br>
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getActualConfigFilePath()`<br>
> Obtiene la ruta real del archivo de configuración
> - Si el usuario proporcionó --configFile, usa esa ruta
> - Si no, usa la ruta por defecto en .javadocmd/

- `private `**Config** `fixConfigurationInteractively(Config existingConfig, String actualConfigFile)`<br>
- `private ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setupInternationalization(Scanner scanner)`<br>
- `private ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `createDefaultLanguageFiles(Path langsPath, Scanner scanner)`<br>
- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getDefaultSpanishContent()`<br>
- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getDefaultEnglishContent()`<br>
</details>

### 🛠️ Constructores

- `public ValidateCommand()`
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private` ResourceBundle `messages`
- `private` **ConfigurationService** `configService`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `shortPositive`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `longPositive`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `helpRequested`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `interactive`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `mute`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `configFile`
</details>


</details>
<details>
<summary> <strong> 📘 Public Class SetCommand</strong> </summary>

## #4 📘 Public Class SetCommand

```java
@Command(name = "set", description = "${usage.set}", resourceBundle = "messages")public class SetCommand
implements Callable
```
### 🧮 Métodos

<details open><summary>Public</summary>

- `public `Integer `call()`<br><sub>@Override</sub>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setConfigFile(String configFile)`<br>
> Permite configurar el archivo de configuración desde fuera

</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getActualConfigFilePath()`<br>
> Obtiene la ruta real del archivo de configuración - Si el usuario
> proporcionó --configFile, usa esa ruta - Si no, usa la ruta por defecto
> en .javadocmd/

- `private `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<String> `applyChanges(Config config)`<br>
- `private ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `showChangesSummary(List<String> changes, String configFilePath)`<br>
</details>

### 🛠️ Constructores

- `public SetCommand()`
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private` ResourceBundle `appMessages`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `helpRequested`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `sourcePath`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `outputPath`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `outFileName`
- `private` Boolean `combinePackagesMode`
- `private` Boolean `includePrivate`
- `private` Boolean `includeProtected`
- `private` Boolean `includePublic`
- `private` Boolean `debugMode`
- `private` Boolean `tableOfContent`
- `private` Boolean `printEmptyNotify`
- `private` Boolean `printClassIndex`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `foreSignClassIndex`
- `private` Boolean `foreSignClassIndexOnDetails`
- `private` Boolean `foreSignClassIndexOnSubtitle`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `markdownLanguage`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `configFile`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `forceCreate`
</details>


</details>
<details>
<summary> <strong> 📘 Public Class GetCommand</strong> </summary>

## #5 📘 Public Class GetCommand

```java
@Command(name = "get", description = "${usage.get}", resourceBundle = "messages")public class GetCommand
implements Callable
```
### 🧮 Métodos

<details open><summary>Public</summary>

- `public `Integer `call()`<br><sub>@Override</sub>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setConfigFile(String configFile)`<br>
> Permite configurar el archivo de configuración desde fuera

</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getActualConfigFilePath()`<br>
> Obtiene la ruta real del archivo de configuración - Si el usuario
> proporcionó --configFile, usa esa ruta - Si no, usa la ruta por defecto
> en .javadocmd/

- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getConfigValue(Config config, String key)`<br>
</details>

### 🛠️ Constructores

- `public GetCommand()`
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private` ResourceBundle `appMessages`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `helpRequested`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `quietMode`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `configFile`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `key`
</details>


</details>
<details>
<summary> <strong> 📘 Public Class InitCommand</strong> </summary>

## #6 📘 Public Class InitCommand

```java
@Command(name = "init", description = "${usage.init}", resourceBundle = "messages")public class InitCommand
implements Callable
```
### 🧮 Métodos

<details open><summary>Public</summary>

- `public `Integer `call()`<br><sub>@Override</sub>
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

- `private `Integer `createWithParameters(String configFilePath)`<br>
</details>

### 🛠️ Constructores

- `public InitCommand()`
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private` ResourceBundle `messages`
- `private` **ConfigurationService** `configService`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `helpRequested`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `sourcePath`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `outputPath`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `outFileName`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `interactive`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `mute`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `configFile`
</details>


</details>
<details>
<summary> <strong> 📘 Public Class ShowCommand</strong> </summary>

## #7 📘 Public Class ShowCommand

```java
@Command(name = "show", description = "${usage.show}", resourceBundle = "messages")public class ShowCommand
implements Callable
```
### 🧮 Métodos

<details open><summary>Public</summary>

- `public `Integer `call()`<br><sub>@Override</sub>
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setConfigFile(String configFile)`<br>
> Permite configurar el archivo de configuración desde fuera

</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

- `private `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getActualConfigFilePath()`<br>
> Obtiene la ruta real del archivo de configuración - Si el usuario
> proporcionó --configFile, usa esa ruta - Si no, usa la ruta por defecto
> en .javadocmd/

- `private ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `showConfiguration(Config config, String configFilePath)`<br>
- `private ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `printField(String label, Object value)`<br>
- `private ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `printField(String label, boolean value)`<br>
- `private ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `showRawFile(String actualConfigFile)`<br>
</details>

### 🛠️ Constructores

- `public ShowCommand()`
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private` ResourceBundle `appMessages`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `helpRequested`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `configFile`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `raw`
</details>


</details>
<details>
<summary> <strong> 📘 Public Class AliasCommand</strong> </summary>

## #8 📘 Public Class AliasCommand

```java
@Command(name = "alias", aliases = ["make-alias"], description = "${usage.alias}", mixinStandardHelpOptions = true, resourceBundle = "messages")public class AliasCommand
implements Callable
```
### 🧮 Métodos

<details open><summary>Public</summary>

- `public `Integer `call()`<br><sub>@Override</sub>
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

- `private `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `aliasExists(String aliasName)`<br>
- `private `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `createAlias()`<br>
</details>

### 🛠️ Constructores

- `public AliasCommand()`
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private` ResourceBundle `messages`
- `private` **ConfigurationService** `configService`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `jarPath`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `aliasName`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `force`
</details>


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

- `public  static` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `main(String[] args)`<br>
> Método principal que inicia el proceso de generación de documentación.

> - *@param* **args** argumentos opcionales (no utilizados actualmente). Se planea
en futuras versiones aceptar <code>sourcePath</code> y
<code>outputPath</code> como parámetros desde consola.
- `public  static` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `generatePackageDocs(Config config)`<br>
- `public  static` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `generateCombinedDocs(Config config, LanguageManager lang)`<br>
- `public  static` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `forceJavaLevel(ParserConfiguration.LanguageLevel languageLevel)`<br>
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

