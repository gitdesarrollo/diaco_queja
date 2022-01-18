/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.model;

import gt.gob.mineco.diaco.model.*;
import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import gt.gob.mineco.diaco.util.CheckNull;
@Entity
@Table(name="diaco_tipo_comercio")
@NamedQueries({
@NamedQuery(name="TipoComercio.findAll", query="SELECT t FROM TipoComercio t WHERE t.habilitado='1' AND t.id_actividad_economica = :id_actividad_economicaP "),
@NamedQuery(name="TipoComercio.findAll2", query="SELECT t FROM TipoComercio t WHERE t.habilitado='1' ")

})
//inner join t.tipopasoqueja og where og.id_queja_paso=:usr
public class TipoComercio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tipo_comercio", precision=0) 
        private Integer id_tipo_comercio;
        @Column(name="id_actividad_economica")
        private Integer id_actividad_economica;
        @Column(name="habilitado")
        private String habilitado;
        @Column(name="tipo_comercio")
        private String tipo_comercio;
        @Column(name="usuario_adiciono")
        private String usuario_adiciono;
        @Column(name="usuario_modifico")
        private String usuario_modifico;
        @Column(name="fecha_modificacion")
        @Temporal(javax.persistence.TemporalType.TIMESTAMP)
        private Date fecha_modificacion;
        @Column(name="fecha_adicion")
        @Temporal(javax.persistence.TemporalType.TIMESTAMP)
        private Date fecha_adicion;
        @JoinColumn(name = "id_actividad_economica", referencedColumnName = "id_actividad_economica",updatable=false, insertable=false)
        private TipoActividadEconomica tipoActividadEconomica;

    public String getTipo_comercio() {
        return tipo_comercio;
    }

    public void setTipo_comercio(String tipo_comercio) {
        this.tipo_comercio = tipo_comercio;
    }

        
        
    public TipoActividadEconomica getTipoActividadEconomica() {
        return tipoActividadEconomica;
    }

    public void setTipoActividadEconomica(TipoActividadEconomica tipoActividadEconomica) {
        this.tipoActividadEconomica = tipoActividadEconomica;
    }


    public Integer getId_tipo_comercio() {
        return id_tipo_comercio;
    }

    public void setId_tipo_comercio(Integer id_tipo_comercio) {
        this.id_tipo_comercio = id_tipo_comercio;
    }

    public Integer getId_actividad_economica() {
        return id_actividad_economica;
    }

    public void setId_actividad_economica(Integer id_actividad_economica) {
        this.id_actividad_economica = id_actividad_economica;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
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

    public Date getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(Date fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public Date getFecha_adicion() {
        return fecha_adicion;
    }

    public void setFecha_adicion(Date fecha_adicion) {
        this.fecha_adicion = fecha_adicion;
    }
        
        
     public String toString() {
            CheckNull checknull = new CheckNull();
            JsonObject temp = new JsonObject();
            temp.addProperty("id_tipo_comercio",this.id_tipo_comercio);
            temp.addProperty("id_actividad_economica",checknull.cknull(this.id_actividad_economica));
            temp.addProperty("usuario_modifico",checknull.cknull(this.usuario_modifico));
            temp.addProperty("usuario_adiciono",checknull.cknull(this.usuario_adiciono));
            temp.addProperty("habilitado",checknull.cknull(this.habilitado));
            temp.addProperty("tipo_comercio",checknull.cknull(this.tipo_comercio));
            
          
             return temp.toString()+",";
           }
           
           
             public JsonObject toJsonelement() {
            CheckNull checknull = new CheckNull();
            JsonObject temp = new JsonObject();
             temp.addProperty("id_tipo_comercio",this.id_tipo_comercio);
            temp.addProperty("id_actividad_economica",checknull.cknull(this.id_actividad_economica));
            temp.addProperty("usuario_modifico",checknull.cknull(this.usuario_modifico));
            temp.addProperty("usuario_adiciono",checknull.cknull(this.usuario_adiciono));
            temp.addProperty("habilitado",checknull.cknull(this.habilitado));
            temp.addProperty("tipo_comercio",checknull.cknull(this.tipo_comercio));
            
            return temp;
             }
    
        
        
 }
