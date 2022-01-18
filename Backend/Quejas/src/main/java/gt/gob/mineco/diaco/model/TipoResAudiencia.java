package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import gt.gob.mineco.diaco.util.CheckNull;
import java.io.Serializable;
import javax.persistence.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * The persistent class for the diaco.dbo.diaco_res_audiencia database table.
 * 
 */
@Entity
@Table(name="diaco_res_audiencia")
@NamedQuery( name = "TipoResAudiencia.findAll", query = "SELECT t FROM TipoResAudiencia t inner join t.tipopasoqueja og where og.id_queja = :id_queja")
public class TipoResAudiencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", precision=0) 
        private Integer id;
        /*@Column(name="id_queja_paso")
        private Integer id_queja_paso;*/
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fechahora")        
        private Date fechahora;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fechanotificacion")        
        private Date fechanotificacion;
        @Column(name="tipo")
        private Integer tipo;
        @Column(name="ccitacion")        
	private String ccitacion;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_actualizado")        
        private Date fecha_actualizado;
        @Column(name="usuario_actualizado")
        private Integer usuario_actualizado;
	@Column(name="id_tipo_registro")
        private Integer id_tipo_registro;
        @Column(name="id_notif_cons")
        private Integer id_notif_cons;
        @Column(name="id_notif_prov")
        private Integer id_notif_prov;
        @Column(name="id_acta")
        private Integer id_acta;
        @Column(name="corr_acta")
        private Integer corr_acta;
        @Column(name="corr_notif_cons")
        private Integer corr_notif_cons;
        @Column(name="corr_notif_prov")
        private Integer corr_notif_prov;
        /*@JoinColumn(name = "tipo", referencedColumnName = "id_catalogo",updatable=false, insertable=false)
        @OneToMany
        private TipoCatalogo tipocatalogo;*/
        @JoinColumn(name = "id_queja_paso", referencedColumnName = "id_queja_paso")
        @OneToOne
        private TipoPasoQueja tipopasoqueja;   
        
        public TipoResAudiencia() {
	}

    public Integer getId_notif_cons() {
        return id_notif_cons;
    }

    public void setId_notif_cons(Integer id_notif_cons) {
        this.id_notif_cons = id_notif_cons;
    }

    public Integer getId_notif_prov() {
        return id_notif_prov;
    }

    public void setId_notif_prov(Integer id_notif_prov) {
        this.id_notif_prov = id_notif_prov;
    }

    public Integer getId_acta() {
        return id_acta;
    }

    public void setId_acta(Integer id_acta) {
        this.id_acta = id_acta;
    }

    public Integer getCorr_acta() {
        return corr_acta;
    }

    public void setCorr_acta(Integer corr_acta) {
        this.corr_acta = corr_acta;
    }

    public Integer getCorr_notif_cons() {
        return corr_notif_cons;
    }

    public void setCorr_notif_cons(Integer corr_notif_cons) {
        this.corr_notif_cons = corr_notif_cons;
    }

    public Integer getCorr_notif_prov() {
        return corr_notif_prov;
    }

    public void setCorr_notif_prov(Integer corr_notif_prov) {
        this.corr_notif_prov = corr_notif_prov;
    }

    public TipoPasoQueja getTipopasoqueja() {
        return tipopasoqueja;
    }

    public void setTipopasoqueja(TipoPasoQueja tipopasoqueja) {
        this.tipopasoqueja = tipopasoqueja;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /*public Integer getId_queja_paso() {
        return id_queja_paso;
    }

    public void setId_queja_paso(Integer id_queja_paso) {
        this.id_queja_paso = id_queja_paso;
    }*/

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    public Date getFechanotificacion() {
        return fechanotificacion;
    }

    public void setFechanotificacion(Date fechanotificacion) {
        this.fechanotificacion = fechanotificacion;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getCcitacion() {
        return ccitacion;
    }

    public void setCcitacion(String ccitacion) {
        this.ccitacion = ccitacion;
    }

    public Date getFecha_actualizado() {
        return fecha_actualizado;
    }

    public void setFecha_actualizado(Date fecha_actualizado) {
        this.fecha_actualizado = fecha_actualizado;
    }

    public Integer getUsuario_actualizado() {
        return usuario_actualizado;
    }

    public void setUsuario_actualizado(Integer usuario_actualizado) {
        this.usuario_actualizado = usuario_actualizado;
    }

    public Integer getId_tipo_registro() {
        return id_tipo_registro;
    }

    public void setId_tipo_registro(Integer id_tipo_registro) {
        this.id_tipo_registro = id_tipo_registro;
    }

        
        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            CheckNull checknull = new CheckNull();
            temp.addProperty("id",this.id);
            temp.addProperty("fechahora",checknull.cknull(this.fechahora));
            temp.addProperty("fechanotificacion",checknull.cknull(this.fechanotificacion));
            temp.addProperty("tipo",checknull.cknull(this.tipo));
            temp.addProperty("id_notif_cons",checknull.cknull(this.id_notif_cons));
            temp.addProperty("id_notif_prov",checknull.cknull(this.id_notif_prov));
            temp.addProperty("id_acta",checknull.cknull(this.id_acta));
            temp.addProperty("corr_acta",checknull.cknull(this.corr_acta));
            temp.addProperty("corr_notif_cons",checknull.cknull(this.corr_notif_cons));
            temp.addProperty("corr_notif_prov",checknull.cknull(this.corr_notif_prov));
            /*if(tipocatalogo!=null){
                temp.addProperty("tipo_catalogo",tipocatalogo.getNombre_tabla_catalogo());
            }else{
                temp.addProperty("tipo_catalogo","");
            }*/
            return temp.toString()+",";
        }
        
}
