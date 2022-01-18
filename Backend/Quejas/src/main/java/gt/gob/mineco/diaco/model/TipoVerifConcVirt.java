package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import gt.gob.mineco.diaco.util.CheckNull;
/**
 * The persistent class for the diaco_verif_conciliacion_previa database table.
 * 
 */
@Entity
@Table(name="diaco_verif_conciliacion_previa")
//@NamedQuery(name="TipoVerifConcVirt.findAll", query="SELECT t FROM TipoVerifConcVirt t inner join t.tipopasoqueja og where og.id_queja = :idqueja and og.id_tipo_registro = t.id_tipo_registro")
@NamedQuery(name="TipoVerifConcVirt.findAll", query="SELECT t FROM TipoVerifConcVirt t inner join t.tipopasoqueja og where og.id_queja = :idqueja")
//inner join t.tipopasoqueja og where og.id_queja_paso=:usr
public class TipoVerifConcVirt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_verif_conciliacion_previa", precision=0) 
        private Integer id_verif_conciliacion_previa;
        @JoinColumn(name = "id_queja_paso", referencedColumnName = "id_queja_paso")
        @OneToOne
        private TipoPasoQueja tipopasoqueja;        
        @Column(name="queja_resuelta")
        private String queja_resuelta;
        @Column(name="razon_prop_provee_rechazada")        
	private String razon_prop_provee_rechazada;
        @Column(name="id_tipo_registro")
        private Integer id_tipo_registro;
	@Column(name="notas")        
	private String notas;
        @Column(name="resolucion")
        private Integer resolucion;
        @Column(name="respuesta_diaco")
        private Integer respuesta_diaco;
	@Column(name="respuesta_con")        
	private String respuesta_con;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_respuesta_con")
        private Date fecha_respuesta_con;
        @Column(name="respuesta_veces")
        private Integer respuesta_veces;
        
	public TipoVerifConcVirt() {
	}

    public Integer getRespuesta_veces() {
        return respuesta_veces;
    }

    public void setRespuesta_veces(Integer respuesta_veces) {
        this.respuesta_veces = respuesta_veces;
    }

    public Date getFecha_respuesta_con() {
        return fecha_respuesta_con;
    }

    public void setFecha_respuesta_con(Date fecha_respuesta_con) {
        this.fecha_respuesta_con = fecha_respuesta_con;
    }
        
    public String getRespuesta_con() {
        return respuesta_con;
    }

    public void setRespuesta_con(String respuesta_con) {
        this.respuesta_con = respuesta_con;
    }
        
    public Integer getId_verif_conciliacion_previa() {
        return id_verif_conciliacion_previa;
    }

    public void setId_verif_conciliacion_previa(Integer id_verif_conciliacion_previa) {
        this.id_verif_conciliacion_previa = id_verif_conciliacion_previa;
    }

    public TipoPasoQueja getTipopasoqueja() {
        return tipopasoqueja;
    }

    public void setTipopasoqueja(TipoPasoQueja tipopasoqueja) {
        this.tipopasoqueja = tipopasoqueja;
    }

    public String getQueja_resuelta() {
        return queja_resuelta;
    }

    public void setQueja_resuelta(String queja_resuelta) {
        this.queja_resuelta = queja_resuelta;
    }

    public String getRazon_prop_provee_rechazada() {
        return razon_prop_provee_rechazada;
    }

    public void setRazon_prop_provee_rechazada(String razon_prop_provee_rechazada) {
        this.razon_prop_provee_rechazada = razon_prop_provee_rechazada;
    }

    public Integer getId_tipo_registro() {
        return id_tipo_registro;
    }

    public void setId_tipo_registro(Integer id_tipo_registro) {
        this.id_tipo_registro = id_tipo_registro;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Integer getResolucion() {
        return resolucion;
    }

    public void setResolucion(Integer resolucion) {
        this.resolucion = resolucion;
    }

    public Integer getRespuesta_diaco() {
        return respuesta_diaco;
    }

    public void setRespuesta_diaco(Integer respuesta_diaco) {
        this.respuesta_diaco = respuesta_diaco;
    }
    
    @Override
    public String toString() {
            JsonObject temp = new JsonObject();
            CheckNull checknull = new CheckNull();
            temp.addProperty("id_verif_conciliacion_previa",this.id_verif_conciliacion_previa);
            temp.addProperty("queja_resuelta",checknull.cknull(this.queja_resuelta));
            temp.addProperty("razon_prop_provee_rechazada",checknull.cknull(this.razon_prop_provee_rechazada));
            temp.addProperty("id_tipo_registro",checknull.cknull(this.id_tipo_registro));
            temp.addProperty("notas",checknull.cknull(this.notas));
            temp.addProperty("resolucion",checknull.cknull(this.resolucion));
            temp.addProperty("respuesta_diaco",checknull.cknull(this.respuesta_diaco));
            temp.addProperty("respuesta_con",checknull.cknull(this.respuesta_con));
            return temp.toString()+",";
    }
            
}
