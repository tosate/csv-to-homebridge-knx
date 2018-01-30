package de.devtom.java.homekit;

import de.devtom.java.homebridge.knxd.KnxCsvRecord;

public class ContactSensorService extends AbstractService {
	public ContactSensorService(KnxCsvRecord record) {
		super(record);
		this.serviceType = ServiceType.CONTACTSENSOR;
	}
	
	public ContactSensorService() {
		super();
		this.serviceType = ServiceType.CONTACTSENSOR;
	}

	@Override
	protected void initialize(KnxCsvRecord record) {
		Characteristics characteristics = new Characteristics();
		characteristics.setType(CharacteristicsType.CONTACTSENSORSTATE);
		characteristics.getListen().add(record.getGroupAddress1());
		characteristics.setDpt(ContactSensorService.KNX_DPT1);
		
		this.getCharacteristics().add(characteristics);
	}
}
