@echo off
chcp 65001 >nul
setlocal enabledelayedexpansion

echo 🚀 Instalando JavaDocMd...

:: Directorio de instalación
set "INSTALL_DIR=%USERPROFILE%\.javadocmd"
set "JAR_FILE=javadocmd-1.0.0.jar"

:: Verificar si el JAR existe
if not exist "%JAR_FILE%" (
    echo ❌ Error: No se encuentra %JAR_FILE% en el directorio actual
    echo    Asegúrate de ejecutar este script desde la carpeta donde está el JAR
    pause
    exit /b 1
)

:: Crear directorio de instalación
echo 📁 Creando directorio de instalación: %INSTALL_DIR%
if not exist "%INSTALL_DIR%" mkdir "%INSTALL_DIR%"

:: Mover el JAR
echo 📦 Moviendo %JAR_FILE% a %INSTALL_DIR%\
move "%JAR_FILE%" "%INSTALL_DIR%\" >nul

if !errorlevel! equ 0 (
    echo ✅ JAR movido exitosamente
) else (
    echo ❌ Error al mover el JAR
    pause
    exit /b 1
)

:: Crear script batch para el alias (Windows no tiene aliases nativos)
set "BATCH_FILE=%USERPROFILE%\AppData\Local\Microsoft\WindowsApps\javadocmd.bat"
echo @echo off > "%BATCH_FILE%"
echo java -jar "%INSTALL_DIR%\%JAR_FILE%" %%* >> "%BATCH_FILE%"

echo 🔧 Creando comando global...

:: Mensaje final
echo.
echo 🎉 Instalación completada!
echo.
echo 📝 Para usar JavaDocMd, puedes:
echo    - Ejecutar 'javadocmd' desde cualquier terminal
echo    - O usar 'javadocmd.bat' directamente
echo.
echo 📋 El JAR está instalado en:
echo    %INSTALL_DIR%\%JAR_FILE%
echo.

pause