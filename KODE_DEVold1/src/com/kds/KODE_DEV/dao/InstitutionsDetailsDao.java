package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

public class InstitutionsDetailsDao {
	Connection con = null;
	PreparedStatement pstmt = null;
	 public ArrayList<DisplayCoursesDomain> selectInstitutionsDetails(DisplayCoursesDomain dc){
			//DBTransaction dbt = new DBTransaction();
			 ArrayList<DisplayCoursesDomain> arl=new  ArrayList<DisplayCoursesDomain>();
			try {
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
				String quary = "select * from collegegrouplist where collge_name='"+dc.getCourseName()+"'";
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					DisplayCoursesDomain dcd=new DisplayCoursesDomain();
					dcd.setCourseName(rs.getString("collge_name"));
					dcd.setImagePath(rs.getString("institute_logo_path"));
					dcd.setCourseDetails(rs.getString("grouplists"));
					//System.out.println(dcd.getCourseName());
					arl.add(dcd);
				}
				}catch(Exception e){
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
		return arl;
		   
	   }
}
