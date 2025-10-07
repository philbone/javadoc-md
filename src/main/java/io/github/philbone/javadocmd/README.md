# JavadocMd

## io.github.philbone.javadocmd

## Resumen de Clases


|CLASE|DESCRIPCIÓN|
|---|---|
|[public abstract_class JavadocMd](#-public-abstract_class-javadocmd)|Punto de entrada principal del programa javadoc-md .
## 📕 Public Abstract Class JavadocMd

```java
public abstract class JavadocMd
```
> **Descripción:**
> Punto de entrada principal del programa <b>javadoc-md</b>.
> <p>
> Esta clase se encarga de:
> <ul>
> <li>Recorrer los archivos fuente de un proyecto Java.</li>
> <li>Analizar sus clases, interfaces, enums y records.</li>
> <li>Extraer la documentación Javadoc mediante
> {@link JavadocExtractorVisitor}.</li>
> <li>Generar documentación en formato Markdown usando un
> {@link DocExporter}.</li>
> </ul>
> 
> <p>
> Actualmente soporta la exportación de documentación hacia un archivo
> <code>README.md</code> por cada paquete encontrado en el proyecto.</p>

### 📦 Campos

- `private static` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `outFileName`
> Nombre por defecto del archivo de salida que contendrá la documentación
> en cada paquete.

- `private static` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `sourcePath`
> Directorio de entrada donde se encuentran las clases a documentar.

- `private static` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `outputPath`
> Directorio base donde se escribirá la documentación generada.

- `private static` int `executionCount`
> Contador global de ejecuciones del generador de documentación.

- `private static` boolean `debug`
> Bandera de depuración para imprimir trazas adicionales.

### 🛠️ Constructores

- `protected JavadocMd()`
> **Descripción:**
> Constructor protegido por defecto.
> <p>
> Inicializa valores de configuración básicos.

> - *@throws* **IllegalStateException** si la configuración inicial es inválida.
### 🧮 Métodos

- `public static` **void** `main(String[] args)`
> Método principal que inicia el proceso de generación de documentación.

> - *@param* **args** argumentos opcionales (no utilizados actualmente). Se planea
en futuras versiones aceptar <code>sourcePath</code> y
<code>outputPath</code> como parámetros desde consola.
- `public static` **void** `generateDocs(String sourcePath, String outputPath)`
> Genera la documentación en formato Markdown a partir del código fuente de
> un proyecto Java.
> <p>
> El proceso sigue los siguientes pasos:
> <ol>
> <li>Recorrer todos los archivos <code>.java</code> en el directorio de
> entrada.</li>
> <li>Analizar cada archivo con {@link StaticJavaParser}.</li>
> <li>Obtener el nombre del paquete y crear un objeto {@link DocPackage}
> asociado.</li>
> <li>Visitar cada clase, método y comentario con
> {@link JavadocExtractorVisitor}.</li>
> <li>Exportar la documentación usando un {@link MarkdownExporter}.</li>
> </ol>

> - *@param* **sourcePath** ruta del directorio que contiene los archivos fuente de
Java a documentar.
> - *@param* **outputPath** ruta del directorio donde se guardará la documentación
generada. Si es <code>null</code> o vacío, la documentación se imprime en
consola.
- `public static` **void** `forceJavaLevel(ParserConfiguration.LanguageLevel languageLevel)`
