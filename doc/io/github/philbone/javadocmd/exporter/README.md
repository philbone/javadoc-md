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

> _No hay métodos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

> _No hay métodos visibles_

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

> _No hay métodos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

> _No hay métodos visibles_
### 🛠️ Constructores

> _No hay constructores visibles_
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay campos private visibles_
</details>

> _No hay campos visibles_

</details>
<details>
<summary> <strong> 📘 Public Class MarkdownBuilder</strong> </summary>

## #3 📘 Public Class MarkdownBuilder

```java
public class MarkdownBuilder
```
### 🧮 Métodos

<details open><summary>Public</summary>

> _No hay métodos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

> _No hay métodos visibles_
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay campos private visibles_
</details>

> _No hay campos visibles_

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

> _No hay métodos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

> _No hay métodos visibles_
### 🛠️ Constructores

> _No hay constructores visibles_
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay campos private visibles_
</details>

> _No hay campos visibles_

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

> _No hay métodos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

> _No hay métodos visibles_
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay campos private visibles_
</details>

> _No hay campos visibles_

</details>
