package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.FacilitatorSessionReportDomain;

public class FacilitatorShowSessionReportDao {
Connection connection = null;
	
	PreparedStatement preparedStatement = null;

	FacilitatorSessionReportDomain rDomain = new FacilitatorSessionReportDomain();

	public FacilitatorSessionReportDomain fetchValue(FacilitatorSessionReportDomain rDomain) {

		try {
			connection = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sql = " select DISTINCT * from session_details where session_name = '"+ rDomain.getSessionId() + "'";
			preparedStatement = connection.prepareStatement(sql);

			//System.out.println("the query is:" + sql);
			//preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				String sessionID = resultSet.getString("session_id");

				//System.out.println("userid is:" + sessionID);

				rDomain.setSessionId(resultSet.getString("session_id"));
				rDomain.setSessionName(resultSet.getString("session_name"));
				rDomain.setCategory(resultSet.getString("category"));
				rDomain.setDuration(resultSet.getString("duration"));
				rDomain.setSessionStartTime(resultSet.getString("session_start_time"));
				rDomain.setCostOfSession(resultSet.getString("cost_of_session"));
				rDomain.setFacultyName(resultSet.getString("faculty_name"));
				rDomain.setOrgId(resultSet.getString("organization_id"));
				rDomain.setRecipientType(resultSet.getString("recipient_type"));
				rDomain.setSessionEndTime(resultSet.getString("session_end_time"));
				rDomain.setSessionStatus(resultSet.getString("session_status"));	
			}
			//System.out.println("the return value is:" + rDomain);

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return rDomain;
	}
}