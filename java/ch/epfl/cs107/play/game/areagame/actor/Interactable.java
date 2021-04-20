package ch.epfl.cs107.play.game.areagame.actor;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Models objects receptive to interaction (i.e. Interactor can interact with
 * them)
 * 
 * @see Interactor This interface makes sense only in the "AreaGame" context
 *      with Actor contained into Area Cell
 */
public interface Interactable {

	List<DiscreteCoordinates> getCurrentCells();

	boolean takeCellSpace();

	boolean isViewInteractable();

	boolean isCellInteractable();

	public default void acceptInteraction(AreaInteractionVisitor v) {
		// avec une définition par défaut simple
	}

}
