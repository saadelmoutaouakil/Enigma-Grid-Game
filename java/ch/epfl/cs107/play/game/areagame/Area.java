package ch.epfl.cs107.play.game.areagame;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

/**
 * Area is a "Part" of the AreaGame. It is characterized by its AreaBehavior and
 * a List of Actors
 */
public abstract class Area implements Playable {

	private Window window;
	private FileSystem fileSystem;
	private List<Actor> actors;
	private List<Actor> registeredActors;
	private List<Actor> unregisteredActors;
	private Actor viewCandidate;
	private Vector viewCenter;
	private AreaBehavior areaBehavior;
	private boolean status;
	private Map<Interactable, List<DiscreteCoordinates>> interactablesToEnter;
	private Map<Interactable, List<DiscreteCoordinates>> interactablesToLeave;
	private LinkedList<Interactor> interactors;
	private Boolean play = true;

	// Context objects
	// TODO implements me #PROJECT #TUTO

	/**
	 * @return (float): camera scale factor, assume it is the same in x and y
	 *         direction
	 */
	public abstract float getCameraScaleFactor();

	/**
	 * Add an actor to the actors list
	 * 
	 * @param a
	 *            (Actor): the actor to add, not null
	 * @param forced
	 *            (Boolean): if true, the method ends
	 */
	private void addActor(Actor a, boolean forced) {
		// TODO implements me #PROJECT #TUTO
		// Here decisions at the area level to decide if an actor
		// must be added or not
		boolean errorOccured = !actors.add(a);
		if (a instanceof Interactable) {
			errorOccured = errorOccured || !enterAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());
		}
		if (a instanceof Interactor) {
			errorOccured = errorOccured || !interactors.add((Interactor) a);
		}
		if (errorOccured && !forced) {
			System.out.println("Actor " + a + " cannot be completely added , so remove it from where itwas");
			removeActor(a, true);
		}
	}

	/**
	 * Remove an actor form the actor list
	 * 
	 * @param a
	 *            (Actor): the actor to remove, not null
	 * @param forced
	 *            (Boolean): if true, the method ends
	 */
	private void removeActor(Actor a, boolean forced) {

		boolean errorOccured = !actors.remove(a);
		if (a instanceof Interactable) {
			errorOccured = errorOccured || !leaveAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());
		}
		if (a instanceof Interactor) {
			errorOccured = errorOccured || !interactors.remove((Interactor) a);
		}
		if (errorOccured && !forced) {
			System.out.println("Actor " + a + " cannot be completely deleted , so let it where itwas");
			addActor(a, true);
		}
	}

	/**
	 * Register an actor : will be added at next update
	 * 
	 * @param a
	 *            (Actor): the actor to register, not null
	 * @return (boolean): true if the actor is correctly registered
	 */
	public final boolean registerActor(Actor a) {

		return registeredActors.add(a);
	}

	/**
	 * Unregister an actor : will be removed at next update
	 * 
	 * @param a
	 *            (Actor): the actor to unregister, not null
	 * @return (boolean): true if the actor is correctly unregistered
	 */
	public final boolean unregisterActor(Actor a) {

		return unregisteredActors.add(a);
	}

	/**
	 * Getter for the area width
	 * 
	 * @return (int) : the width in number of cols
	 */
	public final int getWidth() {
		return areaBehavior.getWidth();

	}

	/**
	 * Getter for the area height
	 * 
	 * @return (int) : the height in number of rows
	 */
	public final int getHeight() {

		return areaBehavior.getHeight();
	}

	/** @return the Window Keyboard for inputs */
	public final Keyboard getKeyboard() {
		return window.getKeyboard();
	}

	/// Area implements Playable

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		actors = new LinkedList<>();
		registeredActors = new LinkedList<>();
		unregisteredActors = new LinkedList<>();
		interactablesToEnter = new HashMap<>();
		interactablesToLeave = new HashMap<>();
		interactors = new LinkedList<Interactor>();
		this.window = window;
		this.fileSystem = fileSystem;
		viewCenter = Vector.ZERO;
		viewCandidate = null;
		status = true;
		return true;
	}

	/**
	 * Resume method: Can be overridden
	 * 
	 * @param window
	 *            (Window): display context, not null
	 * @param fileSystem
	 *            (FileSystem): given file system, not null
	 * @return (boolean) : if the resume succeed, true by default
	 */
	public boolean resume(Window window, FileSystem fileSystem) {
		return true;
	}

	@Override
	public void update(float deltaTime) {
		Keyboard keyboard = window.getKeyboard();
		Button space = keyboard.get(Keyboard.SPACE);
		if (space.isPressed() && play == true) {
			play = false;
		} else if (space.isPressed() && play == false) {
			play = true;
		}
		purgeRegistration();
		if (play) {
			for (Actor a : actors) {
				a.update(deltaTime);
			}
			for (Interactor interactor : interactors) {
				if (interactor.wantsCellInteraction()) {
					areaBehavior.cellInteractionOf(interactor);
				}
				if (interactor.wantsViewInteraction()) {
					areaBehavior.viewInteractionOf(interactor);
				}
			}
		}
		updateCamera();

		for (Actor a : actors) {
			a.draw(window);
		}
	}

	private void updateCamera() {
		if (viewCandidate != null) {
			viewCenter = viewCandidate.getPosition();
			Transform viewTransform = Transform.I.scaled(getCameraScaleFactor()).translated(viewCenter);
			window.setRelativeTransform(viewTransform);
		}
	}

	/**
	 * Suspend method: Can be overridden, called before resume other
	 */
	public void suspend() {
		purgeRegistration();
	}

	@Override
	public void end() {
		// TODO save the AreaState somewhere
	}

	private final void purgeRegistration() {
		for (int i = 0; i < registeredActors.size(); ++i) {
			addActor(registeredActors.get(i), false);
		}
		for (int i = 0; i < unregisteredActors.size(); ++i) {
			removeActor(unregisteredActors.get(i), false);
		}
		registeredActors.clear();
		unregisteredActors.clear();
		for (Entry<Interactable, List<DiscreteCoordinates>> pairToEnter : interactablesToEnter.entrySet()) {
			areaBehavior.enter(pairToEnter.getKey(), pairToEnter.getValue());
		}
		interactablesToEnter.clear();
		for (Entry<Interactable, List<DiscreteCoordinates>> pairToLeave : interactablesToLeave.entrySet()) {
			areaBehavior.leave(pairToLeave.getKey(), pairToLeave.getValue());
		}
		interactablesToLeave.clear();

	}

	public final void setViewCandidate(Actor a) {
		this.viewCandidate = a;
	}

	protected final void setBehavior(AreaBehavior ab) {
		areaBehavior = ab;
	}

	public boolean getStatus() {
		return status;
	}

	public AreaBehavior getAreaBehavior() {
		return areaBehavior;
	}

	public final boolean leaveAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {
		if (areaBehavior.canLeave(entity, coordinates)) {
			interactablesToLeave.put(entity, coordinates);
			return true;
		} else
			return false;
	}

	public final boolean enterAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {
		if (areaBehavior.canEnter(entity, coordinates)) {
			interactablesToEnter.put(entity, coordinates);
			return true;
		} else
			return false;
	}

	public Window getWindow() {
		return window;
	}

}
