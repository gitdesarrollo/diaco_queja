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
public class TipoPerfilCatalogoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_catalogo")
    private int idCatalogo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_perfil")
    private int idPerfil;

    public TipoPerfilCatalogoPK() {
    }

    public TipoPerfilCatalogoPK(int idCatalogo, int idPerfil) {
        this.idCatalogo = idCatalogo;
        this.idPerfil = idPerfil;
    }

    public int getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(int idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCatalogo;
        hash += (int) idPerfil;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPerfilCatalogoPK)) {
            return false;
        }
        TipoPerfilCatalogoPK other = (TipoPerfilCatalogoPK) object;
        if (this.idCatalogo != other.idCatalogo) {
            return false;
        }
        if (this.idPerfil != other.idPerfil) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.TipoPerfilCatalogoPK[ idCatalogo=" + idCatalogo + ", idPerfil=" + idPerfil + " ]";
    }
    
}
