package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the diaco_verifd_interaccion database table.
 * 
 */
@Entity
@Table(name="diaco_verifd_interaccion")
@NamedQuery(name = "TipoVerifDatosInt.findAll", query = "SELECT t FROM TipoVerifDatosInt t where t.id_queja = :id_queja order by t.fecha")
public class TipoVerifDatosInt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", precision=0)
        private Integer id;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha")
        private Date fecha;
        @Column(name="id_queja")
        private Integer id_queja;
        @JoinColumn(name = "id_queja_paso", referencedColumnName = "id_queja_paso")
        @OneToOne
        private TipoPasoQueja tipopasoqueja;        

	public TipoVerifDatosInt() {
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getId_queja() {
        return id_queja;
    }

    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
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
            temp.addProperty("id",this.id);
            temp.addProperty("fecha",this.fecha.toString());
            temp.addProperty("id_queja",this.id_queja);
            return temp.toString()+",";
        }
           
}
