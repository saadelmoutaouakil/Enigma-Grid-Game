package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Cup extends Collectable implements Logic {

	private Sprite cup;
	private boolean on;
	private Logic logic;

	public Cup(Area area, Orientation orientation, DiscreteCoordinates position, Logic logic) {
		super(area, orientation, position);
		cup = new Sprite("cup.1", 1, 1.f, this);
		on = false;
		this.logic = logic;
	}

	@Override
	public void draw(Canvas canvas) {
		if (logic.getIntensity() == 1) {
			cup.draw(canvas);
		}
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public Logic getLogic() {
		return logic;
	}

}
