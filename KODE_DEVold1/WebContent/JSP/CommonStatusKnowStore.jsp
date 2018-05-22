<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.sql.*"%>
    <%@page import="com.kds.KODE_DEV.dbconnection.DBTransaction" %>
<%
String status=request.getParameter("val");
if(status==null || status.trim().equals("")){
out.print("Please enter id");
}else{
//String userId=String.parse(s);
//out.print(userId);
//System.out.println("status:"+status);
try{
/* Class.forName("org.postgresql.Driver");
Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/KODE_DEV","postgres","12345"); */

Connection con=DBTransaction.connect();
PreparedStatement ps=con.prepareStatement("select status from knowledgestore_info where ksid=?");
ps.setString(1,status);

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
{   String status1 = rs.getString("status"); 
    out.println("<font color=green>");
    out.println("Status : "+status1);
    out.println("</font>");

}else {

    out.println("<font color=green>");
    out.println("Record not found");
    out.println("</font>");

}

}catch(Exception e){e.printStackTrace();}
}
%>