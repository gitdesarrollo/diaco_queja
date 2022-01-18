package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the diaco_email_fuente database view.
 * 
 */
@Entity
@Table(name="diaco_email_fuente")
@NamedQuery(name="TipoEmailFuente.findAll", query="SELECT t FROM TipoEmailFuente t")
public class TipoEmailFuente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id_fuente;
        private Integer id_flujo;
	private String nombre;
        private String de;
        private String subject;
        private String direccion;
        private String root;

    public TipoEmailFuente(){}    
        
    public Integer getId_fuente() {
        return id_fuente;
    }

    public void setId_fuente(Integer id_fuente) {
        this.id_fuente = id_fuente;
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

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }
    
        @Override
    public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_fuente",this.id_fuente);
            temp.addProperty("nombre",this.nombre);
            return temp.toString()+",";
    }

    
}
