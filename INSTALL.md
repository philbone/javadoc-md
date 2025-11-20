```
      d8b                                 888                                          888
      Y8P                                 888                                          888
                                          888                                          888
     8888  8888b.  888  888  8888b.   .d88888  .d88b.   .d8888b     88888b.d88b.   .d88888
     "888     "88b 888  888     "88b d88" 888 d88""88b d88P"        888 "888 "88b d88" 888
      888 .d888888 Y88  88P .d888888 888  888 888  888 888          888  888  888 888  888
      888 888  888  Y8bd8P  888  888 Y88b 888 Y88..88P Y88b.    d8b 888  888  888 Y88b 888
      888 "Y888888   Y88P   "Y888888  "Y88888  "Y88P"   "Y8888P Y8P 888  888  888  "Y88888
      888
     d88P
   888P"
d8b                   888             888 888                                d8b          888
Y8P                   888             888 888                                Y8P          888
                      888             888 888                                             888
888 88888b.  .d8888b  888888  8888b.  888 888      .d8888b   .d8888b 888d888 888 88888b.  888888
888 888 "88b 88K      888        "88b 888 888      88K      d88P"    888P"   888 888 "88b 888
888 888  888 "Y8888b. 888    .d888888 888 888      "Y8888b. 888      888     888 888  888 888
888 888  888      X88 Y88b.  888  888 888 888           X88 Y88b.    888     888 888 d88P Y88b.
888 888  888  88888P'  "Y888 "Y888888 888 888       88888P'  "Y8888P 888     888 88888P"   "Y888
                                                                                 888
                                                                                 888
                                                                                 888
```

# Guía de Instalación de JavaDocMd

## 📋 Tabla de Contenidos

- Métodos de Instalación
- Opción 1: Instalación Automática
- Opción 2: Instalación Manual en Home
- Opción 3: Uso Directo en Proyecto
- Verificación
- Solución de Problemas
- Desinstalación

## 🚀 Métodos de Instalación
Elige el método que mejor se adapte a tus necesidades:


|Método|	Dificultad	|Recomendado para
|---         |---         |---         |
|Automática	|🟢 Muy Fácil	|Usuarios que quieren empezar rápido|
|Manual en Home	|🟡 Media	|Usuarios que prefieren control total|
|Directo en Proyecto	|🔵 Fácil	|Proyectos específicos o CI/CD|

## 🎯 Opción 1: Instalación Automática (Recomendada)
Es la opción recomendada. Tienes dos scripts para correr a elección:
un fichero `install.sh` para correr sobre Linux/Mac y otro fichero `install.bat` para correr sobre Windows.

Un tercer fichero `install` puede ser usado opcionalmente para permitir que el sistema operativo elija.

En cualquier caso el flujo de instalación es el siguiente:

1. Descarga y descomprime el ZIP
2. Navega a la carpeta descomprimida
3. Ejecuta el script de instalación

### Para Linux/macOS

#### 🔒 Antes que nada: permisos de ejecución

```
# Lo primero es otorgar permisos de ejecución al script de instalación
chmod +x install.sh

# Luego ejecutar normalmente
./install.sh
```

### Para Windows:
Los archivos .bat no requieren configuración de permisos especiales. Puedes ejecutarlos directamente.

Puedes hacer doble clic sobre el fichero `install.bat`

```
# O puedes ejecutar desde la consola
install.bat
```

### ¿Qué hace el script automático?

- Crea el directorio ~/.javadocmd/ (Linux/macOS) o %USERPROFILE%\.javadocmd\ (Windows)
- Mueve el archivo JAR al directorio de instalación
- Configura un alias/comando global javadocmd
- Proporciona instrucciones para usar la herramienta

## 🔧 Opción 2: Instalación Manual en Directorio Home
La instalación manual prescinde de los ficheros install/install.sh/install.bat.
Este método es útil si no te funciona el método automático o si prefieres tener control sobre lo que se instala en tu máquina.

### Paso 1: Crear directorio de instalación
Crea el directorio (si no existe). Puedes hacerlo con tu explorador o vía terminal:
```
mkdir -p ~/.javadocmd
```

### Paso 2: Mover el archivo JAR
Asumiendo que ya descargaste y descomprimiste el ZIP, navega a la carpeta descomprimida(deberías ver el JAR).

Copia javadocmd-1.0.0.jar y pega en el directorio ~/.javadocmd
De otra manera, si prefieres usar la terminal:
```
# Mover el JAR al directorio de instalación
# (asumiendo que el JAR está en tu directorio actual)

mv javadocmd-1.0.0.jar ~/.javadocmd/
```
### Paso 3: Configurar el alias
#### Para Bash (.bashrc)
```
# Editar el archivo de configuración de Bash
nano ~/.bashrc
# o
code ~/.bashrc

# Agregar esta línea al final del archivo:
alias javadocmd='java -jar ~/.javadocmd/javadocmd-1.0.0.jar'
```

#### Para Zsh (.zshrc)
```
# Editar el archivo de configuración de Zsh
nano ~/.zshrc
# o
code ~/.zshrc

# Agregar esta línea al final del archivo:
alias javadocmd='java -jar ~/.javadocmd/javadocmd-1.0.0.jar'
```

### Paso 4: Recargar la configuración
Para que el alias `javadocmd` esté disponible inmediatamente, debes recargar el fichero bashrc o zshrc según sea tu caso:

```
# Para Bash
source ~/.bashrc

# Para Zsh
source ~/.zshrc
```

## 📁 Opción 3: Uso Directo en Proyecto
Otra forma rápida de uso es pegar el JAR directamente en el directorio raíz de tu proyecto.

### Método A: JAR en el mismo proyecto
```
# Copiar el JAR a tu proyecto
cp javadocmd-1.0.0.jar /ruta/a/tu/proyecto/

# Usarlo desde cualquier lugar dentro del proyecto
java -jar javadocmd-1.0.0.jar
```

### Método B: Ruta absoluta
```
# Usar el JAR con ruta absoluta
java -jar /ruta/completa/a/tu/proyecto/javadocmd-1.0.0.jar
```

### Ejemplo en script de build
```
#!/bin/bash
# generate-docs.sh

# Configuración
INPUT_DIR="./src"
OUTPUT_DIR="./docs"
JAR_PATH="./javadocmd-1.0.0.jar"

# Generar documentación
java -jar $JAR_PATH --input $INPUT_DIR --output $OUTPUT_DIR

echo "✅ Documentación generada en $OUTPUT_DIR"
```

### Ejemplo en package.json (si usas npm)
```
{
  "scripts": {
    "generate-docs": "java -jar javadocmd-1.0.0.jar --input ./src --output ./docs"
  }
}
```

## ✅ Verificación de la Instalación
Independientemente del método elegido, verifica que la instalación fue exitosa:
```
# Para Opciones 1 y 2
javadocmd --help

# Para Opción 3
java -jar javadocmd-1.0.0.jar --help
```
Deberías ver la ayuda de JavadocMd con todas las opciones disponibles.

## 🔧 Solución de Problemas
Solución a problemas típicos.

### Problema típico 1: "Comando no encontrado" después de la instalación
Es probable que luego de instalar JavadocMD o de crear el alias, no hayas recargado el shell.
```
# Solución: Recargar el shell
source ~/.bashrc   # Para Bash
source ~/.zshrc    # Para Zsh

# O cerrar y abrir una nueva terminal
```

### Problema típico 2: Permisos denegados en Linux/macOS
Antes de ejecutar en Linux/mac es necesario que otorges permiso de ejecución al(los) script(s) de instalación.
```
# Dar permisos de ejecución al script
chmod +x install.sh

# Luego ejecutar
./install.sh
```

### Problema típico 3: El alias no persiste después de reiniciar la terminal
- Verifica que agregaste el alias al archivo correcto (.bashrc o .zshrc)
- Confirma que el archivo existe en tu home directory

### Problema típico 4: Java no está instalado
Comprueba si Java está instalado en tu sistema.
```
# Verificar instalación de Java
java -version
```
Si no está instalado, instálalo [según tu sistema operativo](https://www.java.com/en/download/help/download_options.html).

### Problema típico 5: Los scripts de instalación fallaron
Si los scripts nativos install.sh o install.bat falló, puedes recurrir al comando alias incluido en JavadocMd. El comando alias no es capaz de mover el JAR, pero puede crear el alias por ti.
```
java -jar javadocmd-1.0.0.jar install
```

## 🗑️ Desinstalación
Para desinstalar JavadocMD solo debes eliminar el directorio de instalación. O el fichero JAR directamente. Si creaste un alias de comando también deberías removerlo.

### Si instalaste JavadocMd de forma automática o manual:
```
# 1. Eliminar el directorio de instalación
rm -rf ~/.javadocmd

# 2. Remover el alias del archivo de configuración
# Abrir con un editor ~/.bashrc o ~/.zshrc y eliminar la línea del alias

# 3. Recargar la configuración
source ~/.bashrc   # o source ~/.zshrc
```

### Si instalaste JavadocMd dentro de tu proyecto:
```
# Simplemente eliminar el JAR del proyecto
rm javadocmd-1.0.0.jar
```

## ❓ Preguntas Frecuentes
### ¿Puedo tener múltiples versiones instaladas?
Sí, puedes:

- Solo debes renombrar el JAR: `javadocmd-2.0.0.jar`
- Y crear aliases diferentes: `alias javadocmd2='java -jar ~/.javadocmd/javadocmd-2.0.0.jar'`

### ¿Funciona en Windows Subsystem for Linux (WSL)?
Sí, usa los métodos para Linux.

### ¿Necesito permisos de administrador?
No, todas las instalaciones son en el directorio de usuario.

## 📞 Soporte
Si encuentras problemas:

1. Revisa esta guía nuevamente
1. Verifica que Java esté instalado correctamente
1. Ejecuta el comando con --help para ver las opciones disponibles
