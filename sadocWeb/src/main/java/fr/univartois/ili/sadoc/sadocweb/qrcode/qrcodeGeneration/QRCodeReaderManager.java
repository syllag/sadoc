package fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.common.HybridBinarizer;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.BinaryBitmap;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.BufferedImageLuminanceSource;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.ChecksumException;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.FormatException;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.LuminanceSource;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.NotFoundException;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.Result;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.qrcode.QRCodeReader;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.utils.QRCProperties;

/**
 * @author francois
 * 
 */
public class QRCodeReaderManager {

	private static QRCodeReaderManager instance;

	private QRCProperties props;

	private String name;

	/**
	 * Constructor
	 */
	private QRCodeReaderManager() {
		props = QRCProperties.getInstance();

		instance = this;
	}

	/**
	 * @return instance
	 */
	public static QRCodeReaderManager getInstance() {
		if (instance == null) {
			new QRCodeReaderManager();
		}

		return instance;
	}

	/**
	 * @param bi
	 * @return a string
	 */
	public String decodeImage(BufferedImage bi) {
		return this.decodeData(bi);
	}

	/**
	 * @param name
	 * @return a string
	 */
	public String decodeFileImage(String name) {
		this.name = name;

		return this.decodeData(null);
	}

	/**
	 * 
	 */
	private String decodeData(BufferedImage bi) {
		// Ouverture du fichier
		File fileImgQRC = null;
		// Lecture de l'image dans un buffer
		BufferedImage imgQRC = null;
		// Decodage des donnees de l'image
		LuminanceSource ls = null;
		Result result = null;

		try {
			if (bi != null) {
				imgQRC = bi;
			} else {
				fileImgQRC = new File(this.props.getPath() + this.name + "."
						+ this.props.getFormat());
				imgQRC = ImageIO.read(fileImgQRC);
			}

			ls = new BufferedImageLuminanceSource(imgQRC);

			result = new QRCodeReader().decode(new BinaryBitmap(
					new HybridBinarizer(ls)));

		} catch (NullPointerException e) {
			System.err
					.println("Probleme d'ouverture d'un fichier dans QRCodeReaderManager -> "
							+ this.props.getPath()
							+ this.name
							+ "."
							+ this.props.getFormat());
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.err
					.println("Probleme de lecture de l'image dans QRCodeReaderManager -> "
							+ this.name + "." + this.props.getFormat());
			e.printStackTrace();
			return null;
		} catch (NotFoundException e) {
			System.err
					.println("Probleme de decodage d'un fichier dans QRCodeReaderManager -> "
							+ this.name + "." + this.props.getFormat());
			e.printStackTrace();
			return null;
		} catch (ChecksumException e) {
			System.err
					.println("Probleme de decodage d'un fichier dans QRCodeReaderManager -> "
							+ this.name + "." + this.props.getFormat());
			e.printStackTrace();
			return null;
		} catch (FormatException e) {
			System.err
					.println("Probleme de decodage d'un fichier dans QRCodeReaderManager -> "
							+ this.name + "." + this.props.getFormat());
			e.printStackTrace();
			return null;
		}

		// Affichage des donnees
		System.out.println(new StringBuffer("Image QRC decodee : ")
				.append(this.name).append(" -> ").append(result.getText()));
		return result.getText();
	}

}
