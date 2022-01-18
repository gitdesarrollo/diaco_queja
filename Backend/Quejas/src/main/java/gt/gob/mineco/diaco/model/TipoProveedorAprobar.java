package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import gt.gob.mineco.diaco.util.CheckNull;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * The persistent class for the diaco_proveedor_aprovar database table.
 *
 */
@Entity
@Table(name = "diaco_proveedor_aprovar")
@NamedQueries({
    @NamedQuery(name = "TipoProveedorAprobar.findxEstado", query = "SELECT t FROM TipoProveedorAprobar t where t.estado = :estado order by t.nombre"),
    @NamedQuery(name = "TipoProveedorAprobar.findAll", query = "SELECT t FROM TipoProveedorAprobar t order by t.nombre"),
    @NamedQuery(name = "TipoProveedorAprobar.findByNitProv", query = "SELECT t FROM TipoProveedorAprobar t where t.estado = 'A' and t.nit = :NitProv")
})
//inner join t.tipopasoqueja og where og.id_queja_paso=:usr
public class TipoProveedorAprobar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", precision = 0)
    private Integer id;
    @Column(name = "nit")
    private String nit;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "razon_social")
    private String razon_social;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "departamento")
    private Integer departamento;
    @Column(name = "municipio")
    private Integer municipio;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "correo")
    private String correo;
    @Column(name = "telefono_notif")
    private String telefono_notif;
    @Column(name = "correo_notif")
    private String correo_notif;
    @Column(name = "nota_rechazo")
    private String nota_rechazo;
    @Column(name = "estado")
    private String estado;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creado")
    private Date fecha_creado;
    @Column(name = "creado_por")
    private Integer creado_por;
    @Column(name = "tipo_proveedor")
    private Integer tipo_proveedor;
    @Column(name = "id_actividad_economica")
    private Integer id_actividad_economica;
    @Column(name = "id_tipo_comercio")
    private Integer id_tipo_comercio;
    /**
     * Almacena el Codigo Unico de Identificacion: CUI.
     */
    @Column(name = "cui")
    private String cui;

    public TipoProveedorAprobar() {

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

    public Integer getId_tipo_comercio() {
        return id_tipo_comercio;
    }

    public void setId_tipo_comercio(Integer id_tipo_comercio) {
        this.id_tipo_comercio = id_tipo_comercio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Integer departamento) {
        this.departamento = departamento;
    }

    public Integer getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Integer municipio) {
        this.municipio = municipio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono_notif() {
        return telefono_notif;
    }

    public void setTelefono_notif(String telefono_notif) {
        this.telefono_notif = telefono_notif;
    }

    public String getCorreo_notif() {
        return correo_notif;
    }

    public void setCorreo_notif(String correo_notif) {
        this.correo_notif = correo_notif;
    }

    public String getNota_rechazo() {
        return nota_rechazo;
    }

    public void setNota_rechazo(String nota_rechazo) {
        this.nota_rechazo = nota_rechazo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha_creado() {
        return fecha_creado;
    }

    public void setFecha_creado(Date fecha_creado) {
        this.fecha_creado = fecha_creado;
    }

    public Integer getCreado_por() {
        return creado_por;
    }

    public void setCreado_por(Integer creado_por) {
        this.creado_por = creado_por;
    }

    /**
     * Almacena el Codigo Unico de Identificacion: CUI.
     *
     * @return Cadena
     */
    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    @Override
    public String toString() {
        CheckNull checknull = new CheckNull();
        JsonObject temp = new JsonObject();
        temp.addProperty("id", this.id);
        temp.addProperty("nombre", checknull.cknull(this.nombre));
        temp.addProperty("direccion", checknull.cknull(this.direccion));
        temp.addProperty("nit", checknull.cknull(this.nit));
        temp.addProperty("telefono", checknull.cknull(this.telefono));
        temp.addProperty("correo", checknull.cknull(this.correo));
        temp.addProperty("telefono_notif", checknull.cknull(this.telefono_notif));
        temp.addProperty("correo_notif", checknull.cknull(this.correo_notif));
        temp.addProperty("estado", checknull.cknull(this.estado));
        temp.addProperty("fecha_creado", checknull.cknull(this.fecha_creado));
        temp.addProperty("nota_rechazo", checknull.cknull(this.nota_rechazo));
        temp.addProperty("cui", checknull.cknull(this.cui));
        return temp.toString() + ",";
    }

}
