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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mps
 */
@Entity
@Table(name = "diaco_establecimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiacoEstablecimiento.findAll", query = "SELECT d FROM DiacoEstablecimiento d")
    , @NamedQuery(name = "DiacoEstablecimiento.findByIdEstablecimiento", query = "SELECT d FROM DiacoEstablecimiento d WHERE d.idEstablecimiento = :idEstablecimiento")
    , @NamedQuery(name = "DiacoEstablecimiento.findByNit", query = "SELECT d FROM DiacoEstablecimiento d WHERE d.nit = :nit")
    , @NamedQuery(name = "DiacoEstablecimiento.findByNombreComercial", query = "SELECT d FROM DiacoEstablecimiento d WHERE d.nombreComercial = :nombreComercial")
    , @NamedQuery(name = "DiacoEstablecimiento.findByDireccion", query = "SELECT d FROM DiacoEstablecimiento d WHERE d.direccion = :direccion")
    , @NamedQuery(name = "DiacoEstablecimiento.findByHabilitado", query = "SELECT d FROM DiacoEstablecimiento d WHERE d.habilitado = :habilitado")
    , @NamedQuery(name = "DiacoEstablecimiento.findByUsuarioAdiciono", query = "SELECT d FROM DiacoEstablecimiento d WHERE d.usuarioAdiciono = :usuarioAdiciono")
    , @NamedQuery(name = "DiacoEstablecimiento.findByFechaAdicion", query = "SELECT d FROM DiacoEstablecimiento d WHERE d.fechaAdicion = :fechaAdicion")
    , @NamedQuery(name = "DiacoEstablecimiento.findByUsuarioModifico", query = "SELECT d FROM DiacoEstablecimiento d WHERE d.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "DiacoEstablecimiento.findByFechaModificacion", query = "SELECT d FROM DiacoEstablecimiento d WHERE d.fechaModificacion = :fechaModificacion")})
public class DiacoEstablecimiento implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Id
    //@Basic(optional = false)
    //@NotNull
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_establecimiento")
    private Integer idEstablecimiento;
    //@Size(max = 20)
    @Column(name = "nit")
    private String nit;
    //@Size(max = 100)
    @Column(name = "nombre_comercial")
    private String nombreComercial;
    //@Size(max = 200)
    @Column(name = "direccion")
    private String direccion;
    //
    @Column(name = "zona")
    private String zona;
    //@Size(max = 1)
    @Column(name = "habilitado")
    private String habilitado;
    //@Size(max = 20)
    @Column(name = "usuario_adiciono")
    private String usuarioAdiciono;
    @Column(name = "fecha_adicion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAdicion;
    //@Size(max = 20)
    @Column(name = "usuario_modifico")
    private String usuarioModifico;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    //@Size(max = 20)
    @Column(name = "documento_identificacion")
    private String documentoIdentificacion;
    @JoinColumn(name = "codigo_departamento", referencedColumnName = "codigo_departamento")
    @ManyToOne
    private Departamento codigoDepartamento;
    @JoinColumn(name = "codigo_municipio", referencedColumnName = "codigo_municipio")
    @ManyToOne
    private Municipio codigoMunicipio;
    @Transient
    private Integer idDepartamento;
    @Transient
    private Integer idMunicipio;

    public DiacoEstablecimiento() {
    }

    public DiacoEstablecimiento(Integer idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

    public Integer getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(Integer idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    public String getUsuarioAdiciono() {
        return usuarioAdiciono;
    }

    public void setUsuarioAdiciono(String usuarioAdiciono) {
        this.usuarioAdiciono = usuarioAdiciono;
    }

    public Date getFechaAdicion() {
        return fechaAdicion;
    }

    public void setFechaAdicion(Date fechaAdicion) {
        this.fechaAdicion = fechaAdicion;
    }

    public String getUsuarioModifico() {
        return usuarioModifico;
    }

    public void setUsuarioModifico(String usuarioModifico) {
        this.usuarioModifico = usuarioModifico;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Departamento getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(Departamento codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public Municipio getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Municipio codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstablecimiento != null ? idEstablecimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiacoEstablecimiento)) {
            return false;
        }
        DiacoEstablecimiento other = (DiacoEstablecimiento) object;
        if ((this.idEstablecimiento == null && other.idEstablecimiento != null) || (this.idEstablecimiento != null && !this.idEstablecimiento.equals(other.idEstablecimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.DiacoEstablecimiento[ idEstablecimiento=" + idEstablecimiento + " ]";
    }

    public String getDocumentoIdentificacion() {
        return documentoIdentificacion;
    }

    public void setDocumentoIdentificacion(String documentoIdentificacion) {
        this.documentoIdentificacion = documentoIdentificacion;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    
}
