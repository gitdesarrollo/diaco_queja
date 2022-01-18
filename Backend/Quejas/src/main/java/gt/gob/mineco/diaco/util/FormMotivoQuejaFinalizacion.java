/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.util;

import com.google.gson.JsonObject;


public class FormMotivoQuejaFinalizacion {
 private Integer  id_motivo_queja_finalizada;
 private String descripcion;
 private String tipo;
 private String nombreTipo;
 private String estado;
 private Integer padre;
 private String nombrePadre;

    public Integer getId_motivo_queja_finalizada() {
        return id_motivo_queja_finalizada;
    }

    public void setId_motivo_queja_finalizada(Integer id_motivo_queja_finalizada) {
        this.id_motivo_queja_finalizada = id_motivo_queja_finalizada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getPadre() {
        return padre;
    }

    public void setPadre(Integer padre) {
        this.padre = padre;
    }

    public String getNombrePadre() {
        return nombrePadre;
    }

    public void setNombrePadre(String nombrePadre) {
        this.nombrePadre = nombrePadre;
    }
 
 
       
      @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id",this.id_motivo_queja_finalizada);
           temp.addProperty("descripcion",this.descripcion);
           temp.addProperty("estado",this.estado);
           temp.addProperty("nombrePadre",this.nombrePadre);
           temp.addProperty("padre",this.padre);
           temp.addProperty("tipo",this.tipo);
           temp.addProperty("nombreTipo",this.nombreTipo);
          
             
             
            
            return temp.toString()+",";
        }
 
        
}
