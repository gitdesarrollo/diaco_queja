/**
 * ConsultaSiaf.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gt.gob.rgm.sat.ws;

import java.net.URL;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;

public interface ConsultaSiaf extends Service {
	public String getConsultasPublicasPortAddress();

	public ContribuyenteWsEp getConsultasPublicasPort() throws ServiceException;

	public ContribuyenteWsEp getConsultasPublicasPort(URL portAddress) throws ServiceException;
}
