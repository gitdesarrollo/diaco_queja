/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.util;

import java.io.Serializable;

/**
 *
 * @author julio
 */
public class FormCalificacionUsuario implements Serializable {
    
    private String id_instalacion;
    private String nit_proveedor;
    private Integer calificacion;
    private Integer idCategoriaCalificacion;

    /**
     * @return the id_instalacion
     */
    public String getId_instalacion() {
        return id_instalacion;
    }

    /**
     * @param id_instalacion the id_instalacion to set
     */
    public void setId_instalacion(String id_instalacion) {
        this.id_instalacion = id_instalacion;
    }

    /**
     * @return the nit_proveedor
     */
    public String getNit_proveedor() {
        return nit_proveedor;
    }

    /**
     * @param nit_proveedor the nit_proveedor to set
     */
    public void setNit_proveedor(String nit_proveedor) {
        this.nit_proveedor = nit_proveedor;
    }

    /**
     * @return the calificacion
     */
    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the idCategoriaCalificacion
     */
    public Integer getIdCategoriaCalificacion() {
        return idCategoriaCalificacion;
    }

    /**
     * @param idCategoriaCalificacion the idCategoriaCalificacion to set
     */
    public void setIdCategoriaCalificacion(Integer idCategoriaCalificacion) {
        this.idCategoriaCalificacion = idCategoriaCalificacion;
    }
    
}
