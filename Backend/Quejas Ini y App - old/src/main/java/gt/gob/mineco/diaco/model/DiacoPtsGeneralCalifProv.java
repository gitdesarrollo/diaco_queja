/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author julio
 */
@Entity
@Table(name = "diaco_pts_general_calif_prov")
@NamedQueries({
    @NamedQuery(name = "DiacoPtsGeneralCalifProv.findAll", query = "SELECT d FROM DiacoPtsGeneralCalifProv d")
    , @NamedQuery(name = "DiacoPtsGeneralCalifProv.findByIdPtsGeneralCalifProv", query = "SELECT d FROM DiacoPtsGeneralCalifProv d WHERE d.idPtsGeneralCalifProv = :idPtsGeneralCalifProv")
    , @NamedQuery(name = "DiacoPtsGeneralCalifProv.findByPromedio", query = "SELECT d FROM DiacoPtsGeneralCalifProv d WHERE d.promedio = :promedio")
    , @NamedQuery(name = "DiacoPtsGeneralCalifProv.findByIdProveedor", query = "SELECT d FROM DiacoPtsGeneralCalifProv d WHERE d.idProveedor = :idProveedor")})
public class DiacoPtsGeneralCalifProv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_pts_general_calif_prov")
    private Integer idPtsGeneralCalifProv;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "promedio")
    private BigDecimal promedio;
    @Column(name = "id_proveedor")
    private Integer idProveedor;
    @Column(name = "num_calificaciones")
    private Integer num_calificaciones;
    @JoinColumn(name = "id_categoria_calificacion", referencedColumnName = "id_categoria_calificacion")
    @ManyToOne
    private DiacoCatalogoCatagoriaCalificacion idCategoriaCalificacion;

    public DiacoPtsGeneralCalifProv() {
    }

    public DiacoPtsGeneralCalifProv(Integer idPtsGeneralCalifProv) {
        this.idPtsGeneralCalifProv = idPtsGeneralCalifProv;
    }

    public Integer getIdPtsGeneralCalifProv() {
        return idPtsGeneralCalifProv;
    }

    public void setIdPtsGeneralCalifProv(Integer idPtsGeneralCalifProv) {
        this.idPtsGeneralCalifProv = idPtsGeneralCalifProv;
    }

    public BigDecimal getPromedio() {
        return promedio;
    }

    public void setPromedio(BigDecimal promedio) {
        this.promedio = promedio;
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
        hash += (idPtsGeneralCalifProv != null ? idPtsGeneralCalifProv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiacoPtsGeneralCalifProv)) {
            return false;
        }
        DiacoPtsGeneralCalifProv other = (DiacoPtsGeneralCalifProv) object;
        if ((this.idPtsGeneralCalifProv == null && other.idPtsGeneralCalifProv != null) || (this.idPtsGeneralCalifProv != null && !this.idPtsGeneralCalifProv.equals(other.idPtsGeneralCalifProv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.DiacoPtsGeneralCalifProv[ idPtsGeneralCalifProv=" + idPtsGeneralCalifProv + " ]";
    }

    /**
     * @return the num_calificaciones
     */
    public Integer getNum_calificaciones() {
        return num_calificaciones;
    }

    /**
     * @param num_calificaciones the num_calificaciones to set
     */
    public void setNum_calificaciones(Integer num_calificaciones) {
        this.num_calificaciones = num_calificaciones;
    }
    
}
