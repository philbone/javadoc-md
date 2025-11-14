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

> _No hay métodos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

> _No hay métodos visibles_
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
