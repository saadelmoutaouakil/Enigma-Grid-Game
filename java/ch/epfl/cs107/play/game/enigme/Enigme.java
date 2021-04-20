package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.area.Enigme1;
import ch.epfl.cs107.play.game.enigme.area.Enigme2;
import ch.epfl.cs107.play.game.enigme.area.Level1;
import ch.epfl.cs107.play.game.enigme.area.Level2;
import ch.epfl.cs107.play.game.enigme.area.Level3;
import ch.epfl.cs107.play.game.enigme.area.LevelSelector;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

/**
 * Enigme Game is a concept of Game derived for AreaGame. It introduces the
 * notion of Player When initializing the player is added to the current area
 */
public class Enigme extends AreaGame {

	/// The player is a concept of RPG games
	// TODO implements me #PROJECT

	private EnigmePlayer player;
	private Level1 level1;
	private Level2 level2;
	private Level3 level3;
	private LevelSelector levelSelector;
	private Enigme1 enigme1;
	private Enigme2 enigme2;
	private Door door;

	@Override
	public String getTitle() {
		return "Enigme";
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		levelSelector = new LevelSelector();
		level1 = new Level1();
		level2 = new Level2();
		level3 = new Level3();
		enigme1 = new Enigme1();
		enigme2 = new Enigme2();
		addArea(levelSelector);
		addArea(level1);
		addArea(level2);
		addArea(level3);
		addArea(enigme1);
		addArea(enigme2);
		setCurrentArea(levelSelector.getTitle(), false);
		player = new EnigmePlayer(getCurrentArea(), new DiscreteCoordinates(5, 5));
		player.enterArea(getCurrentArea(), new DiscreteCoordinates(5, 5));
		return true;
	}

	@Override
	public void update(float deltaTime) {
		if (getCurrentArea().getTitle().equals("Enigme1")) {
			if (player.getCurrentCells().get(0).equals(new DiscreteCoordinates(23, 14))) {
				enigme1.getIndication1().draw(getWindow());
			}
			if (player.getCurrentCells().get(0).equals(new DiscreteCoordinates(1, 1))) {
				enigme1.getIndication2().draw(getWindow());
			}
		}
		if (player.isPassingDoor()) {
			door = player.passedDoor();
			player.leaveArea();
			setCurrentArea(door.getAreaTransitName(), false);
			player.enterArea(getCurrentArea(), door.getDestinationCoordinates());
			player.setPassingDoor(false);
		}
		super.update(deltaTime);
	}

	@Override
	public int getFrameRate() {
		return 24;
	}
}
