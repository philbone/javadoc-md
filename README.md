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
sourcePath: ./src
outputPath: ./docs
combinePackages: false       # Un único .md o uno por paquete
tableOfContent: true          # Mostrar índice
includePublic: true
includeProtected: false
includePrivate: false
printEmptyNotify: true
printClassIndex: true
locale: es                    # Idioma de salida (auto, es, en)
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
| ✅ | CLI completo (`init`, `validate`, `get`, `set`, `show`) |
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
