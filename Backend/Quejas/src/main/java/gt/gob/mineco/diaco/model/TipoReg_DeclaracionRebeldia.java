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
@Table(name = "reg_declaracion_rebeldia")
@NamedQueries({
    @NamedQuery(name = "TipoReg_DeclaracionRebeldia.findAll", query = "SELECT t FROM TipoReg_DeclaracionRebeldia t")
    , @NamedQuery(name = "TipoReg_DeclaracionRebeldia.findById", query = "SELECT t FROM TipoReg_DeclaracionRebeldia t WHERE t.id = :id")
    , @NamedQuery(name = "TipoReg_DeclaracionRebeldia.findByIdResultadoAudiencia", query = "SELECT t FROM TipoReg_DeclaracionRebeldia t WHERE t.idResultadoAudiencia = :idResultadoAudiencia")
    , @NamedQuery(name = "TipoReg_DeclaracionRebeldia.findByAnio", query = "SELECT t FROM TipoReg_DeclaracionRebeldia t WHERE t.anio = :anio")
    , @NamedQuery(name = "TipoReg_DeclaracionRebeldia.findByConsumidorNombre", query = "SELECT t FROM TipoReg_DeclaracionRebeldia t WHERE t.consumidorNombre = :consumidorNombre")
    , @NamedQuery(name = "TipoReg_DeclaracionRebeldia.findByProveedorNombre", query = "SELECT t FROM TipoReg_DeclaracionRebeldia t WHERE t.proveedorNombre = :proveedorNombre")
    , @NamedQuery(name = "TipoReg_DeclaracionRebeldia.findByCodigo", query = "SELECT t FROM TipoReg_DeclaracionRebeldia t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "TipoReg_DeclaracionRebeldia.findByUsuarioCreacion", query = "SELECT t FROM TipoReg_DeclaracionRebeldia t WHERE t.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "TipoReg_DeclaracionRebeldia.findByVersion", query = "SELECT t FROM TipoReg_DeclaracionRebeldia t WHERE t.version = :version")
    , @NamedQuery(name = "TipoReg_DeclaracionRebeldia.findByQuejanumero", query = "SELECT t FROM TipoReg_DeclaracionRebeldia t WHERE t.quejanumero = :quejanumero")
    , @NamedQuery(name = "TipoReg_DeclaracionRebeldia.findByFechaNotificacion", query = "SELECT t FROM TipoReg_DeclaracionRebeldia t WHERE t.fechaNotificacion = :fechaNotificacion")
    , @NamedQuery(name = "TipoReg_DeclaracionRebeldia.findByFechaSegundaAudiencia", query = "SELECT t FROM TipoReg_DeclaracionRebeldia t WHERE t.fechaSegundaAudiencia = :fechaSegundaAudiencia")
    , @NamedQuery(name = "TipoReg_DeclaracionRebeldia.findByFechaCreacion", query = "SELECT t FROM TipoReg_DeclaracionRebeldia t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TipoReg_DeclaracionRebeldia.findByCorrelativo", query = "SELECT t FROM TipoReg_DeclaracionRebeldia t WHERE t.correlativo = :correlativo")
    , @NamedQuery(name = "TipoReg_DeclaracionRebeldia.findByIdTipoRegistro", query = "SELECT t FROM TipoReg_DeclaracionRebeldia t WHERE t.idTipoRegistro = :idTipoRegistro")
    , @NamedQuery(name = "TipoReg_DeclaracionRebeldia.findByIdQueja", query = "SELECT t FROM TipoReg_DeclaracionRebeldia t WHERE t.idQueja = :idQueja")
    , @NamedQuery(name = "TipoReg_DeclaracionRebeldia.findByIdRegistro", query = "SELECT t FROM TipoReg_DeclaracionRebeldia t WHERE t.idRegistro = :idRegistro")
    , @NamedQuery(name = "TipoReg_DeclaracionRebeldia.findByCreadoPor", query = "SELECT t FROM TipoReg_DeclaracionRebeldia t WHERE t.creadoPor = :creadoPor")})
public class TipoReg_DeclaracionRebeldia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_resultado_audiencia")
    private Integer idResultadoAudiencia;
    @Column(name = "anio")
    private Integer anio;
    @Size(max = 150)
    @Column(name = "consumidor_nombre")
    private String consumidorNombre;
    @Size(max = 150)
    @Column(name = "proveedor_nombre")
    private String proveedorNombre;
    @Size(max = 30)
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "usuario_creacion")
    private Integer usuarioCreacion;
    @Size(max = 30)
    @Column(name = "version")
    private String version;
    @Column(name = "quejanumero")
    private Integer quejanumero;
    @Column(name = "fecha_notificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNotificacion;
    @Column(name = "fecha_segunda_audiencia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSegundaAudiencia;
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
    
    @JoinColumn(name = "creado_por", referencedColumnName = "id_usuario",updatable=false, insertable=false)
        private TipoUsuario tipoUsuario;

    public TipoReg_DeclaracionRebeldia() {
    }

    public TipoReg_DeclaracionRebeldia(Integer id) {
        this.id = id;
    }

    public TipoReg_DeclaracionRebeldia(Integer id, int idTipoRegistro, int idQueja, int idRegistro, int creadoPor) {
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

    public Integer getIdResultadoAudiencia() {
        return idResultadoAudiencia;
    }

    public void setIdResultadoAudiencia(Integer idResultadoAudiencia) {
        this.idResultadoAudiencia = idResultadoAudiencia;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getConsumidorNombre() {
        return consumidorNombre;
    }

    public void setConsumidorNombre(String consumidorNombre) {
        this.consumidorNombre = consumidorNombre;
    }

    public String getProveedorNombre() {
        return proveedorNombre;
    }

    public void setProveedorNombre(String proveedorNombre) {
        this.proveedorNombre = proveedorNombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Date getFechaSegundaAudiencia() {
        return fechaSegundaAudiencia;
    }

    public void setFechaSegundaAudiencia(Date fechaSegundaAudiencia) {
        this.fechaSegundaAudiencia = fechaSegundaAudiencia;
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
        if (!(object instanceof TipoReg_DeclaracionRebeldia)) {
            return false;
        }
        TipoReg_DeclaracionRebeldia other = (TipoReg_DeclaracionRebeldia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.TipoReg_DeclaracionRebeldia[ id=" + id + " ]";
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
    
}
