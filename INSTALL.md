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
|Automática	|🟢 Fácil	|Usuarios que quieren empezar rápido|
|Manual en Home	|🟡 Media	|Usuarios que prefieren control total|
|Directo en Proyecto	|🟡 Media	|Proyectos específicos o CI/CD|

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


