package gt.gob.mineco.diaco.util;

import java.io.Serializable;

public class ValidateFacData implements Serializable{
    private String nit;
    private String correlativo;

    public ValidateFacData(){};

    

    /**
     * @return String return the nit
     */
    public String getNit() {
        return nit;
    }

    /**
     * @param nit the nit to set
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * @return String return the correlativo
     */
    public String getCorrelativo() {
        return correlativo;
    }

    /**
     * @param correlativo the correlativo to set
     */
    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

}
