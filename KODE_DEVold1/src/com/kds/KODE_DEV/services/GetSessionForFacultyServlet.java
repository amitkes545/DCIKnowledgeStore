package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kds.KODE_DEV.dao.GetSessionForFacultyDao;
import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.GetSessionForFacultyDomain;

@WebServlet("/getSessionForFaculty")
public class GetSessionForFacultyServlet extends HttpServlet{
     
	private static final long serialVersionUID = 1L;
	String json = null;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		GetSessionForFacultyDao getsessionforfaculty=new GetSessionForFacultyDao();
		ArrayList<GetSessionForFacultyDomain> arl = new ArrayList<GetSessionForFacultyDomain>();
		
		System.out.println("json started");
		
		
		//ArrayList<GetSessionForFacultyDomain> sessionList= getsessionforfaculty.selectSession( request.getParameter("date"),(String)session.getAttribute("userid"),request.getParameter("selectedValue"));
		
		String date=request.getParameter("date");
		String facultyName=(String)session.getAttribute("userid");
		String course=request.getParameter("selectedValue");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=DBTransaction.connect();

		Date initDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		String parsedDate = formatter.format(initDate);
		System.out.println(parsedDate);

		ResultSet rs = null;
		String quary = "select session_id, session_name, session_start_time from session_details where " + 
				"category = '"+course+"' and faculty_name = '"+facultyName+"'  and to_date(session_start_time,'YYYY-MM-DD') = '"+parsedDate+"' ";
		System.out.println("quary==>" + quary);
		pstmt = con.prepareStatement(quary);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			System.out.println("hello json");
			GetSessionForFacultyDomain getSessionDomain = new GetSessionForFacultyDomain();
			getSessionDomain.setSessionName(rs.getString("session_name"));
			getSessionDomain.setSessionId(rs.getString("session_id"));
			
			
			session.setAttribute("se", getSessionDomain.getSessionId());
			
			arl.add(getSessionDomain);
		}
		
		
		
		
		//System.out.println(sessionList.toString());
		
		json = new Gson().toJson(arl);
		response.setContentType("text/plain");
		response.getWriter().write(json);
		
		
		
		System.out.println("course==>"+request.getParameter("selectedValue"));
		
		System.out.println("hii");
		
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		/*try {
			HttpSession session = request.getSession();
			session.setAttribute("courseid", request.getParameter("selectedValue"));
			PreparedStatement ps = null;
			ResultSet rs = null;
			System.out.println("Entered into servlet services");
			String json = null;
			Connection connection = DBTransaction.connect();
			//String sql = "select subject_id, subname from subject_description where organization_id=? and course_id_defined_by_teaching_source=?";
			String sql=Queries.getSubjectForFaculty;
			ps = connection.prepareStatement(sql);
			System.out.println("org id in services;"+session.getAttribute("orgid").toString());
			System.out.println("Selected value of course "+request.getParameter("selectedValue"));
			System.out.println("Selected value hidden :"+request.getParameter("data"));
			ps.setString(1, (String)session.getAttribute("userid"));
		    ps.setString(2, request.getParameter("selectedValue"));
			rs = ps.executeQuery();
			List<SubjectInfoDomain> subjects = new  ArrayList<SubjectInfoDomain>();
			System.out.println("Query :"+sql);
			while (rs.next()) {
				SubjectInfoDomain subject = new SubjectInfoDomain();
				System.out.println("Elements :" + rs.getString(1));
				subject.setSubjectId(rs.getString(1));
				subject.setSubject(rs.getString(2));
				subjects.add(subject);
			}
			json = new Gson().toJson(subjects);
			response.setContentType("text/plain");
			response.getWriter().write(json);
			
			
		connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}

	
}