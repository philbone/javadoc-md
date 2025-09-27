# `io.github.philbone.javadocmd.model`

## Class: DocClass

## 🧮 Métodos

- #### `String getName()`
- #### `String getDescription()`
- #### `Kind getKind()`
- #### `List<DocMethod> getMethods()`
- #### `void addMethod(DocMethod method)`
## Class: DocMethod

## 🧮 Métodos

- #### `String getName()`
- #### `String getReturnType()`
- #### `List<String> getParameters()`
- #### `String getDescription()`
- #### `List<DocParameter> getDocParameters()`
- #### `void addDocParameter(DocParameter param)`
- #### `String getReturnDescription()`
- #### `void setReturnDescription(String returnDescription)`
- #### `List<DocException> getExceptions()`
- #### `void addException(DocException exception)`
## Class: DocParameter

## 🧮 Métodos

- #### `String getName()`
- #### `String getDescription()`
## Class: DocPackage

## 🧮 Métodos

- #### `String getName()`
- #### `List<DocClass> getClasses()`
- #### `void addClass(DocClass docClass)`
## Enum: Kind

## Class: DocException

## 🧮 Métodos

- #### `String getName()`
- #### `String getDescription()`
