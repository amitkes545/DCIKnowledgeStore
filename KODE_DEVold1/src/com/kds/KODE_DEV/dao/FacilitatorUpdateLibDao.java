package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.FacilitatorManageKnowStoreDomain;

public class FacilitatorUpdateLibDao {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	
	public String updateValues(ArrayList<FacilitatorManageKnowStoreDomain> arl) {

	String status=null;

	try {
                connection = DBTransaction.connect();
               /* if(arl.size()>0){
                Iterator<FacilitatorManageKnowStoreDomain> it=arl.iterator();
                while(it.hasNext()){
                FacilitatorManageKnowStoreDomain fnrDomain=it.next();*/
              
				String sql = "update knowledgestorelib_info set ksid=?,name_of_lib=?,size_of_lib=?,user_id=?,organization_id=?,space_uom=? where lib_id=?";
				preparedStatement = connection.prepareStatement(sql);
				
				System.out.println("the query is:"+sql);
				for(FacilitatorManageKnowStoreDomain s:arl){
				preparedStatement.setString(1, s.getKsId());
				preparedStatement.setString(2,s.getLibName());
				preparedStatement.setInt(3,s.getLibSize());
				preparedStatement.setString(4,s.getUserId());
				preparedStatement.setString(5,s.getOrgId());
				preparedStatement.setString(6,s.getSpaceUom());
				preparedStatement.setString(7,s.getLibId());
				
				preparedStatement.addBatch();
				
				int[] count=preparedStatement.executeBatch();
				System.out.println(Arrays.toString(count)+"updated");
				
				if (count!=null)
					status = "success";
				else
					status = "failure";
                }
                
	
			}catch(Exception e){
				e.printStackTrace();
			}
	finally
	   {
		   try{
			   
			   preparedStatement.close();
			   connection.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
			return status;
		}
	public boolean libNameDuplicate(FacilitatorManageKnowStoreDomain aksDomain) { 
		PreparedStatement preparedStatement = null; 
		ResultSet resultSet = null; 
		String userId = aksDomain.getUserId(); 
		boolean result = false; 
		try { 
			Connection connection = DBTransaction.connect();  
			String query = "select lib_id,name_of_lib from knowledgestorelib_info where name_of_lib='"+aksDomain.getStudentId()+"'";   System.out.println("the duplicate query is :"+query); 
			/*String sql="update session_details set session_name=?,category=?,duration=?  where session_id=?";  prepareStatement = connection.prepareStatement(sql);prepareStatement.addBatch();
			 * int[] count=prepareStatement.executeBatch();*/ 
			preparedStatement = connection.prepareStatement(query);  
			// preparedStatement.addBatch(); 
			// int[] count=preparedStatement.executeBatch();  
			// System.out.println("the number is :"+count);  
			//preparedStatement.setString(1, userId); 
			resultSet = preparedStatement.executeQuery(); 
			//if(count!=null)  
			if(resultSet.next())   
			result = true;  
			} catch (Exception e) { 
			e.printStackTrace();  
			}  return result;
			}
		
	
     }