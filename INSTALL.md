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