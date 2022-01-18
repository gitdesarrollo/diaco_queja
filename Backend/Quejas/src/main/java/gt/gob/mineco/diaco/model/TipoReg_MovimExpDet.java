package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import gt.gob.mineco.diaco.model.TipoQuejaRegistro;
import gt.gob.mineco.diaco.util.CheckNull;
/**
 * The persistent class for the reg_formulario_mov_exp_detalle database table.
 * 
 */
@Entity
@Table(name="reg_formulario_mov_exp_detalle")
@NamedQuery(name = "TipoReg_MovimExpDet.findAll",query = "SELECT t FROM TipoReg_MovimExpDet t where t.tipoReg_MovimExp.id = :id")
public class TipoReg_MovimExpDet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", precision=0)
        private Integer id;
        @OneToOne
        @JoinColumn(name = "id_formulario", referencedColumnName = "id")
        private TipoReg_MovimExp tipoReg_MovimExp;
        @Column(name="no")
        private Integer no;
        @Column(name="queja_no")
        private String queja_no;
        @Column(name="via_de_ingreso")
        private String via_de_ingreso;
        @Column(name="nombre_consumidor")
        private String nombre_consumidor;
        @Column(name="nombre_proveedor")
        private String nombre_proveedor;
        @Column(name="observaciones")
        private String observaciones;
        
	public TipoReg_MovimExpDet() {
	}

    public TipoReg_MovimExp getTipoReg_MovimExp() {
        return tipoReg_MovimExp;
    }

    public void setTipoReg_MovimExp(TipoReg_MovimExp tipoReg_MovimExp) {
        this.tipoReg_MovimExp = tipoReg_MovimExp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getQueja_no() {
        return queja_no;
    }

    public void setQueja_no(String queja_no) {
        this.queja_no = queja_no;
    }

    public String getVia_de_ingreso() {
        return via_de_ingreso;
    }

    public void setVia_de_ingreso(String via_de_ingreso) {
        this.via_de_ingreso = via_de_ingreso;
    }

    public String getNombre_consumidor() {
        return nombre_consumidor;
    }

    public void setNombre_consumidor(String nombre_consumidor) {
        this.nombre_consumidor = nombre_consumidor;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

        

        @Override
        public String toString() {
            CheckNull checknull = new CheckNull();
            JsonObject temp = new JsonObject();
            temp.addProperty("id",checknull.cknull(this.id));
            temp.addProperty("no",checknull.cknull(this.no));
            temp.addProperty("queja_no",checknull.cknull(this.queja_no));
            temp.addProperty("via_de_ingreso",checknull.cknull(this.via_de_ingreso));
            temp.addProperty("nombre_consumidor",checknull.cknull(this.nombre_consumidor));
            temp.addProperty("nombre_proveedor",checknull.cknull(this.nombre_proveedor));
            temp.addProperty("observaciones",checknull.cknull(this.observaciones));
            return temp.toString()+",";
        }
           
}
