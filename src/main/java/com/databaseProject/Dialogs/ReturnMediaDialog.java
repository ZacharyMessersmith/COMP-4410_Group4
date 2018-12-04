package com.databaseProject.Dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;
import java.util.List;

import com.databaseProject.DAOs.MediaDAO;
import com.databaseProject.DAOs.UserDAO;
import com.databaseProject.Pojos.Media;
import com.databaseProject.Pojos.User;
import com.databaseProject.databaseProject.AdminPanel;
import com.databaseProject.databaseProject.MainJFrame;

public class ReturnMediaDialog extends JDialog
						 implements ActionListener
{
	JComboBox	emailDropdown;
//	JTextField	inventoryBox;
	JButton		updateButton;
	JButton		cancelButton;
	
	UserDAO		userDao;
	MediaDAO	mediaDao;
	
	Media		media;
	AdminPanel	parent;
	
	public ReturnMediaDialog(Media media, AdminPanel parent)
	{
	JLabel		emailLabel;
//	JLabel		inventoryLabel;
	JPanel		panel1;
	JPanel		buttonPanel;
	Container	cp;
	List<User> 	userList;
	List<String>	emailList;
	
	userDao = new UserDAO();
	mediaDao = new MediaDAO();
	
	this.media = media;
	this.parent = parent;
	
	emailLabel = new JLabel("User returning the media:");
//	inventoryLabel = new JLabel("Number of copies in stock:");
	
	emailDropdown = new JComboBox();
	userList = userDao.getAllUsers();

	for (User user : userList)
		{
		emailDropdown.addItem(user.getEmail());
		}
	
//	inventoryBox = new JTextField(10);
	
	updateButton = new JButton("Process Return");
	updateButton.addActionListener(this);
	updateButton.setActionCommand("UPDATE");
	
	cancelButton = new JButton("Cancel");
	cancelButton.addActionListener(this);
	cancelButton.setActionCommand("CANCEL");
	
	panel1 = new JPanel();
	
	GroupLayout layout = new GroupLayout(panel1);
	panel1.setLayout(layout);

	layout.setAutoCreateGaps(true);

	layout.setAutoCreateContainerGaps(true);

	GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

	hGroup.addGroup(layout.createParallelGroup().
            addComponent(emailLabel));//.addComponent(inventoryLabel));
	hGroup.addGroup(layout.createParallelGroup().
            addComponent(emailDropdown));//.addComponent(inventoryBox));
	layout.setHorizontalGroup(hGroup);

	GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(emailLabel).addComponent(emailDropdown));
//	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
//            addComponent(inventoryLabel).addComponent(inventoryBox));
	layout.setVerticalGroup(vGroup);
	
	buttonPanel = new JPanel();
	buttonPanel.add(updateButton);
	buttonPanel.add(cancelButton);
	
	cp = getContentPane();
	cp.add(panel1, BorderLayout.CENTER);
	cp.add(buttonPanel, BorderLayout.SOUTH);
	
	setupReturnMediaDialog(media);
	System.out.println("Done.");
	}
	
	public void	actionPerformed(ActionEvent ae)
	{
	if (ae.getActionCommand().equals("UPDATE"))
		{
		User	user;
		String	email;
		
		email = (String)emailDropdown.getSelectedItem();
		
		user = userDao.getUser(email);
		if (user.getNumRentalsAvailable() < user.getMaxNumRentals())
			{
			user.setNumRentalsAvailable(user.getNumRentalsAvailable() + 1);
			userDao.updateNumberOfAvailableRentalsForUser(user);	
			}
		
		//media.setNumCopiesAvailable(Integer.parseInt(inventoryBox.getText().trim()));
		media.setNumCopiesAvailable(media.getNumCopiesAvailable() + 1);
		mediaDao.updateMediaInventory(media.getMediaID(), media.getNumCopiesAvailable());
		parent.showMediaBox.setSelectedItem(parent.showMediaBox.getSelectedItem());
		dispose();
		}
	else if (ae.getActionCommand().equals("CANCEL"))
		dispose();
	}
	
	void	setupReturnMediaDialog(Media media)
	{
	Toolkit		tk;
	Dimension	d;

	tk = Toolkit.getDefaultToolkit();
	d = tk.getScreenSize();

	setSize(500, 200);
	setLocation(d.width/3, d.height/4);

//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	getRootPane().setDefaultButton(updateButton);

	setTitle("Process Return of " + media.getTitle());

	setVisible(true);
	}
}