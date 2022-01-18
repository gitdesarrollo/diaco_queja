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
@NamedQuery(name = "TipoAudienciaRegistrosJuridico.findAll",query = "SELECT t FROM TipoAudienciaRegistrosJuridico t where t.id_audiencia = :id_audiencia") 
public class TipoAudienciaRegistrosJuridico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_audiencia", precision=0)
        private Integer id_audiencia;
        @JoinColumn(name = "id_audiencia", referencedColumnName = "id_audiencia",updatable=false, insertable=false)
        @OneToOne
        private TipoReg_CedulaNotificacionCitacionJuridico tipoReg_CedulaNotificacionCitacionJuridico;
        @JoinColumn(name = "id_audiencia", referencedColumnName = "id_audiencia",updatable=false, insertable=false)
        @OneToOne        
        private TipoReg_CedulaNotificacionResJuridico tipoReg_CedulaNotificacionResJuridico; 
        @JoinColumn(name = "id_audiencia", referencedColumnName = "id_audiencia",updatable=false, insertable=false)
        @OneToOne
        private TipoReg_CedulaNotificacionResCorreo tipoReg_CedulaNotificacionResCorreo;
        @JoinColumn(name = "id_audiencia", referencedColumnName = "id_audiencia",updatable=false, insertable=false)
        @OneToOne
        private TipoReg_RegCedulaNotificacionCitCorreo tipoReg_RegCedulaNotificacionCitCorreo;        
             
	public TipoAudienciaRegistrosJuridico() {
	}

    public Integer getId_audiencia() {
        return id_audiencia;
    }

    public void setId_audiencia(Integer id_audiencia) {
        this.id_audiencia = id_audiencia;
    }

        
        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_audiencia",this.id_audiencia);
            if(tipoReg_CedulaNotificacionCitacionJuridico!=null){
                temp.addProperty("cedula_notificacion_citacion_jur_id",tipoReg_CedulaNotificacionCitacionJuridico.getId());
                temp.addProperty("cedula_notificacion_citacion_jur_codigo",tipoReg_CedulaNotificacionCitacionJuridico.getCodigo());
            }
            if(tipoReg_CedulaNotificacionResJuridico!=null){
                temp.addProperty("cedula_notificacion_res_jur_id",tipoReg_CedulaNotificacionResJuridico.getId());
                temp.addProperty("cedula_notificacion_res_jur_codigo",tipoReg_CedulaNotificacionResJuridico.getCodigo());
            }
            if(tipoReg_CedulaNotificacionResCorreo!=null){
                temp.addProperty("cedula_notificacion_re_correo_id",tipoReg_CedulaNotificacionResCorreo.getId());
                temp.addProperty("cedula_notificacion_re_correo_codigo",tipoReg_CedulaNotificacionResCorreo.getCodigo());
            }
            if(tipoReg_RegCedulaNotificacionCitCorreo!=null){
                temp.addProperty("cedula_notificacion_cit_correo_id",tipoReg_RegCedulaNotificacionCitCorreo.getId());
                temp.addProperty("cedula_notificacion_cit_correo_codigo",tipoReg_RegCedulaNotificacionCitCorreo.getCodigo());
            }

            return temp.toString()+",";
        }

    /**
     * @return the tipoReg_CedulaNotificacionCitacionJuridico
     */
    public TipoReg_CedulaNotificacionCitacionJuridico getTipoReg_CedulaNotificacionCitacionJuridico() {
        return tipoReg_CedulaNotificacionCitacionJuridico;
    }

    /**
     * @param tipoReg_CedulaNotificacionCitacionJuridico the tipoReg_CedulaNotificacionCitacionJuridico to set
     */
    public void setTipoReg_CedulaNotificacionCitacionJuridico(TipoReg_CedulaNotificacionCitacionJuridico tipoReg_CedulaNotificacionCitacionJuridico) {
        this.tipoReg_CedulaNotificacionCitacionJuridico = tipoReg_CedulaNotificacionCitacionJuridico;
    }

    /**
     * @return the tipoReg_CedulaNotificacionResJuridico
     */
    public TipoReg_CedulaNotificacionResJuridico getTipoReg_CedulaNotificacionResJuridico() {
        return tipoReg_CedulaNotificacionResJuridico;
    }

    /**
     * @param tipoReg_CedulaNotificacionResJuridico the tipoReg_CedulaNotificacionResJuridico to set
     */
    public void setTipoReg_CedulaNotificacionResJuridico(TipoReg_CedulaNotificacionResJuridico tipoReg_CedulaNotificacionResJuridico) {
        this.tipoReg_CedulaNotificacionResJuridico = tipoReg_CedulaNotificacionResJuridico;
    }

    /**
     * @return the tipoReg_CedulaNotificacionResCorreo
     */
    public TipoReg_CedulaNotificacionResCorreo getTipoReg_CedulaNotificacionResCorreo() {
        return tipoReg_CedulaNotificacionResCorreo;
    }

    /**
     * @param tipoReg_CedulaNotificacionResCorreo the tipoReg_CedulaNotificacionResCorreo to set
     */
    public void setTipoReg_CedulaNotificacionResCorreo(TipoReg_CedulaNotificacionResCorreo tipoReg_CedulaNotificacionResCorreo) {
        this.tipoReg_CedulaNotificacionResCorreo = tipoReg_CedulaNotificacionResCorreo;
    }

    /**
     * @return the tipoReg_RegCedulaNotificacionCitCorreo
     */
    public TipoReg_RegCedulaNotificacionCitCorreo getTipoReg_RegCedulaNotificacionCitCorreo() {
        return tipoReg_RegCedulaNotificacionCitCorreo;
    }

    /**
     * @param tipoReg_RegCedulaNotificacionCitCorreo the tipoReg_RegCedulaNotificacionCitCorreo to set
     */
    public void setTipoReg_RegCedulaNotificacionCitCorreo(TipoReg_RegCedulaNotificacionCitCorreo tipoReg_RegCedulaNotificacionCitCorreo) {
        this.tipoReg_RegCedulaNotificacionCitCorreo = tipoReg_RegCedulaNotificacionCitCorreo;
    }
                
}
