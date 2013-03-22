package fr.univartois.ili.sadoc.metier.ui.utils;

import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import fr.univartois.ili.sadoc.metier.ui.vo.Acquisition;

public class MapperToolsAcquisition {
	
	/**
	 * Convert AcquisitionTool to AcquisitionVO Parses the AcquisitionTool interpreting its
	 * content as a AcquisitionVO.
	 * 
	 * @param acqTool
	 * @return acquisitionVO
	 */
	private static Acquisition acquisitionToolToAcquisitionVO(fr.univartois.ili.sadoc.client.webservice.tools.Acquisition acqTool){
		Acquisition acqVO = new Acquisition();
		acqVO.setId(acqTool.getId());
		acqVO.setId_item(acqTool.getIdItem());
		acqVO.setCreationDate(acqTool.getCreationDate().toGregorianCalendar().getTime());
		return acqVO;
	}
	
	/**
	 * Convert AcquisitionVO to AcquisitionTool Parses the AcquisitionVO interpreting its
	 * content as a AcquisitionTool.
	 * 
	 * @param acqVO
	 * @return acquisitionTool
	 */
	private static fr.univartois.ili.sadoc.client.webservice.tools.Acquisition acquisitionVOToAcquisitionTool(Acquisition acqVO){
		fr.univartois.ili.sadoc.client.webservice.tools.Acquisition acqTool = new fr.univartois.ili.sadoc.client.webservice.tools.Acquisition();
		acqTool.setId(acqVO.getId());
		acqTool.setIdItem(acqVO.getId_item());
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(acqVO.getCreationDate());
		try {
			acqTool.setCreationDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return acqTool;
	}

}
