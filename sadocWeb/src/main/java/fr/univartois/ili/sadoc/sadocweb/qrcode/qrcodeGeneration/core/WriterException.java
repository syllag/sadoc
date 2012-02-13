package fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core;

/**
 * A base class which covers the range of exceptions which may occur when encoding a barcode using
 * the Writer framework.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 */
public final class WriterException extends Exception {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public WriterException() {
    super();
  }

  public WriterException(String message) {
    super(message);
  }

}
