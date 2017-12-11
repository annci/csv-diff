package se.annci;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CsvParser {
	public List<String> parseRecord(List<String> records) {
		List<String> output = new ArrayList<>();
		final Pattern PATTERN = Pattern.compile("(\"[^\"]+\")|([0-9]+)");

		for (int i = 0; i < records.size(); i++) {
		     Matcher m = PATTERN.matcher(records.get(i));
		     int index = 0;
		     StringBuffer sb = new StringBuffer();
		     while (m.find(index)) {
		    	 sb.append(m.group()).append(" ");
		         index = m.end();
		     }
	    	 output.add(sb.toString());
		}
		
		return output;
	}

}
