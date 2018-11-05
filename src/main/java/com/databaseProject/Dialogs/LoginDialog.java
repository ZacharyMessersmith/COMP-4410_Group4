package com.databaseProject.Dialogs;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;
import javax.swing.JDialog;

import com.databaseProject.DAOs.UserDAO;
import com.databaseProject.databaseProject.MainJFrame;

public class LoginDialog extends JDialog
						 implements ActionListener
{
	JTextField	usernameBox;
	JTextField	passwordBox;
	JButton		loginButton;
	
	UserDAO		userDao;
	
	public LoginDialog()
	{
	JLabel		usernameLabel;
	JLabel		passwordLabel;
	JPanel		panel1;
	Container	cp;
	
	userDao = new UserDAO();
	
	usernameLabel = new JLabel("Username:");
	passwordLabel = new JLabel("Password:");
	
	usernameBox = new JTextField(10);
	passwordBox = new JTextField(10);
	
	loginButton = new JButton("Login");
	loginButton.addActionListener(this);
	
	panel1 = new JPanel();
	
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
		boolean	validUser = false;
		String email = usernameBox.getText().trim();
		String password = passwordBox.getText().trim();
		validUser = userDao.testUser(email, password);
		
		if (validUser)
			{
			System.out.println("valid user");
			new MainJFrame(userDao.getUser(email));
			dispose();
			}
		else
			JOptionPane.showMessageDialog(this, "Username or password is incorrect.", "Error", JOptionPane.INFORMATION_MESSAGE);
			
		}
	}
	
	void	setupLoginDialog()
	{
	Toolkit		tk;
	Dimension	d;

	tk = Toolkit.getDefaultToolkit();
	d = tk.getScreenSize();

	setSize(500, 200);
	setLocation(d.width/3, d.height/4);

//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	getRootPane().setDefaultButton(loginButton);

	setTitle("Login");

	setVisible(true);
	}
}