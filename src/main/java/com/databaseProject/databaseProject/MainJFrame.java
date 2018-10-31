package com.databaseProject.databaseProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;

public class MainJFrame extends JFrame
						 implements ActionListener, ChangeListener
{
	AdminPanel	adminPanel;
	UserPanel	userPanel;
	JTabbedPane	tabPane;
	
	MainJFrame()
	{
	Container	cp;
	
	adminPanel = new AdminPanel();
	userPanel = new UserPanel();
	
	tabPane = new JTabbedPane();
	tabPane.addChangeListener(this);
	
	tabPane.addTab("Administrator", adminPanel);
	tabPane.addTab("Member", userPanel);
	//tabPane.setTabComponentAt(0, new JLabel("Tab"));
	
	cp = getContentPane();
	cp.add(tabPane, BorderLayout.CENTER);
	
	setupMainFrame();
	System.out.println("Done.");
	}


	
	

	public void	stateChanged(ChangeEvent e)
	{
	
	}



	public void	actionPerformed(ActionEvent ae)
	{
	
	}
	
	void	setupMainFrame()
	{
	Toolkit		tk;
	Dimension	d;

	tk = Toolkit.getDefaultToolkit();
	d = tk.getScreenSize();

	setSize(900, 700);
	setLocation(d.width/3, d.height/4);

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//	getRootPane().setDefaultButton(saveButton);

	setTitle("Movies-R-Us");

	setVisible(true);
	}

}