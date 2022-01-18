package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="diaco_estado_queja")
@NamedQuery(name="DiacoEstadoQueja.findAll", query="SELECT e FROM DiacoEstadoQueja e")
public class DiacoEstadoQueja implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_estado_queja")
	private Integer idEstadoQueja;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="flujo_id")
	private Integer flujoId;
	
	@Column(name="usuario_adiciono")
	private String usuarioAdiciono;
	
	@Column(name="fecha_adicion")
	private Timestamp fechaAdicion;
	
	public DiacoEstadoQueja() {
	}

	public Integer getIdEstadoQueja() {
		return idEstadoQueja;
	}

	public void setIdEstadoQueja(Integer idEstadoQueja) {
		this.idEstadoQueja = idEstadoQueja;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getFlujoId() {
		return flujoId;
	}

	public void setFlujoId(Integer flujoId) {
		this.flujoId = flujoId;
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
