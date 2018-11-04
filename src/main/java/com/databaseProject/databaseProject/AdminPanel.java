package com.databaseProject.databaseProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.databaseProject.DAOs.MediaDAO;
import com.databaseProject.DAOs.RentalDAO;
import com.databaseProject.DAOs.UserDAO;
import com.databaseProject.Dialogs.AdminMediaDialog;
import com.databaseProject.Dialogs.UserDialog;
import com.databaseProject.Dialogs.UserMediaDialog;
import com.databaseProject.Pojos.Media;
import com.databaseProject.Pojos.Rental;
import com.databaseProject.Pojos.User;
import com.databaseProject.TableModels.AdminRentalInfoTableModel;
import com.databaseProject.TableModels.MediaInfoTableModel;
import com.databaseProject.TableModels.MemberInfoTableModel;

public class AdminPanel extends JRootPane
						implements ActionListener, MouseListener
{	
	JMenuItem	menuCreateUser;
	JMenuItem	menuCreateMedia;
	JMenuItem	menuViewMembers;
	JMenuItem	menuViewMedia;
	JMenuItem	menuViewRentals;
	
	JComboBox	showMediaBox;
	
	JPanel		memberInfoPanel;
	JTable		memberInfoTable;
	JPopupMenu	memberInfoPopup;
	
	JPanel		mediaInfoPanel;
	JTable		mediaInfoTable;
	JPopupMenu	mediaInfoPopup;
	
	JPanel		rentalInfoPanel;
	JTable		rentalInfoTable;
	
	CardLayout	cardLayout;
	
	MediaDAO	mediaDao;
	UserDAO		userDao;
	RentalDAO	rentalDao;

	AdminPanel()
	{
	Container	cp;
	
	mediaDao = new MediaDAO();
	userDao = new UserDAO();
	rentalDao = new RentalDAO(); 
	setJMenuBar(newMenuBar());
	
	memberInfoPanel = createMemberInfoPanel();
	mediaInfoPanel = createMediaInfoPanel();
	rentalInfoPanel =  createRentalInfoPanel();
	
	cp = getContentPane();
	cardLayout = new CardLayout();
	cp.setLayout(cardLayout);
	cp.add(memberInfoPanel, "MemberInfoPanel");//BorderLayout.CENTER);
	cp.add(mediaInfoPanel, "MediaInfoPanel");//BorderLayout.CENTER);
	cp.add(rentalInfoPanel, "RentalInfoPanel");//BorderLayout.CENTER);
	}
	
	public void mouseClicked(MouseEvent e)
	{
	if (e.getSource() == memberInfoTable)
		{
		int		rowIndex;
		int		colIndex;
		MemberInfoTableModel	tableModel;
		User	selectedUser;
		Point	clickLocation;
		
		clickLocation = e.getPoint();
		
		rowIndex = memberInfoTable.rowAtPoint(clickLocation);
		colIndex = memberInfoTable.columnAtPoint(clickLocation);
		
		tableModel = (MemberInfoTableModel)(memberInfoTable.getModel());
		selectedUser = tableModel.getUserAt(rowIndex);
		
		if (e.getClickCount() == 2)
			new UserDialog(selectedUser, true);
		else if (e.getButton() == MouseEvent.BUTTON3)
			{
			memberInfoTable.setRowSelectionInterval(rowIndex, rowIndex);
			memberInfoPopup.show(this, clickLocation.x+10, clickLocation.y+100);
			}
		}
	else if (e.getSource() == mediaInfoTable)
		{
		int		rowIndex;
		int		colIndex;
		MediaInfoTableModel	tableModel;
		Media	selectedMedia;
		Point	clickLocation;
		int		inventory;
		
		clickLocation = e.getPoint();
		
		rowIndex = mediaInfoTable.rowAtPoint(clickLocation);
		colIndex = mediaInfoTable.columnAtPoint(clickLocation);
		
		tableModel = (MediaInfoTableModel)(mediaInfoTable.getModel());
		selectedMedia = tableModel.getMediaAt(rowIndex);
		
		if (e.getClickCount() == 2)
			{
			if (colIndex == 4)
				{
				UIManager.put("OptionPane.minimumSize", new Dimension(500,50)); 
				// Should be in a try-catch
				String inv = JOptionPane.showInputDialog(null, "Please enter the number of copies in stock:", "Update Inventory of " + selectedMedia.getTitle(), JOptionPane.PLAIN_MESSAGE);
				if (inv != null)
					{
					inventory =	Integer.parseInt(inv);
					// need to update the database here
					selectedMedia.setNumCopiesAvailable(inventory);
					}
				}
			else
				new UserMediaDialog(selectedMedia);
			}
		else if (e.getButton() == MouseEvent.BUTTON3)
			{
			mediaInfoTable.setRowSelectionInterval(rowIndex, rowIndex);
			mediaInfoPopup.show(this, clickLocation.x+10, clickLocation.y+100);
			}
		}
	
	}
	
	//create functions to show/hide the proper things for each view
	public void actionPerformed(ActionEvent ae)
	{
	if (ae.getActionCommand().equals("CREATE_USER"))
		{
		new UserDialog();
		}
	else if (ae.getActionCommand().equals("EDIT_USER"))
		{
		MemberInfoTableModel	tableModel;
		User					selectedUser;
		int						selectedRow;
		
		selectedRow = memberInfoTable.getSelectedRow();
		tableModel = (MemberInfoTableModel)(memberInfoTable.getModel());
		selectedUser = tableModel.getUserAt(selectedRow);
		new UserDialog(selectedUser, true);
		}
	else if (ae.getActionCommand().equals("DELETE_USER"))
		{}
	else if (ae.getActionCommand().equals("CREATE_MEDIA"))
		{
		new AdminMediaDialog();
		}
	else if (ae.getActionCommand().equals("EDIT_MEDIA"))
		{
		MediaInfoTableModel		tableModel;
		Media					selectedMedia;
		int						selectedRow;
		
		selectedRow = mediaInfoTable.getSelectedRow();
		tableModel = (MediaInfoTableModel)(mediaInfoTable.getModel());
		selectedMedia = tableModel.getMediaAt(selectedRow);
		new AdminMediaDialog(selectedMedia);
		}
	else if (ae.getActionCommand().equals("DELETE_MEDIA"))
		{}
	
	else if (ae.getActionCommand().equals("VIEW_MEMBERS"))
		{
		cardLayout.show(getContentPane(), "MemberInfoPanel");
		}
	else if (ae.getActionCommand().equals("VIEW_MEDIA"))
		{
		cardLayout.show(getContentPane(), "MediaInfoPanel");
		}
	else if (ae.getActionCommand().equals("VIEW_RENTALS"))
		{
		cardLayout.show(getContentPane(), "RentalInfoPanel");
		}
	else if (ae.getActionCommand().equals("UPDATE_MEDIA_VIEW"))
		{
		if (showMediaBox.getSelectedItem().equals("All"))
			{
			// need a database call to get a list of all of the media from the database
			// Use that list to update the TableModel
			System.out.println("Show all");
			}
		else 
			{
			// need a database call to get a list of top ten rentals in the past month
			// Use that list to update the tableModel
			System.out.println("Show top 10");
			}
		}

	}
	
	JPanel	createMemberInfoPanel()
	{
	JPanel	infoPanel;
	JLabel	titleLabel;
	JScrollPane	scrollPane;
	List<User>	userList;
	DefaultListModel<User>	userListModel;		
	MemberInfoTableModel	tableModel;
	JMenuItem	item;	
	
	titleLabel = new JLabel(" Members");
	titleLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 45));
	
	userList = userDao.getAllUsers();
	
	
	userListModel = new DefaultListModel<User>();
	for (User user : userList)
		{
		userListModel.addElement(user);
		}

	tableModel = new MemberInfoTableModel(userListModel);
	
	memberInfoTable = new JTable(tableModel);
	memberInfoTable.addMouseListener(this);
	memberInfoTable.setColumnModel(getMemberColumnModel());
	
	memberInfoPopup = new JPopupMenu();
	item = new JMenuItem("Edit");
	item.addActionListener(this);
	item.setActionCommand("EDIT_USER");
	memberInfoPopup.add(item);
	item = new JMenuItem("Delete");
	item.addActionListener(this);
	item.setActionCommand("DELETE_USER");
	memberInfoPopup.add(item);
	
	scrollPane = new JScrollPane(memberInfoTable);
	
	infoPanel = new JPanel();
	infoPanel.setLayout(new BorderLayout());
	infoPanel.add(titleLabel, BorderLayout.NORTH);
	infoPanel.add(scrollPane, BorderLayout.CENTER);
	
	return infoPanel;
	}
	
	JPanel	createMediaInfoPanel()
	{
	JPanel	infoPanel;
	JPanel	titlePanel;
	JPanel	spacingPanel;
	JLabel	titleLabel;
	JLabel	showLabel;
	JScrollPane	scrollPane;
	List<Media>	mediaList;
	DefaultListModel<Media>	mediaListModel;		
	MediaInfoTableModel	tableModel;
	JMenuItem	item;
	
	titleLabel = new JLabel(" Media");
	titleLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 45));
	
	showLabel = new JLabel("Show:");
	showMediaBox = new JComboBox();
	showMediaBox.addItem("All");
	showMediaBox.addItem("Top 10");
	showMediaBox.addActionListener(this);
	showMediaBox.setActionCommand("UPDATE_MEDIA_VIEW");
	
	// needs to be populated from the database
	mediaList = mediaDao.getAllMedia();//new ArrayList<Media>();
	
	mediaListModel = new DefaultListModel<Media>();
	for (Media media : mediaList)
		{
		mediaListModel.addElement(media);
		}

	tableModel = new MediaInfoTableModel(mediaListModel);
	
	mediaInfoTable = new JTable(tableModel);
	mediaInfoTable.addMouseListener(this);
	mediaInfoTable.setColumnModel(getMediaColumnModel());
	
	mediaInfoPopup = new JPopupMenu();
	item = new JMenuItem("Edit");
	item.addActionListener(this);
	item.setActionCommand("EDIT_MEDIA");
	mediaInfoPopup.add(item);
	item = new JMenuItem("Delete");
	item.addActionListener(this);
	item.setActionCommand("DELETE_MEDIA");
	mediaInfoPopup.add(item);
	
	scrollPane = new JScrollPane(mediaInfoTable);
	
	titlePanel = new JPanel();
	titlePanel.setLayout(new BorderLayout());
	spacingPanel = new JPanel();
	spacingPanel.add(showLabel);
	spacingPanel.add(showMediaBox);
	spacingPanel.setMinimumSize(new Dimension(200, 100));
	titlePanel.add(titleLabel, BorderLayout.CENTER);
	titlePanel.add(spacingPanel, BorderLayout.EAST);
	
	infoPanel = new JPanel();
	infoPanel.setLayout(new BorderLayout());
	infoPanel.add(titlePanel, BorderLayout.NORTH);
	infoPanel.add(scrollPane, BorderLayout.CENTER);
	
	return infoPanel;
	}
	
	JPanel	createRentalInfoPanel()
	{
	JPanel	infoPanel;
	JLabel	titleLabel;
	JScrollPane	scrollPane;
	List<Rental>	rentalList;
	DefaultListModel<Rental>	rentalListModel;		
	AdminRentalInfoTableModel	tableModel;
	JMenuItem	item;
	
	titleLabel = new JLabel(" Rental Information");
	titleLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 45));
	
	// needs to be populated from the database
	rentalList = rentalDao.getRentalsWithinLast24Hours();
	
	rentalListModel = new DefaultListModel<Rental>();
	for (Rental rental : rentalList)
		{
		rentalListModel.addElement(rental);
		}

	tableModel = new AdminRentalInfoTableModel(rentalListModel);
	
	rentalInfoTable = new JTable(tableModel);
	rentalInfoTable.setColumnModel(getRentalColumnModel());

	scrollPane = new JScrollPane(rentalInfoTable);
	
	infoPanel = new JPanel();
	infoPanel.setLayout(new BorderLayout());
	infoPanel.add(titleLabel, BorderLayout.NORTH);
	infoPanel.add(scrollPane, BorderLayout.CENTER);
	
	return infoPanel;
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
	
	TableColumnModel getRentalColumnModel()
	{
	TableColumn              col;
	DefaultTableColumnModel  colModel;

	colModel = new DefaultTableColumnModel();

	col = new TableColumn(0);    // 1 is the column index
	col.setPreferredWidth(5);
	col.setMinWidth(5);
	col.setHeaderValue("Date Rented");
	colModel.addColumn(col);
	
	col = new TableColumn(1);    // 0 is the column index
	col.setPreferredWidth(35);
	col.setMinWidth(35);
	col.setHeaderValue("Name");
	colModel.addColumn(col);
	
	col = new TableColumn(2);    // 2 is the column index
	col.setPreferredWidth(20);
	col.setMinWidth(20);
	col.setHeaderValue("Email");
	colModel.addColumn(col);

	col = new TableColumn(3);    // 3 is the column index
	col.setPreferredWidth(50);
	col.setMinWidth(50);
	col.setHeaderValue("Shipping Address");
	colModel.addColumn(col);
	
	col = new TableColumn(4);    // 4 is the column index
	col.setPreferredWidth(50);
	col.setMinWidth(50);
	col.setHeaderValue("Title");
	colModel.addColumn(col);

	col = new TableColumn(5);    // 5 is the column index
	col.setPreferredWidth(7);
	col.setMinWidth(7);
	col.setHeaderValue("Media Type");
	colModel.addColumn(col);
	
	return colModel;
	}
	
	
	TableColumnModel getMemberColumnModel()
	{
	TableColumn              col;
	DefaultTableColumnModel  colModel;

	colModel = new DefaultTableColumnModel();

	col = new TableColumn(0);    // 0 is the column index
	col.setPreferredWidth(50);
	col.setMinWidth(50);
	col.setHeaderValue("Name");
	colModel.addColumn(col);

	col = new TableColumn(1);    // 1 is the column index
	col.setPreferredWidth(20);
	col.setMinWidth(20);
	col.setHeaderValue("Email");
	colModel.addColumn(col);

	col = new TableColumn(2);    // 2 is the column index
	col.setPreferredWidth(20);
	col.setMinWidth(20);
	col.setHeaderValue("Phone Number");
	colModel.addColumn(col);
	
	col = new TableColumn(3);    // 3 is the column index
	col.setPreferredWidth(50);
	col.setMinWidth(50);
	col.setHeaderValue("Addresses");
	colModel.addColumn(col);

	col = new TableColumn(4);    // 4 is the column index
	col.setPreferredWidth(20);
	col.setMinWidth(20);
	col.setHeaderValue("Plan");
	colModel.addColumn(col);
	
	return colModel;
	}
	
	
	
	private	 JMenuBar newMenuBar()
	{
	JMenuBar	menuBar;
	JMenu		subMenu;


	menuBar = new JMenuBar();

	subMenu = new JMenu("Create");

	menuCreateUser = newMenuItem("Create User", "CREATE_USER", this, "Create a new user. ");
	menuCreateMedia = newMenuItem("Create Media", "CREATE_MEDIA", this, "Create a new game or movie. ");
	menuViewMembers = newMenuItem("Members", "VIEW_MEMBERS", this, "View members. ");
	menuViewMedia = newMenuItem("Media", "VIEW_MEDIA", this, "View games and movies. ");
	menuViewRentals = newMenuItem("Rentals", "VIEW_RENTALS", this, "View rentals made in the past 24 hours. ");

	subMenu.add(menuCreateUser);
	subMenu.add(menuCreateMedia);

	menuBar.add(subMenu);

	subMenu = new JMenu("View");
	subMenu.add(menuViewMembers);
	subMenu.add(menuViewMedia);
	subMenu.add(menuViewRentals);

	menuBar.add(subMenu);
	
	menuCreateUser.setEnabled(true);
	menuCreateMedia.setEnabled(true);
	menuViewMembers.setEnabled(true);
	menuViewMedia.setEnabled(true);
	menuViewRentals.setEnabled(true);

	return menuBar;
	}

	private	JMenuItem	newMenuItem(String label, String actionCommand, ActionListener menuListener, String toolTipText)
	{
	JMenuItem	m;

	m = new JMenuItem(label);

	m.getAccessibleContext().setAccessibleDescription(toolTipText);
	m.setToolTipText(toolTipText);
	m.setActionCommand(actionCommand);
	m.addActionListener(menuListener);

	return	m;
	}

	public void mouseEntered(MouseEvent e)
	{}

	public void mouseExited(MouseEvent e)
	{}

	public void mousePressed(MouseEvent e)
	{}

	public void mouseReleased(MouseEvent e)
	{}
}