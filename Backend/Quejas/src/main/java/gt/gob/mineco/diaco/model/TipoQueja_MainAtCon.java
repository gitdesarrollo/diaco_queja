package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import com.google.gson.JsonObject;
import gt.gob.mineco.diaco.util.CheckNull;
/**
 * The persistent class for the diaco_queja database table.
 * 
 */
@Entity
@Table(name="diaco_queja")
@NamedQuery(name="TipoQueja_MainAtCon.findAll", query="SELECT t FROM TipoQueja_MainAtCon t ORDER BY t.fecha_queja desc")
public class TipoQueja_MainAtCon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @Column(name="id_queja")
	private Integer id_queja;
        @Column(name="id_consumidor")
	private Integer id_consumidor;
        @Column(name="id_proveedor")
	private Integer id_proveedor;
        @Column(name="anio")
	private Integer anio;
        @Column(name="no_queja")
	private Integer no_queja;
        @Column(name="usuario_asignado")
	private Integer usuario_asignado;
        @Column(name="detalle_queja")
	private String detalle_queja;
        @Column(name="fuente")
	private String fuente;
        @Column(name="completo_40_datos")
	private String completo_datos;
        @Column(name="solicita_que")
	private String solicita_que;
        @Column(name="id_departamento")
        private Integer id_departamento;
        @Column(name="id_municipio")
        private Integer id_municipio;
        @OneToOne
        @JoinColumn(name = "id_consumidor", referencedColumnName = "id_consumidor",updatable=false, insertable=false)
        private TipoConsumidor tipoconsumidor;
        @OneToOne
        @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor",updatable=false, insertable=false)
        private TipoProveedor tipoproveedor;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_queja")
        private Date fecha_queja; 
        @Column(name="id_estado_queja")
        private Integer id_estado_queja;
        @JoinColumn(name = "id_queja", referencedColumnName = "id_queja",updatable=false, insertable=false)
        @OneToOne
        private TipoFlujoGuia tipoflujoguia;  
        @JoinColumn(name = "id_estado_queja", referencedColumnName = "id_estado_queja",updatable=false, insertable=false)        
        @OneToOne
        private TipoEstadoQueja tipoEstadoQueja;
        @OneToOne
        @JoinColumn(name = "id_departamento", referencedColumnName = "codigo_departamento",updatable=false, insertable=false)
        private TipoDepartamento tipoDepartamento;
        @OneToOne
        @JoinColumn(name = "id_municipio", referencedColumnName = "codigo_municipio",updatable=false, insertable=false)
        private TipoMunicipio tipoMunicipio;
        @OneToOne
        @JoinColumn(name = "usuario_asignado", referencedColumnName = "id_usuario",updatable=false, insertable=false)
        private TipoUsuario_Conf tipoUsuario;

        
	public TipoQueja_MainAtCon() {
	}
        
        

    public String getCompleto_datos() {
        return completo_datos;
    }


    public void setCompleto_datos(String completo_datos) {
        this.completo_datos = completo_datos;
    }
    
    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
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

        public Integer getUsuario_asignado() {
            return usuario_asignado;
        }

        public void setUsuario_asignado(Integer usuario_asignado) {
            this.usuario_asignado = usuario_asignado;
        }

        public String getQuejaNumero(){
            String retvalue="";
            if(this.anio != null && this.no_queja != null)
                retvalue=this.no_queja.toString()+"-"+this.anio.toString();
            return retvalue;
        }
        
    public Integer getId_queja() {
        return id_queja;
    }

    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
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

    public TipoProveedor getTipoproveedor() {
        return tipoproveedor;
    }

    public void setTipoproveedor(TipoProveedor tipoproveedor) {
        this.tipoproveedor = tipoproveedor;
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

    public TipoFlujoGuia getTipoflujoguia() {
        return tipoflujoguia;
    }

    public void setTipoflujoguia(TipoFlujoGuia tipoflujoguia) {
        this.tipoflujoguia = tipoflujoguia;
    }

    public TipoEstadoQueja getTipoEstadoQueja() {
        return tipoEstadoQueja;
    }

    public void setTipoEstadoQueja(TipoEstadoQueja tipoEstadoQueja) {
        this.tipoEstadoQueja = tipoEstadoQueja;
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

        
        
    @Override
    public String toString() {
        
        JsonObject queja = new JsonObject();
        CheckNull checknull = new CheckNull();
        try{
        queja.addProperty("id_queja",this.id_queja);
        queja.addProperty("fecha_queja",checknull.cknull(this.fecha_queja));
        queja.addProperty("id_estado_queja",checknull.cknull(this.id_estado_queja));
        if(this.tipoconsumidor!=null)
            queja.addProperty("nombre_consumidor",this.tipoconsumidor.getNombreCompleto());
        if(this.tipoproveedor!=null)
        queja.addProperty("nombre_proveedor",this.tipoproveedor.getNombre());
        if(this.tipoEstadoQueja!=null){
            queja.addProperty("id_flujo",this.tipoEstadoQueja.getTipoflujo().getId());
            queja.addProperty("nombre_flujo",this.tipoEstadoQueja.getTipoflujo().getFlujo());
        }
        if(this.tipoDepartamento!=null)
            queja.addProperty("nombre_departamento",this.tipoDepartamento.getNombre_departamento());
        if(this.tipoMunicipio!=null)
            queja.addProperty("nombre_municipio",this.tipoMunicipio.getNombre_municipio());
        queja.addProperty("quejanumero",this.getQuejaNumero());
        queja.addProperty("detalle_queja",checknull.cknull(this.detalle_queja));
        queja.addProperty("solicita_que",checknull.cknull(this.solicita_que));
        
        if(tipoUsuario != null){
            queja.addProperty("asignado_nombre",this.tipoUsuario.getNombre());
            queja.addProperty("asignado_usuario",this.tipoUsuario.getLogin());
        }else{
            queja.addProperty("asignado_nombre","");
            queja.addProperty("asignado_usuario","");
        }
        
        if(this.fuente!=null){
            queja.addProperty("fuente",this.fuente);
        }else{
            queja.addProperty("fuente","");
        }
        
        if(this.completo_datos != null){
            queja.addProperty("completo_datos", "Datos Completos");
        }else{
            queja.addProperty("completo_datos","Faltan Datos");
        }
        
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
        }catch(Exception e){
            e.printStackTrace();
        }
        return queja.toString()+",";
    }

    
        
}
