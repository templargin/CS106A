/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		listOfEntries.clear();	
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		listOfEntries.add(entry);			
	}
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		addGrid();
		if (!listOfEntries.isEmpty()) drawFromEntry(listOfEntries);
	}
	
	
	/** This function adds vertical lines, horizontal lines and decades at the bottom **/
	private void addGrid()
	{
		GLine gridLine;
		GLine gridLineTop;
		GLine gridLineBottom;
		GLabel decadeLabel;
		int initialDecade = START_DECADE;
		
		spacer = getWidth()/NDECADES;
		
		for (int i = 1; i <= NDECADES; i++)
		{
			gridLine = new GLine(spacer * i, 0 , spacer * i, getHeight());	
			decadeLabel = new GLabel(String.valueOf(initialDecade), spacer * (i - 1) + SMALL_MARGIN, getHeight() - 5);		
			initialDecade = initialDecade + 10;		
			add(gridLine);
			add(decadeLabel);
		}	
		
		gridLineTop = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		add(gridLineTop);
		gridLineBottom = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		add(gridLineBottom);
		
	}
	
	/** Takes a list of entries and draws a graph for every entry of this list */
	private void drawFromEntry(ArrayList list)
	{
		for (int j = 0; j < list.size(); j++)
		{
			NameSurferEntry entry = (NameSurferEntry) list.get(j);
			int initialRank;
			int finalRank;
			double initialRankCoordinate;
			double finalRankCoordinate;
			GLabel nameLabel;
			GLine rankLine;
			
			
			initialRank = entry.getRank(0);	
			if (initialRank == 0)
			{				
				initialRankCoordinate = getHeight()- GRAPH_MARGIN_SIZE;
			}
			else
			{
				initialRankCoordinate = GRAPH_MARGIN_SIZE + initialRank * (getHeight() - 2 * GRAPH_MARGIN_SIZE) / 1000;
			}			
			
			for (int i = 1; i < NDECADES; i++)
			{
				finalRank = entry.getRank(i);
				if (finalRank == 0)
				{				
					finalRankCoordinate = getHeight()- GRAPH_MARGIN_SIZE;
				}
				else
				{
					finalRankCoordinate = GRAPH_MARGIN_SIZE + finalRank * (getHeight() - 2 * GRAPH_MARGIN_SIZE) / 1000;
				}			
				rankLine = new GLine(spacer * (i - 1), initialRankCoordinate, spacer * i, finalRankCoordinate);	
				nameLabel = new GLabel(entry.getName() + " " + initialRank, spacer * (i - 1) + SMALL_MARGIN , initialRankCoordinate - SMALL_MARGIN);
				
				nameLabel.setColor(colorOfLine(j));
				rankLine.setColor(colorOfLine(j));
				add(rankLine);
				add(nameLabel);	
				
				initialRank = finalRank;
				if (initialRank == 0)
				{
					initialRankCoordinate = getHeight() - GRAPH_MARGIN_SIZE;
				}
				else
				{
					initialRankCoordinate = GRAPH_MARGIN_SIZE + initialRank * (getHeight() - 2 * GRAPH_MARGIN_SIZE) / 1000;
				}
			}
			
			
			nameLabel = new GLabel(entry.getName() + " " + initialRank, spacer * (NDECADES - 1) + SMALL_MARGIN , initialRankCoordinate - SMALL_MARGIN);
			nameLabel.setColor(colorOfLine(j));
			add(nameLabel);	
		}
		
	}
	
	private Color colorOfLine(int sizeOfEntry)
	{
		if  (((sizeOfEntry) % 4) == 1)
		return Color.RED;
		
		else
			if  (((sizeOfEntry) % 4) == 2)
				return Color.MAGENTA;
		
				else
					if  (((sizeOfEntry) % 6) == 3) 
						return Color.BLUE;
					
								else return Color.BLACK;
	}
	
	private double spacer;
	private ArrayList <NameSurferEntry> listOfEntries = new ArrayList <NameSurferEntry>();
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	

	
	
}
