<%-- <%@page import="org.apache.naming.java.javaURLContextFactory"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.*"%>
    <%@ page import="java.sql.*"%>
    <%-- <%@ page errorPage="error.jsp" %> --%>
 
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.*" %>

<%@ page import="com.kds.KODE_DEV.dao.*" %>
 
<%
		String table_name = request.getParameter("table_name").toString();
	try{
		
		FeesModuleDao dao=new FeesModuleDao();
		ArrayList<HashMap<String,String>> ret=dao.getColumnNamesByTableName(table_name);
		String ColumnsList="";
		if(ret!=null && ret.size()>0)
		{
			for(int i=0;i<ret.size();i++)
			{
				HashMap<String,String> info=ret.get(i);
				String column_name=info.get("column_name").toString().trim();
				if(i==0)
					ColumnsList=column_name;
				else
					ColumnsList=ColumnsList+";"+column_name;
			}
		}
		out.println(ColumnsList);
		
}
 catch (Exception e) { e.printStackTrace();	}
	  

%>

