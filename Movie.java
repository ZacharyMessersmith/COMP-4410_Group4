//Programmer: Kyle Savina
//Date: 10/25/2018
//Description: An extended POJO of Media that represents a movie.


public class Movie extends Media 
{

	public Movie()
	{
		
		//Intentionally blank.
		
	}
	
	
//========================================================================

	public void setMovieID(Integer movieID)
	{
		
		setMediaID(movieID);
		
	}
	
//=============================================================================

	public Integer getMovieID()
	{
		
		return getMediaID();
		
	}
	
//=============================================================================

	public Media getMediaObject()
	{
		
		Media media = new Media();
		
		media.setMediaID(getMovieID());
		media.setReleaseDate(getReleaseDate());
		media.setGenre(getGenre());
		media.setTitle(getTitle());
		media.setNumOfCopiesAvailable(getNumOfCopiesAvailable());
		
		return media;
		
	}

//=============================================================================
	
}
