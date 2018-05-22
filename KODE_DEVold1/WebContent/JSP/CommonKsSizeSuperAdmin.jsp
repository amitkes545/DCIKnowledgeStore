<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.sql.*"%>
    <%@page import="com.kds.KODE_DEV.dbconnection.DBTransaction" %>
<%
String kssize=request.getParameter("val");
if(kssize==null || kssize.trim().equals("")){
out.print("Please enter id");
}else{
//String userId=String.parse(s);
//out.print(userId);
//System.out.println("know store size:"+kssize);
try{
/* Class.forName("org.postgresql.Driver");
Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/KODE_DEV","postgres","12345"); */

Connection con=DBTransaction.connect();
PreparedStatement ps=con.prepareStatement("select knowledge_store_space from knowledgestore_info where user_id='"+session.getAttribute("userid")+"'");
//ps.setString();

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
{   String kssize1 = rs.getString("knowledge_store_space"); 
    out.println("<font color=green>");
    out.println("Your know store size :"+kssize1+"GB");
    out.println("</font>");

}else {

    out.println("<font color=red>");
    out.println("You don't have knowledge store space");
    out.println("</font>");

}

}catch(Exception e){e.printStackTrace();}
}
%>