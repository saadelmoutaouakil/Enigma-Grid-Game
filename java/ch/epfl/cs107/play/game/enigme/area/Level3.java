package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Torch;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.LogicNumber;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.signal.logic.Or;
import ch.epfl.cs107.play.window.Window;

public class Level3 extends EnigmeArea {

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		Key key = new Key(this, null, new DiscreteCoordinates(1, 3));
		PressurePlate pressurePlate = new PressurePlate(this, null, new DiscreteCoordinates(9, 8));
		PressureSwitch pressureSwitch1 = new PressureSwitch(this, null, new DiscreteCoordinates(4, 4));
		PressureSwitch pressureSwitch2 = new PressureSwitch(this, null, new DiscreteCoordinates(5, 4));
		PressureSwitch pressureSwitch3 = new PressureSwitch(this, null, new DiscreteCoordinates(6, 4));
		PressureSwitch pressureSwitch4 = new PressureSwitch(this, null, new DiscreteCoordinates(5, 5));
		PressureSwitch pressureSwitch5 = new PressureSwitch(this, null, new DiscreteCoordinates(4, 6));
		PressureSwitch pressureSwitch6 = new PressureSwitch(this, null, new DiscreteCoordinates(5, 6));
		PressureSwitch pressureSwitch7 = new PressureSwitch(this, null, new DiscreteCoordinates(6, 6));
		Lever lever1 = new Lever(this, null, new DiscreteCoordinates(10, 5));
		Lever lever2 = new Lever(this, null, new DiscreteCoordinates(9, 5));
		Lever lever3 = new Lever(this, null, new DiscreteCoordinates(8, 5));
		Torch torch = new Torch(this, null, new DiscreteCoordinates(7, 5));
		registerActor(key);
		registerActor(torch);
		registerActor(pressurePlate);
		registerActor(pressureSwitch1);
		registerActor(pressureSwitch2);
		registerActor(pressureSwitch3);
		registerActor(pressureSwitch4);
		registerActor(pressureSwitch5);
		registerActor(pressureSwitch6);
		registerActor(pressureSwitch7);
		registerActor(lever1);
		registerActor(lever2);
		registerActor(lever3);
		registerActor(new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(3, 6), Orientation.DOWN,
				new DiscreteCoordinates(5, 9), key));
		registerActor(new SignalRock(this, null, new DiscreteCoordinates(6, 8), pressurePlate));
		registerActor(new SignalRock(this, null, new DiscreteCoordinates(5, 8), new MultipleAnd(pressureSwitch1,
				pressureSwitch2, pressureSwitch3, pressureSwitch4, pressureSwitch5, pressureSwitch6, pressureSwitch7)));
		registerActor(new SignalRock(this, null, new DiscreteCoordinates(4, 8),
				new Or(new LogicNumber(5f,lever1,lever2,lever3), torch)));
		return true;
	}

	public String getTitle() {
		return "Level3";
	}
}
