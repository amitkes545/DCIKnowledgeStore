<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="java.sql.ResultSet"%>
     <%@page import="java.util.ArrayList"%>
     <%@page import="com.kds.KODE_DEV.domain.UsersInfoDomain" %>
     <%@page import="com.kds.KODE_DEV.domain.DisplayDomain" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Engineering Information</title>

<link rel="stylesheet" href="../CSS/kstyle.css"/>
<link rel="stylesheet" href="../CSS/jcider.css"/>
<link rel="stylesheet" href="../CSS/caro.css"/>
<script src="../JS/jquery-1.11.3.min.js"></script>
<!-- <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>  -->
<script src="../JS/jcider.min.js"></script>
<!-- <script src="https://cdn.jsdelivr.net/jquery.jcider/latest/jcider.min.js"></script>  -->
       
</head>
<body>
<div class="main">
<div class="container">
	<div>
		<%@ include file="../JSP/header.jsp"%>
	</div>
<div style="clear:both;"></div>

<!-- <section class="slider">
            <div>
                <div class="active"> IT </div>
                <div class="active"> MCA</div>
                <div class="active"> CSE </div>
                 <div class="active"> MBA </div>
                  <div class="active"> EEE </div>
                   
            </div>
        </section> -->
        <div class="caros">
<div class="slider-wrap">
		<div class="slider">
		<ul>
		<li><a href="" class="car-active"><%=session.getAttribute("streamName") %></a></li>
		<% session=request.getSession();
		if(session.getAttribute("streamInfo")!=null){
			ArrayList<DisplayDomain> StreamInfo=(ArrayList<DisplayDomain>)session.getAttribute("streamInfo");
			Iterator iterator=StreamInfo.iterator();
						while(iterator.hasNext()){
							DisplayDomain displayDomain=(DisplayDomain)iterator.next();
							%>
							
		<li><a href="/KODE_DEV/ControllerServlet/DepartmentInfoService"><%=displayDomain.getDepartName()%></a>
		<%}} %>
		</li>
		<!-- <li><a href="">TEST bae</a></li>
		<li><a href="">TEST bae</a></li>
		<li><a href="">TEST bae</a></li>
		<li><a href="">TEST bae</a></li>
		<li><a href="">TEST bae</a></li>
		<li><a href="">TEST bae</a></li>
		<li><a href="">TEST bae</a></li>
		<li><a href="">TEST bae</a></li>
		<li><a href="">TEST bae</a></li>
		<li><a href="">TEST bae</a></li> -->
		</ul>
		</div>
<a href="#" class="slider-arrow sa-left"></a> 
<a href="#" class="slider-arrow sa-right"></a> 

        <% if(session.getAttribute("departmentInfo")!=null){
        	ArrayList<UsersInfoDomain> result=(ArrayList<UsersInfoDomain>)session.getAttribute("departmentInfo");
        	Iterator iterator=result.iterator();
						while(iterator.hasNext()){
							UsersInfoDomain domain=(UsersInfoDomain)iterator.next();
							%>
							<div class="str">
        <div class="stream1">
<div class="strm1">
<div class="strm_img"><a href="/KODE_DEV/ControllerServlet/UniversityTypeService?universityType=<%=domain.getOrgId()%>"><img src="../Image/engineer.jpg" style="width:100%;"/></a></div>
<div class="strm_txt"><%=domain.getOrgId()%></div>

</div>
</div>
<%} }%>
<% if(request.getAttribute("universityType")!=null){
        	ArrayList<UsersInfoDomain> result=(ArrayList<UsersInfoDomain>)request.getAttribute("universityType");
        	Iterator iterator=result.iterator();
						while(iterator.hasNext()){
							UsersInfoDomain domain=(UsersInfoDomain)iterator.next();
							%>
							<div class="str">
<div class="stream1">
<div class="strm2">
<div class="strm_img"><a href=""><img src="../Image/institutions.jpg" style="width:100%;"/></a></div>
<div class="strm_txt"><%=domain.getOrgId()%></div>

</div>
</div>
		<%-- <a href=""><%=result.getString("organization_name")%></a> --%>
	<%} }%>	
		</div>
		</div>
		</div>
</div>

       <%--  <div class="str">
        <div class="stream1">
<div class="strm1">
<div class="strm_img"><img src="../Image/medical.jpg" style="width:100%;"/></div>
<div class="strm_txt">UNIVERSITY</div>
</div>
<div class="strm2">
<img src="../Image/engineer.jpg" style="width:100%;"/>
<div class="strm_txt">PROFESSIONAL</div>
</div>
<div class="strm3">
<img src="../Image/arts.jpg" style="width:100%;"/>
<div class="strm_txt">INSTITUTION</div>
</div>
<div class="strm4">
<img src="../Image/commerce.png" style="width:100%;"/>
<div class="strm_txt">VOCATIONAL</div>
</div>
</div>
<div style="clear:both;"></div>
<div class="stream2">
<div class="strm1">
<div class="strm_img"><img src="../Image/CA.jpg"" style="width:100%;"/></div>
<div class="strm_txt">SRM</div>
</div>
<div class="strm2">
<img src="../Image/banking1.jpg" style="width:100%;"/>
<div class="strm_txt">SASTRA</div>
</div>
<div class="strm3">
<img src="../Image/sport.png"" style="width:100%;"/>
<div class="strm_txt">SATYABAMA</div>
</div>
<div class="strm4">
<img src="../Image/management.jpg" style="width:100%;"/>
<div class="strm_txt">JNU</div>
</div>
</div>
<div style="clear:both;"></div>
	<div>
		<%@ include file="../JSP/footer.jsp" %>
	</div>
</div> --%>

<!-- <script type="text/javascript">
$(document).ready(function() {
	$('.slider').jcider();
});
</script> -->
<script src="../JS/jquery.lbslider.js"></script> 
<script>
jQuery('.slider').lbSlider({
    leftBtn: '.sa-left',
    rightBtn: '.sa-right',
    visible: 6,
    autoPlay:false,
    autoPlayDelay: 5
});
</script>
</body>
</html>