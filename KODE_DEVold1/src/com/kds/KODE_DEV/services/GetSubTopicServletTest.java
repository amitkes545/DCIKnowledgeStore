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
import com.kds.KODE_DEV.domain.SubTopicInfoDomain;
import com.kds.KODE_DEV.domain.TopicInfoDomain;

@WebServlet("/getSubTopics1")
public class GetSubTopicServletTest  extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			PreparedStatement ps = null;
			ResultSet rs = null;
			System.out.println("Entered into servlet services Test");
			String json = null;
		/*	Connection connection = DBTransaction.connect();
			String sql = "select sub_topic_id, subtopicname, topic_id from sub_topic_description where topic_id=?";
			ps = connection.prepareStatement(sql);
			System.out.println("org id in services;"+session.getAttribute("orgid").toString());
			System.out.println("Selected value of course "+request.getParameter("selectedValue"));
			System.out.println("Selected value hidden :"+request.getParameter("data"));
			ps.setString(1, request.getParameter("selectedValue"));
			rs = ps.executeQuery();*/
			List<SubTopicInfoDomain> subTopics = new  ArrayList<SubTopicInfoDomain>();
			//System.out.println("Query :"+sql);
		
				SubTopicInfoDomain subTopic = new SubTopicInfoDomain();
				//System.out.println("Elements :" + rs.getString(1));
			subTopic.setDate("12/03/1234");
				subTopics.add(subTopic);
			
			json = new Gson().toJson(subTopics);
			System.out.println("json="+json);
			response.setContentType("text/plain");
			response.getWriter().write(json);
			
			
			} catch (Exception e) {
			e.printStackTrace();
		}
	}

}