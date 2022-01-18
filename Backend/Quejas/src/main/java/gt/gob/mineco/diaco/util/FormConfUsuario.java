package gt.gob.mineco.diaco.util;

import java.io.Serializable;
import java.util.Date;

public class FormConfUsuario implements Serializable {
        private String usuario;
        private Integer id_usuario;
        private String nombre;
        private String token;
        private String nit;
        private String dpi;
        private String email;
        private String email2;
        private Integer area;
        private Integer estado;
        private Integer puesto;
        private String telefono;
        private Integer id_sede;
        private Integer glob_paramgen;
        private Integer glob_admusuarios;
        private Integer glob_aprobarpp;
        private Integer glob_catalogos;
        private Integer atcon_rol;
        private Integer atcon_confsistema;
        private Integer atcon_admcolas;
        private Integer atcon_presencial;
        private Integer atcon_call_center;
        private Integer jur_rol;
        private Integer jur_confsistema;
        private Integer jur_admcolas;
        private Integer vyv_rol;
        private Integer vyv_confsistema;
        private Integer vyv_admcolas;
        private Integer sp_rol;
        private Integer sp_confsistema;
        private Integer sp_admcolas;
        private Integer usuario_operacion;
        private Integer glob_confsistema;
        private Integer pp_rol;
        private Integer proveedor;
        private Integer resetpassword;
        
        public FormConfUsuario() {
        }
        
    public Integer getAtcon_call_center() {
        return atcon_call_center;
    }


    public void setAtcon_call_center(Integer atcon_call_center) {
        this.atcon_call_center = atcon_call_center;
    }

    public Integer getAtcon_presencial() {
        return atcon_presencial;
    }

    public void setAtcon_presencial(Integer atcon_presencial) {
        this.atcon_presencial = atcon_presencial;
    }

    public Integer getGlob_catalogos() {
        return glob_catalogos;
    }

    public void setGlob_catalogos(Integer glob_catalogos) {
        this.glob_catalogos = glob_catalogos;
    }

    public Integer getGlob_aprobarpp() {
        return glob_aprobarpp;
    }

    public void setGlob_aprobarpp(Integer glob_aprobarpp) {
        this.glob_aprobarpp = glob_aprobarpp;
    }

    public Integer getResetpassword() {
        return resetpassword;
    }

    public void setResetpassword(Integer resetpassword) {
        this.resetpassword = resetpassword;
    }

    public Integer getProveedor() {
        return proveedor;
    }

    public void setProveedor(Integer proveedor) {
        this.proveedor = proveedor;
    }

    public Integer getPp_rol() {
        return pp_rol;
    }

    public void setPp_rol(Integer pp_rol) {
        this.pp_rol = pp_rol;
    }

    public Integer getGlob_confsistema() {
        return glob_confsistema;
    }

    public void setGlob_confsistema(Integer glob_confsistema) {
        this.glob_confsistema = glob_confsistema;
    }

    public Integer getUsuario_operacion() {
        return usuario_operacion;
    }

    public void setUsuario_operacion(Integer usuario_operacion) {
        this.usuario_operacion = usuario_operacion;
    }

    public Integer getId_sede() {
        return id_sede;
    }

    public void setId_sede(Integer id_sede) {
        this.id_sede = id_sede;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getPuesto() {
        return puesto;
    }

    public void setPuesto(Integer puesto) {
        this.puesto = puesto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getGlob_paramgen() {
        return glob_paramgen;
    }

    public void setGlob_paramgen(Integer glob_paramgen) {
        this.glob_paramgen = glob_paramgen;
    }

    public Integer getGlob_admusuarios() {
        return glob_admusuarios;
    }

    public void setGlob_admusuarios(Integer glob_admusuarios) {
        this.glob_admusuarios = glob_admusuarios;
    }

    public Integer getAtcon_rol() {
        return atcon_rol;
    }

    public void setAtcon_rol(Integer atcon_rol) {
        this.atcon_rol = atcon_rol;
    }

    public Integer getAtcon_confsistema() {
        return atcon_confsistema;
    }

    public void setAtcon_confsistema(Integer atcon_confsistema) {
        this.atcon_confsistema = atcon_confsistema;
    }

    public Integer getAtcon_admcolas() {
        return atcon_admcolas;
    }

    public void setAtcon_admcolas(Integer atcon_admcolas) {
        this.atcon_admcolas = atcon_admcolas;
    }

    public Integer getJur_rol() {
        return jur_rol;
    }

    public void setJur_rol(Integer jur_rol) {
        this.jur_rol = jur_rol;
    }

    public Integer getJur_confsistema() {
        return jur_confsistema;
    }

    public void setJur_confsistema(Integer jur_confsistema) {
        this.jur_confsistema = jur_confsistema;
    }

    public Integer getJur_admcolas() {
        return jur_admcolas;
    }

    public void setJur_admcolas(Integer jur_admcolas) {
        this.jur_admcolas = jur_admcolas;
    }

    public Integer getVyv_rol() {
        return vyv_rol;
    }

    public void setVyv_rol(Integer vyv_rol) {
        this.vyv_rol = vyv_rol;
    }

    public Integer getVyv_confsistema() {
        return vyv_confsistema;
    }

    public void setVyv_confsistema(Integer vyv_confsistema) {
        this.vyv_confsistema = vyv_confsistema;
    }

    public Integer getVyv_admcolas() {
        return vyv_admcolas;
    }

    public void setVyv_admcolas(Integer vyv_admcolas) {
        this.vyv_admcolas = vyv_admcolas;
    }

    public Integer getSp_rol() {
        return sp_rol;
    }

    public void setSp_rol(Integer sp_rol) {
        this.sp_rol = sp_rol;
    }

    public Integer getSp_confsistema() {
        return sp_confsistema;
    }

    public void setSp_confsistema(Integer sp_confsistema) {
        this.sp_confsistema = sp_confsistema;
    }

    public Integer getSp_admcolas() {
        return sp_admcolas;
    }

    public void setSp_admcolas(Integer sp_admcolas) {
        this.sp_admcolas = sp_admcolas;
    }


}
