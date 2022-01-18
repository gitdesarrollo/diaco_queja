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
@Table(name = "reg_cedula_notificacion_res_correo")
@NamedQueries({
    @NamedQuery(name = "TipoReg_CedulaNotificacionResCorreo.findAll", query = "SELECT t FROM TipoReg_CedulaNotificacionResCorreo t")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResCorreo.findById", query = "SELECT t FROM TipoReg_CedulaNotificacionResCorreo t WHERE t.id = :id")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResCorreo.findByIdAudiencia", query = "SELECT t FROM TipoReg_CedulaNotificacionResCorreo t WHERE t.id_audiencia = :id_audiencia")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResCorreo.findByCodigo", query = "SELECT t FROM TipoReg_CedulaNotificacionResCorreo t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResCorreo.findByVersion", query = "SELECT t FROM TipoReg_CedulaNotificacionResCorreo t WHERE t.version = :version")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResCorreo.findByQuejanumero", query = "SELECT t FROM TipoReg_CedulaNotificacionResCorreo t WHERE t.quejanumero = :quejanumero")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResCorreo.findByNotificacionHacia", query = "SELECT t FROM TipoReg_CedulaNotificacionResCorreo t WHERE t.notificacionHacia = :notificacionHacia")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResCorreo.findByFechaResolucion", query = "SELECT t FROM TipoReg_CedulaNotificacionResCorreo t WHERE t.fechaResolucion = :fechaResolucion")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResCorreo.findByFechaCreacion", query = "SELECT t FROM TipoReg_CedulaNotificacionResCorreo t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResCorreo.findByCorrelativoNotificacion", query = "SELECT t FROM TipoReg_CedulaNotificacionResCorreo t WHERE t.correlativoNotificacion = :correlativoNotificacion")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResCorreo.findByIdTipoRegistro", query = "SELECT t FROM TipoReg_CedulaNotificacionResCorreo t WHERE t.idTipoRegistro = :idTipoRegistro")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResCorreo.findByIdQueja", query = "SELECT t FROM TipoReg_CedulaNotificacionResCorreo t WHERE t.idQueja = :idQueja")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResCorreo.findByIdRegistro", query = "SELECT t FROM TipoReg_CedulaNotificacionResCorreo t WHERE t.idRegistro = :idRegistro")
    , @NamedQuery(name = "TipoReg_CedulaNotificacionResCorreo.findByCreadoPor", query = "SELECT t FROM TipoReg_CedulaNotificacionResCorreo t WHERE t.creadoPor = :creadoPor")})
public class TipoReg_CedulaNotificacionResCorreo implements Serializable {

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
    @Size(max = 100)
    @Column(name = "notificacion_hacia")
    private String notificacionHacia;
    @Column(name = "fecha_resolucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaResolucion;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Size(max = 30)
    @Column(name = "correlativo_notificacion")
    private String correlativoNotificacion;
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

    public TipoReg_CedulaNotificacionResCorreo() {
    }

    public TipoReg_CedulaNotificacionResCorreo(Integer id) {
        this.id = id;
    }

    public TipoReg_CedulaNotificacionResCorreo(Integer id, int idAudiencia, int idTipoRegistro, int idQueja, int idRegistro, int creadoPor) {
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

    public String getNotificacionHacia() {
        return notificacionHacia;
    }

    public void setNotificacionHacia(String notificacionHacia) {
        this.notificacionHacia = notificacionHacia;
    }

    public Date getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCorrelativoNotificacion() {
        return correlativoNotificacion;
    }

    public void setCorrelativoNotificacion(String correlativoNotificacion) {
        this.correlativoNotificacion = correlativoNotificacion;
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
        if (!(object instanceof TipoReg_CedulaNotificacionResCorreo)) {
            return false;
        }
        TipoReg_CedulaNotificacionResCorreo other = (TipoReg_CedulaNotificacionResCorreo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.TipoReg_CedulaNotificacionResCorreo[ id=" + id + " ]";
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
    
}
