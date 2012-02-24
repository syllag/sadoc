package fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.common;

import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.ResultPoint;

/**
 * <p>Encapsulates the result of detecting a barcode in an image. This includes the raw
 * matrix of black/white pixels corresponding to the barcode, and possibly points of interest
 * in the image, like the location of finder patterns or corners of the barcode in the image.</p>
 *
 * @author Sean Owen
 */
public final class DetectorResult {

  private final BitMatrix bits;
  private final ResultPoint[] points;

  public DetectorResult(BitMatrix bits, ResultPoint[] points) {
    this.bits = bits;
    this.points = points.clone();
  }

  public BitMatrix getBits() {
    return bits;
  }

  public ResultPoint[] getPoints() {
    return points.clone();
  }

}