
# Uso: Generar ZIP de instalación (Linux/Mac & Windows)

El script zip-release sirve para crear un zip de instalación rápidamente. Este documento explica cómo aprovechar la utilidad zip-release incluida para generar un ZIP de instalación de JavadocMd desde el código compilado.

Hay dos scripts:

- `zip-release.sh` — Script POSIX (Linux / macOS / WSL).
- `zip-release.bat` — Script para Windows (CMD). (Si prefieres PowerShell, existe `zip-release.ps1` opcional.)

Ambos scripts realizan los mismos pasos:
- Buscan el JAR compilado con el patrón `javadocmd-X.X.X.jar` en el directorio `target/`.
- Extraen la versión desde el nombre del JAR.
- Verifican que los archivos requeridos estén presentes.
- Copian los archivos a un directorio temporal.
- Crean un ZIP `release/<version>/javadocmd-<version>.zip` con los archivos necesarios.
- Muestran información sobre el ZIP creado.

---

## Requisitos comunes
- El proyecto debe estar compilado (p. ej. `mvn package`) y producir `target/javadocmd-X.X.X.jar`.
- Archivos requeridos en la raíz del proyecto:
  - `README.md`
  - `install`
  - `install.sh`
  - `install.bat`
  - `cat.webp`

---