package com.databaseProject.databaseProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AdminPanel extends JRootPane
						implements ActionListener
{	

	JMenuItem	menuCreateUser;
	JMenuItem	menuCreateMedia;
	JMenuItem	menuViewMembers;
	JMenuItem	menuViewMedia;
	JMenuItem	menuViewRentals;
	
	JComboBox	showMediaBox;
	
	//JTable to show members
	//JTable to show media
	//JTable to show rentals
	//JLabels for each JTable

	AdminPanel()
	{
	showMediaBox = new JComboBox();
	showMediaBox.addItem("All");
	showMediaBox.addItem("Top 10");
	showMediaBox.addActionListener(this);
	showMediaBox.setActionCommand("UPDATE_MEDIA_VIEW");
	
	setJMenuBar(newMenuBar());
	}
	
	//create functions to show/hide the proper things for each view
	
	
	public void actionPerformed(ActionEvent ae)
	{
	if (ae.getActionCommand().equals("CREATE_USER"))
		{
		new UserDialog();
		}
	else if (ae.getActionCommand().equals("CREATE_MEDIA"))
		{
		new AdminMediaDialog();
		}
	else if (ae.getActionCommand().equals("VIEW_MEMBERS"))
		{}
	else if (ae.getActionCommand().equals("VIEW_MEDIA"))
		{}
	else if (ae.getActionCommand().equals("VIEW_RENTALS"))
		{}
	else if (ae.getActionCommand().equals("UPDATE_MEDIA_VIEW"))
		{}
	
	}
	
	private	 JMenuBar newMenuBar()
	{
	JMenuBar	menuBar;
	JMenu		subMenu;


	menuBar = new JMenuBar();

	subMenu = new JMenu("Create");

	menuCreateUser = newMenuItem("Create User", "CREATE_USER", this, "Create a new user. ");
	menuCreateMedia = newMenuItem("Create Media", "CREATE_MEDIA", this, "Create a new game or movie. ");
	menuViewMembers = newMenuItem("Members", "VIEW_MEMBERS", this, "View members. ");
	menuViewMedia = newMenuItem("Media", "VIEW_MEDIA", this, "View games and movies. ");
	menuViewRentals = newMenuItem("Rentals", "VIEW_RENTALS", this, "View rentals made in the past 24 hours. ");

	subMenu.add(menuCreateUser);
	subMenu.add(menuCreateMedia);

	menuBar.add(subMenu);

	subMenu = new JMenu("View");
	subMenu.add(menuViewMembers);
	subMenu.add(menuViewMedia);
	subMenu.add(menuViewRentals);

	menuBar.add(subMenu);
	
	menuCreateUser.setEnabled(true);
	menuCreateMedia.setEnabled(true);
	menuViewMembers.setEnabled(true);
	menuViewMedia.setEnabled(true);
	menuViewRentals.setEnabled(true);

	return menuBar;
	}

	private	JMenuItem	newMenuItem(String label, String actionCommand, ActionListener menuListener, String toolTipText)
	{
	JMenuItem	m;

	m = new JMenuItem(label);

	m.getAccessibleContext().setAccessibleDescription(toolTipText);
	m.setToolTipText(toolTipText);
	m.setActionCommand(actionCommand);
	m.addActionListener(menuListener);

	return	m;
	}
}