package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the departamento database table.
 * 
 */
@Entity
@Table(name="departamento")
@NamedQuery(name = "TipoDepartamento.findAll", query = "SELECT t FROM TipoDepartamento t")

public class TipoDepartamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo_departamento", precision=0)
        private Integer codigo_departamento;
        @Column(name="nombre_departamento")
        private String nombre_departamento;
        @Column(name="habilitado")
        private String habilitado;
	
	public TipoDepartamento() {
	}

    public Integer getCodigo_departamento() {
        return codigo_departamento;
    }

    public void setCodigo_departamento(Integer codigo_departamento) {
        this.codigo_departamento = codigo_departamento;
    }

    public String getNombre_departamento() {
        return nombre_departamento;
    }

    public void setNombre_departamento(String nombre_departamento) {
        this.nombre_departamento = nombre_departamento;
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
            temp.addProperty("codigo_departamento",this.codigo_departamento);
            temp.addProperty("nombre_departamento",this.nombre_departamento);
            return temp.toString()+",";
        }
   
        public JsonObject toJsonelement() {
            JsonObject temp = new JsonObject();
            temp.addProperty("codigo_departamento",this.codigo_departamento);
            temp.addProperty("nombre_departamento",this.nombre_departamento);
            return temp;
        }

        
}
