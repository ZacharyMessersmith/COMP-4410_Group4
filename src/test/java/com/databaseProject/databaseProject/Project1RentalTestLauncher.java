//Programmer: Kyle Savina
//Date: 10/25/2018
//Description: A POJO that hold information about media

package com.databaseProject.databaseProject;

import com.databaseProject.DAOs.*;
import com.databaseProject.Pojos.*;
import java.util.List;
import java.util.ArrayList;
import java.sql.Date;

//#############################################################################
class Project1RentalTestLauncher
{
	
//=============================================================================
	
	public static void main(String[] args)
	{
		
		System.out.println("Starting Application... \n");
		
		rentalDAOTest();
		//mediaDAOTest();
		//movieDAOTest();
		//gameDAOTest();
		
		System.out.println("Done.");
		
	}//end main()
	
//=============================================================================

	public static void rentalDAOTest()
	{
		
		List<Media>		mediaList;
		List<Rental>	rentalList;
		RentalDAO		rentalDAO;
		MediaDAO		mediaDAO;
		
		rentalList = new ArrayList<Rental>();
		rentalDAO = new RentalDAO();
		mediaDAO = new MediaDAO();
		
		rentalList = rentalDAO.getRentalsWithinLast24Hours();
		
		System.out.println("Testing Getting rentals withing 24 hours");
		for(int i = 0; i < rentalList.size(); i++)
		{
			
			System.out.println("User: " + rentalList.get(i).getUser().getEmail());
			System.out.println("Media: " + rentalList.get(i).getMedia().getTitle());
			System.out.println("Date Rented: " + rentalList.get(i).getDateRented().toString());
			System.out.println("Date Returned: " + rentalList.get(i).getDateReturned().toString());
			
		}
		
		System.out.println("Testing getting all the rentals of a user given an email");
		
		rentalList.clear();
		rentalList = new ArrayList<Rental>();
		rentalList = rentalDAO.getUsersRentals("Bala.Stella@hotmail.com");
		System.out.println("User: " + rentalList.get(0).getUser().getEmail());
		for(int i = 0; i < rentalList.size(); i++)
		{

			System.out.println("Media: " + rentalList.get(i).getMedia().getTitle());
			System.out.println("Date Rented: " + rentalList.get(i).getDateRented().toString());
			System.out.println("Date Returned: " + rentalList.get(i).getDateReturned().toString());
			
		}
		
		mediaList = rentalDAO.getTop10MediaInLastMonth();
		
		for(int i = 0; i < mediaList.size(); i++)
		{

			System.out.println("Media Top 10 Number " + (i + 1) + ": " + mediaList.get(i).getTitle());
			
		}
		
	}
	
}
