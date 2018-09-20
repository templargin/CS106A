/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		addInteractors();
		 dB = new FacePamphletDatabase();
		 canvas = new FacePamphletCanvas();
		 add(canvas);
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
    	
    	
  /** Add */  	  	 	
    	if (e.getActionCommand().equals("Add"))
    	{	
    		inputName = nameField.getText(); 
    		if (!dB.containsProfile(inputName))
    		{
	        	profileName = new FacePamphletProfile(inputName);
	        	dB.addProfile(profileName);
	    		message = "Added profile: " + inputName;
    		}
    		else
    		{
    			message = "Profile already exists";
    		}
    	}
    	
   /** Lookup */     	
    	if (e.getActionCommand().equals("Lookup"))
    	{
    		inputName = nameField.getText(); 
    		if(dB.containsProfile(inputName))
    		{
    			message = "Displaying " + inputName; 
    		}
    		else
    		{
    			message = "Profile does not exist";
    			inputName = "";
    		}
    	}
    	
    /** Delete */       	
    	if (e.getActionCommand().equals("Delete"))
    	{
    		inputName = nameField.getText(); 
    		if(dB.containsProfile(inputName))
    		{
    			
    			Iterator it = dB.getProfile(inputName).getFriends();
    			
    			while(it.hasNext())
    			{
    				String obj = (String)it.next();
    				dB.getProfile(obj).removeFriend(inputName);
    			}
    			
    			dB.deleteProfile(inputName);
    			message = inputName + "'s profile deleted";
       			inputName = "";
    		}
    		else
    		{
    			inputName = "";
    			message = "Profile does not exist";
    		}
    	} 
   
    /** Adding a friend */	
    	if (e.getActionCommand().equals("addFriendButton"))
    	{  		
    		if (inputName != "")
	    	{
	    		friendName = addFriend.getText();
	    		if (dB.containsProfile(friendName))
	    		{
	    		   	if (dB.getProfile(inputName).addFriend(friendName) == false)	
	    		   	{
	    		   		message = inputName + " is already friends with " + friendName;
	    		   	}
	    		   	else
	    		   	{	    			
		    			message = inputName + " added " + friendName + " as a friend";
		    			dB.getProfile(friendName).addFriend(inputName);
	    		   	}
	    		}
	    		else
	    		{
	    			message = "Profile does not exist";
	    		}   	
    		}
    		else
    		{
    			message = "Please select a profile";
    		}
    	}
    
    /** Changing status */	
    	if (e.getActionCommand().equals("changeStatusButton"))
    	{
    		if (inputName != "")
    		{
	    		status = changeStatus.getText();
	    		dB.getProfile(inputName).setStatus(status);
	    		message = "Status changed";
    		}
    		else
    		{
    			message = "Please select a profile";
    		}
    	}
    	
    /** Change Picture */  	  	 	
    	if (e.getActionCommand().equals("changePictureButton"))
    	{	
    		if (inputName != "")
	    		{
	    		pictureName = changePicture.getText(); 
	    		GImage image = null;
	    		try
	    		{
	    			image = new GImage(pictureName);
	    			dB.getProfile(inputName).setImage(image);
	    			message = "Profile picture updated";
	    		} 
	    		catch (ErrorException ex) 
	    		{	
	    			message = "File with this name does not exist";
	    		}
    		}
    		else
    		{
    			message = "Please select a profile";
    		}
    	}
    	
    /** Display canvas and message */   	
    	canvas.displayProfile(dB.getProfile(inputName));
    	canvas.showMessage(message);
    	   	
	}
    
    
    private void addInteractors() {
        nameField = new JTextField(TEXT_FIELD_SIZE);
        nameField.setActionCommand("Add");
        nameField.addActionListener(this);
        
        add(new JLabel("Name"), NORTH);
        add(nameField, NORTH);
        add(new JButton("Add"), NORTH);
        add(new JButton("Delete"), NORTH);
        add(new JButton("Lookup"), NORTH);

        changeStatus = new JTextField(TEXT_FIELD_SIZE);
        changeStatus.setActionCommand("changeStatusButton");
        changeStatus.addActionListener(this);
        
        changePicture = new JTextField(TEXT_FIELD_SIZE);
        changePicture.setActionCommand("changePictureButton");
        changePicture.addActionListener(this);
        
        addFriend = new JTextField(TEXT_FIELD_SIZE);
        addFriend.setActionCommand("addFriendButton");
        addFriend.addActionListener(this);
        
        add(changeStatus, WEST);
        add(new JButton("changeStatusButton"), WEST);
        add(new JLabel(EMPTY_LABEL_TEXT), WEST);
        add(changePicture, WEST);
        add(new JButton("changePictureButton"), WEST);
        add(new JLabel(EMPTY_LABEL_TEXT), WEST);
        add(addFriend, WEST);
        add(new JButton("addFriendButton"), WEST);
        
        add(new JButton("testing"), WEST);
              
        addActionListeners();
    }
       
    private JTextField nameField;
    private JTextField changeStatus;
    private JTextField changePicture;
    private JTextField addFriend;
    
	private String inputName = "";
	private String friendName;
	private String status;
	private String pictureName;
	private String message;
   
    private FacePamphletDatabase dB;  
    private FacePamphletProfile profileName;
    private FacePamphletCanvas canvas;

}
