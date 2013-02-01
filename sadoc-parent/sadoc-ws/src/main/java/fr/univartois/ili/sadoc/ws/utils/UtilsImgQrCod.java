package fr.univartois.ili.sadoc.ws.utils;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

public class UtilsImgQrCod {

	private UtilsImgQrCod(){}
	
	/**
	 * Ajoute une image sur une image.
	 * 
	 * @param image1
	 *            premiere Image.
	 * @param image2
	 *            deuxieme Image.
	 * @param x
	 *            position de l'image x.
	 * @param y
	 *            position de l'image y.
	 * @return BufferedImage : l'image redimensionnée.
	 */
	public static BufferedImage addImage(BufferedImage image1, BufferedImage image2,
			int x, int y) {
		Logger.getAnonymousLogger().warning("REDIMENSIONNEMENT d'une image !! ");
		Graphics2D g2d = image1.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

		g2d.drawImage(image2, x, y, null);

		g2d.dispose();

		return image1;
	}

	/**
	 * Redimensionne une image.
	 * 
	 * @param source
	 *            Image à redimensionner.
	 * @param width
	 *            Largeur de l'image cible.
	 * @param height
	 *            Hauteur de l'image cible.
	 * @return Image redimensionnée.
	 */
	public static Image scale(Image source, int width, int height) {
		/* On crée une nouvelle image aux bonnes dimensions. */
		BufferedImage buf = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);

		/* On dessine sur le Graphics de l'image bufferisée. */
		Graphics2D g = buf.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(source, 0, 0, width, height, null);
		g.dispose();

		/* On retourne l'image bufferisée, qui est une image. */
		return buf;
	}

	/**
	 * Cette méthode retourne une image bufferisée a partir d'une image
	 * 
	 * @param image
	 *            Image a bufferiser.
	 * @return BufferedImage image bufferiser.
	 */
	public static BufferedImage toBufferedImage(Image image) {
		if ((image instanceof BufferedImage)) {
			return (BufferedImage) image;
		}
		image = new ImageIcon(image).getImage();
		BufferedImage bImage = null;
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		try {
			int transparency = Transparency.OPAQUE;
			GraphicsDevice gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bImage = gc.createCompatibleImage(image.getWidth(null),
					image.getHeight(null), transparency);
		} catch (HeadlessException e) {
			// The system does not have a screen
		}
		if (bImage == null) {
			// Creation d'une image en utilisant le model de couleur par default
			int type = BufferedImage.TYPE_INT_RGB;
			bImage = new BufferedImage(image.getWidth(null),
					image.getHeight(null), type);
		}
		Graphics g = bImage.createGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return bImage;
	}

}
