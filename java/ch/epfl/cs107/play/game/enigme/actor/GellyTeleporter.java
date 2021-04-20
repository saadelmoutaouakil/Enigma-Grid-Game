package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class GellyTeleporter extends Door {
	private Sprite redGelly;
	private Sprite greenGelly;
	private Logic logic;

	public GellyTeleporter(Area area, String areaTransitName, DiscreteCoordinates destinationCoordinates,
			Orientation orientation, DiscreteCoordinates position, Logic logic,
			DiscreteCoordinates... neighboringCells) {
		super(area, areaTransitName, destinationCoordinates, orientation, position, neighboringCells);
		this.logic = logic;
		redGelly = new Sprite("gelly.red.1", 1, 1.f, this);
		greenGelly = new Sprite("gelly.green.1", 1, 1.f, this);
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
			greenGelly.draw(canvas);
		} else
			redGelly.draw(canvas);
	}
}
