package de.devtom.java.homekit;

import de.devtom.java.homebridge.knxd.KnxCsvRecord;

public class SmokeSensorService extends AbstractService {
	
	public SmokeSensorService() {
		super();
		this.serviceType = ServiceType.SMOKESENSOR;
	}
	
	public SmokeSensorService(KnxCsvRecord record) {
		super(record);
		this.serviceType = ServiceType.SMOKESENSOR;
	}

	@Override
	protected void initialize(KnxCsvRecord record) {
		Characteristics smokeDetectedCharacteristics = new Characteristics();
		smokeDetectedCharacteristics.setType(CharacteristicsType.SMOKEDETECTED);
		smokeDetectedCharacteristics.getListen().add(record.getGroupAddress1());
		smokeDetectedCharacteristics.setDpt(SmokeSensorService.KNX_DPT1);
		
		this.getCharacteristics().add(smokeDetectedCharacteristics);
	}

}
