package ch.epfl.cs107.play.game.enigme.actor;

import java.awt.Color;
import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.TextAlign;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Dialog implements Graphics {

	/// Static variables for positioning the text and the dialog window
	private final float DX;
	private final float DY;

	/// Number max of char per line of text
	private static final int MAX_LINE_SIZE = 90;

	/// Sprite and text graphics line
	private final ImageGraphics sprite;
	private final TextGraphics[] lines;

	/// Full Text of the dialog
	private String text;
	/// Index of the next char to print
	private int cursor;

	/**
	 * Default Dialog Constructor
	 * 
	 * @param text
	 *            (String): string of the dialog, not null
	 * @param backgroundName
	 *            (String): Background file name (i.e only the name, with neither
	 *            path, nor file extension), may be null
	 * @param area
	 *            (Area): this owner area to compute scale factor ratios, not null
	 */
	public Dialog(String text, String backgroundName, Area area) {

		DX = area.getCameraScaleFactor() / 2;
		DY = area.getCameraScaleFactor() / 2;
		final float height = area.getCameraScaleFactor() / 4;
		final float width = area.getCameraScaleFactor();

		final float fontSize = 0.024f * area.getCameraScaleFactor();
		final float leftPadding = 0.038f * area.getCameraScaleFactor();

		final Vector firstLineAnchor = new Vector(leftPadding, height - fontSize);
		final Vector secondLineAnchor = new Vector(leftPadding, height - 2.5f * fontSize);
		final Vector thirdLineAnchor = new Vector(leftPadding, height - 4 * fontSize);

		sprite = new ImageGraphics(ResourcePath.getSprite(backgroundName), width, height, null, Vector.ZERO, 1.0f,
				1000);

		lines = new TextGraphics[3];

		lines[0] = new TextGraphics("", fontSize, Color.BLACK, null, 0.0f, false, false, firstLineAnchor,
				TextAlign.Horizontal.LEFT, TextAlign.Vertical.MIDDLE, 1.0f, 1001);
		lines[1] = new TextGraphics("", fontSize, Color.BLACK, null, 0.0f, false, false, secondLineAnchor,
				TextAlign.Horizontal.LEFT, TextAlign.Vertical.MIDDLE, 1.0f, 1001);
		lines[2] = new TextGraphics("", fontSize, Color.BLACK, null, 0.0f, false, false, thirdLineAnchor,
				TextAlign.Horizontal.LEFT, TextAlign.Vertical.MIDDLE, 1.0f, 1001);

		resetDialog(text);
	}

	/**
	 * Reset the dialog window with a new dialog text
	 * 
	 * @param newText
	 *            (String), not null
	 */
	public void resetDialog(String newText) {
		this.text = newText;
		this.cursor = 0;
		push();
	}

	/**
	 * From the cursor display on the three available lines the next chars of the
	 * stream Notice: this method assume it will never face to single word longer or
	 * equals than MAX_LINE_SIZE
	 * 
	 * @return (boolean) if the dialog can be closed or not
	 */
	public boolean push() {

		int lengthToPush = text.length() - cursor;
		// Simply close the dialog
		if (lengthToPush <= 0)
			return true;

		// For each line
		for (int i = 0; i < 3; i++) {

			// If some text still need to be pushed : fill the next line
			if (lengthToPush <= 0) {
				lines[i].setText("");
			} else if (lengthToPush <= MAX_LINE_SIZE) {
				lines[i].setText(text.substring(cursor));
				cursor += lengthToPush;
			} else {
				int maxSize = MAX_LINE_SIZE;
				String toConcat = "";

				if (i == 2) {
					maxSize -= 4;
					toConcat += " ...";
				}

				String sub = text.substring(cursor, cursor + maxSize + 1);
				int last = sub.lastIndexOf(' ');
				if (last == -1) {
					System.out.println("Error: You get a Word longer than " + MAX_LINE_SIZE);
				}

				lines[i].setText(sub.substring(0, last) + toConcat);
				cursor = cursor + last + 1;
			}

			lengthToPush = text.length() - cursor;
		}
		return false;
	}

	/// Dialog implements Graphics

	@Override
	public void draw(Canvas canvas) {
		final Transform transform = Transform.I.translated(canvas.getPosition().sub(DX, DY));
		sprite.setRelativeTransform(transform);
		sprite.draw(canvas);

		for (int i = 0; i < 3; i++) {
			lines[i].setRelativeTransform(transform);
			lines[i].draw(canvas);
		}
	}
}
