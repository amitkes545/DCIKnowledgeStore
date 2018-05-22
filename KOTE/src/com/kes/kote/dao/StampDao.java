package com.kes.kote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.kes.kote.dbconnection.DBTransaction;
import com.kes.kote.domain.StampCMTDomain;
import com.kes.kote.domain.StampEPWorkFlowDomain;
import com.kes.kote.domain.StampEnrollFormDomain;
import com.kes.kote.domain.StampNMTDomain;
import com.kes.kote.util.CLModuleTaskControlUtil;
import com.kes.kote.util.SessionUtil;

public class StampDao {
	
	TaskControlDao taskDao=new TaskControlDao();
	ModuleControlDao moduleDao=new ModuleControlDao();
	CommonDao commmonDao=new CommonDao();
	
	public int saveEPWrokFlow(ArrayList<StampEPWorkFlowDomain> EPWorkFlowDetails,HttpSession sess)
	{
		int ret=0;
		Connection conn=null;
		PreparedStatement st=null;
		try
		{
			HashMap<String,String> wfstage=new HashMap<String,String>();
			HashMap<String,String> wfitem=new HashMap<String,String>();
			
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			conn=DBTransaction.openConnection();
			
			if(EPWorkFlowDetails!=null && EPWorkFlowDetails.size()>0)
			{
				for(int i=0;i<EPWorkFlowDetails.size();i++)
				{
					StampEPWorkFlowDomain domain=EPWorkFlowDetails.get(i);
					
					String wfsID=commmonDao.getSeqVal("wfstage","wfs",sess);
					
					String sql="INSERT INTO work_flow_stages(wf_stage_id, wf_stage_desc, wf_stage_status, organization_id, created_datetime, last_updated_datetime, mod_user_id) "
							+ "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
					
					st=conn.prepareStatement(sql);
					st.setString(1, wfsID);
					st.setString(2, domain.getStageName());
					st.setString(3, "Active");
					st.setString(4, util.getOrgID());
					st.setTimestamp(5, commmonDao.getTimeStamp());
					st.setTimestamp(6, commmonDao.getTimeStamp());
					st.setString(7, util.getuserID());
					util.print("saveEPWrokFlow stage : "+st.toString());
					ret=st.executeUpdate();
					
					wfstage.put(domain.getStageName(),wfsID);
					
				}
				st.close();
				
				HashMap<String,String> unqiuewfitem=new HashMap<String,String>(); 
				for(int i=0;i<EPWorkFlowDetails.size();i++)
				{
					StampEPWorkFlowDomain domain=EPWorkFlowDetails.get(i);
					
					if(!unqiuewfitem.containsKey(domain.getItemName()))
					{
						String wfiID=commmonDao.getSeqVal("wfitem","wfi",sess);
						
						String sql="INSERT INTO work_flow_items(wf_item_id, wf_item_desc, wf_item_status, organization_id, created_datetime,last_updated_datetime, mod_user_id) "
								+ "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
						
						st=conn.prepareStatement(sql);
						st.setString(1, wfiID);
						st.setString(2, domain.getItemName());
						st.setString(3, "Active");
						st.setString(4, util.getOrgID());
						st.setTimestamp(5, commmonDao.getTimeStamp());
						st.setTimestamp(6, commmonDao.getTimeStamp());
						st.setString(7, util.getuserID());
						util.print("saveEPWrokFlow item : "+st.toString());
						ret=st.executeUpdate();
						
						wfitem.put(domain.getItemName(),wfiID);
					}
				}
				st.close();
				
				//SessionUtil.print(""+wfitem.toString() +""+wfstage.toString());
				for(int i=0;i<EPWorkFlowDetails.size();i++)
				{
					StampEPWorkFlowDomain domain=EPWorkFlowDetails.get(i);
				
					String ItemName=domain.getItemName();
					String StageNae=domain.getStageName();
					
					
					String wfiID=wfitem.get(ItemName);
					String wfsID=wfstage.get(StageNae);
					String nxtwfsID=wfsID;
					if(i<EPWorkFlowDetails.size())
					{
						int cnt=i+1;
						if(EPWorkFlowDetails.size()==cnt)
							cnt=EPWorkFlowDetails.size()-1;
						
						StampEPWorkFlowDomain nxtdomain=EPWorkFlowDetails.get(cnt);
						String nxtStageName=nxtdomain.getStageName();
						nxtwfsID=wfstage.get(nxtStageName);
					}
					
					util.print(wfiID+""+wfsID+""+nxtwfsID);
					
					String sql="INSERT INTO wf_item_stage_map(wf_item_id, wf_stage_id, next_stage_id, wf_stage_approver_id, wf_stage_status, organization_id, created_datetime, last_updated_datetime, mod_user_id) "
							+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					
					st=conn.prepareStatement(sql);
					st.setString(1, wfiID);
					st.setString(2, wfsID);
					st.setString(3, nxtwfsID);
					st.setString(4, domain.getApprovedUsrID());
					st.setString(5, "Active");
					st.setString(6, util.getOrgID());
					st.setTimestamp(7, commmonDao.getTimeStamp());
					st.setTimestamp(8, commmonDao.getTimeStamp());
					st.setString(9, util.getuserID());
					util.print("saveEPWrokFlow mapping : "+st.toString());
					ret=st.executeUpdate();
				}
				
			}
			if(ret==1)
				taskDao.saveTaskControl(CLModuleTaskControlUtil.STAMPEFW,"Completed",sess);
			else
				taskDao.saveTaskControl(CLModuleTaskControlUtil.STAMPEFW,"Failed",sess);
			
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
	
	public int saveEnrollForm(ArrayList<StampEnrollFormDomain> enrollFormDetails,HttpSession sess)
	{
		int ret=0;
		Connection conn=null;
		PreparedStatement st=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			if(enrollFormDetails!=null && enrollFormDetails.size()>0)
			{
				for(int i=0;i<enrollFormDetails.size();i++)
				{
					StampEnrollFormDomain row=enrollFormDetails.get(i);
					if(row.getIsRequired().trim().equalsIgnoreCase("Yes"))
					{
						String sql="INSERT INTO instn_enrollment_attributes( organization_id, attribute_id, caption_id, group_no, group_title, attribute_sequence_in_group,"
								+ " tab_no, tab_title, attribute_sequence_in_tab, group_sequence_in_tab, grid_no, attribute_sequence_in_grid, screen_sequence, text_box_size, caption_font, caption_size, caption_color, caption_bold,"
								+ "	caption_italic, text_font, text_size, text_color, text_bold, text_italic, mandatory, enabled, created_datetime, last_updated_datetime, mod_user_id) "
								+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
						st=conn.prepareStatement(sql);
						st.setString(1, util.getOrgID());
						st.setString(2, row.getAttributeId());
						st.setString(3, row.getCaptionId());
						st.setInt(4, commmonDao.convertToInt(row.getGroupNo()));
						st.setString(5, row.getGroupTitle());
						st.setInt(6, commmonDao.convertToInt(row.getArrtibuteSeqinGrp()));
						st.setInt(7, commmonDao.convertToInt(row.getTabNo()));
						st.setString(8, row.getTabTitle());
						st.setInt(9, commmonDao.convertToInt(row.getAttributeSeqinTab()));
						st.setInt(10, commmonDao.convertToInt(row.getGroupSeqinTab()));
						st.setInt(11, commmonDao.convertToInt(row.getGridNo()));
						st.setInt(12, commmonDao.convertToInt(row.getAttributeSeqinGrid()));
						st.setInt(13, commmonDao.convertToInt(row.getSeqinScreen()));
						st.setInt(14, commmonDao.convertToInt(row.getTextBoxSize()));
						st.setString(15, row.getCaptionFont());
						st.setInt(16, commmonDao.convertToInt(row.getCaptionSize()));
						st.setString(17, row.getCaptionColor());
						st.setBoolean(18, row.getBoldedCaption().trim().equalsIgnoreCase("TRUE"));
						st.setBoolean(19, row.getItalicCaption().trim().equalsIgnoreCase("TRUE"));
						st.setString(20, row.getTextFont());
						st.setInt(21, commmonDao.convertToInt(row.getTextSize()));
						st.setString(22, row.getTextColor());
						st.setBoolean(23, row.getBoldedText().trim().equalsIgnoreCase("TRUE"));
						st.setBoolean(24, row.getItalicText().trim().equalsIgnoreCase("TRUE"));
						st.setBoolean(25, row.getIsMandatory().trim().equalsIgnoreCase("TRUE"));
						st.setBoolean(26, row.getIsEnabled().trim().equalsIgnoreCase("TRUE"));
						st.setTimestamp(27, commmonDao.getTimeStamp());
						st.setTimestamp(28, commmonDao.getTimeStamp());
						st.setString(29, util.getuserID());
						
						util.print("instn_enrollmnent_attributes == "+st.toString());
						ret=st.executeUpdate();
						
					}
				}
				
				if( ret==1)
					taskDao.saveTaskControl(CLModuleTaskControlUtil.STAMPENROLLFORM,"Completed",sess);
				else
					taskDao.saveTaskControl(CLModuleTaskControlUtil.STAMPENROLLFORM,"Failed",sess);
			}
		}catch(Exception ex){ex.printStackTrace();}
		
		return ret;
	}

	public int saveCMT(ArrayList<StampCMTDomain> CMTDetails,HttpSession sess) {
		Connection conn=null;
		PreparedStatement st=null;
		int ret=0;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			
			if(CMTDetails!=null && CMTDetails.size()>0)
			{
				for(int i=0;i<CMTDetails.size();i++)
				{
					StampCMTDomain domain =CMTDetails.get(i);
					
					String mtempID=commmonDao.getSeqVal("msgid","msg",sess);
					
					String sql="INSERT INTO mst_notification_template( msg_template_id, msg_template_desc, msg_category, msg_subject, header_text, body_text, footer_text, msg_sender_userid, msg_sender_pwd, msg_template_status, organization_id, created_datetime, last_updated_datetime, mod_user_id)"
							+ "    VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
					
					st=conn.prepareStatement(sql);
					st.setString(1, mtempID);
					st.setString(2, domain.getCommunicationType());
					st.setString(3, "ACT");
					st.setString(4, domain.getSubject());
					st.setString(5, domain.getHeader());
					st.setString(6, domain.getBody());
					st.setString(7, domain.getFooter());
					st.setString(8, domain.getUIDOfSender());
					st.setString(9, null);
					st.setString(10, "Active");
					st.setString(11, util.getOrgID());
					st.setTimestamp(12, commmonDao.getTimeStamp());
					st.setTimestamp(13, commmonDao.getTimeStamp());
					st.setString(14, util.getuserID());
					util.print("saveEPWrokFlow item : "+st.toString());
					 ret=st.executeUpdate();
					
				}
			}
			if( ret==1)
				taskDao.saveTaskControl(CLModuleTaskControlUtil.STAMPACT,"Completed",sess);
			else
				taskDao.saveTaskControl(CLModuleTaskControlUtil.STAMPACT,"Failed",sess);
			
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
		return  ret;
	}
	
	public int saveNMT(ArrayList<StampNMTDomain> NMTDetails,HttpSession sess) {
		Connection conn=null;
		PreparedStatement st=null;
		int ret=0;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			conn=DBTransaction.openConnection();
			
			if(NMTDetails!=null && NMTDetails.size()>0)
			{
				for(int i=0;i<NMTDetails.size();i++)
				{
					StampNMTDomain domain =NMTDetails.get(i);
					
					String mtempID=commmonDao.getSeqVal("msgid","msg",sess);
					
					String sql="INSERT INTO mst_notification_template( msg_template_id, msg_template_desc, msg_category, msg_subject, header_text, body_text, footer_text, msg_sender_userid, msg_sender_pwd, msg_template_status, organization_id, created_datetime, last_updated_datetime, mod_user_id)"
							+ "    VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
					
					st=conn.prepareStatement(sql);
					st.setString(1, mtempID);
					st.setString(2, domain.getNotificationType());
					st.setString(3, "ANT");
					st.setString(4, domain.getSubject());
					st.setString(5, domain.getHeader());
					st.setString(6, domain.getBody());
					st.setString(7, domain.getFooter());
					st.setString(8, domain.getUIDOfSender());
					st.setString(9, null);
					st.setString(10, "Active");
					st.setString(11, util.getOrgID());
					st.setTimestamp(12, commmonDao.getTimeStamp());
					st.setTimestamp(13, commmonDao.getTimeStamp());
					st.setString(14, util.getuserID());
					util.print("saveEPWrokFlow item : "+st.toString());
					ret=st.executeUpdate();
					
				}
			}
			
			if(ret==1)
			{
				taskDao.saveTaskControl(CLModuleTaskControlUtil.STAMPANT,"Completed",sess);
				moduleDao.saveModuleControl("STAMP","Completed",sess);	
			}else
			{
				taskDao.saveTaskControl(CLModuleTaskControlUtil.STAMPANT,"Failed",sess);
				moduleDao.saveModuleControl("STAMP","Failed",sess);
			}
			
			
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
}
