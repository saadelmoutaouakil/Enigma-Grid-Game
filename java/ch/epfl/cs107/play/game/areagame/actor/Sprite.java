package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Sprite extends ImageGraphics {

	private final float depthCorrection;

	/**
	 * Creates a new Sprite.
	 * 
	 * @param name
	 *            (String): image name, may be null
	 * @param width
	 *            (int): actual image width, before transformation
	 * @param height
	 *            (int): actual image height, before transformation
	 * @param parent
	 *            (Positionable): parent of this, not null
	 * @param roi
	 *            (RegionOfInterest): region of interest into the image as a
	 *            rectangle in the image. May be null
	 * @param anchor
	 *            (Vector): image anchor, not null
	 * @param alpha
	 *            (float): transparency, between 0 (invisible) and 1 (opaque)
	 * @param depthCorrection
	 *            (float): correction of the deepness defined by the parent position
	 *            if exists
	 */
	public Sprite(String name, float width, float height, Positionable parent, RegionOfInterest roi, Vector anchor,
			float alpha, float depthCorrection) {
		super(ResourcePath.getSprite(name), width, height, roi, anchor, alpha,
				-parent.getPosition().y + depthCorrection);
		setParent(parent);
		this.depthCorrection = depthCorrection;
	}

	/**
	 * Creates a new image graphics.
	 * 
	 * @param name
	 *            (String): image name, not null
	 * @param width
	 *            (int): actual image width, before transformation
	 * @param height
	 *            (int): actual image height, before transformation
	 * @param parent
	 *            (Positionable): parent of this, not null
	 * @param roi
	 *            (RegionOfInterest): region of interest into the image as a
	 *            rectangle in the image. May be null
	 * @param anchor
	 *            (Vector): image anchor, not null
	 */
	public Sprite(String name, float width, float height, Positionable parent, RegionOfInterest roi, Vector anchor) {
		super(ResourcePath.getSprite(name), width, height, roi, anchor, 1.0f, -parent.getPosition().y);
		setParent(parent);
		depthCorrection = 0;
	}

	/**
	 * Creates a new image graphics.
	 * 
	 * @param name
	 *            (String): image name, not null
	 * @param width
	 *            (int): actual image width, before transformation
	 * @param height
	 *            (int): actual image height, before transformation
	 * @param parent
	 *            (Positionable): parent of this, not null
	 * @param roi
	 *            (RegionOfInterest): region of interest into the image as a
	 *            rectangle in the image. May be null
	 */
	public Sprite(String name, float width, float height, Positionable parent, RegionOfInterest roi) {
		super(ResourcePath.getSprite(name), width, height, roi, Vector.ZERO, 1.0f, -parent.getPosition().y);
		setParent(parent);
		depthCorrection = 0;
	}

	/**
	 * Creates a new image graphics.
	 * 
	 * @param name
	 *            (String): image name, not null
	 * @param width
	 *            (int): actual image width, before transformation
	 * @param height
	 *            (int): actual image height, before transformation
	 * @param parent
	 *            (Positionable): parent of this, not null
	 */
	public Sprite(String name, float width, float height, Positionable parent) {
		super(ResourcePath.getSprite(name), width, height, null, Vector.ZERO, 1.0f, -parent.getPosition().y);
		setParent(parent);
		depthCorrection = 0;
	}

	@Override
	public void draw(Canvas canvas) {

		if (getParent() != null) {
			setDepth(-getParent().getPosition().y + depthCorrection);
		}
		super.draw(canvas);
	}
}
