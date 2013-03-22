
package fr.univartois.ili.sadoc.client.webservice.tools;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://sadoc.com/ac/schemas}idDocument"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idDocument"
})
@XmlRootElement(name = "getDocumentInformationsRequest")
public class GetDocumentInformationsRequest {

    @XmlElement(required = true)
    protected long idDocument;

    /**
     * Gets the value of the idDocument property.
     * 
     * @return
     *     possible object is
     *     {@link long }
     *     
     */
    public long getIdDocument() {
        return idDocument;
    }

    /**
     * Sets the value of the idDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link long }
     *     
     */
    public void setIdDocument(long value) {
        this.idDocument = value;
    }

}
