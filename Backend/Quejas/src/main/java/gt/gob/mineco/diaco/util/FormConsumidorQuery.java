package gt.gob.mineco.diaco.util;

import java.io.Serializable;

public class FormConsumidorQuery implements Serializable{
    
    private String no_queja;
    private String anio;

    

    public FormConsumidorQuery(){}

    

    /**
     * @return String return the no_queja
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
     * @return String return the anio
     */
    public String getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(String anio) {
        this.anio = anio;
    }

}
