package de.devtom.java.homekit;

import de.devtom.java.homebridge.knxd.KnxCsvRecord;

public class GarageDoorOpenerService extends AbstractService {
	private static final String KNX_PLUGIN_HANDLER = "GarageDoorOpenerAdvanced";
	private static final String KEY_SIMULATE_DOOR_CONTACT_MODE = "simulateDoorContactMode";
	private static final String VALUE_SIMULATE_DOOR_CONTACT_MODE = "internal";
	private static final String KEY_DOOR_CONTACT_PRESENT = "doorContactPresent";
	private static final String VALUE_DOOR_CONTACT_PRESENT = "open";
	private static final String KEY_STAIRCASE_FUNC = "staircaseFunc";
	private static final Boolean VALUE_STAIRCASE_FUNC = Boolean.FALSE;
	private static final String KEY_SEPARATE_PULSE_UPDOWN = "separatePulseUpDown";
	private static final Boolean VALUE_SEPARATE_PULSE_UPDOWN = Boolean.TRUE;
	private static final String KEY_SENSOR_ON = "sensorOn";
	private static final Integer VALUE_SENSOR_ON = 1;
	private static final String KEY_PULSE_LENGTH = "pulseLength";
	private static final Integer VALUE_PULSE_LENGTH = 500;
	private static final String KEY_DOOR_RUNTIME = "doorRunTime";
	private static final Integer VALUE_DOOR_RUNTIME = 18500;
	
	public GarageDoorOpenerService() {
		super();
		this.serviceType = ServiceType.GARAGEDOOROPENER;
	}

	public GarageDoorOpenerService(String serviceName, KnxCsvRecord record) {
		super(serviceName, record);
		 this.serviceType = ServiceType.GARAGEDOOROPENER;
		 this.handler = KNX_PLUGIN_HANDLER;
	}

	@Override
	protected void initialize(KnxCsvRecord record) {
		Characteristics currentDoorStateCharacteristics = new Characteristics();
		currentDoorStateCharacteristics.setType(CharacteristicsType.CURRENTDOORSTATE);
		
		this.getCharacteristics().add(currentDoorStateCharacteristics);
		
		Characteristics targetDoorStateCharacteristics = new Characteristics();
		targetDoorStateCharacteristics.setType(CharacteristicsType.TARGETDOORSTATE);
		
		this.getCharacteristics().add(targetDoorStateCharacteristics);
		
		KnxObject knxPulseUpObject = new KnxObject();
		knxPulseUpObject.setType(KnxObjectType.KNXPULSEUP.getValue());
		knxPulseUpObject.setSet(record.getGroupAddress1());
		knxPulseUpObject.setListen(record.getGroupAddress1());
		knxPulseUpObject.setDpt(GarageDoorOpenerService.KNX_DPT1);
		this.getKnxObjects().add(knxPulseUpObject);
		
		
		KnxObject knxPulseDownObject = new KnxObject();
		knxPulseDownObject.setType(KnxObjectType.KNXPULSEDOWN.getValue());
		knxPulseDownObject.setSet(record.getGroupAddress1());
		knxPulseDownObject.setListen(record.getGroupAddress1());
		knxPulseDownObject.setDpt(GarageDoorOpenerService.KNX_DPT1);
		this.getKnxObjects().add(knxPulseDownObject);
		
		KnxObject knxDoorOpenObject = new KnxObject();
		knxDoorOpenObject.setType(KnxObjectType.KNXDOOROPEN.getValue());
		knxDoorOpenObject.setListen(record.getGroupAddress2());
		knxDoorOpenObject.setDpt(GarageDoorOpenerService.KNX_DPT1);
		this.getKnxObjects().add(knxDoorOpenObject);
		
		this.getKnxReadRequests().add(record.getGroupAddress2());
		this.getKnxReadRequests().add(record.getGroupAddress3());
		
		this.getLocalConstants().getLocalConstantsMap().put(KEY_SIMULATE_DOOR_CONTACT_MODE, VALUE_SIMULATE_DOOR_CONTACT_MODE);
		this.getLocalConstants().getLocalConstantsMap().put(KEY_DOOR_CONTACT_PRESENT, VALUE_DOOR_CONTACT_PRESENT);
		this.getLocalConstants().getLocalConstantsMap().put(KEY_STAIRCASE_FUNC, VALUE_STAIRCASE_FUNC);
		this.getLocalConstants().getLocalConstantsMap().put(KEY_SEPARATE_PULSE_UPDOWN, VALUE_SEPARATE_PULSE_UPDOWN);
		this.getLocalConstants().getLocalConstantsMap().put(KEY_SENSOR_ON, VALUE_SENSOR_ON);
		this.getLocalConstants().getLocalConstantsMap().put(KEY_PULSE_LENGTH, VALUE_PULSE_LENGTH);
		this.getLocalConstants().getLocalConstantsMap().put(KEY_DOOR_RUNTIME, VALUE_DOOR_RUNTIME);
	}
}
