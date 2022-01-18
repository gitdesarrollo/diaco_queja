package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import gt.gob.mineco.diaco.util.CheckNull;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the diaco_portal_links database table.
 * 
 */
@Entity
@Table(name="diaco_portal_links")
@NamedQuery(
        name = "TipoPortalLinks.findAll",
        query = "SELECT t FROM TipoPortalLinks t")

public class TipoPortalLinks implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @Column(name="id", precision=0)
        private Integer id;
        @Column(name="nombre")
        private String nombre;
        @Column(name="link")
        private String link;
        @Column(name="activo")
        private String activo;
	
	public TipoPortalLinks() {
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

        
        
        @Override
        public String toString() {
            CheckNull checknull = new CheckNull();
            JsonObject temp = new JsonObject();
            temp.addProperty("id",this.id);
            temp.addProperty("nombre",checknull.cknull(this.nombre));
            temp.addProperty("link",checknull.cknull(this.link));
            temp.addProperty("activo",checknull.cknull(this.activo));
            return temp.toString()+",";
        }
   

        
}
