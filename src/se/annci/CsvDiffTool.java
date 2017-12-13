package se.annci;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CsvDiffTool {
			
	public static void main(String[] args) {
		String fileA = "";
		String fileB = "";
		if (args.length == 4 && args[0].equals("-a") && args[2].equals("-b")) {
			fileA = args[1];
			fileB = args[3];
		}
		else {
			System.out.println("Missing arguments. Syntax: -a filenameA -b filenameB");
		}
				
		Path pathA = Paths.get(fileA);
		Path pathB = Paths.get(fileB);
		
		try {
			List<String> recordsA = Files.readAllLines(pathA);
			List<String> recordsB = Files.readAllLines(pathB);
			
			if (!recordsA.get(0).equals(recordsB.get(0))) {
				System.err.println("Headers doesn't match!");
				System.exit(0);
			}
			String header = recordsA.get(0);
			recordsA.remove(0);
			recordsB.remove(0);
			
			CsvParser parser = new CsvParser();
			List<String> parsedRecordsA = parser.parseRecord(recordsA);
			Set<String> setAUni = new HashSet<String>(parsedRecordsA);
			Set<String> setA = new HashSet<String>(parsedRecordsA);
			Set<String> setBoth = new HashSet<String>(parsedRecordsA);

			List<String> parsedRecordsB = parser.parseRecord(recordsB);
			Set<String> setBUni = new HashSet<String>(parsedRecordsB);
			Set<String> setB = new HashSet<String>(parsedRecordsB);

			CsvWriter writer = new CsvWriter();
			setAUni.removeAll(setB);
		    
		    setBUni.removeAll(setA);
		    
			setBoth.retainAll(setB);
			
			writer.print(header, setAUni, setBUni, setBoth);
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}
}
