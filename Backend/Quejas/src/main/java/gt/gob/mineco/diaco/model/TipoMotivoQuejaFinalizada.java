package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
/**
 * The persistent class for the diaco_motivo_queja_finalizada database view.
 * 
 */
@Entity
@Table(name="diaco_motivo_queja_finalizada")
@NamedQueries({
@NamedQuery(name="TipoMotivoQuejaFinalizada.findAll", query="SELECT t FROM TipoMotivoQuejaFinalizada t where t.estado = 'A' and t.tipo = :tipo"),
@NamedQuery(name="TipoMotivoQuejaFinalizada.findAll2", query="SELECT t FROM TipoMotivoQuejaFinalizada t where t.estado = 'A' "),    
@NamedQuery(name="TipoMotivoQuejaFinalizada.findMotivo", query="SELECT t FROM TipoMotivoQuejaFinalizada t where t.estado = 'A' and t.tipo = 'C' and t.padre=:padre")
})
        public class TipoMotivoQuejaFinalizada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id_motivo_queja_finalizada;
	private String descripcion;
        private String tipo;
        private String estado;
        private Integer padre;
        private String usuario_modifico;
        @Temporal(TemporalType.TIMESTAMP)
        private Date fecha_modifico;
        private String usuario_creacion;
        @Temporal(TemporalType.TIMESTAMP)
        private Date fecha_creacion;
        
        
     public   TipoMotivoQuejaFinalizada(){
        }

    public String getUsuario_modifico() {
        return usuario_modifico;
    }

    public void setUsuario_modifico(String usuario_modifico) {
        this.usuario_modifico = usuario_modifico;
    }

    public Date getFecha_modifico() {
        return fecha_modifico;
    }

    public void setFecha_modifico(Date fecha_modifico) {
        this.fecha_modifico = fecha_modifico;
    }

    public String getUsuario_creacion() {
        return usuario_creacion;
    }

    public void setUsuario_creacion(String usuario_creacion) {
        this.usuario_creacion = usuario_creacion;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
        
        
        

    public Integer getId_motivo_queja_finalizada() {
        return id_motivo_queja_finalizada;
    }

    public void setId_motivo_queja_finalizada(Integer id_motivo_queja_finalizada) {
        this.id_motivo_queja_finalizada = id_motivo_queja_finalizada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }        

    public Integer getPadre() {
        return padre;
    }

    public void setPadre(Integer padre) {
        this.padre = padre;
    }
        
        @Override
    public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_motivo_queja_finalizada",this.id_motivo_queja_finalizada);
            temp.addProperty("descripcion",this.descripcion);
            temp.addProperty("tipo",this.tipo);
            temp.addProperty("padre",this.padre);
            temp.addProperty("estado",this.estado);
            temp.addProperty("id",this.id_motivo_queja_finalizada);
            
            return temp.toString()+",";
    }

    
}
