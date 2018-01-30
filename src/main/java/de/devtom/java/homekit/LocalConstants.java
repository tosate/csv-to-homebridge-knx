package de.devtom.java.homekit;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class LocalConstants {
	private Map<String, Object> localConstantsMap;
	
	@JsonAnyGetter
	public Map<String, Object> getLocalConstantsMap() {
		if(this.localConstantsMap == null) {
			localConstantsMap = new HashMap<String, Object>();
		}
		
		return localConstantsMap;
	}
	
	@JsonAnySetter
	public void setLocalConstantsMap(String key, Object value) {
		this.getLocalConstantsMap().put(key, value);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(!LocalConstants.class.isAssignableFrom(obj.getClass()) ) {
			return false;
		}
		final LocalConstants other = (LocalConstants) obj;
		if((this.localConstantsMap == null) ? (other.localConstantsMap != null) : !this.localConstantsMap.equals(other.localConstantsMap)) {
			return false;
		}
		
		return true;
	}
}
