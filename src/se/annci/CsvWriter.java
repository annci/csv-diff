package se.annci;

import java.util.Set;

public class CsvWriter {
	public void print(String header, Set<String> setA, Set<String> setB, Set<String> setBoth) {
		System.out.println("_diff," + header);

	    for (String line : setA) {
	    	System.out.println("a," + line.trim().replace(" ", ","));
	    }

	    for (String line : setB) {
	    	System.out.println("b," + line.trim().replace(" ", ","));
	    }

	    for (String line : setBoth) {
	    	System.out.println("ab," + line.trim().replace(" ", ","));
	    }
	}
}
