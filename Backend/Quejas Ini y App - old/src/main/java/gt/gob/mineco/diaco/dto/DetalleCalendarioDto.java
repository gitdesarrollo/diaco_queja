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
public class DetalleCalendarioDto implements Serializable {

    /**
     * Serial de la clase.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Id de la audiencia.
     */
    private int id;
    /**
     * Fecha en que inicia.
     */
    private String start;
    /**
     * Fecha en que termina.
     */
    private String end;
    /**
     * Breve descripcion.
     */
    private String description;
    /**
     * Color a mostrar
     */
    private String color;
    /**
     * Titulo a mostrar
     */
    private String title;
    /**
     * Usuario asociado.
     */
    private String user;
    
    private String no_queja;
    
    private String año;

    /**
     * Crea una nueva instancia de <code>DetalleCalendarioDto</code>.
     */
    public DetalleCalendarioDto() {
        // SonarFix
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "DetalleCalendarioDto{" + "id=" + id + ", start="
                + start + ", end=" + end + ", description=" + description
                + ", color=" + color + ", title=" + title + ", user=" + user + '}';
    }

    /**
     * @return the no_queja
     */
    public String getNo_queja() {
        return no_queja;
    }

    /**
     * @param no_queja the no_queja to set
     */
    public void setNo_queja(String no_queja) {
        this.no_queja = no_queja;
    }

    /**
     * @return the año
     */
    public String getAño() {
        return año;
    }

    /**
     * @param año the año to set
     */
    public void setAño(String año) {
        this.año = año;
    }

}
