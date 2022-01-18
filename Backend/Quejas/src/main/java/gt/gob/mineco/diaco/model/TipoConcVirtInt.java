package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import gt.gob.mineco.diaco.model.TipoUsuario_Simple;
import gt.gob.mineco.diaco.util.CheckNull;
/**
 * The persistent class for the diaco_concvirt_interaccion database table.
 * 
 */
@Entity
@Table(name="diaco_concvirt_interaccion")
@NamedQueries({
    @NamedQuery(name="TipoConcVirtInt.findAll", query="SELECT t FROM TipoConcVirtInt t where t.id_queja = :idqueja order by t.fecha"),
        @NamedQuery(name="TipoConcVirtInt.findLast", query="SELECT t FROM TipoConcVirtInt t where t.id_queja = :idqueja order by t.fecha desc")
})
//inner join t.tipopasoqueja og where og.id_queja_paso=:usr
public class TipoConcVirtInt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", precision=0) 
        private Integer id;
        @JoinColumn(name = "id_queja_paso", referencedColumnName = "id_queja_paso")
        @OneToOne
        private TipoPasoQueja tipopasoqueja;        
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha")
        private Date fecha;
        @Column(name="pregunta")        
	private String pregunta;
        @Column(name="respuesta_con")        
	private String respuesta_con;
        @Column(name="id_queja")        
	private Integer id_queja;
        @Column(name="estado")        
	private String estado;
        @Column(name = "cambiar_archivos", columnDefinition="BIT")
        private boolean cambiar_archivos;
        @Column(name = "cambiar_datos", columnDefinition="BIT")
        private boolean cambiar_datos;

        public TipoConcVirtInt(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoPasoQueja getTipopasoqueja() {
        return tipopasoqueja;
    }

    public void setTipopasoqueja(TipoPasoQueja tipopasoqueja) {
        this.tipopasoqueja = tipopasoqueja;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta_con() {
        return respuesta_con;
    }

    public void setRespuesta_con(String respuesta_con) {
        this.respuesta_con = respuesta_con;
    }

    public Integer getId_queja() {
        return id_queja;
    }

    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isCambiar_archivos() {
        return cambiar_archivos;
    }

    public void setCambiar_archivos(boolean cambiar_archivos) {
        this.cambiar_archivos = cambiar_archivos;
    }

    public boolean isCambiar_datos() {
        return cambiar_datos;
    }

    public void setCambiar_datos(boolean cambiar_datos) {
        this.cambiar_datos = cambiar_datos;
    }
        
        
        
    @Override
    public String toString() {
            JsonObject temp = new JsonObject();
            CheckNull checknull = new CheckNull();
            temp.addProperty("id",this.id);
            temp.addProperty("fecha",checknull.cknull(this.fecha));
            temp.addProperty("pregunta",checknull.cknull(this.pregunta));
            temp.addProperty("respuesta_con",checknull.cknull(this.respuesta_con));
            temp.addProperty("id_queja",checknull.cknull(this.id_queja));
            temp.addProperty("estado",checknull.cknull(this.estado));
            temp.addProperty("cambiar_archivos",this.cambiar_archivos);
            temp.addProperty("cambiar_datos",this.cambiar_datos);
            return temp.toString()+",";
    }
            
}
