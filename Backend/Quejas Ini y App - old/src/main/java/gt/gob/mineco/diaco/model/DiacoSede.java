package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the diaco_sede database table.
 * 
 */
@Entity
@Table(name="diaco_sede")
@NamedQueries({
	@NamedQuery(name="DiacoSede.findAll", query="SELECT d FROM DiacoSede d"),
	@NamedQuery(name="DiacoSede.findByMunicipio", query="SELECT d FROM DiacoSede d WHERE d.municipio.codigoMunicipio = :municipio")
})
public class DiacoSede implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_diaco_sede")
	private int idDiacoSede;

	@Column(name="direccion_sede")
	private String direccionSede;

	@Column(name="fecha_adicion")
	private Timestamp fechaAdicion;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	private String habilitado;

	@Column(name="nombre_sede")
	private String nombreSede;

	@Column(name="usuario_adiciono")
	private String usuarioAdiciono;

	@Column(name="usuario_modifico")
	private String usuarioModifico;

	//bi-directional many-to-one association to Municipio
	@ManyToOne
	@JoinColumn(name="codigo_municipio")
	private Municipio municipio;

	public DiacoSede() {
	}

	public int getIdDiacoSede() {
		return this.idDiacoSede;
	}

	public void setIdDiacoSede(int idDiacoSede) {
		this.idDiacoSede = idDiacoSede;
	}

	public String getDireccionSede() {
		return this.direccionSede;
	}

	public void setDireccionSede(String direccionSede) {
		this.direccionSede = direccionSede;
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

	public String getHabilitado() {
		return this.habilitado;
	}

	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

	public String getNombreSede() {
		return this.nombreSede;
	}

	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
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

	public Municipio getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

}