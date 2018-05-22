package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.kds.KODE_DEV.domain.SessionDomain;
import com.kds.KODE_DEV.domain.SubjectInfoDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

@WebServlet("/getSessionsForCourse")
public class GetSessionsForCourseServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			ArrayList<SessionDomain> sessionDetails=new ArrayList<SessionDomain>();
			Date date1=null;
			// SessionDomain sessionDomain = new SessionDomain();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			Calendar cal = Calendar.getInstance();
			//System.out.println(dateFormat.format(cal.getTime())); //2014/08/06 16:00:22
			String date=dateFormat.format(cal.getTime());
			
			try {
				 date1 = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(date);
			
			} catch (ParseException e1) {
			
				e1.printStackTrace();
			}
			String[] DateAndTime1=date.split("");
			String fromDbDate1=DateAndTime1[0];
			String fromDbTime1=DateAndTime1[1];
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			System.out.println("Entered into servlet For Session For Course services");
			String json = null;
			Connection connection = DBTransaction.connect();
			String courseId=request.getParameter("selectedValue");
			System.out.println("Course Id : "+courseId);
			//String sql = "select subject_id, subname from subject_description where organization_id=? and course_id_defined_by_teaching_source=?";
			 String sql = "select * from session_details sd left join student_groups sg on sd.faculty_name=sg.created_by and "
						+ "sd.organization_id=sg.organization_id and sd.recipient_type=sg.student_id left join course_description cd on sd.category=cd.course_id_defined_by_teaching_source and sd.organization_id=cd.customer_id "
						+ " where sd.organization_id='"+session.getAttribute("orgid")+"'"+"and faculty_name='"+session.getAttribute("userid")+"'";
			
			ps = connection.prepareStatement(sql);
			System.out.println("org id in services;"+session.getAttribute("orgid").toString());
			System.out.println("Selected value of course "+request.getParameter("selectedValue"));
			System.out.println("Selected value hidden :"+request.getParameter("data"));
			// ps.setString(1, (String)session.getAttribute("userid"));
		    // ps.setString(2, request.getParameter("selectedValue"));
			rs = ps.executeQuery();
			while(rs.next()) {	
				String datefromdb=rs.getString("session_start_time");
				/*String[] DateAndTime=date1.split("");
				String fromDbDate=DateAndTime[0];
				String fromDbTime=DateAndTime[1];
				//System.out.println("from database:"+fromDbDate);
				//System.out.println("fromDbTime:"+fromDbTime);*/
				Date date12 = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(datefromdb);
				//System.out.println("date12 from db:"+date12);
				//System.out.println("date1 from db:"+date1);
				if(date12.before(date1)){
					SessionDomain sessionDomain=new SessionDomain();
					////System.out.println(resultSet.getString("session_name"));
					sessionDomain.setSessionId(rs.getString("session_id"));
					sessionDomain.setSessionName(rs.getString("session_name"));
					sessionDomain.setCategory(rs.getString("category"));
					sessionDomain.setCourseName(rs.getString("course_name"));
					sessionDomain.setDuration(rs.getString("duration"));
					sessionDomain.setCostOfSession(rs.getString("cost_of_session"));
					//sessionDomain.setRecipientType(resultSet.getString("recipient_type"));
				
					String newrtype="",rtype_split[]=null;
					String rtype=rs.getString("recipient_type");
										System.out.println("rtype="+rtype);
										if(rtype!=null){
										if(rtype.contains("_group"))
											{
											rtype_split=rtype.split("_group");
											newrtype=rtype_split[0];
											}
										else{
											newrtype=rtype;
										}}
					sessionDomain.setRecipientType(newrtype);
					
					sessionDomain.setGroupName(rs.getString("group_name"));
					sessionDomain.setSessionStartTime(rs.getString("session_start_time"));
					sessionDomain.setSessionEndTime(rs.getString("session_end_time"));
					sessionDomain.setStatus(rs.getString("session_status"));
					sessionDomain.setGroupName(rs.getString("group_name"));
					sessionDetails.add(sessionDomain);
					////System.out.println("db date is before current date:");
				}else if(date12.after(date1)){
					/*SessionDomain sessionDomain=new SessionDomain();
					////System.out.println(resultSet.getString("session_name"));
					sessionDomain.setSessionId(resultSet.getString("session_id"));
					sessionDomain.setSessionName(resultSet.getString("session_name"));
					sessionDomain.setCategory(resultSet.getString("category"));
					sessionDomain.setDuration(resultSet.getString("duration"));
					sessionDomain.setCostOfSession(resultSet.getString("cost_of_session"));
					sessionDomain.setRecipientType(resultSet.getString("recipient_type"));
					sessionDomain.setSessionStartTime(resultSet.getString("session_start_time"));
					sessionDomain.setSessionEndTime(resultSet.getString("session_end_time"));
					sessionDomain.setStatus(resultSet.getString("session_status"));
					sessionDetails.add(sessionDomain);*/	
					//System.out.println("db date is after current date:");
			}
				
			}
		
			json = new Gson().toJson(sessionDetails);
			response.setContentType("text/plain");
			response.getWriter().write(json);
			
			
		connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
