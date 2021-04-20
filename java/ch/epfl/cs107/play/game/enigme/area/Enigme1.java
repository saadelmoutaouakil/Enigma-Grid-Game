package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Boy;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.actor.GellyTeleporter;
import ch.epfl.cs107.play.game.enigme.actor.Ingot;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.OldManHelper;
import ch.epfl.cs107.play.game.enigme.actor.Potion;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Weapons;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.io.XMLTexts;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.And;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.signal.logic.Not;
import ch.epfl.cs107.play.signal.logic.Or;
import ch.epfl.cs107.play.window.Window;

public class Enigme1 extends EnigmeArea {
	private Dialog indication1;
	private Dialog indication2;

	@Override
	public String getTitle() {
		return "Enigme1";
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		Ingot ingot1 = new Ingot(this, null, new DiscreteCoordinates(14, 14));
		Ingot ingot2 = new Ingot(this, null, new DiscreteCoordinates(17, 22));
		Ingot ingot3 = new Ingot(this, null, new DiscreteCoordinates(10, 27));
		Ingot ingot4 = new Ingot(this, null, new DiscreteCoordinates(19, 24));
		Ingot ingot5 = new Ingot(this, null, new DiscreteCoordinates(19, 25));
		Ingot ingot6 = new Ingot(this, null, new DiscreteCoordinates(19, 26));
		Lever lever1 = new Lever(this, null, new DiscreteCoordinates(23, 30));
		Lever lever2 = new Lever(this, null, new DiscreteCoordinates(17, 18));
		Lever lever3 = new Lever(this, null, new DiscreteCoordinates(19, 22));
		SignalRock signalRock1 = new SignalRock(this, null, new DiscreteCoordinates(19, 18), lever1);
		SignalRock signalRock2 = new SignalRock(this, null, new DiscreteCoordinates(13, 24), new And(lever2, lever3));
		Boy boy = new Boy(this, null, new DiscreteCoordinates(10, 24), new MultipleAnd(ingot1, ingot2, ingot3));
		Potion potion1 = new Potion(this, null, new DiscreteCoordinates(11, 1), "potion.4");
		Potion potion2 = new Potion(this, null, new DiscreteCoordinates(5, 6), "potion.5");
		Weapons axe = new Weapons(this, null, new DiscreteCoordinates(1, 7), "axe.2");
		Weapons sword = new Weapons(this, null, new DiscreteCoordinates(3, 5), "sword.2");
		Weapons bow = new Weapons(this, null, new DiscreteCoordinates(10, 7), "bow.2");
		OldManHelper oldManHelper = new OldManHelper(this, null, new DiscreteCoordinates(6, 8),
				new And(potion1, potion2), new Dialog(XMLTexts.getText("dialogOldMan1"), "dialog.1", this));
		indication1 = new Dialog(XMLTexts.getText("enigme1_indication1"), "dialog.1", this);
		indication2 = new Dialog(XMLTexts.getText("enigme1_indication2"), "dialog.1", this);
		registerActor(potion1);
		registerActor(potion2);
		registerActor(axe);
		registerActor(sword);
		registerActor(bow);
		registerActor(oldManHelper);
		registerActor(boy);
		registerActor(new GellyTeleporter(this, "Enigme1", new DiscreteCoordinates(1, 1), Orientation.DOWN,
				new DiscreteCoordinates(10, 25), new MultipleAnd(ingot1, ingot2, ingot3)));
		registerActor(new SignalRock(this, null, new DiscreteCoordinates(23, 13), Logic.FALSE));
		registerActor(new SignalRock(this, null, new DiscreteCoordinates(20, 27), Logic.FALSE));
		registerActor(new SignalRock(this, null, new DiscreteCoordinates(18, 24), Logic.FALSE));
		registerActor(new SignalRock(this, null, new DiscreteCoordinates(21, 24), Logic.FALSE));
		registerActor(new SignalRock(this, null, new DiscreteCoordinates(8, 8), Logic.FALSE));
		registerActor(new SignalRock(this, null, new DiscreteCoordinates(13, 5), Logic.FALSE));
		registerActor(
				new SignalRock(this, null, new DiscreteCoordinates(1, 1), new And(new Not(potion1), new Not(potion2))));
		registerActor(signalRock1);
		registerActor(signalRock2);
		registerActor(ingot1);
		registerActor(ingot2);
		registerActor(ingot3);
		registerActor(ingot4);
		registerActor(ingot5);
		registerActor(ingot6);
		registerActor(lever1);
		registerActor(lever2);
		registerActor(lever3);
		registerActor(new GellyTeleporter(this, "Enigme2", new DiscreteCoordinates(5, 5), Orientation.DOWN,
				new DiscreteCoordinates(7, 8), new MultipleAnd(axe, sword, bow, potion1, potion2)));
		return true;
	}

	public Dialog getIndication1() {
		return indication1;
	}

	public Dialog getIndication2() {
		return indication2;
	}

}
