<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*"%>
    <%@page import="com.kds.KODE_DEV.dbconnection.DBTransaction" %>
<%
String userid=request.getParameter("val");
if(userid==null || userid.trim().equals("")){
out.print("Please enter id");
}else{
//String userId=String.parse(s);
//out.print(userId);
//System.out.println("userid:"+userid);
try{
/* Class.forName("org.postgresql.Driver");
Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/KODE_DEV","postgres","12345"); */

Connection con=DBTransaction.connect();
PreparedStatement ps=con.prepareStatement("select user_id from users_info where user_id=?");
ps.setString(1,userid);

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