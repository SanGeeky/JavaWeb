/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sangeeky
 */
@Entity
@Table(name = "anios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anios.findAll", query = "SELECT a FROM Anios a"),
    @NamedQuery(name = "Anios.findByIdAnio", query = "SELECT a FROM Anios a WHERE a.idAnio = :idAnio"),
    @NamedQuery(name = "Anios.findByAnio", query = "SELECT a FROM Anios a WHERE a.anio = :anio"),
    @NamedQuery(name = "Anios.findByDescripcion", query = "SELECT a FROM Anios a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "Anios.findByActual", query = "SELECT a FROM Anios a WHERE a.actual = :actual")})
public class Anios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id_anio")
    private String idAnio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anio")
    private int anio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "actual")
    private boolean actual;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAnio")
    private Collection<EstudianteMaterias> estudianteMateriasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAnio")
    private Collection<Periodos> periodosCollection;

    public Anios() {
    }

    public Anios(String idAnio) {
        this.idAnio = idAnio;
    }

    public Anios(String idAnio, int anio, String descripcion, boolean actual) {
        this.idAnio = idAnio;
        this.anio = anio;
        this.descripcion = descripcion;
        this.actual = actual;
    }

    public String getIdAnio() {
        return idAnio;
    }

    public void setIdAnio(String idAnio) {
        this.idAnio = idAnio;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    @XmlTransient
    public Collection<EstudianteMaterias> getEstudianteMateriasCollection() {
        return estudianteMateriasCollection;
    }

    public void setEstudianteMateriasCollection(Collection<EstudianteMaterias> estudianteMateriasCollection) {
        this.estudianteMateriasCollection = estudianteMateriasCollection;
    }

    @XmlTransient
    public Collection<Periodos> getPeriodosCollection() {
        return periodosCollection;
    }

    public void setPeriodosCollection(Collection<Periodos> periodosCollection) {
        this.periodosCollection = periodosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnio != null ? idAnio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anios)) {
            return false;
        }
        Anios other = (Anios) object;
        if ((this.idAnio == null && other.idAnio != null) || (this.idAnio != null && !this.idAnio.equals(other.idAnio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.Anios[ idAnio=" + idAnio + " ]";
    }
    
}
