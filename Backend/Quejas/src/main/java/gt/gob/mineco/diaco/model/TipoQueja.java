package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import com.google.gson.JsonObject;
import gt.gob.mineco.diaco.model.TipoTelefono;
import gt.gob.mineco.diaco.model.TipoEmail;
import gt.gob.mineco.diaco.model.TipoConsumidor;
import gt.gob.mineco.diaco.model.TipoEstadoQueja;
import gt.gob.mineco.diaco.model.TipoSede;
import gt.gob.mineco.diaco.model.TipoDepartamento;
import gt.gob.mineco.diaco.model.TipoMunicipio;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.JsonArray;
import gt.gob.mineco.diaco.util.CheckNull;
/**
 * The persistent class for the diaco_queja database table.
 * 
 */
@Entity
@Table(name="diaco_queja")
@NamedQueries({
    @NamedQuery(
        name = "TipoQueja.findAll",
        query = "SELECT t FROM TipoQueja t ORDER BY t.fecha_queja desc"),
    @NamedQuery(
        name = "TipoQueja.findAllbyConsumidor",
        query = "SELECT t FROM TipoQueja t where t.id_consumidor = :id_consumidor ORDER BY t.fecha_queja desc"),
    @NamedQuery(
        name = "TipoQueja.findAllbyProveedor",
        query = "SELECT t FROM TipoQueja t where t.id_proveedor = :id_proveedor ORDER BY t.fecha_queja desc"),
    @NamedQuery(
        name = "TipoQueja.findAllPendienteRuteo",
        query = "SELECT t FROM TipoQueja t where t.id_estado_queja = 140"),
    @NamedQuery(
        name = "TipoQueja.findbyCodigo",
        query = "SELECT t FROM TipoQueja t where CONCAT(UPPER(t.no_queja), '-',UPPER(t.anio)) = :codigoqueja"),
    @NamedQuery(
        name = "TipoQueja.findAllExpiradoAtConCorreo",
        query = "SELECT t FROM TipoQueja t where t.id_estado_queja = 150 and t.expirado_mail IS NULL"),
    @NamedQuery(
        name = "TipoQueja.findAllExpiradoJurCorreo",
        query = "SELECT t FROM TipoQueja t where t.id_estado_queja = 250 and t.expirado_mail IS NULL"),
    @NamedQuery(
        name = "TipoQueja.findAllExpiradoVyVCorreo",
        query = "SELECT t FROM TipoQueja t where t.id_estado_queja = 350 and t.expirado_mail IS NULL"),
    @NamedQuery(
        name = "TipoQueja.findAllExpiradoSPCorreo",
        query = "SELECT t FROM TipoQueja t where t.id_estado_queja = 650 and t.expirado_mail IS NULL"),
    @NamedQuery(
        name = "TipoQueja.findAllAlertaExpiradoAtConCorreo",
        query = "SELECT t FROM TipoQueja t where t.flag_alerta = 6"),
    @NamedQuery(
        name = "TipoQueja.findAllAlertaExpiradoJurCorreo",
        query = "SELECT t FROM TipoQueja t where t.flag_alerta = 7"),
    @NamedQuery(
        name = "TipoQueja.findAllAlertaExpiradoVyVCorreo",
        query = "SELECT t FROM TipoQueja t where t.flag_alerta = 8"),
    @NamedQuery(
        name = "TipoQueja.findAllAlertaExpiradoSPCorreo",
        query = "SELECT t FROM TipoQueja t where t.flag_alerta = 9")
})

public class TipoQueja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @Column(name="id_queja")
	private Integer id_queja;
        @Column(name="id_consumidor")
	private Integer id_consumidor;
        @Column(name="id_proveedor")
	private Integer id_proveedor;
        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
        @JoinColumn(name = "id_consumidor", referencedColumnName = "id_consumidor")
        private List<TipoTelefono> tipotelefono = new ArrayList<>();
        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
        @JoinColumn(name = "id_consumidor", referencedColumnName = "id_consumidor")
        private List<TipoEmail> tipoemail = new ArrayList<>();
        @OneToOne
        @JoinColumn(name = "id_consumidor", referencedColumnName = "id_consumidor",updatable=false, insertable=false)
        private TipoConsumidor tipoconsumidor;
        @OneToOne
        @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor",updatable=false, insertable=false)
        private TipoProveedor tipoproveedor;
        @Column(name="id_sub__actividad_economica")
	private Integer id_sub__actividad_economica;
        @Column(name="factura_numero")
	private String factura_numero;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_factura")
        private Date fecha_factura;
        @Column(name="detalle_queja")
	private String detalle_queja;
        @Column(name="solicita_que")
	private String solicita_que;
        @Column(name="procede_queja")
	private String procede_queja;
        @Column(name="razon_no_procede")
	private String razon_no_procede;
        @Column(name="datos_verificados")
	private String datos_verificados;
        @Column(name="usuario_asignado")
	private Integer usuario_asignado;
        @Column(name="motivo")
	private String motivo;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_queja")
        private Date fecha_queja; 
        @Column(name="id_estado_queja")
        private Integer id_estado_queja;
        @Column(name="anio")
        private Integer anio;
        @Column(name="no_queja")
        private Integer no_queja;
        @Column(name="id_departamento")
        private Integer id_departamento;
        @Column(name="id_municipio")
        private Integer id_municipio;
        @Column(name="id_diaco_sede")
        private Integer id_diaco_sede;
        @Column(name="documentado")
	private String documentado;
        @Column(name="expirado_mail")
        private Integer expirado_mail;
        @Column(name="flag_alerta")
        private Integer flag_alerta;
        @JoinColumn(name = "id_queja", referencedColumnName = "id_queja",updatable=false, insertable=false)
        @OneToOne
        private TipoFlujoGuia tipoflujoguia;  
        @JoinColumn(name = "id_estado_queja", referencedColumnName = "id_estado_queja",updatable=false, insertable=false)        
        @OneToOne
        private TipoEstadoQueja tipoEstadoQueja;
        @JoinColumn(name = "id_diaco_sede", referencedColumnName = "id_diaco_sede",updatable=false, insertable=false)        
        @OneToOne
        private TipoSede tipoSede;
        @OneToOne
        @JoinColumn(name = "id_departamento", referencedColumnName = "codigo_departamento",updatable=false, insertable=false)
        private TipoDepartamento tipoDepartamento;
        @OneToOne
        @JoinColumn(name = "id_municipio", referencedColumnName = "codigo_municipio" ,updatable=false, insertable=false)
        private TipoMunicipio tipoMunicipio;
        @OneToOne
        @JoinColumn(name = "usuario_asignado", referencedColumnName = "id_usuario",updatable=false, insertable=false)
        private TipoUsuario_Simple tipoUsuario;
        @Column(name = "fuente")
        private String fuente;
        @Column(name = "direccion_proveedor")
        private String direccion_proveedor;
        @Column(name="zona_proveedor")
        private Integer zona_proveedor;
        
        @Column(name="id_departamento_proveedor")
        private Integer id_departamento_proveedor;
        @Column(name="id_municipio_proveedor")
        private Integer id_municipio_proveedor;
        
        @Column(name="is_est_resolver")
        private Integer is_est_resolver;
        
        @OneToOne
        @JoinColumn(name = "id_departamento_proveedor", referencedColumnName = "codigo_departamento",updatable=false, insertable=false)
        private TipoDepartamento tipoDepartamentoProv;
        @OneToOne
        @JoinColumn(name = "id_municipio_proveedor", referencedColumnName = "codigo_municipio" ,updatable=false, insertable=false)
        private TipoMunicipio tipoMunicipioProv;

        
	public TipoQueja() {
	}

    public Integer getFlag_alerta() {
        return flag_alerta;
    }

    public void setFlag_alerta(Integer flag_alerta) {
        this.flag_alerta = flag_alerta;
    }

    public Integer getExpirado_mail() {
        return expirado_mail;
    }

    public void setExpirado_mail(Integer expirado_mail) {
        this.expirado_mail = expirado_mail;
    }

    public TipoUsuario_Simple getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario_Simple tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getDocumentado() {
        return documentado;
    }

    public void setDocumentado(String documentado) {
        this.documentado = documentado;
    }

        public String getQuejaNumero(){
            String retvalue="";
            if(this.anio != null && this.no_queja != null)
                retvalue=this.no_queja.toString()+"-"+this.anio.toString();
            return retvalue;
        }

    public Integer getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(Integer id_departamento) {
        this.id_departamento = id_departamento;
    }

    public Integer getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(Integer id_municipio) {
        this.id_municipio = id_municipio;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getNo_queja() {
        return no_queja;
    }

    public void setNo_queja(Integer no_queja) {
        this.no_queja = no_queja;
    }

    public TipoDepartamento getTipoDepartamento() {
        return tipoDepartamento;
    }

    public void setTipoDepartamento(TipoDepartamento tipoDepartamento) {
        this.tipoDepartamento = tipoDepartamento;
    }

    public TipoMunicipio getTipoMunicipio() {
        return tipoMunicipio;
    }

    public void setTipoMunicipio(TipoMunicipio tipoMunicipio) {
        this.tipoMunicipio = tipoMunicipio;
    }

        
        
    public TipoSede getTipoSede() {
        return tipoSede;
    }

    public void setTipoSede(TipoSede tipoSede) {
        this.tipoSede = tipoSede;
    }

        public Integer getId_queja() {
            return id_queja;
        }

        public void setId_queja(Integer id_queja) {
            this.id_queja = id_queja;
        }

        public Date getFecha_queja() {
            return fecha_queja;
        }

        public void setFecha_queja(Date fecha_queja) {
            this.fecha_queja = fecha_queja;
        }

        public Integer getId_estado_queja() {
            return id_estado_queja;
        }

        public void setId_estado_queja(Integer id_estado_queja) {
            this.id_estado_queja = id_estado_queja;
        }

        public List<TipoTelefono> getTipotelefono() {
            return tipotelefono;
        }

        public List<TipoEmail> getTipoemail() {
            return tipoemail;
        }

        public void setTipoemail(List<TipoEmail> tipoemail) {
            this.tipoemail = tipoemail;
        }

        public void setTipotelefono(List<TipoTelefono> tipotelefono) {
            this.tipotelefono = tipotelefono;
        }

        public Integer getId_sub__actividad_economica() {
            return id_sub__actividad_economica;
        }

        public void setId_sub__actividad_economica(Integer id_sub__actividad_economica) {
            this.id_sub__actividad_economica = id_sub__actividad_economica;
        }

        public String getFactura_numero() {
            return factura_numero;
        }

        public void setFactura_numero(String factura_numero) {
            this.factura_numero = factura_numero;
        }

        public Date getFecha_factura() {
            return fecha_factura;
        }

        public void setFecha_factura(Date fecha_factura) {
            this.fecha_factura = fecha_factura;
        }

        public String getDetalle_queja() {
            return detalle_queja;
        }

        public void setDetalle_queja(String detalle_queja) {
            this.detalle_queja = detalle_queja;
        }

        public String getSolicita_que() {
            return solicita_que;
        }

        public void setSolicita_que(String solicita_que) {
            this.solicita_que = solicita_que;
        }

        public String getProcede_queja() {
            return procede_queja;
        }

        public void setProcede_queja(String procede_queja) {
            this.procede_queja = procede_queja;
        }

        public String getRazon_no_procede() {
            return razon_no_procede;
        }

        public void setRazon_no_procede(String razon_no_procede) {
            this.razon_no_procede = razon_no_procede;
        }

        public String getDatos_verificados() {
            return datos_verificados;
        }

        public void setDatos_verificados(String datos_verificados) {
            this.datos_verificados = datos_verificados;
        }

        public Integer getUsuario_asignado() {
            return usuario_asignado;
        }

        public void setUsuario_asignado(Integer usuario_asignado) {
            this.usuario_asignado = usuario_asignado;
        }

        public String getMotivo() {
            return motivo;
        }

        public void setMotivo(String motivo) {
            this.motivo = motivo;
        }

        public Integer getId_consumidor() {
            return id_consumidor;
        }

        public void setId_consumidor(Integer id_consumidor) {
            this.id_consumidor = id_consumidor;
        }

        public Integer getId_proveedor() {
            return id_proveedor;
        }

        public void setId_proveedor(Integer id_proveedor) {
            this.id_proveedor = id_proveedor;
        }

        public TipoConsumidor getTipoconsumidor() {
            return tipoconsumidor;
        }

        public void setTipoconsumidor(TipoConsumidor tipoconsumidor) {
            this.tipoconsumidor = tipoconsumidor;
        }

        public TipoFlujoGuia getTipoflujoguia() {
            return tipoflujoguia;
        }

        public void setTipoflujoguia(TipoFlujoGuia tipoflujoguia) {
            this.tipoflujoguia = tipoflujoguia;
        }

        public TipoProveedor getTipoproveedor() {
            return tipoproveedor;
        }

        public void setTipoproveedor(TipoProveedor tipoproveedor) {
            this.tipoproveedor = tipoproveedor;
        }

        public TipoEstadoQueja getTipoEstadoQueja() {
            return tipoEstadoQueja;
        }

        public void setTipoEstadoQueja(TipoEstadoQueja tipoEstadoQueja) {
            this.tipoEstadoQueja = tipoEstadoQueja;
        }

        public Integer getId_diaco_sede() {
            return id_diaco_sede;
        }

        public void setId_diaco_sede(Integer id_diaco_sede) {
            this.id_diaco_sede = id_diaco_sede;
        }        
        
    @Override
    public String toString() {
        CheckNull checknull = new CheckNull();
        JsonObject queja = new JsonObject();
        JsonArray tel = new JsonArray();
        JsonArray email = new JsonArray();
        queja.addProperty("id_queja",this.id_queja);
        queja.addProperty("fecha_queja",this.fecha_queja.toString());
        queja.addProperty("id_estado_queja",this.id_estado_queja);
        queja.addProperty("detalle_queja",this.detalle_queja);
        queja.addProperty("solicita_que",this.solicita_que);
        queja.addProperty("motivo",checknull.cknull(this.motivo));
        queja.addProperty("nombre_consumidor",checknull.cknull(this.tipoconsumidor.getNombreCompleto()));
        queja.addProperty("nombre_proveedor",checknull.cknull(this.tipoproveedor.getNombre()));
        queja.addProperty("documentado",checknull.cknull(this.documentado));
        queja.addProperty("id_flujo",this.tipoEstadoQueja.getTipoflujo().getId());
        queja.addProperty("nombre_flujo",this.tipoEstadoQueja.getTipoflujo().getFlujo());
        queja.addProperty("id_diaco_sede",this.id_diaco_sede);
        queja.addProperty("nombre_sede",this.tipoSede.getNombre_sede());
        queja.addProperty("is_est_resolver",this.getIs_est_resolver());
        if(tipoDepartamento!=null)
            queja.addProperty("nombre_departamento",this.tipoDepartamento.getNombre_departamento());
        if(tipoMunicipio!=null)
            queja.addProperty("nombre_municipio",this.tipoMunicipio.getNombre_municipio());
        if(tipoUsuario!=null){
            queja.addProperty("usuario_asignado",this.tipoUsuario.getId_usuario());
            queja.addProperty("usuario_asignado_nombre",this.tipoUsuario.getNombre());
        }
        if(tipoEstadoQueja!=null)
            queja.addProperty("estado_descripcion",this.tipoEstadoQueja.getDescripcion());
        queja.addProperty("quejanumero", this.getQuejaNumero());
        for (TipoTelefono loc : this.tipotelefono) {
            tel.add(loc.toJsonelement());
	}
        queja.add("tipotelefono",tel);
        for (TipoEmail loc : this.tipoemail) {
            email.add(loc.toJsonelement());
	}
        queja.add("tipoemail",email);
        
        if(tipoflujoguia != null){
            queja.add("tipoflujoguia",tipoflujoguia.toJsonelement());
        }else{
            JsonObject temp = new JsonObject();
            temp.addProperty("atcon_conc_previa",false);
            temp.addProperty("atcon_verif_datos",false);
            temp.addProperty("atcon_com_permanente",false);
            temp.addProperty("atcon_audiencia_conc",false);
            temp.addProperty("atcon_audiencia_prueba",false);
            temp.addProperty("vyv_flag",false);
            temp.addProperty("vyv_informe_final",false);
            temp.addProperty("jur_flag",false);
            temp.addProperty("jur_documentado",false);
            temp.addProperty("jur_result_audiencia",false);
            temp.addProperty("jur_resol_final",false);
            temp.addProperty("jur_cobro_sancion",false);
            queja.add("tipoflujoguia",temp);
        }
        return queja.toString()+",";
    }

    /**
     * @return the fuente
     */
    public String getFuente() {
        return fuente;
    }

    /**
     * @param fuente the fuente to set
     */
    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    /**
     * @return the direccion_proveedor
     */
    public String getDireccion_proveedor() {
        return direccion_proveedor;
    }

    /**
     * @param direccion_proveedor the direccion_proveedor to set
     */
    public void setDireccion_proveedor(String direccion_proveedor) {
        this.direccion_proveedor = direccion_proveedor;
    }

    /**
     * @return the zona_proveedor
     */
    public Integer getZona_proveedor() {
        return zona_proveedor;
    }

    /**
     * @param zona_proveedor the zona_proveedor to set
     */
    public void setZona_proveedor(Integer zona_proveedor) {
        this.zona_proveedor = zona_proveedor;
    }

    /**
     * @return the tipoDepartamentoProv
     */
    public TipoDepartamento getTipoDepartamentoProv() {
        return tipoDepartamentoProv;
    }

    /**
     * @param tipoDepartamentoProv the tipoDepartamentoProv to set
     */
    public void setTipoDepartamentoProv(TipoDepartamento tipoDepartamentoProv) {
        this.tipoDepartamentoProv = tipoDepartamentoProv;
    }

    /**
     * @return the tipoMunicipioProv
     */
    public TipoMunicipio getTipoMunicipioProv() {
        return tipoMunicipioProv;
    }

    /**
     * @param tipoMunicipioProv the tipoMunicipioProv to set
     */
    public void setTipoMunicipioProv(TipoMunicipio tipoMunicipioProv) {
        this.tipoMunicipioProv = tipoMunicipioProv;
    }

    /**
     * @return the id_departamento_proveedor
     */
    public Integer getId_departamento_proveedor() {
        return id_departamento_proveedor;
    }

    /**
     * @param id_departamento_proveedor the id_departamento_proveedor to set
     */
    public void setId_departamento_proveedor(Integer id_departamento_proveedor) {
        this.id_departamento_proveedor = id_departamento_proveedor;
    }

    /**
     * @return the id_municipio_proveedor
     */
    public Integer getId_municipio_proveedor() {
        return id_municipio_proveedor;
    }

    /**
     * @param id_municipio_proveedor the id_municipio_proveedor to set
     */
    public void setId_municipio_proveedor(Integer id_municipio_proveedor) {
        this.id_municipio_proveedor = id_municipio_proveedor;
    }

    /**
     * @return the is_est_resolver
     */
    public Integer getIs_est_resolver() {
        return is_est_resolver;
    }

    /**
     * @param is_est_resolver the is_est_resolver to set
     */
    public void setIs_est_resolver(Integer is_est_resolver) {
        this.is_est_resolver = is_est_resolver;
    }

}
