package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the diaco_qr_path database table.
 * 
 */
@Entity
@Table(name="diaco_qr_path")
@NamedQuery(
    name = "TipoQRpath.findAll",
    query = "SELECT t FROM TipoQRpath t")
public class TipoQRpath implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id", precision=0)
        private Integer id;
        @Column(name="path")
        private String path;
	
	public TipoQRpath() {
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

        
        @Override
        public String toString() {
            //String temp="{¬id_mail¬:" + this.id_mail + ", ¬correo_electronico¬:¬" + this.correo_electronico+"¬},";
            //return temp;//.replace('`', '"');
            JsonObject temp = new JsonObject();
            temp.addProperty("id",this.id);
            temp.addProperty("path",this.path);
            return temp.toString()+",";
        }
           
}
