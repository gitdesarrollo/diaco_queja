package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
/**
 * The persistent class for the View_MainQueja database view.
 * 
 */
@Entity
@Table(name="diaco_catalogo")
@NamedQuery(name="TipoCatalogo.findAll", query="SELECT t FROM TipoCatalogo t where t.estado = 'A' and t.nombre_tabla_catalogo = :tabla")
public class TipoCatalogo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id_catalogo;
	private String descripcion_catalogo;
        private String estado;
        private String nombre_tabla_catalogo;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre_tabla_catalogo() {
        return nombre_tabla_catalogo;
    }

    public void setNombre_tabla_catalogo(String nombre_tabla_catalogo) {
        this.nombre_tabla_catalogo = nombre_tabla_catalogo;
    }
        
        

    public Integer getId_catalogo() {
        return id_catalogo;
    }

    public void setId_catalogo(Integer id_catalogo) {
        this.id_catalogo = id_catalogo;
    }

    public String getDescripcion_catalogo() {
        return descripcion_catalogo;
    }

    public void setDescripcion_catalogo(String descripcion_catalogo) {
        this.descripcion_catalogo = descripcion_catalogo;
    }
	

        @Override
    public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_catalogo",this.id_catalogo);
            temp.addProperty("descripcion_catalogo",this.descripcion_catalogo);
            return temp.toString()+",";
    }

    
}
