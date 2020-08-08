/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

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
 * @author sangeeky
 */
@Entity
@Table(name = "periodos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Periodos.findAll", query = "SELECT p FROM Periodos p"),
    @NamedQuery(name = "Periodos.findByIdPeriodo", query = "SELECT p FROM Periodos p WHERE p.idPeriodo = :idPeriodo"),
    @NamedQuery(name = "Periodos.findByPeriodo", query = "SELECT p FROM Periodos p WHERE p.periodo = :periodo"),
    @NamedQuery(name = "Periodos.findByActual", query = "SELECT p FROM Periodos p WHERE p.actual = :actual")})
public class Periodos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id_periodo")
    private String idPeriodo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "periodo")
    private int periodo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "actual")
    private boolean actual;
    @JoinColumn(name = "id_anio", referencedColumnName = "id_anio")
    @ManyToOne(optional = false)
    private Anios idAnio;

    public Periodos() {
    }

    public Periodos(String idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Periodos(String idPeriodo, int periodo, boolean actual) {
        this.idPeriodo = idPeriodo;
        this.periodo = periodo;
        this.actual = actual;
    }

    public String getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(String idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public boolean getActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    public Anios getIdAnio() {
        return idAnio;
    }

    public void setIdAnio(Anios idAnio) {
        this.idAnio = idAnio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeriodo != null ? idPeriodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodos)) {
            return false;
        }
        Periodos other = (Periodos) object;
        if ((this.idPeriodo == null && other.idPeriodo != null) || (this.idPeriodo != null && !this.idPeriodo.equals(other.idPeriodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.Periodos[ idPeriodo=" + idPeriodo + " ]";
    }
    
}
