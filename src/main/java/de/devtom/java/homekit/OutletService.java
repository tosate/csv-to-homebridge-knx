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
		onCharacteristics.getSet().add(record.getGroupAddress1());
		onCharacteristics.setDpt(OutletService.KNX_DPT1);

		this.getCharacteristics().add(onCharacteristics);
		
		Characteristics outletInUseCharacteristics = new Characteristics();
		outletInUseCharacteristics.setType(CharacteristicsType.OUTLETINUSE);
		outletInUseCharacteristics.getListen().add(record.getGroupAddress2());
		outletInUseCharacteristics.setDpt(OutletService.KNX_DPT1);
		
		this.getCharacteristics().add(outletInUseCharacteristics);
	}

}
