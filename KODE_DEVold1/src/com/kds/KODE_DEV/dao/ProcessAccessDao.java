package com.kds.KODE_DEV.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.ProcessDomain;

public class ProcessAccessDao  {
	Connection connection= null;	
	//DBTransaction db= new DBTransaction();
	ResultSet rs= null;
	PreparedStatement ps=null;
	public String insertAccessDao(String userid,List<String> resultList){
		
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
	public String updateAccessDao(String userid,List<String> finalListToAdd){
		
		String status="";
		try {
			
			
			connection=DBTransaction.connect();
			String sql="insert into process_mapping(process_id,user_id)values(?,?)";
			ps = connection.prepareStatement(sql);
			//System.out.println("quary is"+sql);
			/*Iterator it=finalListToAdd.iterator();
			while(it.hasNext()){
				String value=(String) it.next();
				//System.out.println("final values are"+value);
				ps.setString(1,value);
				ps.setString(2,userid);
				ps.addBatch();
			}*/
			for(String s:finalListToAdd){
			ps.setString(1,s);
			ps.setString(2,userid);
			
			ps.addBatch();
			}
			
			
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
	public String deleteAccessDao(String userid,List<String> finalListToDelete){
		
		String status="";
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
	ArrayList<ProcessDomain> pvalue=new ArrayList<ProcessDomain>();
	public ArrayList<ProcessDomain> getOldAccessId(ProcessDomain adomain)
	{
      try {
    	  connection=DBTransaction.connect();
			String sql="select process_id from process_details where organization_id='"+adomain.getOrgId()+"'"+"and privilege='"+adomain.getPrivilege()+"'";
			ps = connection.prepareStatement(sql);
			//System.out.println("quary is"+sql);
			rs=ps.executeQuery();
			while(rs.next()){
				ProcessDomain podmain=new ProcessDomain();
				podmain.setProcessId(rs.getString("process_id"));
				pvalue.add(podmain);
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
return pvalue;
	}

public String insertAccessIdValue(ProcessDomain Adomain){
		
		String status="";
		try {
			
			
			connection=DBTransaction.connect();
			String sql="insert into process_details(process_id,process_name,organization_id,privilege)values(?,?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1,Adomain.getProcessId());
			ps.setString(2,Adomain.getProcessName());
			ps.setString(3,Adomain.getOrgId());
			ps.setString(4,Adomain.getPrivilege());
			//System.out.println("quary is"+sql);
			int n=ps.executeUpdate();
			
			if(n!=0){
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
public String deleteAccessIdValue(String delID){
	
	String status="";
	try {
		
		
		connection=DBTransaction.connect();
		String sql="delete from process_details where process_id='"+delID+"'";
		ps = connection.prepareStatement(sql);
	
		//System.out.println("quary is"+sql);
		int n=ps.executeUpdate();
		
		if(n>=0){
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
public String editAccessValues(ProcessDomain Adomain){
	
	String status="";
	try {
		
		
		connection=DBTransaction.connect();
		String sql="update process_details set process_id=?,process_name=? where process_id=?";
		ps = connection.prepareStatement(sql);
	    ps.setString(1,Adomain.getEditId());
	    ps.setString(2,Adomain.getEditName());
	    ps.setString(3,Adomain.getProcessId());
		//System.out.println("quary is"+sql);
		int n=ps.executeUpdate();
		
		if(n>=0){
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