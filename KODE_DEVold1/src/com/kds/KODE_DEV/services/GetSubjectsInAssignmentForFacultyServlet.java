package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kds.KODE_DEV.dao.Queries;
import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.CourseAdminDomain;
import com.kds.KODE_DEV.domain.SubjectInfoDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

@WebServlet("/getSubjectsInAssignmentForFaculty")
public class GetSubjectsInAssignmentForFacultyServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			session.setAttribute("courseid", request.getParameter("selectedValue"));
			PreparedStatement ps = null;
			ResultSet rs = null;
			System.out.println("Entered into servlet services");
			String json = null;
			Connection connection = DBTransaction.connect();
			//String sql = "select subject_id, subname from subject_description where organization_id=? and course_id_defined_by_teaching_source=?";
			//String sql=Queries.getSubjectForFaculty;
			String sql="select distinct(a.subject_id), b.subname from assessment_info a, subject_description b where a.organization_id = ? and "+
			"a.uploaded_by = ? and (a.course_id = ? or a.course_id  like '%') and "+
			"b.organization_id = a.organization_id and b.subject_id = a.subject_id";
			
			ps = connection.prepareStatement(sql);
			System.out.println("org id in services;"+session.getAttribute("orgid").toString());
			System.out.println("Selected value of course "+request.getParameter("selectedValue"));
			System.out.println("Selected value hidden :"+request.getParameter("data"));
			ps.setString(1, (String)session.getAttribute("orgid"));
			ps.setString(2, (String)session.getAttribute("userid"));
		    ps.setString(3, request.getParameter("selectedValue"));
		    
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
	}
}
