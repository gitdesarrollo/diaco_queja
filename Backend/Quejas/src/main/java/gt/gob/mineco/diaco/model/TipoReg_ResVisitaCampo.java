package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import gt.gob.mineco.diaco.model.TipoQuejaRegistro;
/**
 * The persistent class for the reg_resolucion_visita_campo database table.
 * 
 */
@Entity
@Table(name="reg_resolucion_visita_campo")
@NamedQuery(name = "TipoReg_ResVisitaCampo.findAll",query = "SELECT t FROM TipoReg_ResVisitaCampo t where t.id_queja = :id_queja")
public class TipoReg_ResVisitaCampo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", precision=0)
        private Integer id;
        @Column(name="codigo")
        private String codigo;
        @Column(name="id_queja")
        private Integer id_queja;
        @Column(name="id_registro")
        private Integer id_registro;
        @Column(name="id_tipo_registro")
        private Integer id_tipo_registro;
        @Column(name="anio_queja")
        private Integer anio_queja;
        @Column(name="quejanumero")
        private Integer quejanumero;
        @Column(name="version")
        private String version;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_creacion")
        private Date fecha_creacion;
        @Column(name="creado_por")
        private Integer creado_por;
        @Column(name="nombre_consumidor")        
	private String nombre_consumidor;
        @Column(name="nombre_proveedor")        
	private String nombre_proveedor;
        @Column(name="correlativo_resolucion")        
	private String correlativo_resolucion;
        /*@JoinColumn(name = "id", referencedColumnName = "id_detalle",updatable=false, insertable=false)
        @OneToOne
        private TipoQuejaRegistro tipoQuejaRegistro;   */     

	
	public TipoReg_ResVisitaCampo() {
	}

    public String getNombre_consumidor() {
        return nombre_consumidor;
    }

    public void setNombre_consumidor(String nombre_consumidor) {
        this.nombre_consumidor = nombre_consumidor;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getId_queja() {
        return id_queja;
    }

    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
    }

    public Integer getId_registro() {
        return id_registro;
    }

    public void setId_registro(Integer id_registro) {
        this.id_registro = id_registro;
    }

    public Integer getId_tipo_registro() {
        return id_tipo_registro;
    }

    public void setId_tipo_registro(Integer id_tipo_registro) {
        this.id_tipo_registro = id_tipo_registro;
    }

    public Integer getAnio_queja() {
        return anio_queja;
    }

    public void setAnio_queja(Integer anio_queja) {
        this.anio_queja = anio_queja;
    }

    public Integer getQuejanumero() {
        return quejanumero;
    }

    public void setQuejanumero(Integer quejanumero) {
        this.quejanumero = quejanumero;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Integer getCreado_por() {
        return creado_por;
    }

    public void setCreado_por(Integer creado_por) {
        this.creado_por = creado_por;
    }

        
        
        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id",this.id);
            temp.addProperty("codigo",this.codigo);
            temp.addProperty("id_queja",this.id_queja);
            temp.addProperty("id_registro",this.id_registro);
            temp.addProperty("id_tipo_registro",this.id_tipo_registro);
            temp.addProperty("anio_queja",this.anio_queja);
            temp.addProperty("quejanumero",this.quejanumero);
            temp.addProperty("version",this.version);
            temp.addProperty("nombre_consumidor",this.nombre_consumidor);
            temp.addProperty("nombre_proveedor",this.nombre_proveedor);
            return temp.toString()+",";
        }
   
        public JsonObject toJsonelement() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id",this.id);
            temp.addProperty("codigo",this.codigo);
            temp.addProperty("id_queja",this.id_queja);
            temp.addProperty("id_registro",this.id_registro);
            temp.addProperty("id_tipo_registro",this.id_tipo_registro);
            temp.addProperty("anio_queja",this.anio_queja);
            temp.addProperty("quejanumero",this.quejanumero);
            temp.addProperty("version",this.version);
            temp.addProperty("nombre_consumidor",this.nombre_consumidor);
            temp.addProperty("nombre_proveedor",this.nombre_proveedor);
            return temp;
        }

    /**
     * @return the correlativo_resolucion
     */
    public String getCorrelativo_resolucion() {
        return correlativo_resolucion;
    }

    /**
     * @param correlativo_resolucion the correlativo_resolucion to set
     */
    public void setCorrelativo_resolucion(String correlativo_resolucion) {
        this.correlativo_resolucion = correlativo_resolucion;
    }

        
}
