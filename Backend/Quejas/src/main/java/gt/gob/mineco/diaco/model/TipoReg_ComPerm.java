package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import gt.gob.mineco.diaco.model.TipoQuejaRegistro;
/**
 * The persistent class for the email database table.
 * 
 */
@Entity
@Table(name="reg_comunicacion_permanente")
//@NamedQuery(name = "TipoReg_ComPerm.findAll",query = "SELECT t FROM TipoReg_ComPerm t inner join t.tipoQuejaRegistro og where og.id_queja = :idqueja")
@NamedQuery(name = "TipoReg_ComPerm.findAll",query = "SELECT t FROM TipoReg_ComPerm t where t.id_queja = :idqueja")
public class TipoReg_ComPerm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", precision=0)
        private Integer id;
        @Column(name="codigo")
        private String codigo;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_presentacion")
        private Date fecha_presentacion;
        @Column(name="id_queja")
        private Integer id_queja;
        @Column(name="id_registro")
        private Integer id_registro;
        @Column(name="id_tipo_registro")
        private Integer id_tipo_registro;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_asignacion")
        private Date fecha_asignacion;
        @Column(name="responsable")
        private String responsable;
        @Column(name="via_comunicacion")
        private String via_comunicacion;
        @Column(name="con_nombre")
        private String con_nombre;
        @Column(name="con_telefono")
        private String con_telefono;
        @Column(name="con_email")
        private String con_email;
        @Column(name="estatus")
        private String estatus;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_notificacion")
        private Date fecha_notificacion;
        @Column(name="observaciones")
        private String observaciones;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_creacion")
        private Date fecha_creacion;
        @Column(name="creado_por")
        private Integer creado_por;
        @Column(name="anio_queja")
        private Integer anio_queja;
        @Column(name="quejanumero")
        private Integer quejanumero;
        /*@JoinColumn(name = "id", referencedColumnName = "id_detalle",updatable=false, insertable=false)
        @OneToOne
        private TipoQuejaRegistro tipoQuejaRegistro;   */     

	
	public TipoReg_ComPerm() {
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFecha_presentacion() {
        return fecha_presentacion;
    }

    public void setFecha_presentacion(Date fecha_presentacion) {
        this.fecha_presentacion = fecha_presentacion;
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

    public Date getFecha_asignacion() {
        return fecha_asignacion;
    }

    public void setFecha_asignacion(Date fecha_asignacion) {
        this.fecha_asignacion = fecha_asignacion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getVia_comunicacion() {
        return via_comunicacion;
    }

    public void setVia_comunicacion(String via_comunicacion) {
        this.via_comunicacion = via_comunicacion;
    }

    public String getCon_nombre() {
        return con_nombre;
    }

    public void setCon_nombre(String con_nombre) {
        this.con_nombre = con_nombre;
    }

    public String getCon_telefono() {
        return con_telefono;
    }

    public void setCon_telefono(String con_telefono) {
        this.con_telefono = con_telefono;
    }

    public String getCon_email() {
        return con_email;
    }

    public void setCon_email(String con_email) {
        this.con_email = con_email;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Date getFecha_notificacion() {
        return fecha_notificacion;
    }

    public void setFecha_notificacion(Date fecha_notificacion) {
        this.fecha_notificacion = fecha_notificacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
            temp.addProperty("fecha_presentacion",this.fecha_presentacion.toString());
            temp.addProperty("id_queja",this.id_queja);
            temp.addProperty("id_registro",this.id_registro);
            temp.addProperty("id_tipo_registro",this.id_tipo_registro);
            temp.addProperty("fecha_asignacion",this.fecha_asignacion.toString());
            temp.addProperty("responsable",this.responsable);
            temp.addProperty("via_comunicacion",this.via_comunicacion);
            temp.addProperty("con_nombre",this.con_nombre);
            temp.addProperty("con_telefono",this.con_telefono);
            temp.addProperty("con_email",this.con_email);
            temp.addProperty("estatus",this.estatus);
            temp.addProperty("fecha_notificacion",this.fecha_notificacion.toString());
            temp.addProperty("observaciones",this.observaciones);
            temp.addProperty("anio_queja",this.anio_queja);
            temp.addProperty("quejanumero",this.quejanumero);
            return temp.toString()+",";
        }
   
        public JsonObject toJsonelement() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id",this.id);
            temp.addProperty("codigo",this.codigo);
            temp.addProperty("fecha_presentacion",this.fecha_presentacion.toString());
            temp.addProperty("id_queja",this.id_queja);
            temp.addProperty("id_registro",this.id_registro);
            temp.addProperty("id_tipo_registro",this.id_tipo_registro);
            temp.addProperty("fecha_asignacion",this.fecha_asignacion.toString());
            temp.addProperty("responsable",this.responsable);
            temp.addProperty("via_comunicacion",this.via_comunicacion);
            temp.addProperty("con_nombre",this.con_nombre);
            temp.addProperty("con_telefono",this.con_telefono);
            temp.addProperty("con_email",this.con_email);
            temp.addProperty("estatus",this.estatus);
            temp.addProperty("fecha_notificacion",this.fecha_notificacion.toString());
            temp.addProperty("observaciones",this.observaciones);
            temp.addProperty("anio_queja",this.anio_queja);
            temp.addProperty("quejanumero",this.quejanumero);
            return temp;
        }

        
}
