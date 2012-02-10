package fr.univartois.ili.sadoc.sadocweb.springbidon;
/**
 * 
 * @author habib
 *
 */
public class BusinessObjectImpl implements BusinessObject {
	
	private long valeur;

	public long calculCA() {
		return this.valeur;
	}

	public long getValeur() {
		return valeur;
	}

	public void setValeur(long valeur) {
		this.valeur = valeur;
	}

}
