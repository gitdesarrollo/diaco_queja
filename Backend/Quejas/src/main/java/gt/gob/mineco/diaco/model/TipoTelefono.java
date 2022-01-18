package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import gt.gob.mineco.diaco.model.TipoCatalogo;
/**
 * The persistent class for the telefono database table.
 * 
 */
@Entity
@Table(name="diaco_telefono")
@NamedQueries({
    @NamedQuery(
        name = "TipoTelefono.findAllC",
        query = "SELECT t FROM TipoTelefono t where t.id_consumidor = :id_consumidor"),
    @NamedQuery(
        name = "TipoTelefono.findAllP",
        query = "SELECT t FROM TipoTelefono t where t.id_proveedor = :id_proveedor")
})
public class TipoTelefono implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_telefono", precision=0)
        private Integer id_telefono;
        @Column(name="id_consumidor")
        private Integer id_consumidor;
        @Column(name="id_proveedor")
        private Integer id_proveedor;
        @Column(name="telefono")
        private String telefono;
        @Column(name="tipo_telefono")
        private Integer tipo_telefono;
        @JoinColumn(name = "tipo_telefono", referencedColumnName = "id_catalogo",updatable=false, insertable=false)
        @OneToOne
        private TipoCatalogo tipocatalogo; 
        @Column(name="usuario_adiciono")
        private Integer usuario_adiciono;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_adicion")
        private Date fecha_adicion;
        @Column(name="usuario_modifico")
        private Integer usuario_modifico;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_modificacion")
        private Date fecha_modificacion;
	
	public TipoTelefono() {
	}

    public Integer getId_telefono() {
        return id_telefono;
    }

    public void setId_telefono(Integer id_telefono) {
        this.id_telefono = id_telefono;
    }

    public Integer getId_consumidor() {
        return id_consumidor;
    }

    public void setId_consumidor(Integer id_consumidor) {
        this.id_consumidor = id_consumidor;
    }

    public Integer getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Integer id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getTipo_telefono() {
        return tipo_telefono;
    }

    public void setTipo_telefono(Integer tipo_telefono) {
        this.tipo_telefono = tipo_telefono;
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

    public Integer getUsuario_modifico() {
        return usuario_modifico;
    }

    public void setUsuario_modifico(Integer usuario_modifico) {
        this.usuario_modifico = usuario_modifico;
    }

    public Date getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(Date fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public TipoCatalogo getTipocatalogo() {
        return tipocatalogo;
    }

    public void setTipocatalogo(TipoCatalogo tipocatalogo) {
        this.tipocatalogo = tipocatalogo;
    }

    
        
        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_telefono",this.id_telefono);
            temp.addProperty("telefono",this.telefono);
            temp.addProperty("tipo_telefono",this.tipocatalogo.getDescripcion_catalogo());
            return temp.toString()+",";
        }
        
        public JsonObject toJsonelement() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_telefono",this.id_telefono);
            temp.addProperty("telefono",this.telefono);
            temp.addProperty("tipo_telefono",this.tipocatalogo.getDescripcion_catalogo());
            return temp;
        }
}
