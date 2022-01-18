/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mps
 */
@Embeddable
public class TipoUsuarioPerfilPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_perfil_puesto")
    private int idPerfilPuesto;

    public TipoUsuarioPerfilPK() {
    }

    public TipoUsuarioPerfilPK(int idUsuario, int idPerfilPuesto) {
        this.idUsuario = idUsuario;
        this.idPerfilPuesto = idPerfilPuesto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPerfilPuesto() {
        return idPerfilPuesto;
    }

    public void setIdPerfilPuesto(int idPerfilPuesto) {
        this.idPerfilPuesto = idPerfilPuesto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuario;
        hash += (int) idPerfilPuesto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoUsuarioPerfilPK)) {
            return false;
        }
        TipoUsuarioPerfilPK other = (TipoUsuarioPerfilPK) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idPerfilPuesto != other.idPerfilPuesto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.TipoUsuarioPerfilPK[ idUsuario=" + idUsuario + ", idPerfilPuesto=" + idPerfilPuesto + " ]";
    }
    
}
