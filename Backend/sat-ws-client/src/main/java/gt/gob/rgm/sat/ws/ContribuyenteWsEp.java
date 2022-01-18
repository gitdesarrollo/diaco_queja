/**
 * ContribuyenteWsEp.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gt.gob.rgm.sat.ws;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ContribuyenteWsEp extends Remote {
	public String isAValidFactura(String usuario, String clave, String nit, String numeroSerie, String noDocumento)
			throws RemoteException;

	public String buscarRazonDenominacionSocial(String pUsuario, String pClave, String pNombre) throws RemoteException;

	public String buscarNombreComercial(String pUsuario, String pClave, String pNombreComercial) throws RemoteException;

	public String findContribuyentesByFechaAdicion(String usuario, String clave, String fecha) throws RemoteException;

	public String findContribuyentesByFechaModificacion(String usuario, String clave, String fecha)
			throws RemoteException;

	public String findContribuyenteByNombres(String usuario, String clave, String primer_apellido,
			String segundo_apellido, String apellido_casada, String primer_nombre, String segundo_nombre,
			String fecha_Nacimiento) throws RemoteException;

	public String listContribuyentesByFecha(String usuario, String clave, String fecha) throws RemoteException;

	public String listContribuyentesByFechaModificacion(String usuario, String clave, String fecha)
			throws RemoteException;

	public String listContribuyentesByNit(String usuario, String clave, String nit) throws RemoteException;

	public String listContribuyenteByNombreDicabi(String pUsuario, String pClave, String pPrimerApellido,
			String pSegundoApellido, String pApellidoCasada, String pPrimerNombre, String pSegundoNombre,
			String pFechaNacimiento) throws RemoteException;

	public String listContribuyentesByFechaAdicionDicabi(String pUsuario, String pClave, String pFecha)
			throws RemoteException;

	public String listContribuyentesByFechaModificacionDicabi(String pUsuario, String pClave, String pFecha)
			throws RemoteException;

	public String listContribuyentesByNitDicabi(String pUsuario, String pClave, String pnit) throws RemoteException;

	public String findContribuyenteByNitMFP(String usuario, String clave, String nit) throws RemoteException;

	public String getNit(String pUsuario, String pContrasenia, String pCui) throws RemoteException;

	public String catalogo_errores(String pUsuario, String pClave) throws RemoteException;

	public String findContribuyenteByNitGC(String pUsuario, String pContrasenia, String pNit) throws RemoteException;
}
