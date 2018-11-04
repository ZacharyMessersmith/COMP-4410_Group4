package com.databaseProject.DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.databaseProject.Pojos.Plan;
import com.databaseProject.databaseProject.ConnectionManager;

public class PlanDAO 
{
	public static void main(String[] args)
	{
		PlanDAO pd = new PlanDAO();
		Plan p = pd.getPlan(1);
		System.out.println("This is plan for 1:");
		System.out.println(p.getPlanID()+"  cost:"+p.getCost()+" numMediaAllowed:" +p.getNumMediaAllowed());
		
		System.out.println("DONE...");
		List<Plan> listOfPlans = pd.getAllPlans();
		for(int i=0;i<listOfPlans.size();i++)
		{
			p=listOfPlans.get(i);
			System.out.println(p.getPlanID()+"  cost:"+p.getCost()+" numMediaAllowed:" +p.getNumMediaAllowed());
		}
		System.out.println("DONE...");
	}
	
	
	public PlanDAO()
	{
		
	}
	
	
	public Plan getPlan(Integer planID)
	{
		Plan p;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		int					numOfColumns;
		
		
		p=new Plan();
		pstatement = null;
		resultSet = null;
		
		
		try 
		{
			Connection connection = ConnectionManager.getConnection();
			pstatement = connection.prepareStatement("SELECT * FROM plans P WHERE P.planID = ?");
			
			pstatement.clearParameters();
			pstatement.setInt(1, planID);
			
			resultSet = pstatement.executeQuery();
			
			
			
			while(resultSet.next())
			{
				p.setPlanID(resultSet.getInt("planID"));
				p.setCost(resultSet.getInt("cost"));
				p.setNumMediaAllowed(resultSet.getInt("numMediaAllowed"));
			}
			resultSet.close();
			pstatement.close();
			connection.close();
		} 
		catch (SQLException e) 
		{
			//System.out.println("Something messed up in the planDAO.java file Ln:56");
			System.out.println("SQLState = " + e.getSQLState() + "\n" + e.getMessage());
		}
		
		
		
		return p;
	}
	//=====================================================================
	
	public List<Plan> getPlans(List<Integer> planIDList)
	{
		List<Plan>	lp;
		Plan		p;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		int					numOfColumns;
		Connection 			connection;
		lp = new ArrayList<Plan>();
		p = new Plan();
		pstatement = null;
		resultSet = null;
		
		ResultSetMetaData metaData;
		try 
		{
			connection = ConnectionManager.getConnection();
			pstatement = connection.prepareStatement("SELECT * FROM plans P WHERE P.planID = ?");
			for(int i=0;i<planIDList.size();i++)
			{
				pstatement.clearParameters();
				pstatement.setInt(1, planIDList.get(i));
				
				resultSet = pstatement.executeQuery();
				
				metaData = resultSet.getMetaData();
				numOfColumns= metaData.getColumnCount();
				
				while(resultSet.next())
				{
					p.setPlanID(resultSet.getInt("planID"));
					p.setCost(resultSet.getInt("cost"));
					p.setNumMediaAllowed(resultSet.getInt("numMediaAllowed"));
					
					lp.add(p);
				}
			}
			resultSet.close();
			pstatement.close();
			connection.close();
		} 
		catch (SQLException e)
		{
			System.out.println("SQLState = " + e.getSQLState() + "\n" + e.getMessage());
		}
		
		
		return lp;
	}
	//============================================================
	
	public List<Plan> getAllPlans()
	{
		List<Plan> lp;
		Plan		p;
		PreparedStatement 	pstatement;
		ResultSet 			resultSet;
		int					numOfColumns;
		Connection 			connection;
		lp = new ArrayList<Plan>();
		p = new Plan();
		pstatement = null;
		resultSet = null;
		
		try 
		{
			connection = ConnectionManager.getConnection();
			
			pstatement = connection.prepareStatement("SELECT * FROM plans P");
			
			pstatement.clearParameters();
			
			resultSet = pstatement.executeQuery();
			
			ResultSetMetaData metaData = resultSet.getMetaData();
			
			numOfColumns = metaData.getColumnCount();
			
			while(resultSet.next())
			{
				p = new Plan();
				p.setPlanID(resultSet.getInt("planID"));
				p.setCost(resultSet.getInt("cost"));
				p.setNumMediaAllowed(resultSet.getInt("numMediaAllowed"));
				
				lp.add(p);
			}
			
			resultSet.close();
			pstatement.close();
			connection.close();
			
		} 
		catch (SQLException e)
		{
			System.out.println("SQLState = " + e.getSQLState() + "\n" + e.getMessage());
		}
		
		
		
		return lp;
	}
}


