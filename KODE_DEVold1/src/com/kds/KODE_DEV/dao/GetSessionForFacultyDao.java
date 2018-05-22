package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.GetSessionForFacultyDomain;
import com.kds.KODE_DEV.domain.RetriveImagesDomain;

public class GetSessionForFacultyDao {

	public ArrayList<GetSessionForFacultyDomain> selectSession(String date,String facultyName,String course) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ArrayList<GetSessionForFacultyDomain> arl = new ArrayList<GetSessionForFacultyDomain>();
		try {
			
			
			Date initDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			String parsedDate = formatter.format(initDate);
			System.out.println("parse="+parsedDate);

			ResultSet rs = null;
			String quary = "select session_id, session_name, session_start_time from session_details where " + 
					"category = '"+course+"' and faculty_name = '"+facultyName+"'  and to_date(session_start_time,'YYYY-MM-DD') = '"+parsedDate+"' ";
			System.out.println("quary==>" + quary);
			pstmt = con.prepareStatement(quary);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GetSessionForFacultyDomain getSessionDomain = new GetSessionForFacultyDomain();
				getSessionDomain.setSessionName(rs.getString("session_name"));
				arl.add(getSessionDomain);
			}

		}

		catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				con.close();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return arl;

	}
}
