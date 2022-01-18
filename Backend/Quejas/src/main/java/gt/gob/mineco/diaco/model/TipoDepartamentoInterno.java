package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the diaco_departamento_interno database table.
 * 
 */
@Entity
@Table(name="diaco_departamento_interno")
@NamedQueries({
@NamedQuery(name = "TipoDepartamentoInterno.findAll", query = "SELECT t FROM TipoDepartamentoInterno t where t.habilitado='1' "),
@NamedQuery(name = "TipoDepartamentoInterno.findMovHacia", query = "SELECT t FROM TipoDepartamentoInterno t where t.id_departamento_interno in (2,3,4)"),
@NamedQuery(name = "TipoDepartamentoInterno.findMovHaciaVyV", query = "SELECT t FROM TipoDepartamentoInterno t where t.id_departamento_interno in (1,2,4)"),
@NamedQuery(name = "TipoDepartamentoInterno.findMovHaciaSinJefe", query = "SELECT t FROM TipoDepartamentoInterno t where t.id_departamento_interno in (2,4)"),
@NamedQuery(name = "TipoDepartamentoInterno.findMovHaciaJur", query = "SELECT t FROM TipoDepartamentoInterno t where t.id_departamento_interno in (1,3,4)")
})
public class TipoDepartamentoInterno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        //@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_departamento_interno", precision=0)
        private Integer id_departamento_interno;
        @Column(name="nombre")
        private String nombre;
        @Column(name="usuario_adiciono")
        private Integer usuario_adiciono;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_adicion")
        private Date fecha_adicion;
        @Column(name="habilitado")
        private String habilitado;

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }
	
	public TipoDepartamentoInterno() {
	}

    public Integer getId_departamento_interno() {
        return id_departamento_interno;
    }

    public void setId_departamento_interno(Integer id_departamento_interno) {
        this.id_departamento_interno = id_departamento_interno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getUsuario_adiciono() {
        return usuario_adiciono;
    }

    public void setUsuario_adiciono(Integer usuario_adiciono) {
        this.usuario_adiciono = usuario_adiciono;
    }

    public Date getFecha_adicion() {
        return fecha_adicion;
    }

    public void setFecha_adicion(Date fecha_adicion) {
        this.fecha_adicion = fecha_adicion;
    }
        
        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_departamento_interno",this.id_departamento_interno);
            temp.addProperty("nombre",this.nombre);
            return temp.toString()+",";
        }
   
       

        
}
