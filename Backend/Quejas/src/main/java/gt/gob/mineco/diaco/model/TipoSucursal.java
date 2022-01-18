/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import gt.gob.mineco.diaco.util.CheckNull;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "diaco_sucursal")
@NamedQuery(name = "TipoSucursal.findAllByProveedor", query = "SELECT d FROM TipoSucursal d where d.habilitado='1' and d.id_proveedor= :id_proveedor ")
public class TipoSucursal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_sucursal")
    private Integer id_sucursal;
    @Column(name = "id_proveedor")
    private Integer id_proveedor;
        @Column(name = "codigo_departamento")
    private Integer codigo_departamento;
        @Column(name = "codigo_municipio")
    private Integer codigo_municipio;
    
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "zona")
    private String zona;
        @Column(name = "telefono")
    private Integer telefono;
@Column(name="habilitado")
private String habilitado;

@Column(name="fecha_adiciono")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
private Date fecha_adiciono;

@Column(name="fecha_modifico")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
private Date fecha_modifico;

@Column(name="usuario_adiciono")
private String usuario_adiciono;

@Column(name="usuario_modifico")
private String usuario_modifico;




        
     public TipoSucursal() {
         
     }   

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    public Date getFecha_adiciono() {
        return fecha_adiciono;
    }

    public void setFecha_adiciono(Date fecha_adiciono) {
        this.fecha_adiciono = fecha_adiciono;
    }

    public Date getFecha_modifico() {
        return fecha_modifico;
    }

    public void setFecha_modifico(Date fecha_modifico) {
        this.fecha_modifico = fecha_modifico;
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
     
     

    public Integer getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(Integer id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public Integer getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Integer id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public Integer getCodigo_departamento() {
        return codigo_departamento;
    }

    public void setCodigo_departamento(Integer codigo_departamento) {
        this.codigo_departamento = codigo_departamento;
    }

    public Integer getCodigo_municipio() {
        return codigo_municipio;
    }

    public void setCodigo_municipio(Integer codigo_municipio) {
        this.codigo_municipio = codigo_municipio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }


    
    
    
      public String toString() {
            CheckNull checknull = new CheckNull();
            JsonObject temp = new JsonObject();
            temp.addProperty("id_sucursal",this.id_sucursal);
            temp.addProperty("id_proveedor",checknull.cknull(this.id_proveedor));
           temp.addProperty("codigo_departamento",this.codigo_departamento);
            temp.addProperty("codigo_municipio",this.codigo_municipio);
             temp.addProperty("direccion",this.direccion);
              temp.addProperty("zona",this.zona);
               temp.addProperty("telefono",this.telefono);
           
             return temp.toString()+",";
           }
    
}
