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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author julio
 */
@Entity
@Table(name = "reg_res_archivo_unica_audiencia_conc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoReg_ResArchivoUnicaAudienciaConc.findAll", query = "SELECT t FROM TipoReg_ResArchivoUnicaAudienciaConc t")
    , @NamedQuery(name = "TipoReg_ResArchivoUnicaAudienciaConc.findById", query = "SELECT t FROM TipoReg_ResArchivoUnicaAudienciaConc t WHERE t.id = :id")
    , @NamedQuery(name = "TipoReg_ResArchivoUnicaAudienciaConc.findByIdCorrelativoUnicaAudiencia", query = "SELECT t FROM TipoReg_ResArchivoUnicaAudienciaConc t WHERE t.idCorrelativoUnicaAudiencia = :idCorrelativoUnicaAudiencia")
    , @NamedQuery(name = "TipoReg_ResArchivoUnicaAudienciaConc.findByFechaArchUnicoConc", query = "SELECT t FROM TipoReg_ResArchivoUnicaAudienciaConc t WHERE t.fechaArchUnicoConc = :fechaArchUnicoConc")
    , @NamedQuery(name = "TipoReg_ResArchivoUnicaAudienciaConc.findByNombreConsumidor", query = "SELECT t FROM TipoReg_ResArchivoUnicaAudienciaConc t WHERE t.nombreConsumidor = :nombreConsumidor")
    , @NamedQuery(name = "TipoReg_ResArchivoUnicaAudienciaConc.findByNombreProveedor", query = "SELECT t FROM TipoReg_ResArchivoUnicaAudienciaConc t WHERE t.nombreProveedor = :nombreProveedor")
    , @NamedQuery(name = "TipoReg_ResArchivoUnicaAudienciaConc.findByFechaCreacion", query = "SELECT t FROM TipoReg_ResArchivoUnicaAudienciaConc t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TipoReg_ResArchivoUnicaAudienciaConc.findByIdTipoRegistro", query = "SELECT t FROM TipoReg_ResArchivoUnicaAudienciaConc t WHERE t.idTipoRegistro = :idTipoRegistro")
    , @NamedQuery(name = "TipoReg_ResArchivoUnicaAudienciaConc.findByIdQueja", query = "SELECT t FROM TipoReg_ResArchivoUnicaAudienciaConc t WHERE t.idQueja = :idQueja")
    , @NamedQuery(name = "TipoReg_ResArchivoUnicaAudienciaConc.findByCreadoPor", query = "SELECT t FROM TipoReg_ResArchivoUnicaAudienciaConc t WHERE t.creadoPor = :creadoPor")})
public class TipoReg_ResArchivoUnicaAudienciaConc implements Serializable {

    private static final long serialVersionUID = 1L;
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_correlativo_unica_audiencia")
    private int idCorrelativoUnicaAudiencia;
    @Column(name = "fecha_arch_unico_conc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaArchUnicoConc;
    @Size(max = 500)
    @Column(name = "nombre_consumidor")
    private String nombreConsumidor;
    @Size(max = 500)
    @Column(name = "nombre_proveedor")
    private String nombreProveedor;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
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
    @Column(name = "creado_por")
    private int creadoPor;
    @Column(name = "no_queja")
    private String no_queja;

    public TipoReg_ResArchivoUnicaAudienciaConc() {
    }

    public TipoReg_ResArchivoUnicaAudienciaConc(Integer id) {
        this.id = id;
    }

    public TipoReg_ResArchivoUnicaAudienciaConc(Integer id, int idCorrelativoUnicaAudiencia, int idTipoRegistro, int idQueja, int creadoPor) {
        this.id = id;
        this.idCorrelativoUnicaAudiencia = idCorrelativoUnicaAudiencia;
        this.idTipoRegistro = idTipoRegistro;
        this.idQueja = idQueja;
        this.creadoPor = creadoPor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdCorrelativoUnicaAudiencia() {
        return idCorrelativoUnicaAudiencia;
    }

    public void setIdCorrelativoUnicaAudiencia(int idCorrelativoUnicaAudiencia) {
        this.idCorrelativoUnicaAudiencia = idCorrelativoUnicaAudiencia;
    }

    public Date getFechaArchUnicoConc() {
        return fechaArchUnicoConc;
    }

    public void setFechaArchUnicoConc(Date fechaArchUnicoConc) {
        this.fechaArchUnicoConc = fechaArchUnicoConc;
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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
        if (!(object instanceof TipoReg_ResArchivoUnicaAudienciaConc)) {
            return false;
        }
        TipoReg_ResArchivoUnicaAudienciaConc other = (TipoReg_ResArchivoUnicaAudienciaConc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }


   @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id",this.id);
            temp.addProperty("idCorrelativo",this.idCorrelativoUnicaAudiencia);
            temp.addProperty("idQueja",this.idQueja);
  
            return temp.toString()+",";
        }    

    /**
     * @return the no_queja
     */
    public String getNo_queja() {
        return no_queja;
    }

    /**
     * @param no_queja the no_queja to set
     */
    public void setNo_queja(String no_queja) {
        this.no_queja = no_queja;
    }
}
