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
@Table(name = "reg_cedula_notificacion_citacion_juridico")
@NamedQueries({
    @NamedQuery(name = "TipoReg_CedulaNotificacionCitacionJuridico.findAll", query = "SELECT t FROM TipoReg_CedulaNotificacionCitacionJuridico t")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionCitacionJuridico.findById", query = "SELECT t FROM TipoReg_CedulaNotificacionCitacionJuridico t WHERE t.id = :id")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionCitacionJuridico.findByIdAudiencia", query = "SELECT t FROM TipoReg_CedulaNotificacionCitacionJuridico t WHERE t.id_audiencia = :id_audiencia")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionCitacionJuridico.findByIdAudienciaTipoCP", query = "SELECT t FROM TipoReg_CedulaNotificacionCitacionJuridico t WHERE t.id_audiencia = :id_audiencia and t.es_consumidor_proveedor= :esCP")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionCitacionJuridico.findByCodigo", query = "SELECT t FROM TipoReg_CedulaNotificacionCitacionJuridico t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionCitacionJuridico.findByVersion", query = "SELECT t FROM TipoReg_CedulaNotificacionCitacionJuridico t WHERE t.version = :version")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionCitacionJuridico.findByQuejanumero", query = "SELECT t FROM TipoReg_CedulaNotificacionCitacionJuridico t WHERE t.quejanumero = :quejanumero")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionCitacionJuridico.findByFechaCreacion", query = "SELECT t FROM TipoReg_CedulaNotificacionCitacionJuridico t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionCitacionJuridico.findByCorrelativoResolucion", query = "SELECT t FROM TipoReg_CedulaNotificacionCitacionJuridico t WHERE t.correlativo = :correlativoResolucion")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionCitacionJuridico.findByIdTipoRegistro", query = "SELECT t FROM TipoReg_CedulaNotificacionCitacionJuridico t WHERE t.idTipoRegistro = :idTipoRegistro")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionCitacionJuridico.findByIdQueja", query = "SELECT t FROM TipoReg_CedulaNotificacionCitacionJuridico t WHERE t.idQueja = :idQueja")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionCitacionJuridico.findByIdRegistro", query = "SELECT t FROM TipoReg_CedulaNotificacionCitacionJuridico t WHERE t.idRegistro = :idRegistro")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionCitacionJuridico.findByCreadoPor", query = "SELECT t FROM TipoReg_CedulaNotificacionCitacionJuridico t WHERE t.creadoPor = :creadoPor")})
public class TipoReg_CedulaNotificacionCitacionJuridico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_audiencia")
    private int id_audiencia;
    @Size(max = 30)
    @Column(name = "codigo")
    private String codigo;
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
    
    @Column(name = "telefono")
    private String telefono; 
    @Column(name = "municipio")        
    private String municipio;
    @Column(name = "departamento")            
    private String departamento;
    @Column(name = "direccion")            
    private String direccion;
    @Column(name = "nombre")            
    private String nombre;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_citacion")            
    private Date fecha_citacion;
    @Column(name = "es_consumidor_proveedor")
    private String es_consumidor_proveedor;

    public TipoReg_CedulaNotificacionCitacionJuridico() {
    }

    public TipoReg_CedulaNotificacionCitacionJuridico(Integer id) {
        this.id = id;
    }

    public TipoReg_CedulaNotificacionCitacionJuridico(Integer id, int idAudiencia, int idTipoRegistro, int idQueja, int idRegistro, int creadoPor) {
        this.id = id;
        this.id_audiencia = idAudiencia;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
        if (!(object instanceof TipoReg_CedulaNotificacionCitacionJuridico)) {
            return false;
        }
        TipoReg_CedulaNotificacionCitacionJuridico other = (TipoReg_CedulaNotificacionCitacionJuridico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.TipoReg_CedulaNotificacionCitacionJuridico[ id=" + id + " ]";
    }

    /**
     * @return the id_audiencia
     */
    public int getId_audiencia() {
        return id_audiencia;
    }

    /**
     * @param id_audiencia the id_audiencia to set
     */
    public void setId_audiencia(int id_audiencia) {
        this.id_audiencia = id_audiencia;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * @return the departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento the departamento to set
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the fecha_citacion
     */
    public Date getFecha_citacion() {
        return fecha_citacion;
    }

    /**
     * @param fecha_citacion the fecha_citacion to set
     */
    public void setFecha_citacion(Date fecha_citacion) {
        this.fecha_citacion = fecha_citacion;
    }

    /**
     * @return the es_consumidor_proveedor
     */
    public String getEs_consumidor_proveedor() {
        return es_consumidor_proveedor;
    }

    /**
     * @param es_consumidor_proveedor the es_consumidor_proveedor to set
     */
    public void setEs_consumidor_proveedor(String es_consumidor_proveedor) {
        this.es_consumidor_proveedor = es_consumidor_proveedor;
    }
    
}
