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
 * The persistent class for the diaco_tipo_cola database table.
 * 
 */
@Entity
@Table(name="diaco_tipo_cola")
@NamedQueries({
    @NamedQuery(name = "TipoTipoCola.findxflujo",query = "SELECT t FROM TipoTipoCola t where t.id_flujo = :id_flujo and t.tipo = 'f' and t.habilitado='1'"),
    @NamedQuery(name = "TipoTipoCola.findxsede",query = "SELECT t FROM TipoTipoCola t where t.id_sede = :sede and t.tipo = 's' and t.habilitado='1'"),
    @NamedQuery(name = "TipoTipoCola.findAllxsede",query = "SELECT t FROM TipoTipoCola t where t.tipo = 's' and t.habilitado='1'"),
    @NamedQuery(name = "TipoTipoCola.findAllTiposxflujo",query = "SELECT t FROM TipoTipoCola t where t.id_flujo = :id_flujo and t.habilitado='1'"),
    @NamedQuery(name = "TipoTipoCola.findxFlujoEspecial",query = "SELECT t FROM TipoTipoCola t where t.id_flujo = :id_flujo and t.tipo is null and t.habilitado='1'"),
    @NamedQuery(name = "TipoTipoCola.findAll",query = "SELECT t FROM TipoTipoCola t where t.habilitado='1' ")  
})

public class TipoTipoCola implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        //@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tipo_cola", precision=0)
        private Integer id_tipo_cola;
        @Column(name="id_flujo")
        private Integer id_flujo;
        @Column(name="nombre")
        private String nombre;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_creacion")
        private Date fecha_creacion;
        @Column(name="creado_por")
        private Integer creado_por;
        @Column(name="ultima_asignacion")
        private Integer ultima_asignacion;
        @Column(name="tipo")
        private String tipo;
        @Column(name="id_sede")
        private Integer id_sede;
        
        @Column(name="habilitado")
        private String habilitado;
        
        @Column(name="modificado_por")
        private Integer modificado_por;
        
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_modificacion")
        private Date fecha_modificacion;
        
        @OneToOne
        @JoinColumn(name = "id_flujo", referencedColumnName = "id",updatable=false, insertable=false)
        private TipoFlujo tipoflujo;
        
	public TipoTipoCola() {
	}

    public Integer getId_sede() {
        return id_sede;
    }

    public void setId_sede(Integer id_sede) {
        this.id_sede = id_sede;
    }
        
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getId_tipo_cola() {
        return id_tipo_cola;
    }

    public void setId_tipo_cola(Integer id_tipo_cola) {
        this.id_tipo_cola = id_tipo_cola;
    }

    public Integer getId_flujo() {
        return id_flujo;
    }

    public void setId_flujo(Integer id_flujo) {
        this.id_flujo = id_flujo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Integer getUltima_asignacion() {
        return ultima_asignacion;
    }

    public void setUltima_asignacion(Integer ultima_asignacion) {
        this.ultima_asignacion = ultima_asignacion;
    }

    public Integer getModificado_por() {
        return modificado_por;
    }

    public void setModificado_por(Integer modificado_por) {
        this.modificado_por = modificado_por;
    }

    public Date getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(Date fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public TipoFlujo getTipoflujo() {
        return tipoflujo;
    }

    public void setTipoflujo(TipoFlujo tipoflujo) {
        this.tipoflujo = tipoflujo;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }
     
    
    
        @Override
        public String toString() {
            CheckNull checknull = new CheckNull();
            JsonObject temp = new JsonObject();
            temp.addProperty("id_tipo_cola",this.id_tipo_cola);
            temp.addProperty("id_flujo",checknull.cknull(this.id_flujo));
            if(this.tipoflujo!=null){
                temp.addProperty("flujo",checknull.cknull(this.tipoflujo.getFlujo()));
            }
            temp.addProperty("nombre",checknull.cknull(this.nombre));
            temp.addProperty("id_sede",checknull.cknull(this.id_sede));
            return temp.toString()+",";
        }
                
}
