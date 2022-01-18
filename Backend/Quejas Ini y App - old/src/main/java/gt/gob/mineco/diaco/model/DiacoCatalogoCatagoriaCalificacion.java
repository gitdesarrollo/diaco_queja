/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.model;


import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author julio
 */
@Entity
@Table(name = "diaco_catalogo_catagoria_calificacion")
@NamedQueries({
    @NamedQuery(name = "DiacoCatalogoCatagoriaCalificacion.findAll", query = "SELECT d FROM DiacoCatalogoCatagoriaCalificacion d")
    , @NamedQuery(name = "DiacoCatalogoCatagoriaCalificacion.findByIdCategoriaCalificacion", query = "SELECT d FROM DiacoCatalogoCatagoriaCalificacion d WHERE d.idCategoriaCalificacion = :idCategoriaCalificacion")
    , @NamedQuery(name = "DiacoCatalogoCatagoriaCalificacion.findByNombreCategoria", query = "SELECT d FROM DiacoCatalogoCatagoriaCalificacion d WHERE d.nombreCategoria = :nombreCategoria")})
public class DiacoCatalogoCatagoriaCalificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_categoria_calificacion")
    private Integer idCategoriaCalificacion;
    @Size(max = 50)
    @Column(name = "nombre_categoria")
    private String nombreCategoria;
    @JsonbTransient 
    @OneToMany(mappedBy = "idCategoriaCalificacion")
    private Collection<DiacoPtsGeneralCalifProv> diacoPtsGeneralCalifProvCollection;
    @JsonbTransient 
    @OneToMany(mappedBy = "idCategoriaCalificacion")
    private Collection<DiacoCalificacionUsuarioProv> diacoCalificacionUsuarioProvCollection;

    public DiacoCatalogoCatagoriaCalificacion() {
    }

    public DiacoCatalogoCatagoriaCalificacion(Integer idCategoriaCalificacion) {
        this.idCategoriaCalificacion = idCategoriaCalificacion;
    }

    public Integer getIdCategoriaCalificacion() {
        return idCategoriaCalificacion;
    }

    public void setIdCategoriaCalificacion(Integer idCategoriaCalificacion) {
        this.idCategoriaCalificacion = idCategoriaCalificacion;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
    
    public Collection<DiacoPtsGeneralCalifProv> getDiacoPtsGeneralCalifProvCollection() {
        return diacoPtsGeneralCalifProvCollection;
    }

    public void setDiacoPtsGeneralCalifProvCollection(Collection<DiacoPtsGeneralCalifProv> diacoPtsGeneralCalifProvCollection) {
        this.diacoPtsGeneralCalifProvCollection = diacoPtsGeneralCalifProvCollection;
    }
 
    public Collection<DiacoCalificacionUsuarioProv> getDiacoCalificacionUsuarioProvCollection() {
        return diacoCalificacionUsuarioProvCollection;
    }

    public void setDiacoCalificacionUsuarioProvCollection(Collection<DiacoCalificacionUsuarioProv> diacoCalificacionUsuarioProvCollection) {
        this.diacoCalificacionUsuarioProvCollection = diacoCalificacionUsuarioProvCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoriaCalificacion != null ? idCategoriaCalificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiacoCatalogoCatagoriaCalificacion)) {
            return false;
        }
        DiacoCatalogoCatagoriaCalificacion other = (DiacoCatalogoCatagoriaCalificacion) object;
        if ((this.idCategoriaCalificacion == null && other.idCategoriaCalificacion != null) || (this.idCategoriaCalificacion != null && !this.idCategoriaCalificacion.equals(other.idCategoriaCalificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.DiacoCatalogoCatagoriaCalificacion[ idCategoriaCalificacion=" + idCategoriaCalificacion + " ]";
    }
    
}
