@echo off
REM -------------------------------------------------------------
REM javadoc-md - zip-release.bat
REM Generador de ZIP de instalación para JavadocMd (Windows - CMD)
REM Compatibilidad máxima: intenta usar PowerShell, 7-Zip, tar o zip
REM -------------------------------------------------------------

setlocal enabledelayedexpansion

echo =========================================
echo   Generador de ZIP de instalación - JavadocMd
echo =========================================
echo.

REM Buscar el primer JAR en target\ con patrón javadocmd-*.jar
set "JAR_FILE="
set "JAR_NAME="
for /r "target" %%F in (javadocmd-*.jar) do (
    set "JAR_FILE=%%~fF"
    set "JAR_NAME=%%~nxF"
    set "JAR_BASE=%%~nF"
    goto :FOUND_JAR
)

echo [ERROR] No se encontraron archivos JAR en el directorio target\.
echo Por favor, ejecuta "mvn package" o "ant build" primero.
exit /b 1

:FOUND_JAR
echo [INFO] Archivo JAR encontrado: %JAR_FILE%

REM Validar nombre y extraer versión (espera javadocmd-X.X.X.jar)
echo %JAR_BASE% | findstr /r /c:"^javadocmd-[0-9][0-9]*\.[0-9][0-9]*\.[0-9][0-9]*$" >nul
if errorlevel 1 (
    echo [ERROR] No se pudo extraer la version del JAR: %JAR_NAME%
    echo Asegurate de que el JAR sigue el formato: javadocmd-X.X.X.jar
    exit /b 1
)
set "VERSION=%JAR_BASE:javadocmd-=%"
echo [INFO] Version detectada: %VERSION%
echo.

REM Verificar archivos requeridos
set "MISSING="
for %%G in (README.md install install.sh install.bat cat.webp) do (
    if not exist "%%G" (
        set "MISSING=!MISSING! %%G"
    )
)

if defined MISSING (
    echo [ERROR] Archivos requeridos no encontrados: %MISSING%
    echo Por favor, asegurate de que todos los archivos esten en el directorio actual.
    exit /b 1
)

REM Preparar directorio de salida
set "OUTPUT_DIR=%CD%\release\%VERSION%"
if not exist "%OUTPUT_DIR%" (
    echo [INFO] Creando directorio de salida: %OUTPUT_DIR%
    mkdir "%OUTPUT_DIR%" 2>nul || (
        echo [ERROR] No se pudo crear el directorio de salida: %OUTPUT_DIR%
        exit /b 1
    )
)

REM Verificar permisos de escritura (intentar crear archivo temporal)
set "TEST_FILE=%OUTPUT_DIR%\._javadocmd_test"
echo test > "%TEST_FILE%" 2>nul || (
    echo [ERROR] No tienes permisos de escritura en: %OUTPUT_DIR%
    exit /b 1
)
del /f /q "%TEST_FILE%" >nul 2>&1

REM Crear directorio temporal
set "TEMP_ROOT=%TEMP%\javadocmd-%RANDOM%-%RANDOM%"
set "TEMP_SUB=%TEMP_ROOT%\javadocmd-%VERSION%"
mkdir "%TEMP_SUB%" 2>nul || (
    echo [ERROR] No se pudo crear directorio temporal: %TEMP_SUB%
    exit /b 1
)
echo [INFO] Directorio temporal: %TEMP_SUB%

REM Copiar JAR y archivos
copy /Y "%JAR_FILE%" "%TEMP_SUB%\" >nul || (
    echo [ERROR] Error copiando %JAR_FILE%
    goto :CLEANUP
)
for %%H in (README.md install install.sh install.bat cat.webp) do (
    if exist "%%H" (
        copy /Y "%%H" "%TEMP_SUB%\" >nul || echo [WARNING] Error copiando %%H
    ) else (
        echo [WARNING] Archivo %%H no encontrado, omitiendo...
    )
)
echo [SUCCESS] Archivos copiados correctamente.
echo.

REM Determinar herramienta para crear ZIP
set "ZIP_NAME=javadocmd-%VERSION%.zip"
set "ZIP_PATH=%OUTPUT_DIR%\%ZIP_NAME%"
set "ARCHIVER="

REM Intentar usar PowerShell Compress-Archive si existe
for /f "usebackq delims=" %%A in (`powershell -NoProfile -Command "if (Get-Command Compress-Archive -ErrorAction SilentlyContinue) { Write-Output 'YES' }" 2^>nul`) do set "HAS_COMPRESS=%%A"
if "%HAS_COMPRESS%"=="YES" (
    echo [INFO] Usando Compress-Archive (PowerShell) para crear el ZIP...
    powershell -NoProfile -Command "Compress-Archive -Path '%TEMP_SUB%\*' -DestinationPath '%ZIP_PATH%' -Force" 1>nul 2>nul
    if errorlevel 1 (
        echo [ERROR] Compress-Archive fallo al crear el ZIP.
        goto :CLEANUP
    )
    set "ARCHIVER=Compress-Archive"
) else (
    REM Intentar 7z
    where 7z >nul 2>&1
    if not errorlevel 1 (
        echo [INFO] Usando 7-Zip para crear el ZIP...
        7z a -tzip "%ZIP_PATH%" "%TEMP_SUB%\*" >nul
        if errorlevel 1 (
            echo [ERROR] 7z fallo al crear el ZIP.
            goto :CLEANUP
        )
        set "ARCHIVER=7z"
    ) else (
        REM Intentar tar (Windows 10 tiene tar)
        where tar >nul 2>&1
        if not errorlevel 1 (
            echo [INFO] Usando tar para crear el ZIP...
            REM -a auto-detecta formato (zip) si extension .zip usada
            pushd "%TEMP_ROOT%" >nul
            tar -a -c -f "%ZIP_PATH%" "javadocmd-%VERSION%" >nul 2>nul
            popd >nul
            if errorlevel 1 (
                echo [ERROR] tar fallo al crear el ZIP.
                goto :CLEANUP
            )
            set "ARCHIVER=tar"
        ) else (
            REM Intentar zip.exe
            where zip >nul 2>&1
            if not errorlevel 1 (
                echo [INFO] Usando zip.exe para crear el ZIP...
                pushd "%TEMP_ROOT%" >nul
                zip -r "%ZIP_PATH%" "javadocmd-%VERSION%" >nul
                popd >nul
                if errorlevel 1 (
                    echo [ERROR] zip.exe fallo al crear el ZIP.
                    goto :CLEANUP
                )
                set "ARCHIVER=zip"
            )
        )
    )
)

if not defined ARCHIVER (
    echo [ERROR] No se encontro ningun metodo para crear ZIP.
    echo Instala PowerShell 5+ (Compress-Archive), 7-Zip (7z.exe), o tar/zip utilidades.
    goto :CLEANUP
)

echo [SUCCESS] ZIP creado: %ZIP_PATH%

REM Mostrar tamano del archivo
for %%I in ("%ZIP_PATH%") do set "ZIP_SIZE=%%~zI"
echo Tamaño del archivo: %ZIP_SIZE% bytes
echo.

REM Intentar listar contenido del ZIP (7z preferido, luego PowerShell)
if "%ARCHIVER%"=="7z" (
    echo [INFO] Contenido del ZIP:
    7z l "%ZIP_PATH%"
) else (
    REM Si PowerShell disponible, usar System.IO.Compression para listar
    for /f "usebackq delims=" %%P in (`powershell -NoProfile -Command "Add-Type -AssemblyName System.IO.Compression.FileSystem >\$null; try { [IO.Compression.ZipFile]::OpenRead('%ZIP_PATH%').Entries | ForEach-Object{ $_.FullName } } catch { Write-Output '' }" 2^>nul`) do (
        if "%%P"=="" (
            REM nada
        ) else (
            echo   %%P
        )
    )
)

echo.
echo [INFO] Ubicacion del ZIP: %ZIP_PATH%
echo.

REM LIMPIEZA y salida
:CLEANUP
if exist "%TEMP_ROOT%" (
    rd /s /q "%TEMP_ROOT%" 2>nul || (
        echo [WARNING] No se pudo eliminar el directorio temporal: %TEMP_ROOT%
    )
) else (
    REM nada
)

endlocal
exit /b 0