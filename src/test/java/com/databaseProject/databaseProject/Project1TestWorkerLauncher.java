//Programmer: Kyle Savina
//Date: 10/25/2018
//Description: A POJO that hold information about worker

package com.databaseProject.databaseProject;

import com.databaseProject.DAOs.*;
import com.databaseProject.Pojos.*;
import java.util.List;
import java.util.ArrayList;
import java.sql.Date;

//#############################################################################
class Project1TestWorkerLauncher
{
	
//=============================================================================
	
	public static void main(String[] args)
	{
		
		System.out.println("Starting Application... \n");
		
		workerDAOTest();
		//workerDAOTest();
		//movieDAOTest();
		//gameDAOTest();
		
		System.out.println("Done.");
		
	}//end main()
	
//=============================================================================

	public static void workerDAOTest()
	{
		
		Worker				workerInsert;
		Worker				workerRetrieve;
		Worker				tempWorker;
		Date				releaseDate;
		List<Worker> 		workerListInsert;
		List<Worker>			workerListRetrieve;
		List<Integer>		workerIDList;
		List<String> 		nameList;
		WorkerDAO 			workerDAO;
		byte				trueFalseByte;
		
		releaseDate = new Date(System.currentTimeMillis());
		workerRetrieve = new Worker();
		workerListInsert = new ArrayList<Worker>();
		workerListRetrieve = new ArrayList<Worker>();
		workerIDList = new ArrayList<Integer>();
		trueFalseByte = 0;
		
		/*int i;
		for(i = 0; i < 10; i++)
		{
			
			workerInsert = new Worker();
			workerIDList.add(new Integer(i));
			workerInsert.setWorkerID(i);
			workerInsert.setWName("Worker" + i);
			workerInsert.setIsActor(trueFalseByte);
			workerInsert.setIsDirector(trueFalseByte);
			
			workerListInsert.add(workerInsert);
			
		}*/
		
		
		workerInsert = new Worker();
		trueFalseByte = 1;
		workerInsert.setWorkerID(7);
		workerInsert.setWName("Worker" + 7);
		workerInsert.setIsActor(trueFalseByte);
		workerInsert.setIsDirector(trueFalseByte);
		
		System.out.println("Creating WorkerDAO");
		workerDAO = new WorkerDAO();
		System.out.println("Created WorkerDAO");
		
		/*System.out.println("Testing getting workerID given a name");
		System.out.println(workerDAO.getWorkerID("Worker7"));
		System.out.println("Done Testing getting workerID when given name");*/
		
		/*
		System.out.println("Testing adding worker to Works_On given a movieID");
		//workerDAO.insertWorker(workerInsert);
		workerDAO.insertWorks_On(14555, 24);//Pre-setup workerID and movieID to test
		System.out.println("Done testing adding to Works_On");*/
		
		
		/*System.out.println("Manually adding single worker");
		workerDAO.insertWorker(workerInsert);
		System.out.println("Successfully added single worker");
		
		System.out.println("Adding list of worker");
		workerDAO.insertWorker(workerListInsert);
		System.out.println("successfully added list of worker");*/
		
		/*System.out.println("Getting single worker");
		workerRetrieve = workerDAO.getWorker(i);
		System.out.println("Got worker. Compare to input.");
		
		System.out.println("Inserted: " + workerInsert.getWName());
		System.out.println("Retrieved: " + workerRetrieve.getWName());
		
		System.out.println("Compared. Compare list of worker Iserted and Retrieved alternating");
	*/
		/*	
		System.out.println("Compared. Testing to see if worker exists.");
	
		if( false == workerDAO.testWorker("DoesNotExist@email.test" ,  "DoesNotExist"))
		{
		
			System.out.println("DoesNotExist@email.test with password DoesNotExist does not exist");
		
		}
		else
		{
			
			System.out.println("DoesNotExist@email.test with password DoesNotExist exists");
			
		}
		
		
		if( false ==  workerDAO.testWorker("ManualAdd@email.test" ,  "ManualAddPassword"))
		{
			
			System.out.println("ManualAdd@email.test with password ManualAddPassword does not exist");
			
		}
		
		else
		{
			
			System.out.println("ManualAdd@email.test with password ManualAddPassword  exists");
			
		}
		*/
		/*System.out.println("Retrieving list of worker. Returning count of worker retrieved");
		
		workerListRetrieve = workerDAO.getWorker(workerIDList);
		System.out.println("Size = " + workerListRetrieve.size());
		
		System.out.println("Done retrieving list of worker. Testing retrieving all worker. Returning count of worker retrieved.");
		
		workerListRetrieve.clear();
		workerListRetrieve = workerDAO.getAllWorkers();
		System.out.println("Size = " + workerListRetrieve.size());
		
		nameList = workerDAO.getAllWorkerNames();
		
		for(i = 0; i < nameList.size(); i++)
		{
			
			System.out.println(nameList.get(i));
			
		}
		
		System.out.println("Testing getting all actors");
		
		nameList = workerDAO.getAllActorNames();
		
		for(i = 0; i < nameList.size(); i++)
		{
			
			System.out.println(nameList.get(i));
			
		}
		
		System.out.println("Done testing getting actor names.");
		System.out.println("Testing getting director names");
		
		nameList = workerDAO.getAllDirectorNames();
		
		for(i = 0; i < nameList.size(); i++)
		{
			
			System.out.println(nameList.get(i));
			
		}
		
		System.out.println("Done testing getting actor names.");
		
		System.out.println("Done getting all worker.");*/
		/*
		System.out.println("Testing updating worker information");
		
		workerInsert.setEmail("ManualAdd2@email.test2");
		workerInsert.setPassword("ManualAddPassword2");
		//implies no change
		workerInsert.setPhoneNum(null);
		workerInsert.setName("ManualAdd2");
		workerDAO.updateWorker(workerInsert);
		workerRetrieve = workerDAO.getWorker("ManualAdd2@email.test2", "ManualAddPassword2");
		
		workerInsert.setEmail(null);
		workerInsert.setPassword(null);
		workerInsert.setPhoneNum("Man-ual-Add3");
		workerInsert.setName(null);
		workerDAO.updateWorker(workerInsert);
		workerRetrieve = workerDAO.getWorker("ManualAdd2@email.test2", "ManualAddPassword2");
		workerRetrieve.toString();
		
		System.out.println("Done testing update a single worker. Testing list of worker");
		
		for( int i = 10; i < 20; i++)
		{
			
			tempWorker = new Worker();
			emailList.set(i-10, "Worker" + i + "@email.test");
			passwordList.set(i-10, "Password" + i);
			tempWorker.setEmail("Worker" + i + "@email.test");
			tempWorker.setPassword("Password" + i);
			tempWorker.setName("Name" + i);
			tempWorker.setPhoneNum("Pho-Num-ber" + i);
			tempWorker.setIsMember(1);
			tempWorker.setIsAdmin(1);
			workerListInsert.set(i - 10, tempWorker);
		
		}
		
		workerDAO.updateWorker(workerListInsert);
		workerListRetrieve = workerDAO.getWorker(emailList, passwordList);
		
		for(int i = 0; i < 10; i++)
		{
			
			System.out.println(workerListRetrieve.get(i).getEmail());
			
		}
		
		System.out.println("Done Testing updates on a list.");
		
		
		System.out.println("Testing Deleting on a worker");
		
		try
		{
			
			workerDAO.deleteWorker("ManualAdd@email.test");
			workerRetrieve = workerDAO.getWorker("ManualAdd@email.test", "ManualAddPassword");
			
			workerRetrieve.toString();
			
			
		}
		
		catch(Exception e)
		{
			
			System.out.println("Failed in deleting single worker. Success");
			
		}
		
		System.out.println("Done Testing deleting single worker. Testing deleting list of worker.");
		
		try
		{
			
			workerDAO.deleteWorker(emailList);
			workerDAO.getWorker(emailList, passwordList);
			
		}
		
		catch(Exception e)
		{
			
			System.out.println("Falure in deleting list of worker");
			
		}
		
		System.out.println("Done testing delete worker list.");
		*/
		
		System.out.println("Done testing workerDAO.");
		
		
	}
	
//=============================================================================
	
}

//#############################################################################