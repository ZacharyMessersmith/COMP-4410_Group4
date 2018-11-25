package com.databaseProject.databaseProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.databaseProject.Dialogs.LoginDialog;
import com.databaseProject.Pojos.User;

import java.awt.*;

public class MainJFrame extends JFrame
						 implements MouseListener
{
	AdminPanel	adminPanel;
	UserPanel	userPanel;
	JTabbedPane	tabPane;
	
	JMenu		menuLogout;
	
	public MainJFrame(User user)
	{
	Container	cp;
	
	adminPanel = new AdminPanel();
	userPanel = new UserPanel(user);
	
	tabPane = new JTabbedPane();
	//tabPane.addChangeListener(this);
	
	if (user.isAdmin())
		tabPane.addTab("Administrator", adminPanel);
	
	if (user.isUser())
		tabPane.addTab("Member", userPanel);
	//tabPane.setTabComponentAt(0, new JLabel("Tab"));
	
	cp = getContentPane();
	cp.add(tabPane, BorderLayout.CENTER);
	setJMenuBar(newMenuBar());
	
	
	setupMainFrame();
	System.out.println("Done.");
	}

	public void mouseClicked(MouseEvent e)
	{
	if (e.getSource() == menuLogout)
		{
		this.dispose();
		new LoginDialog();
		}
	}
	
	

	
	private	 JMenuBar newMenuBar()
	{
	JMenuBar	menuBar;

	menuBar = new JMenuBar();
	
	menuBar.add(Box.createHorizontalGlue());
	
	menuLogout = new JMenu("Logout");
	menuLogout.addMouseListener(this);
	menuBar.add(menuLogout);
	
	menuBar.add(Box.createRigidArea(new Dimension(10,10)));
	
	return menuBar;
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
	
	public void mouseReleased(MouseEvent e)
	{}

	public void mousePressed(MouseEvent e) 
	{}
	
	public void mouseEntered(MouseEvent e)
	{}
	
	public void mouseExited(MouseEvent e)
	{}

}