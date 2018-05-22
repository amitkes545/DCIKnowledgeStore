package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.CourseAdminDomain;
import com.kds.KODE_DEV.domain.CourseFacultyDomain;
import com.kds.KODE_DEV.domain.SubjectInfoDomain;

public class GetCourseForFacultyDao {
	
	

	public List<CourseFacultyDomain> getCourseInAssignmentForFaculty(String userId,String orgId) {
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = null;
		try{
		    connection = DBTransaction.connect();
			
			String sql = Queries.getCourseForFaculty;
		    
		  /*  String sql="select distinct(a.course_id), c.department_name from assessment_info a, mst_course_catalogue b, transaction_department_info c where "+ 
		    		   "a.organization_id = ? and a.uploaded_by = ? and b.organization_id = a.organization_id and "+
		    		   "b.courseid_by_ts = a.course_id and c.department_id = b.dept_id";*/
		    
			ps = connection.prepareStatement(sql);
			ps.setString(1, userId);
			//ps.setString(2, userId);
			rs = ps.executeQuery();
			List<CourseFacultyDomain> courses = new  ArrayList<CourseFacultyDomain>();
			System.out.println("Query :"+sql);
			while(rs.next()) {
				CourseFacultyDomain course = new CourseFacultyDomain();
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
				
				ps.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public List<CourseFacultyDomain> getCourseForFaculty(String userId) {
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = null;
		try{
		    connection = DBTransaction.connect();
			
			String sql = Queries.getCourseForFaculty;
			ps = connection.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			List<CourseFacultyDomain> courses = new  ArrayList<CourseFacultyDomain>();
			System.out.println("Query :"+sql);
			while(rs.next()) {
				CourseFacultyDomain course = new CourseFacultyDomain();
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
	
	public List<CourseFacultyDomain> getCourseForFacultyMarkingAttedance(String userId,String orgId) {
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = null;
		try{
		    connection = DBTransaction.connect();
			
		//	String sql = Queries.getSubjectForFacultyForAttendanceMarking;
			
			String sql="select distinct(a.course_id_defined_by_teaching_source), c.department_name from "
+"admin_faculty_mapping a, mst_course_catalogue b, transaction_department_info c  where "
+"a.faculty_user_id ='"+userId+"' 	and a.organization_id = '"+orgId+"' and"
+" b.organization_id = a.organization_id and b.deptid_by_ts = a.course_id_defined_by_teaching_source and"
+" c.department_id = b.dept_id ";
			
			ps = connection.prepareStatement(sql);
		//	ps.setString(1, userId);
		//	ps.setString(2, orgId);
			
			System.out.println("prepare statement="+ps);
			rs = ps.executeQuery();
			List<CourseFacultyDomain> courses = new  ArrayList<CourseFacultyDomain>();
			System.out.println("Query :"+sql);
			while(rs.next()) {
				CourseFacultyDomain course = new CourseFacultyDomain();
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
	
	
	
	
		


}
