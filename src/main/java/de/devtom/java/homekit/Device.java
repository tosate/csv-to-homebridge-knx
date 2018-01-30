package de.devtom.java.homekit;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Device {
	protected String deviceName;
	protected List<AbstractService> services;
	
	public Device() {}
	
	public Device(String name) {
		this.deviceName = name;
	}

	@JsonProperty("DeviceName")
	public String getDeviceName() {
		return deviceName;
	}

	@JsonProperty("DeviceName")
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	@JsonProperty("Services")
	public List<AbstractService> getServices() {
		if(this.services == null) {
			this.services = new ArrayList<AbstractService>();
		}
		
		return services;
	}

	@JsonProperty("Services")
	public void setServices(List<AbstractService> services) {
		this.services = services;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(!Device.class.isAssignableFrom(obj.getClass()) ) {
			return false;
		}
		final Device other = (Device) obj;
		if((this.deviceName == null) ? (other.deviceName != null) : !this.deviceName.equals(other.deviceName)) {
			return false;
		}
		if(!this.services.equals(other.services)) {
			return false;
		}
		
		return true;
	}
}
