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
@Table(name = "reg_acta_juridico")
@NamedQueries({
    @NamedQuery(name = "TipoReg_ActaJuridico.findAll", query = "SELECT t FROM TipoReg_ActaJuridico t")
    , @NamedQuery(name = "TipoReg_ActaJuridico.findById", query = "SELECT t FROM TipoReg_ActaJuridico t WHERE t.id = :id")
    , @NamedQuery(name = "TipoReg_ActaJuridico.findByIdCorrelativoActa", query = "SELECT t FROM TipoReg_ActaJuridico t WHERE t.idCorrelativoActa = :idCorrelativoActa")
    , @NamedQuery(name = "TipoReg_ActaJuridico.findByFechaActa", query = "SELECT t FROM TipoReg_ActaJuridico t WHERE t.fechaActa = :fechaActa")
    , @NamedQuery(name = "TipoReg_ActaJuridico.findByFechaCreacion", query = "SELECT t FROM TipoReg_ActaJuridico t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TipoReg_ActaJuridico.findByIdQueja", query = "SELECT t FROM TipoReg_ActaJuridico t WHERE t.idQueja = :idQueja")
    , @NamedQuery(name = "TipoReg_ActaJuridico.findByIdRegistro", query = "SELECT t FROM TipoReg_ActaJuridico t WHERE t.idRegistro = :idRegistro")
    , @NamedQuery(name = "TipoReg_ActaJuridico.findByCreadoPor", query = "SELECT t FROM TipoReg_ActaJuridico t WHERE t.creadoPor = :creadoPor")})
public class TipoReg_ActaJuridico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "id_correlativo_acta")
    private String idCorrelativoActa;
    @Column(name = "fecha_acta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActa;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
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

    public TipoReg_ActaJuridico() {
    }

    public TipoReg_ActaJuridico(Integer id) {
        this.id = id;
    }

    public TipoReg_ActaJuridico(Integer id, String idCorrelativoActa, int idQueja, int idRegistro, int creadoPor) {
        this.id = id;
        this.idCorrelativoActa = idCorrelativoActa;
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

    public String getIdCorrelativoActa() {
        return idCorrelativoActa;
    }

    public void setIdCorrelativoActa(String idCorrelativoActa) {
        this.idCorrelativoActa = idCorrelativoActa;
    }

    public Date getFechaActa() {
        return fechaActa;
    }

    public void setFechaActa(Date fechaActa) {
        this.fechaActa = fechaActa;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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
        if (!(object instanceof TipoReg_ActaJuridico)) {
            return false;
        }
        TipoReg_ActaJuridico other = (TipoReg_ActaJuridico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    
    
   @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id",this.id);
            temp.addProperty("idCorrelativo",this.idCorrelativoActa);
            temp.addProperty("idQueja",this.idQueja);
  
            return temp.toString()+",";
        }   
}
