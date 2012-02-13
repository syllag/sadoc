package fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.common.BitMatrix;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.BarcodeFormat;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.MatrixToImageWriter;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.WriterException;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.qrcode.QRCodeWriter;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.utils.MyProperties;

/**
 * @author francois
 * 
 */
public class QRCodeWriterManager {

	private static QRCodeWriterManager instance;

	private static final int MAX_SIZE_DATA = 60;

	private MyProperties props;

	private String name;
	private String data;

	/**
	 * Constructor
	 */
	private QRCodeWriterManager() {
		props = MyProperties.getInstance();

		instance = this;
	}

	public static QRCodeWriterManager getInstance() {
		if (instance == null) {
			new QRCodeWriterManager();
		}

		return instance;
	}

	/**
	 * @return
	 */
	public MyProperties getProps() {
		return props;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param name
	 * @param data
	 * @return
	 */
	public QRCodeWriterManager encode(String name, String data) {
		this.processData(data);
		this.name = name;

		this.encodeData();

		return this;
	}

	/**
	 * @param data
	 */
	private void processData(String data) {
		if (data.length() < MAX_SIZE_DATA) {
			this.data = data;
			for (int i = 0; i < (MAX_SIZE_DATA - data.length()); i++)
				this.data += " ";
		} else {
			this.data = data.substring(0, MAX_SIZE_DATA);
		}
	}

	/**
	 * @return
	 */
	private BitMatrix encodeData() {
		QRCodeWriter writer = new QRCodeWriter();

		// Encodage des donnees en image
		BitMatrix bitMatrix = null;
		try {
			bitMatrix = writer.encode(this.data, BarcodeFormat.QR_CODE,
					this.props.getSize(), this.props.getSize());
		} catch (WriterException e) {
			System.err
					.println("Probleme d'encodage d'un fichier dans QRCmainEnc -> "
							+ this.name + "." + this.props.getFormat());
			e.printStackTrace();

			return null;
		}

		return bitMatrix;
	}

	/**
	 * Get picture
	 * 
	 * @return
	 */
	public BufferedImage getImage() {
		BufferedImage imgQRC = MatrixToImageWriter.toBufferedImage(this
				.encodeData());

		return imgQRC;

	}

	/**
	 * Get the file image
	 */
	public void getFileImage() {
		BufferedImage imgQRC = this.getImage();

		File fileImgQRC = null;
		try {
			// Ouverture du fichier
			fileImgQRC = new File(this.props.getPath() + this.name + "."
					+ this.props.getFormat());
			// Ecriture de l'image dans le fichier
			ImageIO.write(imgQRC, this.props.getFormat(), fileImgQRC);
			System.out.println(new StringBuffer(
					"Image QRC encodee et generee : ").append(this.name)
					.append(".").append(this.props.getFormat()).append(" -> ")
					.append(this.data));
		} catch (NullPointerException e) {
			System.err
					.println("Probleme d'ouverture d'un fichier dans QRCmainEnc -> "
							+ this.props.getPath()
							+ this.name
							+ "."
							+ this.props.getFormat());
			e.printStackTrace();
			return;
		} catch (IOException e) {
			System.err
					.println("Probleme d'ecriture de l'image dans QRCmainEnc -> "
							+ this.name + "." + this.props.getFormat());
			e.printStackTrace();
			return;
		}
	}
}
