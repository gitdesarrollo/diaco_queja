package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the diaco_aten_consumidor database table.
 * 
 */
@Entity
@Table(name="diaco_aten_consumidor")
@NamedQuery(name="DiacoAtenConsumidor.findAll", query="SELECT d FROM DiacoAtenConsumidor d")
public class DiacoAtenConsumidor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_atencion_consumidor")
	private int idAtencionConsumidor;

	@Column(name="fecha_adicion")
	private Timestamp fechaAdicion;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	private String habilitado;

	private String razon;

	@Column(name="usuario_adiciono")
	private String usuarioAdiciono;

	@Column(name="usuario_modifico")
	private String usuarioModifico;

	public DiacoAtenConsumidor() {
	}

	public int getIdAtencionConsumidor() {
		return this.idAtencionConsumidor;
	}

	public void setIdAtencionConsumidor(int idAtencionConsumidor) {
		this.idAtencionConsumidor = idAtencionConsumidor;
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

	public String getRazon() {
		return this.razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
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