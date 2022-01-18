/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
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
@Table(name = "reg_resolucion_archivo_verificacion")
@NamedQueries({
    @NamedQuery(name = "TipoReg_ResArchivoVerificacion.findAll", query = "SELECT t FROM TipoReg_ResArchivoVerificacion t")
    , @NamedQuery(name = "TipoReg_ResArchivoVerificacion.findById", query = "SELECT t FROM TipoReg_ResArchivoVerificacion t WHERE t.id = :id")
    , @NamedQuery(name = "TipoReg_ResArchivoVerificacion.findByVersion", query = "SELECT t FROM TipoReg_ResArchivoVerificacion t WHERE t.version = :version")
    , @NamedQuery(name = "TipoReg_ResArchivoVerificacion.findByFechaVerificacion", query = "SELECT t FROM TipoReg_ResArchivoVerificacion t WHERE t.fechaVerificacion = :fechaVerificacion")
    , @NamedQuery(name = "TipoReg_ResArchivoVerificacion.findByInicialesVerificador", query = "SELECT t FROM TipoReg_ResArchivoVerificacion t WHERE t.inicialesVerificador = :inicialesVerificador")
    , @NamedQuery(name = "TipoReg_ResArchivoVerificacion.findByAnioQueja", query = "SELECT t FROM TipoReg_ResArchivoVerificacion t WHERE t.anioQueja = :anioQueja")
    , @NamedQuery(name = "TipoReg_ResArchivoVerificacion.findByQuejanumero", query = "SELECT t FROM TipoReg_ResArchivoVerificacion t WHERE t.quejanumero = :quejanumero")
    , @NamedQuery(name = "TipoReg_ResArchivoVerificacion.findByFechaCreacion", query = "SELECT t FROM TipoReg_ResArchivoVerificacion t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TipoReg_ResArchivoVerificacion.findByNombreConsumidor", query = "SELECT t FROM TipoReg_ResArchivoVerificacion t WHERE t.nombreConsumidor = :nombreConsumidor")
    , @NamedQuery(name = "TipoReg_ResArchivoVerificacion.findByNombreProveedor", query = "SELECT t FROM TipoReg_ResArchivoVerificacion t WHERE t.nombreProveedor = :nombreProveedor")
    , @NamedQuery(name = "TipoReg_ResArchivoVerificacion.findByIdTipoRegistro", query = "SELECT t FROM TipoReg_ResArchivoVerificacion t WHERE t.idTipoRegistro = :idTipoRegistro")
    , @NamedQuery(name = "TipoReg_ResArchivoVerificacion.findByIdQueja", query = "SELECT t FROM TipoReg_ResArchivoVerificacion t WHERE t.idQueja = :idQueja")
    , @NamedQuery(name = "TipoReg_ResArchivoVerificacion.findByIdRegistro", query = "SELECT t FROM TipoReg_ResArchivoVerificacion t WHERE t.idRegistro = :idRegistro")
    , @NamedQuery(name = "TipoReg_ResArchivoVerificacion.findByCreadoPor", query = "SELECT t FROM TipoReg_ResArchivoVerificacion t WHERE t.creadoPor = :creadoPor")})
public class TipoReg_ResArchivoVerificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "version")
    private String version;
    @Column(name="codigo")
    private String codigo;
    @Column(name = "fecha_verificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVerificacion;
    @Column(name = "iniciales_verificador")
    private String inicialesVerificador;
    @Column(name = "anio_queja")
    private Integer anioQueja;
    @Column(name = "quejanumero")
    private Integer quejanumero;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "nombre_consumidor")
    private String nombreConsumidor;
    @Column(name = "nombre_proveedor")
    private String nombreProveedor;
    @Column(name = "id_tipo_registro")
    private int idTipoRegistro;
    @Column(name = "id_queja")
    private int idQueja;
    @Column(name = "id_registro")
    private int idRegistro;
    @Column(name = "creado_por")
    private int creadoPor;
    @Column(name="correlativo_resolucion")        
    private String correlativo_resolucion;

    public TipoReg_ResArchivoVerificacion() {
    }

    public TipoReg_ResArchivoVerificacion(Integer id) {
        this.id = id;
    }

    public TipoReg_ResArchivoVerificacion(Integer id, Date fechaVerificacion, String inicialesVerificador, String nombreConsumidor, String nombreProveedor, int idTipoRegistro, int idQueja, int idRegistro, int creadoPor) {
        this.id = id;
        this.fechaVerificacion = fechaVerificacion;
        this.inicialesVerificador = inicialesVerificador;
        this.nombreConsumidor = nombreConsumidor;
        this.nombreProveedor = nombreProveedor;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getFechaVerificacion() {
        return fechaVerificacion;
    }

    public void setFechaVerificacion(Date fechaVerificacion) {
        this.fechaVerificacion = fechaVerificacion;
    }

    public String getInicialesVerificador() {
        return inicialesVerificador;
    }

    public void setInicialesVerificador(String inicialesVerificador) {
        this.inicialesVerificador = inicialesVerificador;
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombreConsumidor() {
        return nombreConsumidor;
    }

    public void setNombreConsumidor(String nombreConsumidor) {
        this.nombreConsumidor = nombreConsumidor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
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
        if (!(object instanceof TipoReg_ResArchivoVerificacion)) {
            return false;
        }
        TipoReg_ResArchivoVerificacion other = (TipoReg_ResArchivoVerificacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    
        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id",this.id);
            temp.addProperty("codigo",this.codigo);
            temp.addProperty("id_queja",this.idQueja);
            temp.addProperty("id_registro",this.idRegistro);
            temp.addProperty("id_tipo_registro",this.idTipoRegistro);
            temp.addProperty("anio_queja",this.anioQueja);
            temp.addProperty("quejanumero",this.quejanumero);
            temp.addProperty("version",this.version);
            temp.addProperty("iniciales_verificador",this.inicialesVerificador);
            temp.addProperty("nombre_consumidor",this.nombreConsumidor);
            temp.addProperty("nombre_proveedor",this.nombreProveedor);
            return temp.toString()+",";
        }

    /**
     * @return the correlativo_resolucion
     */
    public String getCorrelativo_resolucion() {
        return correlativo_resolucion;
    }

    /**
     * @param correlativo_resolucion the correlativo_resolucion to set
     */
    public void setCorrelativo_resolucion(String correlativo_resolucion) {
        this.correlativo_resolucion = correlativo_resolucion;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
}
