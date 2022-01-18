package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import gt.gob.mineco.diaco.model.TipoUsuario_Simple;
/**
 * The persistent class for the diaco_visita_campo database table.
 * 
 */
@Entity
@Table(name="diaco_visita_campo")
@NamedQueries({
    @NamedQuery(name="TipoVisitaCampo.findAllAtCon", query="SELECT t FROM TipoVisitaCampo t inner join t.tipopasoqueja og where og.id_queja = :idqueja and t.id_flujo = 1 order by t.fecha_visita"),
    @NamedQuery(name="TipoVisitaCampo.findLastAtCon", query="SELECT t FROM TipoVisitaCampo t inner join t.tipopasoqueja og where og.id_queja = :idqueja and t.id_flujo = 1 order by t.fecha_visita desc"),
    @NamedQuery(name="TipoVisitaCampo.findAll", query="SELECT t FROM TipoVisitaCampo t inner join t.tipopasoqueja og where og.id_queja = :idqueja order by t.fecha_visita"),
    @NamedQuery(name="TipoVisitaCampo.findLast", query="SELECT t FROM TipoVisitaCampo t inner join t.tipopasoqueja og where og.id_queja = :idqueja order by t.fecha_visita desc"),
    @NamedQuery(name="TipoVisitaCampo.findVisitaVigByIdQueja", query="SELECT t FROM TipoVisitaCampo t inner join t.tipopasoqueja og where og.id_queja = :idqueja and t.id_flujo = 3 order by t.id_visita_campo desc"),
})
//inner join t.tipopasoqueja og where og.id_queja_paso=:usr
public class TipoVisitaCampo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_visita_campo", precision=0) 
        private Integer id_visita_campo;
        @JoinColumn(name = "id_queja_paso", referencedColumnName = "id_queja_paso")
        @OneToOne
        private TipoPasoQueja tipopasoqueja;        
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_visita")
        private Date fecha_visita;
        @Column(name="observaciones")        
	private String observaciones;
        @Column(name="id_tipo_registro")
        private Integer id_tipo_registro;
	@Column(name="hecha_por")        
	private Integer hecha_por;
        @Column(name="id_flujo")        
	private Integer id_flujo;
        @JoinColumn(name = "hecha_por", referencedColumnName = "id_usuario",updatable=false, insertable=false)
        @OneToOne
        private TipoUsuario_Simple tipoUsuario;      

    public TipoUsuario_Simple getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario_Simple tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
        
    public Integer getId_flujo() {
        return id_flujo;
    }

    public void setId_flujo(Integer id_flujo) {
        this.id_flujo = id_flujo;
    }    
        
    public void setTipopasoqueja(TipoPasoQueja tipopasoqueja) {
        this.tipopasoqueja = tipopasoqueja;
    }
    
    public TipoPasoQueja getTipopasoqueja() {
        return tipopasoqueja;
    }

    public Integer getId_visita_campo() {
        return id_visita_campo;
    }

    public void setId_visita_campo(Integer id_visita_campo) {
        this.id_visita_campo = id_visita_campo;
    }

    public Date getFecha_visita() {
        return fecha_visita;
    }

    public void setFecha_visita(Date fecha_visita) {
        this.fecha_visita = fecha_visita;
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

    public Integer getHecha_por() {
        return hecha_por;
    }

    public void setHecha_por(Integer hecha_por) {
        this.hecha_por = hecha_por;
    }

    
    
    @Override
    public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_visita_campo",this.id_visita_campo);
            temp.addProperty("fecha_visita",this.fecha_visita.toString());
            temp.addProperty("observaciones",this.observaciones);
            temp.addProperty("id_tipo_registro",this.id_tipo_registro);
            temp.addProperty("hecha_por",this.hecha_por);
            if(this.tipoUsuario!=null)
                temp.addProperty("hecha_por_nombre",this.tipoUsuario.getNombre());
            return temp.toString()+",";
    }
            
}
