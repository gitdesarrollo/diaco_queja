/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.model;



import gt.gob.mineco.diaco.model.*;
import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import gt.gob.mineco.diaco.util.CheckNull;
/**
 * The persistent class for the diaco_proveedor database table.
 * 
 */
@Entity
@Table(name="diaco_proveedor_comun")
@NamedQuery(name="TipoProveedorComun.findAll", query="SELECT t FROM TipoProveedorComun t ")
//inner join t.tipopasoqueja og where og.id_queja_paso=:usr
public class TipoProveedorComun implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_proveedor_comun", precision=0) 
        private Integer id_proveedor_comun;
        @Column(name="proveedor")
        private String proveedor;
        @Column(name="usuario_adiciono")        
	private String usuario_adiciono;
        @Column(name="usuario_modifico")
        private String usuario_modifico;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_modificacion")
        private Date fecha_modificacion;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_adicion")
        private Date fecha_adicion;
        @Column(name="id_proveedor")
        private Integer id_proveedor;
        
        @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor",updatable=false, insertable=false)
        private TipoProveedor tipoProveedor;

    public Integer getId_proveedor_comun() {
        return id_proveedor_comun;
    }

    public void setId_proveedor_comun(Integer id_proveedor_comun) {
        this.id_proveedor_comun = id_proveedor_comun;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
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

    public Integer getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Integer id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public TipoProveedor getTipoProveedor() {
        return tipoProveedor;
    }

    public void setTipoProveedor(TipoProveedor tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }
        
        
           public String toString() {
            CheckNull checknull = new CheckNull();
            JsonObject temp = new JsonObject();
            temp.addProperty("id_proveedor",this.id_proveedor);
            temp.addProperty("id_proveedor_comun",checknull.cknull(this.id_proveedor_comun));
            temp.addProperty("usuario_modifico",checknull.cknull(this.usuario_modifico));
            temp.addProperty("usuario_adiciono",checknull.cknull(this.usuario_adiciono));
            temp.addProperty("proveedor",checknull.cknull(this.proveedor));
            
          
             return temp.toString()+",";
           }
           
           
             public JsonObject toJsonelement() {
            CheckNull checknull = new CheckNull();
            JsonObject temp = new JsonObject();
           temp.addProperty("id_proveedor",this.id_proveedor);
            temp.addProperty("id_proveedor_comun",checknull.cknull(this.id_proveedor_comun));
            temp.addProperty("usuario_modifico",checknull.cknull(this.usuario_modifico));
            temp.addProperty("usuario_adiciono",checknull.cknull(this.usuario_adiciono));
            temp.addProperty("proveedor",checknull.cknull(this.proveedor));
            
            return temp;
             }
        
}