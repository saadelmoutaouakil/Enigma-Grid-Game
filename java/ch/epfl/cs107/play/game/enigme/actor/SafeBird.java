package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class SafeBird extends AreaEntity {

	private Logic logic;
	private Sprite safeBird;

	public SafeBird(Area area, Orientation orientation, DiscreteCoordinates position, Logic logic) {
		super(area, orientation, position);
		this.logic = logic;
		Vector anchor = new Vector(0.25f, 0.32f);
		safeBird = new Sprite("bird.1", 0.5f, 0.65625f, this, new RegionOfInterest(16, 0, 16, 21), anchor);
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		if (logic.getIntensity() == 0) {
			return true;
		} else
			return false;
	}

	@Override
	public boolean isViewInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw(Canvas canvas) {
		if (logic.getIntensity() == 0) {
			safeBird.draw(canvas);
		}
	}

}