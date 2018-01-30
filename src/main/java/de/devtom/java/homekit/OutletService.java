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
		Characteristics characteristics = new Characteristics();
		characteristics.setType(CharacteristicsType.ON);
		characteristics.getListen().add(record.getGroupAddress2());
		characteristics.getSet().add(record.getGroupAddress1());
		characteristics.setDpt(OutletService.KNX_DPT1);

		this.getCharacteristics().add(characteristics);
	}

}
