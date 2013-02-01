
package fr.univartois.ili.sadoc.client.webservice.tools;

import java.math.BigInteger;
import javax.activation.DataHandler;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sadoc.ac.schemas package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _IdDocument_QNAME = new QName("http://sadoc.com/ac/schemas", "idDocument");
    private final static QName _Pk7_QNAME = new QName("http://sadoc.com/ac/schemas", "pk7");
    private final static QName _FirstName_QNAME = new QName("http://sadoc.com/ac/schemas", "firstName");
    private final static QName _Doc_QNAME = new QName("http://sadoc.com/ac/schemas", "doc");
    private final static QName _Name_QNAME = new QName("http://sadoc.com/ac/schemas", "name");
    private final static QName _Description_QNAME = new QName("http://sadoc.com/ac/schemas", "description");
    private final static QName _CreationDate_QNAME = new QName("http://sadoc.com/ac/schemas", "creationDate");
    private final static QName _Id_QNAME = new QName("http://sadoc.com/ac/schemas", "id");
    private final static QName _CheckSum_QNAME = new QName("http://sadoc.com/ac/schemas", "checkSum");
    private final static QName _Validation_QNAME = new QName("http://sadoc.com/ac/schemas", "validation");
    private final static QName _Acronym_QNAME = new QName("http://sadoc.com/ac/schemas", "acronym");
    private final static QName _LastName_QNAME = new QName("http://sadoc.com/ac/schemas", "lastName");
    private final static QName _Mail_QNAME = new QName("http://sadoc.com/ac/schemas", "mail");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sadoc.ac.schemas
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ImportCompetencesRequest }
     * 
     */
    public ImportCompetencesRequest createImportCompetencesRequest() {
        return new ImportCompetencesRequest();
    }

    /**
     * Create an instance of {@link Owner }
     * 
     */
    public Owner createOwner() {
        return new Owner();
    }

    /**
     * Create an instance of {@link GetOwnerRequest }
     * 
     */
    public GetOwnerRequest createGetOwnerRequest() {
        return new GetOwnerRequest();
    }

    /**
     * Create an instance of {@link ImportDocumentRequest }
     * 
     */
    public ImportDocumentRequest createImportDocumentRequest() {
        return new ImportDocumentRequest();
    }

    /**
     * Create an instance of {@link CreateOwnerRequest }
     * 
     */
    public CreateOwnerRequest createCreateOwnerRequest() {
        return new CreateOwnerRequest();
    }

    /**
     * Create an instance of {@link CreateOwnerResponse }
     * 
     */
    public CreateOwnerResponse createCreateOwnerResponse() {
        return new CreateOwnerResponse();
    }

    /**
     * Create an instance of {@link GetDocumentInformationsRequest }
     * 
     */
    public GetDocumentInformationsRequest createGetDocumentInformationsRequest() {
        return new GetDocumentInformationsRequest();
    }

    /**
     * Create an instance of {@link CreateCertificateRequest }
     * 
     */
    public CreateCertificateRequest createCreateCertificateRequest() {
        return new CreateCertificateRequest();
    }

    /**
     * Create an instance of {@link SignDocumentResponse }
     * 
     */
    public SignDocumentResponse createSignDocumentResponse() {
        return new SignDocumentResponse();
    }

    /**
     * Create an instance of {@link VerifyDocumentRequest }
     * 
     */
    public VerifyDocumentRequest createVerifyDocumentRequest() {
        return new VerifyDocumentRequest();
    }

    /**
     * Create an instance of {@link GetDocumentRequest }
     * 
     */
    public GetDocumentRequest createGetDocumentRequest() {
        return new GetDocumentRequest();
    }

    /**
     * Create an instance of {@link CreateCertificateResponse }
     * 
     */
    public CreateCertificateResponse createCreateCertificateResponse() {
        return new CreateCertificateResponse();
    }

    /**
     * Create an instance of {@link GetDocumentResponse }
     * 
     */
    public GetDocumentResponse createGetDocumentResponse() {
        return new GetDocumentResponse();
    }

    /**
     * Create an instance of {@link ImportDocumentResponse }
     * 
     */
    public ImportDocumentResponse createImportDocumentResponse() {
        return new ImportDocumentResponse();
    }

    /**
     * Create an instance of {@link Document }
     * 
     */
    public Document createDocument() {
        return new Document();
    }

    /**
     * Create an instance of {@link GetDocumentInformationsResponse }
     * 
     */
    public GetDocumentInformationsResponse createGetDocumentInformationsResponse() {
        return new GetDocumentInformationsResponse();
    }

    /**
     * Create an instance of {@link VerifyDocumentResponse }
     * 
     */
    public VerifyDocumentResponse createVerifyDocumentResponse() {
        return new VerifyDocumentResponse();
    }

    /**
     * Create an instance of {@link ImportCompetencesResponse }
     * 
     */
    public ImportCompetencesResponse createImportCompetencesResponse() {
        return new ImportCompetencesResponse();
    }

    /**
     * Create an instance of {@link Competence }
     * 
     */
    public Competence createCompetence() {
        return new Competence();
    }

    /**
     * Create an instance of {@link SignDocumentRequest }
     * 
     */
    public SignDocumentRequest createSignDocumentRequest() {
        return new SignDocumentRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sadoc.com/ac/schemas", name = "idDocument")
    public JAXBElement<BigInteger> createIdDocument(BigInteger value) {
        return new JAXBElement<BigInteger>(_IdDocument_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataHandler }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sadoc.com/ac/schemas", name = "pk7")
    @XmlMimeType("*/*")
    public JAXBElement<DataHandler> createPk7(DataHandler value) {
        return new JAXBElement<DataHandler>(_Pk7_QNAME, DataHandler.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sadoc.com/ac/schemas", name = "firstName")
    public JAXBElement<String> createFirstName(String value) {
        return new JAXBElement<String>(_FirstName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataHandler }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sadoc.com/ac/schemas", name = "doc")
    @XmlMimeType("*/*")
    public JAXBElement<DataHandler> createDoc(DataHandler value) {
        return new JAXBElement<DataHandler>(_Doc_QNAME, DataHandler.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sadoc.com/ac/schemas", name = "name")
    public JAXBElement<String> createName(String value) {
        return new JAXBElement<String>(_Name_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sadoc.com/ac/schemas", name = "description")
    public JAXBElement<String> createDescription(String value) {
        return new JAXBElement<String>(_Description_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sadoc.com/ac/schemas", name = "creationDate")
    public JAXBElement<XMLGregorianCalendar> createCreationDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_CreationDate_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sadoc.com/ac/schemas", name = "id")
    public JAXBElement<BigInteger> createId(BigInteger value) {
        return new JAXBElement<BigInteger>(_Id_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sadoc.com/ac/schemas", name = "checkSum")
    public JAXBElement<String> createCheckSum(String value) {
        return new JAXBElement<String>(_CheckSum_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sadoc.com/ac/schemas", name = "validation")
    public JAXBElement<Boolean> createValidation(Boolean value) {
        return new JAXBElement<Boolean>(_Validation_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sadoc.com/ac/schemas", name = "acronym")
    public JAXBElement<String> createAcronym(String value) {
        return new JAXBElement<String>(_Acronym_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sadoc.com/ac/schemas", name = "lastName")
    public JAXBElement<String> createLastName(String value) {
        return new JAXBElement<String>(_LastName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sadoc.com/ac/schemas", name = "mail")
    public JAXBElement<String> createMail(String value) {
        return new JAXBElement<String>(_Mail_QNAME, String.class, null, value);
    }

}
