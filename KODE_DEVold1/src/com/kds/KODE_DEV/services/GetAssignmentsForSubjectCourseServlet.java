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
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.AssessmentDomain;
import com.kds.KODE_DEV.domain.CourseAdminDomain;
import com.kds.KODE_DEV.domain.SubjectInfoDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

@WebServlet("/getAssessmentsOnCourseForFaculty")
public class GetAssignmentsForSubjectCourseServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		    ArrayList<AssessmentDomain> arrayListForAllAssessment = new ArrayList<AssessmentDomain>();
		    ArrayList<AdminDomain> al= new ArrayList<AdminDomain>();
			HttpSession session = request.getSession();

			System.out.println("-----JACK of Trades Open ----------");
			PreparedStatement ps = null;
			ResultSet rs = null;
			System.out.println("Entered into servlet GetAssignmentsOnCourseSubjectServlet services ");
			String json = null;
			Connection connection=null;
			try {
				connection = DBTransaction.connect();
			//String courseId=request.getParameter("selectedValue");
			//System.out.println("Course ID in services : "+courseId);
			System.out.println("org id in services "+session.getAttribute("orgId").toString());
			System.out.println("user id in services "+session.getAttribute("userid").toString());
			//String sql = "select subject_id, subname from subject_description where organization_id=? and course_id_defined_by_teaching_source=?";
			String orgId=session.getAttribute("orgId").toString();
			String faculyid=session.getAttribute("userid").toString();
			
			String courseId=request.getParameter("courseid");
			String subjectId=request.getParameter("subjectid");
			
			
			String tempCourseId=request.getParameter("courses");
			String tempSubjectId=request.getParameter("subjects");
			
			System.out.println("courseId="+courseId);
			System.out.println("subjectid="+subjectId);
			
			String tempQuery="SELECT AI.assessment_id,assessment_name,ASI.marks,status,remarks,assessment_description,path_of_file,path,user_id,username,AI.uploaded_by "
					+ ", (case when ASI.uploaded_by is not null and path_of_file is not null then 'Yes' else 'No' end) completed "
					+ "from (SELECT distinct AI.assessment_id,assessment_name,assessment_description,path_of_file,user_id,username,AI.uploaded_by "
					+ " FROM assessment_info AI left join student_groups SG on AI.recipient_type=SG.group_name and AI.organization_id = SG.organization_id "
					+ "cross join users_info UI where privilege='Student' and (CASE WHEN AI.recipient_type = 'All' then TRUE when UI.user_id IN (SELECT regexp_split_to_table(student_id,E'\\#+')) then TRUE "
					+ " else UI.user_id IN (SELECT regexp_split_to_table(recipient_type,E'\\#+'))  end) "
					+ "/* AND AI.assessment_id like 'All'*/ and UI.organization_id='"+session.getAttribute("orgId")+"' and AI.uploaded_by='"+session.getAttribute("userId")+"' )"
					+ " AI LEFT JOIN assessment_student_info ASI ON AI.assessment_id = ASI.assessment_id and AI.user_id = ASI.uploaded_by ";
		

			//String sql= "select assessment_id,assessment_name from assessment_info where uploaded_by='"+faculyid+"'"+"and organization_id='"+orgId+"'";
	
			
			String sql="select assessment_id, assessment_name from assessment_info where organization_id = '"+session.getAttribute("orgId")+"' and uploaded_by = ? and "+
				"course_id  = ? and "+
				"subject_id = ? ";
			
						
			ps = connection.prepareStatement(sql);
			
			System.out.println("faculyid="+faculyid);
			//faculyid="JKFjk2313";
			 ps.setString(1, faculyid);
		     ps.setString(2, courseId);
		     ps.setString(3, subjectId);
		     System.out.println("TEMPORARY : "+ps);

			rs = ps.executeQuery();
			while(rs.next())
			{
				AdminDomain cDomain= new AdminDomain();
				cDomain.setAssignment_ID(rs.getString("assessment_id"));
				cDomain.setAssignment_name(rs.getString("assessment_name"));
				al.add(cDomain);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   
				   ps.close();
				   connection.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
			json = new Gson().toJson(al);
			response.setContentType("text/plain");
			response.getWriter().write(json);
		
	}
}
