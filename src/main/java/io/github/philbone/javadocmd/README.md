# JavadocMD

## io.github.philbone.javadocmd

## Resumen de Clases


|#|CLASE|DESCRIPCIÓN|
|---|---|---|
|**1**|[public abstract class JavadocMd](#1-public-abstract-class-javadocmd)|Punto de entrada principal del programa javadoc-md .
## #1 📕 Public Abstract Class JavadocMd

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

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

- `private static` int `executionCount`
> Contador global de ejecuciones del generador de documentación.

</details>

### 🛠️ Constructores

- `protected JavadocMd()`
> **Descripción:**
> Constructor protegido por defecto.
> <p>
> Inicializa valores de configuración básicos.

> - *@throws* **IllegalStateException** si la configuración inicial es inválida.
### 🧮 Métodos

<details open><summary>Public</summary>

- `public  static` **void** `main(String[] args)`
> Método principal que inicia el proceso de generación de documentación.

> - *@param* **args** argumentos opcionales (no utilizados actualmente). Se planea
en futuras versiones aceptar <code>sourcePath</code> y
<code>outputPath</code> como parámetros desde consola.
- `public  static` **void** `generatePackageDocs(Config config)`
- `public  static` **void** `generateCombinedDocs(Config config)`
- `public  static` **void** `forceJavaLevel(ParserConfiguration.LanguageLevel languageLevel)`
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

