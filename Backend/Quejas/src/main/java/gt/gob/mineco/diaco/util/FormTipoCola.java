/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.util;

import com.google.gson.JsonObject;
import java.util.Date;

public class FormTipoCola {
   	private Integer id_tipo_cola;
	private Integer id_flujo;
        private String nombre_flujo;
	private String nombre;
	private Date fecha_creacion;
	private Integer creado_por;
        private Date fecha_modificacion;
        private Integer modificado_por;
	private Integer ultima_asignacion;
	private String tipo;
	private Integer id_sede;
        private String nombre_sede;
        private String habilitado;

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

    public String getNombre_flujo() {
        return nombre_flujo;
    }

    public void setNombre_flujo(String nombre_flujo) {
        this.nombre_flujo = nombre_flujo;
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

    public Date getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(Date fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public Integer getModificado_por() {
        return modificado_por;
    }

    public void setModificado_por(Integer modificado_por) {
        this.modificado_por = modificado_por;
    }

    public Integer getUltima_asignacion() {
        return ultima_asignacion;
    }

    public void setUltima_asignacion(Integer ultima_asignacion) {
        this.ultima_asignacion = ultima_asignacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getId_sede() {
        return id_sede;
    }

    public void setId_sede(Integer id_sede) {
        this.id_sede = id_sede;
    }

    public String getNombre_sede() {
        return nombre_sede;
    }

    public void setNombre_sede(String nombre_sede) {
        this.nombre_sede = nombre_sede;
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
            temp.addProperty("id_tipo_cola",this.id_tipo_cola);
            temp.addProperty("id_flujo",this.id_flujo);
            temp.addProperty("nombre_flujo",this.nombre_flujo);
            temp.addProperty("ultima_asignacion",this.ultima_asignacion);
            temp.addProperty("tipo",this.tipo);
            temp.addProperty("id_sede",this.id_sede);
            temp.addProperty("nombre_sede",this.nombre_sede);
            temp.addProperty("habilitado",this.habilitado);
            temp.addProperty("nombre",this.nombre);
             
            return temp.toString()+",";
        }
      
    
}
