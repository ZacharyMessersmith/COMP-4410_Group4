package com.databaseProject.databaseProject;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;

public class UserPanel extends JRootPane
					   implements ActionListener, MouseListener, ChangeListener
{
	JMenu	menuHistory;
	JMenu	menuEditProfile;
	
	JTextField	searchBar;
	JButton		searchButton;
	JCheckBox	gamesCheck;
	JCheckBox	moviesCheck;
	JCheckBox	awardsCheck;
	JCheckBox	notPrevRentedCheck;
	JComboBox	searchByBox;
	JLabel		numRentalsAvailableLabel;
	
	//JTable for displaying media information
	
	User		user;
	
	UserPanel()//User user)
	{
	Container	cp;
	JPanel		buttonPanel;
	JLabel		searchByLabel;
	
//	this.user = user;
	
	setJMenuBar(newMenuBar());
	setDefaultButton(searchButton);
	
	searchBar = new JTextField(10);
	
	searchButton = new JButton("Search");
	searchButton.addActionListener(this);
	searchButton.setActionCommand("SEARCH");
	
	gamesCheck = new JCheckBox("Games", true);
	gamesCheck.addChangeListener(this);
	moviesCheck = new JCheckBox("Movies", true);
	awardsCheck = new JCheckBox("Award winning", false);
	notPrevRentedCheck = new JCheckBox("Not previously rented", false);
	
	searchByBox = new JComboBox();
	searchByBox.addItem("Keyword");
	searchByBox.addItem("Actor");
	searchByBox.addItem("Director");
	searchByBox.addItem("Genre");
	
	searchByLabel = new JLabel("Search by: ", SwingConstants.RIGHT);
	searchByLabel.setMinimumSize(new Dimension(100, 0));
	
	numRentalsAvailableLabel = new JLabel("Number of Rentals Available: 0", SwingConstants.RIGHT);
	//updateNumRentalsAvailableLabel(user.getMaxNumRentals()-user.getCurrentNumRentals());
	
	
	//////////////////////////////////////////Layout panel/////////////////////////////////////////////////////////
	
	buttonPanel = new JPanel();
	GroupLayout layout = new GroupLayout(buttonPanel);
	buttonPanel.setLayout(layout);

	layout.setAutoCreateGaps(true);

	layout.setAutoCreateContainerGaps(true);

	GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

	hGroup.addGroup(layout.createParallelGroup().
            addComponent(searchBar).addComponent(gamesCheck).addComponent(notPrevRentedCheck));
	hGroup.addGroup(layout.createParallelGroup().
			addComponent(searchByLabel).addComponent(moviesCheck).addComponent(awardsCheck));
	hGroup.addGroup(layout.createParallelGroup().
            addComponent(searchByBox));
	hGroup.addGroup(layout.createParallelGroup().
            addComponent(searchButton));
	layout.setHorizontalGroup(hGroup);

	GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(searchBar).addComponent(searchByBox).addComponent(searchByLabel).addComponent(searchButton));
	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(gamesCheck).addComponent(moviesCheck));
	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(notPrevRentedCheck).addComponent(awardsCheck));
	layout.setVerticalGroup(vGroup);
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	cp = getContentPane();
	cp.add(buttonPanel, BorderLayout.NORTH);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void stateChanged(ChangeEvent e)
	{
	
	}
	
	public void	actionPerformed(ActionEvent ae)
	{
	if (ae.getActionCommand().equals("SEARCH"))
		{}
	}
	

	
	public void mouseClicked(MouseEvent e)
	{
	if (e.getSource() == menuHistory)
		{
		System.out.println("history");
		}
	else if (e.getSource() == menuEditProfile)
		{
		System.out.println("profile");
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	void	updateNumRentalsAvailableLabel(int numAvailable)
	{
	numRentalsAvailableLabel.setText("Number of Rentals Available: " + numAvailable);
	}
	
	
	private	 JMenuBar newMenuBar()
	{
	JMenuBar	menuBar;

	menuBar = new JMenuBar();
	
	menuHistory = new JMenu("Show History");
	menuHistory.addMouseListener(this);
	menuBar.add(menuHistory);
	
	menuEditProfile = new JMenu("Edit Profile");
	menuEditProfile.addMouseListener(this);
	menuBar.add(menuEditProfile);
	
	menuHistory.setEnabled(true);
	menuEditProfile.setEnabled(true);
	
	return menuBar;
	}
	
	public void mouseReleased(MouseEvent e)
	{}

	public void mousePressed(MouseEvent e) 
	{}
	
	public void mouseEntered(MouseEvent e)
	{}
	
	public void mouseExited(MouseEvent e)
	{}
}