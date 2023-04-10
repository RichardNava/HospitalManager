package net.core.models;

import java.util.Objects;

public class ProvinciaCSV {
    private final int ID, IDCCAA; // ID - PK IDCCAA - FK
    private final String name;
    private int poblacion;
    
    public ProvinciaCSV(int ID, int IDCCAA, String name) {
        this.ID = ID;
        this.IDCCAA = IDCCAA;
        this.name = name;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public int getID() {
        return ID;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public int getIDCCAA() {
        return IDCCAA;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.ID;
        hash = 79 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if(!(obj instanceof ProvinciaCSV)){
            return false;
        }
        final ProvinciaCSV other = (ProvinciaCSV) obj;
        if (this.ID != other.ID) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return """
               Provincia
               \tID=""" + ID + ", IDCCAA=" + IDCCAA + ", name=" + name;
    }
    
    public String toCSV(){
        return ID+","+IDCCAA+","+name+","+poblacion+"\n";
    } 
    
}
