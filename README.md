```                                                                                                                                                                    
   d8b                                 888                                            888 
   Y8P                                 888                                            888 
                                       888                                            888 
  8888  8888b.  888  888  8888b.   .d88888  .d88b.   .d8888b       88888b.d88b.   .d88888 
  "888     "88b 888  888     "88b d88" 888 d88""88b d88P"          888 "888 "88b d88" 888 
   888 .d888888 Y88  88P .d888888 888  888 888  888 888            888  888  888 888  888 
   888 888  888  Y8bd8P  888  888 Y88b 888 Y88..88P Y88b.          888  888  888 Y88b 888 
   888 "Y888888   Y88P   "Y888888  "Y88888  "Y88P"   "Y8888P       888  888  888  "Y88888 
   888
  d88P
888P"
```

# 📘 JavadocMd

**JavadocMd** convierte los comentarios Javadoc de tu código Java en documentación en formato Markdown lista para usarse en repositorios (GitHub/GitLab). Diseñado para ser simple, configurable y ejecutable desde CLI.

- Genera documentación legible por humanos a partir de los Javadoc de tus clases, métodos y constructores.
- Soporta exportación por paquete o en archivos combinados, índices y enlaces automáticos a la API estándar de Java y a clases internas.

## Instalación Rápida 🚀

### Opción 1: Automática (Recomendada)
```bash
./install.sh        # Linux/macOS
install.bat         # Windows
```

### Opción 2: Manual
```
mkdir -p ~/.javadocmd
mv javadocmd-1.0.0.jar ~/.javadocmd/
echo "alias javadocmd='java -jar ~/.javadocmd/javadocmd-1.0.0.jar'" >> ~/.bashrc
source ~/.bashrc
```

### Opción 3: Instalar a nivel de proyecto
Mueves el fichero javadocmd-1.0.0.jar al directorio raíz de tu proyecto, y luego usas:
```
java -jar javadocmd-1.0.0.jar
```

[ver instrucciones de instalación completas](INSTALL.md)

---

## ✨ Características principales

### 🧩 Exportación inteligente
- Genera documentación **por paquete** o **consolidada en un único archivo**.
- Soporta **clases, interfaces, enums, records y constructores**.
- Procesa etiquetas `@param`, `@return`, `@throws` y `@see`.
- Crea **índices interactivos**, bloques de cita y tablas de contenido numeradas.
- Enlaces automáticos a:
  - La **API oficial de Java** (`List`, `Map`, `LocalDate`, etc.).
  - **Clases internas** del propio proyecto.
- Conversión automática de `<img>` en Javadoc → `![alt](url)` Markdown.

### ⚙️ Configuración avanzada (YAML)
Control total mediante `config.yml`:

```yaml
sourcePath: ./src                   # Establece el directorio del código fuente
outputPath: ./doc                   # Establece el directorio de salida
outFileName: README.md              # Establece el nombre del fichero de salida
debugMode: false                    # Activa/Desactiva el modo depuración
combinePackagesMode: false          # Combina/Separa la documentación
includePrivate: false               # Muestra/Oculta miembros privados
includeProtected: false             # Muestra/Oculta miembros protegidos
includePublic: false                # Muestra/Oculta miembros publicos
tableOfContent: true                # Muestra/Oculta la tabla de contenido
printEmptyNotify: true              # Imprime listas de miembros aun estando vacías
printClassIndex: true               # Muestra/Oculta índice de las clases en la tabla de contenido
foreSignClassIndex: '#'             # Establece el signo que aparecerá delante de cada Clase
foreSignClassIndexOnDetails: false  # Mostrar/Ocultar foreSignClassIndex en la tabla de contenido
foreSignClassIndexOnSubtitle: true  # Mostrar/Ocultar foreSignClassIndex en los subitulos
markdownLanguage: en                # Lenguaje de la documentación de salida
methodAnnotations: true             # Mostrar/Ocultar anotaciones en métodos
```

### 🌐 Internacionalización (i18n)
- Soporte multilenguaje mediante **YAML externo** y `ResourceBundle`.
- Traducción automática de mensajes del CLI según idioma del sistema.
- Soporte actual: **Español 🇪🇸** e **Inglés 🇬🇧**.

### 💻 CLI interactivo
CLI moderno con soporte para modos **interactivo y parametrizado**.

| Comando | Descripción |
|----------|--------------|
| `javadocmd init` | Crea o inicializa el archivo `config.yml`. |
| `javadocmd show` | Muestra la configuración activa. |
| `javadocmd get` | Obtiene valores específicos de configuración. |
| `javadocmd set` | Modifica parámetros individuales en `config.yml`. |
| `javadocmd validate` | Verifica y corrige configuraciones inválidas. |
| `javadocmd alias` | Crea un alias permanente para usar desde la terminal |

Ejemplo rápido:
```bash
javadocmd init --sourcePath ./src --outputPath ./docs
javadocmd validate
javadocmd
```

---
## Flujo de Uso Típico

- Inicializar/crear config.yml en el proyecto: `javadocmd init --sourcePath ./src --outputPath ./docs`
- Validar la configuración: `javadocmd validate`
- Mostrar la configuración activa: `javadocmd show`
- Obtener un parámetro específico: `javadocmd get sourcePath`
- Modificar un parámetro: `javadocmd set outputPath ./docs/generated`
- Gestionar alias de comandos (ver ayuda para detalles): `javadocmd alias --help`

Consejo: añade `--help` a cualquier comando para ver sus opciones concretas: `javadocmd init --help`

## Flujo de Uso Típico (ejemplo compacto)

Inicializar: `javadocmd init --sourcePath ./src --outputPath ./docs`
Validar: `javadocmd validate`
Ejecutar `javadocmd` para generar documentación. Revisa el comando de exportación disponible en tu versión o usa el flujo definido en tu proyecto.

---

## 📖 Ejemplo de uso

Dado el archivo `MathUtils.java`:

```java
/**
 * Utilidades matemáticas.
 */
public class MathUtils {
    /**
     * Suma dos enteros.
     *
     * @param a primer número
     * @param b segundo número
     * @return la suma de ambos números
     */
    public int sumar(int a, int b) { return a + b; }
}
```

Salida generada:

### 📦 Paquete `com.ejemplo.utils`

#### 🧮 Clase `MathUtils`
> Utilidades matemáticas.

**Métodos**
- `int sumar(int a, int b)`  
  > Suma dos enteros.  
  **Parámetros:**  
  - `a`: primer número  
  - `b`: segundo número  
  **Retorna:** la suma de ambos números

---

## 🧭 Roadmap

| Estado | Funcionalidad |
|:-------:|----------------|
| ✅ | Generación por paquete o combinada |
| ✅ | CLI completo (`init`, `validate`, `get`, `set`, `show`, `alias) |
| ✅ | Configuración YAML avanzada |
| ✅ | Internacionalización (es/en) |
| ✅ | Enlaces automáticos a API de Java y clases internas |
| ✅ | Secciones colapsables `<details>` |  
| 🟡 | Soporte de anotaciones en clases/métodos |
| 🟡 | Comentarios @see, @link y @inheritDoc |
| 🟡 | Soporte para enlaces internos |
| 🟡 | Exportación HTML/JSON/XML |
| 🟡 | Extensiones IDE (Eclipse / IntelliJ / NetBeans) |
| 🟡 | Temas visuales personalizables |
| 🟡 | Integración con GitHub Actions para documentación automática |

---

## 🧑‍💻 Contribuir

Las contribuciones son bienvenidas.  
Clona el repositorio, crea una rama de mejora y envía un pull request con descripción clara siguiendo las convenciones de commit semántico (`feat:`, `fix:`, `refactor:`, etc.).

```bash
git clone https://github.com/philbone/javadoc-md.git
cd javadoc-md
mvn clean package
```

---

## 🐾 Créditos

Desarrollado por **Felipe M. (philbone)**
Inspirado por la necesidad de documentar proyectos Java de forma moderna, legible y automatizada.

![](cat.webp)
