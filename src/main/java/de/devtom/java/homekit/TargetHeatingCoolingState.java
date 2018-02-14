package de.devtom.java.homekit;

public enum TargetHeatingCoolingState {
	OFF(0),
	HEAT(1),
	COOL(2),
	AUTO(3);
	
	private int value;
	
	private TargetHeatingCoolingState(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
