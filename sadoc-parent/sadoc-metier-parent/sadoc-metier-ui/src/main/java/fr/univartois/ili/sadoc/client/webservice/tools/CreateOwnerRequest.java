
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
 *         &lt;element ref="{http://sadoc.com/ac/schemas}mail_initial"/>
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
    "mail_initial"
})
@XmlRootElement(name = "createOwnerRequest")
public class CreateOwnerRequest {
    
    @XmlElement(required = true)
    protected String mail_initial;

	/**
	 * @return the mail_initial
	 */
	public String getMail_initial() {
		return mail_initial;
	}

	/**
	 * @param mail_initial the mail_initial to set
	 */
	public void setMail_initial(String mail_initial) {
		this.mail_initial = mail_initial;
	}

   

}
