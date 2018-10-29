package com.databaseProject.databaseProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;
import javax.swing.JDialog;

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
	
	UserDialog()
	{
	initializeBaseUserDialog();
	setTitle("Create User");
	
	saveButton.setActionCommand("CREATE_USER");
	passwordBox.setVisible(false);
	passwordLabel.setVisible(false);
	userPlanBox.setVisible(false);
	userPlanLabel.setVisible(false);
	}
	
	UserDialog(User user, boolean isAdmin)
	{
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
	
	//Should be populated from the database
	planDropdown.addItem("Plan 1");
	planDropdown.addItem("Plan 2");
	planDropdown.addItem("Plan 3");
	
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
		user.setPassword(passwordBox.getText().trim());
		user.setStreetAddress(streetAddressBox.getText().trim());
		user.setCity(cityBox.getText().trim());
		user.setState(stateBox.getText().trim());
		user.setZipCode(Integer.parseInt(zipBox.getText().trim()));
		user.setAdmin(isAdminBox.isSelected());
		user.setUser(isUserBox.isSelected());
		user.setPlan((String)planDropdown.getSelectedItem());
		
		//update where the email = user.getEmail()
		
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
		
		//update the database where the email = user.getEmail()
		
		dispose();
		}
	else if (ae.getActionCommand().equals("CREATE_USER"))
		{
		User	newUser;
		String	email, password, name, streetAddress, city, state, phoneNumber;
		int		zip;
		boolean	isAdmin, isUser;
		String	plan;
		
		email = emailBox.getText();
		name = nameBox.getText();
		streetAddress = streetAddressBox.getText();
		city = cityBox.getText();
		state = stateBox.getText();
		phoneNumber = phoneNumberBox.getText();
		
	//probably need this in a try-catch, or at least do some form of validation
		zip = Integer.parseInt(zipBox.getText());
		
		// look into how to make one of them be selected at all times
				// if I figure it out, implement that on the UserPanel too
		isAdmin = isAdminBox.isSelected();
		isUser = isUserBox.isSelected();
		
		password = email; //default password
		
		//insert into the database
		newUser = new User(email, name, phoneNumber, password, streetAddress, city, state, zip, isAdmin, isUser);
		
		plan = (String)planDropdown.getSelectedItem();
		// Insert the email/plan combo into Has_Plan
		
		dispose();
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