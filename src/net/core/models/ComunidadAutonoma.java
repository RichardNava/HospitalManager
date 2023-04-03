package net.core.models;

import java.util.List;
import java.util.Objects;

public class ComunidadAutonoma {

    private int ID;
    private String name;
    private List<Provincia> provincias;
    private int poblacion;

    public ComunidadAutonoma(int ID, String name, List<Provincia> provincias, int poblacion) {
        this.ID = ID;
        this.name = name;
        this.provincias = provincias;
        this.poblacion = poblacion;
    }

    public ComunidadAutonoma(int ID, String name, int poblacion) {
        this.ID = ID;
        this.name = name;
        this.poblacion = poblacion;
    }

    public ComunidadAutonoma(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public int getPoblacion() {
        return poblacion;
    }

    @Override
    public String toString() {
        return "ComunidadAutonoma{" + "ID=" + ID + ", name=" + name + ", provincias=" + provincias + ", poblacion=" + poblacion + '}';
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
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

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

    public String toCSV() {
        return ID + "," + name + "," + poblacion + "\n";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.ID;
        hash = 89 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ComunidadAutonoma other = (ComunidadAutonoma) obj;
        if (this.ID != other.ID) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

}
