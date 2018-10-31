package com.databaseProject.TableModels;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;

import com.databaseProject.Pojos.Media;

public class MediaInfoTableModel extends AbstractTableModel
{
	//String[]	columnNames = {"Name", "Email", "Phone Number", "Address", "Plan"};
	
	DefaultListModel<Media>	mediaList;

	public MediaInfoTableModel(DefaultListModel mediaList)
	{
	this.mediaList = mediaList;// new DefaultListModel<User>();
	}
	
	public int getColumnCount()
	{
	return 6;
	}
	
	public Media getMediaAt(int rowIndex)
	{
	return (Media)getValueAt(rowIndex, 5);
	}
	
	public int getRowCount()
	{
	return mediaList.getSize();
	}
	
	public Media getDataAt(int index) 
	{                                  
	return (Media)mediaList.getElementAt(index);
	}
	
	public Object getValueAt(int row, int col)
	{
	Media media;
	media = getDataAt(row);

	if (col == 0)
	    return media.getTitle();
	else if (col == 1)
		{
	    if (media.getMediaType() == 'm')
	    	return "Movie";
	    else
	    	return "Game";
		}
	else if (col == 2)
	    return media.getGenre();
	else if (col == 3)
		return media.getReleaseDate();
	else if (col == 4)
	    return media.getNumCopiesAvailable();
	else if (col == 5)
		return media;
	else
	    return null;
	}


}