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
public class TipoPerfilReportePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_perfil")
    private int idPerfil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_reporte")
    private int idReporte;

    public TipoPerfilReportePK() {
    }

    public TipoPerfilReportePK(int idPerfil, int idReporte) {
        this.idPerfil = idPerfil;
        this.idReporte = idReporte;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPerfil;
        hash += (int) idReporte;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPerfilReportePK)) {
            return false;
        }
        TipoPerfilReportePK other = (TipoPerfilReportePK) object;
        if (this.idPerfil != other.idPerfil) {
            return false;
        }
        if (this.idReporte != other.idReporte) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.TipoPerfilReportePK[ idPerfil=" + idPerfil + ", idReporte=" + idReporte + " ]";
    }
    
}
