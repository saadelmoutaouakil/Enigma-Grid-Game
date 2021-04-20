package ch.epfl.cs107.play.game.areagame;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

/**
 * AreaBehavior manages a map of Cells.
 */
public abstract class AreaBehavior {
	/// The behavior is an Image of size height x width
	private final Image behaviorMap;
	private final int width, height;
	/// We will convert the image into an array of cells
	private final Cell[][] cells;

	public abstract class Cell implements Interactable {
		private DiscreteCoordinates coord;
		protected Set<Interactable> entities;

		public Cell(int x, int y) {
			coord = new DiscreteCoordinates(x, y);
			entities = new HashSet<>();
		}

		public DiscreteCoordinates getCoord() {
			return coord;
		}

		public List<DiscreteCoordinates> getCurrentCells() {
			List<DiscreteCoordinates> tab = new LinkedList<DiscreteCoordinates>();
			tab.add(getCoord());
			return tab;
		}

		public void enter(Interactable entity) {
			entities.add(entity);
		}

		private void leave(Interactable entity) {
			entities.remove(entity);
		}

		abstract protected boolean canEnter(Interactable entity);

		abstract protected boolean canLeave(Interactable entity);

		private void cellInteractionOf(Interactor interactor) {
			for (Interactable interactable : entities) {
				if (interactable.isCellInteractable()) {
					interactor.interactWith(interactable);
				}
			}
		}

		private void viewInteractionOf(Interactor interactor) {

			for (Interactable interactable : entities) {
				if (interactable.isViewInteractable()) {
					interactor.interactWith(interactable);
				}
			}
		}
	}

	/**
	 * Default AreaBehavior Constructor
	 * 
	 * @param window
	 *            (Window): graphic context, not null
	 * @param fileName
	 *            (String): name of the file containing the behavior image, not null
	 */
	public AreaBehavior(Window window, String fileName) {
		behaviorMap = window.getImage(ResourcePath.getBehaviors(fileName), null, false);
		width = behaviorMap.getWidth();
		height = behaviorMap.getHeight();
		cells = new Cell[width][height];
	}

	public Cell[][] getCells() {
		return cells;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Image getBehaviorMap() {
		return behaviorMap;
	}

	public boolean canLeave(Interactable entity, List<DiscreteCoordinates> coordinates) {
		boolean authorisation = true;
		for (int i = 0; i < coordinates.size(); ++i) {
			authorisation = authorisation && cells[coordinates.get(i).x][coordinates.get(i).y].canLeave(entity);
		}
		return authorisation;
	}

	public boolean canEnter(Interactable entity, List<DiscreteCoordinates> coordinates) {
		boolean authorisation = true;
		boolean isInGridLimit = true;
		for (int i = 0; i < coordinates.size(); ++i) {
			authorisation = authorisation && cells[coordinates.get(i).x][coordinates.get(i).y].canEnter(entity);
			isInGridLimit = isInGridLimit && (coordinates.get(i).x < getWidth() && coordinates.get(i).y < getHeight());
		}
		return (authorisation && isInGridLimit);
	}

	protected void leave(Interactable entity, List<DiscreteCoordinates> coordinates) {
		for (int i = 0; i < coordinates.size(); ++i) {
			cells[coordinates.get(i).x][coordinates.get(i).y].leave(entity);
		}

	}

	protected void enter(Interactable entity, List<DiscreteCoordinates> coordinates) {
		for (int i = 0; i < coordinates.size(); ++i) {
			cells[coordinates.get(i).x][coordinates.get(i).y].enter(entity);
		}
	}

	public void cellInteractionOf(Interactor interactor) {

		for (int i = 0; i < interactor.getCurrentCells().size(); ++i) {
			cells[interactor.getCurrentCells().get(i).x][interactor.getCurrentCells().get(i).y]
					.cellInteractionOf(interactor);
		}
	}

	public void viewInteractionOf(Interactor interactor) {
		for (int i = 0; i < interactor.getFieldOfViewCells().size(); ++i) {
			cells[interactor.getFieldOfViewCells().get(i).x][interactor.getFieldOfViewCells().get(i).y]
					.viewInteractionOf(interactor);
		}
	}

}
