/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mps
 */
@Entity
@Table(name = "diaco_imagenes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiacoImagenes.findAll", query = "SELECT d FROM DiacoImagenes d"),
    @NamedQuery(name = "DiacoImagenes.findByIdImagen", query = "SELECT d FROM DiacoImagenes d WHERE d.idImagen = :idImagen"),
    @NamedQuery(name = "DiacoImagenes.findByImagen", query = "SELECT d FROM DiacoImagenes d WHERE d.imagen = :imagen"),
    @NamedQuery(name = "DiacoImagenes.findByUsuarioAdiciono", query = "SELECT d FROM DiacoImagenes d WHERE d.usuarioAdiciono = :usuarioAdiciono"),
    @NamedQuery(name = "DiacoImagenes.findByFechaAdicion", query = "SELECT d FROM DiacoImagenes d WHERE d.fechaAdicion = :fechaAdicion"),
    @NamedQuery(name = "DiacoImagenes.findByHabilitado", query = "SELECT d FROM DiacoImagenes d WHERE d.habilitado = :habilitado"),
    @NamedQuery(name = "DiacoImagenes.findByIdCategoriaImagen", query = "SELECT d FROM DiacoImagenes d WHERE d.idCategoriaImagen = :idCategoriaImagen"),
    @NamedQuery(name = "DiacoImagenes.findByIdRepositorio", query = "SELECT d FROM DiacoImagenes d WHERE d.idRepositorio = :idRepositorio"),
    @NamedQuery(name = "DiacoImagenes.findByDescripcionImagenOtros", query = "SELECT d FROM DiacoImagenes d WHERE d.descripcionImagenOtros = :descripcionImagenOtros"),
    @NamedQuery(name = "DiacoImagenes.findbyCatImagenProv", query = "SELECT t FROM DiacoImagenes t WHERE t.habilitado = 'A' and t.idProveedor = :idproveedor and t.idCategoriaImagen = :categoria ORDER BY t.fechaAdicion"),
    @NamedQuery(name = "DiacoImagenes.findbyCatImagenCons", query = "SELECT t FROM DiacoImagenes t WHERE t.habilitado = 'A' and t.idConsumidor = :idconsumidor and t.idCategoriaImagen = :categoria ORDER BY t.fechaAdicion")
})
public class DiacoImagenes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @Basic(optional = false)
//    @NotNull
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Integer idImagen;
    @Column(name = "id_proveedor")
    private Integer idProveedor;
    @Column(name = "id_consumidor")
    private Integer idConsumidor;
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
    @JoinColumn(name = "id_tipo_imagen", referencedColumnName = "id_tipo_imagen")
    @ManyToOne
    private TipoTipoImagen idTipoImagen;

    public DiacoImagenes() {
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

    public TipoTipoImagen getIdTipoImagen() {
        return idTipoImagen;
    }

    public void setIdTipoImagen(TipoTipoImagen idTipoImagen) {
        this.idTipoImagen = idTipoImagen;
    }

    public Integer getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdConsumidor() {
        return idConsumidor;
    }

    public void setIdConsumidor(Integer idConsumidor) {
        this.idConsumidor = idConsumidor;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImagen != null ? idImagen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiacoImagenes)) {
            return false;
        }
        DiacoImagenes other = (DiacoImagenes) object;
        if ((this.idImagen == null && other.idImagen != null) || (this.idImagen != null && !this.idImagen.equals(other.idImagen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.DiacoImagenes[ idImagen=" + idImagen + " ]";
    }
    
}
