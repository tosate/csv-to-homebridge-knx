package de.devtom.java.homekit;

public enum KnxObjectType {
	KNXPULSEUP("KNXPulseUp"),
	KNXPULSEDOWN("KNXPulseDown"),
	KNXDOOROPEN("KNXDoorOpen");
	
	private String value;
	
	private KnxObjectType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
