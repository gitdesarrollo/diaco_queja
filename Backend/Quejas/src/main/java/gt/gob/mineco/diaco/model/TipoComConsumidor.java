package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import javax.persistence.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
/**
 * The persistent class for the diaco_comunicacion_consumidor database table.
 * 
 */
@Entity
@Table(name="diaco_comunicacion_consumidor")
@NamedQuery(name="TipoComConsumidor.findAll", query="SELECT t FROM TipoComConsumidor t inner join t.tipopasoqueja og where og.id_queja = :idqueja and og.id_tipo_registro in (3,20,29)")
@NamedQuery(name="TipoComConsumidor.findInd", query="SELECT t FROM TipoComConsumidor t where t.id_comunicacion_consumidor = :idcomunicacionconsumidor")
@NamedQuery(name="TipoComConsumidor.findAll.list", query="SELECT t FROM TipoComConsumidor t inner join t.tipopasoqueja og where og.id_queja = :idqueja and og.id_tipo_registro in (3,20,29)") //TRAE TODOS LOS REGISTROS DE COMUNICACION
//inner join t.tipopasoqueja og where og.id_queja_paso=:usr
public class TipoComConsumidor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_comunicacion_consumidor", precision=0) 
        private Integer id_comunicacion_consumidor;
        @JoinColumn(name = "id_queja_paso", referencedColumnName = "id_queja_paso")
        @OneToOne
        private TipoPasoQueja tipopasoqueja;        
        @Column(name="estatus")
        private String estatus;
        @Column(name="observaciones")        
	private String observaciones;
        @Column(name="id_tipo_registro")
        private Integer id_tipo_registro;
	@Column(name="via")        
	private Integer via;
        @Column(name="id_flujo")        
	private Integer id_flujo;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_ingreso")
        private Date fecha_ingreso;
        

    public Integer getId_flujo() {
        return id_flujo;
    }

    public void setId_flujo(Integer id_flujo) {
        this.id_flujo = id_flujo;
    }

    public TipoPasoQueja getTipopasoqueja() {
        return tipopasoqueja;
    }

    public void setTipopasoqueja(TipoPasoQueja tipopasoqueja) {
        this.tipopasoqueja = tipopasoqueja;
    }
        
	public TipoComConsumidor() {
	}

    public Integer getId_comunicacion_consumidor() {
        return id_comunicacion_consumidor;
    }

    public void setId_comunicacion_consumidor(Integer id_comunicacion_consumidor) {
        this.id_comunicacion_consumidor = id_comunicacion_consumidor;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getId_tipo_registro() {
        return id_tipo_registro;
    }

    public void setId_tipo_registro(Integer id_tipo_registro) {
        this.id_tipo_registro = id_tipo_registro;
    }

    public Integer getVia() {
        return via;
    }

    public void setVia(Integer via) {
        this.via = via;
    }

    @Override
    public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_comunicacion_consumidor",this.id_comunicacion_consumidor);
            temp.addProperty("estatus",this.estatus);
            temp.addProperty("observaciones",this.observaciones);
            temp.addProperty("id_tipo_registro",this.id_tipo_registro);
            temp.addProperty("via",this.via);
            
            String strDateFormat = "dd/MM/yyyy";
            SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
            String fecha = objSDF.format(this.fecha_ingreso);
            //String fecha = fecha_ingreso.toString();
            temp.addProperty("fecha_ingreso",fecha);
            return temp.toString()+",";
    }

    /**
     * @return the fecha_ingreso
     */
    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    /**
     * @param fecha_ingreso the fecha_ingreso to set
     */
    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }
            
}
