//Programmer: Kyle Savina
//Date: 10/25/2018
//Description: An object that accesses a database to retrieve
//				the information about a Worker.

package com.databaseProject.DAOs;

import com.databaseProject.Pojos.Worker;
import com.databaseProject.databaseProject.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;



public class WorkerDAO 
{

	
	
	public WorkerDAO()
	{
		
		//Intentionally blank
		
	}
/*
//=============================================================================
	
	public void insertWorker(Worker worker)
	{
		
		PreparedStatement 	pstatement;
		int		 			result;
		//int					numOfColumns;
		
		pstatement = null;
		//resultSet = null;
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("INSERT INTO Worker (workerID, releaseDate, genre, title, numCopiesAvailable) VALUES (?, ?, ?, ?, ?)");

			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setInt(1, worker.getWorkerID());
			pstatement.setDate(2, worker.getReleaseDate());
			pstatement.setString(3, worker.getGenre());
			pstatement.setString(4, worker.getTitle());
			pstatement.setInt(5, worker.getNumOfCopiesAvailable());
			
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
/*	
	public void insertWorker( List<Worker> workerList)
	{
		
		Worker worker;
		PreparedStatement 	pstatement;
		int		 			result;
		//int					numOfColumns;
		
		worker = null;
		pstatement = null;
		//resultSet = null;
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("INSERT INTO Worker (workerID, releaseDate, genre, title, numCopiesAvailable) VALUES (?, ?, ?, ?, ?)");
			
			for(int i = 0; i < workerList.size(); i++)
			{
				
				worker = workerList.get(i);
				// instantiate parameters
				pstatement.clearParameters();
				pstatement.setInt(1, worker.getWorkerID());
				pstatement.setDate(2, worker.getReleaseDate());
				pstatement.setString(3, worker.getGenre());
				pstatement.setString(4, worker.getTitle());
				pstatement.setInt(5, worker.getNumOfCopiesAvailable());
			
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
	*/
	public Worker getWorker(Integer workerID)
	{
		
		Worker 				worker;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		
		worker = new Worker();
		pstatement = null;
		resultSet = null;
		
		
		try
		{
			
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT * FROM Workers W WHERE W.workerID = ?");
			
			//instantiate parameters
			pstatement.clearParameters();
			pstatement.setInt(1, workerID);
			
			resultSet = pstatement.executeQuery();

			while ( resultSet.next() ) 
			{
					
				worker.setWorkerID(resultSet.getInt("workerID"));
				worker.setWName(resultSet.getString("wname"));
				worker.setIsActor(resultSet.getByte("isActor"));
				worker.setIsDirector(resultSet.getByte("isDirector"));
				
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
		
		return worker;
		
		
	}
	
//=============================================================================
	
	public List<Worker> getWorker(List<Integer> workerIDList)
	{
		
		List<Worker> 		workerList;
		Worker 				worker;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		
		workerList = new ArrayList<Worker>();
		worker = new Worker();
		pstatement = null;
		resultSet = null;
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT * FROM Workers M WHERE M.workerID = ?");
			
			for(int i = 0; i < workerIDList.size(); i++)
			{
				
				pstatement.clearParameters();
				pstatement.setInt(1, workerIDList.get(i));
			
				resultSet = pstatement.executeQuery();

				while ( resultSet.next() ) 
				{
					worker = new Worker();
					worker.setWorkerID(resultSet.getInt("workerID"));
					worker.setWName(resultSet.getString("wname"));
					worker.setIsActor(resultSet.getByte("isActor"));
					worker.setIsDirector(resultSet.getByte("isDirector"));
					workerList.add(worker);
					
				} //end while
			
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
		
		return workerList;
		
		
	}
	
//=============================================================================
	
	public List<Worker> getAllWorkers()
	{
		
		List<Worker>			workerList;
		Worker 				worker;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		
		workerList = new ArrayList<Worker>();
		worker = new Worker();
		pstatement = null;
		resultSet = null;
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT * FROM Workers M");
			
			// instantiate parameters
			pstatement.clearParameters();
			
			resultSet = pstatement.executeQuery();

			while ( resultSet.next() ) 
			{
			
					
				worker = new Worker();
				worker.setWorkerID(resultSet.getInt("workerID"));
				worker.setWName(resultSet.getString("wname"));
				worker.setIsActor(resultSet.getByte("isActor"));
				worker.setIsDirector(resultSet.getByte("isDirector"));
				workerList.add(worker);
					
			
				
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
		
		
		
		return workerList;
		
	}

//=============================================================================
	/*
	public void deleteWorker(Integer workerID)
	{
		
		
		
	}
	
//=============================================================================
	
	
	public void deleteWorker(List<Integer> workerIDList)
	{
		
		
		
	}
	
//=============================================================================
	
	public void updateWorker(Worker worker)
	{
		
		
		
	}
	
//=============================================================================
	
	public void updateWorker(List<Worker> workerList)
	{
		
		
		
	}
	
//=============================================================================
	
	public void upateAllWorker(Worker worker)
	{
		
		
		
	}
	*/
//=============================================================================

	public List<String> getAllWorkerNames()
	{
		
		List<Worker> 	workerList;
		List<String>	nameList;
		
		nameList = new ArrayList<String>();
		workerList = getAllWorkers();
		
		for(int i = 0; i < workerList.size(); i++)
		{
			
			nameList.add(workerList.get(i).getWName());
			
		}
		
		return nameList;
		
	}

//============================================================================
	
	public List<String> getAllActorNames()
	{
		List<String>		wnameList;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		byte				getActorByte;
		
		wnameList = new ArrayList<String>();
		pstatement = null;
		resultSet = null;
		getActorByte = 1;
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT M.wname FROM Workers M WHERE M.isActor = ?");
			
			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setByte(1, getActorByte);
			
			resultSet = pstatement.executeQuery();

			while ( resultSet.next() ) 
			{
			
				wnameList.add(resultSet.getString("wname"));
				
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
		
		
		
		return wnameList;
		
	}
	
//=============================================================================
	
	public List<String> getAllDirectorNames()
	{
		List<String>		wnameList;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		byte				getActorByte;
		
		wnameList = new ArrayList<String>();
		pstatement = null;
		resultSet = null;
		getActorByte = 1;
		
		
		try
		{
			Connection connection = ConnectionManager.getConnection();
		
			pstatement = connection.prepareStatement("SELECT M.wname FROM Workers M WHERE M.isDirector = ?");
			
			// instantiate parameters
			pstatement.clearParameters();
			pstatement.setByte(1, getActorByte);
			
			resultSet = pstatement.executeQuery();

			while ( resultSet.next() ) 
			{
			
				wnameList.add(resultSet.getString("wname"));
				
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
		
		
		
		return wnameList;
		
	}
	
//=============================================================================
		
}
