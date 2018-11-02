//Programmer: Kyle Savina
//Date: 10/25/2018
//Description: A POJO that holds a users information.


public class User 
{
	
	String email;
	String name;
	String phoneNum;
	String password;
	byte isMember;
	byte isAdmin;

	public User()
	{
		
		email = null;
		name = null;
		phoneNum = null;
		password = null;
		isMember = 0;
		isAdmin = 0;
		
	}
	
//=============================================================================
	
	public void setEmail(String email)
	{
		
		this.email = email;
		
	}
	
//=============================================================================
	
	public String getEmail()
	{
		
		return this.email;
		
	}
	
//=============================================================================
	
	
	public void setName(String name)
	{
			
		this.name = name;
		
	}
	
//=============================================================================
	
	public String getName()
	{
		
		return this.name;
		
	}
	
//=============================================================================
	
	
	
	public void setPhoneNum(String phoneNum)
	{
			
		this.phoneNum = phoneNum;
		
	}
	
//=============================================================================
	
	public String getPhoneNum()
	{
		
		return this.phoneNum;
		
	}
	
//=============================================================================
	
	
	public void setPassword(String password)
	{
			
		this.password = password;
		
	}
	
//=============================================================================
	
	public String getPassword()
	{
		
		return this.password;
		
	}
	
//=============================================================================
	
	
	public void setIsMember(byte isMember)
	{
			
		this.isMember = isMember;
		
	}
	
//=============================================================================
	
	public byte getIsMember()
	{
		
		return this.isMember;
		
	}
	
//=============================================================================
	
	public void setIsAdmin(byte isAdmin)
	{
			
		this.isAdmin = isAdmin;
		
	}
	
//=============================================================================
	
	public byte getIsAdmin()
	{
		
		return this.isAdmin;
		
	}
	
//=============================================================================


	@Override
	public String toString()
	{
	
	return "" + email + "\n" + name + "\n" + phoneNum + "\n" + password + "\n" + isMember + "\n" + isAdmin;
	
	}

//==========================================================================

}


