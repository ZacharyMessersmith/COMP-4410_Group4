//Programmer: Kyle Savina
//Date: 10/25/2018
//Description: An object that accesses a database to retrieve
//				the information about a user.

package com.databaseProject.DAOs;
import com.databaseProject.Pojos.User;
import com.databaseProject.databaseProject.ConnectionManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import java.util.ArrayList;

public class UserDAO 
{
	public UserDAO()
	{
		
		//Intentionally blank.
		
	}

//=============================================================================
	
	public void deleteUser(User user)
	{
	PreparedStatement 	pstatement;
	
	pstatement = null;
	
	try
	{
		Connection connection = ConnectionManager.getConnection();

		if (!hasRented(user.getEmail()))
			{
			// Leave the address in the Addresses table
			pstatement = connection.prepareStatement("DELETE FROM Has_Plan WHERE email = ?");
			pstatement.clearParameters();
			pstatement.setString(1, user.getEmail());
			pstatement.executeUpdate(); 
			
			pstatement = connection.prepareStatement("DELETE FROM Lives_At1 WHERE email = ?");
			pstatement.clearParameters();
			pstatement.setString(1, user.getEmail());
			pstatement.executeUpdate(); 
			
			pstatement = connection.prepareStatement("DELETE FROM Users WHERE email = ?");
			pstatement.clearParameters();
			pstatement.setString(1, user.getEmail());
			pstatement.executeUpdate();  
			
			pstatement.close();                                      
			connection.close();                       
			}
		else
			JOptionPane.showMessageDialog(null, "You cannot delete a user that has rented an item.", "Error", JOptionPane.ERROR_MESSAGE); 
	}
			
	catch(SQLException sqle)
	{
		
		System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
		
	}
		
	}
	
	
	
//=============================================================================	
	
	private boolean	hasRented(String email)
	{
	PreparedStatement 	pstatement;
	ResultSet			resultSet;
	boolean				hasRented;
	
	pstatement = null;
	hasRented = false;
	
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT * FROM Rental_Info WHERE email = ?");
			
			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setString(1, email);
			
			resultSet = pstatement.executeQuery();
	
			while ( resultSet.next() ) 
			{
				hasRented = true;	
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
		
		return hasRented;
	
	}
	
//=============================================================================
	
	public void insertUser(User user)
	{
		
		PreparedStatement 	pstatement;
		byte				trueFalseByte;
		
		pstatement = null;
		//resultSet = null;
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("INSERT INTO Users (email, name, phoneNum, password, isMember, isAdmin, numRentalsAvailable) VALUES (?, ?, ?, ?, ?, ?, ?)");

			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setString(1, user.getEmail());
			pstatement.setString(2, user.getName());
			pstatement.setString(3, user.getPhoneNumber());
			pstatement.setString(4, user.getPassword());
			
			if(true == user.isUser())
				trueFalseByte = 1;
			else
				trueFalseByte = 0;
			
			pstatement.setByte(5,trueFalseByte);
			
			if(true == user.isUser())
				trueFalseByte = 1;
			else
				trueFalseByte = 0;
			
			pstatement.setByte(6, trueFalseByte);
			
			pstatement.setInt(7, getMaxNumRentalsForPlan(Integer.parseInt(user.getPlan())));
			
			pstatement.executeUpdate();
			
			// ensure statement and connection are closed properly                                                                           
			pstatement.close();                                      
			connection.close();                       
		
			insertAddressForUser(user);
			insertPlanForUser(user);
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
	}
	
//=============================================================================
	
	public void insertPlanForUser(User user)
	{
		PreparedStatement 	pstatement;
		byte				trueFalseByte;
		int					planId;
		
		pstatement = null;		
		
		try
		{
			planId = Integer.parseInt(user.getPlan());
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("INSERT INTO Has_Plan (email, planID) VALUES (?, ?)");

			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setString(1, user.getEmail());
			pstatement.setInt(2, planId);
			
			pstatement.executeUpdate();                                                                           
			pstatement.close();                                      
			connection.close();                       
		
		}
		
		catch(SQLException sqle)
		{
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
		}
		
		catch(NumberFormatException e)
		{
		e.printStackTrace();
		}
		
	}
	
//=============================================================================

		
	public void insertAddressForUser(User user)
	{
		PreparedStatement 	pstatement;
		byte				trueFalseByte;
		
		pstatement = null;		
		
		try
		{
			if (!addressExists(user.getStreetAddress(), user.getZipCode()))
				createAddress(user);
		
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("INSERT INTO Lives_At1 (email, street, zip) VALUES (?, ?, ?)");

			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setString(1, user.getEmail());
			pstatement.setString(2, user.getStreetAddress());
			pstatement.setInt(3, user.getZipCode());
			
			pstatement.executeUpdate();                                                                           
			pstatement.close();                                      
			connection.close();                       
		
		}
		
		catch(SQLException sqle)
		{
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
		}
		
	}
	
//=============================================================================	
	
	public void createAddress(User user)
	{
		PreparedStatement 	pstatement;
		byte				trueFalseByte;
		
		pstatement = null;		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("INSERT INTO Addresses (street, zip, city, state) VALUES (?, ?, ?, ?)");

			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setString(1, user.getStreetAddress());
			pstatement.setInt(2, user.getZipCode());
			pstatement.setString(3, user.getCity());
			pstatement.setString(4, user.getState());
			
			pstatement.executeUpdate();                                                                           
			pstatement.close();                                      
			connection.close();                       
		
		}
		
		catch(SQLException sqle)
		{
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
		}
		
	}
	
//=============================================================================
	
	public boolean	addressExists(String streetAddress, int zipCode)
	{
	boolean	addressExists;
	PreparedStatement pstatement;
	Connection connection;
	ResultSet	resultSet;
	
	addressExists = false;
	
	try
		{
		connection = ConnectionManager.getConnection();
		
		pstatement = connection.prepareStatement("SELECT * FROM Addresses a WHERE a.street = ? AND a.zip = ?");
		
		// instantiate parameters
		pstatement.clearParameters();
		pstatement.setString(1, streetAddress);
		pstatement.setInt(2, zipCode);
	
		// query database
		resultSet = pstatement.executeQuery();
		
		while ( resultSet.next() ) 
			{
			addressExists = true;
			}
		
		resultSet.close();                                      
		pstatement.close();  
		connection.close();                       
		}
	
	catch(SQLException sqle)
		{
		System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
		}
	
	return addressExists;
	}
	
//=============================================================================
	
	public void insertUsers(List<User> userList)
	{
		for (int i=0; i < userList.size(); i++)
			insertUser(userList.get(i));
	}
	
//=============================================================================

	//returns true if a user with this username and password exists in the database.
	public boolean testUser(String email, String password)
	{
		boolean exists = false;
		
		if(null != getUser(email, password).getEmail())
		{
			exists = true;
		}
		return exists;
	}
	
//=============================================================================
	
	public boolean userExists(String email)
	{
		boolean exists = false;
		
		User user = getUser(email);
		
		if(user != null)
			{
			if (user.getEmail() != null)
				{
				if (!email.equals(user.getEmail()))
					exists = true;
				}
			}
		
		return exists;
	}
	
//=============================================================================

//gets a users information
	public User getUser(String email)
	{
		
		User 				user;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		byte				trueFalseByte;
		
		user = new User();
		pstatement = null;
		resultSet = null;
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT * FROM Users U WHERE U.email = ?");
			
			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setString(1, email);
			
			resultSet = pstatement.executeQuery();

			while ( resultSet.next() ) 
			{
				
				user.setEmail(resultSet.getString("email"));
				user.setName(resultSet.getString("name"));
				user.setPhoneNumber(resultSet.getString("phoneNum"));
				user.setPassword(resultSet.getString("password"));
				user.setPlan(Integer.toString(getPlanIDForUser(user.getEmail())));
				user.setMaxNumRentals(getMaxNumRentalsForPlan(Integer.parseInt(user.getPlan())));
				user.setNumRentalsAvailable(resultSet.getInt("numRentalsAvailable"));
				
				setUserAddress(user);
				
			
			if(1 == resultSet.getByte("isMember"))
				user.setUser(true);
			else
				user.setUser(false);
			
			if(1 == resultSet.getByte("isAdmin"))
				user.setAdmin(true);
			else
				user.setAdmin(false);
				
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
		
		return user;
	}

//=============================================================================
	
	//gets a users information
	public User getUser(String email, String password)
	{
		User 				user;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		
		user = new User();
		pstatement = null;
		resultSet = null;
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT * FROM Users U WHERE U.email = ? AND BINARY U.password = ?");
			
			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setString(1, email);
			pstatement.setString(2, password);
			
			resultSet = pstatement.executeQuery();

			while ( resultSet.next() ) 
			{
					
				user.setEmail(resultSet.getString("email"));
				user.setName(resultSet.getString("name"));
				user.setPhoneNumber(resultSet.getString("phoneNum"));
				user.setPassword(resultSet.getString("password"));
				user.setPlan(Integer.toString(getPlanIDForUser(user.getEmail())));
				user.setMaxNumRentals(getMaxNumRentalsForPlan(Integer.parseInt(user.getPlan())));
				user.setNumRentalsAvailable(resultSet.getInt("numRentalsAvailable"));
				
				setUserAddress(user);
				
				if(1 == resultSet.getByte("isMember"))
					user.setUser(true);
				else
					user.setUser(false);
				
				if(1 == resultSet.getByte("isAdmin"))
					user.setAdmin(true);
				else
					user.setAdmin(false);
				
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
		
		return user;
	}


//=============================================================================
	
	public List<User> getUsers(List<String> emailList)
	{	
	List<User> 			userList;
	
	userList = new ArrayList<User>();

	for(int i = 0; i < emailList.size(); i++)
		{
		userList.add(getUser(emailList.get(i)));
		}
	
	return userList;
	}
		
	
//=============================================================================
	
	//emailList and passwordList should be the same size and assumes that the
	//kth element in emailList corresponds to the kth element in passwordList
	public List<User> getUsers(List<String> emailList, List<String> passwordList)
	{
		
		List<User> 			userList;
		User 				user;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		
		userList = new ArrayList<User>();
		user = new User();
		pstatement = null;
		resultSet = null;
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT * FROM Users U WHERE U.email = ? AND U.password = ?");
			
			for(int i = 0; i < emailList.size(); i++)
			{
				
				// instantiate parameters
				pstatement.clearParameters();
				pstatement.setString(1, emailList.get(i));
				pstatement.setString(2, passwordList.get(i));
			
				resultSet = pstatement.executeQuery();

				while ( resultSet.next() ) 
				{
						
				user = new User();
				user.setEmail(resultSet.getString("email"));
				user.setName(resultSet.getString("name"));
				user.setPhoneNumber(resultSet.getString("phoneNum"));
				user.setPassword(resultSet.getString("password"));
				user.setPlan(Integer.toString(getPlanIDForUser(user.getEmail())));
				user.setMaxNumRentals(getMaxNumRentalsForPlan(Integer.parseInt(user.getPlan())));
				user.setNumRentalsAvailable(resultSet.getInt("numRentalsAvailable"));
				
				setUserAddress(user);
				
				if(0 == resultSet.getByte("isMember"))
					user.setUser(false);
				else
					user.setUser(true);
				
				if(1 == resultSet.getByte("isAdmin"))
					user.setAdmin(true);
				else
					user.setAdmin(false);
				
				userList.add(user);
				
				} // end while
			
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
		
		return userList;
		
	}
	
//=============================================================================
	
	public List<User> getAllUsers()
	{
		
		List<User> 			userList;
		User 				user;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		
		userList = new ArrayList<User>();
		user = new User();
		pstatement = null;
		resultSet = null;
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT * FROM Users");
			// instantiate parameters
			pstatement.clearParameters();
			
			resultSet = pstatement.executeQuery();

			while ( resultSet.next() ) 
			{
				user = new User();
				user.setEmail(resultSet.getString("email"));
				user.setName(resultSet.getString("name"));
				user.setPhoneNumber(resultSet.getString("phoneNum"));
				user.setPassword(resultSet.getString("password"));
				user.setPlan(Integer.toString(getPlanIDForUser(user.getEmail())));
				user.setMaxNumRentals(getMaxNumRentalsForPlan(Integer.parseInt(user.getPlan())));
				user.setNumRentalsAvailable(resultSet.getInt("numRentalsAvailable"));
				
				setUserAddress(user);
				
				if(0 == resultSet.getByte("isMember"))
					user.setUser(false);
				else
					user.setUser(true);
				
				if(1 == resultSet.getByte("isAdmin"))
					user.setAdmin(true);
				else
					user.setAdmin(false);
				
				userList.add(user);
				
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
		
		return userList;
		
	}
	
//=============================================================================
	
	public List<String> getAllPlanNames()
	{
	PreparedStatement 	pstatement;
	ResultSet 			resultSet;
	List<String>		planIDs;
	
	pstatement = null;
	planIDs = new ArrayList<String>();
	try
		{
		Connection connection = ConnectionManager.getConnection();
	
		pstatement = connection.prepareStatement("SELECT * FROM Plans");

		// query database
		resultSet = pstatement.executeQuery();
		
		
		while ( resultSet.next() ) 
			{
			planIDs.add(Integer.toString(resultSet.getInt("planID")));
			}
		
		// ensure statement and connection are closed properly                                      
		resultSet.close();                                      
		pstatement.close();                                      
		connection.close();                       
	
		return planIDs;
		}
	
	catch(SQLException sqle)
		{
		System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
		return planIDs;
		}
	}	
	
//=============================================================================
	
	public int getPlanIDForUser(String email)
	{
	PreparedStatement 	pstatement;
	ResultSet 			resultSet;
	int					planID;
	
	pstatement = null;
	planID = -1;
	
	try
		{
		Connection connection = ConnectionManager.getConnection();
	
		pstatement = connection.prepareStatement("SELECT hp.planID FROM Has_Plan hp WHERE hp.email = ?");

		// instantiate parameters
		pstatement.clearParameters();
		pstatement.setString(1, email);

		// query database
		resultSet = pstatement.executeQuery();
		
		while ( resultSet.next() ) 
			{
			planID = resultSet.getInt("planID");
			}
		
		// ensure statement and connection are closed properly                                      
		resultSet.close();                                      
		pstatement.close();                                      
		connection.close();                       
	
		return planID;
		}
	
	catch(SQLException sqle)
		{
		System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
		return -1;
		}
	}	

//=============================================================================
	
	public int getMaxNumRentalsForPlan(int planID)
	{
	PreparedStatement 	pstatement;
	ResultSet 			resultSet;
	int					maxNumRentals;
	
	pstatement = null;
	maxNumRentals = -1;
	
	try
		{
		Connection connection = ConnectionManager.getConnection();
	
		pstatement = connection.prepareStatement("SELECT * FROM Plans p WHERE p.planID = ?");

		// instantiate parameters
		pstatement.clearParameters();
		pstatement.setInt(1, planID);

		// query database
		resultSet = pstatement.executeQuery();
		
		while ( resultSet.next() ) 
			{
			maxNumRentals = resultSet.getInt("numMediaAllowed");
			}
		
		// ensure statement and connection are closed properly                                      
		resultSet.close();                                      
		pstatement.close();                                      
		connection.close();                       
	
		return maxNumRentals;
		}
	
	catch(SQLException sqle)
		{
		System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
		return -1;
		}
	}	
	
//=============================================================================	

	public void setUserAddress(User user)
	{
	PreparedStatement 	pstatement;
	ResultSet 			resultSet;
	int					maxNumRentals;
	
	pstatement = null;
	maxNumRentals = -1;
	
	try
		{
		Connection connection = ConnectionManager.getConnection();
	
		pstatement = connection.prepareStatement("SELECT * FROM Lives_At1 l WHERE l.email = ?");

		// instantiate parameters
		pstatement.clearParameters();
		pstatement.setString(1, user.getEmail());

		// query database
		resultSet = pstatement.executeQuery();
		
		while ( resultSet.next() ) 
			{
			user.setStreetAddress(resultSet.getString("street"));
			user.setZipCode(resultSet.getInt("zip"));
			}
		
		// ensure statement and connection are closed properly                                      
		resultSet.close();                                      
		pstatement.close();  
		
		pstatement = connection.prepareStatement("SELECT * FROM Addresses a WHERE a.street = ? AND a.zip = ?");
		
		// instantiate parameters
		pstatement.clearParameters();
		pstatement.setString(1, user.getStreetAddress());
		pstatement.setInt(2, user.getZipCode());

		// query database
		resultSet = pstatement.executeQuery();
		
		while ( resultSet.next() ) 
			{
			user.setCity(resultSet.getString("city"));
			user.setState(resultSet.getString("state"));
			}
		
		resultSet.close();                                      
		pstatement.close();  
		connection.close();                       
		}
	
	catch(SQLException sqle)
		{
		System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
		}
	
	
	}
	
//========================= DELETE STUFF ====================================================
	
	public void deleteUser(String email)
	{
		PreparedStatement 	pstatement;
		int		 			result;
		
		pstatement = null;
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("DELETE FROM Users WHERE Users.email = ?");

			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setString(1, email);

			// query database
			result = pstatement.executeUpdate();

			// ensure statement and connection are closed properly                                      
			//resultSet.close();                                      
			pstatement.close();                                      
			connection.close();                       
		
		}
		
		catch(SQLException sqle)
		{
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
		}
		
	}
	
//=============================================================================
	
	public void updateUser(User user)
	{
		
		PreparedStatement 	pstatement;
		int					isMember;
		int					isAdmin;
		
		pstatement = null;
		if (user.isAdmin())
			isAdmin = 1;
		else
			isAdmin = 0;
		
		if (user.isUser())
			isMember = 1;
		else
			isMember = 0;
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
				
			pstatement = connection.prepareStatement("Update Users U "
					+ "Set U.name = ?, U.phoneNum = ?, U.isMember = ?, U.isAdmin = ?, U.password = ?, "
					+ "U.numRentalsAvailable = ? Where U.email = ? ");

			// instantiate parameters
			pstatement.clearParameters();
			
			int index = 1;
			pstatement.setString(index++, user.getName());
			pstatement.setString(index++, user.getPhoneNumber());
			pstatement.setInt(index++, isMember);
			pstatement.setInt(index++, isAdmin);
			pstatement.setString(index++, user.getPassword());
			pstatement.setInt(index++, user.getNumRentalsAvailable());
			pstatement.setString(index++, user.getEmail());
			
			pstatement.executeUpdate();
			pstatement.close();             
			connection.close();
			
			updatePlan(user);
			updateUserAddress(user);
		}
		
		catch(SQLException sqle)
		{
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());	
		}
		
	}
	
//============================================================================

	public void updateUserAddress(User user)
	{
		PreparedStatement 	pstatement;
		
		pstatement = null;
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
				
			pstatement = connection.prepareStatement("DELETE FROM Lives_At1 WHERE email = ?");

			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setString(1, user.getEmail());
			pstatement.executeUpdate();
			
			pstatement.close();             
			connection.close();
			
			if (!addressExists(user.getStreetAddress(), user.getZipCode()))
				{
				insertIntoAddresses(user.getStreetAddress(), user.getZipCode(), user.getCity(), user.getState());
				}
			else if (addressHasBeenChanged(user.getStreetAddress(), user.getZipCode(), user.getCity(), user.getState()))
				{
				updateAddress(user.getStreetAddress(), user.getZipCode(), user.getCity(), user.getState());
				}
			
			insertIntoToLives_At1(user.getEmail(), user.getStreetAddress(), user.getZipCode());
		}
	
		catch(SQLException sqle)
		{
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());	
		}
	}
	
//============================================================================
	
	public void updateNumberOfAvailableRentalsForUser(User user)
	{
		
		PreparedStatement 	pstatement;
		
		pstatement = null;
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
				
			pstatement = connection.prepareStatement("Update Users U Set U.numRentalsAvailable = ? Where U.email = ?; ");

			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setInt(1, user.getNumRentalsAvailable());
			pstatement.setString(2, user.getEmail());
			
			pstatement.executeUpdate();
			
			pstatement.close();             
			connection.close();
			
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
	}
	
//============================================================================
	
	public void updateUserPlan(String email, int planID)
	{
		
		PreparedStatement 	pstatement;
		
		pstatement = null;
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
				
			pstatement = connection.prepareStatement("Update Has_Plan HP Set HP.planID = ? Where HP.email = ?; ");

			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setInt(1, planID);
			pstatement.setString(2, email);
			
			pstatement.executeUpdate();
			
			pstatement.close();             
			connection.close();
			
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
	}
	
	public void updatePlan(User user)
	{
	try
		{
		int planId = Integer.parseInt(user.getPlan());
		updateUserPlan(user.getEmail(), planId);
		}
	catch (NumberFormatException e)
		{
		e.printStackTrace();
		}	
	}
	
//=============================================================================

	public List<String> getAllUserEmails()
	{
		List<String>	emailStringList;
		List<User>		userList;
		
		emailStringList = new ArrayList<String>();
		userList = getAllUsers();
		
		for(int i = 0; i < userList.size(); i++)
		{
			
			emailStringList.add(userList.get(i).getEmail());
			
		}
		
		return emailStringList;
		
	}

//============================================================================

	public void insertIntoToLives_At1(String email,String street, int zip)
	{

		PreparedStatement 	pstatement;
		pstatement = null;
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("INSERT INTO lives_at1 (email, street, zip) VALUES (?, ?, ?)");

			//Filling the '?' in pstatement
			pstatement.clearParameters();
			pstatement.setString(1, email);
			pstatement.setString(2, street);
			pstatement.setInt(3, zip);
			
			pstatement.executeUpdate();
			
			                                 
			//Close the connections                                  
			pstatement.close();                                      
			connection.close();                     
			
		}
		catch(SQLException e)
		{
			System.out.println("SQLState = " + e.getSQLState() + "\n" + e.getMessage());
		}
	}
//=============================================================================
	public void insertIntoAddresses(String street, int zip, String city, String state)
	{
		PreparedStatement 	pstatement;
		pstatement = null;
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("INSERT INTO addresses (street, zip, city, state) VALUES (?, ?, ?, ?)");

			//Filling the '?' in pstatement
			pstatement.clearParameters();
			pstatement.setString(1, street);
			pstatement.setInt(2, zip);
			pstatement.setString(3, city);
			pstatement.setString(4, state);
			
			pstatement.executeUpdate();
			
			                                 
			//Close the connections                                  
			pstatement.close();                                      
			connection.close();                     
			
		}
		catch(SQLException e)
		{
			System.out.println("SQLState = " + e.getSQLState() + "\n" + e.getMessage());
		}
	}
//=============================================================================
	public boolean isAddress(String street, int zip)
	{
		PreparedStatement 	pstatement;
		pstatement = null;
		ResultSet rs;
		rs= null;
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT * FROM addresses A WHERE A.street = ? AND A.zip = ? ");
			
			pstatement.setString(1, street);
			pstatement.setInt(2, zip);
			
			rs = pstatement.executeQuery();
			
			//this checks if the result set is empty or not
			//if empty return false, else return true (found the address)
			if(rs.next()==false)
			{
				rs.close();
				pstatement.close();
				connection.close();
				
				return false;
			}
			else
			{
				rs.close();
				pstatement.close();
				connection.close();
				return true;
			}	
		}
		catch(SQLException e)
		{
			System.out.println("SQLState = " + e.getSQLState() + "\n" + e.getMessage());
			return false;
		}
	}
	
//============================================================================

	public void updateAddress (String street, int zip, String city, String state)
	{
		PreparedStatement 	pstatement;
		pstatement = null;
		ResultSet rs;
		rs= null;
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("UPDATE addresses A SET A.city = ? AND A.state = ? "
					+ "WHERE A.street = ? AND A.zip = ? ");
			
			pstatement.setString(1, city);
			pstatement.setString(2, state);
			pstatement.setString(3, street);
			pstatement.setInt(4, zip);
			
			pstatement.executeUpdate();
			
			pstatement.close();
			connection.close();
		}
		catch(SQLException e)
		{
			System.out.println("SQLState = " + e.getSQLState() + "\n" + e.getMessage());
		}
	}	
	
//============================================================================
	
	public boolean addressHasBeenChanged(String street, int zip, String city, String state)
	{
		PreparedStatement 	pstatement;
		pstatement = null;
		ResultSet rs;
		rs= null;
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT * FROM addresses A WHERE A.street = ? AND A.zip = ? "
					+ "AND A.city = ? AND A.state = ?");
			
			pstatement.setString(1, street);
			pstatement.setInt(2, zip);
			pstatement.setString(3, city);
			pstatement.setString(4, state);
			
			rs = pstatement.executeQuery();
			
			//this checks if the result set is empty or not
			//if empty return false, else return true (found the address)
			if(rs.next()==false)
			{
				rs.close();
				pstatement.close();
				connection.close();
				
				return true;
			}
			else
			{
				rs.close();
				pstatement.close();
				connection.close();
				return false;
			}	
		}
		catch(SQLException e)
		{
			System.out.println("SQLState = " + e.getSQLState() + "\n" + e.getMessage());
			return false;
		}
	}
	
//============================================================================
	

	
	
	
}
