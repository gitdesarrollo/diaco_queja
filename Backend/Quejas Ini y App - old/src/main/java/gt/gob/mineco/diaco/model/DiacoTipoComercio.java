package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="diaco_tipo_comercio")
@NamedQueries({
	@NamedQuery(name="DiacoTipoComercio.findAll", query="SELECT t FROM DiacoTipoComercio t"),
	@NamedQuery(name="DiacoTipoComercio.findByActividadEconomica", query="SELECT t FROM DiacoTipoComercio t WHERE t.idActividadEconomica = :actividadEconomica")
})
public class DiacoTipoComercio implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_tipo_comercio")
	private Integer idTipoComercio;
	
	@Column(name="id_actividad_economica")
	private Integer idActividadEconomica;
	
	@Column(name="tipo_comercio")
	private String tipoComercio;
	
	private String habilitado;
	
	@Column(name="usuario_adiciono")
	private String usuarioAdiciono;
	
	@Column(name="fecha_adicion")
	private Timestamp fechaAdicion;
	
	@Column(name="usuario_modifico")
	private String usuarioModifico;
	
	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;
	
	public DiacoTipoComercio() {
	}

	public Integer getIdTipoComercio() {
		return idTipoComercio;
	}

	public void setIdTipoComercio(Integer idTipoComercio) {
		this.idTipoComercio = idTipoComercio;
	}

	public Integer getIdActividadEconomica() {
		return idActividadEconomica;
	}

	public void setIdActividadEconomica(Integer idActividadEconomica) {
		this.idActividadEconomica = idActividadEconomica;
	}

	public String getTipoComercio() {
		return tipoComercio;
	}

	public void setTipoComercio(String tipoComercio) {
		this.tipoComercio = tipoComercio;
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
