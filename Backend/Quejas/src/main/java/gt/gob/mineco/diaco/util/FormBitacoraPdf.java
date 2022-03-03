package gt.gob.mineco.diaco.util;

import java.io.Serializable;

public class FormBitacoraPdf implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private double id;
    private String no_queja;
    private String nombre_archivo;
    private String formato;
    private String path;

    public FormBitacoraPdf(){}



    /**
     * @return double return the id
     */
    public double getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(double id) {
        this.id = id;
    }

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
     * @return String return the nombre_archivo
     */
    public String getNombre_archivo() {
        return nombre_archivo;
    }

    /**
     * @param nombre_archivo the nombre_archivo to set
     */
    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }

    /**
     * @return String return the formato
     */
    public String getFormato() {
        return formato;
    }

    /**
     * @param formato the formato to set
     */
    public void setFormato(String formato) {
        this.formato = formato;
    }

    /**
     * @return String return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

}
