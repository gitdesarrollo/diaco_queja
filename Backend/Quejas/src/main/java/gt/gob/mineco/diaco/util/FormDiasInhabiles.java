
package gt.gob.mineco.diaco.util;



import java.io.Serializable;

public class FormDiasInhabiles implements Serializable {
  private Integer dia;
  private Integer mes;
  private String descripcion;
  private Integer idDepartamento;
  private String nombreDepartamento;
  private Integer idDiaInabil;
  private Integer idDiaInabilDepto;

    public Integer getIdDiaInabilDepto() {
        return idDiaInabilDepto;
    }

    public void setIdDiaInabilDepto(Integer idDiaInabilDepto) {
        this.idDiaInabilDepto = idDiaInabilDepto;
    }

    public Integer getIdDiaInabil() {
        return idDiaInabil;
    }

    public void setIdDiaInabil(Integer idDiaInabil) {
        this.idDiaInabil = idDiaInabil;
    }
  
    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }
  
    
}
