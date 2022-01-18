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
@Table(name = "reg_resolucion_apertura_a_prueba")
@NamedQueries({
    @NamedQuery(name = "TipoReg_ResolucionAperturaAPrueba.findAll", query = "SELECT t FROM TipoReg_ResolucionAperturaAPrueba t")
    , @NamedQuery(name = "TipoReg_ResolucionAperturaAPrueba.findById", query = "SELECT t FROM TipoReg_ResolucionAperturaAPrueba t WHERE t.id = :id")
    , @NamedQuery(name = "TipoReg_ResolucionAperturaAPrueba.findByCodigo", query = "SELECT t FROM TipoReg_ResolucionAperturaAPrueba t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "TipoReg_ResolucionAperturaAPrueba.findByVersion", query = "SELECT t FROM TipoReg_ResolucionAperturaAPrueba t WHERE t.version = :version")
    , @NamedQuery(name = "TipoReg_ResolucionAperturaAPrueba.findByQuejanumero", query = "SELECT t FROM TipoReg_ResolucionAperturaAPrueba t WHERE t.quejanumero = :quejanumero")
    , @NamedQuery(name = "TipoReg_ResolucionAperturaAPrueba.findByAsignadoA", query = "SELECT t FROM TipoReg_ResolucionAperturaAPrueba t WHERE t.asignadoA = :asignadoA")
    , @NamedQuery(name = "TipoReg_ResolucionAperturaAPrueba.findByConsumidor", query = "SELECT t FROM TipoReg_ResolucionAperturaAPrueba t WHERE t.consumidor = :consumidor")
    , @NamedQuery(name = "TipoReg_ResolucionAperturaAPrueba.findByProveedor", query = "SELECT t FROM TipoReg_ResolucionAperturaAPrueba t WHERE t.proveedor = :proveedor")
    , @NamedQuery(name = "TipoReg_ResolucionAperturaAPrueba.findByFechaResolucionApertura", query = "SELECT t FROM TipoReg_ResolucionAperturaAPrueba t WHERE t.fechaResolucionApertura = :fechaResolucionApertura")
    , @NamedQuery(name = "TipoReg_ResolucionAperturaAPrueba.findByFechaCreacion", query = "SELECT t FROM TipoReg_ResolucionAperturaAPrueba t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TipoReg_ResolucionAperturaAPrueba.findByCorrelativo", query = "SELECT t FROM TipoReg_ResolucionAperturaAPrueba t WHERE t.correlativo = :correlativo")
    , @NamedQuery(name = "TipoReg_ResolucionAperturaAPrueba.findByIdTipoRegistro", query = "SELECT t FROM TipoReg_ResolucionAperturaAPrueba t WHERE t.idTipoRegistro = :idTipoRegistro")
    , @NamedQuery(name = "TipoReg_ResolucionAperturaAPrueba.findByIdQueja", query = "SELECT t FROM TipoReg_ResolucionAperturaAPrueba t WHERE t.idQueja = :idQueja")
    , @NamedQuery(name = "TipoReg_ResolucionAperturaAPrueba.findByIdRegistro", query = "SELECT t FROM TipoReg_ResolucionAperturaAPrueba t WHERE t.idRegistro = :idRegistro")
    , @NamedQuery(name = "TipoReg_ResolucionAperturaAPrueba.findByIdResultadoAudiencia", query = "SELECT t FROM TipoReg_ResolucionAperturaAPrueba t WHERE t.idResultadoAudiencia = :idResultadoAudiencia")
    , @NamedQuery(name = "TipoReg_ResolucionAperturaAPrueba.findByCreadoPor", query = "SELECT t FROM TipoReg_ResolucionAperturaAPrueba t WHERE t.creadoPor = :creadoPor")})
public class TipoReg_ResolucionAperturaAPrueba implements Serializable {

    private static final long serialVersionUID = 1L;
      @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Size(max = 30)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 30)
    @Column(name = "version")
    private String version;
    @Column(name = "quejanumero")
    private Integer quejanumero;
    @Size(max = 100)
    @Column(name = "asignado_a")
    private String asignadoA;
    @Size(max = 100)
    @Column(name = "consumidor")
    private String consumidor;
    @Size(max = 100)
    @Column(name = "proveedor")
    private String proveedor;
    @Column(name = "fecha_resolucion_apertura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaResolucionApertura;
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
    @Column(name = "id_resultado_audiencia")
    private int idResultadoAudiencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creado_por")
    private int creadoPor;

     @JoinColumn(name = "creado_por", referencedColumnName = "id_usuario",updatable=false, insertable=false)
        private TipoUsuario tipoUsuario;
    
    public TipoReg_ResolucionAperturaAPrueba() {
    }

    public TipoReg_ResolucionAperturaAPrueba(Integer id) {
        this.id = id;
    }

    public TipoReg_ResolucionAperturaAPrueba(Integer id, int idTipoRegistro, int idQueja, int idRegistro, int idResultadoAudiencia, int creadoPor) {
        this.id = id;
        this.idTipoRegistro = idTipoRegistro;
        this.idQueja = idQueja;
        this.idRegistro = idRegistro;
        this.idResultadoAudiencia = idResultadoAudiencia;
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

    public String getAsignadoA() {
        return asignadoA;
    }

    public void setAsignadoA(String asignadoA) {
        this.asignadoA = asignadoA;
    }

    public String getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(String consumidor) {
        this.consumidor = consumidor;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFechaResolucionApertura() {
        return fechaResolucionApertura;
    }

    public void setFechaResolucionApertura(Date fechaResolucionApertura) {
        this.fechaResolucionApertura = fechaResolucionApertura;
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

    public int getIdResultadoAudiencia() {
        return idResultadoAudiencia;
    }

    public void setIdResultadoAudiencia(int idResultadoAudiencia) {
        this.idResultadoAudiencia = idResultadoAudiencia;
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
        if (!(object instanceof TipoReg_ResolucionAperturaAPrueba)) {
            return false;
        }
        TipoReg_ResolucionAperturaAPrueba other = (TipoReg_ResolucionAperturaAPrueba) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.TipoReg_ResolucionAperturaAPrueba[ id=" + id + " ]";
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
