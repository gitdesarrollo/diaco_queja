package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import gt.gob.mineco.diaco.dao.TipoRepository;
import java.io.Serializable;
import javax.persistence.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import gt.gob.mineco.diaco.util.CheckNull;
import javax.inject.Inject;
/**
 * The persistent class for the diaco_usuario database table.
 * 
 */
@Entity
@Table(name="diaco_usuario")
@NamedQueries({
@NamedQuery(name="TipoUsuario_Conf.findbyID", query="SELECT t FROM TipoUsuario_Conf t where t.id_usuario = :id_usuario"),
@NamedQuery(name="TipoUsuario_Conf.findbyLogin", query="SELECT t FROM TipoUsuario_Conf t where t.login = :login"),
@NamedQuery(name="TipoUsuario_Conf.findbyEmail", query="SELECT t FROM TipoUsuario_Conf t where t.email = :email"),
@NamedQuery(name="TipoUsuario_Conf.findbyLoginExc", query="SELECT t FROM TipoUsuario_Conf t where t.login = :login and t.id_usuario != :idusuario"),
@NamedQuery(name="TipoUsuario_Conf.findbyEmailExc", query="SELECT t FROM TipoUsuario_Conf t where t.email = :email and t.id_usuario != :idusuario"),
@NamedQuery(name="TipoUsuario_Conf.findbyJefe", query="SELECT t FROM TipoUsuario_Conf t where t.id_puesto = :id_puesto order by t.nombre")
})
//inner join t.tipopasoqueja og where og.id_queja_paso=:usr
public class TipoUsuario_Conf implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario", precision=0) 
        private Integer id_usuario;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_adicion")
        private Date fecha_adicion;
        @Column(name="nombre")        
	private String nombre;
        @Column(name="id_sede_diaco")
        private Integer id_sede_diaco;
	@Column(name="login")        
	private String login;
        @Column(name="habilitado")        
	private String habilitado;
        @Column(name="email")        
	private String email;
        @Column(name="dpi")        
	private String dpi;
        @Column(name="nit")        
	private String nit;
        @Column(name="telefono")        
	private String telefono;
        @Column(name="clave")        
	private String clave;
        @Column(name="id_puesto")
        private Integer id_puesto;
        @Column(name="id_proveedor")
        private Integer id_proveedor;
        
        public TipoUsuario_Conf(){}

    public Integer getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Integer id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public Integer getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(Integer id_puesto) {
        this.id_puesto = id_puesto;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Date getFecha_adicion() {
        return fecha_adicion;
    }

    public void setFecha_adicion(Date fecha_adicion) {
        this.fecha_adicion = fecha_adicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId_sede_diaco() {
        return id_sede_diaco;
    }

    public void setId_sede_diaco(Integer id_sede_diaco) {
        this.id_sede_diaco = id_sede_diaco;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
            JsonObject temp = new JsonObject();
            CheckNull checknull=new CheckNull();
            temp.addProperty("id_usuario",this.id_usuario);
            temp.addProperty("fecha_adicion",checknull.cknull(this.fecha_adicion));
            temp.addProperty("nombre",checknull.cknull(this.nombre));
            temp.addProperty("id_sede_diaco",checknull.cknull(this.id_sede_diaco));
            temp.addProperty("login",checknull.cknull(this.login));
            temp.addProperty("habilitado",checknull.cknull(this.habilitado));
            temp.addProperty("email",checknull.cknull(this.email));
            temp.addProperty("dpi",checknull.cknull(this.dpi));
            temp.addProperty("telefono",checknull.cknull(this.telefono));
            temp.addProperty("nit",checknull.cknull(this.nit));
            temp.addProperty("id_puesto",checknull.cknull(this.id_puesto));
           
            return temp.toString()+",";
    }
            
}
