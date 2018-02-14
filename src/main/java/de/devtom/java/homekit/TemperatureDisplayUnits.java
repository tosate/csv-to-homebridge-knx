package de.devtom.java.homekit;

public enum TemperatureDisplayUnits {
	CELSIUS(0),
	FAHRENHEIT(1);
	
	private int value;
	
	private TemperatureDisplayUnits(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
