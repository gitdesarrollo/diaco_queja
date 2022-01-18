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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "diaco_perfiles_puesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPerfilesPuesto.findAll", query = "SELECT d FROM TipoPerfilesPuesto d")
    , @NamedQuery(name = "TipoPerfilesPuesto.findByIdPerfilPuesto", query = "SELECT d FROM TipoPerfilesPuesto d WHERE d.idPerfilPuesto = :idPerfilPuesto")
    , @NamedQuery(name = "TipoPerfilesPuesto.findByPerfil", query = "SELECT d FROM TipoPerfilesPuesto d WHERE d.perfil = :perfil")
    , @NamedQuery(name = "TipoPerfilesPuesto.findByFechaAdicion", query = "SELECT d FROM TipoPerfilesPuesto d WHERE d.fechaAdicion = :fechaAdicion")})
public class TipoPerfilesPuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_perfil_puesto")
    private Integer idPerfilPuesto;
    @Size(max = 1)
    @Column(name = "perfil")
    private String perfil;
    @Column(name = "fecha_adicion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAdicion;
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "diacoPerfilesPuesto")
    private List<TipoPerfilReporte> diacoPerfilReporteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diacoPerfilesPuesto")
    private List<TipoPerfilOperaEstado> diacoPerfilOperaEstadoList;*/
    @JoinColumn(name = "id_flujo", referencedColumnName = "id")
    @ManyToOne
    private TipoFlujo tipoflujo;
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "diacoPerfilesPuesto")
    private List<TipoUsuarioPerfil> diacoUsuarioPerfilList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diacoPerfilesPuesto")
    private List<TipoPerfilCatalogo> diacoPerfilCatalogoList;*/

    public TipoPerfilesPuesto() {
    }

    public TipoPerfilesPuesto(Integer idPerfilPuesto) {
        this.idPerfilPuesto = idPerfilPuesto;
    }

    public Integer getIdPerfilPuesto() {
        return idPerfilPuesto;
    }

    public void setIdPerfilPuesto(Integer idPerfilPuesto) {
        this.idPerfilPuesto = idPerfilPuesto;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Date getFechaAdicion() {
        return fechaAdicion;
    }

    public void setFechaAdicion(Date fechaAdicion) {
        this.fechaAdicion = fechaAdicion;
    }

    public TipoFlujo getTipoflujo() {
        return tipoflujo;
    }

    public void setTipoflujo(TipoFlujo tipoflujo) {
        this.tipoflujo = tipoflujo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerfilPuesto != null ? idPerfilPuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPerfilesPuesto)) {
            return false;
        }
        TipoPerfilesPuesto other = (TipoPerfilesPuesto) object;
        if ((this.idPerfilPuesto == null && other.idPerfilPuesto != null) || (this.idPerfilPuesto != null && !this.idPerfilPuesto.equals(other.idPerfilPuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.TipoPerfilesPuesto[ idPerfilPuesto=" + idPerfilPuesto + " ]";
    }
    
}
