package fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.utils;

import java.util.ResourceBundle;

public class MyProperties {
	
	private static MyProperties instance;
	
	public static final String NAME_FILE_PROPERTIES = "qrc";
	
	public static final String PATH_KEY = "pathRW";
	public static final String FORMAT_KEY = "format";
	public static final String SIZE_KEY = "size";
	
	private String path;
	private String format;
	private int size;
	
	/**
	 * Constructor
	 */
	private MyProperties() {
		ResourceBundle bundle = ResourceBundle.getBundle(NAME_FILE_PROPERTIES);
		this.path = bundle.getString(PATH_KEY);
		this.format = bundle.getString(FORMAT_KEY);
		this.size = Integer.valueOf(bundle.getString(SIZE_KEY));
		
		instance = this;
	}
	
	/**
	 * @return instance
	 */
	public static MyProperties getInstance() {
		if (instance == null) {
			new MyProperties();
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

}
