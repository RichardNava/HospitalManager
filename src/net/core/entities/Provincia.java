/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.core.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author richa
 */
@Entity
@Table(name = "provincia")
@NamedQueries({
    @NamedQuery(name = "Provincia.findAll", query = "SELECT p FROM Provincia p"),
    @NamedQuery(name = "Provincia.findById", query = "SELECT p FROM Provincia p WHERE p.id = :id"),
    @NamedQuery(name = "Provincia.findByNombre", query = "SELECT p FROM Provincia p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Provincia.findByPoblacion", query = "SELECT p FROM Provincia p WHERE p.poblacion = :poblacion")})
public class Provincia implements Serializable, Comparable<Provincia> {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "poblacion")
    private int poblacion;
    @OneToMany(mappedBy = "idProvincia")
    private List<Hospital> hospitalesList;
    @JoinColumn(name = "id_ca", referencedColumnName = "ID")
    @ManyToOne
    private ComunidadAutonoma idCa;

    public Provincia() {
    }

    public Provincia(Integer id) {
        this.id = id;
    }

    public Provincia(Integer id, String nombre, int poblacion, List<Hospital> hospitalesList, int idCa) {
        this.id = id;
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.hospitalesList = hospitalesList;
        this.idCa = new ComunidadAutonoma(idCa);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public List<Hospital> getHospitalesList() {
        return hospitalesList;
    }

    public void setHospitalesList(List<Hospital> hospitalesList) {
        this.hospitalesList = hospitalesList;
    }

    public ComunidadAutonoma getIdCa() {
        return idCa;
    }

    public void setIdCa(ComunidadAutonoma idCa) {
        this.idCa = idCa;
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
        if (!(object instanceof Provincia)) {
            return false;
        }
        Provincia other = (Provincia) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Provincia -> " + "ID= " + id + ", Nombre= " + nombre + ", Poblacion= " + poblacion;
    }



    @Override
    public int compareTo(Provincia o) {
        return this.id - o.id;

    }

}
