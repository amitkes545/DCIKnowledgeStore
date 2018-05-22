package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

public class InstitutionsListDao {
	Connection con = null;
	PreparedStatement pstmt = null;
	 public ArrayList<DisplayCoursesDomain> selectInstitutions(){
			//DBTransaction dbt = new DBTransaction();
			 ArrayList<DisplayCoursesDomain> arl=new  ArrayList<DisplayCoursesDomain>();
			try {
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
				String quary = "select distinct org_details,institute_logo_path from users_info except select org_details,institute_logo_path from users_info where org_details='Kompac Digital Systems'";
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next()){
					DisplayCoursesDomain dcd=new DisplayCoursesDomain();
					dcd.setCourseName(rs.getString("org_details"));
					dcd.setImagePath(rs.getString("institute_logo_path"));
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
