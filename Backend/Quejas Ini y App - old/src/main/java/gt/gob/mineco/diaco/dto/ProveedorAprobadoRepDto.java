/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mps
 */
@XmlRootElement(name = "REP_LEG")

public class ProveedorAprobadoRepDto {    
    private String nitrepleg;    
    private String nomrepleg;

    @XmlElement  (name="NIT_REP_LEG")
    public String getNitrepleg() {
        return nitrepleg;
    }

    public void setNitrepleg(String nitrepleg) {
        this.nitrepleg = nitrepleg;
    }

    @XmlElement  (name="NOM_REP_LEG")
    public String getNomrepleg() {
        return nomrepleg;
    }

    public void setNomrepleg(String nomrepleg) {
        this.nomrepleg = nomrepleg;
    }
    
    
}
