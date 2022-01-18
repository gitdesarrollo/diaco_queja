/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author julio
 */
@Entity
@Table(name = "reg_cedula_notificacion_res_juridico")
@NamedQueries({
    @NamedQuery(name = "TipoReg_CedulaNotificacionResJuridico.findAll", query = "SELECT t FROM TipoReg_CedulaNotificacionResJuridico t")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResJuridico.findById", query = "SELECT t FROM TipoReg_CedulaNotificacionResJuridico t WHERE t.id = :id")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResJuridico.findByIdResultadoResolucion", query = "SELECT t FROM TipoReg_CedulaNotificacionResJuridico t WHERE t.idResultadoResolucion = :idResultadoResolucion")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResJuridico.findByEsCP", query = "SELECT t FROM TipoReg_CedulaNotificacionResJuridico t WHERE t.esCP = :esCP")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResJuridico.findByEsResultadoResolucion", query = "SELECT t FROM TipoReg_CedulaNotificacionResJuridico t WHERE t.esResultadoResolucion = :esResultadoResolucion")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResJuridico.findByCodigo", query = "SELECT t FROM TipoReg_CedulaNotificacionResJuridico t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResJuridico.findByTelefono", query = "SELECT t FROM TipoReg_CedulaNotificacionResJuridico t WHERE t.telefono = :telefono")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResJuridico.findByMunicipio", query = "SELECT t FROM TipoReg_CedulaNotificacionResJuridico t WHERE t.municipio = :municipio")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResJuridico.findByDepartamento", query = "SELECT t FROM TipoReg_CedulaNotificacionResJuridico t WHERE t.departamento = :departamento")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResJuridico.findByDireccion", query = "SELECT t FROM TipoReg_CedulaNotificacionResJuridico t WHERE t.direccion = :direccion")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResJuridico.findByNotificoA", query = "SELECT t FROM TipoReg_CedulaNotificacionResJuridico t WHERE t.notificoA = :notificoA")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResJuridico.findByFechaResolucion", query = "SELECT t FROM TipoReg_CedulaNotificacionResJuridico t WHERE t.fechaResolucion = :fechaResolucion")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResJuridico.findByUsuarioCreacion", query = "SELECT t FROM TipoReg_CedulaNotificacionResJuridico t WHERE t.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResJuridico.findByVersion", query = "SELECT t FROM TipoReg_CedulaNotificacionResJuridico t WHERE t.version = :version")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResJuridico.findByQuejanumero", query = "SELECT t FROM TipoReg_CedulaNotificacionResJuridico t WHERE t.quejanumero = :quejanumero")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResJuridico.findByFechaCreacion", query = "SELECT t FROM TipoReg_CedulaNotificacionResJuridico t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResJuridico.findByCorrelativo", query = "SELECT t FROM TipoReg_CedulaNotificacionResJuridico t WHERE t.correlativo = :correlativo")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResJuridico.findByIdTipoRegistro", query = "SELECT t FROM TipoReg_CedulaNotificacionResJuridico t WHERE t.idTipoRegistro = :idTipoRegistro")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResJuridico.findByIdQueja", query = "SELECT t FROM TipoReg_CedulaNotificacionResJuridico t WHERE t.idQueja = :idQueja")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResJuridico.findByIdRegistro", query = "SELECT t FROM TipoReg_CedulaNotificacionResJuridico t WHERE t.idRegistro = :idRegistro")
        , @NamedQuery(name = "TipoReg_CedulaNotificacionResJuridico.findByIdResCP", query = "SELECT t FROM TipoReg_CedulaNotificacionResJuridico t WHERE t.idQueja = :idQueja and t.esCP = :esCP and t.esResultadoResolucion = :esResultadoResolucion ")
        , @NamedQuery(name = "TipoReg_CedulaNotificacionResJuridico.findByIdResQuejaAud", query = "SELECT t FROM TipoReg_CedulaNotificacionResJuridico t WHERE t.idQueja = :idQueja and t.esResultadoResolucion = :esResultadoResolucion ")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResJuridico.findByCreadoPor", query = "SELECT t FROM TipoReg_CedulaNotificacionResJuridico t WHERE t.creadoPor = :creadoPor")})
public class TipoReg_CedulaNotificacionResJuridico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_resultado_resolucion")
    private Integer idResultadoResolucion;
    @Size(max = 1)
    @Column(name = "es_CP")
    private String esCP;
    @Size(max = 25)
    @Column(name = "es_resultado_resolucion")
    private String esResultadoResolucion;
    @Size(max = 30)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 150)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 100)
    @Column(name = "municipio")
    private String municipio;
    @Size(max = 100)
    @Column(name = "departamento")
    private String departamento;
    @Size(max = 150)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 150)
    @Column(name = "notifico_a")
    private String notificoA;
    @Column(name = "fecha_resolucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaResolucion;
    @Column(name = "usuario_creacion")
    private Integer usuarioCreacion;
    @Size(max = 30)
    @Column(name = "version")
    private String version;
    @Column(name = "quejanumero")
    private Integer quejanumero;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Size(max = 30)
    @Column(name = "correlativo")
    private String correlativo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo_registro")
    private int idTipoRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_queja")
    private int idQueja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_registro")
    private int idRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creado_por")
    private int creadoPor;
     @Column(name = "anio")
    private int anio;
    
    @JoinColumn(name = "creado_por", referencedColumnName = "id_usuario",updatable=false, insertable=false)
        private TipoUsuario tipoUsuario;
        

    public TipoReg_CedulaNotificacionResJuridico() {
    }

    public TipoReg_CedulaNotificacionResJuridico(Integer id) {
        this.id = id;
    }

    public TipoReg_CedulaNotificacionResJuridico(Integer id, int idTipoRegistro, int idQueja, int idRegistro, int creadoPor) {
        this.id = id;
        this.idTipoRegistro = idTipoRegistro;
        this.idQueja = idQueja;
        this.idRegistro = idRegistro;
        this.creadoPor = creadoPor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdResultadoResolucion() {
        return idResultadoResolucion;
    }

    public void setIdResultadoResolucion(Integer idResultadoResolucion) {
        this.idResultadoResolucion = idResultadoResolucion;
    }

    public String getEsCP() {
        return esCP;
    }

    public void setEsCP(String esCP) {
        this.esCP = esCP;
    }

    public String getEsResultadoResolucion() {
        return esResultadoResolucion;
    }

    public void setEsResultadoResolucion(String esResultadoResolucion) {
        this.esResultadoResolucion = esResultadoResolucion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNotificoA() {
        return notificoA;
    }

    public void setNotificoA(String notificoA) {
        this.notificoA = notificoA;
    }

    public Date getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public Integer getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(Integer usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getQuejanumero() {
        return quejanumero;
    }

    public void setQuejanumero(Integer quejanumero) {
        this.quejanumero = quejanumero;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public int getIdTipoRegistro() {
        return idTipoRegistro;
    }

    public void setIdTipoRegistro(int idTipoRegistro) {
        this.idTipoRegistro = idTipoRegistro;
    }

    public int getIdQueja() {
        return idQueja;
    }

    public void setIdQueja(int idQueja) {
        this.idQueja = idQueja;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(int creadoPor) {
        this.creadoPor = creadoPor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoReg_CedulaNotificacionResJuridico)) {
            return false;
        }
        TipoReg_CedulaNotificacionResJuridico other = (TipoReg_CedulaNotificacionResJuridico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.TipoReg_CedulaNotificacionResJuridico[ id=" + id + " ]";
    }

    /**
     * @return the tipoUsuario
     */
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @param tipoUsuario the tipoUsuario to set
     */
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * @return the anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }
    
}
