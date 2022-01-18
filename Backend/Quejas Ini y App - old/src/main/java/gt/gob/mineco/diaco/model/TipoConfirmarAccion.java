package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import gt.gob.mineco.diaco.util.CheckNull;
/**
 * The persistent class for the diaco_confirmar_accion database table.
 * 
 */
@Entity
@Table(name="diaco_confirmar_accion")
@NamedQueries({
@NamedQuery(name="TipoConfirmarAccion.findAll", query="SELECT t FROM TipoConfirmarAccion t where t.id_usuario = :id_usuario and t.estado = 'A'"),
@NamedQuery(name="TipoConfirmarAccion.findxIdQueja", query="SELECT t FROM TipoConfirmarAccion t where t.id_queja = :id_queja and t.id_usuario = :id_usuario and t.estado = 'A'")
})
//inner join t.tipopasoqueja og where og.id_queja_paso=:usr
public class TipoConfirmarAccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", precision=0) 
        private Integer id;
        @Column(name="id_queja")
        private Integer id_queja;
        @JoinColumn(name = "id_queja", referencedColumnName = "id_queja",updatable=false, insertable=false)
        @OneToOne
        private TipoQueja_MainAtCon tipoqueja;
        @Column(name="id_usuario")
        private Integer id_usuario;         
        @Column(name="estado")
        private String estado;
        @Temporal(TemporalType.DATE)
        @Column(name="fecha_creacion")
        private Date fecha_creacion; 
        @Temporal(TemporalType.DATE)
        @Column(name="fecha_aceptacion")
        private Date fecha_aceptacion; 
        @Temporal(TemporalType.DATE)
        @Column(name="fecha_rechazo")
        private Date fecha_rechazo; 


        public TipoConfirmarAccion(){}

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

    public TipoQueja_MainAtCon getTipoqueja() {
        return tipoqueja;
    }

    public void setTipoqueja(TipoQueja_MainAtCon tipoqueja) {
        this.tipoqueja = tipoqueja;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Date getFecha_aceptacion() {
        return fecha_aceptacion;
    }

    public void setFecha_aceptacion(Date fecha_aceptacion) {
        this.fecha_aceptacion = fecha_aceptacion;
    }

    public Date getFecha_rechazo() {
        return fecha_rechazo;
    }

    public void setFecha_rechazo(Date fecha_rechazo) {
        this.fecha_rechazo = fecha_rechazo;
    }
        
    @Override
    public String toString() {
            JsonObject temp = new JsonObject();
            CheckNull checknull=new CheckNull();
            temp.addProperty("id",this.id);
            temp.addProperty("fecha_creacion",checknull.cknull(this.fecha_creacion));
            if(tipoqueja != null){
                temp.addProperty("id_queja",this.tipoqueja.getId_queja());
                temp.addProperty("quejanumero",this.tipoqueja.getQuejaNumero());
                temp.addProperty("consumidor",this.tipoqueja.getTipoconsumidor().getNombreCompleto());
                temp.addProperty("proveedor",this.tipoqueja.getTipoproveedor().getNombre());
            }
            return temp.toString()+",";
    }
            
}
