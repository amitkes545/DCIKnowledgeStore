package com.kes.kote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.kes.kote.dbconnection.DBTransaction;
import com.kes.kote.domain.CourseInfoDomain;
import com.kes.kote.domain.CrsFeeDomain;
import com.kes.kote.domain.DepartmetInfoDomain;
import com.kes.kote.domain.DisciplineDomain;
import com.kes.kote.domain.EnrAttrCaptionsDomain;
import com.kes.kote.domain.EnrAttributesDomain;
import com.kes.kote.domain.InstFeeDomain;
import com.kes.kote.domain.LetterGradeDomain;
import com.kes.kote.domain.MailserverDomain;
import com.kes.kote.domain.MenuDomain;
import com.kes.kote.domain.ProgramDomain;
import com.kes.kote.domain.SubTopicDomain;
import com.kes.kote.domain.SubjectDomain;
import com.kes.kote.domain.TopicDomain;
import com.kes.kote.domain.UseRoleDomain;
import com.kes.kote.domain.UsersInfoDomain;
import com.kes.kote.interfaces.LookupInterface;
import com.kes.kote.util.SessionUtil;

public class LookupDao implements LookupInterface{
	
	@Override
	public List<DisciplineDomain> getDesciplineList(HttpSession sess)
	{
		List<DisciplineDomain> ret=new ArrayList<DisciplineDomain>();
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");

			conn=DBTransaction.openConnection();
			String sql="SELECT discipline_id,discipline_name FROM transaction_discipline_info order by discipline_name";
			st=conn.prepareStatement(sql);
			util.print("getDesciplineList : "+st.toString());
			result=st.executeQuery();
			while(result.next())
			{
				String discipline_id=result.getString("discipline_id").toString().trim();
				String discipline_name=result.getString("discipline_name").toString().trim();
				
				DisciplineDomain row=new DisciplineDomain();
				row.setDisciplineID(discipline_id);
				row.setDisciplineName(discipline_name);
				ret.add(row);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();
			}catch(Exception ex){}
			
		}
		return ret;
	}
	
	@Override
	public List<ProgramDomain> getProgramList(HttpSession sess)
	{
		List<ProgramDomain> ret=new ArrayList<ProgramDomain>();
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");

			conn=DBTransaction.openConnection();
			String sql="SELECT distinct(program_name)  FROM transaction_program_info order by program_name";
			st=conn.prepareStatement(sql);
			util.print("getProgramList :: "+st.toString());
			result=st.executeQuery();
			while(result.next())
			{
				String program_name="";
				if(result.getObject("program_name")!=null)
					program_name=result.getString("program_name").toString().trim();
				
				ProgramDomain row=new ProgramDomain();
				row.setProgramName(program_name);
				if(program_name.length()>0)
					ret.add(row);
			}
			
			String[] prgList={"Bachelors","Masters","Doctorate","Post Doctoral","Research","Diploma","Post Graduate Diploma"};
			if(ret.size()>0)
			{
				for(int i=0;i<ret.size();i++)
				{
					ProgramDomain row=ret.get(i);
					String name=row.getProgramName();
					for(int j=0;j<prgList.length;j++)
					{
						if(prgList[j].contains(name))
						{
							row.setProgramName(prgList[j]);
							ret.set(i, row);
						}
					}
				}
			}
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();	
			}catch(Exception ex){}
			
		}
		return ret;
	}
	
	@Override
	public List<CourseInfoDomain> getCourseList(HttpSession sess)
	{
		List<CourseInfoDomain> ret=new ArrayList<CourseInfoDomain>();
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");

			conn=DBTransaction.openConnection();
			String sql="select distinct a.courseid_by_ts,b.course_name from mst_course_catalogue a,transaction_course_info b where a.course_id=b.course_id and a.organization_id=?";
			st=conn.prepareStatement(sql);
			st.setString(1, util.getOrgID());
			util.print("getCourseList :: "+st.toString());
			result=st.executeQuery();
			while(result.next())
			{
				String course_id="";	if(result.getObject("courseid_by_ts")!=null)	course_id=result.getString("courseid_by_ts").toString().trim();
				String course_name="";	if(result.getObject("course_name")!=null)	course_name=result.getString("course_name").toString().trim();		
				
				CourseInfoDomain row=new CourseInfoDomain();
				row.setCourseID(course_id);
				row.setCourseName(course_name);
				
				if(course_id.length()>0 )
					ret.add(row);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();	
			}catch(Exception ex){}
			
		}
		return ret;
	}
	
	@Override
	public List<DepartmetInfoDomain> getDeptList(HttpSession sess)
	{
		List<DepartmetInfoDomain> ret=new ArrayList<DepartmetInfoDomain>();
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");

			conn=DBTransaction.openConnection();
			String sql="select a.deptid_by_ts,b.department_name from mst_course_catalogue a,transaction_department_info b where b.department_id =a.dept_id and a.organization_id=? ";
			st=conn.prepareStatement(sql);
			st.setString(1, util.getOrgID());
			util.print("getDeptList :: "+st.toString());
			result=st.executeQuery();
			while(result.next())
			{
				String deptid_by_ts="";	if(result.getObject("deptid_by_ts")!=null)	deptid_by_ts=result.getString("deptid_by_ts").toString().trim();
				String department_name="";	if(result.getObject("department_name")!=null)	department_name=result.getString("department_name").toString().trim();
				
				
				DepartmetInfoDomain row=new DepartmetInfoDomain();
				row.setDeptID(deptid_by_ts);
				row.setDeptName(department_name);
				
				if(deptid_by_ts.length()>0 && department_name.length()>0)
					ret.add(row);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();	
			}catch(Exception ex){}
			
		}
		return ret;
	}
	
	//select a.deptid_by_ts,b.department_name from mst_course_catalogue a,transaction_department_info b where b.department_id =a.dept_id and a.organization_id='' 	
	
	@Override
	public List<SubjectDomain> getSubjectsList(HttpSession sess)
	{ 
		List<SubjectDomain> ret=new ArrayList<SubjectDomain>();
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			conn=DBTransaction.openConnection();
			String sql="select subjectid_by_ts,subject_name,subject_id from mst_subjects where organization_id=?";
			
			st=conn.prepareStatement(sql);
			st.setString(1, util.getOrgID());
			util.print("getSubjectsList :: "+st.toString());
			result=st.executeQuery();
			while(result.next())
			{
				String subjectid_by_ts="";	if(result.getObject("subjectid_by_ts")!=null)subjectid_by_ts=result.getString("subjectid_by_ts").toString().trim();
				String subject_name="";	if(result.getObject("subject_name")!=null)subject_name=result.getString("subject_name").toString().trim();
				String subject_id="";if(result.getObject("subject_id")!=null)subject_id=result.getString("subject_id").toString().trim();
				
				SubjectDomain row =new SubjectDomain();
				row.setSubjectId(subjectid_by_ts);
				row.setSubjectName(subject_name);
				row.setDBSubjectId(subject_id);
				ret.add(row);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();	
			}catch(Exception ex){}
			
		}
		return ret;
	}
	
	@Override
	public List<TopicDomain> getTopicsList(HttpSession sess)
	{
		List<TopicDomain> ret=new ArrayList<TopicDomain>();
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			conn=DBTransaction.openConnection();
			String sql="select topic_id,topic_name,topicid_by_ts from mst_topics where organization_id=?";
			
			st=conn.prepareStatement(sql);
			st.setString(1, util.getOrgID());
			util.print("getTopicsList :: "+st.toString());
			result=st.executeQuery();
			while(result.next())
			{
				String topic_id="";	if(result.getObject("topic_id")!=null) topic_id=result.getString("topic_id").toString().trim();
				String topic_name="";	if(result.getObject("topic_name")!=null) topic_name=result.getString("topic_name").toString().trim();
				String topicid_by_ts="";	if(result.getObject("topicid_by_ts")!=null) topicid_by_ts=result.getString("topicid_by_ts").toString().trim();
				
				TopicDomain row=new TopicDomain();
				row.setTopic_id(topic_id);
				row.setTopic_name(topic_name);
				row.setTopic_id_by_ts(topicid_by_ts);
				
				if(topicid_by_ts.length()>0)
					ret.add(row);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();	
			}catch(Exception ex){}
			
		}
		return ret;
	}
	
	@Override
	public List<SubTopicDomain> getSubTopicsList(HttpSession sess)
	{
		List<SubTopicDomain> ret=new ArrayList<SubTopicDomain>();
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			String sql="select sub_topic_id,sub_topic_name,subtopic_id_by_ts from mst_subtopics where organization_id=?";
			
			st=conn.prepareStatement(sql);
			st.setString(1, util.getOrgID());
			util.print("getSubTopicsList :: "+st.toString());
			result=st.executeQuery();
			while(result.next())
			{
				String sub_topic_id="";	if(result.getObject("sub_topic_id")!=null) sub_topic_id=result.getString("sub_topic_id").toString().trim();
				String sub_topic_name="";	if(result.getObject("sub_topic_name")!=null) sub_topic_name=result.getString("sub_topic_name").toString().trim();
				String subtopic_id_by_ts="";	if(result.getObject("subtopic_id_by_ts")!=null) subtopic_id_by_ts=result.getString("subtopic_id_by_ts").toString().trim();
				
				SubTopicDomain row=new SubTopicDomain();
				row.setSubTopic_id(sub_topic_id);
				row.setSubTopic_id_by_ts(subtopic_id_by_ts);
				row.setSubTopic_name(sub_topic_name);;
				
				if(subtopic_id_by_ts.length()>0)
					ret.add(row);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();	
			}catch(Exception ex){}
			
		}
		return ret;
	}
	
	@Override
	public List<UsersInfoDomain> getUsersListByPrivilege(String Privilege,HttpSession sess)
	{
		return  getAllUsersList(sess);  
		
		/*ArrayList<UsersInfoDomain> ret=new ArrayList<UsersInfoDomain>();
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			conn=DBTransaction.openConnection();
			String sql="select * from users_info where privilege=? and organization_id=?";
			
			st=conn.prepareStatement(sql);
			st.setString(1, Privilege);
			st.setString(2, SessionUtil.getOrgID());
			SessionUtil.print("getUsersListByPrivilege :: "+st.toString());
			result=st.executeQuery();
			while(result.next())
			{
				String user_id=result.getString("user_id").toString().trim();
				String username=result.getString("username").toString().trim();
				String email=result.getString("email").toString().trim();
				String address=result.getString("address").toString().trim();
				String contact_no=result.getString("contact_no").toString().trim();
				String privilege=result.getString("privilege").toString().trim();
				String organizationId=result.getString("organization_id").toString().trim();
				String country=result.getString("country").toString().trim();
				String state=result.getString("state").toString().trim();
				String city=result.getString("city").toString().trim();
				String courseId=result.getString("course_id").toString().trim();
				
				UsersInfoDomain row=new UsersInfoDomain();
				row.setUserID(user_id);
				row.setUserName(username);
				row.setEmail(email);
				row.setAddress(address);
				row.setContactNo(contact_no);
				row.setPrivilege(privilege);
				row.setOrganizationId(organizationId);
				row.setCountry(country);
				row.setState(state);
				row.setCity(city);
				row.setCourseId(courseId);
				
				ret.add(row);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();	
			}catch(Exception ex){}
			
		}
		return ret;*/
	}
	
	@Override
	public List<UsersInfoDomain> getAllUsersList(HttpSession sess)
	{
		List<UsersInfoDomain> ret=new ArrayList<UsersInfoDomain>();
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			conn=DBTransaction.openConnection();
			String sql="select * from users_info where organization_id=?";
			
			st=conn.prepareStatement(sql);
			st.setString(1, util.getOrgID());
			util.print("getAllUsersList() :: "+st.toString());
			result=st.executeQuery();
			while(result.next())
			{
				String user_id=result.getString("user_id").toString().trim();
				String username=result.getString("username").toString().trim();
				String email=result.getString("email").toString().trim();
				String address=result.getString("address").toString().trim();
				String contact_no=result.getString("contact_no").toString().trim();
				String privilege=""; // result.getString("privilege").toString().trim();
				String organizationId=result.getString("organization_id").toString().trim();
				String country=result.getString("country").toString().trim();
				String state=result.getString("state").toString().trim();
				String city=result.getString("city").toString().trim();
				String courseId="";
				
				UsersInfoDomain row=new UsersInfoDomain();
				row.setUserID(user_id);
				row.setUserName(username);
				row.setEmail(email);
				row.setAddress(address);
				row.setContactNo(contact_no);
				row.setPrivilege(privilege);
				row.setOrganizationId(organizationId);
				row.setCountry(country);
				row.setState(state);
				row.setCity(city);
				row.setCourseId(courseId);
				
				ret.add(row);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();	
			}catch(Exception ex){}
			
		}
		return ret;
	}

	@Override
	public List<InstFeeDomain> getInstFeeDetails(HttpSession sess) {
		// TODO Auto-generated method stub
		List<InstFeeDomain> InstFeeDetails=new ArrayList<InstFeeDomain>();
		
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			String sql="select mlt_fee_name,mlt_fee_code from mst_mltsource_x_fee_components where mlt_id=?";
			st=conn.prepareStatement(sql);
			st.setString(1, util.getOrgID());
			util.print("sql == "+st.toString());
			result=st.executeQuery();
			while(result.next())
			{
				String feecode="";if(result.getObject("mlt_fee_code")!=null)feecode=result.getString("mlt_fee_code");
				String feename="";if(result.getObject("mlt_fee_name")!=null)feename=result.getString("mlt_fee_name");
				
				InstFeeDomain row=new InstFeeDomain();
				row.setInstFeeId(feecode);
				row.setInstFeeName(feename);
				
				InstFeeDetails.add(row);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();	
			}catch(Exception ex){}
			
		}
		return InstFeeDetails;
	}

	@Override
	public List<CrsFeeDomain> getCrsFeeDetails(HttpSession sess) {
		// TODO Auto-generated method stub
		List<CrsFeeDomain> CrsFeeDetails=new ArrayList<CrsFeeDomain>();
		
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			conn=DBTransaction.openConnection();
			String sql="select * from mst_mltsource_fees_x_mltsource_course where mlt_id=?";
			st=conn.prepareStatement(sql);
			st.setString(1, util.getOrgID());
			util.print("sql == "+st.toString());
			result=st.executeQuery();
			while(result.next())
			{
				String feecode="";if(result.getObject("mlt_fee_code")!=null)feecode=result.getString("mlt_fee_code");
				String crsFeeId="";if(result.getObject("mlt_course_fee_code")!=null)crsFeeId=result.getString("mlt_course_fee_code");
				String group="";if(result.getObject("grouped_feecode")!=null)group=result.getString("grouped_feecode");
				String fee_amount="";if(result.getObject("fee_amount")!=null)fee_amount=result.getString("fee_amount");
				String fee_type="";if(result.getObject("fee_type")!=null)fee_type=result.getString("fee_type");
				String recurring_frequency="";if(result.getObject("recurring_frequency")!=null)recurring_frequency=result.getString("recurring_frequency");
				
				CrsFeeDomain row=new CrsFeeDomain();
				row.setInstFeeId(feecode);
				row.setCrsFeeId(crsFeeId);
				row.setCrsFeeReq(recurring_frequency);
				row.setCrsFeeType(fee_type);
				row.setGroup(group);
				row.setFeeAmount(fee_amount);
				
				
				CrsFeeDetails.add(row);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();	
			}catch(Exception ex){}
			
		}
		return CrsFeeDetails;
	}

	@Override
	public List<MailserverDomain> getMailserverDetails(HttpSession sess) {
		// TODO Auto-generated method stub
		
		List<MailserverDomain> MailserverDetails=new ArrayList<MailserverDomain>();
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			String sql="SELECT server_id,server_name FROM mst_mail_server where organization_id=?";
			st=conn.prepareStatement(sql);
			st.setString(1, util.getOrgID());
			result=st.executeQuery();
			while(result.next())
			{
				String server_id="";if(result.getObject("server_id")!=null)server_id=result.getString("server_id");
				String server_name="";if(result.getObject("server_name")!=null)server_name=result.getString("server_name");
				
				MailserverDomain row=new MailserverDomain();
				row.setServerId(server_id);
				row.setServerName(server_name);
				
				MailserverDetails.add(row);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();	
			}catch(Exception ex){}
			
		}
		return MailserverDetails;
	}

	@Override
	public List<UseRoleDomain> getRoleList(HttpSession sess) {
		// TODO Auto-generated method stub
		
		List<UseRoleDomain> RoleList=new ArrayList<UseRoleDomain>();
		
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			String sql="SELECT role_id, role_desc,role_type FROM mst_roles where organization_id=?";
			st=conn.prepareStatement(sql);
			st.setString(1, util.getOrgID());
			util.print("getRoleList() :: "+st.toString());
			result=st.executeQuery();
			while(result.next())
			{
				UseRoleDomain row=new UseRoleDomain();
				
				String role_id="";if(result.getObject("role_id")!=null)role_id=result.getString("role_id");
				String role_desc="";if(result.getObject("role_desc")!=null)role_desc=result.getString("role_desc");
				String role_type="";if(result.getObject("role_type")!=null)role_type=result.getString("role_type");
				
				row.setRoleId(role_id);
				row.setDescription(role_desc);
				row.setType(role_type);
				
				RoleList.add(row);
				
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();	
			}catch(Exception ex){}
			
		}
		return RoleList;
	}

	@Override
	public List<MenuDomain> getMenuNameListByLevel(int level,HttpSession sess) {
		// TODO Auto-generated method stub
		
		List<MenuDomain> MenuList=new ArrayList<MenuDomain>();
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			String sql="SELECT distinct(menu_name) FROM mst_menus where organization_id=? and menu_level=?";
			st=conn.prepareStatement(sql);
			st.setString(1, "OwnKesInd");
			st.setInt(2, level);
			util.print("getMenuList() :: "+st.toString());
			result=st.executeQuery();
			while(result.next())
			{
				String menu_name="";if(result.getObject("menu_name")!=null) menu_name=result.getString("menu_name");
				
				MenuDomain row=new MenuDomain();
				row.setMenuName(menu_name);
				
				MenuList.add(row);
			}
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();	
			}catch(Exception ex){}
			
		}	
		return MenuList;
	}

	@Override
	public List<EnrAttributesDomain> getEnrAttributes(HttpSession sess) {
		// TODO Auto-generated method stub
		
		List<EnrAttributesDomain> Attributes=new ArrayList<EnrAttributesDomain>();
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			
			conn=DBTransaction.openConnection();
			String sql="select * from enrollment_attributes";
			st=conn.prepareStatement(sql);
			result=st.executeQuery();
			while(result.next())
			{
				String attribute_id=result.getString("attribute_id");
				String attribute_name=result.getString("attribute_name");
				String attribute_description=result.getString("attribute_description");
				String attribute_caption=result.getString("attribute_caption");
				String attribute_status=result.getString("attribute_status");
				
				EnrAttributesDomain row=new EnrAttributesDomain();
				row.setAttribute_id(attribute_id);
				row.setAttribute_name(attribute_name);
				row.setAttribute_description(attribute_description);
				row.setAttribute_caption(attribute_caption);
				row.setAttribute_status(attribute_status);
				
				Attributes.add(row);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();	
			}catch(Exception ex){}
			
		}	
		return Attributes;
		
		
	}

	@Override
	public List<EnrAttrCaptionsDomain> getEnrAttCaptions(HttpSession sess) {
		// TODO Auto-generated method stub
		List<EnrAttrCaptionsDomain> AttrCaptions=new ArrayList<EnrAttrCaptionsDomain>();
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			conn=DBTransaction.openConnection();
			String sql="select * from attribute_captions";
			st=conn.prepareStatement(sql);
			result=st.executeQuery();
			while(result.next())
			{
				String caption_id=result.getString("caption_id");
				String attribute_id=result.getString("attribute_id");
				String caption_description=result.getString("caption_description");
				
				EnrAttrCaptionsDomain row=new EnrAttrCaptionsDomain();
				row.setCaption_id(caption_id);
				row.setCaption_description(caption_description);
				row.setAttribute_id(attribute_id);
				
				AttrCaptions.add(row);
			}
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();	
			}catch(Exception ex){}
			
		}	
		return AttrCaptions;
	}

	@Override
	public HashMap<String, String> getUidMapping(HttpSession sess) {
		// TODO Auto-generated method stub
		
		HashMap<String, String> ret=new HashMap<String, String>();
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			String sql="select * from mst_cfl_usermapping where organization_id=?";
			st=conn.prepareStatement(sql);
			st.setString(1, util.getOrgID());
			util.print("mst_cfl_usermapping :: "+st.toString());
			result=st.executeQuery();
			while(result.next())
			{
				String userid_by_ts="";if(result.getObject("userid_by_ts")!=null)userid_by_ts=result.getString("userid_by_ts");
				String userid_by_cfl="";if(result.getObject("userid_by_cfl")!=null)userid_by_cfl=result.getString("userid_by_cfl");
				
				if(userid_by_cfl.trim().length()>0 && userid_by_ts.trim().length()>0)
					{
						ret.put(userid_by_cfl, userid_by_ts);
						ret.put(userid_by_ts, userid_by_cfl);
					}
					
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();	
			}catch(Exception ex){}
			
		}
		return ret;
	}

	@Override
	public List<LetterGradeDomain> getLetterGrades(HttpSession sess) {
		// TODO Auto-generated method stub
		List<LetterGradeDomain> letterGrades=new ArrayList<LetterGradeDomain>();
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			conn=DBTransaction.openConnection();
			String sql="select * from mst_letter_grades";
			st=conn.prepareStatement(sql);
			result=st.executeQuery();
			while(result.next())
			{
				String letter_grade=result.getString("letter_grade");
				String description=result.getString("description");
								
				LetterGradeDomain row=new LetterGradeDomain();
				row.setLetterGrade(letter_grade);
				row.setDescription(description);
								
				letterGrades.add(row);
			}
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();	
			}catch(Exception ex){}
			
		}	
		return letterGrades;
	}
	
	

}
