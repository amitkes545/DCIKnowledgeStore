package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminReportKnowStoreDomain;

public class AdminSelectUseridKSDao {

	AdminReportKnowStoreDomain adminReportDomain = new AdminReportKnowStoreDomain();
	ArrayList<String> arrayList=new ArrayList<String>();
	PreparedStatement pstmt = null;
	Connection con =null;
	//int counter=0;
	// method for selecting user id according to organization id and Privilege 
	public ArrayList<String> fetchUsers(String orgId) {

		try {
			 con = DBTransaction.connect();
			//PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sqlQuery = "select user_id from users_info where organization_id ='"+orgId+"' and privilege ='Faculty'";
			pstmt = con.prepareStatement(sqlQuery);

			//System.out.println("the query is:" + sqlQuery);
			pstmt = con.prepareStatement(sqlQuery);
			rs = pstmt.executeQuery();
			while(rs.next()) {		
				//System.out.println(rs.getString("user_id"));
				adminReportDomain.setKsId(rs.getString("user_id"));
				String userid=rs.getString("user_id");
				arrayList.add(userid);
				////System.out.println(counter++);			 
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
			   con.close();
				pstmt.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayList;
	}// closing fetchUsers()
}// closing class body