package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import gt.gob.mineco.diaco.model.TipoDepartamento;

/**
 * The persistent class for the municipio database table.
 * 
 */
@Entity
@Table(name="municipio")
@NamedQueries({
    @NamedQuery(
        name = "TipoMunicipio.findAll",
        query = "SELECT t FROM TipoMunicipio t where t.habilitado='1'"),
    @NamedQuery(
        name = "TipoMunicipio.findAllbyDpto",
        query = "SELECT t FROM TipoMunicipio t where t.codigo_departamento = :id_departamento and t.habilitado='1'")
})

public class TipoMunicipio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo_municipio", precision=0)
        private Integer codigo_municipio;
        @Column(name="codigo_departamento", precision=0)
        private Integer codigo_departamento;
        @Column(name="nombre_municipio")
        private String nombre_municipio;
        @Column(name="habilitado")
        private String habilitado;
        @Column(name="usuario_adiciono")
        private String usuario_adiciono;
        @Column(name="usuario_modifico")
        private String usuario_modifico;
        
        @Column(name="fecha_modificacion")
        @Temporal(javax.persistence.TemporalType.TIMESTAMP)
        private Date fecha_modificacion;
        
        @Column(name="fecha_adicion")
        @Temporal(javax.persistence.TemporalType.TIMESTAMP)
        private Date fecha_adicion;
        
        
        @JoinColumn(name = "codigo_departamento", referencedColumnName = "codigo_departamento",updatable=false, insertable=false)
        @OneToOne
        private TipoDepartamento tipotipoDpto; 

        
	public TipoMunicipio() {
	}

    public String getUsuario_adiciono() {
        return usuario_adiciono;
    }

    public void setUsuario_adiciono(String usuario_adiciono) {
        this.usuario_adiciono = usuario_adiciono;
    }

    public String getUsuario_modifico() {
        return usuario_modifico;
    }

    public void setUsuario_modifico(String usuario_modifico) {
        this.usuario_modifico = usuario_modifico;
    }

    public Date getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(Date fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public Date getFecha_adicion() {
        return fecha_adicion;
    }

    public void setFecha_adicion(Date fecha_adicion) {
        this.fecha_adicion = fecha_adicion;
    }

    public Integer getCodigo_municipio() {
        return codigo_municipio;
    }

    public void setCodigo_municipio(Integer codigo_municipio) {
        this.codigo_municipio = codigo_municipio;
    }

    public Integer getCodigo_departamento() {
        return codigo_departamento;
    }

    public void setCodigo_departamento(Integer codigo_departamento) {
        this.codigo_departamento = codigo_departamento;
    }

    public String getNombre_municipio() {
        return nombre_municipio;
    }

    public void setNombre_municipio(String nombre_municipio) {
        this.nombre_municipio = nombre_municipio;
    }

    public TipoDepartamento getTipotipoDpto() {
        return tipotipoDpto;
    }

    public void setTipotipoDpto(TipoDepartamento tipotipoDpto) {
        this.tipotipoDpto = tipotipoDpto;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

        
        
        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("codigo_municipio",this.codigo_municipio);
            temp.addProperty("codigo_departamento",this.codigo_departamento);
            temp.addProperty("nombre_departamento",this.tipotipoDpto.getNombre_departamento());
            temp.addProperty("nombre_municipio",this.nombre_municipio);
            temp.addProperty("habilitado",this.habilitado);
            temp.addProperty("usuario_adiciono", this.usuario_adiciono);
            temp.addProperty("usuario_modifico",this.usuario_modifico);
            return temp.toString()+",";
        }
   
        public JsonObject toJsonelement() {
            JsonObject temp = new JsonObject();
            temp.addProperty("codigo_municipio",this.codigo_municipio);
            temp.addProperty("codigo_departamento",this.codigo_departamento);
            temp.addProperty("nombre_departamento",this.tipotipoDpto.getNombre_departamento());
            temp.addProperty("nombre_municipio",this.nombre_municipio);
            temp.addProperty("habilitado",this.habilitado);
            temp.addProperty("usuario_adiciono", this.usuario_adiciono);
            temp.addProperty("usuario_modifico",this.usuario_modifico);
          
            return temp;
        }

        
}
