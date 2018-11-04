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
import java.util.ArrayList;

public class UserDAO 
{

	
	
	public UserDAO()
	{
		
		//Intentionally blank.
		
	}
	
//=============================================================================
	
	public void insertUser(User user)
	{
		
		PreparedStatement 	pstatement;
		int		 			result;
		byte				trueFalseByte;
		
		pstatement = null;
		//resultSet = null;
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("INSERT INTO Users (email, name, phoneNum, password, isMember, isAdmin) VALUES (?, ?, ?, ?, ?, ?)");

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
	
	public void insertUsers( List<User> userList)
	{
		
		User user;
		PreparedStatement 	pstatement;
		int		 			result;
		byte				trueFalseByte;
		
		user = null;
		pstatement = null;
		//resultSet = null;
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("INSERT INTO Users (email, name, phoneNum, password, isMember, isAdmin) VALUES (?, ?, ?, ?, ?, ?)");
			
			for(int i = 0; i < userList.size(); i++)
			{
				
				user = userList.get(i);
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
				
				result = pstatement.executeUpdate();
			
			}
			
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

//gets a users information
	public User getUser(String email)
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
					
					setUserAddress(user);
					
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
		
			pstatement = connection.prepareStatement("SELECT * FROM Users U WHERE U.email = ? AND U.password = ?");
			
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
			
				pstatement = connection.prepareStatement("SELECT * FROM Users U WHERE U.email = ?");
				
				for(int i = 0; i < emailList.size(); i++)
				{
					
					// instantiate parameters
					pstatement.clearParameters();
					pstatement.setString(1, emailList.get(i));
				
					resultSet = pstatement.executeQuery();

					while ( resultSet.next() ) 
					{
							
							user= new User();
							user.setEmail(resultSet.getString("email"));
							user.setName(resultSet.getString("name"));
							user.setPhoneNumber(resultSet.getString("phoneNum"));
							user.setPassword(resultSet.getString("password"));
							
							if(0 == resultSet.getByte("isMember"))
								user.setUser(false);
							else
								user.setUser(false);
							
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
						
						user= new User();
						user.setEmail(resultSet.getString("email"));
						user.setName(resultSet.getString("name"));
						user.setPhoneNumber(resultSet.getString("phoneNum"));
						user.setPassword(resultSet.getString("password"));
						user.setPlan(Integer.toString(getPlanIDForUser(user.getEmail())));
						user.setMaxNumRentals(getMaxNumRentalsForPlan(Integer.parseInt(user.getPlan())));
						
						setUserAddress(user);
						
						if(0 == resultSet.getByte("isMember"))
							user.setUser(false);
						else
							user.setUser(false);
						
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
	
	
	public void deleteUsers(List<String> emailList)
	{
		
		PreparedStatement 	pstatement;
		int			 		result;
		
		pstatement = null;
		//resultSet = null;
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("DELETE FROM Users WHERE Users.email = ?");
			
			for(int i = 0; i < emailList.size(); i++)	
			{
				// instantiate parameters
				pstatement.clearParameters();
				pstatement.setString(1, emailList.get(i));

				// query database
				result = pstatement.executeUpdate();

			}
			
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
/*
	//Checks and updates a user in the database when given a database object.
	//If an attribute is null in the user object, it is interpreted as not
	//wanting to be updated and will not be updated in the database.
	public void updateUser(User user)
	{
		
		PreparedStatement 	pstatement;
		String				pstatementString;
		ResultSet 			resultSet;
		
		pstatement = null;
		resultSet = null;
		
		if(null != user.get())
		{
			
			
			
		}
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("DELETE FROM Users WHERE Users.email = ?");
			
			
			
			// ensure statement and connection are closed properly                                      
			resultSet.close();                                      
			pstatement.close();                                      
			connection.close();                       
		
		}
		
		catch(SQLException sqle)
		{
			
			System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
			
		}
		
	}
	
//=============================================================================
	
	public void updateUsers(List<User> userList)
	{
		
		
		
	}
	*/
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


	
	
	
	
}
