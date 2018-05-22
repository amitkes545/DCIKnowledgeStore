package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileUploadException;

import com.kds.KODE_DEV.dao.GetCourseForFacultyDao;
import com.kds.KODE_DEV.dao.PresentAttendanceDetailsDAO;
import com.kds.KODE_DEV.domain.CourseFacultyDomain;
import com.kds.KODE_DEV.domain.GetSessionForFacultyDomain;

public class PresentAttendanceDetailsService extends CommonService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void run() throws ServletException, IOException, FileUploadException, Exception {
		try {
			String tableSize = "0";
			session = request.getSession();
			session.getAttribute("orgId");

			String enr = null;
			String name = null;
			String stuclass = null;
			String stuatten = null;
			String sessionId = null;
			int save=0;
			String se = (String) session.getAttribute("se");
			//System.out.println("Presentstu8888==>" + se);

			if (request.getParameter("tableSize") != null)
				tableSize = (String) request.getParameter("tableSize");
			PresentAttendanceDetailsDAO presentattendence = new PresentAttendanceDetailsDAO();
			GetSessionForFacultyDomain getsessionforfaculty = new GetSessionForFacultyDomain();

			;
			int size = Integer.parseInt(tableSize);
			if (size > 0) {
				for (int i = 1; i < size; i++) {
					enr = "enrId" + i;
					name = "name" + i;
					stuclass = "class" + i;
					stuatten = "checkboxGroup" + i;

					enr = request.getParameter(enr);
					name = request.getParameter(name);
					stuclass = request.getParameter(stuclass);
					stuatten = request.getParameter(stuatten);
					sessionId = request.getParameter("sessionId");
					//System.out.println("sessionId =>" + sessionId);
					if (stuatten != null) {
						String orgId = (String) session.getAttribute("orgId");
						String userId = (String) session.getAttribute("userid");
					    save =presentattendence.savePresentStudentData((String) session.getAttribute("orgId"),
						(String) session.getAttribute("userid"), se, enr);
						getsessionforfaculty.setEnid(enr);
						getsessionforfaculty.setStudentName(name);
						getsessionforfaculty.setClass1(stuclass);
						getsessionforfaculty.setSessionId(getsessionforfaculty.getSessionId());
						System.out.println("enrId==>" + getsessionforfaculty.getEnid());
						
						
					}

				}
			}
			
		
			String msg="";
			
			if(save!=0) {
				 msg="Attendance Details Saved Successfully";
				 request.setAttribute("FacultySuccess",msg);
				//response.sendRedirect("../JSP/AttendanceMarking.jsp");
				 
				 RequestDispatcher rd=request.getRequestDispatcher("../JSP/AttendanceMarking.jsp");
		       		rd.forward(request, response);

					
		}
			else
			{
				 msg="Failed to update attendance";
				 request.setAttribute("FacultyFailure",msg);
				 RequestDispatcher rd=request.getRequestDispatcher("../JSP/AttendanceMarking.jsp");
		       		rd.forward(request, response);

					
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
