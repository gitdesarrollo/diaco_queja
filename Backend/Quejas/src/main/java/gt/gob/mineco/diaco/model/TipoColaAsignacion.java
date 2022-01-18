package gt.gob.mineco.diaco.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import gt.gob.mineco.diaco.util.CheckNull;
import gt.gob.mineco.diaco.model.TipoPasoQueja;
import gt.gob.mineco.diaco.model.TipoProgramaAudiencia;

/**
 * The persistent class for the diaco_cola_asignacion database table.
 * 
 */
@Entity
@Table(name="diaco_cola_asignacion")
@NamedQueries({
    @NamedQuery(
        name = "TipoColaAsignacion.findPrimero",
        query = "SELECT t FROM TipoColaAsignacion t where t.id_tipo_cola = :id_tipo_cola and t.activo = true order by t.id"),
    @NamedQuery(
        name = "TipoColaAsignacion.findSiguiente",
        query = "SELECT t FROM TipoColaAsignacion t where t.id_tipo_cola = :id_tipo_cola and t.id > "
                + "(SELECT sq.id from TipoColaAsignacion sq where sq.id_tipo_cola = :id_tipo_cola and sq.id_usuario = :id_usuario) "
                + "and t.activo = true order by t.id"),   
    @NamedQuery(
        name = "TipoColaAsignacion.findXTipoyUsuario",
        query = "SELECT t FROM TipoColaAsignacion t where t.id_tipo_cola = :id_tipo_cola and t.id_usuario = :id_usuario "
                + "and t.activo = true order by t.id"),
    @NamedQuery(
        name = "TipoColaAsignacion.findXTipo",
        query = "SELECT t FROM TipoColaAsignacion t where t.id_tipo_cola = :id_tipo_cola "
                + "order by t.id")
})

public class TipoColaAsignacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", precision=0)
        private Integer id;
        @Column(name="id_usuario")
        private Integer id_usuario;
        @Column(name="id_tipo_cola")
        private Integer id_tipo_cola;
        @Column(name = "activo", columnDefinition="BIT")
        private boolean activo;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_creacion")
        private Date fecha_creacion; 
        @Column(name="creado_por")
        private Integer creado_por;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_programado")
        private Date fecha_programado; 
        @Column(name="flag_programado")
        private Integer flag_programado;
        @OneToOne
        @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", updatable=false, insertable=false)
        private TipoUsuario_Conf tipousuario;
        
	public TipoColaAsignacion() {
	}

    public Date getFecha_programado() {
        return fecha_programado;
    }

    public void setFecha_programado(Date fecha_programado) {
        this.fecha_programado = fecha_programado;
    }

    public Integer getFlag_programado() {
        return flag_programado;
    }

    public void setFlag_programado(Integer flag_programado) {
        this.flag_programado = flag_programado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_tipo_cola() {
        return id_tipo_cola;
    }

    public void setId_tipo_cola(Integer id_tipo_cola) {
        this.id_tipo_cola = id_tipo_cola;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Integer getCreado_por() {
        return creado_por;
    }

    public void setCreado_por(Integer creado_por) {
        this.creado_por = creado_por;
    }
        
        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            CheckNull checknull = new CheckNull();
            temp.addProperty("id",this.id);
            temp.addProperty("id_usuario", checknull.cknull(this.id_usuario));
            temp.addProperty("activo", this.activo);
            if(tipousuario!=null){
                temp.addProperty("usuario", checknull.cknull(this.tipousuario.getNombre()));
                temp.addProperty("login", checknull.cknull(this.tipousuario.getLogin()));
            }
            if(flag_programado!=null){
                temp.addProperty("flag_programado", this.flag_programado);
                temp.addProperty("fecha_programado", checknull.cknull(this.fecha_programado));
            }else{
                temp.addProperty("flag_programado", 0);
            }
            return temp.toString()+",";
        }
                
}
