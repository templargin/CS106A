/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import acm.util.*;


public class HangmanLexicon {

	// This is the HangmanLexicon constructor
	public HangmanLexicon() {
		
		try {
			listOfWords = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			
			while (true) {
				String word = br.readLine();
				
				if (word == null)
					break;
				
				listOfWords.add(word);
			}
			
			br.close();
			
		} catch (IOException e) {
			throw new ErrorException(e);
		}
	}
	
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		int count = 0;
		for (int i = 0; i < listOfWords.size(); i++)
			
		{
			count++;
		}
		
		return count;
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		
		return listOfWords.get(index);
			
		/*
		switch (index) {
			case 0: return "BUOY";
			case 1: return "COMPUTER";
			case 2: return "CONNOISSEUR";
			case 3: return "DEHYDRATE";
			case 4: return "FUZZY";
			case 5: return "HUBBUB";
			case 6: return "KEYHOLE";
			case 7: return "QUAGMIRE";
			case 8: return "SLITHER";
			case 9: return "ZIRCON";
			default: throw new ErrorException("getWord: Illegal index");
		}
		*/
	};
	
	private ArrayList<String> listOfWords;


	
}
