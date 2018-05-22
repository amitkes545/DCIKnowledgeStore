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

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.TopicInfoDomain;

@WebServlet("/getTopicForFaculty")
public class GetTopicForFacultyServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			PreparedStatement ps = null;
			ResultSet rs = null;
			System.out.println("Entered into getTopicForFaculty servlet services");
			String json = null;
			Connection connection = DBTransaction.connect();
			String orgId= session.getAttribute("orgid").toString();
			String userId= session.getAttribute("userId").toString();
			String courseid=request.getParameter("courseid");
			String subjectid=request.getParameter("subjectid");
			String selectedValue=request.getParameter("selectedValue");
			System.out.println("org id in services;"+orgId);
			System.out.println("userId in services;"+userId);
			System.out.println("courseid in services;"+courseid);
			System.out.println("subjectid in services;"+subjectid);
			//		courseid =
			System.out.println("org id in services;"+session.getAttribute("orgid").toString());
			System.out.println("org id in services;"+session.getAttribute("userId").toString());
			System.out.println("Selected value of course "+request.getParameter("selectedValue"));
			System.out.println("Selected value hidden :"+request.getParameter("data"));
			//String sql = "select subject_id, subname from subject_description where organization_id=? and course_id_defined_by_teaching_source=?";
			//String sql=Queries.getSubjectForFaculty;
			String sql = "select distinct(a.faculty_topic_id), b.topicname from admin_faculty_mapping a, topic_description b where "
					+ " a.organization_id = '"+session.getAttribute("orgid").toString()+"' and "
					+ " a.faculty_user_id = '"+session.getAttribute("userId").toString()+"'  and "
					+ " a.course_id_defined_by_teaching_source = '"+courseid+"' and "
					+ " a.faculty_subject_id = '"+selectedValue+"' and "
					+ " b.subject_id = a.faculty_subject_id and "
					+ " b.topic_id = a.faculty_topic_id";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			List<TopicInfoDomain> subjects = new  ArrayList<TopicInfoDomain>();
			System.out.println("Query :"+sql);
			while (rs.next()) {
				TopicInfoDomain topicDomain = new TopicInfoDomain();
				System.out.println("Elements :"+rs.getString(1));
				String topicId = rs.getString(1);
				String topic =rs.getString(2);
				System.out.println("Elements :" + rs.getString(1));
				topicDomain.setTopicId(topicId);
				topicDomain.setTopic(topic);
				subjects.add(topicDomain);
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
