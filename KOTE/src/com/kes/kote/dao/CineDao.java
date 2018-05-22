package com.kes.kote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.kes.kote.dbconnection.DBTransaction;
import com.kes.kote.domain.CineOrgDetailsDomain;
import com.kes.kote.domain.CineSpocsDomain;
import com.kes.kote.services.Mailer;
import com.kes.kote.util.CLModuleTaskControlUtil;
import com.kes.kote.util.PropertiesUtil;
import com.kes.kote.util.SessionUtil;

public class CineDao {
	
	TaskControlDao taskDao=new TaskControlDao();
	ModuleControlDao moduleDao=new ModuleControlDao();
	CommonDao commmonDao=new CommonDao();
	
	public int saveOrgDetails(CineOrgDetailsDomain domain,HttpSession sess)
	{
		int ret=0;
		Connection conn=null;
		PreparedStatement st=null;
		
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			
			String OrgType=util.getTeachingSource();
			String OrgID=generateOrgID(domain,sess);
			String sql="INSERT INTO org_details(organization_id, organization_name, org_type, address, city, country, postal_code,  url, logo, year_of_foundation, state, last_name, date_time,org_currency,org_date_format)"
					+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			st=conn.prepareStatement(sql);
			st.setString(1, OrgID);
			st.setString(2, domain.getOrgName());
			st.setString(3, OrgType);
			st.setString(4, domain.getAddress());
			st.setString(5, domain.getCity());
			st.setString(6, domain.getCountry());
			st.setString(7, domain.getPinCode());
			st.setString(8, domain.getWebsite());
			st.setString(9, domain.getLogoName());
			st.setString(10, domain.getYearofEst());
			st.setString(11, domain.getState());
			if(OrgType.trim().equalsIgnoreCase("University") || OrgType.trim().equalsIgnoreCase("Corporate") || OrgType.trim().equalsIgnoreCase("VTI"))
				st.setString(12, "");
			else if(OrgType.trim().equalsIgnoreCase("Professionals") )
				st.setString(12, domain.getOrgLastName());
			
			st.setTimestamp(13, commmonDao.getTimeStamp());
			st.setString(14, domain.getCurrencyCode());
			st.setString(15, domain.getDateFormat());
			
			util.print("saveOrgDetails query : "+st.toString());
			ret=st.executeUpdate();
			
			if(ret==1)
			{
				saveSpacetoTS(domain,sess);
				moduleDao.createModuleControl(OrgID,sess);
				taskDao.createTaskControl(OrgID,sess);
				
				util.setOrgCurrencyCode(domain.getCurrencyCode());
				util.setOrgDateFormat(domain.getDateFormat());
			}
			
			if(ret==1)
				taskDao.saveTaskControl(CLModuleTaskControlUtil.CINEORGDETAILS,"Completed",sess);
			else
				taskDao.saveTaskControl(CLModuleTaskControlUtil.CINEORGDETAILS,"Failed",sess);
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try {
				if(conn!=null)
					conn.close();
				if(st!=null)
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return ret;
	}
	
	public int saveSpacetoTS(CineOrgDetailsDomain domain,HttpSession sess)
	{
		int ret=0;
		Connection conn=null;
		PreparedStatement st=null;
		
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			
			String tssid=commmonDao.getSeqVal("tssid", "tssid",sess);
			String sql="INSERT INTO knowledgestore_teachingsource_info(ts_space_id, organization_id, space_allocated, space_uom, space_available, created_datetime, last_updated_datetime, mod_user_id)"
					+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
			st=conn.prepareStatement(sql);
			st.setString(1, tssid);
			st.setString(2, util.getOrgID());
			st.setInt(3, commmonDao.convertToInt(domain.getKsSpace()));
			st.setString(4, domain.getSpaceUOM().toUpperCase());
			st.setInt(5, commmonDao.convertToInt(domain.getKsSpace()));
			st.setTimestamp(6, commmonDao.getTimeStamp());
			st.setTimestamp(7, commmonDao.getTimeStamp());
			st.setString(8, util.getuserID());
			
			util.print("saveSpacetoTS query : "+st.toString());
			
			ret=st.executeUpdate();
			
		}catch(Exception ex){}
		finally{
			try {
				if(conn!=null)
					conn.close();
				if(st!=null)
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return ret;
	}
	
	public int saveSpocDetails(ArrayList<CineSpocsDomain> spocDetails,HttpSession sess)
	{
		int ret=0;
		Connection conn=null;
		PreparedStatement st=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			if(spocDetails!=null && spocDetails.size()>0)
			{
				for(int i=0;i<spocDetails.size();i++)
				{
					CineSpocsDomain domain=spocDetails.get(i);
					
					String SpocID=commmonDao.getSeqVal("spocid","spoc",sess);
					
					String sql="INSERT INTO mst_instn_spocs(spoc_id, department, spoc_name, spoc_design, spoc_mobile, spoc_landline, spoc_lline_extn, spoc_faxno, spoc_email, spoc_status, organization_id, created_datetime, last_updated_datetime, mod_user_id)"
							+ " VALUES ( ?,  ?,  ?,  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
					
					st=conn.prepareStatement(sql);
					st.setString(1, SpocID);
					st.setString(2, domain.getDeptName());
					st.setString(3, domain.getSPOCName());
					st.setString(4, domain.getDisgn());
					st.setDouble(5, commmonDao.convertToDouble(domain.getMobNo()));
					st.setDouble(6, commmonDao.convertToDouble(domain.getLLNo()));
					st.setInt(7, commmonDao.convertToInt(domain.getExtNo()));
					st.setDouble(8, commmonDao.convertToDouble(domain.getFaxNo()));
					st.setString(9, domain.getEmail());
					st.setString(10, "Active");
					st.setString(11, util.getOrgID());
					st.setTimestamp(12, commmonDao.getTimeStamp());
					st.setTimestamp(13, commmonDao.getTimeStamp());
					st.setString(14, util.getuserID());
					
					util.print("saveSpocDetails query at "+i+" : "+st.toString());
					ret=st.executeUpdate();
					if(ret==1)
					{
						if(domain.getEmail()!=null && domain.getEmail().length()>0)
						{
							String subject=PropertiesUtil.getMessageProperty("spocs.mailsubject");
							String Msg=PropertiesUtil.getMessageProperty("spocs.mailbody");
							if(Msg.contains("<User>"))
							{
								Msg=Msg.replace("<User>", domain.getSPOCName());
								Msg=Msg.replace("<orgname>", util.getOrgName());
								Msg=Msg.replace("<disgn>", domain.getDisgn());
							}
							String Footer=PropertiesUtil.getMessageProperty("spocs.mailfooter");
							
							Msg=Msg+"<br> <br>"
									+Footer;
							try
							{
								Mailer.send(domain.getEmail(), subject, Msg,sess); 
							}catch(Exception ex){}
						}
					}
				}
			}
			
			if(ret==1)
				taskDao.saveTaskControl(CLModuleTaskControlUtil.CINESPOCS,"Completed",sess);
			else
				taskDao.saveTaskControl(CLModuleTaskControlUtil.CINESPOCS,"Failed",sess);
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try {
				if(conn!=null)
					conn.close();
				if(st!=null)
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return ret;
	}

	public String generateOrgID(CineOrgDetailsDomain domain,HttpSession sess)
	{
		String ret="";
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			String OrgType=util.getTeachingSource().toUpperCase();
			String OrgName=domain.getOrgName();
			String orgLName=domain.getOrgLastName();
			OrgName=commmonDao.getStringWithoutSpaces(OrgName).toUpperCase();
			orgLName=commmonDao.getStringWithoutSpaces(orgLName).toUpperCase();
			
			String R4Digit=CommonDao.getRandom4Digit();
			
			if(OrgType.trim().equalsIgnoreCase("University") || OrgType.trim().equalsIgnoreCase("Corporate") || OrgType.trim().equalsIgnoreCase("VTI"))
			{
				ret=OrgName.substring(0, 3)+""+OrgType.substring(0, 3)+""+R4Digit;
			}else if(OrgType.trim().equalsIgnoreCase("Professionals") )
			{
				if(OrgName.trim().length()>3 && orgLName.trim().length()>3)
				{
					ret=OrgName.substring(0, 1)+orgLName.subSequence(0, 1)+OrgType.substring(0, 4)+R4Digit;
				}else if(OrgName.trim().length()>3 && orgLName.trim().length()==0)
				{
					ret=OrgName.substring(0, 3)+OrgType.substring(0, 4)+R4Digit;
				}else if(OrgName.trim().length()<=3 && orgLName.trim().length()>3)
				{
					ret=OrgName+orgLName.substring(0, 1)+OrgType.substring(0, 4)+R4Digit;
				}else if(OrgName.trim().length()>3 && orgLName.trim().length()<=3)
				{
					ret=OrgName.substring(0, 1)+orgLName+OrgType.substring(0, 4)+R4Digit;
				}
			}
			util.print("generateOrgID == "+ret);
			util.setOrgID(ret);
			util.setOrgName(OrgName);
		}catch(Exception ex){ex.printStackTrace();}
		return ret;
	}
	
	   
	
}
