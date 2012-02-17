package fr.univartois.ili.sadoc.configuration;

public abstract class Request {
	
	public static final String FIND_OWNER_BY_MAIL = "SELECT o FROM Owner o WHERE o.mail= :mail";
	public static final String FIND_IN_ACQUISITION_BY_OWNER = "SELECT a FROM Acquisition a WHERE a.owner = :owner";
	public static final String FIND_IN_ACQUISITION_BY_DOCUMENT = "SELECT a FROM Acquisition a WHERE a.document = :document";
	public static final String FIND_IN_ACQUISITION_BY_COMPETENCE = "SELECT a FROM Acquisition a WHERE a.competence = :competence";

}
