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
		String deviceName = "Garage OG";
		String serviceName = "Licht Garage OG";
		String groupAddress1 = "1/1/1";
		String groupAddress2 = "1/4/1";
		KnxConfiguration knxConfiguration = getKnxConfiguration();
		
		KnxCsvRecord record = new KnxCsvRecord(deviceName, ServiceType.LIGHTBULB.getValue(), serviceName, groupAddress1, groupAddress2);
		List<KnxCsvRecord> records = new ArrayList<KnxCsvRecord>();
		records.add(record);
		
		knxConfiguration.processCsvRecords(records);
		
		KnxConfiguration knxConfiguration2 = knxConfigurationFromJson(LIGHTBULB_TEST_FILE);
		
		assertEquals(knxConfiguration, knxConfiguration2);
	}
	
	@Test
	public void testDimmableLightbulbConfig() throws Exception {
		String deviceName = "Wohnzimmer";
		String serviceName = "Licht Wohnzimmer";
		String groupAddress1 = "1/1/1";
		String groupAddress2 = "1/4/1";
		String groupAddress3 = "1/3/1";
		String groupAddress4 = "1/5/1";
		KnxConfiguration knxConfiguration = getKnxConfiguration();
		
		KnxCsvRecord record = new KnxCsvRecord(deviceName, ServiceType.LIGHTBULB.getValue(), serviceName, groupAddress1, groupAddress2, groupAddress3, groupAddress4);
		List<KnxCsvRecord> records = new ArrayList<KnxCsvRecord>();
		records.add(record);
		
		knxConfiguration.processCsvRecords(records);
		
		KnxConfiguration knxConfiguration2 = knxConfigurationFromJson(DIMMABLE_LIGHTBULB_TEST_FILE);
		
		assertEquals(knxConfiguration, knxConfiguration2);
	}
	
	@Test
	public void testWindowCoveringConfig() throws Exception {
		String deviceName = "Schlafzimmer";
		String serviceName = "Rollade Schlafzimmer rechts";
		String groupAddress1 = "2/4/1";
		String groupAddress2 = "2/3/1";
		KnxConfiguration knxConfiguration = getKnxConfiguration();
		
		KnxCsvRecord record = new KnxCsvRecord(deviceName, ServiceType.WINDOWCOVERING.getValue(), serviceName, groupAddress1, groupAddress2);
		List<KnxCsvRecord> records = new ArrayList<KnxCsvRecord>();
		records.add(record);
		
		knxConfiguration.processCsvRecords(records);
		
		KnxConfiguration knxConfiguration2 = knxConfigurationFromJson(WINDOWCOVERING_TEST_FILE);
		
		assertEquals(knxConfiguration, knxConfiguration2);
	}
	
	@Test
	public void testWindowCoveringJalousieConfig() throws Exception {
		String deviceName = "Wohnzimmer";
		String serviceName = "Jalousie Wohnzimmer";
		String groupAddress1 = "2/4/1";
		String groupAddress2 = "2/3/1";
		String groupAddress3 = "2/6/1";
		String groupAddress4 = "2/5/1";
		KnxConfiguration knxConfiguration = getKnxConfiguration();
		
		KnxCsvRecord record = new KnxCsvRecord(deviceName, ServiceType.WINDOWCOVERING.getValue(), serviceName, groupAddress1, groupAddress2, groupAddress3, groupAddress4);
		List<KnxCsvRecord> records = new ArrayList<KnxCsvRecord>();
		records.add(record);
		
		knxConfiguration.processCsvRecords(records);
		
		KnxConfiguration knxConfiguration2 = knxConfigurationFromJson(WINDOWCOVERING_JALOUSIE_TEST_FILE);
		
		assertEquals(knxConfiguration, knxConfiguration2);
	}
	
	@Test
	public void testGarageDoorOpener() throws Exception {
		String deviceName = "Schranke";
		String serviceName = "Schranke";
		String groupAddress1 = "2/3/0";
		String groupAddress2 = "2/3/2";
		String groupAddress3 = "2/3/3";
		KnxConfiguration knxConfiguration = getKnxConfiguration();
		
		KnxCsvRecord record = new KnxCsvRecord(deviceName, ServiceType.GARAGEDOOROPENER.getValue(), serviceName, groupAddress1, groupAddress2, groupAddress3);
		List<KnxCsvRecord> records = new ArrayList<KnxCsvRecord>();
		records.add(record);
		
		knxConfiguration.processCsvRecords(records);
		
		KnxConfiguration knxConfiguration2 = knxConfigurationFromJson(GARGEDOOROPENER_TEST_FILE);
		
		assertEquals(knxConfiguration, knxConfiguration2);
	}
	
	@Test
	public void testOutlet() throws Exception {
		String deviceName = "Kitchen";
		String serviceName = "Kitchen Counter Right-side Outlets";
		String groupAddress1 = "1/2/3";
		String groupAddress2 = "1/4/3";
		
		KnxConfiguration knxConfiguration = getKnxConfiguration();
		
		KnxCsvRecord record = new KnxCsvRecord(deviceName, ServiceType.OUTLET.getValue(), serviceName, groupAddress1, groupAddress2);
		List<KnxCsvRecord> records = new ArrayList<KnxCsvRecord>();
		records.add(record);
		
		knxConfiguration.processCsvRecords(records);
		
		KnxConfiguration knxConfiguration2 = knxConfigurationFromJson(OUTLET_TEST_FILE);
		
		assertEquals(knxConfiguration, knxConfiguration2);
	}
	
	@Test
	public void testContactSensor() throws Exception {
		String deviceName = "Küche";
		String serviceName = "Fenster";
		String groupAddress1 = "3/5/1";
		
		KnxConfiguration knxConfiguration = getKnxConfiguration();
		
		KnxCsvRecord record = new KnxCsvRecord(deviceName, ServiceType.CONTACTSENSOR.getValue(), serviceName, groupAddress1);
		List<KnxCsvRecord> records = new ArrayList<KnxCsvRecord>();
		records.add(record);
		
		knxConfiguration.processCsvRecords(records);
		
		KnxConfiguration knxConfiguration2 = knxConfigurationFromJson(CONTACTSENSOR_TEST_FILE);
		
		assertEquals(knxConfiguration, knxConfiguration2);
	}
	
	@Test
	public void testOccupancySensor() throws Exception {
		String deviceName = "Hall";
		String serviceName = "Hall Sensor";
		String groupAddress1 = "6/4/1";
		
		KnxConfiguration knxConfiguration = getKnxConfiguration();
		
		KnxCsvRecord record = new KnxCsvRecord(deviceName, ServiceType.OCCUPANCYSENSOR.getValue(), serviceName, groupAddress1);
		List<KnxCsvRecord> records = new ArrayList<KnxCsvRecord>();
		records.add(record);
		
		knxConfiguration.processCsvRecords(records);
		
		KnxConfiguration knxConfiguration2 = knxConfigurationFromJson(OCCUPANCYSENSOR_TEST_FILE);
		
		assertEquals(knxConfiguration, knxConfiguration2);
	}
	
	@Test
	public void testSwitch() throws Exception {
		String deviceName = "Hall";
		String serviceName = "Switch";
		String groupAddress1 = "6/3/1";
		
		KnxConfiguration knxConfiguration = getKnxConfiguration();
		
		KnxCsvRecord record = new KnxCsvRecord(deviceName, ServiceType.SWITCH.getValue(), serviceName, groupAddress1);
		List<KnxCsvRecord> records = new ArrayList<KnxCsvRecord>();
		records.add(record);
		
		knxConfiguration.processCsvRecords(records);
		
		KnxConfiguration knxConfiguration2 = knxConfigurationFromJson(SWITCH_TEST_FILE);
		
		assertEquals(knxConfiguration, knxConfiguration2);
	}
	
	@Test
	public void testThermostat() throws Exception {
		String deviceName = "Office";
		String serviceName = "Office Thermostat";
		String groupAddress1 = "3/1/2";
		String groupAddress2 = "3/2/2";
		String groupAddress3 = "3/6/2";
		String groupAddress4 = "3/3/12";
		String groupAddress5 = "1/2/3";
		
		KnxConfiguration knxConfiguration = getKnxConfiguration();
		
		KnxCsvRecord record = new KnxCsvRecord(deviceName, ServiceType.THERMOSTAT.getValue(), serviceName, groupAddress1, groupAddress2, groupAddress3, groupAddress4, groupAddress5);
		List<KnxCsvRecord> records = new ArrayList<KnxCsvRecord>();
		records.add(record);
		
		knxConfiguration.processCsvRecords(records);
		
		KnxConfiguration knxConfiguration2 = knxConfigurationFromJson(THERMOSTAT_TEST_FILE);
		
		assertEquals(knxConfiguration, knxConfiguration2);
	}
}
