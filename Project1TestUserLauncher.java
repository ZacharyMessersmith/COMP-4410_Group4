//Programmer: Kyle Savina
//Date: 10/25/2018
//Description: A POJO that hold information about media


import java.util.List;
import java.util.ArrayList;

//#############################################################################
class Project1TestUserLauncher
{
	
//=============================================================================
	
	public static void main(String[] args)
	{
		
		System.out.println("Starting Application... \n");
		
		userDAOTest();
		//mediaDAOTest();
		//movieDAOTest();
		//gameDAOTest();
		
		System.out.println("Done.");
		
	}//end main()
	
//=============================================================================

	public static void userDAOTest()
	{
		
		User				userInsert;
		User				userRetrieve;
		User				tempUser;
		List<User> 			userListInsert;
		List<User>			userListRetrieve;
		List<String>		emailList;
		List<String>		passwordList;
		UserDAO 			userDAO;
		byte				falseByte;
		byte				trueByte;
		
		
		falseByte = 0;
		trueByte = 1;
		userRetrieve = new User();
		userListInsert = new ArrayList<User>();
		userListRetrieve = new ArrayList();
		emailList = new ArrayList<String>();
		passwordList = new ArrayList<String>();
		
		
		for(int i = 0; i < 10; i++)
		{
			
			userInsert = new User();
			emailList.add("User" + i + "@email.test");
			userInsert.setEmail("User" + i + "@email.test");
			
			passwordList.add("Password" + i);
			userInsert.setPassword("Password" + i);
			
			userInsert.setName("Name" + i);
			userInsert.setPhoneNum("PhoNumber" + i);
			userInsert.setIsMember(falseByte);
			userInsert.setIsAdmin(falseByte);
			
			userListInsert.add(userInsert);
			
		}
		
		
		userInsert = new User();
		userInsert.setEmail("ManualAdd@email.test");
		userInsert.setPassword("ManualAddPassword");
		userInsert.setName("ManualAddName");
		userInsert.setPhoneNum("ManualAdd1");
		userInsert.setIsMember(trueByte);
		userInsert.setIsAdmin(trueByte);
		
		System.out.println("Creating UserDAO");
		userDAO = new UserDAO();
		System.out.println("Created UserDAO");
		
		System.out.println("Manually adding single user");
		userDAO.insertUser(userInsert);
		System.out.println("Successfully added single user");
		
		System.out.println("Adding list of users");
		userDAO.insertUsers(userListInsert);
		System.out.println("successfully added list of users");
		
		System.out.println("Getting single user");
		userRetrieve = userDAO.getUser("ManualAdd@email.test", "ManualAddPassword");
		System.out.println("Got user. Compare to input.");
		
		System.out.println("Inserted: " + userInsert.getEmail());
		System.out.println("Retrieved: " + userRetrieve.getEmail());
		
		System.out.println("Compared. Compare list of users Iserted and Retrieved alternating");
		
		System.out.println("Compared. Testing to see if users exists.");
		if( false == userDAO.testUser("DoesNotExist@email.test" ,  "DoesNotExist"))
		{
		
			System.out.println("DoesNotExist@email.test with password DoesNotExist does not exist");
		
		}
		else
		{
			
			System.out.println("DoesNotExist@email.test with password DoesNotExist exists");
			
		}
		
		
		if( false ==  userDAO.testUser("ManualAdd@email.test" ,  "ManualAddPassword"))
		{
			
			System.out.println("ManualAdd@email.test with password ManualAddPassword does not exist");
			
		}
		
		else
		{
			
			System.out.println("ManualAdd@email.test with password ManualAddPassword  exists");
			
		}
		
		System.out.println("Done testing users. Retrieving list of users. Returning count of users retrieved");
		
		userListRetrieve = userDAO.getUsers(emailList, passwordList);
		System.out.println("Size = " + userListRetrieve.size());
		
		System.out.println("Done retrieving list of users. Testing retrieving all users. Returning count of users retrieved.");
		
		userListRetrieve.clear();
		userListRetrieve = userDAO.getAllUsers();
		System.out.println("Size = " + userListRetrieve.size());
		
		System.out.println("Done getting all users.");
		
		List<String> emailStringList = userDAO.getAllUserEmails();
		for(int i = 0; i < emailStringList.size(); i++)
		{
			
			System.out.println(emailStringList.get(i));
			
		}
		
		/*
		System.out.println("Testing updating user information");
		
		userInsert.setEmail("ManualAdd2@email.test2");
		userInsert.setPassword("ManualAddPassword2");
		//implies no change
		userInsert.setPhoneNum(null);
		userInsert.setName("ManualAdd2");
		userDAO.updateUser(userInsert);
		userRetrieve = userDAO.getUser("ManualAdd2@email.test2", "ManualAddPassword2");
		
		userInsert.setEmail(null);
		userInsert.setPassword(null);
		userInsert.setPhoneNum("Man-ual-Add3");
		userInsert.setName(null);
		userDAO.updateUser(userInsert);
		userRetrieve = userDAO.getUser("ManualAdd2@email.test2", "ManualAddPassword2");
		userRetrieve.toString();
		
		System.out.println("Done testing update a single user. Testing list of users");
		
		for( int i = 10; i < 20; i++)
		{
			
			tempUser = new User();
			emailList.set(i-10, "User" + i + "@email.test");
			passwordList.set(i-10, "Password" + i);
			tempUser.setEmail("User" + i + "@email.test");
			tempUser.setPassword("Password" + i);
			tempUser.setName("Name" + i);
			tempUser.setPhoneNum("Pho-Num-ber" + i);
			tempUser.setIsMember(1);
			tempUser.setIsAdmin(1);
			userListInsert.set(i - 10, tempUser);
		
		}
		
		userDAO.updateUsers(userListInsert);
		userListRetrieve = userDAO.getUsers(emailList, passwordList);
		
		for(int i = 0; i < 10; i++)
		{
			
			System.out.println(userListRetrieve.get(i).getEmail());
			
		}
		
		System.out.println("Done Testing updates on a list.");
		*/
		/*
		System.out.println("Testing Deleting on a user");
		
		try
		{
			
			userDAO.deleteUser("ManualAdd@email.test");
			userRetrieve = userDAO.getUser("ManualAdd@email.test", "ManualAddPassword");
			
			userRetrieve.toString();
			
			
		}
		
		catch(Exception e)
		{
			
			System.out.println("Failed in deleting single user. Success");
			
		}
		
		System.out.println("Done Testing deleting single user. Testing deleting list of users.");
		
		try
		{
			
			userDAO.deleteUsers(emailList);
			userDAO.getUsers(emailList, passwordList);
			
		}
		
		catch(Exception e)
		{
			
			System.out.println("Falure in deleting list of users");
			
		}
		
		System.out.println("Done testing delete user list.");
		System.out.println("Done testing userDAO.");
		
		*/
	}
	
//=============================================================================
	
}

//#############################################################################