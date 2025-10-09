# JavadocMd

## io.github.philbone.javadocmd.model

## Resumen de Clases


|#|CLASE|DESCRIPCIÓN|
|---|---|---|
|**1**|[public class DocConstructor](#1-public-class-docconstructor)|Representa un constructor documentado dentro de una clase.
|**2**|[public class DocClass](#2-public-class-docclass)|Representa la definición de una clase, interfaz, enum o record dentro del modelo intermedio de documentación.
|**3**|[public class DocMethod](#3-public-class-docmethod)|
|**4**|[public class DocParameter](#4-public-class-docparameter)|
|**5**|[public class DocPackage](#5-public-class-docpackage)|Representa un paquete de Java dentro del modelo intermedio de documentación.
|**6**|[public enum Kind](#6-public-enum-kind)|
|**7**|[public class DocException](#7-public-class-docexception)|
|**8**|[public class DocField](#8-public-class-docfield)|Representa un campo (atributo) documentado dentro de una clase.
<details>
<summary> <strong>1 📘 Public Class DocConstructor</strong> </summary>

## #1 📘 Public Class DocConstructor

```java
public class DocConstructor
```
> **Descripción:**
> Representa un constructor documentado dentro de una clase.

### 📦 Campos

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `name`
- `private` [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<String> `parameters`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `description`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `visibility`
- `private` boolean `isStatic`
- `private` [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocParameter> `docParameters`
- `private` [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocException> `exceptions`
### 🛠️ Constructores

- `public DocConstructor(String name, List<String> parameters, String description, String visibility, boolean isStatic)`
### 🧮 Métodos

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getName()`
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<String> `getParameters()`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getDescription()`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getVisibility()`
- `public `boolean `isStatic()`
- `public ` **void** `addDocParameter(DocParameter param)`
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocParameter> `getDocParameters()`
- `public ` **void** `addException(DocException exception)`
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocException> `getExceptions()`

</details>
<details>
<summary> <strong>2 📘 Public Class DocClass</strong> </summary>

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

### 📦 Campos

- `private` int `indexOrder`
> El número que tomará en la tabla de contenido

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `name`
> Nombre simple de la clase, interfaz, enum o record.

- `private` Kind `kind`
> Tipo de elemento representado (clase, interfaz, enum, record, abstracta).

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `visibility`
> Nivel de visibilidad del tipo (public, protected, package-private, private).

- `private` boolean `isStatic`
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

### 🛠️ Constructores

- `public DocClass(String name, String description, Kind kind, String visibility, boolean isStatic)`
> **Descripción:**
> Crea una nueva representación de clase en el modelo intermedio.

> - *@param* `name` nombre simple de la clase
> - *@param* `description` descripción principal (desde Javadoc)
> - *@param* `kind` tipo del elemento (clase, interfaz, enum, record)
> - *@param* `visibility` nivel de visibilidad (public, protected, package-private, private)
> - *@param* `isStatic` indica si la clase fue declarada como {@code static}
### 🧮 Métodos

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getName()`
> - *@return* el nombre simple de la clase.
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getDescription()`
> - *@return* la descripción tomada del Javadoc.
- `public `Kind `getKind()`
> - *@return* el tipo de elemento representado.
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getVisibility()`
> - *@return* la visibilidad del tipo (public, protected, etc.).
- `public `boolean `isStatic()`
> - *@return* {@code true} si la clase fue declarada como estática.
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocField> `getFields()`
> - *@return* lista inmutable de campos de la clase.
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocMethod> `getMethods()`
> - *@return* lista inmutable de métodos de la clase.
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocConstructor> `getConstructors()`
> - *@return* lista inmutable de constructores de la clase.
- `public ` **void** `addField(DocField field)`
> Agrega un campo al modelo de la clase.

> - *@param* **field** definición del campo
- `public ` **void** `addMethod(DocMethod method)`
> Agrega un método al modelo de la clase.

> - *@param* **method** definición del método
- `public ` **void** `addConstructor(DocConstructor constructor)`
> Agrega un constructor al modelo de la clase.

> - *@param* **constructor** definición del constructor
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getSuperClass()`
> - *@return* el nombre de la superclase, o {@code null} si no tiene.
- `public ` **void** `setSuperClass(String superClass)`
> Define la superclase de este tipo.

> - *@param* **superClass** nombre de la clase padre
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<String> `getInterfaces()`
> - *@return* lista de interfaces implementadas (clases) o extendidas (interfaces).
- `public ` **void** `addInterface(String iface)`
> Agrega una interfaz implementada o extendida.

> - *@param* **iface** nombre de la interfaz
- `public ` **void** `setDescription(String description)`
- `public `int `getIndexOrder()`
- `public ` **void** `setIndexOrder(int indexOrder)`

</details>
<details>
<summary> <strong>3 📘 Public Class DocMethod</strong> </summary>

## #3 📘 Public Class DocMethod

```java
public class DocMethod
```
### 📦 Campos

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `name`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `returnType`
- `private` [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<String> `parameters`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `description`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `visibility`
- `private` boolean `isStatic`
- `private` boolean `isVoid`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `returnDescription`
- `private` [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocParameter> `docParameters`
- `private` [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocException> `exceptions`
### 🛠️ Constructores

- `public DocMethod(String name, String returnType, List<String> parameters, String description, String visibility, boolean isStatic, boolean isVoid)`
### 🧮 Métodos

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getName()`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getReturnType()`
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<String> `getParameters()`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getDescription()`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getVisibility()`
- `public `boolean `isStatic()`
- `public `boolean `isVoid()`
- `public ` **void** `setReturnDescription(String returnDescription)`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getReturnDescription()`
- `public ` **void** `addDocParameter(DocParameter param)`
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocParameter> `getDocParameters()`
- `public ` **void** `addException(DocException exception)`
- `public `[List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocException> `getExceptions()`

</details>
<details>
<summary> <strong>4 📘 Public Class DocParameter</strong> </summary>

## #4 📘 Public Class DocParameter

```java
public class DocParameter
```
### 📦 Campos

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `name`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `description`
### 🛠️ Constructores

- `public DocParameter(String name, String description)`
### 🧮 Métodos

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getName()`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getDescription()`

</details>
<details>
<summary> <strong>5 📘 Public Class DocPackage</strong> </summary>

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

### 📦 Campos

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `name`
> Nombre completo del paquete (ejemplo: {@code io.github.philbone.javadocmd.exporter}).

- `private` [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<DocClass> `classes`
> Conjunto de clases, interfaces, enums y records pertenecientes al paquete.

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `projectName`
### 🛠️ Constructores

- `public DocPackage(String name)`
> **Descripción:**
> Crea un nuevo descriptor de paquete.

> - *@param* `name` nombre del paquete en notación estándar de Java.
### 🧮 Métodos

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
- `public ` **void** `addClass(DocClass docClass)`
> Agrega una nueva clase, interfaz, enum o record al paquete.

> - *@param* **docClass** instancia de {@link DocClass} a agregar.
- `public ` **void** `setProjectName(String projectName)`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getProjectName()`

</details>
<details>
<summary> <strong>6 📙 Public Enum Kind</strong> </summary>

## #6 📙 Public Enum Kind

```java
public enum Kind
```

</details>
<details>
<summary> <strong>7 📘 Public Class DocException</strong> </summary>

## #7 📘 Public Class DocException

```java
public class DocException
```
### 📦 Campos

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `name`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `description`
### 🛠️ Constructores

- `public DocException(String name, String description)`
### 🧮 Métodos

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getName()`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getDescription()`

</details>
<details>
<summary> <strong>8 📘 Public Class DocField</strong> </summary>

## #8 📘 Public Class DocField

```java
public class DocField
```
> **Descripción:**
> Representa un campo (atributo) documentado dentro de una clase.

### 📦 Campos

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `name`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `type`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `description`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `visibility`
- `private` boolean `isStatic`
### 🛠️ Constructores

- `public DocField(String name, String type, String description, String visibility, boolean isStatic)`
### 🧮 Métodos

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getName()`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getType()`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getDescription()`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getVisibility()`
- `public `boolean `isStatic()`

</details>
