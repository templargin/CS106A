/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		GLabel message = new GLabel(msg);
		message.setFont(MESSAGE_FONT);
		add(message, getWidth()/2 - message.getWidth()/2, getHeight() - BOTTOM_MESSAGE_MARGIN);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		
		removeAll();
		if (profile != null)
			
			{
		/** Displaying Name */
			GLabel profileName = new GLabel(profile.getName());
			profileName.setFont(PROFILE_NAME_FONT);
			profileName.setColor(Color.BLUE);
			add(profileName, LEFT_MARGIN, TOP_MARGIN + profileName.getAscent());
	
		/** Displaying Image */
			GImage image = profile.getImage();
			if (image != null)
			{
				image.scale(IMAGE_WIDTH / image.getWidth(), IMAGE_HEIGHT / image.getHeight());
				add(image, LEFT_MARGIN, profileName.getHeight() + IMAGE_MARGIN);
			}
			else
			{
				GRect placeHolder = new GRect(LEFT_MARGIN, profileName.getHeight() + IMAGE_MARGIN , IMAGE_WIDTH, IMAGE_HEIGHT);
				GLabel text = new GLabel("No image");
				text.setFont(PROFILE_IMAGE_FONT);
				add(text, LEFT_MARGIN + IMAGE_WIDTH/2 - text.getWidth()/2, profileName.getHeight() + IMAGE_MARGIN + IMAGE_HEIGHT/2 + text.getAscent()/2);
				add(placeHolder);
			}
			
		/** Displaying Status */	
			GLabel status;
			if (profile.getStatus() != "")
			{
				status = new GLabel(profile.getName() + " is " + profile.getStatus());
			}
			else
			{
				status = new GLabel("No current status");
			}
			status.setFont(PROFILE_STATUS_FONT);
			add(status, LEFT_MARGIN, profileName.getHeight() + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN + status.getAscent());
		
		/** Displaying Friends */
			GLabel header = new GLabel("Friends:", getWidth()/2, profileName.getHeight() + IMAGE_MARGIN);			
			header.setFont(PROFILE_FRIEND_LABEL_FONT);
			add(header);
			
			Iterator it = profile.getFriends();
			int i = 1;
			
			while (it.hasNext())
			{
				GLabel friends = new GLabel((String)it.next());
				friends.setFont(PROFILE_FRIEND_FONT);
				add(friends, getWidth()/2, profileName.getHeight() + IMAGE_MARGIN + i * friends.getAscent() + 2 );
				i++;
			}
			
		}

	}


	
}
