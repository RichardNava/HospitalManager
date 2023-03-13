package net.core.models;

public enum Unidad {
    CON_RESPIRADOR("U. Críticas CON respirador"), SIN_RESPIRADOR("U. Críticas SIN respirador"), CONVENCIONAL("Hospitalización convencional");
    private String parsetext;

    private Unidad(String parsetext) {
        this.parsetext = parsetext;
    }

    public String getParsetext() {
        return parsetext;
    }

}
