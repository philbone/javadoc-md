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

# javadoc-md
Un exportador de Javadoc a Markdown que genera automáticamente documentación legible directamente en los repositorios de GitHub.

Con javadoc-md, cada paquete de tu proyecto Java obtiene un archivo .md que contiene la descripción de sus clases, interfaces y métodos, listos para navegar desde la interfaz de GitHub.

## ✨ Características

  * 📦 Genera un documento .md por cada paquete de tu proyecto Java.
  * 📚 Exporta Javadoc de:
      * Clases e interfaces
      * Métodos y constructores
      * Parámetros (@param), retornos (@return) y excepciones (@throws)
  * 📝 Formato Markdown limpio, legible y amigable en GitHub.
  * ⚡ Fácil de integrar.

🚀 Instalación

1. Descarga el último JAR desde Releases
1. Ejecuta con: `java -jar javadoc-md-1.0.0.jar /ruta/al/proyecto`

## 📖 Ejemplo de uso
Dado el archivo MathUtils.java:

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

`javadoc-md` generará en el paquete correspondiente:

### Documentación del paquete `com.ejemplo.utils`

#### Clase: `MathUtils`
Utilidades matemáticas.

#### Métodos
- `int sumar(int a, int b)`  
  Suma dos enteros.  
  **Parámetros:**  
  - `a`: primer número  
  - `b`: segundo número  
  **return:** la suma de ambos números  

## 🧭 Roadmap
  * [x] Generar .md automáticamente por paquete.
  * [x] Soporte para documentación de constructores.
  * [x] Exportar también atributos con sus Javadocs.
  * [ ] Opción CLI para elegir entre salida consolidada (.md único) o por clase.
  * [ ] Opción CLI para cambiar el nombre del md.
  * [ ] Opción CLI para incluir un md el directorio raíz.
  * [ ] Posible extensión para BlueJ/NetBeans/Eclipse.

<img width="100%" src="https://media3.giphy.com/media/v1.Y2lkPTc5MGI3NjExaHNnOHpuN3VvcjJzcHRmNGVwZzVwcW02ZTQ5MTk2a2Y5YXczNTExdCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/wwg1suUiTbCY8H8vIA/giphy.gif">
