package fr.univartois.ili.sadoc.metier.commun.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Mohamed Belhadj-adda <belhadjadda.mohamed at gmail.com>
 * 
 */
public class Referentiel implements Serializable {

	private static final long serialVersionUID = 2834501934059922232L;

	private long id = -1L;
	private String codeReferentiel = null;
	private String name = null;
	private String description = null;
	private long seuil = 0L;
	private String url = null;

	private List<Domaine> domaines = new ArrayList<Domaine>();

	public Referentiel() {
	}

	public Referentiel(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Referentiel(String codeReferentiel, String name, String description,
			String url) {
		this(name, description);
		this.codeReferentiel = codeReferentiel;
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

	public List<Domaine> getDomaines() {
		return domaines;
	}

	public void setDomaines(List<Domaine> domaines) {
		this.domaines = domaines;
	}
}
