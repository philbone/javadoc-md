# JavadocMD

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

- `private static` int `executionCount`
> Contador global de ejecuciones del generador de documentación.

### 🛠️ Constructores

> _No hay constructores visibles_
### 🧮 Métodos

- `public  static` **void** `main(String[] args)`
> Método principal que inicia el proceso de generación de documentación.

> - *@param* **args** argumentos opcionales (no utilizados actualmente). Se planea
en futuras versiones aceptar <code>sourcePath</code> y
<code>outputPath</code> como parámetros desde consola.
- `public  static` **void** `generatePackageDocs(Config config)`
- `public  static` **void** `generateCombinedDocs(Config config)`
- `public  static` **void** `forceJavaLevel(ParserConfiguration.LanguageLevel languageLevel)`
