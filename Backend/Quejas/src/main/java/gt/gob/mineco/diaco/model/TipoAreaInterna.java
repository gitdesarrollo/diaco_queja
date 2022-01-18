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

@Entity
@Table(name="diaco_area_interna")
@NamedQuery(name = "TipoAreaInterna.findAll",query = "SELECT t FROM TipoAreaInterna t where t.habilitado='1'")
public class TipoAreaInterna implements Serializable {
 private static final long serialVersionUID = 1L;
    
 @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_area", precision=0)
         private Integer id_area;
         @Column(name="tipo_area")
         private Integer tipo_area;
         @Column(name="nombre")
         private String nombre;
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

    public Integer getId_area() {
        return id_area;
    }

    public void setId_area(Integer id_area) {
        this.id_area = id_area;
    }

    public Integer getTipo_area() {
        return tipo_area;
    }

    public void setTipo_area(Integer tipo_area) {
        this.tipo_area = tipo_area;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        
	
 
        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_area",this.id_area);
            temp.addProperty("tipo_area",this.tipo_area);
            temp.addProperty("habilitado",this.habilitado);
            temp.addProperty("nombre",this.nombre);
            
           
           
            return temp.toString()+",";
        }
   
 
}
