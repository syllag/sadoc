package fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.qrcode.detector;

/**
 * <p>
 * Encapsulates information about finder patterns in an image, including the
 * location of the three finder patterns, and their estimated module size.
 * </p>
 * 
 * @author Sean Owen
 */
public final class FinderPatternInfo {

	private final FinderPattern bottomLeft;
	private final FinderPattern topLeft;
	private final FinderPattern topRight;

	public FinderPatternInfo(FinderPattern[] patternCenters) {
		FinderPattern[] patternC = patternCenters.clone();

		this.bottomLeft = patternC[0];
		this.topLeft = patternC[1];
		this.topRight = patternC[2];
	}

	public FinderPattern getBottomLeft() {
		return bottomLeft;
	}

	public FinderPattern getTopLeft() {
		return topLeft;
	}

	public FinderPattern getTopRight() {
		return topRight;
	}

}
