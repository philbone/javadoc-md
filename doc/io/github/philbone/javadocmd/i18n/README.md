## io.github.philbone.javadocmd.i18n

## Resumen de Clases


|#|CLASE|DESCRIPCIÓN|
|---|---|---|
|**1**|[public class LanguageManager](#1-public-class-languagemanager)|Gestiona la carga y acceso a textos traducidos para la documentación.
## #1 📘 Public Class LanguageManager

```java
public class LanguageManager
```
> **Descripción:**
> Gestiona la carga y acceso a textos traducidos para la documentación.
> Usa archivos YAML externos ubicados en la carpeta "langs/".
> 
> Si no se encuentra el idioma solicitado, carga inglés por defecto.
> Si falta alguna clave, devuelve la clave original como fallback.
> 
> Ejemplo de uso:
> LanguageManager lang = new LanguageManager("es", Paths.get("."));
> System.out.println(lang.get("classes")); // → "Clases"

### 🧮 Métodos

<details open><summary>Public</summary>

> _No hay métodos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay métodos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay métodos private visibles_
</details>

> _No hay métodos visibles_
### 🛠️ Constructores

> _No hay constructores visibles_
### 📦 Campos

<details open><summary>Public</summary>

> _No hay campos public visibles_
</details>

<details open><summary>Protected</summary>

> _No hay campos protected visibles_
</details>

<details open><summary>Private</summary>

> _No hay campos private visibles_
</details>

> _No hay campos visibles_
