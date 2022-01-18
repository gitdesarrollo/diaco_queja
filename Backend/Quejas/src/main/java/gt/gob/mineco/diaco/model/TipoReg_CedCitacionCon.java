package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import gt.gob.mineco.diaco.util.CheckNull;
/**
 * The persistent class for the reg_cedula_citacion_con database table.
 * 
 */
@Entity
@Table(name="reg_cedula_citacion_con")
@NamedQuery(name = "TipoReg_CedCitacionCon.findAll",query = "SELECT t FROM TipoReg_CedCitacionCon t where t.id_audiencia = :id_audiencia")
public class TipoReg_CedCitacionCon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", precision=0)
        private Integer id;
        @Column(name="id_queja")
        private Integer id_queja;
        @Column(name="id_registro")
        private Integer id_registro;
        @Column(name="id_tipo_registro")
        private Integer id_tipo_registro;
        @Column(name="id_audiencia")
        private Integer id_audiencia;
        @Column(name="codigo")
        private String codigo;
        @Column(name="version")
        private String version;
        @Column(name="codigo_citacion")
        private String codigo_citacion;
        @Column(name="jefe_dpto")
        private String jefe_dpto;
        @Column(name="nombre_consumidor")
        private String nombre_consumidor;
        @Column(name="direccion")
        private String direccion;
        @Column(name="zona")
        private String zona;
        @Column(name="dpto_muni")
        private String dpto_muni;
        @Column(name="telefonos")
        private String telefonos;
        @Column(name="direccion_sede")
        private String direccion_sede;
        @Column(name="dpto_sede")
        private String dpto_sede;
        @Column(name="muni_sede")
        private String muni_sede;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_cita")
        private Date fecha_cita;
        @Column(name="no_audiencia")
        private String no_audiencia;
        @Column(name="nombre_proveedor")
        private String nombre_proveedor;
        @Column(name="detalle_queja")
        private String detalle_queja;        
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_creacion")
        private Date fecha_creacion;
        @Column(name="creado_por")
        private Integer creado_por;
        @Column(name="anio_queja")
        private Integer anio_queja;
        @Column(name="quejanumero")
        private Integer quejanumero;
	@Column(name="solucion")
        private String solucion;  
        @Column(name = "correlativo")
         private String correlativo;
        
        // @OneToOne
       @JoinColumn(name = "creado_por", referencedColumnName = "id_usuario",updatable=false, insertable=false)
        private TipoUsuario tipoUsuario;
        
	public TipoReg_CedCitacionCon() {
	}

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
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
        
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getId_audiencia() {
        return id_audiencia;
    }

    public void setId_audiencia(Integer id_audiencia) {
        this.id_audiencia = id_audiencia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCodigo_citacion() {
        return codigo_citacion;
    }

    public void setCodigo_citacion(String codigo_citacion) {
        this.codigo_citacion = codigo_citacion;
    }

    public String getJefe_dpto() {
        return jefe_dpto;
    }

    public void setJefe_dpto(String jefe_dpto) {
        this.jefe_dpto = jefe_dpto;
    }

    public String getNombre_consumidor() {
        return nombre_consumidor;
    }

    public void setNombre_consumidor(String nombre_consumidor) {
        this.nombre_consumidor = nombre_consumidor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getDpto_muni() {
        return dpto_muni;
    }

    public void setDpto_muni(String dpto_muni) {
        this.dpto_muni = dpto_muni;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getDireccion_sede() {
        return direccion_sede;
    }

    public void setDireccion_sede(String direccion_sede) {
        this.direccion_sede = direccion_sede;
    }

    public String getDpto_sede() {
        return dpto_sede;
    }

    public void setDpto_sede(String dpto_sede) {
        this.dpto_sede = dpto_sede;
    }

    public String getMuni_sede() {
        return muni_sede;
    }

    public void setMuni_sede(String muni_sede) {
        this.muni_sede = muni_sede;
    }

    public Date getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(Date fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public String getNo_audiencia() {
        return no_audiencia;
    }

    public void setNo_audiencia(String no_audiencia) {
        this.no_audiencia = no_audiencia;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public String getDetalle_queja() {
        return detalle_queja;
    }

    public void setDetalle_queja(String detalle_queja) {
        this.detalle_queja = detalle_queja;
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
            CheckNull checknull = new CheckNull();
            JsonObject temp = new JsonObject();
            temp.addProperty("id",this.id);
            temp.addProperty("id_queja",this.id_queja);
            temp.addProperty("id_registro",this.id_registro);
            temp.addProperty("id_tipo_registro",this.id_tipo_registro);
            temp.addProperty("id_audiencia",this.id_tipo_registro);
            temp.addProperty("codigo",checknull.cknull(this.codigo));
            temp.addProperty("version",checknull.cknull(this.version));
            temp.addProperty("codigo_citacion",checknull.cknull(this.codigo_citacion));
            temp.addProperty("jefe_dpto",checknull.cknull(this.jefe_dpto));
            temp.addProperty("nombre_consumidor",checknull.cknull(this.nombre_consumidor));
            temp.addProperty("direccion",checknull.cknull(this.direccion));
            temp.addProperty("zona",checknull.cknull(this.zona));
            temp.addProperty("dpto_muni",checknull.cknull(this.dpto_muni));
            temp.addProperty("telefonos",checknull.cknull(this.telefonos));
            temp.addProperty("direccion_sede",checknull.cknull(this.direccion_sede));
            temp.addProperty("dpto_sede",checknull.cknull(this.dpto_sede));
            temp.addProperty("muni_sede",checknull.cknull(this.muni_sede));
            temp.addProperty("fecha_cita",checknull.cknull(this.fecha_cita)); 
            temp.addProperty("no_audiencia",checknull.cknull(this.no_audiencia));
            temp.addProperty("nombre_proveedor",checknull.cknull(this.nombre_proveedor));
            temp.addProperty("detalle_queja",checknull.cknull(this.detalle_queja));
            temp.addProperty("anio_queja",checknull.cknull(this.anio_queja));
            temp.addProperty("quejanumero",checknull.cknull(this.quejanumero));
            return temp.toString()+",";
        }

    /**
     * @return the correlativo
     */
    public String getCorrelativo() {
        return correlativo;
    }

    /**
     * @param correlativo the correlativo to set
     */
    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    /**
     * @return the tipoUsuario
     */
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @param tipoUsuario the tipoUsuario to set
     */
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
           
}
