package com.databaseProject.Pojos;

import java.sql.Date;

public class	Rental
{
	private User	user;
	private Media	media;
	private	Date	dateRented;
	private	Date	dateReturned;
	private Date	dueDate;
	private int		rentalID;

	public Rental(User user, Media media, Date dateRented, Date dateReturned, Date dueDate)
	{
	this.user = user;
	this.media = media;
	this.dateRented = dateRented;
	this.dateReturned = dateReturned;
	this.dueDate = dueDate;
	}
	
	public Rental()
	{
	user = null;
	media = null;
	dateRented = null;
	dateReturned = null;
	dueDate = null;
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
	
	public Date getDueDate()
	{
	return dueDate;
	}

	public void setDueDate(Date dueDate)
	{
	this.dueDate = dueDate;
	}
	
	public int getRentalID()
	{
	return rentalID;
	}

	public void setRentalID(int rentalID)
	{
	this.rentalID = rentalID;
	}


}