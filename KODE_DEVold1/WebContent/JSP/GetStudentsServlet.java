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
import com.kds.KODE_DEV.domain.ActiveSessionDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

@WebServlet("/getStudents")
public class GetStudentsServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			PreparedStatement ps = null;
			ResultSet rs = null;
			System.out.println("Entered into servlet services");
			String json = null;
			Connection connection = DBTransaction.connect();
			
			String sql= "select user_id,username from users_info where organization_id= ? and course_id=?";
			ps=connection.prepareStatement(sql);
			String org=(String)session.getAttribute("orgid");
			System.out.println("org id in services;"+session.getAttribute("orgid").toString());
			System.out.println("Selected value of course "+request.getParameter("selectedValue"));
			System.out.println("Selected value hidden :"+request.getParameter("data"));
			ps.setString(1, session.getAttribute("orgid").toString());
			ps.setString(2, request.getParameter("selectedValue"));

			rs=ps.executeQuery();
			ArrayList<ActiveSessionDomain> arrayList2=new ArrayList<ActiveSessionDomain>();
			while(rs.next())
			{
				ActiveSessionDomain sessionDomain=new ActiveSessionDomain();
				sessionDomain.setEnrid(rs.getString("user_id"));
				sessionDomain.setStudentname(rs.getString("username"));
				arrayList2.add(sessionDomain);
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
