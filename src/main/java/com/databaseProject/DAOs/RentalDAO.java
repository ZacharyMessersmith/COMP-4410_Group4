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
	
//=============================================================================
	
	public List<Rental> getUsersRentals(String email)
	{
		
		List<Rental>		rentalList;
		User				user;
		List<Media>			mediaList;
		List<Integer>		mediaIDList;
		Rental				rental;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		Date				nowDate;
		Date				yesterdayDate;
		UserDAO				userDAO;
		MediaDAO			mediaDAO;
		
		rentalList = new ArrayList<Rental>();
		user = new User();
		mediaList = new ArrayList<Media>();
		mediaIDList = new ArrayList<Integer>();
		rental = new Rental();
		pstatement = null;
		resultSet = null;
		nowDate = new Date(System.currentTimeMillis());;
		userDAO = new UserDAO();
		mediaDAO = new MediaDAO();
		
		java.util.Date date = new java.util.Date();
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(java.util.Calendar.DAY_OF_MONTH, -1);
		yesterdayDate =new Date( calendar.getTimeInMillis());
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT U.email, M.mediaID, RI.checkoutDate, RI.returnedDate FROM Rental_Info RI, Users U, Media M WHERE U.email = ? AND U.email = RI.email AND M.mediaID = RI.mediaID");
			
			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setString(1, email);
			
			resultSet = pstatement.executeQuery();

			while ( resultSet.next() ) 
			{
				
				rental = new Rental();
				rental.setDateRented(resultSet.getDate("checkoutDate"));
				rental.setDateReturned(resultSet.getDate("returnedDate"));
				rentalList.add(rental);
				mediaIDList.add( resultSet.getInt("mediaID"));
				
				
			} // end while
			
			user = userDAO.getUser(email);
			mediaList = mediaDAO.getMedia(mediaIDList);
		
			for(int i = 0; i < mediaList.size(); i++)
			{
				
				rentalList.get(i).setUser(user);
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
	
//=============================================================================

	
	public List<Media> getTop10MediaInLastMonth()
	{
		
		Media				media;
		List<Media>			mediaList;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		Date				nowDate;
		Date				lastMonthDate;
		MediaDAO			mediaDAO;
		
		media = new Media();
		mediaList = new ArrayList<Media>();
		pstatement = null;
		resultSet = null;
		nowDate = new Date(System.currentTimeMillis());
		
		//back 1 month
		java.util.Date date = new java.util.Date();
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(java.util.Calendar.MONTH, -1);
		lastMonthDate =new Date( calendar.getTimeInMillis());
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT M.*, COUNT(M.mediaID) AS Popularity FROM Rental_Info RI, Media M WHERE M.mediaID = RI.mediaID AND RI.checkoutDate <= ? AND RI.checkoutDate >= ? ORDER BY Popularity DESC LIMIT 10;");
			
			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setDate(1, nowDate);
			pstatement.setDate(2, lastMonthDate);
			
			resultSet = pstatement.executeQuery();

			while ( resultSet.next() ) 
			{
				media = new Media();
				media.setMediaID(resultSet.getInt("mediaID"));
				media.setGenre(resultSet.getString("genre"));
				media.setTitle(resultSet.getString("Title"));
				media.setNumCopiesAvailable(resultSet.getInt("numCopiesAvailable"));
				mediaList.add(media);
				
			} // end while
			
			
			// ensure statement and connection are closed properly                                      
			resultSet.close();                                      
			pstatement.close();                                      
			connection.close();                       
		
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
		
		
		return mediaList;
		
	}
	
//=============================================================================
}
