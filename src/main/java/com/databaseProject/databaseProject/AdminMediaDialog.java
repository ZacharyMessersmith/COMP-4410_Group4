package com.databaseProject.databaseProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//Since this will be used by the admin, we should give them a default password that they can change later.

public class AdminMediaDialog extends JDialog
						 implements ActionListener, ChangeListener
{
	JTextField	titleBox;
	JTextField	numCopiesBox;
	JTextField	releaseDateBox;
	JTextField	genreBox;
	
	ButtonGroup		mediaTypeGroup;
	JRadioButton	gameOption;
	JRadioButton	movieOption;
	
	JButton		saveButton;
	JButton		cancelButton;
	
	JTextField	versionBox;
	JTextField	platformBox;
	
	JList	awardsList;
	JList	castList;
	JList	directorList;
	JList	sequelsList;
	
	JPanel	gamePanel;
	JPanel	moviePanel;
	
	Media	media;
	
	AdminMediaDialog()
	{
	setupBaseMediaDialog();
	saveButton.setActionCommand("CREATE_MEDIA");
	setTitle("Create Media");
	}
	
	
	AdminMediaDialog(Media media)
	{
	setupBaseMediaDialog();
	this.media = media;
	saveButton.setActionCommand("EDIT_MEDIA");
	setTitle("Edit Media");
	
	titleBox.setText(media.getTitle());
	numCopiesBox.setText(Integer.toString(media.getNumCopiesAvailable()));
	//Need to figure out how to convert from a date to a string
	//releaseDateBox.setText(media.getReleaseDate());
	genreBox.setText(media.getGenre());
	
	// These might need changed based on what is stored in the database
	if (media.getMediaType() == 'g') 
		{
		gameOption.setSelected(true);
		versionBox.setText(Float.toString(media.getVersion()));
		platformBox.setText(media.getPlatform());
		}
	else
		{
		movieOption.setSelected(true);
		
		}
	
	}
	
	void	setupBaseMediaDialog()
	{
	JLabel	titleLabel;
	JLabel	numCopiesLabel;
	JLabel	releaseDateLabel;
	JLabel	genreLabel;
	JPanel	mainPanel;
	JPanel	buttonPanel;
	Container	cp;
	
	titleLabel = new JLabel("Title:");
	numCopiesLabel = new JLabel("Copies Available:");
	releaseDateLabel = new JLabel("Release Date:");
	genreLabel = new JLabel("Genre:");
	
	titleBox = new JTextField(10);
	numCopiesBox = new JTextField(10);
	releaseDateBox = new JTextField(10);
	genreBox = new JTextField(10);
	
	gameOption = new JRadioButton("Game");
	gameOption.addChangeListener(this);
	movieOption = new JRadioButton("Movie");
	movieOption.addChangeListener(this);
			
	gamePanel = createGamePanel();
	gamePanel.setVisible(true);
	moviePanel = createMoviePanel();
	moviePanel.setVisible(false);
	
	mediaTypeGroup = new ButtonGroup();
	mediaTypeGroup.add(gameOption);
	mediaTypeGroup.add(movieOption);
	gameOption.setSelected(true);
	
	saveButton = new JButton("Save");
	saveButton.addActionListener(this);
	
	cancelButton = new JButton("Cancel");
	cancelButton.addActionListener(this);
	cancelButton.setActionCommand("CANCEL");
	
	mainPanel = new JPanel();
	GroupLayout layout = new GroupLayout(mainPanel);
	mainPanel.setLayout(layout);

	layout.setAutoCreateGaps(true);
	layout.setAutoCreateContainerGaps(true);

	GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

	hGroup.addGroup(layout.createParallelGroup().
            addComponent(titleLabel).addComponent(releaseDateLabel).addComponent(gameOption));
	hGroup.addGroup(layout.createParallelGroup().
			addComponent(titleBox).addComponent(releaseDateBox).addComponent(movieOption));
	hGroup.addGroup(layout.createParallelGroup().
            addComponent(numCopiesLabel).addComponent(genreLabel));
	hGroup.addGroup(layout.createParallelGroup().
			addComponent(numCopiesBox).addComponent(genreBox));
	layout.setHorizontalGroup(hGroup);

	GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(titleLabel).addComponent(titleBox).addComponent(numCopiesLabel).addComponent(numCopiesBox));
	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(releaseDateLabel).addComponent(releaseDateBox).addComponent(genreLabel).addComponent(genreBox));
	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
			addComponent(gameOption).addComponent(movieOption));
	layout.setVerticalGroup(vGroup);
	
	buttonPanel = new JPanel();
	buttonPanel.add(saveButton);
	buttonPanel.add(cancelButton);
	
	cp = getContentPane();
	cp.add(mainPanel, BorderLayout.NORTH);
	cp.add(gamePanel, BorderLayout.WEST);
	cp.add(moviePanel, BorderLayout.CENTER);
	cp.add(buttonPanel, BorderLayout.SOUTH);
	
	setupMediaDialog();
	}
	
	JPanel	createMoviePanel()
	{
	JLabel	awardsLabel;
	JLabel	directorLabel;
	JLabel	castLabel;
	JLabel	sequelsLabel;
	JScrollPane	awardsPane;
	JScrollPane	castPane;
	JScrollPane	directorPane;
	JScrollPane	sequelsPane;
	List<String>	cast;
	List<String>	awards;
	List<String>	directors;
	List<String>	sequels;
	DefaultListModel	castListModel;
	DefaultListModel	awardsListModel;
	DefaultListModel	directorListModel;
	DefaultListModel	sequelsListModel;
	JPanel	tempMoviePanel;
	
	awardsLabel = new JLabel("Awards:");
	directorLabel = new JLabel("Director:");
	castLabel = new JLabel("Cast:");
	sequelsLabel = new JLabel("Sequels:");
	
	// need to be returned from a database call
	cast = new ArrayList<String>();
	awards = new ArrayList<String>();
	directors = new ArrayList<String>();
	sequels = new ArrayList<String>(); 
	
	castListModel = new DefaultListModel();
	for (String actor : cast)
		{
		castListModel.addElement(actor);
		}
	
	awardsListModel = new DefaultListModel();
	for (String award : awards)
		{
		awardsListModel.addElement(award);
		}
	
	directorListModel = new DefaultListModel();
	for (String director : directors)
		{
		directorListModel.addElement(director);
		}
	
	sequelsListModel = new DefaultListModel();
	for (String sequel : sequels)
		{
		castListModel.addElement(sequel);
		}
	
	castList = new JList(castListModel);
	awardsList = new JList(awardsListModel);
	directorList = new JList(directorListModel);
	sequelsList = new JList(sequelsListModel);
	
	castPane = new JScrollPane(castList);
	awardsPane = new JScrollPane(awardsList);
	directorPane = new JScrollPane(directorList);
	sequelsPane = new JScrollPane(sequelsList);
	
	tempMoviePanel = new JPanel();
	GroupLayout layout = new GroupLayout(tempMoviePanel);
	tempMoviePanel.setLayout(layout);

	layout.setAutoCreateGaps(true);
	layout.setAutoCreateContainerGaps(true);
	Component 	filler = Box.createRigidArea(new Dimension(10,10));

	GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

	hGroup.addGroup(layout.createParallelGroup().
            addComponent(directorLabel).addComponent(directorPane));
	hGroup.addGroup(layout.createParallelGroup().
			addComponent(castLabel).addComponent(castPane));
	hGroup.addGroup(layout.createParallelGroup().
            addComponent(awardsLabel).addComponent(awardsPane));
	hGroup.addGroup(layout.createParallelGroup().
            addComponent(sequelsLabel).addComponent(sequelsPane));
	layout.setHorizontalGroup(hGroup);

	GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
			addComponent(directorLabel).addComponent(castLabel).addComponent(awardsLabel).addComponent(sequelsLabel));
	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
			addComponent(directorPane).addComponent(castPane).addComponent(awardsPane).addComponent(sequelsPane));
	layout.setVerticalGroup(vGroup);
	
	return tempMoviePanel;
	}
	
	JPanel	createGamePanel()
	{
	JLabel	versionLabel;
	JLabel	platformLabel;
	JPanel	tempGamePanel;
	
	versionLabel = new JLabel("Version:");
	platformLabel = new JLabel("Platform");
	
	versionBox = new JTextField(10);
	platformBox = new JTextField(10);
	
	tempGamePanel = new JPanel();
	GroupLayout layout = new GroupLayout(tempGamePanel);
	tempGamePanel.setLayout(layout);

	layout.setAutoCreateGaps(true);
	layout.setAutoCreateContainerGaps(true);

	GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

	hGroup.addGroup(layout.createParallelGroup().
            addComponent(versionLabel).addComponent(platformLabel));
	hGroup.addGroup(layout.createParallelGroup().
			addComponent(versionBox).addComponent(platformBox));
	layout.setHorizontalGroup(hGroup);

	GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(versionLabel).addComponent(versionBox));
	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(platformLabel).addComponent(platformBox));
	layout.setVerticalGroup(vGroup);
	
	return tempGamePanel;
	}

	public void actionPerformed(ActionEvent ae)
	{
	if (ae.getActionCommand().equals("CREATE_MEDIA"))
		{
		Media	media;
		// make sure to update the Works_In table too
		
		// Fill in using info from boxes
		media = new Media();
		
		dispose();
		}
	if (ae.getActionCommand().equals("EDIT_MEDIA")) 
		{
		// update database where mediaID = this.mediaID
		// make sure to update the Works_In table too
		
		dispose();
		}
	else if (ae.getActionCommand().equals("CANCEL"))
		{
		dispose();
		}
	
	}
	
	public void stateChanged(ChangeEvent e)
	{
	if (gameOption.isSelected())
		{
		gamePanel.setVisible(true);
		moviePanel.setVisible(false);
		}
	if (movieOption.isSelected())
		{
		gamePanel.setVisible(false);
		moviePanel.setVisible(true);
		}
	}
	
	void	setupMediaDialog()
	{
	Toolkit		tk;
	Dimension	d;

	tk = Toolkit.getDefaultToolkit();
	d = tk.getScreenSize();

	setSize(700, 500);
	setLocation(d.width/3, d.height/4);

//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//	getRootPane().setDefaultButton(saveButton);

	setVisible(true);
	}
}