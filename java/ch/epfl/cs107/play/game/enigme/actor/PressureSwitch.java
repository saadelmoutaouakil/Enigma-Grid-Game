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

public class PressureSwitch extends Switcher {

	private Sprite groundLightOn;
	private Sprite groundLightOff;

	public PressureSwitch(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		groundLightOn = new Sprite("GroundLightOn", 1, 1.f, this);
		groundLightOff = new Sprite("GroundLightOff", 1, 1.f, this);
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return super.getCurrentCells();
	}

	@Override
	public boolean takeCellSpace() {
		// TODO Auto-generated method stub
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
		return true;
	}

	@Override
	public void draw(Canvas canvas) {
		if (isOn()) {
			groundLightOn.draw(canvas);
		} else
			groundLightOff.draw(canvas);
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

}
