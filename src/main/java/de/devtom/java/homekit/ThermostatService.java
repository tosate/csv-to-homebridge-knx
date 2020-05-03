package de.devtom.java.homekit;

import de.devtom.java.homebridge.knxd.KnxCsvRecord;

public class ThermostatService extends AbstractService {
	public ThermostatService() {
		super();
		this.serviceType = ServiceType.THERMOSTAT;
	}
	
	public ThermostatService(String serviceName, KnxCsvRecord record) {
		super(serviceName, record);
		this.serviceType = ServiceType.THERMOSTAT;
	}

	@Override
	protected void initialize(KnxCsvRecord record) {
		Characteristics currentHeatingCoolingState = new Characteristics();
		currentHeatingCoolingState.setType(CharacteristicsType.CURRENTHEATINGCOOLINGSTATE);
		currentHeatingCoolingState.getListen().add(record.getGroupAddress4());
		
		this.getCharacteristics().add(currentHeatingCoolingState);
		
		Characteristics targetHeatingCoolingState = new Characteristics();
		targetHeatingCoolingState.setType(CharacteristicsType.TARGETHEATINGCOOLINGSTATE);
		targetHeatingCoolingState.getListen().add(record.getGroupAddress5());
		targetHeatingCoolingState.setDpt(ThermostatService.KNX_DPT5);
		
		this.getCharacteristics().add(targetHeatingCoolingState);
		
		Characteristics currentTemperature = new Characteristics();
		currentTemperature.setType(CharacteristicsType.CURRENTTEMPERATURE);
		currentTemperature.getListen().add(record.getGroupAddress1());
		currentTemperature.setDpt(ThermostatService.KNX_DPT9);
		/*
		 * maxValue: 100
		 * minValue: 0
		 * minStep: 0.1
		 */
		
		this.getCharacteristics().add(currentTemperature);
		
		Characteristics targetTemperature = new Characteristics();
		targetTemperature.setType(CharacteristicsType.TARGETTEMPERATURE);
		targetTemperature.getListen().add(record.getGroupAddress2());
		targetTemperature.getSet().add(record.getGroupAddress3());
		targetTemperature.setDpt(ThermostatService.KNX_DPT9);
		/*
		 * maxValue: 38
		 * minValue: 10
		 * minStep: 0.1
		 */
		
		this.getCharacteristics().add(targetTemperature);
		
		Characteristics temperatureDisplayUnits = new Characteristics();
		temperatureDisplayUnits.setType(CharacteristicsType.TEMPERATUREDISPLAYUNITS);
		temperatureDisplayUnits.setTemperatureDisplayUnits(Integer.valueOf(TemperatureDisplayUnits.CELSIUS.getValue()));
		
		this.getCharacteristics().add(temperatureDisplayUnits);
	}
}
