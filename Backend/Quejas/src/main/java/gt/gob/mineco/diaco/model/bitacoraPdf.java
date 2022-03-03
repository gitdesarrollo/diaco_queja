package gt.gob.mineco.diaco.model;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.JsonObject;

@Entity(name="bitacora_pdf")
@Table(name="bitacora_pdf")
@NamedQueries({
    @NamedQuery(name="bitacora.findById", query="SELECT t FROM bitacora_pdf t WHERE t.no_queja = :no_queja"),
    @NamedQuery(name="bitacora.findByName", query="SELECT t FROM bitacora_pdf t WHERE t.nombre_archivo = :nombre_archivo"),
    @NamedQuery(name="bitacora.countByName", query="SELECT count(t) FROM bitacora_pdf t WHERE t.nombre_archivo = :nombre_archivo"),
})
public class bitacoraPdf implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", precision = 0)
    private Integer id;

    @Column(name="no_queja")
    private String no_queja;

    @Column(name="nombre_archivo")
    private String nombre_archivo;

    @Column(name="formato")
    private String formato;

    @Column(name="path")
    private String path;


    public bitacoraPdf(){}

    


    /**
     * @return Integer return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
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

    public JsonObject toJsonElement(){
        JsonObject response = new JsonObject();

        response.addProperty("no_queja", this.no_queja);
        response.addProperty("nombre_archivo", this.nombre_archivo);
        response.addProperty("formato", this.formato);
        response.addProperty("path", this.path);
        return response;
    }

    public String toString(){
        JsonObject response = new JsonObject();

        response.addProperty("no_queja", this.no_queja);
        response.addProperty("nombre_archivo", this.nombre_archivo);
        response.addProperty("formato", this.formato);
        response.addProperty("path", this.path);
        return response.toString();
    }

}
