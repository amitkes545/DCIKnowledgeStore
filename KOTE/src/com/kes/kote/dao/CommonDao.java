package com.kes.kote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import com.kes.kote.dbconnection.DBTransaction;
import com.kes.kote.domain.CineOrgDetailsDomain;
import com.kes.kote.util.CLModuleTaskControlUtil;
import com.kes.kote.util.SessionUtil;

public class CommonDao {

	
	public String getSeqVal(String SeqName,String AppendTxt,HttpSession sess)
	{
		String ret="";
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		try
		{
			conn=DBTransaction.openConnection();
			String sql="SELECT nextval('"+SeqName+"') as "+SeqName+" ";
			st=conn.prepareStatement(sql);
			util.print("getSeqVal "+st.toString());
			rs=st.executeQuery();
			while(rs.next())
			{
				ret=rs.getString(SeqName);
			}
			if(ret.trim().length()==1) ret="00"+ret;
				else if(ret.trim().length()==2) ret="0"+ret;
					// else if(ret.trim().length()==3) ret="0"+ret;
			
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
		util.print("Seq o/p == "+ret);
		return ret;
	}
	
	public List<HashMap<String,String>> getUsersofOrg(HttpSession sess)
	{
		List<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
		
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			String sql="SELECT username, user_id  FROM users_info where organization_id= ?";
			st=conn.prepareStatement(sql);
			st.setString(1, util.getOrgID());
			util.print("getUsersofOrg query for : "+st.toString());
			rs=st.executeQuery();
			while(rs.next())
			{
				HashMap<String,String> info =new HashMap<String,String>();
				info.put("username", rs.getString("username").toString().trim());
				info.put("user_id", rs.getString("user_id").toString().trim());
				
				ret.add(info);
			}
			
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
	
	public String checkAndGetOrgId(CineOrgDetailsDomain  domain,HttpSession sess)
	{
		String ret="";
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			String sql="SELECT organization_id,org_type FROM org_details where organization_name=? and country=? and postal_code=? and org_type=?";
			st=conn.prepareStatement(sql);
			st.setString(1, domain.getOrgName());
			st.setString(2, domain.getCountry());
			st.setString(3, domain.getPinCode());
			st.setString(4, util.getTeachingSource());
			
			util.print("checkAndGetOrgId == "+st.toString());
			rs=st.executeQuery();
			//String org_type="";
			while(rs.next())
			{
				ret=rs.getString("organization_id").toString().trim();
				//org_type=rs.getString("org_type").toString().trim();
			}
			
			util.setOrgID(ret);
			//util.setTeachingSource(org_type);
			util.setOrgName(domain.getOrgName());
			
		}catch(Exception ex){ret=""; ex.printStackTrace();}
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
	
	public String getLastCompletedTaskIdbyOrgId(String OrgID,HttpSession sess)
	{
		String ret="";
		
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			String sql="select task_id from mst_configlayer_task_control where organization_id= ? and task_status='Completed' order by task_id desc limit 2 ";
			sql="select task_id from mst_configlayer_task_control where organization_id =? and last_updated_datetime=(select max(last_updated_datetime) from mst_configlayer_task_control where organization_id =?)";
			st=conn.prepareStatement(sql);
			st.setString(1, OrgID);
			st.setString(2, OrgID);
			util.print("getLastCompletedTaskIdbyOrgId =="+st.toString());
			rs=st.executeQuery();
			String cflid="";
			while(rs.next())
			{
				cflid=rs.getString("task_id").toString().trim();
				ret=cflid;
				/*if(cflid.trim().length()==0)
				{
					cflid=rs.getString("task_id").toString().trim();
					ret=cflid;
				}else if(cflid.trim().equalsIgnoreCase(CLModuleTaskControlUtil.STAMPENROLLFORM))
				{
					cflid=rs.getString("task_id").toString().trim();
					if(!cflid.trim().equalsIgnoreCase(CLModuleTaskControlUtil.STAMPEFW))
						ret=cflid;
				}else if(cflid.trim().equalsIgnoreCase(CLModuleTaskControlUtil.STACSTIMETABLE))
				{
					cflid=rs.getString("task_id").toString().trim();
					if(!cflid.trim().equalsIgnoreCase(CLModuleTaskControlUtil.STACSCosTST))
						ret=cflid;
				}else if(cflid.trim().equalsIgnoreCase(CLModuleTaskControlUtil.STACSSGPACGPA))
				{
					cflid=rs.getString("task_id").toString().trim();
					if(!cflid.trim().equalsIgnoreCase(CLModuleTaskControlUtil.STACSTIMETABLE))
						ret=cflid;
				}*/
				
			}
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
	
	public java.sql.Timestamp getTimeStamp()
	{
		java.sql.Timestamp ret=null;
		try
		{
			Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			ret = new java.sql.Timestamp(now.getTime());
			
		}catch(Exception ex){ex.printStackTrace();}
		
		return ret;
	}
	
	public java.sql.Timestamp getSessiondate(String SessionDate,SessionUtil util)
	{
		if ((SessionDate.trim().length() > 0) && (SessionDate.contains("."))) {
			      SessionDate = SessionDate.replaceAll(".", ":");
			    }
		
		java.sql.Timestamp ret=null;
		try
		{
			String dFormat=util.getOrgDateFormat()+" HH:mm";
			
			String[] checksec=SessionDate.split(":");
			if(checksec.length==2)
				dFormat=util.getOrgDateFormat()+" HH:mm";
			if(checksec.length==3)
				dFormat=util.getOrgDateFormat()+" HH:mm:ss";
			
			SimpleDateFormat sdf1 = new SimpleDateFormat(dFormat);
			java.util.Date now = sdf1.parse(SessionDate);
			
			ret = new java.sql.Timestamp(now.getTime());
			
		}catch(Exception ex){ex.printStackTrace();}
		
		return ret;
	}
	
	public String getDateForSessionTable(String SessionDate,SessionUtil util)
	{
		if ((SessionDate.trim().length() > 0) && (SessionDate.contains("."))) {
		      SessionDate = SessionDate.replaceAll(".", ":");
		    }
		
		String ret=SessionDate;
		try
		{
			String dFormat=util.getOrgDateFormat()+" HH:mm";
			
			String[] checksec=SessionDate.split(":");
			if(checksec.length==2)
				dFormat=util.getOrgDateFormat()+" HH:mm";
			if(checksec.length==3)
				dFormat=util.getOrgDateFormat()+" HH:mm:ss";
			
			SimpleDateFormat sdf1 = new SimpleDateFormat(dFormat);
			java.util.Date now = sdf1.parse(SessionDate);
			sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			ret=sdf1.format(now);
			
		}catch(Exception ex){ex.printStackTrace();}
		return ret;
	}
	
	
	public java.sql.Date getDate(String dateStr,SessionUtil util)
	{
		java.sql.Date dt=null;
		try
		{
			SimpleDateFormat sdf1 = new SimpleDateFormat(util.getOrgDateFormat());
			java.util.Date date = sdf1.parse(dateStr);
			dt = new java.sql.Date(date.getTime());
			
		}catch(Exception ex){}
		return dt;
	}
	public java.sql.Date getDatebySlash(String dateStr,SessionUtil util)
	{
		java.sql.Date dt=null;
		try
		{
			SimpleDateFormat sdf1 = new SimpleDateFormat(util.getOrgDateFormat());
			java.util.Date date = sdf1.parse(dateStr);
			dt = new java.sql.Date(date.getTime());
			
		}catch(Exception ex){}
		return dt;
	}
	
	public Double convertToDouble(String value)
	{
		try
		{
			return new Double(value);	
		}catch(Exception ex){ return -1.0; }
		
	}
	
	public int convertToInt(String value)
	{
		try
		{
			return new Integer(value);
		}catch(Exception ex){return -1;}
	}
	
	public static String getStringWithoutSpaces(String s)
	  {
				String str[] = s.split(" ");
				String str2 = "";
				for (int i = 0; i < str.length; i++)
				{   
					str2 = str2.concat(str[i]);
				}
				return str2;
				
	  }
	
	public static String getRandom4Digit()
	{
		String ret="";
		try
		{
			Random rand = new Random();
			String id = String.format("%04d", rand.nextInt(10000));

			ret=id;

			
		}catch(Exception ex){}
		return ret;
	}
	
	/*PreparedStatement statement = connection.prepareStatement("Select * from test where field in (?)");
	Array array = statement.getConnection().createArrayOf("VARCHAR", new Object[]{"A1", "B2","C3"});
	statement.setArray(1, array);
	ResultSet rs = statement.executeQuery();*/
	public static String formatDate(String date, String fromDateFormat, String toDateFormat) throws ParseException {
	    Date initDate = new SimpleDateFormat(fromDateFormat).parse(date);
	    SimpleDateFormat formatter = new SimpleDateFormat(toDateFormat);
	    String parsedDate = formatter.format(initDate);
	    return parsedDate;
	}
	

}
