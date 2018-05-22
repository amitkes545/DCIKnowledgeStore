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

@WebServlet("/GLFS")
public class GetCourseForDCIStudent extends HttpServlet{

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("hii lib servlet");
		
		try {
			HttpSession session = request.getSession();
			String uid=(String)session.getAttribute("userId");
			String orgId=(String)session.getAttribute("orgid");
			PreparedStatement ps = null;
			ResultSet rs = null;
			System.out.println("Entered into servlet Library for Student services");
			String json = null;
			Connection connection = DBTransaction.connect();
		
			
			
					
			 String sql="select b.deptid_by_ts, c.department_name from transaction_enrollment_process a, mst_course_catalogue b, transaction_department_info c where a.enrollment_process_id = '"+uid+"' and b.organization_id = '"+orgId+"' and b.dept_id = a.course_id and c.department_id = b.dept_id";
			
			
			ps=connection.prepareStatement(sql);
			
               
			rs=ps.executeQuery();
			
			ArrayList<CourseSubjectStudentDomain> arrayList2=new ArrayList<CourseSubjectStudentDomain>();
			
			while(rs.next())
			{
				CourseSubjectStudentDomain CourseLibStudentDomain=new CourseSubjectStudentDomain();
				
				CourseLibStudentDomain.setLibId(rs.getString(1));
				
			
				
				//CourseSubjectStudentDomain.setSubjectName(rs.getString("subname"));

				arrayList2.add(CourseLibStudentDomain);
				//System.out.println("===="+CourseLibStudentDomain.getSubjectId());
				System.out.println("==="+arrayList2.toString());
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
