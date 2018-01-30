package de.devtom.java.homekit;

public enum ServiceType {
	LIGHTBULB("Lightbulb"),
	WINDOWCOVERING("WindowCovering"),
	GARAGEDOOROPENER("GarageDoorOpener"),
	OUTLET("Outlet"),
	CONTACTSENSOR("ContactSensor");

	private String value;
	
	private ServiceType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
