package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Lever extends Switcher {

	private Sprite leverLeft;
	private Sprite leverRight;

	public Lever(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		leverLeft = new Sprite("lever.big.left", 1, 1.f, this);
		leverRight = new Sprite("lever.big.right", 1, 1.f, this);
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return super.getCurrentCells();
	}

	@Override
	public boolean takeCellSpace() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw(Canvas canvas) {
		if (isOn()) {
			leverLeft.draw(canvas);

		} else
			leverRight.draw(canvas);
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}
}
