package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * The persistent class for the diaco_repositorio database table.
 * 
 */
@Entity
@Table(name="diaco_repositorio")
@NamedQuery(name="TipoRepositorio.findActive", query="SELECT t FROM TipoRepositorio t WHERE t.activo = 1 and t.tipo = :tipo order by t.fecha_creado")
public class TipoRepositorio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
        private Integer id;        
	@Column(name="nombre")
	private String nombre;
        @Column(name="repositorio")
	private String repositorio;
        @Column(name="activo")
	private boolean activo;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_creado")        
        private Date fecha_creado;
        @Column(name="tipo")
	private String tipo;

        public TipoRepositorio() {
	}

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
        
        public Date getFecha_creado() {
            return fecha_creado;
        }

        public void setFecha_creado(Date fecha_creado) {
            this.fecha_creado = fecha_creado;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getRepositorio() {
            return repositorio;
        }

        public void setRepositorio(String repositorio) {
            this.repositorio = repositorio;
        }

        public boolean isActivo() {
            return activo;
        }

        public void setActivo(boolean activo) {
            this.activo = activo;
        }
        
            @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id",this.id);
            temp.addProperty("nombre",this.nombre);   
            temp.addProperty("repositorio",this.repositorio);
            temp.addProperty("activo",this.activo);
            return temp.toString()+",";
        }
           
        public JsonObject toJsonelement() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id",this.id);
            temp.addProperty("nombre",this.nombre);   
            temp.addProperty("repositorio",this.repositorio);
            temp.addProperty("activo",this.activo);
            return temp;
        }
        
}
