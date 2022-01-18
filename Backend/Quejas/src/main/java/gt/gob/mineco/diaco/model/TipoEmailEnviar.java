package gt.gob.mineco.diaco.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import gt.gob.mineco.diaco.util.CheckNull;
import gt.gob.mineco.diaco.model.TipoPasoQueja;
import gt.gob.mineco.diaco.model.TipoProgramaAudiencia;

/**
 * The persistent class for the diaco_email_enviar database table.
 * 
 */
@Entity
@Table(name="diaco_email_enviar")
@NamedQueries({
    @NamedQuery(
        name = "TipoEmailEnviar.findxIdQuejaNo",
        query = "SELECT t FROM TipoEmailEnviar t where t.id_queja = :id_queja"),
    @NamedQuery(
        name = "TipoEmailEnviar.findxActivo",
        query = "SELECT t FROM TipoEmailEnviar t where t.estado = 'A'")
})

public class TipoEmailEnviar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", precision=0)
        private Integer id;
	@Column(name="id_queja", precision=0)
        private Integer id_queja;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_creado")
        private Date fecha_creado;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_enviado")
        private Date fecha_enviado;
        @Column(name="de")
        private String de;
        @Column(name="para")
        private String para;
        @Column(name="subject")
        private String subject;
        @Column(name="mensaje")
        private String mensaje;
        @Column(name="fallos", precision=0)
        private Integer fallos;
        @Column(name="estado")
        private String estado;
        @Column(name="id_fuente", precision=0)
        private Integer id_fuente;     
             
	public TipoEmailEnviar() {
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

    public Date getFecha_creado() {
        return fecha_creado;
    }

    public void setFecha_creado(Date fecha_creado) {
        this.fecha_creado = fecha_creado;
    }

    public Date getFecha_enviado() {
        return fecha_enviado;
    }

    public void setFecha_enviado(Date fecha_enviado) {
        this.fecha_enviado = fecha_enviado;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getFallos() {
        return fallos;
    }

    public void setFallos(Integer fallos) {
        this.fallos = fallos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getId_fuente() {
        return id_fuente;
    }

    public void setId_fuente(Integer id_fuente) {
        this.id_fuente = id_fuente;
    }

        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id",this.id);
            temp.addProperty("id_queja",this.id_queja);
            String fecha= fecha_creado.toString();
            temp.addProperty("fecha_creado",fecha);
            String fecha_env= fecha_enviado.toString();
            temp.addProperty("fecha_enviado",fecha_env);
            temp.addProperty("de",this.de);
            temp.addProperty("para",this.para);
            temp.addProperty("subject",this.subject);
            temp.addProperty("mensaje",this.mensaje);
            temp.addProperty("fallos",this.fallos);
            temp.addProperty("estado",this.estado);
            temp.addProperty("id_fuente",this.id_fuente);
            return temp.toString()+",";
        }
                
}
