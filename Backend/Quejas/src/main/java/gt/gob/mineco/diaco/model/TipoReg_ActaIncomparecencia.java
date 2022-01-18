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

/**
 *
 * @author julio
 */
@Entity
@Table(name = "reg_acta_incomparecencia")
@NamedQueries({
    @NamedQuery(name = "TipoReg_ActaIncomparecencia.findAll", query = "SELECT t FROM TipoReg_ActaIncomparecencia t")
    , @NamedQuery(name = "TipoReg_ActaIncomparecencia.findById", query = "SELECT t FROM TipoReg_ActaIncomparecencia t WHERE t.id = :id")
    , @NamedQuery(name = "TipoReg_ActaIncomparecencia.findByIdCorrelativoActaIncomparecencia", query = "SELECT t FROM TipoReg_ActaIncomparecencia t WHERE t.idCorrelativoActaIncomparecencia = :idCorrelativoActaIncomparecencia")
    , @NamedQuery(name = "TipoReg_ActaIncomparecencia.findByFechaActaIncomparecencia", query = "SELECT t FROM TipoReg_ActaIncomparecencia t WHERE t.fechaActaIncomparecencia = :fechaActaIncomparecencia")
    , @NamedQuery(name = "TipoReg_ActaIncomparecencia.findByFechaCreacion", query = "SELECT t FROM TipoReg_ActaIncomparecencia t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TipoReg_ActaIncomparecencia.findByIdTipoRegistro", query = "SELECT t FROM TipoReg_ActaIncomparecencia t WHERE t.idTipoRegistro = :idTipoRegistro")
    , @NamedQuery(name = "TipoReg_ActaIncomparecencia.findByIdQueja", query = "SELECT t FROM TipoReg_ActaIncomparecencia t WHERE t.idQueja = :idQueja")
    , @NamedQuery(name = "TipoReg_ActaIncomparecencia.findByCreadoPor", query = "SELECT t FROM TipoReg_ActaIncomparecencia t WHERE t.creadoPor = :creadoPor")})
public class TipoReg_ActaIncomparecencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_correlativo_acta_incomparecencia")
    private String idCorrelativoActaIncomparecencia;
    @Column(name = "fecha_acta_incomparecencia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActaIncomparecencia;
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

    public TipoReg_ActaIncomparecencia() {
    }

    public TipoReg_ActaIncomparecencia(Integer id) {
        this.id = id;
    }

    public TipoReg_ActaIncomparecencia(Integer id, String idCorrelativoActaIncomparecencia, int idTipoRegistro, int idQueja, int creadoPor) {
        this.id = id;
        this.idCorrelativoActaIncomparecencia = idCorrelativoActaIncomparecencia;
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

    public String getIdCorrelativoActaIncomparecencia() {
        return idCorrelativoActaIncomparecencia;
    }

    public void setIdCorrelativoActaIncomparecencia(String idCorrelativoActaIncomparecencia) {
        this.idCorrelativoActaIncomparecencia = idCorrelativoActaIncomparecencia;
    }

    public Date getFechaActaIncomparecencia() {
        return fechaActaIncomparecencia;
    }

    public void setFechaActaIncomparecencia(Date fechaActaIncomparecencia) {
        this.fechaActaIncomparecencia = fechaActaIncomparecencia;
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
        if (!(object instanceof TipoReg_ActaIncomparecencia)) {
            return false;
        }
        TipoReg_ActaIncomparecencia other = (TipoReg_ActaIncomparecencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.TipoReg_ActaIncomparecencia[ id=" + id + " ]";
    }
    
}
