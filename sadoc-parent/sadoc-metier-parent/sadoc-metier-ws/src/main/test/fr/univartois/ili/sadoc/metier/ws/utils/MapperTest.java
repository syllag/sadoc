package fr.univartois.ili.sadoc.metier.ws.utils;

import static org.junit.Assert.*;
import fr.univartois.ili.sadoc.metier.ws.utils.Mapper;

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.junit.Before;
import org.junit.Test;

import fr.univartois.ili.sadoc.dao.entities.ChecksumAlgorithm;
import fr.univartois.ili.sadoc.metier.ws.vo.Document;
import static org.mockito.Mockito.mock;

public class MapperTest {
	private fr.univartois.ili.sadoc.dao.entities.Document docDO;
	private fr.univartois.ili.sadoc.dao.entities.Document docDO2;
	private Document docVO;
	
	
	@Before
	public void setUp() {

	}
	
	@Test
	public void objectToBlobTest() throws ClassNotFoundException, IOException, SQLException {
		KeyPairGenerator kpg;
		try {
			kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(1024, new SecureRandom());
			KeyPair userKeys = kpg.generateKeyPair(); 
			
			try {				
				Blob b2 = Mapper.objectToBlob(userKeys.getPrivate());
				KeyPair userKeys2 = new KeyPair(null,Mapper.blobToObject(b2, PrivateKey.class));
				assertEquals(userKeys.getPrivate(),userKeys2.getPrivate());				
				Blob b3 = Mapper.objectToBlob(userKeys2.getPrivate());
				assertEquals(b2.getBytes(1, (int) b2.length()).length,b3.getBytes(1, (int) b3.length()).length);				
			} catch (SerialException e) {
				fail("");
			} catch (SQLException e) {
				fail("");
			}	
		} catch (NoSuchAlgorithmException e) {
			fail("");
		} 
		
		
	}
	@Test
	public void documentMapperTest() {
		docDO = mock(fr.univartois.ili.sadoc.dao.entities.Document.class);
		docVO = new Document();
		docDO2 = new fr.univartois.ili.sadoc.dao.entities.Document();
		
		fr.univartois.ili.sadoc.dao.entities.Acquisition acq = mock(fr.univartois.ili.sadoc.dao.entities.Acquisition.class);
		fr.univartois.ili.sadoc.dao.entities.Acquisition acq2 = mock(fr.univartois.ili.sadoc.dao.entities.Acquisition.class);
		acq.setId(3);
		acq2.setId(4);
		List<fr.univartois.ili.sadoc.dao.entities.Acquisition> acqs = new ArrayList<fr.univartois.ili.sadoc.dao.entities.Acquisition>();
		acqs.add(acq);
		acqs.add(acq2);
		
		fr.univartois.ili.sadoc.dao.entities.Signature sig = mock(fr.univartois.ili.sadoc.dao.entities.Signature.class);
		fr.univartois.ili.sadoc.dao.entities.Signature sig2 = mock(fr.univartois.ili.sadoc.dao.entities.Signature.class);
		sig.setId(6);
		sig.setId(7);
		List<fr.univartois.ili.sadoc.dao.entities.Signature> sigs = new ArrayList<fr.univartois.ili.sadoc.dao.entities.Signature>();
		sigs.add(sig);
		sigs.add(sig2);
		
		docDO.setId(1);
		docDO.setName("test");
		docDO.setUrl("www.url.com");
		docDO.setChecksum("checksum");
		docDO.setAlgorithm(ChecksumAlgorithm.MD5);
		docDO.setAcquisitions(acqs);
		docDO.setSignatures(sigs);
		
		docVO = Mapper.documentDOToDocumentVO(docDO);
		docDO2 = Mapper.documentVOToDocumentDO(docVO);  
		
		assertEquals(docDO.getId(), docDO2.getId() , docVO.getId());
		assertEquals(docDO.getName(), docDO2.getName() , docVO.getName());
		assertEquals(docDO.getUrl(), docDO2.getUrl() , docVO.getUrl());
		assertEquals(docDO.getChecksum(), docDO2.getChecksum());
		assertEquals(docDO.getAlgorithm(), docDO2.getAlgorithm());
		assertEquals(docDO.getAlgorithm(), docVO.getAlgorithm());
		assertEquals(docDO.getAcquisitions(), docDO2.getAcquisitions());
		assertEquals(docDO.getAcquisitions(), docVO.getAcquisitions());
		assertEquals(docDO.getSignatures(), docDO2.getSignatures() );
		assertEquals(docDO.getSignatures(), docVO.getSignatures());
		
	}
	
	

}
