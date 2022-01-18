/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.util;

import com.google.gson.JsonObject;


public class FormSede {
    
    
    private Integer id_diaco_sede;
     
	private String nombre_sede;
	private String habilitado;
	private String direccion_sede;
       private String direccion_calle;
        private String direccion_avenida;
        private String direccion_zona;
        private String direccion_detalle;
        private Integer codigo_municipio;
        private String nombre_municipio;
        private Integer codigo_departamento;
        private String nombre_departamento;

    public Integer getId_diaco_sede() {
        return id_diaco_sede;
    }

    public void setId_diaco_sede(Integer id_diaco_sede) {
        this.id_diaco_sede = id_diaco_sede;
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

    public String getDireccion_sede() {
        return direccion_sede;
    }

    public void setDireccion_sede(String direccion_sede) {
        this.direccion_sede = direccion_sede;
    }

    public String getDireccion_calle() {
        return direccion_calle;
    }

    public void setDireccion_calle(String direccion_calle) {
        this.direccion_calle = direccion_calle;
    }

    public String getDireccion_avenida() {
        return direccion_avenida;
    }

    public void setDireccion_avenida(String direccion_avenida) {
        this.direccion_avenida = direccion_avenida;
    }

    public String getDireccion_zona() {
        return direccion_zona;
    }

    public void setDireccion_zona(String direccion_zona) {
        this.direccion_zona = direccion_zona;
    }

    public String getDireccion_detalle() {
        return direccion_detalle;
    }

    public void setDireccion_detalle(String direccion_detalle) {
        this.direccion_detalle = direccion_detalle;
    }

    public Integer getCodigo_municipio() {
        return codigo_municipio;
    }

    public void setCodigo_municipio(Integer codigo_municipio) {
        this.codigo_municipio = codigo_municipio;
    }

    public String getNombre_municipio() {
        return nombre_municipio;
    }

    public void setNombre_municipio(String nombre_municipio) {
        this.nombre_municipio = nombre_municipio;
    }

    public Integer getCodigo_departamento() {
        return codigo_departamento;
    }

    public void setCodigo_departamento(Integer codigo_departamento) {
        this.codigo_departamento = codigo_departamento;
    }

    public String getNombre_departamento() {
        return nombre_departamento;
    }

    public void setNombre_departamento(String nombre_departamento) {
        this.nombre_departamento = nombre_departamento;
    }
       
    
        @Override
        public String toString() {
            JsonObject queja = new JsonObject();
            queja.addProperty("id_diaco_sede",this.id_diaco_sede);
            queja.addProperty("nombre_sede",this.nombre_sede);
            queja.addProperty("habilitado",this.habilitado);
            queja.addProperty("direccion_sede",this.direccion_sede);
            /*queja.addProperty("fecha_adicion",this.fecha_adicion.toString());
            queja.addProperty("fecha_modificacion",this.fecha_modificacion.toString());
            queja.addProperty("usuario_adiciono",this.usuario_adiciono);
            queja.addProperty("usuario_modifico",this.usuario_modifico);*/
            queja.addProperty("codigo_departamento",this.codigo_departamento);
            queja.addProperty("nombre_departamento",this.nombre_departamento);
            queja.addProperty("codigo_municipio",this.codigo_municipio);
            queja.addProperty("nombre_municipio",this.nombre_municipio);
            
            queja.addProperty("direccion_avenida", this.direccion_avenida);
            queja.addProperty("direccion_calle",this.direccion_calle);
            queja.addProperty("direccion_zona",this.direccion_zona);
            queja.addProperty("direccion_detalle",this.direccion_detalle);
            return queja.toString()+",";
        }
    
}
