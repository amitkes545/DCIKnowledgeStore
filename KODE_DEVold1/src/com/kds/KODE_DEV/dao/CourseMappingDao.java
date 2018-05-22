package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SubTopicInfoDomain;

public class CourseMappingDao {

	public String getFacultyEmail(String userid, String orgId) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		String email = "";
		Connection connection = null;

		try {
		    connection = DBTransaction.connect();

			String sql = "select user_id, email from users_info where organization_id=? and privilege=? and user_id=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, orgId);
			ps.setString(2, "Faculty");
			ps.setString(3, userid);
			rs = ps.executeQuery();
			System.out.println("Query :" + sql);
			while (rs.next()) {
				email = rs.getString(2);
				
			}
			System.out.println("Eamil Id for the user assigned :"+email);
			return email;
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

	public SubTopicInfoDomain getSubTopicDetails(String subTopic) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
			connection = DBTransaction.connect();

			String sql = "select sub_topic_id, subtopicname, topic_id from sub_topic_description where sub_topic_id=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, subTopic);
			rs = ps.executeQuery();
			System.out.println("Query :" + sql);
			SubTopicInfoDomain topicDomain = new SubTopicInfoDomain();
			while (rs.next()) {
				
				topicDomain.setSubTopicId(rs.getString(1));
				topicDomain.setSubTopic(rs.getString(2));
				topicDomain.setTopicId(rs.getString(3));
			}
			return topicDomain;
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
	
	public boolean getDuplicateCourseMapping(String userId, String course, String orgId) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isDuplicate = false;
		Connection connection = null;
		try {
		    connection = DBTransaction.connect();

			String sql = "select admin_user_id, course_id_defined_by_teaching_source from superadmin_admin_mapping where course_id_defined_by_teaching_source=? and organization_id=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, course);
			ps.setString(2, orgId);
			rs = ps.executeQuery();
			System.out.println("Query :" + sql);
			while (rs.next()) {
				
				isDuplicate = true;
			}
			return isDuplicate;
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
		
		return isDuplicate;

	}
	
	

}
