package de.devtom.java;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import de.devtom.java.homebridge.knxd.CsvReader;
import de.devtom.java.homebridge.knxd.KnxConfiguration;
import de.devtom.java.homebridge.knxd.KnxCsvRecord;
import de.devtom.java.homekit.DeviceFactory;
import de.devtom.java.homekit.ServiceFactory;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		final String KEY_KNXD_USE = "use.knxd";
		final String KEY_KNXD_IP = "knx.ip";
		final String KEY_KNXD_PORT = "knx.port";
		final String KEY_WEB_SERVER_ALLOW = "web.server.allow";
		final String KEY_WEB_SERVER_PORT = "web.server.port";
		final String KEY_ALLOW_KILL_HOMEBRIDGE = "allow.kill.homebridge";
		final String APPLICATION_PROPERTIES = "application.properties";
		final String KEY_CONFIG_INPUT = "config.input.file";
		final String KEY_KNX_CONFIG_OUTPUT = "knx.config.output.file";

		ClassLoader loader = App.class.getClassLoader();
		Properties properties = new Properties();

		InputStream inputStream = loader.getResourceAsStream(APPLICATION_PROPERTIES);
		try {
			properties.load(inputStream);
		} catch (IOException e2) {
			e2.printStackTrace();
			return;
		}

		KnxConfiguration config = new KnxConfiguration();
		config.setUseKnxd(properties.getProperty(KEY_KNXD_USE).equalsIgnoreCase("true"));
		config.setKnxdIp(properties.getProperty(KEY_KNXD_IP));
		config.setKnxdPort(Integer.valueOf(properties.getProperty(KEY_KNXD_PORT)));
		config.setAllowWebServer(
				properties.getProperty(KEY_WEB_SERVER_ALLOW).equalsIgnoreCase(Boolean.TRUE.toString()));
		config.setWebServerPort(Integer.valueOf(properties.getProperty(KEY_WEB_SERVER_PORT)));
		config.setAllowKillHomebridge(
				properties.getProperty(KEY_ALLOW_KILL_HOMEBRIDGE).equalsIgnoreCase(Boolean.TRUE.toString()));

		try {
			List<KnxCsvRecord> knxRecords = CsvReader.readCsv(properties.getProperty(KEY_CONFIG_INPUT), config);
			config.processCsvRecords(knxRecords);
			config.writeJson(properties.getProperty(KEY_KNX_CONFIG_OUTPUT));

			// Output statistics
			System.out.println(String.format("Total number of devices: %d", DeviceFactory.getInstance().getDeviceCount()));
			System.out.println(String.format("Total number of services: %d", ServiceFactory.getInstance().getServicesCount()));
			System.out.println(String.format("Number of lightbulbs: %d", ServiceFactory.getInstance().getLightbulbCount()));
			System.out.println(String.format("Number of window coverings and jalousies: %d", ServiceFactory.getInstance().getWindowCoveringCount()));
			System.out.println(String.format("Number of occupancy sensors: %d", ServiceFactory.getInstance().getOccupancySensorCount()));
			System.out.println(String.format("Number of thermostats: %d", ServiceFactory.getInstance().getThermostatCount()));
			System.out.println(String.format("Number of contact sensors: %d", ServiceFactory.getInstance().getContactSensorCount()));
			System.out.println(String.format("Number of power outlets: %d", ServiceFactory.getInstance().getOutletCount()));
			System.out.println(String.format("Number of smoke sensors: %d", ServiceFactory.getInstance().getSmokeSensorCount()));
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
			e1.printStackTrace();
			return;
		}
	}
}
