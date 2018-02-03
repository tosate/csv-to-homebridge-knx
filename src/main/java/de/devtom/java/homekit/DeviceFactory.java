package de.devtom.java.homekit;

import java.util.HashMap;
import java.util.Map;

import de.devtom.java.homebridge.knxd.KnxConfiguration;

public class DeviceFactory {
	private static DeviceFactory instance;
	private Map<String, Device> devicesMap;

	public static DeviceFactory getInstance() {
		if(instance == null) {
			instance = new DeviceFactory();
		}
		
		return instance;
	}
	
	private DeviceFactory() {
		this.devicesMap = new HashMap<String, Device>();
	}
	
	public Device getNewDevice(KnxConfiguration config, String deviceName) {
		if(devicesMap.containsKey(deviceName)) {
			return devicesMap.get(deviceName);
		} else {
			Device device = new Device(deviceName);
			devicesMap.put(deviceName, device);
			config.getDevicesMap().put(deviceName, device);
			
			return device;
		}
	}
	
	public void clearMap() {
		this.devicesMap.clear();
	}
}
