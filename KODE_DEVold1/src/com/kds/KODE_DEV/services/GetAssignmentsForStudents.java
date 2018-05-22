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
import com.kds.KODE_DEV.dao.AssessmentDao;
import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.CourseDomain;
import com.kds.KODE_DEV.domain.CourseSubjectStudentDomain;

@WebServlet("/GAFStudent")
public class GetAssignmentsForStudents extends HttpServlet{

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String uid=(String)session.getAttribute("userId");
			String organizationId=(String)session.getAttribute("orgid");
			//String courseId=request.getParameter("selectedValue");
			String subjectId=request.getParameter("selectedValue");
			
			
			
			
			
			
			CourseSubjectStudentDomain courseIdDomain=new CourseSubjectStudentDomain();
			String courseId=(String)session.getAttribute("courseId");

			CourseDomain coursedomain=new CourseDomain();
			coursedomain.setCourse_id(subjectId);
			coursedomain.setSubject_id(courseId);
			System.out.println("<<<<<<<<<"+subjectId+"<<<<<<<<<"+courseId);
			
			System.out.println(uid+"=="+organizationId+"=="+courseId+"=="+subjectId);
			
			
			String created_by=(String)session.getAttribute("createdBy");
			PreparedStatement ps = null;
			ResultSet rs = null;
			System.out.println("Entered into servlet services for assignments");
			String json = null;
			Connection connection = DBTransaction.connect();
			
			//String sql= "select assessment_name from assessment_info where uploaded_by='"+created_by+"'";
			
			AssessmentDao assesmentId=new AssessmentDao();
			String assesmentId1=assesmentId.getAssessmentIdBsedOnOrgid(organizationId);
			
			System.out.println("assesmentId1==>"+assesmentId1);
			
			String sql= "select a.assessment_id, a.assessment_name from assessment_info a, assessment_recepients b  where b.recepient_id = '"+uid+"' and (b.assessment_id = 'assess100' or b.assessment_id like '%') and a.organization_id = '"+organizationId+"' and a.course_id = '"+courseId+"' and a.subject_id = '"+subjectId+"' and  a.assessment_id = b.assessment_id";
		
			//String sql="select distinct a.assessment_id, a.assessment_name from assessment_info a, assessment_recepients b  where   a.organization_id = '"+organizationId+"' and a.course_id = '"+courseId+"' and a.subject_id ='"+subjectId+"'";
			ps=connection.prepareStatement(sql);
			
System.out.println("queryforstudent="+ps);
			rs=ps.executeQuery();
			
			ArrayList<CourseSubjectStudentDomain> arrayList2=new ArrayList<CourseSubjectStudentDomain>();
			while(rs.next())
			{
				CourseSubjectStudentDomain CourseSubjectStudentDomain=new CourseSubjectStudentDomain();
				
				
				CourseSubjectStudentDomain.setAssignmenId(rs.getString("assessment_id"));
System.out.println(CourseSubjectStudentDomain.getAssignmenId());
				arrayList2.add(CourseSubjectStudentDomain);
				
			}
		
		json = new Gson().toJson(arrayList2);
			response.setContentType("text/plain");
			response.getWriter().write(json);
			
			
		connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
