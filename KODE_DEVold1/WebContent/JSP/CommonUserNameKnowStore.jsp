<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.sql.*"%>
    <%@page import="com.kds.KODE_DEV.dbconnection.DBTransaction" %>
<%
String username1=request.getParameter("val");
if(username1==null || username1.trim().equals("")){
out.print("Please enter id");
}else{
//String userId=String.parse(s);
//out.print(userId);
//System.out.println("status:"+username1);
try{
/* Class.forName("org.postgresql.Driver");
Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/KODE_DEV","postgres","12345"); */

Connection con=DBTransaction.connect();
PreparedStatement ps=con.prepareStatement("select user_id from knowledgestore_info where ksid=?");
ps.setString(1,username1);

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
{   String userID = rs.getString("user_id"); 
    out.println("<font color=green>");
    out.println("User ID : "+userID);
    out.println("</font>");

}else {

    out.println("<font color=green>");
    out.println("Record not found");
    out.println("</font>");

}

}catch(Exception e){e.printStackTrace();}
}
%>