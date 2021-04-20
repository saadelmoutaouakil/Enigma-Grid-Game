package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Cake;
import ch.epfl.cs107.play.game.enigme.actor.Cup;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.OldManHelper;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.SafeBird;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.io.XMLTexts;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.window.Window;

public class Enigme2 extends EnigmeArea {
	private Dialog dialog;

	@Override
	public String getTitle() {
		return "Enigme2";
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		PressureSwitch pressureSwitch1 = new PressureSwitch(this, null, new DiscreteCoordinates(12, 3));
		PressureSwitch pressureSwitch2 = new PressureSwitch(this, null, new DiscreteCoordinates(11, 3));
		PressureSwitch pressureSwitch3 = new PressureSwitch(this, null, new DiscreteCoordinates(10, 3));
		PressureSwitch pressureSwitch4 = new PressureSwitch(this, null, new DiscreteCoordinates(8, 3));
		PressureSwitch pressureSwitch5 = new PressureSwitch(this, null, new DiscreteCoordinates(7, 3));
		PressureSwitch pressureSwitch6 = new PressureSwitch(this, null, new DiscreteCoordinates(6, 3));
		PressureSwitch pressureSwitch7 = new PressureSwitch(this, null, new DiscreteCoordinates(10, 4));
		PressureSwitch pressureSwitch8 = new PressureSwitch(this, null, new DiscreteCoordinates(7, 4));
		PressureSwitch pressureSwitch9 = new PressureSwitch(this, null, new DiscreteCoordinates(12, 5));
		PressureSwitch pressureSwitch10 = new PressureSwitch(this, null, new DiscreteCoordinates(11, 5));
		PressureSwitch pressureSwitch11 = new PressureSwitch(this, null, new DiscreteCoordinates(10, 5));
		PressureSwitch pressureSwitch12 = new PressureSwitch(this, null, new DiscreteCoordinates(8, 5));
		PressureSwitch pressureSwitch13 = new PressureSwitch(this, null, new DiscreteCoordinates(7, 5));
		PressureSwitch pressureSwitch14 = new PressureSwitch(this, null, new DiscreteCoordinates(6, 5));
		SignalRock signalRock1 = new SignalRock(this, null, new DiscreteCoordinates(2, 10), Logic.FALSE);
		SignalRock signalRock2 = new SignalRock(this, null, new DiscreteCoordinates(1, 9),
				new MultipleAnd(pressureSwitch1, pressureSwitch2, pressureSwitch3, pressureSwitch4, pressureSwitch5,
						pressureSwitch6, pressureSwitch7, pressureSwitch8, pressureSwitch9, pressureSwitch10,
						pressureSwitch11, pressureSwitch12, pressureSwitch13, pressureSwitch14));
		SignalRock signalRock3 = new SignalRock(this, null, new DiscreteCoordinates(7, 2), Logic.FALSE);
		SignalRock signalRock4 = new SignalRock(this, null, new DiscreteCoordinates(7, 1), Logic.FALSE);
		Cake cake1 = new Cake(this, null, new DiscreteCoordinates(7, 11));
		Cake cake2 = new Cake(this, null, new DiscreteCoordinates(9, 4));
		Cake cake3 = new Cake(this, null, new DiscreteCoordinates(13, 7));
		Cake cake4 = new Cake(this, null, new DiscreteCoordinates(2, 6));
		Key key = new Key(this, null, new DiscreteCoordinates(10, 11));
		SafeBird safeBird = new SafeBird(this, null, new DiscreteCoordinates(2, 3), key);
		Cup cup = new Cup(this, null, new DiscreteCoordinates(1, 3), key);
		OldManHelper oldManHelper = new OldManHelper(this, null, new DiscreteCoordinates(5, 8), Logic.TRUE,
				new Dialog(XMLTexts.getText("dialogOldMan2"), "dialog.1", this));
		registerActor(oldManHelper);
		registerActor(key);
		registerActor(safeBird);
		registerActor(cup);
		registerActor(cake1);
		registerActor(cake2);
		registerActor(cake3);
		registerActor(cake4);
		registerActor(signalRock1);
		registerActor(signalRock2);
		registerActor(signalRock3);
		registerActor(signalRock4);
		registerActor(new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(5, 5), Orientation.DOWN,
				new DiscreteCoordinates(1, 10), new MultipleAnd(cup.getLogic(), cake1, cake2, cake3, cake4)));
		registerActor(pressureSwitch1);
		registerActor(pressureSwitch2);
		registerActor(pressureSwitch3);
		registerActor(pressureSwitch4);
		registerActor(pressureSwitch5);
		registerActor(pressureSwitch6);
		registerActor(pressureSwitch7);
		registerActor(pressureSwitch8);
		registerActor(pressureSwitch9);
		registerActor(pressureSwitch10);
		registerActor(pressureSwitch11);
		registerActor(pressureSwitch12);
		registerActor(pressureSwitch13);
		registerActor(pressureSwitch14);
		return true;
	}

	public Dialog getDialog() {
		return dialog;
	}
}
