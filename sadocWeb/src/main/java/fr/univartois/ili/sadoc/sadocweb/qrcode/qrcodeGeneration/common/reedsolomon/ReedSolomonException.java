package fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.common.reedsolomon;

/**
 * <p>Thrown when an exception occurs during Reed-Solomon decoding, such as when
 * there are too many errors to correct.</p>
 *
 * @author Sean Owen
 */
public final class ReedSolomonException extends Exception {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public ReedSolomonException(String message) {
    super(message);
  }

}