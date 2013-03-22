package fr.univartois.ili.sadoc.metier.ui.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.GregorianCalendar;

import javax.activation.DataHandler;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import fr.univartois.ili.sadoc.metier.ui.vo.Document;

public class MapperToolsDocument {

	/**
	 * Convert DocumentTool to DocumentVO Parses the DocumentTool interpreting
	 * its content as a DocumentVO.
	 * 
	 * @param docTool
	 * @return documentVO
	 */
	private static Document documentToolToDocumentVO(
			fr.univartois.ili.sadoc.client.webservice.tools.Document docTool) {
		Document docVO = new Document();
		docVO.setId(String.valueOf(docTool.getId()));
		docVO.setName(docTool.getName());
		docVO.setCheckSum(docTool.getCheckSum());
		docVO.setUrl(docTool.getUrl());

		
		byte[] b = new byte[0];
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			for (DataHandler handler : docTool.getP7S()) {
				handler.writeTo(output);
				b = output.toByteArray();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		docVO.setP7s(b);

		docVO.setCreationDate(docTool.getCreationDate().toGregorianCalendar()
				.getTime());
		return docVO;
	}

	/**
	 * Convert DocumentVO to DocumentTool Parses the DocumentVO interpreting its
	 * content as a DocumentTool.
	 * 
	 * @param docVO
	 * @return documentTool
	 */
	private static fr.univartois.ili.sadoc.client.webservice.tools.Document documentVOToDocumentTool(
			Document docVO) {
		fr.univartois.ili.sadoc.client.webservice.tools.Document docTool = new fr.univartois.ili.sadoc.client.webservice.tools.Document();
		docTool.setId(new Long(docVO.getId()));
		docTool.setName(docVO.getName());
		docTool.setCheckSum(docVO.getCheckSum());
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(docVO.getCreationDate());
		try {
			docTool.setCreationDate(DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(gc));
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return docTool;
	}
}
