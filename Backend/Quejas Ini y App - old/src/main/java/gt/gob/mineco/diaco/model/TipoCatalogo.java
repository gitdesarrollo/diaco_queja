/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mps
 */
@Entity
@Table(name = "diaco_catalogo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCatalogo.findAll", query = "SELECT t FROM TipoCatalogo t where t.estado = 'A' and t.nombreTablaCatalogo = :tabla"),
    @NamedQuery(name = "TipoCatalogo.findByIdCatalogo", query = "SELECT d FROM TipoCatalogo d WHERE d.idCatalogo = :idCatalogo"),
    @NamedQuery(name = "TipoCatalogo.findByDescripcionCatalogo", query = "SELECT d FROM TipoCatalogo d WHERE d.descripcionCatalogo = :descripcionCatalogo"),
    @NamedQuery(name = "TipoCatalogo.findByNombreTablaCatalogo", query = "SELECT d FROM TipoCatalogo d WHERE d.nombreTablaCatalogo = :nombreTablaCatalogo"),
    @NamedQuery(name = "TipoCatalogo.findByFechaAdicion", query = "SELECT d FROM TipoCatalogo d WHERE d.fechaAdicion = :fechaAdicion"),
    @NamedQuery(name = "TipoCatalogo.findByUsuarioAdicion", query = "SELECT d FROM TipoCatalogo d WHERE d.usuarioAdicion = :usuarioAdicion"),
    @NamedQuery(name = "TipoCatalogo.findByEstado", query = "SELECT d FROM TipoCatalogo d WHERE d.estado = :estado")})
public class TipoCatalogo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@Basic(optional = false)
    //@NotNull
    @Column(name = "id_catalogo")
    private Integer idCatalogo;
    //@Size(max = 50)
    @Column(name = "descripcion_catalogo")
    private String descripcionCatalogo;
    //@Size(max = 30)
    @Column(name = "nombre_tabla_catalogo")
    private String nombreTablaCatalogo;
    @Column(name = "fecha_adicion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAdicion;
    //@Size(max = 20)
    @Column(name = "usuario_adicion")
    private String usuarioAdicion;
    //@Size(max = 1)
    @Column(name = "estado")
    private String estado;
//    @OneToMany(mappedBy = "tipoTelefono")
//    private List<DiacoTelefono_1> diacoTelefonoList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diacoCatalogo")
//    private List<DiacoUsuarioCatalogo_1> diacoUsuarioCatalogoList;
//    @OneToMany(mappedBy = "via")
//    private List<DiacoComunicacionConsumidor_1> diacoComunicacionConsumidorList;
//    @OneToMany(mappedBy = "resolucion")
//    private List<DiacoVerifConciliacionPrevia_1> diacoVerifConciliacionPreviaList;
//    @OneToMany(mappedBy = "respuestaDiaco")
//    private List<DiacoVerifConciliacionPrevia_1> diacoVerifConciliacionPreviaList1;

    public TipoCatalogo() {
    }

    public TipoCatalogo(Integer idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public Integer getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(Integer idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public String getDescripcionCatalogo() {
        return descripcionCatalogo;
    }

    public void setDescripcionCatalogo(String descripcionCatalogo) {
        this.descripcionCatalogo = descripcionCatalogo;
    }

    public String getNombreTablaCatalogo() {
        return nombreTablaCatalogo;
    }

    public void setNombreTablaCatalogo(String nombreTablaCatalogo) {
        this.nombreTablaCatalogo = nombreTablaCatalogo;
    }

    public Date getFechaAdicion() {
        return fechaAdicion;
    }

    public void setFechaAdicion(Date fechaAdicion) {
        this.fechaAdicion = fechaAdicion;
    }

    public String getUsuarioAdicion() {
        return usuarioAdicion;
    }

    public void setUsuarioAdicion(String usuarioAdicion) {
        this.usuarioAdicion = usuarioAdicion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

//    @XmlTransient
//    public List<DiacoTelefono_1> getDiacoTelefonoList() {
//        return diacoTelefonoList;
//    }
//
//    public void setDiacoTelefonoList(List<DiacoTelefono_1> diacoTelefonoList) {
//        this.diacoTelefonoList = diacoTelefonoList;
//    }
//
//    @XmlTransient
//    public List<DiacoUsuarioCatalogo_1> getDiacoUsuarioCatalogoList() {
//        return diacoUsuarioCatalogoList;
//    }
//
//    public void setDiacoUsuarioCatalogoList(List<DiacoUsuarioCatalogo_1> diacoUsuarioCatalogoList) {
//        this.diacoUsuarioCatalogoList = diacoUsuarioCatalogoList;
//    }
//
//    @XmlTransient
//    public List<DiacoComunicacionConsumidor_1> getDiacoComunicacionConsumidorList() {
//        return diacoComunicacionConsumidorList;
//    }
//
//    public void setDiacoComunicacionConsumidorList(List<DiacoComunicacionConsumidor_1> diacoComunicacionConsumidorList) {
//        this.diacoComunicacionConsumidorList = diacoComunicacionConsumidorList;
//    }
//
//    @XmlTransient
//    public List<DiacoVerifConciliacionPrevia_1> getDiacoVerifConciliacionPreviaList() {
//        return diacoVerifConciliacionPreviaList;
//    }
//
//    public void setDiacoVerifConciliacionPreviaList(List<DiacoVerifConciliacionPrevia_1> diacoVerifConciliacionPreviaList) {
//        this.diacoVerifConciliacionPreviaList = diacoVerifConciliacionPreviaList;
//    }
//
//    @XmlTransient
//    public List<DiacoVerifConciliacionPrevia_1> getDiacoVerifConciliacionPreviaList1() {
//        return diacoVerifConciliacionPreviaList1;
//    }
//
//    public void setDiacoVerifConciliacionPreviaList1(List<DiacoVerifConciliacionPrevia_1> diacoVerifConciliacionPreviaList1) {
//        this.diacoVerifConciliacionPreviaList1 = diacoVerifConciliacionPreviaList1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatalogo != null ? idCatalogo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCatalogo)) {
            return false;
        }
        TipoCatalogo other = (TipoCatalogo) object;
        if ((this.idCatalogo == null && other.idCatalogo != null) || (this.idCatalogo != null && !this.idCatalogo.equals(other.idCatalogo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.TipoCatalogo[ idCatalogo=" + idCatalogo + " ]";
    }
    
}
