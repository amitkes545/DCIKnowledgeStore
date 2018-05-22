package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.FacilitatorKnowReportDomain;
import com.kds.KODE_DEV.domain.FacilitatorManageKnowStoreDomain;

public class FacilitatorKnowReportDao {
	
	Connection con = null;
	PreparedStatement preparedStatement = null;

	FacilitatorKnowReportDomain rDomain = new FacilitatorKnowReportDomain();

	public FacilitatorKnowReportDomain fetchValue(FacilitatorKnowReportDomain rDomain) {
		
		try {
			con = DBTransaction.connect();
			
			//PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			String sql = " select * from knowledgestorelib_info where lib_id = '"+ rDomain.getLibId() + "'";
			preparedStatement = con.prepareStatement(sql);

			//System.out.println("the query is:" + sql);
			//preparedStatement = con.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			if (rs.next()) {

				String LibId = rs.getString("lib_id");

				//System.out.println("LibId is:" + LibId);
                rDomain.setLibId(rs.getString("lib_id")); 
				rDomain.setKsId(rs.getString("ksid"));
				rDomain.setLibName(rs.getString("name_of_lib"));
				rDomain.setLibSize(rs.getInt("size_of_lib"));
				rDomain.setUserId(rs.getString("user_id"));
				rDomain.setOrgId(rs.getString("organization_id"));
				
			}
			//System.out.println("the return value is:" + rDomain);

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return rDomain;
	}
	
	
	ArrayList<FacilitatorManageKnowStoreDomain> arl=new ArrayList<FacilitatorManageKnowStoreDomain>();
	public ArrayList<FacilitatorManageKnowStoreDomain> fetchLibId(FacilitatorManageKnowStoreDomain aDomain) {
		
		
		try {
			con = DBTransaction.connect();
			
			//PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			String sql = " select * from knowledgestorelib_info where lib_id = '"+ aDomain.getLibId() + "'";
			preparedStatement = con.prepareStatement(sql);

			//System.out.println("the query is:" + sql);
			//preparedStatement = con.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				FacilitatorManageKnowStoreDomain rDomain1=new FacilitatorManageKnowStoreDomain();
				String LibId = rs.getString("lib_id");

				//System.out.println("libid is:" + LibId);
                rDomain1.setLibId(rs.getString("lib_id"));
				rDomain1.setKsId(rs.getString("ksid"));
				rDomain1.setLibName(rs.getString("name_of_lib"));
				rDomain1.setLibSize(rs.getInt("size_of_lib"));
				rDomain1.setUserId(rs.getString("user_id"));
				rDomain1.setOrgId(rs.getString("organization_id"));	
				rDomain1.setSpaceUom(rs.getString("space_uom"));
				arl.add(rDomain1);
			}
			//System.out.println("the return value is:" + rDomain);

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arl;
	}
	
	ArrayList<FacilitatorManageKnowStoreDomain> arl2=new ArrayList<FacilitatorManageKnowStoreDomain>();
	public ArrayList<FacilitatorManageKnowStoreDomain> fetchAllLibValue(FacilitatorManageKnowStoreDomain aDomain) {
		
		
		try {
			con = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			String sql = " select * from knowledgestorelib_info where user_id='"+aDomain.getUserId()+"' and organization_id='"+aDomain.getOrgId()+"'";
			preparedStatement = con.prepareStatement(sql);

			//System.out.println("the query is all:" + sql);
			//preparedStatement = con.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				FacilitatorManageKnowStoreDomain rDomain1=new FacilitatorManageKnowStoreDomain();
				String LibId = rs.getString("lib_id");

				//System.out.println("userid is:" + LibId);
                rDomain1.setLibId(rs.getString("lib_id"));
				rDomain1.setKsId(rs.getString("ksid"));
				rDomain1.setLibName(rs.getString("name_of_lib"));
				rDomain1.setLibSize(rs.getInt("size_of_lib"));
				rDomain1.setUserId(rs.getString("user_id"));
				rDomain1.setOrgId(rs.getString("organization_id"));	
				rDomain1.setSpaceUom(rs.getString("space_uom"));	
				arl2.add(rDomain1);
			}
			//System.out.println("the return value is:" + rDomain);

		} catch (Exception e) {
			e.printStackTrace();
		}finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arl2;
	}
}