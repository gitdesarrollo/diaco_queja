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
@Table(name = "diaco_perfil_catalogo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPerfilCatalogo.findAll", query = "SELECT d FROM TipoPerfilCatalogo d")
    , @NamedQuery(name = "TipoPerfilCatalogo.findByIdCatalogo", query = "SELECT d FROM TipoPerfilCatalogo d WHERE d.diacoPerfilCatalogoPK.idCatalogo = :idCatalogo")
    , @NamedQuery(name = "TipoPerfilCatalogo.findByIdPerfil", query = "SELECT d FROM TipoPerfilCatalogo d WHERE d.diacoPerfilCatalogoPK.idPerfil = :idPerfil")
    , @NamedQuery(name = "TipoPerfilCatalogo.findByFechaAdicion", query = "SELECT d FROM TipoPerfilCatalogo d WHERE d.fechaAdicion = :fechaAdicion")
    , @NamedQuery(name = "TipoPerfilCatalogo.findByUsuarioAdicion", query = "SELECT d FROM TipoPerfilCatalogo d WHERE d.usuarioAdicion = :usuarioAdicion")})
public class TipoPerfilCatalogo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TipoPerfilCatalogoPK diacoPerfilCatalogoPK;
    @Column(name = "fecha_adicion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAdicion;
    @Size(max = 20)
    @Column(name = "usuario_adicion")
    private String usuarioAdicion;
    @JoinColumn(name = "id_catalogo", referencedColumnName = "id_catalogo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoCatalogo diacoCatalogo;
    @JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil_puesto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoPerfilesPuesto diacoPerfilesPuesto;

    public TipoPerfilCatalogo() {
    }

    public TipoPerfilCatalogo(TipoPerfilCatalogoPK diacoPerfilCatalogoPK) {
        this.diacoPerfilCatalogoPK = diacoPerfilCatalogoPK;
    }

    public TipoPerfilCatalogo(int idCatalogo, int idPerfil) {
        this.diacoPerfilCatalogoPK = new TipoPerfilCatalogoPK(idCatalogo, idPerfil);
    }

    public TipoPerfilCatalogoPK getTipoPerfilCatalogoPK() {
        return diacoPerfilCatalogoPK;
    }

    public void setTipoPerfilCatalogoPK(TipoPerfilCatalogoPK diacoPerfilCatalogoPK) {
        this.diacoPerfilCatalogoPK = diacoPerfilCatalogoPK;
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

    public TipoCatalogo getTipoCatalogo() {
        return diacoCatalogo;
    }

    public void setTipoCatalogo(TipoCatalogo diacoCatalogo) {
        this.diacoCatalogo = diacoCatalogo;
    }

    public TipoPerfilesPuesto getTipoPerfilesPuesto() {
        return diacoPerfilesPuesto;
    }

    public void setTipoPerfilesPuesto(TipoPerfilesPuesto diacoPerfilesPuesto) {
        this.diacoPerfilesPuesto = diacoPerfilesPuesto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diacoPerfilCatalogoPK != null ? diacoPerfilCatalogoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPerfilCatalogo)) {
            return false;
        }
        TipoPerfilCatalogo other = (TipoPerfilCatalogo) object;
        if ((this.diacoPerfilCatalogoPK == null && other.diacoPerfilCatalogoPK != null) || (this.diacoPerfilCatalogoPK != null && !this.diacoPerfilCatalogoPK.equals(other.diacoPerfilCatalogoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.TipoPerfilCatalogo[ diacoPerfilCatalogoPK=" + diacoPerfilCatalogoPK + " ]";
    }
    
}
