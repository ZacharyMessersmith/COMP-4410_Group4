package com.databaseProject.databaseProject;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// THIS CLASS IS NOT ACTUALLY USED FOR ANYTHING
public class UserFrame extends JFrame
					   implements ActionListener
{
	JTable	resultsTable;
	JButton	searchButton;
	JTextField	searchBox;

	UserFrame()
	{
	searchBox = new JTextField(10);
	searchButton = new JButton("Search");
	searchButton.addActionListener(this);
	
	
	}
	
	public void actionPerformed(ActionEvent ae)
	{
	if (ae.getSource() == searchButton)
		{
		
		}
	}
}


// Needs:
//	--A member should be able to search movies by actor, director, genre, and/or keywords. 
//	  There should be options for searching only the award winning movies and movies that the member hasn’t checked out previously. 
//	  A member should also be able to search games by genre, platform, and/or keywords. 
//	  The member should be able to perform all these searches from a single interface. 
//	  Each item in the search result should include the item details.
// 	--A member should be able to view a list of all sequels. 
//	--A member should be able to view his/her detailed history of rentals that will include movie/game details, check out dates, and return dates. 
//	--A member should be able to rent movies/games (based on availability and quota). 
//	--A member should be able to modify his personal information.

// General Needs:
// 	--A dialog to edit personal information
