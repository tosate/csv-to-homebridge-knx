package de.devtom.java.homekit;

import java.util.HashMap;
import java.util.Map;

import de.devtom.java.homebridge.knxd.KnxCsvRecord;

public class ServiceFactory {
	private static ServiceFactory instance;
	
	private Map<String, AbstractService> servicesMap;
	private int servicesCount = 0;
	private int contactSensorCount = 0;
	private int garageDoorOpenerCount = 0;
	private int lightbulbCount = 0;
	private int outletCount = 0;
	private int occupancySensorCount = 0;
	private int smokeSensorCount = 0;
	private int switchSensorCount = 0;
	private int thermostaCount = 0;
	private int windowCoveringCount = 0;
	
	private ServiceFactory() {
		this.servicesMap = new HashMap<String, AbstractService>();
	}
	
	public static ServiceFactory getInstance() {
		if(instance == null) {
			instance = new ServiceFactory();
		}

		return instance;
	}
	
	public LightbulbService getNewLightbulbService(KnxCsvRecord record) throws Exception {
		String serviceName = String.format("Lightbulb_%03d", ++this.lightbulbCount);
		checkForExistingService(serviceName);
		
		LightbulbService lightbulbService = new LightbulbService(serviceName, record);
		servicesMap.put(serviceName, lightbulbService);
		
		return lightbulbService;
	}

	public WindowCoveringService getNewWindowCoveringService(KnxCsvRecord record) throws Exception {
		String serviceName = String.format("WindowCovering_%03d", ++this.windowCoveringCount);
		checkForExistingService(serviceName);
		
		WindowCoveringService windowCoveringService = new WindowCoveringService(serviceName, record);
		servicesMap.put(serviceName, windowCoveringService);
		
		return windowCoveringService;
	}

	public GarageDoorOpenerService getNewGarageDoorOpenerService(KnxCsvRecord record) throws Exception {
		String serviceName = String.format("GarageDoorOpener_%03d", ++this.garageDoorOpenerCount);
		checkForExistingService(serviceName);
		
		GarageDoorOpenerService garageDoorOpenerService = new GarageDoorOpenerService(serviceName, record);
		servicesMap.put(serviceName, garageDoorOpenerService);
		
		return garageDoorOpenerService;
	}
	
	private void checkForExistingService(String serviceName) throws Exception {
		if(servicesMap.containsKey(serviceName)) {
			throw new Exception(serviceName + " is not a unique service name!");
		} else {
			this.servicesCount++;
		}
	}

	public OutletService getNewOutletService(KnxCsvRecord record) throws Exception {
		String serviceName = String.format("Outlet_%03d", ++this.outletCount);
		checkForExistingService(serviceName);
		
		OutletService outletService = new OutletService(serviceName, record);
		servicesMap.put(outletService.getServiceName(), outletService);
		
		return outletService;
	}

	public ContactSensorService getNewContactSensorService(KnxCsvRecord record) throws Exception {
		String serviceName = String.format("ContactSensor_%03d",  ++this.contactSensorCount);
		checkForExistingService(serviceName);
		
		ContactSensorService contactSensorService = new ContactSensorService(serviceName, record);
		servicesMap.put(contactSensorService.getServiceName(), contactSensorService);
		
		return contactSensorService;
	}

	public OccupancySensorService getNewOccupancySensorService(KnxCsvRecord record) throws Exception {
		String serviceName = String.format("OccopancySensor_%03d", ++this.occupancySensorCount);
		checkForExistingService(serviceName);
		
		OccupancySensorService occupancySensorService = new OccupancySensorService(serviceName, record);
		servicesMap.put(occupancySensorService.getServiceName(), occupancySensorService);
		
		return occupancySensorService;
	}

	public SwitchService getNewSwitchService(KnxCsvRecord record) throws Exception {
		String serviceName = String.format("Switch_%03d", ++this.switchSensorCount);
		checkForExistingService(serviceName);
		
		SwitchService switchService = new SwitchService(serviceName, record);
		servicesMap.put(switchService.getServiceName(), switchService);
		
		return switchService;
	}

	public ThermostatService getNewThermostatService(KnxCsvRecord record) throws Exception {
		String serviceName = String.format("Thermostat_%03d", ++this.thermostaCount);
		checkForExistingService(serviceName);
		
		ThermostatService thermostatService = new ThermostatService(serviceName, record);
		
		servicesMap.put(thermostatService.getServiceName(), thermostatService);
		
		return thermostatService;
	}

	public SmokeSensorService getNewSmokeSensorService(KnxCsvRecord record) throws Exception {
		String serviceName = String.format("SmokeSensor_%03d", ++this.smokeSensorCount);
		checkForExistingService(serviceName);
		
		SmokeSensorService smokeSensorService = new SmokeSensorService(serviceName, record);
		
		servicesMap.put(smokeSensorService.getServiceName(), smokeSensorService);
		
		return smokeSensorService;
	}

	public int getServicesCount() {
		return servicesCount;
	}
}
