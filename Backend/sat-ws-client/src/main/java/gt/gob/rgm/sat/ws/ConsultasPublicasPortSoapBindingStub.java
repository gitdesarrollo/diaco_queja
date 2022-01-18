/**
 * ConsultasPublicasPortSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gt.gob.rgm.sat.ws;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Enumeration;

import javax.xml.namespace.QName;
import javax.xml.rpc.Service;

import org.apache.axis.AxisFault;
import org.apache.axis.NoEndPointException;
import org.apache.axis.client.Call;
import org.apache.axis.client.Stub;
import org.apache.axis.constants.Style;
import org.apache.axis.constants.Use;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.soap.SOAPConstants;
import org.apache.axis.utils.JavaUtils;

public class ConsultasPublicasPortSoapBindingStub extends Stub implements ContribuyenteWsEp {
	private java.util.Vector cachedSerClasses = new java.util.Vector();
	private java.util.Vector cachedSerQNames = new java.util.Vector();
	private java.util.Vector cachedSerFactories = new java.util.Vector();
	private java.util.Vector cachedDeserFactories = new java.util.Vector();

	static OperationDesc[] _operations;

	static {
		_operations = new OperationDesc[17];
		_initOperationDesc1();
		_initOperationDesc2();
	}

	private static void _initOperationDesc1() {
		OperationDesc oper;
		ParameterDesc param;
		oper = new OperationDesc();
		oper.setName("isAValidFactura");
		param = new ParameterDesc(new QName("", "usuario"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "clave"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "nit"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "numeroSerie"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "noDocumento"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new QName("", "isAValidFacturaReturn"));
		oper.setStyle(Style.RPC);
		oper.setUse(Use.ENCODED);
		_operations[0] = oper;

		oper = new OperationDesc();
		oper.setName("buscarRazonDenominacionSocial");
		param = new ParameterDesc(new QName("", "pUsuario"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "pClave"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "pNombre"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new QName("", "buscarRazonDenominacionSocialReturn"));
		oper.setStyle(Style.RPC);
		oper.setUse(Use.ENCODED);
		_operations[1] = oper;

		oper = new OperationDesc();
		oper.setName("buscarNombreComercial");
		param = new ParameterDesc(new QName("", "pUsuario"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "pClave"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "pNombreComercial"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new QName("", "buscarNombreComercialReturn"));
		oper.setStyle(Style.RPC);
		oper.setUse(Use.ENCODED);
		_operations[2] = oper;

		oper = new OperationDesc();
		oper.setName("findContribuyentesByFechaAdicion");
		param = new ParameterDesc(new QName("", "Usuario"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "Clave"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "Fecha"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new QName("", "findContribuyentesByFechaAdicionReturn"));
		oper.setStyle(Style.RPC);
		oper.setUse(Use.ENCODED);
		_operations[3] = oper;

		oper = new OperationDesc();
		oper.setName("findContribuyentesByFechaModificacion");
		param = new ParameterDesc(new QName("", "usuario"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "clave"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "Fecha"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new QName("", "findContribuyentesByFechaModificacionReturn"));
		oper.setStyle(Style.RPC);
		oper.setUse(Use.ENCODED);
		_operations[4] = oper;

		oper = new OperationDesc();
		oper.setName("findContribuyenteByNombres");
		param = new ParameterDesc(new QName("", "usuario"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "clave"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "primer_apellido"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "segundo_apellido"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "apellido_casada"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "primer_nombre"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "segundo_nombre"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "fecha_Nacimiento"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new QName("", "findContribuyenteByNombresReturn"));
		oper.setStyle(Style.RPC);
		oper.setUse(Use.ENCODED);
		_operations[5] = oper;

		oper = new OperationDesc();
		oper.setName("listContribuyentesByFecha");
		param = new ParameterDesc(new QName("", "Usuario"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "Clave"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "Fecha"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new QName("", "listContribuyentesByFechaReturn"));
		oper.setStyle(Style.RPC);
		oper.setUse(Use.ENCODED);
		_operations[6] = oper;

		oper = new OperationDesc();
		oper.setName("listContribuyentesByFechaModificacion");
		param = new ParameterDesc(new QName("", "Usuario"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "Clave"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "Fecha"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new QName("", "listContribuyentesByFechaModificacionReturn"));
		oper.setStyle(Style.RPC);
		oper.setUse(Use.ENCODED);
		_operations[7] = oper;

		oper = new OperationDesc();
		oper.setName("listContribuyentesByNit");
		param = new ParameterDesc(new QName("", "Usuario"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "Clave"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "Nit"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new QName("", "listContribuyentesByNitReturn"));
		oper.setStyle(Style.RPC);
		oper.setUse(Use.ENCODED);
		_operations[8] = oper;

		oper = new OperationDesc();
		oper.setName("listContribuyenteByNombreDicabi");
		param = new ParameterDesc(new QName("", "pUsuario"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "pClave"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "pPrimerApellido"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "pSegundoApellido"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "pApellidoCasada"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "pPrimerNombre"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "pSegundoNombre"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "pFechaNacimiento"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new QName("", "listContribuyenteByNombreDicabiReturn"));
		oper.setStyle(Style.RPC);
		oper.setUse(Use.ENCODED);
		_operations[9] = oper;

	}

	private static void _initOperationDesc2() {
		OperationDesc oper;
		ParameterDesc param;
		oper = new OperationDesc();
		oper.setName("listContribuyentesByFechaAdicionDicabi");
		param = new ParameterDesc(new QName("", "pUsuario"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "pClave"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "pFecha"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new QName("", "listContribuyentesByFechaAdicionDicabiReturn"));
		oper.setStyle(Style.RPC);
		oper.setUse(Use.ENCODED);
		_operations[10] = oper;

		oper = new OperationDesc();
		oper.setName("listContribuyentesByFechaModificacionDicabi");
		param = new ParameterDesc(new QName("", "pUsuario"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "pClave"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "pFecha"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new QName("", "listContribuyentesByFechaModificacionDicabiReturn"));
		oper.setStyle(Style.RPC);
		oper.setUse(Use.ENCODED);
		_operations[11] = oper;

		oper = new OperationDesc();
		oper.setName("listContribuyentesByNitDicabi");
		param = new ParameterDesc(new QName("", "pUsuario"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "pClave"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "Pnit"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new QName("", "listContribuyentesByNitDicabiReturn"));
		oper.setStyle(Style.RPC);
		oper.setUse(Use.ENCODED);
		_operations[12] = oper;

		oper = new OperationDesc();
		oper.setName("findContribuyenteByNitMFP");
		param = new ParameterDesc(new QName("", "usuario"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "clave"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "nit"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new QName("", "findContribuyenteByNitMFPReturn"));
		oper.setStyle(Style.RPC);
		oper.setUse(Use.ENCODED);
		_operations[13] = oper;

		oper = new OperationDesc();
		oper.setName("getNit");
		param = new ParameterDesc(new QName("", "pUsuario"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "pContrasenia"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "pCui"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new QName("", "getNitReturn"));
		oper.setStyle(Style.RPC);
		oper.setUse(Use.ENCODED);
		_operations[14] = oper;

		oper = new OperationDesc();
		oper.setName("catalogo_errores");
		param = new ParameterDesc(new QName("", "pUsuario"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "pClave"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new QName("", "catalogo_erroresReturn"));
		oper.setStyle(Style.RPC);
		oper.setUse(Use.ENCODED);
		_operations[15] = oper;

		oper = new OperationDesc();
		oper.setName("findContribuyenteByNitGC");
		param = new ParameterDesc(new QName("", "pUsuario"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "pContrasenia"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new ParameterDesc(new QName("", "pNit"), ParameterDesc.IN,
				new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new QName("", "findContribuyenteByNitGCReturn"));
		oper.setStyle(Style.RPC);
		oper.setUse(Use.ENCODED);
		_operations[16] = oper;

	}

	public ConsultasPublicasPortSoapBindingStub() throws AxisFault {
		this(null);
	}

	public ConsultasPublicasPortSoapBindingStub(URL endpointURL, Service service) throws AxisFault {
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	public ConsultasPublicasPortSoapBindingStub(Service service) throws AxisFault {
		if (service == null) {
			super.service = new org.apache.axis.client.Service();
		} else {
			super.service = service;
		}
		((org.apache.axis.client.Service) super.service).setTypeMappingVersion("1.2");
	}

	protected Call createCall() throws RemoteException {
		try {
			Call _call = super._createCall();
			if (super.maintainSessionSet) {
				_call.setMaintainSession(super.maintainSession);
			}
			if (super.cachedUsername != null) {
				_call.setUsername(super.cachedUsername);
			}
			if (super.cachedPassword != null) {
				_call.setPassword(super.cachedPassword);
			}
			if (super.cachedEndpoint != null) {
				_call.setTargetEndpointAddress(super.cachedEndpoint);
			}
			if (super.cachedTimeout != null) {
				_call.setTimeout(super.cachedTimeout);
			}
			if (super.cachedPortName != null) {
				_call.setPortName(super.cachedPortName);
			}
			Enumeration keys = super.cachedProperties.keys();
			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				_call.setProperty(key, super.cachedProperties.get(key));
			}
			return _call;
		} catch (Throwable _t) {
			throw new AxisFault("Failure trying to get the Call object", _t);
		}
	}

	public String isAValidFactura(String usuario, String clave, String nit, String numeroSerie, String noDocumento)
			throws RemoteException {
		if (super.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[0]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("http://ws.contribuyente.rtu.sat.gob.gt", "isAValidFactura"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { usuario, clave, nit, numeroSerie, noDocumento });

			if (_resp instanceof RemoteException) {
				throw (RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) JavaUtils.convert(_resp, String.class);
				}
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String buscarRazonDenominacionSocial(String pUsuario, String pClave, String pNombre) throws RemoteException {
		if (super.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[1]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("http://ws.contribuyente.rtu.sat.gob.gt", "buscarRazonDenominacionSocial"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { pUsuario, pClave, pNombre });

			if (_resp instanceof RemoteException) {
				throw (RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) JavaUtils.convert(_resp, String.class);
				}
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String buscarNombreComercial(String pUsuario, String pClave, String pNombreComercial)
			throws RemoteException {
		if (super.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[2]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("http://ws.contribuyente.rtu.sat.gob.gt", "buscarNombreComercial"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { pUsuario, pClave, pNombreComercial });

			if (_resp instanceof RemoteException) {
				throw (RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) JavaUtils.convert(_resp, String.class);
				}
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String findContribuyentesByFechaAdicion(String usuario, String clave, String fecha) throws RemoteException {
		if (super.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[3]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("http://ws.contribuyente.rtu.sat.gob.gt", "findContribuyentesByFechaAdicion"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { usuario, clave, fecha });

			if (_resp instanceof RemoteException) {
				throw (RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) JavaUtils.convert(_resp, String.class);
				}
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String findContribuyentesByFechaModificacion(String usuario, String clave, String fecha)
			throws RemoteException {
		if (super.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[4]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(
				new QName("http://ws.contribuyente.rtu.sat.gob.gt", "findContribuyentesByFechaModificacion"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { usuario, clave, fecha });

			if (_resp instanceof RemoteException) {
				throw (RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) JavaUtils.convert(_resp, String.class);
				}
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String findContribuyenteByNombres(String usuario, String clave, String primer_apellido,
			String segundo_apellido, String apellido_casada, String primer_nombre, String segundo_nombre,
			String fecha_Nacimiento) throws RemoteException {
		if (super.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[5]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("http://ws.contribuyente.rtu.sat.gob.gt", "findContribuyenteByNombres"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { usuario, clave, primer_apellido, segundo_apellido,
					apellido_casada, primer_nombre, segundo_nombre, fecha_Nacimiento });

			if (_resp instanceof RemoteException) {
				throw (RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) JavaUtils.convert(_resp, String.class);
				}
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String listContribuyentesByFecha(String usuario, String clave, String fecha) throws RemoteException {
		if (super.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[6]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("http://ws.contribuyente.rtu.sat.gob.gt", "listContribuyentesByFecha"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { usuario, clave, fecha });

			if (_resp instanceof RemoteException) {
				throw (RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) JavaUtils.convert(_resp, String.class);
				}
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String listContribuyentesByFechaModificacion(String usuario, String clave, String fecha)
			throws RemoteException {
		if (super.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[7]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(
				new QName("http://ws.contribuyente.rtu.sat.gob.gt", "listContribuyentesByFechaModificacion"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { usuario, clave, fecha });

			if (_resp instanceof RemoteException) {
				throw (RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) JavaUtils.convert(_resp, String.class);
				}
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String listContribuyentesByNit(String usuario, String clave, String nit) throws RemoteException {
		if (super.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[8]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("http://ws.contribuyente.rtu.sat.gob.gt", "listContribuyentesByNit"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { usuario, clave, nit });

			if (_resp instanceof RemoteException) {
				throw (RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) JavaUtils.convert(_resp, String.class);
				}
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String listContribuyenteByNombreDicabi(String pUsuario, String pClave, String pPrimerApellido,
			String pSegundoApellido, String pApellidoCasada, String pPrimerNombre, String pSegundoNombre,
			String pFechaNacimiento) throws RemoteException {
		if (super.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[9]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("http://ws.contribuyente.rtu.sat.gob.gt", "listContribuyenteByNombreDicabi"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { pUsuario, pClave, pPrimerApellido, pSegundoApellido,
					pApellidoCasada, pPrimerNombre, pSegundoNombre, pFechaNacimiento });

			if (_resp instanceof RemoteException) {
				throw (RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) JavaUtils.convert(_resp, String.class);
				}
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String listContribuyentesByFechaAdicionDicabi(String pUsuario, String pClave, String pFecha)
			throws RemoteException {
		if (super.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[10]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(
				new QName("http://ws.contribuyente.rtu.sat.gob.gt", "listContribuyentesByFechaAdicionDicabi"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { pUsuario, pClave, pFecha });

			if (_resp instanceof RemoteException) {
				throw (RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) JavaUtils.convert(_resp, String.class);
				}
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String listContribuyentesByFechaModificacionDicabi(String pUsuario, String pClave, String pFecha)
			throws RemoteException {
		if (super.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[11]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(
				new QName("http://ws.contribuyente.rtu.sat.gob.gt", "listContribuyentesByFechaModificacionDicabi"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { pUsuario, pClave, pFecha });

			if (_resp instanceof RemoteException) {
				throw (RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) JavaUtils.convert(_resp, String.class);
				}
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String listContribuyentesByNitDicabi(String pUsuario, String pClave, String pnit) throws RemoteException {
		if (super.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[12]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("http://ws.contribuyente.rtu.sat.gob.gt", "listContribuyentesByNitDicabi"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { pUsuario, pClave, pnit });

			if (_resp instanceof RemoteException) {
				throw (RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) JavaUtils.convert(_resp, String.class);
				}
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String findContribuyenteByNitMFP(String usuario, String clave, String nit) throws RemoteException {
		if (super.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[13]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("http://ws.contribuyente.rtu.sat.gob.gt", "findContribuyenteByNitMFP"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { usuario, clave, nit });

			if (_resp instanceof RemoteException) {
				throw (RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) JavaUtils.convert(_resp, String.class);
				}
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String getNit(String pUsuario, String pContrasenia, String pCui) throws RemoteException {
		if (super.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[14]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("http://ws.contribuyente.rtu.sat.gob.gt", "getNit"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { pUsuario, pContrasenia, pCui });

			if (_resp instanceof RemoteException) {
				throw (RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) JavaUtils.convert(_resp, String.class);
				}
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String catalogo_errores(String pUsuario, String pClave) throws RemoteException {
		if (super.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[15]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("http://ws.contribuyente.rtu.sat.gob.gt", "catalogo_errores"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { pUsuario, pClave });

			if (_resp instanceof RemoteException) {
				throw (RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) JavaUtils.convert(_resp, String.class);
				}
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String findContribuyenteByNitGC(String pUsuario, String pContrasenia, String pNit) throws RemoteException {
		if (super.cachedEndpoint == null) {
			throw new NoEndPointException();
		}
		Call _call = createCall();
		_call.setOperation(_operations[16]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new QName("http://ws.contribuyente.rtu.sat.gob.gt", "findContribuyenteByNitGC"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { pUsuario, pContrasenia, pNit });

			if (_resp instanceof RemoteException) {
				throw (RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) JavaUtils.convert(_resp, String.class);
				}
			}
		} catch (AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

}
