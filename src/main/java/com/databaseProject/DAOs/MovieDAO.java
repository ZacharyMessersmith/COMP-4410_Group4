//Programmer: Kyle Savina
//Date: 10/25/2018
//Description: An object that accesses a database to retrieve
//				the information about a Movie.


import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class MovieDAO extends MediaDAO
{

	
	
	public MovieDAO()
	{
		
		//Intentionally blank
		
	}
	
//=============================================================================
	
	public void insertMovie(Movie movie)
	{
		
		PreparedStatement 	pstatement;
		int		 			result;
		//int					numOfColumns;
		
		
		pstatement = null;
		//resultSet = null;


		
		try
		{
			
			insertMedia(movie);
			
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("INSERT INTO Movies (movieID) VALUES (?)");
			
			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setInt(1, movie.getMovieID());
		
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
	
	public void insertMovies( List<Movie> movieList)
	{
		
		Movie				movie;
		List<Media> 		mediaList;
		PreparedStatement 	pstatement;
		int		 			result;
		//int					numOfColumns;
		
		
		mediaList = new ArrayList<Media>();
		pstatement = null;
		//resultSet = null;
		
		
		for(int i =0; i < movieList.size(); i++)
		{
			
			mediaList.add(movieList.get(i).getMediaObject());
			
		}


		
		try
		{
			
			insertMedia(mediaList);
			
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("INSERT INTO Movies (movieID) VALUES (?)");
			
			for(int i = 0; i < movieList.size(); i++)
			{

				// instantiate parameters
				pstatement.clearParameters();
				pstatement.setInt(1, movieList.get(i).getMovieID());
		
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
	
	public Movie getMovie(int movieID)
	{
		
		Movie				movie;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		int					numOfColumns;
		
		movie = new Movie();
		pstatement = null;
		resultSet = null;
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT * FROM Media M, Movies M2 WHERE M.mediaID = ? AND M2.movieID = ?");
			
			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setInt(1, movieID);
			pstatement.setInt(2, movieID);
			
			resultSet = pstatement.executeQuery();
			
			
			// process query results
			ResultSetMetaData metaData = resultSet.getMetaData();
			int numberOfColumns = metaData.getColumnCount();

			while ( resultSet.next() ) 
			{
				
				//for ( int i = 1; i <= numberOfColumns; i++ )
				//{
					
					movie.setMovieID(resultSet.getInt("mediaID"));
					movie.setReleaseDate(resultSet.getDate("releaseDate"));
					movie.setGenre(resultSet.getString("genre"));
					movie.setTitle(resultSet.getString("title"));
					System.out.println("1");
					movie.setNumOfCopiesAvailable(resultSet.getInt("numCopiesAvailable"));
					
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
		
		
		
		return movie;
		
	}
	
//=============================================================================
	
	public List<Movie> getMovies(List<Integer> movieIDList)
	{
		
		List<Movie> 		movieList;
		Movie 				movie;
		List<Media>			mediaList;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		int					numOfColumns;
		
		mediaList = new ArrayList<Media>();
		movieList = new ArrayList<Movie>();
		movie = new Movie();
		pstatement = null;
		resultSet = null;
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT * FROM Media M, Movies M2 WHERE M.mediaID = ? AND M2.movieID = ?");
			
			for(int i = 0; i < movieIDList.size(); i++)
			{
				
				pstatement.clearParameters();
				pstatement.setInt(1, movieIDList.get(i));
				pstatement.setInt(2, movieIDList.get(i));
			
				resultSet = pstatement.executeQuery();
			
			
				// process query results
				ResultSetMetaData metaData = resultSet.getMetaData();
				int numberOfColumns = metaData.getColumnCount();

				while ( resultSet.next() ) 
				{
				
					//for ( int j = 1; j <= numberOfColumns; j++ )
					//{
						movie = new Movie();
						movie.setMediaID(resultSet.getInt("mediaID"));
						movie.setReleaseDate(resultSet.getDate("releaseDate"));
						movie.setGenre(resultSet.getString("genre"));
						movie.setTitle(resultSet.getString("title"));
						movie.setNumOfCopiesAvailable(resultSet.getInt("numCopiesAvailable"));
						movieList.add(movie);
					
					//}
				
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
		
		return movieList;
		
	}
	
//=============================================================================
	
	public List<Movie> getAllMovies()
	{
		//Temp
		List<Movie>			movieList;
		Movie 				movie;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		int					numOfColumns;
		
		movieList = new ArrayList<Movie>();
		movie = new Movie();
		pstatement = null;
		resultSet = null;
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT * FROM Media M, Movies M2 WHERE M.mediaID = M2.movieID");
			
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
					
					movie = new Movie();
					movie.setMovieID(resultSet.getInt("movieID"));
					movie.setReleaseDate(resultSet.getDate("releaseDate"));
					movie.setGenre(resultSet.getString("genre"));
					movie.setTitle(resultSet.getString("title"));
					movie.setNumOfCopiesAvailable(resultSet.getInt("numCopiesAvailable"));
					
					movieList.add(movie);
					
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
		
		
		
		return movieList;
		
	}
	
//=============================================================================
	
	public void deleteMovie(String email)
	{
		
		
		
	}
	
//=============================================================================
	
	
	public void deleteMovies(List<String> emailList)
	{
		
		
		
	}
	
//=============================================================================
	
	public void updateMovie(Movie movie)
	{
		
		
		
	}
	
//=============================================================================
	
	public void updateMovies(List<Movie> movieList)
	{
		
		
		
	}
	
//=============================================================================

	
	public List<String> getAllMovieTitles()
	{
		
		List<Movie> 	movieList;
		List<String>	titleList;
		
		titleList = new ArrayList<String>();
		movieList = getAllMovies();
		
		for(int i = 0; i < movieList.size(); i++)
		{
			
			titleList.add(movieList.get(i).getTitle());
			
		}
		
		return titleList;
		
		
	}
	
}

