package de.devtom.java.homekit;

import de.devtom.java.homebridge.knxd.KnxCsvRecord;

public class SwitchService extends AbstractService {
	public SwitchService() {
		super();
		this.serviceType = ServiceType.SWITCH;
	}
	
	public SwitchService(KnxCsvRecord record) {
		super(record);
		this.serviceType = ServiceType.SWITCH;
	}

	@Override
	protected void initialize(KnxCsvRecord record) {
		Characteristics onCharacteristics = new Characteristics();
		onCharacteristics.setType(CharacteristicsType.ON);
		onCharacteristics.getSet().add(record.getGroupAddress1());
		onCharacteristics.getListen().add(record.getGroupAddress1());
		onCharacteristics.setDpt(SwitchService.KNX_DPT1);
		
		this.getCharacteristics().add(onCharacteristics);
	}

}
