package gt.gob.mineco.diaco.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import gt.gob.mineco.diaco.util.CheckNull;
import gt.gob.mineco.diaco.model.TipoPasoQueja;
import gt.gob.mineco.diaco.model.TipoProgramaAudiencia;

/**
 * The persistent class for the diaco_audiencia database table.
 * 
 */
@Entity
@Table(name="diaco_audiencia")
@NamedQueries({
    @NamedQuery(
        name = "TipoAudiencia.findxIdQuejaNo",
        query = "SELECT t FROM TipoAudiencia t inner join t.tipopasoqueja og where og.id_queja = :idqueja and t.es_primera_seg_audiencia = :no_audiencia") 
})

public class TipoAudiencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_audiencia", precision=0)
        private Integer id_audiencia;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_finalizada")
        private Date fecha_finalizada;
        @Column(name="es_primera_seg_audiencia")
        private Integer es_primera_seg_audiencia;
        @Column(name="id_tipo_registro")
        private Integer id_tipo_registro;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_firma_direccion")
        private Date fecha_firma_direccion;
        @Column(name="aprobado_jefe_juridico")
        private String aprobado_jefe_juridico;
        @Column(name="ced_cita_consumidor")
        private String ced_cita_consumidor;
        @Column(name="ced_cita_proveedor")
        private String ced_cita_proveedor;
        @Column(name="ced_notif_consumidor")
        private String ced_notif_consumidor;
        @Column(name="ced_notif_proveedor")
        private String ced_notif_proveedor;
        @JoinColumn(name = "id_queja_paso", referencedColumnName = "id_queja_paso")
        @OneToOne
        private TipoPasoQueja tipopasoqueja;        
        @JoinColumn(name = "id_audiencia", referencedColumnName = "id_audiencia",updatable=false, insertable=false)
        @OneToMany
        private List<TipoProgramaAudiencia> tipoProgramaAudiencia;        
             
	public TipoAudiencia() {
	}

    public Integer getId_audiencia() {
        return id_audiencia;
    }

    public void setId_audiencia(Integer id_audiencia) {
        this.id_audiencia = id_audiencia;
    }

    public Date getFecha_finalizada() {
        return fecha_finalizada;
    }

    public void setFecha_finalizada(Date fecha_finalizada) {
        this.fecha_finalizada = fecha_finalizada;
    }

    public Integer getEs_primera_seg_audiencia() {
        return es_primera_seg_audiencia;
    }

    public void setEs_primera_seg_audiencia(Integer es_primera_seg_audiencia) {
        this.es_primera_seg_audiencia = es_primera_seg_audiencia;
    }

    public Integer getId_tipo_registro() {
        return id_tipo_registro;
    }

    public void setId_tipo_registro(Integer id_tipo_registro) {
        this.id_tipo_registro = id_tipo_registro;
    }

    public Date getFecha_firma_direccion() {
        return fecha_firma_direccion;
    }

    public void setFecha_firma_direccion(Date fecha_firma_direccion) {
        this.fecha_firma_direccion = fecha_firma_direccion;
    }

    public String getAprobado_jefe_juridico() {
        return aprobado_jefe_juridico;
    }

    public void setAprobado_jefe_juridico(String aprobado_jefe_juridico) {
        this.aprobado_jefe_juridico = aprobado_jefe_juridico;
    }

    public String getCed_cita_consumidor() {
        return ced_cita_consumidor;
    }

    public void setCed_cita_consumidor(String ced_cita_consumidor) {
        this.ced_cita_consumidor = ced_cita_consumidor;
    }

    public String getCed_cita_proveedor() {
        return ced_cita_proveedor;
    }

    public void setCed_cita_proveedor(String ced_cita_proveedor) {
        this.ced_cita_proveedor = ced_cita_proveedor;
    }

    public String getCed_notif_consumidor() {
        return ced_notif_consumidor;
    }

    public void setCed_notif_consumidor(String ced_notif_consumidor) {
        this.ced_notif_consumidor = ced_notif_consumidor;
    }

    public String getCed_notif_proveedor() {
        return ced_notif_proveedor;
    }

    public void setCed_notif_proveedor(String ced_notif_proveedor) {
        this.ced_notif_proveedor = ced_notif_proveedor;
    }

    public TipoPasoQueja getTipopasoqueja() {
        return tipopasoqueja;
    }

    public void setTipopasoqueja(TipoPasoQueja tipopasoqueja) {
        this.tipopasoqueja = tipopasoqueja;
    }

    public List<TipoProgramaAudiencia> getTipoProgramaAudiencia() {
        return tipoProgramaAudiencia;
    }

    public void setTipoProgramaAudiencia(List<TipoProgramaAudiencia> tipoProgramaAudiencia) {
        this.tipoProgramaAudiencia = tipoProgramaAudiencia;
    }
        
        
        
        @Override
        public String toString() {
            CheckNull checknull = new CheckNull();
            JsonObject temp = new JsonObject();
            temp.addProperty("id_audiencia",this.id_audiencia);
            temp.addProperty("fecha_finalizada",checknull.cknull(this.fecha_finalizada));
            temp.addProperty("es_primera_seg_audiencia",checknull.cknull(this.es_primera_seg_audiencia));
            temp.addProperty("id_tipo_registro",checknull.cknull(this.id_tipo_registro));
            temp.addProperty("fecha_firma_direccion",checknull.cknull(this.fecha_firma_direccion));
            temp.addProperty("aprobado_jefe_juridico",checknull.cknull(this.aprobado_jefe_juridico));
            temp.addProperty("ced_cita_consumidor",checknull.cknull(this.ced_cita_consumidor));
            temp.addProperty("ced_cita_proveedor",checknull.cknull(this.ced_cita_proveedor));
            temp.addProperty("ced_notif_consumidor",checknull.cknull(this.ced_notif_consumidor));
            temp.addProperty("ced_notif_proveedor",checknull.cknull(this.ced_notif_proveedor));
            temp.addProperty("id_queja",checknull.cknull(this.tipopasoqueja.getId_queja()));            
            JsonArray programa_aud = new JsonArray();
            for (TipoProgramaAudiencia loc : this.tipoProgramaAudiencia) {
                programa_aud.add(loc.toJsonelement());
            }
            temp.add("ProgramaAudiencia",programa_aud);

            return temp.toString()+",";
        }
                
}
