package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
/**
 * The persistent class for the diaco_puesto database view.
 * 
 */
@Entity
@Table(name="diaco_puesto")
@NamedQuery(name="TipoPuesto.findAll", query="SELECT t FROM TipoPuesto t where t.estado = 1")
public class TipoPuesto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private String nombre;
        private Integer estado;
        private Integer modificado_por;
        @Temporal(TemporalType.TIMESTAMP)
        private Date modificado;

    public Integer getModificado_por() {
        return modificado_por;
    }

    public void setModificado_por(Integer modificado_por) {
        this.modificado_por = modificado_por;
    }

    public Date getModificado() {
        return modificado;
    }

    public void setModificado(Date modificado) {
        this.modificado = modificado;
    }
      
        
        
    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id",this.id);
            temp.addProperty("nombre",this.nombre);
            return temp.toString()+",";
    }

    
}
