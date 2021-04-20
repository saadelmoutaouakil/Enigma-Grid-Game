package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class SignalDoor extends Door {
	private Sprite openedDoor;
	private Sprite closedDoor;
	private Logic logic;

	public SignalDoor(Area area, String areaTransitName, DiscreteCoordinates destinationCoordinates,
			Orientation orientation, DiscreteCoordinates position, Logic logic,
			DiscreteCoordinates... neighboringCells) {
		super(area, areaTransitName, destinationCoordinates, orientation, position, neighboringCells);
		this.logic = logic;
		closedDoor = new Sprite("door.close.1", 1, 1.f, this);
		openedDoor = new Sprite("door.open.1", 1, 1.f, this);

	}

	@Override
	public boolean takeCellSpace() {
		if (logic.getIntensity() == 0) {
			return true;
		} else
			return false;
	}

	@Override
	public boolean isCellInteractable() {
		if (logic.getIntensity() == 1) {
			return true;
		} else
			return false;
	}

	@Override
	public void draw(Canvas canvas) {
		if (isCellInteractable()) {
			openedDoor.draw(canvas);
		} else
			closedDoor.draw(canvas);
	}
}
