package gt.gob.mineco.diaco.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import gt.gob.mineco.diaco.model.TipoFlujoGuia;
/**
 * The persistent class for the View_MainQueja database view.
 * 
 */
@Entity
@Table(name="View_MainQueja")
@NamedQueries({
    @NamedQuery(
        name = "TipoViewMainQueja.noFilter",
        query = "SELECT v FROM TipoViewMainQueja v ORDER BY v.fecha_queja desc"),
})
public class TipoViewMainQueja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="TIPOARTICULO_ID_GENERATOR", sequenceName="TIPOARTICULO_SEQ", allocationSize=1)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPOARTICULO_ID_GENERATOR")
	private Integer id_queja;
        @Temporal(TemporalType.TIMESTAMP)
        private Date fecha_queja; 
	private String nombre_consumidor;
        private String nombre_proveedor;
        private Integer id_estado_queja;
        private Integer id_proveedor;
        private String detalle_queja;
        private String solicita_que;
        private Integer id_departamento;
        private Integer id_municipio;
	@JoinColumn(name = "id_queja", referencedColumnName = "id_queja",updatable=false, insertable=false)
        @OneToOne
        private TipoFlujoGuia tipoflujoguia;      
        
	public TipoViewMainQueja() {
	}

    public Integer getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(Integer id_departamento) {
        this.id_departamento = id_departamento;
    }

    public Integer getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(Integer id_municipio) {
        this.id_municipio = id_municipio;
    }

        
        
    public String getDetalle_queja() {
        return detalle_queja;
    }

    public void setDetalle_queja(String detalle_queja) {
        this.detalle_queja = detalle_queja;
    }

    public String getSolicita_que() {
        return solicita_que;
    }

    public void setSolicita_que(String solicita_que) {
        this.solicita_que = solicita_que;
    }

        
        
        public Integer getId_proveedor() {
            return id_proveedor;
        }

        public void setId_proveedor(Integer id_proveedor) {
            this.id_proveedor = id_proveedor;
        }
        
	public Integer getId_queja() {
		return this.id_queja;
	}

	public void setId_queja(Integer id_queja) {
		this.id_queja = id_queja;
	}

    public Date getFecha_queja() {
        return fecha_queja;
    }

    public void setFecha_queja(Date fecha_queja) {
        this.fecha_queja = fecha_queja;
    }

    public String getNombre_consumidor() {
        return nombre_consumidor;
    }

    public void setNombre_consumidor(String nombre_consumidor) {
        this.nombre_consumidor = nombre_consumidor;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public Integer getId_estado_queja() {
        return id_estado_queja;
    }

    public void setId_estado_queja(Integer id_estado_queja) {
        this.id_estado_queja = id_estado_queja;
    }

        @Override
    public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_queja",this.id_queja);
            temp.addProperty("fecha_queja",this.fecha_queja.toString());
            temp.addProperty("nombre_consumidor",this.nombre_consumidor);
            temp.addProperty("nombre_proveedor",this.nombre_proveedor);
            temp.addProperty("id_estado_queja",this.id_estado_queja);
            temp.addProperty("detalle_queja",this.detalle_queja);
            temp.addProperty("solicita_que",this.solicita_que);            
            
            JsonArray flujoguia = new JsonArray();
            flujoguia.add(tipoflujoguia.toJsonelement());
            temp.add("tipoflujoguia",flujoguia);
            return temp.toString()+",";
    }

    
}
