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
        @Column(name = "vyv_flag", columnDefinition="BIT")
        private boolean vyv_flag;
        @Column(name = "vyv_informe_final", columnDefinition="BIT")
        private boolean vyv_informe_final;
        @Column(name = "jur_flag", columnDefinition="BIT")
        private boolean jur_flag;
        @Column(name = "jur_documentado", columnDefinition="BIT")
        private boolean jur_documentado;
        @Column(name = "jur_result_audiencia", columnDefinition="BIT")
        private boolean jur_result_audiencia;
        @Column(name = "jur_resol_final", columnDefinition="BIT")
        private boolean jur_resol_final;
        @Column(name = "jur_cobro_sancion", columnDefinition="BIT")
        private boolean jur_cobro_sancion;
        @Column(name = "sp_flag", columnDefinition="BIT")
        private boolean sp_flag;
        
	public TipoFlujoGuia() {
	}

    public boolean isSp_flag() {
        return sp_flag;
    }

    public void setSp_flag(boolean sp_flag) {
        this.sp_flag = sp_flag;
    }

    public boolean getVyv_flag() {
        return vyv_flag;
    }

    public void setVyv_flag(boolean vyv_flag) {
        this.vyv_flag = vyv_flag;
    }

    public boolean getVyv_informe_final() {
        return vyv_informe_final;
    }

    public void setVyv_informe_final(boolean vyv_informe_final) {
        this.vyv_informe_final = vyv_informe_final;
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

    public boolean getJur_flag() {
        return jur_flag;
    }

    public void setJur_flag(boolean jur_flag) {
        this.jur_flag = jur_flag;
    }

    public boolean getJur_documentado() {
        return jur_documentado;
    }

    public void setJur_documentado(boolean jur_documentado) {
        this.jur_documentado = jur_documentado;
    }

    public boolean getJur_result_audiencia() {
        return jur_result_audiencia;
    }

    public void setJur_result_audiencia(boolean jur_result_audiencia) {
        this.jur_result_audiencia = jur_result_audiencia;
    }

    public boolean getJur_resol_final() {
        return jur_resol_final;
    }

    public void setJur_resol_final(boolean jur_resol_final) {
        this.jur_resol_final = jur_resol_final;
    }

    public boolean getJur_cobro_sancion() {
        return jur_cobro_sancion;
    }

    public void setJur_cobro_sancion(boolean jur_cobro_sancion) {
        this.jur_cobro_sancion = jur_cobro_sancion;
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
            temp.addProperty("vyv_flag",this.vyv_flag);
            temp.addProperty("vyv_informe_final",this.vyv_informe_final);
            temp.addProperty("jur_flag",this.jur_flag);
            temp.addProperty("jur_documentado",this.jur_documentado);
            temp.addProperty("jur_result_audiencia",this.jur_result_audiencia);
            temp.addProperty("jur_resol_final",this.jur_resol_final);
            temp.addProperty("jur_cobro_sancion",this.jur_cobro_sancion);
            temp.addProperty("sp_flag",this.sp_flag);
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
            temp.addProperty("vyv_flag",this.vyv_flag);
            temp.addProperty("vyv_informe_final",this.vyv_informe_final);
            temp.addProperty("jur_flag",this.jur_flag);
            temp.addProperty("jur_documentado",this.jur_documentado);
            temp.addProperty("jur_result_audiencia",this.jur_result_audiencia);
            temp.addProperty("jur_resol_final",this.jur_resol_final);
            temp.addProperty("jur_cobro_sancion",this.jur_cobro_sancion);
            temp.addProperty("sp_flag",this.sp_flag);
            return temp;
        }
        
}
