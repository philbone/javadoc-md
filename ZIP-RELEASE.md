
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

## Uso en Linux/macOS (zip-release.sh)
1. Asegúrate de que `zip` esté instalado (por ejemplo `sudo apt install zip` o `brew install zip` en macOS).
1. Compila el proyecto: `mvn package` (o el comando que uses).
1. Verifica que el JAR fue creado en el directorio target/ Si estás trabajando con NetBeans deberías tener este directorio, sino, créalo tú mismo y genera el JAR en esta ubicación.
1. Desde la raíz del repositorio ejecutas zip-release:
   ```bash
   # primero otorga permiso de ejecución al script
   chmod +x zip-release.sh

   # luego puedes ejecutarlo
   ./zip-release.sh
   ```
1. El ZIP resultante será creado en `release/<version>/javadocmd-<version>.zip`.

Problemas comunes:
- "No se encontraron archivos JAR en el directorio target/": ejecuta el build para generar el JAR.
- Falta `zip`: instala la utilidad `zip` o usa otra forma para comprimir.

---

## Uso en Windows (zip-release.bat)
Al igual que la versión para Linux/macOS, el script zip-release.bat intentará usar la mejor herramienta disponible en tu sistema para crear el ZIP.
Asegúrate de tener al menos una de estas herramientas instaladas:

- Compress-Archive (PowerShell 5+)
- 7-Zip (`7z.exe`)
- tar (Windows 10+ que incluya tar)
- zip.exe

Luego:
1. Compila el proyecto desde NetBeans, `mvn package`, o el comando que uses.
1. Verifica que el JAR fue creado en el directorio target/ Si estás trabajando con NetBeans deberías tener este directorio, sino, créalo tú mismo y genera el JAR en esta ubicación.
1. Desde la raíz del repositorio ejecutas zip-release:
   ```
   zip-release.bat
   ```
1. El ZIP resultante será creado en `release\<version>\javadocmd-<version>.zip`.

Si el script no encuentra ninguna herramienta para crear ZIP aparecerá una instrucción para instalar PowerShell 5+, 7-Zip o utilidades tar/zip.

Notas para Windows antiguos:
- En máquinas muy antiguas que no tengan PowerShell ni 7-Zip, instala 7-Zip y asegúrate de que `7z.exe` esté en el PATH.
- Alternativamente usa la versión PowerShell (`zip-release.ps1`) si PowerShell está disponible.

---

## Qué se incluye en el ZIP
- `javadocmd-X.X.X.jar`
- `README.md`
- `install`
- `install.sh`
- `install.bat`
- `cat.webp`

---

## Solución de problemas
- No se encuentra el JAR: asegúrate de compilar de que el JAR tenga el formato `javadocmd-X.X.X.jar` y de que este se encuentre en el directorio target/.
- Error al crear ZIP: revisa permisos en `release/<version>` y el espacio disponible en disco.
- El script Batch no lista el contenido del ZIP: si no está disponible una utilidad de listado, instala 7-Zip o usa PowerShell para inspeccionar el contenido.

---
