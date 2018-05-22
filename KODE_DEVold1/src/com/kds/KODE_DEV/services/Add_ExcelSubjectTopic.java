package com.kds.KODE_DEV.services;

	import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.kds.KODE_DEV.dao.DCItoKSMappingDao;
import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SetupCurriculamDomain;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;

import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;





	@SuppressWarnings("serial")
	public class Add_ExcelSubjectTopic extends CommonService {
		//static final Logger LOGGER = Logger.getLogger(CreateFacultyManagement.class);
		DCItoKSMappingDao mapdao=new DCItoKSMappingDao();
		SetupCurriculamDomain SetupCurriculamDomainObj=new SetupCurriculamDomain();
       
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement st=null;
		
		 int day=0,diff=0;
      String stdate=null,endDate=null,tpattern=null,startDate=null;
	    public void run() throws Exception {
	    	session = request.getSession(false);
	    	try{
	    	
		
			String sql = null,fileName="",string1="",sql1="";
			String exception=null;
			boolean result;
			System.out.println("file built");
			FileOutputStream output=null;
			FileInputStream input=null;
		
			int successrow=0;
			 startDate=request.getParameter("datepicker").toString().trim();
			 System.out.println("start date in dci "+startDate);
			 endDate= startDate;
	        try{
	        	DCItoKSMapping();
	        	
	        	// run1();
	        	
	        	DiskFileItemFactory factory = new DiskFileItemFactory();
	    	
	        	ServletContext servletContext = request.getSession().getServletContext();
	    		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
	    	
	    		factory.setRepository(repository);
	    		ServletFileUpload upload = new ServletFileUpload(factory);
	    		factory.setSizeThreshold(20480000);
	    		upload.setSizeMax(51500000);
	    		
	    		List<FileItem> items = upload.parseRequest(request);
	    		java.util.Iterator<FileItem> iter = items.iterator();
	    		InputStream inputStream = null;
	    		long sizeInBytes=0;
	    		while (iter.hasNext()) {
	    		    FileItem item = iter.next();

	    		    if (item.isFormField()) {
	    		    	
	    		    }
	    		    else {
	    		    	
	    		        fileName = item.getName();
	    		        
	    		        inputStream=item.getInputStream();
	    		        string1=item.getString();
	    		      
	    		    }
	    		}
	    		String OS =   System.getProperty("os.name"); 
	    		     // return OS;
	    		System.out.println("OS="+OS);
	    		   
	    		 if(OS.contains("Windows"))
	    		 {
	    			 System.out.println("in windows");
	    			 output = new FileOutputStream("C:\\data\\"+fileName); 
	    	
	    		 }
	    		 else
	    		 {
	    			 System.out.println("in linux");
	    			 output = new FileOutputStream("C:\\data\\"+fileName);
	    			
	    		 }
	    		int k=inputStream.read();
	    		while(k!=-1){
	    		output.write(k);
	    		k=inputStream.read();
	    		}
	    		output.close();
	      
	           if(OS.contains("Windows"))
	    		 {
	        	   input = new FileInputStream("C:\\data\\"+fileName);
	    		 }
	           else
	           {
	        	 
	        	 input = new FileInputStream("C:\\data\\"+fileName);
	           }
	         

	           String fileExtn = GetFileExtension(fileName);
	    
	    		XSSFWorkbook wbx=null;
	    		HSSFWorkbook wbh=null;
	    		XSSFSheet sheetx=null;
	    		HSSFSheet sheeth=null;
	    		Sheet sheet=null;
	     
	    		if (fileExtn.equalsIgnoreCase("xlsx"))
	    	      {
	    			 wbx = new XSSFWorkbook(input);
		            sheet = wbx.getSheetAt(0);
	    	      }
	    		if (fileExtn.equalsIgnoreCase("xls"))
	    	      {
	    		
	            POIFSFileSystem fs = new POIFSFileSystem( input );
	             wbh = new HSSFWorkbook(fs);
	            sheet = wbh.getSheetAt(0);
	    	      }
	    	
	            Row row;
	            String subject="",concatemp="",topic="",subtopic="",semesterId="";
	            double sales_inc=0.0;
	           
	            session=request.getSession(false);
	           
	            Iterator rows = sheet.rowIterator();
	            for(int i=1,offset=0; i<=sheet.getLastRowNum(); i++,offset++){
	            	
	                row = sheet.getRow(i);
	                
	                XSSFCell cell=null,cellsales=null,cellbeat=null;
	                HSSFCell cellh=null,cellsalesh=null,cellbeath=null,cellthird=null;
	                int cellType=0,cellTypeh=0,cellTsales=0,cellTbeat=0,cellTsalesh=0,cellTbeath=0;
	                if (fileExtn.equalsIgnoreCase("xlsx"))
		    	      {
	                	 cellh = (HSSFCell)row.getCell(0);
	 	                cellsalesh = (HSSFCell)row.getCell(1);
	 	                cellthird=(HSSFCell)row.getCell(2);
	 	                cellTypeh = cellh.getCellType();
	 	                cellTsalesh = cellsalesh.getCellType();
	               // System.out.println("Im"+cellTsales);
	         
		    	      }else if (fileExtn.equalsIgnoreCase("xls"))
		    	      {
	                cellh = (HSSFCell)row.getCell(0);
	                cellsalesh = (HSSFCell)row.getCell(1);
	                cellthird=(HSSFCell)row.getCell(2);
	                cellTypeh = cellh.getCellType();
	                cellTsalesh = cellsalesh.getCellType();
	         
	                //System.out.println("Imc"+cellTsalesh);
		    	      }
		    	
	                if ((cellType == XSSFCell.CELL_TYPE_STRING || cellTypeh == HSSFCell.CELL_TYPE_STRING) && (cellTsales == XSSFCell.CELL_TYPE_NUMERIC || cellTsalesh == HSSFCell.CELL_TYPE_NUMERIC))
	                {
	                	
	                	 subject = row.getCell(0).getStringCellValue();
	                	 topic = row.getCell(1).getStringCellValue();
	                	 subtopic = row.getCell(2).getStringCellValue();
	                	
	                	 String subname=getSubjectName();
	                	
	                	 String course=request.getParameter("stream");
	                     if(!(subname.equals(subject)))
	                	 {   
	                    	 
	                		 subjectmethod(subject);
	                		 semesterId=row.getCell(3).getStringCellValue();
	                		 System.out.println("semesterId="+semesterId);
	                		 subjectMethodFormst(semesterId,subject,request.getParameter("stream").toString().trim(),request.getParameter("orgid").toString().trim());
	                		
	                	 }
	                	    
	                	 String orgid=request.getParameter("orgid").toString().trim();
	                	 String cidbts=request.getParameter("stream").toString().trim();
	                	 String userId=(String) session.getAttribute("userId");
	              	     String subjectid=getSubjectId();
	              	     String subjectIdForMst=mapdao.getSubjectIdForMst(subject,orgid,cidbts);
	                	 if(subjectid!=null)
	                	 {
	                		 if(subtopic!=null)
	                		 {
	                			 String topicname=getTopicName(subjectid);
	                			 if(!(topicname.equals(topic)))
	                			 {
	                				 sendTopicData(subjectid,topic);
	                				 mapdao.sendTopicDataInmst(subjectIdForMst,topic,orgid,userId,cidbts);
	                			 }
	                			
	                		 }
	                	 }
	                	 String topicid=getTopicId(topic,subjectid);
	                	  String topicIdForMst=mapdao.getTopicIdForMst(topic,orgid,cidbts);
	                	 if(topicid!=null)
	                	 {
	                		 sendSubTopicData(topicid,subtopic);
	                		 mapdao.sendMstSubTopicData(topicIdForMst,subtopic,orgid,userId);
	                	 }
	                	 successrow++;
	                	 
	                }else
	                {
	                		throw new IOException(); 
	                }
	           
	          
	                
	            }
	      
	          
	          
	            input.close();
	         
	        }catch(IOException e){
	        	exception="Incorrect File format: 1st column-employee id(Text), 2nd- Sales Incentive(Numeric)";
	        	
	            System.out.println(e);
	        }catch(Exception ioe){
	        	//exception=ioe;
	        	System.out.println("Exception occured as below:"+ioe);
	            ioe.printStackTrace();
	        }
	        RequestDispatcher rd=null;
	        if(successrow>0){
	        	
 	    	   String OrgStatus="Curriculum data inserted successfully";
 	    	  request.setAttribute("OwnerSuccess",OrgStatus);
	        rd =request.getRequestDispatcher("../JSP/Create_Course.jsp");
	        
	        }
	        else{
	        	String OrgStatus="This curriculum is already configured";
	 	    	  request.setAttribute("FailureMessage",OrgStatus);
	        	rd =request.getRequestDispatcher("../JSP/Create_Course.jsp");
	        }
	        rd.forward(request, response);
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	

	    }
	    
	    
	
	    public String getStringWithoutSpaces(String s)
		{
			String str[] = s.split(" ");
			String str2 = "";
			for (int i = 0; i < str.length; i++)
			{   
				str2 = str2.concat(str[i]);
			}
			return str2;
			//System.out.println(str2);
		} 
	    public void DCItoKSMapping()
	    {
	    	try
	    	{		
	    		String orgid=request.getParameter("orgid").toString().trim();
	    		String disid=request.getParameter("disid").toString().trim();
	    		String progName=request.getParameter("progid").toString().trim();
	    		String courseName=request.getParameter("courseid").toString().trim();
	    		String stream=request.getParameter("stream").toString().trim();
	    		String strmName=request.getParameter("strmid").toString().trim();
	    		tpattern=request.getParameter("tpattern").toString().trim();
	    		String duration=request.getParameter("duration").toString().trim();
	    		//String tpattern=request.getParameter("tpattern").toString().trim();
	    		System.out.println("duration="+duration);
	    		String nsession=request.getParameter("nsession").toString().trim();
	    		String durationType=request.getParameter("durationType").toString().trim();
	    		String parallelAttempt=request.getParameter("parAttempt").toString().trim();
	    		String coursename=request.getParameter("coursename").toString().trim();
	    		String userId=(String) session.getAttribute("userId");
	    		System.out.println("userid="+userId);
	    		int dur=Integer.parseInt(duration);
	    		
	    		int nsess=Integer.parseInt(nsession);
	    		String courseicon=orgid+"_"+getStringWithoutSpaces(coursename)+".png"; //request.getParameter("courseicon").toString().trim();
	    		
	    		String streamname=request.getParameter("streamname").toString().trim();
	    		String streamicon=orgid+"_"+getStringWithoutSpaces(streamname)+".png"; //request.getParameter("streamicon").toString().trim();
	    		
	    		
	    		
	    		Enumeration<String> keys=request.getParameterNames();
	    		
	    		while(keys.hasMoreElements()){
	                String key = (String)keys.nextElement();
	                System.out.println("Value of "+key+" is: "+request.getParameter(key));
	            }
	    		
	    		
	    		
	    		String topLayerTeachingSource=mapdao.getTopLayerTeachingSource(orgid);
	    		
	    		String ProgID=mapdao.checkAndGetProgramIDByName(disid, progName);
	    		System.out.println("got ProgID == "+ProgID);
	    		if(ProgID.length()>0)
	    		{
	    			String CourseID=mapdao.checkAndGetCourseIDByName(ProgID, courseName,coursename,courseicon);
		    		System.out.println("got CourseID == "+CourseID);
		    		
		    		if(CourseID.length()>0)
		    		{
		    			String StreamID=mapdao.checkAndGetStreamIDByName(CourseID, strmName,streamname,streamicon);
			    		System.out.println("got StreamID == "+StreamID);
			    		
			    		if(StreamID.length()>0)
			    		{
			    			String TopLayerID=mapdao.checkAndGetTopLayerID(CourseID, StreamID, orgid);
				    		System.out.println("got TopLayerID == "+TopLayerID);
				    		
				    		if(TopLayerID.length()>0)
				    		{
				    			String MLTID=mapdao.checkAndGetMiddleLayerID(TopLayerID, orgid);
					    		System.out.println("got MLTID == "+MLTID);
					    		if(MLTID.length()>0)
					    		{
					    			HashMap<String,String> htinfo=new HashMap<String,String>();
						    		htinfo.put("MLTID", MLTID);
						    		htinfo.put("CrsDuration", duration);
						    		htinfo.put("TeachingPattern", tpattern);
						    		htinfo.put("CIDTS", stream);
						    		htinfo.put("CourseID", StreamID);
						    		
						    		String courseDescId=mapdao.insertCourseDescription(htinfo);
						    		
						    		
						    		mapdao.insertDCICourseDesc(stream, courseName, strmName, duration, nsession, orgid);
						    		
						    		SetupCurriculamDomainObj.settopLayerTeaching(topLayerTeachingSource);
						    		SetupCurriculamDomainObj.setDesciplineId(disid);
						    		SetupCurriculamDomainObj.setProgramId(ProgID);
						    		SetupCurriculamDomainObj.setCourseId(CourseID);
						    		SetupCurriculamDomainObj.setDeptId(StreamID);
						    		SetupCurriculamDomainObj.setCourseDiscriptionId(courseDescId);
						    		SetupCurriculamDomainObj.setCibts(stream);
						    		SetupCurriculamDomainObj.setDurationType(durationType);
						    		SetupCurriculamDomainObj.setParllelAttemp(parallelAttempt);
						    		SetupCurriculamDomainObj.setDurationPeriod(dur);
						    		SetupCurriculamDomainObj.setNoOfSession(nsess);
						    		SetupCurriculamDomainObj.setOrgid(orgid);
						    		SetupCurriculamDomainObj.setUserId(userId);
						    		SetupCurriculamDomainObj.setTeachingPattern(tpattern);
						    		
						    		int success=mapdao.saveForFixingGap(SetupCurriculamDomainObj);
						    		
						    		
						    		
						    		
						    		
					    		}
					    			
				    		}
				    			
			    		}
			    			
		    		}
		    			
	    		}
	    		
	    		
	    	}catch(Exception ex){ex.printStackTrace();}
	    }
	   
	    
	    public void subjectmethod(String subject)
	    {
	    	try{
	    		con = DBTransaction.connect();
	         	
	          
	    	 String insertQuery="insert into subject_description(organization_id,course_id_defined_by_teaching_source,subname) values(?,?,?)";
        	
        	   
        	    ps = con.prepareStatement(insertQuery);
 	    		ps.setString(1,request.getParameter("orgid"));
       			ps.setString(2,request.getParameter("stream"));
       		ps.setString(3, subject);
       		int data=0;

       		data= ps.executeUpdate();
       		if(data!=0);
       		
       		
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	finally{
	    		DBTransaction.closeConnection(con);
				try{
				if(ps!=null)
					ps.close();
				
				
				if(con!=null)
					con.close();
				
				
				if(rs!=null)
					rs.close();
				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}
			}
	    }
	    
	    public void subjectMethodFormst(String semesterid,String subject,String cidbts,String orgid)
	    {
	    	
	    	 System.out.println("startDate========"+startDate);
	    	try{
	    		con = DBTransaction.connect();// .dbConnection();
	         	
	    		if(tpattern.equalsIgnoreCase("Semester"))
	    				diff=180;
	    		else if(tpattern.equalsIgnoreCase("Trimester"))
	    			diff=90;
	    		else if(tpattern.equalsIgnoreCase("Yearly"))
	    			diff=365;
	    		else
	    			diff=100;
	    		String subid=mapdao.getSeqVal("nsubid","nsu");
	    		String cacs_id=mapdao.getCacsId(orgid,request.getParameter("stream").toString().trim());
	            String subIdFromSubject=mapdao.getSubIdFromSubjectDescription(subject,cidbts,orgid);
	    	
	    		 String insertQuery="insert into mst_subjects(subject_id,subject_name,subjectid_by_ts,subject_status,organization_id,created_datetime,last_updated_datetime,mod_user_id) values(?,?,?,?,?,now(),now(),?)";
        	  
	    	 String userid=(String)session.getAttribute("userId");
	    	 
        	ps = con.prepareStatement(insertQuery);
 	        ps.setString(1,subid);
       		ps.setString(2,subject);
       		ps.setString(3, cidbts);
       		ps.setString(4, "Active");
       		ps.setString(5, orgid);
       		ps.setString(6, userid);
       		int data=0;

       		data= ps.executeUpdate();
       		if(data!=0)
       		System.out.println("inserted in mst_subjects");
       		mapdao.saveDataToMapSubject(subid,subIdFromSubject,orgid,userid);
       		String semid=mapdao.getSemId();
       		
       		System.out.println("Semester id="+semid);
       		if(semid!=null)
       		{
       			semid="semester"+semid;
       		if(semid.equalsIgnoreCase(semesterid))
       		{
       		
       		}
       		else{
       			startDate=endDate;
       		 stdate=addDate(startDate,day);
       		 endDate=addDate(startDate,diff);
       		}
       		}
       		else
       		{
       		 stdate=addDate(startDate,day);
       		 endDate=addDate(startDate,diff);
       		}
       		mapdao.saveDataForDynamicSyllabus(cacs_id,subIdFromSubject,semesterid,stdate,endDate,orgid,userid);
       		
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	finally{
	    		DBTransaction.closeConnection(con);
				try{
				if(ps!=null)
					ps.close();
				
				
				if(con!=null)
					con.close();
				
				
				if(rs!=null)
					rs.close();
				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}
			}
	    }
	    
	    
	    
	    public void sendTopicData(String subjectid,String topic)
	    {
	    	try{
	    		
	          con = DBTransaction.connect();//.dbConnection();
	    	 String insertQuery="insert into topic_description(subject_id,topicname) values(?,?)";
        	  
        	   
        	    ps = con.prepareStatement(insertQuery);
 	    		ps.setString(1,subjectid);
       			ps.setString(2,topic);
       		
       		int data=0;

       		data= ps.executeUpdate();
       		if(data!=0);
       	//	System.out.println("inserted in topic discription");
       		
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	finally{
	    		DBTransaction.closeConnection(con);
				try{
				if(ps!=null)
					ps.close();
				
				
				if(con!=null)
					con.close();
				
				
				if(rs!=null)
					rs.close();
				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}
			}
	    }
	    
	    
	    public void sendSubTopicData(String topicid,String subtopic)
	    {
	    	try{
	    		con = DBTransaction.connect();//.dbConnection();
	         	//st = con.createStatement();
	           
	    	 String insertQuery="insert into sub_topic_description(topic_id,subtopicname) values(?,?)";
        	    //System.out.println(insertQuery);
        	   
        	    ps = con.prepareStatement(insertQuery);
 	    		ps.setString(1,topicid);
       			ps.setString(2,subtopic);
       		
       		int data=0;

       		data= ps.executeUpdate();
       		if(data!=0);
       		//System.out.println("inserted in subtopic discription");
       		
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	finally{
	    		DBTransaction.closeConnection(con);
				try{
				if(ps!=null)
					ps.close();
				
				
				if(con!=null)
					con.close();
				
				
				if(rs!=null)
					rs.close();
				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}
			}
	    }

	    
	    public String getSubjectId()
	    {
	    	String  subjectid=""; 
	    	try{
	    		  con = DBTransaction.connect();//.dbConnection();
	         	st = con.createStatement();
	       
	            
	            rs = st.executeQuery( "SELECT subject_id FROM subject_description where course_id_defined_by_teaching_source='"+request.getParameter("stream")+"'" );
	            while ( rs.next() ) {
	                 subjectid = rs.getString("subject_id");
	             //   System.out.println("subjectid "+subjectid);
	                
	             }
	    	}
       	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	finally{
	    		DBTransaction.closeConnection(con);
				try{
				if(ps!=null)
					ps.close();
				
				if(st!=null)
					st.close();
				if(con!=null)
					con.close();
				
				
				if(rs!=null)
					rs.close();
				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}
			}
	    	return subjectid;
	    }
	    

	    
	    public String getSubjectName()
	    {
	    	String  subjectname=""; 
	    	try{
	    		con = DBTransaction.connect();//.dbConnection();
	         	st = con.createStatement();
	          
	            
	            rs = st.executeQuery("SELECT subname FROM subject_description where course_id_defined_by_teaching_source='"+request.getParameter("stream")+"'");
	            while ( rs.next() ) {
	            	subjectname = rs.getString("subname");
	             //   System.out.println("subjectid "+subjectname);
	                
	             }
	    	}
       	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	finally{
	    		DBTransaction.closeConnection(con);
				try{
				if(ps!=null)
					ps.close();
				
				if(st!=null)
					st.close();
				if(con!=null)
					con.close();
				
			
				if(rs!=null)
					rs.close();
				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}
			}
	    	return subjectname;
	    }
	    
	    public String getTopicName(String subjectid)
	    {
	    	String  topicname=""; 
	    	try{
	    		con= DBTransaction.connect();//.dbConnection();
	         	st = con.createStatement();
	           
	            
	            rs = st.executeQuery( "SELECT topicname FROM topic_description where subject_id='"+subjectid+"'" );
	            while ( rs.next() ) {
	            	topicname = rs.getString("topicname");
	               // System.out.println("topicname "+topicname);
	                
	             }
	    	}
       	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	finally{
	    		DBTransaction.closeConnection(con);
				try{
				if(ps!=null)
					ps.close();
				
				if(st!=null)
					st.close();
				if(con!=null)
					con.close();
				
				
				if(rs!=null)
					rs.close();
				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}
			}
	    	return topicname;
	    }
	    
	    public String getTopicId(String topic,String subjectid)
	    {
	    	String  topicid=""; 
	    	try{
	    		con = DBTransaction.connect();//.dbConnection();
	    	
	         	st = con.createStatement();
	           
	            
	            rs = st.executeQuery( "SELECT topic_id FROM topic_description where topicname='"+topic+"' and subject_id='"+subjectid+"' " );
	            while ( rs.next() ) {
	                 topicid = rs.getString("topic_id");
	              //  System.out.println("topicid "+topicid);
	                
	             }
	    	}
       	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	finally{
	    		DBTransaction.closeConnection(con);//.dbConnection();
				try{
				if(ps!=null)
					ps.close();
				
				if(st!=null)
					st.close();
				if(con!=null)
					con.close();
				
				
				if(rs!=null)
					rs.close();
				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}
			}
	    	return topicid;
	    }

	    
	    /*public String getCourseId()
	    {
	    	String  courseid=""; 
	    	try{
	    		dbCon = DBTransaction.connect();
	         	st = dbCon.createStatement();
	            Connection con = DBTransaction.connect();
	            
	            rs = st.executeQuery( "SELECT course_id_defined_by_teaching_source FROM subject_description offset 0;" );
	            while ( rs.next() ) {
	            	courseid = rs.getString("course_id_defined_by_teaching_source");
	                System.out.println("topicid "+courseid);
	                
	             }
	    	}
       	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	finally{
				try{
				if(prepareStatement!=null)
					prepareStatement.close();
				
				if(st!=null)
					st.close();
				if(con!=null)
					con.close();
				
				if(dbCon!=null)
					dbCon.close();
				if(rs!=null)
					rs.close();
				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}
			}
	    	return courseid;
	    }*/
	    
	    private static String GetFileExtension(String fname2)
	    {
	        String fileName = fname2;
	        String fname="";
	        String ext="";
	        int mid= fileName.lastIndexOf(".");
	        fname=fileName.substring(0,mid);
	        ext=fileName.substring(mid+1,fileName.length());
	        return ext;
	    }
	    
	    
	    public static String addDate(String CurrentDate,Integer day){ 
	        String newdt="";
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	   Calendar c = Calendar.getInstance();
	   try {
	    c.setTime(sdf.parse(CurrentDate));
	   } catch (Exception e) {
	    e.printStackTrace();
	   }
	   c.add(Calendar.DATE, day); // number of days to add
	   newdt = sdf.format(c.getTime()); // dt is now the new date
	   System.out.println("Current Date:- " + newdt);
	  // c.add(Calendar.DATE, -day);    
	   String prevdate=sdf.format(c.getTime());  
	   return prevdate;  
	  }  
	    
	  

	}
