/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Font;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		
		GLine scaffold = new GLine(getWidth()/2 - SHIFT, 50, getWidth()/2 - SHIFT, 100 + SCAFFOLD_HEIGHT );
		GLine beam = new GLine(getWidth()/2 - SHIFT, 50, getWidth()/2 - SHIFT + BEAM_LENGTH, 50);
		GLine rope = new GLine(getWidth()/2 - SHIFT + BEAM_LENGTH, 50, getWidth()/2 - SHIFT + BEAM_LENGTH, 50 + ROPE_LENGTH);
		add(scaffold);
		add(beam);
		add(rope);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
	
	if (ourWord != null)
	{
		remove(ourWord);
	}
		ourWord = new GLabel (word, 10, 20);
		ourWord.setFont(new Font("Serif", Font.BOLD, 20));
		add(ourWord);
		
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
				
		if(head == null)
		{
			head = new GOval(getWidth()/2 - SHIFT + BEAM_LENGTH - HEAD_RADIUS, 50 + ROPE_LENGTH, 2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
			add(head);
		}
		
		else 
			if (body == null)
			{
				body = new GLine(getWidth()/2 - SHIFT + BEAM_LENGTH, 50 + ROPE_LENGTH + 2 * HEAD_RADIUS, getWidth()/2 - SHIFT + BEAM_LENGTH, 50 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + ARM_OFFSET_FROM_HEAD);
				add(body);
			}
			else
				if ((leftArmUpper == null) || (leftArmLower == null))
				{
					leftArmUpper = new GLine(getWidth()/2 - SHIFT + BEAM_LENGTH, 50 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD, getWidth()/2 - SHIFT + BEAM_LENGTH - UPPER_ARM_LENGTH, 50 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD );
					leftArmLower = new GLine(getWidth()/2 - SHIFT + BEAM_LENGTH - UPPER_ARM_LENGTH, 50 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD, getWidth()/2 - SHIFT + BEAM_LENGTH - UPPER_ARM_LENGTH, 50 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH );
			
					add(leftArmUpper);
					add(leftArmLower);
				}
		
				else
					if ((rightArmUpper == null) || (rightArmLower == null))
					{
						rightArmUpper = new GLine(getWidth()/2 - SHIFT + BEAM_LENGTH, 50 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD, getWidth()/2 - SHIFT + BEAM_LENGTH + UPPER_ARM_LENGTH, 50 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD );
						rightArmLower = new GLine(getWidth()/2 - SHIFT + BEAM_LENGTH + UPPER_ARM_LENGTH, 50 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD, getWidth()/2 - SHIFT + BEAM_LENGTH + UPPER_ARM_LENGTH, 50 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH );
				
						add(rightArmUpper);
						add(rightArmLower);
					}
		
					else
						if ((rightLegUpper == null) || (rightLegLower == null))
						{
							rightLegUpper = new GLine(getWidth()/2 - SHIFT + BEAM_LENGTH, 50 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + BODY_LENGTH + ROPE_LENGTH, getWidth()/2 - SHIFT + BEAM_LENGTH + HIP_WIDTH, 50 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + BODY_LENGTH + ROPE_LENGTH);
							rightLegLower = new GLine(getWidth()/2 - SHIFT + BEAM_LENGTH + HIP_WIDTH, 50 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + BODY_LENGTH + ROPE_LENGTH,  getWidth()/2 - SHIFT + BEAM_LENGTH + HIP_WIDTH, 50 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + BODY_LENGTH + LEG_LENGTH + ROPE_LENGTH);
					
							add(rightLegUpper);
							add(rightLegLower);
						}
		
						else
							if ((leftLegUpper == null) || (leftLegLower == null))
							{
								leftLegUpper = new GLine(getWidth()/2 - SHIFT + BEAM_LENGTH, 50 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + BODY_LENGTH + ROPE_LENGTH, getWidth()/2 - SHIFT + BEAM_LENGTH - HIP_WIDTH, 50 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + BODY_LENGTH + ROPE_LENGTH);
								leftLegLower = new GLine(getWidth()/2 - SHIFT + BEAM_LENGTH - HIP_WIDTH, 50 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + BODY_LENGTH + ROPE_LENGTH,  getWidth()/2 - SHIFT + BEAM_LENGTH - HIP_WIDTH, 50 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + BODY_LENGTH + LEG_LENGTH + ROPE_LENGTH);
						
								add(leftLegUpper);
								add(leftLegLower);
							}
		
							else
								if (leftFoot == null)
								{
									leftFoot = new GLine(getWidth()/2 - SHIFT + BEAM_LENGTH - HIP_WIDTH, 50 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + BODY_LENGTH + LEG_LENGTH + ROPE_LENGTH, getWidth()/2 - SHIFT + BEAM_LENGTH - HIP_WIDTH - FOOT_LENGTH, 50 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + BODY_LENGTH + LEG_LENGTH + ROPE_LENGTH );
									add(leftFoot);
								}
		
								else
									if (rightFoot == null)
									{
										rightFoot = new GLine(getWidth()/2 - SHIFT + BEAM_LENGTH + HIP_WIDTH, 50 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + BODY_LENGTH + LEG_LENGTH + ROPE_LENGTH, getWidth()/2 - SHIFT + BEAM_LENGTH + HIP_WIDTH + FOOT_LENGTH, 50 + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + BODY_LENGTH + LEG_LENGTH + ROPE_LENGTH );
										add(rightFoot);
									}
		
		if (guesses != null)
		{
			remove(guesses);
		}
	
		ourGuesses = ourGuesses + letter;
		guesses = new GLabel (ourGuesses, 10, 40);
		guesses.setFont(new Font("Serif", Font.PLAIN, 15));
		add(guesses);
		
	}
	
	

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 50;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int SHIFT = 100;
	
	private GOval head;
	private GLine body;
	
	private GLine leftArmUpper;
	private GLine leftArmLower;
	
	private GLine rightArmUpper;
	private GLine rightArmLower;

	private GLine rightLegUpper;
	private GLine rightLegLower;
	
	private GLine leftLegUpper;
	private GLine leftLegLower;
	
	private GLine leftFoot;
	private GLine rightFoot;
	
	private GLabel ourWord;
	private GLabel guesses;
	private String ourGuesses = "";
	

}
