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
 * @since 07/02/2020
 * @version 1.0
 */
public class ConfirmaEmailDto implements Serializable {

    /**
     * Serial de la clase.
     */
    private static final long serialVersionUID = 1L;
    private String llave;

    /**
     * Crea una nueva instancia de <code>ConfirmaEmailDto</code>.
     */
    public ConfirmaEmailDto() {
        // SonarFix
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    @Override
    public String toString() {
        return "ConfirmaEmailDto{" + "llave=" + llave + '}';
    }

}
