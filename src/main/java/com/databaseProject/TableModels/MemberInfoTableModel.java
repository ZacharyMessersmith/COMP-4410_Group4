package com.databaseProject.TableModels;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;

import com.databaseProject.Pojos.User;

public class MemberInfoTableModel extends AbstractTableModel
{
	//String[]	columnNames = {"Name", "Email", "Phone Number", "Address", "Plan"};
	
	DefaultListModel<User>	userList;

	public MemberInfoTableModel(DefaultListModel userList)
	{
	this.userList = userList;// new DefaultListModel<User>();
	}
	
	public int getColumnCount()
	{
	return 6;
	}
	
	public User	getUserAt(int rowIndex)
	{
	return (User)getValueAt(rowIndex, 5);
	}
	
	public int getRowCount()
	{
	return userList.getSize();
	}
	
	public User getDataAt(int index) 
	{                                  
	return (User)userList.getElementAt(index);
	}
	
	public Object getValueAt(int row, int col)
	{
	User user;
	user = getDataAt(row);

	if (col == 0)
	    return user.getName();
	else if (col == 1)
	    return user.getEmail();
	else if (col == 2)
	    return user.getPhoneNumber();
	else if (col == 3)
		return user.getStreetAddress() + ", " + user.getCity() + ", " + user.getState() + " " + user.getZipCode();
	else if (col == 4)
	    return user.getPlan();
	else if (col == 5)
		return user;
	else
	    return null;
	}


}