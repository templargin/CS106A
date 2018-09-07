/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		name = readName(line);
		decades = readList(line);
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		
		return (int)decades.get(decade);
		
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		
		String result = "";
		for (int i = 0; i < decades.size() - 1; i++)
		{
			result = result + decades.get(i) + " ";
		}
		
		result = name + " [" + result + decades.get(decades.size() - 1) + "]";
			
		return result;
		
	}
	
	private String readName(String input)
	{
		String myName = "";
		
		for (int i = 0; i < input.length(); i++)
		{
			if (Character.isLetter(input.charAt(i)))
			{
				myName = myName + input.charAt(i);
			}
			else break;
		}
		return myName;
	}
	
	private ArrayList<Integer> readList(String input)
	{		
		int initIndex = input.indexOf(' ');;
		int rightIndex = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
				
		while (true)
		{
			rightIndex = input.indexOf(' ', initIndex + 1);
			if (rightIndex != -1)
			{
			list.add(Integer.parseInt(input.substring(initIndex + 1, rightIndex)));
			initIndex = rightIndex;
			}
			else
			{
				list.add(Integer.parseInt(input.substring(initIndex + 1)));
				break;			
			}
		}	
		return list;
	}
	
	
	private String name;
	private ArrayList<Integer> decades;
	
	
}

