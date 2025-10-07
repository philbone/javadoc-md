# JavadocMD

## io.github.philbone.javadocmd.config

## Resumen de Clases


|CLASE|DESCRIPCIÓN|
|---|---|
|[public class ConfigLoader](#-public-class-configloader)|Esta clase se encarga de cargar el fichero de configuración
|[public class Config](#-public-class-config)|
## 📘 Public Class ConfigLoader

```java
public class ConfigLoader
```
> **Descripción:**
> Esta clase se encarga de cargar el fichero de configuración

### 🧮 Métodos

- `public static`Config `loadConfig()`
> - *@return* un objeto con los datos de configuracion iniciales.
## 📘 Public Class Config

```java
public class Config
```
### 📦 Campos

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `sourcePath`
> Directorio de entrada donde se encuentran las clases a documentar. *

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `outputPath`
> Directorio base donde se escribirá la documentación generada.

- `private` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `outFileName`
> Nombre del archivo de salida que contendrá la documentación en cada
> paquete.

- `private` boolean `debugMode`
> Bandera de depuración para imprimir trazas adicionales.

### 🛠️ Constructores

- `public Config()`
### 🧮 Métodos

- `public`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getSourcePath()`
> Obtiene la ruta del código fuente a documentar.

> - *@return* el directorio donde se encuentran el código fuente.
- `public` **void** `setSourcePath(String sourcePath)`
> - *@param* **sourcePath** 
- `public`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getOutputPath()`
> - *@return* 
- `public` **void** `setOutputPath(String outputPath)`
> - *@param* **outputPath** 
- `public`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `getOutFileName()`
> - *@return* 
- `public` **void** `setOutFileName(String outFileName)`
> - *@param* **outFileName** 
- `public`boolean `isDebugMode()`
> - *@return* 
- `public` **void** `setDebugMode(boolean debugMode)`
> - *@param* **debugMode** 
