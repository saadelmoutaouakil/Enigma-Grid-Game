package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Door extends AreaEntity {

	private String areaTransitName;
	private DiscreteCoordinates destinationCoordinates;
	private LinkedList<DiscreteCoordinates> neighboringCells;

	public Door(Area area, String areaTransitName, DiscreteCoordinates destinationCoordinates, Orientation orientation,
			DiscreteCoordinates position, DiscreteCoordinates... neighboringCells) {
		super(area, orientation, position);
		this.areaTransitName = areaTransitName;
		this.destinationCoordinates = destinationCoordinates;
		this.neighboringCells = new LinkedList<DiscreteCoordinates>();
		for (int i = 0; i < neighboringCells.length; ++i) {
			this.neighboringCells.add(neighboringCells[i]);
		}

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
	}

	public String getAreaTransitName() {
		return areaTransitName;
	}

	public DiscreteCoordinates getDestinationCoordinates() {
		return destinationCoordinates;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

}
