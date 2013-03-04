package fr.univartois.ili.sadoc.metier.ws.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.PrivateKey;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import fr.univartois.ili.sadoc.dao.entities.OwnerWS;
import fr.univartois.ili.sadoc.metier.ws.certificate.CertificatePGP;
import fr.univartois.ili.sadoc.metier.ws.certificate.CertificatePublicKey;
import fr.univartois.ili.sadoc.metier.ws.certificate.CertificateX509;
import fr.univartois.ili.sadoc.metier.ws.vo.Acquisition;
import fr.univartois.ili.sadoc.metier.ws.vo.Certificate;
import fr.univartois.ili.sadoc.metier.ws.vo.Document;
import fr.univartois.ili.sadoc.metier.ws.vo.Signature;

public class Mapper {

	/**
	 * Convert DocumentVO to DocumentDO Parses the DocumentVO interpreting its
	 * content as a DocumentDO.
	 * 
	 * @param documentVO
	 * @return documentDO
	 */

	public final static fr.univartois.ili.sadoc.dao.entities.Document documentVOToDocumentDO(
			Document docVO) {
		List<fr.univartois.ili.sadoc.dao.entities.Signature> signs = new ArrayList<fr.univartois.ili.sadoc.dao.entities.Signature>();
		List<fr.univartois.ili.sadoc.dao.entities.Acquisition> acquis = new ArrayList<fr.univartois.ili.sadoc.dao.entities.Acquisition>();
		fr.univartois.ili.sadoc.dao.entities.Document docDO = new fr.univartois.ili.sadoc.dao.entities.Document();
		docDO.setId(docVO.getId());
		docDO.setName(docVO.getName());
		docDO.setUrl(docVO.getUrl());
		docDO.setChecksum(docVO.getCheckSum());
		docDO.setCreationDate(docVO.getCreationDate());
		docDO.setAlgorithm(docVO.getAlgorithm());
		for (Signature sign : docVO.getSignatures())
			signs.add(signatureVOToSignatureDO(sign));
		for (Acquisition acqui : docVO.getAcquisitions())
			acquis.add(acquisitionVOToAcquisitionDO(acqui));
		docDO.setSignatures(signs);
		docDO.setAcquisitions(acquis);
		return docDO;
	}

	/**
	 * Convert DocumentDO to DocumentVO Parses the DocumentDO interpreting its
	 * content as a DocumentVO.
	 * 
	 * @param documentDO
	 * @return documentVO
	 */
	public final static Document documentDOToDocumentVO(
			fr.univartois.ili.sadoc.dao.entities.Document docDO) {
		List<Signature> signs = new ArrayList<Signature>();
		List<Acquisition> acquis = new ArrayList<Acquisition>();
		Document docVO = new Document();
		docVO.setId(docDO.getId());
		docVO.setName(docDO.getName());
		docVO.setAlgorithm(docDO.getAlgorithm());
		docVO.setCheckSum(docDO.getChecksum());
		docVO.setUrl(docDO.getUrl());
		docVO.setCreationDate(docDO.getCreationDate());
		for (fr.univartois.ili.sadoc.dao.entities.Signature sign : docDO
				.getSignatures())
			signs.add(signatureDOToSignatureVO(sign));
		for (fr.univartois.ili.sadoc.dao.entities.Acquisition acqui : docDO
				.getAcquisitions())
			acquis.add(acquisitionDOToAcquisitionVO(acqui));
		docVO.setSignatures(signs);
		docVO.setAcquisitions(acquis);
		return docVO;
	}

	/**
	 * ConvertCertificateVO to CertificateDO Parses the CertificateVO
	 * interpreting its content as a CertificateDO.
	 * 
	 * @param CertificateVO
	 * @return CertificateDO
	 * @throws SQLException
	 * @throws IOException
	 */

	public final static fr.univartois.ili.sadoc.dao.entities.Certificate certificateVOToCertificateDO(
			Certificate certVO) throws SQLException, IOException {
		fr.univartois.ili.sadoc.dao.entities.Certificate certDO = new fr.univartois.ili.sadoc.dao.entities.Certificate();
		List<fr.univartois.ili.sadoc.dao.entities.Signature> signs = new ArrayList<fr.univartois.ili.sadoc.dao.entities.Signature>();
		certDO.setId(certVO.getId());
		certDO.setDateValidity(certVO.getDateValidity());
		OwnerWS ows = new OwnerWS();
		ows.setId(certVO.getIdOwner());
		certDO.setOwnerWs(ows);
		for (Signature sign : certVO.getSignatures())
			signs.add(signatureVOToSignatureDO(sign));
		certDO.setSignatures(signs);

		certDO.setPrivateKey(objectToBlob(certVO.getPrivateKey()));
		certDO.setCertificate(objectToBlob(certVO.getCertificate()));
		certDO.setCertificateType(certVO.getCertificate().getType());

		return certDO;
	}

	/**
	 * @throws IOException
	 * @throws SQLException
	 * @throws SerialException
	 * 
	 */

	public final static <T> Blob objectToBlob(T t) throws IOException,
			SerialException, SQLException {
		ByteArrayOutputStream baos;
		ObjectOutputStream out;

		baos = new ByteArrayOutputStream();
		out = new ObjectOutputStream(baos);

		out.writeObject(t);
		out.close();

		Blob blob = new SerialBlob(baos.toByteArray());
		return blob;

	}

	/**
	 * ConvertCertificateDO to CertificateVO Parses the CertificateDO
	 * interpreting its content as a CertificateVO.
	 * 
	 * @param CertificateDO
	 * @return CertificateVO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public final static Certificate certificateDOToCertificateVO(
			fr.univartois.ili.sadoc.dao.entities.Certificate certDO)
			throws ClassNotFoundException, SQLException, IOException {
		Certificate certVO = new Certificate();
		List<Signature> signs = new ArrayList<Signature>();
		certVO.setId(certDO.getId());
		certVO.setDateValidity(certDO.getDateValidity());
		certVO.setIdOwner(certDO.getOwnerWs().getId());

		for (fr.univartois.ili.sadoc.dao.entities.Signature sign : certDO
				.getSignatures())
			signs.add(signatureDOToSignatureVO(sign));
		certVO.setSignatures(signs);
		
		// Blob To PrivateKey		
		certVO.setPrivateKey(blobToObject(certDO.getPrivateKey(), PrivateKey.class ));
		// Blob To BaseCertificate (X509, PGP or PublicKey)		
		switch (certDO.getCertificateType()) {
		case X509:
			certVO.setCertificate(blobToObject(certDO.getCertificate(), CertificateX509.class));
			break;
		case PGP:	
			certVO.setCertificate(blobToObject(certDO.getCertificate(), CertificatePGP.class));
			break;
		case PublicKey:	
			certVO.setCertificate(blobToObject(certDO.getCertificate(), CertificatePublicKey.class));
			break;
		default:
			throw new IllegalArgumentException("Certificate type must be X509, PGP or PublicKey");
		}
		return certVO;
	}

	/**
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public final static <T> T blobToObject(Blob b, Class<T> clazz)
			throws SQLException, IOException, ClassNotFoundException {
		ByteArrayInputStream bais;
		ObjectInputStream in;

		bais = new ByteArrayInputStream(b.getBytes(1,(int) b.length()));
		in = new ObjectInputStream(bais);

		T object = (T) in.readObject();
		in.close();
		return (T) object;
	}

	/**
	 * ConvertSignatureVO to SignatureDO Parses the SignatureVO interpreting its
	 * content as a SignatureDO.
	 * 
	 * @param SignatureVO
	 * @return SignatureDO
	 */
	public final static fr.univartois.ili.sadoc.dao.entities.Signature signatureVOToSignatureDO(
			Signature signVO) {
		fr.univartois.ili.sadoc.dao.entities.Signature signDO = new fr.univartois.ili.sadoc.dao.entities.Signature();
		signDO.setId(signVO.getId());
		signDO.setDocument(signVO.getDocument());
		signDO.setCertificate(signVO.getCertificate());
		signDO.setDateSignature(signVO.getCreationDate());
		return signDO;
	}

	/**
	 * ConvertSignatureDO to SignatureVO Parses the SignatureDO interpreting its
	 * content as a SignatureVO.
	 * 
	 * @param SignatureDO
	 * @return SignatureVO
	 */
	public final static Signature signatureDOToSignatureVO(
			fr.univartois.ili.sadoc.dao.entities.Signature signDO) {
		Signature signVO = new Signature();
		signVO.setId(signDO.getId());
		signVO.setCreationDate(signDO.getDateSignature());
		signVO.setDocument(signDO.getDocument());
		signVO.setCertificate(signDO.getCertificate());
		return signVO;
	}

	/**
	 * Convert AcquisitionVO to AcquisitionDO Parses the AcquisitionVO
	 * interpreting its content as a AcquisitionDO.
	 * 
	 * @param AcquisitionVO
	 * @return AcquisitionDO
	 */
	public final static fr.univartois.ili.sadoc.dao.entities.Acquisition acquisitionVOToAcquisitionDO(
			Acquisition acqVO) {
		fr.univartois.ili.sadoc.dao.entities.Acquisition acqDO = new fr.univartois.ili.sadoc.dao.entities.Acquisition();
		List<fr.univartois.ili.sadoc.dao.entities.Document> docs = new ArrayList<fr.univartois.ili.sadoc.dao.entities.Document>();
		acqDO.setId(acqVO.getId());
		acqDO.setCreationDate(acqVO.getCreationDate());
		acqDO.setId_item(acqVO.getId_item());

		for (Document doc : acqVO.getDocuments())
			docs.add(documentVOToDocumentDO(doc));
		acqDO.setDocuments(docs);

		return acqDO;

	}

	/**
	 * Convert AcquisitionDO to AcquisitionVO Parses the AcquisitionDO
	 * interpreting its content as a AcquisitionVO.
	 * 
	 * @param AcquisitionDO
	 * @return AcquisitionVO
	 */
	public final static Acquisition acquisitionDOToAcquisitionVO(
			fr.univartois.ili.sadoc.dao.entities.Acquisition acqDO) {
		Acquisition acqVO = new Acquisition();
		List<Document> docs = new ArrayList<Document>();
		acqVO.setId(acqDO.getId());
		acqVO.setCreationDate(acqDO.getCreationDate());
		acqVO.setId_item(acqDO.getId_item());

		for (fr.univartois.ili.sadoc.dao.entities.Document doc : acqDO
				.getDocuments())
			docs.add(documentDOToDocumentVO(doc));
		acqVO.setDocuments(docs);

		return acqVO;

	}
}
