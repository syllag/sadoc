package fr.univartois.ili.sadoc.metier.ws.vo;
import java.io.Serializable;
import java.util.List;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 *
 */
public class Owner implements Serializable, Comparable<Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private long id;
	// TODO Numéro de SECU ou Numéro carte séjour
	
	private String mail_initial;
	
	private List<Certificate> certificates;
	
	/************************************************/
	
	public Owner(){}
	
	public Owner(String mail) {
		this.mail_initial=mail;
	}
	
	public Owner(String mail, List<Certificate> certificates) {
		this.mail_initial=mail;
		this.certificates=certificates;
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
//	      String name1 = ((Owner) other).getLastName()+((Owner) other).getFirstName(); 
//	      String name2 = this.getLastName()+this.getFirstName(); 
//	      return name2.compareTo(name1);
		return this.getMail_initial().compareTo(((Owner) other).getMail_initial());
	   } 
	
	/************************************************/
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMail_initial() {
		return mail_initial;
	}

	public void setMail_initial(String mail_initial) {
		this.mail_initial = mail_initial;
	}

	public List<Certificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<Certificate> certificates) {
		this.certificates = certificates;
	}

}
