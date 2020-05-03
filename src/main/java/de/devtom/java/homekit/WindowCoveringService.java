package de.devtom.java.homekit;

import org.apache.commons.lang3.StringUtils;

import de.devtom.java.homebridge.knxd.KnxCsvRecord;

public class WindowCoveringService extends AbstractService {
	private static final String KNX_PLUGIN_HANDLER = "HagerWindowCoveringTilt";
	
	public WindowCoveringService() {
		super();
		this.serviceType = ServiceType.WINDOWCOVERING;
	}

	public WindowCoveringService(String serviceName, KnxCsvRecord record) {
		super(serviceName, record);
		this.serviceType = ServiceType.WINDOWCOVERING;
		this.handler = KNX_PLUGIN_HANDLER;
	}

	@Override
	protected void initialize(KnxCsvRecord record) {
		Characteristics targetPositionCharacteristics = new Characteristics();
		targetPositionCharacteristics.setType(CharacteristicsType.TARGETPOSITION);
		targetPositionCharacteristics.getSet().add(record.getGroupAddress1());
		targetPositionCharacteristics.getListen().add(record.getGroupAddress1());
		targetPositionCharacteristics.setDpt(WindowCoveringService.KNX_DPT5);

		this.getCharacteristics().add(targetPositionCharacteristics);

		Characteristics currentPositionCharacteristics = new Characteristics();
		currentPositionCharacteristics.setType(CharacteristicsType.CURRENTPOSITION);
		currentPositionCharacteristics.getListen().add(record.getGroupAddress2());
		currentPositionCharacteristics.setDpt(WindowCoveringService.KNX_DPT5);

		this.getCharacteristics().add(currentPositionCharacteristics);
		
		Characteristics positionStateCharacteristics = new Characteristics();
		positionStateCharacteristics.setType(CharacteristicsType.POSITIONSTATE);
		
		this.getCharacteristics().add(positionStateCharacteristics);
		
		if(StringUtils.isNotEmpty(record.getGroupAddress3())) {
			Characteristics targetHorizontalTiltAngleCharacteristics = new Characteristics();
			targetHorizontalTiltAngleCharacteristics.setType(CharacteristicsType.TARGETHORIZONTALTILTANGLE);
			targetHorizontalTiltAngleCharacteristics.getSet().add(record.getGroupAddress3());
			targetHorizontalTiltAngleCharacteristics.getListen().add(record.getGroupAddress3());
			targetHorizontalTiltAngleCharacteristics.setDpt(WindowCoveringService.KNX_DPT5);
			
			this.getCharacteristics().add(targetHorizontalTiltAngleCharacteristics);
			
			Characteristics currentHorizontalTiltAngleCharacteristics = new Characteristics();
			currentHorizontalTiltAngleCharacteristics.setType(CharacteristicsType.CURRENTHORIZONTALTILTANGLE);
			currentHorizontalTiltAngleCharacteristics.getListen().add(record.getGroupAddress4());
			currentHorizontalTiltAngleCharacteristics.setDpt(WindowCoveringService.KNX_DPT5);
			
			this.getCharacteristics().add(currentHorizontalTiltAngleCharacteristics);
		}
	}
}
