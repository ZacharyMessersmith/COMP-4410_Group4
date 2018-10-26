package com.databaseProject.databaseProject;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;
import javax.swing.JDialog;

public class LoginDialog extends JDialog
						 implements ActionListener
{
	JTextField	usernameBox;
	JTextField	passwordBox;
	JButton		loginButton;
	
	LoginDialog()
	{
	JLabel		usernameLabel;
	JLabel		passwordLabel;
	JPanel		panel1;
	Container	cp;
	
	usernameLabel = new JLabel("Username:");
	passwordLabel = new JLabel("Password:");
	
	usernameBox = new JTextField(10);
	passwordBox = new JTextField(10);
	
	loginButton = new JButton("Login");
	loginButton.addActionListener(this);
	
	panel1 = new JPanel();
	panel1.add(usernameLabel);
	
	GroupLayout layout = new GroupLayout(panel1);
	panel1.setLayout(layout);

	layout.setAutoCreateGaps(true);

	layout.setAutoCreateContainerGaps(true);

	GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

	hGroup.addGroup(layout.createParallelGroup().
            addComponent(usernameLabel).addComponent(passwordLabel));
	hGroup.addGroup(layout.createParallelGroup().
            addComponent(usernameBox).addComponent(passwordBox).addComponent(loginButton));
	layout.setHorizontalGroup(hGroup);

	GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(usernameLabel).addComponent(usernameBox));
	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(passwordLabel).addComponent(passwordBox));
	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(loginButton));
	layout.setVerticalGroup(vGroup);
	
	cp = getContentPane();
	cp.add(panel1, BorderLayout.CENTER);
	
	setupLoginDialog();
	System.out.println("Done.");
	}
	
	public void	actionPerformed(ActionEvent ae)
	{
	if (ae.getSource() == loginButton)
		{
		// get db connection using ConnectionManager.getConnection();
		// perform login
		// if (login successful)
			// determine if admin or regular user
			// show proper JFrame
		}
	}
	
	void	setupLoginDialog()
	{
	Toolkit		tk;
	Dimension	d;

	tk = Toolkit.getDefaultToolkit();
	d = tk.getScreenSize();

	setSize(701, 550);
	setLocation(d.width/3, d.height/4);

//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//	getRootPane().setDefaultButton(saveButton);

	setTitle("Login");

	setVisible(true);
	}
}