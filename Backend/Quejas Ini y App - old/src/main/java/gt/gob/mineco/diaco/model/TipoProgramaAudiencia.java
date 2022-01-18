package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import gt.gob.mineco.diaco.model.TipoAudiencia;
import gt.gob.mineco.diaco.util.CheckNull;

/**
 * The persistent class for the diaco_programa_audiencia database table.
 * 
 */
@Entity
@Table(name="diaco_programa_audiencia")
@NamedQueries({
    @NamedQuery(
        name = "TipoProgramaAudiencia.findxIdQuejaAct",
        query = "SELECT t FROM TipoProgramaAudiencia t inner join t.tipoAudiencia.tipopasoqueja og "
                + "where og.id_queja = :idqueja and t.tipoAudiencia.es_primera_seg_audiencia = :no_audiencia order by t.id_progra_aud desc"),
})

public class TipoProgramaAudiencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_progra_aud", precision=0)
        private Integer id_progra_aud;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_programada")
        private Date fecha_programada;
        @Column(name = "activo", columnDefinition="BIT")
        private boolean activo;
	@JoinColumn(name = "id_audiencia", referencedColumnName = "id_audiencia")
        @OneToOne
        private TipoAudiencia tipoAudiencia;        
        @Column(name="id_resultado_audiencia")
        private Integer id_resultado_audiencia;
        @Column(name="conclusion")
        private String conclusion;
        @Column(name="comentario")
        private String comentario;

	public TipoProgramaAudiencia() {
	}

    public Integer getId_resultado_audiencia() {
        return id_resultado_audiencia;
    }

    public void setId_resultado_audiencia(Integer id_resultado_audiencia) {
        this.id_resultado_audiencia = id_resultado_audiencia;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

        
        
    public TipoAudiencia getTipoAudiencia() {
        return tipoAudiencia;
    }

    public void setTipoAudiencia(TipoAudiencia tipoAudiencia) {
        this.tipoAudiencia = tipoAudiencia;
    }

    public Integer getId_progra_aud() {
        return id_progra_aud;
    }

    public void setId_progra_aud(Integer id_progra_aud) {
        this.id_progra_aud = id_progra_aud;
    }

    public Date getFecha_programada() {
        return fecha_programada;
    }

    public void setFecha_programada(Date fecha_programada) {
        this.fecha_programada = fecha_programada;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

        
        
        @Override
        public String toString() {
            CheckNull checknull = new CheckNull();
            JsonObject temp = new JsonObject();
            temp.addProperty("id_progra_aud",this.id_progra_aud);
            temp.addProperty("fecha_programada",this.fecha_programada.toString());
            temp.addProperty("activo",this.activo);            
            temp.addProperty("id_resultado_audiencia",checknull.cknull(this.id_resultado_audiencia));
            temp.addProperty("conclusion",checknull.cknull(this.conclusion));
            temp.addProperty("comentario",checknull.cknull(this.comentario));
            return temp.toString()+",";
        }
   
        public JsonObject toJsonelement() {
            CheckNull checknull = new CheckNull();
            JsonObject temp = new JsonObject();
            temp.addProperty("id_progra_aud",this.id_progra_aud);
            temp.addProperty("fecha_programada",this.fecha_programada.toString());
            temp.addProperty("activo",this.activo);
            temp.addProperty("id_resultado_audiencia",checknull.cknull(this.id_resultado_audiencia));
            temp.addProperty("conclusion",checknull.cknull(this.conclusion));
            temp.addProperty("comentario",checknull.cknull(this.comentario));
            return temp;
        }

        
}
