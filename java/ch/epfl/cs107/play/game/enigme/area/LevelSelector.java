package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class LevelSelector extends EnigmeArea {

	@Override
	public String getTitle() {
		return "LevelSelector";
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		registerActor(new SignalDoor(this, "Level1", new DiscreteCoordinates(5, 1), Orientation.DOWN,
				new DiscreteCoordinates(1, 7), Logic.TRUE));
		registerActor(new SignalDoor(this, "Level2", new DiscreteCoordinates(5, 1), Orientation.DOWN,
				new DiscreteCoordinates(2, 7), Logic.TRUE));
		registerActor(new SignalDoor(this, "Level3", new DiscreteCoordinates(5, 1), Orientation.DOWN,
				new DiscreteCoordinates(3, 7), Logic.TRUE));
		registerActor(new SignalDoor(this, "Enigme1", new DiscreteCoordinates(23, 14), Orientation.DOWN,
				new DiscreteCoordinates(4, 7), Logic.TRUE));
		registerActor(new SignalDoor(this, "Enigme2", new DiscreteCoordinates(5, 5), Orientation.DOWN,
				new DiscreteCoordinates(5, 7), Logic.TRUE));
		registerActor(new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(5, 5), Orientation.DOWN,
				new DiscreteCoordinates(6, 7), Logic.FALSE));
		registerActor(new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(5, 5), Orientation.DOWN,
				new DiscreteCoordinates(7, 7), Logic.FALSE));
		registerActor(new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(5, 5), Orientation.DOWN,
				new DiscreteCoordinates(8, 7), Logic.FALSE));
		return true;
	}
}
