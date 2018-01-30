package de.devtom.java.homebridge.knxd;

import org.apache.commons.csv.CSVRecord;

public class KnxCsvRecord {
	private String deviceName;
	private String serviceType;
	private String serviceName;
	private String groupAddress1;
	private String groupAddress2;
	private String groupAddress3;
	private String groupAddress4;
	
	public KnxCsvRecord(CSVRecord csvRecord) {
		this.deviceName = csvRecord.get(CsvHeaders.DEVICE_NAME);
		this.serviceType = csvRecord.get(CsvHeaders.SERVICE_TYPE);
		this.serviceName = csvRecord.get(CsvHeaders.SERVICE_NAME);
		this.groupAddress1 = csvRecord.get(CsvHeaders.GROUP_ADDRESS);
		this.groupAddress2 = csvRecord.get(CsvHeaders.GROUP_ADDRESS2);
		this.groupAddress3 = csvRecord.get(CsvHeaders.GROUP_ADDRESS3);
		this.groupAddress4 = csvRecord.get(CsvHeaders.GROUP_ADDRESS4);
	}
	
	public KnxCsvRecord(String deviceName, String serviceType, String serviceName, String groupAddress1, String groupAddress2, String groupAddress3, String groupAddress4) {
		this.deviceName = deviceName;
		this.serviceType = serviceType;
		this.serviceName = serviceName;
		this.groupAddress1 = groupAddress1;
		this.groupAddress2 = groupAddress2;
		this.groupAddress3 = groupAddress3;
		this.groupAddress4 = groupAddress4;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public String getServiceType() {
		return serviceType;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getGroupAddress1() {
		return groupAddress1;
	}

	public String getGroupAddress2() {
		return groupAddress2;
	}

	public String getGroupAddress3() {
		return groupAddress3;
	}

	public String getGroupAddress4() {
		return groupAddress4;
	}
}
