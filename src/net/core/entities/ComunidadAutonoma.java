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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author richa
 */
@Entity
@Table(name = "comunidad_autonoma")
@NamedQueries({
    @NamedQuery(name = "ComunidadAutonoma.findAll", query = "SELECT c FROM ComunidadAutonoma c"),
    @NamedQuery(name = "ComunidadAutonoma.findById", query = "SELECT c FROM ComunidadAutonoma c WHERE c.id = :id"),
    @NamedQuery(name = "ComunidadAutonoma.findByNombre", query = "SELECT c FROM ComunidadAutonoma c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "ComunidadAutonoma.findByPoblacion", query = "SELECT c FROM ComunidadAutonoma c WHERE c.poblacion = :poblacion")})
public class ComunidadAutonoma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "poblacion")
    private String poblacion;
    @OneToMany(mappedBy = "idCa")
    private List<Provincia> provinciaList;

    public ComunidadAutonoma() {
    }

    public ComunidadAutonoma(Integer id) {
        this.id = id;
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

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public List<Provincia> getProvinciaList() {
        return provinciaList;
    }

    public void setProvinciaList(List<Provincia> provinciaList) {
        this.provinciaList = provinciaList;
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
        if (!(object instanceof ComunidadAutonoma)) {
            return false;
        }
        ComunidadAutonoma other = (ComunidadAutonoma) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.core.prueba.ComunidadAutonoma[ id=" + id + " ]";
    }
    
}
