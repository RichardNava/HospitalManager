/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.core.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author richa
 */
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
public class Hospitales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "fecha")
    private String fecha;
    @Column(name = "unidad")
    private String unidad;
    @Column(name = "totalcamas")
    private String totalcamas;
    @Column(name = "ocupadascovid19")
    private String ocupadascovid19;
    @Column(name = "ocupadasnocovid19")
    private String ocupadasnocovid19;
    @Column(name = "ingresoscovid19")
    private String ingresoscovid19;
    @Column(name = "altas24hcovid19")
    private String altas24hcovid19;
    @JoinColumn(name = "id_provincia", referencedColumnName = "id")
    @ManyToOne
    private Provincia idProvincia;

    public Hospitales() {
    }

    public Hospitales(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getTotalcamas() {
        return totalcamas;
    }

    public void setTotalcamas(String totalcamas) {
        this.totalcamas = totalcamas;
    }

    public String getOcupadascovid19() {
        return ocupadascovid19;
    }

    public void setOcupadascovid19(String ocupadascovid19) {
        this.ocupadascovid19 = ocupadascovid19;
    }

    public String getOcupadasnocovid19() {
        return ocupadasnocovid19;
    }

    public void setOcupadasnocovid19(String ocupadasnocovid19) {
        this.ocupadasnocovid19 = ocupadasnocovid19;
    }

    public String getIngresoscovid19() {
        return ingresoscovid19;
    }

    public void setIngresoscovid19(String ingresoscovid19) {
        this.ingresoscovid19 = ingresoscovid19;
    }

    public String getAltas24hcovid19() {
        return altas24hcovid19;
    }

    public void setAltas24hcovid19(String altas24hcovid19) {
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
        if (!(object instanceof Hospitales)) {
            return false;
        }
        Hospitales other = (Hospitales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.core.prueba.Hospitales[ id=" + id + " ]";
    }
    
}
