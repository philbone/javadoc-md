package io.github.philbone.javadocmd.exporter;

import io.github.philbone.javadocmd.config.Config;
import io.github.philbone.javadocmd.extractor.JavadocUtils;
import io.github.philbone.javadocmd.model.*;
import java.util.stream.Collectors;

/**
 * Exportador que genera documentación en formato Markdown
 * a partir del modelo intermedio construido con
 * {@link io.github.philbone.javadocmd.extractor.JavadocExtractorVisitor}.
 *
 * <p>Renderiza:</p>
 * <ul>
 *     <li>Firma de la clase (visibilidad, static, tipo, nombre).</li>
 *     <li>Extensiones ({@code extends}) e implementaciones ({@code implements}).</li>
 *     <li>Descripción general de la clase.</li>
 *     <li>Campos, constructores y métodos con sus firmas y documentación Javadoc.</li>
 * </ul>
 *
 * @project JavadocMd
 */
public class MarkdownExporter implements DocExporter
{
    /**
     * Número mínimo de clases dentro de un paquete para activar el modo colapsable.
     * Si el paquete tiene más de este número, cada clase se renderiza dentro de un bloque `<details>`.
     */
    private static final int COLLAPSE_THRESHOLD = 4;
    private static final String VISIBILITY_PUBLIC = "public";
    private static final String VISIBILITY_PRIVATE = "private";
    private static final String VISIBILITY_PROTECTED = "protected";
    
    private final Config config;
    
    private int totalMethodsCount = 0;
    private int totalFieldsCount = 0;

    private final JavaApiLinker apiLinker = new JavaApiLinker();
    private final InternalLinker internalLinker;
    
    private DocPackage docPackage;

    
    public MarkdownExporter(Config config, InternalLinker internalLinker) {
        this.config = config;        
        this.internalLinker = internalLinker;
    }

    @Override
    public String export(DocPackage docPackage) {
        MarkdownBuilder builder = new MarkdownBuilder();
        this.docPackage = docPackage;
        
        builder.tag("<style>ul {list-style-type: none;}</style>");
        
        // Encabezado principal
        if (this.docPackage.getProjectName() != null && !this.docPackage.getProjectName().isEmpty()) {
            builder.title(this.docPackage.getProjectName());
        }

        builder.subtitle(this.docPackage.getName());

        // TOC
        if (config.isTableOfContent()) {
            builder.toc(this.docPackage);
        }

        // Determinar si se deben colapsar las clases
        boolean collapseClasses = this.docPackage.getClasses().size() > COLLAPSE_THRESHOLD;

        // Recorrer clases / interfaces / enums / records
        for (DocClass docClass : this.docPackage.getClasses()) {
            String emoji = formatEmoji(docClass.getKind());
            String header = emoji + " "
                    + capitalize(docClass.getVisibility())
                    + (docClass.isStatic() ? " static " : " ")
                    + formatKind(docClass.getKind())
                    + " " + docClass.getName();
            
            if (collapseClasses) {
                builder.tag("<details>\n");                
                builder.tag("<summary> <strong>" + 
                        // imprime el numero index en el detalle si está activa la opción
                        printIndexNumber(docClass.getIndexOrder(), config.isForeSignClassIndexOnDetails()) + " " + 
                        header.trim() + "</strong> </summary>\n\n");
            }

            builder.subtitle( printIndexNumber(docClass.getIndexOrder(), config.isForeSignClassIndexOnSubtitle()) + " " + header.trim() );

            // ========== Firma en bloque de código ==========            
            StringBuilder signature = new StringBuilder();
            
            // arma una lista de anotaciones correspondiente a la clase
            if(!docClass.getAnnotations().isEmpty()){
                StringBuilder annotations = new StringBuilder();
                for (DocAnnotation da : docClass.getAnnotations()) {
                    annotations.append(da);
                }
                signature.append(annotations);
            }
            
            signature.append(docClass.getVisibility()).append(" ");
            if (docClass.isStatic()) {
                signature.append("static ");
            }
            signature.append(switch (docClass.getKind()) {
                case CLASS ->
                    "class ";
                case ABSTRACT_CLASS ->
                    "abstract class ";
                case INTERFACE ->
                    "interface ";
                case ENUM ->
                    "enum ";
                case RECORD ->
                    "record ";
            });
            signature.append(docClass.getName());

            if (docClass.getSuperClass() != null) {
                signature.append("\nextends ").append(docClass.getSuperClass());
            }

            if (!docClass.getInterfaces().isEmpty()) {
                signature.append("\nimplements ")
                        .append(String.join(", ", docClass.getInterfaces()));
            }

            builder.codeBlock(signature.toString(), "java");
            builder.tag("\n");
            
            // ========== Tags ================= 
            if(docClass.getTags() != null) {
                for(DocTag tg : docClass.getTags()){
                    if( isTagPrintable(tg.getName()) ){
                        builder.listItem("`@" + tg.getName() + "` " + tg.getDescription());
                    }
                }
            }else{
                builder.tag("> ")
                       .listItem("no tags!");
            }
            
            // ========== Descripción ==========
            if (docClass.getDescription() != null && !docClass.getDescription().isEmpty()) {
                String desc = JavadocUtils.normalizeImages(docClass.getDescription());
                builder.blockquote("**Descripción:**\n" + desc);
            }

             // 🧮 Métodos
            if (!docClass.getMethods().isEmpty()) {
                builder.h3("🧮 Métodos");                
                // imprimir métodos en grupo
                builder.tag( printMethods(docClass, VISIBILITY_PUBLIC) );
                builder.tag( printMethods(docClass, VISIBILITY_PROTECTED) );
                builder.tag( printMethods(docClass, VISIBILITY_PRIVATE) );                
                // sino hay métodos imprimir notificación de lista vacía
                if (totalMethodsCount == 0) {
                    builder.tag("> _No hay métodos visibles_\n");
                }
            }

            // 🛠️ Constructores
            if (!docClass.getConstructors().isEmpty()) {
                builder.h3("🛠️ Constructores");
                int count = 0;
                for (DocConstructor constructor : docClass.getConstructors()) {
                    
                    if (isPrintable(constructor.getVisibility())) {
                        String signatureCons = constructor.getVisibility()
                                + (constructor.isStatic() ? " static " : " ")
                                + constructor.getName()
                                + "(" + String.join(", ", constructor.getParameters()) + ")";
                        builder.listItem("`" + signatureCons.trim() + "`");

                        if (constructor.getDescription() != null && !constructor.getDescription().isEmpty()) {
                            String desc = JavadocUtils.normalizeImages(constructor.getDescription());
                            builder.blockquote("**Descripción:**\n" + desc);
                        }

                        for (DocParameter param : constructor.getDocParameters()) {
                            builder.tag("> ");
                            builder.listItem("*@param* `" + param.getName() + "` " + param.getDescription());
                        }

                        for (DocException ex : constructor.getExceptions()) {
                            builder.tag("> ");
                            builder.listItem("*@throws* **" + ex.getName() + "** " + ex.getDescription());
                        }
                        count++;
                    }                    
                }
                if (count == 0) {
                    builder.tag("> _No hay constructores visibles_\n");
                }
            }

            // 📦 Campos
            if (!docClass.getFields().isEmpty()) {
                builder.h3("📦 Campos");
                // imprimir campos en grupo
                builder.tag( printFields(docClass, VISIBILITY_PUBLIC) );
                builder.tag( printFields(docClass, VISIBILITY_PROTECTED) );
                builder.tag( printFields(docClass, VISIBILITY_PRIVATE) );
                //si no hay campos imprimir notificación de lista vacía
                if (totalFieldsCount == 0) {
                    builder.tag("> _No hay campos visibles_\n");
                }
            }
            
            // Fin Clase
            if (collapseClasses) {
                builder.tag("\n</details>\n");
            }
        }
        
        return builder.build();
    }
    
    /**
     * Determina si la visibilidad es imprimible según la configuración.
     * @param visibility la visiblidad a evaluar
     * @return true si la visibilidad es imprimible
     */
    private boolean isPrintable(String visibility){
        boolean r = true;
        if ( visibility.equals("public") && !config.isIncludePublic() ) {
            r = false;
        }
        if ( visibility.equals("private") && !config.isIncludePrivate()) {
            r = false;
        }
        if ( visibility.equals("protected") && !config.isIncludeProtected() ) {
            r = false;
        }
        return r;
    }
    
    /**
     * Determina si el tag de clase es imprimible
     * @param tagName el nombre del tag a evaluar
     * @return true si debe ser imprimido, false si debe ser omitido
     */
    private boolean isTagPrintable(String tagName){
        boolean r = true;        
        if( tagName.equals("author") && !config.isAuthorClassTag() ){
            r = false;
        }
        if( tagName.equals("deprecated") && !config.isDeprecatedClassTag()){
            r = false;
        }
        if( tagName.equals("see") && !config.isSeeClassTag()){
            r = false;
        }
        if( tagName.equals("serial") && !config.isSerialClassTag()){
            r = false;
        }
        if( tagName.equals("since") && !config.isSinceClassTag()){
            r = false;
        }
        if( tagName.equals("version") && !config.isVersionClassTag() ){
            r = false;
        }        
        if( tagName.equals("project") && !config.isProjectClassTag()){
            r = false;
        }
        return r;
    }

    /** Si el tipo tiene enlace conocido, devuelve el link Markdown. Si no, lo envuelve en `code`. */
    private String formatCodeOrLink(String type) {        
        if (type == null || type.isBlank()) return "";

        // 1. Intentar con clases internas del mismo proyecto
        String internalUrl = internalLinker.linkIfInternalType(type);
        if (internalUrl != null) {
            return internalUrl;
        }
        
        // 2. Intentar con clases Java estándar
        @SuppressWarnings("static-access")
        String javaUrl = apiLinker.linkIfJavaType(type);
        if (javaUrl != null) {
            return javaUrl; // ya viene como [String](https://...) etc.
        }
        return "`" + type + "`";
    }

    private String formatKind(Kind kind) {
        return switch (kind) {
            case CLASS -> "Class";
            case ABSTRACT_CLASS -> "Abstract Class";
            case INTERFACE -> "Interface";
            case ENUM -> "Enum";
            case RECORD -> "Record";
        };
    }

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    private String formatEmoji(Kind kind) {
        return switch (kind) {
            case CLASS -> "📘";
            case ABSTRACT_CLASS -> "📕";
            case INTERFACE -> "📗";
            case ENUM -> "📙";
            case RECORD -> "📒";
        };
    }
    
    private String printFields(DocClass docClass, String text) {
        MarkdownBuilder fieldBuilder = new MarkdownBuilder();
        int fieldCount = 0;
        
        for (DocField field : docClass.getFields()) {
            
            if (field.getVisibility().equals(text)) {
                if (isPrintable(field.getVisibility())) {                    
                    String typeLinked = formatCodeOrLink(field.getType());
                    String signatureField = " `" + field.getVisibility()
                            + (field.isStatic() ? " static`" : "`")
                            + " " + typeLinked
                            + " `" + field.getName() + "` ";
                    fieldBuilder.listItem(signatureField.trim());

                    if (field.getDescription() != null && !field.getDescription().isEmpty()) {
                        String desc = JavadocUtils.normalizeImages(field.getDescription());
                        fieldBuilder.blockquote(desc);
                    }
                    fieldCount++;
                    totalFieldsCount++;
                }
            }
        }
        // si no hay campos, pero la configuración de usuario dice imprimir notificación de lista vacía
        if (fieldCount == 0 && config.isPrintEmptyNotify()) {
            fieldBuilder.insertAt(0, "<details open><summary>" + capitalize(text) + "</summary>\n\n")
                    .tag("> _No hay campos " + text + " visibles_\n")
                    .tag("</details>\n\n");
        }
        // si hay campos
        if (fieldCount >= 1) {
            fieldBuilder.insertAt(0, "<details open><summary>" + capitalize(text) + "</summary>\n\n")
                    .tag("</details>\n\n");
        }// en cualquier otro caso ignora la impresión
        
        return fieldBuilder.build();
    }
    
    private String printMethods(DocClass docClass, String text) {
        MarkdownBuilder methodBuilder = new MarkdownBuilder();
        int methodCount = 0;
        
        for (DocMethod method : docClass.getMethods()) {
            
            // arma un listado compacto con las anotaciones
            StringBuilder annotations = new StringBuilder();
            boolean compact = false;// true:la lista se arma com lista compacta, false: la lista se arma en una linea
            if (!method.getAnnotations().isEmpty()) {
                if (compact) {
                    annotations.append(
                            method.getAnnotations().stream()
                                    .map(da -> "<sub>" + da + "</sub>")
                                    .collect(Collectors.joining("<br>"))
                    );
                } else {
                    annotations.append(
                            method.getAnnotations().stream()
                                    .map(da -> "<sub>" + da + "</sub>")
                                    .collect(Collectors.joining("<sub>,</sub> "))
                    );
                }
            }
            
            if (method.getVisibility().equals(text)) {
                if (isPrintable(method.getVisibility())) {
                    String returnType = formatCodeOrLink(method.getReturnType());
                    String signatureMeth = "⭐️ **`" + method.getVisibility() + " "
                            + (method.isStatic() ? " static`" : "`")
                            + (method.isVoid() ? " **"+ returnType +"**" : returnType)
                            + " `" + method.getName()
                            + "(" + String.join(", ", method.getParameters()) + ")`**";
                    
                    //methodBuilder.paragraph(annotations.toString()+ "\n" +signatureMeth.trim());
                    methodBuilder.tag("- " + signatureMeth.trim())
                            .tag("<br>")
                            .tag(annotations.toString()+"\n");

                    if (method.getDescription() != null && !method.getDescription().isEmpty()) {
                        String desc = JavadocUtils.normalizeImages(method.getDescription());
                        methodBuilder.blockquote(desc);
                    }
                    
                    for (DocTag tag : method.getTags()){
                        methodBuilder.tag("> ");
                        methodBuilder.listItem("*@" + tag.getName() + "* " + tag.getDescription());
                    }

                    for (DocParameter param : method.getDocParameters()) {
                        methodBuilder.tag("> ");
                        methodBuilder.listItem("*@param* **" + param.getName() + "** " + param.getDescription());
                    }

                    if (method.getReturnDescription() != null) {
                        methodBuilder.tag("> ");
                        methodBuilder.listItem("*@return* " + method.getReturnDescription());
                    }

                    for (DocException ex : method.getExceptions()) {
                        methodBuilder.tag("> ");
                        methodBuilder.listItem("*@throws* **" + ex.getName() + "** " + ex.getDescription());
                    }
                    methodCount++;
                    totalMethodsCount++;
                }
            }

        }
        // si no hay métodos, pero la configuración de usuario dice imprimir notificación de lista vacía
        if (methodCount == 0 && config.isPrintEmptyNotify()) {
            methodBuilder.insertAt(0, "<details open><summary>" + capitalize(text) + "</summary>\n\n")                    
                    .tag("> _No hay métodos " + text + " visibles_\n")
                    .tag("</details>\n\n");
        }
        // si hay métodos
        if (methodCount >= 1) {
            methodBuilder.insertAt(0, "<details open><summary>" + capitalize(text) + "</summary>\n\n")
                    .tag("</details>\n\n");
        }// en cualquier otro caso ignora la impresión
        
        return methodBuilder.build();
    }

    // no es el flujo óptimo
    private String printIndexNumber(int indexOrder, boolean foreSign) {
        String r = "";// si la conf no require usar index el método retoranrá una cadena de texto vacía
        if (foreSign) {// si evalua la conf requiere usar el foresign 
            if (config.isPrintClassIndex()) {// evalua si la config requiete imprimir index
                // evalua si el existe foresign declarado en la config
                if (!config.getForeSignClassIndex().isBlank() && !config.getForeSignClassIndex().isEmpty()) {
                    r += config.getForeSignClassIndex();// lo agrega al string
                }
                r += String.valueOf(indexOrder);//agrega el index al string
            }
        }
        return r;
    }
    
}
