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

import com.databaseProject.Pojos.Media;


public class UserMediaDialog extends JDialog
							 implements ActionListener
{
	JTextField	numCopiesBox;
	JTextField	releaseDateBox;
	JTextField	genreBox;
	JTextField	typeBox;
	
	JButton		closeButton;
	
	JPanel	gamePanel;
	JPanel	moviePanel;
	
	Media	media;
	
	UserMediaDialog(Media media)
	{
	this.media = media;
	
	JLabel	typeLabel;
	JLabel	numCopiesLabel;
	JLabel	releaseDateLabel;
	JLabel	genreLabel;
	JPanel	mainPanel;
	JPanel	buttonPanel;
	Container	cp;
	
	typeLabel = new JLabel("Media Type:");
	numCopiesLabel = new JLabel("Copies Available:");
	releaseDateLabel = new JLabel("Release Date:");
	genreLabel = new JLabel("Genre:");
	
	numCopiesBox = new JTextField(10);
	numCopiesBox.setEditable(false);
	numCopiesBox.setText(Integer.toString(media.getNumCopiesAvailable()));
	
	releaseDateBox = new JTextField(10);
	releaseDateBox.setEditable(false);
	// Need to convert this to something that can actually be displayed
	//releaseDateBox.setText(media.getReleaseDate());
	
	genreBox = new JTextField(10);
	genreBox.setEditable(false);
	
	// Might need changed based on how it's stored in the database
	typeBox = new JTextField(10);
	typeBox.setEditable(false);
	if (media.getMediaType() == 'm')
		{
		typeBox.setText("Movie");
		moviePanel = createMoviePanel(media);
		gamePanel = null;
		}
	else
		{
		typeBox.setText("Game");
		gamePanel = createGamePanel(media);
		moviePanel = null;
		}
	
	closeButton = new JButton("Close");
	closeButton.addActionListener(this);
	closeButton.setActionCommand("CLOSE");
	
	mainPanel = new JPanel();
	GroupLayout layout = new GroupLayout(mainPanel);
	mainPanel.setLayout(layout);

	layout.setAutoCreateGaps(true);
	layout.setAutoCreateContainerGaps(true);

	GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

	hGroup.addGroup(layout.createParallelGroup().
            addComponent(typeLabel).addComponent(releaseDateLabel));
	hGroup.addGroup(layout.createParallelGroup().
			addComponent(typeBox).addComponent(releaseDateBox));
	hGroup.addGroup(layout.createParallelGroup().
            addComponent(numCopiesLabel).addComponent(genreLabel));
	hGroup.addGroup(layout.createParallelGroup().
			addComponent(numCopiesBox).addComponent(genreBox));
	layout.setHorizontalGroup(hGroup);

	GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(typeLabel).addComponent(typeBox).addComponent(numCopiesLabel).addComponent(numCopiesBox));
	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(releaseDateLabel).addComponent(releaseDateBox).addComponent(genreLabel).addComponent(genreBox));
	layout.setVerticalGroup(vGroup);
	
	buttonPanel = new JPanel();
	buttonPanel.add(closeButton);
	
	cp = getContentPane();
	cp.add(mainPanel, BorderLayout.NORTH);
	cp.add(buttonPanel, BorderLayout.SOUTH);
	if (gamePanel == null)
		cp.add(moviePanel, BorderLayout.CENTER);
	else
		cp.add(gamePanel, BorderLayout.WEST);
	
	setupMediaDialog(media.getTitle());
	}
	
	JPanel	createMoviePanel(Media media)
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
	DefaultListModel	sequelListModel;
	JList	awardsList;
	JList	castList;
	JList	directorList;
	JList	sequelsList;
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
	
	sequelListModel = new DefaultListModel();
	for (String sequel : sequels)
		{
		directorListModel.addElement(sequel);
		}
	
	castList = new JList(castListModel);
	awardsList = new JList(awardsListModel);
	directorList = new JList(directorListModel);
	sequelsList = new JList(sequelListModel);
	
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
	
	JPanel	createGamePanel(Media media)
	{
	JLabel	versionLabel;
	JLabel	platformLabel;
	JPanel	tempGamePanel;
	JTextField	versionBox;
	JTextField	platformBox;
	
	versionLabel = new JLabel("Version:");
	platformLabel = new JLabel("Platform");
	
	versionBox = new JTextField(10);
	versionBox.setEnabled(false);
	versionBox.setText(Float.toString(media.getVersion()));
	platformBox = new JTextField(10);
	platformBox.setEnabled(false);
	platformBox.setText(media.getPlatform());
	
	
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
	if (ae.getActionCommand().equals("CLOSE"))
		dispose();
	}
	
	void	setupMediaDialog(String title)
	{
	Toolkit		tk;
	Dimension	d;

	tk = Toolkit.getDefaultToolkit();
	d = tk.getScreenSize();
	
	setTitle(title);
	setSize(700, 500);
	setLocation(d.width/3, d.height/4);

//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//	getRootPane().setDefaultButton(saveButton);

	setVisible(true);
	}
	
}