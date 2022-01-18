package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import gt.gob.mineco.diaco.model.TipoDepartamento;
import gt.gob.mineco.diaco.model.TipoMunicipio;
/**
 * The persistent class for the diaco_consumidor database table.
 * 
 */
@Entity
@Table(name="diaco_consumidor")
@NamedQuery(name="TipoConsumidor.findAll", query="SELECT t FROM TipoConsumidor t   ")
//inner join t.tipopasoqueja og where og.id_queja_paso=:usr
public class TipoConsumidor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_consumidor", precision=0) 
        private Integer id_consumidor;
        @Column(name="nacionalidad")
        private Integer nacionalidad;
        @Column(name="tipo_consumidor")        
	private Integer tipo_consumidor;
        @Column(name="nit_consumidor")
        private String nit_consumidor;
	@Column(name="documento_identificacion")        
	private String documento_identificacion;
        @Column(name="genero")        
	private String genero;
        @Column(name="direccion_calle")        
	private String direccion_calle;
        @Column(name="direccion_avenida")        
	private String direccion_avenida;
        @Column(name="direccion_zona")        
	private String direccion_zona;
        @Column(name="codigo_municipio")        
	private Integer codigo_municipio;
        @Column(name="codigo_departamento")        
	private Integer codigo_departamento;
        @Column(name="sede_diaco_cercana")        
	private Integer sede_diaco_cercana;
        @Column(name="habilitado_notificacion_electronica")        
	private String habilitado_notificacion_electronica;
	@Column(name = "habilitado")
    private String habilitado;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_adicion")
        private Date fecha_adicion;
        @Column(name="usuario_adiciono")        
	private String usuario_adiciono;
        @Column(name="usuario_modifico")        
	private String usuario_modifico;
        @Column(name="nombre1")        
	private String nombre1;
        @Column(name="nombre2")        
	private String nombre2;
        @Column(name="nombre3")        
	private String nombre3;
        @Column(name="apellido1")        
	private String apellido1;
        @Column(name="apellido2")        
	private String apellido2;
        @Column(name="apellido_casada")        
	private String apellido_casada;
        @OneToOne
        @JoinColumn(name = "codigo_departamento", referencedColumnName = "codigo_departamento",updatable=false, insertable=false)
        private TipoDepartamento tipoDepartamento;
        @OneToOne
        @JoinColumn(name = "codigo_municipio", referencedColumnName = "codigo_municipio",updatable=false, insertable=false)
        private TipoMunicipio tipoMunicipio;

        
    public TipoConsumidor() {
       
    }

    public String GetDireccionCompleta(){
        String direccion="";
        if(direccion_calle!=null)
            direccion=direccion+direccion_calle+" calle ";
        if(direccion_avenida!=null)
            direccion=direccion+direccion_avenida+" avenida ";
        if(direccion_zona!=null)
            direccion=direccion+", zona "+direccion_zona;
        return direccion.trim();
    }
    
    public Integer getId_consumidor() {
        return id_consumidor;
    }

    public void setId_consumidor(Integer id_consumidor) {
        this.id_consumidor = id_consumidor;
    }

    public Integer getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Integer nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Integer getTipo_consumidor() {
        return tipo_consumidor;
    }

    public void setTipo_consumidor(Integer tipo_consumidor) {
        this.tipo_consumidor = tipo_consumidor;
    }

    public String getNit_consumidor() {
        return nit_consumidor;
    }

    public void setNit_consumidor(String nit_consumidor) {
        this.nit_consumidor = nit_consumidor;
    }

    public String getDocumento_identificacion() {
        return documento_identificacion;
    }

    public void setDocumento_identificacion(String documento_identificacion) {
        this.documento_identificacion = documento_identificacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion_calle() {
        return direccion_calle;
    }

    public void setDireccion_calle(String direccion_calle) {
        this.direccion_calle = direccion_calle;
    }

    public String getDireccion_avenida() {
        return direccion_avenida;
    }

    public void setDireccion_avenida(String direccion_avenida) {
        this.direccion_avenida = direccion_avenida;
    }

    public String getDireccion_zona() {
        return direccion_zona;
    }

    public void setDireccion_zona(String direccion_zona) {
        this.direccion_zona = direccion_zona;
    }

    public Integer getCodigo_municipio() {
        return codigo_municipio;
    }

    public void setCodigo_municipio(Integer codigo_municipio) {
        this.codigo_municipio = codigo_municipio;
    }

    public Integer getCodigo_departamento() {
        return codigo_departamento;
    }

    public void setCodigo_departamento(Integer codigo_departamento) {
        this.codigo_departamento = codigo_departamento;
    }

    public Integer getSede_diaco_cercana() {
        return sede_diaco_cercana;
    }

    public void setSede_diaco_cercana(Integer sede_diaco_cercana) {
        this.sede_diaco_cercana = sede_diaco_cercana;
    }

    public String getHabilitado_notificacion_electronica() {
        return habilitado_notificacion_electronica;
    }

    public void setHabilitado_notificacion_electronica(String habilitado_notificacion_electronica) {
        this.habilitado_notificacion_electronica = habilitado_notificacion_electronica;
    }
	
		public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    public Date getFecha_adicion() {
        return fecha_adicion;
    }

    public void setFecha_adicion(Date fecha_adicion) {
        this.fecha_adicion = fecha_adicion;
    }

    public String getUsuario_adiciono() {
        return usuario_adiciono;
    }

    public void setUsuario_adiciono(String usuario_adiciono) {
        this.usuario_adiciono = usuario_adiciono;
    }

    public String getUsuario_modifico() {
        return usuario_modifico;
    }

    public void setUsuario_modifico(String usuario_modifico) {
        this.usuario_modifico = usuario_modifico;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getNombre3() {
        return nombre3;
    }

    public void setNombre3(String nombre3) {
        this.nombre3 = nombre3;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getApellido_casada() {
        return apellido_casada;
    }

    public void setApellido_casada(String apellido_casada) {
        this.apellido_casada = apellido_casada;
    }

    public String getNombreCompleto(){
        String nombrecompleto=this.nombre1+' '+this.nombre2+' '+this.nombre3+' '+this.apellido1+' '+this.apellido2+' '+this.apellido_casada;
        return nombrecompleto.replaceAll("null", "").replaceAll("\\s+", " ").trim();
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
    
    
    
        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_consumidor",this.id_consumidor);
            String nombrecompleto=this.nombre1+' '+this.nombre2+' '+this.nombre3+' '+this.apellido1+' '+this.apellido2+' '+this.apellido_casada;
            temp.addProperty("nombre_consumidor",nombrecompleto.replaceAll("null", "").replaceAll("\\s+", " ").trim());
            temp.addProperty("nit",this.nit_consumidor);
            temp.addProperty("doc_identificacion",this.documento_identificacion);
            return temp.toString()+",";
        }
        
        public JsonObject toJsonelement() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_consumidor",this.id_consumidor);
            String nombrecompleto=this.nombre1+' '+this.nombre2+' '+this.nombre3+' '+this.apellido1+' '+this.apellido2+' '+this.apellido_casada;
            temp.addProperty("nombre_consumidor",nombrecompleto.replaceAll("null", "").replaceAll("\\s+", " ").trim());
            return temp;
        }
}
