package fr.univartois.ili.sadoc.metier.ui.vo;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 *
 */
public class Document implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String name;
	private String checkSum;
	private byte[] p7s;
	private String url;
	
	private Date creationDate;
	
	/************************************************/
	
	public Document(){}
	
	public Document(String name, String checkSum,String url,byte[] p7s,Date creationDate){
		this.name=name;
		this.checkSum=checkSum;
		this.url=url;
		this.p7s=p7s;
		this.creationDate=creationDate;
	}
	
	/************************************************/
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public boolean equals(Document document) {
		return this.id == document.getId();
	}

	public byte[] getP7s() {
		return p7s;
	}

	public void setP7s(byte[] p7s) {
		this.p7s = p7s;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	public static Document getFakeDocument() {
		byte[]tab=new byte[10];
		Date date=new Date(100000);
		return new Document("fakedanicet", "checksum","http://www.openoffice.org/fr/Documentation/How-to/General/02pdffr.pdf",tab, date);
	}
	
}
