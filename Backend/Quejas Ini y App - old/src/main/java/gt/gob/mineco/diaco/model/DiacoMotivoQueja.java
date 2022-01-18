package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;


/**
 * The persistent class for the diaco_motivo_queja database table.
 * 
 */
@Entity
@Table(name="diaco_motivo_queja")
@NamedQuery(name="DiacoMotivoQueja.findAll", query="SELECT d FROM DiacoMotivoQueja d WHERE d.habilitado = '1'")
public class DiacoMotivoQueja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_motivo_queja")
	private int idMotivoQueja;

	private String motivo;
	
	@Column(name="habilitado")
	private String habilitado;
	
	@Column(name="usuario_adiciono")
	private String usuarioAdiciono;
	
	@Column(name="fecha_adicion")
	private Timestamp fechaAdicion;
	
	@Column(name="usuario_modifico")
	private String usuarioModifico;
	
	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	public DiacoMotivoQueja() {
	}

	public int getIdMotivoQueja() {
		return idMotivoQueja;
	}

	public void setIdMotivoQueja(int idMotivoQueja) {
		this.idMotivoQueja = idMotivoQueja;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
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

	public String getUsuarioModifico() {
		return usuarioModifico;
	}

	public void setUsuarioModifico(String usuarioModifico) {
		this.usuarioModifico = usuarioModifico;
	}

	public Timestamp getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
}