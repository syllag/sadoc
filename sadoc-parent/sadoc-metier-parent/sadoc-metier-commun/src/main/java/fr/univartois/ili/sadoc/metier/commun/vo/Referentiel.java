package fr.univartois.ili.sadoc.metier.commun.vo;

/**
 * 
 * @author Mohamed Belhadj-adda <belhadjadda.mohamed at gmail.com>
 * 
 */
public class Referentiel {
	private long id;
	private String codeReferentiel;
	private String name;
	private String description;
	private long seuil;
	private String url;

	public Referentiel() {
	}

	public Referentiel(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Referentiel(String codeReferentiel, String name, String description,
			String url) {
		this.codeReferentiel = codeReferentiel;
		this.name = name;
		this.description = description;
		this.url = url;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodeReferentiel() {
		return codeReferentiel;
	}

	public void setCodeReferentiel(String codeReferentiel) {
		this.codeReferentiel = codeReferentiel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getSeuil() {
		return seuil;
	}

	public void setSeuil(long seuil) {
		this.seuil = seuil;
	}

}
