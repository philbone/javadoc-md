# `io.github.philbone.javadocmd.exporter`

## 📗 Public Interface DocExporter

```java
public interface DocExporter
```
## 🧮 Métodos

- #### `package-private String export(DocPackage docPackage)`
---

## 📘 Public Class MarkdownExporter

```java
public class MarkdownExporter
implements DocExporter
```
> **Descripción**
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

## 🧮 Métodos

- #### `public String export(DocPackage docPackage)`
- #### `private String formatKind(Kind kind)`
- #### `private String capitalize(String s)`
- #### `private String formatEmoji(Kind kind)`
---

## 📘 Public Class MarkdownBuilder

```java
public class MarkdownBuilder
```
## 📦 Campos

- #### `private StringBuilder sb`
## 🧮 Métodos

- #### `public MarkdownBuilder title(String text)`
- #### `public MarkdownBuilder subtitle(String text)`
- #### `public MarkdownBuilder paragraph(String text)`
- #### `public MarkdownBuilder listItem(String text)`
- #### `public String build()`
- #### `public void codeBlock(String content, String codeLang)`
- #### `public MarkdownBuilder blockquote(String text)`
- #### `public MarkdownBuilder tag(String tag)`
