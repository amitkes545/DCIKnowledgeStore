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

@WebServlet("/GetCourseForAdmin")
public class GetCourseServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("get course");
			
			HttpSession session = request.getSession();
			
			String descid=request.getParameter("decsid");
			String pid=request.getParameter("pid");
			
			System.out.println("descipline id="+descid);
			System.out.println("program id="+pid);
			
			PreparedStatement ps = null;
			ResultSet rs = null;

			String json = null;
			Connection connection = DBTransaction.connect();
			
			String sql= "select course_name,course_id from transaction_course_info where program_id in (select program_id from transaction_program_info where discipline_id in(select discipline_id from transaction_discipline_info where discipline_name='"+descid+"'))";
			ps=connection.prepareStatement(sql);
		
			
			System.out.println("quuery for course="+ps);
			

			rs=ps.executeQuery();
			ArrayList<ActiveSessionDomain> arrayList2=new ArrayList<ActiveSessionDomain>();
			while(rs.next())
			{
				ActiveSessionDomain sessionDomain=new ActiveSessionDomain();
				
				sessionDomain.setCourseName(rs.getString("course_name"));
				sessionDomain.setCourseId(rs.getString("course_id"));
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
