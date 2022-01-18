package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "diaco_consumidor")
@NamedQueries({
    @NamedQuery(name = "DiacoConsumidor.findAll", query = "SELECT c FROM DiacoConsumidor c  "),
    @NamedQuery(name = "DiacoConsumidor.findByDocumentoIdentificacion", query = "SELECT c FROM DiacoConsumidor c WHERE c.documentoIdentificacion = :documentoIdentificacion or c.nitConsumidor = :documentoIdentificacion "),
    @NamedQuery(name = "DiacoConsumidor.findByNit", query = "SELECT c FROM DiacoConsumidor c WHERE c.nitConsumidor = :nit ")
})
public class DiacoConsumidor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consumidor_seq")
    @SequenceGenerator(name = "consumidor_seq", sequenceName = "seq_diaco_consumidor", allocationSize = 1)
    @Column(name = "id_consumidor")
    private Integer idConsumidor;

    @JoinColumn(name = "nacionalidad")
    private Integer nacionalidad;
    //private DiacoPais nacionalidad;

    //@Column(insertable=false, updatable=false)
//	private Integer nacionalidad;
    @Column(name = "nit_consumidor")
    private String nitConsumidor;

    @Column(name = "documento_identificacion")
    private String documentoIdentificacion;

    @Column(name = "genero")
    private String genero;

    @Column(name = "direccion_calle")
    private String direccionCalle;

    @Column(name = "direccion_avenida")
    private String direccionAvenida;

    @Column(name = "direccion_zona")
    private String direccionZona;

    @JoinColumn(name = "codigo_municipio")
    private Municipio municipio;

    @Column(name = "codigo_municipio", insertable = false, updatable = false)
    private Integer codigoMunicipio;

    @JoinColumn(name = "codigo_departamento")
    private Departamento departamento;

    @Column(name = "codigo_departamento", insertable = false, updatable = false)
    private Integer codigoDepartamento;

    //@JoinColumn(name = "sede_diaco_cercana", referencedColumnName = "sede_diaco_cercana",updatable=false, insertable=false)
    @Column(name = "sede_diaco_cercana")
    private Integer sedeDiacoCercana;

    @Column(name = "habilitado_notificacion_electronica")
    private String habilitadoNotificacionElectronica;
	
	@Column(name = "habilitado")
    private String habilitado;

    @Column(name = "fecha_adicion")
    private Timestamp fechaAdicion;

    @Column(name = "usuario_adiciono")
    private String usuarioAdiciono;

    @Column(name = "usuario_modifico")
    private String usuarioModifico;
    @Column(name = "nombre1")
    private String nombre1;
    @Column(name = "nombre2")
    private String nombre2;
    @Column(name = "nombre3")
    private String nombre3;
    @Column(name = "apellido1")
    private String apellido1;
    @Column(name = "apellido2")
    private String apellido2;

    @Column(name = "apellido_casada")
    private String apellidoCasada;

    @Column(name = "tipo_consumidor")
    private Integer tipoConsumidor;

    @Column(name = "tipo_documento")
    private Integer tipoDocumento;

    //@Column(name = "id_pais")
    //private Integer pais;
    @JoinColumn(name = "id_pais")
    private DiacoPais pais;

    @Column(name = "id_etnia")
    private Integer idEtnia;

    @Column(name = "direccion_detalle")
    private String direccionDetalle;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "domicilio")
    private String domicilio;
    @Column(name = "referencia")
    private String referencia;

    @Column(name = "correo_electronico1")
    private String correoElectronico1;

    @Column(name = "correo_electronico2")
    private String correoElectronico2;

    @Column(name = "direccion")
    private String direccion;
    @Column(name = "autorizoPublicar")
    private String autorizoPublicar;

    @Column(name = "representante_legal")
    private String representanteLegal;
    @Column(name = "nombre_empresa")
    private String nombreEmpresa;
    @Column(name = "razon_social")
    private String razonSocial;
    @Column(name = "id_motivo_queja")
    private Integer idMotivoQueja;

    @Transient
    private DiacoSede sede;

    @Transient
    private String telefonoCelular;
    @Transient
    private String telefonoDomicilio;
    @Transient
    private String telefonoReferencia;
    @Transient
    private String email;
    @Transient
    private String email2;

    public DiacoConsumidor() {
    }

    public Integer getIdConsumidor() {
        return idConsumidor;
    }

    public void setIdConsumidor(Integer idConsumidor) {
        this.idConsumidor = idConsumidor;
    }

//    public Integer getNacionalidad() {
//        return nacionalidad;
//    }
//
//    public void setNacionalidad(Integer nacionalidad) {
//        this.nacionalidad = nacionalidad;
//    }
    public String getNitConsumidor() {
        return nitConsumidor;
    }

    public void setNitConsumidor(String nitConsumidor) {
        this.nitConsumidor = nitConsumidor;
    }

    public String getDocumentoIdentificacion() {
        return documentoIdentificacion;
    }

    public void setDocumentoIdentificacion(String documentoIdentificacion) {
        this.documentoIdentificacion = documentoIdentificacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccionCalle() {
        return direccionCalle;
    }

    public void setDireccionCalle(String direccionCalle) {
        this.direccionCalle = direccionCalle;
    }

    public String getDireccionAvenida() {
        return direccionAvenida;
    }

    public void setDireccionAvenida(String direccionAvenida) {
        this.direccionAvenida = direccionAvenida;
    }

    public String getDireccionZona() {
        return direccionZona;
    }

    public void setDireccionZona(String direccionZona) {
        this.direccionZona = direccionZona;
    }

    public Integer getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Integer codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public Integer getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(Integer codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public Integer getSedeDiacoCercana() {
        return sedeDiacoCercana;
    }

    public void setSedeDiacoCercana(Integer sedeDiacoCercana) {
        this.sedeDiacoCercana = sedeDiacoCercana;
    }

    public String getHabilitadoNotificacionElectronica() {
        return habilitadoNotificacionElectronica;
    }

    public void setHabilitadoNotificacionElectronica(String habilitadoNotificacionElectronica) {
        this.habilitadoNotificacionElectronica = habilitadoNotificacionElectronica;
    }
	
	public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }
	

    public Timestamp getFechaAdicion() {
        return fechaAdicion;
    }

    public void setFechaAdicion(Timestamp fechaAdicion) {
        this.fechaAdicion = fechaAdicion;
    }

    public String getUsuarioAdiciono() {
        return usuarioAdiciono;
    }

    public void setUsuarioAdiciono(String usuarioAdiciono) {
        this.usuarioAdiciono = usuarioAdiciono;
    }

    public String getUsuarioModifico() {
        return usuarioModifico;
    }

    public void setUsuarioModifico(String usuarioModifico) {
        this.usuarioModifico = usuarioModifico;
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

    public String getApellidoCasada() {
        return apellidoCasada;
    }

    public void setApellidoCasada(String apellidoCasada) {
        this.apellidoCasada = apellidoCasada;
    }

    public Integer getTipoConsumidor() {
        return tipoConsumidor;
    }

    public void setTipoConsumidor(Integer tipoConsumidor) {
        this.tipoConsumidor = tipoConsumidor;
    }

    public Integer getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Integer nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

//    public DiacoSede getSede() {
//        return sede;
//    }
//
    public void setSede(DiacoSede sede) {
        this.sede = sede;
    }

    public Integer getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Integer tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public DiacoPais getPais() {
        return pais;
    }

    public void setPais(DiacoPais pais) {
        this.pais = pais;
    }

    public Integer getIdEtnia() {
        return idEtnia;
    }

    public void setIdEtnia(Integer idEtnia) {
        this.idEtnia = idEtnia;
    }

    public String getDireccionDetalle() {
        return direccionDetalle;
    }

    public void setDireccionDetalle(String direccionDetalle) {
        this.direccionDetalle = direccionDetalle;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCorreoElectronico1() {
        return correoElectronico1;
    }

    public void setCorreoElectronico1(String correoElectronico1) {
        this.correoElectronico1 = correoElectronico1;
    }

    public String getCorreoElectronico2() {
        return correoElectronico2;
    }

    public void setCorreoElectronico2(String correoElectronico2) {
        this.correoElectronico2 = correoElectronico2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getAutorizoPublicar() {
        return autorizoPublicar;
    }

    public void setAutorizoPublicar(String autorizoPublicar) {
        this.autorizoPublicar = autorizoPublicar;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Integer getIdMotivoQueja() {
        return idMotivoQueja;
    }

    public void setIdMotivoQueja(Integer idMotivoQueja) {
        this.idMotivoQueja = idMotivoQueja;
    }

    public String getTelefonoDomicilio() {
        return telefonoDomicilio;
    }

    public void setTelefonoDomicilio(String telefonoDomicilio) {
        this.telefonoDomicilio = telefonoDomicilio;
    }

    public String getTelefonoReferencia() {
        return telefonoReferencia;
    }

    public void setTelefonoReferencia(String telefonoReferencia) {
        this.telefonoReferencia = telefonoReferencia;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
