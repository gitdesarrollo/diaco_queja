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
@Table(name="diaco_actividad_economica")
@NamedQuery(name="TipoActividadEconomica.findAll", query="SELECT t FROM TipoActividadEconomica t WHERE t.habilitado='1' ")
public class TipoActividadEconomica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_actividad_economica", precision=0) 
        private Integer id_actividad_economica;
        @Column(name="nombre_actividad_economica")
        private String nombre_actividad_economica;
        @Column(name="habilitado")
        private String habilitado;
        @Column(name="usuario_adiciono")
        private String usuario_adiciono;
        @Column(name="usuario_modifico")
        private String usuario_modifico;
        @Column(name="fecha_adicion")
        @Temporal(javax.persistence.TemporalType.TIMESTAMP)
        private Date fecha_adicion;
        @Column(name="fecha_modificacion")
        @Temporal(javax.persistence.TemporalType.TIMESTAMP)
        private Date fecha_modificacion;

    public Integer getId_actividad_economica() {
        return id_actividad_economica;
    }

    public void setId_actividad_economica(Integer id_actividad_economica) {
        this.id_actividad_economica = id_actividad_economica;
    }

    public String getNombre_actividad_economica() {
        return nombre_actividad_economica;
    }

    public void setNombre_actividad_economica(String nombre_actividad_economica) {
        this.nombre_actividad_economica = nombre_actividad_economica;
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
                 
        
        
         public String toString() {
            CheckNull checknull = new CheckNull();
            JsonObject temp = new JsonObject();
            temp.addProperty("id_actividad_economica",this.id_actividad_economica);
            temp.addProperty("nombre_actividad_economica",checknull.cknull(this.nombre_actividad_economica));
            temp.addProperty("usuario_modifico",checknull.cknull(this.usuario_modifico));
            temp.addProperty("usuario_adiciono",checknull.cknull(this.usuario_adiciono));
            temp.addProperty("habilitado",checknull.cknull(this.habilitado));
          
          
             return temp.toString()+",";
           }
           
           
             public JsonObject toJsonelement() {
            CheckNull checknull = new CheckNull();
            JsonObject temp = new JsonObject();
           temp.addProperty("id_actividad_economica",this.id_actividad_economica);
            temp.addProperty("nombre_actividad_economica",checknull.cknull(this.nombre_actividad_economica));
            temp.addProperty("usuario_modifico",checknull.cknull(this.usuario_modifico));
            temp.addProperty("usuario_adiciono",checknull.cknull(this.usuario_adiciono));
            temp.addProperty("habilitado",checknull.cknull(this.habilitado));
            
            return temp;
             }
        
        

        }