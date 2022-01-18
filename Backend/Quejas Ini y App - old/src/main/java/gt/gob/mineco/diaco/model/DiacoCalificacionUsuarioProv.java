/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.model;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author julio
 */
@Entity
@Table(name = "diaco_calificacion_usuario_prov")
@NamedQueries({
    @NamedQuery(name = "DiacoCalificacionUsuarioProv.findAll", query = "SELECT d FROM DiacoCalificacionUsuarioProv d")
    , @NamedQuery(name = "DiacoCalificacionUsuarioProv.findByIdCalificacionUsuarioProv", query = "SELECT d FROM DiacoCalificacionUsuarioProv d WHERE d.idCalificacionUsuarioProv = :idCalificacionUsuarioProv")
    , @NamedQuery(name = "DiacoCalificacionUsuarioProv.findByIdInstalacionMobil", query = "SELECT d FROM DiacoCalificacionUsuarioProv d WHERE d.idInstalacionMobil = :idInstalacionMobil")
    , @NamedQuery(name = "DiacoCalificacionUsuarioProv.findByCalificacion", query = "SELECT d FROM DiacoCalificacionUsuarioProv d WHERE d.calificacion = :calificacion")
    , @NamedQuery(name = "DiacoCalificacionUsuarioProv.findByIdProveedorAndInstalacionId", query = "SELECT d FROM DiacoCalificacionUsuarioProv d WHERE d.idProveedor = :idProveedor and d.idInstalacionMobil = :idInstalacionMobil")
    , @NamedQuery(name = "DiacoCalificacionUsuarioProv.findByIdProvAndInstallIdAndCategoria", query = "SELECT d FROM DiacoCalificacionUsuarioProv d WHERE d.idProveedor = :idProveedor and d.idInstalacionMobil = :idInstalacionMobil and d.idCategoriaCalificacion = :idCategoriaCalificacion")
    , @NamedQuery(name = "DiacoCalificacionUsuarioProv.findByIdProveedor", query = "SELECT d FROM DiacoCalificacionUsuarioProv d WHERE d.idProveedor = :idProveedor")})
public class DiacoCalificacionUsuarioProv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_calificacion_usuario_prov")
    private Integer idCalificacionUsuarioProv;
    @Size(max = 50)
    @Column(name = "id_instalacion_mobil")
    private String idInstalacionMobil;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_calificacion")
    private Date fecha_calificacion;
    @Column(name = "calificacion")
    private Integer calificacion;
    @Column(name = "id_proveedor")
    private Integer idProveedor;
    @JoinColumn(name = "id_categoria_calificacion", referencedColumnName = "id_categoria_calificacion")
    @ManyToOne
    private DiacoCatalogoCatagoriaCalificacion idCategoriaCalificacion;

    public DiacoCalificacionUsuarioProv() {
    }

    public DiacoCalificacionUsuarioProv(Integer idCalificacionUsuarioProv) {
        this.idCalificacionUsuarioProv = idCalificacionUsuarioProv;
    }

    public Integer getIdCalificacionUsuarioProv() {
        return idCalificacionUsuarioProv;
    }

    public void setIdCalificacionUsuarioProv(Integer idCalificacionUsuarioProv) {
        this.idCalificacionUsuarioProv = idCalificacionUsuarioProv;
    }

    public String getIdInstalacionMobil() {
        return idInstalacionMobil;
    }

    public void setIdInstalacionMobil(String idInstalacionMobil) {
        this.idInstalacionMobil = idInstalacionMobil;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }
    
    public DiacoCatalogoCatagoriaCalificacion getIdCategoriaCalificacion() {
        return idCategoriaCalificacion;
    }

    public void setIdCategoriaCalificacion(DiacoCatalogoCatagoriaCalificacion idCategoriaCalificacion) {
        this.idCategoriaCalificacion = idCategoriaCalificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCalificacionUsuarioProv != null ? idCalificacionUsuarioProv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiacoCalificacionUsuarioProv)) {
            return false;
        }
        DiacoCalificacionUsuarioProv other = (DiacoCalificacionUsuarioProv) object;
        if ((this.idCalificacionUsuarioProv == null && other.idCalificacionUsuarioProv != null) || (this.idCalificacionUsuarioProv != null && !this.idCalificacionUsuarioProv.equals(other.idCalificacionUsuarioProv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.DiacoCalificacionUsuarioProv[ idCalificacionUsuarioProv=" + idCalificacionUsuarioProv + " ]";
    }

    /**
     * @return the fecha_calificacion
     */
    public Date getFecha_calificacion() {
        return fecha_calificacion;
    }

    /**
     * @param fecha_calificacion the fecha_calificacion to set
     */
    public void setFecha_calificacion(Date fecha_calificacion) {
        this.fecha_calificacion = fecha_calificacion;
    }
    
}
