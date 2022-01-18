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
@Table(name = "diaco_usuario_perfil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoUsuarioPerfil.findAll", query = "SELECT d FROM TipoUsuarioPerfil d")
    , @NamedQuery(name = "TipoUsuarioPerfil.findByIdUsuario", query = "SELECT d FROM TipoUsuarioPerfil d WHERE d.diacoUsuarioPerfilPK.idUsuario = :idUsuario")
    , @NamedQuery(name = "TipoUsuarioPerfil.findByIdPerfilPuesto", query = "SELECT d FROM TipoUsuarioPerfil d WHERE d.diacoUsuarioPerfilPK.idPerfilPuesto = :idPerfilPuesto")
    , @NamedQuery(name = "TipoUsuarioPerfil.findByFechaAdicion", query = "SELECT d FROM TipoUsuarioPerfil d WHERE d.fechaAdicion = :fechaAdicion")
    , @NamedQuery(name = "TipoUsuarioPerfil.findByUsuarioAdicion", query = "SELECT d FROM TipoUsuarioPerfil d WHERE d.usuarioAdicion = :usuarioAdicion")})
public class TipoUsuarioPerfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TipoUsuarioPerfilPK diacoUsuarioPerfilPK;
    @Column(name = "fecha_adicion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAdicion;
    @Size(max = 20)
    @Column(name = "usuario_adicion")
    private String usuarioAdicion;
    @JoinColumn(name = "id_perfil_puesto", referencedColumnName = "id_perfil_puesto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoPerfilesPuesto diacoPerfilesPuesto;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoUsuario diacoUsuario;

    public TipoUsuarioPerfil() {
    }

    public TipoUsuarioPerfil(TipoUsuarioPerfilPK diacoUsuarioPerfilPK) {
        this.diacoUsuarioPerfilPK = diacoUsuarioPerfilPK;
    }

    public TipoUsuarioPerfil(int idUsuario, int idPerfilPuesto) {
        this.diacoUsuarioPerfilPK = new TipoUsuarioPerfilPK(idUsuario, idPerfilPuesto);
    }

    public TipoUsuarioPerfilPK getTipoUsuarioPerfilPK() {
        return diacoUsuarioPerfilPK;
    }

    public void setTipoUsuarioPerfilPK(TipoUsuarioPerfilPK diacoUsuarioPerfilPK) {
        this.diacoUsuarioPerfilPK = diacoUsuarioPerfilPK;
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

    public TipoUsuario getTipoUsuario() {
        return diacoUsuario;
    }

    public void setTipoUsuario(TipoUsuario diacoUsuario) {
        this.diacoUsuario = diacoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diacoUsuarioPerfilPK != null ? diacoUsuarioPerfilPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoUsuarioPerfil)) {
            return false;
        }
        TipoUsuarioPerfil other = (TipoUsuarioPerfil) object;
        if ((this.diacoUsuarioPerfilPK == null && other.diacoUsuarioPerfilPK != null) || (this.diacoUsuarioPerfilPK != null && !this.diacoUsuarioPerfilPK.equals(other.diacoUsuarioPerfilPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.TipoUsuarioPerfil[ diacoUsuarioPerfilPK=" + diacoUsuarioPerfilPK + " ]";
    }
    
}
