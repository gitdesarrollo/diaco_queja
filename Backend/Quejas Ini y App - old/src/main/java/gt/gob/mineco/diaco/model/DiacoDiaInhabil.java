package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the diaco_dia_inhabil database table.
 * 
 */
@Entity
@Table(name="diaco_dia_inhabil")
@NamedQuery(name="DiacoDiaInhabil.findAll", query="SELECT d FROM DiacoDiaInhabil d")
public class DiacoDiaInhabil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_dia_inhabil")
	private int idDiaInhabil;

	private String descripcion;

	private Timestamp dia;

	@Column(name="fecha_adicion")
	private Timestamp fechaAdicion;

	@Column(name="usuario_adiciono")
	private String usuarioAdiciono;

	public DiacoDiaInhabil() {
	}

	public int getIdDiaInhabil() {
		return this.idDiaInhabil;
	}

	public void setIdDiaInhabil(int idDiaInhabil) {
		this.idDiaInhabil = idDiaInhabil;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getDia() {
		return this.dia;
	}

	public void setDia(Timestamp dia) {
		this.dia = dia;
	}

	public Timestamp getFechaAdicion() {
		return this.fechaAdicion;
	}

	public void setFechaAdicion(Timestamp fechaAdicion) {
		this.fechaAdicion = fechaAdicion;
	}

	public String getUsuarioAdiciono() {
		return this.usuarioAdiciono;
	}

	public void setUsuarioAdiciono(String usuarioAdiciono) {
		this.usuarioAdiciono = usuarioAdiciono;
	}

}