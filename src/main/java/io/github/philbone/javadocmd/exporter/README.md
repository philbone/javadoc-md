# JavadocMd

## io.github.philbone.javadocmd.exporter

### Resumen de Clases


|CLASE|DESCRIPCIÓN|
|---|---|
|[public interface DocExporter](#-public-interface-docexporter)|
|[public class MarkdownExporter](#-public-class-markdownexporter)|Exportador que genera documentación en formato Markdown a partir del modelo intermedio construido con {@link io.
|[public class MarkdownBuilder](#-public-class-markdownbuilder)|
---

## 📗 Public Interface DocExporter

```java
public interface DocExporter
```
## 🧮 Métodos

- `package-private String export(DocPackage docPackage)`
---

## 📘 Public Class MarkdownExporter

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

## 🧮 Métodos

- `public String export(DocPackage docPackage)`
- `private String formatKind(Kind kind)`
- `private String capitalize(String s)`
- `private String formatEmoji(Kind kind)`
---

## 📘 Public Class MarkdownBuilder

```java
public class MarkdownBuilder
```
## 📦 Campos

- `private StringBuilder sb`
## 🧮 Métodos

- `public MarkdownBuilder title(String text)`
- `public MarkdownBuilder subtitle(String text)`
- `public MarkdownBuilder h3(String text)`
- `public MarkdownBuilder paragraph(String text)`
- `public MarkdownBuilder listItem(String text)`
- `public String build()`
- `public void codeBlock(String content, String codeLang)`
- `public MarkdownBuilder blockquote(String text)`
- `public MarkdownBuilder tag(String tag)`
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
- `public MarkdownBuilder toc(DocPackage docPackage)`
- `private String sanitizeDescription(String raw)`
