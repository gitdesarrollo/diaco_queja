package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="diaco_dia_inhabil_x_dept")
@NamedQuery(name="DiacoDiaInhabilXDept.findAll", query="SELECT d FROM DiacoDiaInhabilXDept d")
public class DiacoDiaInhabilXDept implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_dia_inhabil_x_dept")
	private Integer idDiaInhabilXDept;
	
	@Column(name="id_dia_inhabil")
	private Integer idDiaInhabil;
	
	@Column(name="id_departamento")
	private Integer idDepartamento;
	
	@Column(name="usuario_adiciono")
	private String usuarioAdiciono;
	
	@Column(name="fecha_adicion")
	private Timestamp fechaAdicion;
	
	public DiacoDiaInhabilXDept() {
	}

	public Integer getIdDiaInhabilXDept() {
		return idDiaInhabilXDept;
	}

	public void setIdDiaInhabilXDept(Integer idDiaInhabilXDept) {
		this.idDiaInhabilXDept = idDiaInhabilXDept;
	}

	public Integer getIdDiaInhabil() {
		return idDiaInhabil;
	}

	public void setIdDiaInhabil(Integer idDiaInhabil) {
		this.idDiaInhabil = idDiaInhabil;
	}

	public Integer getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Integer idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getUsuarioAdiciono() {
		return usuarioAdiciono;
	}

	public void setUsuarioAdiciono(String usuarioAdiciono) {
		this.usuarioAdiciono = usuarioAdiciono;
	}

	public Timestamp getFechaAdicion() {
		return fechaAdicion;
	}

	public void setFechaAdicion(Timestamp fechaAdicion) {
		this.fechaAdicion = fechaAdicion;
	}
}
