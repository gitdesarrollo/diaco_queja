/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mps
 */
@Entity
@Table(name = "diaco_queja_correlativo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiacoQuejaCorrelativo.findAll", query = "SELECT d FROM DiacoQuejaCorrelativo d")
    , @NamedQuery(name = "DiacoQuejaCorrelativo.findByAnio", query = "SELECT d.correlativo FROM DiacoQuejaCorrelativo d WHERE d.anio = :anio")
    , @NamedQuery(name = "DiacoQuejaCorrelativo.findByCorrelativo", query = "SELECT d FROM DiacoQuejaCorrelativo d WHERE d.correlativo = :correlativo")})
public class DiacoQuejaCorrelativo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "anio")
    private String anio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "correlativo")
    private String correlativo;

    public DiacoQuejaCorrelativo() {
    }

    public DiacoQuejaCorrelativo(String anio) {
        this.anio = anio;
    }

    public DiacoQuejaCorrelativo(String anio, String correlativo) {
        this.anio = anio;
        this.correlativo = correlativo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (anio != null ? anio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiacoQuejaCorrelativo)) {
            return false;
        }
        DiacoQuejaCorrelativo other = (DiacoQuejaCorrelativo) object;
        if ((this.anio == null && other.anio != null) || (this.anio != null && !this.anio.equals(other.anio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.DiacoQuejaCorrelativo[ anio=" + anio + " ]";
    }
    
}
