/**
 * ConsultaSiafLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gt.gob.rgm.sat.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Remote;
import java.util.HashSet;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisFault;
import org.apache.axis.EngineConfiguration;
import org.apache.axis.client.Service;

public class ConsultaSiafLocator extends Service implements ConsultaSiaf {

	public ConsultaSiafLocator() {
	}

	public ConsultaSiafLocator(EngineConfiguration config) {
		super(config);
	}

	public ConsultaSiafLocator(String wsdlLoc, QName sName) throws ServiceException {
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for ConsultasPublicasPort
	private String ConsultasPublicasPort_address = "https://farm2.sat.gob.gt/sat/rtu_siaf/contribuyente/serviciosWeb/ConsultaSiaf";

	public String getConsultasPublicasPortAddress() {
		return ConsultasPublicasPort_address;
	}

	// The WSDD service name defaults to the port name.
	private String ConsultasPublicasPortWSDDServiceName = "ConsultasPublicasPort";

	public String getConsultasPublicasPortWSDDServiceName() {
		return ConsultasPublicasPortWSDDServiceName;
	}

	public void setConsultasPublicasPortWSDDServiceName(String name) {
		ConsultasPublicasPortWSDDServiceName = name;
	}

	public ContribuyenteWsEp getConsultasPublicasPort() throws ServiceException {
		URL endpoint;
		try {
			endpoint = new URL(ConsultasPublicasPort_address);
		} catch (MalformedURLException e) {
			throw new ServiceException(e);
		}
		return getConsultasPublicasPort(endpoint);
	}

	public ContribuyenteWsEp getConsultasPublicasPort(URL portAddress) throws ServiceException {
		try {
			ConsultasPublicasPortSoapBindingStub _stub = new ConsultasPublicasPortSoapBindingStub(portAddress, this);
			_stub.setPortName(getConsultasPublicasPortWSDDServiceName());
			return _stub;
		} catch (AxisFault e) {
			return null;
		}
	}

	public void setConsultasPublicasPortEndpointAddress(String address) {
		ConsultasPublicasPort_address = address;
	}

	/**
	 * For the given interface, get the stub implementation. If this service has no
	 * port for the given interface, then ServiceException is thrown.
	 */
	public Remote getPort(Class serviceEndpointInterface) throws ServiceException {
		try {
			if (ContribuyenteWsEp.class.isAssignableFrom(serviceEndpointInterface)) {
				ConsultasPublicasPortSoapBindingStub _stub = new ConsultasPublicasPortSoapBindingStub(
						new URL(ConsultasPublicasPort_address), this);
				_stub.setPortName(getConsultasPublicasPortWSDDServiceName());
				return _stub;
			}
		} catch (Throwable t) {
			throw new ServiceException(t);
		}
		throw new ServiceException("There is no stub implementation for the interface:  "
				+ (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
	}

	/**
	 * For the given interface, get the stub implementation. If this service has no
	 * port for the given interface, then ServiceException is thrown.
	 */
	public Remote getPort(QName portName, Class serviceEndpointInterface) throws ServiceException {
		if (portName == null) {
			return getPort(serviceEndpointInterface);
		}
		String inputPortName = portName.getLocalPart();
		if ("ConsultasPublicasPort".equals(inputPortName)) {
			return getConsultasPublicasPort();
		} else {
			Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public QName getServiceName() {
		return new QName("http://www.sat.gob.gt", "ConsultaSiaf");
	}

	private HashSet ports = null;

	public Iterator getPorts() {
		if (ports == null) {
			ports = new HashSet();
			ports.add(new QName("http://www.sat.gob.gt", "ConsultasPublicasPort"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(String portName, String address) throws ServiceException {

		if ("ConsultasPublicasPort".equals(portName)) {
			setConsultasPublicasPortEndpointAddress(address);
		} else { // Unknown Port Name
			throw new ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
		}
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(QName portName, String address) throws ServiceException {
		setEndpointAddress(portName.getLocalPart(), address);
	}

}
