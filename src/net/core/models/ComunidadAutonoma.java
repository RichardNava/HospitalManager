package net.core.models;

import java.util.List;

public class ComunidadAutonoma {
    private int ID;
    private String name;
    private List<String> provincias;

    public ComunidadAutonoma(int ID, String name, List<String> provincias) {
        this.ID = ID;
        this.name = name;
        this.provincias = provincias;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<String> provincias) {
        this.provincias = provincias;
    }
    
    
}
