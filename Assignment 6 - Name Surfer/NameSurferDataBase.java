import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import acm.util.ErrorException;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			while (true) {
				
				String entry = br.readLine();
				if (entry == null) break;
				
				NameSurferEntry entryNames = new NameSurferEntry(entry);
				result.add(entryNames);
				
			}		
			br.close();		
		} catch (IOException e) {
			throw new ErrorException(e);
		}
		
	}
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	 public NameSurferEntry findEntry(String name) {
	
		for (int i = 0; i < result.size(); i++)
		{
			if (result.get(i).getName().equals(name)) return result.get(i);
		}
		return null;
	}
	
	private ArrayList<NameSurferEntry> result = new ArrayList<NameSurferEntry>();

}

