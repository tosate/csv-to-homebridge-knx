package de.devtom.java.homekit;

public enum CurrentHeatingCoolingState {
	OFF(0),
	HEAT(1),
	COOL(2);
	
	private int value;
	
	private CurrentHeatingCoolingState(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
