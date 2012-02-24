package fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.qrcode.encoder;

final class BlockPair {

  private final byte[] dataBytes;
  private final byte[] errorCorrectionBytes;

  BlockPair(byte[] data, byte[] errorCorrection) {
    dataBytes = data.clone();
    errorCorrectionBytes = errorCorrection.clone();
  }

  public byte[] getDataBytes() {
    return dataBytes;
  }

  public byte[] getErrorCorrectionBytes() {
    return errorCorrectionBytes;
  }

}
