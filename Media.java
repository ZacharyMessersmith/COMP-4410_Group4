//Programmer: Kyle Savina
//Date: 10/25/2018
//Description: A POJO that hold information about media

import java.sql.*;

public class Media 
{

	Integer mediaID;
	Date releaseDate;
	String genre;
	String title;
	Integer numOfCopiesAvailable;
	
	public Media()
	{
		
		mediaID= null;
		releaseDate = null;
		genre = null;
		title = null;
		numOfCopiesAvailable = null;
		
	}

//=============================================================================

	public void setMediaID(Integer mediaID)
	{
		
		this.mediaID = mediaID;
		
	}
	
//=============================================================================

	public Integer getMediaID()
	{
		
		return this.mediaID;
		
	}
	
//=============================================================================

	public void setReleaseDate(Date releaseDate)
	{
		
		this.releaseDate = releaseDate;
		
	}
	
//=============================================================================

	public Date getReleaseDate()
	{
		
		return this.releaseDate;
		
	}
	
//=============================================================================

	public void setGenre(String genre)
	{
		
		this.genre = genre;
		
	}
	
//=============================================================================

	public String getGenre()
	{
		
		return this.genre;
		
	}
	
//=============================================================================

	public void setTitle(String title)
	{
		
		this.title = title;
		
	}
	
//=============================================================================

	public String getTitle()
	{
		
		return this.title;
		
	}
	
//=============================================================================

	public void setNumOfCopiesAvailable(Integer numOfCopiesAvailable)
	{
		
		this.numOfCopiesAvailable = numOfCopiesAvailable;
		
	}
	
//=============================================================================

	public Integer getNumOfCopiesAvailable()
	{
		
		return this.numOfCopiesAvailable;
		
	}
	
//=============================================================================
}