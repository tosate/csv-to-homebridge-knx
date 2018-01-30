package de.devtom.java.homekit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class KnxObject {
	private String type;
	private String set;
	private String listen;
	private String dpt;
	
	@JsonProperty("Type")
	@JsonInclude(Include.NON_NULL)
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@JsonProperty("Set")
	@JsonInclude(Include.NON_NULL)
	public String getSet() {
		return set;
	}
	
	public void setSet(String set) {
		this.set = set;
	}
	
	@JsonProperty("Listen")
	@JsonInclude(Include.NON_NULL)
	public String getListen() {
		return listen;
	}
	
	public void setListen(String listen) {
		this.listen = listen;
	}
	
	@JsonProperty("DPT")
	@JsonInclude(Include.NON_NULL)
	public String getDpt() {
		return dpt;
	}
	
	public void setDpt(String dpt) {
		this.dpt = dpt;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(!KnxObject.class.isAssignableFrom(obj.getClass()) ) {
			return false;
		}
		final KnxObject other = (KnxObject) obj;
		if((this.dpt == null) ? (other.dpt != null) : !this.dpt.equals(other.dpt)) {
			return false;
		}
		if((this.listen == null) ? (other.listen != null) : !this.listen.equals(other.listen)) {
			return false;
		}
		if((this.set == null) ? (other.set != null) : !this.set.equals(other.set)) {
			return false;
		}
		if((this.type == null) ? (other.type != null) : !this.type.equals(other.type)) {
			return false;
		}
		
		return true;
	}	
}
