package gt.gob.mineco.diaco.model;



import gt.gob.mineco.diaco.model.*;
import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
/**
 * The persistent class for the diaco_proveedor database table.
 * 
 */
@Entity
@Table(name="diaco_proveedor")
@NamedQuery(name="TipoProveedor.findAll", query="SELECT t FROM TipoProveedor t")
//inner join t.tipopasoqueja og where og.id_queja_paso=:usr
public class TipoProveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_proveedor", precision=0) 
        private Integer id_proveedor;
        @Column(name="nombre")
        private String nombre;
        @Column(name="direccion")        
	private String direccion;
        @Column(name="nit_proveedor")
        private String nit_proveedor;
	@Column(name="habilitado_conciliacion_previa")        
	private String habilitado_conciliacion_previa;
        @Column(name="codigo_departamento")        
	private Integer codigo_departamento;
        @Column(name="habilitado_notificacion_electronica")        
	private String habilitado_notificacion_electronica;
        @Column(name="habilitado")
        private String habilitado;
        @Column(name="codigo_municipio")        
	private Integer codigo_municipio;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_registro_conciliacion_previa")
        private Date fecha_registro_conciliacion_previa;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_adicion")
        private Date fecha_adicion;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_modificacion")
        private Date fecha_modificacion;
        @Column(name="usuario_adiciono")        
	private Integer usuario_adiciono;
        @Column(name="usuario_modifico")        
	private Integer usuario_modifico;
        @JoinColumn(name = "codigo_departamento", referencedColumnName = "codigo_departamento",updatable=false, insertable=false)
        private TipoDepartamento tipoDepartamento;
        @OneToOne
        @JoinColumn(name = "codigo_municipio", referencedColumnName = "codigo_municipio",updatable=false, insertable=false)
        private TipoMunicipio tipoMunicipio;
        
    public TipoProveedor() {
       
    }

    public TipoDepartamento getTipoDepartamento() {
        return tipoDepartamento;
    }

    public void setTipoDepartamento(TipoDepartamento tipoDepartamento) {
        this.tipoDepartamento = tipoDepartamento;
    }

    public TipoMunicipio getTipoMunicipio() {
        return tipoMunicipio;
    }

    public void setTipoMunicipio(TipoMunicipio tipoMunicipio) {
        this.tipoMunicipio = tipoMunicipio;
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

    public Date getFecha_registro_conciliacion_previa() {
        return fecha_registro_conciliacion_previa;
    }

    public void setFecha_registro_conciliacion_previa(Date fecha_registro_conciliacion_previa) {
        this.fecha_registro_conciliacion_previa = fecha_registro_conciliacion_previa;
    }

    public Date getFecha_adicion() {
        return fecha_adicion;
    }

    public void setFecha_adicion(Date fecha_adicion) {
        this.fecha_adicion = fecha_adicion;
    }

    public Date getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(Date fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public Integer getUsuario_adiciono() {
        return usuario_adiciono;
    }

    public void setUsuario_adiciono(Integer usuario_adiciono) {
        this.usuario_adiciono = usuario_adiciono;
    }

    public Integer getUsuario_modifico() {
        return usuario_modifico;
    }

    public void setUsuario_modifico(Integer usuario_modifico) {
        this.usuario_modifico = usuario_modifico;
    }

    
        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_proveedor",this.id_proveedor);
            temp.addProperty("nombre",this.nombre);
            temp.addProperty("direccion",this.direccion);
            temp.addProperty("nit",this.nit_proveedor);
            temp.addProperty("codigo_departamento",this.codigo_departamento);
            temp.addProperty("codigo_municipio",this.codigo_municipio);
            return temp.toString()+",";
        }
        
        public JsonObject toJsonelement() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_proveedor",this.id_proveedor);
            temp.addProperty("nombre",this.nombre);
            temp.addProperty("direccion",this.direccion);
            temp.addProperty("nit",this.nit_proveedor);
            temp.addProperty("codigo_departamento",this.codigo_departamento);
            temp.addProperty("codigo_municipio",this.codigo_municipio);
            return temp;
        }
}
