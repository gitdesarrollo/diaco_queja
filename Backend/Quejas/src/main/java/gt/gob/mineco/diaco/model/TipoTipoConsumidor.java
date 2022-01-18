/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="diaco_tipo_consumidor")
@NamedQuery(name="TipoTipoConsumidor.findAll", query="SELECT t FROM TipoTipoConsumidor t where t.habilitado='1' ")
public class TipoTipoConsumidor implements Serializable {
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tipo_consumidor", precision=0) 
        private Integer id_tipo_consumidor;
        @Column(name="nombre_consumidor")
        private String nombre_consumidor;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_adicion")
        private Date fecha_adicion;
        @Column(name="usuario_adicion")        
	private String usuario_adicion;
        @Column(name="usuario_modificacion")        
	private String usuario_modificacion;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_modificacion")
        private Date fecha_modificacion;
        @Column(name="habilitado")
        private String habilitado;

    public Integer getId_tipo_consumidor() {
        return id_tipo_consumidor;
    }

    public void setId_tipo_consumidor(Integer id_tipo_consumidor) {
        this.id_tipo_consumidor = id_tipo_consumidor;
    }

    public String getNombre_consumidor() {
        return nombre_consumidor;
    }

    public void setNombre_consumidor(String nombre_consumidor) {
        this.nombre_consumidor = nombre_consumidor;
    }

    public Date getFecha_adicion() {
        return fecha_adicion;
    }

    public void setFecha_adicion(Date fecha_adicion) {
        this.fecha_adicion = fecha_adicion;
    }

    public String getUsuario_adicion() {
        return usuario_adicion;
    }

    public void setUsuario_adicion(String usuario_adicion) {
        this.usuario_adicion = usuario_adicion;
    }

    public String getUsuario_modificacion() {
        return usuario_modificacion;
    }

    public void setUsuario_modificacion(String usuario_modificacion) {
        this.usuario_modificacion = usuario_modificacion;
    }

    public Date getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(Date fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }
        
     @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_tipo_consumidor",this.id_tipo_consumidor);
            temp.addProperty("nombre_consumidor",this.nombre_consumidor);
            temp.addProperty("habilitado",this.habilitado);
            temp.addProperty("usuario_adicion",this.usuario_adicion);
            temp.addProperty("usuario_modificacion",this.usuario_modificacion);
           
            return temp.toString()+",";
        }
           
    
}
