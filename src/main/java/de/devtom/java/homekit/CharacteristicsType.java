package de.devtom.java.homekit;

public enum CharacteristicsType {
	ON("On"),
	BRIGHTNESS("Brightness"),
	TARGETPOSITION("TargetPosition"),
	CURRENTPOSITION("CurrentPosition"),
	POSITIONSTATE("PositionState"),
	CURRENTDOORSTATE("CurrentDoorState)"),
	TARGETDOORSTATE("TargetDoorState"),
	CONTACTSENSORSTATE("ContactSensorState"),
	TARGETHORIZONTALTILTANGLE("TargetHorizontalTiltAngle"),
	CURRENTHORIZONTALTILTANGLE("CurrentHorizontalTiltAngle");
	
	private String value;
	
	private CharacteristicsType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}