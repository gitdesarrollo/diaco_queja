/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.util;

import com.google.gson.JsonObject;

public class FormSucursal {
    
    private Integer id_sucursal;
    private Integer id_proveedor;
    private Integer codigo_departamento;
    private Integer codigo_municipio;
    private String direccion;
    private String zona;
    private Integer telefono;
    private String nombre_proveedor;
    private String nombre_departamento;
    private String nombre_municipio;
    private String usuario;
    private String habilitado;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
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

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public String getNombre_departamento() {
        return nombre_departamento;
    }

    public void setNombre_departamento(String nombre_departamento) {
        this.nombre_departamento = nombre_departamento;
    }

    public String getNombre_municipio() {
        return nombre_municipio;
    }

    public void setNombre_municipio(String nombre_municipio) {
        this.nombre_municipio = nombre_municipio;
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
            temp.addProperty("nombre_proveedor",this.nombre_proveedor);
             temp.addProperty("nombre_departamento",this.nombre_departamento);
              temp.addProperty("nombre_municipio",this.nombre_municipio);
           temp.addProperty("habilitado",this.habilitado);
           temp.addProperty("usuario",this.usuario);
           
           
           
             return temp.toString()+",";
           }
   
    
}
