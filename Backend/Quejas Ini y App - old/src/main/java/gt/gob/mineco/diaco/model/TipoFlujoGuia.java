package gt.gob.mineco.diaco.model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the diaco_flujo_guia database table.
 * 
 */
@Entity
@Table(name="diaco_flujo_guia")
@NamedQuery(name = "TipoFlujoGuia.findAll", query = "SELECT t FROM TipoFlujoGuia t where t.id_queja = :id_queja")
public class TipoFlujoGuia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_queja", precision=0)
        private Integer id_queja;
        @Column(name = "atcon_conc_previa", columnDefinition="BIT")
        private boolean atcon_conc_previa;
        @Column(name = "atcon_verif_datos", columnDefinition="BIT")
        private boolean atcon_verif_datos;
        @Column(name = "atcon_com_permanente", columnDefinition="BIT")
        private boolean atcon_com_permanente;
        @Column(name = "atcon_audiencia_conc", columnDefinition="BIT")
        private boolean atcon_audiencia_conc;
        @Column(name = "atcon_audiencia_prueba", columnDefinition="BIT")
        private boolean atcon_audiencia_prueba;
        
	public TipoFlujoGuia() {
	}

    public Integer getIdqueja() {
        return id_queja;
    }

    public void setIdqueja(Integer idqueja) {
        this.id_queja = idqueja;
    }

    public boolean getAtcon_conc_previa() {
        return atcon_conc_previa;
    }

    public void setAtcon_conc_previa(boolean atcon_conc_previa) {
        this.atcon_conc_previa = atcon_conc_previa;
    }

    public boolean getAtcon_verif_datos() {
        return atcon_verif_datos;
    }

    public void setAtcon_verif_datos(boolean atcon_verif_datos) {
        this.atcon_verif_datos = atcon_verif_datos;
    }

    public boolean getAtcon_com_permanente() {
        return atcon_com_permanente;
    }

    public void setAtcon_com_permanente(boolean atcon_com_permanente) {
        this.atcon_com_permanente = atcon_com_permanente;
    }

    public boolean getAtcon_audiencia_conc() {
        return atcon_audiencia_conc;
    }

    public void setAtcon_audiencia_conc(boolean atcon_audiencia_conc) {
        this.atcon_audiencia_conc = atcon_audiencia_conc;
    }

    public boolean getAtcon_audiencia_prueba() {
        return atcon_audiencia_prueba;
    }

    public void setAtcon_audiencia_prueba(boolean atcon_audiencia_prueba) {
        this.atcon_audiencia_prueba = atcon_audiencia_prueba;
    }

        
        
        @Override
        public String toString() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_queja",this.id_queja);
            temp.addProperty("atcon_conc_previa",this.atcon_conc_previa);
            temp.addProperty("atcon_verif_datos",this.atcon_verif_datos);
            temp.addProperty("atcon_com_permanente",this.atcon_com_permanente);
            temp.addProperty("atcon_audiencia_conc",this.atcon_audiencia_conc);
            temp.addProperty("atcon_audiencia_prueba",this.atcon_audiencia_prueba);
            return temp.toString()+",";
        }
   
        public JsonObject toJsonelement() {
            JsonObject temp = new JsonObject();
            temp.addProperty("id_queja",this.id_queja);
            temp.addProperty("atcon_conc_previa",this.atcon_conc_previa);
            temp.addProperty("atcon_verif_datos",this.atcon_verif_datos);
            temp.addProperty("atcon_com_permanente",this.atcon_com_permanente);
            temp.addProperty("atcon_audiencia_conc",this.atcon_audiencia_conc);
            temp.addProperty("atcon_audiencia_prueba",this.atcon_audiencia_prueba);
            return temp;
        }
        
}
