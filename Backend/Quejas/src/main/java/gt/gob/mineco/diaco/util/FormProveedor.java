/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.util;

import com.google.gson.JsonObject;

public class FormProveedor {

        private Integer id_proveedor;
        private String nombre;
	private String direccion;
        private String nit_proveedor;
	private String habilitado_conciliacion_previa;
	private Integer codigo_departamento;
        private String nombre_departamento;
	private String habilitado_notificacion_electronica;
        private String habilitado;
	private Integer codigo_municipio;
        private String nombre_municipio;
       private String primer_nombre;
        private String segundo_nombre;
        private String tercer_nombre;
         private String primer_apellido;
        private String segundo_apellido;
        private String apellido_casada;
        private String direccion_calle;
        private String direccion_avenida;
        private String direccion_zona;
        private String direccion_detalle;
        private String nombre_empresa;
        private String razon_social;
        private String email;
        private Integer tipo_proveedor;
        private Integer id_actividad_economica; 
        private String nombre_actividad_economica;
        private Integer id_tipo_comercio;
        private String tipo_comercio;
        private String telefono;
        private boolean servicio_publico;
        private String callecilla;
        private boolean permite_calificacion;
        private String token;
        private String usuario;

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
        
        

    public boolean isPermite_calificacion() {
        return permite_calificacion;
    }

    public void setPermite_calificacion(boolean permite_calificacion) {
        this.permite_calificacion = permite_calificacion;
    }
        
        

    public String getCallecilla() {
        if(direccion.contains("calle")) {
            callecilla="calle";
        }
        else {
            callecilla="avenida";
        }
        return callecilla;
    }

    public void setCallecilla(String callecilla) {
        this.callecilla = callecilla;
    }

        
        
    public boolean isServicio_publico() {
        return servicio_publico;
    }

    public void setServicio_publico(boolean servicio_publico) {
        this.servicio_publico = servicio_publico;
    }
        
        

    public Integer getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Integer id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNit_proveedor() {
        return nit_proveedor;
    }

    public void setNit_proveedor(String nit_proveedor) {
        this.nit_proveedor = nit_proveedor;
    }

    public String getHabilitado_conciliacion_previa() {
        return habilitado_conciliacion_previa;
    }

    public void setHabilitado_conciliacion_previa(String habilitado_conciliacion_previa) {
        this.habilitado_conciliacion_previa = habilitado_conciliacion_previa;
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

    public String getHabilitado_notificacion_electronica() {
        return habilitado_notificacion_electronica;
    }

    public void setHabilitado_notificacion_electronica(String habilitado_notificacion_electronica) {
        this.habilitado_notificacion_electronica = habilitado_notificacion_electronica;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    public Integer getCodigo_municipio() {
        return codigo_municipio;
    }

    public void setCodigo_municipio(Integer codigo_municipio) {
        this.codigo_municipio = codigo_municipio;
    }

    public String getNombre_municipio() {
        return nombre_municipio;
    }

    public void setNombre_municipio(String nombre_municipio) {
        this.nombre_municipio = nombre_municipio;
    }

    public String getPrimer_nombre() {
        return primer_nombre;
    }

    public void setPrimer_nombre(String primer_nombre) {
        this.primer_nombre = primer_nombre;
    }

    public String getSegundo_nombre() {
        return segundo_nombre;
    }

    public void setSegundo_nombre(String segundo_nombre) {
        this.segundo_nombre = segundo_nombre;
    }

    public String getTercer_nombre() {
        return tercer_nombre;
    }

    public void setTercer_nombre(String tercer_nombre) {
        this.tercer_nombre = tercer_nombre;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public String getApellido_casada() {
        return apellido_casada;
    }

    public void setApellido_casada(String apellido_casada) {
        this.apellido_casada = apellido_casada;
    }

    public String getDireccion_calle() {
        return direccion_calle;
    }

    public void setDireccion_calle(String direccion_calle) {
        this.direccion_calle = direccion_calle;
    }

    public String getDireccion_avenida() {
        return direccion_avenida;
    }

    public void setDireccion_avenida(String direccion_avenida) {
        this.direccion_avenida = direccion_avenida;
    }

    public String getDireccion_zona() {
        return direccion_zona;
    }

    public void setDireccion_zona(String direccion_zona) {
        this.direccion_zona = direccion_zona;
    }

    public String getDireccion_detalle() {
        return direccion_detalle;
    }

    public void setDireccion_detalle(String direccion_detalle) {
        this.direccion_detalle = direccion_detalle;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTipo_proveedor() {
        return tipo_proveedor;
    }

    public void setTipo_proveedor(Integer tipo_proveedor) {
        this.tipo_proveedor = tipo_proveedor;
    }

    public Integer getId_actividad_economica() {
        return id_actividad_economica;
    }

    public void setId_actividad_economica(Integer id_actividad_economica) {
        this.id_actividad_economica = id_actividad_economica;
    }

    public String getNombre_actividad_economica() {
        return nombre_actividad_economica;
    }

    public void setNombre_actividad_economica(String nombre_actividad_economica) {
        this.nombre_actividad_economica = nombre_actividad_economica;
    }

    public Integer getId_tipo_comercio() {
        return id_tipo_comercio;
    }

    public void setId_tipo_comercio(Integer id_tipo_comercio) {
        this.id_tipo_comercio = id_tipo_comercio;
    }

    public String getTipo_comercio() {
        return tipo_comercio;
    }

    public void setTipo_comercio(String tipo_comercio) {
        this.tipo_comercio = tipo_comercio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
        
        
    @Override
        public String toString() {
            CheckNull checknull = new CheckNull();
            JsonObject temp = new JsonObject();
            temp.addProperty("id_proveedor",this.id_proveedor);
            temp.addProperty("nombre",checknull.cknull(this.nombre));
            temp.addProperty("direccion",checknull.cknull(this.direccion));
            temp.addProperty("nit_proveedor",checknull.cknull(this.nit_proveedor));
            temp.addProperty("codigo_departamento",checknull.cknull(this.codigo_departamento));
            temp.addProperty("codigo_municipio",checknull.cknull(this.codigo_municipio));
            temp.addProperty("primer_nombre",checknull.cknull(this.primer_nombre));
            temp.addProperty("segundo_nombre",checknull.cknull(this.segundo_nombre));
            temp.addProperty("tercer_nombre",checknull.cknull(this.tercer_nombre));
            temp.addProperty("primer_apellido",checknull.cknull(this.primer_apellido));
            temp.addProperty("segundo_apellido",checknull.cknull(this.segundo_apellido));
            temp.addProperty("apellido_casada",checknull.cknull(this.apellido_casada));
            temp.addProperty("direccion_calle",checknull.cknull(this.direccion_calle));
            temp.addProperty("direccion_avenida",checknull.cknull(this.direccion_avenida));
            temp.addProperty("direccion_zona",checknull.cknull(this.direccion_zona));
            temp.addProperty("direccion",checknull.cknull(this.direccion));
            temp.addProperty("direccion_detalle",checknull.cknull(this.direccion_detalle));
            temp.addProperty("nombre_empresa",checknull.cknull(this.nombre_empresa));
            temp.addProperty("razon_social",checknull.cknull(this.razon_social));
            temp.addProperty("email",checknull.cknull(this.email));
            temp.addProperty("tipo_proveedor",checknull.cknull(this.tipo_proveedor));
            temp.addProperty("id_tipo_comercio",checknull.cknull(this.id_tipo_comercio));
            temp.addProperty("id_actividad_economica",checknull.cknull(this.id_actividad_economica));
            temp.addProperty("telefono",checknull.cknull(this.telefono));
            
            temp.addProperty("nombre_departamento",checknull.cknull(this.nombre_departamento));
            temp.addProperty("nombre_municipio",checknull.cknull(this.nombre_municipio));
            temp.addProperty("nombre_actividad_economica",checknull.cknull(this.nombre_actividad_economica));
            temp.addProperty("tipo_comercio",checknull.cknull(this.tipo_comercio));
            temp.addProperty("habilitado_notificacion_electronica",this.habilitado_notificacion_electronica);
            temp.addProperty("habilitado_conciliacion_previa",this.habilitado_conciliacion_previa);
                temp.addProperty("servicio_publico",this.servicio_publico);
                temp.addProperty("callecilla",this.callecilla);
                temp.addProperty("permite_calificacion",this.permite_calificacion);
         
            
            return temp.toString()+",";
        }
          
           
        
        
      
    
}
