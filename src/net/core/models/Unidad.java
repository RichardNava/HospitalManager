package net.core.models;

public enum Unidad {
    CON_RESPIRADOR("U. Cr�ticas CON respirador"), SIN_RESPIRADOR("U. Cr�ticas SIN respirador"), CONVENCIONAL("Hospitalizaci�n convencional");
    private String parsetext;

    private Unidad(String parsetext) {
        this.parsetext = parsetext;
    }

    public String getParsetext() {
        return parsetext;
    }

}
