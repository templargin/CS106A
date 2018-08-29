/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.HashMap;

import acm.io.*;
import acm.program.*;
import acm.util.*;
import acmx.export.java.util.ArrayList;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		
		int[] dice = new int[N_DICE];
		playerScore = new int[nPlayers][N_CATEGORIES];
		ifCategoryUsed = new boolean[N_CATEGORIES][nPlayers];
		
		initScore(playerScore);
		
/** Start loop for Categories */		
		for (int i = 0; i < N_SCORING_CATEGORIES; i++)
		{
/** Start loop for player's turn per category */			
		for (int pT = 1; pT <= nPlayers; pT++ )	
		{
					
		randomDice(dice);
		display.printMessage(playerNames[pT - 1] + "'s turn! Please click \"Roll Dice\" button to roll the dice");
		display.waitForPlayerToClickRoll(pT);
		display.displayDice(dice);

/** Start loop for roll per player */				
		for (int j = 1; j < NUMBER_ROLLS; j++)
		{	
			display.printMessage("Please select dice you wish to re-roll and click \"Roll Again\" ");
			display.waitForPlayerToSelectDice();
			changeDice(dice);
			display.displayDice(dice);
		}
/** Finish loop for roll per player */		
		
		display.printMessage("Please select a category for this roll");
		int catPicked = display.waitForPlayerToSelectCategory();
		
		/* Check if category was used */
		while (ifCategoryUsed[catPicked - 1][pT-1])
		{
			display.printMessage("This category has been already used. Please pick another category");
			catPicked = display.waitForPlayerToSelectCategory();
		}	
		ifCategoryUsed[catPicked - 1][pT - 1] = true;	
		
		
		/* Check if selected category matches the dice configuration */
	//	boolean correct = YahtzeeMagicStub.checkCategory(dice, catPicked);
		boolean correct = checkCategory(dice, catPicked);

		
		/* Assign score if correct, assign zero if not */
		if (correct) 
		{
			int score = determineScore(catPicked, dice);
			playerScore[pT - 1][catPicked - 1] = score;
			display.updateScorecard(catPicked, pT, score);
		}
		else
		{
			display.updateScorecard(catPicked, pT, 0);
		}
		
		/* Update Total value on the board */
		display.updateScorecard(TOTAL, pT, sumDice(playerScore[pT - 1]));	
		
		}
/** Finish loop for player's turn */		
		
		}
/** Finish the Loop All players */	
		
		/* Check and update upper score and bonus */
		checkUpdateUpper();
		
		/* Update lower score */
		updateLowerScore();
		
		/* Update Total Score */
		for (int i = 1; i <= nPlayers; i++)
		{
			display.updateScorecard(TOTAL, i, sumDice(playerScore[i - 1]));	
			playerScore[i - 1][TOTAL - 1] = sumDice(playerScore[i - 1]);
		}
		
		/* Determine a winner */
		int winnerPlayer = determineWinner();
		
		/* Display message for winner */
		display.printMessage("Congratulations, " + playerNames[winnerPlayer] + " you are the winner with a total score of " + playerScore[winnerPlayer][TOTAL - 1]);
	
	}
	
	
	private boolean checkCategory(int[] array, int category)
	{
		switch (category) {
		case ONES: return true;
		case TWOS: return true;
		case THREES: return true;
		case FOURS: return true;
		case FIVES: return true;
		case SIXES: return true;
		case THREE_OF_A_KIND: if ((uniquesInArray(array) == 3) && (maxDuplicates(array) == 3)) return true; else return false;
		case FOUR_OF_A_KIND: if (maxDuplicates(array) == 4) return true; else return false;
		case FULL_HOUSE: if ((uniquesInArray(array) == 2) && (maxDuplicates(array) == 3)) return true; else return false;
		case SMALL_STRAIGHT: if (consecutiveCount(array) == 4) return true; else return false;
		case LARGE_STRAIGHT: if (consecutiveCount(array) == 5) return true; else return false;
		case YAHTZEE: if (uniquesInArray(array) == 1) return true; else return false;
		case CHANCE:  return true;
		default: throw new ErrorException("getWord: Illegal index");
	}
	}
	
	private void sort(int a[])
	{
		int c = 0;
		for (int i = 0; i < a.length; i++)
		{
			for (int j = 0; j < a.length - i - 1; j++)
			{
				if (a[j] > a[j+1]) 
				{
					c = a[j];
					a[j] = a[j+1];
					a[j+1] = c;
				}
			}
		}
		
	}
	
	private int consecutiveCount(int[] arr)
	{
		sort(arr);
		int max = 0;
		int count = 1;
		for (int i = 1; i < arr.length; i++)
		{
			if ((arr[i] - arr[i-1]) == 1) 
			{
				count++;
				if (count > max) max = count;
			}
			else
			{
				count = 1;
			}
		}
		return max;
	}
	
	private int maxDuplicates(int[] arr)
	{
		HashMap<Integer, Integer> duplicatesCount = new HashMap<Integer, Integer>();	
		int max = 0;	
		for (int i = 0; i < arr.length; i++)
		{
			Integer count = duplicatesCount.get(arr[i]);		
			if (count == null)
			{
				duplicatesCount.put(arr[i], 1);
			}
			else 
			{	
				duplicatesCount.put(arr[i], ++count);
				if (count > max)
				{
					max = count; 
				}
			}			
		}	
		
		return max;
	}
	
	private int uniquesInArray(int[] array)
	{
		int count = 0;
		ArrayList check = new ArrayList();
		for (int i = 0; i < array.length; i++)
		{
			if (!check.contains(array[i])) 
			{
				check.add(array[i]);
			}
		}
		return check.size();
	}
	
	
	
	private int determineWinner()
	{
		int winner = 0;
		
		for (int i = 0; i < nPlayers - 1; i++)
		{
			if (playerScore[i][TOTAL - 1] < playerScore[i + 1][TOTAL - 1])
			{
				winner = i + 1;
			}
		}
		
		return winner;
		
	}
	
	private void checkUpdateUpper()
	{
		for (int i = 0; i < nPlayers; i++)
		{
			int upperScore = playerScore[i][0] + playerScore[i][1] + playerScore[i][2] + playerScore[i][3] + playerScore[i][4] + playerScore[i][5] + playerScore[i][6]; 
			display.updateScorecard(UPPER_SCORE, i + 1, upperScore);
			
			if (upperScore > 63) 
			{
				display.updateScorecard(UPPER_BONUS, i + 1, 35);	
				playerScore[i][UPPER_BONUS - 1] = 35;
			}				
			else
			{
				display.updateScorecard(UPPER_BONUS, i + 1, 0);	
			}
		}
	}
	
	private void updateLowerScore()
	{
		for (int i = 0; i < nPlayers; i++)
		{
			int lowerScore = playerScore[i][8] + playerScore[i][9] + playerScore[i][10] + playerScore[i][11] + playerScore[i][12] + playerScore[i][13] + playerScore[i][14];
			display.updateScorecard(LOWER_SCORE, i + 1, lowerScore);	
		}
	}
	
	private int sumDice(int[] array)
	{
		int result = 0;
		for (int i = 0; i < array.length; i++)
		{
			result = result + array[i];
		}		
		return result;
	}
	
	private void initScore(int[][] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			for (int j = 0; j < array[0].length; j++)
			{
				array[i][j] = 0;
			}
		}
	}
	
	private void randomDice(int[] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			array[i] = rgen.nextInt(1, N_FACETS);
		}
	}
	
	private void changeDice(int[] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			if (display.isDieSelected(i))
				array[i] = rgen.nextInt(1, N_FACETS);
		}
	}
	
/* Calculating the score depending on category picked */
	private int determineScore(int category, int[] array) {
		switch (category) {
			case ONES: return oneToSix(category, array);
			case TWOS: return oneToSix(category, array);
			case THREES: return oneToSix(category, array);
			case FOURS: return oneToSix(category, array);
			case FIVES: return oneToSix(category, array);
			case SIXES: return oneToSix(category, array);
			case THREE_OF_A_KIND: return sumDice(array);
			case FOUR_OF_A_KIND: return sumDice(array);
			case FULL_HOUSE: return 25;
			case SMALL_STRAIGHT: return 30;
			case LARGE_STRAIGHT: return 40;
			case YAHTZEE: return 50;
			case CHANCE:  return sumDice(array);
			default: throw new ErrorException("getWord: Illegal index");
		}
	}

	
	private int oneToSix(int category, int[] array)
	{
		int result = 0;
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] == category) result = result + array[i];
		}
		return result;
	}

	
		
/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private boolean[][] ifCategoryUsed; 
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int[][] playerScore; 

}
