package com.databaseProject.Pojos;

import java.util.ArrayList;
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
	private int		numRentalsAvailable; //number of rentals available
	private	boolean	isAdmin;
	private	boolean	isUser;
	private String	plan;
	
	@Override
	public String toString()
	{
	return "User [email=" + email + ", name=" + name + ", phoneNumber=" + phoneNumber + ", password=" + password
			+ ", streetAddress=" + streetAddress + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + "]";
	}
	
	public String getPlan()
	{
	return plan;
	}

	public void setPlan(String plan)
	{
	this.plan = plan;
	}
	
	public User(String email, String name, String phoneNumber, String password, String streetAddress, String city,
			String state, int zipCode, boolean isAdmin, boolean isUser)
	{
	this.email = email;
	this.name = name;
	this.phoneNumber = phoneNumber;
	this.password = password;
	this.streetAddress = streetAddress;
	this.city = city;
	this.state = state;
	this.zipCode = zipCode;
	this.isAdmin = isAdmin;
	this.isUser = isUser;
	}
	
	
	public User()
	{
		
		email = null;
		name = null;
		phoneNumber = null;
		password = null;
		streetAddress = null;
		city = null;
		state = null;
		zipCode = 0;
		isAdmin = false;
		isUser = false;
		maxNumRentals = 0;
		numRentalsAvailable = 0;
		currentRentals = new ArrayList<Media>();
		plan = null;
		
		
	}
	



	public boolean isAdmin()
	{
	return isAdmin;
	}

	public void setAdmin(boolean isAdmin)
	{
	this.isAdmin = isAdmin;
	}

	public boolean isUser()
	{
	return isUser;
	}

	public void setUser(boolean isUser)
	{
	this.isUser = isUser;
	}

	boolean	isAtRentalMax()
	{
	if (numRentalsAvailable < currentRentals.size())
		return false;
	else
		return true;
	}
	
	//Assumes that the user isn't already at the max number of rentals
	void	rentMedia(Media media)
	{
	currentRentals.add(media);
	numRentalsAvailable++;
	//Update database
	}
	
	
	void	returnMedia(Media media)
	{
	if (currentRentals.contains(media))
		{
		currentRentals.remove(media); // Not sure if this will work
		numRentalsAvailable--;
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
	
	public int getNumRentalsAvailable()
	{
	return numRentalsAvailable;
	}
	
	public void setNumRentalsAvailable(int currentNumRentals)
	{
	this.numRentalsAvailable = currentNumRentals;
	}
		
}