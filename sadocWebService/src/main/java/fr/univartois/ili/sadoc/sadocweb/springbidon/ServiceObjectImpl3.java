package fr.univartois.ili.sadoc.sadocweb.springbidon;

/**
 * 
 * @author habib
 *
 */
public class ServiceObjectImpl3 implements ServiceObject {
	
	private BusinessObject businessObject;

	public long calculCA() {
		return this.businessObject.calculCA();
	}

	public ServiceObjectImpl3(BusinessObject businessObject) {
		super();
		this.businessObject = businessObject;
	}

}
