package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Count iterations and sort them alphabetically 
 **/
public class AnalyticsCounter {
	private String filepath = "symptoms.txt";

	public static void main(String args[]) throws Exception {
		AnalyticsCounter analyticsCounter = new AnalyticsCounter();
		analyticsCounter.writeSymptoms();
	}

	/**
	 * Write in output file 
	 * 
	 * @throws if the file is not read
	 **/
	public void writeSymptoms() throws IOException {
		FileWriter writer = new FileWriter("result.out");
		getListSymptoms().forEach((id, name) -> {
			try {
				writer.write(id + ": " + name + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		writer.close();
	}
	
	/**
	 * @return list of symptoms sorted alphabetically
	 **/
	SortedMap<String, Integer> getListSymptoms() {
		SortedMap<String, Integer> listSymptoms = new TreeMap<String, Integer>();
		ISymptomReader reader = new ReadSymptomDataFromFile(filepath);
		List<String> data = reader.getSymptoms();
		
		for(String s : data) {
			listSymptoms.putIfAbsent(s, 0);
			int count = listSymptoms.get(s);
			listSymptoms.put(s, count + 1);
		}
		return listSymptoms;
	}
}