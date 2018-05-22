package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AllGroupBean;
import com.kds.KODE_DEV.domain.RetriveImagesDomain;
import com.kds.KODE_DEV.domain.SessionDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;



public class StudentGroupNameDao {
	Connection con = null;
	PreparedStatement pstmt = null;
	

String strStudnetID="";
	 public ArrayList<RetriveImagesDomain> selectIndividualStudents(UsersInfoDomain uid){
			//DBTransaction1 dbt = new DBTransaction1();
			 ArrayList<RetriveImagesDomain> arl=new  ArrayList<RetriveImagesDomain>();
			try {
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
				String quary = "SELECT group_name FROM student_groups where created_by='"+uid.getCreatedBy()+"' and organization_id='"+uid.getOrgId()+"'";
				//System.out.println("in group"+quary);
				pstmt = con.prepareStatement(quary);
				System.out.println("in group"+pstmt);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					RetriveImagesDomain dcd=new RetriveImagesDomain();
					dcd.setCourseName(rs.getString("group_name")); 
					//dcd.setCourseImageLocation(rs.getString("user_id"));
					arl.add(dcd);
				}
				}catch(Exception e){
				e.printStackTrace();
			}
			finally
			   {
				   try{
					   con.close();
					   pstmt.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
		 return arl;
		   
	   }
	 
	 public ArrayList<AllGroupBean> selectAllDetails(String  uid,String orgid){
			//DBTransaction1 dbt = new DBTransaction1();
			  ArrayList<AllGroupBean> arl=new  ArrayList<AllGroupBean>();
			try {
				con = DBTransaction.connect();
				ResultSet rs = null;
			
				//String query1="select distinct group_name as groupname from student_groups where organization_id='"+orgid+"'"+" and created_by='"+uid+"'";
				/*String quary="select distinct username as username, user_id as userid from users_info where organization_id='"+orgid+"'"+" and created_by='"+uid+"'";
				*/
				String quary ="select a.username,a.user_id,b.group_name,"
						+ "b.student_id from users_info a,student_groups b where a.created_by='"+uid+"'"
								+" and a.organization_id='"+orgid+"'";
				
			/*	String quary = "SELECT distinct users_info.username as username,"
						+ "users_info.user_id as userid,"
						+ "student_groups.student_id,"
						+ "student_groups.group_name as groupname from users_info"
						+ " inner join student_groups on users_info.created_by='"+uid+"'"+" "
							+ "and users_info.organization_id='"+orgid+"'";
				*/
				/*String quary="SELECT distinct users_info.username as username,"
						+ "users_info.user_id as userid,"
						+ "student_groups.student_id,"
						+ "student_groups.group_name as groupname from users_info"
						+ " inner join student_groups on users_info.organization_id=student_groups.organization_id "
						+ "and users_info.created_by=student_groups.created_by where users_info.created_by='"+uid+"'"+" "
						+ "and users_info.organization_id='"+orgid+"'";*/
				//String quary="select * from users_info where organization_id='"+orgid+"' and created_by='"+uid+"'";
				/*//System.out.println("query1= "+query1);
				pstmt1=con.prepareStatement(query1);
				rs1=pstmt1.executeQuery();*/
				//System.out.println("for view participants== "+quary);
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next())
				{  
					AllGroupBean dcd=new AllGroupBean();
					dcd.setUsername(rs.getString("username"));
					dcd.setUserid(rs.getString("user_id"));
			     	dcd.setGroupname(rs.getString("group_name")); 
					arl.add(dcd); 
				} 
				}catch(Exception e){  
				e.printStackTrace();
			}
			finally
			   {
				   try{
					   con.close();
					   pstmt.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
		return arl; 
		   
	   }
	 
	 public ArrayList<UsersInfoDomain> SingleStudentDetails(String  stuid){
			//DBTransaction1 dbt = new DBTransaction1();
			  ArrayList<UsersInfoDomain> arl=new  ArrayList<UsersInfoDomain>();
			try {
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
			
				String quary ="select username,user_id,email,contact_no,address,users_status,gender,dateofbirth from users_info where user_id='"+stuid+"'";
				//System.out.println(quary);
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();  
				while(rs.next())
				{  
					UsersInfoDomain dcde=new UsersInfoDomain();
					dcde.setUserName(rs.getString("username"));
					dcde.setUserId(rs.getString("user_id")); 
					dcde.setEmail(rs.getString("email")); 
					dcde.setContactno(rs.getString("contact_no")); 	
					dcde.setAddress(rs.getString("address")); 
					dcde.setStatus(rs.getString("users_status")); 
					dcde.setDateofbirth(rs.getString("dateofbirth"));
					arl.add(dcde);
				} 
				}catch(Exception e){  
				e.printStackTrace();
			}
			finally
			   {
				   try{
					   con.close();
					   pstmt.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
		return arl; 
		   
	   }

	public String  getGroupDetails(String userid, String orgid, String strIdvalue) {
					try {
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
				
			
				
				//String quary ="select distinct a.username as username,a.user_id as userid,b.group_name as groupname ,b.student_id from users_info a,student_groups b where a.created_by='"+uid+"'"
			     // +" and a.organization_id='"+orgid+"'";
				String quary ="select student_id as studentid from student_groups where group_name='"+strIdvalue+"' and organization_id='"+orgid+"' and created_by='"+userid+"'";
				
				//System.out.println(quary);
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next())
				{  
					
					strStudnetID=rs.getString("studentid");
					//System.out.println("strStudnetID for group"+strStudnetID); 					
				} 
				}catch(Exception e){  
				e.printStackTrace();
			}
					finally
					   {
						   try{
							   con.close();
							   pstmt.close();
						   }
						   catch(Exception e){
								e.printStackTrace();
					   }
					   }
		return strStudnetID; 
		   
	   } 
	public ArrayList<AllGroupBean> selectAllDetailParticipantsIndividual(String  uid,String orgid){	
	
		 ArrayList<AllGroupBean> arl=new  ArrayList<AllGroupBean>();
			try {
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
				/*String quary="select distinct username, user_id from users_info where organization_id='"+orgid+"'"+" and created_by='"+uid+"' order by username";*/
				String quary="select distinct username, user_id from users_info left join course_description on users_info.organization_id=course_description.customer_id "
						+ "						where organization_id='"+orgid+"'"+" and privilege='Student' order by username";
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
			//	System.out.println(quary);
				while(rs.next())
				{  
					AllGroupBean dcd=new AllGroupBean();
					dcd.setUsername(rs.getString("username"));
					dcd.setUserid(rs.getString("user_id"));	
					arl.add(dcd); 
				} 
				}catch(Exception e){  
				e.printStackTrace();
			}
			finally
			   {
				   try{
					   con.close();
					   pstmt.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
		return arl; 
	}
	public ArrayList<AllGroupBean> selectAllDetailParticipantsGroup(String  uid,String orgid){	
		
		 ArrayList<AllGroupBean> arl=new  ArrayList<AllGroupBean>();
			try {
				con = DBTransaction.connect();
				//PreparedStatement pstmt = null;
				ResultSet rs = null;
				String quary="select distinct * from student_groups where organization_id='"+orgid+"'"+" and created_by='"+uid+"'";
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next())
				{  
					AllGroupBean dcd=new AllGroupBean();
					dcd.setGroupname(rs.getString("group_name"));
					dcd.setStudentid(rs.getString("student_id"));
					arl.add(dcd); 
				} 
				}catch(Exception e){  
				e.printStackTrace();
			}
			finally
			   {
				   try{
					   con.close();
					   pstmt.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
		return arl; 
	}
	public ArrayList<UsersInfoDomain>  getParticipatsModifyAll(String uid, String orgid) {
		 ArrayList<UsersInfoDomain> arl=new  ArrayList<UsersInfoDomain>();
		try {
	con = DBTransaction.connect();
	//PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String quary ="select * from users_info left join course_description on users_info.organization_id=course_description.customer_id where organization_id='"+orgid+"'"+" and privilege='Student' order by user_id";
	
	//System.out.println(quary);
	pstmt = con.prepareStatement(quary);
	rs = pstmt.executeQuery();
	while(rs.next())
	{  
		UsersInfoDomain dcde=new UsersInfoDomain();
		dcde.setUserName(rs.getString("username"));
		dcde.setUserId(rs.getString("user_id")); 
		dcde.setEmail(rs.getString("email")); 
		dcde.setContactno(rs.getString("contact_no")); 	
		dcde.setAddress(rs.getString("address")); 
		dcde.setStatus(rs.getString("users_status")); 
		dcde.setDateofbirth(rs.getString("dateofbirth"));
		arl.add(dcde);						
	} 
	}catch(Exception e){  
	e.printStackTrace();
}
		finally
		   {
			   try{
				   con.close();
				   pstmt.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
return arl; 

} 
	//Connection connection= null;
	//PreparedStatement pstmt=null;
	public String updateUserDetails(ArrayList<UsersInfoDomain> list){
		
		String status="";
		try {
			con=DBTransaction.connect();
			String sql="update users_info set username=?,email=?,address=?,contact_no=?,users_status=?,dateofbirth=? where user_id=?";
			pstmt = con.prepareStatement(sql);
			
			//System.out.println("Size of list=="+list.size());
			for(UsersInfoDomain s:list){
				//System.out.println("value of s==="+s);
				pstmt.setString(1, s.getUserName());
				pstmt.setString(2,s.getEmail());
				pstmt.setString(3,s.getAddress());
				pstmt.setString(4,s.getContactno());
				pstmt.setString(5,s.getStatus());
				pstmt.setString(6,s.getDateofbirth());
				pstmt.setString(7,s.getUserId());
				pstmt.addBatch();
			}
			int[] count=pstmt.executeBatch();
			//System.out.println("query is"+sql);
			//System.out.println(count+"values are inserted");
			if(count!=null){
				status="success";
			}else {
				status="failure";
			}
	
	}catch(Exception e){
		e.printStackTrace();
	}
		finally
		   {
			   try{
				   con.close();
				   pstmt.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return status;
	}
public String updateUserDetailsGroup(ArrayList<UsersInfoDomain> list){
		
		String status="";
		try {
			con=DBTransaction.connect();
			String sql="update users_info set username=?,email=?,address=?,contact_no=?,users_status=?,dateofbirth=? where user_id=?";
			pstmt = con.prepareStatement(sql);
			//System.out.println("query is"+sql);
			//System.out.println("Size of list=="+list.size());
			for(UsersInfoDomain s:list){
				//System.out.println("value of s==="+s);
				pstmt.setString(1, s.getUserName());
				pstmt.setString(2,s.getEmail());
				pstmt.setString(3,s.getAddress());
				pstmt.setString(4,s.getContactno());
				pstmt.setString(5,s.getStatus());
				pstmt.setString(6,s.getDateofbirth());
				pstmt.setString(7,s.getUserId());
				pstmt.addBatch();
			}
			int[] count=pstmt.executeBatch();
			//System.out.println(count+"values are inserted");
			//System.out.println("query is"+sql);
			if(count!=null){
				status="success";
			}else {
				status="failure";
			}
	
	}catch(Exception e){
		e.printStackTrace();
	}
		finally
		   {
			   try{
				   con.close();
				   pstmt.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return status;
	}


public String updateGroup(ArrayList<UsersInfoDomain> list, String groupid, String orgid, String newgroupname){
	
	String status="";
	try {
		con=DBTransaction.connect();
		String users="";
		String sql="update student_groups set student_id=? , group_name=? where group_name=? and organization_id=?";
		pstmt = con.prepareStatement(sql);
		//System.out.println("query is"+sql);
		//System.out.println("Size of list=="+list.size());
		if(list.size()>0){
		for(UsersInfoDomain s:list){
			users=users+s.getUserId()+"#";
			//System.out.println("users="+users);
			//prepareStatement.addBatch();
		}
		}
		pstmt.setString(1, users);
		pstmt.setString(3, groupid);
		pstmt.setString(4, orgid );
		pstmt.setString(2, newgroupname);	
		//pstmt.execute();
		if(pstmt.execute()){
			status="failed";
		}else {
			status="success";
		}

		}catch(Exception e){
		e.printStackTrace();
		}
	finally
	   {
		   try{
			   con.close();
			   pstmt.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
		return status;
	}
public String deleteGroup(String groupid, String orgid){
	
	String status="";
	try {
		con=DBTransaction.connect();
		String users="";
		String sql="delete from student_groups where group_name=? and organization_id=?";
		pstmt = con.prepareStatement(sql);
		//System.out.println("query is"+sql);
		//System.out.println("Size of list=="+list.size());
		
		pstmt.setString(1, groupid);
		pstmt.setString(2, orgid );
		//pstmt.execute();
		if(pstmt.execute()){
			status="failed";
		}else {
			status="success";
		}

		}catch(Exception e){
		e.printStackTrace();
		}
	finally
	   {
		   try{
			   con.close();
			   pstmt.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
		return status;
	}

public ArrayList<UsersInfoDomain> RestStudentDetails(String  stuid, String orgid){
	//DBTransaction1 dbt = new DBTransaction1();
	  ArrayList<UsersInfoDomain> finalarl=new  ArrayList<UsersInfoDomain>();
	  ArrayList<UsersInfoDomain> arl=new  ArrayList<UsersInfoDomain>();
	try {
		con = DBTransaction.connect();
		//PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		String quary ="select username,user_id,email,contact_no,address,users_status,gender,dateofbirth from users_info where user_id not in ("+stuid+")  and organization_id = '"+orgid+"' and privilege='Student' order by user_id" ;
		System.out.println(" dispaly student list only="+quary);
		pstmt = con.prepareStatement(quary);
		rs = pstmt.executeQuery();  
		while(rs.next())
		{  
			UsersInfoDomain dcde=new UsersInfoDomain();
			dcde.setUserName(rs.getString("username"));
			dcde.setUserId(rs.getString("user_id")); 
			dcde.setEmail(rs.getString("email")); 
			dcde.setContactno(rs.getString("contact_no")); 	
			dcde.setAddress(rs.getString("address")); 
			dcde.setStatus(rs.getString("users_status")); 
			dcde.setDateofbirth(rs.getString("dateofbirth"));
			arl.add(dcde);
			//finalarl.add(arl);
		} 
		}catch(Exception e){  
		e.printStackTrace();
	}
	finally
	   {
		   try{
			   con.close();
			   pstmt.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
   //System.out.println("finalarl length="+arl.size());
return arl; 
   
}


}