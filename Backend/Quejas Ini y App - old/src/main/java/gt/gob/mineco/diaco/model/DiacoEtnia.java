package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the diaco_etnia database table.
 * 
 */
@Entity
@Table(name="diaco_etnia")
@NamedQuery(name="DiacoEtnia.findAll", query="SELECT d FROM DiacoEtnia d")
public class DiacoEtnia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_etnia")
	private int codigoEtnia;

	private String descripcion;

	public DiacoEtnia() {
	}

	public int getCodigoEtnia() {
		return this.codigoEtnia;
	}

	public void setCodigoEtnia(int codigoEtnia) {
		this.codigoEtnia = codigoEtnia;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}