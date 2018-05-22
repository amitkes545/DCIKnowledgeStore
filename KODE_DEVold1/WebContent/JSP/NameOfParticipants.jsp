<%-- <%@page import="org.apache.naming.java.javaURLContextFactory"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.*"%>
    <%@ page import="java.sql.*"%>
    <%-- <%@ page errorPage="error.jsp" %> --%>
 
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.*" %>

<%@ page import="com.kds.KODE_DEV.dbconnection.DBTransaction" %>
 
<%
		String SESSID = request.getParameter("sesid").toString();
		String lastdtm = request.getParameter("lastdtm").toString();
		//System.out.println("incoming lastdtm="+lastdtm);
java.sql.Connection con=null;
java.sql.Statement st=null;
try{
	//String SESSID=(String)request.getParameter("sesid");
	  con = DBTransaction.connect();
	 st= con.createStatement();
//ResultSet rs=st.executeQuery("select * from tracking_table order by date_time desc limit 2");
//g SESSID=getSessionID(facultyId, priv);

String student_id="",log_status="",privilege="";
int count=0;
java.util.Date date= new java.util.Date();
String query = "select t.* from classroom_tracking t join (select student_id,max(date_time) max_dtm from classroom_tracking where date(date_time) = current_date "
		+ "group by student_id) latest on t.student_id = latest.student_id and t.date_time = latest.max_dtm where log_status='in' and t.student_id"
		+ " in (select user_id from users_info u join session_details s on u.organization_id = s.organization_id and (u.user_id = s.faculty_name or "
		+ "(lower(u.privilege) = 'student' and (u.user_id = s.recipient_type or s.recipient_type = 'all' or position(u.user_id||'#' in s.recipient_type ) > 0)))   where session_id='"+SESSID+"'  )";
//System.out.println("query in NOP="+query);
ResultSet rs=st.executeQuery(query);

		while(rs.next()){
			if(count == 0) student_id = rs.getString("student_id");
			else student_id = student_id + ", "+ rs.getString("student_id");
			count++;		
	//SessionTrackingDomain std=new SessionTrackingDomain();
	//student_id=rs.getString("student_id");
	//log_status=rs.getString("log_status");
	//privilege=rs.getString("privilege");
	//System.out.println(rs.getString("privilege"));
	//System.out.println(rs.getString("log_status"));
	//arl.add(std);
	
	}	
//return arl;
if(lastdtm==""){
	lastdtm=new Timestamp(date.getTime()).toString();
	System.out.println("lastdtm="+lastdtm);
}
query = "select '' student_id,'' privilege,localtimestamp lastdtm union all  select t.student_id,privilege,localtimestamp lastdtm from classroom_tracking t where date_time > case when '"+lastdtm+"' is null or '"+lastdtm+"' = ''  then current_timestamp - interval '10 second' else '"+lastdtm+"' end  and log_status='out' and t.student_id in (select user_id from users_info u join session_details s on u.organization_id = s.organization_id and (u.user_id = s.faculty_name or (lower(u.privilege) = 'student' and (u.user_id = s.recipient_type or s.recipient_type = 'all' or position(u.user_id||'#' in s.recipient_type ) > 0)))   where session_id='"+SESSID+"')";
//System.out.println("query for out="+query);
 rs=st.executeQuery(query);
String outname=""; lastdtm="";
String priv="";
int count1=0;
while(rs.next()){
	if(rs.getString("student_id")!=null && !rs.getString("student_id").isEmpty())
			{
			if(count1 == 0) {
				outname = rs.getString("student_id");
				}
			else outname=outname+","+rs.getString("student_id");
			count1++;
			};
			priv=rs.getString("privilege");
			lastdtm=rs.getString("lastdtm");
			//System.out.println("outname="+outname);
		}
//;//rohan";

//String ctime=new Timestamp(date.getTime()).toString();
String ctime=lastdtm;
//System.out.println("outname="+outname);
		out.println(count+";"+student_id+";"+outname+";"+ctime+";"+priv);
		//System.out.println(count+";"+student_id+";"+outname+";"+ctime+";"+priv);
}
	 catch (SQLException e) { e.printStackTrace();	}
	  catch (ClassNotFoundException e) { e.printStackTrace(); }
finally
{
	DBTransaction.closeConnection(con);
}

%>

