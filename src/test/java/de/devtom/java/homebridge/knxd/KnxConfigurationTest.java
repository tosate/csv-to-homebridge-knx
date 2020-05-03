package de.devtom.java.homebridge.knxd;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.devtom.java.App;
import de.devtom.java.homekit.DeviceFactory;
import de.devtom.java.homekit.ServiceType;

public class KnxConfigurationTest {
	private static final String KNXD_IP = "127.0.0.1";
	private static final int KNXD_PORT = 6720;
	private static final boolean ALLOW_WEB_SERVER = true;
	private static final boolean ALLOW_KILL_HOMEBRIDGE = false;
	
	private static final String LIGHTBULB_TEST_FILE = "knx_lightbulb_config.json";
	private static final String DIMMABLE_LIGHTBULB_TEST_FILE = "knx_lightbulb_dimmable_config.json";
	private static final String WINDOWCOVERING_TEST_FILE = "knx_windowcovering_config.json";
	private static final String GARGEDOOROPENER_TEST_FILE = "knx_garagedooropener_config.json";
	private static final String OUTLET_TEST_FILE = "knx_outlet_config.json";
	private static final String CONTACTSENSOR_TEST_FILE = "knx_contactsensor_config.json";
	private static final String WINDOWCOVERING_JALOUSIE_TEST_FILE = "knx_windowcovering_jalousie_config.json";
	private static final String OCCUPANCYSENSOR_TEST_FILE = "knx_occupancysensor_config.json";
	private static final String SWITCH_TEST_FILE = "knx_switch_config.json";
	private static final String THERMOSTAT_TEST_FILE = "knx_thermostat_config.json";
	
	private static final String LIGHTBULB_SERVICE_NAME = "Lampe";
	private static final String DIMMABLE_LIGHTBULB_SERVICE_NAME = "Dimmbare Lampe";
	private static final String WINDOWCOVERING_SERVICE_NAME = "Rollade";
	private static final String WINDOWCOVERING_JALOUSIE_SERVICE_NAME = "Jalousie";
	private static final String GARAGEDOOROPENER_SERVICE_NAME = "Garagentoröffner";
	private static final String OUTLET_SERVICE_NAME = "Steckdose";
	private static final String CONTACTSENSOR_SERVICE_NAME = "Kontaktsensor";
	private static final String OCCUPANCYSENSOR_SERVICE_NAME = "Bewegungsmelder";
	private static final String SWITCH_SERVICE_NAME = "Schalter";
	private static final String THERMOSTAT_SERVICE_NAME = "Thermostat";
	
	private KnxConfiguration knxConfigurationFromJson(String resource) throws IOException {
		ClassLoader loader = App.class.getClassLoader();
		InputStream inputStream = loader.getResourceAsStream(resource);
		byte[] jsonData = IOUtils.toByteArray(inputStream);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		KnxConfiguration knxConfiguration = objectMapper.readValue(jsonData, KnxConfiguration.class);
		
		return knxConfiguration;
	}
	
	private KnxConfiguration getKnxConfiguration() {
		KnxConfiguration knxConfiguration = new KnxConfiguration();
		knxConfiguration.setKnxdIp(KNXD_IP);
		knxConfiguration.setKnxdPort(KNXD_PORT);
		knxConfiguration.setAllowWebServer(ALLOW_WEB_SERVER);
		knxConfiguration.setAllowKillHomebridge(ALLOW_KILL_HOMEBRIDGE);
		
		return knxConfiguration;
	}
	
	@Before
	public void setUp() {
		DeviceFactory.getInstance().clearMap();
	}

	@Test
	public void testLightbulbConfig() throws Exception {
		String deviceName = "Licht Garage OG";
		String serviceName = LIGHTBULB_SERVICE_NAME;
		String groupAddress1 = "1/1/1";
		String groupAddress2 = "1/4/1";
		KnxConfiguration knxConfiguration = getKnxConfiguration();
		
		KnxCsvRecord record = new KnxCsvRecord(deviceName, ServiceType.LIGHTBULB.getValue(), groupAddress1, groupAddress2);
		List<KnxCsvRecord> records = new ArrayList<KnxCsvRecord>();
		records.add(record);
		
		knxConfiguration.processCsvRecords(records);
		knxConfiguration.getDevices().get(0).getServices().get(0).setServiceName(serviceName);
		
		KnxConfiguration knxConfiguration2 = knxConfigurationFromJson(LIGHTBULB_TEST_FILE);
		
		assertEquals(knxConfiguration, knxConfiguration2);
	}
	
	@Test
	public void testDimmableLightbulbConfig() throws Exception {
		String deviceName = "Licht Wohnzimmer";
		String serviceName = DIMMABLE_LIGHTBULB_SERVICE_NAME;
		String groupAddress1 = "1/1/1";
		String groupAddress2 = "1/4/1";
		String groupAddress3 = "1/3/1";
		String groupAddress4 = "1/5/1";
		KnxConfiguration knxConfiguration = getKnxConfiguration();
		
		KnxCsvRecord record = new KnxCsvRecord(deviceName, ServiceType.LIGHTBULB.getValue(), groupAddress1, groupAddress2, groupAddress3, groupAddress4);
		List<KnxCsvRecord> records = new ArrayList<KnxCsvRecord>();
		records.add(record);
		
		knxConfiguration.processCsvRecords(records);
		knxConfiguration.getDevices().get(0).getServices().get(0).setServiceName(serviceName);
		
		KnxConfiguration knxConfiguration2 = knxConfigurationFromJson(DIMMABLE_LIGHTBULB_TEST_FILE);
		
		assertEquals(knxConfiguration, knxConfiguration2);
	}
	
	@Test
	public void testWindowCoveringConfig() throws Exception {
		String deviceName = "Rollade Schlafzimmer rechts";
		String serviceName = WINDOWCOVERING_SERVICE_NAME;
		String groupAddress1 = "2/4/1";
		String groupAddress2 = "2/3/1";
		KnxConfiguration knxConfiguration = getKnxConfiguration();
		
		KnxCsvRecord record = new KnxCsvRecord(deviceName, ServiceType.WINDOWCOVERING.getValue(), groupAddress1, groupAddress2);
		List<KnxCsvRecord> records = new ArrayList<KnxCsvRecord>();
		records.add(record);
		
		knxConfiguration.processCsvRecords(records);
		knxConfiguration.getDevices().get(0).getServices().get(0).setServiceName(serviceName);
		
		KnxConfiguration knxConfiguration2 = knxConfigurationFromJson(WINDOWCOVERING_TEST_FILE);
		
		assertEquals(knxConfiguration, knxConfiguration2);
	}
	
	@Test
	public void testWindowCoveringJalousieConfig() throws Exception {
		String deviceName = "Jalousie Wohnzimmer";
		String serviceName = WINDOWCOVERING_JALOUSIE_SERVICE_NAME;
		String groupAddress1 = "2/4/1";
		String groupAddress2 = "2/3/1";
		String groupAddress3 = "2/6/1";
		String groupAddress4 = "2/5/1";
		KnxConfiguration knxConfiguration = getKnxConfiguration();
		
		KnxCsvRecord record = new KnxCsvRecord(deviceName, ServiceType.WINDOWCOVERING.getValue(), groupAddress1, groupAddress2, groupAddress3, groupAddress4);
		List<KnxCsvRecord> records = new ArrayList<KnxCsvRecord>();
		records.add(record);
		
		knxConfiguration.processCsvRecords(records);
		knxConfiguration.getDevices().get(0).getServices().get(0).setServiceName(serviceName);
		
		KnxConfiguration knxConfiguration2 = knxConfigurationFromJson(WINDOWCOVERING_JALOUSIE_TEST_FILE);
		
		assertEquals(knxConfiguration, knxConfiguration2);
	}
	
	@Test
	public void testGarageDoorOpener() throws Exception {
		String deviceName = "Garagentor";
		String serviceName = GARAGEDOOROPENER_SERVICE_NAME;
		String groupAddress1 = "2/3/0";
		String groupAddress2 = "2/3/2";
		String groupAddress3 = "2/3/3";
		KnxConfiguration knxConfiguration = getKnxConfiguration();
		
		KnxCsvRecord record = new KnxCsvRecord(deviceName, ServiceType.GARAGEDOOROPENER.getValue(), groupAddress1, groupAddress2, groupAddress3);
		List<KnxCsvRecord> records = new ArrayList<KnxCsvRecord>();
		records.add(record);
		
		knxConfiguration.processCsvRecords(records);
		knxConfiguration.getDevices().get(0).getServices().get(0).setServiceName(serviceName);
		
		KnxConfiguration knxConfiguration2 = knxConfigurationFromJson(GARGEDOOROPENER_TEST_FILE);
		
		assertEquals(knxConfiguration, knxConfiguration2);
	}
	
	@Test
	public void testOutlet() throws Exception {
		String deviceName = "Kitchen Counter Right-side Outlets";
		String serviceName = OUTLET_SERVICE_NAME;
		String groupAddress1 = "1/2/3";
		String groupAddress2 = "1/4/3";
		
		KnxConfiguration knxConfiguration = getKnxConfiguration();
		
		KnxCsvRecord record = new KnxCsvRecord(deviceName, ServiceType.OUTLET.getValue(), groupAddress1, groupAddress2);
		List<KnxCsvRecord> records = new ArrayList<KnxCsvRecord>();
		records.add(record);
		
		knxConfiguration.processCsvRecords(records);
		knxConfiguration.getDevices().get(0).getServices().get(0).setServiceName(serviceName);
		
		KnxConfiguration knxConfiguration2 = knxConfigurationFromJson(OUTLET_TEST_FILE);
		
		assertEquals(knxConfiguration, knxConfiguration2);
	}
	
	@Test
	public void testContactSensor() throws Exception {
		String deviceName = "Küchenfensterkontakt";
		String serviceName = CONTACTSENSOR_SERVICE_NAME;
		String groupAddress1 = "3/5/1";
		
		KnxConfiguration knxConfiguration = getKnxConfiguration();
		
		KnxCsvRecord record = new KnxCsvRecord(deviceName, ServiceType.CONTACTSENSOR.getValue(), groupAddress1);
		List<KnxCsvRecord> records = new ArrayList<KnxCsvRecord>();
		records.add(record);
		
		knxConfiguration.processCsvRecords(records);
		knxConfiguration.getDevices().get(0).getServices().get(0).setServiceName(serviceName);
		
		KnxConfiguration knxConfiguration2 = knxConfigurationFromJson(CONTACTSENSOR_TEST_FILE);
		
		assertEquals(knxConfiguration, knxConfiguration2);
	}
	
	@Test
	public void testOccupancySensor() throws Exception {
		String deviceName = "Hall Sensor";
		String serviceName = OCCUPANCYSENSOR_SERVICE_NAME;
		String groupAddress1 = "6/4/1";
		
		KnxConfiguration knxConfiguration = getKnxConfiguration();
		
		KnxCsvRecord record = new KnxCsvRecord(deviceName, ServiceType.OCCUPANCYSENSOR.getValue(), groupAddress1);
		List<KnxCsvRecord> records = new ArrayList<KnxCsvRecord>();
		records.add(record);
		
		knxConfiguration.processCsvRecords(records);
		knxConfiguration.getDevices().get(0).getServices().get(0).setServiceName(serviceName);
		
		KnxConfiguration knxConfiguration2 = knxConfigurationFromJson(OCCUPANCYSENSOR_TEST_FILE);
		
		assertEquals(knxConfiguration, knxConfiguration2);
	}
	
	@Test
	public void testSwitch() throws Exception {
		String deviceName = "Hall Switch";
		String serviceName = SWITCH_SERVICE_NAME;
		String groupAddress1 = "6/3/1";
		
		KnxConfiguration knxConfiguration = getKnxConfiguration();
		
		KnxCsvRecord record = new KnxCsvRecord(deviceName, ServiceType.SWITCH.getValue(), groupAddress1);
		List<KnxCsvRecord> records = new ArrayList<KnxCsvRecord>();
		records.add(record);
		
		knxConfiguration.processCsvRecords(records);
		knxConfiguration.getDevices().get(0).getServices().get(0).setServiceName(serviceName);
		
		KnxConfiguration knxConfiguration2 = knxConfigurationFromJson(SWITCH_TEST_FILE);
		
		assertEquals(knxConfiguration, knxConfiguration2);
	}
	
	@Test
	public void testThermostat() throws Exception {
		String deviceName = "Office Thermostat";
		String serviceName = THERMOSTAT_SERVICE_NAME;
		String groupAddress1 = "3/1/2";
		String groupAddress2 = "3/2/2";
		String groupAddress3 = "3/6/2";
		String groupAddress4 = "3/3/12";
		String groupAddress5 = "1/2/3";
		
		KnxConfiguration knxConfiguration = getKnxConfiguration();
		
		KnxCsvRecord record = new KnxCsvRecord(deviceName, ServiceType.THERMOSTAT.getValue(), groupAddress1, groupAddress2, groupAddress3, groupAddress4, groupAddress5);
		List<KnxCsvRecord> records = new ArrayList<KnxCsvRecord>();
		records.add(record);
		
		knxConfiguration.processCsvRecords(records);
		knxConfiguration.getDevices().get(0).getServices().get(0).setServiceName(serviceName);
		
		KnxConfiguration knxConfiguration2 = knxConfigurationFromJson(THERMOSTAT_TEST_FILE);
		
		assertEquals(knxConfiguration, knxConfiguration2);
	}
}
