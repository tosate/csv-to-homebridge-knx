package de.devtom.java;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import de.devtom.java.homebridge.knxd.CsvReader;
import de.devtom.java.homebridge.knxd.KnxConfiguration;
import de.devtom.java.homebridge.knxd.KnxCsvRecord;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	final String KEY_KNXD_IP = "knx.ip";
    	final String KEY_KNXD_PORT = "knx.port";
    	final String KEY_ALLOW_WEB_SERVER = "allow.web.server";
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
        config.setKnxdIp(properties.getProperty(KEY_KNXD_IP));
        config.setKnxdPort(Integer.valueOf(properties.getProperty(KEY_KNXD_PORT)));
        config.setAllowWebServer(properties.getProperty(KEY_ALLOW_WEB_SERVER).equalsIgnoreCase(Boolean.TRUE.toString()));
        config.setAllowKillHomebridge(properties.getProperty(KEY_ALLOW_KILL_HOMEBRIDGE).equalsIgnoreCase(Boolean.TRUE.toString()));
        
        
        try {
			List<KnxCsvRecord> knxRecords = CsvReader.readCsv(properties.getProperty(KEY_CONFIG_INPUT), config);
			config.processCsvRecords(knxRecords);
			config.writeJson(properties.getProperty(KEY_KNX_CONFIG_OUTPUT));
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
			e1.printStackTrace();
			return;
		}
    }
}
