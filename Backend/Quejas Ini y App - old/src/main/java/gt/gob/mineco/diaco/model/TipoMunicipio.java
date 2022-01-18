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
        query = "SELECT t FROM TipoMunicipio t"),
    @NamedQuery(
        name = "TipoMunicipio.findAllbyDpto",
        query = "SELECT t FROM TipoMunicipio t where t.codigo_departamento = :id_departamento")
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
	
        @JoinColumn(name = "codigo_departamento", referencedColumnName = "codigo_departamento",updatable=false, insertable=false)
        @OneToOne
        private TipoDepartamento tipotipoDpto; 

        
	public TipoMunicipio() {
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

        
        
        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("codigo_municipio",this.codigo_municipio);
            temp.addProperty("codigo_departamento",this.codigo_departamento);
            temp.addProperty("nombre_departamento",this.tipotipoDpto.getNombre_departamento());
            temp.addProperty("nombre_municipio",this.nombre_municipio);

            return temp.toString()+",";
        }
   
        public JsonObject toJsonelement() {
            JsonObject temp = new JsonObject();
            temp.addProperty("codigo_municipio",this.codigo_municipio);
            temp.addProperty("codigo_departamento",this.codigo_departamento);
            temp.addProperty("nombre_departamento",this.tipotipoDpto.getNombre_departamento());
            temp.addProperty("nombre_municipio",this.nombre_municipio);
            return temp;
        }

        
}
