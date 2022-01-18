package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import com.google.gson.JsonObject;
import gt.gob.mineco.diaco.model.TipoTelefono;
import gt.gob.mineco.diaco.model.TipoEmail;
import gt.gob.mineco.diaco.model.TipoConsumidor;
import gt.gob.mineco.diaco.model.TipoEstadoQueja;
import gt.gob.mineco.diaco.model.TipoSede;
import gt.gob.mineco.diaco.model.TipoDepartamento;
import gt.gob.mineco.diaco.model.TipoMunicipio;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.JsonArray;
/**
 * The persistent class for the diaco_queja database table.
 * 
 */
@Entity
@Table(name="diaco_queja")
@NamedQueries({
@NamedQuery(name="TipoQueja_Simple.findAllActivoAtCon", query="SELECT t FROM TipoQueja_Simple t where t.id_estado_queja in (101,102,103,104,105,106) ORDER BY t.id_queja"),
@NamedQuery(name="TipoQueja_Simple.findAllVyVAtCon", query="SELECT t FROM TipoQueja_Simple t where t.id_estado_queja = 131 ORDER BY t.id_queja"),
@NamedQuery(name="TipoQueja_Simple.findAllJuridicoAtCon", query="SELECT t FROM TipoQueja_Simple t where t.id_estado_queja = 132 ORDER BY t.id_queja"),
@NamedQuery(name="TipoQueja_Simple.findAllArchivoAtCon", query="SELECT t FROM TipoQueja_Simple t where t.id_estado_queja = 133 ORDER BY t.id_queja"),
@NamedQuery(name="TipoQueja_Simple.findPendAsignacion", query="SELECT t FROM TipoQueja_Simple t where t.id_estado_queja = 129 ORDER BY t.id_queja"),
@NamedQuery(name="TipoQueja_Simple.findAll", query="SELECT t FROM TipoQueja_Simple t ORDER BY t.id_estado_queja"),
@NamedQuery(name="TipoQueja_Simple.VyVfindAllAtCon", query="SELECT t FROM TipoQueja_Simple t where t.id_estado_queja = 331 ORDER BY t.id_queja"),
@NamedQuery(name="TipoQueja_Simple.VyVfindAllJuridico", query="SELECT t FROM TipoQueja_Simple t where t.id_estado_queja = 332 ORDER BY t.id_queja"),
@NamedQuery(name="TipoQueja_Simple.VyVfindAllArchivo", query="SELECT t FROM TipoQueja_Simple t where t.id_estado_queja = 333 ORDER BY t.id_queja"),
@NamedQuery(name="TipoQueja_Simple.VyVfindPendAsignacion", query="SELECT t FROM TipoQueja_Simple t where t.id_estado_queja = 329 ORDER BY t.id_queja"),
@NamedQuery(name="TipoQueja_Simple.JurfindAllArchivo", query="SELECT t FROM TipoQueja_Simple t where t.id_estado_queja = 233 ORDER BY t.id_queja"),
@NamedQuery(name="TipoQueja_Simple.JurfindAllVerificacion", query="SELECT t FROM TipoQueja_Simple t where t.id_estado_queja = 232 ORDER BY t.id_queja"),
@NamedQuery(name="TipoQueja_Simple.JurfindAllAtCon", query="SELECT t FROM TipoQueja_Simple t where t.id_estado_queja = 231 ORDER BY t.id_queja"),
@NamedQuery(name="TipoQueja_Simple.JurfindPendAsignacion", query="SELECT t FROM TipoQueja_Simple t where t.id_estado_queja = 229 ORDER BY t.id_queja")
})
        
public class TipoQueja_Simple implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @Column(name="id_queja")
	private Integer id_queja;
        @Column(name="anio")
	private Integer anio;
        @Column(name="no_queja")
	private Integer no_queja;
        @Column(name="id_estado_queja")
        private Integer id_estado_queja;
        @Column(name="detalle_queja")
        private String detalle_queja;
        
	public TipoQueja_Simple() {
	}

        public String getQuejaNumero(){
            String retvalue="";
            if(this.anio != null && this.no_queja != null)
                retvalue=this.no_queja.toString()+"-"+this.anio.toString();
            return retvalue;
        }
        
    public Integer getId_queja() {
        return id_queja;
    }

    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getNo_queja() {
        return no_queja;
    }

    public void setNo_queja(Integer no_queja) {
        this.no_queja = no_queja;
    }

    public Integer getId_estado_queja() {
        return id_estado_queja;
    }

    public void setId_estado_queja(Integer id_estado_queja) {
        this.id_estado_queja = id_estado_queja;
    }

        
        
    @Override
    public String toString() {
        JsonObject queja = new JsonObject();
        queja.addProperty("id_queja",this.id_queja);
        queja.addProperty("quejanumero",this.getQuejaNumero());
        queja.addProperty("id_estado_queja",this.id_estado_queja);
        queja.addProperty("detalle_queja",this.detalle_queja);
        return queja.toString()+",";
    }
        
}
