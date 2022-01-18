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
@Table(name = "reg_cedula_citacion_con_jur")
@NamedQueries({
    @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findAll", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findById", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.id = :id")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findByCodigo", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findByVersion", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.version = :version")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findByCodigoCitacion", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.codigoCitacion = :codigoCitacion")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findByNombreConsumidor", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.nombreConsumidor = :nombreConsumidor")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findByDireccionConsumidor", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.direccionConsumidor = :direccionConsumidor")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findByZonaConsumidor", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.zonaConsumidor = :zonaConsumidor")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findByDptoConsumidorMuni", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.dptoConsumidorMuni = :dptoConsumidorMuni")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findByTelefonosConsumidor", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.telefonosConsumidor = :telefonosConsumidor")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findByDireccionSede", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.direccionSede = :direccionSede")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findByDptoSede", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.dptoSede = :dptoSede")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findByMuniSede", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.muniSede = :muniSede")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findByFechaCita", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.fechaCita = :fechaCita")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findByNoAudiencia", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.noAudiencia = :noAudiencia")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findByNombreProveedor", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.nombreProveedor = :nombreProveedor")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findByFechaCreacion", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findByAnioQueja", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.anioQueja = :anioQueja")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findByQuejanumero", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.quejanumero = :quejanumero")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findByCorrelativo", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.correlativo = :correlativo")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findByIdAudiencia", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.idAudiencia = :id_audiencia")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findByIdTipoRegistro", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.idTipoRegistro = :idTipoRegistro")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findByIdQueja", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.idQueja = :idQueja")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findByIdRegistro", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.idRegistro = :idRegistro")
    , @NamedQuery(name = "TIpoReg_CedulaCitacionConJur.findByCreadoPor", query = "SELECT t FROM TIpoReg_CedulaCitacionConJur t WHERE t.creadoPor = :creadoPor")})
public class TIpoReg_CedulaCitacionConJur implements Serializable {

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
    @Column(name = "nombre_consumidor")
    private String nombreConsumidor;
    @Size(max = 200)
    @Column(name = "direccion_consumidor")
    private String direccionConsumidor;
    @Size(max = 10)
    @Column(name = "zona_consumidor")
    private String zonaConsumidor;
    @Size(max = 200)
    @Column(name = "dpto_consumidor_muni")
    private String dptoConsumidorMuni;
    @Size(max = 200)
    @Column(name = "telefonos_consumidor")
    private String telefonosConsumidor;
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
    @Column(name = "nombre_proveedor")
    private String nombreProveedor;
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

    public TIpoReg_CedulaCitacionConJur() {
    }

    public TIpoReg_CedulaCitacionConJur(Integer id) {
        this.id = id;
    }

    public TIpoReg_CedulaCitacionConJur(Integer id, int idAudiencia, int idTipoRegistro, int idQueja, int idRegistro) {
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

    public String getNombreConsumidor() {
        return nombreConsumidor;
    }

    public void setNombreConsumidor(String nombreConsumidor) {
        this.nombreConsumidor = nombreConsumidor;
    }

    public String getDireccionConsumidor() {
        return direccionConsumidor;
    }

    public void setDireccionConsumidor(String direccionConsumidor) {
        this.direccionConsumidor = direccionConsumidor;
    }

    public String getZonaConsumidor() {
        return zonaConsumidor;
    }

    public void setZonaConsumidor(String zonaConsumidor) {
        this.zonaConsumidor = zonaConsumidor;
    }

    public String getDptoConsumidorMuni() {
        return dptoConsumidorMuni;
    }

    public void setDptoConsumidorMuni(String dptoConsumidorMuni) {
        this.dptoConsumidorMuni = dptoConsumidorMuni;
    }

    public String getTelefonosConsumidor() {
        return telefonosConsumidor;
    }

    public void setTelefonosConsumidor(String telefonosConsumidor) {
        this.telefonosConsumidor = telefonosConsumidor;
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

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
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
        if (!(object instanceof TIpoReg_CedulaCitacionConJur)) {
            return false;
        }
        TIpoReg_CedulaCitacionConJur other = (TIpoReg_CedulaCitacionConJur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.TIpoReg_CedulaCitacionConJur[ id=" + id + " ]";
    }
    
}
