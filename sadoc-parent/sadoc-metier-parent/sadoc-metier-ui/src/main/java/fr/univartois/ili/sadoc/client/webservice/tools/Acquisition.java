
package fr.univartois.ili.sadoc.client.webservice.tools;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element ref="{http://sadoc.com/ac/schemas}id_item"/>
 *         &lt;element ref="{http://sadoc.com/ac/schemas}creationDate"/>
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
    "id_item",
    "creationDate"
})
@XmlRootElement(name = "competence")
public class Acquisition {

    @XmlElement(required = true)
    protected long id;
    @XmlElement(required = true)
    protected String id_item;
    @XmlElement(required = true)
    protected XMLGregorianCalendar creationDate;   

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
	 * @return the id_item
	 */
	public String getId_item() {
		return id_item;
	}

	/**
	 * @param id_item the id_item to set
	 */
	public void setId_item(String id_item) {
		this.id_item = id_item;
	}

	/**
	 * @return the creationDate
	 */
	public XMLGregorianCalendar getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(XMLGregorianCalendar creationDate) {
		this.creationDate = creationDate;
	}

   

  
    

}
