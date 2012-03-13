
package fr.univartois.ili.sadoc.client.webservice.tools;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "SadocServicesService", targetNamespace = "http://sadoc.com/ac/schemas", wsdlLocation = "http://localhost:8080/sadocWeb/services/sadoc.wsdl")
public class SadocServicesService
    extends Service
{

    private final static URL SADOCSERVICESSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(fr.univartois.ili.sadoc.client.webservice.tools.SadocServicesService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = fr.univartois.ili.sadoc.client.webservice.tools.SadocServicesService.class.getResource(".");
            url = new URL(baseUrl, "http://localhost:8080/sadocWeb/services/sadoc.wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://localhost:8080/sadocWeb/services/sadoc.wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        SADOCSERVICESSERVICE_WSDL_LOCATION = url;
    }

    public SadocServicesService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SadocServicesService() {
        super(SADOCSERVICESSERVICE_WSDL_LOCATION, new QName("http://sadoc.com/ac/schemas", "SadocServicesService"));
    }

    /**
     * 
     * @return
     *     returns SadocServices
     */
    @WebEndpoint(name = "SadocServicesSoap11")
    public SadocServices getSadocServicesSoap11() {
        return super.getPort(new QName("http://sadoc.com/ac/schemas", "SadocServicesSoap11"), SadocServices.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SadocServices
     */
    @WebEndpoint(name = "SadocServicesSoap11")
    public SadocServices getSadocServicesSoap11(WebServiceFeature... features) {
        return super.getPort(new QName("http://sadoc.com/ac/schemas", "SadocServicesSoap11"), SadocServices.class, features);
    }

}
