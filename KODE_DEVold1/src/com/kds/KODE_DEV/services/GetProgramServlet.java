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

@WebServlet("/GetProgram")
public class GetProgramServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("get Program");
			
			HttpSession session = request.getSession();
			
			String org=(String)session.getAttribute("orgid");
			String desc=request.getParameter("selectedValue");
			PreparedStatement ps = null;
			ResultSet rs = null;

			String json = null;
			Connection connection = DBTransaction.connect();
			
			String sql= "select * from transaction_program_info where discipline_id in(select discipline_id from transaction_discipline_info where discipline_name='"+desc+"')";
			ps=connection.prepareStatement(sql);
		
			
System.out.println("gett program="+ps);
			rs=ps.executeQuery();
			ArrayList<ActiveSessionDomain> arrayList2=new ArrayList<ActiveSessionDomain>();
			while(rs.next())
			{
				ActiveSessionDomain sessionDomain=new ActiveSessionDomain();
				
				sessionDomain.setProgramId(rs.getString("program_id"));
				sessionDomain.setProgramName(rs.getString("program_name"));
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
