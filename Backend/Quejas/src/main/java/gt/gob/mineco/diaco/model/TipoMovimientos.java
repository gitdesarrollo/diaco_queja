package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import gt.gob.mineco.diaco.util.CheckNull;
import java.io.Serializable;
import javax.persistence.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * The persistent class for the diaco.dbo.diaco_movimientos database table.
 * 
 */
@Entity
@Table(name="diaco_movimientos")
@NamedQuery( name = "TipoMovimientos.findAll", query = "SELECT t FROM TipoMovimientos t where t.id_queja = :id_queja")
public class TipoMovimientos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", precision=0) 
        private Integer id;
        @Column(name="observaciones")
        private String observaciones;
        @Column(name="id_queja")
        private Integer id_queja;
        
        public TipoMovimientos() {
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getId_queja() {
        return id_queja;
    }

    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
    }
        
        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            CheckNull checknull = new CheckNull();
            temp.addProperty("id",this.id);
            return temp.toString()+",";
        }
        
}
