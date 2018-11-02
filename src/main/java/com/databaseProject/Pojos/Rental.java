package com.databaseProject.Pojos;

import java.sql.Date;

public class	Rental
{
	private User	user;
	private Media	media;
	private	Date	dateRented;
	private	Date	dateReturned;
	
	public Rental(User user, Media media, Date dateRented, Date dateReturned)
	{
	this.user = user;
	this.media = media;
	this.dateRented = dateRented;
	this.dateReturned = dateReturned;
	}

	public User getUser()
	{
	return user;
	}

	public void setUser(User user)
	{
	this.user = user;
	}

	public Media getMedia()
	{
	return media;
	}

	public void setMedia(Media media)
	{
	this.media = media;
	}

	public Date getDateRented()
	{
	return dateRented;
	}

	public void setDateRented(Date dateRented)
	{
	this.dateRented = dateRented;
	}

	public Date getDateReturned()
	{
	return dateReturned;
	}

	public void setDateReturned(Date dateReturned)
	{
	this.dateReturned = dateReturned;
	}

}