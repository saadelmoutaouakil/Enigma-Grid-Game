package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class EnigmePlayer extends MovableAreaEntity implements Interactor {

	private boolean passingDoor;
	private final static int ANIMATION_DURATION = 8;
	private Door lastDoor;
	private static int stepCounter;
	private final EnigmePlayerHandler handler;
	private Sprite[] spritesDOWN;
	private Sprite[] spritesUP;
	private Sprite[] spritesRIGHT;
	private Sprite[] spritesLEFT;

	public void setPassingDoor(boolean passingDoor) {
		this.passingDoor = passingDoor;
	}

	public boolean isPassingDoor() {
		return passingDoor;
	}

	public void enterArea(Area area, DiscreteCoordinates position) {
		setOwnerArea(area);
		area.registerActor(this);
		setCurrentPosition(position.toVector());
		getOwnerArea().setViewCandidate(this);
		resetMotion();
	}

	public void leaveArea() {
		getOwnerArea().unregisterActor(this);
	}

	public void setIsPassingDoor(Door door) {
		passingDoor = true;
		lastDoor = door;
	}

	public Door passedDoor() {
		return lastDoor;
	}

	public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
		handler = new EnigmePlayerHandler();
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	public EnigmePlayer(Area area, DiscreteCoordinates coordinates) {
		this(area, Orientation.DOWN, coordinates);
		Vector anchor = new Vector(0.25f, 0.32f);
		spritesDOWN = new Sprite[4];
		for (int i = 0; i < 4; ++i) {
			spritesDOWN[i] = new Sprite("max.new.1", 0.5f, 0.65625f, this, new RegionOfInterest(0, i * 21, 16, 21),
					anchor);
		}
		spritesUP = new Sprite[4];
		for (int i = 0; i < 4; ++i) {
			spritesUP[i] = new Sprite("max.new.1", 0.5f, 0.65625f, this, new RegionOfInterest(32, i * 21, 16, 21),
					anchor);
		}
		spritesRIGHT = new Sprite[4];
		for (int i = 0; i < 4; ++i) {
			spritesRIGHT[i] = new Sprite("max.new.1", 0.5f, 0.65625f, this, new RegionOfInterest(48, i * 21, 16, 21),
					anchor);
		}
		spritesLEFT = new Sprite[4];
		for (int i = 0; i < 4; ++i) {
			spritesLEFT[i] = new Sprite("max.new.1", 0.5f, 0.65625f, this, new RegionOfInterest(16, i * 21, 16, 21),
					anchor);
		}
	}

	@Override
	public void update(float deltaTime) {
		if (getOwnerArea().getKeyboard().get(Keyboard.LEFT).isDown()) {
			++stepCounter;
			if (getOrientation().equals(Orientation.LEFT)) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.LEFT);
			}
		}
		if (getOwnerArea().getKeyboard().get(Keyboard.RIGHT).isDown()) {
			++stepCounter;
			if (getOrientation().equals(Orientation.RIGHT)) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.RIGHT);
			}
		}
		if (getOwnerArea().getKeyboard().get(Keyboard.UP).isDown()) {
			++stepCounter;
			if (getOrientation().equals(Orientation.UP)) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.UP);
			}
		}
		if (getOwnerArea().getKeyboard().get(Keyboard.DOWN).isDown()) {
			++stepCounter;
			if (getOrientation().equals(Orientation.DOWN)) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.DOWN);
			}
		}
		super.update(deltaTime);

	}

	@Override
	public void draw(Canvas canvas) {
		if (getOrientation().equals(Orientation.DOWN)) {
			spritesDOWN[stepCounter % 4].draw(canvas);
		}
		if (getOrientation().equals(Orientation.UP)) {
			spritesUP[stepCounter % 4].draw(canvas);
		}

		if (getOrientation().equals(Orientation.RIGHT)) {
			spritesRIGHT[stepCounter % 4].draw(canvas);
		}
		if (getOrientation().equals(Orientation.LEFT)) {
			spritesLEFT[stepCounter % 4].draw(canvas);
		}
	}

	@Override
	public boolean takeCellSpace() {
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		// TODO Auto-generated method stub
		return true;
	}

	public void interactWith(Interactable other) {
		other.acceptInteraction(handler);
	}

	private class EnigmePlayerHandler implements EnigmeInteractionVisitor {
		@Override
		public void interactWith(Door door) {
			setIsPassingDoor(door);
		}

		@Override
		public void interactWith(Apple apple) {
			apple.pickUp();
		}

		@Override
		public void interactWith(PressureSwitch pressureSwitch) {
			if (getTargetMainCellCoordinates().equals(getCurrentCells().get(0))) {
				pressureSwitch.switcher();
			}

		}

		@Override
		public void interactWith(Torch torch) {
			torch.switcher();
		}

		@Override
		public void interactWith(Lever lever) {
			lever.switcher();
		}

		@Override
		public void interactWith(PressurePlate pressurePlate) {
			pressurePlate.setOn(true);
		}

		@Override
		public void interactWith(Key key) {
			key.pickUp();
			key.setOn(true);
		}

		@Override
		public void interactWith(Cup cup) {
			cup.pickUp();
			cup.setOn(true);
		}

		@Override
		public void interactWith(Cake cake) {
			cake.pickUp();
			cake.setOn(true);
		}

		@Override
		public void interactWith(Ingot ingot) {
			ingot.pickUp();
			ingot.setOn(true);
		}

		@Override
		public void interactWith(Potion potion) {
			potion.pickUp();
			potion.setOn(true);
		}

		@Override
		public void interactWith(Weapons weapon) {
			weapon.pickUp();
			weapon.setOn(true);
		}

		@Override
		public void interactWith(OldManHelper oldManHelper) {
			oldManHelper.setDialogOn(true);
		}

	}

	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates().jump(getOrientation().toVector()));
	}

	@Override
	public boolean wantsCellInteraction() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean wantsViewInteraction() {
		Keyboard keyboard = getOwnerArea().getKeyboard();
		Button buttonL = keyboard.get(Keyboard.L);
		if (buttonL.isPressed()) {
			return true;
		} else
			return false;
	}

}
