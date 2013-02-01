package fr.univartois.ili.sadoc.metier.ws.vo;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 *
 */
public class Certificate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private PublicKey publicKey;
	private PrivateKey privateKey;
	
	private Owner owner;
	
	/************************************************/

	public Certificate(){}
	
	public Certificate(PublicKey publicKey, PrivateKey privateKey) {
		this.publicKey=publicKey;
		this.privateKey=privateKey;
	}
	
	public Certificate(PublicKey publicKey, PrivateKey privateKey, Owner owner) {
		this.publicKey=publicKey;
		this.privateKey=privateKey;
		this.owner=owner;
	}
	
	/************************************************/

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public PublicKey getPublicKey() {
		return publicKey;
	}
	
	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}
	
	public PrivateKey getPrivateKey() {
		return privateKey;
	}
	
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
}
