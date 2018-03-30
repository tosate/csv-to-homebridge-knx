package de.devtom.java.homekit;

import de.devtom.java.homebridge.knxd.KnxCsvRecord;

public class OutletService extends AbstractService {
	public OutletService(KnxCsvRecord record) {
		super(record);
		this.serviceType = ServiceType.OUTLET;
		
	}
	
	public OutletService() {
		super();
		this.serviceType = ServiceType.OUTLET;
	}

	@Override
	protected void initialize(KnxCsvRecord record) {
		Characteristics onCharacteristics = new Characteristics();
		onCharacteristics.setType(CharacteristicsType.ON);
		onCharacteristics.getListen().add(record.getGroupAddress2());
		onCharacteristics.getSet().add(record.getGroupAddress1());
		onCharacteristics.setDpt(OutletService.KNX_DPT1);

		this.getCharacteristics().add(onCharacteristics);
	}

}
