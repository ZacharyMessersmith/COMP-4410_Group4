package com.databaseProject.databaseProject;

import java.util.List;

public class User
{
	private String	email;
	private String	name;
	private String	phoneNumber;
	private String	password;
	private String	streetAddress;
	private String	city;
	private String	state;
	private int		zipCode;
	private int		maxNumRentals;
	private List<Media>	currentRentals;
	private int		currentNumRentals;
	
	boolean	isAtRentalMax()
	{
	if (currentNumRentals < currentRentals.size())
		return false;
	else
		return true;
	}
	
	//Assumes that the user isn't already at the max number of rentals
	void	rentMedia(Media media)
	{
	currentRentals.add(media);
	currentNumRentals++;
	//Update database
	}
	
	
	void	returnMedia(Media media)
	{
	if (currentRentals.contains(media))
		{
		currentRentals.remove(media); // Not sure if this will work
		currentNumRentals--;
		//Update database
		}
	else
		System.out.println("The user didn't rent this Media.");
		// Need a better error.... a popup maybe?
	}
	
	
	public String getEmail()
	{
	return email;
	}
	
	public void setEmail(String email)
	{
	this.email = email;
	}
	
	public String getName()
	{
	return name;
	}
	
	public void setName(String name)
	{
	this.name = name;
	}
	
	public String getPhoneNumber()
	{
	return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber)
	{
	this.phoneNumber = phoneNumber;
	}
	
	public String getPassword()
	{
	return password;
	}
	
	public void setPassword(String password)
	{
	this.password = password;
	}
	
	public String getStreetAddress()
	{
	return streetAddress;
	}
	
	public void setStreetAddress(String streetAddress)
	{
	this.streetAddress = streetAddress;
	}
	
	public String getCity()
	{
	return city;
	}
	
	public void setCity(String city)
	{
	this.city = city;
	}
	
	public String getState()
	{
	return state;
	}
	
	public void setState(String state)
	{
	this.state = state;
	}
	
	public int getZipCode()
	{
	return zipCode;
	}
	
	public void setZipCode(int zipCode)
	{
	this.zipCode = zipCode;
	}
	
	public int getMaxNumRentals()
	{
	return maxNumRentals;
	}
	
	public void setMaxNumRentals(int maxNumRentals)
	{
	this.maxNumRentals = maxNumRentals;
	}
	
	public List<Media> getCurrentRentals()
	{
	return currentRentals;
	}
	
	public void setCurrentRentals(List<Media> currentRentals)
	{
	this.currentRentals = currentRentals;
	}
	
	public int getCurrentNumRentals()
	{
	return currentNumRentals;
	}
	
	public void setCurrentNumRentals(int currentNumRentals)
	{
	this.currentNumRentals = currentNumRentals;
	}
		
}