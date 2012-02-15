package fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.ChecksumException;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.FormatException;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.core.NotFoundException;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.qrcode.decoder.Decoder;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.utils.MyProperties;

/**
 * @author francois
 * 
 */
public class QRCodeReaderManager2 {

	private static QRCodeReaderManager2 instance;

	private MyProperties props;

	private String name;

	/**
	 * Constructor
	 */
	private QRCodeReaderManager2() {
		props = MyProperties.getInstance();

		instance = this;
	}

	/**
	 * @return instance
	 */
	public static QRCodeReaderManager2 getInstance() {
		if (instance == null) {
			new QRCodeReaderManager2();
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
	 * @return
	 */
	private String decodeData(BufferedImage bi) {
		BufferedImage imgQRC = null;
		try {

			if (bi != null) {
				imgQRC = bi;
			} else {
				imgQRC = ImageIO.read(new File(this.props.getPath() + this.name
						+ "." + this.props.getFormat()));

			}

			return new Decoder().decode(
					this.createMatrix(this.createTabInt(imgQRC),
							imgQRC.getWidth(), imgQRC.getHeight())).getText();
		} catch (IOException e) {
			System.err.println("Probleme de lecture !");
			e.printStackTrace();
		} catch (ChecksumException e) {
			System.err.println("ChecksumException");
			e.printStackTrace();
		} catch (FormatException e) {
			System.err.println("FormatException");
			e.printStackTrace();
		} catch (NotFoundException e) {
			System.err.println("NotFoundException");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @param bi
	 */
	private int[][] createTabInt(BufferedImage bi) {
		int[][] tabInt = new int[bi.getWidth()][bi.getHeight()];
		for (int i = 0; i < bi.getHeight(); i++) {
			for (int j = 0; j < bi.getWidth(); j++) {
				if (bi.getRGB(j, i) < -1) {
					tabInt[i][j] = 1;
				} else {
					tabInt[i][j] = 0;
				}
			}
		}

		return tabInt;
	}

	/**
	 * @param tabInt
	 * @param w
	 * @param h
	 * @return
	 */
	private boolean[][] createMatrix(int[][] tabInt, int w, int h) {
		int cpt = 0;
		for (int i = 0; i < w; i++) {
			if (tabInt[i][0] == 1)
				cpt++;
			else
				break;
		}

		int sizeModule = cpt / 7;
		System.out.println(sizeModule);
		boolean[][] matrice = new boolean[w / sizeModule][h / sizeModule];
		cpt = 0;
		int cpt2 = 0;
		int i2 = 0, j2 = 0;
		for (int i = 0; i < h; i++) {
			if (cpt == 0) {
				for (int j = 0; j < w; j++) {
					if (cpt2 == 0) {
						if (tabInt[i][j] == 1) {
							System.out.print("1 ");
							matrice[i2][j2] = true;
						} else {
							System.out.print("0 ");
							matrice[i2][j2] = false;
						}
						j2++;
					}
					cpt2++;
					if (cpt2 == sizeModule) {
						cpt2 = 0;
					}
				}
				System.out.println();
				j2 = 0;
				i2++;
			}
			cpt++;
			if (cpt == sizeModule) {
				cpt = 0;
			}

		}

		return matrice;
	}
}
