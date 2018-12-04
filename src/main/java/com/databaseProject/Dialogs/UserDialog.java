package com.databaseProject.Dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.util.List;

import com.databaseProject.DAOs.UserDAO;
import com.databaseProject.Pojos.User;
import com.databaseProject.databaseProject.AdminPanel;

import java.awt.*;

//Since this will be used by the admin, we should give them a default password that they can change later.

public class UserDialog extends JDialog
						 implements ActionListener
{
	JTextField	emailBox;
	JTextField	passwordBox;
	JLabel		passwordLabel;
	JTextField	nameBox;
	JTextField	streetAddressBox;
	JTextField	cityBox;
	JTextField	stateBox;
	JTextField	zipBox;
	JTextField	phoneNumberBox;
	
	JLabel		statusLabel;
	JCheckBox	isAdminBox;
	JCheckBox	isUserBox;
	
	JLabel		planLabel;
	JComboBox	planDropdown;
	JLabel		userPlanLabel;
	JTextField	userPlanBox;
	
	JButton		saveButton;
	JButton		cancelButton;
	
	User	user;
	
	UserDAO	userDao;
	
	AdminPanel	parent;
	
	public UserDialog(AdminPanel parent)
	{
	this.parent = parent;
	
	userDao = new UserDAO();
	initializeBaseUserDialog();
	setTitle("Create User");
	
	saveButton.setActionCommand("CREATE_USER");
	passwordBox.setVisible(false);
	passwordLabel.setVisible(false);
	userPlanBox.setVisible(false);
	userPlanLabel.setVisible(false);
	}
	
	public UserDialog(User user, boolean isAdmin, AdminPanel parent)
	{
	this.parent = parent;
	
	userDao = new UserDAO();
	initializeBaseUserDialog();
	this.user = user;
	
	emailBox.setText(user.getEmail());
	emailBox.setEditable(false);
	nameBox.setText(user.getName());
	streetAddressBox.setText(user.getStreetAddress());
	cityBox.setText(user.getCity());
	stateBox.setText(user.getState());
	zipBox.setText(Integer.toString(user.getZipCode()));
	phoneNumberBox.setText(user.getPhoneNumber());
	passwordBox.setText(user.getPassword());
	userPlanBox.setText(user.getPlan());
	userPlanBox.setEditable(false);
	
	for (int i=0; i < planDropdown.getItemCount(); i++)
		{
		if (planDropdown.getItemAt(i).equals(user.getPlan()))
			planDropdown.setSelectedIndex(i);
		}
	
	if (user.isAdmin())
		{
		isAdminBox.setSelected(true);
		}
	
	if (user.isUser())
		{
		isUserBox.setSelected(true);
		}
	
	if (!isAdmin)
		{
		saveButton.setActionCommand("USER_EDIT_SAVE");
		setTitle("Edit Profile");
		
		statusLabel.setVisible(false);
		isAdminBox.setVisible(false);
		isUserBox.setVisible(false);
		planDropdown.setVisible(false);
		planLabel.setVisible(false);
		}
	else
		{
		saveButton.setActionCommand("ADMIN_EDIT_SAVE");
		setTitle("Edit User");
		
		passwordBox.setVisible(false);
		passwordLabel.setVisible(false);
		userPlanLabel.setVisible(false);
		userPlanBox.setVisible(false);
		}
	}

	
	void	initializeBaseUserDialog()
	{
	JLabel		emailLabel;
	JLabel		nameLabel;
	JLabel		streetAddressLabel;
	JLabel		cityLabel;
	JLabel		stateLabel;
	JLabel		zipLabel;
	JLabel		addressLabel;
	JLabel		phoneNumberLabel;
	
	JPanel		panel1;
	JPanel		buttonPanel;
	List<String>	planNames;
	Container	cp;
	
	emailLabel = new JLabel("Email:");
	passwordLabel = new JLabel("Password:", SwingConstants.RIGHT);
	passwordLabel.setMinimumSize(new Dimension(75, 0));
	nameLabel = new JLabel("Name:");
	streetAddressLabel = new JLabel("Street:");
	cityLabel = new JLabel("City:");
	stateLabel = new JLabel("State:");
	zipLabel = new JLabel("Zip:");
	phoneNumberLabel = new JLabel("Phone:");
	
	emailBox = new JTextField(10);
	passwordBox = new JTextField(10);
	nameBox = new JTextField(10);
	streetAddressBox = new JTextField(10);
	cityBox = new JTextField(10);
	stateBox = new JTextField(10);
	zipBox = new JTextField(10);
	phoneNumberBox = new JTextField(10);
	
	addressLabel = new JLabel("Address:");
	
	statusLabel = new JLabel("Status:");
	isAdminBox = new JCheckBox("Admin");
	isUserBox = new JCheckBox("Member");
	
	saveButton = new JButton("Save");
	saveButton.addActionListener(this);
	
	cancelButton = new JButton("Cancel");
	cancelButton.addActionListener(this);
	cancelButton.setActionCommand("CANCEL");
	
	planLabel = new JLabel("Plan:", SwingConstants.RIGHT);
	planLabel.setMinimumSize(new Dimension(75, 0));
	planDropdown = new JComboBox();
	
	planNames = userDao.getAllPlanNames();
	for (String name : planNames)
		{
		planDropdown.addItem(name);
		}

	userPlanLabel = new JLabel("Plan:", SwingConstants.RIGHT);
	userPlanLabel.setMinimumSize(new Dimension(75, 0));
	userPlanBox = new JTextField(10);
	
	panel1 = new JPanel();
	
	GroupLayout layout = new GroupLayout(panel1);
	panel1.setLayout(layout);

	layout.setAutoCreateGaps(true);
	layout.setAutoCreateContainerGaps(true);
	Component 	filler = Box.createRigidArea(new Dimension(10,10));
	Component 	filler2 = Box.createRigidArea(new Dimension(10,10));

	GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

	hGroup.addGroup(layout.createParallelGroup().
            addComponent(nameLabel).addComponent(emailLabel).addComponent(phoneNumberLabel)
            .addComponent(filler).addComponent(addressLabel)
            .addComponent(streetAddressLabel).addComponent(cityLabel).addComponent(stateLabel).addComponent(zipLabel));
	hGroup.addGroup(layout.createParallelGroup().
			addComponent(nameBox).addComponent(emailBox).addComponent(phoneNumberBox).addComponent(streetAddressBox)
			.addComponent(cityBox).addComponent(stateBox).addComponent(zipBox));
	hGroup.addGroup(layout.createParallelGroup().
			addComponent(userPlanLabel).addComponent(passwordLabel).addComponent(planLabel));
	hGroup.addGroup(layout.createParallelGroup().
            addComponent(userPlanBox).addComponent(passwordBox).addComponent(statusLabel).addComponent(isUserBox)
            .addComponent(isAdminBox).addComponent(planLabel).addComponent(planDropdown));
	layout.setHorizontalGroup(hGroup);

	GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(nameLabel).addComponent(nameBox).addComponent(userPlanLabel).addComponent(userPlanBox));
	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(emailLabel).addComponent(emailBox).addComponent(passwordLabel).addComponent(passwordBox));
	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(phoneNumberLabel).addComponent(phoneNumberBox));
	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
			addComponent(filler));
	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(addressLabel).addComponent(statusLabel));
	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(streetAddressLabel).addComponent(streetAddressBox).addComponent(isUserBox));
	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(cityLabel).addComponent(cityBox).addComponent(isAdminBox));
	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(stateLabel).addComponent(stateBox));
	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(zipLabel).addComponent(zipBox).addComponent(planLabel).addComponent(planDropdown));
	layout.setVerticalGroup(vGroup);
	
	buttonPanel = new JPanel();
	buttonPanel.add(saveButton);
	buttonPanel.add(cancelButton);
	
	cp = getContentPane();
	cp.add(panel1, BorderLayout.CENTER);
	cp.add(buttonPanel, BorderLayout.SOUTH);
	
	setupUserDialog();
	}
	
	
	
	public void	actionPerformed(ActionEvent ae)
	{
	if (ae.getActionCommand().equals("ADMIN_EDIT_SAVE"))
		{
		user.setName(nameBox.getText().trim());
		user.setPhoneNumber(phoneNumberBox.getText().trim());
		user.setStreetAddress(streetAddressBox.getText().trim());
		user.setCity(cityBox.getText().trim());
		user.setState(stateBox.getText().trim());
		user.setZipCode(Integer.parseInt(zipBox.getText().trim()));
		user.setAdmin(isAdminBox.isSelected());
		user.setUser(isUserBox.isSelected());
		user.setPlan((String)planDropdown.getSelectedItem());
		
		userDao.updateUser(user);
		
		parent.updateUserDisplay();
		
		dispose();
		}
	else if (ae.getActionCommand().equals("USER_EDIT_SAVE"))
		{
		user.setName(nameBox.getText().trim());
		user.setPhoneNumber(phoneNumberBox.getText().trim());
		user.setPassword(passwordBox.getText().trim());
		user.setStreetAddress(streetAddressBox.getText().trim());
		user.setCity(cityBox.getText().trim());
		user.setState(stateBox.getText().trim());
		user.setZipCode(Integer.parseInt(zipBox.getText().trim()));
		
		userDao.updateUser(user);
		
		dispose();
		}
	else if (ae.getActionCommand().equals("CREATE_USER"))
		{
		User	newUser;
		String	email, password, name, streetAddress, city, state, phoneNumber;
		int		zip;
		boolean	isAdmin, isUser;
		String	plan;
		
		newUser = new User();
		newUser.setEmail(emailBox.getText());
		newUser.setName(nameBox.getText());
		newUser.setStreetAddress(streetAddressBox.getText());
		newUser.setCity(cityBox.getText());
		newUser.setState(stateBox.getText());
		newUser.setPhoneNumber(phoneNumberBox.getText());
		newUser.setZipCode(Integer.parseInt(zipBox.getText()));
		newUser.setPassword("BobRoss");
		newUser.setAdmin(isAdminBox.isSelected());
		newUser.setUser(isUserBox.isSelected());
		newUser.setPlan((String)planDropdown.getSelectedItem());
		
		if (!userDao.userExists(newUser.getEmail()))
			{
			userDao.insertUser(newUser);
			parent.updateUserDisplay();
			dispose();
			}
		else
			{
			JOptionPane.showMessageDialog(null, "A user with this email already exists!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		
		}
	else if (ae.getActionCommand().equals("CANCEL"))
		{
		dispose();
		}
	}
	
	void	setupUserDialog()
	{
	Toolkit		tk;
	Dimension	d;

	tk = Toolkit.getDefaultToolkit();
	d = tk.getScreenSize();

	setSize(700, 350);
	setLocation(d.width/3, d.height/4);

//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//	getRootPane().setDefaultButton(saveButton);

	setVisible(true);
	}
}