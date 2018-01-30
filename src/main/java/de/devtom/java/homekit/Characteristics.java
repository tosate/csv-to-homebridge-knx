package de.devtom.java.homekit;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Characteristics {
	private CharacteristicsType type;
	private List<String> set;
	private List<String> listen;
	private String dpt;
	private Boolean reverse;

	@JsonProperty("Reverse")
	@JsonInclude(Include.NON_NULL)
	public Boolean isReverse() {
		if(reverse != null) {
			return reverse;
		} else {
			return null;
		}
	}

	@JsonProperty("Reverse")
	public void setReverse(Boolean reverse) {
		this.reverse = reverse;
	}

	@JsonProperty("DPT")
	@JsonInclude(Include.NON_NULL)
	public String getDpt() {
		return dpt;
	}
	
	@JsonProperty("DPT")
	public void setDpt(String dpt) {
		this.dpt = dpt;
	}
	
	@JsonProperty("Type")
	public String getType() {
		return type.getValue();
	}
	
	@JsonProperty("Type")
	public void setType(String type) {
		this.type = CharacteristicsType.valueOf(type.toUpperCase());
	}
	
	public void setType(CharacteristicsType type) {
		this.type = type;
	}
	
	@JsonProperty("Set")
	@JsonInclude(Include.NON_EMPTY)
	public List<String> getSet() {
		if(set == null) {
			set = new ArrayList<String>();
		}
		
		return set;
	}
	
	@JsonProperty("Listen")
	@JsonInclude(Include.NON_EMPTY)
	public List<String> getListen() {
		if(listen == null) {
			listen = new ArrayList<String>();
		}
		
		return listen;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(!Characteristics.class.isAssignableFrom(obj.getClass()) ) {
			return false;
		}
		final Characteristics other = (Characteristics) obj;
		if(this.type != other.type) {
			return false;
		}
		if((this.set == null) ? (other.set != null) : !this.set.equals(other.set)) {
			return false;
		}
		if((this.listen == null) ? (other.listen != null) : !this.listen.equals(other.listen)) {
			return false;
		}
		if((this.reverse == null) ? (other.reverse != null) : this.reverse != other.reverse) {
			return false;
		}
		if((this.dpt == null) ? (other.dpt != null) : !this.dpt.equals(other.dpt)) {
			return false;
		}
		
		return true;
	}	
}
