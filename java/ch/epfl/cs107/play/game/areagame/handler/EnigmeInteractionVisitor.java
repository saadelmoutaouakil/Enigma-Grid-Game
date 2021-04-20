package ch.epfl.cs107.play.game.areagame.handler;

import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Cake;
import ch.epfl.cs107.play.game.enigme.actor.Cup;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.Ingot;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.OldManHelper;
import ch.epfl.cs107.play.game.enigme.actor.Potion;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.Torch;
import ch.epfl.cs107.play.game.enigme.actor.Weapons;

public interface EnigmeInteractionVisitor extends AreaInteractionVisitor {
	/**
	 * Simulate and interaction between a enigme Interactors and an enigme Apple
	 * 
	 * @param apple
	 *            ( Apple ), not null
	 */
	default void interactWith(Apple apple) {
		// by default the interaction is empty
	}

	default void interactWith(EnigmeBehavior.EnigmeCell cell) {
		// by default the interaction is empty
	}

	default void interactWith(Door door) {
		// by default the interaction is empty
	}

	default void interactWith(Torch torch) {
		// by default the interaction is empty
	}

	default void interactWith(PressureSwitch pressureSwitch) {
		// by default the interaction is empty
	}

	default void interactWith(PressurePlate pressurePlate) {
		// by default the interaction is empty
	}

	default void interactWith(Key key) {
		// by default the interaction is empty
	}

	default void interactWith(Lever lever) {
		// by default the interaction is empty
	}

	default void interactWith(Cake cake) {
		// by default the interaction is empty
	}

	default void interactWith(Cup cup) {
		// by default the interaction is empty
	}

	default void interactWith(Ingot ingot) {
		// by default the interaction is empty
	}

	default void interactWith(Potion potion) {
		// by default the interaction is empty
	}

	default void interactWith(Weapons weapon) {
		// by default the interaction is empty
	}

	default void interactWith(OldManHelper oldManHelper) {
		// by default the interaction is empty
	}
}
