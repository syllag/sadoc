package fr.univartois.ili.sadoc.metier.ws.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.ManyToMany;

import fr.univartois.ili.sadoc.dao.entities.ChecksumAlgorithm;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class Document implements Serializable, Comparable<Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	private String name;
	private String url;
	private String checkSum;
	private byte[] pk7;
	private ChecksumAlgorithm algorithm;

	private Date creationDate;
	
	private List<Signature> signatures;	
	private List<Acquisition> acquisitions;

	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ChecksumAlgorithm getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(ChecksumAlgorithm algorithm) {
		this.algorithm = algorithm;
	}

	public List<Signature> getSignatures() {
		return signatures;
	}

	public void setSignatures(List<Signature> signatures) {
		this.signatures = signatures;
	}

	public List<Acquisition> getAcquisitions() {
		return acquisitions;
	}

	public void setAcquisitions(List<Acquisition> acquisitions) {
		this.acquisitions = acquisitions;
	}

	
	/************************************************/

	public Document() {
	}

	public Document(String name, String checksum, byte[] pk7) {
		this.name = name;
		this.checkSum = checksum;
		this.pk7 = pk7;
	}

	/************************************************/

	//
	// Pourquoi ne pas redéfinir la méthode equals ??
	// Au lieu de redéfinir compareTo
	//
	//
	// This class defines a compareTo(...) method but inherits its equals()
	// method from java.lang.Object. Generally, the value of compareTo should
	// return zero if and only if equals returns true. If this is violated,
	// weird and unpredictable failures will occur in classes such as
	// PriorityQueue. In Java 5 the PriorityQueue.remove method uses the
	// compareTo method, while in Java 6 it uses the equals method.

	public int compareTo(Object other) {
		String name1 = ((Document) other).getName();
		String name2 = this.getName();
		return name2.compareTo(name1);
	}

	/************************************************/

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	}

	public Date getCreationDate() {
		return (Date) creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = (Date) creationDate;
	}

	public byte[] getPk7() {
		return pk7;
	}

	public void setPk7(byte[] pk7) {
		this.pk7 = pk7;
	}

}
