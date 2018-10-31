package com.databaseProject.databaseProject;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.databaseProject.Pojos.Media;
import com.databaseProject.Pojos.Rental;
import com.databaseProject.Pojos.User;
import com.databaseProject.TableModels.AdminRentalInfoTableModel;
import com.databaseProject.TableModels.MediaInfoTableModel;
import com.databaseProject.TableModels.UserRentalInfoTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;

public class UserPanel extends JRootPane
					   implements ActionListener, MouseListener, ChangeListener
{
	JMenu	menuHistory;
	JMenu	menuEditProfile;
	JMenu	menuSearch;
	
	JTextField	searchBar;
	JButton		searchButton;
	JCheckBox	gamesCheck;
	JCheckBox	moviesCheck;
	JCheckBox	awardsCheck;
	JCheckBox	notPrevRentedCheck;
	JComboBox	searchByBox;
	JLabel		numRentalsAvailableLabel;
	
	JPanel		searchPanel;
	
	JPanel		rentalInfoPanel;
	JTable		rentalInfoTable;
	
	JTable		searchResultsTable;
	
	CardLayout	cardLayout;
	
	//JTable for displaying media information
	
	//test comment 2
	
	User		user;
	
	UserPanel()//User user)
	{
	Container	cp;
	
	
//	this.user = user;
	
	setJMenuBar(newMenuBar());
	
	searchPanel = createSearchPanel();
	rentalInfoPanel = createRentalInfoPanel();
	
	cardLayout = new CardLayout();
	
	cp = getContentPane();
	cp.setLayout(cardLayout);
	searchPanel.setBackground(Color.BLUE);
	cp.add(searchPanel, "SearchPanel");
	cp.add(rentalInfoPanel, "RentalInfoPanel");
	}
	
	
	JPanel	createSearchPanel()
	{
	JPanel		buttonPanel;
	JPanel		panel;
	JLabel		searchByLabel;
	JScrollPane	scrollPane;
	List<Media>	mediaList;
	DefaultListModel<Media>	mediaListModel;		
	MediaInfoTableModel	tableModel;
	JMenuItem	item;
	
	mediaList = new ArrayList<Media>();
	
	mediaListModel = new DefaultListModel<Media>();
	for (Media media : mediaList)
		{
		mediaListModel.addElement(media);
		}

	tableModel = new MediaInfoTableModel(mediaListModel);
	
	searchResultsTable = new JTable(tableModel);
	searchResultsTable.addMouseListener(this);
	searchResultsTable.setColumnModel(getMediaColumnModel());
	
	scrollPane = new JScrollPane(searchResultsTable);
	
	//setDefaultButton(searchButton);
	
	searchBar = new JTextField(10);
	
	searchButton = new JButton("Search");
	searchButton.addActionListener(this);
	searchButton.setActionCommand("SEARCH");
	
	gamesCheck = new JCheckBox("Games", true);
	gamesCheck.addChangeListener(this);
	moviesCheck = new JCheckBox("Movies", true);
	awardsCheck = new JCheckBox("Award winning", false);
	notPrevRentedCheck = new JCheckBox("Not previously rented", false);
	
	searchByBox = new JComboBox();
	searchByBox.addItem("Keyword");
	searchByBox.addItem("Actor");
	searchByBox.addItem("Director");
	searchByBox.addItem("Genre");
	
	searchByLabel = new JLabel("Search by: ", SwingConstants.RIGHT);
	searchByLabel.setMinimumSize(new Dimension(100, 0));
	
	numRentalsAvailableLabel = new JLabel("Number of Rentals Available: 0", SwingConstants.RIGHT);
	//updateNumRentalsAvailableLabel(user.getMaxNumRentals()-user.getCurrentNumRentals());
		
	buttonPanel = new JPanel();
	GroupLayout layout = new GroupLayout(buttonPanel);
	buttonPanel.setLayout(layout);

	layout.setAutoCreateGaps(true);

	layout.setAutoCreateContainerGaps(true);

	GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

	hGroup.addGroup(layout.createParallelGroup().
            addComponent(searchBar).addComponent(gamesCheck).addComponent(notPrevRentedCheck));
	hGroup.addGroup(layout.createParallelGroup().
			addComponent(searchByLabel).addComponent(moviesCheck).addComponent(awardsCheck));
	hGroup.addGroup(layout.createParallelGroup().
            addComponent(searchByBox));
	hGroup.addGroup(layout.createParallelGroup().
            addComponent(searchButton));
	layout.setHorizontalGroup(hGroup);

	GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(searchBar).addComponent(searchByBox).addComponent(searchByLabel).addComponent(searchButton));
	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(gamesCheck).addComponent(moviesCheck));
	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(notPrevRentedCheck).addComponent(awardsCheck));
	layout.setVerticalGroup(vGroup);
	
	panel = new JPanel();
	panel.add(buttonPanel, BorderLayout.NORTH);
	panel.add(scrollPane, BorderLayout.CENTER);
	
	return panel;
	}
	
	JPanel	createRentalInfoPanel()
	{
	JPanel	infoPanel;
	JLabel	titleLabel;
	JScrollPane	scrollPane;
	List<Rental>	rentalList;
	DefaultListModel<Rental>	rentalListModel;		
	UserRentalInfoTableModel	tableModel;
	JMenuItem	item;
	
	titleLabel = new JLabel(" Rental History");
	titleLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 45));
	
	// needs to be populated from the database
	rentalList = new ArrayList<Rental>();
	
	//This is a test Rental
	//Rental(String name, String email, String streetAddress, String city, String state, String zipCode,
	//String title, char mediaType, Date dateRented)
	
	rentalListModel = new DefaultListModel<Rental>();
	for (Rental rental : rentalList)
		{
		rentalListModel.addElement(rental);
		}

	tableModel = new UserRentalInfoTableModel(rentalListModel);
	
	rentalInfoTable = new JTable(tableModel);
	rentalInfoTable.addMouseListener(this);
	rentalInfoTable.setColumnModel(getRentalColumnModel());

	scrollPane = new JScrollPane(rentalInfoTable);
	
	infoPanel = new JPanel();
	infoPanel.setLayout(new BorderLayout());
	infoPanel.add(titleLabel, BorderLayout.NORTH);
	infoPanel.add(scrollPane, BorderLayout.CENTER);
	
	return infoPanel;
	}
	
	TableColumnModel getRentalColumnModel()
	{
	TableColumn              col;
	DefaultTableColumnModel  colModel;

	colModel = new DefaultTableColumnModel();

	col = new TableColumn(0);    // 0 is the column index
	col.setPreferredWidth(10);
	col.setMinWidth(10);
	col.setHeaderValue("Date Rented");
	colModel.addColumn(col);

	col = new TableColumn(1);    // 1 is the column index
	col.setPreferredWidth(10);
	col.setMinWidth(10);
	col.setHeaderValue("Date Returned");
	colModel.addColumn(col);

	col = new TableColumn(2);    // 2 is the column index
	col.setPreferredWidth(50);
	col.setMinWidth(50);
	col.setHeaderValue("Title");
	colModel.addColumn(col);
	
	col = new TableColumn(3);    // 3 is the column index
	col.setPreferredWidth(10);
	col.setMinWidth(10);
	col.setHeaderValue("Media Type");
	colModel.addColumn(col);
	
	col = new TableColumn(4);    // 4 is the column index
	col.setPreferredWidth(20);
	col.setMinWidth(20);
	col.setHeaderValue("Genre");
	colModel.addColumn(col);

	col = new TableColumn(5);    // 4 is the column index
	col.setPreferredWidth(10);
	col.setMinWidth(10);
	col.setHeaderValue("Release Date");
	colModel.addColumn(col);
	
	return colModel;
	}
	
	TableColumnModel getMediaColumnModel()
	{
	TableColumn              col;
	DefaultTableColumnModel  colModel;

	colModel = new DefaultTableColumnModel();

	col = new TableColumn(0);    // 0 is the column index
	col.setPreferredWidth(50);
	col.setMinWidth(50);
	col.setHeaderValue("Title");
	colModel.addColumn(col);

	col = new TableColumn(1);    // 1 is the column index
	col.setPreferredWidth(20);
	col.setMinWidth(20);
	col.setHeaderValue("Media Type");
	colModel.addColumn(col);

	col = new TableColumn(2);    // 2 is the column index
	col.setPreferredWidth(20);
	col.setMinWidth(20);
	col.setHeaderValue("Genre");
	colModel.addColumn(col);
	
	col = new TableColumn(3);    // 3 is the column index
	col.setPreferredWidth(50);
	col.setMinWidth(50);
	col.setHeaderValue("Release Date");
	colModel.addColumn(col);

	col = new TableColumn(4);    // 4 is the column index
	col.setPreferredWidth(20);
	col.setMinWidth(20);
	col.setHeaderValue("Inventory");
	colModel.addColumn(col);
	
	return colModel;
	}
	
	
	public void stateChanged(ChangeEvent e)
	{
	
	}
	
	public void	actionPerformed(ActionEvent ae)
	{
	if (ae.getActionCommand().equals("SEARCH"))
		{
		List<Media>	mediaList;
		DefaultListModel<Media>	mediaListModel;		
		MediaInfoTableModel	tableModel;
		
		// Get list from the database
		mediaList = new ArrayList<Media>();
		
		mediaListModel = new DefaultListModel<Media>();
		for (Media media : mediaList)
			{
			mediaListModel.addElement(media);
			}

		tableModel = new MediaInfoTableModel(mediaListModel);
		searchResultsTable.setModel(tableModel);
		}
	}
	

	public void mouseClicked(MouseEvent e)
	{
	if (e.getSource() == menuHistory)
		{
		List<Rental>	rentalList;
		DefaultListModel<Rental>	rentalListModel;		
		UserRentalInfoTableModel	tableModel;
		
		rentalList = new ArrayList<Rental>();
		
		//Get rental list from the database (needed in case they rent something new)
		rentalListModel = new DefaultListModel<Rental>();
		for (Rental rental : rentalList)
			{
			rentalListModel.addElement(rental);
			}

		tableModel = new UserRentalInfoTableModel(rentalListModel);
		rentalInfoTable.setModel(tableModel);
		
		cardLayout.show(getContentPane(), "RentalInfoPanel");
		
		//new UserMediaDialog(new Media());
		}
	else if (e.getSource() == menuEditProfile)
		{
		new UserDialog(user, false);
		//new UserDialog(new User(), false);
		}
	else if (e.getSource() == menuSearch)
		{
		cardLayout.show(getContentPane(), "SearchPanel");
		//new UserDialog(user, false);
		//new UserDialog(new User(), false);
		}
	else if (e.getSource() == rentalInfoTable)
		{
		int		rowIndex;
		int		colIndex;
		UserRentalInfoTableModel	tableModel;
		Rental	selectedRental;
		Point	clickLocation;
		int		inventory;
		
		clickLocation = e.getPoint();
		
		rowIndex = rentalInfoTable.rowAtPoint(clickLocation);
		colIndex = rentalInfoTable.columnAtPoint(clickLocation);
		
		tableModel = (UserRentalInfoTableModel)(rentalInfoTable.getModel());
		selectedRental = tableModel.getRentalAt(rowIndex);
		
		if (e.getClickCount() == 2)
			{
			new UserMediaDialog(selectedRental.getMedia());
			}
		}
	else if (e.getSource() == searchResultsTable)
		{
		int		rowIndex;
		int		colIndex;
		MediaInfoTableModel	tableModel;
		Media	selectedRental;
		Point	clickLocation;
		int		inventory;
		
		clickLocation = e.getPoint();
		
		rowIndex = searchResultsTable.rowAtPoint(clickLocation);
		colIndex = searchResultsTable.columnAtPoint(clickLocation);
		
		tableModel = (MediaInfoTableModel)(searchResultsTable.getModel());
		selectedRental = tableModel.getMediaAt(rowIndex);
		
		if (e.getClickCount() == 2)
			{
			new UserMediaDialog(selectedRental);
			}
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	void	updateNumRentalsAvailableLabel(int numAvailable)
	{
	numRentalsAvailableLabel.setText("Number of Rentals Available: " + numAvailable);
	}
	
	
	private	 JMenuBar newMenuBar()
	{
	JMenuBar	menuBar;

	menuBar = new JMenuBar();
	
	menuSearch = new JMenu("Search Movies & Games");
	menuSearch.addMouseListener(this);
	menuBar.add(menuSearch);
	
	menuHistory = new JMenu("Show History");
	menuHistory.addMouseListener(this);
	menuBar.add(menuHistory);
	
	menuEditProfile = new JMenu("Edit Profile");
	menuEditProfile.addMouseListener(this);
	menuBar.add(menuEditProfile);
	
	menuHistory.setEnabled(true);
	menuEditProfile.setEnabled(true);
	menuSearch.setEnabled(true);
	
	return menuBar;
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