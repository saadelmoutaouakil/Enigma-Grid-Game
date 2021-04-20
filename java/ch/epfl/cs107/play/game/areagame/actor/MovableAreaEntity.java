package ch.epfl.cs107.play.game.areagame.actor;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

/**
 * MovableAreaEntity are AreaEntity able to move on a grid
 */
public abstract class MovableAreaEntity extends AreaEntity {

	/// Indicate if the actor is currently moving
	private boolean isMoving;
	/// Indicate how many frames the current move is suppose to take
	private int framesForCurrentMove;
	/// The target cell (i.e. where the mainCell will be after the motion )
	private DiscreteCoordinates targetMainCellCoordinates;
	// TODO implements me #PROJECT #TUTO

	/**
	 * Default MovableAreaEntity constructor
	 * 
	 * @param area
	 *            (Area): Owner area. Not null
	 * @param position
	 *            (Coordinate): Initial position of the entity. Not null
	 * @param orientation
	 *            (Orientation): Initial orientation of the entity. Not null
	 */
	public MovableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		resetMotion();
	}

	/**
	 * Initialize or reset the current motion information
	 */
	protected void resetMotion() {
		isMoving = false;
		framesForCurrentMove = 0;
		targetMainCellCoordinates = getCurrentMainCellCoordinates();
	}

	/**
	 * 
	 * @param frameForMove
	 *            (int): number of frames used for simulating motion
	 * @return (boolean): returns true if motion can occur
	 */

	protected boolean move(int framesForMove) {
		if (!isMoving || getCurrentMainCellCoordinates().equals(targetMainCellCoordinates)) {
			if (!getOwnerArea().enterAreaCells(this, getEnteringCells())
					|| !getOwnerArea().leaveAreaCells(this, getLeavingCells())) {
				return false;
			} else {
				if (framesForMove < 1) {
					framesForCurrentMove = 1;
				}
				framesForCurrentMove = framesForMove;
				isMoving = true;
				Vector orientation = getOrientation().toVector();
				targetMainCellCoordinates = getCurrentMainCellCoordinates().jump(orientation);
				return true;
			}
		}
		return false;
	}
	/// MovableAreaEntity implements Actor

	@Override
	public void update(float deltaTime) {
		if (isMoving && !getCurrentMainCellCoordinates().equals(targetMainCellCoordinates)) {
			Vector distance = getOrientation().toVector();
			distance = distance.mul(1.0f / framesForCurrentMove);
			setCurrentPosition(getPosition().add(distance));
		} else {
			resetMotion();
		}
	}

	/// Implements Positionable

	@Override
	public Vector getVelocity() {
		// TODO implements me #PROJECT #TUTO
		// the velocity must be computed as the orientation vector
		// (getOrientation().toVector() mutiplied by
		// framesForCurrentMove
		return new Vector(getOrientation().toVector().getX() * framesForCurrentMove,
				getOrientation().toVector().getY() * framesForCurrentMove);
	}

	protected final List<DiscreteCoordinates> getLeavingCells() {
		return getCurrentCells();
	}

	protected final List<DiscreteCoordinates> getEnteringCells() {
		List<DiscreteCoordinates> tab = new LinkedList<DiscreteCoordinates>();
		for (int i = 0; i < getCurrentCells().size(); ++i) {
			if (getCurrentCells().get(i).jump(getOrientation().toVector()).x < getOwnerArea().getWidth()
					&& getCurrentCells().get(i).jump(getOrientation().toVector()).y < getOwnerArea().getHeight())
				tab.add(getCurrentCells().get(i).jump(getOrientation().toVector()));
		}
		return tab;
	}

	@Override
	protected void setOrientation(Orientation orientation) {
		if (isMoving == false) {
			super.setOrientation(orientation);
		}
	}

	public DiscreteCoordinates getTargetMainCellCoordinates() {
		return targetMainCellCoordinates;
	}

}
