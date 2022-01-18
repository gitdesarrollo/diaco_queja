package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="diaco_razon_final_cons")
@NamedQuery(name="DiacoRazonFinalCons.findAll", query="SELECT r FROM DiacoRazonFinalCons r")
public class DiacoRazonFinalCons implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_razon_final")
	private Integer idRazonFinal;
	
	@Column(name="id_atencion_consumidor")
	private Integer idAtencionConsumidor;
	
	private String categoria;
	
	private String habilitado;
	
	@Column(name="usuario_adiciono")
	private String usuarioAdiciono;
	
	@Column(name="fecha_adicion")
	private Timestamp fechaAdicion;
	
	@Column(name="usuario_modifico")
	private String usuarioModifico;
	
	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;
	
	public DiacoRazonFinalCons() {
	}

	public Integer getIdRazonFinal() {
		return idRazonFinal;
	}

	public void setIdRazonFinal(Integer idRazonFinal) {
		this.idRazonFinal = idRazonFinal;
	}

	public Integer getIdAtencionConsumidor() {
		return idAtencionConsumidor;
	}

	public void setIdAtencionConsumidor(Integer idAtencionConsumidor) {
		this.idAtencionConsumidor = idAtencionConsumidor;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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
