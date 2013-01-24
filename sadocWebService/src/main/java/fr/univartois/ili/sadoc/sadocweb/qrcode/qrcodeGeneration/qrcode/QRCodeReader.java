package fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.qrcode;

import java.util.Hashtable;


import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.common.BitMatrix;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.common.DecoderResult;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.common.DetectorResult;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.BarcodeFormat;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.BinaryBitmap;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.ChecksumException;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.DecodeHintType;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.FormatException;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.NotFoundException;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.Reader;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.Result;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.ResultMetadataType;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.ResultPoint;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.qrcode.decoder.Decoder;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.qrcode.detector.Detector;

/**
 * This implementation can detect and decode QR Codes in an image.
 *
 * @author Sean Owen
 */
public class QRCodeReader implements Reader {

  private static final ResultPoint[] NO_POINTS = new ResultPoint[0];

  private final Decoder decoder = new Decoder();

  protected Decoder getDecoder() {
    return decoder;
  }

  /**
   * Locates and decodes a QR code in an image.
   *
   * @return a String representing the content encoded by the QR code
   * @throws NotFoundException if a QR code cannot be found
   * @throws FormatException if a QR code cannot be decoded
   * @throws ChecksumException if error correction fails
   */
  public Result decode(BinaryBitmap image) throws NotFoundException, ChecksumException, FormatException {
    return decode(image, null);
  }

  public Result decode(BinaryBitmap image, Hashtable hints)
      throws NotFoundException, ChecksumException, FormatException {
    DecoderResult decoderResult;
    ResultPoint[] points;
    if (hints != null && hints.containsKey(DecodeHintType.PURE_BARCODE)) {
      BitMatrix bits = extractPureBits(image.getBlackMatrix());
      decoderResult = decoder.decode(bits, hints);
      points = NO_POINTS;
    } else {
      DetectorResult detectorResult = new Detector(image.getBlackMatrix()).detect(hints);
      decoderResult = decoder.decode(detectorResult.getBits(), hints);
      points = detectorResult.getPoints();
    }

    Result result = new Result(decoderResult.getText(), decoderResult.getRawBytes(), points, BarcodeFormat.QR_CODE);
    if (decoderResult.getByteSegments() != null) {
      result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, decoderResult.getByteSegments());
    }
    if (decoderResult.getECLevel() != null) {
      result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, decoderResult.getECLevel().toString());
    }
    return result;
  }

  public void reset() {
    // do nothing
  }

  /**
   * This method detects a barcode in a "pure" image -- that is, pure monochrome image
   * which contains only an unrotated, unskewed, image of a barcode, with some white border
   * around it. This is a specialized method that works exceptionally fast in this special
   * case.
   */
  public static BitMatrix extractPureBits(BitMatrix image) throws NotFoundException {

    int height = image.getHeight();
    int width = image.getWidth();
    int minDimension = Math.min(height, width);

    // And then keep tracking across the top-left black module to determine module size
    //int moduleEnd = borderWidth;
    int[] leftTopBlack = image.getTopLeftOnBit();
    if (leftTopBlack == null) {
      throw NotFoundException.getNotFoundInstance();
    }
    int x = leftTopBlack[0];
    int y = leftTopBlack[1];
    while (x < minDimension && y < minDimension && image.get(x, y)) {
      x++;
      y++;
    }
    if (x == minDimension || y == minDimension) {
      throw NotFoundException.getNotFoundInstance();
    }

    int moduleSize = x - leftTopBlack[0];
    if (moduleSize == 0) {
      throw NotFoundException.getNotFoundInstance();
    }

    // And now find where the rightmost black module on the first row ends
    int rowEndOfSymbol = width - 1;
    while (rowEndOfSymbol > x && !image.get(rowEndOfSymbol, y)) {
      rowEndOfSymbol--;
    }
    if (rowEndOfSymbol <= x) {
      throw NotFoundException.getNotFoundInstance();
    }
    rowEndOfSymbol++;

    // Make sure width of barcode is a multiple of module size
    if ((rowEndOfSymbol - x) % moduleSize != 0) {
      throw NotFoundException.getNotFoundInstance();
    }
    int dimension = 1 + ((rowEndOfSymbol - x) / moduleSize);

    // Push in the "border" by half the module width so that we start
    // sampling in the middle of the module. Just in case the image is a
    // little off, this will help recover. Need to back up at least 1.
    int backOffAmount = moduleSize == 1 ? 1 : moduleSize >> 1;
    x -= backOffAmount;
    y -= backOffAmount;

    if ((x + (dimension - 1) * moduleSize) >= width ||
        (y + (dimension - 1) * moduleSize) >= height) {
      throw NotFoundException.getNotFoundInstance();
    }

    // Now just read off the bits
    BitMatrix bits = new BitMatrix(dimension);
    for (int i = 0; i < dimension; i++) {
      int iOffset = y + i * moduleSize;
      for (int j = 0; j < dimension; j++) {
        if (image.get(x + j * moduleSize, iOffset)) {
          bits.set(j, i);
        }
      }
    }
    return bits;
  }

}
