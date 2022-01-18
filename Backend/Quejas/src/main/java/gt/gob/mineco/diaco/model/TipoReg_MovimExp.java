package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import gt.gob.mineco.diaco.model.TipoQuejaRegistro;
import gt.gob.mineco.diaco.model.TipoMovimExp;
import gt.gob.mineco.diaco.util.CheckNull;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * The persistent class for the reg_formulario_mov_exp database table.
 * 
 */
@Entity
@Table(name="reg_formulario_mov_exp")
@NamedQueries({
@NamedQuery(name = "TipoReg_MovimExp.findAll",query = "SELECT t FROM TipoReg_MovimExp t where t.tipoMovimExp.id_cambio_exp = :id_cambio_exp"),
@NamedQuery(name = "TipoReg_MovimExp.findById",query = "SELECT t FROM TipoReg_MovimExp t where t.id = :id")    
})
public class TipoReg_MovimExp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", precision=0)
        private Integer id;
        @Column(name="id_queja")
        private Integer id_queja;
        @Column(name="id_registro")
        private Integer id_registro;
        @Column(name="id_tipo_registro")
        private Integer id_tipo_registro;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_creacion")
        private Date fecha_creacion;
        @Column(name="creado_por")
        private Integer creado_por;
        @Column(name="anio_queja")
        private Integer anio_queja;
        @Column(name="quejanumero")
        private Integer quejanumero;
        @Column(name="no")
        private Integer no;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_entrega")
        private Date fecha_entrega;
        @Column(name="dpto_envia")
        private String dpto_envia;
        @Column(name="dpto_recibe")
        private String dpto_recibe;
        @Column(name="codigo")
        private String codigo;
        @Column(name="version")
        private String version;
        @OneToOne
        @JoinColumn(name = "id_cambio_exp", referencedColumnName = "id_cambio_exp")
        private TipoMovimExp tipoMovimExp;
        
	public TipoReg_MovimExp() {
	}

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_queja() {
        return id_queja;
    }

    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
    }

    public Integer getId_registro() {
        return id_registro;
    }

    public void setId_registro(Integer id_registro) {
        this.id_registro = id_registro;
    }

    public Integer getId_tipo_registro() {
        return id_tipo_registro;
    }

    public void setId_tipo_registro(Integer id_tipo_registro) {
        this.id_tipo_registro = id_tipo_registro;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Integer getCreado_por() {
        return creado_por;
    }

    public void setCreado_por(Integer creado_por) {
        this.creado_por = creado_por;
    }

    public Integer getAnio_queja() {
        return anio_queja;
    }

    public void setAnio_queja(Integer anio_queja) {
        this.anio_queja = anio_queja;
    }

    public Integer getQuejanumero() {
        return quejanumero;
    }

    public void setQuejanumero(Integer quejanumero) {
        this.quejanumero = quejanumero;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public String getDpto_envia() {
        return dpto_envia;
    }

    public void setDpto_envia(String dpto_envia) {
        this.dpto_envia = dpto_envia;
    }

    public String getDpto_recibe() {
        return dpto_recibe;
    }

    public void setDpto_recibe(String dpto_recibe) {
        this.dpto_recibe = dpto_recibe;
    }

    public TipoMovimExp getTipoMovimExp() {
        return tipoMovimExp;
    }

    public void setTipoMovimExp(TipoMovimExp tipoMovimExp) {
        this.tipoMovimExp = tipoMovimExp;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
        @Override
        public String toString() {
            CheckNull checknull = new CheckNull();
            JsonObject temp = new JsonObject();
            temp.addProperty("id",checknull.cknull(this.id));
            temp.addProperty("id_queja",checknull.cknull(this.id_queja));
            temp.addProperty("id_registro",checknull.cknull(this.id_registro));
            temp.addProperty("id_tipo_registro",checknull.cknull(this.id_tipo_registro));
            temp.addProperty("id_cambio_exp",checknull.cknull(this.tipoMovimExp.getId_cambio_exp()));
            temp.addProperty("no",checknull.cknull(this.no));
            temp.addProperty("fecha_entrega",checknull.cknull(this.fecha_entrega));
            temp.addProperty("dpto_envia",checknull.cknull(this.dpto_envia));
            temp.addProperty("dpto_recibe",checknull.cknull(this.dpto_recibe));
            temp.addProperty("anio_queja",checknull.cknull(this.anio_queja));
            temp.addProperty("quejanumero",checknull.cknull(this.quejanumero));
            temp.addProperty("codigo",checknull.cknull(this.codigo));
            temp.addProperty("version",checknull.cknull(this.version));
            return temp.toString()+",";
        }
           
}
