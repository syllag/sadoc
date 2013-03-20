
package fr.univartois.ili.sadoc.client.webservice.tools;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlInlineBinaryData;
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
 *         &lt;element ref="{http://sadoc.com/ac/schemas}name"/>
 *         &lt;element ref="{http://sadoc.com/ac/schemas}url"/>
 *         &lt;element ref="{http://sadoc.com/ac/schemas}checkSum"/>
 *         &lt;element ref="{http://sadoc.com/ac/schemas}creationDate"/>
 *         &lt;element ref="{http://sadoc.com/ac/schemas}p7s" maxOccurs="unbounded"/>
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
    "name",
    "url",    
    "checkSum",   
    "creationDate",
    "p7s"
})
@XmlRootElement(name = "document")
public class Document {

    @XmlElement(required = true)
    protected long id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String url;
    @XmlElement(required = true)
    protected String checkSum;
    @XmlElement(required = true)
    protected XMLGregorianCalendar creationDate; 
    @XmlElement(required = true)
    @XmlInlineBinaryData
    protected Byte[] p7s;

  

    /**
     * Gets the value of the pk7 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pk7 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPk7().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataHandler }
     * 
     * 
     */
    public Byte[] getP7s() {
        if (p7s == null) {
            p7s =new  Byte[1];
        }
        return this.p7s;
    }



	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}



	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}



	/**
	 * @return the checkSum
	 */
	public String getCheckSum() {
		return checkSum;
	}



	/**
	 * @param checkSum the checkSum to set
	 */
	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
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



	/**
	 * @param p7s the p7s to set
	 */
	public void setP7s(Byte[] p7s) {
		this.p7s = p7s;
	}

    
}
