<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="java.sql.*"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="com.kds.KODE_DEV.dbconnection.DBTransaction" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
</head>
<body>
<%
                   String img=null;
                   String org_id=request.getParameter("org_id");
                
                   Connection  con=DBTransaction.connect();
                   /*  //System.out.println("organization is:"+org_id);
                    String username = request.getParameter("username"); */
                    /* Class.forName("org.postgresql.Driver");
                    //System.out.println("display page");
                    Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/keducom", "postgres", "12345"); */
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("select logo from org_details where organization_id='"+org_id+"'");
                    while (rs.next()) {
                    	 img = rs.getString("logo");
                        byte[] img2 = new byte[img.length()];
                        InputStream imgstream = rs.getBinaryStream("logo");
                        int index = imgstream.read(img2, 0, img.length());
                        //System.out.println("index value is"+index);
                       response.reset();
                        //OutputStream os=response.getOutputStream().write(img2,0,img2.length);
                        response.getOutputStream().write(img2,0,img2.length);
                        response.getOutputStream().flush();
                        imgstream.close();
                        //System.out.println("image value");
                       //response.getOutputStream().write(img2,0,img2.length); */
                    }
                    %>
                 
</body>
</html>