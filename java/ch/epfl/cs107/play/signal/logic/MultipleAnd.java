package ch.epfl.cs107.play.signal.logic;

import java.util.LinkedList;

public class MultipleAnd extends LogicSignal {

	private LinkedList<Logic> input = new LinkedList<Logic>();

	public MultipleAnd(Logic... s) {
		for (int i = 0; i < s.length; ++i) {
			input.add(s[i]);
		}
	}

	@Override
	public boolean isOn() {
		for (int i = 0; i < input.size() - 1; i++) {
			Logic logic = new And(input.get(i), input.get(i + 1));
			if (logic.isOn() == false) {
				return false;
			}
		}
		return true;
	}
}
