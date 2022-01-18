package gt.gob.rgm.sat.ws;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;
import javax.xml.rpc.Stub;

public class ContribuyenteWsEpProxy implements ContribuyenteWsEp {
	private String _endpoint = null;
	private ContribuyenteWsEp contribuyenteWsEp = null;

	public ContribuyenteWsEpProxy() {
		_initContribuyenteWsEpProxy();
	}

	public ContribuyenteWsEpProxy(String endpoint) {
		_endpoint = endpoint;
		_initContribuyenteWsEpProxy();
	}

	private void _initContribuyenteWsEpProxy() {
		try {
			contribuyenteWsEp = (new ConsultaSiafLocator()).getConsultasPublicasPort();
			if (contribuyenteWsEp != null) {
				if (_endpoint != null)
					((Stub) contribuyenteWsEp)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
				else
					_endpoint = (String) ((Stub) contribuyenteWsEp)
							._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		} catch (ServiceException serviceException) {
		}
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
		if (contribuyenteWsEp != null)
			((Stub) contribuyenteWsEp)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

	}

	public ContribuyenteWsEp getContribuyenteWsEp() {
		if (contribuyenteWsEp == null)
			_initContribuyenteWsEpProxy();
		return contribuyenteWsEp;
	}

	public String isAValidFactura(String usuario, String clave, String nit, String numeroSerie, String noDocumento)
			throws RemoteException {
		if (contribuyenteWsEp == null)
			_initContribuyenteWsEpProxy();
		return contribuyenteWsEp.isAValidFactura(usuario, clave, nit, numeroSerie, noDocumento);
	}

	public String buscarRazonDenominacionSocial(String pUsuario, String pClave, String pNombre) throws RemoteException {
		if (contribuyenteWsEp == null)
			_initContribuyenteWsEpProxy();
		return contribuyenteWsEp.buscarRazonDenominacionSocial(pUsuario, pClave, pNombre);
	}

	public String buscarNombreComercial(String pUsuario, String pClave, String pNombreComercial)
			throws RemoteException {
		if (contribuyenteWsEp == null)
			_initContribuyenteWsEpProxy();
		return contribuyenteWsEp.buscarNombreComercial(pUsuario, pClave, pNombreComercial);
	}

	public String findContribuyentesByFechaAdicion(String usuario, String clave, String fecha) throws RemoteException {
		if (contribuyenteWsEp == null)
			_initContribuyenteWsEpProxy();
		return contribuyenteWsEp.findContribuyentesByFechaAdicion(usuario, clave, fecha);
	}

	public String findContribuyentesByFechaModificacion(String usuario, String clave, String fecha)
			throws RemoteException {
		if (contribuyenteWsEp == null)
			_initContribuyenteWsEpProxy();
		return contribuyenteWsEp.findContribuyentesByFechaModificacion(usuario, clave, fecha);
	}

	public String findContribuyenteByNombres(String usuario, String clave, String primer_apellido,
			String segundo_apellido, String apellido_casada, String primer_nombre, String segundo_nombre,
			String fecha_Nacimiento) throws RemoteException {
		if (contribuyenteWsEp == null)
			_initContribuyenteWsEpProxy();
		return contribuyenteWsEp.findContribuyenteByNombres(usuario, clave, primer_apellido, segundo_apellido,
				apellido_casada, primer_nombre, segundo_nombre, fecha_Nacimiento);
	}

	public String listContribuyentesByFecha(String usuario, String clave, String fecha) throws RemoteException {
		if (contribuyenteWsEp == null)
			_initContribuyenteWsEpProxy();
		return contribuyenteWsEp.listContribuyentesByFecha(usuario, clave, fecha);
	}

	public String listContribuyentesByFechaModificacion(String usuario, String clave, String fecha)
			throws RemoteException {
		if (contribuyenteWsEp == null)
			_initContribuyenteWsEpProxy();
		return contribuyenteWsEp.listContribuyentesByFechaModificacion(usuario, clave, fecha);
	}

	public String listContribuyentesByNit(String usuario, String clave, String nit) throws RemoteException {
		if (contribuyenteWsEp == null)
			_initContribuyenteWsEpProxy();
		return contribuyenteWsEp.listContribuyentesByNit(usuario, clave, nit);
	}

	public String listContribuyenteByNombreDicabi(String pUsuario, String pClave, String pPrimerApellido,
			String pSegundoApellido, String pApellidoCasada, String pPrimerNombre, String pSegundoNombre,
			String pFechaNacimiento) throws RemoteException {
		if (contribuyenteWsEp == null)
			_initContribuyenteWsEpProxy();
		return contribuyenteWsEp.listContribuyenteByNombreDicabi(pUsuario, pClave, pPrimerApellido, pSegundoApellido,
				pApellidoCasada, pPrimerNombre, pSegundoNombre, pFechaNacimiento);
	}

	public String listContribuyentesByFechaAdicionDicabi(String pUsuario, String pClave, String pFecha)
			throws RemoteException {
		if (contribuyenteWsEp == null)
			_initContribuyenteWsEpProxy();
		return contribuyenteWsEp.listContribuyentesByFechaAdicionDicabi(pUsuario, pClave, pFecha);
	}

	public String listContribuyentesByFechaModificacionDicabi(String pUsuario, String pClave, String pFecha)
			throws RemoteException {
		if (contribuyenteWsEp == null)
			_initContribuyenteWsEpProxy();
		return contribuyenteWsEp.listContribuyentesByFechaModificacionDicabi(pUsuario, pClave, pFecha);
	}

	public String listContribuyentesByNitDicabi(String pUsuario, String pClave, String pnit) throws RemoteException {
		if (contribuyenteWsEp == null)
			_initContribuyenteWsEpProxy();
		return contribuyenteWsEp.listContribuyentesByNitDicabi(pUsuario, pClave, pnit);
	}

	public String findContribuyenteByNitMFP(String usuario, String clave, String nit) throws RemoteException {
		if (contribuyenteWsEp == null)
			_initContribuyenteWsEpProxy();
		return contribuyenteWsEp.findContribuyenteByNitMFP(usuario, clave, nit);
	}

	public String getNit(String pUsuario, String pContrasenia, String pCui) throws RemoteException {
		if (contribuyenteWsEp == null)
			_initContribuyenteWsEpProxy();
		return contribuyenteWsEp.getNit(pUsuario, pContrasenia, pCui);
	}

	public String catalogo_errores(String pUsuario, String pClave) throws RemoteException {
		if (contribuyenteWsEp == null)
			_initContribuyenteWsEpProxy();
		return contribuyenteWsEp.catalogo_errores(pUsuario, pClave);
	}

	public String findContribuyenteByNitGC(String pUsuario, String pContrasenia, String pNit) throws RemoteException {
		if (contribuyenteWsEp == null)
			_initContribuyenteWsEpProxy();
		return contribuyenteWsEp.findContribuyenteByNitGC(pUsuario, pContrasenia, pNit);
	}

}