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
		
		List<Rental>	rentalList;
		RentalDAO		rentalDAO;
		
		rentalList = new ArrayList<Rental>();
		rentalDAO = new RentalDAO();
		
		rentalList = rentalDAO.getRentalsWithinLast24Hours();
		
		for(int i = 0; i < rentalList.size(); i++)
		{
			
			System.out.println("User: " + rentalList.get(i).getUser().getEmail());
			System.out.println("Media: " + rentalList.get(i).getMedia().getTitle());
			System.out.println("Date Rented: " + rentalList.get(i).getDateRented().toString());
			System.out.println("Date Returned: " + rentalList.get(i).getDateReturned().toString());
			
		}
		
		
	}
	
}
