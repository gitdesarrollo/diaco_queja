package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="diaco_email")
@NamedQuery(name="DiacoEmail.findAll", query="SELECT e FROM DiacoEmail e")
public class DiacoEmail implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_mail")
	private Integer idMail;
	
	@Column(name="id_consumidor")
	private Integer idConsumidor;
	
	@Column(name="id_proveedor")
	private Integer idProveedor;
	
	@Column(name="id_representante_legal")
	private Integer idRepresentanteLegal;
	
	@Column(name="correo_electronico")
	private String correoElectronico;
	
	@Column(name="usuario_adiciono")
	private String usuarioAdiciono;
	
	@Column(name="fecha_adicion")
	private Timestamp fechaAdicion;
	
	@Column(name="usuario_modifico")
	private String usuarioModifico;
	
	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;
	
	public DiacoEmail() {
	}

	public Integer getIdMail() {
		return idMail;
	}

	public void setIdMail(Integer idMail) {
		this.idMail = idMail;
	}

	public Integer getIdConsumidor() {
		return idConsumidor;
	}

	public void setIdConsumidor(Integer idConsumidor) {
		this.idConsumidor = idConsumidor;
	}

	public Integer getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}

	public Integer getIdRepresentanteLegal() {
		return idRepresentanteLegal;
	}

	public void setIdRepresentanteLegal(Integer idRepresentanteLegal) {
		this.idRepresentanteLegal = idRepresentanteLegal;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
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
