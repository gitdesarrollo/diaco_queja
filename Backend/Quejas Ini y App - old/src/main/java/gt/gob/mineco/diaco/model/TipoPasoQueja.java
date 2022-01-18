package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * The persistent class for the diaco_paso_queja database table.
 * 
 */
@Entity
@Table(name="diaco_paso_queja")
@NamedQueries({
    @NamedQuery(name = "TipoPasoQueja.findByType",query = "SELECT t FROM TipoPasoQueja t where t.id_queja = :id_queja and t.id_tipo_registro = :id_tipo_registro ORDER BY t.fecha_operacion desc"),
    @NamedQuery(name="TipoPasoQueja.findAll", query="SELECT t FROM TipoPasoQueja t where t.id_queja = :idqueja ORDER BY t.fecha_operacion desc"),
    @NamedQuery(name="TipoPasoQueja.findByIdQueja", query="SELECT t FROM TipoPasoQueja t where t.id_queja = :idqueja and  t.fecha_operacion = (SELECT MAX(tt.fecha_operacion) FROM TipoPasoQueja tt where tt.id_queja = :idqueja)")
})


public class TipoPasoQueja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_queja_paso", precision=0) 
        private double id_queja_paso;
        @Column(name="id_queja")
        private Integer id_queja;
        @Column(name="id_estado_operado")
        private Integer id_estado_operado;
        @Column(name="descripcion")
        private String descripcion;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_operacion")        
        private Date fecha_operacion;
        @Column(name="usuario_operacion")
        private Integer usuario_operacion;
        @Column(name="id_sede_diaco_operacio")
        private Integer id_sede_diaco_operacio;
        @Column(name="id_departamento_interno")
        private Integer id_departamento_interno;
        @Column(name="descripcion_consumidor")
        private String descripcion_consumidor;
        @Column(name="id_tipo_registro")
        private Integer id_tipo_registro;
        @Column(name="operacion")
        private String operacion;
        
	public TipoPasoQueja() {
	}

        public TipoPasoQueja(double id_queja_paso, Integer id_queja, Integer id_estado_operado, String descripcion, Date fecha_operacion, Integer usuario_operacion, Integer id_sede_diaco_operacio, Integer id_departamento_interno, String descripcion_consumidor, Integer id_tipo_registro ) {
            this.id_queja_paso = id_queja_paso;
            this.id_queja = id_queja;
            this.id_estado_operado = id_estado_operado;
            this.descripcion = descripcion;
            this.fecha_operacion = fecha_operacion;
            this.usuario_operacion = usuario_operacion;
            this.id_sede_diaco_operacio = id_sede_diaco_operacio;
            this.id_departamento_interno = id_departamento_interno;
            this.descripcion_consumidor = descripcion_consumidor;
            this.id_tipo_registro = id_tipo_registro;
	}

    public Integer getId_tipo_registro() {
        return id_tipo_registro;
    }

    public void setId_tipo_registro(Integer id_tipo_registro) {
        this.id_tipo_registro = id_tipo_registro;
    }

    public double getId_queja_paso() {
        return id_queja_paso;
    }

    public void setId_queja_paso(double id_queja_paso) {
        this.id_queja_paso = id_queja_paso;
    }

    public Integer getId_queja() {
        return id_queja;
    }

    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
    }

    public Integer getId_estado_operado() {
        return id_estado_operado;
    }

    public void setId_estado_operado(Integer id_estado_operado) {
        this.id_estado_operado = id_estado_operado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_operacion() {
        return fecha_operacion;
    }

    public void setFecha_operacion(Date fecha_operacion) {
        this.fecha_operacion = fecha_operacion;
    }

    public Integer getUsuario_operacion() {
        return usuario_operacion;
    }

    public void setUsuario_operacion(Integer usuario_operacion) {
        this.usuario_operacion = usuario_operacion;
    }

    public Integer getId_sede_diaco_operacio() {
        return id_sede_diaco_operacio;
    }

    public void setId_sede_diaco_operacio(Integer id_sede_diaco_operacio) {
        this.id_sede_diaco_operacio = id_sede_diaco_operacio;
    }

    public Integer getId_departamento_interno() {
        return id_departamento_interno;
    }

    public void setId_departamento_interno(Integer id_departamento_interno) {
        this.id_departamento_interno = id_departamento_interno;
    }

    public String getDescripcion_consumidor() {
        return descripcion_consumidor;
    }

    public void setDescripcion_consumidor(String descripcion_consumidor) {
        this.descripcion_consumidor = descripcion_consumidor;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }        

        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_queja_paso",this.id_queja_paso);
            temp.addProperty("id_queja",this.id_queja);
            temp.addProperty("id_estado_operado",this.id_estado_operado);
            temp.addProperty("fecha_operacion",this.fecha_operacion.toString());
            temp.addProperty("usuario_operacion",this.usuario_operacion);
            return temp.toString()+",";            
        }
        
}
