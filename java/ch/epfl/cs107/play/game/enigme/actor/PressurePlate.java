package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class PressurePlate extends AreaEntity implements Logic {

	private boolean on;
	private float timer;
	protected static final float DEACTIVATION_TIME = 1f;
	private Sprite groundPlateOff;
	private Sprite groundPlateOn;

	public PressurePlate(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		on = false;
		groundPlateOff = new Sprite("GroundPlateOff", 1, 1.f, this);
		groundPlateOn = new Sprite("GroundLightOn", 1, 1.f, this);
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
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
			groundPlateOn.draw(canvas);
		} else
			groundPlateOff.draw(canvas);
	}

	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	@Override
	public void update(float deltaTime) {
		if (isOn()) {
			timer += deltaTime;
			if (timer > DEACTIVATION_TIME) {
				on = false;
				timer = 0;
			}
		}

	}

}
