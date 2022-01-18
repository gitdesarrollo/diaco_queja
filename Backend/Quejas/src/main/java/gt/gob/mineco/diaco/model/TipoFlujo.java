package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the diaco_flujo database table.
 * 
 */
@Entity
@Table(name="diaco_flujo")
@NamedQuery(name = "TipoFlujo.findAll",query = "SELECT t FROM TipoFlujo t")
public class TipoFlujo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @Column(name="id", precision=0)
        private Integer id;
        @Column(name="flujo")
        private String flujo;
        
	public TipoFlujo() {
	}

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getFlujo() {
            return flujo;
        }

        public void setFlujo(String flujo) {
            this.flujo = flujo;
        }

        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id",this.id);
            temp.addProperty("flujo",this.flujo);
            return temp.toString()+",";
        }
}
