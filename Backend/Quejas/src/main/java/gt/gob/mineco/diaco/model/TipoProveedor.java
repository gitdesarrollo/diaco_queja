package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import gt.gob.mineco.diaco.util.CheckNull;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * The persistent class for the diaco_proveedor database table.
 *
 */
@Entity
@Table(name = "diaco_proveedor")
@NamedQueries({
    @NamedQuery(name = "TipoProveedor.findSP", query = "SELECT t FROM TipoProveedor t where t.servicio_publico = true and t.habilitado='1'"),
    @NamedQuery(name = "TipoProveedor.findnoneSP", query = "SELECT t FROM TipoProveedor t where (t.servicio_publico = false or t.servicio_publico IS NULL) and t.habilitado='1'"),
    @NamedQuery(name = "TipoProveedor.findxNit", query = "SELECT t FROM TipoProveedor t where t.nit_proveedor = :nit and t.habilitado='1'"),
    @NamedQuery(name = "TipoProveedor.findByNit", query = "SELECT t FROM TipoProveedor t where  t.nit_proveedor= :nit_proveedorP and t.habilitado='1' "),
    @NamedQuery(name = "TipoProveedor.findCPoSP", query = "SELECT t FROM TipoProveedor t where (t.servicio_publico = true or t.habilitado_conciliacion_previa = '1') and t.codigo_departamento IS NOT NULL and t.habilitado='1'"),

    @NamedQuery(name = "TipoProveedor.findAll", query = "SELECT t FROM TipoProveedor t where t.habilitado='1' ")
})
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "svf_GetSeqNextProveedor",
            procedureName = "svf_GetSeqNextProveedor",
            parameters = {
                @StoredProcedureParameter(
                        name = "newid",
                        type = Integer.class,
                        mode = ParameterMode.OUT)})
})
//inner join t.tipopasoqueja og where og.id_queja_paso=:usr
//@GeneratedValue(strategy=GenerationType.IDENTITY)

//@SequenceGenerator(name="proveedor_seq", sequenceName = "seq_diaco_proveedor", allocationSize=1)
public class TipoProveedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proveedor_seq")
    @SequenceGenerator(name = "proveedor_seq", sequenceName = "seq_diaco_proveedor", allocationSize = 1)
    @Column(name = "id_proveedor")
    private Integer id_proveedor;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "nit_proveedor")
    private String nit_proveedor;
    @Column(name = "habilitado_conciliacion_previa")
    private String habilitado_conciliacion_previa;
    @Column(name = "codigo_departamento")
    private Integer codigo_departamento;
    @Column(name = "habilitado_notificacion_electronica")
    private String habilitado_notificacion_electronica;
    @Column(name = "habilitado")
    private String habilitado;
    @Column(name = "codigo_municipio")
    private Integer codigo_municipio;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro_conciliacion_previa")
    private Date fecha_registro_conciliacion_previa;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_adicion")
    private Date fecha_adicion;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_modificacion")
    private Date fecha_modificacion;
    @Column(name = "usuario_adiciono")
    private String usuario_adiciono;
    @Column(name = "usuario_modifico")
    private String usuario_modifico;
    @Column(name = "servicio_publico", columnDefinition = "BIT")
    private boolean servicio_publico;
    @JoinColumn(name = "codigo_departamento", referencedColumnName = "codigo_departamento", updatable = false, insertable = false)
    private TipoDepartamento tipoDepartamento;

    @Column(name = "primer_nombre")
    private String primer_nombre;
    @Column(name = "segundo_nombre")
    private String segundo_nombre;
    @Column(name = "tercer_nombre")
    private String tercer_nombre;
    @Column(name = "primer_apellido")
    private String primer_apellido;
    @Column(name = "segundo_apellido")
    private String segundo_apellido;
    @Column(name = "apellido_casada")
    private String apellido_casada;
    @Column(name = "direccion_calle")
    private String direccion_calle;
    @Column(name = "direccion_avenida")
    private String direccion_avenida;
    @Column(name = "direccion_zona")
    private String direccion_zona;
    @Column(name = "direccion_detalle")
    private String direccion_detalle;
    @Column(name = "nombre_empresa")
    private String nombre_empresa;
    @Column(name = "razon_social")
    private String razon_social;
    @Column(name = "email")
    private String email;
    @Column(name = "tipo_proveedor")
    private Integer tipo_proveedor;
    @Column(name = "id_actividad_economica")
    private Integer id_actividad_economica;
    @Column(name = "id_tipo_comercio")
    private Integer id_tipo_comercio;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "permite_calificacion", columnDefinition = "BIT")
    private boolean permite_calificacion;

    @JoinColumn(name = "id_actividad_economica", referencedColumnName = "id_actividad_economica", updatable = false, insertable = false)
    private TipoActividadEconomica tipoActividadEconomica;

    @JoinColumn(name = "id_tipo_comercio", referencedColumnName = "id_tipo_comercio", updatable = false, insertable = false)
    private TipoComercio tipoComercio;

    @OneToOne
    @JoinColumn(name = "codigo_municipio", referencedColumnName = "codigo_municipio", updatable = false, insertable = false)
    private TipoMunicipio tipoMunicipio;

    public TipoProveedor() {

    }

    public TipoActividadEconomica getTipoActividadEconomica() {
        return tipoActividadEconomica;
    }

    public void setTipoActividadEconomica(TipoActividadEconomica tipoActividadEconomica) {
        this.tipoActividadEconomica = tipoActividadEconomica;
    }

    public TipoComercio getTipoComercio() {
        return tipoComercio;
    }

    public void setTipoComercio(TipoComercio tipoComercio) {
        this.tipoComercio = tipoComercio;
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

    public Integer getId_tipo_comercio() {
        return id_tipo_comercio;
    }

    public void setId_tipo_comercio(Integer id_tipo_comercio) {
        this.id_tipo_comercio = id_tipo_comercio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isPermite_calificacion() {
        return permite_calificacion;
    }

    public void setPermite_calificacion(boolean permite_calificacion) {
        this.permite_calificacion = permite_calificacion;
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

    public boolean isServicio_publico() {
        return servicio_publico;
    }

    public void setServicio_publico(boolean servicio_publico) {
        this.servicio_publico = servicio_publico;
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
        if (this.getTipo_proveedor() == 6) {
            //return this.getNombreCompletoIndividual();
            String cadena_completa = getNombreProveedor()+" / "+ getNombre_empresa();
            //return getNombreProveedor();
            return cadena_completa;
        } else {
            return getRazon_social();
        }
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

    public String getNombreCompletoIndividual() {
        return ((this.primer_nombre != null ? this.primer_nombre : "") + " " + (this.getSegundo_nombre() != null ? this.getSegundo_nombre() : "") + " " + (this.getTercer_nombre() != null ? this.getTercer_nombre() : "") + " " + (this.getPrimer_apellido() != null ? this.getPrimer_apellido() : "") + " " + (this.getSegundo_apellido() != null ? this.getSegundo_apellido() : "") + " " + ((this.getApellido_casada() != null) ? "De " + this.getApellido_casada() : "") + " PROPIETARIO DE " + (this.getNombre_empresa() != null ? this.getNombre_empresa() : "")).toUpperCase();
    }

    /**
     * Metodo que retorna el nombre del proveedor.
     * <p>
     * Utiliza el atributo {@code nombre} de la Base de datos.
     *
     * @return Cadena.
     */
    public String getNombreProveedor() {
        return this.nombre;
    }

    @Override
    public String toString() {
        CheckNull checknull = new CheckNull();
        JsonObject temp = new JsonObject();
        temp.addProperty("id_proveedor", this.id_proveedor);
        temp.addProperty("nombre", checknull.cknull(this.nombre));
        temp.addProperty("direccion", checknull.cknull(this.direccion));
        temp.addProperty("nit", checknull.cknull(this.nit_proveedor));
        temp.addProperty("nit_proveedor", checknull.cknull(this.nit_proveedor));
        temp.addProperty("codigo_departamento", checknull.cknull(this.codigo_departamento));
        temp.addProperty("codigo_municipio", checknull.cknull(this.codigo_municipio));
        temp.addProperty("usuario_adiciono", checknull.cknull(this.usuario_adiciono));
        temp.addProperty("usuario_modifico", checknull.cknull(this.usuario_modifico));
        temp.addProperty("primer_nombre", checknull.cknull(this.primer_nombre));
        temp.addProperty("segundo_nombre", checknull.cknull(this.segundo_nombre));
        temp.addProperty("tercer_nombre", checknull.cknull(this.tercer_nombre));
        temp.addProperty("primer_apellido", checknull.cknull(this.primer_apellido));
        temp.addProperty("segundo_apellido", checknull.cknull(this.segundo_apellido));
        temp.addProperty("apellido_casada", checknull.cknull(this.apellido_casada));
        temp.addProperty("direccion_calle", checknull.cknull(this.direccion_calle));
        temp.addProperty("direccion_avenida", checknull.cknull(this.direccion_avenida));
        temp.addProperty("direccion_zona", checknull.cknull(this.direccion_detalle));
        temp.addProperty("direccion", checknull.cknull(this.direccion));
        temp.addProperty("direccion_detalle", checknull.cknull(this.direccion_detalle));
        temp.addProperty("nombre_empresa", checknull.cknull(this.nombre_empresa));
        temp.addProperty("razon_social", checknull.cknull(this.razon_social));
        temp.addProperty("email", checknull.cknull(this.email));
        temp.addProperty("tipo_proveedor", checknull.cknull(this.tipo_proveedor));
        temp.addProperty("id_tipo_comercio", checknull.cknull(this.id_tipo_comercio));
        temp.addProperty("id_actividad_economica", checknull.cknull(this.id_actividad_economica));
        temp.addProperty("telefono", checknull.cknull(this.telefono));
        temp.addProperty("servicio_publico", this.servicio_publico);
        temp.addProperty("habilitado_conciliacion_previa", this.habilitado_conciliacion_previa);
        temp.addProperty("habilitado_notificacion_electronica", this.habilitado_notificacion_electronica);
        temp.addProperty("permite_calificacion", this.permite_calificacion);

        /*    temp.addProperty("nombre_departamento",checknull.cknull(this.tipoDepartamento.getNombre_departamento()));
            temp.addProperty("nombre_municipio",checknull.cknull(this.tipoMunicipio.getNombre_municipio()));
            temp.addProperty("nombre_actividad_economica",checknull.cknull(this.tipoActividadEconomica.getNombre_actividad_economica()));
            temp.addProperty("tipo_comercio",checknull.cknull(this.tipoComercio.getTipo_comercio()));
         */
        return temp.toString() + ",";
    }

    public JsonObject toJsonelement() {
        CheckNull checknull = new CheckNull();
        JsonObject temp = new JsonObject();
        temp.addProperty("id_proveedor", this.id_proveedor);
        temp.addProperty("nombre", checknull.cknull(this.nombre));
        temp.addProperty("direccion", checknull.cknull(this.direccion));
        temp.addProperty("nit", checknull.cknull(this.nit_proveedor));
        temp.addProperty("nit_proveedor", checknull.cknull(this.nit_proveedor));
        temp.addProperty("codigo_departamento", checknull.cknull(this.codigo_departamento));
        temp.addProperty("codigo_municipio", checknull.cknull(this.codigo_municipio));
        temp.addProperty("usuario_adiciono", checknull.cknull(this.usuario_adiciono));
        temp.addProperty("usuario_modifico", checknull.cknull(this.usuario_modifico));
        temp.addProperty("primer_nombre", checknull.cknull(this.primer_nombre));
        temp.addProperty("segundo_nombre", checknull.cknull(this.segundo_nombre));
        temp.addProperty("tercer_nombre", checknull.cknull(this.tercer_nombre));
        temp.addProperty("primer_apellido", checknull.cknull(this.primer_apellido));
        temp.addProperty("segundo_apellido", checknull.cknull(this.segundo_apellido));
        temp.addProperty("apellido_casada", checknull.cknull(this.apellido_casada));
        temp.addProperty("direccion_calle", checknull.cknull(this.direccion_calle));
        temp.addProperty("direccion_avenida", checknull.cknull(this.direccion_avenida));
        temp.addProperty("direccion_zona", checknull.cknull(this.direccion_detalle));
        temp.addProperty("direccion", checknull.cknull(this.direccion));
        temp.addProperty("direccion_detalle", checknull.cknull(this.direccion_detalle));
        temp.addProperty("nombre_empresa", checknull.cknull(this.nombre_empresa));
        temp.addProperty("razon_social", checknull.cknull(this.razon_social));
        temp.addProperty("email", checknull.cknull(this.email));
        temp.addProperty("tipo_proveedor", checknull.cknull(this.tipo_proveedor));
        temp.addProperty("id_tipo_comercio", checknull.cknull(this.id_tipo_comercio));
        temp.addProperty("id_actividad_economica", checknull.cknull(this.id_actividad_economica));
        temp.addProperty("telefono", checknull.cknull(this.telefono));
        temp.addProperty("permite_calificacion", this.permite_calificacion);

        /*   temp.addProperty("nombre_departamento",checknull.cknull(this.tipoDepartamento.getNombre_departamento()));
            temp.addProperty("nombre_municipio",checknull.cknull(this.tipoMunicipio.getNombre_municipio()));
            temp.addProperty("nombre_actividad_economica",checknull.cknull(this.tipoActividadEconomica.getNombre_actividad_economica()));
            temp.addProperty("tipo_comercio",checknull.cknull(this.tipoComercio.getTipo_comercio()));
         */
        return temp;
    }
}
