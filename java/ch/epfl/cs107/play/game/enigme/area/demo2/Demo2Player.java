package ch.epfl.cs107.play.game.enigme.area.demo2;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2Cell;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2CellType;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class Demo2Player extends MovableAreaEntity {

	private boolean doorPassing;
	private ImageGraphics sprite;
	private final static int ANIMATION_DURATION = 8;

	public boolean isDoorPassing() {
		return doorPassing;
	}

	public void enterArea(Area area, DiscreteCoordinates position) {
		setOwnerArea(area);
		getOwnerArea().registerActor(this);
		setCurrentPosition(position.toVector());
		getOwnerArea().setViewCandidate(this);
		resetMotion();
	}

	public void leaveArea() {
		getOwnerArea().unregisterActor(this);

	}

	public void setDoorPassing(boolean doorPassing) {
		this.doorPassing = doorPassing;
	}

	public Demo2Player(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);

	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());

	}

	public Demo2Player(Area area, DiscreteCoordinates coordinates) {
		this(area, Orientation.DOWN, coordinates);
		sprite = new Sprite("ghost.1", 1, 1.f, this);

	}

	@Override
	public void update(float deltaTime) {

		if (getOwnerArea().getKeyboard().get(Keyboard.LEFT).isDown()) {

			if (getOrientation().equals(Orientation.LEFT)) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.LEFT);
			}

		}
		if (getOwnerArea().getKeyboard().get(Keyboard.RIGHT).isDown()) {
			if (getOrientation().equals(Orientation.RIGHT)) {
				move(ANIMATION_DURATION);

			} else {
				setOrientation(Orientation.RIGHT);
			}
		}
		if (getOwnerArea().getKeyboard().get(Keyboard.UP).isDown()) {
			if (getOrientation().equals(Orientation.UP)) {
				move(ANIMATION_DURATION);

			} else {
				setOrientation(Orientation.UP);
			}
		}
		if (getOwnerArea().getKeyboard().get(Keyboard.DOWN).isDown()) {
			if (getOrientation().equals(Orientation.DOWN)) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.DOWN);
			}
		}
		super.update(deltaTime);
	}

	@Override
	protected boolean move(int framesForMove) {
		if (super.move(framesForMove)) {
			doorPassing = false;
			for (int i = 0; i < getEnteringCells().size() && doorPassing == false; ++i) {
				for (int j = 0; j < getEnteringCells().size() && doorPassing == false; ++j) {
					int x = getEnteringCells().get(i).x;
					int y = getEnteringCells().get(j).y;

					if (((Demo2Cell) getOwnerArea().getAreaBehavior().getCells()[x][y]).getCellType()
							.equals(Demo2CellType.DOOR)) {
						doorPassing = true;
					}
				}
				return true;
			}
		}
		return false;

	}

	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
	}

	@Override
	public boolean takeCellSpace() {
		// TODO Auto-generated method stub
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
}
