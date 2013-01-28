package fr.univartois.ili.sadoc.constante;

public abstract class Request {
	
	public static final String FIND_OWNER_BY_MAIL = "SELECT o FROM Owner o WHERE o.mail = :mail";
	public static final String FIND_COMPETENCE_BY_ACRONYM = "SELECT c FROM Competence c WHERE c.acronym = :acronym";
	public static final String FIND_IN_SIGNATURE_BY_OWNER = "SELECT s FROM Signature s WHERE s.owner = :owner";
	public static final String FIND_IN_SIGNATURE_BY_DOCUMENT = "SELECT s FROM Signature s WHERE s.document = :document";
	public static final String FIND_IN_SIGNATURE_BY_COMPETENCE = "SELECT s FROM Signature s WHERE s.competence = :competence";
	public static final String FIND_IN_SIGNATURE_BY_CERTIFICATE = "SELECT s FROM Signature s WHERE s.certificate = :certificate";
	public static final String FIND_IN_CERTIFICATE_BY_OWNER = "SELECT s FROM Certificate s WHERE s.owner = :owner";
	public static final String FIND_DOCUMENT_IN_SIGNATURE_BY_OWNER = "SELECT s.document FROM Signature s WHERE s.owner = :owner";
	public static final String FIND_OWNER_IN_SIGNATURE_BY_DOCUMENT = "SELECT s.owner FROM Signature s WHERE s.document = :document";
	public static final String FIND_COMPETENCE_IN_SIGNATURE_BY_DOCUMENT = "SELECT DISTINCT s.competence FROM Signature s WHERE s.document = :document";

}
