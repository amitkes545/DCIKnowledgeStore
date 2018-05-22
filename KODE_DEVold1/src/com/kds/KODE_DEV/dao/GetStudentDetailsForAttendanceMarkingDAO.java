package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.GetSessionForFacultyDomain;

public class GetStudentDetailsForAttendanceMarkingDAO {
	Connection connection = null;
	String query = "";

	public ArrayList<GetSessionForFacultyDomain> getStudentListAttendanceMarking(String orgid, String sesId)
			throws Exception {
		
		System.out.println("dao sesid==>"+sesId);
		
		ArrayList<GetSessionForFacultyDomain> al = new ArrayList<GetSessionForFacultyDomain>();
		GetSessionForFacultyDomain getsessionfacultydomain = new GetSessionForFacultyDomain();
		try {
			query = "select row_number() over (order by a.recepient_id) as sno, a.recepient_id, b.full_name, c.credential_id, 'checked' as attendance_status from  \r\n"
					+ "			session_recepients a, transaction_enrollment_process b, student_credentials c where \r\n"
					+ "			a.organization_id = '" + orgid + "' and a.session_id = '" + sesId + "' and\r\n"
					+ "			b.enrollment_process_id = a.recepient_id and\r\n"
					+ "			c.enrollment_id = a.recepient_id";

			connection = DBTransaction.connect();
			// PreparedStatement ps= null;

			System.out.println("query==>" + query);

			ResultSet resultSet = connection.prepareStatement(query).executeQuery();

			while (resultSet.next()) {
				getsessionfacultydomain = new GetSessionForFacultyDomain();
				getsessionfacultydomain.setSno(resultSet.getString("sno"));
				getsessionfacultydomain.setClass1(resultSet.getString("credential_id"));
				getsessionfacultydomain.setEnid(resultSet.getString("recepient_id"));
				getsessionfacultydomain.setStudentName(resultSet.getString("full_name"));
				al.add(getsessionfacultydomain);

			}
			// System.out.println("dao of emailid="+al);
			// System.out.println("userId Is"+userid);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				connection.close();
				// pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return al;
	}

}
