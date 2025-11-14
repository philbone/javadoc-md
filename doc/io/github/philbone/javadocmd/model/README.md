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
<summary> <strong> 📘 Public Class DocConstructor</strong> </summary>

## #2 📘 Public Class DocConstructor

```java
public class DocConstructor
```
> **Descripción:**
> Representa un constructor documentado dentro de una clase.

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
<summary> <strong> 📘 Public Class DocMethod</strong> </summary>

## #4 📘 Public Class DocMethod

```java
public class DocMethod
```
> **Descripción:**
> Representa un método en el modelo intermedio de documentación.

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
<summary> <strong> 📘 Public Class DocParameter</strong> </summary>

## #5 📘 Public Class DocParameter

```java
public class DocParameter
```
> **Descripción:**
> @author Felipe M. philbone@focused.cl

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
<summary> <strong> 📘 Public Class DocField</strong> </summary>

## #9 📘 Public Class DocField

```java
public class DocField
```
> **Descripción:**
> Representa un campo (atributo) documentado dentro de una clase.

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
