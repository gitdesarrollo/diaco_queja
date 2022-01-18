/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mps
 */
@Entity
@Table(name = "diaco_proveedor_aprovar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiacoProveedorAprovar.findAll", query = "SELECT d FROM DiacoProveedorAprovar d"),
    @NamedQuery(name = "DiacoProveedorAprovar.findById", query = "SELECT d FROM DiacoProveedorAprovar d WHERE d.id = :id"),
    @NamedQuery(name = "DiacoProveedorAprovar.findByNit", query = "SELECT d FROM DiacoProveedorAprovar d WHERE d.nit = :nit"),
    @NamedQuery(name = "DiacoProveedorAprovar.findByNombre", query = "SELECT d FROM DiacoProveedorAprovar d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DiacoProveedorAprovar.findByRazonSocial", query = "SELECT d FROM DiacoProveedorAprovar d WHERE d.razonSocial = :razonSocial"),
    @NamedQuery(name = "DiacoProveedorAprovar.findByDireccion", query = "SELECT d FROM DiacoProveedorAprovar d WHERE d.direccion = :direccion"),
    @NamedQuery(name = "DiacoProveedorAprovar.findByTelefono", query = "SELECT d FROM DiacoProveedorAprovar d WHERE d.telefono = :telefono"),
    @NamedQuery(name = "DiacoProveedorAprovar.findByCorreo", query = "SELECT d FROM DiacoProveedorAprovar d WHERE d.correo = :correo"),
    @NamedQuery(name = "DiacoProveedorAprovar.findByTelefonoNotif", query = "SELECT d FROM DiacoProveedorAprovar d WHERE d.telefonoNotif = :telefonoNotif"),
    @NamedQuery(name = "DiacoProveedorAprovar.findByCorreoNotif", query = "SELECT d FROM DiacoProveedorAprovar d WHERE d.correoNotif = :correoNotif"),
    @NamedQuery(name = "DiacoProveedorAprovar.findByNotaRechazo", query = "SELECT d FROM DiacoProveedorAprovar d WHERE d.notaRechazo = :notaRechazo"),
    @NamedQuery(name = "DiacoProveedorAprovar.findByEstado", query = "SELECT d FROM DiacoProveedorAprovar d WHERE d.estado = :estado"),
    @NamedQuery(name = "DiacoProveedorAprovar.findByFechaCreado", query = "SELECT d FROM DiacoProveedorAprovar d WHERE d.fechaCreado = :fechaCreado")})
public class DiacoProveedorAprovar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@NotNull
    @Column(name = "id")
    private Integer id;
    //@Size(max = 20)
    @Column(name = "nit")
    private String nit;
    ///@Size(max = 200)
    @Column(name = "nombre")
    private String nombre;
    //@Size(max = 200)
    @Column(name = "razon_social")
    private String razonSocial;
    //@Size(max = 200)
    @Column(name = "direccion")
    private String direccion;
    //@Size(max = 50)
    @Column(name = "telefono")
    private String telefono;
    //@Size(max = 50)
    @Column(name = "correo")
    private String correo;
    //@Size(max = 50)
    @Column(name = "telefono_notif")
    private String telefonoNotif;
    //@Size(max = 50)
    @Column(name = "correo_notif")
    private String correoNotif;
    //@Size(max = 100)
    @Column(name = "nota_rechazo")
    private String notaRechazo;
    //@Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha_creado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreado;
    @JoinColumn(name = "departamento", referencedColumnName = "codigo_departamento")
    @ManyToOne
    private Departamento departamento;
    @JoinColumn(name = "creado_por", referencedColumnName = "id_usuario")
    @ManyToOne
    private TipoUsuario creadoPor;
    @JoinColumn(name = "municipio", referencedColumnName = "codigo_municipio")
    @ManyToOne
    private Municipio municipio;
    @OneToMany(mappedBy = "idProveedorAprovar")
    private List<DiacoImagenesProveedor> diacoImagenesProveedorList;
    @Transient
    private Integer idDepartamento;
    @Transient
    private Integer idMunicipio;
    /**
     * Almacena el Codigo Unico de Identificacion: CUI.
     */
    @Column(name = "cui")
    private String cui;
    /**
     * Almacena el tipo del proveedor.
     */
    @Column(name = "tipo_proveedor")
    private Integer tipo_proveedor;

    public DiacoProveedorAprovar() {
    }

    public DiacoProveedorAprovar(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefonoNotif() {
        return telefonoNotif;
    }

    public void setTelefonoNotif(String telefonoNotif) {
        this.telefonoNotif = telefonoNotif;
    }

    public String getCorreoNotif() {
        return correoNotif;
    }

    public void setCorreoNotif(String correoNotif) {
        this.correoNotif = correoNotif;
    }

    public String getNotaRechazo() {
        return notaRechazo;
    }

    public void setNotaRechazo(String notaRechazo) {
        this.notaRechazo = notaRechazo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaCreado() {
        return fechaCreado;
    }

    public void setFechaCreado(Date fechaCreado) {
        this.fechaCreado = fechaCreado;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public TipoUsuario getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(TipoUsuario creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    @XmlTransient
    public List<DiacoImagenesProveedor> getDiacoImagenesProveedorList() {
        return diacoImagenesProveedorList;
    }

    public void setDiacoImagenesProveedorList(List<DiacoImagenesProveedor> diacoImagenesProveedorList) {
        this.diacoImagenesProveedorList = diacoImagenesProveedorList;
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

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public Integer getTipo_proveedor() {
        return tipo_proveedor;
    }

    public void setTipo_proveedor(Integer tipo_proveedor) {
        this.tipo_proveedor = tipo_proveedor;
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
        if (!(object instanceof DiacoProveedorAprovar)) {
            return false;
        }
        DiacoProveedorAprovar other = (DiacoProveedorAprovar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.gob.mineco.diaco.model.DiacoProveedorAprovar[ id=" + id + ", cui=" + cui + " ]";
    }

}
