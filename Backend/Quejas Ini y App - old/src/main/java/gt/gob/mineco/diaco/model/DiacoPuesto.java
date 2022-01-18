package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the diaco_puesto database table.
 * 
 */
@Entity
@Table(name="diaco_puesto")
@NamedQuery(name="DiacoPuesto.findAll", query="SELECT d FROM DiacoPuesto d")
public class DiacoPuesto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_puesto")
	private int idPuesto;

	@Column(name="fecha_adicion")
	private Timestamp fechaAdicion;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	private String habilitado;

	private String puesto;

	@Column(name="usuario_adiciono")
	private String usuarioAdiciono;

	@Column(name="usuario_modifico")
	private String usuarioModifico;

	public DiacoPuesto() {
	}

	public int getIdPuesto() {
		return this.idPuesto;
	}

	public void setIdPuesto(int idPuesto) {
		this.idPuesto = idPuesto;
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

	public String getPuesto() {
		return this.puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
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

}