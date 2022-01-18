package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import gt.gob.mineco.diaco.model.TipoReg_ComPerm;
/**
 * The persistent class for the email database table.
 * 
 */
@Entity
@Table(name="diaco_queja_registro")
@NamedQuery(name = "TipoQuejaRegistro.findbyparams",query = "SELECT t FROM TipoQuejaRegistro t where t.id_queja = :id_queja and t.id_tipo_registro = :id_tipo_registro")
public class TipoQuejaRegistro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_queja_registro", precision=0)
        private Integer id_queja_registro;
        @Column(name="id_queja")
        private Integer id_queja;
        /*@Column(name="id_registro")
        private Integer id_registro;*/
        @Column(name="id_tipo_registro")
        private Integer id_tipo_registro;
        @JoinColumn(name = "id_registro", referencedColumnName = "id")
        @OneToOne
        private TipoReg_ComPerm tipoReg_ComPerm;  
	
	public TipoQuejaRegistro() {
	}

    public Integer getId_queja_registro() {
        return id_queja_registro;
    }

    public void setId_queja_registro(Integer id_queja_registro) {
        this.id_queja_registro = id_queja_registro;
    }

    public Integer getId_queja() {
        return id_queja;
    }

    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
    }

    public Integer getId_tipo_registro() {
        return id_tipo_registro;
    }

    public void setId_tipo_registro(Integer id_tipo_registro) {
        this.id_tipo_registro = id_tipo_registro;
    }

    public TipoReg_ComPerm getTipoReg_ComPerm() {
        return tipoReg_ComPerm;
    }

    public void setTipoReg_ComPerm(TipoReg_ComPerm tipoReg_ComPerm) {
        this.tipoReg_ComPerm = tipoReg_ComPerm;
    }
    
        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_queja_registro",this.id_queja_registro);
            temp.addProperty("id_queja",this.id_queja);
            temp.addProperty("id_tipo_registro",this.id_tipo_registro);
            temp.addProperty("id_registro",this.tipoReg_ComPerm.getId());
            return temp.toString()+",";
        }
   
        public JsonObject toJsonelement() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_queja_registro",this.id_queja_registro);
            temp.addProperty("id_queja",this.id_queja);
            temp.addProperty("id_tipo_registro",this.id_tipo_registro);
            temp.addProperty("id_registro",this.tipoReg_ComPerm.getId());
            return temp;
        }

        
}
