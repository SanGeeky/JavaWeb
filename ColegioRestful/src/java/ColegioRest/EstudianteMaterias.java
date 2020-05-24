/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColegioRest;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sange
 */
@Entity
@Table(name = "estudiante_materias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstudianteMaterias.findAll", query = "SELECT e FROM EstudianteMaterias e"),
    @NamedQuery(name = "EstudianteMaterias.findByIdEstudianteMateria", query = "SELECT e FROM EstudianteMaterias e WHERE e.idEstudianteMateria = :idEstudianteMateria")})
public class EstudianteMaterias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id_estudiante_materia")
    private String idEstudianteMateria;
    @JoinColumn(name = "id_anio", referencedColumnName = "id_anio")
    @ManyToOne(optional = false)
    private Anios idAnio;
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id_estudiante")
    @ManyToOne(optional = false)
    private Estudiantes idEstudiante;
    @JoinColumn(name = "id_materia", referencedColumnName = "id_materia")
    @ManyToOne(optional = false)
    private Materias idMateria;

    public EstudianteMaterias() {
    }

    public EstudianteMaterias(String idEstudianteMateria) {
        this.idEstudianteMateria = idEstudianteMateria;
    }

    public String getIdEstudianteMateria() {
        return idEstudianteMateria;
    }

    public void setIdEstudianteMateria(String idEstudianteMateria) {
        this.idEstudianteMateria = idEstudianteMateria;
    }

    public Anios getIdAnio() {
        return idAnio;
    }

    public void setIdAnio(Anios idAnio) {
        this.idAnio = idAnio;
    }

    public Estudiantes getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Estudiantes idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Materias getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materias idMateria) {
        this.idMateria = idMateria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstudianteMateria != null ? idEstudianteMateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudianteMaterias)) {
            return false;
        }
        EstudianteMaterias other = (EstudianteMaterias) object;
        if ((this.idEstudianteMateria == null && other.idEstudianteMateria != null) || (this.idEstudianteMateria != null && !this.idEstudianteMateria.equals(other.idEstudianteMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ColegioRest.EstudianteMaterias[ idEstudianteMateria=" + idEstudianteMateria + " ]";
    }
    
}
