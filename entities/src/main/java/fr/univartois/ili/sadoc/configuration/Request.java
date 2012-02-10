package fr.univartois.ili.sadoc.configuration;

public abstract class Request {
	
	public static final String FIND_IN_SIGNATURE_BY_OWNER = "SELECT s FROM Signature s WHERE s.owner = :owner";
	public static final String FIND_IN_SIGNATURE_BY_DOCUMENT = "SELECT s FROM Signature s WHERE s.document = :document";
	public static final String FIND_IN_SIGNATURE_BY_COMPETENCE = "SELECT s FROM Signature s WHERE s.competence = :competence";
	public static final String FIND_IN_SIGNATURE_BY_CERTIFICATE = "SELECT s FROM Signature s WHERE s.certificate = :certificate";
	public static final String FIND_IN_CERTIFICATE_BY_OWNER = "SELECT c FROM Certificate c WHERE c.owner = :owner";

}
