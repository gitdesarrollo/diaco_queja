package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the diaco_plantillas database table.
 * 
 */
@Entity
@Table(name="diaco_plantillas")
@NamedQueries({
    @NamedQuery(
        name = "TipoPlantilla.findxID",
        query = "SELECT t FROM TipoPlantilla t where t.id = :id")
})
public class TipoPlantilla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", precision=0)
        private Integer id;
        @Column(name="filename")
        private String filename;
        @Column(name="folder")
        private String folder;
        @Column(name="id_tipo_imagen")
        private Integer id_tipo_imagen;
	
	public TipoPlantilla() {
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public Integer getId_tipo_imagen() {
        return id_tipo_imagen;
    }

    public void setId_tipo_imagen(Integer id_tipo_imagen) {
        this.id_tipo_imagen = id_tipo_imagen;
    }
        
        @Override
        public String toString() {
            //String temp="{¬id_mail¬:" + this.id_mail + ", ¬correo_electronico¬:¬" + this.correo_electronico+"¬},";
            //return temp;//.replace('`', '"');
            JsonObject temp = new JsonObject();
            temp.addProperty("id",this.id);
            temp.addProperty("filename",this.filename);
            temp.addProperty("folder",this.folder);
            return temp.toString()+",";
        }
           
}
