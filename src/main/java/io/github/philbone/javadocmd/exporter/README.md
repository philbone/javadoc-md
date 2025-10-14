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

- `private `boolean `isPrintable(String visibility)`
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

- `private static` int `COLLAPSE_THRESHOLD`
> Número mínimo de clases dentro de un paquete para activar el modo colapsable.
> Si el paquete tiene más de este número, cada clase se renderiza dentro de un bloque `<details>`.

- `private static` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `VISIBILITY_PUBLIC`
- `private static` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `VISIBILITY_PRIVATE`
- `private static` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `VISIBILITY_PROTECTED`
- `private` [Config](Config.md) `config`
- `private` int `totalMethodsCount`
- `private` int `totalFieldsCount`
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
- `public ` **void** `codeBlock(String content, String codeLang)`
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

- `public `int `size()`
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
- `private` boolean `debug`
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

> - *@param* **type** 
> - *@return* 
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

- `private static` Pattern `GENERIC_PATTERN`
> Patrón para detectar tipos genéricos (por ejemplo, List<String>)

</details>


</details>
