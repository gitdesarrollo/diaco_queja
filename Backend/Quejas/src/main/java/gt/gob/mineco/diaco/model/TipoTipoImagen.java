package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * The persistent class for the diaco_tipo_imagen database table.
 * 
 */
@Entity
@Table(name="diaco_tipo_imagen")
@NamedQuery(name="TipoTipoImagen.findAll", query="SELECT t FROM TipoTipoImagen t WHERE t.habilitado = 'A'")
public class TipoTipoImagen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tipo_imagen")
        private Integer id_tipo_imagen;        
	@Column(name="descripcion_imagen")
	private String descripcion_imagen;
        @Column(name="habilitado")
	private String habilitado;

        public TipoTipoImagen() {
	}

    public Integer getId_tipo_imagen() {
        return id_tipo_imagen;
    }

    public void setId_tipo_imagen(Integer id_tipo_imagen) {
        this.id_tipo_imagen = id_tipo_imagen;
    }

    public String getDescripcion_imagen() {
        return descripcion_imagen;
    }

    public void setDescripcion_imagen(String descripcion_imagen) {
        this.descripcion_imagen = descripcion_imagen;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }
        
            @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_tipo_imagen",this.id_tipo_imagen);
            temp.addProperty("descripcion_imagen",this.descripcion_imagen);            
            return temp.toString()+",";
        }
           
        public JsonObject toJsonelement() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_tipo_imagen",this.id_tipo_imagen);
            temp.addProperty("descripcion_imagen",this.descripcion_imagen);
            return temp;
        }
        
}
