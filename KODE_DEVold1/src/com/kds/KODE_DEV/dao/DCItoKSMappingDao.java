package com.kds.KODE_DEV.dao;

import java.io.File;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SetupCurriculamDomain;

public class DCItoKSMappingDao {
	
	Connection conn;
	PreparedStatement st;
	ResultSet result;
	java.sql.Date datefordci;
	java.sql.Date datefordci1;
	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	static final Logger LOGGER = Logger.getLogger(DCItoKSMappingDao.class);
	public HashMap<String,ArrayList<HashMap<String,String>>> fetchLookups()
	{
		
		HashMap<String,ArrayList<HashMap<String,String>>> ret=new HashMap<String,ArrayList<HashMap<String,String>>>();
		try
		{
			ArrayList<HashMap<String,String>> DisList=getDesciplineList();
			ArrayList<HashMap<String,String>> ProgList=getProgramList();
			ArrayList<HashMap<String,String>> CrsList=getCourseList();
			ArrayList<HashMap<String,String>> StreamList=getStreamList();
			
			ret.put("DisciplineList", DisList);
			ret.put("ProgramList", ProgList);
			ret.put("CourseList", CrsList);
			ret.put("StreamList", StreamList);
					
			
		}catch(Exception ex){ex.printStackTrace();}
		
		return ret;
	}

	public ArrayList<HashMap<String,String>> getDesciplineList()
	{
		ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
		try
		{
			conn=DBTransaction.connect();
			String sql="SELECT discipline_id,discipline_name FROM transaction_discipline_info order by discipline_name";
			printStringtoConsole(sql);
			st=conn.prepareStatement(sql);
			result=st.executeQuery();
			while(result.next())
			{
				String discipline_id=result.getString("discipline_id").toString().trim();
				String discipline_name=result.getString("discipline_name").toString().trim();
				
				HashMap<String,String> ht=new HashMap<String,String>();
				ht.put("discipline_id", discipline_id);
				ht.put("discipline_name", discipline_name);
				ret.add(ht);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				conn.close();
				st.close();
				result.close();	
			}catch(Exception ex){}
			
		}
		return ret;
	}
	
	public ArrayList<HashMap<String,String>> getProgramList()
	{
		ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
		try
		{
			conn=DBTransaction.connect();
			String sql="SELECT distinct(program_name)  FROM transaction_program_info order by program_name";
			printStringtoConsole(sql);
			st=conn.prepareStatement(sql);
			result=st.executeQuery();
			while(result.next())
			{
				String program_name=result.getString("program_name").toString().trim();
				
				HashMap<String,String> ht=new HashMap<String,String>();
				ht.put("program_name", program_name);
				ret.add(ht);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				conn.close();
				st.close();
				result.close();	
			}catch(Exception ex){}
			
		}
		return ret;
	}
	
	public ArrayList<HashMap<String,String>> getCourseList()
	{
		ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
		try
		{
			conn=DBTransaction.connect();
			String sql="SELECT distinct(course_name) FROM transaction_course_info order by course_name";
			printStringtoConsole(sql);
			st=conn.prepareStatement(sql);
			result=st.executeQuery();
			while(result.next())
			{
				String course_name=result.getString("course_name").toString().trim();
				
				HashMap<String,String> ht=new HashMap<String,String>();
				ht.put("course_name", course_name);
				ret.add(ht);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				conn.close();
				st.close();
				result.close();	
			}catch(Exception ex){}
			
		}
		return ret;
	}
	
	public ArrayList<HashMap<String,String>> getStreamList()
	{
		ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
		try
		{
			conn=DBTransaction.connect();
			String sql="SELECT distinct(department_name) from transaction_department_info order by department_name";
			printStringtoConsole(sql);
			st=conn.prepareStatement(sql);
			result=st.executeQuery();
			while(result.next())
			{
				String department_name=result.getString("department_name").toString().trim();
				
				HashMap<String,String> ht=new HashMap<String,String>();
				ht.put("department_name", department_name);
				ret.add(ht);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				conn.close();
				st.close();
				result.close();	
			}catch(Exception ex){}
			
		}
		return ret;
	}
	
	public String checkAndGetProgramIDByName(String DiscID,String ProgramName)
	{
		String ret="";
		try
		{
			String ProgImagePath="";
			
			boolean programstatus=false;
			conn=DBTransaction.connect();
			String sql="select program_id from transaction_program_info where discipline_id='"+DiscID+"' and program_name='"+ProgramName+"'";
			printStringtoConsole(sql);
			st=conn.prepareStatement(sql);
			result=st.executeQuery();
			while(result.next())
			{
				programstatus=true;
				ret=result.getString("program_id");
			}
			st.close();
			result.close();
			
			if(!programstatus)
			{
				sql="select program_image_path from transaction_program_info where program_name='"+ProgramName+"'";
				printStringtoConsole(sql);
				st=conn.prepareStatement(sql);
				result=st.executeQuery();
				while(result.next())
				{
					if(ProgImagePath.trim().length()==0)
						if(result.getObject("program_image_path")!=null)
							ProgImagePath=result.getString("program_image_path").toString().trim();
				}
				st.close();
				result.close();
				
				sql="INSERT INTO transaction_program_info(discipline_id, program_name, program_discription, program_image_path)"
						+ " VALUES ('"+DiscID+"', '"+ProgramName+"', '"+ProgramName+"', '"+ProgImagePath+"')";
				printStringtoConsole(sql);
				st=conn.prepareStatement(sql);
				st.executeUpdate();
				
				st.close();
				result.close();
				
				sql="select program_id from transaction_program_info where discipline_id='"+DiscID+"' and program_name='"+ProgramName+"'";
				printStringtoConsole(sql);
				st=conn.prepareStatement(sql);
				result=st.executeQuery();
				while(result.next())
				{
					
					ret=result.getString("program_id");
				}
			}
		}catch(Exception ex){ex.printStackTrace();}
		finally
		{
			try
			{
				conn.close();
				st.close();
				result.close();	
			}catch(Exception ex){}
			
		}
		return ret;
	}
	
	public String checkAndGetCourseIDByName(String ProgID,String CourseName,String CrsNameByOrg,String CrsIconByOrg)
	{
		String ret="";
		try
		{
						
			String CrsImagePath="";
			
			if(CourseName.trim().equalsIgnoreCase("Other"))
			{	
				CourseName=CrsNameByOrg;
				CrsImagePath="/images/"+CrsIconByOrg;
			}
			
			boolean coursestatus=false;
			conn=DBTransaction.connect();
			String sql="select course_id from transaction_course_info where program_id='"+ProgID+"' and course_name='"+CourseName+"'";
			printStringtoConsole(sql);
			st=conn.prepareStatement(sql);
			result=st.executeQuery();
			while(result.next())
			{
				coursestatus=true;
				ret=result.getString("course_id");
			}
			st.close();
			result.close();
			
			if(!coursestatus)
			{
				sql="select course_image_path from transaction_course_info where course_name='"+CourseName+"'";
				printStringtoConsole(sql);
				st=conn.prepareStatement(sql);
				result=st.executeQuery();
				while(result.next())
				{
					if(CrsImagePath.trim().length()==0)
						if(result.getObject("course_image_path")!=null)
							CrsImagePath=result.getString("course_image_path").toString().trim();
				}
				st.close();
				result.close();
				
				sql="INSERT INTO transaction_course_info(program_id, course_name, course_discription, department_information_applicable, course_image_path)"
						+ " VALUES ('"+ProgID+"', '"+CourseName+"', '"+CourseName+"', 'TRUE', '"+CrsImagePath+"')";
				printStringtoConsole(sql);
				st=conn.prepareStatement(sql);
				st.executeUpdate();
				
				st.close();
				result.close();
				
				sql="select course_id from transaction_course_info where program_id='"+ProgID+"' and course_name='"+CourseName+"'";
				printStringtoConsole(sql);
				st=conn.prepareStatement(sql);
				result=st.executeQuery();
				while(result.next())
				{
					
					ret=result.getString("course_id");
				}
			}
		}catch(Exception ex){ex.printStackTrace();}
		finally
		{
			try
			{
				conn.close();
				st.close();
				result.close();	
			}catch(Exception ex){}
			
		}
		return ret;
	}

	public String checkAndGetStreamIDByName(String CourseID,String StreamName,String StreamnamebyOrg,String StreamIconByOrg)
	{
		String ret="";
		try
		{
			String StreamImagePath="";
			
			if(StreamName.trim().equalsIgnoreCase("Other"))
			{
				StreamName=StreamnamebyOrg;
				StreamImagePath="/images/"+StreamIconByOrg;
			}
			boolean streamstatus=false;
			conn=DBTransaction.connect();
			String sql="select department_id from transaction_department_info where course_id='"+CourseID+"' and department_name='"+StreamName+"'";
			printStringtoConsole(sql);
			st=conn.prepareStatement(sql);
			result=st.executeQuery();
			while(result.next())
			{
				streamstatus=true;
				ret=result.getString("department_id");
			}
			st.close();
			result.close();
			
			if(!streamstatus)
			{
				sql="select department_image_path from transaction_department_info where department_name='"+StreamName+"'";
				printStringtoConsole(sql);
				st=conn.prepareStatement(sql);
				result=st.executeQuery();
				while(result.next())
				{
					if(StreamImagePath.trim().length()==0)
						if(result.getObject("department_image_path")!=null)
							StreamImagePath=result.getString("department_image_path").toString().trim();
				}
				st.close();
				result.close();
				
				sql="INSERT INTO transaction_department_info(course_id, department_name, department_name_discription, department_image_path)"
						+ " VALUES ('"+CourseID+"', '"+StreamName+"', '"+StreamName+"', '"+StreamImagePath+"')";
				printStringtoConsole(sql);
				st=conn.prepareStatement(sql);
				st.executeUpdate();
				
				st.close();
				result.close();
				
				sql="select department_id from transaction_department_info where course_id='"+CourseID+"' and department_name='"+StreamName+"'";
				printStringtoConsole(sql);
				st=conn.prepareStatement(sql);
				result=st.executeQuery();
				while(result.next())
				{
					
					ret=result.getString("department_id");
				}
			}
		}catch(Exception ex){ex.printStackTrace();}
		finally
		{
			try
			{
				conn.close();
				st.close();
				result.close();	
			}catch(Exception ex){}
			
		}
		return ret;
	}
	
	public String checkAndGetTopLayerID(String CourseID,String DeptID,String OrgID)
	{
		String ret="";
		try
		{
			String TopLayerName="";
			String TopLayerImagePath="";
			
			boolean toplayerstatus=false;
			conn=DBTransaction.connect();
			String sql="select org_type from org_details where organization_id='"+OrgID+"'";
			printStringtoConsole(sql);
			st=conn.prepareStatement(sql);
			result=st.executeQuery();
			while(result.next())
			{
				TopLayerName=result.getString("org_type");
			}
			st.close();
			result.close();
			
			sql="select top_layer_teaching_source_id from transaction_top_layer_teaching_source where top_layer_teaching_source_name='"+TopLayerName+"' and department_id='"+DeptID+"' and course_id='"+CourseID+"'";
			printStringtoConsole(sql);
			st=conn.prepareStatement(sql);
			result=st.executeQuery();
			while(result.next())
			{
				toplayerstatus=true;
				ret=result.getString("top_layer_teaching_source_id");
			}
			st.close();
			result.close();
			
			if(!toplayerstatus)
			{
				sql="select top_layer_teaching_source_image_path from transaction_top_layer_teaching_source where top_layer_teaching_source_name='"+TopLayerName+"'";
				printStringtoConsole(sql);
				st=conn.prepareStatement(sql);
				result=st.executeQuery();
				while(result.next())
				{
					if(TopLayerImagePath.trim().length()==0)
						if(result.getObject("top_layer_teaching_source_image_path")!=null)
							TopLayerImagePath=result.getString("top_layer_teaching_source_image_path").toString().trim();
				}
				st.close();
				result.close();
				
				sql="INSERT INTO transaction_top_layer_teaching_source(course_id, department_id, top_layer_teaching_source_name, top_layer_teaching_source_discription, top_layer_teaching_source_image_path)"
						+ "    VALUES ('"+CourseID+"', '"+DeptID+"', '"+TopLayerName+"', '"+TopLayerName+"', '"+TopLayerImagePath+"' ) "; 
				printStringtoConsole(sql);
				st=conn.prepareStatement(sql);
				st.executeUpdate();
				
				st.close();
				result.close();
				
				sql="select top_layer_teaching_source_id from transaction_top_layer_teaching_source where top_layer_teaching_source_name='"+TopLayerName+"'";
				printStringtoConsole(sql);
				st=conn.prepareStatement(sql);
				result=st.executeQuery();
				while(result.next())
				{
					
					ret=result.getString("top_layer_teaching_source_id");
				}
			} 
		}catch(Exception ex){ex.printStackTrace();}
		finally
		{
			try
			{
				conn.close();
				st.close();
				result.close();	
			}catch(Exception ex){}
			
		}
		return ret;
	}

	public String checkAndGetMiddleLayerID(String TopLayerID,String OrgID)
	{
		String ret="";
		try
		{
			String MiddleLayerName="";
			String MiddleLayerImagePath="";
			
			boolean middlelayerstatus=false;
			conn=DBTransaction.connect();
			String sql="select organization_name,logo from org_details where organization_id='"+OrgID+"'";
			printStringtoConsole(sql);
			st=conn.prepareStatement(sql);
			result=st.executeQuery();
			while(result.next())
			{
				MiddleLayerName=result.getString("organization_name");
				if(result.getObject("logo")!=null)
					if(result.getString("logo").length()>0)
						MiddleLayerImagePath=result.getString("logo");
			}
			st.close();
			result.close();
			
			if(!middlelayerstatus)
			{
				/*sql="select middle_layer_teaching_source_image_path from transaction_middle_layer_teaching_source where middle_layer_teaching_source_name='"+MiddleLayerName+"'";
				printStringtoConsole(sql);
				st=conn.prepareStatement(sql);
				result=st.executeQuery();
				while(result.next())
				{
					MiddleLayerImagePath=result.getString("middle_layer_teaching_source_image_path").toString().trim();
				}
				st.close();
				result.close();
				*/
				if(MiddleLayerImagePath!=null && MiddleLayerImagePath.trim().length()>0)
					MiddleLayerImagePath="/images/"+MiddleLayerImagePath;
				else
					MiddleLayerImagePath="/images/university_icon.png";
				
				sql="INSERT INTO transaction_middle_layer_teaching_source(top_layer_teaching_source_id, middle_layer_teaching_source_name, middle_layer_teaching_source_discription, bottom_layer_applicable, middle_layer_teaching_source_image_path, organization_id)"
						+ "  VALUES ('"+TopLayerID+"', '"+MiddleLayerName+"', '"+MiddleLayerName+"', 'false', '"+MiddleLayerImagePath+"', '"+OrgID+"') "; 
				printStringtoConsole(sql);
				st=conn.prepareStatement(sql);
				st.executeUpdate();
				
				st.close();
				result.close();
				
				sql="select middle_layer_teaching_source_id from transaction_middle_layer_teaching_source where middle_layer_teaching_source_name='"+MiddleLayerName+"' and organization_id='"+OrgID+"'";
				printStringtoConsole(sql);
				st=conn.prepareStatement(sql);
				result=st.executeQuery();
				while(result.next())
				{
					
					ret=result.getString("middle_layer_teaching_source_id");
				}
			}
		}catch(Exception ex){ex.printStackTrace();}
		finally
		{
			try
			{
				conn.close();
				st.close();
				result.close();	
			}catch(Exception ex){}
			
		}
		return ret;
	}
	
	public String insertCourseDescription(HashMap<String,String> htinfo)
	{
		String courseDescId=null;
		try
		{
			/*
			 * INSERT INTO transaction_course_description_info(middle_layer_teaching_source_id, bottom_layer_teaching_source_id, course_duration_in_month, teaching_pattern, course_id_declared_by_teaching_source, course_id, image_path)
    VALUES (?, ?, ?, ?, ?, ?, ?)
			 */
			
			String MLTID=htinfo.get("MLTID");
			String BLTID=null;
			String CrsDuration=htinfo.get("CrsDuration");
			String TeachingPattern=htinfo.get("TeachingPattern");
			String CIDTS=htinfo.get("CIDTS");
			String CourseID=htinfo.get("CourseID"); // deptID
			String[] SylImages=buildsyllabusArray(CrsDuration,TeachingPattern);
			
			System.out.println(" testststs "+ getSqlArray(SylImages)) ;
			
			conn=DBTransaction.connect();
			String sql="INSERT INTO transaction_course_description_info(middle_layer_teaching_source_id, bottom_layer_teaching_source_id, course_duration_in_month, teaching_pattern, course_id_declared_by_teaching_source, course_id, image_path)"
					+ " VALUES ('"+MLTID+"', null, '"+CrsDuration+"', '"+TeachingPattern+"', '"+CIDTS+"', '"+CourseID+"', '"+getSqlArray(SylImages)+"')";
			printStringtoConsole(sql);
			st=conn.prepareStatement(sql);  
			/*st.setString(1, MLTID); 
			st.setString(2, BLTID);
			st.setInt(3, Integer.parseInt(CrsDuration));
			st.setString(4, TeachingPattern);
			st.setString(5, CIDTS);
			st.setString(6, CourseID);
			final java.sql.Array sqlArray = conn.createArrayOf("character", SylImages); 
			st.setArray(7, sqlArray);
			*/
			st.executeUpdate();
			
			String sql1="select course_discription_id from transaction_course_description_info where middle_layer_teaching_source_id='"+MLTID+"' and course_id_declared_by_teaching_source='"+CIDTS+"' and course_id='"+CourseID+"'";
			printStringtoConsole(sql1);
			st=conn.prepareStatement(sql1);
			result=st.executeQuery();
			while(result.next())
			{
				courseDescId=result.getString("course_discription_id").toString().trim();
			}
		}catch(Exception ex){ex.printStackTrace();}
		finally
		{
			try
			{
				conn.close();
				st.close();
				result.close();	
			}catch(Exception ex){}
		}
		return courseDescId;
	}
	
	public void insertDCICourseDesc(String StreamID,String CourseName,String StreamName,String Duration,String NoOfSession,String OrgID)
	{
		try
		{
			conn=DBTransaction.connect();
			String sql="INSERT INTO course_description(course_id_defined_by_teaching_source, course_name, stream, duration_in_month,"
					+ " number_of_session, customer_id) VALUES ('"+StreamID+"', '"+CourseName+"', '"+StreamName+"', '"+Duration+"', '"+NoOfSession+"', '"+OrgID+"' )";
			printStringtoConsole(sql);
			st=conn.prepareStatement(sql);
			
			st.executeUpdate();
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		{
			try
			{
				conn.close();
				st.close();
				result.close();	
			}catch(Exception ex){}
		}
	}
	
	
	
	
	
	String getSqlArray(String[] arr)
	{
		String ret = "{";
		String single="/images/syllabus/Blank_Syllabus.png";
		if(arr.length>0)
		{
			
			
			for(int i=0;i<arr.length;i++)
			{
				
				if(i==0)
					ret=ret+single;
				else
					ret=ret+","+single;	
			}  
			ret=ret+"}";
			//System.out.println(""+ret);
		}
		return ret;
	}
	String[] buildsyllabusArray(String CrsDuration,String TeachingPattrn)
	{
		 String[] arr=new String[1];
		 String SinglePath="/images/syllabus/Blank_Syllabus.png";
		 arr[0]=SinglePath;
		try
		{
			int CD=Integer.parseInt(CrsDuration);
			int TP=1;
			if(TeachingPattrn.trim().equalsIgnoreCase("nonsemester"))
			{
				arr=new String[1];
				arr[0]=SinglePath;
			}else if(TeachingPattrn.trim().equalsIgnoreCase("semester"))
			{
				TP=CD/6;
			}else if(TeachingPattrn.trim().equalsIgnoreCase("yearly"))
			{
				TP=CD/12;
			}else if(TeachingPattrn.trim().equalsIgnoreCase("trimester"))
			{
				TP=CD/3;
			}
			
			if(TP>0)
			{
				arr=new String[TP];
				for(int i=0;i<TP;i++)
				{
					arr[i]=SinglePath;
				}	
			}
			
		}catch(Exception ex){ return arr;}
		
		return arr;
	}
	void printStringtoConsole(String text)
	{
		System.out.println(text);
		//LOGGER.info(text);
	}
	
	public String getTopLayerTeachingSource(String orgid)
	{
		String orgType=null;
		try{
		conn=DBTransaction.connect();
		String sql="select org_type from org_details where organization_id='"+orgid+"'";
		printStringtoConsole(sql);
		st=conn.prepareStatement(sql);
		result=st.executeQuery();
		while(result.next())
		{
			orgType=result.getString("org_type").toString().trim();
			
		}
		
	}catch(Exception ex){ex.printStackTrace();}
	finally{
		try
		{
			conn.close();
			st.close();
			result.close();	
		}catch(Exception ex){}
		
	}
	return orgType;	
	}
	
	public int saveForFixingGap(SetupCurriculamDomain SetupCurriculamDomainObj)
	{
		String cacsid=getSeqVal("cacsid","cacs");
		int success=0;
		try
		{
			conn=DBTransaction.connect();
			String sql="INSERT INTO mst_course_catalogue(cacs_id, tlt_type, discipline_id, program_id, course_id, dept_id,"
					+ " courseid_by_ts,deptid_by_ts, course_description_id, parallel_attempt, duration_type, duration_period, "
					+ "teaching_pattern,  no_of_sessions, cacs_status, organization_id, created_datetime, last_updated_datetime,"
					+ " mod_user_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),now(),?)";
				
			printStringtoConsole(sql);
			st=conn.prepareStatement(sql);
			st.setString(1, cacsid);
			st.setString(2, SetupCurriculamDomainObj.gettopLayerTeaching());
			st.setString(3, SetupCurriculamDomainObj.getDesciplineId());
			st.setString(4, SetupCurriculamDomainObj.getProgramId());
			st.setString(5, SetupCurriculamDomainObj.getCourseId());
			st.setString(6,SetupCurriculamDomainObj.getDeptId());
			st.setString(7,SetupCurriculamDomainObj.getCourseId());
			
			System.out.println("getcourseid="+SetupCurriculamDomainObj.getCourseId());
			
			
			st.setString(8,SetupCurriculamDomainObj.getCibts());
			
			System.out.println("getCibts="+SetupCurriculamDomainObj.getCibts());
			
			st.setString(9, SetupCurriculamDomainObj.getCourseDiscriptionId());
			st.setString(10,SetupCurriculamDomainObj.getParllelAttemp() );
			st.setString(11, SetupCurriculamDomainObj.getDurationType());
			st.setInt(12, SetupCurriculamDomainObj.getDurationPeriod());
			st.setString(13, SetupCurriculamDomainObj.getTeachingPattern());
			st.setInt(14, SetupCurriculamDomainObj.getNoOfSession());
			st.setString(15, "Active");
			st.setString(16,SetupCurriculamDomainObj.getOrgid());
			st.setString(17, SetupCurriculamDomainObj.getUserId());
			
			success=st.executeUpdate();
			printStringtoConsole("sql="+st);

		}catch(Exception ex){ex.printStackTrace();}
		finally
		{
			try
			{
				conn.close();
				st.close();
				result.close();	
			}catch(Exception ex){}
		}
		return success;
	}
	
	public String getSeqVal(String SeqName,String AppendTxt)
	 {
	  String ret="";
	  Connection conn=null;
	  PreparedStatement st=null;
	  ResultSet rs=null;
	  try
	  {
	   conn=DBTransaction.connect();
	   String sql="SELECT nextval('"+SeqName+"') as "+SeqName+" ";
	  
	   st=conn.prepareStatement(sql);
	   rs=st.executeQuery();
	   while(rs.next())
	   {
	    ret=rs.getString(SeqName);
	   }
	   if(ret.trim().length()==1) ret="000"+ret;
	    else if(ret.trim().length()==2) ret="00"+ret;
	     else if(ret.trim().length()==3) ret="0"+ret;
	   
	   ret=AppendTxt+ret;
	  }catch(Exception ex){ex.printStackTrace();}
	  finally{
	   try {
	     conn.close();
	     st.close();
	     rs.close();
	    } catch (SQLException e) {
	     // TODO Auto-generated catch block
	     e.printStackTrace();
	    }
	  }
	
	  return ret;
	 }
	
	public String getSubjectIdForMst(String subname,String orgid,String cidbts)
	{
		
	String subid=null;
		try{
			conn=DBTransaction.connect();
			String sql="select subject_id from mst_subjects where organization_id='"+orgid+"' and subject_name='"+subname+"' and subjectid_by_ts='"+cidbts+"'";
			printStringtoConsole(sql);
			st=conn.prepareStatement(sql);
			result=st.executeQuery();
			while(result.next())
			{
				subid=result.getString("subject_id").toString().trim();
				
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				conn.close();
				st.close();
				result.close();	
			}catch(Exception ex){}
	}
	return subid;
	}
	
	public String getTopicIdForMst(String topicname,String orgid,String cidbts)
	{
		
	String topicid=null;
		try{
			conn=DBTransaction.connect();
			String sql="select subject_id from mst_topics where organization_id='"+orgid+"' and topic_name='"+topicname+"' and topicid_by_ts='"+cidbts+"'";
			printStringtoConsole(sql);
			st=conn.prepareStatement(sql);
			result=st.executeQuery();
			while(result.next())
			{
				topicid=result.getString("topic_id").toString().trim();
				
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				conn.close();
				st.close();
				result.close();	
			}catch(Exception ex){}
	}
	return topicid;
	}
	
	public void sendTopicDataInmst(String subid,String topicname,String orgid,String userId,String cidbts)
	{
		try{
		 conn = DBTransaction.connect();//.dbConnection();
    	 String insertQuery="insert into mst_topics(topic_id,subject_id,topic_name,topicid_by_ts,topic_status,organization_id,created_datetime,last_updated_datetime,mod_user_id) values(?,?,?,?,?,?,now(),now(),?)";
    	 String topicid=getSeqVal("ntopicid","ntopic");    	   
    	    st = conn.prepareStatement(insertQuery);
	    		st.setString(1,topicid);
   			st.setString(2,subid);
   			st.setString(3,topicname );
   			st.setString(4, topicname);
   			st.setString(5, "Active");
   			st.setString(6,orgid);
   			st.setString(7, userId);
   		
   		int data=0;

   		 data=st.executeUpdate();
	}
	catch(Exception ex){ex.printStackTrace();}
	finally{
		try
		{
			conn.close();
			st.close();
			result.close();	
		}catch(Exception ex){}
	
}
}
	
	
	public void sendMstSubTopicData(String topicid,String subtopicname,String orgid,String userId)
	{
		try{
		 conn = DBTransaction.connect();//.dbConnection();
    	 String insertQuery="insert into mst_subtopics(sub_topic_id,topic_id,sub_topic_name,subtopic_id_by_ts,sub_topic_status,organization_id,created_datetime,last_updated_datetime,mod_user_id) values(?,?,?,?,?,?,now(),now(),?)";
    	 String subtopicid=getSeqVal("nsubtopicid","nsubtopic");    	   
    	    st = conn.prepareStatement(insertQuery);
	    		st.setString(1,subtopicid);
   			st.setString(2,topicid);
   			st.setString(3,subtopicname );
   			st.setString(4, subtopicname);
   			st.setString(5, "Active");
   			st.setString(6,orgid);
   			st.setString(7, userId);
   		
   		int data=0;

   		 data=st.executeUpdate();
	}
	catch(Exception ex){ex.printStackTrace();}
	finally{
		try
		{
			conn.close();
			st.close();
			result.close();	
		}catch(Exception ex){}
	
}
}
	
	
	public String getCacsId(String orgid,String didbts)
	{
		String cacsid=null;
		try{
			conn=DBTransaction.connect();
			String sql="select cacs_id from mst_course_catalogue where organization_id='"+orgid+"' and courseid_by_ts='"+didbts+"'";
			printStringtoConsole(sql);
			st=conn.prepareStatement(sql);
			result=st.executeQuery();
			while(result.next())
			{
				cacsid=result.getString("cacs_id").toString().trim();
				
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				conn.close();
				st.close();
				result.close();	
			}catch(Exception ex){}
	}
		return cacsid;
	}
	
	public void saveDataForDynamicSyllabus(String casc_id,String subIdFromSubject,String semesterid,String stdate,String endDate,String orgid,String userid)
	{	

		char semid=semesterid.charAt(semesterid.length()-1);
		int semid1=Character.getNumericValue(semid);
		System.out.println("start date"+stdate);
		System.out.println("start date"+endDate);
		try{
			java.util.Date date =formatter.parse(stdate);
			datefordci = new java.sql.Date(date.getTime());
			
			java.util.Date date1 =formatter.parse(endDate);
			 datefordci1 = new java.sql.Date(date1.getTime());
			 String sylId=getSeqVal("sylbusid","sylbus");
			conn=DBTransaction.connect();
			String insertQuery="insert into mst_course_syllabus(syllabus_id,cacs_id,subject_id,semester_id,semester_start_date,semester_end_date,syllabus_status,organization_id,created_datetime,last_updated_datetime,mod_user_id) values(?,?,?,?,?,?,?,?,now(),now(),?)";
	    	     	   
	    	    st = conn.prepareStatement(insertQuery);
	    	    st.setString(1,sylId);
		    	st.setString(2,casc_id);
	   			st.setString(3,subIdFromSubject);
	   			st.setInt(4,semid1);
	   			st.setDate(5, datefordci);
	   			st.setDate(6, datefordci1);
			    st.setString(7,"Active");
			    st.setString(8, orgid);
			    st.setString(9, userid);
			    int data=0;
		   		 data=st.executeUpdate();
		   		 if(data!=0)
		   			 System.out.println("Data inserted in to mst_course_syllabus");
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				conn.close();
				st.close();
				result.close();	
			}catch(Exception ex){}
	}				
			
	}
	
	public void saveDataToMapSubject(String mstsubid,String subid,String orgid,String userId)
	{
		try{
			 conn = DBTransaction.connect();//.dbConnection();
	    	 String insertQuery="insert into subject_mst_subject_mapping(mst_subject_id,subject_id,organization_id,map_status,created_datetime,last_updated_datetime,mod_user_id) values(?,?,?,?,now(),now(),?)";
	    	 String subtopicid=getSeqVal("nsubtopicid","nsubtopic");    	   
	    	    st = conn.prepareStatement(insertQuery);
		    		st.setString(1,mstsubid);
	   			st.setString(2,subid);
	   			st.setString(3,orgid );
	   			st.setString(4, "Active");
	   			st.setString(5, userId);
	   		
	   		int data=0;

	   		 data=st.executeUpdate();
		}
		catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				conn.close();
				st.close();
				result.close();	
			}catch(Exception ex){}
		
	}
	}
	
	public String getSubIdFromSubjectDescription(String subjectName,String cidbts,String orgid)
	{
		String subid=null;
		try{
			conn=DBTransaction.connect();
			String sql="select subject_id from subject_description where organization_id='"+orgid+"' and course_id_defined_by_teaching_source='"+cidbts+"' and subname='"+subjectName+"'";
			printStringtoConsole(sql);
			st=conn.prepareStatement(sql);
			result=st.executeQuery();
			while(result.next())
			{
				subid=result.getString("subject_id").toString().trim();
				
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				conn.close();
				st.close();
				result.close();	
			}catch(Exception ex){}
	}
		return subid;
	}
	
	
	public String getSemId()
	{
		String semid=null;
		try{
			conn=DBTransaction.connect();
			String sql="select semester_id from mst_course_syllabus";
			printStringtoConsole(sql);
			st=conn.prepareStatement(sql);
			result=st.executeQuery();
			while(result.next())
			{
				semid=result.getString("semester_id").toString().trim();
				
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				conn.close();
				st.close();
				result.close();	
			}catch(Exception ex){}
	}
		return semid;

	}
}