<!doctype html>
<html lang=''>
<head>
   <meta charset='utf-8'>
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link href="../CSS/menu.css" rel="stylesheet"></link>
  <!--  <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script> -->
   <script type="text/javascript">
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

   </script>
  <!--  <script src="../JS/script.js"></script>  -->
   <title></title>
</head>
<body>

<div id='cssmenu'>
<ul>
   
   <li class='active has-sub'><a href='#'>Participants</a>
        	   <ul>
                <li><a href='../JSP/Student.jsp'>Create</a></li>
                <li><a href='../JSP/CollaborateStudent.jsp'>Create Group</a></li>
               <!--  <li><a href="../JSP/AccessStudent.jsp">Access</a></li> -->
               <!--  <li><a href='../JSP/Assessment.jsp'>Assessment</a></li>
                <li><a href='../JSP/Assignment.jsp'>Assignment</a></li> -->
                <li><a href="../JSP/Workware.jsp">WorkWare</a>
                <li><a href='../JSP/CertifyStudent.jsp'>Certify</a></li>
                <!--  <li><a href='../JSP/TrackStudent.jsp'>Track</a></li> -->
                <li><a href='../JSP/StudentReports.jsp'>View</a></li>
                <!-- <li><a href="../JSP/IndividualParticipants.jsp">Individual</a></li>
                <li><a href="../JSP/GroupParticipants.jsp">Groups</a></li> -->
                </ul>
                </li>
      <li class='active has-sub'><a href='#'>Knowledge Store</a>
        	   <ul>
                <li><a href='../JSP/FacilitatorBuildKnowLib.jsp'>Build Library</a></li>
                <li><a href='../JSP/FacilitatorManageLib.jsp'>Modify</a></li>
                <!-- <li><a href='../JSP/FacilitatorFileShareKnowStore.jsp'>Share</a></li> -->
              <!--   <li><a href='../JSP/AdminDataBackupKnowStore.jsp'>Data Backup</a></li> -->
                <!-- <li><a href='../JSP/Ftpfile.jsp'>Library View</a></li> -->
                <li><a href='../JSP/FacilitatorLibInfoListViewKnow.jsp'>View</a></li>
                </ul>
                </li>
      <li class='active has-sub'><a href='#'>Knowledge Assets</a>
       		   <ul>
               <li><a href='../JSP/FacilitatorKnowSetup.jsp'>Upload</a></li>
               <li><a href='../JSP/FacilitatorAssetKnow.jsp'>Publish</a></li>
                <li><a href='../JSP/FacilitatorKAssetsViewInList.jsp'>View</a></li>
                
               </ul>
               </li>
        
          <li class='active has-sub'><a href='#'>Session Management</a>
        	   <ul>
                <li><a href='../JSP/CreateSession.jsp'>Create</a></li>
                <li><a href='../JSP/PostPoneSession.jsp'>Modify</a></li>
               <!--  <li><a href='../JSP/ManageSessionSearch.jsp'>Manage</a></li>
                <li><a href='../JSP/ShareFileToFTP.jsp'>Share</a></li>
                <li><a href='../JSP/FacilitatorSessionCancel.jsp'>Cancel</a></li> -->
                <!-- <li><a href='../JSP/FacilitatorGetSessionBackup.jsp'>Session Backup</a></li>-->
                
                <li><a href='../JSP/FacilitatorSessionReport.jsp'>View</a></li> 
                
                </ul>
                </li>
                <li class='active has-sub'><a href='/KODE_DEV/ControllerServlet/FacilitatorDashBoardService'>DashBoard</a>
        	  
                </li>
  </ul>
  </div>

</body>
<html>
