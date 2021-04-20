package ch.epfl.cs107.play.signal.logic;

public abstract class LogicSignal implements Logic {

	public final float getIntensity(float t) {
		return Logic.super.getIntensity(t);
	}

	@Override
	public final float getIntensity() {
		// TODO Auto-generated method stub
		return Logic.super.getIntensity();
	}

}
