/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "diaco_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoUsuario.findAll", query = "SELECT d FROM TipoUsuario d")
    , @NamedQuery(name = "TipoUsuario.findByIdUsuario", query = "SELECT d FROM TipoUsuario d WHERE d.idUsuario = :idUsuario")
    , @NamedQuery(name = "TipoUsuario.findByNombre", query = "SELECT d FROM TipoUsuario d WHERE d.nombre = :nombre")
    , @NamedQuery(name = "TipoUsuario.findByLogin", query = "SELECT d FROM TipoUsuario d WHERE d.login = :login")
    , @NamedQuery(name = "TipoUsuario.findByHabilitado", query = "SELECT d FROM TipoUsuario d WHERE d.habilitado = :habilitado")
    , @NamedQuery(name = "TipoUsuario.findByFechaAdicion", query = "SELECT d FROM TipoUsuario d WHERE d.fechaAdicion = :fechaAdicion")
    , @NamedQuery(name = "TipoUsuario.findByDpi", query = "SELECT d FROM TipoUsuario d WHERE d.dpi = :dpi")
    , @NamedQuery(name = "TipoUsuario.findByEmail", query = "SELECT d FROM TipoUsuario d WHERE d.email = :email")
    , @NamedQuery(name = "TipoUsuario.findByTelefono", query = "SELECT d FROM TipoUsuario d WHERE d.telefono = :telefono")})
public class TipoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 20)
    @Column(name = "login")
    private String login;
    @Size(max = 200)
    @Column(name = "clave")
    private String clave; 
    @Size(max = 1)
    @Column(name = "habilitado")
    private String habilitado;
    @Column(name = "fecha_adicion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAdicion;
    @Size(max = 20)
    @Column(name = "dpi")
    private String dpi;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 30)
    @Column(name = "email")
    private String email;
    @Size(max = 30)
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "id_proveedor")
    private Integer id_proveedor;
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "creadoPor")
    private List<RegResolucionVisitaCampo> regResolucionVisitaCampoList;
    @OneToMany(mappedBy = "creadoPor")
    private List<RegCedulaCitacionPro> regCedulaCitacionProList;
    @OneToMany(mappedBy = "creadoPor")
    private List<RegFichaQueja> regFichaQuejaList;
    @OneToMany(mappedBy = "creadoPor")
    private List<RegCedulaCitacionCon> regCedulaCitacionConList;
    @OneToMany(mappedBy = "creadoPor")
    private List<RegFormularioMovExp> regFormularioMovExpList;*/
//    @OneToMany(mappedBy = "usuarioModifico")
//    private List<TipoTelefono> diacoTelefonoList;
    /*@OneToMany(mappedBy = "hechaPor")
    private List<TipoVisitaCampo> diacoVisitaCampoList;
    @OneToMany(mappedBy = "creadoPor")
    private List<RegCedulaNotificacionCon> regCedulaNotificacionConList;
    @JoinColumn(name = "id_sede_diaco", referencedColumnName = "id_diaco_sede")
    @ManyToOne
    private TipoSede idSedeTipo;*/
    //@JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diacoUsuario")
    //@OneToMany
    private List<TipoUsuarioPerfil> diacoUsuarioPerfilList;
    /*@OneToMany(mappedBy = "creadoPor")
    private List<RegCedulaNotificacionPro> regCedulaNotificacionProList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creadoPor")
    private List<RegComunicacionPermanente> regComunicacionPermanenteList;
    @OneToMany(mappedBy = "usuarioActual")
    private List<TipoCambioAreaExp> diacoCambioAreaExpList;
    @OneToMany(mappedBy = "usuarioPrevio")
    private List<TipoCambioAreaExp> diacoCambioAreaExpList1;*/
//    @OneToMany(mappedBy = "usuario")
//    private List<TipoBitacora> diacoBitacoraList;
//    @OneToMany(mappedBy = "usuarioAsignado")
//    private List<TipoQueja> diacoQuejaList;
//    @OneToMany(mappedBy = "usuarioAdiciono")
//    private List<TipoDepartamentoInterno> diacoDepartamentoInternoList;
//    @OneToMany(mappedBy = "creadoPor")
//    private List<RegFormularioQueja> regFormularioQuejaList;

    public Integer getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Integer id_proveedor) {
        this.id_proveedor = id_proveedor;
    }
    
    public TipoUsuario() {
    }

    public TipoUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    public Date getFechaAdicion() {
        return fechaAdicion;
    }

    public void setFechaAdicion(Date fechaAdicion) {
        this.fechaAdicion = fechaAdicion;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /*@XmlTransient
    public List<RegResolucionVisitaCampo> getRegResolucionVisitaCampoList() {
        return regResolucionVisitaCampoList;
    }

    public void setRegResolucionVisitaCampoList(List<RegResolucionVisitaCampo> regResolucionVisitaCampoList) {
        this.regResolucionVisitaCampoList = regResolucionVisitaCampoList;
    }

    @XmlTransient
    public List<RegCedulaCitacionPro> getRegCedulaCitacionProList() {
        return regCedulaCitacionProList;
    }

    public void setRegCedulaCitacionProList(List<RegCedulaCitacionPro> regCedulaCitacionProList) {
        this.regCedulaCitacionProList = regCedulaCitacionProList;
    }

    @XmlTransient
    public List<RegFichaQueja> getRegFichaQuejaList() {
        return regFichaQuejaList;
    }

    public void setRegFichaQuejaList(List<RegFichaQueja> regFichaQuejaList) {
        this.regFichaQuejaList = regFichaQuejaList;
    }

    @XmlTransient
    public List<RegCedulaCitacionCon> getRegCedulaCitacionConList() {
        return regCedulaCitacionConList;
    }

    public void setRegCedulaCitacionConList(List<RegCedulaCitacionCon> regCedulaCitacionConList) {
        this.regCedulaCitacionConList = regCedulaCitacionConList;
    }

    @XmlTransient
    public List<RegFormularioMovExp> getRegFormularioMovExpList() {
        return regFormularioMovExpList;
    }

    public void setRegFormularioMovExpList(List<RegFormularioMovExp> regFormularioMovExpList) {
        this.regFormularioMovExpList = regFormularioMovExpList;
    }

//    @XmlTransient
//    public List<TipoTelefono> getTipoTelefonoList() {
//        return diacoTelefonoList;
//    }
//
//    public void setTipoTelefonoList(List<TipoTelefono> diacoTelefonoList) {
//        this.diacoTelefonoList = diacoTelefonoList;
//    }

    @XmlTransient
    public List<TipoVisitaCampo> getTipoVisitaCampoList() {
        return diacoVisitaCampoList;
    }

    public void setTipoVisitaCampoList(List<TipoVisitaCampo> diacoVisitaCampoList) {
        this.diacoVisitaCampoList = diacoVisitaCampoList;
    }

    @XmlTransient
    public List<RegCedulaNotificacionCon> getRegCedulaNotificacionConList() {
        return regCedulaNotificacionConList;
    }

    public void setRegCedulaNotificacionConList(List<RegCedulaNotificacionCon> regCedulaNotificacionConList) {
        this.regCedulaNotificacionConList = regCedulaNotificacionConList;
    }

    public TipoSede getIdSedeTipo() {
        return idSedeTipo;
    }

    public void setIdSedeTipo(TipoSede idSedeTipo) {
        this.idSedeTipo = idSedeTipo;
    }*/

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    @XmlTransient
    public List<TipoUsuarioPerfil> getTipoUsuarioPerfilList() {
        return diacoUsuarioPerfilList;
    }

    public void setTipoUsuarioPerfilList(List<TipoUsuarioPerfil> diacoUsuarioPerfilList) {
        this.diacoUsuarioPerfilList = diacoUsuarioPerfilList;
    }

    /*@XmlTransient
    public List<RegCedulaNotificacionPro> getRegCedulaNotificacionProList() {
        return regCedulaNotificacionProList;
    }

    public void setRegCedulaNotificacionProList(List<RegCedulaNotificacionPro> regCedulaNotificacionProList) {
        this.regCedulaNotificacionProList = regCedulaNotificacionProList;
    }

    @XmlTransient
    public List<RegComunicacionPermanente> getRegComunicacionPermanenteList() {
        return regComunicacionPermanenteList;
    }

    public void setRegComunicacionPermanenteList(List<RegComunicacionPermanente> regComunicacionPermanenteList) {
        this.regComunicacionPermanenteList = regComunicacionPermanenteList;
    }

    @XmlTransient
    public List<TipoCambioAreaExp> getTipoCambioAreaExpList() {
        return diacoCambioAreaExpList;
    }

    public void setTipoCambioAreaExpList(List<TipoCambioAreaExp> diacoCambioAreaExpList) {
        this.diacoCambioAreaExpList = diacoCambioAreaExpList;
    }

    @XmlTransient
    public List<TipoCambioAreaExp> getTipoCambioAreaExpList1() {
        return diacoCambioAreaExpList1;
    }

    public void setTipoCambioAreaExpList1(List<TipoCambioAreaExp> diacoCambioAreaExpList1) {
        this.diacoCambioAreaExpList1 = diacoCambioAreaExpList1;
    }*/

//    @XmlTransient
//    public List<TipoBitacora> getTipoBitacoraList() {
//        return diacoBitacoraList;
//    }
//
//    public void setTipoBitacoraList(List<TipoBitacora> diacoBitacoraList) {
//        this.diacoBitacoraList = diacoBitacoraList;
//    }

//    @XmlTransient
//    public List<TipoQueja> getTipoQuejaList() {
//        return diacoQuejaList;
//    }
//
//    public void setTipoQuejaList(List<TipoQueja> diacoQuejaList) {
//        this.diacoQuejaList = diacoQuejaList;
//    }

//    @XmlTransient
//    public List<TipoDepartamentoInterno> getTipoDepartamentoInternoList() {
//        return diacoDepartamentoInternoList;
//    }
//
//    public void setTipoDepartamentoInternoList(List<TipoDepartamentoInterno> diacoDepartamentoInternoList) {
//        this.diacoDepartamentoInternoList = diacoDepartamentoInternoList;
//    }

//    @XmlTransient
//    public List<RegFormularioQueja> getRegFormularioQuejaList() {
//        return regFormularioQuejaList;
//    }
//
//    public void setRegFormularioQuejaList(List<RegFormularioQueja> regFormularioQuejaList) {
//        this.regFormularioQuejaList = regFormularioQuejaList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoUsuario)) {
            return false;
        }
        TipoUsuario other = (TipoUsuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        JsonObject queja = new JsonObject();
        JsonArray arr_roles = new JsonArray();
        queja.addProperty("login",this.login);
        if(diacoUsuarioPerfilList!=null){
            for (TipoUsuarioPerfil loc : this.diacoUsuarioPerfilList) {
                JsonObject rolitem = new JsonObject();                
                rolitem.addProperty("id_flujo",loc.getTipoPerfilesPuesto().getTipoflujo().getId());
                rolitem.addProperty("nombre_flujo",loc.getTipoPerfilesPuesto().getTipoflujo().getFlujo());
                rolitem.addProperty("perfil",loc.getTipoPerfilesPuesto().getPerfil());
                arr_roles.add(rolitem);
            }
            queja.add("roles",arr_roles);
        }
        return queja.toString()+",";
        //return "gt.gob.mineco.diaco.model.TipoUsuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
