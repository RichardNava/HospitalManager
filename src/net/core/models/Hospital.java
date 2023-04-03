/* esto si se puede */
package net.core.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// no se hereda de esta clase es final
public final class Hospital implements Comparable<Hospital> {

    private LocalDate fecha;
    private Unidad unidad;
    private String provincia; // int idProvincias 
    private int camas,
            ocupadascovid19,
            ocupadasnocovid19,
            ingresoscovid19,
            altas24hcovid19;

    public Hospital() {
    }

    @Override
    public String toString() {
        return "Hospital{" + "fecha=" + fecha + ", unidad=" + unidad + ", provincia=" + provincia + '}';
    }

    public Hospital(LocalDate fecha, Unidad unidad, String provincia, int camas) {
        this.fecha = fecha;
        this.unidad = unidad;
        this.provincia = provincia;
        this.camas = camas;
    }

    public Hospital(String fecha, String unidad, String provincia, int camas) {
        setFecha(fecha);
        setUnidad(unidad);
        this.provincia = provincia;
        this.camas = camas;
    }

    public int getCamas() {
        return camas;
    }

    public LocalDate getFecha() {

        return fecha;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public void setUnidad(String unidad) {

        switch (unidad) {
            case "U. Críticas CON respirador":
                this.unidad = Unidad.CON_RESPIRADOR;
                break;
            case "U. Críticas SIN respirador":
                this.unidad = Unidad.SIN_RESPIRADOR;
                break;
            case "Hospitalización convencional":
                this.unidad = Unidad.CONVENCIONAL;
                break;

            default:
                this.unidad = Unidad.CONVENCIONAL;
        }
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public int compareTo(Hospital t) {
        if (this.unidad.ordinal() - t.unidad.ordinal() != 0) {

            return this.unidad.ordinal() - t.unidad.ordinal();

        }
        return this.camas - t.camas;
    }

}// clase
