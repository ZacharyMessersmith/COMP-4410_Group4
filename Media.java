package com.databaseProject.databaseProject;

import java.sql.Date;
import java.util.List;

public class Media
{
	//mediaID, releaseDate, genre, title, numCopiesAvailable, version, platform, game or movie
	private int		mediaID;
	private Date	releaseDate;//might need a different data type for this
	private String	genre;
	private String	title;
	private int		numCopiesAvailable;
	private char	mediaType;
	private float	version;
	private String	platform;
	
	public boolean	isAvailable()
	{
	if (numCopiesAvailable > 0)
		return true;
	else
		return false;
	}
	
	// Assumes there's copies to available to rent
	public void	rentMedia()
	{
	numCopiesAvailable--;
	}
	
	public void	returnMedia()
	{
	numCopiesAvailable--;
	}
	
	
	public int getMediaID()
	{
	return mediaID;
	}
	
	public void setMediaID(int mediaID)
	{
	this.mediaID = mediaID;
	}
	
	public Date getReleaseDate()
	{
	return releaseDate;
	}
	
	public void setReleaseDate(Date releaseDate)
	{
	this.releaseDate = releaseDate;
	}
	
	public String getGenre()
	{
	return genre;
	}
	
	public void setGenre(String genre)
	{
	this.genre = genre;
	}
	
	public String getTitle()
	{
	return title;
	}
	
	public void setTitle(String title)
	{
	this.title = title;
	}
	
	public int getNumCopiesAvailable()
	{
	return numCopiesAvailable;
	}
	
	public void setNumCopiesAvailable(int numCopiesAvailable)
	{
	this.numCopiesAvailable = numCopiesAvailable;
	}
	
	public char getMediaType()
	{
	return mediaType;
	}
	
	public void setMediaType(char mediaType)
	{
	this.mediaType = mediaType;
	}
	
	public float getVersion()
	{
	return version;
	}
	
	public void setVersion(float version)
	{
	this.version = version;
	}
	
	public String getPlatform()
	{
	return platform;
	}
	
	public void setPlatform(String platform)
	{
	this.platform = platform;
	}
	
	
	

}