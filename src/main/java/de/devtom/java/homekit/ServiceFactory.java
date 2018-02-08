package de.devtom.java.homekit;

import java.util.HashMap;
import java.util.Map;

import de.devtom.java.homebridge.knxd.KnxCsvRecord;

public class ServiceFactory {
	private static ServiceFactory instance;
	private Map<String, AbstractService> servicesMap;
	
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
		checkForExistingService(record.getServiceName());
		
		LightbulbService lightbulbService = new LightbulbService(record);
		servicesMap.put(lightbulbService.getServiceName(), lightbulbService);
		
		return lightbulbService;
	}

	public WindowCoveringService getNewWindowCoveringService(KnxCsvRecord record) throws Exception {
		checkForExistingService(record.getServiceName());
		
		WindowCoveringService windowCoveringService = new WindowCoveringService(record);
		servicesMap.put(windowCoveringService.getServiceName(), windowCoveringService);
		
		return windowCoveringService;
	}

	public GarageDoorOpenerService getNewGarageDoorOpenerService(KnxCsvRecord record) throws Exception {
		checkForExistingService(record.getServiceName());
		
		GarageDoorOpenerService garageDoorOpenerService = new GarageDoorOpenerService(record);
		servicesMap.put(garageDoorOpenerService.getServiceName(), garageDoorOpenerService);
		
		return garageDoorOpenerService;
	}
	
	private void checkForExistingService(String serviceName) throws Exception {
		if(servicesMap.containsKey(serviceName)) {
			throw new Exception(serviceName + " is not a unique service name!");
		}
	}

	public OutletService getNewOutletService(KnxCsvRecord record) throws Exception {
		checkForExistingService(record.getServiceName());
		
		OutletService outletService = new OutletService(record);
		servicesMap.put(outletService.getServiceName(), outletService);
		
		return outletService;
	}

	public ContactSensorService getNewContactSensorService(KnxCsvRecord record) throws Exception {
		checkForExistingService(record.getServiceName());
		
		ContactSensorService contactSensorService = new ContactSensorService(record);
		servicesMap.put(contactSensorService.getServiceName(), contactSensorService);
		
		return contactSensorService;
	}

	public OccupancySensorService getNewOccupancySensorService(KnxCsvRecord record) throws Exception {
		checkForExistingService(record.getServiceName());
		
		OccupancySensorService occupancySensorService = new OccupancySensorService(record);
		servicesMap.put(occupancySensorService.getServiceName(), occupancySensorService);
		
		return occupancySensorService;
	}

	public SwitchService getNewSwitchService(KnxCsvRecord record) throws Exception {
		checkForExistingService(record.getServiceName());
		
		SwitchService switchService = new SwitchService(record);
		servicesMap.put(switchService.getServiceName(), switchService);
		
		return switchService;
	}
}
