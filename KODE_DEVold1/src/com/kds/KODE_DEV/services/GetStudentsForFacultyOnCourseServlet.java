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
import com.kds.KODE_DEV.dao.Queries;
import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.CourseAdminDomain;
import com.kds.KODE_DEV.domain.SubjectInfoDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

@WebServlet("/GSFFOCS")
public class GetStudentsForFacultyOnCourseServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			//session.setAttribute("courseid", request.getParameter("selectedValue"));
			System.out.println(request.getParameter("selectedValue"));
			PreparedStatement ps = null;
			ResultSet rs = null;
			System.out.println("Entered into servlet services for individuals");
			String json = null;
			Connection connection = DBTransaction.connect();
			String courseId=request.getParameter("selectedValue");
			//String sql = "select subject_id, subname from subject_description where organization_id=? and course_id_defined_by_teaching_source=?";
			/*String sql="select a.enrollment_process_id, a.full_name from transaction_enrollment_process a, org_details b where "+
					   "b.organization_id = '"+session.getAttribute("orgid")+"' and a.middle_layer_teaching_source_name = b.organization_name and "+
					   "a.course_id = '"+courseId+"' and a.course_completed <> 'YES' order by a.full_name";*/
			
			String sql= "select distinct user_id,username from users_info left join course_description on users_info.organization_id=course_description.customer_id "
					+ "where privilege='Student' and organization_id='"+session.getAttribute("orgid")+"' order by user_id";
			ps = connection.prepareStatement(sql);
			System.out.println("org id in services;"+session.getAttribute("orgid").toString());
			System.out.println("Selected value of course "+request.getParameter("selectedValue"));
			System.out.println("Selected value hidden :"+request.getParameter("data"));
			// ps.setString(1, (String)session.getAttribute("userid"));
		    // ps.setString(2, request.getParameter("selectedValue"));
			rs = ps.executeQuery();
			List<UsersInfoDomain> users = new  ArrayList<UsersInfoDomain>();
			System.out.println("Query :"+sql);
			while (rs.next()) {
				UsersInfoDomain user = new UsersInfoDomain();
			    user.setUserId(rs.getString(1));
			    user.setUserName(rs.getString(2));
			    users.add(user);
			}
			json = new Gson().toJson(users);
			response.setContentType("text/plain");
			response.getWriter().write(json);
			
			
		connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
