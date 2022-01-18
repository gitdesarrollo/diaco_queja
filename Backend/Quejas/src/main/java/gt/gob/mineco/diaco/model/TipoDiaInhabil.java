/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="diaco_dia_inhabil")
@NamedQuery(name = "TipoDiaInhabil.findAllByDepto", query = "SELECT t FROM TipoDiaInhabil t where t.habilitado='1' and t.id_departamento= :id_departamentoP ")
public class TipoDiaInhabil implements Serializable {
private static final long serialVersionUID = 1L;   

        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_dia_inhabil", precision=0)
        private Integer id_dia_inhabil;
        @Column(name="descripcion")
        private String descripcion;
        @Column(name="dia")
        private Integer dia;
        @Column(name="mes")
        private Integer mes;
        @Column(name="usuario_adiciono")
        private String usuario_adiciono;
        @Column(name="fecha_adicion")
        @Temporal(javax.persistence.TemporalType.TIMESTAMP)
        private Date fecha_adicion;
        @Column(name="fecha_modifico")
        @Temporal(javax.persistence.TemporalType.TIMESTAMP)
        private Date fecha_modifico;          
        @Column(name="usuario_modifico")
        private String usuario_modifico;
        @Column(name="id_departamento")
        private Integer id_departamento;
        @Column(name="habilitado")
        private String habilitado;

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }
        
    public String getUsuario_modifico() {
        return usuario_modifico;
    }

    public void setUsuario_modifico(String usuario_modifico) {
        this.usuario_modifico = usuario_modifico;
    }

    public Integer getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(Integer id_departamento) {
        this.id_departamento = id_departamento;
    }
        


    public Integer getId_dia_inhabil() {
        return id_dia_inhabil;
    }

    public void setId_dia_inhabil(Integer id_dia_inhabil) {
        this.id_dia_inhabil = id_dia_inhabil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public String getUsuario_adiciono() {
        return usuario_adiciono;
    }

    public void setUsuario_adiciono(String usuario_adiciono) {
        this.usuario_adiciono = usuario_adiciono;
    }

    public Date getFecha_adicion() {
        return fecha_adicion;
    }

    public void setFecha_adicion(Date fecha_adicion) {
        this.fecha_adicion = fecha_adicion;
    }

    public Date getFecha_modifico() {
        return fecha_modifico;
    }

    public void setFecha_modifico(Date fecha_modifico) {
        this.fecha_modifico = fecha_modifico;
    }
      
  
        
   @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_dia_inhabil",this.id_dia_inhabil);
            temp.addProperty("descripcion",this.descripcion);
            temp.addProperty("dia",this.dia);
            temp.addProperty("mes",this.mes);
            temp.addProperty("usuario_adiciono",this.usuario_adiciono);  
            temp.addProperty("usuario_modifico",this.usuario_modifico);
            temp.addProperty("id_departamento",this.id_departamento);
            temp.addProperty("habilitado",this.habilitado);
            
            return temp.toString()+",";
        }      
   public JsonObject toJsonelement() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_dia_inhabil",this.id_dia_inhabil);
            temp.addProperty("descripcion",this.descripcion);
            temp.addProperty("dia",this.dia);
            temp.addProperty("mes",this.mes);
            temp.addProperty("usuario_adiciono",this.usuario_adiciono); 
            temp.addProperty("usuario_modifico",this.usuario_modifico);
            temp.addProperty("id_departamento",this.id_departamento);
          temp.addProperty("habilitado",this.habilitado);
            
            return temp;
        }
      
         
    
}
