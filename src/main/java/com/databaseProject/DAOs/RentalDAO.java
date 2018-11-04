//Programmer: Kyle Savina
//Date: 11/3/2018
//Description: Contains Functions for retrieving rental information
//				form a database

package com.databaseProject.DAOs;

import com.databaseProject.DAOs.*;
import com.databaseProject.Pojos.*;
import com.databaseProject.databaseProject.ConnectionManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class RentalDAO 
{

	public RentalDAO()
	{
		
		//intentionally blank
		
	}
	
	public List<Rental> getRentalsWithinLast24Hours()
	{
		
		List<Rental>		rentalList;
		List<User>			userList;
		List<Media>			mediaList;
		List<String>		emailList;
		List<Integer>		mediaIDList;
		Rental				rental;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		Date				nowDate;
		Date				yesterdayDate;
		UserDAO				userDAO;
		MediaDAO			mediaDAO;
		
		rentalList = new ArrayList<Rental>();
		userList = new ArrayList<User>();
		mediaList = new ArrayList<Media>();
		emailList = new ArrayList<String>();
		mediaIDList = new ArrayList<Integer>();
		rental = new Rental();
		pstatement = null;
		resultSet = null;
		nowDate = new Date(System.currentTimeMillis());
		yesterdayDate = new Date(System.currentTimeMillis() - 86400000 );
		userDAO = new UserDAO();
		mediaDAO = new MediaDAO();
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT U.email, M.mediaID, RI.checkoutDate, RI.returnedDate FROM Rental_Info RI, Users U, Media M WHERE U.email = RI.email AND M.mediaID = RI.mediaID AND RI.checkoutDate <= ? AND RI.checkoutDate >= ?");
			
			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setDate(1, nowDate);
			pstatement.setDate(2, yesterdayDate);
			
			resultSet = pstatement.executeQuery();

			while ( resultSet.next() ) 
			{
				
				rental = new Rental();
				rental.setDateRented(resultSet.getDate("checkoutDate"));
				rental.setDateReturned(resultSet.getDate("returnedDate"));
				rentalList.add(rental);
				emailList.add(resultSet.getString("email"));
				mediaIDList.add( resultSet.getInt("mediaID"));
				
				
			} // end while
			
			userList = userDAO.getUsers(emailList);
			mediaList = mediaDAO.getMedia(mediaIDList);
			
			for(int i = 0; i < userList.size(); i++)
			{
				
				rentalList.get(i).setUser(userList.get(i));
				rentalList.get(i).setMedia(mediaList.get(i));
				
			}
			
			// ensure statement and connection are closed properly                                      
			resultSet.close();                                      
			pstatement.close();                                      
			connection.close();                       
		
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
		
		
		return rentalList;
		
		
	}
	
}
