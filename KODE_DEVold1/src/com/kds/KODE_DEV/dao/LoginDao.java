package com.kds.KODE_DEV.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileUploadException;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.CreateDomain;
import com.kds.KODE_DEV.domain.LoginDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;
import com.kds.KODE_DEV.services.CommonService;


public class LoginDao {
	Connection con=null;
	public String checkUserCredentials(LoginDomain lDomain) {
		//DBTransaction DBT = new DBTransaction();
		String status = null;

		try {
			 con = DBTransaction.connect();
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String quary = "select user_id,password,privilege from users_info where user_id='"
					+ lDomain.getLoginId() + "'";
			pstmt = con.prepareStatement(quary);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				String quary1 = "select user_id,password,privilege from users_info where user_id='"
						+ lDomain.getLoginId()
						+ "'and password='"
						+ lDomain.getPassword() + "'";

				pstmt = con.prepareStatement(quary1);

				rs = pstmt.executeQuery();
				boolean flag=rs.next();
				if (flag) {
					     String s1=rs.getString("privilege");
					    		 //System.out.println("privilege value is:"+s1);
				           if("owner".equalsIgnoreCase(s1)){
				        	   status="owner";
				           }
				           else if("superadmin".equalsIgnoreCase(s1)){
				        	   status="superadmin";
				           }
				           else if("admin".equalsIgnoreCase(s1)){
				        	   status="admin";
				           }
				           else if("faculty".equalsIgnoreCase(s1)){
				        	   status="faculty";
				           }
				           else if("student".equalsIgnoreCase(s1)){
				        	   status="student";
				           }
				}   else {
					status = "pwdnotvalid";
				}
			}else {
				status = "useridnotvalid";
			}
		 }catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			DBTransaction.closeConnection(con);
		}
		return status;
	}
	public String changePassword(LoginDomain cdomain,String userid) {
		//DBTransaction DBT = new DBTransaction();
		String status = null;
          //System.out.println("password value is"+cdomain.getNewpwd());
		try {
			 con = DBTransaction.connect();
			
			String quary = "update users_info set password='"+cdomain.getNewpwd()+"'"+" where user_id='"+userid+"'";
			PreparedStatement ps = con.prepareStatement(quary);
			
			//System.out.println("the quary is:"+quary);
			int i = ps.executeUpdate();
			//System.out.println(i+"row is updated successfully");
			
			if (i==1)
				status = "success";
			else
				status = "failure";
		}catch(Exception e){
			e.printStackTrace();
		}
		finally
		{
			DBTransaction.closeConnection(con);
		}
		return status;
	}
	public String retriveUserName(String userid)  {
		//DBTransaction DBT = new DBTransaction();
		//Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String status = null;
          //System.out.println("username:"+userid);
		try {
			 con = DBTransaction.connect();
			
			String quary = "select username from users_info where user_id='"+userid+"'";
			pstmt = con.prepareStatement(quary);
			
			//System.out.println("the quary is:"+quary);
			rs=pstmt.executeQuery();
			while(rs.next()){
				status=rs.getString("username");
			}
		}catch(Exception e){
			e.printStackTrace();
		} 
		finally
		{
			DBTransaction.closeConnection(con);
		}
		return status;
	}
	//ArrayList<CreateDomain> arrayList=new ArrayList<CreateDomain>();
	List<String> list=new ArrayList<String>();
	public List<String> retriveOrgid(String userid)  {
		//DBTransaction DBT = new DBTransaction();
		//Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//String status = null;
          //System.out.println("username:"+userid);
		try {
			 con = DBTransaction.connect();
			
			String query = "select organization_name,organization_id from org_details where organization_id=(select organization_id from users_info where user_id='"+userid+"')";
			// String query="select organization_id,organization_name from org_details org,users_info user where org.organization_id=user.organization_id";
			pstmt = con.prepareStatement(query);
			
			//System.out.println("the quary is:"+query);
			rs=pstmt.executeQuery();
			while(rs.next()){
				//CreateDomain createDomain=new CreateDomain();
				//createDomain.setOrg_id(rs.getString("organization_id"));
				//createDomain.setOrg_name(rs.getString("organization_name"));
				list.add(rs.getString("organization_id"));
				list.add(rs.getString("organization_name"));
			}
		}catch(Exception e){
			e.printStackTrace();
		} 
		finally
		{
			DBTransaction.closeConnection(con);
		}
    	//System.out.println("oraganization name:"+list);
	
		return list;
	}	
	
	/** Description of loginUsersInfo(LoginDomain lDomain)
	 * Method to check get Login Credentials
	   */
public UsersInfoDomain loginUsersInfo(LoginDomain lDomain){
		
	/** creation of object to DBTransaction1 */
	
		//DBTransaction1 DBT = new DBTransaction1();
		
		/** Creating UsersInfoDomain object to Store Login Credentials */
		
		UsersInfoDomain uid=new UsersInfoDomain();
		try {
			 con = DBTransaction.connect();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String quary = "select created_by,organization_id,privilege,user_id,username from users_info where user_id='"+ lDomain.getLoginId() + "' and password='"+lDomain.getPassword()+"'";
			
			System.out.println(quary);
			pstmt = con.prepareStatement(quary);
			rs = pstmt.executeQuery();
			if (rs.next()) 
			{
				uid.setCreatedBy(rs.getString("created_by"));
				uid.setOrgId(rs.getString("organization_id"));
				uid.setPrevilege(rs.getString("privilege"));
				uid.setUserId(rs.getString("user_id"));
				uid.setUserName(rs.getString("username"));
				//System.out.println(rs.getString("username"));
				
			}
	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		finally
		{
			DBTransaction.closeConnection(con);
		}
		return uid;
	}
public String checkWebViewUserCredentials(LoginDomain lDomain) {
	//DBTransaction DBT = new DBTransaction();
	String status = null;
	
	try {
		//System.out.println("intry+");
		// session=request.getSession();
		 //System.out.println("DBT connecting+");
		 con = DBTransaction.connect();
		//System.out.println("DBT connected+");
		//System.out.println("lDomain="+lDomain);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String quary = "select user_id from users_info where user_id='"
				+ lDomain.getLoginId() + "'";
		pstmt = con.prepareStatement(quary);
		//System.out.println("select query :"+pstmt);
		rs = pstmt.executeQuery();
		if (rs.next()) {

			String quary1 = "select user_id,password,privilege from users_info where user_id='"
					+ lDomain.getLoginId()
					+ "'and password='"
					+ lDomain.getPassword() + "'";
			
			pstmt = con.prepareStatement(quary1);
			System.out.println("select query in if:"+pstmt);
			rs = pstmt.executeQuery();
		 if(rs.next()){
			// System.out.println("privilege ::"+rs.getString("privilege"));
			 //session.setAttribute("previlege",rs.getString("privilege"));
			status="success";
			
		}else {
			status = "pwdnotvalid";
			
			////System.out.println("status:"+status);
		}
		}else {
			status="useridnotvalid";
		}
	 }catch (Exception e) {
		e.printStackTrace();
	}
	finally
	{
		DBTransaction.closeConnection(con);
	}
	////System.out.println("status value in dao:"+status);
	return status;
}

public String getLogoName(String uid){ 
	PreparedStatement ps = null;
	ResultSet rs = null; 
	String logoname = null; 
	try { 
		con=DBTransaction.connect();
		String sql= "select logo from org_details where organization_id= (select organization_id from users_info where user_id='"+uid+"')";
		ps=con.prepareStatement(sql); 
		rs=ps.executeQuery(); 
		while(rs.next())
		{    
			logoname=rs.getString("logo"); 
			}     
		}catch(Exception e){ 
			e.printStackTrace(); }
	finally  
	{    
		try{ 
			if(con!=null) 
				con.close(); 
			if(ps!=null)
				ps.close();  
			if(rs!=null) 
				rs.close();
			}  
		catch(Exception e){  
			e.printStackTrace(); 
			} 
		}  
	return logoname;
	}

public int savedata(String userid,String sessionid)
{
	 Calendar cal = Calendar.																																																																																																																																																																																																																																																																																																																																	getInstance();
     SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    String abc=sdf.format(cal.getTime()) ;
    int n=0;
	System.out.println("Time DAO="+abc);
    PreparedStatement ps = null;
	try {
		con = DBTransaction.connect();
		String sql="INSERT INTO timer(start_time,session_id,user_id) VALUES (?,?,?)";
		ps = con.prepareStatement(sql);
		ps.setString(1,abc);
		ps.setString(2, sessionid);
		ps.setString(3, userid);
		 n=ps.executeUpdate();
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return n;
}

public String getData(String sessionid,String userid)
{
	 Calendar cal = Calendar.getInstance();
     SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    String abc=sdf.format(cal.getTime()) ;
    int n=0;
	System.out.println("Time DAO="+abc);
    PreparedStatement ps = null;
    ResultSet rs=null;
    String sttime=null;
       
	try {
		con = DBTransaction.connect();
		String sql= "select start_time from timer where session_id='"+sessionid+"'" ;
		
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		System.out.println("Query="+ps);
		while(rs.next())
		{
			 sttime=rs.getString("start_time");
					

		}
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return sttime;
}

}
