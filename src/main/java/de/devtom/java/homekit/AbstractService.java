package de.devtom.java.homekit;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import de.devtom.java.homebridge.knxd.KnxCsvRecord;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes( {
	@JsonSubTypes.Type(value = LightbulbService.class, name = "Lightbulb"),
	@JsonSubTypes.Type(value = WindowCoveringService.class, name = "WindowCovering"),
	@JsonSubTypes.Type(value = GarageDoorOpenerService.class, name = "GarageDoorOpener"),
	@JsonSubTypes.Type(value = OutletService.class, name = "Outlet"),
	@JsonSubTypes.Type(value = ContactSensorService.class, name = "ContactSensor"),
	@JsonSubTypes.Type(value = OccupancySensorService.class, name = "OccupancySensor"),
	@JsonSubTypes.Type(value = SwitchService.class, name = "Switch"),
	@JsonSubTypes.Type(value = ThermostatService.class, name="Thermostat")
})
public abstract class AbstractService {
	public static final String KNX_DPT1 = "DPT1";
	public static final String KNX_DPT5 = "DPT5";
	public static final String KNX_DPT9 = "DPT9";
	protected ServiceType serviceType;
	protected String serviceName;
	protected List<Characteristics> characteristics;
	protected List<String> knxReadRequests;
	protected String handler;
	protected List<KnxObject> knxObjects;
	protected LocalConstants localConstants;
	
	public AbstractService() {}
	
	public AbstractService(String serviceName, KnxCsvRecord record) {
		this.serviceName = serviceName;
		this.initialize(record);
	}

	@JsonProperty("ServiceType")
	public String getServiceType() {
		return serviceType.getValue();
	}

	@JsonProperty("ServiceType")
	public void setServiceType(String serviceType) {
		this.serviceType = ServiceType.valueOf(serviceType.toUpperCase());
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(!AbstractService.class.isAssignableFrom(obj.getClass()) ) {
			return false;
		}
		final AbstractService other = (AbstractService) obj;
 		if((this.serviceName == null) ? (other.serviceName != null) : !this.serviceName.equals(other.serviceName)) {
			return false;
		}
		if((this.handler == null) ? (other.handler != null) : !this.handler.equals(other.handler)) {
			return false;
		}
		if(this.serviceType != other.serviceType) {
			return false;
		}
		if((this.localConstants == null) ? (other.localConstants != null) : !this.localConstants.equals(localConstants)) {
			return false;
		}
		if(!this.characteristics.equals(other.characteristics)) {
			return false;
		}
		if((this.knxObjects == null) ? (other.knxObjects != null) : !this.knxObjects.equals(other.knxObjects)) {
			return false;
		}
		if((this.knxReadRequests == null) ? (other.knxReadRequests != null) : !this.knxReadRequests.equals(other.knxReadRequests)) {
			return false;
		}
		
 		return true;
	}

	@JsonProperty("ServiceName")
	public String getServiceName() {
		return serviceName;
	}

	@JsonProperty("ServiceName")
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@JsonProperty("Characteristics")
	public void setCharacteristics(List<Characteristics> characteristics) {
		this.characteristics = characteristics;
	}

	@JsonProperty("KNXReadRequests")
	public void setKnxReadRequests(List<String> knxReadRequests) {
		this.knxReadRequests = knxReadRequests;
	}

	@JsonProperty("Handler")
	public void setHandler(String handler) {
		this.handler = handler;
	}

	@JsonProperty("KNXObjects")
	public void setKnxObjects(List<KnxObject> knxObjects) {
		this.knxObjects = knxObjects;
	}

	@JsonProperty("LocalConstants")
	public void setLocalConstants(LocalConstants localConstants) {
		this.localConstants = localConstants;
	}

	@JsonProperty("Characteristics")
	public List<Characteristics> getCharacteristics() {
		if(characteristics == null) {
			this.characteristics = new ArrayList<Characteristics>();
		}
		return characteristics;
	}
	
	@JsonProperty("KNXReadRequests")
	@JsonInclude(Include.NON_EMPTY)
	public List<String> getKnxReadRequests() {
		if(this.knxReadRequests == null) {
			knxReadRequests = new ArrayList<String>();
		}
		
		return knxReadRequests;
	}
	
	@JsonProperty("Handler")
	@JsonInclude(Include.NON_NULL)
	public String getHandler() {
		return handler;
	}

	@JsonProperty("KNXObjects")
	@JsonInclude(Include.NON_EMPTY)
	public List<KnxObject> getKnxObjects() {
		if(this.knxObjects == null) {
			knxObjects = new ArrayList<KnxObject>();
		}
		
		return knxObjects;
	}

	@JsonProperty("LocalConstants")
	@JsonInclude(Include.NON_NULL)
	public LocalConstants getLocalConstants() {
		if(this.localConstants == null) {
			localConstants = new LocalConstants();
		}
		return localConstants;
	}
	
	protected abstract void initialize(KnxCsvRecord record);
}
