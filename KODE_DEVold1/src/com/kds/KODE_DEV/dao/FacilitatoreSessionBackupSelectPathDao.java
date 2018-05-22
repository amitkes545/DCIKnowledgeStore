package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.FacilitatorSessionReportDomain;

public class FacilitatoreSessionBackupSelectPathDao {
	Connection con=null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt1 = null;
	FacilitatorSessionReportDomain fDomain=new FacilitatorSessionReportDomain();

	       ArrayList<String> arl=new ArrayList<String>();
	        
	       public ArrayList<String> fetchValue() { 
		   
	    	  
	
		try {
			con = DBTransaction.connect();
			
			//PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql="select session_id from session_backup where session_id='"+fDomain.getSessionId()+"'";
			//System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			//System.out.println("the query is:" + sql);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) 
			{
     				fDomain.setSessionId(rs.getString("session_id"));
     				String sessionID = rs.getString("session_Id");
     				arl.add(sessionID);
			}
			
			
			ResultSet rs1 = null;
			String sql2 = "select session_name from session_details where faculty_name='"+fDomain.getFacultyId()+"'";
			//System.out.println(sql2);
			pstmt1 = con.prepareStatement(sql2);
			rs1=pstmt1.executeQuery();
			if(rs.next())
			{
				fDomain.setSessionName(rs1.getString("session_name"));
				String sessionName = rs1.getString("session_name");
				arl.add(sessionName);
			}
			
					}catch(Exception e){
			e.printStackTrace();
		}
		finally
		   {
			   try{
			   con.close();
			   pstmt.close();
			   pstmt1.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arl;
	  }	
}