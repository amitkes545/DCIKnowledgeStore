<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*"%>
    <%@page import="com.kds.KODE_DEV.dbconnection.DBTransaction" %>
<%
String facultyid=(String)session.getAttribute("userid");
String orgid=(String)session.getAttribute("orgid");
String sessionStartTime=request.getParameter("val");
if(sessionStartTime==null || sessionStartTime.trim().equals("")){
out.print("Please enter session start time");
}else{
//String userId=String.parse(s);
//out.print(userId);
//System.out.println("session start time:"+sessionStartTime+ "facultyid:"+facultyid+ "orgid:"+orgid);
try{
/* Class.forName("org.postgresql.Driver");
Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/KODE_DEV","postgres","12345"); */

Connection con=DBTransaction.connect();
PreparedStatement ps=con.prepareStatement("select session_start_time from session_details where session_start_time=? and faculty_name=? and organization_id=?");
ps.setString(1,sessionStartTime);
ps.setString(2,facultyid);
ps.setString(3,orgid);

ResultSet rs=ps.executeQuery();
//System.out.println("sql query:"+ps);
/* if(rs.next()){
//out.print(rs.getString(1))//System.out.println("customer id:"+rs.getString(1));
out.print("Cutsomer Exit!Try Another");
}/* else {
	//System.out.println("customer can,t exit");
	out.print("Cutsomer can,t Exit");
} */
if(rs.next())
{    
    out.println("<font color=red>");
    out.println("<img src='../Image/no.png'/>");
    out.println("</font>");

}else {

    out.println("<font color=green>");
    out.println("<img src='../Image/right.png'/>");
    out.println("</font>");

}

}catch(Exception e){e.printStackTrace();}
}
%>