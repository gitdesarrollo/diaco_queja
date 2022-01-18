/*
 *  Copyright (C) 2019-2020 Ittraktys.com
 *  Todos los derechos reservados.
 *  contacto@ittraktys.com
 *  
 *  Este documento esta protegido por derechos de autor.
 *  No puede ser copiado y/o distribuido por
 *  cualquier medio sin el permiso debido de Ittraktys.
 *  _ _   _             _    _            
 * (_) |_| |_ _ __ __ _| | _| |_ _   _ ___ 
 * | | __| __| '__/ _` | |/ / __| | | / __|
 * | | |_| |_| | | (_| |   <| |_| |_| \__ \
 * |_|\__|\__|_|  \__,_|_|\_\\__|\__, |___/
 *                                |___/     
 */
package gt.gob.mineco.diaco.dto;

import java.io.Serializable;

/**
 * Esta clase define un conjunto de atributos que encapsulan la informacion para
 * ser transferida a traves de las distintas capas del sistema.
 *
 * @author Yeremy Anthony Chun Quinillo
 * <a href="mailto:yachunqu@ittraktys.com">Anthony Chun</a>
 * @since 24/04/2020
 * @version 1.0
 */
public class CalendarioDto implements Serializable {

    /**
     * Serial de la clase.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Usuario de la operacion.
     */
    private int usuarioOperacion;
    /**
     * Id de la queja.
     */
    private int idQueja;
    /**
     * Fecha inicial desde donde se consulta el calendario.
     */
    private String fechaInicio;
    /**
     * Fecha final desde donde se consulta el calendario.
     */
    private String fechaFinal;

    /**
     * Crea una nueva instancia de <code>CalendarioDto</code>.
     */
    public CalendarioDto() {
        // SonarFix
    }

    public int getUsuarioOperacion() {
        return usuarioOperacion;
    }

    public void setUsuarioOperacion(int usuarioOperacion) {
        this.usuarioOperacion = usuarioOperacion;
    }

    public int getIdQueja() {
        return idQueja;
    }

    public void setIdQueja(int idQueja) {
        this.idQueja = idQueja;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    @Override
    public String toString() {
        return "CalendarioDto{" + "usuarioOperacion=" + usuarioOperacion
                + ", idQueja=" + idQueja + ", fechaInicio=" + fechaInicio
                + ", fechaFinal=" + fechaFinal + '}';
    }
}
