package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import gt.gob.mineco.diaco.model.TipoQuejaRegistro;
import gt.gob.mineco.diaco.util.CheckNull;
/**
 * The persistent class for the reg_ficha_queja database table.
 * 
 */
@Entity
@Table(name="reg_ficha_queja")
@NamedQuery(name = "TipoReg_FichaQueja.findAll",query = "SELECT t FROM TipoReg_FichaQueja t where t.id_queja = :idqueja")
public class TipoReg_FichaQueja implements Serializable {
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
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_ingreso_queja")
        private Date fecha_ingreso_queja;
        @Column(name="nombre_proveedor")
        private String nombre_proveedor;
        @Column(name="nombre_consumidor")
        private String nombre_consumidor;
        @Column(name="solicita_que")
        private String solicita_que;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_comunicacion")
        private Date fecha_comunicacion;
        @Column(name="visita_campo")
        private String visita_campo;
        @Column(name="citacion")
        private String citacion;
        @Column(name="audiencia_conciliacion")
        private String audiencia_conciliacion;
        @Column(name="conclusion")
        private String conclusion;
        @Column(name="comentarios")
        private String comentarios;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_creacion")
        private Date fecha_creacion;
        @Column(name="creado_por")
        private Integer creado_por;
        @Column(name="anio_queja")
        private Integer anio_queja;
        @Column(name="quejanumero")
        private Integer quejanumero;
       /* @JoinColumn(name = "id", referencedColumnName = "id_detalle",updatable=false, insertable=false)
        @OneToOne
        private TipoQuejaRegistro tipoQuejaRegistro;   */     

	
	public TipoReg_FichaQueja() {
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

    public Date getFecha_ingreso_queja() {
        return fecha_ingreso_queja;
    }

    public void setFecha_ingreso_queja(Date fecha_ingreso_queja) {
        this.fecha_ingreso_queja = fecha_ingreso_queja;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public String getNombre_consumidor() {
        return nombre_consumidor;
    }

    public void setNombre_consumidor(String nombre_consumidor) {
        this.nombre_consumidor = nombre_consumidor;
    }

    public String getSolicita_que() {
        return solicita_que;
    }

    public void setSolicita_que(String solicita_que) {
        this.solicita_que = solicita_que;
    }

    public Date getFecha_comunicacion() {
        return fecha_comunicacion;
    }

    public void setFecha_comunicacion(Date fecha_comunicacion) {
        this.fecha_comunicacion = fecha_comunicacion;
    }

    public String getVisita_campo() {
        return visita_campo;
    }

    public void setVisita_campo(String visita_campo) {
        this.visita_campo = visita_campo;
    }

    public String getCitacion() {
        return citacion;
    }

    public void setCitacion(String citacion) {
        this.citacion = citacion;
    }

    public String getAudiencia_conciliacion() {
        return audiencia_conciliacion;
    }

    public void setAudiencia_conciliacion(String audiencia_conciliacion) {
        this.audiencia_conciliacion = audiencia_conciliacion;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
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
            temp.addProperty("id",checknull.cknull(this.id));
            temp.addProperty("id_queja",checknull.cknull(this.id_queja));
            temp.addProperty("id_registro",checknull.cknull(this.id_registro));
            temp.addProperty("id_tipo_registro",checknull.cknull(this.id_tipo_registro));
            temp.addProperty("fecha_ingreso_queja",checknull.cknull(this.fecha_ingreso_queja));
            temp.addProperty("nombre_proveedor",checknull.cknull(this.nombre_proveedor));
            temp.addProperty("nombre_consumidor",checknull.cknull(this.nombre_consumidor));
            temp.addProperty("anio_queja",checknull.cknull(this.anio_queja));
            temp.addProperty("quejanumero",checknull.cknull(this.quejanumero));
            /*temp.addProperty("solicita_que",this.solicita_que);
            temp.addProperty("fecha_comunicacion",this.fecha_comunicacion.toString());
            temp.addProperty("visita_campo",this.visita_campo);
            temp.addProperty("citacion",this.citacion);
            temp.addProperty("audiencia_conciliacion",this.audiencia_conciliacion);
            temp.addProperty("conclusion",this.conclusion);
            temp.addProperty("comentarios",this.comentarios);*/
            return temp.toString()+",";
        }
           
}
