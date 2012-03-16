package fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.WriterException;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.qrcode.decoder.ErrorCorrectionLevel;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.qrcode.encoder.Encoder;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.qrcode.encoder.QRCode;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.utils.QRCProperties;

/**
 * @author francois
 * 
 */
public final class QRCodeWriterManager2 {

	private static QRCodeWriterManager2 instance;

	private static final int MAX_SIZE_DATA = 75;
	private static final int ZOOM_MAX = 10;
	private int zoom = 1;
	/*
	 * private static final int SIZE_MODULE = 4; private static final int
	 * INIT_SIZE_VERSION = 17;
	 */
	private ErrorCorrectionLevel ecLevel;
	/*
	 * private int matrixWidth; private int version; private int maskPattern;
	 * private int numTotalBytes; private int numDataBytes; private int
	 * numECBytes; private int numRSBlocks;
	 */
	private QRCode qrc;

	private String data;
	private String name;

	private QRCProperties props;

	/**
	 * Constructor
	 */
	private QRCodeWriterManager2() {
		qrc = new QRCode();
		// A mettre en param
		// L ~ 7%, M ~ 15%, Q ~ 25%, H ~ 30%
		this.ecLevel = ErrorCorrectionLevel.M;
		// Param auto-genere
		/*
		 * this.version = 1; this.matrixWidth = INIT_SIZE_VERSION +
		 * (version-1)*SIZE_MODULE; this.maskPattern = 0; this.numTotalBytes =
		 * 26; this.numDataBytes = 9; this.numECBytes = this.numTotalBytes -
		 * this.numDataBytes; // 17 this.numRSBlocks = 1;
		 */
		this.setParam();
		this.setZoom(zoom);

		this.props = QRCProperties.getInstance();

		instance = this;
	}

	/**
	 * @return instance
	 */
	public static QRCodeWriterManager2 getInstance() {
		if (instance == null) {
			new QRCodeWriterManager2();
		}

		return instance;
	}

	/**
	 * @param name
	 * @param data
	 * @return
	 */
	public QRCodeWriterManager2 encode(String data) {
		this.processData(data);

		this.encodeData();

		return this;
	}

	/**
	 * @param data
	 */
	private void processData(String data) {
		this.data = this.props.getPrefixURL() + data;
		
		if (this.data.length() < MAX_SIZE_DATA) {
			for (int i = 0; i < (MAX_SIZE_DATA - this.data.length()); i++){
				this.data += " ";
			}
		} else {
			this.data = data.substring(0, MAX_SIZE_DATA);
		}
	}

	/**
	 * 
	 * @param zoom
	 */
	private void setZoom(int zoom) {
		if (zoom < 1){
			this.zoom = 1;
		}else if (zoom > ZOOM_MAX){
			this.zoom = ZOOM_MAX;
		}else{
			this.zoom = zoom;
		}
	}

	/**
	 * Set parameter
	 */
	private void setParam() {
		// Facultatif...
		// qrc.setMode(Mode.ALPHANUMERIC);

		qrc.setECLevel(this.ecLevel);

		// Param auto-genere
		/*
		 * qrc.setVersion(this.version); qrc.setMatrixWidth(this.matrixWidth);
		 * qrc.setMaskPattern(this.maskPattern);
		 * qrc.setNumTotalBytes(this.numTotalBytes);
		 * qrc.setNumDataBytes(this.numDataBytes);
		 * qrc.setNumECBytes(this.numECBytes);
		 * qrc.setNumRSBlocks(this.numRSBlocks);
		 */
	}

	/**
	 * Encode data
	 */
	private void encodeData() {
		try {
			Encoder.encode(this.data, qrc.getECLevel(), qrc);
		} catch (WriterException e) {
//			System.err.println("Probleme d'encodage !");
			e.printStackTrace();
		}
	}

	/**
	 * Get picture
	 * 
	 * @return
	 */
	public BufferedImage getImage() {
//		System.out.println("TEST : \n QRC valide ? : " + qrc.isValid() + "\n"
//				+ qrc.toString());

		byte[][] matriceQRC = qrc.getMatrix().getArray();

		System.out.println("Taille de la matrice : " + matriceQRC.length
				+ " x " + matriceQRC.length);

		BufferedImage imgQRC = new BufferedImage(matriceQRC.length * this.zoom,
				matriceQRC.length * this.zoom, BufferedImage.TYPE_INT_RGB);

		int i = 0, j = 0;
		for (i = 0; i < matriceQRC.length; i++) {
			for (j = 0; j < matriceQRC.length; j++) {
				int couleur = -1;
				if (matriceQRC[j][i] == 0){
					couleur = Color.WHITE.getRGB();
				}else{
					couleur = Color.BLACK.getRGB();
				}

				this.drawImageZoom(imgQRC, i, j, couleur);
			}
		}

		return imgQRC;
	}

	/**
	 * 
	 * @param imgQRC
	 * @param i
	 * @param j
	 * @param couleur
	 */
	private void drawImageZoom(BufferedImage imgQRC, int i, int j, int couleur) {
		for (int k = 0; k < this.zoom; k++) {
			for (int l = 0; l < this.zoom; l++) {
				imgQRC.setRGB(i * this.zoom + l, j * this.zoom + k, couleur);
			}
		}
	}

	/**
	 * Get the file image
	 * 
	 * @param name
	 */
	public void getFileImage(String name) {
		this.name = name;
		BufferedImage imgQRC = this.getImage();
		File fileImgQRC = new File(this.props.getPath() + this.name + "."
				+ this.props.getFormat());

		try {
			ImageIO.write(imgQRC, this.props.getFormat(), fileImgQRC);

//			System.out.println("\nImage genere !!!");
		} catch (IOException e) {
//			System.err.println("Probleme d'ecriture !");
			e.printStackTrace();
		}

		// Pour voir les formats acceptes
		// String formats[] = ImageIO.getWriterFormatNames();
		// for (int l = 0; l < formats.length; l++)
		// System.out.println(formats[l]);
	}
}
