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
@Table(name = "reg_cedula_citacion_pro_jur")
@NamedQueries({
    @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findAll", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findById", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.id = :id")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findByCodigo", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findByVersion", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.version = :version")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findByCodigoCitacion", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.codigoCitacion = :codigoCitacion")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findByNombreProveedor", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.nombreProveedor = :nombreProveedor")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findByDireccionProveedor", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.direccionProveedor = :direccionProveedor")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findByZonaProveedor", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.zonaProveedor = :zonaProveedor")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findByDptoProveedorMuni", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.dptoProveedorMuni = :dptoProveedorMuni")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findByTelefonosProveedor", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.telefonosProveedor = :telefonosProveedor")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findByDireccionSede", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.direccionSede = :direccionSede")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findByDptoSede", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.dptoSede = :dptoSede")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findByMuniSede", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.muniSede = :muniSede")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findByFechaCita", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.fechaCita = :fechaCita")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findByNoAudiencia", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.noAudiencia = :noAudiencia")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findByNombreConsumidor", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.nombreConsumidor = :nombreConsumidor")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findByFechaCreacion", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findByAnioQueja", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.anioQueja = :anioQueja")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findByQuejanumero", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.quejanumero = :quejanumero")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findByCorrelativo", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.correlativo = :correlativo")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findByIdAudiencia", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.idAudiencia = :id_audiencia")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findByIdTipoRegistro", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.idTipoRegistro = :idTipoRegistro")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findByIdQueja", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.idQueja = :idQueja")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findByIdRegistro", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.idRegistro = :idRegistro")
    , @NamedQuery(name = "TipoReg_CedulaCitacionProJur.findByCreadoPor", query = "SELECT t FROM TipoReg_CedulaCitacionProJur t WHERE t.creadoPor = :creadoPor")})
public class TipoReg_CedulaCitacionProJur implements Serializable {

    private static final long serialVersionUID = 1L;
     @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Size(max = 30)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 10)
    @Column(name = "version")
    private String version;
    @Size(max = 30)
    @Column(name = "codigo_citacion")
    private String codigoCitacion;
    @Size(max = 600)
    @Column(name = "nombre_proveedor")
    private String nombreProveedor;
    @Size(max = 200)
    @Column(name = "direccion_proveedor")
    private String direccionProveedor;
    @Size(max = 10)
    @Column(name = "zona_proveedor")
    private String zonaProveedor;
    @Size(max = 200)
    @Column(name = "dpto_proveedor_muni")
    private String dptoProveedorMuni;
    @Size(max = 200)
    @Column(name = "telefonos_proveedor")
    private String telefonosProveedor;
    @Size(max = 200)
    @Column(name = "direccion_sede")
    private String direccionSede;
    @Size(max = 100)
    @Column(name = "dpto_sede")
    private String dptoSede;
    @Size(max = 100)
    @Column(name = "muni_sede")
    private String muniSede;
    @Column(name = "fecha_cita")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCita;
    @Size(max = 50)
    @Column(name = "no_audiencia")
    private String noAudiencia;
    @Size(max = 600)
    @Column(name = "nombre_consumidor")
    private String nombreConsumidor;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "anio_queja")
    private Integer anioQueja;
    @Column(name = "quejanumero")
    private Integer quejanumero;
    @Size(max = 30)
    @Column(name = "correlativo")
    private String correlativo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_audiencia")
    private int idAudiencia;
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
    @Column(name = "creado_por")
    private Integer creadoPor;

    public TipoReg_CedulaCitacionProJur() {
    }

    public TipoReg_CedulaCitacionProJur(Integer id) {
        this.id = id;
    }

    public TipoReg_CedulaCitacionProJur(Integer id, int idAudiencia, int idTipoRegistro, int idQueja, int idRegistro) {
        this.id = id;
        this.idAudiencia = idAudiencia;
        this.idTipoRegistro = idTipoRegistro;
        this.idQueja = idQueja;
        this.idRegistro = idRegistro;
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

    public String getCodigoCitacion() {
        return codigoCitacion;
    }

    public void setCodigoCitacion(String codigoCitacion) {
        this.codigoCitacion = codigoCitacion;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }

    public String getZonaProveedor() {
        return zonaProveedor;
    }

    public void setZonaProveedor(String zonaProveedor) {
        this.zonaProveedor = zonaProveedor;
    }

    public String getDptoProveedorMuni() {
        return dptoProveedorMuni;
    }

    public void setDptoProveedorMuni(String dptoProveedorMuni) {
        this.dptoProveedorMuni = dptoProveedorMuni;
    }

    public String getTelefonosProveedor() {
        return telefonosProveedor;
    }

    public void setTelefonosProveedor(String telefonosProveedor) {
        this.telefonosProveedor = telefonosProveedor;
    }

    public String getDireccionSede() {
        return direccionSede;
    }

    public void setDireccionSede(String direccionSede) {
        this.direccionSede = direccionSede;
    }

    public String getDptoSede() {
        return dptoSede;
    }

    public void setDptoSede(String dptoSede) {
        this.dptoSede = dptoSede;
    }

    public String getMuniSede() {
        return muniSede;
    }

    public void setMuniSede(String muniSede) {
        this.muniSede = muniSede;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getNoAudiencia() {
        return noAudiencia;
    }

    public void setNoAudiencia(String noAudiencia) {
        this.noAudiencia = noAudiencia;
    }

    public String getNombreConsumidor() {
        return nombreConsumidor;
    }

    public void setNombreConsumidor(String nombreConsumidor) {
        this.nombreConsumidor = nombreConsumidor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getAnioQueja() {
        return anioQueja;
    }

    public void setAnioQueja(Integer anioQueja) {
        this.anioQueja = anioQueja;
    }

    public Integer getQuejanumero() {
        return quejanumero;
    }

    public void setQuejanumero(Integer quejanumero) {
        this.quejanumero = quejanumero;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public int getIdAudiencia() {
        return idAudiencia;
    }

    public void setIdAudiencia(int idAudiencia) {
        this.idAudiencia = idAudiencia;
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

    public Integer getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Integer creadoPor) {
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
        if (!(object instanceof TipoReg_CedulaCitacionProJur)) {
            return false;
        }
        TipoReg_CedulaCitacionProJur other = (TipoReg_CedulaCitacionProJur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.TipoReg_CedulaCitacionProJur[ id=" + id + " ]";
    }
    
}
