<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.kds.KODE_DEV.domain.UsersInfoDomain" %>
     <%@page import="com.kds.KODE_DEV.domain.RetriveImagesDomain" %>
    <%@page import="com.kds.KODE_DEV.dao. DisplayStudentsDao" %>
    <%@page import="java.util.Iterator" %>
     <%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Guest Registration Done Here</title>
<link rel="stylesheet" href="../CSS/kstyle.css"/>
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script src="../JS/jquery.autocomplete.js"></script>	
<script>

	$(document).ready(function() {
		$(".autohide").delay(5000).fadeOut("slow");
	});
   </script>
   <script>
   //alert("hi");
   
	jQuery(function(){
		//alert("list");
		$("#search").autocomplete("list.jsp");
	});
  
   </script>
</head>
<body>
<div class="main">
<div class="container">
<div class="header">
<div class="header1">
<div class="kdslogo">
<img src="../Image/kds.png"/>
</div>
<div class="kedulogo">
<img src="../Image/Keducom_logo.png" style="width:256px;margin-top: 23px"/>
</div>
</div>
<div class="header2">
<div class="smsg">
<p class="wmsg">Welcome <%=session.getAttribute("userName")%></p>
</div>

<!-- <div class="logtn">
<a href="../JSP/ParticipantView.jsp">
<p class="myFolder"> <span>MyFolder</span></p></a>
</div> -->
<div class="logbtn">
<a href="../JSP/logout_knowstore.jsp">
<p class="lgout"><img src="../Image/logout.png" style="width:30px;vertical-align: middle;"/> <span>Logout</span></p></a>
</div>

</div>
<div>
<a href="../JSP/ParticipantView.jsp">
<p class="myFolder"><span>MyFolder</span></p></a>
</div>
</div>
<div style="clear:both;"></div>
<div class="search_box">
<input type="text" name="search" id="search" placeholder="Search" class="searching"/>
<img alt="www" src="../Image/search.png" style="width:28px; vertical-align: middle;margin-left: -39px;margin-top:5px;"/>
</div>
<%if(request.getAttribute("alertInfo")!=null){ %>
<p class="autohide" style="color:red; font-size:12px; font-weight: bold; top: 19%; left: 30%; position: absolute;">	<%= request.getAttribute("alertInfo") %>	</p>
<%} %>
<div style="clear:both;"></div>
<div class="stream">
 
<% DisplayStudentsDao displayStudentsDao=new DisplayStudentsDao();
							 ArrayList<RetriveImagesDomain> arrayList=new ArrayList<RetriveImagesDomain>();
							 arrayList=displayStudentsDao.selectStream();
							 	Iterator<RetriveImagesDomain> iterator= arrayList.iterator();%>
		                     
		                      
		                    <% while(iterator.hasNext())
		                     {
		                    	RetriveImagesDomain streamInfo=iterator.next();  %>
		    	            <%--  <%= imagePath%> --%>
		                  <%--  <div class="strm_img">
		                   
		                 <a href="/KODE_DEV/ControllerServlet/EngineeringInformationService?streamId=<%=streamInfo.getStreamId()%>&streamName=<%= streamInfo.getStreamName()%>" > 
		                  <img src="../Image/<%= streamInfo.getImagePath()%>" style="width:100%;"/><span ><%=streamInfo.getStreamName() %></span></a>
		                    </div> --%>
		                    
		                    <div class="str">
        <div class="stream1">
<div class="strm1">
<div class="strm_img"><a href="/KODE_DEV/ControllerServlet/EngineeringInformationService?streamId=<%=streamInfo.getStreamId()%>&streamName=<%= streamInfo.getStreamName()%>" > <img src="../Image/<%= streamInfo.getImagePath()%>" style="width:100%;"/></a></div>
<div class="strm_txt"><%=streamInfo.getStreamName() %></div>
</div>
		                         <%  }%>
		                      
<!-- <div class="stream1">
<div class="strm1">
<div class="strm_img"><img src="../Image/medical.jpg" style="width:100%;"/></div>
<div class="strm_txt">Medical</div>
</div>
<div class="strm2">
<a  href="../JSP/EngineeringInfo.jsp">
<img src="../Image/engineer.jpg" style="width:100%;"/>
<div class="strm_txt">Engineering</div>
</a>
<a class="strm_txt" href="../JSP/EngineeringInfo.jsp">Engineering</a>
</div>
<div class="strm3">
<img src="../Image/arts.jpg" style="width:100%;"/>
<div class="strm_txt">Arts</div>
</div>
<div class="strm4">
<img src="../Image/commerce.png" style="width:100%;"/>
<div class="strm_txt">Commerce</div>
</div>
</div>
<div style="clear:both;"></div>
<div class="stream2">
<div class="strm1">
<div class="strm_img"><img src="../Image/CA.jpg"" style="width:100%;"/></div>
<div class="strm_txt">Chartered Accountant</div>
</div>
<div class="strm2">
<img src="../Image/banking1.jpg" style="width:100%;"/>
<div class="strm_txt">Banking</div>
</div>
<div class="strm3">
<img src="../Image/sport.png"" style="width:100%;"/>
<div class="strm_txt">Sports</div>
</div>
<div class="strm4">
<img src="../Image/management.jpg" style="width:100%;"/>
<div class="strm_txt">Management</div>
</div> -->
</div>
</div>
<div style="clear:both;"></div>
<div>
		<%@ include file="../JSP/footer.jsp" %>
		</div>
</div>
</div>
</body>
</html>