#!/bin/bash

# Utilidad para crear el zip de instalación de JavadocMd
# Autor: Felipe M. <philbone@focused.cl>

# Colores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Función para imprimir mensajes informativos
info() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

# Función para imprimir mensajes de éxito
success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

# Función para imprimir mensajes de advertencia
warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

# Función para imprimir mensajes de error
error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Función para verificar si un comando existe
command_exists() {
    command -v "$1" >/dev/null 2>&1
}

# Verificar dependencias
check_dependencies() {
    local missing_deps=()
    
    if ! command_exists zip; then
        missing_deps+=("zip")
    fi
    
    if [ ${#missing_deps[@]} -ne 0 ]; then
        error "Dependencias faltantes: ${missing_deps[*]}"
        echo "Por favor, instala las dependencias necesarias:"
        for dep in "${missing_deps[@]}"; do
            echo "  - $dep"
        done
        exit 1
    fi
}

# Obtener información de la versión
get_version_info() {
    # Buscar el archivo JAR en target
    local jar_files=$(find target -name "javadocmd-*.jar" -type f 2>/dev/null)
    
    if [ -z "$jar_files" ]; then
        error "No se encontraron archivos JAR en el directorio target/"
        echo "Por favor, ejecuta 'mvn package' o 'ant build' primero."
        exit 1
    fi
    
    # Tomar el primer JAR encontrado
    local jar_file=$(echo "$jar_files" | head -n1)
    local jar_name=$(basename "$jar_file")
    
    # Extraer versión del nombre del JAR
    if [[ $jar_name =~ javadocmd-([0-9]+\.[0-9]+\.[0-9]+)\.jar ]]; then
        VERSION="${BASH_REMATCH[1]}"
        JAR_FILE="$jar_file"
        JAR_NAME="$jar_name"
    else
        error "No se pudo extraer la versión del archivo JAR: $jar_name"
        echo "Por favor, asegúrate de que el JAR sigue el formato: javadocmd-X.X.X.jar"
        exit 1
    fi
}

# Verificar archivos requeridos
check_required_files() {
    local missing_files=()
    
    # Archivos requeridos
    local required_files=(
        "README.md"
        "install"
        "install.sh" 
        "install.bat"
        "cat.webp"
    )
    
    for file in "${required_files[@]}"; do
        if [ ! -f "$file" ]; then
            missing_files+=("$file")
        fi
    done
    
    if [ ${#missing_files[@]} -ne 0 ]; then
        error "Archivos requeridos no encontrados:"
        for file in "${missing_files[@]}"; do
            echo "  - $file"
        done
        echo "Por favor, asegúrate de que todos los archivos estén en el directorio actual."
        exit 1
    fi
}

# Crear directorio temporal
create_temp_dir() {
    TEMP_DIR=$(mktemp -d)
    if [ $? -ne 0 ]; then
        error "No se pudo crear directorio temporal"
        exit 1
    fi
    
    # Crear estructura en directorio temporal
    mkdir -p "$TEMP_DIR/javadocmd-$VERSION"
}

# Copiar archivos al directorio temporal
copy_files() {
    info "Copiando archivos al directorio temporal..."
    
    # Copiar JAR
    if ! cp "$JAR_FILE" "$TEMP_DIR/javadocmd-$VERSION/"; then
        error "Error copiando $JAR_FILE"
        return 1
    fi
    
    # Copiar archivos adicionales
    local additional_files=(
        "README.md"
        "install"
        "install.sh"
        "install.bat" 
        "cat.webp"
    )
    
    for file in "${additional_files[@]}"; do
        if [ -f "$file" ]; then
            if ! cp "$file" "$TEMP_DIR/javadocmd-$VERSION/"; then
                warning "Error copiando $file"
            fi
        else
            warning "Archivo $file no encontrado, omitiendo..."
        fi
    done
    
    success "Archivos copiados correctamente"
    return 0
}

# Crear archivo ZIP
create_zip() {
    local output_dir="$1"
    local zip_name="javadocmd-$VERSION.zip"
    local zip_path="$output_dir/$zip_name"
    
    info "Creando archivo ZIP: $zip_path"
    
    # Verificar que el directorio temporal tiene contenido
    if [ ! -d "$TEMP_DIR/javadocmd-$VERSION" ]; then
        error "Directorio temporal no tiene contenido"
        return 1
    fi
    
    # Usar ruta absoluta para el ZIP
    local absolute_zip_path=$(realpath "$zip_path")
    
    # Crear ZIP desde el directorio temporal
    if (cd "$TEMP_DIR" && zip -r "$absolute_zip_path" "javadocmd-$VERSION" > /dev/null); then
        success "ZIP creado exitosamente: $zip_path"
        echo "Tamaño del archivo: $(du -h "$zip_path" | cut -f1)"
        return 0
    else
        error "Error al crear el archivo ZIP"
        # Verificar permisos y espacio en disco
        echo "Verifica:"
        echo "  - Permisos de escritura en: $output_dir"
        echo "  - Espacio disponible en disco"
        echo "  - Que el directorio target existe"
        return 1
    fi
}

# Limpiar directorio temporal
cleanup() {
    if [ -n "$TEMP_DIR" ] && [ -d "$TEMP_DIR" ]; then
        rm -rf "$TEMP_DIR"
        info "Directorio temporal limpiado"
    fi
}

# Función principal
main() {
    echo "========================================="
    echo "  Generador de ZIP de instalación"
    echo "           JavadocMd"
    echo "========================================="
    echo ""
    
    # Verificar dependencias
    check_dependencies
    
    # Obtener información de versión
    get_version_info
    
    info "Versión detectada: $VERSION"
    info "Archivo JAR: $JAR_FILE"
    echo ""
    
    # Verificar archivos requeridos
    check_required_files
    
    # Directorio de salida por defecto
    OUTPUT_DIR="release/$VERSION"
    info "Directorio de salida: $OUTPUT_DIR"
    
    # Crear directorio de salida si no existe
    if [ ! -d "$OUTPUT_DIR" ]; then
        info "Creando directorio de salida: $OUTPUT_DIR"
        if ! mkdir -p "$OUTPUT_DIR"; then
            error "No se pudo crear el directorio de salida: $OUTPUT_DIR"
            exit 1
        fi
    fi
    
    # Verificar permisos de escritura
    if [ ! -w "$OUTPUT_DIR" ]; then
        error "No tienes permisos de escritura en: $OUTPUT_DIR"
        exit 1
    fi
    
    # Crear directorio temporal
    create_temp_dir
    
    # Configurar trap para limpiar en caso de interrupción
    trap cleanup EXIT INT TERM
    
    # Copiar archivos
    if ! copy_files; then
        error "Error copiando archivos"
        exit 1
    fi
    
    # Crear ZIP
    if create_zip "$OUTPUT_DIR"; then
        echo ""
        success "Proceso completado exitosamente!"
        info "Archivos incluidos en el ZIP:"
        echo "  - $JAR_NAME"
        echo "  - README.md"
        echo "  - install"
        echo "  - install.sh"
        echo "  - install.bat"
        echo "  - cat.webp"
        echo ""
        info "Ubicación del ZIP: $OUTPUT_DIR/javadocmd-$VERSION.zip"
        
        # Mostrar contenido del ZIP creado
        echo ""
        info "Contenido del ZIP:"
        unzip -l "$OUTPUT_DIR/javadocmd-$VERSION.zip"
    else
        error "Error creando el archivo ZIP"
        exit 1
    fi
}

# Ejecutar función principal
main "$@"