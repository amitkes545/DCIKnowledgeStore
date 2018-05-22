package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.CourseAdminDomain;
import com.kds.KODE_DEV.domain.SubjectInfoDomain;

public class GetCourseForAdminDao {
	

	public List<CourseAdminDomain> getCourseForAdmin(String orgId) {
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = null;
		try{
		    connection = DBTransaction.connect();
			
			
			String sql = "select course_id_defined_by_teaching_source,course_name from course_description where customer_id=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, orgId);
			
			rs = ps.executeQuery();
			List<CourseAdminDomain> courses = new  ArrayList<CourseAdminDomain>();
			System.out.println("Query :"+sql);
			while(rs.next()) {
				CourseAdminDomain course = new CourseAdminDomain();
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
