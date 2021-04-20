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

public class Torch extends Switcher {

	private Sprite torchOn;
	private Sprite torchOff;

	public Torch(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		torchOn = new Sprite("torch.ground.on.1", 1, 1.f, this);
		torchOff = new Sprite("torch.ground.off", 1, 1.f, this);
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
			torchOn.draw(canvas);
		} else
			torchOff.draw(canvas);
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}
}
