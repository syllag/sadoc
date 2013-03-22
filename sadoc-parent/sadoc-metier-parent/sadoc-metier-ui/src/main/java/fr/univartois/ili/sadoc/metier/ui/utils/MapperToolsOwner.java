package fr.univartois.ili.sadoc.metier.ui.utils;

import fr.univartois.ili.sadoc.metier.ui.vo.Owner;

public class MapperToolsOwner {

	/**
	 * Convert OwnerTool to OwnerVO Parses the OwerTool interpreting its
	 * content as a OwnerVO.
	 * 
	 * @param ownerTool
	 * @return ownerVO
	 */
	private static Owner ownerToolToOwnerVO(fr.univartois.ili.sadoc.client.webservice.tools.Owner ownerTool){
		Owner ownerVO = new Owner();
		ownerVO.setId(ownerTool.getId());
		ownerVO.setMail(ownerTool.getMailInitial());
		return ownerVO;
	}
}
