package de.devtom.java.homebridge.knxd;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import de.devtom.java.homekit.ContactSensorService;
import de.devtom.java.homekit.Device;
import de.devtom.java.homekit.DeviceFactory;
import de.devtom.java.homekit.GarageDoorOpenerService;
import de.devtom.java.homekit.LightbulbService;
import de.devtom.java.homekit.OccupancySensorService;
import de.devtom.java.homekit.OutletService;
import de.devtom.java.homekit.ServiceFactory;
import de.devtom.java.homekit.ServiceType;
import de.devtom.java.homekit.SmokeSensorService;
import de.devtom.java.homekit.SwitchService;
import de.devtom.java.homekit.ThermostatService;
import de.devtom.java.homekit.WindowCoveringService;

public class KnxConfiguration {	
	private String knxdIp;
	private int knxdPort;
	private boolean allowWebServer;
	private boolean allowKillHomebridge;
	private Map<String, Device> devices;
	
	public KnxConfiguration() {
		this.devices = new HashMap<String, Device>();
	}
	
	public void processCsvRecords(List<KnxCsvRecord> knxCsvRecords) throws Exception {
		for (KnxCsvRecord record : knxCsvRecords) {
			Device device = DeviceFactory.getInstance().getNewDevice(this, record.getDeviceName());

			if (record.getServiceType().equals(ServiceType.LIGHTBULB.getValue())) {
				LightbulbService lightbulbService = ServiceFactory.getInstance().getNewLightbulbService(record);
				device.getServices().add(lightbulbService);
			}

			if (record.getServiceType().equals(ServiceType.WINDOWCOVERING.getValue())) {
				WindowCoveringService windowCoveringService = ServiceFactory.getInstance()
						.getNewWindowCoveringService(record);
				device.getServices().add(windowCoveringService);
			}
			
			if(record.getServiceType().equals(ServiceType.GARAGEDOOROPENER.getValue())) {
				GarageDoorOpenerService garageDoorOpenerService = ServiceFactory.getInstance().getNewGarageDoorOpenerService(record);
				
				device.getServices().add(garageDoorOpenerService);
			}
			
			if(record.getServiceType().equals(ServiceType.OUTLET.getValue())) {
				OutletService outletService = ServiceFactory.getInstance().getNewOutletService(record);
				device.getServices().add(outletService);
			}
			
			if(record.getServiceType().equals(ServiceType.CONTACTSENSOR.getValue())) {
				ContactSensorService contactSensorService = ServiceFactory.getInstance().getNewContactSensorService(record);
				
				device.getServices().add(contactSensorService);
			}
			
			if(record.getServiceType().equals(ServiceType.OCCUPANCYSENSOR.getValue())) {
				OccupancySensorService occupancySensorService = ServiceFactory.getInstance().getNewOccupancySensorService(record);
				
				device.getServices().add(occupancySensorService);
			}
			
			if(record.getServiceType().equals(ServiceType.SWITCH.getValue())) {
				SwitchService switchService = ServiceFactory.getInstance().getNewSwitchService(record);
				
				device.getServices().add(switchService);
			}
			
			if(record.getServiceType().equals(ServiceType.THERMOSTAT.getValue())) {
				ThermostatService thermostatService = ServiceFactory.getInstance().getNewThermostatService(record);
				
				device.getServices().add(thermostatService);
			}
			
			if(record.getServiceType().equals(ServiceType.SMOKESENSOR.getValue())) {
				SmokeSensorService smokeSensorService = ServiceFactory.getInstance().getNewSmokeSensorService(record);
				
				device.getServices().add(smokeSensorService);
			}
		}
	}
	
	public void writeJson(String outputFile) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		mapper.writeValue(new File(outputFile), this);
	}

	@JsonProperty("knxd_ip")
	public String getKnxdIp() {
		return knxdIp;
	}

	public void setKnxdIp(String knxdIp) {
		this.knxdIp = knxdIp;
	}

	@JsonProperty("knxd_port")
	public int getKnxdPort() {
		return knxdPort;
	}

	public void setKnxdPort(int knxdPort) {
		this.knxdPort = knxdPort;
	}

	@JsonProperty("AllowKillHomebridge")
	public boolean isAllowKillHomebridge() {
		return allowKillHomebridge;
	}

	@JsonProperty("AllowKillHomebridge")
	public void setAllowKillHomebridge(boolean allowKillHomebridge) {
		this.allowKillHomebridge = allowKillHomebridge;
	}

	@JsonProperty("Devices")
	public List<Device> getDevices() {
		return new ArrayList<Device>(devices.values());
	}

	@JsonProperty("AllowWebserver")
	public boolean isAllowWebServer() {
		return allowWebServer;
	}

	@JsonProperty("AllowWebserver")
	public void setAllowWebServer(boolean allowWebServer) {
		this.allowWebServer = allowWebServer;
	}

	@JsonIgnore
	public Map<String, Device> getDevicesMap() {
		return this.devices;
	}

	@JsonProperty("Devices")
	public void setDevices(List<Device> devices) {
		this.devices = new HashMap<String, Device>();
		
		for(Device device : devices) {
			this.devices.put(device.getDeviceName(), device);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(!KnxConfiguration.class.isAssignableFrom(obj.getClass()) ) {
			return false;
		}
		final KnxConfiguration other = (KnxConfiguration) obj;
		if((this.knxdIp == null) ? (other.knxdIp != null) : !this.knxdIp.equals(other.knxdIp)) {
			return false;
		}
		if(this.knxdPort != other.knxdPort) {
			return false;
		}
		if(this.allowWebServer != other.allowWebServer) {
			return false;
		}
		if(this.allowKillHomebridge != other.allowKillHomebridge) {
			return false;
		}
		if(!this.devices.equals(other.devices)) {
			return false;
		}
		
		return true;
	}
}
