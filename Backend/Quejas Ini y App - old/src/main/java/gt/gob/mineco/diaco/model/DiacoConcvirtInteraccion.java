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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author mps
 */
@Entity
@Table(name = "diaco_concvirt_interaccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiacoConcvirtInteraccion.findAll", query = "SELECT d FROM DiacoConcvirtInteraccion d")
    , @NamedQuery(name = "DiacoConcvirtInteraccion.findById", query = "SELECT d FROM DiacoConcvirtInteraccion d WHERE d.id = :id")
    , @NamedQuery(name = "DiacoConcvirtInteraccion.findByFecha", query = "SELECT d FROM DiacoConcvirtInteraccion d WHERE d.fecha = :fecha")
    , @NamedQuery(name = "DiacoConcvirtInteraccion.findByPregunta", query = "SELECT d FROM DiacoConcvirtInteraccion d WHERE d.pregunta = :pregunta")
    , @NamedQuery(name = "DiacoConcvirtInteraccion.findByRespuestaCon", query = "SELECT d FROM DiacoConcvirtInteraccion d WHERE d.respuestaCon = :respuestaCon")
    , @NamedQuery(name = "DiacoConcvirtInteraccion.findByEstado", query = "SELECT d FROM DiacoConcvirtInteraccion d WHERE d.estado = :estado")
    , @NamedQuery(name = "DiacoConcvirtInteraccion.findByCambiarArchivos", query = "SELECT d FROM DiacoConcvirtInteraccion d WHERE d.cambiarArchivos = :cambiarArchivos")
    , @NamedQuery(name = "DiacoConcvirtInteraccion.findByCambiarDatos", query = "SELECT d FROM DiacoConcvirtInteraccion d WHERE d.cambiarDatos = :cambiarDatos")})
public class DiacoConcvirtInteraccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 200)
    @Column(name = "pregunta")
    private String pregunta;
    @Size(max = 200)
    @Column(name = "respuesta_con")
    private String respuestaCon;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @Column(name = "cambiar_archivos")
    private Boolean cambiarArchivos;
    @Column(name = "cambiar_datos")
    private Boolean cambiarDatos;
    /*
    @JoinColumn(name = "id_queja_paso", referencedColumnName = "id_queja_paso")
    @ManyToOne
    private DiacoPasoQueja idQuejaPaso;
*/
    @JoinColumn(name = "id_queja", referencedColumnName = "id_queja")
    @ManyToOne
    private DiacoQueja idQueja;

    public DiacoConcvirtInteraccion() {
    }

    public DiacoConcvirtInteraccion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuestaCon() {
        return respuestaCon;
    }

    public void setRespuestaCon(String respuestaCon) {
        this.respuestaCon = respuestaCon;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Boolean getCambiarArchivos() {
        return cambiarArchivos;
    }

    public void setCambiarArchivos(Boolean cambiarArchivos) {
        this.cambiarArchivos = cambiarArchivos;
    }

    public Boolean getCambiarDatos() {
        return cambiarDatos;
    }

    public void setCambiarDatos(Boolean cambiarDatos) {
        this.cambiarDatos = cambiarDatos;
    }

//    public DiacoPasoQueja getIdQuejaPaso() {
//        return idQuejaPaso;
//    }
//
//    public void setIdQuejaPaso(DiacoPasoQueja idQuejaPaso) {
//        this.idQuejaPaso = idQuejaPaso;
//    }

    public DiacoQueja getIdQueja() {
        return idQueja;
    }

    public void setIdQueja(DiacoQueja idQueja) {
        this.idQueja = idQueja;
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
        if (!(object instanceof DiacoConcvirtInteraccion)) {
            return false;
        }
        DiacoConcvirtInteraccion other = (DiacoConcvirtInteraccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.DiacoConcvirtInteraccion[ id=" + id + " ]";
    }
    
}
