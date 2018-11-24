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
class Project1TestMediaLauncher
{
	
//=============================================================================
	
	public static void main(String[] args)
	{
		
		System.out.println("Starting Application... \n");
		
		mediaDAOTest();
		//mediaDAOTest();
		//movieDAOTest();
		//gameDAOTest();
		
		System.out.println("Done.");
		
	}//end main()
	
//=============================================================================

	public static void mediaDAOTest()
	{
		
		Media				mediaInsert;
		Media				mediaRetrieve;
		Media				tempMedia;
		Date				releaseDate;
		List<Media> 		mediaListInsert;
		List<Media>			mediaListRetrieve;
		List<Integer>		mediaIDList;
		List<String> 		titleList;
		MediaDAO 			mediaDAO;
		
		releaseDate = new Date(System.currentTimeMillis());
		mediaRetrieve = new Media();
		mediaListInsert = new ArrayList<Media>();
		mediaListRetrieve = new ArrayList<Media>();
		mediaIDList = new ArrayList<Integer>();
		titleList = null;
		
		/*int i;
		for(i = 0; i < 10; i++)
		{
			
			mediaInsert = new Media();
			mediaIDList.add(new Integer(i));
			releaseDate.setTime(System.currentTimeMillis());
			mediaInsert.setMediaID(i);
			mediaInsert.setReleaseDate(releaseDate);
			mediaInsert.setGenre("genre" + i);
			mediaInsert.setTitle("Media" + i);
			mediaInsert.setNumCopiesAvailable(4);
			mediaInsert.setMediaType('m');
			
			mediaListInsert.add(mediaInsert);
			
		}
		
		
		mediaInsert = new Media();
		releaseDate.setTime(System.currentTimeMillis());
		mediaInsert.setMediaID(i);
		mediaInsert.setReleaseDate(releaseDate);
		mediaInsert.setGenre("genreCustom");
		mediaInsert.setTitle("MediaCustom");
		mediaInsert.setNumCopiesAvailable(3);
		mediaInsert.setMediaType('g');
		mediaInsert.setVersion((float) 1.0);
		mediaInsert.setPlatform("X-bone");
		*/
		System.out.println("Creating MediaDAO");
		mediaDAO = new MediaDAO();
		System.out.println("Created MediaDAO");
		
		
		System.out.println("Testing updating media information");
		mediaDAO.updateMedia(24, new Date(System.currentTimeMillis()), 5, "Action");
		System.out.println("Done Testing updating media information");
		
		
		
		/*System.out.println("Testing getting mediaID given a movieTitle");
		System.out.println(mediaDAO.getMediaIDUsingMovieTitle("BulletBoy"));
		System.out.println("Done Testing getting mediaID given a movieTitle");*/
		
		
		/*System.out.println("Testing Setting sequel givel a prequelID and sequelID");
		mediaDAO.insertSequel(6, 24);
		System.out.println("Done Testing Setting sequel givel a prequelID and sequelID");*/
		
		/*System.out.println("Testing Setting Won using movieID and awardID");
		mediaDAO.insertWon(24, 2);
		System.out.println("Done Testing Setting Won using movieID and awardID");*/
		
		
	/*	System.out.println("Testing getting an awardID using the aname");
		System.out.println(mediaDAO.getAwardID("Legendary"));
		System.out.println("Done Testing getting an awardID using the aname");*/
		
		/*System.out.println("Attempting to insert a movie given information");
		mediaInsert = new Media();
		releaseDate.setTime(System.currentTimeMillis());
		mediaInsert.setMediaID(24);
		mediaInsert.setReleaseDate(releaseDate);
		mediaInsert.setGenre("action");
		mediaInsert.setTitle("BulletBoy");
		mediaInsert.setNumCopiesAvailable(3);
		mediaInsert.setMediaType('n');//using 'n' instead of 'm' or 'g' causes no insert into movies for testing the following function
		mediaInsert.setVersion((float) 1.0);
		mediaInsert.setPlatform("X-bone");
		mediaDAO.insertMedia(mediaInsert);
		mediaDAO.insertMovie(mediaInsert.getMediaID());
		System.out.println("done inserting movie based on information");*/
		
		/*System.out.println("Attempting to insert a game given information");
		mediaInsert = new Media();
		releaseDate.setTime(System.currentTimeMillis());
		mediaInsert.setMediaID(23);
		mediaInsert.setReleaseDate(releaseDate);
		mediaInsert.setGenre("action");
		mediaInsert.setTitle("BulletBoy");
		mediaInsert.setNumCopiesAvailable(3);
		mediaInsert.setMediaType('n');//using 'n' instead of 'm' or 'g' causes no insert into games for testing the following function
		mediaInsert.setVersion((float) 1.0);
		mediaInsert.setPlatform("X-bone");
		mediaDAO.insertMedia(mediaInsert);
		mediaDAO.insertGame(mediaInsert.getPlatform(), mediaInsert.getVersion(), mediaInsert.getMediaID());
		System.out.println("done inserting game based on information");*/
		
		/*
		System.out.println("Testing Adding a media by giving information instead of media");
		mediaDAO.insertMedia(new java.sql.Date(System.currentTimeMillis()), 
								"Test Genre 1", "Test Title 1", 3);
		System.out.println("Done testing adding media by giving information");
		*/
		/*System.out.println("Manually adding single media");
		mediaDAO.insertMedia(mediaInsert);
		System.out.println("Successfully added single media");
		
		System.out.println("Adding list of media");
		mediaDAO.insertMedia(mediaListInsert);
		System.out.println("successfully added list of media");
		
		System.out.println("Getting single media");
		mediaRetrieve = mediaDAO.getMedia(i);
		System.out.println("Got media. Compare to input.");
		
		System.out.println("Inserted: " + mediaInsert.getTitle());
		System.out.println("Retrieved: " + mediaRetrieve.getTitle());
		
		System.out.println("Compared. Compare list of media Iserted and Retrieved alternating");
	*/
		/*	
		System.out.println("Compared. Testing to see if media exists.");
	
		if( false == mediaDAO.testMedia("DoesNotExist@email.test" ,  "DoesNotExist"))
		{
		
			System.out.println("DoesNotExist@email.test with password DoesNotExist does not exist");
		
		}
		else
		{
			
			System.out.println("DoesNotExist@email.test with password DoesNotExist exists");
			
		}
		
		
		if( false ==  mediaDAO.testMedia("ManualAdd@email.test" ,  "ManualAddPassword"))
		{
			
			System.out.println("ManualAdd@email.test with password ManualAddPassword does not exist");
			
		}
		
		else
		{
			
			System.out.println("ManualAdd@email.test with password ManualAddPassword  exists");
			
		}
		*/
		/*
		System.out.println("Retrieving list of media. Returning count of media retrieved");
		
		mediaListRetrieve = mediaDAO.getMedia(mediaIDList);
		System.out.println("Size = " + mediaListRetrieve.size());
		
		System.out.println("Done retrieving list of media. Testing retrieving all media. Returning count of media retrieved.");
		
		mediaListRetrieve.clear();
		mediaListRetrieve = mediaDAO.getAllMedia();
		System.out.println("Size = " + mediaListRetrieve.size());
		
		for(i = 0; i < mediaListRetrieve.size(); i++)
		{
			
			if(mediaListRetrieve.get(i).getMediaType() == 'g')
			{
				System.out.println("Game: " + mediaListRetrieve.get(i).getTitle());
				System.out.println("--" + mediaListRetrieve.get(i).getPlatform());
				System.out.println("--" + new Float( mediaListRetrieve.get(i).getVersion()).toString());
			
			}
			
			else if(mediaListRetrieve.get(i).getMediaType() == 'm')
			{
				
				System.out.println("Movie: " + mediaListRetrieve.get(i).getTitle());
				
				
			}
			
			else
			{
				
				System.out.println("Undefined Media Type: " + mediaListRetrieve.get(i).getTitle() );	
			
			}
			
			
			
		}
		
		
		System.out.println("Done Testing If Media Type is acquired.");
		System.out.println("Testing getting Cast From Media");
		
		
		
		for( i = 0; i < mediaRetrieve.getCastList().size(); i ++)
		{
			
			System.out.println("Cast Name: " + mediaRetrieve.getCastList().get(i));
			
		}
		
		System.out.println("Done Testing Cast List. Testing Director List");
		
		
		
		for( i = 0; i < mediaRetrieve.getDirectorList().size(); i ++)
		{
			
			System.out.println("Director Name: " + mediaRetrieve.getDirectorList().get(i));
			
		}
		
		System.out.println("Done Testing Director List");
		
		System.out.println("Testing Sequels");
		
		for(i = 0; i < mediaRetrieve.getSequelsList().size(); i++)
		{
			
			System.out.println("Sequel: " + mediaRetrieve.getSequelsList().get(i));
			
		}
		
		*/
		
		/*
		titleList = mediaDAO.getAllMediaTitles();
		
		for(i = 0; i < titleList.size(); i++)
		{
			
			System.out.println(titleList.get(i));
			
		}
		
		System.out.println("Done getting all media.");
		
		System.out.println("Testing getting movie titles");
		
		titleList = mediaDAO.getAllMovieTitles();
		
		for(i = 0; i < titleList.size(); i++)
		{
			
			System.out.println(titleList.get(i));
			
		}
		
		System.out.println("Done Testing getting movie titles");*/
		
		/*
		System.out.println("Testing updating media information");
		
		mediaInsert.setEmail("ManualAdd2@email.test2");
		mediaInsert.setPassword("ManualAddPassword2");
		//implies no change
		mediaInsert.setPhoneNum(null);
		mediaInsert.setName("ManualAdd2");
		mediaDAO.updateMedia(mediaInsert);
		mediaRetrieve = mediaDAO.getMedia("ManualAdd2@email.test2", "ManualAddPassword2");
		
		mediaInsert.setEmail(null);
		mediaInsert.setPassword(null);
		mediaInsert.setPhoneNum("Man-ual-Add3");
		mediaInsert.setName(null);
		mediaDAO.updateMedia(mediaInsert);
		mediaRetrieve = mediaDAO.getMedia("ManualAdd2@email.test2", "ManualAddPassword2");
		mediaRetrieve.toString();
		
		System.out.println("Done testing update a single media. Testing list of media");
		
		for( int i = 10; i < 20; i++)
		{
			
			tempMedia = new Media();
			emailList.set(i-10, "Media" + i + "@email.test");
			passwordList.set(i-10, "Password" + i);
			tempMedia.setEmail("Media" + i + "@email.test");
			tempMedia.setPassword("Password" + i);
			tempMedia.setName("Name" + i);
			tempMedia.setPhoneNum("Pho-Num-ber" + i);
			tempMedia.setIsMember(1);
			tempMedia.setIsAdmin(1);
			mediaListInsert.set(i - 10, tempMedia);
		
		}
		
		mediaDAO.updateMedia(mediaListInsert);
		mediaListRetrieve = mediaDAO.getMedia(emailList, passwordList);
		
		for(int i = 0; i < 10; i++)
		{
			
			System.out.println(mediaListRetrieve.get(i).getEmail());
			
		}
		
		System.out.println("Done Testing updates on a list.");
		
		
		System.out.println("Testing Deleting on a media");
		
		try
		{
			
			mediaDAO.deleteMedia("ManualAdd@email.test");
			mediaRetrieve = mediaDAO.getMedia("ManualAdd@email.test", "ManualAddPassword");
			
			mediaRetrieve.toString();
			
			
		}
		
		catch(Exception e)
		{
			
			System.out.println("Failed in deleting single media. Success");
			
		}
		
		System.out.println("Done Testing deleting single media. Testing deleting list of media.");
		
		try
		{
			
			mediaDAO.deleteMedia(emailList);
			mediaDAO.getMedia(emailList, passwordList);
			
		}
		
		catch(Exception e)
		{
			
			System.out.println("Falure in deleting list of media");
			
		}
		
		System.out.println("Done testing delete media list.");
		*/
		
		System.out.println("Done testing mediaDAO.");
		
		
	}
	
//=============================================================================
	
}

//#############################################################################