package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.area.demo2.Demo2Player;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room0;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room1;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Window;

public class Demo2 extends AreaGame {

	private Demo2Player player;
	private Room0 room0 = new Room0();
	private Room1 room1 = new Room1();

	@Override
	public int getFrameRate() {
		// TODO Auto-generated method stub
		return 24;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Demo2";
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		addArea(room0);
		addArea(room1);
		setCurrentArea(room0.getTitle(), false);
		player = new Demo2Player(getCurrentArea(), new DiscreteCoordinates(5, 5));
		player.enterArea(getCurrentArea(), new DiscreteCoordinates(5, 5));
		return true;
	}

	@Override
	public void update(float deltaTime) {
		if (player.isDoorPassing() && getCurrentArea().getTitle().equals(room0.getTitle())) {
			setCurrentArea(room1.getTitle(), false);
			player.leaveArea();
			player.enterArea(room1, new DiscreteCoordinates(5, 2));
			player.setDoorPassing(false);

		}
		if (player.isDoorPassing() && getCurrentArea().getTitle().equals(room1.getTitle())) {
			setCurrentArea(room0.getTitle(), false);
			player.leaveArea();
			player.enterArea(room0, new DiscreteCoordinates(5, 5));
			player.setDoorPassing(false);

		}
		super.update(deltaTime);
	}

}
