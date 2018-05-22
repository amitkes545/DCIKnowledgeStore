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
import com.kds.KODE_DEV.domain.FacilitatorKnowReportDomain;
import com.kds.KODE_DEV.domain.TopicInfoDomain;

@WebServlet("/getLibarayForFaculty")
public class GetLibaryForFacultyServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			PreparedStatement ps = null;
			ResultSet rs = null;
			System.out.println("Entered into GetLibaryForFacultyServlet  services ");
			String json = null;
			Connection connection = DBTransaction.connect();
			
			String orgId= session.getAttribute("orgid").toString();
			String userId= session.getAttribute("userId").toString();
			String courseId=request.getParameter("courseid");
			String subId=request.getParameter("subjectid");
			String selectedValue=request.getParameter("selectedValue");
			
			System.out.println("org id in services;"+orgId);
			System.out.println("userId in services;"+userId);
			System.out.println("courseid in services;"+courseId);
			System.out.println("subjectid in services;"+subId);
			
			
			System.out.println("org id in services;"+session.getAttribute("orgid").toString());
			System.out.println("org id in services;"+session.getAttribute("userId").toString());
			System.out.println("Selected value of course "+request.getParameter("selectedValue"));
			
			
			
			String sql = " select distinct(a.lib_id), b.name_of_lib from knowledgeassets_info a,"
					+ " knowledgestorelib_info b where  "
					+ " a.uploaded_by = '"+userId+"' and  "
					+ " a.course_id = '"+courseId+"' and a.subject =  '"+subId+"'  and "
					+ " b.lib_id = a.lib_id";
			//preparedStatement = connection.prepareStatement(sql);

			System.out.println("the query in FacilitatorSelectKnowStoreIdDao:" + sql);
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			List<FacilitatorKnowReportDomain> arrayListDomain= new ArrayList<FacilitatorKnowReportDomain>();
			while(rs.next()) {
				FacilitatorKnowReportDomain ffDomain = new FacilitatorKnowReportDomain();
				ffDomain.setLibId(rs.getString(1));
				//ffDomain.setKsId(resultSet.getString("ksid"));
				ffDomain.setLibName(rs.getString(2));
				//ffDomain.setLibSize(resultSet.getInt("size_of_lib"));
				arrayListDomain.add(ffDomain);
				

			}
			json = new Gson().toJson(arrayListDomain);
			response.setContentType("text/plain");
			response.getWriter().write(json);
			
			
		connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
