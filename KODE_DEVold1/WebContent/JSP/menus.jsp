
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <link href="../CSS/menus.css" rel="stylesheet"></link>
  <!--  <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script> -->
    <!--  <script type="text/javascript">
   (function($) {

	   $.fn.menumaker = function(options) {
	       
	       var cssmenu = $(this), settings = $.extend({
	         title: "Menu",
	         format: "dropdown",
	         sticky: false
	       }, options);

	       return this.each(function() {
	         cssmenu.prepend('<div id="menu-button">' + settings.title + '</div>');
	         $(this).find("#menu-button").on('click', function(){
	           $(this).toggleClass('menu-opened');
	           var mainmenu = $(this).next('ul');
	           if (mainmenu.hasClass('open')) { 
	             mainmenu.hide().removeClass('open');
	           }
	           else {
	             mainmenu.show().addClass('open');
	             if (settings.format === "dropdown") {
	               mainmenu.find('ul').show();
	             }
	           }
	         });

	         cssmenu.find('li ul').parent().addClass('has-sub');

	         multiTg = function() {
	           cssmenu.find(".has-sub").prepend('<span class="submenu-button"></span>');
	           cssmenu.find('.submenu-button').on('click', function() {
	             $(this).toggleClass('submenu-opened');
	             if ($(this).siblings('ul').hasClass('open')) {
	               $(this).siblings('ul').removeClass('open').hide();
	             }
	             else {
	               $(this).siblings('ul').addClass('open').show();
	             }
	           });
	         };

	         if (settings.format === 'multitoggle') multiTg();
	         else cssmenu.addClass('dropdown');

	         if (settings.sticky === true) cssmenu.css('position', 'fixed');

	         resizeFix = function() {
	           if ($( window ).width() > 768) {
	             cssmenu.find('ul').show();
	           }

	           if ($(window).width() <= 768) {
	             cssmenu.find('ul').hide().removeClass('open');
	           }
	         };
	         resizeFix();
	         return $(window).on('resize', resizeFix);

	       });
	   };
	 })(jQuery);

   $(function(){
	  $('li a').click(function(){
	     $('li a').each(function(a){
	       $( this ).removeClass('selectedclass');
	     });
	     $( this ).addClass('selectedclass');
	   }); 
	 	 $('ul a').click(function(){
	     $('ul a').each(function(a){
	      $( this ).removeClass('selectedclass');
	     });
	     $( this ).addClass('selectedclass');
	   });
	   $('li a').click(function(){
		     $('li a').each(function(a){
		       $( this ).removeClass('selectedclass');
		     });
		     $( this ).parents("li").addClass('active');
		   }); 
		 	 $('ul a').click(function(){
		     $('ul a').each(function(a){
		      $( this ).removeClass('selectedclass');
		     });
		     $( this ).parents("li").addClass('active');
		   });
	 
	});
   
 	 (function($){
	 $(document).ready(function(){

	 $(document).ready(function() {
	   $("#cssmenu").menumaker({
	     title: "Menu",
	     format: "multitoggle"
	   });

	   $("#cssmenu").prepend("<div id='menu-line'></div>");

	 var foundActive = false, activeElement, linePosition = 0, menuLine = $("#cssmenu #menu-line"), lineWidth, defaultPosition, defaultWidth;

	 $("#cssmenu > ul > li").each(function() {
	   if ($(this).hasClass('active')) {
	     activeElement = $(this);
	     foundActive = true;
	   }
	 });

	 if (foundActive === false) {
	   activeElement = $("#cssmenu > ul > li").first();
	 }

	 defaultWidth = lineWidth = activeElement.width();

	 defaultPosition = linePosition = activeElement.position().left;

	 menuLine.css("width", lineWidth);
	 menuLine.css("left", linePosition);

	 $("#cssmenu > ul > li").hover(function() {
	   activeElement = $(this);
	   lineWidth = activeElement.width();
	   linePosition = activeElement.position().left;
	   menuLine.css("width", lineWidth);
	   menuLine.css("left", linePosition);
	 }, 
	 function() {
	   menuLine.css("left", defaultPosition);
	   menuLine.css("width", defaultWidth);
	 });

	 });


	 });
	 })(jQuery);

   </script>-->
 
   
   <!--  <script src="../JS/script.js"></script>  -->
   <title></title>
</head>
<%
String user_id=(String)session.getAttribute("userid");
//System.out.println("userid="+user_id);
%>
<body>

<div id='cssmenu'>
<ul>
 <li class='active has-sub krishna' style="border-top:4px solid #0099d2;"><a href='#'>Participants</a>
        	   <ul class="sub_menu">
               <!--   <li><a href='../JSP/Student.jsp'>Create Student</a></li>  -->
                   <!-- <li><a href='../JSP/create-student.jsp'>Create Student</a></li>  -->
                <!--   <li><a href='/KODE_DEV/ControllerServlet/StudentsViewService'>Map Student</a></li>   -->
                <li><a href='../JSP/CollaborateStudent.jsp'>Create Group</a></li>
                <li><a href='../JSP/ParticipantModifyAllInTabView.jsp'>Modify Group</a></li>
               <!--  <li><a href="../JSP/AccessStudent.jsp">Access</a></li>
                <li><a href='../JSP/Assessment.jsp'>Assessment</a></li>
                <li><a href='../JSP/Assignment.jsp'>Assignment</a></li> -->
                <!-- <li><a href="../JSP/Workware.jsp">Workware</a></li> -->
                <li class="has-sub"><a href='#'>WorkWare</a>
                <ul style="margin-left: 83%;" class="sub_menu">
                <li><a href="../JSP/Workware.jsp">Create</a></li>
                 <li><a href='../JSP/ViewWorkware.jsp'>View</a></li>
                <li><a href="../JSP/CertifyStudent.jsp">Evaluate</a></li>
                 <li><a href="../JSP/DeleteAssignments1.jsp">Delete</a></li>
                </ul>
                </li>
                <!-- <li><a href='../JSP/CertifyStudent.jsp'>Certify</a></li> -->
                <!--  <li><a href='../JSP/TrackStudent.jsp'>Track</a></li> -->
                <li><a href='../JSP/StudentReports.jsp'>View</a></li>
                </ul>
                </li>
                
      <li class='active has-sub'><a href='#'>Knowledge Library</a>
        	   <ul class="sub_menu">
                <li><a href='../JSP/FacilitatorBuildKnowLib.jsp'>Build Library</a></li>
                <li><a href='../JSP/FacilitatorManageLib.jsp'>Modify Library</a></li>
                <!-- <li><a href='../JSP/FacilitatorFileShareKnowStore.jsp'>Share</a></li>
                <li><a href='../JSP/AdminDataBackupKnowStore.jsp'>Data Backup</a></li>
                <li><a href='../JSP/Ftpfile.jsp'>Ftp File</a></li> -->
                <li><a href='../JSP/FacilitatorLibInfoListViewKnow.jsp'>View Library</a></li>
                </ul>
                </li>
      <li class='active has-sub'><a href='#'>Knowledge Assets</a>
       		   <ul class="sub_menu">
               <li><a href='../JSP/FacilitatorKnowSetup.jsp'>Upload</a></li>
               <li><a href='../JSP/FacilitatorAssetKnow.jsp'>Publish</a></li>
                <li><a href='../JSP/FacilitatorKAssetsViewInList.jsp'>View</a></li>
                
               </ul>
               </li>
        
          <li class='active has-sub'><a href='#'>Session Management</a>
        	   <ul class="sub_menu">
                <li><a href='../JSP/CreateSession.jsp'>Create</a></li>
               
                <li><a href='../JSP/PostPoneSession.jsp'>Modify</a></li>
               <li class="has-sub"><a href='#'>View</a>
                <ul class="sub_menu">
                <li><a href='../JSP/FacilitatorSessionOldReport.jsp'>Completed</a></li>
                <li><a href='../JSP/FacilitatorSessionUpcomingReport.jsp'>Upcoming</a></li>
                </ul>
                </li>
                </ul>
                </li>
            <li class='active'><a href='/KODE_DEV/ControllerServlet/FacilitatorDashBoardService'>Dashboard</a>
                </li>
                  <li class='active'><a href='../JSP/AttendanceMarking.jsp'>Attendance</a>
                <%
                System.out.println("user_id="+user_id);
                if(user_id==null || user_id==""){
                	//response.sendRedirect("../JSP/error.jsp?errmsg=Session Expired");
                	System.out.println("User name is NUll..");
            		out.print("parent.location.href='../JSP/LoginWebView.jsp'");
                }
                else{
                if(user_id.equalsIgnoreCase("redington")){ %>
                <li class='active has-sub'><a href='../JSP/camera.jsp'>Camera</a>
                </li>
                <%} }%>
                 <li class='active'><a href='../JSP/FacilitatorSessionUpcomingReport.jsp'>KlassRoom</a>
              </li>
              
              <li class='active has-sub' > <a href='#'> MIS</a>
        	   			<ul>
        	   			<li><a href='../JSP/StudentAttendingClasses.jsp'>Student Attendance</a></li> 
                		<!-- <li><a href='../JSP/StudentsAssessmentResultReport.jsp'>Assessment Details</a></li> -->
                		<li><a href='../JSP/StudentsAssignmentResultReport.jsp'>Assignment Details</a></li>
                		</ul>
        	   		</li>
  </ul>
  </div>

</body>
