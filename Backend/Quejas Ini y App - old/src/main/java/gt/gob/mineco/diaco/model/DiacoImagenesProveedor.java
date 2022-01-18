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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mps
 */
@Entity
@Table(name = "diaco_imagenes_proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiacoImagenesProveedor.findAll", query = "SELECT d FROM DiacoImagenesProveedor d"),
    @NamedQuery(name = "DiacoImagenesProveedor.findByIdImagenProveedor", query = "SELECT d FROM DiacoImagenesProveedor d WHERE d.idImagenProveedor = :idImagenProveedor"),
    @NamedQuery(name = "DiacoImagenesProveedor.findByImagen", query = "SELECT d FROM DiacoImagenesProveedor d WHERE d.imagen = :imagen"),
    @NamedQuery(name = "DiacoImagenesProveedor.findByUsuarioAdiciono", query = "SELECT d FROM DiacoImagenesProveedor d WHERE d.usuarioAdiciono = :usuarioAdiciono"),
    @NamedQuery(name = "DiacoImagenesProveedor.findByFechaAdicion", query = "SELECT d FROM DiacoImagenesProveedor d WHERE d.fechaAdicion = :fechaAdicion"),
    @NamedQuery(name = "DiacoImagenesProveedor.findByHabilitado", query = "SELECT d FROM DiacoImagenesProveedor d WHERE d.habilitado = :habilitado"),
    @NamedQuery(name = "DiacoImagenesProveedor.findByIdCategoriaImagen", query = "SELECT d FROM DiacoImagenesProveedor d WHERE d.idCategoriaImagen = :idCategoriaImagen"),
    @NamedQuery(name = "DiacoImagenesProveedor.findByIdRepositorio", query = "SELECT d FROM DiacoImagenesProveedor d WHERE d.idRepositorio = :idRepositorio"),
    @NamedQuery(name = "DiacoImagenesProveedor.findByDescripcionImagenOtros", query = "SELECT d FROM DiacoImagenesProveedor d WHERE d.descripcionImagenOtros = :descripcionImagenOtros"),
    @NamedQuery(name = "DiacoImagenesProveedor.findbyCatImagen", query = "SELECT t FROM DiacoImagenesProveedor t WHERE t.habilitado = 'A' and t.idProveedorAprovar.id = :idproveedor and t.idCategoriaImagen = :categoria ORDER BY t.fechaAdicion"),
})
public class DiacoImagenesProveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @Basic(optional = false)
//    @NotNull
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_imagen_proveedor")
    private Integer idImagenProveedor;
    //@Size(max = 100)
    @Column(name = "imagen")
    private String imagen;
    //@Size(max = 20)
    @Column(name = "usuario_adiciono")
    private String usuarioAdiciono;
    @Column(name = "fecha_adicion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAdicion;
    //@Size(max = 1)
    @Column(name = "habilitado")
    private String habilitado;
    @Column(name = "id_categoria_imagen")
    private Integer idCategoriaImagen;
    @Column(name = "id_repositorio")
    private Integer idRepositorio;
    //@Size(max = 100)
    @Column(name = "descripcion_imagen_otros")
    private String descripcionImagenOtros;
    @JoinColumn(name = "id_proveedor_aprovar", referencedColumnName = "id")
    @ManyToOne
    private DiacoProveedorAprovar idProveedorAprovar;
    @JoinColumn(name = "id_tipo_imagen", referencedColumnName = "id_tipo_imagen")
    @ManyToOne
    private TipoTipoImagen idTipoImagen;

    public DiacoImagenesProveedor() {
    }

    public DiacoImagenesProveedor(Integer idImagenProveedor) {
        this.idImagenProveedor = idImagenProveedor;
    }

    public Integer getIdImagenProveedor() {
        return idImagenProveedor;
    }

    public void setIdImagenProveedor(Integer idImagenProveedor) {
        this.idImagenProveedor = idImagenProveedor;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getUsuarioAdiciono() {
        return usuarioAdiciono;
    }

    public void setUsuarioAdiciono(String usuarioAdiciono) {
        this.usuarioAdiciono = usuarioAdiciono;
    }

    public Date getFechaAdicion() {
        return fechaAdicion;
    }

    public void setFechaAdicion(Date fechaAdicion) {
        this.fechaAdicion = fechaAdicion;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    public Integer getIdCategoriaImagen() {
        return idCategoriaImagen;
    }

    public void setIdCategoriaImagen(Integer idCategoriaImagen) {
        this.idCategoriaImagen = idCategoriaImagen;
    }

    public Integer getIdRepositorio() {
        return idRepositorio;
    }

    public void setIdRepositorio(Integer idRepositorio) {
        this.idRepositorio = idRepositorio;
    }

    public String getDescripcionImagenOtros() {
        return descripcionImagenOtros;
    }

    public void setDescripcionImagenOtros(String descripcionImagenOtros) {
        this.descripcionImagenOtros = descripcionImagenOtros;
    }

    public DiacoProveedorAprovar getIdProveedorAprovar() {
        return idProveedorAprovar;
    }

    public void setIdProveedorAprovar(DiacoProveedorAprovar idProveedorAprovar) {
        this.idProveedorAprovar = idProveedorAprovar;
    }

    public TipoTipoImagen getIdTipoImagen() {
        return idTipoImagen;
    }

    public void setIdTipoImagen(TipoTipoImagen idTipoImagen) {
        this.idTipoImagen = idTipoImagen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImagenProveedor != null ? idImagenProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiacoImagenesProveedor)) {
            return false;
        }
        DiacoImagenesProveedor other = (DiacoImagenesProveedor) object;
        if ((this.idImagenProveedor == null && other.idImagenProveedor != null) || (this.idImagenProveedor != null && !this.idImagenProveedor.equals(other.idImagenProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.DiacoImagenesProveedor[ idImagenProveedor=" + idImagenProveedor + " ]";
    }
    
}
