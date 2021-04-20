package ch.epfl.cs107.play.window;

import ch.epfl.cs107.play.math.*;

import java.awt.Color;

/**
 * Represents a rendering context, with various drawing capabilities.
 */
public interface Canvas extends Positionable {

	// TODO maybe provide some size/aspect ratio information

	/**
	 * Gets image from file system.
	 * 
	 * @param name
	 *            (String): full name of image, not null
	 * @param roi
	 *            (RegionOfInterest): region of interest
	 * @param removeBackground
	 *            (boolean): which indicate if we need to remove an uniform
	 *            background
	 * @return an image object, null on error
	 */
	Image getImage(String name, RegionOfInterest roi, boolean removeBackground);

	/**
	 * Draws specified image.
	 * 
	 * @param image
	 *            (Image): any image associated to this context, may be null
	 * @param transform
	 *            (Transform): any affine transform, not null
	 * @param alpha
	 *            (float): transparency, between 0.0 and 1.0
	 * @param depth
	 *            (float): any real, larger values are drawn afterward, i.e. above
	 */
	void drawImage(Image image, Transform transform, float alpha, float depth);

	/**
	 * Draws specified image
	 * 
	 * @param shape
	 *            (Shape): any shape, may be null
	 * @param transform
	 *            (Transform): any affine transform, not null
	 * @param fillColor
	 *            (Color): color used to fill the shape, may be null
	 * @param outlineColor
	 *            (Color): color used to draw shape border, may be null
	 * @param thickness
	 *            (float): border thickness
	 * @param alpha
	 *            (float): transparency, between 0.0 and 1.0
	 * @param depth
	 *            (float): any real, larger values are drawn afterward, i.e. above
	 */
	void drawShape(Shape shape, Transform transform, Color fillColor, Color outlineColor, float thickness, float alpha,
			float depth);

	/**
	 * Creates a new text graphics.
	 * 
	 * @param text
	 *            (String): content, not null
	 * @param transform
	 *            (Transform): affine transform, not null
	 * @param fontSize
	 *            (float): size
	 * @param fillColor
	 *            (Color): fill color, may be null
	 * @param outlineColor
	 *            (Color): outline color, may be null
	 * @param thickness
	 *            (float): outline thickness
	 * @param bold
	 *            (boolean): whether to use bold font
	 * @param italics
	 *            (boolean): whether to use italics font
	 * @param anchor
	 *            (Vector): text anchor
	 * @param hAlign
	 *            (TextAlign.Horizontal): horizontal alignment of the text around
	 *            the anchor vector
	 * @param vAlign
	 *            (TextAlign.Vertical): vertical alignment of the text around the
	 *            anchor vector
	 * @param alpha
	 *            (float): transparency, between 0 (invisible) and 1 (opaque)
	 * @param depth
	 *            (float): render priority, lower-values drawn first
	 */
	void drawText(String text, float fontSize, Transform transform, Color fillColor, Color outlineColor,
			float thickness, boolean bold, boolean italics, Vector anchor, TextAlign.Horizontal hAlign,
			TextAlign.Vertical vAlign, float alpha, float depth);

}
