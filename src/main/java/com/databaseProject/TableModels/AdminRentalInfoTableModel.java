package com.databaseProject.TableModels;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;

import com.databaseProject.Pojos.Rental;

public class AdminRentalInfoTableModel extends AbstractTableModel
{
	//String[]	columnNames = {"Name", "Email", "Phone Number", "Address", "Plan"};
	
	DefaultListModel<Rental>	rentalList;

	public AdminRentalInfoTableModel(DefaultListModel rentalList)
	{
	this.rentalList = rentalList;// new DefaultListModel<User>();
	}
	
	public int getColumnCount()
	{
	return 7;
	}
	
	public Rental getRentalAt(int rowIndex)
	{
	return (Rental)getValueAt(rowIndex, 6);
	}
	
	public int getRowCount()
	{
	return rentalList.getSize();
	}
	
	public Rental getDataAt(int index) 
	{                                  
	return (Rental)rentalList.getElementAt(index);
	}
	
	public Object getValueAt(int row, int col)
	{
	Rental rental;
	rental = getDataAt(row);

	if (col == 0)
		return rental.getDateRented();
	else if (col == 1)
	    return rental.getUser().getName();
	else if (col == 2)
		return rental.getUser().getEmail();
	else if (col == 3)
		return rental.getUser().getStreetAddress() + ", " + rental.getUser().getCity() + ", " + rental.getUser().getState() + " " + rental.getUser().getZipCode();
	else if (col == 4)
		return rental.getMedia().getTitle();
	else if (col == 5)
		{
	    if (rental.getMedia().getMediaType() == 'm')
	    	return "Movie";
	    else
	    	return "Game";
		}
	else if (col == 6)
		return rental;
	else
	    return null;
	}


}