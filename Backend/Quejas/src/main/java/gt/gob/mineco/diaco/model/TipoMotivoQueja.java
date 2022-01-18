/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.model;
import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="diaco_motivo_queja")
@NamedQuery(name = "TipoMotivoQueja.findAll",query = "SELECT t FROM TipoMotivoQueja t where t.habilitado='1'")
public class TipoMotivoQueja implements Serializable {
 private static final long serialVersionUID = 1L;
 
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_motivo_queja", precision=0)
        private Integer id_motivo_queja;
        @Column(name="motivo")
        private String motivo;
        @Column(name="habilitado")
        private String habilitado;
	 @Column(name="fecha_adicion")
 @Temporal(javax.persistence.TemporalType.TIMESTAMP)
        private Date fecha_adicion;
         @Column(name="fecha_modificacion")
 @Temporal(javax.persistence.TemporalType.TIMESTAMP)
        private Date fecha_modificacion;
         @Column(name="usuario_adiciono")
         private String usuario_adiciono;
         @Column(name="usuario_modifico")
         private String usuario_modifico;

    public Integer getId_motivo_queja() {
        return id_motivo_queja;
    }

    public void setId_motivo_queja(Integer id_motivo_queja) {
        this.id_motivo_queja = id_motivo_queja;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    public Date getFecha_adicion() {
        return fecha_adicion;
    }

    public void setFecha_adicion(Date fecha_adicion) {
        this.fecha_adicion = fecha_adicion;
    }

    public Date getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(Date fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public String getUsuario_adiciono() {
        return usuario_adiciono;
    }

    public void setUsuario_adiciono(String usuario_adiciono) {
        this.usuario_adiciono = usuario_adiciono;
    }

    public String getUsuario_modifico() {
        return usuario_modifico;
    }

    public void setUsuario_modifico(String usuario_modifico) {
        this.usuario_modifico = usuario_modifico;
    }

         
         
         
           @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_motivo_queja",this.id_motivo_queja);
            temp.addProperty("motivo",this.motivo);
            temp.addProperty("habilitado",this.habilitado);
            temp.addProperty("usuario_adiciono",this.usuario_adiciono);
            temp.addProperty("usuario_modifico",this.usuario_modifico);
            
           
           
            return temp.toString()+",";
        }
   
        public JsonObject toJsonelement() {
             JsonObject temp = new JsonObject();
            temp.addProperty("id_motivo_queja",this.id_motivo_queja);
            temp.addProperty("motivo",this.motivo);
            temp.addProperty("habilitado",this.habilitado);
            temp.addProperty("usuario_adiciono",this.usuario_adiciono);
            temp.addProperty("usuario_modifico",this.usuario_modifico);
            
            
            return temp;
        }

}
