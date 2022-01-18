package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import gt.gob.mineco.diaco.model.TipoMotivoPOA;
import gt.gob.mineco.diaco.model.TipoMotivoQuejaFinalizada;
import gt.gob.mineco.diaco.model.TipoPasoQueja;
import gt.gob.mineco.diaco.util.CheckNull;

/**
 * The persistent class for the diaco_queja_finalizada database table.
 * 
 */
@Entity
@Table(name="diaco_queja_finalizada")
@NamedQueries({
    @NamedQuery(
        name = "TipoQuejaFinalizada.findxIdQuejaAct",
        query = "SELECT t FROM TipoQuejaFinalizada t inner join t.tipopasoqueja og where og.id_queja = :idqueja"),
})

public class TipoQuejaFinalizada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_queja_finalizada", precision=0)
        private Integer id_queja_finalizada;
        @Column(name = "id_motivo_queja_finalizada")
        private Integer id_motivo_queja_finalizada;
        @Column(name = "id_motivo_queja_finalizada_poa")
        private Integer id_motivo_queja_finalizada_poa;
        @Column(name = "id_motivo_queja_finalizada_categoria")
        private Integer id_motivo_queja_finalizada_categoria;
        @Column(name = "monto_recuperado", precision=2)
        private double monto_recuperado;
        @Column(name = "detalles")
        private String detalles;
        @Column(name = "conclusion")
        private String conclusion;
        @Column(name = "comentario")
        private String comentario;
        @Column(name = "id_flujo")
        private Integer id_flujo;
	@JoinColumn(name = "id_motivo_queja_finalizada_poa", referencedColumnName = "id_motivo_queja_finalizada_poa",updatable=false, insertable=false)
        @OneToOne
        private TipoMotivoPOA tipoMotivoPOA;        
        @JoinColumn(name = "id_motivo_queja_finalizada", referencedColumnName = "id_motivo_queja_finalizada",updatable=false, insertable=false)
        @OneToOne
        private TipoMotivoQuejaFinalizada tipoMotivoQuejaFinalizada;
        @JoinColumn(name = "id_motivo_queja_finalizada_categoria", referencedColumnName = "id_motivo_queja_finalizada",updatable=false, insertable=false)
        @OneToOne
        private TipoMotivoQuejaFinalizada tipoMotivoQuejaFinalizadacategoria;
        @JoinColumn(name = "id_queja_paso", referencedColumnName = "id_queja_paso")
        @OneToOne
        private TipoPasoQueja tipopasoqueja;
        
	public TipoQuejaFinalizada() {
	}

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getId_flujo() {
        return id_flujo;
    }

    public void setId_flujo(Integer id_flujo) {
        this.id_flujo = id_flujo;
    }

    public Integer getId_queja_finalizada() {
        return id_queja_finalizada;
    }

    public void setId_queja_finalizada(Integer id_queja_finalizada) {
        this.id_queja_finalizada = id_queja_finalizada;
    }

    public Integer getId_motivo_queja_finalizada() {
        return id_motivo_queja_finalizada;
    }

    public void setId_motivo_queja_finalizada(Integer id_motivo_queja_finalizada) {
        this.id_motivo_queja_finalizada = id_motivo_queja_finalizada;
    }

    public Integer getId_motivo_queja_finalizada_poa() {
        return id_motivo_queja_finalizada_poa;
    }

    public void setId_motivo_queja_finalizada_poa(Integer id_motivo_queja_finalizada_poa) {
        this.id_motivo_queja_finalizada_poa = id_motivo_queja_finalizada_poa;
    }

    public Integer getId_motivo_queja_finalizada_categoria() {
        return id_motivo_queja_finalizada_categoria;
    }

    public void setId_motivo_queja_finalizada_categoria(Integer id_motivo_queja_finalizada_categoria) {
        this.id_motivo_queja_finalizada_categoria = id_motivo_queja_finalizada_categoria;
    }

    public double getMonto_recuperado() {
        return monto_recuperado;
    }

    public void setMonto_recuperado(double monto_recuperado) {
        this.monto_recuperado = monto_recuperado;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public TipoMotivoPOA getTipoMotivoPOA() {
        return tipoMotivoPOA;
    }

    public void setTipoMotivoPOA(TipoMotivoPOA tipoMotivoPOA) {
        this.tipoMotivoPOA = tipoMotivoPOA;
    }

    public TipoMotivoQuejaFinalizada getTipoMotivoQuejaFinalizada() {
        return tipoMotivoQuejaFinalizada;
    }

    public void setTipoMotivoQuejaFinalizada(TipoMotivoQuejaFinalizada tipoMotivoQuejaFinalizada) {
        this.tipoMotivoQuejaFinalizada = tipoMotivoQuejaFinalizada;
    }

    public TipoMotivoQuejaFinalizada getTipoMotivoQuejaFinalizadacategoria() {
        return tipoMotivoQuejaFinalizadacategoria;
    }

    public void setTipoMotivoQuejaFinalizadacategoria(TipoMotivoQuejaFinalizada tipoMotivoQuejaFinalizadacategoria) {
        this.tipoMotivoQuejaFinalizadacategoria = tipoMotivoQuejaFinalizadacategoria;
    }

    public TipoPasoQueja getTipopasoqueja() {
        return tipopasoqueja;
    }

    public void setTipopasoqueja(TipoPasoQueja tipopasoqueja) {
        this.tipopasoqueja = tipopasoqueja;
    }

        
        
        @Override
        public String toString() {
            CheckNull checknull=new CheckNull();
            JsonObject temp = new JsonObject();
            temp.addProperty("id_queja_finalizada",checknull.cknull(this.id_queja_finalizada));
            temp.addProperty("id_motivo_queja_finalizada",checknull.cknull(this.id_motivo_queja_finalizada));
            temp.addProperty("id_motivo_queja_finalizada_poa",checknull.cknull(this.id_motivo_queja_finalizada_poa));
            temp.addProperty("id_motivo_queja_finalizada_categoria",checknull.cknull(this.id_motivo_queja_finalizada_categoria));
            temp.addProperty("monto_recuperado",checknull.cknull(this.monto_recuperado));
            temp.addProperty("detalles",checknull.cknull(this.detalles));
            temp.addProperty("conclusion",checknull.cknull(this.conclusion));
            temp.addProperty("comentario",checknull.cknull(this.comentario));
            if(this.tipoMotivoQuejaFinalizada != null)
                temp.addProperty("motivo_queja_finalizada",this.tipoMotivoQuejaFinalizada.getDescripcion());
            if(this.tipoMotivoPOA != null)
                temp.addProperty("motivo_queja_finalizada_poa",this.tipoMotivoPOA.getDescripcion());
            if(this.tipoMotivoQuejaFinalizadacategoria != null)
                temp.addProperty("motivo_queja_finalizada_categoria",this.tipoMotivoQuejaFinalizadacategoria.getDescripcion());
            return temp.toString()+",";
        }
           
}
