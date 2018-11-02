//Programmer: Kyle Savina
//Date: 10/25/2018
//Description: A POJO that hold information about movie


import java.util.List;
import java.util.ArrayList;
import java.sql.Date;

//#############################################################################
class Project1TestMovieLauncher
{
	
//=============================================================================
	
	public static void main(String[] args)
	{
		
		System.out.println("Starting Application... \n");
		
		movieDAOTest();
		//movieDAOTest();
		//movieDAOTest();
		//gameDAOTest();
		
		System.out.println("Done.");
		
	}//end main()
	
//=============================================================================

	public static void movieDAOTest()
	{
		
		Movie				movieInsert;
		Movie				movieRetrieve;
		Movie				tempMovie;
		Date				releaseDate;
		List<Movie> 		movieListInsert;
		List<Movie>			movieListRetrieve;
		List<Integer>		movieIDList;
		MovieDAO 			movieDAO;
		byte				falseByte;
		byte				trueByte;
		
		
		falseByte = 0;
		trueByte = 1;
		releaseDate = new Date(System.currentTimeMillis());
		movieRetrieve = new Movie();
		movieListInsert = new ArrayList<Movie>();
		movieListRetrieve = new ArrayList<Movie>();
		movieIDList = new ArrayList<Integer>();
		
		int i;
		for(i = 0; i < 10; i++)
		{
			
			movieInsert = new Movie();
			movieIDList.add(new Integer(i));
			releaseDate.setTime(System.currentTimeMillis());
			movieInsert.setMovieID(i);
			movieInsert.setReleaseDate(releaseDate);
			movieInsert.setGenre("genre" + i);
			movieInsert.setTitle("Movie" + i);
			movieInsert.setNumOfCopiesAvailable(4);
			
			movieListInsert.add(movieInsert);
			
		}
		
		
		movieInsert = new Movie();
		releaseDate.setTime(System.currentTimeMillis());
		movieInsert.setMovieID(i);
		movieInsert.setReleaseDate(releaseDate);
		movieInsert.setGenre("genreCustom");
		movieInsert.setTitle("MovieCustom");
		movieInsert.setNumOfCopiesAvailable(3);
		
		System.out.println("Creating MovieDAO");
		movieDAO = new MovieDAO();
		System.out.println("Created MovieDAO");
		
		System.out.println("Manually adding single movie");
		movieDAO.insertMovie(movieInsert);
		System.out.println("Successfully added single movie");
		
		System.out.println("Adding list of movie");
		movieDAO.insertMovies(movieListInsert);
		System.out.println("successfully added list of movie");
		
		System.out.println("Getting single movie");
		movieRetrieve = movieDAO.getMovie(i);
		System.out.println("Got movie. Compare to input.");
		
		System.out.println("Inserted: " + movieInsert.getTitle());
		System.out.println("Retrieved: " + movieRetrieve.getTitle());
		
		System.out.println("Compared. Compare list of movie Iserted and Retrieved alternating");
	
		/*	
		System.out.println("Compared. Testing to see if movie exists.");
	
		if( false == movieDAO.testMovie("DoesNotExist@email.test" ,  "DoesNotExist"))
		{
		
			System.out.println("DoesNotExist@email.test with password DoesNotExist does not exist");
		
		}
		else
		{
			
			System.out.println("DoesNotExist@email.test with password DoesNotExist exists");
			
		}
		
		
		if( false ==  movieDAO.testMovie("ManualAdd@email.test" ,  "ManualAddPassword"))
		{
			
			System.out.println("ManualAdd@email.test with password ManualAddPassword does not exist");
			
		}
		
		else
		{
			
			System.out.println("ManualAdd@email.test with password ManualAddPassword  exists");
			
		}
		*/
		System.out.println("Retrieving list of movie. Returning count of movie retrieved");
		
		movieListRetrieve = movieDAO.getMovies(movieIDList);
		System.out.println("Size = " + movieListRetrieve.size());
		
		System.out.println("Done retrieving list of movie. Testing retrieving all movie. Returning count of movie retrieved.");
		
		movieListRetrieve.clear();
		movieListRetrieve = movieDAO.getAllMovies();
		System.out.println("Size = " + movieListRetrieve.size());
		
		List<String> titleList = movieDAO.getAllMovieTitles();
		
		for(i = 0; i < titleList.size(); i++)
		{
			
			System.out.println(titleList.get(i));
			
		}
		
		System.out.println("Done getting all movie.");
		/*
		System.out.println("Testing updating movie information");
		
		movieInsert.setEmail("ManualAdd2@email.test2");
		movieInsert.setPassword("ManualAddPassword2");
		//implies no change
		movieInsert.setPhoneNum(null);
		movieInsert.setName("ManualAdd2");
		movieDAO.updateMovie(movieInsert);
		movieRetrieve = movieDAO.getMovie("ManualAdd2@email.test2", "ManualAddPassword2");
		
		movieInsert.setEmail(null);
		movieInsert.setPassword(null);
		movieInsert.setPhoneNum("Man-ual-Add3");
		movieInsert.setName(null);
		movieDAO.updateMovie(movieInsert);
		movieRetrieve = movieDAO.getMovie("ManualAdd2@email.test2", "ManualAddPassword2");
		movieRetrieve.toString();
		
		System.out.println("Done testing update a single movie. Testing list of movie");
		
		for( int i = 10; i < 20; i++)
		{
			
			tempMovie = new Movie();
			emailList.set(i-10, "Movie" + i + "@email.test");
			passwordList.set(i-10, "Password" + i);
			tempMovie.setEmail("Movie" + i + "@email.test");
			tempMovie.setPassword("Password" + i);
			tempMovie.setName("Name" + i);
			tempMovie.setPhoneNum("Pho-Num-ber" + i);
			tempMovie.setIsMember(1);
			tempMovie.setIsAdmin(1);
			movieListInsert.set(i - 10, tempMovie);
		
		}
		
		movieDAO.updateMovie(movieListInsert);
		movieListRetrieve = movieDAO.getMovie(emailList, passwordList);
		
		for(int i = 0; i < 10; i++)
		{
			
			System.out.println(movieListRetrieve.get(i).getEmail());
			
		}
		
		System.out.println("Done Testing updates on a list.");
		
		
		System.out.println("Testing Deleting on a movie");
		
		try
		{
			
			movieDAO.deleteMovie("ManualAdd@email.test");
			movieRetrieve = movieDAO.getMovie("ManualAdd@email.test", "ManualAddPassword");
			
			movieRetrieve.toString();
			
			
		}
		
		catch(Exception e)
		{
			
			System.out.println("Failed in deleting single movie. Success");
			
		}
		
		System.out.println("Done Testing deleting single movie. Testing deleting list of movie.");
		
		try
		{
			
			movieDAO.deleteMovie(emailList);
			movieDAO.getMovie(emailList, passwordList);
			
		}
		
		catch(Exception e)
		{
			
			System.out.println("Falure in deleting list of movie");
			
		}
		
		System.out.println("Done testing delete movie list.");
		*/
		
		System.out.println("Done testing movieDAO.");
		
		
	}
	
//=============================================================================
	
}

//#############################################################################