package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the email database table.
 * 
 */
@Entity
@Table(name="diaco_registro")
@NamedQueries({
    @NamedQuery(name = "TipoRegistro.findAll",query = "SELECT t FROM TipoRegistro t"),
    @NamedQuery(name = "TipoRegistro.findbyID",query = "SELECT t FROM TipoRegistro t where t.id_registro = :id_registro")
})
public class TipoRegistro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_registro", precision=0)
        private Integer id_registro;
        @Column(name="nombre")
        private String nombre;
        @Column(name="prefijo")
        private String prefijo;
        @Column(name="correlativo")
        private Integer correlativo;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="creado")
        private Date creado;
        @Column(name="version")
        private String version;
        @Column(name="codigo_correlativo")
        private String codigo_correlativo;
	
	public TipoRegistro() {
	}

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

        
        
    public Integer getId_registro() {
        return id_registro;
    }

    public void setId_registro(Integer id_registro) {
        this.id_registro = id_registro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public Integer getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(Integer correlativo) {
        this.correlativo = correlativo;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

        
        
        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_registro",this.id_registro);
            temp.addProperty("nombre",this.nombre);
            temp.addProperty("prefijo",this.prefijo);
            temp.addProperty("correlativo",this.correlativo);
            temp.addProperty("version",this.version);
            return temp.toString()+",";
        }
   
        public JsonObject toJsonelement() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_registro",this.id_registro);
            temp.addProperty("nombre",this.nombre);
            temp.addProperty("prefijo",this.prefijo);
            temp.addProperty("correlativo",this.correlativo);
            temp.addProperty("version",this.version);
            return temp;
        }

    /**
     * @return the codigo_correlativo
     */
    public String getCodigo_correlativo() {
        return codigo_correlativo;
    }

    /**
     * @param codigo_correlativo the codigo_correlativo to set
     */
    public void setCodigo_correlativo(String codigo_correlativo) {
        this.codigo_correlativo = codigo_correlativo;
    }

        
}
