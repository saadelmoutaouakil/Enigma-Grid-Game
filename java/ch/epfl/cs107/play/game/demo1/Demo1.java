package ch.epfl.cs107.play.game.demo1;

import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.demo1.actor.MovingRock;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Demo1 implements Game {

	private MovingRock rock1;
	private Actor actor1;
	private TextGraphics boom = new TextGraphics("BOOM !!!", 0.04f, Color.RED);
	private Window window;
	private FileSystem fileSystem;
	private float radius = 0.2f;

	public String getTitle() {
		return "Demo1";
	}

	public int getFrameRate() {
		return 24;
	}

	public boolean begin(Window window, FileSystem fileSystem) {
		actor1 = new GraphicsEntity(Vector.ZERO, new ShapeGraphics(new Circle(radius), null, Color.RED, 0.005f));
		this.window = window;
		this.fileSystem = fileSystem;
		rock1 = new MovingRock(new Vector(0.3f, 0.3f), "Hello, i'm a moving rock !");
		return true;
	}

	public void end() {
	}

	public void update(float deltaTime) {
		actor1.draw(window);
		rock1.draw(window);
		Keyboard keyboard = window.getKeyboard();
		Button downArrow = keyboard.get(Keyboard.DOWN);
		if (downArrow.isDown()) {
			rock1.update(deltaTime);
		}
		if (Math.pow(rock1.getPosition().x, 2) + Math.pow(rock1.getPosition().y, 2) <= Math.pow(radius, 2)) {
			// We compared the squares of the radius and the norm of the rock1's position
			// vector
			boom.draw(window);
		}
	}
}
