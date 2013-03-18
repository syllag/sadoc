package fr.univartois.ili.sadoc.constante;

public interface Request {
	
	String FIND_OWNER_BY_MAIL = "SELECT o FROM OwnerWS o WHERE o.mail_initial = :mail";
	String FIND_IN_SIGNATURE_BY_OWNER = "SELECT s FROM OwnerWS o, IN(o.certificates) c,IN(c.signatures) s WHERE o = :owner";
	String FIND_IN_SIGNATURE_BY_DOCUMENT = "SELECT s FROM Signature s WHERE s.document = :document";
	String FIND_IN_SIGNATURE_BY_CERTIFICATE = "SELECT s FROM Signature s WHERE s.certificate = :certificate";
	String FIND_IN_CERTIFICATE_BY_OWNER = "SELECT c FROM Certificate c WHERE c.ownerWs = :owner";
	String FIND_DOCUMENT_IN_SIGNATURE_BY_OWNER = "Select distinct(s.document) FROM OwnerWS o, IN(o.certificates) c, IN(c.signatures) s where o=:owner";
	String FIND_OWNER_IN_SIGNATURE_BY_DOCUMENT = "Select distinct(o) FROM OwnerWS o, IN(o.certificates) c, IN(c.signatures) s, s.document=:document";
	String FIND_ACQUISITION_BY_DOCUMENT= "SELECT distinct(a) FROM Acquisition a WHERE :document MEMBER OF a.documents";
	String FIND_ACQUISITION_BY_OWNER = "Select distinct(a) FROM OwnerWS o, IN(o.certificates) c, IN(c.signatures) s, IN(s.document.acquisitions) a where o.id=:id";
	String FIND_ACQUISITION_BY_ACRONYM = "SELECT distinct(a) FROM Acquisition a WHERE a.id_item=:acronym";
	String FIND_DOCUMENTS_BY_OWNERWS = "Select distinct(d) FROM OwnerWS o, IN(o.certificates) c, IN(c.signatures) s, IN(s.document) d where o:=owner";

}