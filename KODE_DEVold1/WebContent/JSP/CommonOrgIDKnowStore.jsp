<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.sql.*"%>
    <%@page import="com.kds.KODE_DEV.dbconnection.DBTransaction" %>
<%
String ksid=request.getParameter("val");
if(ksid==null || ksid.trim().equals("")){
out.print("Please enter Know Store ID");
}else{
//String userId=String.parse(s);
//out.print(userId);
//System.out.println("orgid:"+ksid);
try{
/* Class.forName("org.postgresql.Driver");
Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/KODE_DEV","postgres","12345"); */

Connection con=DBTransaction.connect();
PreparedStatement ps=con.prepareStatement("select organization_id from knowledgestore_info where ksid=?");
ps.setString(1,ksid);

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
{   String orgID = rs.getString("Organization_id"); 
    out.println("<font color=green>");
    out.println("Organization id : "+orgID);
    out.println("</font>");

} else {
    out.println("<font color=red>");
    out.println("Record not available");
    out.println("</font>");

}

}catch(Exception e){e.printStackTrace();}
}
%>