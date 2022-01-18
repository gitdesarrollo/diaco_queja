package gt.gob.mineco.diaco.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import gt.gob.mineco.diaco.util.CheckNull;
import gt.gob.mineco.diaco.model.TipoReg_CedCitacionCon;
import gt.gob.mineco.diaco.model.TipoReg_CedCitacionPro;
import gt.gob.mineco.diaco.model.TipoReg_CedNotificacionCon;
import gt.gob.mineco.diaco.model.TipoReg_CedNotificacionPro;
import gt.gob.mineco.diaco.model.TipoAudiencia;
/**
 * The persistent class for the diaco_audiencia database table.
 * 
 */
@Entity
//@ReadOnly
@Table(name="diaco_audiencia")
@NamedQuery(name = "TipoAudienciaRegistros.findAll",query = "SELECT t FROM TipoAudienciaRegistros t where t.id_audiencia = :id_audiencia") 
public class TipoAudienciaRegistros implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_audiencia", precision=0)
        private Integer id_audiencia;
        @JoinColumn(name = "id_audiencia", referencedColumnName = "id_audiencia",updatable=false, insertable=false)
        @OneToOne
        private TipoReg_CedCitacionCon tipoReg_CedCitacionCon;
        @JoinColumn(name = "id_audiencia", referencedColumnName = "id_audiencia",updatable=false, insertable=false)
        @OneToOne        
        private TipoReg_CedCitacionPro tipoReg_CedCitacionPro; 
        @JoinColumn(name = "id_audiencia", referencedColumnName = "id_audiencia",updatable=false, insertable=false)
        @OneToOne
        private TipoReg_CedNotificacionCon tipoReg_CedNotificacionCon;
        @JoinColumn(name = "id_audiencia", referencedColumnName = "id_audiencia",updatable=false, insertable=false)
        @OneToOne
        private TipoReg_CedNotificacionPro tipoReg_CedNotificacionPro;        
             
	public TipoAudienciaRegistros() {
	}

    public Integer getId_audiencia() {
        return id_audiencia;
    }

    public void setId_audiencia(Integer id_audiencia) {
        this.id_audiencia = id_audiencia;
    }

    public TipoReg_CedCitacionCon getTipoReg_CedCitacionCon() {
        return tipoReg_CedCitacionCon;
    }

    public void setTipoReg_CedCitacionCon(TipoReg_CedCitacionCon tipoReg_CedCitacionCon) {
        this.tipoReg_CedCitacionCon = tipoReg_CedCitacionCon;
    }

    public TipoReg_CedCitacionPro getTipoReg_CedCitacionPro() {
        return tipoReg_CedCitacionPro;
    }

    public void setTipoReg_CedCitacionPro(TipoReg_CedCitacionPro tipoReg_CedCitacionPro) {
        this.tipoReg_CedCitacionPro = tipoReg_CedCitacionPro;
    }

    public TipoReg_CedNotificacionCon getTipoReg_CedNotificacionCon() {
        return tipoReg_CedNotificacionCon;
    }

    public void setTipoReg_CedNotificacionCon(TipoReg_CedNotificacionCon tipoReg_CedNotificacionCon) {
        this.tipoReg_CedNotificacionCon = tipoReg_CedNotificacionCon;
    }

    public TipoReg_CedNotificacionPro getTipoReg_CedNotificacionPro() {
        return tipoReg_CedNotificacionPro;
    }

    public void setTipoReg_CedNotificacionPro(TipoReg_CedNotificacionPro tipoReg_CedNotificacionPro) {
        this.tipoReg_CedNotificacionPro = tipoReg_CedNotificacionPro;
    }
        
        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_audiencia",this.id_audiencia);
            if(tipoReg_CedCitacionCon!=null){
                temp.addProperty("cedula_citacion_con_id",tipoReg_CedCitacionCon.getId());
                temp.addProperty("cedula_citacion_con_codigo",tipoReg_CedCitacionCon.getCodigo());
            }
            if(tipoReg_CedCitacionPro!=null){
                temp.addProperty("cedula_citacion_pro_id",tipoReg_CedCitacionPro.getId());
                temp.addProperty("cedula_citacion_pro_codigo",tipoReg_CedCitacionPro.getCodigo());
            }
            if(tipoReg_CedNotificacionCon!=null){
                temp.addProperty("cedula_notificacion_con_id",tipoReg_CedNotificacionCon.getId());
                temp.addProperty("cedula_notificacion_con_codigo",tipoReg_CedNotificacionCon.getCodigo());
            }
            if(tipoReg_CedNotificacionPro!=null){
                temp.addProperty("cedula_notificacion_pro_id",tipoReg_CedNotificacionPro.getId());
                temp.addProperty("cedula_notificacion_pro_codigo",tipoReg_CedNotificacionPro.getCodigo());
            }

            return temp.toString()+",";
        }
                
}
