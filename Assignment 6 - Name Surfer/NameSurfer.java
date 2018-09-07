/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

// public class NameSurfer extends Program implements NameSurferConstants {
public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		addInteractors();
		dB = new NameSurferDataBase(NAMES_FILE);
		graph = new NameSurferGraph();
		add(graph);
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
        String inputName = nameField.getText();  
              
        
        // Detect both clicks and ENTER for adding a new label
        if (e.getActionCommand().equals("Graph")) 
        {
        	if (dB.findEntry(inputName) == null)
        	{
        		
        	}
        	else
        	{     		
        		graph.addEntry(dB.findEntry(inputName));
        		graph.update();
        	}
        } 
        
        
        else if (e.getActionCommand().equals("Clear")) 
        {
            graph.clear();
            graph.update();
        }
	}
	
    private void addInteractors() {
        add(new JLabel("Name"), SOUTH);
        nameField = new JTextField(MAX_NAME_SIZE);
        nameField.setActionCommand("Graph");
        nameField.addActionListener(this);
        
        add(nameField, SOUTH);

        add(new JButton("Graph"), SOUTH);
        add(new JButton("Clear"), SOUTH);
        
        addActionListeners();
    }
    
    private JTextField nameField;
    private NameSurferDataBase dB;
    private NameSurferGraph graph;
}
