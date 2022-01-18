package gt.gob.mineco.diaco.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import gt.gob.mineco.diaco.util.CheckNull;
/**
 * The persistent class for the diaco_cambio_area_exp_detalle database table.
 * 
 */
@Entity
@Table(name="diaco_cambio_area_exp_detalle")
@NamedQueries({
    @NamedQuery(
        name = "TipoMovimExpDetalle.findall",
        query = "SELECT t FROM TipoMovimExpDetalle t where t.tipoMovimExp.id_cambio_exp = :id") 
})

public class TipoMovimExpDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", precision=0)
        private Integer id;
        @Column(name="id_queja")
        private Integer id_queja;
        @JoinColumn(name = "id_cambio_exp", referencedColumnName = "id_cambio_exp")
        @OneToOne
        private TipoMovimExp tipoMovimExp;        
        @JoinColumn(name = "id_queja", referencedColumnName = "id_queja",updatable=false, insertable=false)
        @OneToOne
        private TipoQueja tipoQueja;        
        @Column(name="observaciones")
        private String observaciones;   
     
	public TipoMovimExpDetalle() {
	}

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public TipoMovimExp getTipoMovimExp() {
        return tipoMovimExp;
    }

    public void setTipoMovimExp(TipoMovimExp tipoMovimExp) {
        this.tipoMovimExp = tipoMovimExp;
    }

    public TipoQueja getTipoQueja() {
        return tipoQueja;
    }

    public void setTipoQueja(TipoQueja tipoQueja) {
        this.tipoQueja = tipoQueja;
    }   

        @Override
        public String toString() {
            CheckNull checknull = new CheckNull();
            JsonObject temp = new JsonObject();
            temp.addProperty("id",this.id);
            temp.addProperty("id_queja",checknull.cknull(this.id_queja));            
            temp.addProperty("numeroqueja",checknull.cknull(this.tipoQueja.getQuejaNumero()));            

            return temp.toString()+",";
        }
                
}
