package de.devtom.java.homekit;

import de.devtom.java.homebridge.knxd.KnxCsvRecord;

public class WindowCoveringService extends AbstractService {
	private static final String KNX_PLUGIN_HANDLER = "HagerWindowCoveringTilt";
	
	public WindowCoveringService() {
		super();
		this.serviceType = ServiceType.WINDOWCOVERING;
	}

	public WindowCoveringService(KnxCsvRecord record) {
		super(record);
		this.serviceType = ServiceType.WINDOWCOVERING;
		this.handler = KNX_PLUGIN_HANDLER;
	}

	@Override
	protected void initialize(KnxCsvRecord record) {
		Characteristics targetPositionCharacteristics = new Characteristics();
		targetPositionCharacteristics.setType(CharacteristicsType.TARGETPOSITION);
		targetPositionCharacteristics.getSet().add(record.getGroupAddress1());
		targetPositionCharacteristics.setDpt(WindowCoveringService.KNX_DPT1);

		this.getCharacteristics().add(targetPositionCharacteristics);

		Characteristics currentPositionCharacteristics = new Characteristics();
		currentPositionCharacteristics.setType(CharacteristicsType.CURRENTPOSITION);
		currentPositionCharacteristics.getListen().add(record.getGroupAddress2());

		this.getCharacteristics().add(currentPositionCharacteristics);

		Characteristics positionStateCharacteristics = new Characteristics();
		positionStateCharacteristics.setType(CharacteristicsType.POSITIONSTATE);

		this.getCharacteristics().add(positionStateCharacteristics);
	}
}
