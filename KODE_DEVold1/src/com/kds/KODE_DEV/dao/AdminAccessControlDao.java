package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.kds.KODE_DEV.dbconnection.DBTransaction;

public class AdminAccessControlDao  {
	
	Connection connection= null;
	PreparedStatement ps=null;
	String status="";
	
	public String insertAdminAccessDao(String userid, List<String> resultList){
		
		String status="";
		
		try {
			connection=DBTransaction.connect();
			String sql="insert into process_mapping(process_id,user_id)values(?,?)";
			ps = connection.prepareStatement(sql);
			//System.out.println("quary is"+sql);
			for(String s:resultList){
			ps.setString(1,s);
			ps.setString(2,userid);
			
			ps.addBatch();
			}
			int[] count=ps.executeBatch();
			//System.out.println(count+"values are inserted");
			if(count!=null){
				status="success";
			}else {
				status="falure";
			}
			
	}catch(Exception e){
		e.printStackTrace();

		
	}
		finally
		   {
			   try{
			   connection.close();
				ps.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return status;
	}
	public String updateAdminAccessDao(String userid,List<String> finalListToAdd){
		
		String status="";
		try {
			
			connection=DBTransaction.connect();
			String sql="insert into process_mapping(process_id,user_id)values(?,?)";
			ps = connection.prepareStatement(sql);
			//System.out.println("quary is"+sql);
			
			for(String s:finalListToAdd){
			ps.setString(1,s);
			ps.setString(2,userid);
			
			ps.addBatch();
			//System.out.println("result query:"+ps);
			}
			//System.out.println("result query:"+ps);
          int[] count=ps.executeBatch();
			//System.out.println(count+"values are updated");
			if(count!=null){
				status="success";
			}else {
				status="failure";
			}
			
			
	}catch(Exception e){
		e.printStackTrace();
		
	}
		finally
		   {
			   try{
			   connection.close();
				ps.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return status;
	
}
	public String deleteAdminAccessDao(String userid,List<String> finalListToDelete){
		
		try {
			
			connection=DBTransaction.connect();
			String sql="delete from process_mapping where process_id=?";
			ps = connection.prepareStatement(sql);
			//System.out.println("quary is"+sql);
			for(String s:finalListToDelete){
				ps.setString(1,s);
				
				
				ps.addBatch();
				}
				
				int[] count=ps.executeBatch();
		   
			if(count!=null){
				status="success";
			}else {
				status="failure";
			}
		

	}catch(Exception e){
		e.printStackTrace();
	
		
	}
		finally
		   {
			   try{
			   connection.close();
				ps.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return status;
	}
}