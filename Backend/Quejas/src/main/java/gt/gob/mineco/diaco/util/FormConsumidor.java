/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.util;

import com.google.gson.JsonObject;
import java.util.Date;

public class FormConsumidor {
        private Integer id_consumidor;
        
        private Integer tipo_consumidor;
        private String nit_consumidor;
	private String documento_identificacion;
        private String genero;
        private String direccion_calle;
        private String direccion_avenida;
        private String direccion_zona;
        private String callecilla;
        
        private String habilitado_notificacion_electronica;
        private Date fecha_adicion;
        private String usuario_adiciono;
        private String usuario_modifico;
        private String nombre1;
        private String nombre2;
        private String nombre3;
        private String apellido1;
        private String apellido2;
        private String apellido_casada;
        
        private Integer id_motivo_queja;
        private String nombre_motivo_queja;        
      private Integer codigo_municipio;
      private String nombre_municipio;
        private Integer codigo_departamento;
        private String nombre_departamento;
        private Integer sede_diaco_cercana;
        private String nombre_sede;
        private Integer nacionalidad;
        private String nombre_pais;
        private Integer id_pais;
        private Integer tipo_documento;
        private Integer id_etnia;
        private String nombre_etnia;
        private String domicilio;  
        private String direccion_detalle;
        private String telefono;
        private String referencia;
        private String correo_electronico1;
        private String correo_electronico2;
        private String direccion;
        private String representante_legal;
        private String nombre_empresa;
        private String razon_social;
        private String token;
        private String habilitado;
        private String autorizoPublicar;
        private String nombre_genero;
        private String nombre_nacionalidad;
        private String nombre_tipo_consumidor;

    public String getCallecilla() {
        return callecilla;
    }

    public void setCallecilla(String callecilla) {
        this.callecilla = callecilla;
    }

        
        
        
    public String getNombre_tipo_consumidor() {
        return nombre_tipo_consumidor;
    }

    public void setNombre_tipo_consumidor(String nombre_tipo_consumidor) {
        this.nombre_tipo_consumidor = nombre_tipo_consumidor;
    }
        
        

    public String getNombre_genero() {
        return nombre_genero;
    }

    public void setNombre_genero(String nombre_genero) {
        this.nombre_genero = nombre_genero;
    }

    public String getNombre_nacionalidad() {
        return nombre_nacionalidad;
    }

    public void setNombre_nacionalidad(String nombre_nacionalidad) {
        this.nombre_nacionalidad = nombre_nacionalidad;
    }

        
        
    public String getAutorizoPublicar() {
        return autorizoPublicar;
    }

    public void setAutorizoPublicar(String autorizoPublicar) {
        this.autorizoPublicar = autorizoPublicar;
    }
     

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDireccion_detalle() {
        return direccion_detalle;
    }

    public void setDireccion_detalle(String direccion_detalle) {
        this.direccion_detalle = direccion_detalle;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCorreo_electronico1() {
        return correo_electronico1;
    }

    public void setCorreo_electronico1(String correo_electronico1) {
        this.correo_electronico1 = correo_electronico1;
    }

    public String getCorreo_electronico2() {
        return correo_electronico2;
    }

    public void setCorreo_electronico2(String correo_electronico2) {
        this.correo_electronico2 = correo_electronico2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRepresentante_legal() {
        return representante_legal;
    }

    public void setRepresentante_legal(String representante_legal) {
        this.representante_legal = representante_legal;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    
    
    
    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getNombre_etnia() {
        return nombre_etnia;
    }

    public void setNombre_etnia(String nombre_etnia) {
        this.nombre_etnia = nombre_etnia;
    }

        
        
        
    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getId_etnia() {
        return id_etnia;
    }

    public void setId_etnia(Integer id_etnia) {
        this.id_etnia = id_etnia;
    }
        

    public Integer getId_pais() {
        return id_pais;
    }

    public Integer getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(Integer tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public void setId_pais(Integer id_pais) {
        this.id_pais = id_pais;
    }

    public Integer getId_consumidor() {
        return id_consumidor;
    }

    public void setId_consumidor(Integer id_consumidor) {
        this.id_consumidor = id_consumidor;
    }

    public Integer getTipo_consumidor() {
        return tipo_consumidor;
    }

    public void setTipo_consumidor(Integer tipo_consumidor) {
        this.tipo_consumidor = tipo_consumidor;
    }

    public String getNit_consumidor() {
        return nit_consumidor;
    }

    public void setNit_consumidor(String nit_consumidor) {
        this.nit_consumidor = nit_consumidor;
    }

    public String getDocumento_identificacion() {
        return documento_identificacion;
    }

    public void setDocumento_identificacion(String documento_identificacion) {
        this.documento_identificacion = documento_identificacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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

    public String getHabilitado_notificacion_electronica() {
        return habilitado_notificacion_electronica;
    }

    public void setHabilitado_notificacion_electronica(String habilitado_notificacion_electronica) {
        this.habilitado_notificacion_electronica = habilitado_notificacion_electronica;
    }

    public Date getFecha_adicion() {
        return fecha_adicion;
    }

    public void setFecha_adicion(Date fecha_adicion) {
        this.fecha_adicion = fecha_adicion;
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

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getNombre3() {
        return nombre3;
    }

    public void setNombre3(String nombre3) {
        this.nombre3 = nombre3;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getApellido_casada() {
        return apellido_casada;
    }

    public void setApellido_casada(String apellido_casada) {
        this.apellido_casada = apellido_casada;
    }

    public Integer getId_motivo_queja() {
        return id_motivo_queja;
    }

    public void setId_motivo_queja(Integer id_motivo_queja) {
        this.id_motivo_queja = id_motivo_queja;
    }

    public String getNombre_motivo_queja() {
        return nombre_motivo_queja;
    }

    public void setNombre_motivo_queja(String nombre_motivo_queja) {
        this.nombre_motivo_queja = nombre_motivo_queja;
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

    public Integer getSede_diaco_cercana() {
        return sede_diaco_cercana;
    }

    public void setSede_diaco_cercana(Integer sede_diaco_cercana) {
        this.sede_diaco_cercana = sede_diaco_cercana;
    }

    public String getNombre_sede() {
        return nombre_sede;
    }

    public void setNombre_sede(String nombre_sede) {
        this.nombre_sede = nombre_sede;
    }

    public Integer getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Integer nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getNombre_pais() {
        return nombre_pais;
    }

    public void setNombre_pais(String nombre_pais) {
        this.nombre_pais = nombre_pais;
    }
        
    
    
      @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_consumidor",this.id_consumidor);
            String nombrecompleto=this.nombre1+' '+this.nombre2+' '+this.nombre3+' '+this.apellido1+' '+this.apellido2+' '+this.apellido_casada;
           temp.addProperty("nombre1",this.nombre1);
           temp.addProperty("nombre2",this.nombre2);
           temp.addProperty("nombre3",this.nombre3);
           temp.addProperty("apellido1",this.apellido1);
           temp.addProperty("apellido2",this.apellido2);
           temp.addProperty("apellido_casada",this.apellido_casada);
            temp.addProperty("nombre_consumidor",nombrecompleto.replaceAll("null", "").replaceAll("\\s+", " ").trim());
            temp.addProperty("nit",this.nit_consumidor);
            temp.addProperty("nit_consumidor",this.nit_consumidor);
            
            temp.addProperty("doc_identificacion",this.documento_identificacion);
            temp.addProperty("documento_identificacion",this.documento_identificacion);
           
            temp.addProperty("direccion_avenida", this.direccion_avenida);
            temp.addProperty("direccion_calle",this.direccion_calle);
            temp.addProperty("direccion_zona",this.direccion_zona);
            temp.addProperty("genero",this.genero);
            temp.addProperty("habilitado_notificacion_electronica",this.habilitado_notificacion_electronica);
            
            temp.addProperty("codigo_municipio",this.codigo_municipio);
            temp.addProperty("codigo_departamento",this.codigo_departamento);
            temp.addProperty("sede_diaco_cercana",this.sede_diaco_cercana);
            temp.addProperty("nacionalidad", this.nacionalidad);
            temp.addProperty("id_motivo_queja",this.id_motivo_queja);
            
            temp.addProperty("nombre_municipio",this.nombre_municipio);
            temp.addProperty("nombre_departamento",this.nombre_departamento);
            temp.addProperty("nombre_motivo_queja",this.nombre_motivo_queja);
            temp.addProperty("nombre_sede",this.nombre_sede);
            temp.addProperty("nombre_pais", this.nombre_pais);
            temp.addProperty("nombre_etnia",this.nombre_etnia);
            temp.addProperty("autorizoPublicar",this.autorizoPublicar);
            temp.addProperty("nombre_genero", this.nombre_genero);
            temp.addProperty("nombre_nacionalidad",this.nombre_nacionalidad);
            temp.addProperty("nombre_tipo_consumidor",this.nombre_tipo_consumidor);
            temp.addProperty("telefono", this.telefono);
            temp.addProperty("domicilio",this.domicilio);
            temp.addProperty("direccion_detalle",this.direccion_detalle);
            temp.addProperty("correo_electronico1",this.correo_electronico1);
            temp.addProperty("correo_electronico2",this.correo_electronico2);
              temp.addProperty("tipo_consumidor",this.tipo_consumidor);
              temp.addProperty("nombre_tipo_consumidor",this.nombre_tipo_consumidor);
            temp.addProperty("id_etnia",this.id_etnia);
            temp.addProperty("nombre_etnia",this.nombre_etnia);
            temp.addProperty("id_pais",this.id_pais);
            temp.addProperty("nombre_pais",this.nombre_pais);
             temp.addProperty("tipo_documento",this.tipo_documento);
             temp.addProperty("callecilla",this.callecilla);
             temp.addProperty("nombre_empresa",this.nombre_empresa);
             temp.addProperty("razon_social",this.razon_social);
             temp.addProperty("representante_legal",this.representante_legal);
             temp.addProperty("referencia",this.referencia);
             temp.addProperty("direccion",this.direccion);
             
             
             
             
            
            return temp.toString()+",";
        }
      

}
