package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.ws.rs.Produces;
import java.util.Date;
import com.google.gson.JsonObject;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.JsonArray;
import gt.gob.mineco.diaco.model.TipoFlujo;
/**
 * The persistent class for the diaco_estado_queja database table.
 * 
 */
@Entity
@Table(name="diaco_estado_queja")
@NamedQuery(name = "TipoEstadoQueja.findAll",query = "SELECT t FROM TipoEstadoQueja t order by t.tipoflujo.id, t.id_estado_queja")
public class TipoEstadoQueja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @Column(name="id_estado_queja")
	private Integer id_estado_queja;
        @Column(name="descripcion")
	private String descripcion;
        @Column(name="usuario_adiciono")
	private Integer usuario_adiciono;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_adicion")
        private Date fecha_adicion;
        @OneToOne
        @JoinColumn(name = "flujo_id", referencedColumnName = "id",updatable=false, insertable=false)
        private TipoFlujo tipoflujo;
        
	public TipoEstadoQueja() {
	}

        public Integer getId_estado_queja() {
            return id_estado_queja;
        }

        public void setId_estado_queja(Integer id_estado_queja) {
            this.id_estado_queja = id_estado_queja;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public Integer getUsuario_adiciono() {
            return usuario_adiciono;
        }

        public void setUsuario_adiciono(Integer usuario_adiciono) {
            this.usuario_adiciono = usuario_adiciono;
        }

        public Date getFecha_adicion() {
            return fecha_adicion;
        }

        public void setFecha_adicion(Date fecha_adicion) {
            this.fecha_adicion = fecha_adicion;
        }

        public TipoFlujo getTipoflujo() {
            return tipoflujo;
        }

        public void setTipoflujo(TipoFlujo tipoflujo) {
            this.tipoflujo = tipoflujo;
        }
                
        @Override
        public String toString() {
            JsonObject queja = new JsonObject();
            queja.addProperty("id_estado_queja",this.id_estado_queja);
            queja.addProperty("descripcion",this.descripcion);
            queja.addProperty("nombre_flujo",this.tipoflujo.getFlujo());
            queja.addProperty("id_flujo",this.tipoflujo.getId());
            return queja.toString()+",";
        }

}
