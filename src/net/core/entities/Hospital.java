package net.core.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import net.core.models.Unidad;

@Entity
@Table(name = "hospitales")
@NamedQueries({
    @NamedQuery(name = "Hospitales.findAll", query = "SELECT h FROM Hospitales h"),
    @NamedQuery(name = "Hospitales.findById", query = "SELECT h FROM Hospitales h WHERE h.id = :id"),
    @NamedQuery(name = "Hospitales.findByFecha", query = "SELECT h FROM Hospitales h WHERE h.fecha = :fecha"),
    @NamedQuery(name = "Hospitales.findByUnidad", query = "SELECT h FROM Hospitales h WHERE h.unidad = :unidad"),
    @NamedQuery(name = "Hospitales.findByTotalcamas", query = "SELECT h FROM Hospitales h WHERE h.totalcamas = :totalcamas"),
    @NamedQuery(name = "Hospitales.findByOcupadascovid19", query = "SELECT h FROM Hospitales h WHERE h.ocupadascovid19 = :ocupadascovid19"),
    @NamedQuery(name = "Hospitales.findByOcupadasnocovid19", query = "SELECT h FROM Hospitales h WHERE h.ocupadasnocovid19 = :ocupadasnocovid19"),
    @NamedQuery(name = "Hospitales.findByIngresoscovid19", query = "SELECT h FROM Hospitales h WHERE h.ingresoscovid19 = :ingresoscovid19"),
    @NamedQuery(name = "Hospitales.findByAltas24hcovid19", query = "SELECT h FROM Hospitales h WHERE h.altas24hcovid19 = :altas24hcovid19")})
public class Hospital implements Serializable, Comparable<Hospital> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "unidad")
    private Unidad unidad;
    @Column(name = "totalcamas")
    private double totalcamas;
    @Column(name = "ocupadascovid19")
    private double ocupadascovid19;
    @Column(name = "ocupadasnocovid19")
    private double ocupadasnocovid19;
    @Column(name = "ingresoscovid19")
    private double ingresoscovid19;
    @Column(name = "altas24hcovid19")
    private double altas24hcovid19;
    @JoinColumn(name = "id_provincia", referencedColumnName = "id")
    @ManyToOne
    private Provincia idProvincia;

    public Hospital() {
    }

    public Hospital(Integer id) {
        this.id = id;
    }

    public Hospital(Integer id, String fecha, String unidad, double totalcamas, double ocupadascovid19, double ocupadasnocovid19, double ingresoscovid19, double altas24hcovid19, int idProvincia) {
        this.id = id;
        this.fecha = LocalDate.parse(fecha,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        setUnidad(unidad);
        this.totalcamas = totalcamas;
        this.ocupadascovid19 = ocupadascovid19;
        this.ocupadasnocovid19 = ocupadasnocovid19;
        this.ingresoscovid19 = ingresoscovid19;
        this.altas24hcovid19 = altas24hcovid19;
        this.idProvincia = new Provincia(idProvincia);
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha.toString();
    }

    public void setFecha(String fecha) {
        this.fecha = LocalDate.parse(fecha,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getUnidad() {
        return unidad.getParsetext();
    }

    public final void setUnidad(String unidad) {

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

    public double getTotalcamas() {
        return totalcamas;
    }

    public void setTotalcamas(double totalcamas) {
        this.totalcamas = totalcamas;
    }

    public double getOcupadascovid19() {
        return ocupadascovid19;
    }

    public void setOcupadascovid19(double ocupadascovid19) {
        this.ocupadascovid19 = ocupadascovid19;
    }

    public double getOcupadasnocovid19() {
        return ocupadasnocovid19;
    }

    public void setOcupadasnocovid19(double ocupadasnocovid19) {
        this.ocupadasnocovid19 = ocupadasnocovid19;
    }

    public double getIngresoscovid19() {
        return ingresoscovid19;
    }

    public void setIngresoscovid19(double ingresoscovid19) {
        this.ingresoscovid19 = ingresoscovid19;
    }

    public double getAltas24hcovid19() {
        return altas24hcovid19;
    }

    public void setAltas24hcovid19(double altas24hcovid19) {
        this.altas24hcovid19 = altas24hcovid19;
    }

    public Provincia getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Provincia idProvincia) {
        this.idProvincia = idProvincia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hospital)) {
            return false;
        }
        Hospital other = (Hospital) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public int compareTo(Hospital t) {
        return this.id-t.id;
    }

    @Override
    public String toString() {
        return """
               Hospital:
               \tID=   """ + id + "\n\tFecha= " + fecha + "\n\tUnidad= " + unidad + 
                "\n\tTotal Camas= " + totalcamas + "\n\tOcupadas Covid19= " + ocupadascovid19 + 
                "\n\tOcupadas No Covid19= " + ocupadasnocovid19 + "\n\tIngresos Covid19= " + ingresoscovid19 + 
                "\n\tAltas 24h Covid19= " + altas24hcovid19;
    }



}
