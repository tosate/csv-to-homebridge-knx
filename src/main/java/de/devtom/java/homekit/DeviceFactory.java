package de.devtom.java.homekit;

import java.util.HashMap;
import java.util.Map;

import de.devtom.java.homebridge.knxd.KnxConfiguration;

public class DeviceFactory {
	private static DeviceFactory instance;
	private Map<String, Device> devicesMap;
	private int deviceCount = 0;

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
			this.deviceCount++;
			
			return device;
		}
	}
	
	public void clearMap() {
		this.devicesMap.clear();
	}

	public int getDeviceCount() {
		return deviceCount;
	}
}
