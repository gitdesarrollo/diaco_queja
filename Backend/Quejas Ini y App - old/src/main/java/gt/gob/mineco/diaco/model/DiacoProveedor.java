package gt.gob.mineco.diaco.model;

import gt.gob.mineco.diaco.dto.CalificacionProveedorDto;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

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
@Table(name="diaco_proveedor")
@NamedQueries({
	@NamedQuery(name="DiacoProveedor.findAll", query="SELECT p FROM DiacoProveedor p"),
	@NamedQuery(name="DiacoProveedor.findByNit", query="SELECT p FROM DiacoProveedor p WHERE p.nitProveedor = :nit"),
        @NamedQuery(name="DiacoProveedor.findById", query="SELECT p FROM DiacoProveedor p WHERE p.idProveedor = :id"),
        @NamedQuery(name="DiacoProveedor.findByNombre", query="SELECT p FROM DiacoProveedor p WHERE p.idProveedor = :id")
})
public class DiacoProveedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proveedor_seq")
    @SequenceGenerator(name = "proveedor_seq", sequenceName = "seq_diaco_proveedor", allocationSize = 1)
    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "nit_proveedor")
    private String nitProveedor;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "habilitado_conciliacion_previa")
    private String habilitadoConciliacionPrevia;

    @JoinColumn(name = "codigo_departamento")
    private Departamento departamento;

    @Column(name = "codigo_departamento", insertable = false, updatable = false)
    private Integer codigoDepartamento;

    @Column(name = "habilitado_notificacion_electronica")
    private String habilitadoNotificacionElectronica;

    @Column(name = "habilitado")
    private String habilitado;

    @JoinColumn(name = "codigo_municipio")
    private Municipio municipio;

    @Column(name = "codigo_municipio", insertable = false, updatable = false)
    private Integer codigoMunicipio;

    @Column(name = "fecha_registro_conciliacion_previa")
    private Timestamp fechaRegistroConciliacionPrevia;

    @Column(name = "fecha_adicion")
    private Timestamp fechaAdicion;

    @Column(name = "fecha_modificacion")
    private Timestamp fechaModificacion;

    @Column(name = "usuario_adiciono")
    private String usuarioAdiciono;

    @Column(name = "usuario_modifico")
    private String usuarioModifico;

    @Column(name = "primer_nombre")
    private String primerNombre;

    @Column(name = "segundo_nombre")
    private String segundoNombre;

    @Column(name = "tercer_nombre")
    private String tercerNombre;

    @Column(name = "primer_apellido")
    private String primerApellido;

    @Column(name = "segundo_apellido")
    private String segundoApellido;

    @Column(name = "apellido_casada")
    private String apellidoCasada;

    @Column(name = "direccion_calle")
    private String direccionCalle;

    @Column(name = "direccion_avenida")
    private String direccionAvenida;

    @Column(name = "direccion_zona")
    private String direccionZona;

    @Column(name = "direccion_detalle")
    private String direccionDetalle;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    @Column(name = "razon_social")
    private String razonSocial;
    
    @Column(name = "permite_calificacion")
    private String calificacionAutorizada;
    
    
    

//	@Column(name="id_actividad_economica")
//	private Integer idActividadEconomica;
	
//	@Column(name="id_tipo_comercio")
//	private Integer idTipoComercio;
	
//	@Column(name="tipo_proveedor")
//	private Integer tipoProveedor;
	
//	private String telefono;

	    @Column(name = "email")
		private String email;

    @Column(name = "autorizoPublicar")
    private String autorizoPublicar;
    @Column(name = "servicio_publico")
    private String serviciopublico;
    
    @Column(name = "tipo_proveedor")
    private String tipoProveedor;
    @Column(name = "id_actividad_economica")
    private String idActividadEconomica;
    @Column(name = "id_tipo_comercio")
    private String idTipoComercio;
    @Column(name = "telefono")
    private String telefono;
    
    @Transient
    private String telefono1;
    @Transient
    private String telefono2;
    @Transient
    private String telefono3;
    @Transient
    private String email1;
    @Transient
    private String email2;

    @Transient
    private Long quejas_validas;
    
    @Transient
    private Long quejas_validas_resuelta;
                
        //@Transient
       // private Collection<DiacoCalificacionUsuarioProv> diacoCalificacionUsuarioProv;
        
        //@Transient
       // private Collection<DiacoPtsGeneralCalifProv> diacoPtsGeneralCalifProv;
     @Transient
       private Collection<CalificacionProveedorDto> diacoCalificacionProveedorDto;
        
    
        
	public DiacoProveedor() {
	}

	public Integer getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNitProveedor() {
		return nitProveedor;
	}

	public void setNitProveedor(String nitProveedor) {
		this.nitProveedor = nitProveedor;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getHabilitadoConciliacionPrevia() {
		return habilitadoConciliacionPrevia;
	}

	public void setHabilitadoConciliacionPrevia(String habilitadoConciliacionPrevia) {
		this.habilitadoConciliacionPrevia = habilitadoConciliacionPrevia;
	}

	public Integer getCodigoDepartamento() {
		return codigoDepartamento;
	}

	public void setCodigoDepartamento(Integer codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
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

	public Integer getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public void setCodigoMunicipio(Integer codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	public Timestamp getFechaRegistroConciliacionPrevia() {
		return fechaRegistroConciliacionPrevia;
	}

	public void setFechaRegistroConciliacionPrevia(Timestamp fechaRegistroConciliacionPrevia) {
		this.fechaRegistroConciliacionPrevia = fechaRegistroConciliacionPrevia;
	}

	public Timestamp getFechaAdicion() {
		return fechaAdicion;
	}

	public void setFechaAdicion(Timestamp fechaAdicion) {
		this.fechaAdicion = fechaAdicion;
	}

	public Timestamp getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
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

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getTercerNombre() {
		return tercerNombre;
	}

	public void setTercerNombre(String tercerNombre) {
		this.tercerNombre = tercerNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getApellidoCasada() {
		return apellidoCasada;
	}

	public void setApellidoCasada(String apellidoCasada) {
		this.apellidoCasada = apellidoCasada;
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

	public String getDireccionDetalle() {
		return direccionDetalle;
	}

	public void setDireccionDetalle(String direccionDetalle) {
		this.direccionDetalle = direccionDetalle;
	}
	/*
	public Integer getIdActividadEconomica() {
		return idActividadEconomica;
	}

	public void setIdActividadEconomica(Integer idActividadEconomica) {
		this.idActividadEconomica = idActividadEconomica;
	}
    
	public Integer getIdTipoComercio() {
		return idTipoComercio;
	}

	public void setIdTipoComercio(Integer idTipoComercio) {
		this.idTipoComercio = idTipoComercio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	*/
	public String getEmail() {
		return email;
	}

    public void setEmail(String email) {
        this.email = email;
    }

//	public Integer getTipoProveedor() {
//		return tipoProveedor;
//	}
//
//	public void setTipoProveedor(Integer tipoProveedor) {
//		this.tipoProveedor = tipoProveedor;
//	}
    public String getAutorizoPublicar() {
        return autorizoPublicar;
    }

    public void setAutorizoPublicar(String autorizoPublicar) {
        this.autorizoPublicar = autorizoPublicar;
    }

    public String getServiciopublico() {
        return serviciopublico;
    }

    public void setServiciopublico(String serviciopublico) {
        this.serviciopublico = serviciopublico;
    }

    public String getTipoProveedor() {
        return tipoProveedor;
    }

    public void setTipoProveedor(String tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }

    public String getIdActividadEconomica() {
        return idActividadEconomica;
    }

    public void setIdActividadEconomica(String idActividadEconomica) {
        this.idActividadEconomica = idActividadEconomica;
    }

    public String getIdTipoComercio() {
        return idTipoComercio;
    }

    public void setIdTipoComercio(String idTipoComercio) {
        this.idTipoComercio = idTipoComercio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getQuejas_validas() {
        return quejas_validas;
    }

    public void setQuejas_validas(Long quejas_validas) {
        this.quejas_validas = quejas_validas;
    }

    public Long getQuejas_validas_resuelta() {
        return quejas_validas_resuelta;
    }

    public void setQuejas_validas_resuelta(Long quejas_validas_resuelta) {
        this.quejas_validas_resuelta = quejas_validas_resuelta;
    }

    /**
     * @return the diacoCalificacionUsuarioProv
     */
 /*   public Collection<DiacoCalificacionUsuarioProv> getDiacoCalificacionUsuarioProv() {
        return diacoCalificacionUsuarioProv;
    }*/

    /**
     * @param diacoCalificacionUsuarioProv the diacoCalificacionUsuarioProv to set
     */
   /* public void setDiacoCalificacionUsuarioProv(Collection<DiacoCalificacionUsuarioProv> diacoCalificacionUsuarioProv) {
        this.diacoCalificacionUsuarioProv = diacoCalificacionUsuarioProv;
    }*/

    /**
     * @return the diacoPtsGeneralCalifProv
     */
   /* public Collection<DiacoPtsGeneralCalifProv> getDiacoPtsGeneralCalifProv() {
        return diacoPtsGeneralCalifProv;
    }*/

    /**
     * @param diacoPtsGeneralCalifProv the diacoPtsGeneralCalifProv to set
     */
  /*  public void setDiacoPtsGeneralCalifProv(Collection<DiacoPtsGeneralCalifProv> diacoPtsGeneralCalifProv) {
        this.diacoPtsGeneralCalifProv = diacoPtsGeneralCalifProv;
    }*/

    /**
     * @return the diacoCalificacionProveedorDto
     */
    public Collection<CalificacionProveedorDto> getDiacoCalificacionProveedorDto() {
        return diacoCalificacionProveedorDto;
    }

    /**
     * @param diacoCalificacionProveedorDto the diacoCalificacionProveedorDto to set
     */
    public void setDiacoCalificacionProveedorDto(Collection<CalificacionProveedorDto> diacoCalificacionProveedorDto) {
        this.diacoCalificacionProveedorDto = diacoCalificacionProveedorDto;
    }
        
		public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getTelefono3() {
        return telefono3;
    }

    public void setTelefono3(String telefono3) {
        this.telefono3 = telefono3;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    

    /**
     * @return the calificacionAutorizada
     */
    public String getCalificacionAutorizada() {
        return calificacionAutorizada;
    }

    /**
     * @param calificacionAutorizada the calificacionAutorizada to set
     */
    public void setCalificacionAutorizada(String calificacionAutorizada) {
        this.calificacionAutorizada = calificacionAutorizada;
    }
        
}
