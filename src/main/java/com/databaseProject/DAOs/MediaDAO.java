//Programmer: Kyle Savina
//Date: 10/25/2018
//Description: An object that accesses a database to retrieve
//				the information about a Media.

package com.databaseProject.DAOs;

import com.databaseProject.Pojos.Media;
import com.databaseProject.Pojos.Worker;
import com.databaseProject.databaseProject.*;
import java.sql.PreparedStatement;
import java.sql.Connection;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import java.util.ArrayList;



public class MediaDAO 
{

	
	
	public MediaDAO()
	{
		
		//Intentionally blank
		
	}
	
	
	
//=============================================================================
	
	public void deleteMedia(Media media)
	{
	PreparedStatement 	pstatement;
	int		 			result;
	ResultSet 			resultSet;
	WorkerDAO			workerDao;
	List<String>		workers;
	
	pstatement = null;
	workerDao = new WorkerDAO();
	
	try
	{
		Connection connection = ConnectionManager.getConnection();

		if (!hasBeenRented(media.getMediaID()))
			{
			if(media.getMediaType() == 'm')
				{
					workerDao.deleteWorks_On(media.getMediaID());
					deleteWon(media.getMediaID());
					deleteSequels(media.getMediaID());
					deleteMovie(media.getMediaID());
				}
				
			else if(media.getMediaType() == 'g')
				{
					deleteGame(media.getMediaID());
				}
				
			pstatement = connection.prepareStatement("DELETE FROM Media WHERE mediaID = ?");
			
			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setInt(1, media.getMediaID());
			
			result = pstatement.executeUpdate();  
			
			pstatement.close();                                      
			connection.close();                       
			}
		else
			JOptionPane.showMessageDialog(null, "You cannot delete a title that has already been rented.", "Error", JOptionPane.ERROR_MESSAGE); 
	}
			
	catch(SQLException sqle)
	{
		
		System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
		
	}
		
		
	}
	
//=============================================================================
	
	public boolean	hasBeenRented (int mediaID)
	{
	PreparedStatement 	pstatement;
	ResultSet			resultSet;
	boolean				hasBeenRented;
	
	pstatement = null;
	hasBeenRented = false;
	
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT * FROM Rental_Info WHERE mediaID = ?");
			
			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setInt(1, mediaID);
			
			resultSet = pstatement.executeQuery();
	
			while ( resultSet.next() ) 
			{
				hasBeenRented = true;	
			} // end while
			
			// ensure statement and connection are closed properly                                      
			resultSet.close();                                      
			pstatement.close();                                      
			connection.close();                       
		
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
		return hasBeenRented;
	}
	
//=============================================================================

	public void deleteGame(int gameID)
	{
		PreparedStatement 	pstatement;
		
		pstatement = null;
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
				
			pstatement = connection.prepareStatement("DELETE FROM Games WHERE gameID = ?");

			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setInt(1, gameID);
			
			pstatement.executeUpdate();
			
			pstatement.close();                                       
			connection.close();   
		
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
	}	

//=============================================================================
	
	public void deleteMovie(int movieID)
	{
		PreparedStatement 	pstatement;
		
		pstatement = null;
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
				
			pstatement = connection.prepareStatement("DELETE FROM Movies WHERE movieID = ?");

			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setInt(1, movieID);
			
			pstatement.executeUpdate();
			
			pstatement.close();                                       
			connection.close();   
		
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
	}	

//=============================================================================
	
	public void deleteWon(int movieID)
	{
		
		PreparedStatement 	pstatement;
		
		pstatement = null;
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
				
			pstatement = connection.prepareStatement("DELETE FROM Won WHERE movieID = ?");

			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setInt(1, movieID);
			
			pstatement.executeUpdate();
			
			pstatement.close();                                       
			connection.close();   
		
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
	}
	
//=============================================================================

	public void deleteSequels(int movieID)
	{
		
		PreparedStatement 	pstatement;
		
		pstatement = null;
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
				
			pstatement = connection.prepareStatement("DELETE FROM Sequel WHERE prequelID = ?");

			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setInt(1, movieID);
			
			pstatement.executeUpdate();
			
			pstatement.close();                                       
			connection.close();   
		
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
	}	


	
//=============================================================================
	
	public void insertMedia(Media media)
	{
		
		PreparedStatement 	pstatement;
		int		 			result;
		ResultSet 			resultSet;
		WorkerDAO			workerDao;
		List<String>		workers;
		
		pstatement = null;
		workerDao = new WorkerDAO();
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("INSERT INTO Media (releaseDate, genre, title, numCopiesAvailable) VALUES (?, ?, ?, ?)");

			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setDate(1, media.getReleaseDate());
			pstatement.setString(2, media.getGenre());
			pstatement.setString(3, media.getTitle());
			pstatement.setInt(4, media.getNumCopiesAvailable());
			
			result = pstatement.executeUpdate();
			
			pstatement.clearParameters();
			pstatement = connection.prepareStatement("SELECT mediaID FROM Media M WHERE M.releaseDate = ? AND M.genre = ? AND M.title = ? AND numCopiesAvailable = ?");
			pstatement.setDate(1, media.getReleaseDate());
			pstatement.setString(2, media.getGenre());
			pstatement.setString(3, media.getTitle());
			pstatement.setInt(4, media.getNumCopiesAvailable());
			
			resultSet = pstatement.executeQuery();
			while (resultSet.next())
				{
				media.setMediaID(resultSet.getInt("mediaID"));
				}
			
			if(media.getMediaType() == 'm')
			{
				
				pstatement = connection.prepareStatement("INSERT INTO Movies (movieID) VALUES (?)");
				
				// instantiate parameters
				pstatement.clearParameters();
				pstatement.setInt(1, media.getMediaID());
			
				result = pstatement.executeUpdate(); 
				
				workers = media.getCastList();
				for (String director : media.getDirectorList())
					{
					if (!workers.contains(director))
						workers.add(director);
					}	
				workerDao.insertWorks_On(workers, media.getMediaID());
				
				insertWonList(media.getAwardsList(), media.getMediaID());
				insertSequelList(media.getSequelsList(), media.getMediaID());
			}
			
			else if(media.getMediaType() == 'g')
			{
				
				pstatement = connection.prepareStatement("INSERT INTO Games (gameID, version, platform) VALUES (?, ?, ?)");
				
				// instantiate parameters
				pstatement.clearParameters();
				pstatement.setInt(1, media.getMediaID());
				pstatement.setFloat(2, media.getVersion());
				pstatement.setString(3, media.getPlatform());
				
				result = pstatement.executeUpdate(); 
				
			}
			
			// ensure statement and connection are closed properly                                      
			//resultSet.close();                                      
			pstatement.close();                                      
			connection.close();                       
		
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
	}
	
//=============================================================================
	
	public void insertMedia(List<Media> mediaList)
	{
		for(Media media : mediaList)
		{
			insertMedia(media);
		}
	}
	
//=============================================================================

	public void insertMedia(Date releaseDate, String genre, String title,
								int numOfCopiesAvailable)
	{
		PreparedStatement 	pstatement;
		int		 			result;
		
		pstatement = null;
		//resultSet = null;
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
				
			pstatement = connection.prepareStatement("INSERT INTO Media ( releaseDate, genre, title, numCopiesAvailable) VALUES (?, ?, ?, ?)");

			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setDate(1, releaseDate);
			pstatement.setString(2, genre);
			pstatement.setString(3, title);
			pstatement.setInt(4, numOfCopiesAvailable);
			
			result = pstatement.executeUpdate();
				
			//System.out.println("3");
			
			pstatement.close();                                       
			connection.close();   
		
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
	}
	
//=============================================================================
	//Warning: if you define media.setMediaType as 'g' and call 
	//insertMedia(Media media), you do not need this function 
	public void insertGame(String platform, float version, int mediaID)
	{
		
		PreparedStatement 	pstatement;
		int		 			result;
		
		pstatement = null;
		//resultSet = null;
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
				
			pstatement = connection.prepareStatement("INSERT INTO Games (platform, version, gameID) VALUES (?, ?, ?)");

			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setString(1, platform);
			pstatement.setFloat(2, version);
			pstatement.setInt(3, mediaID);
			
			result = pstatement.executeUpdate();
				
			//System.out.println("3");
			
			pstatement.close();                                       
			connection.close();   
		
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
	}
	
//=============================================================================
	//Warning: if you define media.setMediaType as 'm' and call 
	//insertMedia(Media media), you do not need this function 
	public void insertMovie(int movieID)
	{
		
		PreparedStatement 	pstatement;
		int		 			result;
		
		pstatement = null;
		//resultSet = null;
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
				
			pstatement = connection.prepareStatement("INSERT INTO Movies (movieID) VALUES (?)");

			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setInt(1, movieID);
			
			result = pstatement.executeUpdate();
				
			//System.out.println("3");
			
			pstatement.close();                                       
			connection.close();   
		
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
	}
	
//=============================================================================
	public Media getMedia(Integer mediaID)
	{
	Media	media;
	
	media = getMovie(mediaID);
	if (media.getTitle() == null)
		media = getGame(mediaID);
	
	return media;	
	}

//=============================================================================	
	
	public Media getMovie(Integer movieID)
	{
	Media 				media;
	PreparedStatement 	pstatement;
	ResultSet 			resultSet;
	WorkerDAO			workerDao;
	
	workerDao = new WorkerDAO();
	media = new Media();
	pstatement = null;
	resultSet = null;
	
	try
		{
		Connection connection = ConnectionManager.getConnection();
	
		pstatement = connection.prepareStatement("SELECT * FROM Media M, Movies M2 WHERE M.mediaID = M2.movieID "
				+ "AND M2.movieID = ?");
		
		// instantiate parameters
		pstatement.clearParameters();
		pstatement.setInt(1, movieID);
		
		resultSet = pstatement.executeQuery();

		//get movies
		while ( resultSet.next() ) 
			{	
			media = new Media();
			media.setMediaID(resultSet.getInt("mediaID"));
			media.setReleaseDate(resultSet.getDate("releaseDate"));
			media.setGenre(resultSet.getString("genre"));
			media.setTitle(resultSet.getString("title"));
			media.setNumCopiesAvailable(resultSet.getInt("numCopiesAvailable"));
			media.setMediaType('m');
			media.setCastList(getActorsForMovie(media));
			media.setDirectorList(getDirectorsForMovie(media));
			media.setAwardsList(getAwardsForMovie(media));
			media.setSequelsList(getSequelsForMovie(media));
			} 
		
		// ensure statement and connection are closed properly                                      
		resultSet.close();                                      
		pstatement.close();                                      
		connection.close();  
		
		return media;
		}
	
	catch(SQLException sqle)
		{
		System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
		return media;
		}
	
	}	

//=============================================================================
	
	public Media getGame(Integer gameID)
	{
		
	Media 				media;
	PreparedStatement 	pstatement;
	ResultSet 			resultSet;
	WorkerDAO			workerDao;
	
	workerDao = new WorkerDAO();
	media = null;
	pstatement = null;
	resultSet = null;
	
	
	try
		{
		Connection connection = ConnectionManager.getConnection();
	
		pstatement = connection.prepareStatement("SELECT * FROM Media M, Games G WHERE M.mediaID = G.gameID "
				+ "AND G.gameID = ?");
		
		// instantiate parameters
		pstatement.clearParameters();
		pstatement.setInt(1, gameID);
		
		resultSet = pstatement.executeQuery();
		
		while ( resultSet.next() ) 
			{
			media = new Media();
			media.setMediaID(resultSet.getInt("mediaID"));
			media.setReleaseDate(resultSet.getDate("releaseDate"));
			media.setGenre(resultSet.getString("genre"));
			media.setTitle(resultSet.getString("title"));
			media.setNumCopiesAvailable(resultSet.getInt("numCopiesAvailable"));
			media.setVersion(resultSet.getFloat("version"));
			media.setPlatform(resultSet.getString("platform"));
			media.setMediaType('g');
			}	 
		
		// ensure statement and connection are closed properly                                      
		resultSet.close();                                      
		pstatement.close();                                      
		connection.close();  
		
		return media;
		}
	
	catch(SQLException sqle)
		{
		System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
		return media;
		}
		
		
		
		
	}	
	
//=============================================================================
	
	public List<Media> getMedia(List<Integer> mediaIDList)
	{
		
		List<Media> 		mediaList;
		Media 				media;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		
		mediaList = new ArrayList<Media>();
		for (int i=0; i < mediaIDList.size(); i++)
			{
			mediaList.add(getMedia(mediaIDList.get(i)));
//			System.out.println(getMedia(mediaIDList.get(i)).getTitle());
			}
		
		return mediaList;
	}
	
//=============================================================================
	
	public List<Media> getAllMedia()
	{
		
		List<Media>			mediaList;
		Media 				media;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		WorkerDAO			workerDao;
		
		workerDao = new WorkerDAO();
		mediaList = new ArrayList<Media>();
		media = new Media();
		pstatement = null;
		resultSet = null;
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT * FROM Media M, Movies M2 WHERE M.mediaID = M2.movieID");
			
			// instantiate parameters
			pstatement.clearParameters();
			
			resultSet = pstatement.executeQuery();

			//get movies
			while ( resultSet.next() ) 
			{
					
					media = new Media();
					media.setMediaID(resultSet.getInt("mediaID"));
					media.setReleaseDate(resultSet.getDate("releaseDate"));
					media.setGenre(resultSet.getString("genre"));
					media.setTitle(resultSet.getString("title"));
					media.setNumCopiesAvailable(resultSet.getInt("numCopiesAvailable"));
					media.setMediaType('m');
					media.setCastList(getActorsForMovie(media));
					media.setDirectorList(getDirectorsForMovie(media));
					media.setAwardsList(getAwardsForMovie(media));
					media.setSequelsList(getSequelsForMovie(media));
					mediaList.add(media);
				
			} // end while
			
			pstatement = connection.prepareStatement("SELECT * FROM Media M, Games G WHERE M.mediaID = G.gameID");
			
			// instantiate parameters
			pstatement.clearParameters();
			
			resultSet = pstatement.executeQuery();
			
			while ( resultSet.next() ) 
			{
					
					media = new Media();
					media.setMediaID(resultSet.getInt("mediaID"));
					media.setReleaseDate(resultSet.getDate("releaseDate"));
					media.setGenre(resultSet.getString("genre"));
					media.setTitle(resultSet.getString("title"));
					media.setNumCopiesAvailable(resultSet.getInt("numCopiesAvailable"));
					media.setVersion(resultSet.getFloat("version"));
					media.setPlatform(resultSet.getString("platform"));
					media.setMediaType('g');
					mediaList.add(media);
				
			} // end while
			
			// ensure statement and connection are closed properly                                      
			resultSet.close();                                      
			pstatement.close();                                      
			connection.close();                       
		
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
		
		
		return mediaList;
		
	}
	
//=============================================================================
	
	public int getMediaIDUsingMovieTitle(String movieTitle)
	{
		
		PreparedStatement 	pstatement;
		ResultSet			resultSet;
		int					mediaID;
		
		mediaID = -1;
		pstatement = null;
		resultSet = null;
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
				
			pstatement = connection.prepareStatement("Select M.mediaID " + 
													"From Media M, Movies M2 " + 
													"Where M.title= ? AND M.mediaID = M2.movieID");

			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setString(1, movieTitle);
			
			resultSet = pstatement.executeQuery();
				
			while(resultSet.next())
			{
				
				mediaID = resultSet.getInt("mediaID");
				
			}
			
			
			pstatement.close();             
			connection.close();
			
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
		return mediaID;
		
	}
	
//=============================================================================
	
	public List<Integer> getMediaIDListUsingMovieTitles(List<String> movieTitleList)
	{
		
		List<Integer> mediaIDList;
		
		mediaIDList = new ArrayList<Integer>();
		
		for(int i = 0; i < movieTitleList.size(); i++)
		{
			
			mediaIDList.add(new Integer(getMediaIDUsingMovieTitle(movieTitleList.get(i))));
			
		}
		
		return mediaIDList;
		
	}
	
//=============================================================================
	
	public int getAwardID(String aname)
	{
		
		PreparedStatement 	pstatement;
		ResultSet			resultSet;
		int					awardID;
		
		awardID = -1;
		pstatement = null;
		resultSet = null;
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
				
			pstatement = connection.prepareStatement("Select A.awardID From Awards A Where A.aname = ?");

			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setString(1, aname);
			
			resultSet = pstatement.executeQuery();
				
			while(resultSet.next())
			{
				
				awardID = resultSet.getInt("awardID");
				
			}
			
			
			pstatement.close();             
			connection.close();
			
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
		return awardID;
		
	}
	
	
//=============================================================================
	
	public List<Integer> getAwardIDList(List<String> anameList)
	{
		
		List<Integer> awardIDList;
		
		awardIDList = new ArrayList<Integer>();
		
		for(int i = 0; i < anameList.size(); i++)
		{
			
			awardIDList.add(new Integer(getAwardID(anameList.get(i))));
			
		}
		
		return awardIDList;
		
	}
	
//============================================================================
	
	public void insertWon(int awardID, int movieID)
	{
	
		PreparedStatement 	pstatement;
		int 				result;
		
		pstatement = null;
		//resultSet = null;
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
				
			pstatement = connection.prepareStatement("INSERT INTO Won (movieID, awardID) VALUES (?, ?)");

			//instantiate parameters
			pstatement.clearParameters();
			pstatement.setInt(1, movieID);
			pstatement.setInt(2, awardID);
	
			result = pstatement.executeUpdate();

			pstatement.close();             
			connection.close();
			
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
	}
	
//=============================================================================
	
	public void insertWonList(List<String> anameList, int movieID)
	{
	List<Integer> awardIDList = getAwardIDList(anameList);
	
		for(int i = 0; i < awardIDList.size(); i++)
		{
			
			insertWon(awardIDList.get(i), movieID);
			
		}
	}
	
//=============================================================================
	
	public void insertSequel(int sequelID, int prequelID)
	{
		
		PreparedStatement 	pstatement;
		int 				result;
		
		pstatement = null;
		//resultSet = null;
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
				
			pstatement = connection.prepareStatement("INSERT INTO Sequel (prequelID, sequelID) VALUES (?, ?)");

			//instantiate parameters
			pstatement.clearParameters();
			pstatement.setInt(1, prequelID);
			pstatement.setInt(2, sequelID);
	
			result = pstatement.executeUpdate();
			
			pstatement.close();             
			connection.close();
			
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
	}
	
//=============================================================================
	
	public void insertSequelList(List<String> sequelList, int prequelID)
	{
	List<Integer>	sequelIDList = getMediaIDListUsingMovieTitles(sequelList);
	
		for(int i = 0; i < sequelIDList.size(); i++)
		{
			System.out.println(sequelIDList.get(i));
			insertSequel(sequelIDList.get(i), prequelID);	
		}
	}
	
//=============================================================================
	public List<String> getDirectorsForMovie(Media media)
	{
	List<String>		directorList;
	PreparedStatement 	pstatement;
	ResultSet 			resultSet;
	WorkerDAO			workerDao;
	int					isDirectorByte;
	
	workerDao = new WorkerDAO();
	directorList = new ArrayList<String>();
	isDirectorByte = 1;
	pstatement = null;
	resultSet = null;
	
	try
	{
		Connection connection = ConnectionManager.getConnection();
	
		pstatement = connection.prepareStatement("SELECT * FROM Works_On o, Workers w WHERE o.workerID = w.workerID "
				+ "AND o.movieID = ? AND w.isDirector = ?");
		
		// instantiate parameters
		pstatement.clearParameters();
		pstatement.setInt(1, media.getMediaID());
		pstatement.setInt(2, isDirectorByte);
		
		resultSet = pstatement.executeQuery();

		//get movies
		while ( resultSet.next() ) 
			{
			directorList.add(resultSet.getString("wname"));	
			} // end while
		
		
		// ensure statement and connection are closed properly                                      
		resultSet.close();                                      
		pstatement.close();                                      
		connection.close();     
		
		return directorList;
	
		}
	
	catch(SQLException sqle)
		{
		System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
		}
	
	return directorList;
	}	
	
//============================================================================
	
	public List<String> getActorsForMovie(Media media)
	{
	List<String>		actorList;
	PreparedStatement 	pstatement;
	ResultSet 			resultSet;
	WorkerDAO			workerDao;
	int					isActorByte;
	
	workerDao = new WorkerDAO();
	actorList = new ArrayList<String>();
	isActorByte = 1;
	pstatement = null;
	resultSet = null;
	
	try
	{
		Connection connection = ConnectionManager.getConnection();
	
		pstatement = connection.prepareStatement("SELECT * FROM Works_On o, Workers w WHERE o.workerID = w.workerID "
				+ "AND o.movieID = ? AND w.isActor = ?");
		
		// instantiate parameters
		pstatement.clearParameters();
		pstatement.setInt(1, media.getMediaID());
		pstatement.setInt(2, isActorByte);
		
		resultSet = pstatement.executeQuery();

		//get movies
		while ( resultSet.next() ) 
			{
			actorList.add(resultSet.getString("wname"));	
			} // end while
		
		
		// ensure statement and connection are closed properly                                      
		resultSet.close();                                      
		pstatement.close();                                      
		connection.close();     
		
		return actorList;
	
		}
	
	catch(SQLException sqle)
		{
		System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
		}
	
	return actorList;
	}
	
	
//=============================================================================
	
	public List<String> getSequelsForMovie(Media media)
	{
	List<String>		sequelsList;
	PreparedStatement 	pstatement;
	ResultSet 			resultSet;
	
	sequelsList = new ArrayList<String>();
	pstatement = null;
	resultSet = null;
	
	try
	{
		Connection connection = ConnectionManager.getConnection();
	
		pstatement = connection.prepareStatement("SELECT * FROM Sequel s, Movies m, Media me WHERE "
				+ "s.sequelID = m.movieID AND s.sequelID = me.mediaID AND s.prequelID = ?");
		
		// instantiate parameters
		pstatement.clearParameters();
		pstatement.setInt(1, media.getMediaID());
		
		resultSet = pstatement.executeQuery();

		//get movies
		while ( resultSet.next() ) 
			{
			sequelsList.add(resultSet.getString("title"));	
			} // end while
		
		
		// ensure statement and connection are closed properly                                      
		resultSet.close();                                      
		pstatement.close();                                      
		connection.close();     
		
		return sequelsList;
	
		}
	
	catch(SQLException sqle)
		{
		System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
		}
	
	return sequelsList;
	}
	
//============================================================================

	public List<String> getAwardsForMovie(Media media)
	{
	List<String>		awardsList;
	PreparedStatement 	pstatement;
	ResultSet 			resultSet;
	
	awardsList = new ArrayList<String>();
	pstatement = null;
	resultSet = null;
	
	try
	{
		Connection connection = ConnectionManager.getConnection();
	
		pstatement = connection.prepareStatement("SELECT * FROM Awards a, Won w WHERE a.awardID = w.awardID "
				+ "AND w.movieID = ?");
		
		// instantiate parameters
		pstatement.clearParameters();
		pstatement.setInt(1, media.getMediaID());
		
		resultSet = pstatement.executeQuery();

		//get movies
		while ( resultSet.next() ) 
			{
			awardsList.add(resultSet.getString("aname"));	
			} // end while
		
		
		// ensure statement and connection are closed properly                                      
		resultSet.close();                                      
		pstatement.close();                                      
		connection.close();     
		
		return awardsList;
	
		}
	
	catch(SQLException sqle)
		{
		System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
		}
	
	return awardsList;
	}
	
//=============================================================================	
	
	public List<String> getAllAwardNames()
	{
		List<String>		awardNameList;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		
		awardNameList = new ArrayList<String>();
		pstatement = null;
		resultSet = null;
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT a.aname FROM Awards a");
			
			// instantiate parameters
			pstatement.clearParameters();
			
			resultSet = pstatement.executeQuery();

			while ( resultSet.next() ) 
			{
			
				awardNameList.add(resultSet.getString("aname"));
				
			} // end while
			
			// ensure statement and connection are closed properly                                      
			resultSet.close();                                      
			pstatement.close();                                      
			connection.close();                       
		
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
		
		
		return awardNameList;
		
	}
	
//=============================================================================
	
	public void updateMedia(int mediaID, Date releaseDate, 
							int numOfCopiesAvailable, String genre)
	{
		
		PreparedStatement 	pstatement;
		int					result;
		
		pstatement = null;
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
				
			pstatement = connection.prepareStatement("Update Media M " + 
													"Set M.genre = ? " + 
													"Where M.mediaId = ?; ");

			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setString(1, genre);
			pstatement.setInt(2, mediaID);
			
			result = pstatement.executeUpdate();

//--------------------			

			pstatement = connection.prepareStatement("Update Media M " + 
													"Set M.releaseDate = ? " + 
													"Where M.mediaId = ?; ");


			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setDate(1, releaseDate);
			pstatement.setInt(2, mediaID);
			
			result = pstatement.executeUpdate();

//---------------------------
			
			pstatement = connection.prepareStatement("Update Media M " + 
													"Set M.numCopiesAvailable = ? " + 
													"Where M.mediaId = ?; ");

			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setInt(1, numOfCopiesAvailable);
			pstatement.setInt(2, mediaID);
			
			result = pstatement.executeUpdate();
			
			pstatement.close();             
			connection.close();
			
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
	}
	
//=============================================================================
	
	public void updateMedia(List<Media> mediaList)
	{
		
		
		
	}
	
//=============================================================================
	
	public void upateAllMedia(Media media)
	{
		
		
		
	}
	
//=============================================================================

	public List<String> getAllMediaTitles()
	{
		
		List<Media> 	mediaList;
		List<String>	titleList;
		
		titleList = new ArrayList<String>();
		mediaList = getAllMedia();
		
		for(int i = 0; i < mediaList.size(); i++)
		{
			
			titleList.add(mediaList.get(i).getTitle());
			
		}
		
		return titleList;
		
	}

//============================================================================
	
	public List<String> getAllMovieTitles()
	{
		
		List<String>			movieNameList;
		PreparedStatement 		pstatement;
		ResultSet 				resultSet;
		
		movieNameList = new ArrayList<String>();
		pstatement = null;
		resultSet = null;
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT M.title FROM Media M, Movies M2 WHERE M.mediaID = M2.movieID");
			
			// instantiate parameters
			pstatement.clearParameters();
			
			resultSet = pstatement.executeQuery();

			while ( resultSet.next() ) 
			{
					
				movieNameList.add(resultSet.getString("title"));
				
			} // end while
			
			// ensure statement and connection are closed properly                                      
			resultSet.close();                                      
			pstatement.close();                                      
			connection.close();                       
		
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
		return movieNameList;
		
	}
	
//=========================================================================================================

	public List<Media> getMovieWithActor(String actorName, boolean notPreviouslyRented, boolean ifWonAwards, String userEmail)
	{
	    List<Media>           actorSearchList;
	    List<Integer>         listOfMedia;
	    PreparedStatement     pstatement;
	    ResultSet             resultSet;
	    int					  isActor;

	    actorSearchList  = new ArrayList<Media>();
	    listOfMedia = new ArrayList<Integer>();
	    pstatement   = null;
	    resultSet    = null;

	    isActor = 1;

	    try
	    {
	        Connection connection = ConnectionManager.getConnection();
	        if (notPreviouslyRented && !ifWonAwards)
	        {
	        	pstatement = connection.prepareStatement("SELECT WO.movieID FROM Workers W, Works_On WO WHERE W.isActor = ? AND W.workerID = WO.workerID AND W.wname = ? AND WO.movieID NOT IN (SELECT R.mediaID FROM rental_info R WHERE email = ?)");
	            pstatement.clearParameters();
	            pstatement.setInt(1, isActor);
	            pstatement.setString(2, actorName);
	            pstatement.setString(3, userEmail);
	        }

	        else if (ifWonAwards && !notPreviouslyRented)
	        {
	            pstatement = connection.prepareStatement("SELECT WO.movieID FROM Workers W, Works_On WO, Won WN WHERE W.isActor = ? AND W.workerID = WO.workerID AND W.wname = ? AND WN.movieID = WO.movieID");
	            pstatement.clearParameters();
	            pstatement.setInt(1, isActor);
	            pstatement.setString(2, actorName);
	        }

	        else if (notPreviouslyRented && ifWonAwards)
	        {
	            pstatement = connection.prepareStatement("SELECT WO.movieID FROM Workers W, Works_On WO, Won WN WHERE W.isActor = ? AND W.workerID = WO.workerID AND W.wname = ? AND WN.movieID = WO.movieID AND WO.movieID NOT IN (SELECT R.mediaID FROM rental_info R WHERE email = ?)");
	            pstatement.clearParameters();
	            pstatement.setInt(1, isActor);
	            pstatement.setString(2, actorName);
	            pstatement.setString(3, userEmail);
	        }

	        else
	        {
	            pstatement = connection.prepareStatement("SELECT WO.movieID FROM Workers W, Works_On WO WHERE W.isActor = ? AND W.workerID = WO.workerID AND W.wname = ?");
	            pstatement.clearParameters();
	            pstatement.setInt(1, isActor);
	            pstatement.setString(2, actorName);
	        }


            resultSet = pstatement.executeQuery();

            while (resultSet.next())
            {
                listOfMedia.add(resultSet.getInt("movieID"));

            } //end while

            resultSet.close();
            pstatement.close();
            connection.close();

            actorSearchList = getMedia(listOfMedia);

            return actorSearchList;

	    } // end of try

	    catch(SQLException sqle)
	    {

	        System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
	        return actorSearchList;
	    } // end of catch
	}
	
//=========================================================================================================

	public List<Media> getMovieWithDirector(String directorName, boolean notPreviouslyRented, boolean ifWonAwards, String userEmail)
	{
	    List<Media>           directorSearchList;
	    List<Integer>         listOfMedia;
	    PreparedStatement     pstatement;
	    ResultSet             resultSet;
	    int					  isDirector;

	    directorSearchList  = new ArrayList<Media>();
	    listOfMedia = new ArrayList<Integer>();
	    pstatement   = null;
	    resultSet    = null;

	    isDirector = 1;

	    try
	    {
	        Connection connection = ConnectionManager.getConnection();
	        if (notPreviouslyRented && !ifWonAwards)
	        {
	        	pstatement = connection.prepareStatement("SELECT WO.movieID FROM Workers W, Works_On WO WHERE W.isDirector = ? AND W.workerID = WO.workerID AND W.wname = ? AND WO.movieID NOT IN (SELECT R.mediaID FROM rental_info R WHERE email = ?)");
	            pstatement.clearParameters();
	            pstatement.setInt(1, isDirector);
	            pstatement.setString(2, directorName);
	            pstatement.setString(3, userEmail);
	        }

	        else if (ifWonAwards && !notPreviouslyRented)
	        {
	            pstatement = connection.prepareStatement("SELECT WO.movieID FROM Workers W, Works_On WO, Won WN WHERE W.isDirector = ? AND W.workerID = WO.workerID AND W.wname = ? AND WN.movieID = WO.movieID");
	            pstatement.clearParameters();
	            pstatement.setInt(1, isDirector);
	            pstatement.setString(2, directorName);
	        }

	        else if (notPreviouslyRented && ifWonAwards)
	        {
	            pstatement = connection.prepareStatement("SELECT WO.movieID FROM Workers W, Works_On WO, Won WN WHERE W.isDirector = ? AND W.workerID = WO.workerID AND W.wname = ? AND WN.movieID = WO.movieID AND WO.movieID NOT IN (SELECT R.mediaID FROM rental_info R WHERE email = ?)");
	            pstatement.clearParameters();
	            pstatement.setInt(1, isDirector);
	            pstatement.setString(2, directorName);
	            pstatement.setString(3, userEmail);
	        }

	        else
	        {
	            pstatement = connection.prepareStatement("SELECT WO.movieID FROM Workers W, Works_On WO WHERE W.isDirector = ? AND W.workerID = WO.workerID AND W.wname = ?");
	            pstatement.clearParameters();
	            pstatement.setInt(1, isDirector);
	            pstatement.setString(2, directorName);
	        }


            resultSet = pstatement.executeQuery();

            while (resultSet.next())
            {
                listOfMedia.add(resultSet.getInt("movieID"));

            } //end while

            resultSet.close();
            pstatement.close();
            connection.close();

            directorSearchList = getMedia(listOfMedia);

            return directorSearchList;

	    } // end of try

	    catch(SQLException sqle)
	    {

	        System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
	        return directorSearchList;
	    } // end of catch
	}
	//============================================================================
	
	public List<Media> getGamesByPlatform(String platform,boolean notPrevRented,String emailOfUser)
	{
	List<Media>			mediaSearched;
	List<Integer>		mediaIDList;
	PreparedStatement 	pState;
	ResultSet			rSet;
	Media				media;
	
	mediaSearched = new ArrayList<Media>();
	mediaIDList = new ArrayList<Integer>();
	pState = null;
	rSet = null;
	
	try
		{
		Connection connection = ConnectionManager.getConnection();
		
		if (notPrevRented)
			{
			pState = connection.prepareStatement("SELECT * FROM Games G WHERE G.platform = ? AND G.gameID NOT IN (SELECT mediaID FROM rental_info WHERE email = ?)");
			pState.clearParameters();
			pState.setString(1, platform);
			pState.setString(2, emailOfUser);
			}
		else
			{
			pState = connection.prepareStatement("SELECT * FROM Games G WHERE G.platform = ?");
			pState.clearParameters();
			pState.setString(1, platform);
			}
		
		rSet = pState.executeQuery();
		
		while(rSet.next())
			{
			mediaIDList.add(rSet.getInt("gameID"));
			}
		
		rSet.close();                                      
		pState.close();                                      
		connection.close();
		
		mediaSearched = getMedia(mediaIDList);
		
		return mediaSearched;
		}
	catch(SQLException e)
		{
		System.out.println("SQLState = " + e.getSQLState() + "\n" + e.getMessage());
		return mediaSearched;
		}
	}
	
	//============================================================================
	
	//Search by keyword with 2 booleans to tell if you want to search if it was previously rented or has won awards. 
	//Also needs the email of the user just to check if it was prevRented by that user.
	public List<Media> searchGenres(String genre,boolean notPrevRented,boolean wonAwards,String emailOfUser)
	{
	List<Media>			mediaSearched;
	List<Integer>		mediaIDList;
	PreparedStatement 	pState;
	ResultSet			rSet;
	Media				media;
	
	mediaSearched = new ArrayList<Media>();
	mediaIDList = new ArrayList<Integer>();
	pState = null;
	rSet = null;
	
	try
		{
		Connection connection = ConnectionManager.getConnection();
		
		if (notPrevRented && !wonAwards)
			{
			pState = connection.prepareStatement("SELECT * FROM Media M WHERE M.genre = ? AND M.mediaID NOT IN (SELECT mediaID FROM rental_info WHERE email = ?)");
			pState.clearParameters();
			pState.setString(1, genre);
			pState.setString(2, emailOfUser);
			}
		else if (wonAwards && !notPrevRented)
			{
			pState = connection.prepareStatement("SELECT * FROM Media M, Won W WHERE M.genre = ? AND W.movieID = M.mediaID");
			pState.clearParameters();
			pState.setString(1, genre);
			}
		else if (notPrevRented && wonAwards)
			{
			pState = connection.prepareStatement("SELECT * FROM Media M, Won W WHERE W.movieID = M.mediaID AND M.genre = ? AND M.mediaID NOT IN (SELECT mediaID FROM rental_info WHERE email = ?)");
			pState.clearParameters();
			pState.setString(1, genre);
			pState.setString(2, emailOfUser);
			}
		else
			{
			pState = connection.prepareStatement("SELECT * FROM Media M WHERE M.genre = ?");
			pState.clearParameters();
			pState.setString(1, genre);
			}
		
		rSet = pState.executeQuery();
		
		while(rSet.next())
			{
			mediaIDList.add(rSet.getInt("mediaID"));
			}
		
		rSet.close();                                      
		pState.close();                                      
		connection.close();
		
		mediaSearched = getMedia(mediaIDList);
		
		return mediaSearched;
		}
	catch(SQLException e)
		{
		System.out.println("SQLState = " + e.getSQLState() + "\n" + e.getMessage());
		return mediaSearched;
		}
	}

//=========================================================================================================
	
	//Search by keyword with 2 booleans to tell if you want to search if it was previously rented or has won awards. 
	//Also needs the email of the user just to check if it was prevRented by that user.
	public List<Media> keywordSearch(String keyword, boolean notPrevRented,boolean wonAwards,String emailOfUser)
	{
	List<Media>			mediaSearched;
	List<Integer>		mediaIDList;
	PreparedStatement 	pState;
	ResultSet			rSet;
	Media				media;
	
	mediaSearched = new ArrayList<Media>();
	mediaIDList = new ArrayList<Integer>();
	pState = null;
	rSet = null;
	
	try
		{
		Connection connection = ConnectionManager.getConnection();
		
		if (notPrevRented && !wonAwards)
			{
			pState = connection.prepareStatement("SELECT * FROM Media m WHERE m.title LIKE ? AND m.mediaID NOT IN (SELECT mediaID FROM rental_info WHERE email = ?)");
			pState.clearParameters();
			pState.setString(1, "%"+keyword+"%");
			pState.setString(2, emailOfUser);
			}
		else if (wonAwards && !notPrevRented)
			{
			pState = connection.prepareStatement("SELECT * FROM Media M, won W WHERE M.title LIKE ? AND W.movieID = M.mediaID");
			pState.clearParameters();
			pState.setString(1, "%"+keyword+"%");
			}
		else if (notPrevRented && wonAwards)
			{
			pState = connection.prepareStatement("SELECT * FROM Media m, Won w WHERE m.title LIKE ? AND w.movieID = m.mediaID AND m.mediaID NOT IN (SELECT mediaID FROM rental_info WHERE email = ?)");
			pState.clearParameters();
			pState.setString(1, "%"+keyword+"%");
			pState.setString(2, emailOfUser);
			}
		else
			{
			pState = connection.prepareStatement("SELECT * FROM Media M WHERE M.title LIKE ?");
			pState.clearParameters();
			pState.setString(1, "%"+keyword+"%");
			}
		
		rSet = pState.executeQuery();
		
		while(rSet.next())
			{
			mediaIDList.add(rSet.getInt("mediaID"));
			}
		
		rSet.close();                                      
		pState.close();                                      
		connection.close();
		
		mediaSearched = getMedia(mediaIDList);
		
		return mediaSearched;
		}
	catch(SQLException e)
		{
		System.out.println("SQLState = " + e.getSQLState() + "\n" + e.getMessage());
		return mediaSearched;
		}
	}


	public List<Media> emptySearch(boolean notPrevRented,boolean wonAwards,String emailOfUser)
	{
	List<Media>			mediaSearched;
	List<Integer>		mediaIDList;
	PreparedStatement 	pState;
	ResultSet			rSet;
	Media				media;
	
	mediaSearched = new ArrayList<Media>();
	mediaIDList = new ArrayList<Integer>();
	pState = null;
	rSet = null;
	
	try
		{
		Connection connection = ConnectionManager.getConnection();
		
		if (notPrevRented && !wonAwards)
			{
			pState = connection.prepareStatement("SELECT * FROM Media m WHERE m.mediaID NOT IN (SELECT mediaID FROM rental_info WHERE email = ?)");
			pState.clearParameters();
			pState.setString(1, emailOfUser);
			}
		else if (wonAwards && !notPrevRented)
			pState = connection.prepareStatement("SELECT * FROM Media m, Won w WHERE w.movieID = m.mediaID");
		else if (notPrevRented && wonAwards)
			{
			pState = connection.prepareStatement("SELECT * FROM Media m, Won w WHERE w.movieID = m.mediaID AND m.mediaID NOT IN (SELECT mediaID FROM rental_info WHERE email = ?)");
			pState.clearParameters();
			pState.setString(1, emailOfUser);
			}
		else
			pState = connection.prepareStatement("SELECT * FROM Media m");
		
		rSet = pState.executeQuery();
		
		while(rSet.next())
			{
			mediaIDList.add(rSet.getInt("mediaID"));
			}
		
		rSet.close();                                      
		pState.close();                                      
		connection.close();
		
		mediaSearched = getMedia(mediaIDList);
		
		return mediaSearched;
		}
	catch(SQLException e)
		{
		System.out.println("SQLState = " + e.getSQLState() + "\n" + e.getMessage());
		return mediaSearched;
		}
	}

	
}

