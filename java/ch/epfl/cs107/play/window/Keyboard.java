package ch.epfl.cs107.play.window;

/**
 * Represents the keyboard.
 */
public interface Keyboard {
	// TODO put int constants here, instead of using KeyEvent directly?
	int ENTER = 10;
	int SPACE = 32;
	int A = 65;
	int B = 66;
	int D = 68;
	int I = 73;
	int K = 75;
	int L = 76;
	int S = 83;
	int W = 87;
	int UP = 38;
	int DOWN = 40;
	int LEFT = 37;
	int RIGHT = 39;

	/**
	 * Getter for the button corresponding to the given code
	 * 
	 * @param code
	 *            (int): the given code
	 * @return (Button): state of the button for the given code
	 */
	Button get(int code);

}
