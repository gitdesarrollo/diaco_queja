package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the email database table.
 * 
 */
@Entity
@Table(name="diaco_email")
@NamedQueries({
    @NamedQuery(
        name = "TipoEmail.findAllC",
        query = "SELECT t FROM TipoEmail t where t.id_consumidor = :id_consumidor"),
    @NamedQuery(
        name = "TipoEmail.findAllP",
        query = "SELECT t FROM TipoEmail t where t.id_proveedor = :id_proveedor")
})
public class TipoEmail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_mail", precision=0)
        private Integer id_mail;
        @Column(name="id_consumidor")
        private Integer id_consumidor;
        @Column(name="id_proveedor")
        private Integer id_proveedor;
        @Column(name="correo_electronico")
        private String correo_electronico;
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
	
	public TipoEmail() {
	}

    public Integer getId_mail() {
        return id_mail;
    }

    public void setId_mail(Integer id_mail) {
        this.id_mail = id_mail;
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

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
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

        
        
        @Override
        public String toString() {
            //String temp="{¬id_mail¬:" + this.id_mail + ", ¬correo_electronico¬:¬" + this.correo_electronico+"¬},";
            //return temp;//.replace('`', '"');
            JsonObject temp = new JsonObject();
            temp.addProperty("id_mail",this.id_mail);
            temp.addProperty("correo_electronico",this.correo_electronico);
            return temp.toString()+",";
        }
   
        public JsonObject toJsonelement() {
            //String temp="{¬id_telefono¬:" + this.id_telefono + ", ¬telefono¬:¬" + this.telefono+ ", ¬tipo_telefono¬:" + this.tipo_telefono+"},";
            //return temp;//.replace('`', '"');
            JsonObject temp = new JsonObject();
            temp.addProperty("id_mail",this.id_mail);
            temp.addProperty("correo_electronico",this.correo_electronico);
            return temp;
        }

        
}
