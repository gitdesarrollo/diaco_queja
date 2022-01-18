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
public class TipoPerfilOperaEstadoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_perfil_puesto")
    private int idPerfilPuesto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_queja_estado")
    private int idQuejaEstado;

    public TipoPerfilOperaEstadoPK() {
    }

    public TipoPerfilOperaEstadoPK(int idPerfilPuesto, int idQuejaEstado) {
        this.idPerfilPuesto = idPerfilPuesto;
        this.idQuejaEstado = idQuejaEstado;
    }

    public int getIdPerfilPuesto() {
        return idPerfilPuesto;
    }

    public void setIdPerfilPuesto(int idPerfilPuesto) {
        this.idPerfilPuesto = idPerfilPuesto;
    }

    public int getIdQuejaEstado() {
        return idQuejaEstado;
    }

    public void setIdQuejaEstado(int idQuejaEstado) {
        this.idQuejaEstado = idQuejaEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPerfilPuesto;
        hash += (int) idQuejaEstado;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPerfilOperaEstadoPK)) {
            return false;
        }
        TipoPerfilOperaEstadoPK other = (TipoPerfilOperaEstadoPK) object;
        if (this.idPerfilPuesto != other.idPerfilPuesto) {
            return false;
        }
        if (this.idQuejaEstado != other.idQuejaEstado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.TipoPerfilOperaEstadoPK[ idPerfilPuesto=" + idPerfilPuesto + ", idQuejaEstado=" + idQuejaEstado + " ]";
    }
    
}
