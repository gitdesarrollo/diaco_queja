package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the municipio database table.
 * 
 */
@Entity
@Table(name="municipio")
@NamedQueries({
	@NamedQuery(name="Municipio.findAll", query="SELECT m FROM Municipio m"),
	@NamedQuery(name="Municipio.findByDepartamento", query="SELECT m FROM Municipio m WHERE m.codigoDepartamento = :departamento ORDER BY m.nombreMunicipio")
})
public class Municipio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_municipio")
	private int codigoMunicipio;

	@Column(name="codigo_departamento")
	private int codigoDepartamento;

	@Column(name="fecha_adicion")
	private Timestamp fechaAdicion;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	@Column(name="nombre_municipio")
	private String nombreMunicipio;

	@Column(name="usuario_adiciono")
	private String usuarioAdiciono;

	@Column(name="usuario_modifico")
	private String usuarioModifico;

	//bi-directional many-to-one association to DiacoSede
	/*@OneToMany(mappedBy="municipio")
	private List<DiacoSede> diacoSedes;*/

	public Municipio() {
	}

	public int getCodigoMunicipio() {
		return this.codigoMunicipio;
	}

	public void setCodigoMunicipio(int codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	public int getCodigoDepartamento() {
		return this.codigoDepartamento;
	}

	public void setCodigoDepartamento(int codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	public Timestamp getFechaAdicion() {
		return this.fechaAdicion;
	}

	public void setFechaAdicion(Timestamp fechaAdicion) {
		this.fechaAdicion = fechaAdicion;
	}

	public Timestamp getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getNombreMunicipio() {
		return this.nombreMunicipio;
	}

	public void setNombreMunicipio(String nombreMunicipio) {
		this.nombreMunicipio = nombreMunicipio;
	}

	public String getUsuarioAdiciono() {
		return this.usuarioAdiciono;
	}

	public void setUsuarioAdiciono(String usuarioAdiciono) {
		this.usuarioAdiciono = usuarioAdiciono;
	}

	public String getUsuarioModifico() {
		return this.usuarioModifico;
	}

	public void setUsuarioModifico(String usuarioModifico) {
		this.usuarioModifico = usuarioModifico;
	}

	/*public List<DiacoSede> getDiacoSedes() {
		return this.diacoSedes;
	}

	public void setDiacoSedes(List<DiacoSede> diacoSedes) {
		this.diacoSedes = diacoSedes;
	}

	public DiacoSede addDiacoSede(DiacoSede diacoSede) {
		getDiacoSedes().add(diacoSede);
		diacoSede.setMunicipio(this);

		return diacoSede;
	}

	public DiacoSede removeDiacoSede(DiacoSede diacoSede) {
		getDiacoSedes().remove(diacoSede);
		diacoSede.setMunicipio(null);

		return diacoSede;
	}*/

}