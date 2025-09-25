package io.github.philbone.javadocmd.exporter;

import io.github.philbone.javadocmd.model.DocPackage;

public interface DocExporter
{
    String export(DocPackage docPackage);
}
