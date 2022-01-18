package gt.gob.mineco.diaco.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "diaco_queja")
@NamedQueries({
    @NamedQuery(name = "DiacoQueja.findAll", query = "SELECT q FROM DiacoQueja q"),
    @NamedQuery(name = "DiacoQueja.findMaxFromYear", query = "SELECT MAX(q.noQueja) FROM DiacoQueja q WHERE q.anio = :anio"),
    @NamedQuery(name = "DiacoQueja.findByIdFuente", query = "SELECT q FROM DiacoQueja q WHERE q.idFuente = :idfuente"),
    @NamedQuery(name = "DiacoQueja.findAnioSec", query = "SELECT q FROM DiacoQueja q WHERE q.anio = :anio  and q.noQueja = :noQueja"),
    @NamedQuery(name = "DiacoQueja.findQuejasValProv", query = "SELECT COUNT(q.noQueja) FROM DiacoQueja q WHERE q.idProveedor = :id_proveedor  and q.idEstadoQueja = 501"),
    @NamedQuery(name = "DiacoQueja.findFacturas", query = "SELECT q FROM DiacoQueja q WHERE q.idProveedor = :proveedor"),
    @NamedQuery(name = "DiacoQueja.findQuejasProv", query = "SELECT COUNT(q.noQueja) FROM DiacoQueja q WHERE q.idProveedor = :id_proveedor  and q.idEstadoQueja != 501")
})
public class DiacoQueja {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "queja_seq")
    @SequenceGenerator(name = "queja_seq", sequenceName = "seq_diaco_queja", allocationSize = 1)
    @Column(name = "id_queja")
    private Integer idQueja;

    @Column(name = "id_consumidor")
    private Integer idConsumidor;

    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "id_estado_queja")
    private Integer idEstadoQueja;

    @Column(name = "id_sub__actividad_economica")
    private Integer idSubActividadEconomica;

    @Column(name = "factura_numero")
    private String facturaNumero;

    @Column(name = "fecha_factura")
    @Temporal(TemporalType.DATE)
    private Date fechaFactura;

    @Column(name = "detalle_queja")
    private String detalleQueja;

    @Column(name = "solicita_que")
    private String solicitaQue;

    @Column(name = "procede_queja")
    private String procedeQueja;

    @Column(name = "razon_no_procede")
    private String razonNoProcede;

    @Column(name = "datos_verificados")
    private String datosVerificados;

    @Column(name = "fecha_queja")
    @Temporal(TemporalType.DATE)
    private Date fechaQueja;
    @Column(name = "motivo")
    private String motivo;

    @Column(name = "id_departamento")
    private Integer idDepartamento;

    @Column(name = "id_municipio")
    private Integer idMunicipio;

    @Column(name = "usuario_asignado")
    private Integer usuarioAsignado;

    @Column(name = "id_diaco_sede")
    private Integer idDiacoSede;
    
    @Column(name = "id_motivo_queja")
    private Integer idMotivoQueja;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "no_queja")
    private Integer noQueja;

    @Column(name = "fuente")
    private String fuente;

    @Column(name = "id_fuente")
    private Integer idFuente;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "direccion_proveedor")
    private String direccionProveedor;

    @Column(name = "id_departamento_proveedor")
    private Integer idDepartamentoProveedor;

    @Column(name = "id_municipio_proveedor")
    private Integer idMunicipioProveedor;

    @Column(name = "id_sucursal_proveedor")
    private Integer idEstablecimiento;

    @Column(name = "zona_proveedor")
    private int zonaProveedor;
	
	@Column(name = "completo_40_datos")
    private String completo30Datos;

    @Transient
    private String usuarioToken;

    @Transient
    private DiacoProveedor proveedor;

    @Transient
    private DiacoConsumidor consumidor;

    @Transient
    private DiacoSede sede;

    @Transient
    private DiacoEstadoQueja estadoQueja;

    @Transient
    private String fechaFactura_;

    @Transient
    private TipoPasoQueja pasoQueja;

    @Transient
    private String color;

    @Transient
    private String fdpi;

    @Transient
    private String fdocto;

    @Transient
    private List<String> fotros;

    @Transient
    private String pregunta;
    
    @Transient
    private String carchivos;
    
    @Transient
    private String cdatos;
    
    @Transient
    private String respuesta;
    
    @Transient
    private String docp;
    
    @Transient
    private String docc;
    
    @Transient
    private String nomp;
    
    @Transient
    private String nomc;
    
    public DiacoQueja() {
    }

    public Integer getIdQueja() {
        return idQueja;
    }

    public void setIdQueja(Integer idQueja) {
        this.idQueja = idQueja;
    }

    public Integer getIdConsumidor() {
        return idConsumidor;
    }

    public void setIdConsumidor(Integer idConsumidor) {
        this.idConsumidor = idConsumidor;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdEstadoQueja() {
        return idEstadoQueja;
    }

    public void setIdEstadoQueja(Integer idEstadoQueja) {
        this.idEstadoQueja = idEstadoQueja;
    }

    public Integer getIdSubActividadEconomica() {
        return idSubActividadEconomica;
    }

    public void setIdSubActividadEconomica(Integer idSubActividadEconomica) {
        this.idSubActividadEconomica = idSubActividadEconomica;
    }

    public String getFacturaNumero() {
        return facturaNumero;
    }

    public void setFacturaNumero(String facturaNumero) {
        this.facturaNumero = facturaNumero;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getDetalleQueja() {
        return detalleQueja;
    }

    public void setDetalleQueja(String detalleQueja) {
        this.detalleQueja = detalleQueja;
    }

    public String getSolicitaQue() {
        return solicitaQue;
    }

    public void setSolicitaQue(String solicitaQue) {
        this.solicitaQue = solicitaQue;
    }

    public String getProcedeQueja() {
        return procedeQueja;
    }

    public void setProcedeQueja(String procedeQueja) {
        this.procedeQueja = procedeQueja;
    }

    public String getRazonNoProcede() {
        return razonNoProcede;
    }

    public void setRazonNoProcede(String razonNoProcede) {
        this.razonNoProcede = razonNoProcede;
    }

    public String getDatosVerificados() {
        return datosVerificados;
    }

    public void setDatosVerificados(String datosVerificados) {
        this.datosVerificados = datosVerificados;
    }

    public Date getFechaQueja() {
        return fechaQueja;
    }

    public void setFechaQueja(Date fechaQueja) {
        this.fechaQueja = fechaQueja;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Integer getUsuarioAsignado() {
        return usuarioAsignado;
    }

    public void setUsuarioAsignado(Integer usuarioAsignado) {
        this.usuarioAsignado = usuarioAsignado;
    }

    public Integer getIdDiacoSede() {
        return idDiacoSede;
    }

    public void setIdDiacoSede(Integer idDiacoSede) {
        this.idDiacoSede = idDiacoSede;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getNoQueja() {
        return noQueja;
    }

    public void setNoQueja(Integer noQueja) {
        this.noQueja = noQueja;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public Integer getIdFuente() {
        return idFuente;
    }

    public void setIdFuente(Integer idFuente) {
        this.idFuente = idFuente;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public DiacoProveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(DiacoProveedor proveedor) {
        this.proveedor = proveedor;
    }

    public DiacoConsumidor getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(DiacoConsumidor consumidor) {
        this.consumidor = consumidor;
    }

    public DiacoSede getSede() {
        return sede;
    }

    public void setSede(DiacoSede sede) {
        this.sede = sede;
    }

    public DiacoEstadoQueja getEstadoQueja() {
        return estadoQueja;
    }

    public void setEstadoQueja(DiacoEstadoQueja estadoQueja) {
        this.estadoQueja = estadoQueja;
    }

    public String getUsuarioToken() {
        return usuarioToken;
    }

    public void setUsuarioToken(String usuarioToken) {
        this.usuarioToken = usuarioToken;
    }

    public String getFechaFactura_() {
        return fechaFactura_;
    }

    public void setFechaFactura_(String fechaFactura_) {
        this.fechaFactura_ = fechaFactura_;
    }

    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }

    public Integer getIdDepartamentoProveedor() {
        return idDepartamentoProveedor;
    }

    public void setIdDepartamentoProveedor(Integer idDepartamentoProveedor) {
        this.idDepartamentoProveedor = idDepartamentoProveedor;
    }

    public Integer getIdMunicipioProveedor() {
        return idMunicipioProveedor;
    }

    public void setIdMunicipioProveedor(Integer idMunicipioProveedor) {
        this.idMunicipioProveedor = idMunicipioProveedor;
    }

    public TipoPasoQueja getPasoQueja() {
        return pasoQueja;
    }

    public void setPasoQueja(TipoPasoQueja pasoQueja) {
        this.pasoQueja = pasoQueja;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public String getFdpi() {
        return fdpi;
    }

    public void setFdpi(String fdpi) {
        this.fdpi = fdpi;
    }

    public String getFdocto() {
        return fdocto;
    }

    public void setFdocto(String fdocto) {
        this.fdocto = fdocto;
    }

    public List<String> getFotros() {
        return fotros;
    }

    public void setFotros(List<String> fotros) {
        this.fotros = fotros;
    }

    public void setIdEstablecimiento(Integer idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

    public int getZonaProveedor() {
        return zonaProveedor;
    }

    public void setZonaProveedor(int zonaProveedor) {
        this.zonaProveedor = zonaProveedor;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getCarchivos() {
        return carchivos;
    }

    public void setCarchivos(String carchivos) {
        this.carchivos = carchivos;
    }

    public String getCdatos() {
        return cdatos;
    }

    public void setCdatos(String cdatos) {
        this.cdatos = cdatos;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getDocp() {
        return docp;
    }

    public void setDocp(String docp) {
        this.docp = docp;
    }

    public String getDocc() {
        return docc;
    }

    public void setDocc(String docc) {
        this.docc = docc;
    }

    public String getNomc() {
        return nomc;
    }

    public void setNomc(String nomc) {
        this.nomc = nomc;
    }

    public String getNomp() {
        return nomp;
    }

    public void setNomp(String nomp) {
        this.nomp = nomp;
    }

    public String getCompleto30Datos() {
        return completo30Datos;
    }

    public void setCompleto30Datos(String completo30Datos) {
        this.completo30Datos = completo30Datos;
    }

    /**
     * @return the idMotivoQueja
     */
    public Integer getIdMotivoQueja() {
        return idMotivoQueja;
    }

    /**
     * @param idMotivoQueja the idMotivoQueja to set
     */
    public void setIdMotivoQueja(Integer idMotivoQueja) {
        this.idMotivoQueja = idMotivoQueja;
    }


    
}
