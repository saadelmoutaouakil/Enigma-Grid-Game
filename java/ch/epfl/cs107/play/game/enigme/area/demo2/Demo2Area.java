package ch.epfl.cs107.play.game.enigme.area.demo2;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class Demo2Area extends Area {

	@Override
	public float getCameraScaleFactor() {
		return 22f;
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		setBehavior(new Demo2Behavior(window, getTitle()));
		registerActor(new Background(this));
		return true;
	}

	public String getTitle() {
		return null;
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
	}

}
