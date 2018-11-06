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

