package fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.utils;

import java.util.ResourceBundle;

public final class QRCProperties {
	
	private static QRCProperties instance;
	
	private static final String NAME_FILE_PROPERTIES = "qrc";
	
	private static final String PATH_KEY = "pathRW";
	private static final String FORMAT_KEY = "format";
	private static final String SIZE_KEY = "size";
	private static final String PREFIX_URL_KEY = "prefixURL";
	
	private String path;
	private String format;
	private int size;
	private String prefixURL;
	
	/**
	 * Constructor
	 */
	private QRCProperties() {
		ResourceBundle bundle = ResourceBundle.getBundle(NAME_FILE_PROPERTIES);
		this.path = bundle.getString(PATH_KEY);
		this.format = bundle.getString(FORMAT_KEY);
		this.size = Integer.valueOf(bundle.getString(SIZE_KEY));
		this.prefixURL = bundle.getString(PREFIX_URL_KEY);
		
		instance = this;
	}
	
	/**
	 * @return instance
	 */
	public static QRCProperties getInstance() {
		if (instance == null) {
			new QRCProperties();
		}
		
		return instance;
		
	}

	/**
	 * @return
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return
	 */
	public String getPrefixURL() {
		return prefixURL;
	}

	/**
	 * @param prefixURL
	 */
	public void setPrefixURL(String prefixURL) {
		this.prefixURL = prefixURL;
	}

}
