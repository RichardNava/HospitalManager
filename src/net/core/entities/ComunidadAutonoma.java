package net.core.entities;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "comunidad_autonoma")
@NamedQueries({
    @NamedQuery(name = "ComunidadAutonoma.findAll", query = "SELECT c FROM ComunidadAutonoma c"),
    @NamedQuery(name = "ComunidadAutonoma.findById", query = "SELECT c FROM ComunidadAutonoma c WHERE c.id = :id"),
    @NamedQuery(name = "ComunidadAutonoma.findByNombre", query = "SELECT c FROM ComunidadAutonoma c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "ComunidadAutonoma.findByPoblacion", query = "SELECT c FROM ComunidadAutonoma c WHERE c.poblacion = :poblacion")})
public class ComunidadAutonoma implements Serializable, Comparable<ComunidadAutonoma> {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "poblacion")
    private int poblacion;
    @OneToMany(mappedBy = "idCa")
    private List<Provincia> provinciaList;

    public ComunidadAutonoma() {
    }

    public ComunidadAutonoma(Integer id) {
        this.id = id;
    }

    public ComunidadAutonoma(Integer id, String nombre, int poblacion, List<Provincia> provinciaList) {
        this.id = id;
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.provinciaList = provinciaList;
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
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "ComunidadAutonoma-> " + "ID= " + id + ", Nombre= " + nombre + ", Poblacion= " + poblacion 
                + "\n\tProvincias:" + provinciaList.stream().map(p -> "\n\t\t"+p.getId()+" "+p.getNombre()).collect(Collectors.joining());
    }

    @Override
    public int compareTo(ComunidadAutonoma o) {
        return this.id-o.id;
    }


}
