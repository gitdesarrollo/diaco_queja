package gt.gob.mineco.diaco.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import gt.gob.mineco.diaco.util.CheckNull;
import gt.gob.mineco.diaco.model.TipoDepartamentoInterno;
import gt.gob.mineco.diaco.model.TipoSede;
import gt.gob.mineco.diaco.model.TipoMovimExpDetalle;
import gt.gob.mineco.diaco.model.TipoPasoQueja;

/**
 * The persistent class for the diaco_cambio_area_exp database table.
 * 
 */
@Entity
@Table(name="diaco_cambio_area_exp")
@NamedQueries({
    @NamedQuery(name = "TipoMovimExp.findall", query = "SELECT t FROM TipoMovimExp t where t.id_cambio_exp in ( select distinct sq.tipoMovimExp.id_cambio_exp from TipoMovimExpDetalle sq where sq.id_queja = :idqueja )"),
    @NamedQuery(name = "TipoMovimExp.findTop5", query = "SELECT t FROM TipoMovimExp t where t.tiporeg_MovimExp.tipoMovimExp.id_cambio_exp = t.id_cambio_exp and t.id_departamento_interno_desde = :id_departamento_interno_desde order by t.tipopasoqueja.fecha_operacion desc")
})

public class TipoMovimExp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cambio_exp", precision=0)
        private Integer id_cambio_exp;
        @Column(name="id_departamento_interno_desde")
        private Integer id_departamento_interno_desde;
        @Column(name="id_departamento_interno_hasta")
        private Integer id_departamento_interno_hasta;
        @Column(name="usuario_previo")
        private Integer usuario_previo;
        @Column(name="usuario_actual")
        private Integer usuario_actual;
        @Column(name="id_sede_desde")
        private Integer id_sede_desde;
        @Column(name="id_sede_hasta")
        private Integer id_sede_hasta;        
        @Column(name="id_proveedor_hasta")
        private Integer id_proveedor_hasta;   
        @JoinColumn(name = "id_queja_paso", referencedColumnName = "id_queja_paso")
        @OneToOne
        private TipoPasoQueja tipopasoqueja;        
        @JoinColumn(name = "id_departamento_interno_desde", referencedColumnName = "id_departamento_interno",updatable=false, insertable=false)
        @OneToOne
        private TipoDepartamentoInterno tipodepartamentointernodesde;
        @JoinColumn(name = "id_departamento_interno_hasta", referencedColumnName = "id_departamento_interno",updatable=false, insertable=false)
        @OneToOne
        private TipoDepartamentoInterno tipodepartamentointernohasta;
        @JoinColumn(name = "id_sede_desde", referencedColumnName = "id_diaco_sede",updatable=false, insertable=false)
        @OneToOne
        private TipoSede tiposededesde;
        @JoinColumn(name = "id_sede_hasta", referencedColumnName = "id_diaco_sede",updatable=false, insertable=false)
        @OneToOne
        private TipoSede tiposedehasta;
        @JoinColumn(name = "id_cambio_exp", referencedColumnName = "id_cambio_exp",updatable=false, insertable=false)
        @OneToMany       
        List<TipoMovimExpDetalle> tipoMovimExpDetalle;
        @JoinColumn(name = "id_cambio_exp", referencedColumnName = "id_cambio_exp",updatable=false, insertable=false)
        @OneToOne
        private TipoReg_MovimExp tiporeg_MovimExp;
        @JoinColumn(name = "id_proveedor_hasta", referencedColumnName = "id_proveedor",updatable=false, insertable=false)
        @OneToOne
        private TipoProveedor tipoProveedor;
        
	public TipoMovimExp() {
	}

    public Integer getId_proveedor_hasta() {
        return id_proveedor_hasta;
    }

    public void setId_proveedor_hasta(Integer id_proveedor_hasta) {
        this.id_proveedor_hasta = id_proveedor_hasta;
    }

    public Integer getId_cambio_exp() {
        return id_cambio_exp;
    }

    public void setId_cambio_exp(Integer id_cambio_exp) {
        this.id_cambio_exp = id_cambio_exp;
    }

    public Integer getId_departamento_interno_desde() {
        return id_departamento_interno_desde;
    }

    public void setId_departamento_interno_desde(Integer id_departamento_interno_desde) {
        this.id_departamento_interno_desde = id_departamento_interno_desde;
    }

    public Integer getId_departamento_interno_hasta() {
        return id_departamento_interno_hasta;
    }

    public void setId_departamento_interno_hasta(Integer id_departamento_interno_hasta) {
        this.id_departamento_interno_hasta = id_departamento_interno_hasta;
    }

    public Integer getUsuario_previo() {
        return usuario_previo;
    }

    public void setUsuario_previo(Integer usuario_previo) {
        this.usuario_previo = usuario_previo;
    }

    public Integer getUsuario_actual() {
        return usuario_actual;
    }

    public void setUsuario_actual(Integer usuario_actual) {
        this.usuario_actual = usuario_actual;
    }

    public Integer getId_sede_desde() {
        return id_sede_desde;
    }

    public void setId_sede_desde(Integer id_sede_desde) {
        this.id_sede_desde = id_sede_desde;
    }

    public Integer getId_sede_hasta() {
        return id_sede_hasta;
    }

    public void setId_sede_hasta(Integer id_sede_hasta) {
        this.id_sede_hasta = id_sede_hasta;
    }

    public TipoPasoQueja getTipopasoqueja() {
        return tipopasoqueja;
    }

    public void setTipopasoqueja(TipoPasoQueja tipopasoqueja) {
        this.tipopasoqueja = tipopasoqueja;
    }

        @Override
        public String toString() {
            CheckNull checknull = new CheckNull();
            JsonObject temp = new JsonObject();
            temp.addProperty("id_cambio_exp",this.id_cambio_exp);
            temp.addProperty("id_departamento_interno_desde",checknull.cknull(this.id_departamento_interno_desde));
            temp.addProperty("id_departamento_interno_hasta",checknull.cknull(this.id_departamento_interno_hasta));
            temp.addProperty("usuario_previo",checknull.cknull(this.usuario_previo));
            temp.addProperty("usuario_actual",checknull.cknull(this.usuario_actual));
            temp.addProperty("id_sede_desde",checknull.cknull(this.id_sede_desde));
            temp.addProperty("id_sede_hasta",checknull.cknull(this.id_sede_hasta));
            temp.addProperty("id_queja",checknull.cknull(this.tipopasoqueja.getId_queja()));  
            temp.addProperty("fecha",checknull.cknull(this.tipopasoqueja.getFecha_operacion()));          
                    
            if(tipodepartamentointernodesde!=null)
                temp.addProperty("dpto_interno_desde",tipodepartamentointernodesde.getNombre());
            else
                temp.addProperty("dpto_interno_desde","");
            if(tipodepartamentointernohasta!=null)
                temp.addProperty("dpto_interno_hasta",tipodepartamentointernohasta.getNombre());
            else
                temp.addProperty("dpto_interno_hasta","");
            if(tiposededesde!=null)
                temp.addProperty("sede_desde",tiposededesde.getNombre_sede());
            else
                temp.addProperty("sede_desde","");
            if(tiposedehasta!=null)
                temp.addProperty("sede_hasta",tiposedehasta.getNombre_sede());
            else
                temp.addProperty("sede_hasta","");
            if(tiporeg_MovimExp!=null){
                temp.addProperty("reportid",tiporeg_MovimExp.getId());
                temp.addProperty("codigo_reporte",tiporeg_MovimExp.getCodigo());
            }else{
                temp.addProperty("reportid","");
                temp.addProperty("codigo_reporte","");
            }
            if(tipoProveedor!=null)
                temp.addProperty("nombre_proveedor",tipoProveedor.getNombre());
            else
                temp.addProperty("nombre_proveedor","");
            
            return temp.toString()+",";
        }
                
}
