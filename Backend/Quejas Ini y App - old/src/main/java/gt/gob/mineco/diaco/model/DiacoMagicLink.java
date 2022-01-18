package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import gt.gob.mineco.diaco.util.CheckNull;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
/**
 * The persistent class for the diaco_magiclink database view.
 * 
 */
@Entity
@Table(name="diaco_magiclink")
@NamedQueries({
@NamedQuery(name="DiacoMagicLink.findAll", query="SELECT t FROM DiacoMagicLink t where t.linkcode = :linkcode and t.estado = 'A' order by t.id desc"),
@NamedQuery(name="DiacoMagicLink.findLastxIDQueja", query="SELECT t FROM DiacoMagicLink t where t.id_queja = :id_queja and t.id_fuente = :id_fuente and t.estado = 'A' order by t.id desc")
})
public class DiacoMagicLink implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", precision=0) 
	private Integer id;
	private String linkcode;
        private String pagina;
        private Integer id_queja;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name="fecha_enviado")
        private Date fecha_enviado; 
        private String estado;
        private Integer id_fuente;
        private Integer id_referencia;
        @OneToOne
        @JoinColumn(name = "id_fuente", referencedColumnName = "id_fuente",updatable=false, insertable=false)
        private TipoEmailFuente tipoemailfuente;

        
    public DiacoMagicLink(){}    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLinkcode() {
        return linkcode;
    }

    public void setLinkcode(String linkcode) {
        this.linkcode = linkcode;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public Integer getId_queja() {
        return id_queja;
    }

    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
    }

    public Date getFecha_enviado() {
        return fecha_enviado;
    }

    public void setFecha_enviado(Date fecha_enviado) {
        this.fecha_enviado = fecha_enviado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getId_fuente() {
        return id_fuente;
    }

    public void setId_fuente(Integer id_fuente) {
        this.id_fuente = id_fuente;
    }

    public Integer getId_referencia() {
        return id_referencia;
    }

    public void setId_referencia(Integer id_referencia) {
        this.id_referencia = id_referencia;
    }
        
    @Override
    public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id",this.id);
            temp.addProperty("linkcode",this.linkcode);
            if(this.tipoemailfuente!=null)
                temp.addProperty("direccion",this.tipoemailfuente.getDireccion());
            if(this.id_referencia!=null)
                temp.addProperty("referencia",this.id_referencia);
            if(this.id_fuente!=null)
                temp.addProperty("id_fuente",this.id_fuente);
            return temp.toString()+",";
    }

    
}
