/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mps
 */
@Entity
@Table(name = "diaco_perfil_reporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPerfilReporte.findAll", query = "SELECT d FROM TipoPerfilReporte d")
    , @NamedQuery(name = "TipoPerfilReporte.findByIdPerfil", query = "SELECT d FROM TipoPerfilReporte d WHERE d.diacoPerfilReportePK.idPerfil = :idPerfil")
    , @NamedQuery(name = "TipoPerfilReporte.findByIdReporte", query = "SELECT d FROM TipoPerfilReporte d WHERE d.diacoPerfilReportePK.idReporte = :idReporte")
    , @NamedQuery(name = "TipoPerfilReporte.findByFechaAdicion", query = "SELECT d FROM TipoPerfilReporte d WHERE d.fechaAdicion = :fechaAdicion")
    , @NamedQuery(name = "TipoPerfilReporte.findByUsuarioAdicion", query = "SELECT d FROM TipoPerfilReporte d WHERE d.usuarioAdicion = :usuarioAdicion")})
public class TipoPerfilReporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TipoPerfilReportePK diacoPerfilReportePK;
    @Column(name = "fecha_adicion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAdicion;
    @Size(max = 20)
    @Column(name = "usuario_adicion")
    private String usuarioAdicion;
    @JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil_puesto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoPerfilesPuesto diacoPerfilesPuesto;
    @JoinColumn(name = "id_reporte", referencedColumnName = "id_reporte", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoReporte diacoReporte;

    public TipoPerfilReporte() {
    }

    public TipoPerfilReporte(TipoPerfilReportePK diacoPerfilReportePK) {
        this.diacoPerfilReportePK = diacoPerfilReportePK;
    }

    public TipoPerfilReporte(int idPerfil, int idReporte) {
        this.diacoPerfilReportePK = new TipoPerfilReportePK(idPerfil, idReporte);
    }

    public TipoPerfilReportePK getTipoPerfilReportePK() {
        return diacoPerfilReportePK;
    }

    public void setTipoPerfilReportePK(TipoPerfilReportePK diacoPerfilReportePK) {
        this.diacoPerfilReportePK = diacoPerfilReportePK;
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

    public TipoPerfilesPuesto getTipoPerfilesPuesto() {
        return diacoPerfilesPuesto;
    }

    public void setTipoPerfilesPuesto(TipoPerfilesPuesto diacoPerfilesPuesto) {
        this.diacoPerfilesPuesto = diacoPerfilesPuesto;
    }

    public TipoReporte getTipoReporte() {
        return diacoReporte;
    }

    public void setTipoReporte(TipoReporte diacoReporte) {
        this.diacoReporte = diacoReporte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diacoPerfilReportePK != null ? diacoPerfilReportePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPerfilReporte)) {
            return false;
        }
        TipoPerfilReporte other = (TipoPerfilReporte) object;
        if ((this.diacoPerfilReportePK == null && other.diacoPerfilReportePK != null) || (this.diacoPerfilReportePK != null && !this.diacoPerfilReportePK.equals(other.diacoPerfilReportePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.TipoPerfilReporte[ diacoPerfilReportePK=" + diacoPerfilReportePK + " ]";
    }
    
}
