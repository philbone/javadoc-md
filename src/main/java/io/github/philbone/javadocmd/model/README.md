# `io.github.philbone.javadocmd.model`

## 📘 Public Class `DocConstructor`

Representa un constructor documentado dentro de una clase.

## 📦 Campos

- #### `private String name`
- #### `private List<String> parameters`
- #### `private String description`
- #### `private String visibility`
- #### `private boolean isStatic`
- #### `private List<DocParameter> docParameters`
- #### `private List<DocException> exceptions`
## 🛠️ Constructores

- #### `public DocConstructor(String name, List<String> parameters, String description, String visibility, boolean isStatic)`
## 🧮 Métodos

- #### `public String getName()`
- #### `public List<String> getParameters()`
- #### `public String getDescription()`
- #### `public String getVisibility()`
- #### `public boolean isStatic()`
- #### `public void addDocParameter(DocParameter param)`
- #### `public List<DocParameter> getDocParameters()`
- #### `public void addException(DocException exception)`
- #### `public List<DocException> getExceptions()`
---

## 📘 Public Class `DocClass`

## 📦 Campos

- #### `private String name`
- #### `private String description`
- #### `private Kind kind`
- #### `private String visibility`
- #### `private boolean isStatic`
- #### `private List<DocField> fields`
- #### `private List<DocMethod> methods`
- #### `private List<DocConstructor> constructors`
- #### `private String superClass`
- #### `private List<String> interfaces`
## 🛠️ Constructores

- #### `public DocClass(String name, String description, Kind kind, String visibility, boolean isStatic)`
## 🧮 Métodos

- #### `public String getName()`
- #### `public String getDescription()`
- #### `public Kind getKind()`
- #### `public String getVisibility()`
- #### `public boolean isStatic()`
- #### `public List<DocField> getFields()`
- #### `public List<DocMethod> getMethods()`
- #### `public List<DocConstructor> getConstructors()`
- #### `public void addField(DocField field)`
- #### `public void addMethod(DocMethod method)`
- #### `public void addConstructor(DocConstructor constructor)`
- #### `public String getSuperClass()`
- #### `public void setSuperClass(String superClass)`
- #### `public List<String> getInterfaces()`
- #### `public void addInterface(String iface)`
---

## 📘 Public Class `DocMethod`

## 📦 Campos

- #### `private String name`
- #### `private String returnType`
- #### `private List<String> parameters`
- #### `private String description`
- #### `private String visibility`
- #### `private boolean isStatic`
- #### `private String returnDescription`
- #### `private List<DocParameter> docParameters`
- #### `private List<DocException> exceptions`
## 🛠️ Constructores

- #### `public DocMethod(String name, String returnType, List<String> parameters, String description, String visibility, boolean isStatic)`
## 🧮 Métodos

- #### `public String getName()`
- #### `public String getReturnType()`
- #### `public List<String> getParameters()`
- #### `public String getDescription()`
- #### `public String getVisibility()`
- #### `public boolean isStatic()`
- #### `public void setReturnDescription(String returnDescription)`
- #### `public String getReturnDescription()`
- #### `public void addDocParameter(DocParameter param)`
- #### `public List<DocParameter> getDocParameters()`
- #### `public void addException(DocException exception)`
- #### `public List<DocException> getExceptions()`
---

## 📘 Public Class `DocParameter`

## 📦 Campos

- #### `private String name`
- #### `private String description`
## 🛠️ Constructores

- #### `public DocParameter(String name, String description)`
## 🧮 Métodos

- #### `public String getName()`
- #### `public String getDescription()`
---

## 📘 Public Class `DocPackage`

## 📦 Campos

- #### `private String name`
- #### `private List<DocClass> classes`
## 🛠️ Constructores

- #### `public DocPackage(String name)`
## 🧮 Métodos

- #### `public String getName()`
- #### `public List<DocClass> getClasses()`
- #### `public void addClass(DocClass docClass)`
---

## 📙 Public Enum `Kind`

---

## 📘 Public Class `DocException`

## 📦 Campos

- #### `private String name`
- #### `private String description`
## 🛠️ Constructores

- #### `public DocException(String name, String description)`
## 🧮 Métodos

- #### `public String getName()`
- #### `public String getDescription()`
---

## 📘 Public Class `DocField`

Representa un campo (atributo) documentado dentro de una clase.

## 📦 Campos

- #### `private String name`
- #### `private String type`
- #### `private String description`
- #### `private String visibility`
- #### `private boolean isStatic`
## 🛠️ Constructores

- #### `public DocField(String name, String type, String description, String visibility, boolean isStatic)`
## 🧮 Métodos

- #### `public String getName()`
- #### `public String getType()`
- #### `public String getDescription()`
- #### `public String getVisibility()`
- #### `public boolean isStatic()`
