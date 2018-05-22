package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.RetriveImagesDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

public class FacultyKnowledgeAssetsNameDao  {
	Connection con = null;
	PreparedStatement pstmt = null;
	   public ArrayList<RetriveImagesDomain> selectKnowledgeAssetsName(UsersInfoDomain uid){
			//DBTransaction1 dbt = new DBTransaction1();
			 ArrayList<RetriveImagesDomain> arl=new  ArrayList<RetriveImagesDomain>();
			try {
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
				String quary = "select subject,ksid from knowledgeassets_info where ksid=(SELECT ksid FROM knowledgestore_info where organization_id='"+uid.getOrgId()+"' and user_id='"+uid.getCreatedBy()+"')";
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					RetriveImagesDomain dcd=new RetriveImagesDomain();
					dcd.setCourseName(rs.getString("subject"));
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