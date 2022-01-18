package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
/**
 * The persistent class for the diaco_expiracionqueja database view.
 * 
 */
@Entity
@Table(name="diaco_expiracionqueja")
@NamedQuery(name="TipoExpiracion.findAll", query="SELECT t FROM TipoExpiracion t where t.id in (1,2,3,4,5,6,7,8,9)")
public class TipoExpiracion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
        private Integer id_flujo;
	private String nombre;
        private Integer vence_en;
        private Integer actualizado_por;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="actualizado")
        private Date actualizado;

    public TipoExpiracion(){}    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_flujo() {
        return id_flujo;
    }

    public void setId_flujo(Integer id_flujo) {
        this.id_flujo = id_flujo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getVence_en() {
        return vence_en;
    }

    public void setVence_en(Integer vence_en) {
        this.vence_en = vence_en;
    }

    public Integer getActualizado_por() {
        return actualizado_por;
    }

    public void setActualizado_por(Integer actualizado_por) {
        this.actualizado_por = actualizado_por;
    }

    public Date getActualizado() {
        return actualizado;
    }

    public void setActualizado(Date actualizado) {
        this.actualizado = actualizado;
    }
    
        @Override
    public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id",this.id);
            temp.addProperty("nombre",this.nombre);
            return temp.toString()+",";
    }

    
}
