package fr.univartois.ili.sadoc.metier.commun.vo;

/**
 * 
 * @author Mohamed Belhadj-adda <belhadjadda.mohamed at gmail.com>
 * 
 */
public class Domaine {

	private long id;
	private String codeDomaine;
	private String description;

	public Domaine() {
	}

	public Domaine(String description) {
		this.setDescription(description);
	}

	public Domaine(String codeDonaime, String description) {
		this.codeDomaine = codeDonaime;
		this.setDescription(description);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodeDomaine() {
		return codeDomaine;
	}

	public void setCodeDomaine(String codeDomaine) {
		this.codeDomaine = codeDomaine;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
