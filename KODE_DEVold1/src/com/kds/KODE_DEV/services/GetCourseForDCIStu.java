package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.CourseDomain;
import com.kds.KODE_DEV.domain.CourseSubjectStudentDomain;

@WebServlet("/GCFS")
public class GetCourseForDCIStu extends HttpServlet{

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String uid=(String)session.getAttribute("userId");
			PreparedStatement ps = null;
			ResultSet rs = null;
			System.out.println("Entered into servlet services");
			String json = null;
			Connection connection = DBTransaction.connect();
			//String courseId=(String)session.getAttribute("courseId");
			String orgId=(String)session.getAttribute("orgid");
			
			System.out.println("orgId====>"+orgId);
			
			String courseId=request.getParameter("selectedValue");
			
			String course=request.getParameter("selectedValueCourse");
			String subject=request.getParameter("selectedValueSubject");
			
			System.out.println(course+" For Library  "+subject);
			session.setAttribute("courseId", courseId);
			/*CourseDomain coursedomain=new CourseDomain();
			coursedomain.setCourse_id(courseId);
			*/
			
			System.out.println("courseId====>"+courseId);
			
			CourseSubjectStudentDomain courseSubjectIdDomain=new CourseSubjectStudentDomain();
			courseSubjectIdDomain.setCourseId(courseId);
			
			
			System.out.println("get course Id  "+courseSubjectIdDomain.getCourseId());
			
		//	String sql= "select subject_id,subname from subject_description where course_id_defined_by_teaching_source in(select course_id_defined_by_teaching_source from course_description where course_id_defined_by_teaching_source in (select course_id from users_info where user_id='"+uid+"'))";
			
			String sql= "select subject_id, subname from subject_description where organization_id = '"+orgId+"' and course_id_defined_by_teaching_source  = '"+courseId+"'";
			
			
			ps=connection.prepareStatement(sql);
			//ps.setString(1, session.getAttribute("orgid").toString());
			//ps.setString(2, request.getParameter("selectedValue"));
                 System.out.println("queryforstudent="+ps);
			rs=ps.executeQuery();
			
			ArrayList<CourseSubjectStudentDomain> arrayList2=new ArrayList<CourseSubjectStudentDomain>();
			while(rs.next())
			{
				CourseSubjectStudentDomain CourseSubjectStudentDomain=new CourseSubjectStudentDomain();
				
				CourseSubjectStudentDomain.setSubjectId(rs.getString("subject_id"));
				//CourseSubjectStudentDomain.setSubjectName(rs.getString("subname"));

				arrayList2.add(CourseSubjectStudentDomain);
				System.out.println("===="+CourseSubjectStudentDomain.getSubjectId());
				//System.out.println("==="+CourseSubjectStudentDomain.getSubjectName());
			}
		
		json = new Gson().toJson(arrayList2);
			response.setContentType("text/plain");
			response.getWriter().write(json);
			
			System.out.println("json is running");
		connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
