package fr.univartois.ili.sadoc.metier.commun.vo;

/**
 * 
 * @author Mohamed Belhadj-adda <belhadjadda.mohamed at gmail.com>
 * 
 */
public class Item {

	private int id;
	private String codeItem;
	private String description;
	private int poids;
	private int empreinte;
	private String type;

	public Item() {
	}

	public Item(String description) {
		this.setDescription(description);
	}

	public Item(String codeItem, String description) {
		this.setCodeItem(codeItem);
		this.setDescription(description);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodeItem() {
		return codeItem;
	}

	public void setCodeItem(String codeItem) {
		this.codeItem = codeItem;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPoids() {
		return poids;
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}

	public int getEmpreinte() {
		return empreinte;
	}

	public void setEmpreinte(int empreinte) {
		this.empreinte = empreinte;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
