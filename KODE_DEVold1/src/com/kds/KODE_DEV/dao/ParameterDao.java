package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kds.KODE_DEV.dbconnection.DBTransaction;


public class ParameterDao  {
	//DBTransaction db= new DBTransaction();
	Connection connection= null;	
	
	ResultSet resulSet= null;
	PreparedStatement prepareStatement=null;
	
public String GetParameterValue(String parameter)
{
    String parametervalue = "";
	
	try {

		connection = DBTransaction.connect();
		String query="select * from parameters where parameter='"+parameter+"'";
		prepareStatement = connection.prepareStatement(query);
		System.out.println("query is:"+prepareStatement);
		resulSet=prepareStatement.executeQuery();
		
		if(resulSet.next())
		{
			//
			//ParameterDomain nDomain=new ParameterDomain();
			//nDomain.setNum(resulSet.getInt("no_of_participant"));
			parametervalue=resulSet.getString("value");
			System.out.println("prameter value in dao"+ parametervalue);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally
	   {
		   try{
		   connection.close();
		   prepareStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	return parametervalue;
		
}
}
