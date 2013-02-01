package fr.univartois.ili.sadoc.ws.qrcode.qrcodeGeneration.core;

/**
 * Thrown when a barcode was successfully detected and decoded, but
 * was not returned because its checksum feature failed.
 *
 * @author Sean Owen
 */
public final class ChecksumException extends ReaderException {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private static final ChecksumException instance = new ChecksumException();

  private ChecksumException() {
    // do nothing
  }

  public static ChecksumException getChecksumInstance() {
    return instance;
  }

}