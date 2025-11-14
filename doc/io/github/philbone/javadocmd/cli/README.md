## io.github.philbone.javadocmd.cli

## Resumen de Clases


|#|CLASE|DESCRIPCIÓN|
|---|---|---|
|**1**|[public class ValidationIssue](#1-public-class-validationissue)|Representa un problema de validación con su corrección asociada
|**2**|[public class JavadocmdCLI](#2-public-class-javadocmdcli)|
|**3**|[public class ValidateCommand](#3-public-class-validatecommand)|
|**4**|[public class SetCommand](#4-public-class-setcommand)|
|**5**|[public class GetCommand](#5-public-class-getcommand)|
|**6**|[public class InitCommand](#6-public-class-initcommand)|
|**7**|[public class ShowCommand](#7-public-class-showcommand)|
|**8**|[public class AliasCommand](#8-public-class-aliascommand)|
<details>
<summary> <strong> 📘 Public Class ValidationIssue</strong> </summary>

## #1 📘 Public Class ValidationIssue

```java
public class ValidationIssue
```
> **Descripción:**
> Representa un problema de validación con su corrección asociada

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

</details>
<details>
<summary> <strong> 📘 Public Class JavadocmdCLI</strong> </summary>

## #2 📘 Public Class JavadocmdCLI

```java
@Command(name = "javadocmd", mixinStandardHelpOptions = true, version = "1.0.0", description = "${description.app}", resourceBundle = "messages", subcommands = [InitCommand.class, ShowCommand.class, GetCommand.class, SetCommand.class, ValidateCommand.class, AliasCommand.class])
public class JavadocmdCLI
implements Callable
```
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

</details>
<details>
<summary> <strong> 📘 Public Class ValidateCommand</strong> </summary>

## #3 📘 Public Class ValidateCommand

```java
@Command(name = "validate", description = "${usage.validate}", resourceBundle = "messages")
public class ValidateCommand
implements Callable
```
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

</details>
<details>
<summary> <strong> 📘 Public Class SetCommand</strong> </summary>

## #4 📘 Public Class SetCommand

```java
@Command(name = "set", description = "${usage.set}", resourceBundle = "messages")
public class SetCommand
implements Callable
```
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

</details>
<details>
<summary> <strong> 📘 Public Class GetCommand</strong> </summary>

## #5 📘 Public Class GetCommand

```java
@Command(name = "get", description = "${usage.get}", resourceBundle = "messages")
public class GetCommand
implements Callable
```
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

</details>
<details>
<summary> <strong> 📘 Public Class InitCommand</strong> </summary>

## #6 📘 Public Class InitCommand

```java
@Command(name = "init", description = "${usage.init}", resourceBundle = "messages")
public class InitCommand
implements Callable
```
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

</details>
<details>
<summary> <strong> 📘 Public Class ShowCommand</strong> </summary>

## #7 📘 Public Class ShowCommand

```java
@Command(name = "show", description = "${usage.show}", resourceBundle = "messages")
public class ShowCommand
implements Callable
```
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

</details>
<details>
<summary> <strong> 📘 Public Class AliasCommand</strong> </summary>

## #8 📘 Public Class AliasCommand

```java
@Command(name = "alias", aliases = ["make-alias"], description = "${usage.alias}", mixinStandardHelpOptions = true, resourceBundle = "messages")
public class AliasCommand
implements Callable
```
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

</details>
