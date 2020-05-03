package de.devtom.java.homekit;

import org.apache.commons.lang3.StringUtils;

import de.devtom.java.homebridge.knxd.KnxCsvRecord;

public class LightbulbService extends AbstractService {
//	private static final String KNX_PLUGIN_HANDLER = "HagerDimmableLightbuld";
	
	public LightbulbService(String serviceName, KnxCsvRecord record) {
		super(serviceName, record);
		this.serviceType = ServiceType.LIGHTBULB;
	}
	
	public LightbulbService() {
		super();
		this.serviceType = ServiceType.LIGHTBULB;
	}

	@Override
	protected void initialize(KnxCsvRecord record) {
		Characteristics onCharacteristics = new Characteristics();
		onCharacteristics.setType(CharacteristicsType.ON);
		onCharacteristics.getListen().add(record.getGroupAddress2());
		onCharacteristics.getSet().add(record.getGroupAddress1());
		onCharacteristics.setDpt(LightbulbService.KNX_DPT1);
		
		this.getCharacteristics().add(onCharacteristics);
		
		if(StringUtils.isNotEmpty(record.getGroupAddress3())) {
//			this.handler = LightbulbService.KNX_PLUGIN_HANDLER;
			
			Characteristics brigthnessCharacteristics = new Characteristics();
			brigthnessCharacteristics.setType(CharacteristicsType.BRIGHTNESS);
			brigthnessCharacteristics.getSet().add(record.getGroupAddress3());
			brigthnessCharacteristics.getListen().add(record.getGroupAddress4());
			
			this.getCharacteristics().add(brigthnessCharacteristics);
		}
	}
}
