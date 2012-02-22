package fr.univartois.ili.sadoc.actions;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.utils.TestID;

public class CheckDocument extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sa = null;

	public String getSa() {
		return sa;
	}

	public void setSa(final String sa) {
		this.sa = sa;
	}

	public String execute(){
		if(TestID.trueFalseID(sa)){
			long realID = TestID.findRealID(sa);
			
		}
			
		return SUCCESS;
	}	
	
}
