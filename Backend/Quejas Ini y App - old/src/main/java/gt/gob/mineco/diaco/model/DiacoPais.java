package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the diaco_paises database table.
 * 
 */
@Entity
@Table(name="diaco_paises")
@NamedQuery(name="DiacoPais.findAll", query="SELECT d FROM DiacoPais d")
public class DiacoPais implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_pais")
	private int idPais;

	@Column(name="fecha_adicion")
	private Timestamp fechaAdicion;

	@Column(name="nombre_pais")
	private String nombrePais;

	public DiacoPais() {
	}

	public int getIdPais() {
		return this.idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}

	public Timestamp getFechaAdicion() {
		return this.fechaAdicion;
	}

	public void setFechaAdicion(Timestamp fechaAdicion) {
		this.fechaAdicion = fechaAdicion;
	}

	public String getNombrePais() {
		return this.nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

}