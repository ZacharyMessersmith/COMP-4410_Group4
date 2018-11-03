//Programmer: Kyle Savina
//Date: 10/25/2018
//Description: A POJO that holds a workers information.

package com.databaseProject.Pojos;

public class Worker 
{
	
	int 	workerID;
	String 	wname;
	byte 	isActor;
	byte 	isDirector;
	

	public Worker()
	{
		
		workerID = -1;
		wname = null;
		isActor = 0;
		isDirector = 0;
		
	}
	
//=============================================================================
	
	public void setWorkerID(int workerID)
	{
		
		this.workerID = workerID;
		
	}
	
//=============================================================================
	
	public int getWorkerID()
	{
		
		return this.workerID;
		
	}
	
//=============================================================================
	
	
	public void setWName(String wname)
	{
			
		this.wname = wname;
		
	}
	
//=============================================================================
	
	public String getWName()
	{
		
		return this.wname;
		
	}
	
//=============================================================================
	
	public void setIsActor(byte isActor)
	{
			
		this.isActor = isActor;
		
	}
	
//=============================================================================
	
	public byte getIsActor()
	{
		
		return this.isActor;
		
	}
	
//=============================================================================
	
	public void setIsDirector(byte isDirector)
	{
			
		this.isDirector = isDirector;
		
	}
	
//=============================================================================
	
	public byte getIsDirector()
	{
		
		return this.isDirector;
		
	}
	
//=============================================================================


	@Override
	public String toString()
	{
	
	return "" + workerID + "\n" + wname + "\n" + "\n" + isActor + "\n" + isDirector;
	
	}

//==========================================================================

}
