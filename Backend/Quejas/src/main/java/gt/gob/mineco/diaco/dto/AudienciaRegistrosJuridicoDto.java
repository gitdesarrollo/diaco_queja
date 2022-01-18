/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.dto;

import com.google.gson.JsonObject;

/**
 *
 * @author julio
 */
public class AudienciaRegistrosJuridicoDto {
 
    private String id_audiencia;
    
    private String  cedula_notificacion_citacion_jur_con_id;
    private String cedula_notificacion_citacion_jur_con_codigo;
    
    private String  cedula_notificacion_citacion_jur_pro_id;
    private String  cedula_notificacion_citacion_jur_pro_codigo;
    
    private String cedula_citacion_jur_pro_id;
    private String cedula_citacion_jur_pro_codigo;
    
    private String cedula_citacion_jur_con_id;
    private String cedula_citacion_jur_con_codigo;
    
            
    private String cedula_notificacion_re_correo_id;
    private String cedula_notificacion_re_correo_codigo;
            
    private String cedula_notificacion_cit_correo_id;
    private String cedula_notificacion_cit_correo_codigo;

    /**
     * @return the id_audiencia
     */
    public String getId_audiencia() {
        return id_audiencia;
    }

    /**
     * @param id_audiencia the id_audiencia to set
     */
    public void setId_audiencia(String id_audiencia) {
        this.id_audiencia = id_audiencia;
    }

    /**
     * @return the cedula_notificacion_citacion_jur_id
     */
  
  
  
  
    /**
     * @return the cedula_notificacion_re_correo_id
     */
    public String getCedula_notificacion_re_correo_id() {
        return cedula_notificacion_re_correo_id;
    }

    /**
     * @param cedula_notificacion_re_correo_id the cedula_notificacion_re_correo_id to set
     */
    public void setCedula_notificacion_re_correo_id(String cedula_notificacion_re_correo_id) {
        this.cedula_notificacion_re_correo_id = cedula_notificacion_re_correo_id;
    }

    /**
     * @return the cedula_notificacion_re_correo_codigo
     */
    public String getCedula_notificacion_re_correo_codigo() {
        return cedula_notificacion_re_correo_codigo;
    }

    /**
     * @param cedula_notificacion_re_correo_codigo the cedula_notificacion_re_correo_codigo to set
     */
    public void setCedula_notificacion_re_correo_codigo(String cedula_notificacion_re_correo_codigo) {
        this.cedula_notificacion_re_correo_codigo = cedula_notificacion_re_correo_codigo;
    }

    /**
     * @return the cedula_notificacion_cit_correo_id
     */
    public String getCedula_notificacion_cit_correo_id() {
        return cedula_notificacion_cit_correo_id;
    }

    /**
     * @param cedula_notificacion_cit_correo_id the cedula_notificacion_cit_correo_id to set
     */
    public void setCedula_notificacion_cit_correo_id(String cedula_notificacion_cit_correo_id) {
        this.cedula_notificacion_cit_correo_id = cedula_notificacion_cit_correo_id;
    }

    /**
     * @return the cedula_notificacion_cit_correo_codigo
     */
    public String getCedula_notificacion_cit_correo_codigo() {
        return cedula_notificacion_cit_correo_codigo;
    }

    /**
     * @param cedula_notificacion_cit_correo_codigo the cedula_notificacion_cit_correo_codigo to set
     */
    public void setCedula_notificacion_cit_correo_codigo(String cedula_notificacion_cit_correo_codigo) {
        this.cedula_notificacion_cit_correo_codigo = cedula_notificacion_cit_correo_codigo;
    }
    
    @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_audiencia",this.id_audiencia);
            if(getCedula_notificacion_citacion_jur_con_id()!=null && getCedula_notificacion_citacion_jur_con_codigo()!=null){
                temp.addProperty("cedula_notificacion_citacion_jur_con_id", getCedula_notificacion_citacion_jur_con_id());
                temp.addProperty("cedula_notificacion_citacion_jur_con_codigo", getCedula_notificacion_citacion_jur_con_codigo());
            }
            if(getCedula_notificacion_citacion_jur_pro_id()!=null && getCedula_notificacion_citacion_jur_pro_codigo()!=null){
                temp.addProperty("cedula_notificacion_citacion_jur_pro_id", getCedula_notificacion_citacion_jur_pro_id());
                temp.addProperty("cedula_notificacion_citacion_jur_pro_codigo", getCedula_notificacion_citacion_jur_pro_codigo());
            }
            
            if(getCedula_citacion_jur_con_id() !=null && getCedula_citacion_jur_con_codigo()!=null){
                temp.addProperty("cedula_citacion_jur_con_id", getCedula_citacion_jur_con_id());
                temp.addProperty("cedula_citacion_jur_con_codigo", getCedula_citacion_jur_con_codigo());
            } 
            if(getCedula_citacion_jur_pro_id() !=null && getCedula_citacion_jur_pro_codigo()!=null){
                temp.addProperty("cedula_citacion_jur_pro_id", getCedula_citacion_jur_pro_id());
                temp.addProperty("cedula_citacion_jur_pro_codigo", getCedula_citacion_jur_pro_codigo());
            } 
                        
            
            if(cedula_notificacion_re_correo_id!=null&&cedula_notificacion_re_correo_codigo!=null){
                temp.addProperty("cedula_notificacion_re_correo_id",cedula_notificacion_re_correo_id);
                temp.addProperty("cedula_notificacion_re_correo_codigo",cedula_notificacion_re_correo_codigo);
            }
            if(cedula_notificacion_cit_correo_id!=null&&cedula_notificacion_cit_correo_codigo!=null){
                temp.addProperty("cedula_notificacion_cit_correo_id",cedula_notificacion_cit_correo_id);
                temp.addProperty("cedula_notificacion_cit_correo_codigo",cedula_notificacion_cit_correo_codigo);
            }

            return temp.toString()+",";
        }

    /**
     * @return the cedula_notificacion_citacion_jur_con_id
     */
    public String getCedula_notificacion_citacion_jur_con_id() {
        return cedula_notificacion_citacion_jur_con_id;
    }

    /**
     * @param cedula_notificacion_citacion_jur_con_id the cedula_notificacion_citacion_jur_con_id to set
     */
    public void setCedula_notificacion_citacion_jur_con_id(String cedula_notificacion_citacion_jur_con_id) {
        this.cedula_notificacion_citacion_jur_con_id = cedula_notificacion_citacion_jur_con_id;
    }

    /**
     * @return the cedula_notificacion_citacion_jur_con_codigo
     */
    public String getCedula_notificacion_citacion_jur_con_codigo() {
        return cedula_notificacion_citacion_jur_con_codigo;
    }

    /**
     * @param cedula_notificacion_citacion_jur_con_codigo the cedula_notificacion_citacion_jur_con_codigo to set
     */
    public void setCedula_notificacion_citacion_jur_con_codigo(String cedula_notificacion_citacion_jur_con_codigo) {
        this.cedula_notificacion_citacion_jur_con_codigo = cedula_notificacion_citacion_jur_con_codigo;
    }

    /**
     * @return the cedula_notificacion_citacion_jur_pro_id
     */
    public String getCedula_notificacion_citacion_jur_pro_id() {
        return cedula_notificacion_citacion_jur_pro_id;
    }

    /**
     * @param cedula_notificacion_citacion_jur_pro_id the cedula_notificacion_citacion_jur_pro_id to set
     */
    public void setCedula_notificacion_citacion_jur_pro_id(String cedula_notificacion_citacion_jur_pro_id) {
        this.cedula_notificacion_citacion_jur_pro_id = cedula_notificacion_citacion_jur_pro_id;
    }

    /**
     * @return the cedula_notificacion_citacion_jur_pro_codigo
     */
    public String getCedula_notificacion_citacion_jur_pro_codigo() {
        return cedula_notificacion_citacion_jur_pro_codigo;
    }

    /**
     * @param cedula_notificacion_citacion_jur_pro_codigo the cedula_notificacion_citacion_jur_pro_codigo to set
     */
    public void setCedula_notificacion_citacion_jur_pro_codigo(String cedula_notificacion_citacion_jur_pro_codigo) {
        this.cedula_notificacion_citacion_jur_pro_codigo = cedula_notificacion_citacion_jur_pro_codigo;
    }

    /**
     * @return the cedula_citacion_jur_pro_id
     */
    public String getCedula_citacion_jur_pro_id() {
        return cedula_citacion_jur_pro_id;
    }

    /**
     * @param cedula_citacion_jur_pro_id the cedula_citacion_jur_pro_id to set
     */
    public void setCedula_citacion_jur_pro_id(String cedula_citacion_jur_pro_id) {
        this.cedula_citacion_jur_pro_id = cedula_citacion_jur_pro_id;
    }

    /**
     * @return the cedula_citacion_jur_pro_codigo
     */
    public String getCedula_citacion_jur_pro_codigo() {
        return cedula_citacion_jur_pro_codigo;
    }

    /**
     * @param cedula_citacion_jur_pro_codigo the cedula_citacion_jur_pro_codigo to set
     */
    public void setCedula_citacion_jur_pro_codigo(String cedula_citacion_jur_pro_codigo) {
        this.cedula_citacion_jur_pro_codigo = cedula_citacion_jur_pro_codigo;
    }

    /**
     * @return the cedula_citacion_jur_con_id
     */
    public String getCedula_citacion_jur_con_id() {
        return cedula_citacion_jur_con_id;
    }

    /**
     * @param cedula_citacion_jur_con_id the cedula_citacion_jur_con_id to set
     */
    public void setCedula_citacion_jur_con_id(String cedula_citacion_jur_con_id) {
        this.cedula_citacion_jur_con_id = cedula_citacion_jur_con_id;
    }

    /**
     * @return the cedula_citacion_jur_con_codigo
     */
    public String getCedula_citacion_jur_con_codigo() {
        return cedula_citacion_jur_con_codigo;
    }

    /**
     * @param cedula_citacion_jur_con_codigo the cedula_citacion_jur_con_codigo to set
     */
    public void setCedula_citacion_jur_con_codigo(String cedula_citacion_jur_con_codigo) {
        this.cedula_citacion_jur_con_codigo = cedula_citacion_jur_con_codigo;
    }
    
    
}
