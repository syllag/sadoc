package fr.univartois.ili.sadoc.ws.qrcode.qrcodeGeneration.core;

/**
 * Callback which is invoked when a possible result point (significant
 * point in the barcode image such as a corner) is found.
 *
 * @see DecodeHintType#NEED_RESULT_POINT_CALLBACK
 */
public interface ResultPointCallback {

  void foundPossibleResultPoint(ResultPoint point);

}
