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
@Table(name="diaco_paises")
@NamedQuery(name = "TipoPais.findAll",query = "SELECT t FROM TipoPais t where t.habilitado='1'")
public class TipoPais implements Serializable {
 private static final long serialVersionUID = 1L;
 
 @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pais", precision=0)
        private Integer id_pais;
        @Column(name="nombre_pais")
        private String nombre_pais;
        @Column(name="habilitado")
        private String habilitado;
	 @Column(name="fecha_adicion")
 @Temporal(javax.persistence.TemporalType.TIMESTAMP)
        private Date fecha_adicion;
         @Column(name="fecha_modificacion")
 @Temporal(javax.persistence.TemporalType.TIMESTAMP)
        private Date fecha_modificacion;

    public Integer getId_pais() {
        return id_pais;
    }

    public void setId_pais(Integer id_pais) {
        this.id_pais = id_pais;
    }

    public String getNombre_pais() {
        return nombre_pais;
    }

    public void setNombre_pais(String nombre_pais) {
        this.nombre_pais = nombre_pais;
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
         
 
   
        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_pais",this.id_pais);
            temp.addProperty("nombre_pais",this.nombre_pais);
            temp.addProperty("habilitado",this.habilitado);
           
           
            return temp.toString()+",";
        }
   
        public JsonObject toJsonelement() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_pais",this.id_pais);
            temp.addProperty("nombre_pais",this.nombre_pais);
            temp.addProperty("habilitado",this.habilitado);
            
            return temp;
        }

}
