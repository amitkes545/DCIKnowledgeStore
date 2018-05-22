package com.kds.KODE_DEV.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;

/**
 * Servlet implementation class SuperAdminAccessControlDao
 */
//@WebServlet("/SuperAdminAccessControlDao")
public class SuperAdminAccessControlDao  {
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
		return status;
	}
	public ArrayList<AdminDomain> retriveAllDetails(AdminDomain sadomain)
	{
		
		ArrayList<AdminDomain> admindomain=new ArrayList<AdminDomain>();
		//System.out.println("admin id :"+sadomain.getAdminId());
				try {

			
					connection=DBTransaction.connect();
			String sql= "select pmap.process_id,pd.process_name from process_mapping pmap,process_details pd where pmap.process_id=pd.process_id and pmap.user_id=? and pd.organization_id=?";;
			//System.out.println("quary is"+sql);
			ps=connection.prepareStatement(sql);
			ps.setString(1, sadomain.getAdminId());
			ps.setString(2,sadomain.getOrgid());
			rs=ps.executeQuery();
				while(rs.next())
				{	
					AdminDomain ad=new AdminDomain();
				ad.setProcessid(rs.getString("process_id"));
				ad.setProcessname(rs.getString("process_name"));
				//System.out.println("result process id:"+rs.getString("process_id"));
				admindomain.add(ad);
				 }
			 
		}catch(Exception e){
			e.printStackTrace();
		}
		//System.out.println("status value is"+sadomain);
	
	  return admindomain;
	}
}
