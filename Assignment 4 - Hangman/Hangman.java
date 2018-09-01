/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

    public void run() {		 
    	   	
    	sW = new HangmanLexicon();
    	int i = rgen.nextInt(0, sW.getWordCount() - 1);
    	
    	String ourWord = sW.getWord(i);  	
    	String dashedWord = getDashedWord(ourWord);
    	
    //	println(ourWord);
    //	println(dashedWord);
    	
    	int numberOfGuesses = 8;
    	String guess;
    	char ch;
    	
    	println("Welcome to Hangman");
    	canvas.reset();

    	
    	while ( (numberOfGuesses > 0) && (stillDashes(dashedWord) ) )
    	{
			println("The word now looks like this: " + dashedWord);
			canvas.displayWord(dashedWord);
			println("You have " + numberOfGuesses + " guesses left");
			
		
			while (true)
			{
				guess = readLine("Your guess: ");
				if (validGuess(guess)) break;
				else println("Invalid guess, please enter a single letter");
			}
			
			ch = guess.charAt(0);
			
			
			if (checkGuess(ch, ourWord))
			{
				println("That guess is correct");
				dashedWord = changeDashesToLetter(dashedWord, ourWord, ch);			
			}
			else
			{
				println("There is no " + ch + "'s in the word" );
				canvas.noteIncorrectGuess(ch);
				numberOfGuesses--;
			}
    	}
    	
    	if (numberOfGuesses == 0) 
		{
    		println("The word was: " + ourWord);
			println("You lose");
		}
    	else
    	{
			canvas.displayWord(ourWord);
    		println("You guessed the word: " + ourWord);
    		println("You win");
    	}
   	
	}
    
    
    public void init() {
   	 canvas = new HangmanCanvas();
   	 add(canvas);
   	 
    }
    
    
    
    
private boolean validGuess(String str)
{
	if (str.equals("")) return false;
	else 
		if (str.length() > 1) return false;
			else 
				if (!Character.isLetter(str.charAt(0))) return false;
	return true;
}
    
 private HangmanLexicon sW;
 private RandomGenerator rgen = RandomGenerator.getInstance();
 private String getDashedWord(String str)
 {
	 String result = "";
	 for (int i = 0; i < str.length(); i++)
	 {
		 result = result + '-';
	 }
	 return result;
 }
 
 private boolean checkGuess(char ch, String str)
 {
	 for (int i = 0; i < str.length(); i++)
	 {
		 if ((ch == str.charAt(i)) || ( Character.toUpperCase(ch) == str.charAt(i)) )return true;
	 }
	 return false;
 }

 private String changeDashesToLetter(String dash, String orig, char ch)
 {
	 for (int i = 0; i < orig.length(); i++)
	 {
		 if ((orig.charAt(i) == ch) && ( Character.isUpperCase(ch)) )
		 dash = dash.substring(0, i) + ch + dash.substring(i+1);
		 
		 else if ((orig.charAt(i) == Character.toUpperCase(ch)))
				 dash = dash.substring(0, i) + Character.toUpperCase(ch) + dash.substring(i+1);	 
		 
	 }
	 return dash;

 }
 
 private boolean stillDashes(String str)
 {
	 for (int i = 0; i < str.length(); i++)
	 {
		 if (str.charAt(i) == '-') return true;
	 }
	 return false;
 }

 private HangmanCanvas canvas;
 
 
}
