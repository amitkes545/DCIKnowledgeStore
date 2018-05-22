<%-- <%@page import="org.apache.naming.java.javaURLContextFactory"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.*"%>
    <%@ page import="java.sql.*"%>
    <%-- <%@ page errorPage="error.jsp" %> --%>
 
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.*" %>

<%@ page import="com.kds.KODE_DEV.dao.*" %>
 
<%
		String partname = request.getParameter("partname").toString();
	try{
		
		String ColumnsList="";
		FeesModuleDao dao=new FeesModuleDao();
		if(partname.trim().equalsIgnoreCase("StudentList"))
		{
			String OrgID=request.getParameter("OrgID").toString();
			String DeptID = request.getParameter("DeptID").toString();
			ArrayList<HashMap<String,String>> stulist=dao.getStudentListforPaymentByDeptID(DeptID,OrgID);
			if(stulist!=null && stulist.size()>0)
			{
				for(int i=0;i<stulist.size();i++)
				{ 
					HashMap<String,String> info=stulist.get(i);
					String payer_id=info.get("payer_id").toString().trim();
					String payer_name=info.get("payer_name").toString().trim();
					String fullname=payer_name+"~"+payer_id;
					
					// System.out.println(fullname);
					
					if(i==0)
						ColumnsList=fullname;
					else
						ColumnsList=ColumnsList+";"+fullname;
				}
			}	
		}else if(partname.trim().equalsIgnoreCase("PayModeList"))
		{
			String DeptID = request.getParameter("DeptID").toString();
			String ENRID = request.getParameter("ENRID").toString();
			String OrgID=request.getParameter("OrgID").toString();
			ArrayList<HashMap<String,String>> stulist=dao.getPayModeByDeptIDandEnrID(DeptID,ENRID,OrgID);
			//System.out.println(stulist.toString());
			if(stulist!=null && stulist.size()>0)
			{
				for(int i=0;i<stulist.size();i++)
				{
					HashMap<String,String> info=stulist.get(i);
					
					String payment_mode=info.get("payment_mode").toString().trim();
										
					if(i==0)
						ColumnsList=payment_mode;
					else
						ColumnsList=ColumnsList+";"+payment_mode;
				}
			}
		}else if(partname.trim().equalsIgnoreCase("FeesNamesList"))
		{
			String DeptID = request.getParameter("DeptID").toString();
			String ENRID = request.getParameter("ENRID").toString();
			String OrgID=request.getParameter("OrgID").toString();
			ArrayList<HashMap<String,String>> stulist=dao.getFeesListfromPaymentByDeptIDandEnrID(DeptID,ENRID,OrgID);
			// System.out.println(stulist.toString());
			if(stulist!=null && stulist.size()>0)
			{
				for(int i=0;i<stulist.size();i++)
				{
					HashMap<String,String> info=stulist.get(i);
					
					String mlt_course_fee_code=info.get("mlt_course_fee_code").toString().trim();
					String fee_sequence=info.get("fee_sequence").toString().trim();
					String mlt_fee_name=info.get("mlt_fee_name").toString().trim();
					
					String fullname=mlt_course_fee_code+"~"+fee_sequence+"~"+mlt_fee_name;
					
					if(i==0)
						ColumnsList=fullname;
					else
						ColumnsList=ColumnsList+";"+fullname;
				}
			}
		}else if(partname.trim().equalsIgnoreCase("FeePaymentDetails"))
		{
			String DeptID = request.getParameter("DeptID").toString();
			String ENRID = request.getParameter("ENRID").toString();
			String FeesCode = request.getParameter("FeesCode").toString();
			String FeesSeq = request.getParameter("FeesSeq").toString();
			String OrgID=request.getParameter("OrgID").toString();
			HashMap<String,String> info=dao.getFeesPaymentDetails(OrgID,DeptID, ENRID, FeesCode, FeesSeq);
			// System.out.println(info.toString());
			
			if(info!=null && info.size()>0)
			{
				String instrument_number=info.get("instrument_number").toString().trim();
				String instrument_date=info.get("instrument_date").toString().trim();
				String issuer_account_number=info.get("issuer_account_number").toString().trim(); 
				String issuing_bank=info.get("issuing_bank").toString().trim();
				String expiry_month=info.get("expiry_month").toString().trim();
				String expiry_year=info.get("expiry_year").toString().trim();
				String amount_paid=info.get("amount_paid").toString().trim();
				String txn_reference_no=info.get("txn_reference_no").toString().trim();
				String txn_datetime=info.get("txn_datetime").toString().trim();
				String payer_name=info.get("issuer_name").toString().trim();
				
				ColumnsList=instrument_number+"~"+instrument_date+"~"+issuer_account_number+"~"+issuing_bank+"~"
							+expiry_month+"~"+expiry_year+"~"+amount_paid+"~"+txn_reference_no+"~"+txn_datetime+"~"+payer_name;
						
				
			}
		}else if(partname.trim().equalsIgnoreCase("CourseList"))
		{
			String hiddenOrgID=request.getParameter("hiddenOrgID").toString();
			ArrayList<HashMap<String,String>> CourseList=dao.getAllCoursesByOrg(hiddenOrgID);
			ArrayList<HashMap<String,String>> OrgFeesList=dao.getAllFeesIDByOrgID(hiddenOrgID);
			
			String CourseListStr="";
			 if(CourseList!=null && CourseList.size()>0)
             {
             	for(int i=0;i<CourseList.size();i++)
             	{
             		HashMap<String,String> info=CourseList.get(i);
                 	String course_discription_id=info.get("course_discription_id").toString().trim();
                 	String FullName=info.get("FullName").toString().trim();
                 	
                 	String postr=FullName+"~"+course_discription_id; // 
                 	if(i==0)
                 		CourseListStr=postr;
                 	else
                 		CourseListStr=CourseListStr+"@"+postr;
             	}
             }
			 
			 String FeesListStr="";
			 if(OrgFeesList!=null && OrgFeesList.size()>0)
             {
             	for(int i=0;i<OrgFeesList.size();i++)
             	{
             		HashMap<String,String> info=OrgFeesList.get(i);
                 	String mlt_fee_code=info.get("mlt_fee_code").toString().trim();
                 	String FullName=info.get("FullName").toString().trim();
                 	
                 	String postr=FullName+"~"+mlt_fee_code; // 
                 	if(i==0)
                 		FeesListStr=postr;
                 	else
                 		FeesListStr=FeesListStr+"@"+postr;
                 	
                 	
             	}
             }
              
			 ColumnsList=CourseListStr+"#"+FeesListStr;
		}else if(partname.trim().equalsIgnoreCase("CourseListForReceipt"))
		{
			String hiddenOrgID=request.getParameter("hiddenOrgID").toString();
			ArrayList<HashMap<String,String>> CourseList=dao.getAllCoursesByOrg(hiddenOrgID);
			ArrayList<HashMap<String,String>> OrgFeesList=dao.getAllFeesIDByOrgID(hiddenOrgID);
			
			String CourseListStr="";
			 if(CourseList!=null && CourseList.size()>0)
             {
             	for(int i=0;i<CourseList.size();i++)
             	{
             		HashMap<String,String> info=CourseList.get(i);
                 	String course_discription_id=info.get("course_discription_id").toString().trim();
                 	String FullName=info.get("FullName").toString().trim();
                 	
                 	String postr=FullName+"~"+course_discription_id; // 
                 	if(i==0)
                 		CourseListStr=postr;
                 	else
                 		CourseListStr=CourseListStr+"@"+postr;
             	}
             }
			 
			 String FeesListStr="";
			 if(OrgFeesList!=null && OrgFeesList.size()>0)
             {
             	for(int i=0;i<OrgFeesList.size();i++)
             	{
             		HashMap<String,String> info=OrgFeesList.get(i);
                 	String mlt_fee_code=info.get("mlt_fee_code").toString().trim();
                 	String FullName=info.get("FullName").toString().trim();
                 	
                 	String postr=FullName+"~"+mlt_fee_code; // 
                 	if(i==0)
                 		FeesListStr=postr;
                 	else
                 		FeesListStr=FeesListStr+"@"+postr;
                 	
                 	
             	}
             }
              
			 ColumnsList=CourseListStr+"#"+FeesListStr;
		}
		else if(partname.trim().equalsIgnoreCase("CourseListForFeesGroup"))
		{
			String hiddenOrgID=request.getParameter("hiddenOrgID").toString();
			ArrayList<HashMap<String,String>> CourseList=dao.getFeesCoursesByOrg(hiddenOrgID);
			
			
			String CourseListStr="";
			 if(CourseList!=null && CourseList.size()>0)
             {
             	for(int i=0;i<CourseList.size();i++)
             	{
             		HashMap<String,String> info=CourseList.get(i);
                 	String course_discription_id=info.get("course_discription_id").toString().trim();
                 	String FullName=info.get("FullName").toString().trim();
                 	
                 	String postr=FullName+"~"+course_discription_id; // 
                 	if(i==0)
                 		CourseListStr=postr;
                 	else
                 		CourseListStr=CourseListStr+"@"+postr;
             	}
             }
			 
			
              
			 ColumnsList=CourseListStr;
		}else if(partname.trim().equalsIgnoreCase("GroupFeesNamesList"))
		{
			String hiddenOrgID = request.getParameter("hiddenOrgID").toString();
			String hiddenCourseID = request.getParameter("hiddenCourseID").toString();
			
			ArrayList<HashMap<String,String>> GroupFeesList=dao.getAllGroupFeesIDByOrgIDAndByCourseID(hiddenOrgID, hiddenCourseID);
			ArrayList<HashMap<String,String>> MemberFeesList=dao.getAllMemberFeesIDByOrgID(hiddenOrgID,hiddenCourseID);
			if(GroupFeesList!=null && GroupFeesList.size()>0)
			{
				for(int i=0;i<GroupFeesList.size();i++)
				{
					HashMap<String,String> info=GroupFeesList.get(i);
					String mlt_course_fee_code=info.get("mlt_course_fee_code").toString().trim();
					String FullName=info.get("FullName").toString().trim();
					String fullname=mlt_course_fee_code+"~"+FullName;
					
					if(i==0)
						ColumnsList=fullname;
					else
						ColumnsList=ColumnsList+";"+fullname;
				}
			}
			
			 String FeesListStr="";
			 if(MemberFeesList!=null && MemberFeesList.size()>0)
             {
             	for(int i=0;i<MemberFeesList.size();i++)
             	{
             		HashMap<String,String> info=MemberFeesList.get(i);
                 	String mlt_fee_code=info.get("mlt_fee_code").toString().trim();
                 	String FullName=info.get("FullName").toString().trim();
                 	
                 	String postr=FullName+"~"+mlt_fee_code; // 
                 	if(i==0)
                 		FeesListStr=postr;
                 	else
                 		FeesListStr=FeesListStr+"@"+postr;
                 	
                 	
             	}
             }
			 
			 ColumnsList=ColumnsList+"#"+FeesListStr;
			 
		}
		
		out.println(ColumnsList);
		
}
 catch (Exception e) { e.printStackTrace();	}
	  

%>

