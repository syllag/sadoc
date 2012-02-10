package fr.univartois.ili.sadoc.sadocweb.springbidon;

/**
 * 
 * @author habib
 *
 */
public class ServiceObjectImpl2 implements ServiceObject {
	
	private BusinessObject businessObject;

	public long calculCA() {
		return this.businessObject.calculCA();
	}

	public BusinessObject getBusinessObject() {
		return businessObject;
	}

	public void setBusinessObject(BusinessObject businessObject) {
		this.businessObject = businessObject;
	}

}
