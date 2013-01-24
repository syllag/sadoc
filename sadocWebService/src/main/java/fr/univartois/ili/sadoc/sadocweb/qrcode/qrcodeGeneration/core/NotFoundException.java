package fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core;

/**
 * Thrown when a barcode was not found in the image. It might have been
 * partially detected but could not be confirmed.
 *
 * @author Sean Owen
 */
public final class NotFoundException extends ReaderException {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private static final NotFoundException instance = new NotFoundException();

  private NotFoundException() {
    // do nothing
  }

  public static NotFoundException getNotFoundInstance() {
    return instance;
  }

}