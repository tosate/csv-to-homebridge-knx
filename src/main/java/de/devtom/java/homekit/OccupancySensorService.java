package de.devtom.java.homekit;

import de.devtom.java.homebridge.knxd.KnxCsvRecord;

public class OccupancySensorService extends AbstractService {

	public OccupancySensorService() {
		super();
		this.serviceType = ServiceType.OCCUPANCYSENSOR;
	}
	
	public OccupancySensorService(String serviceName, KnxCsvRecord record) {
		super(serviceName, record);
		this.serviceType = ServiceType.OCCUPANCYSENSOR;
	}
	
	@Override
	protected void initialize(KnxCsvRecord record) {
		Characteristics occupancyDetectedCharacteristics = new Characteristics();
		occupancyDetectedCharacteristics.setType(CharacteristicsType.OCCUPANCYDETECTED);
		occupancyDetectedCharacteristics.getListen().add(record.getGroupAddress1());
		occupancyDetectedCharacteristics.setDpt(OccupancySensorService.KNX_DPT1);
		
		this.getCharacteristics().add(occupancyDetectedCharacteristics);
	}

}
