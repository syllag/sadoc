package fr.univartois.ili.sadoc.dao.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Document implements Serializable {

	
	private static final long serialVersionUID = 2105359651993183213L;
	
	@Id
	private long id;
	private String name;
	private String url;
	private String checksum;
	@Enumerated(EnumType.STRING)
	private ChecksumAlgorithm algorithm;
	@Temporal(TemporalType.DATE)
	private Date creationDate;
	
	//rel
	@OneToMany(mappedBy="document")
	private List<Signature> signatures;
	@ManyToMany
	private List<Acquisition> acquisitions;
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
	 * @return the checksum
	 */
	public String getChecksum() {
		return checksum;
	}
	/**
	 * @param checksum the checksum to set
	 */
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	/**
	 * @return the algorithm
	 */
	public ChecksumAlgorithm getAlgorithm() {
		return algorithm;
	}
	/**
	 * @param algorithm the algorithm to set
	 */
	public void setAlgorithm(ChecksumAlgorithm algorithm) {
		this.algorithm = algorithm;
	}
	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return the signatures
	 */
	public List<Signature> getSignatures() {
		return signatures;
	}
	/**
	 * @param signatures the signatures to set
	 */
	public void setSignatures(List<Signature> signatures) {
		this.signatures = signatures;
	}
	/**
	 * @return the acquisitions
	 */
	public List<Acquisition> getAcquisitions() {
		return acquisitions;
	}
	/**
	 * @param acquisitions the acquisitions to set
	 */
	public void setAcquisitions(List<Acquisition> acquisitions) {
		this.acquisitions = acquisitions;
	}
}