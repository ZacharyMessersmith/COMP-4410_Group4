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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;



public class MediaDAO 
{

	
	
	public MediaDAO()
	{
		
		//Intentionally blank
		
	}
	
//=============================================================================
	
	public void insertMedia(Media media)
	{
		
		PreparedStatement 	pstatement;
		int		 			result;
		
		pstatement = null;
		//resultSet = null;
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("INSERT INTO Media (mediaID, releaseDate, genre, title, numCopiesAvailable) VALUES (?, ?, ?, ?, ?)");

			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setInt(1, media.getMediaID());
			pstatement.setDate(2, media.getReleaseDate());
			pstatement.setString(3, media.getGenre());
			pstatement.setString(4, media.getTitle());
			pstatement.setInt(5, media.getNumCopiesAvailable());
			
			result = pstatement.executeUpdate();
			
			if(media.getMediaType() == 'm')
			{
				
				pstatement = connection.prepareStatement("INSERT INTO Movies (movieID) VALUES (?)");
				
				// instantiate parameters
				pstatement.clearParameters();
				pstatement.setInt(1, media.getMediaID());
			
				result = pstatement.executeUpdate(); 
				
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
	
	public void insertMedia( List<Media> mediaList)
	{
		
		Media media;
		PreparedStatement 	pstatement;
		int		 			result;
		
		media = null;
		pstatement = null;
		//resultSet = null;
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
			
			for(int i = 0; i < mediaList.size(); i++)
			{
				
				pstatement = connection.prepareStatement("INSERT INTO Media (mediaID, releaseDate, genre, title, numCopiesAvailable) VALUES (?, ?, ?, ?, ?)");
				media = mediaList.get(i);
				// instantiate parameters
				pstatement.clearParameters();
				pstatement.setInt(1, media.getMediaID());
				pstatement.setDate(2, media.getReleaseDate());
				pstatement.setString(3, media.getGenre());
				pstatement.setString(4, media.getTitle());
				pstatement.setInt(5, media.getNumCopiesAvailable());
			
				result = pstatement.executeUpdate();
				
				System.out.println("3");
				
				if(media.getMediaType() == 'm')
				{

					pstatement = connection.prepareStatement("INSERT INTO Movies (movieID) VALUES (?)");
					
					// instantiate parameters
					pstatement.clearParameters();
					pstatement.setInt(1, media.getMediaID());
				
					result = pstatement.executeUpdate(); 
					
				}
				
				else if(media.getMediaType() == 'g')
				{

					pstatement = connection.prepareStatement("INSERT INTO Games (gameID, version, platform) VALUES (?, ?, ?)");
					
					// instantiate parameters
					pstatement.clearParameters();
					pstatement.setInt(1, media.getMediaID());
					pstatement.setDouble(2, media.getVersion());
					pstatement.setString(3, media.getPlatform());
					
				
					result = pstatement.executeUpdate(); 
					pstatement.close(); 
					
					
				}
				
				pstatement.close(); 
			
			}
			
			// ensure statement and connection are closed properly                                      
			//resultSet.close();                                      
			
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
	
//============================================================================
	
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
	
	public void deleteMedia(Integer mediaID)
	{
		
		
		
	}
	
//=============================================================================
	
	
	public void deleteMedia(List<Integer> mediaIDList)
	{
		
		
		
	}
	
//=============================================================================
	
	public void updateMedia(Media media)
	{
		
		
		
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
	
//=============================================================================
	
	public Media getMediaInformation(int mediaID)
	{
		
		List<String>			nameList;
		//List<Worker>			castList;
		Media					returnMedia;
		//Worker					worker;
		PreparedStatement 		pstatement;
		ResultSet 				resultSet;
		
		//castList = new ArrayList<Worker>();
		nameList = new ArrayList<String>();
		//worker = new Worker();
		pstatement = null;
		resultSet = null;
		returnMedia = null;
		
		
		try
		{
			
			//returnMedia = new Media();
			returnMedia = getMedia(mediaID);
			
			Connection connection = ConnectionManager.getConnection();
		
			
			//Get list of Actor Names
			pstatement = connection.prepareStatement("SELECT W.wname FROM Workers W, Works_On WO WHERE WO.movieID = ? AND WO.workerID = W.workerID AND W.isActor = 1");
			
			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setInt(1, mediaID);
			
			resultSet = pstatement.executeQuery();

			while ( resultSet.next() ) 
			{
					
				
				nameList.add(resultSet.getString("wname"));
				/*worker = new Worker();
				worker.setWorkerID(resultSet.getInt("workerID"));
				worker.setname(resultSet.getString("name"));
				worker.setIsActor(resultSet.getByte("isActor"));
				worker.setIsDirector(resultSet.getByte("isDirector"));
				castList.add(worker);*/
				
			} // end while
			
			returnMedia.setCastList(nameList);
			nameList = new ArrayList<String>();
			
			
			//Get list of Director Names
			pstatement = connection.prepareStatement("SELECT W.* FROM Workers W, Works_On WO WHERE WO.movieID = ? AND WO.workerID = W.workerID AND W.isDirector = 1");
			
			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setInt(1, mediaID);
			
			resultSet = pstatement.executeQuery();

			while ( resultSet.next() ) 
			{
					
				
				nameList.add(resultSet.getString("wname"));
				/*worker = new Worker();
				worker.setWorkerID(resultSet.getInt("workerID"));
				worker.setname(resultSet.getString("name"));
				worker.setIsActor(resultSet.getByte("isActor"));
				worker.setIsDirector(resultSet.getByte("isDirector"));
				castList.add(worker);*/
				
			} // end while
			
			returnMedia.setDirectorList(nameList);

			
			nameList = getSequels(mediaID);
			returnMedia.setSequelsList(nameList); 
			
			// ensure statement and connection are closed properly                                      
			resultSet.close();                                      
			pstatement.close();                                      
			connection.close();                       
		
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
		
		
		return returnMedia;
		
	}
	
//=============================================================================
	
	public List<String> getSequels(int mediaID)
	{
		
		List<String>			sequelList;
		PreparedStatement 		pstatement;
		ResultSet 				resultSet;
		int						sequelToMediaID;
		boolean					notEndOfSequelsBoolean;
		
		sequelList = new ArrayList<String>();
		pstatement = null;
		resultSet = null;
		notEndOfSequelsBoolean = false;
		
		
		try
		{
			
			
			Connection connection = ConnectionManager.getConnection();
			
			//This will change inside to loop in order to repeatedly retrieve
			//all media sequels. This needs to change because a media can 
			//indirectly be a sequel e.g. m3 is sequel of m2 and m2 is 
			//a sequel of m1. m3 is, therefore, a sequel of m1
			sequelToMediaID = mediaID;
			
			//This is a do while because the initial MediaID can be 0
			//and the result of a null value to mediaID is also 0.
			//Therefore, if sequelToMediaID is initialized to 0
			//The program will try to find the sequel to it.
			//The only problem is if a media with ID 0
			//is the sequel to another Media.
			do
			{
				notEndOfSequelsBoolean = false;
				//Get list of sequels
				pstatement = connection.prepareStatement("SELECT M.title, M.mediaID FROM Media M, Sequel S WHERE S.prequelID = ? AND S.sequelID = M.mediaID GROUP BY M.title");
			
				// instantiate parameters
				pstatement.clearParameters();
				pstatement.setInt(1, sequelToMediaID);
			
				resultSet = pstatement.executeQuery();

				while ( resultSet.next() ) 
				{
					
					sequelList.add(resultSet.getString("title"));
					sequelToMediaID = resultSet.getInt("mediaID");
					notEndOfSequelsBoolean = true;
					
				} // end while resultSet.next()
				
				
			
			}while( notEndOfSequelsBoolean );
			
			// ensure statement and connection are closed properly                                      
			resultSet.close();                                      
			pstatement.close();                                      
			connection.close();                       
		
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
		return sequelList;
		
	}
	
//=============================================================================
	
	
}

