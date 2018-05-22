<!doctype html>
<html lang=''>
<head>
   <meta charset='utf-8'>
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link href="../CSS/menus.css" rel="stylesheet"></link>
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
   <!-- <script src="script.js"></script>  -->
   <title></title>
</head>
<body>

<div id='cssmenu'>
<ul>
   
   <li class='active has-sub'><a href='#'>Customer</a>
        	   <ul>
                <li><a href='../JSP/SetupCustomer.jsp'>Setup Customer</a></li>
              <!--  <li><a href="../JSP/ManageCustomer.jsp">Modify</a></li>
                  <li><a href="../JSP/accessctrllist.jsp">Access Control List</a></li>
                <li><a href='../JSP/viewcustomer.jsp'>View</a></li>  -->
                 </ul>
                </li>
      <li class='active has-sub'><a href='#'>User Management</a>
        	   <ul>
                <li><a href='../JSP/Create_user_management.jsp'>Create Super Admin</a></li>
               <!-- <li><a href='../JSP/FacilitatorManageLib.jsp'>Modify</a></li>
                <li><a href='../JSP/FacilitatorKnowReport.jsp'>View</a></li> -->
                </ul>
                </li>
                 <li class='active has-sub'><a href='#'>Setup Curriculum
                 </a>
        	   <ul>
                <li><a href='../JSP/Create_Course.jsp'>Create</a></li>
               <!-- <li><a href='../JSP/FacilitatorManageLib.jsp'>Modify</a></li>
                <li><a href='../JSP/FacilitatorKnowReport.jsp'>View</a></li> -->
                </ul>
                </li>
      <li class='active has-sub'><a href='#'>Knowledge Store</a>
       		   <ul>
               <li><a href='../JSP/OwnerKnowSetup.jsp'>Setup Knowledge Store</a></li>
         <!--     <li><a href='../JSP/FacilitatorKnowPublish.jsp'>Modify</a></li>
                 <li><a href='../JSP/FacilitatorKAssetsReports.jsp'>View</a></li> -->
                
               </ul>
     </li>
     <li class='active has-sub' > <a href='#'> Fees Config</a>
      	<ul>
        	<li><a href='../JSP/Fees_Creation.jsp'>Fees Creation</a></li>
            <li><a href='../JSP/Fees_Config.jsp'>Inst. Fees Config</a></li>
             <li><a href='../JSP/Coursewise_Fees.jsp'>Coursewise Fees</a></li>
             <li><a href='../JSP/Fee_Group_Config.jsp'>Fees Group</a></li>
             <li><a href='../JSP/Fees_Receipt_Configuration.jsp'>Fees Receipt Template Design</a></li>
        </ul>
      </li>          
        
         
  </ul>
  </div>

</body>
<html>
