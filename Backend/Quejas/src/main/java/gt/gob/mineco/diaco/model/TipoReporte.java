/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mps
 */
@Entity
@Table(name = "diaco_reporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoReporte.findAll", query = "SELECT d FROM TipoReporte d")
    , @NamedQuery(name = "TipoReporte.findByIdReporte", query = "SELECT d FROM TipoReporte d WHERE d.idReporte = :idReporte")
    , @NamedQuery(name = "TipoReporte.findByDescripcionReporte", query = "SELECT d FROM TipoReporte d WHERE d.descripcionReporte = :descripcionReporte")
    , @NamedQuery(name = "TipoReporte.findByFechaAdicion", query = "SELECT d FROM TipoReporte d WHERE d.fechaAdicion = :fechaAdicion")
    , @NamedQuery(name = "TipoReporte.findByUsuarioAdicion", query = "SELECT d FROM TipoReporte d WHERE d.usuarioAdicion = :usuarioAdicion")})
public class TipoReporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_reporte")
    private Integer idReporte;
    @Size(max = 200)
    @Column(name = "descripcion_reporte")
    private String descripcionReporte;
    @Column(name = "fecha_adicion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAdicion;
    @Size(max = 20)
    @Column(name = "usuario_adicion")
    private String usuarioAdicion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diacoReporte")
    private List<TipoPerfilReporte> diacoPerfilReporteList;

    public TipoReporte() {
    }

    public TipoReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public String getDescripcionReporte() {
        return descripcionReporte;
    }

    public void setDescripcionReporte(String descripcionReporte) {
        this.descripcionReporte = descripcionReporte;
    }

    public Date getFechaAdicion() {
        return fechaAdicion;
    }

    public void setFechaAdicion(Date fechaAdicion) {
        this.fechaAdicion = fechaAdicion;
    }

    public String getUsuarioAdicion() {
        return usuarioAdicion;
    }

    public void setUsuarioAdicion(String usuarioAdicion) {
        this.usuarioAdicion = usuarioAdicion;
    }

    @XmlTransient
    public List<TipoPerfilReporte> getTipoPerfilReporteList() {
        return diacoPerfilReporteList;
    }

    public void setTipoPerfilReporteList(List<TipoPerfilReporte> diacoPerfilReporteList) {
        this.diacoPerfilReporteList = diacoPerfilReporteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReporte != null ? idReporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoReporte)) {
            return false;
        }
        TipoReporte other = (TipoReporte) object;
        if ((this.idReporte == null && other.idReporte != null) || (this.idReporte != null && !this.idReporte.equals(other.idReporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.TipoReporte[ idReporte=" + idReporte + " ]";
    }
    
}
