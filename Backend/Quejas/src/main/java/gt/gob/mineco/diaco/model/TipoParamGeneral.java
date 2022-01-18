package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * The persistent class for the diaco_parametro_general database table.
 * 
 */
@Entity
@Table(name="diaco_parametro_general")
@NamedQueries({
    @NamedQuery(
        name = "TipoParamGeneral.findAll",
        query = "SELECT t FROM TipoParamGeneral t where t.id_parametro_general = 1")
})
public class TipoParamGeneral implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_parametro_general", precision=0) 
        private Integer id_parametro_general;
        @Column(name = "uma", precision=2)
        private double uma;
        @Column(name="titulo_ministerio")
        private String titulo_ministerio;
        @Column(name="genero_ministro")
        private String genero_ministro;
        @Column(name="nombre_ministro")
        private String nombre_ministro;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_modifico")        
        private Date fecha_modifico;
        @Column(name="usuario_modifico")
        private Integer usuario_modifico;
	
        public TipoParamGeneral() {
	}

    public Integer getId_parametro_general() {
        return id_parametro_general;
    }

    public void setId_parametro_general(Integer id_parametro_general) {
        this.id_parametro_general = id_parametro_general;
    }

    public double getUma() {
        return uma;
    }

    public void setUma(double uma) {
        this.uma = uma;
    }

    public String getTitulo_ministerio() {
        return titulo_ministerio;
    }

    public void setTitulo_ministerio(String titulo_ministerio) {
        this.titulo_ministerio = titulo_ministerio;
    }

    public String getGenero_ministro() {
        return genero_ministro;
    }

    public void setGenero_ministro(String genero_ministro) {
        this.genero_ministro = genero_ministro;
    }

    public String getNombre_ministro() {
        return nombre_ministro;
    }

    public void setNombre_ministro(String nombre_ministro) {
        this.nombre_ministro = nombre_ministro;
    }

    public Date getFecha_modifico() {
        return fecha_modifico;
    }

    public void setFecha_modifico(Date fecha_modifico) {
        this.fecha_modifico = fecha_modifico;
    }

    public Integer getUsuario_modifico() {
        return usuario_modifico;
    }

    public void setUsuario_modifico(Integer usuario_modifico) {
        this.usuario_modifico = usuario_modifico;
    }

        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            if(this.titulo_ministerio == null){
                temp.addProperty("titulo_ministerio","");
            }else{
                temp.addProperty("titulo_ministerio",this.titulo_ministerio);
            }
            if(this.genero_ministro == null){
                temp.addProperty("genero_ministro","");
            }else{
                temp.addProperty("genero_ministro",this.genero_ministro);
            }
            if(this.nombre_ministro == null){
                temp.addProperty("nombre_ministro","");
            }else{
                temp.addProperty("nombre_ministro",this.nombre_ministro);
            }
            temp.addProperty("uma",this.uma);
            return temp.toString()+",";
        }
        
}
