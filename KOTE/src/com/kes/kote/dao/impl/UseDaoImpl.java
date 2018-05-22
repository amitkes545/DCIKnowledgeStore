package com.kes.kote.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.kes.kote.constant.KoteConstants;
import com.kes.kote.dao.CommonDao;
import com.kes.kote.dao.LookupDao;
import com.kes.kote.dao.ModuleControlDao;
import com.kes.kote.dao.TaskControlDao;
import com.kes.kote.dao.UseDao;
import com.kes.kote.dbconnection.DBTransaction;
import com.kes.kote.domain.MenuDomain;
import com.kes.kote.domain.UseAccessDomain;
import com.kes.kote.domain.UseMailServerDomain;
import com.kes.kote.domain.UseNotificationDomain;
import com.kes.kote.domain.UseParamDomain;
import com.kes.kote.domain.UseRoleDomain;
import com.kes.kote.domain.UseRoleMapDomain;
import com.kes.kote.domain.UseUserDomain;
import com.kes.kote.domain.UsersInfoDomain;
import com.kes.kote.interfaces.LookupInterface;
import com.kes.kote.services.Mailer;
import com.kes.kote.util.CLModuleTaskControlUtil;
import com.kes.kote.util.Encryption;
import com.kes.kote.util.GenerateRandomPwd;
import com.kes.kote.util.PropertiesUtil;
import com.kes.kote.util.SessionUtil;

public class UseDaoImpl implements UseDao {

	TaskControlDao taskDao=new TaskControlDao();
	ModuleControlDao moduleDao=new ModuleControlDao();
	CommonDao commmonDao=new CommonDao();
	
	
	@Override
	public int saveUserParam(List<UseParamDomain> useParamList,HttpSession sess) {
		Connection conn = null;
		int result = 0;
		PreparedStatement ps = null;
		try {
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn = DBTransaction.openConnection();
			if (!useParamList.isEmpty()) {
				for (UseParamDomain useParam : useParamList) {
					String parmId=commmonDao.getSeqVal("prmid","prmid",sess);
					String sql = "Insert into system_parameters(param_id, param_key, param_description,param_value, param_type, param_status, organization_id, created_datetime, last_updated_datetime,mod_user_id) values (?,?,?,?,?,?,?,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, parmId);
					ps.setString(2, useParam.getKey());
					ps.setString(3, useParam.getDescription());
					ps.setString(4, useParam.getValue());
					ps.setString(5, useParam.getType());
					ps.setString(6, KoteConstants.ACTIVE);
					ps.setString(7, util.getOrgID());
					ps.setTimestamp(8, commmonDao.getTimeStamp());
					ps.setTimestamp(9, commmonDao.getTimeStamp());
					ps.setString(10, util.getuserID());
					util.print("system_parameters == "+ps.toString());
					result = ps.executeUpdate();
				}
			}
			
			if(result > 0)
				taskDao.saveTaskControl(CLModuleTaskControlUtil.USEPARAM,"Completed",sess);
			else
				taskDao.saveTaskControl(CLModuleTaskControlUtil.USEPARAM,"Failed",sess);
			
		} catch (Exception e) {
               e.printStackTrace();
		}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(ps!=null) ps.close();
				
			}catch(Exception ex){}
			
		}
		return result;
	}


	@Override
	public int saveMailServrDetails(List<UseMailServerDomain> useMailRequestList,HttpSession sess) {
		Connection conn = null;
		int result = 0;
		PreparedStatement ps = null;
		try {
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn = DBTransaction.openConnection();
			if (!useMailRequestList.isEmpty()) {
				for (UseMailServerDomain mailServer : useMailRequestList) {
					String mailServerId=commmonDao.getSeqVal("mailserverid","mserv",sess);
					String sql = "Insert into mst_mail_server(server_id, server_name, smtp_address, smtp_host,smtp_port, server_status, organization_id, created_datetime, last_updated_datetime,mod_user_id) values (?,?,?,?,?,?,?,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, mailServerId);
					ps.setString(2, mailServer.getName());
					ps.setString(3, mailServer.getAddress());
					ps.setString(4, mailServer.getHost());
					ps.setInt(5, commmonDao.convertToInt(mailServer.getPort()));
					ps.setString(6, KoteConstants.ACTIVE);
					ps.setString(7, util.getOrgID());
					ps.setTimestamp(8, commmonDao.getTimeStamp());
					ps.setTimestamp(9, commmonDao.getTimeStamp());
					ps.setString(10, util.getuserID());
					
					util.print("mst_mail_server == "+ps.toString());
					result = ps.executeUpdate();
				}
			}
			
			if(result > 0)
				taskDao.saveTaskControl(CLModuleTaskControlUtil.MAILSERVER,"Completed",sess);
			else
				taskDao.saveTaskControl(CLModuleTaskControlUtil.MAILSERVER,"Failed",sess);
			
		} catch (Exception e) {
               e.printStackTrace();
		}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(ps!=null) ps.close();
				
			}catch(Exception ex){}
			
		}
		return result;
	}


	@Override
	public int saveRoleManagementDetails(List<UseRoleDomain> requestDomain,HttpSession sess) {
		Connection conn = null;
		int result = 0;
		PreparedStatement ps = null;
		try {
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn = DBTransaction.openConnection();
			if (!requestDomain.isEmpty()) {
				for (UseRoleDomain roleDoamin : requestDomain) {
					String sql = "Insert into mst_roles(role_id, role_desc, role_status, role_type, organization_id, created_datetime, last_updated_datetime,mod_user_id) values (?,?,?,?,?,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, roleDoamin.getRoleId());
					ps.setString(2, roleDoamin.getDescription());
					ps.setString(3, KoteConstants.ACTIVE);
					ps.setString(4, roleDoamin.getType());
					ps.setString(5, util.getOrgID());
					ps.setTimestamp(6, commmonDao.getTimeStamp());
					ps.setTimestamp(7, commmonDao.getTimeStamp());
					ps.setString(8, util.getuserID());
					
					util.print("mst_roles == "+ps.toString());
					result = ps.executeUpdate();
				}
			}
			
			if(result > 0)
				taskDao.saveTaskControl(CLModuleTaskControlUtil.ROLE,"Completed",sess);
			else
				taskDao.saveTaskControl(CLModuleTaskControlUtil.ROLE,"Failed",sess);
			
		} catch (Exception e) {
               e.printStackTrace();
		}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(ps!=null) ps.close();
				
			}catch(Exception ex){}
			
		}
		return result;
	}


	@Override
	public int saveRoleMapDetails(List<UseRoleMapDomain> requestDomain,HttpSession sess) {
		Connection conn = null;
		int result = 0;
		PreparedStatement ps = null;
		try {
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			LookupInterface lookup=new LookupDao();
			List<UsersInfoDomain> AllUsersList=lookup.getAllUsersList(sess);
			
			conn = DBTransaction.openConnection();
			if (!requestDomain.isEmpty()) {
				for (UseRoleMapDomain roleMapDoamin : requestDomain) {
					String sql = "Insert into mst_user_role_map(user_id, role_id, map_status, organization_id, created_datetime, last_updated_datetime,mod_user_id) values (?,?,?,?,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, roleMapDoamin.getUserId());
					ps.setString(2, roleMapDoamin.getRoleId());
					ps.setString(3, KoteConstants.ACTIVE);
					ps.setString(4, util.getOrgID());
					ps.setTimestamp(5, commmonDao.getTimeStamp());
					ps.setTimestamp(6, commmonDao.getTimeStamp());
					ps.setString(7, util.getuserID());
					
					util.print("mst_user_role_map == "+ps.toString());
					result = ps.executeUpdate();
					ps.close();
					
					if(result == 1 )
					{
						sql="select role_type from mst_roles where role_id=? and organization_id=?";
						ps=conn.prepareStatement(sql);
						ps.setString(1, roleMapDoamin.getRoleId());
						ps.setString(2, util.getOrgID());
						util.print("select role_type "+ps.toString());
						ResultSet rs=ps.executeQuery();
						String privilege="";
						while(rs.next())
						{
							privilege=rs.getString("role_type");
							
							sql="update users_info set privilege=? where user_id=?";
							ps=conn.prepareStatement(sql);
							ps.setString(1, privilege);
							ps.setString(2, roleMapDoamin.getUserId());
							util.print("change privilege "+ps.toString());
							ps.executeUpdate();
							
						}
						rs.close();
						
						//need to send mail here
						try
						{
						UsersInfoDomain row=getMailidForUser(AllUsersList, roleMapDoamin.getUserId());
						String subject=PropertiesUtil.getMessageProperty("rolemap.mailsubject");
						String Msg=PropertiesUtil.getMessageProperty("rolemap.mailbody");
						if(Msg.contains("<User>"))
							Msg=Msg.replace("<User>", row.getUserName());
						if(Msg.contains("<Role>"))
							Msg=Msg.replace("<Role>", privilege);
						if(Msg.contains("<OrgName>"))
							Msg=Msg.replace("<OrgName>", util.getOrgName());
						
						
						String Footer=PropertiesUtil.getMessageProperty("rolemap.mailfooter");
						Msg=Msg+"<br> <br>"	+Footer;
						Mailer.send(row.getEmail(), subject, Msg,sess); 
						}catch(Exception ex){}
						
					}
				}
			}
			
			if(result > 0)
				taskDao.saveTaskControl(CLModuleTaskControlUtil.USERROLEMAP,"Completed",sess);
			else
				taskDao.saveTaskControl(CLModuleTaskControlUtil.USERROLEMAP,"Failed",sess);
			
		} catch (Exception e) {
               e.printStackTrace();
		}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(ps!=null) ps.close();
				
			}catch(Exception ex){}
			
		}
		return result;
	}

	public UsersInfoDomain getMailidForUser(List<UsersInfoDomain> AllUsersList,String UserId)
	{
		UsersInfoDomain ret=null;
		try
		{
			if(AllUsersList!=null && AllUsersList.size()>0)
			{
				for(int i=0;i<AllUsersList.size();i++)
				{
					UsersInfoDomain row=AllUsersList.get(i);
					if(row.getUserID().trim().equalsIgnoreCase(UserId))
						return row;
				}
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		return ret;
	}

	@Override
	public int saveRoleAccessDetails(List<UseAccessDomain> requestDomain,HttpSession sess) {
		
		
		saveMenusForNewOrganization(sess);
		
		Connection conn = null;
		int result = 0;
		PreparedStatement ps = null;
		try {
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn = DBTransaction.openConnection();
			if (!requestDomain.isEmpty()) {
				for (UseAccessDomain roleAccessDoamin : requestDomain) {
					
					String level1=roleAccessDoamin.getMenuNamelevel1();
					String level2=roleAccessDoamin.getMenuNamelevel2();
					String level3=roleAccessDoamin.getMenuNamelevel3();
					
					String menuId = getMenuId(level1,sess);
					String sql = "Insert into mst_role_menu_map(organization_id, role_id, menu_id, access_rights, map_status, created_datetime, last_updated_datetime,mod_user_id) values (?,?,?,?,?,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, util.getOrgID());
					ps.setString(2, roleAccessDoamin.getRoleId());
					ps.setString(3, menuId);
					ps.setString(4, "IAUD");
					ps.setString(5, KoteConstants.ACTIVE);
					ps.setTimestamp(6, commmonDao.getTimeStamp());
					ps.setTimestamp(7, commmonDao.getTimeStamp());
					ps.setString(8, util.getuserID());
					util.print("mst_role_menu_map == "+ps.toString());
					if(!checkmst_role_menu_map(util.getOrgID(),roleAccessDoamin.getRoleId(),menuId,util))				
						result = ps.executeUpdate();
					ps.close();
					
					if(level2!=null && level2.trim().length()>0)
					{
						menuId = getMenuId(level2,sess);
						sql = "Insert into mst_role_menu_map(organization_id, role_id, menu_id, access_rights, map_status, created_datetime, last_updated_datetime,mod_user_id) values (?,?,?,?,?,?,?,?)";
						ps = conn.prepareStatement(sql);
						ps.setString(1, util.getOrgID());
						ps.setString(2, roleAccessDoamin.getRoleId());
						ps.setString(3, menuId);
						ps.setString(4, "IAUD");
						ps.setString(5, KoteConstants.ACTIVE);
						ps.setTimestamp(6, commmonDao.getTimeStamp());
						ps.setTimestamp(7, commmonDao.getTimeStamp());
						ps.setString(8, util.getuserID());
						util.print("mst_role_menu_map == "+ps.toString());
						if(!checkmst_role_menu_map(util.getOrgID(),roleAccessDoamin.getRoleId(),menuId,util))
							result = ps.executeUpdate();
						ps.close();
					}
					
					if(level3!=null && level3.trim().length()>0)
					{
						menuId = getMenuId(level3,sess);
						sql = "Insert into mst_role_menu_map(organization_id, role_id, menu_id, access_rights, map_status, created_datetime, last_updated_datetime,mod_user_id) values (?,?,?,?,?,?,?,?)";
						ps = conn.prepareStatement(sql);
						ps.setString(1, util.getOrgID());
						ps.setString(2, roleAccessDoamin.getRoleId());
						ps.setString(3, menuId);
						ps.setString(4, "IAUD");
						ps.setString(5, KoteConstants.ACTIVE);
						ps.setTimestamp(6, commmonDao.getTimeStamp());
						ps.setTimestamp(7, commmonDao.getTimeStamp());
						ps.setString(8, util.getuserID());
						util.print("mst_role_menu_map == "+ps.toString());
						if(!checkmst_role_menu_map(util.getOrgID(),roleAccessDoamin.getRoleId(),menuId,util))
							result = ps.executeUpdate();
					}
					
				}
			}
			
			if(result > 0)
				taskDao.saveTaskControl(CLModuleTaskControlUtil.ACCESSRIGHTS,"Completed",sess);
			else
				taskDao.saveTaskControl(CLModuleTaskControlUtil.ACCESSRIGHTS,"Failed",sess);
			
		} catch (Exception e) {
               e.printStackTrace();
		}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(ps!=null) ps.close();
				
			}catch(Exception ex){}
			
		}
		return result;
	}

	boolean checkmst_role_menu_map(String organization_id, String role_id, String menu_id, SessionUtil util)
	{
		Connection conn = null;
		int result = 0;
		PreparedStatement ps = null;
		try
		{
			conn = DBTransaction.openConnection();
			String sql="select * from mst_role_menu_map where organization_id=? and role_id=? and menu_id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, organization_id);
			ps.setString(2, role_id);
			ps.setString(3, menu_id);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				return true;				
			}
		}catch(Exception ex){ex.printStackTrace(); util.print(""+ex.getMessage());}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(ps!=null) ps.close();
				
			}catch(Exception ex){}
			
		}
		return false;
	}
	private String getMenuId(String menuName,HttpSession sess) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String menuId = null;
		try {
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn = DBTransaction.openConnection();
			String query = "select menu_id from mst_menus where menu_name=? and organization_id=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, menuName);
			ps.setString(2, util.getOrgID());
			
			util.print("getMenuId == "+ps.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				menuId = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try
			{
				if(conn!=null) conn.close();
				if(ps!=null) ps.close();
				
			}catch(Exception ex){}
			
		}
		return menuId;
	}


	private List<MenuDomain> saveMenusForNewOrganization(HttpSession sess) {
		
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<MenuDomain> menuList = null;
		try {
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn = DBTransaction.openConnection();
			String query = "select * from mst_menus where organization_id=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, "OwnKesInd");
			util.print("saveMenusForNewOrganization == "+ps.toString());
			
			rs = ps.executeQuery();
			menuList = new ArrayList<MenuDomain>();
			while (rs.next()) {
				MenuDomain domain = new MenuDomain();
				domain.setModuleId(rs.getString(2));
				domain.setMenuLevel(rs.getString(3));
				domain.setParentMenuId(rs.getString(4));
				domain.setMenuName(rs.getString(5));
				domain.setMenuSequence(rs.getString(6));
				domain.setMenuScrTitle(rs.getString(7));
				domain.setMenuUrl(rs.getString(8));
				domain.setWfItemId(rs.getString(9));
				domain.setStatus(rs.getString(10));
				menuList.add(domain);
			}
			addMenuForOrganization(menuList,sess);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBTransaction.closeConnection(conn, ps, rs);
		}
		return menuList;
	}
	
	private int addMenuForOrganization(List<MenuDomain> menuList,HttpSession sess) {
		
		Connection conn = null;
		int result = 0;
		PreparedStatement ps = null;
		try {
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn = DBTransaction.openConnection();
			if (!menuList.isEmpty()) {
				for(MenuDomain domain : menuList) {
					String msgId=commmonDao.getSeqVal("menuid","menuid",sess);
					String sql = "Insert into mst_menus(menu_id, module_id, menu_level, parent_menu_id, menu_name, menu_sequence, menu_scr_title, menu_url, wf_item_id, status, organization_id, created_datetime, last_updated_datetime,mod_user_id)"
							+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, msgId);
					ps.setString(2, domain.getModuleId());
					ps.setInt(3, commmonDao.convertToInt(domain.getMenuLevel()));
					ps.setString(4, domain.getParentMenuId());
					ps.setString(5, domain.getMenuName());
					ps.setInt(6, commmonDao.convertToInt(domain.getMenuSequence()));
					ps.setString(7, domain.getMenuScrTitle());
					ps.setString(8, domain.getMenuUrl());
					ps.setString(9, domain.getWfItemId());
					ps.setString(10, domain.getStatus());
					ps.setString(11, util.getOrgID());
					ps.setTimestamp(12, commmonDao.getTimeStamp());
					ps.setTimestamp(13, commmonDao.getTimeStamp());
					ps.setString(14, util.getuserID());
					
					util.print("mst_menus == "+ps.toString());
					result = ps.executeUpdate();
				}
			}
			
		} catch (Exception e) {
               e.printStackTrace();
		}finally{
			try
			{
				if(conn!=null) conn.close();
				if(ps!=null) ps.close();
				
			}catch(Exception ex){}
			
		}

		return result;
	}


	@Override
	public int saveUserNotificationDetails(List<UseNotificationDomain> requestDomain,HttpSession sess) {
		Connection conn = null;
		int result = 0;
		PreparedStatement ps = null;
		try {
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn = DBTransaction.openConnection();
			if (!requestDomain.isEmpty()) {
				for (UseNotificationDomain notificationDomain : requestDomain) {
					String msgId=commmonDao.getSeqVal("msgid","msg",sess);
					String sql = "Insert into mst_notification_template(msg_template_id, msg_template_desc, msg_category, msg_subject, header_text, body_text, footer_text, msg_sender_userid, msg_template_status, organization_id, created_datetime, last_updated_datetime,mod_user_id) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, msgId);
					ps.setString(2, notificationDomain.getNotificationType());
					ps.setString(3, KoteConstants.UNT);
					ps.setString(4, notificationDomain.getSubject());
					ps.setString(5, notificationDomain.getHeader());
					ps.setString(6, notificationDomain.getBody());
					ps.setString(7, notificationDomain.getFooter());
					ps.setString(8, notificationDomain.getUidSender());
					ps.setString(9, KoteConstants.ACTIVE);
					ps.setString(10, util.getOrgID());
					ps.setTimestamp(11, commmonDao.getTimeStamp());
					ps.setTimestamp(12, commmonDao.getTimeStamp());
					ps.setString(13, util.getuserID());
					
					util.print("mst_notification_template == "+ps.toString());
					result = ps.executeUpdate();
				}
			}
			
			if(result > 0)
				{
					taskDao.saveTaskControl(CLModuleTaskControlUtil.UNT,"Completed",sess);
					moduleDao.saveModuleControl("USE", "Completed",sess);
				}
			else
				{
					taskDao.saveTaskControl(CLModuleTaskControlUtil.UNT,"Failed",sess);
					moduleDao.saveModuleControl("USE", "Failed",sess);
				}
			
		} catch (Exception e) {
               e.printStackTrace();
		}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(ps!=null) ps.close();
				
			}catch(Exception ex){}
			
		}
		return result;
	}


	@Override
	public int saveUserRegistration(List<UseUserDomain> requestDomain,HttpSession sess) {
		// TODO Auto-generated method stub
		
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			conn = DBTransaction.openConnection();
			if (!requestDomain.isEmpty()) {
				
				requestDomain=checkAndGenUID(requestDomain,sess);
				
				for (UseUserDomain row : requestDomain) {
					
				String sql="INSERT INTO users_info(username, user_id, email, address, contact_no, organization_id, created_by, country, state, city, postal_code, users_status,"
						+ " gender, dateofbirth, current_date_time, designation, department, landline_number, landline_extn_no, email_pwd, server_id, space_allotment, "
						+ "last_updated_datetime, user_status, last_name, space_uom, password, privilege )"
						+ "    VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				String pwd=""+GenerateRandomPwd.getRandomPwd();
				pwd=Encryption.encrypt(pwd);
				ps=conn.prepareStatement(sql);
				ps.setString(1, row.getFirstName());
				ps.setString(2, row.getUserId());
				ps.setString(3, row.getEmail());
				ps.setString(4, row.getAddress());
				ps.setString(5, row.getMobile());
				ps.setString(6, util.getOrgID());
				ps.setString(7, util.getuserID());
				ps.setString(8, row.getCountry());
				ps.setString(9, row.getState());
				ps.setString(10, row.getCity());
				ps.setString(11, row.getPostal());
				ps.setString(12, "Created");
				ps.setString(13, row.getGender());
				ps.setDate(14, commmonDao.getDatebySlash(row.getDob(),util));
				ps.setString(15, ""+commmonDao.getTimeStamp());
				ps.setString(16, row.getDesignation());
				ps.setString(17, row.getDepartment());
				ps.setDouble(18, commmonDao.convertToDouble(row.getLandline()));
				ps.setInt(19, commmonDao.convertToInt(row.getExtension()));
				ps.setString(20, "");
				ps.setString(21, row.getMailServer());
				ps.setInt(22, commmonDao.convertToInt(row.getSpace()));
				ps.setTimestamp(23, commmonDao.getTimeStamp());
				ps.setString(24, "Created");
				ps.setString(25, row.getLastName());
				ps.setString(26, row.getUom());
				ps.setString(27, pwd);
				ps.setString(28, " ");
				util.print("users_info == "+ps.toString());
				result=ps.executeUpdate();
				
				if(result==1)
				{
					pwd=Encryption.decrypt(pwd);
					//send mail from here
					if(row.getEmail().trim().length()>0)
					{
						String subject=PropertiesUtil.getMessageProperty("urem.mailsubject");
						String Msg=PropertiesUtil.getMessageProperty("urem.mailbody");
						if(Msg.contains("<User>"))
						{
							Msg=Msg.replace("<User>", row.getFirstName()+" "+row.getLastName());
						}
						String Link=PropertiesUtil.getMessageProperty("urem.link");
						String uName=PropertiesUtil.getMessageProperty("urem.username")+row.getUserId();
						String uPwd=PropertiesUtil.getMessageProperty("urem.pwd")+pwd;
						String Footer=PropertiesUtil.getMessageProperty("urem.mailfooter");
						
						Msg=Msg+"<br> <br>"
								+uName +"<br> "
								+uPwd +"<br> <br>"
								+Link +"<br> <br>"
								+Footer;
						try
						{
							Mailer.send(row.getEmail(), subject, Msg,sess); 
						}catch(Exception ex){}
						
						
					}
				}
				saveKnowStoreSpace(row,sess);
				}
				
				if(result == 1)
					taskDao.saveTaskControl(CLModuleTaskControlUtil.USER,"Completed",sess);
				else
					taskDao.saveTaskControl(CLModuleTaskControlUtil.USER,"Failed",sess);
				
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(ps!=null) ps.close();
				if(rs!=null)rs.close();
			}catch(Exception ex){}
			
		}
		return result;
	}
	
	public void saveKnowStoreSpace(UseUserDomain row,HttpSession sess)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn = DBTransaction.openConnection();
			if(row.getSpace()!=null && row.getSpace().length()>0)
				if(row.getUom()!=null && row.getUom().length()>0)
				{
					String ts_space_id="";
					String sql="select ts_space_id from knowledgestore_teachingsource_info where organization_id= ?";
					ps=conn.prepareStatement(sql);
					ps.setString(1, util.getOrgID());
					rs=ps.executeQuery();
					while(rs.next())
					{
						ts_space_id=rs.getString("ts_space_id");
					}
					String ks_id=commmonDao.getSeqVal("ks_id", "KS",sess);
					rs.close();
					ps.close();
					
					sql=" INSERT INTO knowledgestore_info( ksid, organization_id, user_id, created_by, knowledge_store_space, status, space_uom, ts_space_id) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ? )";
					ps=conn.prepareStatement(sql);
					ps.setString(1, ks_id);
					ps.setString(2, util.getOrgID());
					ps.setString(3, row.getUserId());
					ps.setString(4, util.getuserID());
					ps.setInt(5, commmonDao.convertToInt(row.getSpace()));
					ps.setString(6, KoteConstants.ACTIVE);
					ps.setString(7, row.getUom().toUpperCase());
					ps.setString(8, ts_space_id);
					
					ps.executeUpdate();
				}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(ps!=null) ps.close();
				if(rs!=null)rs.close();
			}catch(Exception ex){}
			
		}
	}

	List<UseUserDomain> checkAndGenUID( List<UseUserDomain> ret,HttpSession sess)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn = DBTransaction.openConnection();
			
			String del="delete from mst_cfl_usermapping where organization_id=?";
			ps=conn.prepareStatement(del);
			ps.setString(1, util.getOrgID());
			util.print("delete :: "+ps.toString());
			ps.executeUpdate();
			ps.close();
			
			if(ret!=null && ret.size()>0)
			{
				
				String uidgen="";
				String sql="SELECT param_value FROM system_parameters where param_key='uid_generation' and organization_id=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, util.getOrgID());
				util.print("uid_generation == "+ps.toString());
				rs=ps.executeQuery();
				while(rs.next())
				{
					uidgen=rs.getString("param_value").toString().trim();
				}
				rs.close();
				ps.close();
				
				if(uidgen.trim().equalsIgnoreCase("Auto Generate"))
				{
					String OrgType=util.getTeachingSource();
					
					
					String fromorgtype=OrgType.substring(0, 3);
					if(OrgType.trim().equalsIgnoreCase("Professionals"))
						fromorgtype=OrgType.substring(0, 4);
					
					
					for(int i=0;i<ret.size();i++)
					{
						UseUserDomain row=ret.get(i);
						
						String AutoID="";
						String fname=row.getFirstName();
						String lname=row.getLastName();
						String rndno=CommonDao.getRandom4Digit();
						
						if(fname.length()>3 && lname.length()>3)
						{
							AutoID=fname.substring(0, 1)+lname.substring(0, 1)+fromorgtype+rndno;
						}else if(fname.length()>3 && lname.length()==0)
						{
							AutoID=fname.substring(0, 3)+fromorgtype+rndno;
						}else if(fname.length()<=3 && lname.length()>3)
						{
							AutoID=fname+lname.substring(0, 3)+fromorgtype+rndno;
						}else if(fname.length()>3 && lname.length()<=3)
						{
							AutoID=fname.substring(0, 3)+lname+fromorgtype+rndno;
						}
						
						util.print("AutoID == "+AutoID);
						insertInToMapping(row.getUserId(),AutoID,sess);
						row.setUserId(AutoID);
						ret.set(i, row);
					}
					
				}else
				{
					String uiprefix="";
					sql="SELECT param_value FROM system_parameters where param_key='uid_prefix' and organization_id=?";
					ps=conn.prepareStatement(sql);
					ps.setString(1, util.getOrgID());
					util.print("uid_prefix == "+ps.toString());
					rs=ps.executeQuery();
					while(rs.next())
					{
						uiprefix=rs.getString("param_value");
					}
					rs.close();
					ps.close();
					
					
					
					for(int i=0;i<ret.size();i++)
					{
						UseUserDomain row=ret.get(i);
						String uid=row.getUserId();
						String uidseq=commmonDao.getSeqVal("userid", "",sess);
						String manualUID=uiprefix+uid+uidseq;
						util.print("manualUID == "+manualUID);
						insertInToMapping(uid,manualUID,sess);
						row.setUserId(manualUID);
						ret.set(i, row);
					}
				}
				
			}
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(ps!=null) ps.close();
				if(rs!=null)rs.close();
			}catch(Exception ex){}
			
		}
		return ret;
	}
	
	void insertInToMapping(String userid_by_ts,String userid_by_cfl,HttpSession sess)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn = DBTransaction.openConnection();
			String sql="INSERT INTO mst_cfl_usermapping( userid_by_ts, userid_by_cfl, organization_id) VALUES (?, ?, ?);";
			ps=conn.prepareStatement(sql);
			ps.setString(1, userid_by_ts);
			ps.setString(2, userid_by_cfl);
			ps.setString(3, util.getOrgID());
			util.print("insertInToMapping == "+ps.toString());
			ps.executeUpdate();
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(ps!=null) ps.close();
			}catch(Exception ex){}
			
		}
	}
}
