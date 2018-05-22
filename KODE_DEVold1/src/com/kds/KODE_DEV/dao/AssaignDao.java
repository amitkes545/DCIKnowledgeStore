package com.kds.KODE_DEV.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;

/**
 * Servlet implementation class AssaignDao
 */

public class AssaignDao   {
	Connection con= null;	
	//DBTransaction db= new DBTransaction();
	
	PreparedStatement ps=null;
	public String insertFacultyValues(AdminDomain adomain,String subjectid){
		String status = null;
		////System.out.println("userid is"+facultyid);
		//String adminid=pdomain.getAdminId();
		try {

			
			con=DBTransaction.connect();
			String sql= "insert into faculty_subject_mapping values(?,?,?,?)";
			
			ps=con.prepareStatement(sql);
			//System.out.println("quary is"+sql);
			ps.setString(1,adomain.getAdminId());
			ps.setString(2,subjectid);
			ps.setString(3,adomain.getOrgid());
			ps.setString(4,adomain.getStream());
			int i=ps.executeUpdate();
			if(i==1){
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
			   con.close();
			   ps.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return status;
		
	}
	  ArrayList<AdminDomain> sd=new ArrayList<AdminDomain>();
	   public ArrayList<AdminDomain> selectSubjectId(AdminDomain sdomain){
		   try {
			  
			   
			   con=DBTransaction.connect();
				ResultSet rs=null;
				String sql="select subject_id from faculty_subject_mapping where faculty_id='"+sdomain.getAdminId()+"'"+" and organization_id='"+sdomain.getOrgid()+"'";
				ps=con.prepareStatement(sql);
				//System.out.println("sql quary is:"+sql);
				rs=ps.executeQuery();
				while(rs.next()){
					AdminDomain sDomain=new AdminDomain();
					sDomain.setSubject_id(rs.getString("subject_id"));
					sd.add(sDomain);
				}
				
				
	   }catch(Exception e){
		   e.printStackTrace();
	   }
		   finally
		   {
			   try{
			   con.close();
			   ps.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
	   return sd;
	
	   }
	   public String deleteAssaignDao(String facultyid,List<String> finalListToDelete){
			
			String status="";
			try {
				
				
				con=DBTransaction.connect();
				String sql="delete from faculty_subject_mapping where subject_id=?";
				ps = con.prepareStatement(sql);
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
				   con.close();
				   ps.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			return status;
		}
	   public String updateAssaignDao(String userid,List<String> finalListToAdd){
			
			String status="";
			try {
				
				
				con=DBTransaction.connect();
				String sql="insert into process_mapping(process_id,role_id)values(?,?)";
				ps = con.prepareStatement(sql);
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
				   con.close();
				   ps.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
		
			return status;

}
	   public String insertAssaignDao(String userid,List<String> resultList){
			
			String status="";
			try {
				
				
				con=DBTransaction.connect();
				String sql="insert into process_mapping(process_id,role_id)values(?,?)";
				ps = con.prepareStatement(sql);
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
				   con.close();
				   ps.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			return status;
}
}
