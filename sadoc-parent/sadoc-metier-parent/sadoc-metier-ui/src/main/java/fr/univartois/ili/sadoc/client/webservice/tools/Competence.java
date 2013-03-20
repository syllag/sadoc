
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
 *         &lt;element ref="{http://sadoc.com/ac/schemas}id"/>
 *         &lt;element ref="{http://sadoc.com/ac/schemas}codeCompetence"/>
 *         &lt;element ref="{http://sadoc.com/ac/schemas}description"/>
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
    "id",   
    "codeCompetence",
    "description"
})
@XmlRootElement(name = "competence")
public class Competence {

    @XmlElement(required = true)
    protected long id;
    @XmlElement(required = true)
    protected String codeCompetence;
    @XmlElement(required = true)
    protected String description;   

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link long }
     *     
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link long }
     *     
     */
    public void setId(long value) {
        this.id = value;
    }

   

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

	/**
	 * @return the codeCompetence
	 */
	public String getCodeCompetence() {
		return codeCompetence;
	}

	/**
	 * @param codeCompetence the codeCompetence to set
	 */
	public void setCodeCompetence(String codeCompetence) {
		this.codeCompetence = codeCompetence;
	}

    

}
