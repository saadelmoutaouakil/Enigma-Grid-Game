package ch.epfl.cs107.play.signal.logic;

import java.util.LinkedList;

public class LogicNumber extends LogicSignal {

	private float nb;
	LinkedList<Logic> e;

	public LogicNumber(float nb, Logic... e) {
		this.nb = nb;
		this.e = new LinkedList<Logic>();
		for (Logic tab : e) {
			this.e.add(tab);
		}
	}

	@Override
	public boolean isOn() {
		if (nb < 0 || nb > Math.pow(2, e.size()) || e.size() > 12) {
			return false;
		}
		float signalNumber = 0;
		for (int i = 0; i < e.size(); ++i) {
			signalNumber += Math.pow(2, i) * e.get(i).getIntensity();
		}
		if (signalNumber == nb) {
			return true;
		} else
			return false;
	}

}
