package com.kes.kote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.kes.kote.dbconnection.DBTransaction;
import com.kes.kote.domain.FiseCourseFeeMapDomain;
import com.kes.kote.domain.FiseFCSDomain;
import com.kes.kote.domain.FiseFPMDomain;
import com.kes.kote.domain.FiseFeeMapDomain;
import com.kes.kote.domain.FiseMemberFeesDomain;
import com.kes.kote.util.CLModuleTaskControlUtil;
import com.kes.kote.util.SessionUtil;

public class FiseDao {
	
	TaskControlDao taskDao=new TaskControlDao();
	ModuleControlDao moduleDao=new ModuleControlDao();
	CommonDao commmonDao=new CommonDao();
	
	public int saveFeeMapDetails(List<FiseFeeMapDomain> feeMapDetails,HttpSession sess) {
		int ret=0;
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			
			if(feeMapDetails!=null && feeMapDetails.size()>0)
			{
				for(int i=0;i<feeMapDetails.size();i++)
				{
					FiseFeeMapDomain row=feeMapDetails.get(i);
					String sql="INSERT INTO mst_mltsource_x_fee_components(mlt_id, fee_code, mlt_fee_code_prefix, mlt_fee_code_body, mlt_fee_code_suffix, mlt_fee_name, mlt_fee_code, creation_date, last_update, user_id, status)"
							+ "    VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?) ";
					
					st=conn.prepareStatement(sql);
					st.setString(1, util.getOrgID());
					st.setString(2, row.getFeeCode());
					st.setString(3, row.getPrefix());
					st.setString(4, row.getBody());
					st.setString(5, row.getSuffix());
					st.setString(6, row.getInstFeeDesc());
					st.setString(7, row.getInstFeeCode());
					st.setTimestamp(8, commmonDao.getTimeStamp());
					st.setTimestamp(9, commmonDao.getTimeStamp());
					st.setString(10, util.getuserID());
					st.setString(11, "Active");
					util.print("sql== "+st.toString());
					ret=st.executeUpdate();
					
				}
			}
			
			if(ret==1)
				taskDao.saveTaskControl(CLModuleTaskControlUtil.FISEFEEMAP, "Completed",sess);
			else
				taskDao.saveTaskControl(CLModuleTaskControlUtil.FISEFEEMAP, "Failed",sess);
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try {
					if(conn!=null)	conn.close();
					if(st!=null)	st.close();
					if(result!=null)	result.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return ret;
	}
	
	public int saveCrsFeeMapDetails(List<FiseCourseFeeMapDomain> CourseFeeMapDetails,HttpSession sess) {
		int ret=0;
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			if(CourseFeeMapDetails!=null && CourseFeeMapDetails.size()>0)
			{
				for(int i=0;i<CourseFeeMapDetails.size();i++)
				{
					FiseCourseFeeMapDomain row=CourseFeeMapDetails.get(i);
					
					String course_discription_id="";
					
					String sql="SELECT course_discription_id FROM transaction_course_description_info where course_id_declared_by_teaching_source=? and middle_layer_teaching_source_id in (select middle_layer_teaching_source_id from transaction_middle_layer_teaching_source where organization_id=?)";
					st=conn.prepareStatement(sql);
					st.setString(1, row.getDeptId());
					st.setString(2, util.getOrgID());
					result=st.executeQuery();
					util.print("sql == "+st.toString());
					while(result.next())
					{
						course_discription_id=result.getString("course_discription_id").toString().trim();
					}
					result.close();
					st.close();
					
					sql="INSERT INTO mst_mltsource_fees_x_mltsource_course( mlt_id, mlt_course, fee_sequence, mlt_fee_code, mlt_course_fee_code, grouped_feecode, creation_date, last_update, user_id, status, fee_amount, fee_type, recurring_frequency)"
							+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					st=conn.prepareStatement(sql);
					st.setString(1, util.getOrgID());
					st.setString(2, course_discription_id);
					st.setInt(3, row.getFeeSequence());
					st.setString(4, row.getInstFeeId());
					st.setString(5, row.getCrsFeeId().trim());
					st.setString(6, row.getGroup());
					st.setTimestamp(7, commmonDao.getTimeStamp());
					st.setTimestamp(8, commmonDao.getTimeStamp());
					st.setString(9, util.getuserID());
					st.setString(10, "Active");
					st.setDouble(11, row.getFeeAmount());
					st.setString(12, row.getFeeType());
					st.setInt(13, row.getFeeRecurring());
					
					util.print("sql == "+st.toString());
					
					ret=st.executeUpdate();
				}
			}
			
			if(ret==1)
				taskDao.saveTaskControl(CLModuleTaskControlUtil.FISECOURSEFEEMAP, "Completed",sess);
			else
				taskDao.saveTaskControl(CLModuleTaskControlUtil.FISECOURSEFEEMAP, "Failed",sess);
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try {
					if(conn!=null)	conn.close();
					if(st!=null)	st.close();
					if(result!=null)	result.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return ret;
	}
	
	public int saveMemberFeeDetails(List<FiseMemberFeesDomain> MemberFeesdetails,HttpSession sess) {
		int ret=0;
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			conn=DBTransaction.openConnection();
			if(MemberFeesdetails!=null && MemberFeesdetails.size()>0)
			{
				for(int i=0;i<MemberFeesdetails.size();i++)
				{
					FiseMemberFeesDomain row= MemberFeesdetails.get(i);
					
									
					String sql="INSERT INTO mst_mltsource_fees_group(mlt_course_fee_code, mlt_fee_code, group_sequence, member_fee_value, member_fee_value_type, creation_date, last_update, user_id, status, mlt_id)"
							+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					st=conn.prepareStatement(sql);
					st.setString(1, row.getGroupFeeId());
					st.setString(2, row.getMemberFeeId());
					st.setInt(3, row.getGroupSeq());
					st.setDouble(4, row.getMemberFeeVal());
					st.setString(5, row.getMemberFeeType());
					st.setTimestamp(6, commmonDao.getTimeStamp());
					st.setTimestamp(7, commmonDao.getTimeStamp());
					st.setString(8, util.getuserID());
					st.setString(9, "Active");
					st.setString(10, util.getOrgID());
					util.print("sql == "+st.toString());
					ret=st.executeUpdate();
				}
			}
			
			if(ret==1)
				taskDao.saveTaskControl(CLModuleTaskControlUtil.FISEMEMBERFEES, "Completed",sess);
			else
				taskDao.saveTaskControl(CLModuleTaskControlUtil.FISEMEMBERFEES, "Failed",sess);
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try {
					if(conn!=null)	conn.close();
					if(st!=null)	st.close();
					if(result!=null)	result.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return ret;
	}
	
	public int saveFPMDetails(List<FiseFPMDomain> FPMDetails,HttpSession sess) {
		int ret=0;
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			if(FPMDetails!=null && FPMDetails.size()>0)
			{
				for(int i=0;i<FPMDetails.size();i++)
				{
					FiseFPMDomain row=FPMDetails.get(i);
					
					String sql="INSERT INTO payment_gateways(gateway_id, gateway_name, gateway_url, gateway_params, gateway_status, organization_id, created_datetime, last_updated_datetime, mod_user_id)"
							+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					st=conn.prepareStatement(sql);
					st.setString(1, row.getGatewayId());
					st.setString(2, row.getGatewayName());
					st.setString(3, row.getGatewayURL());
					st.setString(4, row.getParameterList());
					st.setString(5, "Active");
					st.setString(6, util.getOrgID());
					st.setTimestamp(7, commmonDao.getTimeStamp());
					st.setTimestamp(8, commmonDao.getTimeStamp());
					st.setString(9, util.getuserID());
					util.print("sql == "+st.toString());
					ret=st.executeUpdate();
				}
			}
			
			if(ret==1)
				taskDao.saveTaskControl(CLModuleTaskControlUtil.FISEFPM, "Completed",sess);
			else
				taskDao.saveTaskControl(CLModuleTaskControlUtil.FISEFPM, "Failed",sess);
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try {
					if(conn!=null)	conn.close();
					if(st!=null)	st.close();
					if(result!=null)	result.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return ret;
	}
	
	public int saveFCSDetails(List<FiseFCSDomain> FCSDetails,HttpSession sess) {
		int ret=0;
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			conn=DBTransaction.openConnection();
			
			if(FCSDetails!=null && FCSDetails.size()>0)
			{
				for(int i=0;i<FCSDetails.size();i++)
				{
					FiseFCSDomain row=FCSDetails.get(i);
					
					String cateId=checkCategory(row.getCategory(),sess);
					if(cateId.length()==0)
						return 0;
					
					String subCatId=checkSubCategory(cateId,row.getSubCategory(),sess);
					if(subCatId.length()==0)
						return 0;
					
					String cmsId=commmonDao.getSeqVal("cmsid", "cmsid",sess);
						
					String sql="INSERT INTO collection_management( cms_id, category_code, sub_category_code, accept_indicator, cms_status, organization_id, created_datetime, last_updated_datetime, mod_user_id)"
								+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
					st=conn.prepareStatement(sql);
					st.setString(1, cmsId);
					st.setString(2, cateId);
					st.setString(3, subCatId);
					st.setString(4, row.getAssign());
					st.setString(5, "Active");
					st.setString(6, util.getOrgID());
					st.setTimestamp(7, commmonDao.getTimeStamp());
					st.setTimestamp(8, commmonDao.getTimeStamp());
					st.setString(9, util.getuserID());
					
					util.print("sql == "+st.toString());
					ret=st.executeUpdate();
					
				}
			}
			
			if(ret==1)
				{
					taskDao.saveTaskControl(CLModuleTaskControlUtil.FISEFCS, "Completed",sess);
					moduleDao.saveModuleControl("FISE", "Completed", sess);
				}
			else
				{
				taskDao.saveTaskControl(CLModuleTaskControlUtil.FISEFCS, "Failed",sess);
				moduleDao.saveModuleControl("FISE", "Failed", sess);
				}
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try {
					if(conn!=null)	conn.close();
					if(st!=null)	st.close();
					if(result!=null)	result.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return ret;
	}
	
	String checkCategory(String Category,HttpSession sess)
	{
		String ret="";
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			String sql="SELECT category_code FROM cms_category where category_description=? and organization_id= ?";
			st=conn.prepareStatement(sql);
			st.setString(1, Category);
			st.setString(2, util.getOrgID());
			util.print("sql == "+st.toString());
			result=st.executeQuery();
			while(result.next())
			{
				ret=result.getString("category_code").toString().trim();
			}
			st.close();
			result.close();
			
			if(ret.trim().length()==0)
			{
				String cmcatID=commmonDao.getSeqVal("cmscat", "cmscat",sess);
				sql="INSERT INTO cms_category( category_code, category_description, cat_status, organization_id,  created_datetime, last_updated_datetime, mod_user_id)"
						+ " VALUES ( ?, ?, ?, ?, ?, ?, ?) ";
				st=conn.prepareStatement(sql);
				st.setString(1, cmcatID);
				st.setString(2, Category);
				st.setString(3, "Active");
				st.setString(4, util.getOrgID());
				st.setTimestamp(5, commmonDao.getTimeStamp());
				st.setTimestamp(6, commmonDao.getTimeStamp());
				st.setString(7, util.getuserID());
				util.print("sql == "+st.toString());
				int status=st.executeUpdate();
				st.close();
				
				if(status==1)
					ret=cmcatID;
			}
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try {
					if(conn!=null)	conn.close();
					if(st!=null)	st.close();
					if(result!=null)	result.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return ret;
	}
	
	String checkSubCategory(String categoryId,String SubCategory,HttpSession sess)
	{
		String ret="";
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			String sql="SELECT sub_category_code FROM cms_sub_category where category_code=? and sub_cat_description=? and organization_id= ?";
			st=conn.prepareStatement(sql);
			st.setString(1, categoryId);
			st.setString(2, SubCategory);
			st.setString(3, util.getOrgID());
			util.print("sql == "+st.toString());
			result=st.executeQuery();
			while(result.next())
			{
				ret=result.getString("sub_category_code").toString().trim();
			}
			st.close();
			result.close();
			
			if(ret.trim().length()==0)
			{
				String SubcmcatID=commmonDao.getSeqVal("cmssubcat", "cmssubcat",sess);
				sql="INSERT INTO cms_sub_category( sub_category_code, category_code, sub_cat_description, sub_cat_status, organization_id, created_datetime, last_updated_datetime, mod_user_id )"
						+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?) ";
				st=conn.prepareStatement(sql);
				st.setString(1, SubcmcatID);
				st.setString(2, categoryId);
				st.setString(3, SubCategory);
				st.setString(4, "Active");
				st.setString(5, util.getOrgID());
				st.setTimestamp(6, commmonDao.getTimeStamp());
				st.setTimestamp(7, commmonDao.getTimeStamp());
				st.setString(8, util.getuserID());
				util.print("cms_sub_category == "+st.toString());
				int status=st.executeUpdate();
				st.close();
				
				if(status==1)
					ret=SubcmcatID;
			}
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try {
					if(conn!=null)	conn.close();
					if(st!=null)	st.close();
					if(result!=null)	result.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return ret;
	}
}
