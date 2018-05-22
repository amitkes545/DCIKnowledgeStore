		
	package com.kds.KODE_DEV.dao;

	import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.FacilitatorManageKnowStoreDomain;

	public class FacilitatorSelectLibIdKnowStore {
		Connection con = null;
		PreparedStatement preparedStatement = null;

	    ArrayList<String> arrayList = new ArrayList<String>();	
		public String fetchLibName(FacilitatorManageKnowStoreDomain cDomain) {
			FacilitatorManageKnowStoreDomain rDomain1=new FacilitatorManageKnowStoreDomain();
			
			String getLibName= null;

			try {
				con = DBTransaction.connect();
				
				
				//PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				String sql="select name_of_lib from knowledgestorelib_info where lib_id='"+cDomain.getLibId()+"'";
				//System.out.println(sql);
				preparedStatement = con.prepareStatement(sql);

				//System.out.println("the query is:" + sql);
				//preparedStatement = con.prepareStatement(sql);
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) 
				{
					//rDomain1.setLibId(resultSet.getString("lib_id"));
					rDomain1.setLibId(resultSet.getString("name_of_lib"));
					//String libId=resultSet.getString("lib_id");
					String libName=resultSet.getString("name_of_lib");
					getLibName=libName;
					
				}
						}catch(Exception e){
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
			return getLibName;
		
		}
	}