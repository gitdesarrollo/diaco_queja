/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.util;

import com.google.gson.JsonObject;


public class FormTipoComercio {
   
        private Integer id_tipo_comercio;
        private Integer id_actividad_economica;
        private String habilitado;
        private String tipo_comercio;
        private String nombre_actividad_economica;

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

    public String getTipo_comercio() {
        return tipo_comercio;
    }

    public void setTipo_comercio(String tipo_comercio) {
        this.tipo_comercio = tipo_comercio;
    }

    public String getNombre_actividad_economica() {
        return nombre_actividad_economica;
    }

    public void setNombre_actividad_economica(String nombre_actividad_economica) {
        this.nombre_actividad_economica = nombre_actividad_economica;
    }
        
        
   
     public String toString() {
            CheckNull checknull = new CheckNull();
            JsonObject temp = new JsonObject();
            temp.addProperty("id_tipo_comercio",this.id_tipo_comercio);
            temp.addProperty("id_actividad_economica",checknull.cknull(this.id_actividad_economica));

            temp.addProperty("habilitado",checknull.cknull(this.habilitado));
            temp.addProperty("tipo_comercio",checknull.cknull(this.tipo_comercio));
            temp.addProperty("nombre_actividad_economica",checknull.cknull(this.nombre_actividad_economica));
            
          
             return temp.toString()+",";
           }     
        
}
