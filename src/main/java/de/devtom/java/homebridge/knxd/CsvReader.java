package de.devtom.java.homebridge.knxd;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CsvReader {	
	public static List<KnxCsvRecord> readCsv(String fileName, KnxConfiguration knxConfig) throws Exception {
		Reader in = new FileReader(fileName);
		List<KnxCsvRecord> result = new ArrayList<KnxCsvRecord>();
		
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(CsvHeaders.class).parse(in);
		
		for(CSVRecord record : records) {
			KnxCsvRecord knxRecord = new KnxCsvRecord(record);
			result.add(knxRecord);
		}
		
		return result;
	}
}
