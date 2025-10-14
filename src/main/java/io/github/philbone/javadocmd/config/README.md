# JavadocMD

## io.github.philbone.javadocmd.config

## Resumen de Clases


|#|CLASE|DESCRIPCIÓN|
|---|---|---|
|**1**|[public class ConfigLoader](#1-public-class-configloader)|Esta clase se encarga de detectar el fichero de configuración y cargar los datos si son encontrados.
|**2**|[public class Config](#2-public-class-config)|@author Felipe M.
## #1 📘 Public Class ConfigLoader

```java
public class ConfigLoader
```
> **Descripción:**
> Esta clase se encarga de detectar el fichero de configuración
> y cargar los datos si son encontrados.
> De otra manera cargará los valores por defecto.

### 🧮 Métodos

<details open><summary>Public</summary>

- `public  static`[Config](Config.md) `loadConfig()`
> - *@return* un objeto con los datos de configuracion iniciales.
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

## #2 📘 Public Class Config

```java
public class Config
```
> **Descripción:**
> @author Felipe M. <philbone@focused.cl>

### 🧮 Métodos

<details open><summary>Public</summary>

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getSourcePath()`
> Obtiene la ruta del código fuente a documentar.

> - *@return* el directorio donde se encuentran el código fuente.
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setSourcePath(String sourcePath)`
> - *@param* **sourcePath** 
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getOutputPath()`
> - *@return* 
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setOutputPath(String outputPath)`
> - *@param* **outputPath** 
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getOutFileName()`
> - *@return* 
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setOutFileName(String outFileName)`
> - *@param* **outFileName** 
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isDebugMode()`
> - *@return* 
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setDebugMode(boolean debugMode)`
> - *@param* **debugMode** 
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isCombinePackagesMode()`
> - *@return* 
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setCombinePackagesMode(boolean combinePackages)`
> - *@param* **combinePackages** 
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isIncludePrivate()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setIncludePrivate(boolean includePrivate)`
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isIncludeProtected()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setIncludeProtected(boolean includeProtected)`
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isIncludePublic()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setIncludePublic(boolean includePublic)`
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isTableOfContent()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setTableOfContent(boolean tableOfContent)`
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isPrintEmptyNotify()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setPrintEmptyNotify(boolean printEmptyNotify)`
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isPrintClassIndex()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setPrintClassIndex(boolean printClassIndex)`
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getForeSignClassIndex()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setForeSignClassIndex(String foreSignClassIndex)`
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isForeSignClassIndexOnDetails()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setForeSignClassIndexOnDetails(boolean foreSignClassIndexOnDetails)`
- `public `[boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `isForeSignClassIndexOnSubtitle()`
- `public ` **[void](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Void.html)** `setForeSignClassIndexOnSubtitle(boolean foreSignClassIndexOnSubtitle)`
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

### 🛠️ Constructores

- `public Config()`
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `sourcePath`
> Directorio de entrada donde se encuentran las clases a documentar. *

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `outputPath`
> Directorio base donde se escribirá la documentación generada.

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `outFileName`
> Nombre del archivo de salida que contendrá la documentación en cada
> paquete.

- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `debugMode`
> Bandera de depuración para imprimir trazas adicionales.

- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `combinePackagesMode`
> Bandera para definir el modo de exportación
> false exportar un fichero por cada paquete
> true exportar un fichero de forma global.

- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `includePrivate`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `includeProtected`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `includePublic`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `tableOfContent`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `printEmptyNotify`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `printClassIndex`
- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `foreSignClassIndex`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `foreSignClassIndexOnDetails`
- `private` [boolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html) `foreSignClassIndexOnSubtitle`
</details>

