<%@ page import="java.sql.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Welcome Owner</title>
		<link href="css/owner.css" rel="stylesheet"></link>
		<link href="css/global.css" rel="stylesheet"></link>
		<link href="../CSS/styles.css" rel="stylesheet"></link>
		<link rel="shortcut icon" href="assets/title_icon.ico" type="image/x-icon">
		<link rel="stylesheet" type="text/css" href="css/a.css">
		<link rel="stylesheet" href="http://louisremi.github.com/menu-effects/css/style.css" />
		<!-- <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/> -->

		<script src="js/jquery-2.1.3.min.js" type="text/javascript"></script>		
		<script src="js/global.js"></script>
	
	</head>
	<body>	
	
<% 
//String search_key=request.getParameter("search_key").trim();
Statement stmt;
Connection con;
try{
		
Class.forName("org.postgresql.Driver");
//out.println("connected successfull");
con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/test","postgres","root");
//out.println("connected success");
stmt=con.createStatement();
//out.println("statement successfully");
//pstmt.setString(1,search_key);
//pstmt.setString(2,search_key);
ResultSet rs1=stmt.executeQuery("select * from category");

    
	 
    

%>
		<div class="container">
		<article>
			<div id="anim_img1"></div>
			<div id="kds_logo"></div>
		
			
 <style>
 body{background-image:url('Image/elearning1.jpg');width:100%;}
.menu li{
	width: 150px;
	height: 40px;
}
.menu{background-color:rgba(0, 0, 0,0.5);}
#main{
	float:left;
	width:100%;
	text-align: center;
	margin-top: 0px;
	//background-color:rgba(153, 53, 102,0.5);
}
.menu > li{
	background: none;
	color:#fff;
}
.menu > li:nth-child(2n+1){
	background: none;
	color:#fff;
}
.submenu > li{
	 background: linear-gradient(grey, white);
}
.submenu > li:nth-child(2n+1) {
	background: linear-gradient(grey, white);
}
.submenu > li:nth-child(2n+1):hover{
	background: linear-gradient(grey, #000);
	color:#fff;
}
.submenu > li:hover{
	background: linear-gradient(grey, #000);
	color:#fff;
}
 </style>

<div id='cssmenu'>
<ul>
  <% while (rs1.next()) {%>
  <li>
                    <a href="#">
 
                        <%= rs1.getString("category_name")%>
                    </a>
                    <ul>
                        <%
                            String query2 = "select subcategory_name from subcategory where category_id='" + rs1.getInt("category_id") + "'";
                        
                            Statement st2 = con.createStatement();
                            ResultSet rs2 = st2.executeQuery(query2);
                            while (rs2.next()) {
                        %>
                        <li>
                            <a href="#">
                                <%= rs2.getString("subcategory_name")%>
                            </a>
                        
                        <ul>
                        <%
                        out.print("rs3 success");
                        
                           
                           
                            Statement st3 = con.createStatement();
                            out.print("rs4 success");
                            ResultSet rs3 = st3.executeQuery("select subsubcategory_name from subsubcategory where subcategory_id= '"+ rs1.getInt("subcategory_id") + " ' and category_id='"+ rs1.getInt("category_id") +" '");
                            out.print("rs5 success");
                            while (rs3.next()) {
                            	
                        %>
                        <li>
                          <a href="#">
                                 <%= rs3.getString("subsubcategory_name")%>
                            </a>
                       
                        <%
                            }
                        %>
                    </ul>
                </li>
                <%
                    }
                %>
            </li>
              </ul>
                <%
                    }
                %>
            </ul>
            
				
<%
stmt.close();

con.close();
}
catch(Exception e)
{
	
	out.println("No Match");
	}

%>
	</body>
</html>
