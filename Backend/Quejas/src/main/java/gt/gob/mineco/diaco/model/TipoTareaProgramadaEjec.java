package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * The persistent class for the diaco_tarea_programada_ejecucion database table.
 * 
 */
@Entity
@Table(name="diaco_tarea_programada_ejecucion")
@NamedQuery(name = "TipoTareaProgramadaEjec.findAll",query = "SELECT t FROM TipoTareaProgramadaEjec t where t.id = :id")
public class TipoTareaProgramadaEjec implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", precision=0) 
        private Integer id;
        @Column(name="id_proceso")
        private Integer id_proceso;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="ejecucion")        
        private Date ejecucion;
        @Column(name="resultado")        
	private String resultado;
	
        public TipoTareaProgramadaEjec() {
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_proceso() {
        return id_proceso;
    }

    public void setId_proceso(Integer id_proceso) {
        this.id_proceso = id_proceso;
    }

    public Date getEjecucion() {
        return ejecucion;
    }

    public void setEjecucion(Date ejecucion) {
        this.ejecucion = ejecucion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
        
        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id",this.id);
            temp.addProperty("id_proceso",this.id_proceso);
            temp.addProperty("ejecucion",this.ejecucion.toString());
            temp.addProperty("resultado",this.resultado);
            return temp.toString()+",";
        }
        
}
