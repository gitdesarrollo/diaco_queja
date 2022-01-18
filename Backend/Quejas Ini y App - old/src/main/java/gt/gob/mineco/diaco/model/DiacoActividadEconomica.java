package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the diaco_actividad_economica database table.
 * 
 */
@Entity
@Table(name="diaco_actividad_economica")
@NamedQuery(name="DiacoActividadEconomica.findAll", query="SELECT d FROM DiacoActividadEconomica d")
public class DiacoActividadEconomica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_actividad_economica")
	private int idActividadEconomica;

	@Column(name="fecha_adicion")
	private Timestamp fechaAdicion;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	private String habilitado;

	@Column(name="nombre_actividad_economica")
	private String nombreActividadEconomica;

	@Column(name="usuario_adiciono")
	private String usuarioAdiciono;

	@Column(name="usuario_modifico")
	private String usuarioModifico;

	public DiacoActividadEconomica() {
	}

	public int getIdActividadEconomica() {
		return this.idActividadEconomica;
	}

	public void setIdActividadEconomica(int idActividadEconomica) {
		this.idActividadEconomica = idActividadEconomica;
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

	public String getNombreActividadEconomica() {
		return this.nombreActividadEconomica;
	}

	public void setNombreActividadEconomica(String nombreActividadEconomica) {
		this.nombreActividadEconomica = nombreActividadEconomica;
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