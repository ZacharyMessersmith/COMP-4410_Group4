//Programmer: Kyle Savina
//Date: 10/25/2018
//Description: An object that accesses a database to retrieve
//				the information about a Media.


//import com.databaseProject.databaseProject.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
		//int					numOfColumns;
		
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
			pstatement.setInt(5, media.getNumOfCopiesAvailable());
			
			result = pstatement.executeUpdate();
			
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
		//int					numOfColumns;
		
		media = null;
		pstatement = null;
		//resultSet = null;
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("INSERT INTO Media (mediaID, releaseDate, genre, title, numCopiesAvailable) VALUES (?, ?, ?, ?, ?)");
			
			for(int i = 0; i < mediaList.size(); i++)
			{
				
				media = mediaList.get(i);
				// instantiate parameters
				pstatement.clearParameters();
				pstatement.setInt(1, media.getMediaID());
				pstatement.setDate(2, media.getReleaseDate());
				pstatement.setString(3, media.getGenre());
				pstatement.setString(4, media.getTitle());
				pstatement.setInt(5, media.getNumOfCopiesAvailable());
			
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
	
	public Media getMedia(Integer mediaID)
	{
		
		Media 				media;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		int					numOfColumns;
		
		media = new Media();
		pstatement = null;
		resultSet = null;
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT * FROM Media M WHERE M.mediaID = ?");
			
			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setInt(1, mediaID);
			
			resultSet = pstatement.executeQuery();
			
			
			// process query results
			ResultSetMetaData metaData = resultSet.getMetaData();
			int numberOfColumns = metaData.getColumnCount();

			while ( resultSet.next() ) 
			{
				
				for ( int i = 1; i <= numberOfColumns; i++ )
				{
					
					media.setMediaID(resultSet.getInt("mediaID"));
					media.setReleaseDate(resultSet.getDate("releaseDate"));
					media.setGenre(resultSet.getString("genre"));
					media.setTitle(resultSet.getString("title"));
					media.setNumOfCopiesAvailable(resultSet.getInt("numCopiesAvailable"));
					
				}
				
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
		
		return media;
		
		
	}
	
//=============================================================================
	
	public List<Media> getMedia(List<Integer> mediaIDList)
	{
		
		List<Media> 		mediaList;
		Media 				media;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		int					numOfColumns;
		
		mediaList = new ArrayList<Media>();
		media = new Media();
		pstatement = null;
		resultSet = null;
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT * FROM Media M WHERE M.mediaID = ?");
			
			for(int i = 0; i < mediaIDList.size(); i++)
			{
				
				pstatement.clearParameters();
				pstatement.setInt(1, mediaIDList.get(i));
			
				resultSet = pstatement.executeQuery();
			
			
				// process query results
				ResultSetMetaData metaData = resultSet.getMetaData();
				int numberOfColumns = metaData.getColumnCount();

				while ( resultSet.next() ) 
				{
				
					for ( int j = 1; j <= numberOfColumns; j++ )
					{
					
						media.setMediaID(resultSet.getInt("mediaID"));
						media.setReleaseDate(resultSet.getDate("releaseDate"));
						media.setGenre(resultSet.getString("genre"));
						media.setTitle(resultSet.getString("title"));
						media.setNumOfCopiesAvailable(resultSet.getInt("numCopiesAvailable"));
						mediaList.add(media);
					
					}
				
				} //end while
			
			}
			
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
	
	public List<Media> getAllMedia()
	{
		
		List<Media>			mediaList;
		Media 				media;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		int					numOfColumns;
		
		mediaList = new ArrayList<Media>();
		media = new Media();
		pstatement = null;
		resultSet = null;
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT * FROM Media M");
			
			// instantiate parameters
			pstatement.clearParameters();
			
			resultSet = pstatement.executeQuery();
			
			// process query results
			ResultSetMetaData metaData = resultSet.getMetaData();
			int numberOfColumns = metaData.getColumnCount();

			while ( resultSet.next() ) 
			{
			
				//for ( int i = 1; i <= numberOfColumns; i++ )
				//{
					
					media = new Media();
					media.setMediaID(resultSet.getInt("mediaID"));
					media.setReleaseDate(resultSet.getDate("releaseDate"));
					media.setGenre(resultSet.getString("genre"));
					media.setTitle(resultSet.getString("title"));
					media.setNumOfCopiesAvailable(resultSet.getInt("numCopiesAvailable"));
					
					mediaList.add(media);
					
				//}
				
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
	
}

