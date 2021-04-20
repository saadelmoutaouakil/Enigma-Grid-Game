package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.AreaBehavior.Cell;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.window.Window;

public class EnigmeBehavior extends AreaBehavior {

	public enum EnigmeCellType {
		NULL(0), WALL(-16777216), // RGB code of black
		DOOR(-65536), // RGB code of red
		WATER(-16776961), // RGB code of blue
		INDOOR_WALKABLE(-1), OUTDOOR_WALKABLE(-14112955);
		final int type;

		EnigmeCellType(int type) {
			this.type = type;
		}

		public static EnigmeCellType toType(int type) {
			switch (type) {
			case 0: {
				return NULL;
			}
			case -16777216: {
				return WALL;
			}
			case -65536: {
				return DOOR;
			}
			case -16776961: {
				return WATER;
			}
			case -1: {
				return INDOOR_WALKABLE;
			}
			case -14112955: {
				return OUTDOOR_WALKABLE;
			}
			default: {
				return NULL;
			}
			}
		}

	}

	public class EnigmeCell extends Cell {
		private EnigmeCellType cellType;

		private EnigmeCell(int x, int y, EnigmeCellType type) {
			super(x, y);
			cellType = type;
		}

		public void acceptInteraction(AreaInteractionVisitor v) {
			((EnigmeInteractionVisitor) v).interactWith(this);
		}

		@Override
		public boolean takeCellSpace() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isViewInteractable() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isCellInteractable() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		protected boolean canEnter(Interactable entity) {
			if (this.cellType == EnigmeCellType.WALL || this.cellType == EnigmeCellType.NULL) {
				return false;
			}

			for (Interactable interactables : entities) {
				if (interactables.takeCellSpace()) {
					return false;
				}
			}
			return true;
		}

		@Override
		protected boolean canLeave(Interactable entity) {
			// TODO Auto-generated method stub
			return true;
		}

	}

	public EnigmeBehavior(Window window, String fileName) {
		super(window, fileName);
		for (int x = 0; x < getWidth(); ++x) {
			for (int y = 0; y < getHeight(); ++y) {
				EnigmeCellType cellType = EnigmeCellType.toType(getBehaviorMap().getRGB(getHeight() - 1 - y, x));
				getCells()[x][y] = new EnigmeCell(x, y, cellType);
			}
		}

	}
}
