/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColegioRest;

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
 * @author sange
 */
@Entity
@Table(name = "materias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materias.findAll", query = "SELECT m FROM Materias m"),
    @NamedQuery(name = "Materias.findByIdMateria", query = "SELECT m FROM Materias m WHERE m.idMateria = :idMateria"),
    @NamedQuery(name = "Materias.findByMateria", query = "SELECT m FROM Materias m WHERE m.materia = :materia")})
public class Materias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id_materia")
    private String idMateria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "materia")
    private String materia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMateria")
    private Collection<EstudianteMaterias> estudianteMateriasCollection;

    public Materias() {
    }

    public Materias(String idMateria) {
        this.idMateria = idMateria;
    }

    public Materias(String idMateria, String materia) {
        this.idMateria = idMateria;
        this.materia = materia;
    }

    public String getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(String idMateria) {
        this.idMateria = idMateria;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    @XmlTransient
    public Collection<EstudianteMaterias> getEstudianteMateriasCollection() {
        return estudianteMateriasCollection;
    }

    public void setEstudianteMateriasCollection(Collection<EstudianteMaterias> estudianteMateriasCollection) {
        this.estudianteMateriasCollection = estudianteMateriasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMateria != null ? idMateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materias)) {
            return false;
        }
        Materias other = (Materias) object;
        if ((this.idMateria == null && other.idMateria != null) || (this.idMateria != null && !this.idMateria.equals(other.idMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ColegioRest.Materias[ idMateria=" + idMateria + " ]";
    }
    
}
