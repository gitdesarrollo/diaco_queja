package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
/**
 * The persistent class for the diaco_resultado_audiencia database view.
 * 
 */
@Entity
@Table(name="diaco_resultado_audiencia")
@NamedQuery(name="TipoResultadoAudiencia.findAll", query="SELECT t FROM TipoResultadoAudiencia t where t.es_primera_seg_audiencia = :no_audiencia")
public class TipoResultadoAudiencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id_resultado_audiencia;
	private String descripcion;
        private String es_primera_seg_audiencia;

        TipoResultadoAudiencia(){
        }

    public Integer getId_resultado_audiencia() {
        return id_resultado_audiencia;
    }

    public void setId_resultado_audiencia(Integer id_resultado_audiencia) {
        this.id_resultado_audiencia = id_resultado_audiencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEs_primera_seg_audiencia() {
        return es_primera_seg_audiencia;
    }

    public void setEs_primera_seg_audiencia(String es_primera_seg_audiencia) {
        this.es_primera_seg_audiencia = es_primera_seg_audiencia;
    }

        @Override
    public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_resultado_audiencia",this.id_resultado_audiencia);
            temp.addProperty("descripcion",this.descripcion);
            temp.addProperty("es_primera_seg_audiencia",this.es_primera_seg_audiencia);
            return temp.toString()+",";
    }

    
}
