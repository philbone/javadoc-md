# JavadocMD

## io.github.philbone.javadocmd.config

## Resumen de Clases


|#|CLASE|DESCRIPCIÓN|
|---|---|---|
|**1**|[public class ConfigLoader](#1-public-class-configloader)|Esta clase se encarga de detectar el fichero de configuración y cargar los datos si son encontrados.
|**2**|[public class Config](#2-public-class-config)|
## #1 📘 Public Class ConfigLoader

```java
public class ConfigLoader
```
> **Descripción:**
> Esta clase se encarga de detectar el fichero de configuración
> y cargar los datos si son encontrados.
> De otra manera cargará los valores por defecto.

### 🧮 Métodos

<details open>

<summary>Public</summary>

- `public  static`Config `loadConfig()`
> - *@return* un objeto con los datos de configuracion iniciales.
</details>

<details open>

<summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open>

<summary>Private</summary>

> _No hay métodos private visibles_
</details>

## #2 📘 Public Class Config

```java
public class Config
```
### 📦 Campos

<details open>

<summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open>

<summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open>

<summary>Private</summary>

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `sourcePath`
> Directorio de entrada donde se encuentran las clases a documentar. *

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `outputPath`
> Directorio base donde se escribirá la documentación generada.

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `outFileName`
> Nombre del archivo de salida que contendrá la documentación en cada
> paquete.

- `private` boolean `debugMode`
> Bandera de depuración para imprimir trazas adicionales.

- `private` boolean `combinePackagesMode`
> Bandera para definir el modo de exportación
> false exportar un fichero por cada paquete
> true exportar un fichero de forma global.

- `private` boolean `includePrivate`
- `private` boolean `includeProtected`
- `private` boolean `includePublic`
- `private` boolean `tableOfContent`
</details>

### 🛠️ Constructores

- `public Config()`
### 🧮 Métodos

<details open>

<summary>Public</summary>

- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getSourcePath()`
> Obtiene la ruta del código fuente a documentar.

> - *@return* el directorio donde se encuentran el código fuente.
- `public ` **void** `setSourcePath(String sourcePath)`
> - *@param* **sourcePath** 
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getOutputPath()`
> - *@return* 
- `public ` **void** `setOutputPath(String outputPath)`
> - *@param* **outputPath** 
- `public `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getOutFileName()`
> - *@return* 
- `public ` **void** `setOutFileName(String outFileName)`
> - *@param* **outFileName** 
- `public `boolean `isDebugMode()`
> - *@return* 
- `public ` **void** `setDebugMode(boolean debugMode)`
> - *@param* **debugMode** 
- `public `boolean `isCombinePackagesMode()`
> - *@return* 
- `public ` **void** `setCombinePackagesMode(boolean combinePackages)`
> - *@param* **combinePackages** 
- `public `boolean `isIncludePrivate()`
- `public ` **void** `setIncludePrivate(boolean includePrivate)`
- `public `boolean `isIncludeProtected()`
- `public ` **void** `setIncludeProtected(boolean includeProtected)`
- `public `boolean `isIncludePublic()`
- `public ` **void** `setIncludePublic(boolean includePublic)`
- `public `boolean `isTableOfContent()`
- `public ` **void** `setTableOfContent(boolean tableOfContent)`
</details>

<details open>

<summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open>

<summary>Private</summary>

> _No hay métodos private visibles_
</details>

