package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
/**
 * The persistent class for the diaco_mail_server database view.
 * 
 */
@Entity
@Table(name="diaco_mail_server")
@NamedQuery(name="TipoMailServer.findAll", query="SELECT t FROM TipoMailServer t where t.estado = 'A'")
public class TipoMailServer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private String estado;
        private String host;
        private String usuario;
        private String password;
        private Integer puerto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPuerto() {
        return puerto;
    }

    public void setPuerto(Integer puerto) {
        this.puerto = puerto;
    }

        @Override
    public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id",this.id);
            return temp.toString()+",";
    }

    
}
