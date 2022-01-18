package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import gt.gob.mineco.diaco.util.CheckNull;
import java.io.Serializable;
import javax.persistence.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * The persistent class for the diaco.dbo.diaco_vyv_asignacola database table.
 * 
 */
@Entity
@Table(name="diaco_vyv_asignacola")
@NamedQuery( name = "TipoVyvAsignaCola.findAll", query = "SELECT t FROM TipoVyvAsignaCola t where t.id_queja = :id_queja")
public class TipoVyvAsignaCola implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", precision=0) 
        private Integer id;
        @Column(name="id_queja")
        private Integer id_queja;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_modifico")        
        private Date fecha_modifico;
        @Column(name="id_tipo_cola")
        private Integer id_tipo_cola;
	@Column(name="id_tipo_registro")
        private Integer id_tipo_registro;
        @Column(name="usuario_modifico")
        private Integer usuario_modifico;
        @JoinColumn(name = "id_queja_paso", referencedColumnName = "id_queja_paso")
        @OneToOne
        private TipoPasoQueja tipopasoqueja;   
        
        public TipoVyvAsignaCola() {
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_queja() {
        return id_queja;
    }

    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
    }

    public Date getFecha_modifico() {
        return fecha_modifico;
    }

    public void setFecha_modifico(Date fecha_modifico) {
        this.fecha_modifico = fecha_modifico;
    }

    public Integer getId_tipo_cola() {
        return id_tipo_cola;
    }

    public void setId_tipo_cola(Integer id_tipo_cola) {
        this.id_tipo_cola = id_tipo_cola;
    }

    public Integer getId_tipo_registro() {
        return id_tipo_registro;
    }

    public void setId_tipo_registro(Integer id_tipo_registro) {
        this.id_tipo_registro = id_tipo_registro;
    }

    public Integer getUsuario_modifico() {
        return usuario_modifico;
    }

    public void setUsuario_modifico(Integer usuario_modifico) {
        this.usuario_modifico = usuario_modifico;
    }

    public TipoPasoQueja getTipopasoqueja() {
        return tipopasoqueja;
    }

    public void setTipopasoqueja(TipoPasoQueja tipopasoqueja) {
        this.tipopasoqueja = tipopasoqueja;
    }
        
        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            CheckNull checknull = new CheckNull();
            temp.addProperty("id",this.id);
            temp.addProperty("id_queja",checknull.cknull(this.id_queja));
            temp.addProperty("id_tipo_cola",checknull.cknull(this.id_tipo_cola));
            return temp.toString()+",";
        }
        
}
