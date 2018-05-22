package com.kds.KODE_DEV.services;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;


import com.kds.KODE_DEV.dao.MISReportsDao;

import android.graphics.Paint.Align;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;


@SuppressWarnings("serial")
public class MISReportListService extends CommonService{
	
	static final Logger LOGGER = Logger.getLogger(MISReportListService.class);

	public void run() throws ServletException, IOException {
		System.out.println("MISReportListService class for pdf report generate");
		session = request.getSession();
		
		String RepID=request.getParameter("RepID").toString().trim();
		if(RepID.trim().equalsIgnoreCase("MIS_UA_001"))
		{
			runDailyUserLoginDetailsReport();
		}else if(RepID.trim().equalsIgnoreCase("MIS_UA_002"))
		{
			runHourlyUserLoginDetailsReport();
		}else if(RepID.trim().equalsIgnoreCase("MIS_UA_003"))
		{
			runUsersClockTimeDetailsReport();
		}
		else if(RepID.trim().equalsIgnoreCase("MIS_UA_010"))
		{
			runActiveClassroomsDetailsReport();
		}
		else if(RepID.trim().equalsIgnoreCase("MIS_UA_011"))
		{
			runDisconnectedClassroomsDetailsReport();
		}
		else if(RepID.trim().equalsIgnoreCase("MIS_AD_001"))
		{
			runRegistrationDetailsReport("Pending");
		}
		else if(RepID.trim().equalsIgnoreCase("MIS_AD_002"))
		{
			runRegistrationDetailsReport("Completed");
		}
		else if(RepID.trim().equalsIgnoreCase("MIS_AD_004"))
		{
			runStuAplyCrsDetailsReport();
		}
		else if(RepID.trim().equalsIgnoreCase("MIS_AC_001"))
		{
			runStudentAttendingClassesDetailsReport();
		}
		else if(RepID.trim().equalsIgnoreCase("MIS_AC_003"))
		{
			runFacultyPresentingClassesDetailsReport();
		} 
		else if(RepID.trim().equalsIgnoreCase("MIS_AC_008"))
		{
			runStudentsAssessmentsResultReport();
		}
		else if(RepID.trim().equalsIgnoreCase("MIS_AC_011"))
		{
			runStudentsAssignmentsResultReport();
		}
		else if (RepID.trim().equalsIgnoreCase("MIS_AD_013"))
		{
			runCertificateReportbyStatus("Pending");
		}
		else if (RepID.trim().equalsIgnoreCase("MIS_AD_014"))
		{
			runCertificateReportbyStatus("Issued");
		}
		else if(RepID.trim().equalsIgnoreCase("MIS_AC_009"))
		{
			runStudentsExamEligibleByStatus("Eligible");
		}
		else if(RepID.trim().equalsIgnoreCase("MIS_AC_010"))
		{
			runStudentsExamEligibleByStatus("Not-Eligible");
		}
		else if(RepID.trim().equalsIgnoreCase("MIS_AN_001"))
		{
			runNoOfEnrollments();
		}
		else if(RepID.trim().equalsIgnoreCase("MIS_FI_001"))
		{
			runStudentFeesPaidReport();
		}
  }
	

		
	void runDailyUserLoginDetailsReport()
	{
		try
		{
			
			System.out.println("PDF Service");
			String startDate=request.getParameter("startDate").toString().trim();
			String endDate=request.getParameter("endDate").toString().trim();
			String Role=request.getParameter("Role").toString().trim();
			String SearchVal=request.getParameter("SearchVal").toString().trim();
			String ExpRepFormat=request.getParameter("ExpRepFormat").toString().trim();
			
			String userid=session.getAttribute("userId").toString().trim();
			String orgId=session.getAttribute("orgId").toString().trim();
			String username=session.getAttribute("userName").toString().trim();
			
			
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("Role", Role);
			request.setAttribute("SearchVal", SearchVal);
			request.setAttribute("ExpRepFormat", ExpRepFormat);
			request.setAttribute("ReportPath", "None");
			
			//println("orgId:"+orgId+"	startDate:"+startDate+"	endDate:"+endDate +"	Role:"+Role+"	ExpRepFormat:"+ExpRepFormat);
			
			MISReportsDao MISdao=new MISReportsDao();
			
			ArrayList<HashMap<String,String>> tabledata=new ArrayList<HashMap<String,String>>();
			
			tabledata=MISdao.getDailyLoginDetailsByDateByrole(userid, orgId, startDate, endDate, Role,SearchVal);
			request.setAttribute("tabledata", tabledata);
			if(ExpRepFormat.trim().equalsIgnoreCase("PDF") && tabledata.size()>0) // validate format and with result size 
			{
				String reportPath = getReportPathbyOption("6_Columns"); // report path
				
				HashMap<String,Object> reportParameters = new HashMap<String,Object>();
			      
				
				/*URL url = new URL(getOrganisationParticularsbyOrgID(orgId,"Logo"));
			    Image  image = ImageIO.read(url);
			    javax.swing.ImageIcon ii = new javax.swing.ImageIcon(image);*/
			    reportParameters.put("ReportLogo", null );
			    reportParameters.put("UserName", username);
			    reportParameters.put("Organisation_Name", getOrganisationParticularsbyOrgID(orgId,"Name"));
			    reportParameters.put("FullAddress", getOrganisationAddress(orgId));
			    reportParameters.put("Report_Title", "Details of User Login Details");
			    String FilterDt="From : "+startDate + " - To: "+endDate;
			    reportParameters.put("Filter_Details", FilterDt);
			      
			    reportParameters.put("Column_Name_1", "Date");
			    reportParameters.put("Column_Name_2", "Privilege");
			    reportParameters.put("Column_Name_3", "User ID");
			    reportParameters.put("Column_Name_4", "User Name");
			    reportParameters.put("Column_Name_5", "Time");
			    reportParameters.put("Column_Name_6", "Log Status");
			      
			    if(tabledata!=null && tabledata.size()>0)
			      {
			    	  Collection<Map<String, ?>> ShowsData = new ArrayList<Map<String, ?>>();
			    	  
			    	  for(int i=0;i<tabledata.size();i++)
			    	  {
			    		  HashMap<String,String> info=tabledata.get(i);
			    		
			    		 // println("disciplineName from DB :: "+info.toString());
			    		  
			    		  Map<String,String> data=new HashMap<String,String>();
			    		  
			    		  data.put("Row_Data_1", info.get("Date").toString().trim());
			    		  data.put("Row_Data_2", info.get("Privilege").toString().trim());
			    		  data.put("Row_Data_3", info.get("UserId").toString().trim());
			    		  data.put("Row_Data_4", info.get("UserName").toString().trim());
			    		  data.put("Row_Data_5", info.get("Time").toString().trim());
			    		  data.put("Row_Data_6", info.get("LogStatus").toString().trim());
			    		  ShowsData.add(data);  
			    	  }
			    	  
			    	  JRDataSource reportSource = new JRMapCollectionDataSource(ShowsData);
			          
			          JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,  reportParameters,
			        		  reportSource);
			          
			          JasperExportManager.exportReportToPdfFile(jasperPrint, getOutputPath("UserLoginDetails.pdf"));
			          			          
			          openFileByDefault(getOutputPath("UserLoginDetails.pdf"));
			      }
			      
			} 
			String OrgName=getOrganisationParticularsbyOrgID(orgId, "Name");
			String ReptName="Details of User Login Details";
			String[] Headers={" Date "," Privilege "," User ID "," User Name "," Time " ," Log Status "};
			String[] dataCols={"Date","Privilege","UserId","UserName","Time" ,"LogStatus"};
			
			session.setAttribute("OrgName", OrgName);
			session.setAttribute("ReptName", ReptName);
			session.setAttribute("Headers", Headers);
			session.setAttribute("dataCols", dataCols);
			session.setAttribute("tabledata", tabledata);
			session.setAttribute("FileName", "UserLoginDetails");
			
			session.setAttribute("UserName", username);
			session.setAttribute("Organisation_Name", OrgName);
			session.setAttribute("FullAddress", getOrganisationAddress(orgId));
			session.setAttribute("Report_Title", "Details of User Login Details");
			String FilterDt="From : "+startDate + " - To: "+endDate;
			session.setAttribute("Filter_Details", FilterDt);
			session.setAttribute("No_Of_Column", "6");
			
			/*if(ExpRepFormat.trim().equalsIgnoreCase("PDF") && tabledata.size()>0) // validate format and with result size 
			{
				String reportPath = getReportPathbyOption("6_Columns"); // report path
				session.setAttribute("reportPath", reportPath);
			}*/
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/DailyUserLoginDetailsReport.jsp");
			
			dispatcher.forward(request, response);
			
		}catch(Exception ex){ex.printStackTrace();}
		
	}
	
	void runHourlyUserLoginDetailsReport()
	{
		try
		{
			String startDate=request.getParameter("startDate").toString().trim();
			String endDate=request.getParameter("endDate").toString().trim();
			String Role=request.getParameter("Role").toString().trim();
			String ExpRepFormat=request.getParameter("ExpRepFormat").toString().trim();
			
			String userid=session.getAttribute("userId").toString().trim();
			String orgId=session.getAttribute("orgId").toString().trim();
			String username=session.getAttribute("userName").toString().trim();
			
			
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("Role", Role);
			request.setAttribute("ExpRepFormat", ExpRepFormat);
			
			//println("orgId:"+orgId+"	startDate:"+startDate+"	endDate:"+endDate +"	Role:"+Role+"	ExpRepFormat:"+ExpRepFormat);
			
			MISReportsDao MISdao=new MISReportsDao();
			
			ArrayList<HashMap<String,String>> tabledata=new ArrayList<HashMap<String,String>>();
			
			tabledata=MISdao.getHourlyCountLoginDetailsByDateByrole(userid, orgId, startDate, endDate, Role);
			request.setAttribute("tabledata", tabledata);
			if(ExpRepFormat.trim().equalsIgnoreCase("PDF") && tabledata.size()>0) // validate format and with result size 
			{
				String reportPath = getReportPathbyOption("4_Columns") ; // report path
				
				HashMap<String,Object> reportParameters = new HashMap<String,Object>();
			    String Logo=  getOrganisationParticularsbyOrgID(orgId,"Logo");
			    if(Logo.length()==0)
			    	Logo=null;
			    /*URL url = new URL(getOrganisationParticularsbyOrgID(orgId,"Logo"));
			    Image  image = ImageIO.read(url);
			    javax.swing.ImageIcon ii = new javax.swing.ImageIcon(image);*/
			    reportParameters.put("ReportLogo", null );
			    reportParameters.put("UserName", username);
			    reportParameters.put("Organisation_Name", getOrganisationParticularsbyOrgID(orgId,"Name"));
			    reportParameters.put("FullAddress", getOrganisationAddress(orgId));
			    reportParameters.put("Report_Title", "Details of Hourly Users Logged-in");
			    String FilterDt="From : "+startDate + " - To: "+endDate;
			    reportParameters.put("Filter_Details", FilterDt);
			      
			    reportParameters.put("Column_Name_1", "Date");
			    reportParameters.put("Column_Name_2", "Privilege");
			    reportParameters.put("Column_Name_3", "Hour Range");
			    reportParameters.put("Column_Name_4", "No.Of. Users");
			      
			      
			      if(tabledata!=null && tabledata.size()>0)
			      {
			    	  Collection<Map<String, ?>> ShowsData = new ArrayList<Map<String, ?>>();
			    	  
			    	  for(int i=0;i<tabledata.size();i++)
			    	  {
			    		  HashMap<String,String> info=tabledata.get(i);
			    		
			    		 // println("disciplineName from DB :: "+info.toString());
			    		  
			    		  Map<String,String> data=new HashMap<String,String>();
			    		  
			    		  data.put("Row_Data_1", info.get("Date").toString().trim());
			    		  data.put("Row_Data_2", info.get("Privilege").toString().trim());
			    		  data.put("Row_Data_3", info.get("Hour").toString().trim());
			    		  data.put("Row_Data_4", info.get("LoginCount").toString().trim());
			    		  
			    		  ShowsData.add(data);  
			    	  }
			    	  
			    	  JRDataSource reportSource = new JRMapCollectionDataSource(ShowsData);
			          
			          JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,  reportParameters,
			        		  reportSource);
			          
			          JasperExportManager.exportReportToPdfFile(jasperPrint, getOutputPath("UserHoursLoginDetails.pdf"));
			          
			          openFileByDefault(getOutputPath("UserHoursLoginDetails.pdf"));
			          
			         
			          
			      }
			      
			}
			
			String OrgName=getOrganisationParticularsbyOrgID(orgId, "Name");
			String ReptName="Details of Hourly Users Logged-in";
			String[] Headers={" Date "," Privilege "," Hour Range "," No.Of. Users "};
			String[] dataCols={"Date","Privilege","Hour","LoginCount"};
			
			session.setAttribute("OrgName", OrgName);
			session.setAttribute("ReptName", ReptName);
			session.setAttribute("Headers", Headers);
			session.setAttribute("dataCols", dataCols);
			session.setAttribute("tabledata", tabledata);
			session.setAttribute("FileName", "UserHoursLoginDetails");
			
			/*if(ExpRepFormat.trim().equalsIgnoreCase("Excel") && tabledata.size()>0)
			{
				String OrgName=getOrganisationParticularsbyOrgID(orgId, "Name");
				String ReptName="Details of Hourly Users Logged-in";
				String[] Headers={" Date "," Privilege "," Hour Range "," No.Of. Users "};
				String[] dataCols={"Date","Privilege","Hour","LoginCount"};
				
				GenerateExcelReport(OrgName, ReptName, Headers, tabledata, dataCols,"UserHoursLoginDetails");
			}*/
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/HourlyUserLoginDetailsReport.jsp");
			
			dispatcher.forward(request, response);
		}catch(Exception ex){ex.printStackTrace();}
		
	}
	
	void runUsersClockTimeDetailsReport()
	{
		try
		{
			String startDate=request.getParameter("startDate").toString().trim();
			String endDate=request.getParameter("endDate").toString().trim();
			String Role=request.getParameter("Role").toString().trim();
			String SearchVal=request.getParameter("SearchVal").toString().trim();
			String ExpRepFormat=request.getParameter("ExpRepFormat").toString().trim();
			
			String userid=session.getAttribute("userId").toString().trim();
			String orgId=session.getAttribute("orgId").toString().trim();
			String username=session.getAttribute("userName").toString().trim();
			
			
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("Role", Role);
			request.setAttribute("SearchVal", SearchVal);
			request.setAttribute("ExpRepFormat", ExpRepFormat);
			
			//println("orgId:"+orgId+"	startDate:"+startDate+"	endDate:"+endDate +"	Role:"+Role+"	ExpRepFormat:"+ExpRepFormat);
			
			MISReportsDao MISdao=new MISReportsDao();
			
			ArrayList<HashMap<String,String>> tabledata1=new ArrayList<HashMap<String,String>>();
			
			ArrayList<HashMap<String,String>> tabledata=new ArrayList<HashMap<String,String>>();
			
			tabledata=MISdao.getUsersClockTimeDetailsByDateByrole(userid, orgId, startDate, endDate, Role,SearchVal);// all records
			
			Hashtable<String,HashMap<String,String>> htResultSet=new Hashtable<String,HashMap<String,String>>(); // collect by ID_Date key
			
			if(tabledata!=null &&  tabledata.size()>0)
			{
				for(int i=0;i<tabledata.size();i++)
				{
					HashMap<String,String> infoRow1=tabledata.get(i);
					
					int cnt=i+1;
					if(cnt==tabledata.size())
						break;
					
					HashMap<String,String> infoRow2=tabledata.get(i+1);
					
					String Row1User=infoRow1.get("UserID").toString().trim();
					String Row2User=infoRow2.get("UserID").toString().trim();
					
					String Row1Date=infoRow1.get("LoginDate").toString().trim();
					String Row2Date=infoRow2.get("LoginDate").toString().trim();
					
					String Row1LoginDateTime=infoRow1.get("LoginDateTime").toString().trim();
					String Row2LoginDateTime=infoRow2.get("LoginDateTime").toString().trim();
					
					String Row1Log_Status=infoRow1.get("log_status").toString().trim();
					String Row2Log_Status=infoRow2.get("log_status").toString().trim();
					
					if(Row1User.trim().equalsIgnoreCase(Row2User) && Row1Date.trim().equalsIgnoreCase(Row2Date)) //match ID and Date
						if(Row1Log_Status.equalsIgnoreCase("in") && Row2Log_Status.equalsIgnoreCase("out")) // Check status in/out properly
						{
							String PKey=Row1User+"_"+Row1Date;
							if(htResultSet.containsKey(PKey))
							{
								infoRow1=htResultSet.get(PKey);
								String ExtDuration=infoRow1.get("Duration").toString().trim();
								String duration=getDiffTimeBtwTwoDates(Row1LoginDateTime, Row2LoginDateTime); // calculate in seconds
								duration=SumofTwoSeconds(ExtDuration,duration); // sum of two seconds 
								infoRow1.put("Duration", duration); // overwrite with existing
								htResultSet.put(PKey, infoRow1);
							}else
							{
								String duration=getDiffTimeBtwTwoDates(Row1LoginDateTime, Row2LoginDateTime);// calculate in seconds
								infoRow1.put("Duration", duration); // added newly 
								htResultSet.put(PKey, infoRow1);
							}
						}
				}
				
				for(int i=0;i<tabledata.size();i++)
				{
					HashMap<String,String> infoRow1=tabledata.get(i);
					String User=infoRow1.get("UserID").toString().trim();
					String Date=infoRow1.get("LoginDate").toString().trim();
					String PKey=User+"_"+Date;
					if(htResultSet.containsKey(PKey))
					{
						HashMap<String,String> info=htResultSet.get(PKey);
						
						if(!info.containsKey("Status"))
							info.put("Status", "");
						
						String Status=info.get("Status").toString().trim();
						if(Status.trim().equalsIgnoreCase(""))
						{
							info.put("Status", "Added");
							String Duration=info.get("Duration").toString().trim();
							
							//println("before PKey :"+PKey +"	Duration:"+Duration);
							
							Duration=getDurationString(Integer.parseInt(Duration));
							info.put("Duration", Duration);
							
							//println("after PKey :"+PKey +"	Duration:"+Duration);
							tabledata1.add(info);	
						}
						
					}
					
				}
			}
			
			
			request.setAttribute("tabledata", tabledata1);
			
			if(ExpRepFormat.trim().equalsIgnoreCase("PDF") && tabledata1.size()>0) // validate format and with result size 
			{
				String reportPath = getReportPathbyOption("5_Columns");	 // report path
				
				HashMap<String,Object> reportParameters = new HashMap<String,Object>();
			      
				/*URL url = new URL(getOrganisationParticularsbyOrgID(orgId,"Logo"));
			    Image  image = ImageIO.read(url);
			    javax.swing.ImageIcon ii = new javax.swing.ImageIcon(image);*/
			    reportParameters.put("ReportLogo", null );
			    reportParameters.put("UserName", username);
			    reportParameters.put("Organisation_Name", getOrganisationParticularsbyOrgID(orgId,"Name"));
			    reportParameters.put("FullAddress", getOrganisationAddress(orgId));
			    reportParameters.put("Report_Title", "Details of Daily Usage by Users");
			    String FilterDt="From : "+startDate + " - To: "+endDate;
			    reportParameters.put("Filter_Details", FilterDt);
			      
			    reportParameters.put("Column_Name_1", "Date");
			    reportParameters.put("Column_Name_2", "Privilege");
			    reportParameters.put("Column_Name_3", "User ID");
			    reportParameters.put("Column_Name_4", "User Name");
			    reportParameters.put("Column_Name_5", "Hours");
			      
			      if(tabledata1!=null && tabledata1.size()>0)
			      {
			    	  Collection<Map<String, ?>> ShowsData = new ArrayList<Map<String, ?>>();
			    	  
			    	  for(int i=0;i<tabledata1.size();i++)
			    	  {
			    		  HashMap<String,String> info=tabledata1.get(i);
			    		
			    		 // println("disciplineName from DB :: "+info.toString());
			    		  
			    		  Map<String,String> data=new HashMap<String,String>();
			    		  
			    		  data.put("Row_Data_1", info.get("LoginDate").toString().trim());
			    		  data.put("Row_Data_2", info.get("Privilege").toString().trim());
			    		  data.put("Row_Data_3", info.get("UserID").toString().trim());
			    		  data.put("Row_Data_4", info.get("UserName").toString().trim());
			    		  data.put("Row_Data_5", info.get("Duration").toString().trim());
			    		  ShowsData.add(data);  
			    	  }
			    	  
			    	  JRDataSource reportSource = new JRMapCollectionDataSource(ShowsData);
			          
			          JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,  reportParameters,
			        		  reportSource);
			          
			          JasperExportManager.exportReportToPdfFile(jasperPrint, getOutputPath("ClockTimeDetails.pdf"));
			          
			          openFileByDefault(getOutputPath("ClockTimeDetails.pdf"));
			          
			         
			          
			      }
			      
			}
			
			String OrgName=getOrganisationParticularsbyOrgID(orgId, "Name");
			String ReptName="Details of Daily Usage by Users";
			String[] Headers={" Date "," Privilege ","User ID","User Name"," Hours "};
			String[] dataCols={"LoginDate","Privilege","UserID","UserName","Duration"};
			
			session.setAttribute("OrgName", OrgName);
			session.setAttribute("ReptName", ReptName);
			session.setAttribute("Headers", Headers);
			session.setAttribute("dataCols", dataCols);
			session.setAttribute("tabledata", tabledata1);
			session.setAttribute("FileName", "ClockTimeDetails");
			
			/*if(ExpRepFormat.trim().equalsIgnoreCase("Excel") && tabledata1.size()>0)
			{
				String OrgName=getOrganisationParticularsbyOrgID(orgId, "Name");
				String ReptName="Details of Daily Usage by Users";
				String[] Headers={" Date "," Privilege ","User ID","User Name"," Hours "};
				String[] dataCols={"LoginDate","Privilege","UserID","UserName","Duration"};
				
				GenerateExcelReport(OrgName, ReptName, Headers, tabledata1, dataCols,"ClockTimeDetails");
			}*/
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/UsersClockTimeDetailsUDReport.jsp");
			dispatcher.forward(request, response);
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	//MIS_UA_010
	void runActiveClassroomsDetailsReport()
	{
		try
		{
			String startDate=request.getParameter("startDate").toString().trim();
			String endDate=request.getParameter("endDate").toString().trim();
			String ExpRepFormat=request.getParameter("ExpRepFormat").toString().trim();
			
			String sessionId=request.getParameter("sessionId").toString().trim();
			
			String userid=session.getAttribute("userId").toString().trim();
			String orgId=session.getAttribute("orgId").toString().trim();
			String username=session.getAttribute("userName").toString().trim();
			
			
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("sessionId", sessionId);
			request.setAttribute("ExpRepFormat", ExpRepFormat);
			
			//println("orgId:"+orgId+"	startDate:"+startDate+"	endDate:"+endDate +"	Role:"+Role+"	ExpRepFormat:"+ExpRepFormat);
			
			MISReportsDao MISdao=new MISReportsDao();
			
			ArrayList<HashMap<String,String>> tabledata=new ArrayList<HashMap<String,String>>();
			ArrayList<HashMap<String,String>> tabledata1=new ArrayList<HashMap<String,String>>();
			
			HashMap<String,String> SessionsList=MISdao.getAllSessionsByOrgID(orgId);
			tabledata=MISdao.getActiveClassroomsDetailsByDate(userid, orgId, startDate, endDate,sessionId);
			//count students
			
			HashMap<String,String> htcnt=new HashMap<String,String>(); 
			if(tabledata!=null && tabledata.size()>0)
			{
				for(int i=0;i<tabledata.size();i++)
				{
					HashMap<String,String> info=tabledata.get(i);
					String Date=info.get("Date").toString().trim();
					String SessionID=info.get("SessionID").toString().trim();
					String UserID=info.get("student_id").toString().trim();
					
					String Key=Date+"_"+SessionID; // 2017-05-11_SES70
					String UserKey=Key+"_"+UserID; //2017-05-11_SES70_student1@srm
					if(htcnt.containsKey(UserKey))
					{ 
						
					}else
					{
						if(htcnt.containsKey(Key))
						{
							String UserCnt=htcnt.get(Key).toString().trim();
							int cnt=Integer.parseInt(UserCnt);
							cnt++;
							htcnt.put(Key, ""+cnt);
						}else
						{
							htcnt.put(Key, "1");
						}
						htcnt.put(UserKey, "added");
					}
				}
				for(int i=0;i<tabledata.size();i++)
				{
					HashMap<String,String> info=tabledata.get(i);
					String Date=info.get("Date").toString().trim();
					String SessionID=info.get("SessionID").toString().trim();
					String UserID=info.get("student_id").toString().trim();
					
					if(SessionsList.containsKey(SessionID))
						info.put("SessionName", SessionsList.get(SessionID).toString().trim());
					
					String Key=Date+"_"+SessionID; // 2017-05-11_SES70
				
					if(!htcnt.containsKey(Key+"_status"))
						htcnt.put(Key+"_status", "");
					
					if(htcnt.containsKey(Key))
					{
						if(htcnt.get(Key+"_status").toString().trim().length()==0)
						{
							String NoOfStu=htcnt.get(Key).toString().trim();
							info.put("NoOfStudents", NoOfStu);
							
							tabledata1.add(info);
							htcnt.put(Key+"_status", "added");	
						}
						
					}
				}
				
			}
			
			//println("org::"+tabledata.size() +"	filtered::"+tabledata1.size());
			request.setAttribute("tabledata", tabledata1);
			
			if(ExpRepFormat.trim().equalsIgnoreCase("PDF") && tabledata1.size()>0) // validate format and with result size 
			{
				String reportPath = getReportPathbyOption("4_Columns"); // report path
				
				HashMap<String,Object> reportParameters = new HashMap<String,Object>();
			      
				/*URL url = new URL(getOrganisationParticularsbyOrgID(orgId,"Logo"));
			    Image  image = ImageIO.read(url);
			    javax.swing.ImageIcon ii = new javax.swing.ImageIcon(image);*/
			    reportParameters.put("ReportLogo", null );
			    reportParameters.put("UserName", username);
			    reportParameters.put("Organisation_Name", getOrganisationParticularsbyOrgID(orgId,"Name"));
			    reportParameters.put("FullAddress", getOrganisationAddress(orgId));
			    reportParameters.put("Report_Title", "Details of Classroom Strength");
			    String FilterDt="From : "+startDate + " - To: "+endDate;
			    reportParameters.put("Filter_Details", FilterDt);
			      
			    reportParameters.put("Column_Name_1", "Date");
			    reportParameters.put("Column_Name_2", "Session ID");
			    reportParameters.put("Column_Name_3", "Session Name");
			    reportParameters.put("Column_Name_4", "No.Of.Students");
			      
			      
			      if(tabledata1!=null && tabledata1.size()>0)
			      {
			    	  Collection<Map<String, ?>> ShowsData = new ArrayList<Map<String, ?>>();
			    	  
			    	  for(int i=0;i<tabledata1.size();i++)
			    	  {
			    		  HashMap<String,String> info=tabledata1.get(i);
			    		
			    		 // println("disciplineName from DB :: "+info.toString());
			    		  
			    		  Map<String,String> data=new HashMap<String,String>();
			    		  
			    		  data.put("Row_Data_1", info.get("Date").toString().trim());
			    		  data.put("Row_Data_2", info.get("SessionID").toString().trim());
			    		  data.put("Row_Data_3", info.get("SessionName").toString().trim());
			    		  data.put("Row_Data_4", info.get("NoOfStudents").toString().trim());
			    		  
			    		  ShowsData.add(data);  
			    	  }
			    	  
			    	  JRDataSource reportSource = new JRMapCollectionDataSource(ShowsData);
			          
			          JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,  reportParameters,
			        		  reportSource);
			          
			          JasperExportManager.exportReportToPdfFile(jasperPrint, getOutputPath("ActiveClsDetails.pdf"));
			          
			          openFileByDefault(getOutputPath("ActiveClsDetails.pdf"));
			          
			          
			          
			      }
			      
			}
			
			String OrgName=getOrganisationParticularsbyOrgID(orgId, "Name");
			String ReptName="Details of Classroom Strength";
			String[] Headers={" Date "," Session ID ","Session Name","No.Of.Students"};
			String[] dataCols={"Date","SessionID","SessionName","NoOfStudents"};
			
			session.setAttribute("OrgName", OrgName);
			session.setAttribute("ReptName", ReptName);
			session.setAttribute("Headers", Headers);
			session.setAttribute("dataCols", dataCols);
			session.setAttribute("tabledata", tabledata1);
			session.setAttribute("FileName", "ActiveClsDetails");
			
			/*if(ExpRepFormat.trim().equalsIgnoreCase("Excel") && tabledata1.size()>0)
			{
				String OrgName=getOrganisationParticularsbyOrgID(orgId, "Name");
				String ReptName="Details of Classroom Strength";
				String[] Headers={" Date "," Session ID ","Session Name","No.Of.Students"};
				String[] dataCols={"Date","SessionID","SessionName","NoOfStudents"};
				
				GenerateExcelReport(OrgName, ReptName, Headers, tabledata1, dataCols,"ActiveClsDetails");
			}*/
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/ActiveClassRoomsDetailsReport.jsp");
			
			dispatcher.forward(request, response);
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	//MIS_UA_011
	void runDisconnectedClassroomsDetailsReport()
	{
		try
		{
			String startDate=request.getParameter("startDate").toString().trim();
			String endDate=request.getParameter("endDate").toString().trim();
			String ExpRepFormat=request.getParameter("ExpRepFormat").toString().trim();
			
			String sessionId=request.getParameter("sessionId").toString().trim();
			
			String userid=session.getAttribute("userId").toString().trim();
			String orgId=session.getAttribute("orgId").toString().trim();
			String username=session.getAttribute("userName").toString().trim();
			
			
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("sessionId", sessionId);
			
			request.setAttribute("ExpRepFormat", ExpRepFormat);
			
			//println("orgId:"+orgId+"	startDate:"+startDate+"	endDate:"+endDate +"	Role:"+Role+"	ExpRepFormat:"+ExpRepFormat);
			
			MISReportsDao MISdao=new MISReportsDao();
			
			ArrayList<HashMap<String,String>> tabledata=new ArrayList<HashMap<String,String>>();
			ArrayList<HashMap<String,String>> tabledata1=new ArrayList<HashMap<String,String>>();
			
			HashMap<String,String> SessionsList=MISdao.getAllSessionsByOrgID(orgId);
			HashMap<String,String> StudentsList=MISdao.getAllStudentsByOrgID(orgId);
			tabledata=MISdao.getDisconnectedClassroomsDetailsByDate(userid, orgId, startDate, endDate,sessionId);
			//count students
			
			if(tabledata!=null && tabledata.size()>0)
			{
				for(int i=0;i<tabledata.size();i++)
				{
					HashMap<String,String> CurrentRowinfo=tabledata.get(i);
					
					int cnt=i+1;
					if(cnt==tabledata.size())
						break;
					
					HashMap<String,String> NxtRowinfo=tabledata.get(i+1);
					
					String CurrentRowLog_Status=CurrentRowinfo.get("LogStatus").toString().trim();
					String NxtRowLog_Status=NxtRowinfo.get("LogStatus").toString().trim();
					
					if(CurrentRowLog_Status.trim().equalsIgnoreCase("in") && NxtRowLog_Status.trim().equalsIgnoreCase("in")) // Row0=in and Row1=in then it's disconnected
					{
						String sessionID=CurrentRowinfo.get("SessionID").toString().trim();
						if(SessionsList.containsKey(sessionID))
							CurrentRowinfo.put("SessionName", SessionsList.get(sessionID).toString().trim()); 
						
						String StudentID=CurrentRowinfo.get("StudentID").toString().trim();
						if(StudentsList.containsKey(StudentID))
							CurrentRowinfo.put("StudentName", StudentsList.get(StudentID).toString().trim());
						
						
						tabledata1.add(CurrentRowinfo);
					}
				}
				
				
			}
			
			// println("org::"+tabledata.size() +"	filtered::"+tabledata1.size());
			request.setAttribute("tabledata", tabledata1);
			
			if(ExpRepFormat.trim().equalsIgnoreCase("PDF") && tabledata1.size()>0) // validate format and with result size 
			{
				String reportPath = getReportPathbyOption("5_Columns"); // report path
				
				HashMap<String,Object> reportParameters = new HashMap<String,Object>();
			      
				/*URL url = new URL(getOrganisationParticularsbyOrgID(orgId,"Logo"));
			    Image  image = ImageIO.read(url);
			    javax.swing.ImageIcon ii = new javax.swing.ImageIcon(image);*/
			    reportParameters.put("ReportLogo", null );
			    reportParameters.put("Organisation_Name", getOrganisationParticularsbyOrgID(orgId,"Name"));
			    reportParameters.put("FullAddress", getOrganisationAddress(orgId));
			    reportParameters.put("Report_Title", "Details of Disconnected Classrooms");
			    String FilterDt="From : "+startDate + " - To: "+endDate;
			    reportParameters.put("Filter_Details", FilterDt);
			      
			      reportParameters.put("Column_Name_1", "Date");
			      reportParameters.put("Column_Name_2", "Session ID");
			      reportParameters.put("Column_Name_3", "Session Name");
			      reportParameters.put("Column_Name_4", "Student ID");
			      reportParameters.put("Column_Name_5", "Student Name");
			      	      
			      
			      if(tabledata1!=null && tabledata1.size()>0)
			      {
			    	  Collection<Map<String, ?>> ShowsData = new ArrayList<Map<String, ?>>();
			    	  
			    	  for(int i=0;i<tabledata1.size();i++)
			    	  {
			    		  HashMap<String,String> info=tabledata1.get(i);
			    		
			    		 // println("disciplineName from DB :: "+info.toString());
			    		  
			    		  Map<String,String> data=new HashMap<String,String>();
			    		  
			    		  data.put("Row_Data_1", info.get("Date").toString().trim());
			    		  data.put("Row_Data_2", info.get("SessionID").toString().trim());
			    		  data.put("Row_Data_3", info.get("SessionName").toString().trim());
			    		  data.put("Row_Data_4", info.get("StudentID").toString().trim());
			    		  data.put("Row_Data_5", info.get("StudentName").toString().trim());
			    		  			    		  
			    		  ShowsData.add(data);  
			    	  }
			    	  
			    	  JRDataSource reportSource = new JRMapCollectionDataSource(ShowsData);
			          
			          JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,  reportParameters,
			        		  reportSource);
			          
			          JasperExportManager.exportReportToPdfFile(jasperPrint, getOutputPath("DisConnClsDetails.pdf"));
			          
			          openFileByDefault(getOutputPath("DisConnClsDetails.pdf"));
			          
			         
			          
			      }
			      
			}
			
			String OrgName=getOrganisationParticularsbyOrgID(orgId, "Name");
			String ReptName="Details of Disconnected Classrooms";
			String[] Headers={" Date "," Session ID ","Session Name","Student ID","Student Name"};
			String[] dataCols={"Date","SessionID","SessionName","StudentID","StudentName"};
			
			session.setAttribute("OrgName", OrgName);
			session.setAttribute("ReptName", ReptName);
			session.setAttribute("Headers", Headers);
			session.setAttribute("dataCols", dataCols);
			session.setAttribute("tabledata", tabledata1);
			session.setAttribute("FileName", "DisConnClsDetails");
			
			/*if(ExpRepFormat.trim().equalsIgnoreCase("Excel") && tabledata1.size()>0)
			{
				String OrgName=getOrganisationParticularsbyOrgID(orgId, "Name");
				String ReptName="Details of Disconnected Classrooms";
				String[] Headers={" Date "," Session ID ","Session Name","Student ID","Student Name"};
				String[] dataCols={"Date","SessionID","SessionName","StudentID","StudentName"};
				
				GenerateExcelReport(OrgName, ReptName, Headers, tabledata1, dataCols,"DisConnClsDetails");
			}*/
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/DisconnectedClassRoomsDetailsReport.jsp");
			
			dispatcher.forward(request, response);
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	
	void runRegistrationDetailsReport( String Status)
	{
		try
		{
			String ExpRepFormat=request.getParameter("ExpRepFormat").toString().trim();
			
			String orgId=session.getAttribute("orgId").toString().trim();
			String username=session.getAttribute("userName").toString().trim();
			
			
			request.setAttribute("ExpRepFormat", ExpRepFormat);
			
			//println("orgId:"+orgId+"	startDate:"+startDate+"	endDate:"+endDate +"	Role:"+Role+"	ExpRepFormat:"+ExpRepFormat);
			
			MISReportsDao MISdao=new MISReportsDao();
			
			ArrayList<HashMap<String,String>> tabledata=null;
			if(Status.trim().equalsIgnoreCase("Pending"))
				 tabledata=MISdao.getPendingRegistrationsByOrgID(orgId);
			else if(Status.trim().equalsIgnoreCase("Completed"))
				tabledata=MISdao.getCompletedRegistrationsByOrgID(orgId);
			
			
			if(tabledata!=null &&  tabledata.size()>0)
			{
				for(int i=0;i<tabledata.size();i++)
				{
					HashMap<String,String> infoRow1=tabledata.get(i);
				}
			}
			request.setAttribute("tabledata", tabledata);
			
			if(ExpRepFormat.trim().equalsIgnoreCase("PDF") && tabledata.size()>0) // validate format and with result size 
			{
				String reportPath = getReportPathbyOption("Registration"); // report path
				
				HashMap<String,Object> reportParameters = new HashMap<String,Object>();
			      
				/*URL url = new URL(getOrganisationParticularsbyOrgID(orgId,"Logo"));
			    Image  image = ImageIO.read(url);
			    javax.swing.ImageIcon ii = new javax.swing.ImageIcon(image);*/
			    reportParameters.put("ReportLogo", null );
			    reportParameters.put("UserName", username);
			    reportParameters.put("Organisation_Name", getOrganisationParticularsbyOrgID(orgId,"Name"));
			    reportParameters.put("FullAddress", getOrganisationAddress(orgId));
			    reportParameters.put("Report_Title", "Details of "+Status+" Registrations");
			    reportParameters.put("Filter_Details", "");
			      
			     reportParameters.put("Column_Name_1", "Student ID");
			      reportParameters.put("Column_Name_2", "Student Name");
			      reportParameters.put("Column_Name_3", "Email");
			      reportParameters.put("Column_Name_4", "Contact No");
			      reportParameters.put("Column_Name_5", "Country");
			      
			      if(tabledata!=null && tabledata.size()>0)
			      {
			    	  Collection<Map<String, ?>> ShowsData = new ArrayList<Map<String, ?>>();
			    	  
			    	  for(int i=0;i<tabledata.size();i++)
			    	  {
			    		  HashMap<String,String> info=tabledata.get(i);
			    		
			    		 // println("disciplineName from DB :: "+info.toString());
			    		  
			    		  Map<String,String> data=new HashMap<String,String>();
			    		  
			    		  data.put("Row_Data_1", info.get("UserId").toString().trim());
			    		  data.put("Row_Data_2", info.get("UserName").toString().trim());
			    		  data.put("Row_Data_3", info.get("Email").toString().trim());
			    		  data.put("Row_Data_4", info.get("contact_no").toString().trim());
			    		  data.put("Row_Data_5", info.get("country").toString().trim());
			    		  ShowsData.add(data);  
			    	  }
			    	  
			    	  JRDataSource reportSource = new JRMapCollectionDataSource(ShowsData);
			          
			          JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,  reportParameters,
			        		  reportSource);
			          
			          JasperExportManager.exportReportToPdfFile(jasperPrint, getOutputPath(Status+"RegDetails.pdf"));
			          
			          openFileByDefault(getOutputPath("RegDetails.pdf"));
			          
			         
			          
			      }
			      
			}
			
			String OrgName=getOrganisationParticularsbyOrgID(orgId, "Name");
			String ReptName="Details of "+Status+" Registrations";
			String[] Headers={" Student ID "," Student Name ","Email","Contact No","Country"};
			String[] dataCols={"UserId","UserName","Email","contact_no","country"};
			
			session.setAttribute("OrgName", OrgName);
			session.setAttribute("ReptName", ReptName);
			session.setAttribute("Headers", Headers);
			session.setAttribute("dataCols", dataCols);
			session.setAttribute("tabledata", tabledata);
			session.setAttribute("FileName", Status+"RegDetails");
			
			/*if(ExpRepFormat.trim().equalsIgnoreCase("Excel") && tabledata.size()>0)
			{
				String OrgName=getOrganisationParticularsbyOrgID(orgId, "Name");
				String ReptName="Details of "+Status+" Registrations";
				String[] Headers={" Student ID "," Student Name ","Email","Contact No","Country"};
				String[] dataCols={"UserId","UserName","Email","contact_no","country"};
				
				GenerateExcelReport(OrgName, ReptName, Headers, tabledata, dataCols,Status+"RegDetails");
			}*/
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/PendingRegReport.jsp");
			
			if(Status.trim().equalsIgnoreCase("Completed"))
				dispatcher = request.getRequestDispatcher("../JSP/CompletedRegReport.jsp");
			
			
			dispatcher.forward(request, response);
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	void runStuAplyCrsDetailsReport()
	{
		try
		{
			String ExpRepFormat=request.getParameter("ExpRepFormat").toString().trim();
			
			String orgId=session.getAttribute("orgId").toString().trim();
			String username=session.getAttribute("userName").toString().trim();
			
			
			request.setAttribute("ExpRepFormat", ExpRepFormat);
			
			//println("orgId:"+orgId+"	startDate:"+startDate+"	endDate:"+endDate +"	Role:"+Role+"	ExpRepFormat:"+ExpRepFormat);
			
			MISReportsDao MISdao=new MISReportsDao();
			
			ArrayList<HashMap<String,String>> tabledata=null;
			tabledata=MISdao.getStudentsAppliedCourses(orgId);
			
			request.setAttribute("tabledata", tabledata);
			
			if(ExpRepFormat.trim().equalsIgnoreCase("PDF") && tabledata.size()>0) // validate format and with result size 
			{
				String reportPath = getReportPathbyOption("Enrollment"); // report path
				
				HashMap<String,Object> reportParameters = new HashMap<String,Object>();
			      
				/*URL url = new URL(getOrganisationParticularsbyOrgID(orgId,"Logo"));
			    Image  image = ImageIO.read(url);
			    javax.swing.ImageIcon ii = new javax.swing.ImageIcon(image);*/
			    reportParameters.put("ReportLogo", null );
			    reportParameters.put("UserName", username);
			    reportParameters.put("Organisation_Name", getOrganisationParticularsbyOrgID(orgId,"Name"));
			    reportParameters.put("FullAddress", getOrganisationAddress(orgId));
			    reportParameters.put("Report_Title", "Details of students applied for the course");
			    reportParameters.put("Filter_Details", "");
			      
			    reportParameters.put("Column_Name_1", "Course ID");
			    reportParameters.put("Column_Name_2", "Course Name");
			    reportParameters.put("Column_Name_3", "Student Name");
			    reportParameters.put("Column_Name_4", "Contact No");
			    reportParameters.put("Column_Name_5", "Country");
			    reportParameters.put("Column_Name_6", "Email");
			      
			      if(tabledata!=null && tabledata.size()>0)
			      {
			    	  Collection<Map<String, ?>> ShowsData = new ArrayList<Map<String, ?>>();
			    	  
			    	  for(int i=0;i<tabledata.size();i++)
			    	  {
			    		  HashMap<String,String> info=tabledata.get(i);
			    		
			    		 // println("disciplineName from DB :: "+info.toString());
			    		  
			    		  Map<String,String> data=new HashMap<String,String>();
			    		  
			    		  data.put("Row_Data_1", info.get("course_id").toString().trim());
			    		  data.put("Row_Data_2", info.get("course_name").toString().trim());
			    		  data.put("Row_Data_3", info.get("full_name").toString().trim());
			    		  data.put("Row_Data_4", info.get("username").toString().trim());
			    		  data.put("Row_Data_5", info.get("country").toString().trim());
			    		  data.put("Row_Data_6", info.get("email").toString().trim());
			    		  ShowsData.add(data);  
			    	  }
			    	  
			    	  JRDataSource reportSource = new JRMapCollectionDataSource(ShowsData);
			          
			          JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,  reportParameters,
			        		  reportSource);
			          
			          JasperExportManager.exportReportToPdfFile(jasperPrint, getOutputPath("StuAplyCrsDetails.pdf"));
			          
			          openFileByDefault(getOutputPath("StuAplyCrsDetails.pdf"));
				         
			          
			         
			          
			      }
			      
			}
			
			String OrgName=getOrganisationParticularsbyOrgID(orgId, "Name");
			String ReptName=" Details of students applied for the course ";
			String[] Headers={" Course ID ","Course Name"," Student Name ","Contact No","Country","Email"};
			String[] dataCols={"course_id","course_name","full_name","username","country","email"};
			
			session.setAttribute("OrgName", OrgName);
			session.setAttribute("ReptName", ReptName);
			session.setAttribute("Headers", Headers);
			session.setAttribute("dataCols", dataCols);
			session.setAttribute("tabledata", tabledata);
			session.setAttribute("FileName", "StuAplyCrsDetails");
			
			/*if(ExpRepFormat.trim().equalsIgnoreCase("Excel") && tabledata.size()>0)
			{
				String OrgName=getOrganisationParticularsbyOrgID(orgId, "Name");
				String ReptName=" Details of students applied for the course ";
				String[] Headers={" Course ID ","Course Name"," Student Name ","Contact No","Country","Email"};
				String[] dataCols={"course_id","course_name","full_name","username","country","email"};
				
				GenerateExcelReport(OrgName, ReptName, Headers, tabledata, dataCols,"StuAplyCrsDetails");
			}*/
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/StudentAppliedCoursesReport.jsp");
			
			dispatcher.forward(request, response);
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	//Report Id MIS_AC_001
	void runStudentAttendingClassesDetailsReport()
	{
			try
			{
				
				String startDate=request.getParameter("startDate").toString().trim();
				String endDate=request.getParameter("endDate").toString().trim();
				String sessionId=request.getParameter("sessionId").toString().trim();
				String ExpRepFormat=request.getParameter("ExpRepFormat").toString().trim();
				
				String user_id=session.getAttribute("userId").toString().trim();
				String Org_id=session.getAttribute("orgId").toString().trim();
				String username=session.getAttribute("userName").toString().trim();
								
				request.setAttribute("startDate", startDate);
				request.setAttribute("endDate", endDate);
				request.setAttribute("sessionId", sessionId);
				request.setAttribute("ExpRepFormat", ExpRepFormat);
				
								
				MISReportsDao MISdao=new MISReportsDao();
				
				ArrayList<HashMap<String,String>> studentAtendenceDetailsTabledata=new ArrayList<HashMap<String,String>>();
				
				studentAtendenceDetailsTabledata=MISdao.getDetailsStudentAttendingClasses(user_id, Org_id, startDate, endDate, sessionId);
				/*if(studentAtendenceDetailsTabledata!=null && studentAtendenceDetailsTabledata.size()>0)
				{
					for(int i=0;i<studentAtendenceDetailsTabledata.size();i++)
					{
						HashMap<String,String> info=studentAtendenceDetailsTabledata.get(i);
						String SessionID=info.get("SessionId").toString().trim();
						String SessionName=info.get("SessionName").toString().trim();
						info.put("SessionName", SessionName); 
						
						String StudentId=info.get("StudentId").toString().trim();
						String UserName=info.get("UserName").toString().trim();
							info.put("StudentName", UserName);
						
					}
				}*/
				
				request.setAttribute("StAttendenceDetailsTabledata", studentAtendenceDetailsTabledata);
				
				if(ExpRepFormat.trim().equalsIgnoreCase("PDF") && studentAtendenceDetailsTabledata.size()>0) // validate format and with result size 
				{
					String reportPath = getReportPathbyOption("5_Columns"); // report path
					
					HashMap<String,Object> reportParameters = new HashMap<String,Object>();
				      
					/*URL url = new URL(getOrganisationParticularsbyOrgID(orgId,"Logo"));
				    Image  image = ImageIO.read(url);
				    javax.swing.ImageIcon ii = new javax.swing.ImageIcon(image);*/
				    reportParameters.put("ReportLogo", null );
				    reportParameters.put("UserName", username);
				    reportParameters.put("Organisation_Name", getOrganisationParticularsbyOrgID(Org_id,"Name"));
				    reportParameters.put("FullAddress", getOrganisationAddress(Org_id));
				    reportParameters.put("Report_Title", "Details Of Student Attending Classes");
				    String FilterDt="From : "+startDate + " - To: "+endDate;
				    reportParameters.put("Filter_Details", FilterDt);
				      
				      reportParameters.put("Column_Name_1", "Date");
				      reportParameters.put("Column_Name_2", "Session ID");
				      reportParameters.put("Column_Name_3", "Session Name");
				      reportParameters.put("Column_Name_4", "Student ID");
				      reportParameters.put("Column_Name_5", "Student Name");
				      	      
				      
				      if(studentAtendenceDetailsTabledata!=null && studentAtendenceDetailsTabledata.size()>0)
				      {
				    	  Collection<Map<String, ?>> ShowsData = new ArrayList<Map<String, ?>>();
				    	  
				    	  for(int i=0;i<studentAtendenceDetailsTabledata.size();i++)
				    	  {
				    		  HashMap<String,String> info=studentAtendenceDetailsTabledata.get(i);
				    		
				    		 // println("disciplineName from DB :: "+info.toString());
				    		  
				    		  Map<String,String> data=new HashMap<String,String>();
				    		  
				    		  data.put("Row_Data_1", info.get("Date_Time").toString().trim());
				    		  data.put("Row_Data_2", info.get("SessionId").toString().trim());
				    		  data.put("Row_Data_3", info.get("SessionName").toString().trim());
				    		  data.put("Row_Data_4", info.get("StudentId").toString().trim());
				    		  data.put("Row_Data_5", info.get("StudentName").toString().trim());

				    		  ShowsData.add(data);  
				    	  }
				    	  
				    	  JRDataSource reportSource = new JRMapCollectionDataSource(ShowsData);
				          
				          JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,  reportParameters,
				        		  reportSource);
				          
				          JasperExportManager.exportReportToPdfFile(jasperPrint, getOutputPath("StuAttenClsDetails.pdf"));
				          
				          openFileByDefault(getOutputPath("StuAttenClsDetails.pdf"));
				          
				         
				          
				      }
				      
				}
				String OrgName=getOrganisationParticularsbyOrgID(Org_id, "Name");
				String ReptName=" Details Of Student Attending Classes ";
				String[] Headers={" Date ","Session ID"," Session Name ","Student ID","Student Name"};
				String[] dataCols={"Date_Time","SessionId","SessionName","StudentId","StudentName"};
				
				session.setAttribute("OrgName", OrgName);
				session.setAttribute("ReptName", ReptName);
				session.setAttribute("Headers", Headers);
				session.setAttribute("dataCols", dataCols);
				session.setAttribute("tabledata", studentAtendenceDetailsTabledata);
				session.setAttribute("FileName", "StuAttenClsDetails");
				
				/*if(ExpRepFormat.trim().equalsIgnoreCase("Excel") && studentAtendenceDetailsTabledata.size()>0)
				{
					String OrgName=getOrganisationParticularsbyOrgID(Org_id, "Name");
					String ReptName=" Details Of Student Attending Classes ";
					String[] Headers={" Date ","Session ID"," Session Name ","Student ID","Student Name"};
					String[] dataCols={"Date_Time","SessionId","SessionName","StudentId","StudentName"};
					
					GenerateExcelReport(OrgName, ReptName, Headers, studentAtendenceDetailsTabledata, dataCols,"StuAttenClsDetails");
				}*/
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/StudentAttendingClasses.jsp");
				
				dispatcher.forward(request, response);
			}catch(Exception ex){ex.printStackTrace();}
				
			
			
		}

	//Report Id MIS_AC_003
		void runFacultyPresentingClassesDetailsReport()
		{
			try
			{
					String startDate=request.getParameter("startDate").toString().trim();
					String endDate=request.getParameter("endDate").toString().trim();
					String sessionId=request.getParameter("sessionId").toString().trim();
					String ExpRepFormat=request.getParameter("ExpRepFormat").toString().trim();
					
					String user_id=session.getAttribute("userId").toString().trim();
					String Org_id=session.getAttribute("orgId").toString().trim();
					String username=session.getAttribute("userName").toString().trim();
									
					request.setAttribute("startDate", startDate);
					request.setAttribute("endDate", endDate);
					request.setAttribute("sessionId", sessionId);
					request.setAttribute("ExpRepFormat", ExpRepFormat);
					
									
					MISReportsDao MISdao=new MISReportsDao();
					
					ArrayList<HashMap<String,String>> Tabledata=new ArrayList<HashMap<String,String>>();
					
					
					Tabledata=MISdao.getDetailsFacultyPresentingClasses(user_id, Org_id, startDate, endDate, sessionId);
					/*if(Tabledata!=null && Tabledata.size()>0)
					{
						for(int i=0;i<Tabledata.size();i++)
						{
							HashMap<String,String> info=Tabledata.get(i);
							String SessionID=info.get("SessionId").toString().trim();
							if(SessionsList.containsKey(SessionID))
								info.put("SessionName", SessionsList.get(SessionID).toString().trim()); 
							
							String FacultyId=info.get("FacultyId").toString().trim();
							if(FacultyList.containsKey(FacultyId))
								info.put("FacultyName", FacultyList.get(FacultyId).toString().trim());
							
						}
					}*/
					
					request.setAttribute("Tabledata", Tabledata);
					
					if(ExpRepFormat.trim().equalsIgnoreCase("PDF") && Tabledata.size()>0) // validate format and with result size 
					{
						String reportPath =  getReportPathbyOption("5_Columns"); // report path
						
						HashMap<String,Object> reportParameters = new HashMap<String,Object>();
					      
						/*URL url = new URL(getOrganisationParticularsbyOrgID(orgId,"Logo"));
					    Image  image = ImageIO.read(url);
					    javax.swing.ImageIcon ii = new javax.swing.ImageIcon(image);*/
					    reportParameters.put("ReportLogo", null );
					    reportParameters.put("UserName", username);
					    reportParameters.put("Organisation_Name", getOrganisationParticularsbyOrgID(Org_id,"Name"));
					    reportParameters.put("FullAddress", getOrganisationAddress(Org_id));
					    reportParameters.put("Report_Title", "Details Of Faculties Presenting Classes");
					    String FilterDt="From : "+startDate + " - To: "+endDate;
					    reportParameters.put("Filter_Details", FilterDt);
					      
					      reportParameters.put("Column_Name_1", "Date");
					      reportParameters.put("Column_Name_2", "Session ID");
					      reportParameters.put("Column_Name_3", "Session Name");
					      reportParameters.put("Column_Name_4", "Faculty ID");
					      reportParameters.put("Column_Name_5", "Faculty Name");
					      
					      
					      if(Tabledata!=null && Tabledata.size()>0)
					      {
					    	  Collection<Map<String, ?>> ShowsData = new ArrayList<Map<String, ?>>();
					    	  
					    	  for(int i=0;i<Tabledata.size();i++)
					    	  {
					    		  HashMap<String,String> info=Tabledata.get(i);
					    		
					    		 // println("disciplineName from DB :: "+info.toString());
					    		  
					    		  Map<String,String> data=new HashMap<String,String>();
					    		  
					    		  data.put("Row_Data_1", info.get("Date_Time").toString().trim());
					    		  data.put("Row_Data_2", info.get("SessionId").toString().trim());
					    		  data.put("Row_Data_3", info.get("SessionName").toString().trim());
					    		  data.put("Row_Data_4", info.get("FacultyId").toString().trim());
					    		  data.put("Row_Data_5", info.get("FacultyName").toString().trim());
					    		  			    		  
					    		  ShowsData.add(data);  
					    	  }
					    	  
					    	  JRDataSource reportSource = new JRMapCollectionDataSource(ShowsData);
					          
					          JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,  reportParameters,
					        		  reportSource);
					          
					          JasperExportManager.exportReportToPdfFile(jasperPrint, getOutputPath("FacPreClsDetails.pdf"));
					          
					          openFileByDefault(getOutputPath("FacPreClsDetails.pdf"));
					          
					         /* if (Desktop.isDesktopSupported()) {
					      	    try {
					      	        File myFile = new File(getOutputPath("FacPreClsDetails.pdf"));
					      	        Desktop.getDesktop().open(myFile);
					      	    } catch (IOException ex) {
					      	        // no application registered for PDFs
					      	    }
					      	}*/
					          
					      }
					      
					}
					
					String OrgName=getOrganisationParticularsbyOrgID(Org_id, "Name");
					String ReptName=" Details Of Faculties Presenting Classes ";
					String[] Headers={" Date ","Session ID"," Session Name ","Faculty ID","Faculty Name"};
					String[] dataCols={"Date_Time","SessionId","SessionName","FacultyId","FacultyName"};
					
					session.setAttribute("OrgName", OrgName);
					session.setAttribute("ReptName", ReptName);
					session.setAttribute("Headers", Headers);
					session.setAttribute("dataCols", dataCols);
					session.setAttribute("tabledata", Tabledata);
					session.setAttribute("FileName", "FacPreClsDetails");
					
					/*if(ExpRepFormat.trim().equalsIgnoreCase("Excel") && Tabledata.size()>0)
					{
						String OrgName=getOrganisationParticularsbyOrgID(Org_id, "Name");
						String ReptName=" Details Of Faculties Presenting Classes ";
						String[] Headers={" Date ","Session ID"," Session Name ","Faculty ID","Faculty Name"};
						String[] dataCols={"Date_Time","SessionId","SessionName","FacultyId","FacultyName"};
						
						GenerateExcelReport(OrgName, ReptName, Headers, Tabledata, dataCols,"FacPreClsDetails");
					}*/
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/FacultiesPresentingClasses.jsp");
					
					dispatcher.forward(request, response);
				}catch(Exception ex){ex.printStackTrace();}
					
				
				
			}
//Report Id MIS_AC_008
void runStudentsAssessmentsResultReport(){
			try{
			String status=request.getParameter("ResultStatus").toString().trim();
			String ExpRepFormat=request.getParameter("ExpRepFormat").toString().trim();
			
			String user_id=session.getAttribute("userId").toString().trim();
			String Org_id=session.getAttribute("orgId").toString().trim();
			String previlege=session.getAttribute("previlege").toString().trim();
			
			println(Org_id);
			String username=session.getAttribute("userName").toString().trim();
			
			request.setAttribute("ResultStatus", status);
			request.setAttribute("ExpRepFormat", ExpRepFormat);
			
			MISReportsDao MISdao=new MISReportsDao();
			
			ArrayList<HashMap<String,String>> studentstatusOfSemester=new ArrayList<HashMap<String,String>>();		
			studentstatusOfSemester=MISdao.getStudentResultReports(user_id, Org_id, status,previlege);

			request.setAttribute("Tabledata", 	studentstatusOfSemester);
			if(ExpRepFormat.trim().equalsIgnoreCase("PDF") && studentstatusOfSemester.size()>0) // validate format and with result size 
			{
				String reportPath = getReportPathbyOption("5_Columns");  // report path
				
				HashMap<String,Object> reportParameters = new HashMap<String,Object>();
			      
				/*URL url = new URL(getOrganisationParticularsbyOrgID(orgId,"Logo"));
			    Image  image = ImageIO.read(url);
			    javax.swing.ImageIcon ii = new javax.swing.ImageIcon(image);*/
			    reportParameters.put("ReportLogo", null );
			    reportParameters.put("UserName", username);
			    reportParameters.put("Organisation_Name", getOrganisationParticularsbyOrgID(Org_id,"Name"));
			    reportParameters.put("FullAddress", getOrganisationAddress(Org_id));
			    reportParameters.put("Report_Title", "Details of Students Assesments Results");
			    reportParameters.put("Filter_Details", "");
			    reportParameters.put("Column_Name_1", "Student");
			    reportParameters.put("Column_Name_2", "Assessment ID");
			    reportParameters.put("Column_Name_3", "Assessment Name");
			    reportParameters.put("Column_Name_4", "Marks");
			    reportParameters.put("Column_Name_5", "Result");
			      
			      if(studentstatusOfSemester!=null && studentstatusOfSemester.size()>0)
			      {
			    	  Collection<Map<String, ?>> ShowsData = new ArrayList<Map<String, ?>>();
			    	  
			    	  for(int i=0;i<studentstatusOfSemester.size();i++)
			    	  {
			    		  HashMap<String,String> info=studentstatusOfSemester.get(i);
			    		
			    		 // println("disciplineName from DB :: "+info.toString());
			    		  
			    		  Map<String,String> data=new HashMap<String,String>();
			    		  
			    		  data.put("Row_Data_1", info.get("uploaded_by").toString().trim());
			    		  data.put("Row_Data_2", info.get("assessment_id").toString().trim());
			    		  data.put("Row_Data_3", info.get("assessment_description").toString().trim());
			    		  data.put("Row_Data_4", info.get("marks").toString().trim());
			    		  data.put("Row_Data_5", info.get("ststus").toString().trim());
			    		  ShowsData.add(data);  
			    	  }
			    	  
			    	  JRDataSource reportSource = new JRMapCollectionDataSource(ShowsData);
			          
			          JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,  reportParameters,
			        		  reportSource);
			          
			          JasperExportManager.exportReportToPdfFile(jasperPrint, getOutputPath("StudentAssessmentResultDetails.pdf"));
			          
			          openFileByDefault(getOutputPath("StudentAssessmentResultDetails.pdf"));
			          
			          /*if (Desktop.isDesktopSupported()) {
			      	    try {
			      	        File myFile = new File(getOutputPath("StudentAssessmentResultDetails.pdf"));
			      	        Desktop.getDesktop().open(myFile);
			      	    } catch (IOException ex) {
			      	        // no application registered for PDFs
			      	    }
			      	}*/
			          
			      }
			      
			}
			String OrgName=getOrganisationParticularsbyOrgID(Org_id, "Name");
			String ReptName=" Details of Students Assesments Results ";
			String[] Headers={" Student ","Assessment ID"," Assessment Name ","Marks","Result"};
			String[] dataCols={"uploaded_by","assessment_id","assessment_description","marks","ststus"};
			
			session.setAttribute("OrgName", OrgName);
			session.setAttribute("ReptName", ReptName);
			session.setAttribute("Headers", Headers);
			session.setAttribute("dataCols", dataCols);
			session.setAttribute("tabledata", studentstatusOfSemester);
			session.setAttribute("FileName", "StudentAssessmentResultDetails");
			
			/*if(ExpRepFormat.trim().equalsIgnoreCase("Excel") && studentstatusOfSemester.size()>0)
			{
				String OrgName=getOrganisationParticularsbyOrgID(Org_id, "Name");
				String ReptName=" Details of Students Assesments Results ";
				String[] Headers={" Student ","Assessment ID"," Assessment Name ","Marks","Result"};
				String[] dataCols={"uploaded_by","assessment_id","assessment_description","marks","ststus"};
				
				GenerateExcelReport(OrgName, ReptName, Headers, studentstatusOfSemester, dataCols,"StudentAssessmentResultDetails");
			}*/
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/StudentsAssessmentResultReport.jsp");
			
			dispatcher.forward(request, response);
			
		}catch(Exception ex){ex.printStackTrace();}
}

//MIS_AC_011
void runStudentsAssignmentsResultReport(){
	try{
	String status=request.getParameter("ResultStatus").toString().trim();
	String ExpRepFormat=request.getParameter("ExpRepFormat").toString().trim();
	
	String user_id=session.getAttribute("userId").toString().trim();
	String Org_id=session.getAttribute("orgId").toString().trim();
	println(Org_id);
	String username=session.getAttribute("userName").toString().trim();
	String previlege=session.getAttribute("previlege").toString().trim();
	
	request.setAttribute("ResultStatus", status);
	request.setAttribute("ExpRepFormat", ExpRepFormat);
	
	MISReportsDao MISdao=new MISReportsDao();
	
	ArrayList<HashMap<String,String>> studentstatusOfSemester=new ArrayList<HashMap<String,String>>();		
	studentstatusOfSemester=MISdao.getStudentAssignmentsResultReports(user_id, Org_id, status,previlege);

	request.setAttribute("Tabledata", 	studentstatusOfSemester);
	if(ExpRepFormat.trim().equalsIgnoreCase("PDF") && studentstatusOfSemester.size()>0) // validate format and with result size 
	{
		String reportPath = getReportPathbyOption("5_Columns");  // report path
		
		HashMap<String,Object> reportParameters = new HashMap<String,Object>();
	      
		/*URL url = new URL(getOrganisationParticularsbyOrgID(orgId,"Logo"));
	    Image  image = ImageIO.read(url);
	    javax.swing.ImageIcon ii = new javax.swing.ImageIcon(image);*/
	    reportParameters.put("ReportLogo", null );
	    reportParameters.put("UserName", username);
	    reportParameters.put("Organisation_Name", getOrganisationParticularsbyOrgID(Org_id,"Name"));
	    reportParameters.put("FullAddress", getOrganisationAddress(Org_id));
	    reportParameters.put("Report_Title", "Details of Students Assignment Results");
	    reportParameters.put("Filter_Details", "");
	    reportParameters.put("Column_Name_1", "Student");
	    reportParameters.put("Column_Name_2", "Assignment ID");
	    reportParameters.put("Column_Name_3", "Assignment Name");
	    reportParameters.put("Column_Name_4", "Marks");
	    reportParameters.put("Column_Name_5", "Result");
	      
	      if(studentstatusOfSemester!=null && studentstatusOfSemester.size()>0)
	      {
	    	  Collection<Map<String, ?>> ShowsData = new ArrayList<Map<String, ?>>();
	    	  
	    	  for(int i=0;i<studentstatusOfSemester.size();i++)
	    	  {
	    		  HashMap<String,String> info=studentstatusOfSemester.get(i);
	    		
	    		 // println("disciplineName from DB :: "+info.toString());
	    		  
	    		  Map<String,String> data=new HashMap<String,String>();
	    		  
	    		  data.put("Row_Data_1", info.get("uploaded_by").toString().trim());
	    		  data.put("Row_Data_2", info.get("assignment_id").toString().trim());
	    		  data.put("Row_Data_3", info.get("assignment_description").toString().trim());
	    		  data.put("Row_Data_4", info.get("marks").toString().trim());
	    		  data.put("Row_Data_5", info.get("ststus").toString().trim());
	    		  ShowsData.add(data);  
	    	  }
	    	  
	    	  JRDataSource reportSource = new JRMapCollectionDataSource(ShowsData);
	          
	          JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,  reportParameters,
	        		  reportSource);
	          
	          JasperExportManager.exportReportToPdfFile(jasperPrint, getOutputPath("StudentAssignmentsResultDetails.pdf"));
	          
	          openFileByDefault(getOutputPath("StudentAssignmentsResultDetails.pdf"));
	          
	          /*if (Desktop.isDesktopSupported()) {
	      	    try {
	      	        File myFile = new File(getOutputPath("StudentAssignmentsResultDetails.pdf"));
	      	        Desktop.getDesktop().open(myFile);
	      	    } catch (IOException ex) {
	      	        // no application registered for PDFs
	      	    }
	      	}*/
	          
	      }
	      
	}
	
	String OrgName=getOrganisationParticularsbyOrgID(Org_id, "Name");
	String ReptName=" Details of Students Assignment Results ";
	String[] Headers={" Student ","Assignment ID"," Assignment Name ","Marks","Result"};
	String[] dataCols={"uploaded_by","assignment_id","assignment_description","marks","ststus"};
	
	session.setAttribute("OrgName", OrgName);
	session.setAttribute("ReptName", ReptName);
	session.setAttribute("Headers", Headers);
	session.setAttribute("dataCols", dataCols);
	session.setAttribute("tabledata", studentstatusOfSemester);
	session.setAttribute("FileName", "StudentAssignmentsResultDetails");
	/*
	if(ExpRepFormat.trim().equalsIgnoreCase("Excel") && studentstatusOfSemester.size()>0)
	{
		String OrgName=getOrganisationParticularsbyOrgID(Org_id, "Name");
		String ReptName=" Details of Students Assignment Results ";
		String[] Headers={" Student ","Assignment ID"," Assignment Name ","Marks","Result"};
		String[] dataCols={"uploaded_by","assignment_id","assignment_description","marks","ststus"};
		
		GenerateExcelReport(OrgName, ReptName, Headers, studentstatusOfSemester, dataCols,"StudentAssignmentsResultDetails");
	}*/
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/StudentsAssignmentResultReport.jsp");
	
	dispatcher.forward(request, response);
	
}catch(Exception ex){ex.printStackTrace();}
}

// 
//MIS_AD_013 && MIS_AD_014 
void runCertificateReportbyStatus(String Status){
	try{
	String CourseSelected=request.getParameter("Course").toString().trim();
	String courseval=request.getParameter("courseval").toString().trim();
	
	println("CourseSelected :: "+CourseSelected+"		courseval::"+courseval);
	String ExpRepFormat=request.getParameter("ExpRepFormat").toString().trim();
	
	String user_id=session.getAttribute("userId").toString().trim();
	String Org_id=session.getAttribute("orgId").toString().trim();
	String username=session.getAttribute("userName").toString().trim();
	
	request.setAttribute("Course", CourseSelected);
	request.setAttribute("ExpRepFormat", ExpRepFormat);
	
	MISReportsDao MISdao=new MISReportsDao();
	
	ArrayList<HashMap<String,String>> Tabledata=new ArrayList<HashMap<String,String>>();
	if(Status.trim().equalsIgnoreCase("Pending"))
		Tabledata=MISdao.getCertificateStatusReports(Org_id, "NO", CourseSelected);
	if(Status.trim().equalsIgnoreCase("Issued"))
		Tabledata=MISdao.getCertificateStatusReports(Org_id, "YES", CourseSelected);
	
	request.setAttribute("tabledata", 	Tabledata);
	
	if(ExpRepFormat.trim().equalsIgnoreCase("PDF") && Tabledata.size()>0) // validate format and with result size 
	{
		String reportPath = getReportPathbyOption("Enrollment");  // report path
		
		HashMap<String,Object> reportParameters = new HashMap<String,Object>();
	      
		/*URL url = new URL(getOrganisationParticularsbyOrgID(orgId,"Logo"));
	    Image  image = ImageIO.read(url);
	    javax.swing.ImageIcon ii = new javax.swing.ImageIcon(image);*/
	    reportParameters.put("ReportLogo", null );
	    reportParameters.put("UserName", username);
	    reportParameters.put("Organisation_Name", getOrganisationParticularsbyOrgID(Org_id,"Name"));
	    reportParameters.put("FullAddress", getOrganisationAddress(Org_id));
	    if(Status.trim().equalsIgnoreCase("Pending"))
	    	reportParameters.put("Report_Title", "Details of Certificates Pending Students");
	    else if(Status.trim().equalsIgnoreCase("Issued"))
	    	reportParameters.put("Report_Title", "Details of Certificates Issued Students");
	    reportParameters.put("Filter_Details", "");
	    reportParameters.put("Column_Name_1", "Course ID");
	    reportParameters.put("Column_Name_2", "Course Name");
	    reportParameters.put("Column_Name_3", "Student ID");
	    reportParameters.put("Column_Name_4", "Student Name");
	    reportParameters.put("Column_Name_5", "Contact No");
	    reportParameters.put("Column_Name_6", "Email");  
	      if(Tabledata!=null && Tabledata.size()>0)
	      {
	    	  Collection<Map<String, ?>> ShowsData = new ArrayList<Map<String, ?>>();
	    	  
	    	  for(int i=0;i<Tabledata.size();i++)
	    	  {
	    		  HashMap<String,String> info=Tabledata.get(i);
	    		
	    		 // println("disciplineName from DB :: "+info.toString());
	    		  
	    		  Map<String,String> data=new HashMap<String,String>();
	    		  
	    		  data.put("Row_Data_1", info.get("course_id_declared_by_teaching_source").toString().trim());
	    		  data.put("Row_Data_2", info.get("course_name").toString().trim());
	    		  data.put("Row_Data_3", info.get("enrollment_process_id").toString().trim());
	    		  data.put("Row_Data_4", info.get("full_name").toString().trim());
	    		  data.put("Row_Data_5", info.get("phone").toString().trim());
	    		  data.put("Row_Data_6", info.get("email").toString().trim());
	    		  ShowsData.add(data);  
	    	  }
	    	  
	    	  JRDataSource reportSource = new JRMapCollectionDataSource(ShowsData);
	          
	          JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,  reportParameters,
	        		  reportSource);
	          
	          JasperExportManager.exportReportToPdfFile(jasperPrint, getOutputPath("CertificateStatusReport.pdf"));
	          
	          openFileByDefault(getOutputPath("CertificateStatusReport.pdf"));
	          
	          /*if (Desktop.isDesktopSupported()) {
	      	    try {
	      	        File myFile = new File(getOutputPath("CertificateStatusReport.pdf"));
	      	        Desktop.getDesktop().open(myFile);
	      	    } catch (IOException ex) {
	      	        // no application registered for PDFs
	      	    }
	      	}*/
	          
	      }
	      
	}
	
	String OrgName=getOrganisationParticularsbyOrgID(Org_id, "Name");
	String ReptName=" Details of Certificate Pending Students ";
	if(Status.trim().equalsIgnoreCase("Pending"))
		ReptName="Details of Certificates Pending Students";
    else if(Status.trim().equalsIgnoreCase("Issued"))
    	ReptName="Details of Certificates Issued Students";
    
	
	String[] Headers={" Course ID ","Course Name"," Student ID","Student Name","Contact No","Email"};
	String[] dataCols={"course_id_declared_by_teaching_source","course_name","enrollment_process_id","full_name","phone","email"};
	
	
	session.setAttribute("OrgName", OrgName);
	session.setAttribute("ReptName", ReptName);
	session.setAttribute("Headers", Headers);
	session.setAttribute("dataCols", dataCols);
	session.setAttribute("tabledata", Tabledata);
	session.setAttribute("FileName", "CertificateStatusReport");
	
	/*if(ExpRepFormat.trim().equalsIgnoreCase("Excel") && Tabledata.size()>0)
	{
		String OrgName=getOrganisationParticularsbyOrgID(Org_id, "Name");
		String ReptName=" Details of Certificate Pending Students ";
		if(Status.trim().equalsIgnoreCase("Pending"))
			ReptName="Details of Certificates Pending Students";
	    else if(Status.trim().equalsIgnoreCase("Issued"))
	    	ReptName="Details of Certificates Issued Students";
	    
		
		String[] Headers={" Course ID ","Course Name"," Student ID","Student Name","Contact No","Email"};
		String[] dataCols={"course_id_declared_by_teaching_source","course_name","enrollment_process_id","full_name","phone","email"};
		
		GenerateExcelReport(OrgName, ReptName, Headers, Tabledata, dataCols,"CertificateStatusReport");
	}*/
	if(Status.trim().equalsIgnoreCase("Pending"))
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/CertificatePendingReport.jsp");
		dispatcher.forward(request, response);
	}else if(Status.trim().equalsIgnoreCase("Issued"))
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/CertificateIssuedReport.jsp");
		dispatcher.forward(request, response);
	}
	
	
}catch(Exception ex){ex.printStackTrace();}
}


void runStudentsExamEligibleByStatus(String Status){
	System.out.println("PDF Code in runStudentsExamEligibleByStatus()");
	try{
	String CourseSelected=request.getParameter("Course").toString().trim();
	String ExpRepFormat=request.getParameter("ExpRepFormat").toString().trim();
	
	String user_id=session.getAttribute("userId").toString().trim();
	String Org_id=session.getAttribute("orgId").toString().trim();
	String username=session.getAttribute("userName").toString().trim();
	String previlege=session.getAttribute("previlege").toString().trim();
	
	request.setAttribute("Course", CourseSelected);
	request.setAttribute("ExpRepFormat", ExpRepFormat);
	
	MISReportsDao MISdao=new MISReportsDao();
	
	ArrayList<HashMap<String,String>> Tabledata=new ArrayList<HashMap<String,String>>();
	if(Status.trim().equalsIgnoreCase("Eligible"))
		Tabledata=MISdao.getExamEligibleStatusReports(Org_id, "YES", CourseSelected,previlege,user_id);
	if(Status.trim().equalsIgnoreCase("Not-Eligible"))
		Tabledata=MISdao.getExamEligibleStatusReports(Org_id, "NO", CourseSelected,previlege,user_id);
	
	request.setAttribute("tabledata", 	Tabledata);
	
	if(ExpRepFormat.trim().equalsIgnoreCase("PDF") && Tabledata.size()>0) // validate format and with result size 
	{
		String reportPath = getReportPathbyOption("4_columns");  // report path
		if(Status.trim().equalsIgnoreCase("Not-Eligible"))
			reportPath = getReportPathbyOption("5_columns");  // report path
		
		HashMap<String,Object> reportParameters = new HashMap<String,Object>();
	      
		/*URL url = new URL(getOrganisationParticularsbyOrgID(orgId,"Logo"));
	    Image  image = ImageIO.read(url);
	    javax.swing.ImageIcon ii = new javax.swing.ImageIcon(image);*/
	    reportParameters.put("ReportLogo", null );
	    reportParameters.put("UserName", username);
	    reportParameters.put("Organisation_Name", getOrganisationParticularsbyOrgID(Org_id,"Name"));
	    reportParameters.put("FullAddress", getOrganisationAddress(Org_id));
	    if(Status.trim().equalsIgnoreCase("Not-Eligible"))
	    	reportParameters.put("Report_Title", "Details of Students Not-Eligible for Exams");
	    else if(Status.trim().equalsIgnoreCase("Eligible"))
	    	reportParameters.put("Report_Title", "Details of Students Eligible for Exams");
	    reportParameters.put("Filter_Details", "");
	    reportParameters.put("Column_Name_1", "Student ID");
	    reportParameters.put("Column_Name_2", "Student Name");
	    reportParameters.put("Column_Name_3", "Contact No \n UserName");
	    reportParameters.put("Column_Name_4", "Email");
	    if(Status.trim().equalsIgnoreCase("Not-Eligible"))
	    	reportParameters.put("Column_Name_5", "Reason");
	    
	      if(Tabledata!=null && Tabledata.size()>0)
	      {
	    	  Collection<Map<String, ?>> ShowsData = new ArrayList<Map<String, ?>>();
	    	  
	    	  for(int i=0;i<Tabledata.size();i++)
	    	  {
	    		  HashMap<String,String> info=Tabledata.get(i);
	    		
	    		 // println("disciplineName from DB :: "+info.toString());
	    		  
	    		  Map<String,String> data=new HashMap<String,String>();
	    		  
	    		  data.put("Row_Data_1", info.get("enrollment_process_id").toString().trim());
	    		  data.put("Row_Data_2", info.get("full_name").toString().trim());
	    		  data.put("Row_Data_3", info.get("phone").toString().trim());
	    		  data.put("Row_Data_4", info.get("email").toString().trim());
	    		  if(Status.trim().equalsIgnoreCase("Not-Eligible"))
	    			  data.put("Row_Data_5", info.get("reason").toString().trim());
	    		  
	    		  ShowsData.add(data);  
	    	  }
	    	  
	    	  JRDataSource reportSource = new JRMapCollectionDataSource(ShowsData);
	    	  System.out.println("reportPath="+reportPath);
	          
	          JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,  reportParameters, reportSource);
	          
	          JasperExportManager.exportReportToPdfFile(jasperPrint, getOutputPath("StudentsExamEligible.pdf"));
	          
	          openFileByDefault(getOutputPath("StudentsExamEligible.pdf"));
	          
	          
	          
	      }
	      
	}
	
	String OrgName=getOrganisationParticularsbyOrgID(Org_id, "Name");
	String ReptName=" Details of Exam Eligible Students ";
	if(Status.trim().equalsIgnoreCase("Not-Eligible"))
		ReptName="Details of Students Not-Eligible for Exams";
    else if(Status.trim().equalsIgnoreCase("Eligible"))
    	ReptName="Details of Students Eligible for Exams";
    
	
	String[] Headers= new String[4]; // {" Student ID","Student Name","Contact No \n UserName","Email"};
	String[] dataCols=new String[4]; // {"enrollment_process_id","full_name","phone","email"};
	
	if(Status.trim().equalsIgnoreCase("Not-Eligible"))
	{
		Headers= new String[5];
		dataCols=new String[5];
		
		Headers[0]=" Student ID";Headers[1]="Student Name";Headers[2]="Contact No \n UserName";Headers[3]="Email";Headers[4]="Reason";
		dataCols[0]="enrollment_process_id";dataCols[1]="full_name";dataCols[2]="phone";dataCols[3]="email";dataCols[4]="reason";
	}else
	{
		Headers= new String[4];
		dataCols=new String[4];
		
		Headers[0]=" Student ID";Headers[1]="Student Name";Headers[2]="Contact No \n UserName";Headers[3]="Email";
		dataCols[0]="enrollment_process_id";dataCols[1]="full_name";dataCols[2]="phone";dataCols[3]="email";
	}
	session.setAttribute("OrgName", OrgName);
	session.setAttribute("ReptName", ReptName);
	session.setAttribute("Headers", Headers);
	session.setAttribute("dataCols", dataCols);
	session.setAttribute("tabledata", Tabledata);
	session.setAttribute("FileName", "StudentsExamEligible");
	
	/*if(ExpRepFormat.trim().equalsIgnoreCase("Excel") && Tabledata.size()>0)
	{
		String OrgName=getOrganisationParticularsbyOrgID(Org_id, "Name");
		String ReptName=" Details of Certificate Eligible Students ";
		if(Status.trim().equalsIgnoreCase("Not-Eligible"))
			ReptName="Details of Students Not-Eligible for Exams";
	    else if(Status.trim().equalsIgnoreCase("Eligible"))
	    	ReptName="Details of Students Eligible for Exams";
	    
		
		String[] Headers={" Course ID ","Course Name"," Student ID","Student Name","Contact No \n UserName","Email"};
		String[] dataCols={"course_id_declared_by_teaching_source","course_name","enrollment_process_id","full_name","phone","email"};
		
		GenerateExcelReport(OrgName, ReptName, Headers, Tabledata, dataCols,"StudentsExamEligible");
	}*/
	if(Status.trim().equalsIgnoreCase("Eligible"))
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/StudentsExamEligibleReport.jsp");
		dispatcher.forward(request, response);
	}else if(Status.trim().equalsIgnoreCase("Not-Eligible"))
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/StudentsExamNotEligibleReport.jsp");
		dispatcher.forward(request, response);
	}
	
	
}catch(Exception ex){ex.printStackTrace();}
}



void runStudentFeesPaidReport(){
	try{
	String CourseSelected=request.getParameter("Course").toString().trim();
	String ExpRepFormat=request.getParameter("ExpRepFormat").toString().trim();
	
	String user_id=session.getAttribute("userId").toString().trim();
	String Org_id=session.getAttribute("orgId").toString().trim();
	String username=session.getAttribute("userName").toString().trim();
	
	request.setAttribute("Course", CourseSelected);
	request.setAttribute("ExpRepFormat", ExpRepFormat);
	
	MISReportsDao MISdao=new MISReportsDao();
	
	ArrayList<HashMap<String,String>> Tabledata=new ArrayList<HashMap<String,String>>();
	
	Tabledata=MISdao.getStudentFeesPaidReports(Org_id, CourseSelected);
	
	request.setAttribute("tabledata", 	Tabledata);
	
	if(ExpRepFormat.trim().equalsIgnoreCase("PDF") && Tabledata.size()>0) // validate format and with result size 
	{
		String reportPath = getReportPathbyOption("5_Columns");  // report path
		
		HashMap<String,Object> reportParameters = new HashMap<String,Object>();
	      
		/*URL url = new URL(getOrganisationParticularsbyOrgID(orgId,"Logo"));
	    Image  image = ImageIO.read(url);
	    javax.swing.ImageIcon ii = new javax.swing.ImageIcon(image);*/
	    reportParameters.put("ReportLogo", null );
	    reportParameters.put("UserName", username);
	    reportParameters.put("Organisation_Name", getOrganisationParticularsbyOrgID(Org_id,"Name"));
	    reportParameters.put("FullAddress", getOrganisationAddress(Org_id));
	    reportParameters.put("Report_Title", "Students Fees Payment Details");
	    reportParameters.put("Filter_Details", "Course : "+CourseSelected);
	    reportParameters.put("Column_Name_1", "Student Name");
	    reportParameters.put("Column_Name_2", "Course Name");
	    reportParameters.put("Column_Name_3", "Fees Details");
	    reportParameters.put("Column_Name_4", "Amount");
	    reportParameters.put("Column_Name_5", "Status");
	    
	    
	      if(Tabledata!=null && Tabledata.size()>0)
	      {
	    	  Collection<Map<String, ?>> ShowsData = new ArrayList<Map<String, ?>>();
	    	  
	    	  for(int i=0;i<Tabledata.size();i++)
	    	  {
	    		  HashMap<String,String> info=Tabledata.get(i);
	    		
	    		 // println("disciplineName from DB :: "+info.toString());
	    		  
	    		  Map<String,String> data=new HashMap<String,String>();
	    		  
	    		  data.put("Row_Data_1", info.get("StudentName").toString().trim());
	    		  data.put("Row_Data_2", info.get("CourseName").toString().trim());
	    		  data.put("Row_Data_3", info.get("mlt_fee_name").toString().trim());
	    		  data.put("Row_Data_4", info.get("fee_amount").toString().trim());
	    		  data.put("Row_Data_5", info.get("status").toString().trim());
	    		  
	    		  ShowsData.add(data);  
	    	  }
	    	  
	    	  JRDataSource reportSource = new JRMapCollectionDataSource(ShowsData);
	          
	          JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,  reportParameters,
	        		  reportSource);
	          
	          JasperExportManager.exportReportToPdfFile(jasperPrint, getOutputPath("StudentFeesPaid.pdf"));
	          
	          openFileByDefault(getOutputPath("StudentFeesPaid.pdf"));
	          
	          /*if (Desktop.isDesktopSupported()) {
	      	    try {
	      	        File myFile = new File(getOutputPath("StudentFeesPaid.pdf"));
	      	        Desktop.getDesktop().open(myFile);
	      	    } catch (IOException ex) {
	      	        // no application registered for PDFs
	      	    }
	      	}*/
	          
	      }
	      
	}
	
	String OrgName=getOrganisationParticularsbyOrgID(Org_id, "Name");
	String ReptName=" Students Fees Paid Details ";
	String[] Headers={"Student Name","Course Name","Fees Details","Amount","Status"};
	String[] dataCols={"StudentName","CourseName","mlt_fee_name","fee_amount","status"};
	
	session.setAttribute("OrgName", OrgName);
	session.setAttribute("ReptName", ReptName);
	session.setAttribute("Headers", Headers);
	session.setAttribute("dataCols", dataCols);
	session.setAttribute("tabledata", Tabledata);
	session.setAttribute("FileName", "StudentFeesPaid");
	
	/*if(ExpRepFormat.trim().equalsIgnoreCase("Excel") && Tabledata.size()>0)
	{
		String OrgName=getOrganisationParticularsbyOrgID(Org_id, "Name");
		String ReptName=" Students Fees Paid Details ";
		String[] Headers={"Student Name","Course Name","Fees Details","Amount","Payment Mode","Payment Date"};
		String[] dataCols={"StudentName","CourseName","fee_details","amount","payment_mode","payment_date"};
		
		GenerateExcelReport(OrgName, ReptName, Headers, Tabledata, dataCols,"StudentFeesPaid");
	}*/
	
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/StudentsFeesPaidReport.jsp");
		dispatcher.forward(request, response);
	
	
}catch(Exception ex){ex.printStackTrace();}
}

void runNoOfEnrollments(){
	try{
	String CourseSelected=request.getParameter("Course").toString().trim();
	String ExpRepFormat=request.getParameter("ExpRepFormat").toString().trim();
	
	String user_id=session.getAttribute("userId").toString().trim();
	String Org_id=session.getAttribute("orgId").toString().trim();
	String username=session.getAttribute("userName").toString().trim();
	
	request.setAttribute("Course", CourseSelected);
	request.setAttribute("ExpRepFormat", ExpRepFormat);
	
	MISReportsDao MISdao=new MISReportsDao();
	
	ArrayList<HashMap<String,String>> Tabledata=new ArrayList<HashMap<String,String>>();
	
	Tabledata=MISdao.getNoOfEnrollmentsReports(Org_id, CourseSelected);
	
	//println("Tabledata :: "+Tabledata.size());
	
	request.setAttribute("tabledata", 	Tabledata);
	
	if(ExpRepFormat.trim().equalsIgnoreCase("PDF") && Tabledata.size()>0) // validate format and with result size 
	{
		String reportPath = getReportPathbyOption("3_Columns");  // report path
		
		HashMap<String,Object> reportParameters = new HashMap<String,Object>();
	      
		/*URL url = new URL(getOrganisationParticularsbyOrgID(orgId,"Logo"));
	    Image  image = ImageIO.read(url);
	    javax.swing.ImageIcon ii = new javax.swing.ImageIcon(image);*/
	    reportParameters.put("ReportLogo", null );
	    reportParameters.put("UserName", username);
	    reportParameters.put("Organisation_Name", getOrganisationParticularsbyOrgID(Org_id,"Name"));
	    reportParameters.put("FullAddress", getOrganisationAddress(Org_id));
	    reportParameters.put("Report_Title", "No.Of Enrollments Details");
	    reportParameters.put("Filter_Details", "");
	    reportParameters.put("Column_Name_1", "Course Name");
	    reportParameters.put("Column_Name_2", "Country");
	    reportParameters.put("Column_Name_3", "No.Of Student");
	    
	      if(Tabledata!=null && Tabledata.size()>0)
	      {
	    	  Collection<Map<String, ?>> ShowsData = new ArrayList<Map<String, ?>>();
	    	  
	    	  for(int i=0;i<Tabledata.size();i++)
	    	  {
	    		  HashMap<String,String> info=Tabledata.get(i);
	    		
	    		 // println("disciplineName from DB :: "+info.toString());
	    		  
	    		  Map<String,String> data=new HashMap<String,String>();
	    		  
	    		  data.put("Row_Data_1", info.get("course_name").toString().trim());
	    		  data.put("Row_Data_2", info.get("country").toString().trim());
	    		  data.put("Row_Data_3", info.get("no_of_enrolments").toString().trim());
	    		  
	    		  ShowsData.add(data);  
	    	  }
	    	  
	    	  JRDataSource reportSource = new JRMapCollectionDataSource(ShowsData);
	          
	          JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,  reportParameters,
	        		  reportSource);
	          
	          JasperExportManager.exportReportToPdfFile(jasperPrint, getOutputPath("NoOfEnrollments.pdf"));
	          
	          openFileByDefault(getOutputPath("NoOfEnrollments.pdf"));
	          
	         /* if (Desktop.isDesktopSupported()) {
	      	    try {
	      	        File myFile = new File(getOutputPath("NoOfEnrollments.pdf"));
	      	        Desktop.getDesktop().open(myFile);
	      	    } catch (IOException ex) {
	      	        // no application registered for PDFs
	      	    }
	      	}*/
	          
	      }
	      
	}
	
	String OrgName=getOrganisationParticularsbyOrgID(Org_id, "Name");
	String ReptName=" No.Of Enrollments Details ";
	String[] Headers={"Course Name","Country","No. Of Students"};
	String[] dataCols={"course_name","country","no_of_enrolments"};

	
	session.setAttribute("OrgName", OrgName);
	session.setAttribute("ReptName", ReptName);
	session.setAttribute("Headers", Headers);
	session.setAttribute("dataCols", dataCols);
	session.setAttribute("tabledata", Tabledata);
	session.setAttribute("FileName", "NoOfEnrollments");
	
	/*if(ExpRepFormat.trim().equalsIgnoreCase("Excel") && Tabledata.size()>0)
	{
		String OrgName=getOrganisationParticularsbyOrgID(Org_id, "Name");
		String ReptName=" No.Of Enrollments Details ";
		String[] Headers={"Course Name","Country","No. Of Students"};
		String[] dataCols={"course_name","country","no_of_enrolments"};
		
		GenerateExcelReport(OrgName, ReptName, Headers, Tabledata, dataCols,"NoOfEnrollments");
	}*/
	
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/NoOfEnrollmentsReport.jsp");
	dispatcher.forward(request, response);
	
	
}catch(Exception ex){ex.printStackTrace();}
}


public void GenerateExcelReport(String OrgName,String ReportTitle,String[] Headers,ArrayList<HashMap<String,String>> tabledata,String[] dataCols,String Filename)
{
	try
	{
		
		//Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook(); 
        
        XSSFCellStyle Datastyle=workbook.createCellStyle();
        Datastyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        Datastyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        Datastyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        Datastyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        
        XSSFCellStyle orgnamestyle=workbook.createCellStyle();
        orgnamestyle.setAlignment(HorizontalAlignment.CENTER);
        
        
        
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("MIS Data");
        
        
        //org name start
        int Rowcnt=0;
        Row Headerrow = sheet.createRow(Rowcnt);
        Cell OrgNameCell=Headerrow.createCell(0);
        XSSFRichTextString orgnameval=new XSSFRichTextString(OrgName.toUpperCase());
        OrgNameCell.setCellValue(orgnameval);
        OrgNameCell.setCellStyle(orgnamestyle);
        //org name end
        
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,Headers.length+1 ));
                
        //report title start
        Rowcnt++;
        Headerrow = sheet.createRow(Rowcnt);
        Cell rptName=Headerrow.createCell(0);
        rptName.setCellValue(ReportTitle);
        rptName.setCellStyle(orgnamestyle);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0,Headers.length+1 ));
        //report title end
        
        //empty row start
        Rowcnt++;
        Headerrow = sheet.createRow(Rowcnt);
        //empty row end
        
        //header start
        Rowcnt++;
        Headerrow = sheet.createRow(Rowcnt);
        Headerrow.createCell(0).setCellValue(" ");
        for(int i=0;i<Headers.length;i++)
        {
        	String Head=Headers[i].toString().trim();
        	Cell cellheader=Headerrow.createCell(i+1);
        	cellheader.setCellValue(Head);
        	cellheader.setCellStyle(Datastyle);
        }
        //header end
        
        //data write start
        Rowcnt++;
        for(int i=0;i<tabledata.size();i++)
        {
        	HashMap<String,String> info=tabledata.get(i);
        	Row DataRow=sheet.createRow(Rowcnt);
        	DataRow.createCell(0).setCellValue(" ");
        	for(int j=0;j<dataCols.length;j++)
        	{
        		String Data=info.get(dataCols[j]).toString().trim();
        		Cell celldata=DataRow.createCell(j+1);
        		celldata.setCellStyle(Datastyle);
        		celldata.setCellValue(Data);
        	}
        	Rowcnt++;
        }
        
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(getOutputPath(Filename+".xlsx")));
            workbook.write(out);
            out.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
  
        openFileByDefault(getOutputPath(Filename+".xlsx"));
        
        /*if (Desktop.isDesktopSupported()) {
	      	    try {
	      	        File myFile = new File( getOutputPath(Filename+".xlsx") );
	      	        Desktop.getDesktop().open(myFile);
	      	    } catch (IOException ex) {
	      	        // no application registered for PDFs
	      	    }
	      	}
            
        */
	}catch(Exception ex){ex.printStackTrace();}
}
	String getDiffTimeBtwTwoDates(String Date1,String Date2)
	{
		String ret="";
		try
		{
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); //.SSSSS // HH:mm:ssXXX

			Date d1 = format.parse(Date1);
			Date d2 = format.parse(Date2);
			
			long diff = d2.getTime() - d1.getTime();

			long diffSeconds = diff / 1000;
					
			ret=""+diffSeconds;
			
		//println("Date1:"+Date1+"		Date2:"+Date2 +"	seconds:"+ret);
		}catch(Exception ex){ex.printStackTrace();}
		return ret;
	}
	String SumofTwoSeconds(String Seconds1,String Seconds2)
	{
		String ret="";
		try
		{
			int Sec1=Integer.parseInt(Seconds1);
			int Sec2=Integer.parseInt(Seconds2);
			
			int Tot=Sec1+Sec2;
			ret=""+Tot;
		}catch(Exception ex){ex.printStackTrace();}
		
		return ret;
	}
	
	private String getDurationString(int seconds) {

	    int hours = seconds / 3600;
	    int minutes = (seconds % 3600) / 60;
	    seconds = seconds % 60;

	    return twoDigitString(hours) + " : " + twoDigitString(minutes) + " : " + twoDigitString(seconds);
	}

	private String twoDigitString(int number) {

	    if (number == 0) {
	        return "00";
	    }

	    if (number / 10 == 0) {
	        return "0" + number;
	    }

	    return String.valueOf(number);
	}
	
	public String getReportPathbyOption(String Option) throws FileNotFoundException
	{
		String ret="";
		try
		{
			URL path = MISReportListService.class.getClassLoader().getResource("reports"); 
			
			File target=new File("/tmp/reports");
			if(target.exists()==false)
			{
				//File source=new File("/home/kes2/KDS/KODE_DEV/src/reports");
				File source=new File("D:/Project/KODE/KODE_DEV/src/reports");
				copyFiles(source);
			}
			
			String CurrentPath="/tmp/reports/" ; // path.getPath(); //"/home/kes2/tmp/reports/"; //
			//println("current path : "+CurrentPath);
			 
			
			if(Option.trim().equalsIgnoreCase("3_Columns"))
				ret=CurrentPath+"3_Columns_MISReport.jasper";
			else if(Option.trim().equalsIgnoreCase("4_Columns"))
				ret=CurrentPath+"4_Columns_MISReport.jasper"; 
			else if(Option.trim().equalsIgnoreCase("5_Columns"))
				ret=CurrentPath+"5_Columns_MISReport.jasper";
			else if(Option.trim().equalsIgnoreCase("6_Columns"))
				ret=CurrentPath+"6_Columns_MISReport.jasper";
			else if(Option.trim().equalsIgnoreCase("Registration"))
				ret=CurrentPath+"RegistrationReport.jasper";
			else if(Option.trim().equalsIgnoreCase("Enrollment"))
				ret=CurrentPath+"EnrollmentReport.jasper";
		}catch(Exception ex){}
		
		println("getReportPathbyOption :: "+ret);
		return ret;
	}
	
	public void copyFiles(final File source) {
	    for (final File fileEntry : source.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	copyFiles(fileEntry);
	        } else {
	            try {
	            	System.out.println(fileEntry.getName());
	            	File f1=fileEntry;
	            	File tmpdir=new File("/tmp/reports"); if(tmpdir.exists()==false) tmpdir.mkdirs();
	            	
					File f2=new File("/tmp/reports/"+fileEntry.getName());
					
	            	Files.copy(f1.toPath(),f2.toPath(),StandardCopyOption.REPLACE_EXISTING);
	            	System.out.println(""+f1.exists() +""+f2.exists());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				
				
	        }
	    }
	}
	
	public String getOutputPath(String filename)
	{
		String ret="";
		try
		{
			/*
			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString();
			println("Current relative path is: " + s);*/
			
			URL path = MISReportListService.class.getClassLoader().getResource("reports");
			
			String CurrentPath="/tmp/reports/" ; // path.getPath();
			// println("current path : "+CurrentPath);
			
			ret=CurrentPath+filename;
			
		}catch(Exception ex){}
		 println("getOutputPath :: "+ret);
		
		request.setAttribute("ReportPath", ret);
		session.setAttribute("ReportPath", ret);
		
		return ret;
	}
	
	public void openFileByDefault(String paramString)
	  {
		String fileName="";
		if(paramString!=null)
		{
			String [] path1=paramString.split("/");
			 fileName=path1[path1.length-1];
		}
		
		request.setAttribute("ReportPath", paramString);
	   
	  }
	
	void println(String text)
	{
		System.out.println(text);
		// LOGGER.info(text);
	}
	public String getOrganisationAddress(String OrgID)
	{
		String ret="";
		try
		{
			ret=getOrganisationParticularsbyOrgID(OrgID, "Address") +","
					+getOrganisationParticularsbyOrgID(OrgID, "City") +"-"+getOrganisationParticularsbyOrgID(OrgID, "PCode");
			//+"    Web:"+getOrganisationParticularsbyOrgID(OrgID, "Website")
		}catch(Exception ex){ex.printStackTrace();}
		return ret;
	}
	public String getOrganisationParticularsbyOrgID(String OrgID,String Value)
	{
		String ret="";
		try
		{
			MISReportsDao dao=new MISReportsDao();
			HashMap<String,String> orginfo=dao.getOrganisationDetailsbyOrgID(OrgID);
			if(Value.trim().equalsIgnoreCase("Name"))
			{
				ret=orginfo.get("organization_name").toString().trim();
			}
			else if(Value.trim().equalsIgnoreCase("Logo"))
			{
				/*if(orginfo.get("logo")==null)
					ret="https://upload.wikimedia.org/wikipedia/en/f/f9/No-image-available.jpg";
				else
					ret=orginfo.get("logo").toString().trim();
				
				if(ret.trim().length()==0)*/
					ret="https://upload.wikimedia.org/wikipedia/en/f/f9/No-image-available.jpg";
			}
			else if(Value.trim().equalsIgnoreCase("Address"))
			{
				ret=orginfo.get("address").toString().trim();
			}
			else if(Value.trim().equalsIgnoreCase("City"))
			{
				ret=orginfo.get("city").toString().trim();
			}
			else if(Value.trim().equalsIgnoreCase("PCode"))
			{
				ret=orginfo.get("postal_code").toString().trim();
			}
			else if(Value.trim().equalsIgnoreCase("Phone"))
			{
				ret=orginfo.get("telephone").toString().trim();
			}
			else if(Value.trim().equalsIgnoreCase("Email"))
			{
				ret=orginfo.get("email").toString().trim();
			}
			else if(Value.trim().equalsIgnoreCase("Website"))
			{
				ret=orginfo.get("url").toString().trim();
			}
			else if(Value.trim().equalsIgnoreCase("State"))
			{
				ret=orginfo.get("state").toString().trim();
			}
			
		
		}catch(Exception ex){ret= " ";}
		return ret;
	}
	
	
	  
}