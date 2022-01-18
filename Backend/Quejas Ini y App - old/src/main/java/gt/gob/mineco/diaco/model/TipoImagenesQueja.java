package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * The persistent class for the diaco_imagenes_queja database table.
 * 
 */
@Entity
@Table(name="diaco_imagenes_queja")
@NamedQueries({
    @NamedQuery(
        name = "TipoImagenesQueja.findAllFlujoConcVirt",
        query = "SELECT t FROM TipoImagenesQueja t WHERE t.habilitado = 'A' and t.id_queja = :id_queja and (t.id_flujo = 4 or t.id_categoria_imagen in (2,3)) ORDER BY t.fecha_adicion"),
    @NamedQuery(
        name = "TipoImagenesQueja.findAllFlujoAtencionCon",
        query = "SELECT t FROM TipoImagenesQueja t WHERE t.habilitado = 'A' and t.id_queja = :id_queja and t.id_flujo in (1,4) ORDER BY t.fecha_adicion"),
    @NamedQuery(
        name = "TipoImagenesQueja.findbyQuejaImagen",
        query = "SELECT t FROM TipoImagenesQueja t WHERE t.habilitado = 'A' and t.id_queja = :id_queja and t.imagen = :archivo ORDER BY t.fecha_adicion"),
    @NamedQuery(
        name = "TipoImagenesQueja.findbyCatImagen",
        query = "SELECT t FROM TipoImagenesQueja t WHERE t.habilitado = 'A' and t.id_queja = :id_queja and t.id_categoria_imagen = :categoria ORDER BY t.fecha_adicion"),
       @NamedQuery(
        name = "TipoImagenesQueja.findbyTipoI",
        query = "SELECT t FROM TipoImagenesQueja t WHERE t.id_queja = :id_queja")
})
public class TipoImagenesQueja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_imagen_queja;
        @Column(name="id_tipo_imagen")
	private Integer id_tipo_imagen;
	@Column(name="descripcion_imagen_otros")
	private String descripcion_imagen_otros;
        @Column(name="habilitado")
	private String habilitado;
        @Column(name="imagen")
	private String imagen;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_adicion")        
        private Date fecha_adicion;
        @Column(name="usuario_adiciono")
        private Integer usuario_adiciono;
        @Column(name="id_queja")
        private Integer id_queja;
        @Column(name="id_categoria_imagen")
        private Integer id_categoria_imagen;
        @Column(name="id_flujo")
        private Integer id_flujo;
        @Column(name="id_repositorio")
        private Integer id_repositorio;

        @JoinColumn(name = "id_tipo_imagen", referencedColumnName = "id_tipo_imagen",updatable=false, insertable=false)
        @OneToOne
        private TipoTipoImagen tipotipoimagen; 

	public TipoImagenesQueja() {
	}

    public Integer getId_repositorio() {
        return id_repositorio;
    }

    public void setId_repositorio(Integer id_repositorio) {
        this.id_repositorio = id_repositorio;
    }

        
        
    public Integer getId_categoria_imagen() {
        return id_categoria_imagen;
    }

    public void setId_categoria_imagen(Integer id_categoria_imagen) {
        this.id_categoria_imagen = id_categoria_imagen;
    }

    public Integer getId_flujo() {
        return id_flujo;
    }

    public void setId_flujo(Integer id_flujo) {
        this.id_flujo = id_flujo;
    }

    public TipoTipoImagen getTipotipoimagen() {
        return tipotipoimagen;
    }

    public void setTipotipoimagen(TipoTipoImagen tipotipoimagen) {
        this.tipotipoimagen = tipotipoimagen;
    }
        
    public Integer getId_imagen_queja() {
        return id_imagen_queja;
    }

    public void setId_imagen_queja(Integer id_imagen_queja) {
        this.id_imagen_queja = id_imagen_queja;
    }

    public Integer getId_tipo_imagen() {
        return id_tipo_imagen;
    }

    public void setId_tipo_imagen(Integer id_tipo_imagen) {
        this.id_tipo_imagen = id_tipo_imagen;
    }

    public String getDescripcion_imagen_otros() {
        return descripcion_imagen_otros;
    }

    public void setDescripcion_imagen_otros(String descripcion_imagen_otros) {
        this.descripcion_imagen_otros = descripcion_imagen_otros;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFecha_adicion() {
        return fecha_adicion;
    }

    public void setFecha_adicion(Date fecha_adicion) {
        this.fecha_adicion = fecha_adicion;
    }

    public Integer getUsuario_adiciono() {
        return usuario_adiciono;
    }

    public void setUsuario_adiciono(Integer usuario_adiciono) {
        this.usuario_adiciono = usuario_adiciono;
    }

    public Integer getId_queja() {
        return id_queja;
    }

    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
    }

            @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_imagen_queja",this.id_imagen_queja);
            temp.addProperty("id_tipo_imagen",this.id_tipo_imagen);
            temp.addProperty("tipo_imagen",this.tipotipoimagen.getDescripcion_imagen());
            temp.addProperty("descripcion_imagen_otros",this.descripcion_imagen_otros);
            temp.addProperty("imagen",this.imagen);
            temp.addProperty("id_categoria_imagen",this.id_categoria_imagen);
            temp.addProperty("id_flujo",this.id_flujo);
            return temp.toString()+",";
        }
           
}
