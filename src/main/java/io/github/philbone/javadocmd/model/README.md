# JavadocMd

## io.github.philbone.javadocmd.model

### Resumen de Clases


|CLASE|DESCRIPCIÓN|
|---|---|
|[public class DocConstructor](#-public-class-docconstructor)|Representa un constructor documentado dentro de una clase.
|[public class DocClass](#-public-class-docclass)|Representa la definición de una clase, interfaz, enum o record dentro del modelo intermedio de documentación.
|[public class DocMethod](#-public-class-docmethod)|
|[public class DocParameter](#-public-class-docparameter)|
|[public class DocPackage](#-public-class-docpackage)|Representa un paquete de Java dentro del modelo intermedio de documentación.
|[public enum Kind](#-public-enum-kind)|
|[public class DocException](#-public-class-docexception)|
|[public class DocField](#-public-class-docfield)|Representa un campo (atributo) documentado dentro de una clase.
---

## 📘 Public Class DocConstructor

```java
public class DocConstructor
```
> **Descripción:**
> Representa un constructor documentado dentro de una clase.

## 📦 Campos

- `private String name`
- `private List<String> parameters`
- `private String description`
- `private String visibility`
- `private boolean isStatic`
- `private List<DocParameter> docParameters`
- `private List<DocException> exceptions`
## 🛠️ Constructores

- `public DocConstructor(String name, List<String> parameters, String description, String visibility, boolean isStatic)`
## 🧮 Métodos

- `public String getName()`
- `public List<String> getParameters()`
- `public String getDescription()`
- `public String getVisibility()`
- `public boolean isStatic()`
- `public void addDocParameter(DocParameter param)`
- `public List<DocParameter> getDocParameters()`
- `public void addException(DocException exception)`
- `public List<DocException> getExceptions()`
---

## 📘 Public Class DocClass

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

## 📦 Campos

- `private String name`
> Nombre simple de la clase, interfaz, enum o record.

- `private Kind kind`
> Tipo de elemento representado (clase, interfaz, enum, record, abstracta).

- `private String visibility`
> Nivel de visibilidad del tipo (public, protected, package-private, private).

- `private boolean isStatic`
> Indica si el tipo ha sido declarado como {@code static}.

- `private List<DocField> fields`
> Campos declarados dentro de la clase.

- `private List<DocMethod> methods`
> Métodos declarados dentro de la clase.

- `private List<DocConstructor> constructors`
> Constructores declarados dentro de la clase.

- `private String superClass`
> Nombre de la clase padre (superclase), si existe.

- `private List<String> interfaces`
> Interfaces implementadas (clases) o extendidas (interfaces).

- `private String description`
> Descripción principal tomada del comentario Javadoc asociado.

## 🛠️ Constructores

- `public DocClass(String name, String description, Kind kind, String visibility, boolean isStatic)`
> **Descripción:**
> Crea una nueva representación de clase en el modelo intermedio.

> - *@param* `name`nombre simple de la clase
> - *@param* `description`descripción principal (desde Javadoc)
> - *@param* `kind`tipo del elemento (clase, interfaz, enum, record)
> - *@param* `visibility`nivel de visibilidad (public, protected, package-private, private)
> - *@param* `isStatic`indica si la clase fue declarada como {@code static}
## 🧮 Métodos

- `public String getName()`
> - *@return* el nombre simple de la clase.
- `public String getDescription()`
> - *@return* la descripción tomada del Javadoc.
- `public Kind getKind()`
> - *@return* el tipo de elemento representado.
- `public String getVisibility()`
> - *@return* la visibilidad del tipo (public, protected, etc.).
- `public boolean isStatic()`
> - *@return* {@code true} si la clase fue declarada como estática.
- `public List<DocField> getFields()`
> - *@return* lista inmutable de campos de la clase.
- `public List<DocMethod> getMethods()`
> - *@return* lista inmutable de métodos de la clase.
- `public List<DocConstructor> getConstructors()`
> - *@return* lista inmutable de constructores de la clase.
- `public void addField(DocField field)`
> Agrega un campo al modelo de la clase.

> - *@param* **field** definición del campo
- `public void addMethod(DocMethod method)`
> Agrega un método al modelo de la clase.

> - *@param* **method** definición del método
- `public void addConstructor(DocConstructor constructor)`
> Agrega un constructor al modelo de la clase.

> - *@param* **constructor** definición del constructor
- `public String getSuperClass()`
> - *@return* el nombre de la superclase, o {@code null} si no tiene.
- `public void setSuperClass(String superClass)`
> Define la superclase de este tipo.

> - *@param* **superClass** nombre de la clase padre
- `public List<String> getInterfaces()`
> - *@return* lista de interfaces implementadas (clases) o extendidas (interfaces).
- `public void addInterface(String iface)`
> Agrega una interfaz implementada o extendida.

> - *@param* **iface** nombre de la interfaz
- `public void setDescription(String description)`
---

## 📘 Public Class DocMethod

```java
public class DocMethod
```
## 📦 Campos

- `private String name`
- `private String returnType`
- `private List<String> parameters`
- `private String description`
- `private String visibility`
- `private boolean isStatic`
- `private String returnDescription`
- `private List<DocParameter> docParameters`
- `private List<DocException> exceptions`
## 🛠️ Constructores

- `public DocMethod(String name, String returnType, List<String> parameters, String description, String visibility, boolean isStatic)`
## 🧮 Métodos

- `public String getName()`
- `public String getReturnType()`
- `public List<String> getParameters()`
- `public String getDescription()`
- `public String getVisibility()`
- `public boolean isStatic()`
- `public void setReturnDescription(String returnDescription)`
- `public String getReturnDescription()`
- `public void addDocParameter(DocParameter param)`
- `public List<DocParameter> getDocParameters()`
- `public void addException(DocException exception)`
- `public List<DocException> getExceptions()`
---

## 📘 Public Class DocParameter

```java
public class DocParameter
```
## 📦 Campos

- `private String name`
- `private String description`
## 🛠️ Constructores

- `public DocParameter(String name, String description)`
## 🧮 Métodos

- `public String getName()`
- `public String getDescription()`
---

## 📘 Public Class DocPackage

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

## 📦 Campos

- `private String name`
> Nombre completo del paquete (ejemplo: {@code io.github.philbone.javadocmd.exporter}).

- `private List<DocClass> classes`
> Conjunto de clases, interfaces, enums y records pertenecientes al paquete.

- `private String projectName`
## 🛠️ Constructores

- `public DocPackage(String name)`
> **Descripción:**
> Crea un nuevo descriptor de paquete.

> - *@param* `name`nombre del paquete en notación estándar de Java.
## 🧮 Métodos

- `public String getName()`
> Obtiene el nombre del paquete.

> - *@return* nombre completo del paquete.
- `public List<DocClass> getClasses()`
> Devuelve la lista de clases, interfaces, enums y records que pertenecen al paquete.
> <p>
> La lista devuelta es la instancia interna; se recomienda usar
> {@link #addClass(DocClass)} para agregar elementos en lugar de modificarla directamente.
> </p>

> - *@return* lista de clases del paquete.
- `public void addClass(DocClass docClass)`
> Agrega una nueva clase, interfaz, enum o record al paquete.

> - *@param* **docClass** instancia de {@link DocClass} a agregar.
- `public void setProjectName(String projectName)`
- `public String getProjectName()`
---

## 📙 Public Enum Kind

```java
public enum Kind
```
---

## 📘 Public Class DocException

```java
public class DocException
```
## 📦 Campos

- `private String name`
- `private String description`
## 🛠️ Constructores

- `public DocException(String name, String description)`
## 🧮 Métodos

- `public String getName()`
- `public String getDescription()`
---

## 📘 Public Class DocField

```java
public class DocField
```
> **Descripción:**
> Representa un campo (atributo) documentado dentro de una clase.

## 📦 Campos

- `private String name`
- `private String type`
- `private String description`
- `private String visibility`
- `private boolean isStatic`
## 🛠️ Constructores

- `public DocField(String name, String type, String description, String visibility, boolean isStatic)`
## 🧮 Métodos

- `public String getName()`
- `public String getType()`
- `public String getDescription()`
- `public String getVisibility()`
- `public boolean isStatic()`
