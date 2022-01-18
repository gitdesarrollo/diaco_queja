package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
/**
 * The persistent class for the diaco_motivo_poa database view.
 * 
 */
@Entity
@Table(name="diaco_motivo_poa")
@NamedQuery(name="TipoMotivoPOA.findAll", query="SELECT t FROM TipoMotivoPOA t where t.estado = 'A'")
public class TipoMotivoPOA implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id_motivo_queja_finalizada_poa;
	private String descripcion;
        private String estado;

        TipoMotivoPOA(){
        }

    public Integer getId_motivo_queja_finalizada_poa() {
        return id_motivo_queja_finalizada_poa;
    }

    public void setId_motivo_queja_finalizada_poa(Integer id_motivo_queja_finalizada_poa) {
        this.id_motivo_queja_finalizada_poa = id_motivo_queja_finalizada_poa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

        
        
        @Override
    public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_motivo_queja_finalizada_poa",this.id_motivo_queja_finalizada_poa);
            temp.addProperty("descripcion",this.descripcion);
            return temp.toString()+",";
    }

    
}
