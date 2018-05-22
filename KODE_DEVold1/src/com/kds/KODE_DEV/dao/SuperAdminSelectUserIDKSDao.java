package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SuperAdminReportKnowStoreDomain;

public class SuperAdminSelectUserIDKSDao {

	SuperAdminReportKnowStoreDomain adminReportDomain = new SuperAdminReportKnowStoreDomain();
	ArrayList<String> arrayList=new ArrayList<String>();
	//int counter=0;
	
	public ArrayList<String> fetchUsers(String orgId) {

		try {
			Connection con = DBTransaction.connect();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "select user_id from users_info where organization_id ='"+orgId+"' and privilege ='Admin'";
			pstmt = con.prepareStatement(sql);

			//System.out.println("the query is:" + sql);
			pstmt = con.prepareStatement(sql);
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
		return arrayList;
	}
}