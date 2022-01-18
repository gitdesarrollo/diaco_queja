/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.dto;

/**
 *
 * @author julio
 */
public class CalificacionProveedorDto {
    
    private String idCategoria;
    private String nombreCategoria;
    private String calificacion;
    private String totalCalificaciones;
    private String promedioCalificaciones;

    /**
     * @return the idCategoria
     */
    public String getIdCategoria() {
        return idCategoria;
    }

    /**
     * @param idCategoria the idCategoria to set
     */
    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * @return the nombreCategoria
     */
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    /**
     * @param nombreCategoria the nombreCategoria to set
     */
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    /**
     * @return the calificacion
     */
    public String getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the totalCalificaciones
     */
    public String getTotalCalificaciones() {
        return totalCalificaciones;
    }

    /**
     * @param totalCalificaciones the totalCalificaciones to set
     */
    public void setTotalCalificaciones(String totalCalificaciones) {
        this.totalCalificaciones = totalCalificaciones;
    }

    /**
     * @return the promedioCalificaciones
     */
    public String getPromedioCalificaciones() {
        return promedioCalificaciones;
    }

    /**
     * @param promedioCalificaciones the promedioCalificaciones to set
     */
    public void setPromedioCalificaciones(String promedioCalificaciones) {
        this.promedioCalificaciones = promedioCalificaciones;
    }
    
    
}
