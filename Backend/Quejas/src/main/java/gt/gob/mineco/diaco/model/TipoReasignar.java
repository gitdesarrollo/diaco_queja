package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * The persistent class for the diaco_reasignar database table.
 * 
 */
@Entity
@Table(name="diaco_reasignar")
@NamedQueries({
    @NamedQuery(
        name = "TipoReasignar.findAllxIdQueja",
        query = "SELECT t FROM TipoReasignar t where t.id_queja = :idqueja")
})
public class TipoReasignar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", precision=0) 
        private double id;
        @Column(name="id_queja")
        private Integer id_queja;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_operacion")        
        private Date fecha_operacion;
        @Column(name="usuario_reasignar")
        private Integer usuario_reasignar;
        @Column(name="usuario_operacion")        
	private Integer usuario_operacion;
	
        public TipoReasignar() {
	}

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public Integer getId_queja() {
        return id_queja;
    }

    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
    }

    public Date getFecha_operacion() {
        return fecha_operacion;
    }

    public void setFecha_operacion(Date fecha_operacion) {
        this.fecha_operacion = fecha_operacion;
    }

    public Integer getUsuario_reasignar() {
        return usuario_reasignar;
    }

    public void setUsuario_reasignar(Integer usuario_reasignar) {
        this.usuario_reasignar = usuario_reasignar;
    }

    public Integer getUsuario_operacion() {
        return usuario_operacion;
    }

    public void setUsuario_operacion(Integer usuario_operacion) {
        this.usuario_operacion = usuario_operacion;
    }


        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id",this.id);
            temp.addProperty("id_queja",this.id_queja);
            return temp.toString()+",";
        }
        
}
