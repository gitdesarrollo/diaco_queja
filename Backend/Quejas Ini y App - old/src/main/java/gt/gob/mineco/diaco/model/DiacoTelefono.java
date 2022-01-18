package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="diaco_telefono")
@NamedQuery(name="DiacoTelefono.findAll", query="SELECT t FROM DiacoTelefono t")
public class DiacoTelefono implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_telefono")
	private Integer idTelefono;
	
	@Column(name="id_consumidor")
	private Integer idConsumidor;
	
	@Column(name="id_proveedor")
	private Integer idProveedor;
	
	@Column(name="id_proveedor_sucursal")
	private Integer idProveedorSucursal;
	
	@Column(name="id_representante_legal")
	private Integer idRepresentanteLegal;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="tipo_telefono")
	private Integer tipoTelefono;
	
	@Column(name="usuario_adiciono")
	private String usuarioAdiciono;
	
	@Column(name="fecha_adicion")
	private Timestamp fechaAdicion;
	
	@Column(name="usuario_modifico")
	private String usuarioModifico;
	
	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;
	
	public DiacoTelefono() {
	}

	public Integer getIdTelefono() {
		return idTelefono;
	}

	public void setIdTelefono(Integer idTelefono) {
		this.idTelefono = idTelefono;
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

	public Integer getIdProveedorSucursal() {
		return idProveedorSucursal;
	}

	public void setIdProveedorSucursal(Integer idProveedorSucursal) {
		this.idProveedorSucursal = idProveedorSucursal;
	}

	public Integer getIdRepresentanteLegal() {
		return idRepresentanteLegal;
	}

	public void setIdRepresentanteLegal(Integer idRepresentanteLegal) {
		this.idRepresentanteLegal = idRepresentanteLegal;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getTipoTelefono() {
		return tipoTelefono;
	}

	public void setTipoTelefono(Integer tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
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
