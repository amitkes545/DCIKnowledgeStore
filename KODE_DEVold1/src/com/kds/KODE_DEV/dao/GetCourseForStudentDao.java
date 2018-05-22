package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AssessmentDomain;
import com.kds.KODE_DEV.domain.CourseAdminDomain;
import com.kds.KODE_DEV.domain.CourseStudentDomain;
import com.kds.KODE_DEV.domain.SubjectInfoDomain;

public class GetCourseForStudentDao {
	
    
	public List<CourseStudentDomain> getCourseForStudent(String userId) {
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = null;
		try{
		    connection = DBTransaction.connect();
			
		    System.out.println("HELLO JSON");
		    
		    AssessmentDomain assignment=new AssessmentDomain();
		    
		    String organizationId=assignment.getOrgId();
		   
		    
		    System.out.println("userId==>"+userId);
		    
		    System.out.println("organizationId===>"+organizationId);
		    
		//	String sql = Queries.getCourseForStudent;
		  // String sql="select course_id from users_info where user_id='"+userId+"'";
		    
		  // String userName=assignment.getOrgId();
		   
		   // System.out.println("userName===>"+userName);
		   
		 String sql="select b.deptid_by_ts, c.department_name from transaction_enrollment_process a, mst_course_catalogue b, transaction_department_info c where a.enrollment_process_id = '"+userId+"' and b.organization_id = '"+organizationId+"' and b.dept_id = a.course_id and c.department_id = b.dept_id";
 
		 
			ps = connection.prepareStatement(sql);
		//	ps.setString(1, userId);
			rs = ps.executeQuery();
			List<CourseStudentDomain> courses = new  ArrayList<CourseStudentDomain>();
			System.out.println("Query :"+sql);
			while(rs.next()) {
				CourseStudentDomain course = new CourseStudentDomain();
				System.out.println("Elements :"+rs.getString(1));
				/*course.setCourseId(rs.getString(1));
				course.setCourseName(rs.getString(1));*/
				
				course.setCourseName(rs.getString("deptid_by_ts"));
				
				
				
				courses.add(course);
			}
			return courses;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	//get course id
	
	
	public List<CourseStudentDomain> getCourseIdForStudent(String userid1,String organizationId1) {
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = null;
		try{
		    connection = DBTransaction.connect();
			
		    System.out.println("HELLO JSON");
		    
		    AssessmentDomain assignment=new AssessmentDomain();
		    
		 //   String organizationId1=organizationId1;
		   
		    
		   // System.out.println("userId==>"+userId);
		    
		   // System.out.println("organizationId===>"+organizationId);
		    
		//	String sql = Queries.getCourseForStudent;
		  // String sql="select course_id from users_info where user_id='"+userId+"'";
		    
		  // String userName=assignment.getOrgId();
		   
		   // System.out.println("userName===>"+userName);
		   
		 String sql="select b.deptid_by_ts, c.department_name from transaction_enrollment_process a, mst_course_catalogue b, transaction_department_info c where a.enrollment_process_id = '"+userid1+"' and b.organization_id = '"+organizationId1+"' and b.dept_id = a.course_id and c.department_id = b.dept_id";
 
		 System.out.println(organizationId1+"=="+userid1);
		 
			ps = connection.prepareStatement(sql);
			
		//	ps.setString(1, userId);
			rs = ps.executeQuery();
			List<CourseStudentDomain> courses = new  ArrayList<CourseStudentDomain>();
			System.out.println("Query :"+sql);
			while(rs.next()) {
				CourseStudentDomain course = new CourseStudentDomain();
				System.out.println("Elements :"+rs.getString(1));
				course.setCourseId(rs.getString(1));
				//course.setCourseName(rs.getString(1));
				
			/*	course.setCourseName(rs.getString("deptid_by_ts"));*/
				
				
				
				courses.add(course);
			}
			return courses;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	//GetCourse
	public List<CourseStudentDomain> getAllLibrary(String organizationId1) {
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = null;
		try{
		    connection = DBTransaction.connect();
			
		    System.out.println("HELLO JSON");
		    
		    
		//	String sql = Queries.getCourseForStudent;
		    String sql="select lib_id,name_of_lib from knowledgestorelib_info where user_id='SRMSRM04004' and organization_id='"+ organizationId1+"'";
 
			ps = connection.prepareStatement(sql);
		//	ps.setString(1, userId);
			rs = ps.executeQuery();
			List<CourseStudentDomain> courses = new  ArrayList<CourseStudentDomain>();
			System.out.println("Query :"+sql);
			while(rs.next()) {
				CourseStudentDomain course = new CourseStudentDomain();
				System.out.println("Elements :"+rs.getString(1));
			  course.setCourseId(rs.getString(1));
				course.setCourseName(rs.getString(2));
				courses.add(course);
			}
			return courses;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<CourseStudentDomain> getCourseForFaculty(String userId) {
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = null;
		try{
		    connection = DBTransaction.connect();
			
		    System.out.println("HELLO JSON");
		    
		    
		//	String sql = Queries.getCourseForStudent;
		    String sql="select course_id_defined_by_teaching_source from admin_faculty_mapping where faculty_user_id='"+userId+"'";
 
			ps = connection.prepareStatement(sql);
		//	ps.setString(1, userId);
			rs = ps.executeQuery();
			List<CourseStudentDomain> courses = new  ArrayList<CourseStudentDomain>();
			System.out.println("Query :"+sql);
			while(rs.next()) {
				CourseStudentDomain course = new CourseStudentDomain();
				System.out.println("Elements :"+rs.getString(1));
				course.setCourseId(rs.getString(1));
				course.setCourseName(rs.getString(1));
				courses.add(course);
			}
			return courses;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	public List<CourseAdminDomain> getCourseForAdminFaculty(String orgId, String userId) {
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = null;
		try{
			connection = DBTransaction.connect();
			
			
			String sql = "select course_id_defined_by_teaching_source from superadmin_admin_mapping where organization_id=? and admin_user_id=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, orgId);
			ps.setString(2, userId);
			rs = ps.executeQuery();
			List<CourseAdminDomain> courses = new  ArrayList<CourseAdminDomain>();
			System.out.println("Query :"+sql);
			while(rs.next()) {
				CourseAdminDomain course = new CourseAdminDomain();
				System.out.println("Elements :"+rs.getString(1));
				course.setCourseId(rs.getString(1));
				courses.add(course);
			}
			return courses;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	public List<SubjectInfoDomain> getSubjectForAdminFaculty(String orgId, String courseId) {
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = null;
		try{
			connection = DBTransaction.connect();
			
			
			String sql = "select subject_id, subname from subject_description where organization_id=? and course_id_defined_by_teaching_source=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, orgId);
			ps.setString(2, courseId);
			rs = ps.executeQuery();
			List<SubjectInfoDomain> subjects = new  ArrayList<SubjectInfoDomain>();
			System.out.println("Query :"+sql);
			while(rs.next()) {
				SubjectInfoDomain subject = new SubjectInfoDomain();
				System.out.println("Elements :"+rs.getString(1));
				subject.setSubjectId(rs.getString(1));
				subject.setSubject(rs.getString(2));
				subjects.add(subject);
			}
			return subjects;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	


}
