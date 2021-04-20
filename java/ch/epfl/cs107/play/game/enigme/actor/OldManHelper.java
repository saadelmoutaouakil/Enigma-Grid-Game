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
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class OldManHelper extends AreaEntity {

	private Dialog dialog;
	private boolean dialogOn;
	private Logic logic;
	private Sprite oldManHelper;

	public OldManHelper(Area area, Orientation orientation, DiscreteCoordinates position, Logic logic, Dialog dialog) {
		super(area, orientation, position);
		this.logic = logic;
		Vector anchor = new Vector(0.25f, 0.32f);
		oldManHelper = new Sprite("old.man.1", 0.5f, 0.65625f, this, new RegionOfInterest(0, 0, 16, 21), anchor);
		this.dialog = dialog;
	}

	@Override
	public boolean isViewInteractable() {
		if (logic.getIntensity() == 1) {
			return true;
		} else
			return false;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
	}

	@Override
	public void draw(Canvas canvas) {
		if (logic.getIntensity() == 1) {
			oldManHelper.draw(canvas);
		}
		if (dialogOn) {
			dialog.draw(canvas);
		}
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

	public void setDialogOn(boolean dialogOn) {
		this.dialogOn = dialogOn;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		if (logic.getIntensity() == 0) {
			return false;
		} else
			return true;
	}

}
