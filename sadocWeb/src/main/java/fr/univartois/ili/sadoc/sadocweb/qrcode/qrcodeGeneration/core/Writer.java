package fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core;

import java.util.Hashtable;

import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.common.BitMatrix;

/**
 * The base class for all objects which encode/generate a barcode image.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 */
public interface Writer {

  /**
   * Encode a barcode using the default settings.
   *
   * @param contents The contents to encode in the barcode
   * @param format The barcode format to generate
   * @param width The preferred width in pixels
   * @param height The preferred height in pixels
   * @return The generated barcode as a Matrix of unsigned bytes (0 == black, 255 == white)
   */
  BitMatrix encode(String contents, BarcodeFormat format, int width, int height)
      throws WriterException;

  /**
   *
   * @param contents The contents to encode in the barcode
   * @param format The barcode format to generate
   * @param width The preferred width in pixels
   * @param height The preferred height in pixels
   * @param hints Additional parameters to supply to the encoder
   * @return The generated barcode as a Matrix of unsigned bytes (0 == black, 255 == white)
   */
  BitMatrix encode(String contents, BarcodeFormat format, int width, int height, Hashtable hints)
      throws WriterException;

}
