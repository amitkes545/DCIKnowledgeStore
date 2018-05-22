<%@ page errorPage="/JSP/error.jsp" %>
<%@ page import ="com.kds.KODE_DEV.services.*" %>
<%@ page import ="com.kds.KODE_DEV.domain.*" %>
<%@ page import ="java.util.*" %>
<%@ page import ="java.sql.*" %>
<%@ page import ="com.kds.KODE_DEV.dao.LoginDao" %>
<%@ page import ="java.text.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<!DOCTYPE html>

<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    
    <meta name="author" content="Muaz Khan" >
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="../css1/stylecss/style.css">
    <link rel="stylesheet" href="../css1/global.css">
<link rel="stylesheet" href="../CSS/dci-style.css">
<!-- <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>  -->
<script src="../JS/jquery-1.11.3.min.js"></script>


      <script src="../JS/screen_min.js"> </script>
        <script src="../JS/getScreenId_min.js"> </script> 
          <script src="../JS/canvas-designer-widget_min.js"></script>  

 <script>      
             if(!location.hash.replace('#', '').length) {  
               // location.href = location.href.split('#')[0] + '#' + (Math.random() * 100).toString().replace('.', '');  
                location.href = location.href.split('#')[0] +'#KES';  
                console.log(location.href);  
                location.reload();  
            }   
        </script> <!-- as DR3.11 testing-->
   <style>
    *{padding: 0px;margin: 0px;}
    audio,
    video {
        -moz-transition: all 1s ease;
        -ms-transition: all 1s ease;
        -o-transition: all 1s ease;
        -webkit-transition: all 1s ease;
        transition: all 1s ease;
        vertical-align: top;
    }
    #file
    {
    padding: 0px 12px !important;
    
    }
    .classroom_settingmenu{border: 1px solid #c2c2c2;
     border-left: none; border-right: none;height: 59PX;
     margin-top:0px; background: #f8f9fd; }
    .blackboard_control_board
    {
    display: none;
  width: 99% !important;
  height: 400px;
  border: 5px solid lightblue;
  border-radius: 16px;
    }
   
    #img1_frame img{
	width:100%;
	height:100%;
	}
	   .cls
    {
        width: 30px !important;
        height:25px !important;
    position: absolute;
    margin-top: -3px;
    }
    .close_blackboard img{margin-top: 8px;
    margin-left: -38px;}
	article
	{opacity:0.82;
	
	}
	
    input {
        border: 1px solid #d9d9d9;
        border-radius: 1px;
        font-size: 16px;
        margin-left:30px;
        padding-top:7px;
        height:38px;
        width: 21%;
       }
    select {
        border: 1px solid #d9d9d9;
        border-radius: 1px;
        height: 47px;
        margin-left: 2em;
        margin-right: -12px;
        padding: 1.1em;
        vertical-align: 6px;
	    position: relative;
	    top: -9px;
    }
    .setup1 {
     border-bottom-left-radius: 0;
    border-top-left-radius: 0;
    font-size: 72%;
   /*  position: absolute;
    height: 47px;
    right: 115px;
    top: -7px;
   */  }
    .setup {
     border-bottom-left-radius: 0;
    border-top-left-radius: 0;
    font-size: 102%;
   /*  position: absolute;
    height: 47px;
    right: 115px;
    top: -7px;
   */  }
    
    .setupend {
     border-bottom-left-radius: 0;
    border-top-left-radius: 0;
    font-size: 72%;
    background: red;
   /*  position: absolute;
    height: 47px;
    right: 115px;
    top: -7px;
   */  }
    
    #chat-output div,
    #file-progress div {
        /* border: 1px solid black; */
       /*  border-bottom: 0; */
        padding: .1em .4em;
    }
     #chat-output{width: 98%; text-align: left; height: 100%;}
    #chat-output,
    #file-progress {
        margin: 0 0 0 .4em;
        max-height: 80%;
        overflow: auto;
    }
    
 /*----------------- for doubt div----------------- */
  
    
     #file-progress div {
        border: none !important;
       /*  border-bottom: 0; */
        padding: .0.5em .2em;
    }
    
    #doubt-output,
    #file-progress {
        margin: 0 0 0 .4em;
        
        overflow: auto;
        clear: both;
    }
    .data-box{
        display:none; /* Conversation will not work until participants click on join */
       /*  background: #000; */
        }
    /* .data-box input {
        border: 0px solid;
        font-family: inherit;
        font-size: 1em;
        margin: .1em .3em;
        outline: none;
        padding: .1em .2em;
        width: 70%;
        float: left;
    } */
    .showbox{padding: 25px 25px 25px 45px;}
    
    #presentation_wnd{
    width: 92%;
  	height: 420px;
    background-color:#f5f5f5;
    overflow: hidden;
    display:none;
    padding: 30px 1% 25px 45px;
   
    }
    .close_presentation img
    {
    margin-top: -43px;
    margin-left: -15px;
    }
    .a
    {
    padding: 25px 25px 25px 45px;
    }
    #presentation_wnd img{
    width: 100%;
  	height: 100%;
  	}
   
    #blackboard_div
    {
     display: none; 
    }
    .n_container{
    width: 100%;
    margin: 0 auto;
    margin-top: 0px;
    }
    .main_left
    {
    width: 70%;
    float: left;
    text-align: center;
    
    
    }
    .main_right
    {
    width: 29%;
    float: right;
    }
    .session_main
    {
    width: 210px;
    margin: 0 auto;
    }
    #session-name1
    {
          padding: 0px 10px;
    height: 27px;
    font-size: 18px; 
    position: absolute;
    top: 90px;
    }
    #setup-new-session
    {
        font-size: 18px;
     }
      #emergency-button
    {
        font-size: 18px;
     }
    .zakir_abc{ padding: 80px;}
    #share_file_area { display:none; } 
    .media-container
    {
/* width:100px!important;
    	height:100px!important; */
    	margin:0px 4px 20px 4px!important;
    	}
    	.media-controls .control, .volume-control .control {
    	width:33px!important;
    	height:33px!important;
    	}
    	.volume-control .volume-slider, .media-controls .volume-slider {
    	display:none;
    }
    
    #local_videos-container .media-container:first-child
    {
       margin-right: 2%;
    }
    #a{
	color: red;
	}
#b{
 color: black;
 }
        #abc{
 color: black;
 }
     .setup_new{
     position: absolute;
         top: 65px;
    margin-left: 100px;
     }
     .room_list_session
     {
     width: 400px;
     margin: 0 auto;
     }
     .ask{    margin-top: 7px;
    margin-bottom: -7px;}
    .ask_p{text-align: left;
    border-bottom: 1px solid #c2c2c2;
    padding-left: 20px;
    }
    .itsmechat_div{
   float:right;
    padding-right: 16px;
   border: 1px solid #c2c2c2;
   border-top: 2px solid #c2c2c2;
   border-bottom: 2px solid #c2c2c2;
    /* width: 60%; */
    border-radius: 10px;
    padding: 5px 5px 5px 20px;
     border-right: 10px solid #aad7F1;
    margin-top:10px; 
        margin-left: 30%;
            font-size: 16px;
    }
    .itsyouchat
    {
    /* width: 60%;
    margin-right: 40%; */
    border-radius: 10px;
    margin-top: 10px;
    border: 1px solid #c2c2c2;
    padding: 5px 10px 5px 10px;
        font-size: 16px;
        float:left;
        border-left: 10px solid #aad7F1;
    }
    .itsyouchat span{
    color: #c2c2c2;
    }
    <!-- Added by krishna from here-->
.Participant_add ul {
	padding:0px;
	margin:0px;
	list-style:none;
}
.Participant_add ul li {
	border:none;
	padding-top: 0;
}
.Participant_add ul li a {
	font-size:12px;
	color:#333;
}
#onlineuser .Participant_add {
	display:none;
}
<!-- Added by krishna till here-->
.viewscreen
{padding: 1px 15px 5px 17px;
position: relative;
    top: 100px;
    font-size: 18px;}
    </style>
    <%
    // added by prity from here
    String sesid=request.getParameter("sesid");
    //System.out.println("session sesid:"+sesid);

    session.setAttribute("SessionID",sesid);
   /* window.onbeforeunload = function() {
    return("Are you sure you want to leave this page?");
} */

    //alert("Goodbye!");

 %>

 <%-- window.onunload = function(){goodBye()};
public void jspDestroy()
{
	System.out.println("destroy all in one jsp");
	//alert('destroy all in one jsp');
} --%>
  <script type="text/javascript">
  
   
   /*  $(document).ready(function() {
  	$(".autohide").delay(5000).fadeOut("slow");
  });    */
   
  
 /*  var dont_confirm_leave = 0; //set dont_confirm_leave to 1 when you want the user to be able to leave withou confirmation
  var leave_message = 'You sure you want to leave?'
  function goodbye(e) {
    if (!validNavigation) {
      if (dont_confirm_leave!==1) {
        if(!e) e = window.event;
        //e.cancelBubble is supported by IE - this will kill the bubbling process.
        e.cancelBubble = true;
        e.returnValue = leave_message;
        //e.stopPropagation works in Firefox.
        if (e.stopPropagation) {
          e.stopPropagation();
          e.preventDefault();
        }
        //return works for Chrome and Safari
        return leave_message;
      }
    }
  }
  window.onbeforeunload=goodbye; */
    </script>
   
   <script type="text/javascript">
              

    //window.history.forward(1);
      /* window.onbeforeunload = function() {
        return "Dude, are you sure you want to leave? Think of the kittens!";
    }  */
    document.onkeydown = function(){
	  // alert("in f5");
  switch (event.keyCode){
        case 116 : //F5 button
        	alert("Oops! F5 will not work in ClassRoom");
            event.returnValue = false;
            event.keyCode = 0;
            return false;
      case 114 : //F3 button
        	alert("Oops! F3 will not work in ClassRoom");
            event.returnValue = false;
            event.keyCode = 0;
            return false;
        case 82 : //R button
            if (event.ctrlKey){ 
            	alert("Oops! Ctrl R will not work in ClassRoom");
                event.returnValue = false;
                event.keyCode = 0;
                return false;
            }
    }
}
     
   /*  function OnBeforeUnLoad () {
        return "All data that you have entered will be lost!";
    } */
    var out_time="";
    /* function NameOfParticipant(sesid,out_time){
//alert("in ajax"+sesid);

var out_time=document.startsession.ctime.value;

		$(document).ready(function(){
			//alert("in ajax1");
		this.timer = setTimeout(function () {
			//alert("in ajax2");
			
			//alert(out_time);
		$.ajax({
		url: '../JSP/NameOfParticipants.jsp?sesid='+sesid+'&lastdtm='+out_time,
		//data: 'sesid='+sesid,
		//data: 'lastdtm='+out_time,
		
		type: 'POST',
		success: function(msg){ 
			//alert(msg);
			 var msg = msg.trim();
			 var split = msg.split(';');
	    	 var num_participants=split[0];
	    	 var participants_name=split[1];
	    	 var out_name=split[2];
	    	  out_time=split[3];
	    	  var priv=split[4];
	    	  //alert("privilege="+priv);
			setParticipantsDetails(msg);
		} }); }, 2000);

		return true;
		});
}
     function RefreshOut(){ //sesid,out_time){
    	//alert("in ajax");
 var privilege=document.startsession.previlege.value;
    	 var userid=document.startsession.username.value;
    	 var session_id=document.startsession.sesid.value;
    	//var out_time=document.startsession.ctime.value;

    			$(document).ready(function(){
    				//alert("in ajax1");
    			this.timer = setTimeout(function () {
    			$.ajax({
    			url: '/KODE_DEV/ControllerServlet/ClassRoomRefreshOut?userid='+userid+'&privilege='+privilege+'&session_id='+session_id,
    			type: 'POST',
    		 }); });

    			return true;
    			});
    	}*/
   /*  function RefreshOutAll(){ //sesid,out_time){
     	//alert("in ajax");
  var privilege=document.startsession.previlege.value;
     	 var userid=document.startsession.username.value;
     	 var session_id=document.startsession.sesid.value;
     	//var out_time=document.startsession.ctime.value;

     			$(document).ready(function(){
     				//alert("in ajax1");
     			this.timer = setTimeout(function () {
     			$.ajax({
     			url: '/KODE_DEV/ControllerServlet/ClassRoomRefreshOutAll?userid='+userid+'&privilege='+privilege+'&session_id='+session_id,
     			type: 'POST',
     		 }); });

     			return true;
     			});
     	}*/
     function loadmap(){
    	// alert("in onload");
    	 document.getElementById("end-session").style.visibility = "hidden";
    	 var privilege=document.startsession.previlege.value;
    	 //var out_time=document.startsession.ctime.value;
     	//alert("calling participant="+privilege);
     //	if(privilege.equalsIgnoreCase("Faculty")){
    	 //alert(privilege);
    	 if(privilege=="Faculty"){
//alert("in if"+document.getElementById("exit-session").style.visibility);    		 
						//document.getElementById("exit-session").style.visibility = "hidden";
     		//alert("in conn close");
     		connection.close();
     		//RefreshOutAll();
     	}
    	 //else{
        	// RefreshOut();
     		//}
    	 //document.startsession.action="/KODE_DEV/ControllerServlet/ClassRoomRefreshOut?userid="+userid+"&privilege="+privilege+"&session_id="+session_id;
    	 //alert("after action");
 		//document.startsession.submit();
 		
     	<%--  self.setInterval("NameOfParticipant('<%=sesid%>',out_time)",5000);--%>
     	
     	
    
     }
    /*  function  unloadjsp()
     {
     	alert("unload jsp");
     } */
     function setParticipantsDetails(msg){
    	 var split = msg.split(';');
    	 var num_participants=split[0];
    	 var participants_name=split[1];
    	 var out_name=split[2];
    	 var out_time=split[3];
    	 var priv=split[4];
    	 
    	 document.startsession.totaluser.value=num_participants;
    	 var previlege= document.startsession.previlege.value;
    	 //alert("priv="+priv);
    	/* var HTML = "<table style='font-family:arial; font-size:12px;'>"+
			"<tr><td style= 'width:80px;'># of Users</td><td>:</td><td>"+ num_participants+"</td></tr>"+
			"<tr><td>Users Name </td><td>:</td><td>" + participants_name +"</td></tr>"; */
		
			if(priv=="Faculty"){
			 var HTMLout = 	"<tr><td>"+ out_name +" stop the session. Please refresh if it is incomplete.</td></tr>";
			
			}
			else
			{
				 var HTMLout = 	"<tr><td>"+ out_name +" disconnected from session</td></tr>";
				 //document.getElementById("exit-session").style.visibility = "visible";
			}
			"</table>";
			/* document.getElementById('ParticipantDetails').innerHTML=HTML; */
			//alert(out_name);
			if(out_name!="" && out_name!=null){
				//document.getElementById('ParticipantOutDetails').style.visibility="visible";
document.startsession.ctime.value=out_time;
			document.getElementById('ParticipantOutDetails').innerHTML=HTMLout;
			/* if(priv=="Student"){
			document.getElementById("exit-session").style.visibility = "visible";
			}
			else
				{
				document.getElementById("exit-session").style.visibility = "hidden";
				} */
 setTimeout(function(){ 
	             $("#ParticipantOutDetails").html(" ");
	           }, 7000);
			//alert(out_time);
			}
     } 
     /* added by prity till here */
    </script>
    
    <script>
    document.createElement('article');
    </script>
    <%
    String userid=session.getAttribute("userid").toString();
    String priv=session.getAttribute("previlege").toString();
    
    String stTime="";		
    String etTime="";
//System.out.println("userid="+userid);
%>
	<script src="../JS/firebasejoin.js"></script>
    <script src="../JS/getMediaElement_min.js"> </script>
    <script src="https://cdn.webrtc-experiment.com/socket.io.js"></script>
    <script id="rtcscript" src="../JS/RTCMultiConnection_imp.js" user_id="<%=userid%>" privilege="<%=priv%>" session_id="<%=sesid%>"></script>
    </head>
<%
	LoginDetailsDb ldd=new LoginDetailsDb();
	                 
// Added by Manish to show name in Reconnecting next 5 line
Object uid=session.getAttribute("userid");
if(uid==null)
{
	response.sendRedirect("../JSP/LoginWebView.jsp");
}
String sessionName=ldd.getSessionName(session.getAttribute("userid").toString(),priv);
//System.out.println("session name:"+sessionName);
java.util.Date date= new java.util.Date();
String lastdtm=new Timestamp(date.getTime()).toString();
//System.out.println("lastdtm name:"+lastdtm);
%>



<script>
String.prototype.toHHMMSS = function () {
    var sec_num = parseInt(this, 10); // don't forget the second parm
    var hours = Math.floor(sec_num / 3600);
  //  alert(hours);
    var minutes = Math.floor((sec_num - (hours * 3600)) / 60);
    var seconds = sec_num - (hours * 3600) - (minutes * 60);

    if (hours < 10) { hours = "0" + hours; }
    if (minutes < 10) { minutes = "0" + minutes; }
    if (seconds < 10) { seconds = "0" + seconds; }
    var time = hours + ':' + minutes + ':' + seconds;
    return time;
}


function time_diff(time1, time2) {
    var t1 = new Date();
    var parts = time1.split(":");
    t1.setHours(parts[0], parts[1], parts[2], 0);
    var t2 = new Date();
    parts = time2.split(":");
    t2.setHours(parts[0], parts[1], parts[2], 0);

    return (parseInt(Math.abs(t1.getTime() - t2.getTime()) / 1000)).toString().toHHMMSS();
}





</script>

<body onload="loadmap()" style="background-size: 100%; height: 100%;">

<div class="container">
<%@include file="../JSP/headers.jsp"%>
<%@include file="../JSP/Popup.jsp"%>
<div style="clear: both;"></div>
		<%-- <div>
		<%
		//String priv=session.getAttribute("previlege").toString();
		if(priv.equalsIgnoreCase("Student")){
		%>
		<%@ include file= "../JSP/Participant_menu.jsp" %>
		<%}else{ %>
		<%@ include file= "../JSP/menus.jsp" %>
		<%}%>
		
		</div> --%>

<div style="clear: both;"></div>
<div class="session_main">
  <select id="session" title="Session" style="display: none;">
                   <!--  <option selected>audio+video+data+screen</option> -->
                    <option selected>audio+video+data</option>
                    <option>audio+video+data</option>
           	        <option>audio+video+screen</option>
                    <option>audio+video</option>
                    <option>video+data</option>
                    <option>audio</option>
                    <option>video</option>
                    <option>data</option>
                    <option>screen</option>
                </select>
                <select id="direction" title="Direction" style="display: none;">
                    <option selected>many-to-many</option>
                    <option>one-to-one</option>
                    <option>one-to-many</option>
                    <option>one-way</option>
                </select>
             <!--   <span id="session-name1"><%=sessionName %></span> -->
               <input type="hidden" style="font-size:20px;" placeholder=" Session Name" id="session-name" value="<%=sessionName %>" >
               <form name="startsession" id="startsession" method="post">
                <span class="setup_new">
                <input type="hidden" name="username" id="username" value="<%=session.getAttribute("userid") %>">
                <input type="hidden" name="previlege" id="previlege" value="<%=(String)session.getAttribute("previlege") %>">
                <input type="hidden" name="sesid" id="sesid" value="<%=sesid %>">
                 <input type="hidden" name="ctime" id="ctime" value="<%=lastdtm%>">
                 <input type="hidden" name="totaluser" id="totaluser" >
                <button id="setup-new-session" class="setup" >Start Session</button>
                <button id="end-session" class="setupend" >End Session</button> 
 <button id="exit-session" class="setupend" style="visibility:hidden; position: relative;left: 83px;">Exit Session</button>
                </span>
                </form>
                <!-- <button id="Share_screen" class="setup" >Share Screen</button> -->
                <%
			if(priv.equalsIgnoreCase("student")){%>
			<script type="text/javascript">
// Added by Manish to show name in Reconnecting next 2 line
			var username = '<%=uid%>'; 
			getName(username);
			document.getElementById("setup-new-session").style.visibility = "hidden";
			document.getElementById("end-session").style.visibility = "hidden";
			document.getElementById("session-name").style.visibility = "hidden";
			document.getElementById("direction").style.visibility = "hidden";
			document.getElementById("session").style.visibility = "hidden";
			 
			//document.getElementByClassName("blackboard_control").style.visibility = "hidden"; 
		 	$('#present').hide();
		 	/* $('.blackboard_control').hide(); */
			
       
			</script>
			<%			
			} else
			{
			%>
			 <%-- <span id="session-name1"><%=sessionName %></span>  --%>
			 <%} %>
			
</div>
<%-- <label style="visibility:hidden" id="username"><%=session.getAttribute("userid") %></label> --%>

<div style="clear: both;"></div>
<div class="room_list_session">
<table style="width: 100%;" id="rooms-list">
            </table>
</div>
<div style="clear: both;"></div>

<div class="n_container">
<div class="main_left">

<div class="classroom_settingmenu">

<ul class="menu_ul">
<li class="classroom_control"><img alt="Classroom" src="../Image/classroom_menu.jpg"> </li>
<li class="blackboard_control" id="present"><img alt="Classroom" src="../Image/blackbord_menu.png"> </li>
<li class="knowstore">
<img alt="Classroom" src="../Image/classwork.jpg"> 
</li>
<li id="presentation"><img alt="Classroom" src="../Image/presentation_menu.png"> </li>
<li id="preview"><img alt="Classroom" src="../Image/preview.png"> </li>
<li id="doubt"><img onclick="queryButton()" src="../Images/doubt.png"> </li>
<li id="emergency"><img onclick="sendEmergencyMsgToAdmin()" src="../Images/exigency.png"> </li>

</ul>
</div>
<div style="clear: both;"></div>
<!-- <div class="convnotify">
</div>
 -->
<!-- hIDE SHOW PARTS STARTS HERE -->
<div class="hideshow_main">
<!-- Class chat part starts here -->
 <div class="experiment data-box">
 
 <p class="close_chat">
<img class="close_chat_img" alt="Close me" src="../Images/close.png" /> 
 </p>
 
            
           <div class="showbox">
           <div class="chat_tab_main" style="height : 330px; background: #fff">
           <p class="ask_p" >
 		<img class="ask" alt="Close me" src="../Images/askquestion.png" />
		   </p>
       <div style="width: 100%;background: #fff; height: 292px" cellspacing="0">
                
                
                 <div class="chatting_input_outer">
                    <div class="firsttd">
                       <div id="chat-output" style="clear: both; height:80px;"></div>
                       <div class="chat_text_box">                      
            </div>
                   </div>
                   <div class="chat_enter_div" >
            <input type="text" id="chat-input" style="" placeholder="Write message..." disabled>
            <a id="send_chat"><span> <img class="chatsend" onclick="return validateForm()" src="../Images/send.png"/></span></a>
            </div> 
                   </div>
                    
                   <div class="secondtd" id="hide_question_div">
                    <% 
                     if("student".equalsIgnoreCase(priv)){
                      //System.out.println(" in faculty");//.equalsIgnoreCase((String)session.getAttribute("previlege")));
   %> 
                    <!--     <div style="width:160px;height:140px;position:relative;left:0px;margin-top: 10%;"> -->
                        <a href="javascript:queryButton();" id="img1_frame">
                      <!--   <img src="../Images/doubt2.png" id="img1"></img></a></div>  -->
                     <%}else{ %>
    <script type="text/javascript">
   var username = '<%=uid%>';  
   getName(username); // Added by Manish to show name in Reconnecting 703 n 704
   </script>
   <%} %>

                 </div>
                        <div class="thirdtd" style="width:287px; float:right; height:295px;" >       
                                         <div id="doubt-output" style="width:97%; height:295px;"></div>  
                    </div>
               
                
            </div>
           </div>
           
           
            </div>
            
        </div>
<!-- Class chat part Ends here -->


<!-- whiteboard part starts here -->
<div id="blackboard_div">
<div class="show_box2" style="padding: 25px 25px 25px 45px;">
<p class="close_blackboard" style="text-align: right;">
 <img style="" class="cls" alt="Close me" src="../Images/close.png" />
 </p>

<div id="widget-container" style="/* position: fixed; */ bottom: 0;right: 20%;left: 20%;height: 100%;border: 1px solid black; border-top:0; border-bottom: 0;"></div>
</div>
  </div>
  
    <script>
    
    var designer = new CanvasDesigner();
    
    // both links are mandatory
    // widget.html will internally use widget.js
    designer.widgetHtmlURL = "widget.html"; // you can place this file anywhere
    designer.widgetJsURL = "widget_min.js";     // you can place this file anywhere
    
    designer.addSyncListener(function(data) {
         console.log('Enters send: send the data ');
         /* alert('Enters send data : send the data'); */
            connection.send(data);
    });
 
    
    designer.setSelected('pencil');
 
    designer.setTools({
        pencil: true,
        text: true,
        image: true,
        eraser: true,
        line: true,
        arrow: true,
        dragSingle: true,
        dragMultiple: true,
        arc: true,
        rectangle: true,
        quadratic: true,
        bezier: true,
        marker: true,
            zoom: true
    });
    
   
    
 
    /* Array.prototype.slice.call(document.getElementById('action-controls').querySelectorAll('input[type=checkbox]')).forEach(function(checkbox) {
        checkbox.onchange = function() {
            designer.destroy();
 
            designer.addSyncListener(function(data) {
                connection.send(data);
            });
 
            var tools = {};
            Array.prototype.slice.call(document.getElementById('action-controls').querySelectorAll('input[type=checkbox]')).forEach(function(checkbox2) {
                if(checkbox2.checked) {
                    tools[checkbox2.id] = true;
                }
            });
            designer.setTools(tools);
            designer.appendTo(document.getElementById('widget-container'));
        };
    }) ;*/
            
    </script> 
  
<!-- whiteboard part end here -->


<!-- Know store part starts here -->
<div id="share_file_area">
 <div class="showbox">
 <div class="share_file_inneerdiv">
<!-- <p class="ask_p" >
 			<img class="ask" alt="Close me" src="../Images/share_file.png" />
		   </p>

<p class="close_share" style="text-align: right;">
 <img class="cls" alt="Close me" src="../Images/close.png" />
 </p>		   
-->

<table style="display:none">
            	<tr><td style="border: 0px solid;">
            	<table id="ineer_table">
            	<tr>
            	<td style="width: 70%; height: 35px;">
            	 <input style="position: relative;left:0px;width:70%; margin: 0px;padding:8px 0px 5px 10px !important;height:40px;font-size:14px;" type="file" id="file" disabled>
              <img style="position: relative; right: 0px;top:20px;" onclick="document.getElementById('file').onClick();" id="file" alt="Close me" src="../Images/send.png" />	</td>
            	<td>
            	<div id="file-progress" style="max-height: 125px;overflow-y: auto;"></div> 
            	</td>
            	</tr>
            	</table>
                        
                       
                        <!-- <input style="width:118px;" type="button" onclick="document.getElementById('file').onClick();" id="file"  value="Send" />  -->
                       
                    </td></tr>
            
            </table>
<p style="border-top:1px solid #c2c2c2;text-align: left; padding-left: 125px " >
 			<!-- <img onclick="document.getElementById('file').onClick();" id="file" class="ask" alt="Close me" src=".. /Images/send_file.png" /> -->
		   </p>
</div>
</div>
</div>
<!-- Know store part Ends here -->




<!-- presentation part starts here -->

<!-- <button id="share-screen" class="setup1">Share Screen</button>   
 <section class="experiment">  
            <div style="float: right;">  
                  
            </div>  
            <div id="videos-container"></div>  
           </section> -->
           
           
<div style="">
<div id="presentation_wnd">
<button id="share-screen" class="setup1">Share Screen</button>   
 <section class="experiment">  
            <div style="float: right;">  
                  
            </div>  
            <div id="videos-container"></div>  
           </section>
 <!-- <div class="presentation_inneerdiv">
<p class="close_presentation" style="text-align: right;">
 <img class="cls" alt="Close me" src="../Images/close.png" />
 </p>
<a href="#" class="close_presentation">
            <img class="cls" alt="Close me" src=".. /Images/close.png" />
            </a>

<div>
<iframe src="../web/viewer.html" height="398px" width="100%;"></iframe>
</div>
</div> -->

</div>
</div>
<!-- presentation part end here -->

<!-- starts -->



</div>
<!-- hIDE SHOW PARTS CLOSE HERE -->



<!-- <button class="classroom_control"></button>
<button id="present" class="blackboard_control" ></button>
<button class="knowstore"></button>
<button id="presentation"></button> -->
</div>
<div class="main_right" style="border: 1px solid #c2c2c2;">
<div class="camera_div">       

  <%
  //System.out.println("in strt");
 // SessionTrackingDomain sd=new SessionTrackingDomain();
  
 // String arl=(String)request.getAttribute("allParticipantDetails");//added by prity
  //System.out.println("arl="+arl);//added by prity
  
  //ArrayList<SessionTrackingDomain> arl=ldd.getLoginNames(session.getAttribute("userid").toString(), priv);
  //Iterator<SessionTrackingDomain> it=arl.iterator();
  //Iterator<SessionTrackingDomain> it1=arl.iterator();
  %>
  <div class="fac_box">
   <!-- Added by krishna from here-->	
			<div class="Participant_add">
			<Label id="ParticipantDetails"></Label>
			<!-- class="autohide" style="color:red; font-size:18px; font-weight: bold;"
			<Label style="color:red; font-size:18px; font-weight: bold;" id="ParticipantOutDetails"></Label> -->
            		<ul>
            			<%-- <li><a href="#"># of  users = <%=arl.size() %> </a>
            			<li><a href="#">Participants - <%=participants %> </a> --%>
            			<!-- <li class="reconnect_use"><a href="#">Reconnecting - </a> -->
     			<li class="noofusers"><p style="color:black; font-family:arial; font-size:12px;" id="count"> </p>
<li class="usersname" style="
    position: relative;
    top: -16px;"><p style="color:black; font-family:arial; font-size:12px;" id="b"> </p>
<!-- <li class="userdetails" style="
    position: relative;
    top: -32px;"> --><p style="color:black; font-family:arial; font-size:12px;" id="abc"> </p></li>
<li class="reconnect_use"><p style="color:red; font-size:15px;" id="ParticipantOutDetails"> </p></li>
            			<li class="reconnect_use"><p style="color:red; font-size:14px;" id="a"> </p>
            		</ul>
            </div>
<!-- Added by krishna till here-->
<div class="video-outer">
  <%
  
  //System.out.println(sd.getStudentId()+";"+sd.getPrivilege());
  //if((sd.getStudentId().equals(session.getAttribute("userid")))&&("in".equals(sd.getLoginStatus()))){
     if("faculty".equalsIgnoreCase((String)session.getAttribute("previlege"))){
%> 
<div id="onlineuser" class="testing2">
 <h2 style="display: block; font-size: 16PX; text-align: center;">FACULTY </h2>

 <!-- local/remote videos container -->
  
<div class="my_fac" id="local_videos-container" style="width:100%"></div>

<div id="local_videos-container" style="width:0%"></div>
</div>
<%}else{ %>
<div id="onlineuser" class="myview_box">
<h2 style="display: block; font-size: 16PX; text-align: center;border-right:2px solid #fff;">MY VIEW </h2>
<div id="local_videos-container" style="width:0%"></div>
<h2 style="display: block; font-size: 14PX; text-align: center;background:#999;">Participants View</h2>
</div>
<%} %>


<!-- <!-- added by manish
<h2 style="display: block; font-size: 16PX; text-align: center;">FACULTY </h2>  
<div class="my_fac" id="local_videos-container" style="width:100%"></div>  -->




   <%  
  //if((!(sd.getStudentId().equals(session.getAttribute("userid"))))&&("in".equals(sd.getLoginStatus()))){

   if("faculty".equalsIgnoreCase((String)session.getAttribute("previlege"))){
   %>
   <div id="onlineuser">
   <h2 style="display: block; font-size: 16PX; text-align: center;">Participants View</h2>
 <!-- <p id="a"> </p> commented by prity -->
   <div id="remote_videos-container" style="width:100%"></div>
   </div>
 <%
 
  }else {
 %>
 <div id="onlineuser" class="participants-view-box">
<h2 style="display: block; font-size: 16PX; text-align: center;" class="title_space">FACULTY</h2>
<!-- <p id="a"> </p> commented by prity-->
<div class="helping_space_box"></div>
<div class="faculty_outer">
<div id="remote_videos-container" style="width:100%"></div>
</div>
</div>
<%} %> 
<% if("faculty".equalsIgnoreCase(priv)){
   %>
   <script type="text/javascript">
   $('#preview').hide();
   $('#doubt').hide();
   </script>
   
   <%} %>

</div>

</div>

</div>
</div>

</div>
<div style="clear: both;"></div>

<article style="position: relative;">

        <!-- just copy this <section> and next script -->
        <section class="experiment">
            <div style="float: right;">
                
            </div>
          <section>
           </section>
         
            <!-- list of all available broadcasting rooms -->
            
<br><br>
<% if("student".equalsIgnoreCase(priv)){
			%>
			<script type="text/javascript">
			/* $('#blackboard_control').hide();
			document.getElementById("blackboard_control").style.visibility = "none";
			$('#knowstore').hide(); */
			/* document.getElementById("knowstore").style.visibility = "none"; */
			 $('#presentation').hide(); 
			document.getElementById("presentation").style.visibility = "none";
			$('#emergency').hide();
			document.getElementById("present").style.visibility = "none";
			
			
			</script>
			<%} %>
 <%		
        		        
        String ses=sesid.toUpperCase();		
        System.out.println("sesid="+ses);		
        String startTimeAndEndTime[]=ldd.getStartTimeAndEndTime(ses);		
        String startTime=startTimeAndEndTime[0];		
        String endTime=startTimeAndEndTime[1];		
        System.out.println("startTime="+startTime);		
        System.out.println("endTime="+endTime);		
        String [] st = startTime.split( " " ); 		
        stTime=st[1];		
        String [] et = endTime.split( " " ); 		
        etTime=et[1];		
        System.out.println("stTime="+stTime);		
        System.out.println("etTime="+etTime);		
        %>
</section>

        

        <script>
        // Muaz Khan     - www.MuazKhan.com
        // MIT License   - www.WebRTC-Experiment.com/licence
        // Documentation - www.RTCMultiConnection.org
      
        
       
         var board='start',chat='start';
         var globalPriv=document.startsession.previlege.value;
        var connection = new RTCMultiConnection(); //This line of code will call 43 rd line of code in RTCMulticonnection_imp.js
        /* connection.socketURL = 'https://rtcmulticonnection.herokuapp.com:443/'; */
        connection.socketMessageEvent = 'canvas-designer';
        connection.session = {
            audio: true,
            video: true,
            data: true
        };
        var roomsList = document.getElementById('rooms-list'),
            sessions = {};
        //alert("roomlist  : "+roomsList);
       // alert("Checking existing connection session "+session);
        connection.onNewSession = function(session) {
        	//alert("yes exsiting connection ");
            if (sessions[session.sessionid]) return;
            sessions[session.sessionid] = session;
            
        	 document.getElementById("session").style.display = "none";
        	document.getElementById("direction").style.display = "none";
        	document.getElementById("setup-new-session").style.display = "none";
        	//document.getElementById("end-session").style.display = "none";
        	document.getElementById("session-name").style.display = "none"; 
        	//document.getElementById("session-name1").style.display = "none";
        	
            var tr = document.createElement('tr');
           tr.innerHTML = '<td><span class="aa"><strong>' + session.extra['session-name'] + '</strong> class has started.<button class="join">Please join</button></span></td>';            roomsList.insertBefore(tr, roomsList.firstChild);
           // alert("roomlist "+roomsList.firstChild +"  data_sessionid "+session.sessionid)
            tr.querySelector('.join').setAttribute('data-sessionid',  session.sessionid);
            tr.querySelector('.join').onclick = function() {
            	
            	
            	
            	
            	
            	document.getElementById("end-session").style.visibility = "hidden";
            	ClassRoomIn();
                this.disabled = true;
                session = sessions[this.getAttribute('data-sessionid')];
                
            
             
                if (!session) alert('No room to join.');
                connection.join(session);
                
              
              	document.getElementById("time-bar").style.visibility = "visible";		
              	document.getElementById("stopwatch-duration").style.visibility = "visible";
              			
           
         	
         	<%
         	LoginDao ld=new LoginDao();

         	String startTime1=ld.getData(sesid,userid);
         	System.out.println("time for join"+startTime1);
         
         	%>
         	
         	  var sttime1="<%=startTime1%>";
         	 console.log('start timeeee=',sttime1);
         	 
         	  var d = new Date(); // for now       			
           	d.getHours(); // => 9		
           	d.getMinutes(); // =>  30		
           	d.getSeconds(); // => 51		
           	 var min=d.getMinutes();		
           	 var sec=d.getSeconds();
         	 var second=d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
         	//alert("db time="+sttime1);
         	//alert("Current time="+second);
         	
         	    		 var retTime=time_diff(second,sttime1);
         		
         	
var joinTime=retTime.split(":");
hour=joinTime[0];
if(hour=='00')
	hour='0';
min=joinTime[1];
if(min=='00')
	min='0';
sec=joinTime[2];
              			
                  //for timer		
                  function add() {		
                      sec++;		
                      if (sec >= 60) {		
                          sec = 0;		
                          min++;		
                          if (min >= 60) {		
                              min = 0;		
                              hour++;		
                          }		
                      }		
                   		
                      h1.textContent = (hour ? (hour > 9 ? hour : "0" + hour) : "00") + ":" + (min ? (min > 9 ? min : "0" + min) : "00") + ":" + (sec > 9 ? sec : "0" + sec);		
                      timer();		
                  }		
                  		
                  function timer() {		
                      t = setTimeout(add, 1000);		
                    }		
                  timer();
            };
        };
        
        var localvideosContainer = document.getElementById('local_videos-container') || document.body;
        var remotevideosContainer = document.getElementById('remote_videos-container')|| document.body;
       
     
        connection.onstream = function(e) {
        	
            var buttons = ['mute-audio', 'mute-video', 'record-audio', 'record-video', 'full-screen', 'volume-slider'];
            if (connection.session.audio && !connection.session.video) {
                buttons = ['mute-audio', 'full-screen'];
            }
            
			var type = e.type;
		
		
            var mediaElement = getMediaElement(e.mediaElement, {
            	
                width: (localvideosContainer.clientWidth / 2) - 50,
                title: e.userid,
                buttons: buttons,
                onMuted: function(type) {
                    connection.streams[e.streamid].mute({
                        audio: type == 'audio',
                        video: type == 'video'
                    });
                },
            /*     onUnMuted: function(type) {
                    connection.streams[e.streamid].unmute({
                        audio: type == 'audio',
                        video: type == 'video'
                    });
                }, */
                
                onUnMuted: function(type) {
                    connection.streams[e.streamid].unmute({
                        audio: type == 'audio'
                    });
                    connection.streams[e.streamid].mediaElement.muted = true; // self audio muted on DOM  
                    connection.streams[e.streamid].mediaElement.volume = 0;    //
                },
                onRecordingStarted: function(type) {
                    // www.RTCMultiConnection.org/docs/startRecording/
                    connection.streams[e.streamid].startRecording({
                        audio: type == 'audio',
                        video: type == 'video'
                    });
                },
                onRecordingStopped: function(type) {
                    // www.RTCMultiConnection.org/docs/stopRecording/
                    connection.streams[e.streamid].stopRecording(function(blob) {
                        if (blob.audio) connection.saveToDisk(blob.audio);
                        else if (blob.video) connection.saveToDisk(blob.video);
                        else connection.saveToDisk(blob);
                    }, type);
                },
                onStopped: function() {
                    connection.peers[e.userid].drop();
                }
                
            });
            
            var element=document.getElementById(e.streamid)
            if (element) 
            	{
           	element.parentNode.removeChild(element);  
           	$(".media-box:empty").parent().hide();
           	
            	}
            e.mediaElement.id = e.streamid;
            
            if(type=='local' && globalPriv=='Faculty' ){
                localvideosContainer.before(mediaElement, localvideosContainer);
               }
            else if(type=='local' && globalPriv=='Student')
            	{
            	 
            	  buttons = ['mute-audio'];
                  
                  var mediaElement1 = getMediaElement(e.mediaElement, {
                      
                         width: (localvideosContainer.clientWidth / 2) - 50,
                         title: e.userid,
                         buttons: buttons,
                         onMuted: function(type) {
                             connection.streams[e.streamid].mute({
                                 audio: type == 'audio',
                                 video: type == 'video'
                             });
                         },
                                        
                         onUnMuted: function(type) {
                             connection.streams[e.streamid].unmute({
                                 audio: type == 'audio'
                             });
                             connection.streams[e.streamid].mediaElement.muted = true; // self audio muted on DOM  
                             connection.streams[e.streamid].mediaElement.volume = 0;    //
                         }
                        
                  })
                  
                  localvideosContainer.before(mediaElement1, localvideosContainer);
            	}
               
            else if(type=='remote' &&  globalPriv=='Faculty'){
   buttons = ['stop','full-screen'];
 
                   var mediaElement1 = getMediaElement(e.mediaElement, {
                       
                          width: (localvideosContainer.clientWidth / 2) - 50,
                          title: e.userid,
                          buttons: buttons,
                          onMuted: function(type) {
                              connection.streams[e.streamid].mute({
                                  audio: type == 'audio',
                                  video: type == 'video'
                              });
                          },
                                         
                          onUnMuted: function(type) {
                              connection.streams[e.streamid].unmute({
                                  audio: type == 'audio'
                              });
                              connection.streams[e.streamid].mediaElement.muted = true; // self audio muted on DOM  
                              connection.streams[e.streamid].mediaElement.volume = 0;    //
                          },
                          onStopped: function() {
                          	
                            
                        	  var retVal = prompt("Enter reason ");  
                        	  var userid=e.userid.split("-",1);     
                        	  var session_id=document.startsession.sesid.value;    
                        	  RemoveUser(retVal,userid,session_id);
                              connection.eject(e.userid);
                                                          

                              }, onUnMuted: function(type) {
                                  connection.streams[e.streamid].unmute({
                                      audio: type == 'audio'
                                  });
                                  connection.streams[e.streamid].mediaElement.muted = true; // self audio muted on DOM  
                                  connection.streams[e.streamid].mediaElement.volume = 0;    //
                              }
                   })
                   
                           remotevideosContainer.before(mediaElement1, remotevideosContainer);
                 
               }
            else
            	{
            	buttons = [];
                
                var mediaElement1 = getMediaElement(e.mediaElement, {
                    
                       width: (localvideosContainer.clientWidth / 2) - 50,
                       title: e.userid,
                       buttons: buttons,
                                     })
                
                        remotevideosContainer.before(mediaElement1, remotevideosContainer);
             
            	
            	}
          
            
            if (e.type == 'local') {
                mediaElement.media.muted = true;
                mediaElement.media.volume = 0;
            }
            
            
           
        };
//for presentation
 <%-- var username = '<%=uid%>'; --%>
             //alert(username);
            // getName1(username);
             //alert("after cll");
               function readURL(input) {
                   if (input.files && input.files[0]) {
                       var reader = new FileReader();
                       
                       reader.onload = function (e) {
                           $('#blah').attr('src', e.target.result);
                       }
                       
                       reader.readAsDataURL(input.files[0]);
                   }
               }

               $("#imgInp").change(function(){
                   readURL(this);
               });
               //presentation end

        connection.onstreamended = function(e) {
        	//document.getElementById('Share_screen').disabled = false; // if stop Screen Sharing        	
            if (e.mediaElement.parentNode && e.mediaElement.parentNode.parentNode && e.mediaElement.parentNode.parentNode.parentNode) {
                e.mediaElement.parentNode.parentNode.parentNode.removeChild(e.mediaElement.parentNode.parentNode);
            }
        };
          
        function saveTime(sessionId,userid)      
        {                     
       // 	alert(sessionId);
        //	alert(userid);
        	$(document).ready(function(){    
        		this.timer = setTimeout(function () 
        				{   
        			$.ajax({      
        				type: "POST",    
        				url: "/KODE_DEV/ControllerServlet/SaveTimeForTimer?userid="+userid+"&sessionid="+sessionId, 
        						dataType: "json",
        						});
        			});
        		return true;
        		}); 
        	} 
        
        
        function sendEmergencyMsgToAdmin()
        {
        	var userid= document.startsession.username.value;
            var previlege= document.startsession.previlege.value;
            var sesid= document.startsession.sesid.value;
                	$(document).ready(function(){
               		this.timer = setTimeout(function () {
                  		$.ajax({
            			type: "POST",  
            		url: "/KODE_DEV/ControllerServlet/SendEmergencyMsgToAdmin?userid="+userid+"&privilege="+previlege+"&sesid="+sesid,
            		 dataType: "json",
            	 }); });

            		return true;
            		});
        }
        
        
        function ClassRoomIn()
        {
 	var userid= document.startsession.username.value;
    var previlege= document.startsession.previlege.value;
    var sesid= document.startsession.sesid.value;
        	$(document).ready(function(){
    			//alert("in ajax1");
    		this.timer = setTimeout(function () {
    			//alert("in ajax2");
    		$.ajax({
    			type: "POST",  
    		url: "/KODE_DEV/ControllerServlet/ClassRoomIn?userid="+userid+"&privilege="+previlege+"&sesid="+sesid,
    		 dataType: "json",
    	 }); });

    		return true;
    		});
        
        	//alert("in function ClassRoomInOut");
        /* var userid= document.startsession.username.value;
        var previlege= document.startsession.previlege.value;
        var sesid= document.startsession.sesid.value;
        	//alert("username"+userid +",previlege="+previlege);
        	// alert("in function"+sesid);
        	 document.startsession.action="/KODE_DEV/ControllerServlet/ClassRoomIn?userid="+userid+"&privilege="+previlege+"&sesid="+sesid;
        	 //alert("after action");
     		document.startsession.submit();
         */
        }
        
        
        function RemoveUser(reason,userid,sessionId)      
        {                     
        	$(document).ready(function(){    
        		this.timer = setTimeout(function () 
        				{   
        			$.ajax({      
        				type: "POST",    
        				url: "/KODE_DEV/ControllerServlet/RemoveUsersFromSession?reason="+reason+"&userid="+userid+"&sessionid="+sessionId, 
        						dataType: "json",
        						});
        			});
        		return true;
        		}); 
        	} 
        			
        
        
        function ClassRoomOut()
        {
        	//alert("in function ClassRoomInOut");
        var userid= document.startsession.username.value;
        var previlege= document.startsession.previlege.value;
        	//alert("username"+userid +",previlege="+previlege);
        	// alert("in function"+uInfo.getPrevilege());
        	 document.startsession.action="/KODE_DEV/ControllerServlet/ClassRoomOut?userid="+userid+"&privilege="+previlege;
        	 //alert("after action");
     		document.startsession.submit();
        	
        }
       /*  function EndSession_Popup(){
        	alert("end ses");
        	ModalPopups.Confirm("jsAlert1","<center><div style='padding-left:45px;'>Confirmation </div></center>",
                	"<br> Do you want to Close the Session ? <br><br><br>",
                    {
                            titleBackColor: "#e26e33",
                            titleFontColor: "#fff",
                            popupBackColor: "#f1fafd",
                            popupFontColor: "black",
                            footerBackColor: "#e26e33",
                            footerFontColor: "white",
                            fontFamily: "Arial", 
                            fontSize: "12px",               
                            yesButtonText: "Delete",
                            noButtonText: "Cancel",
                            onYes: "DeletePopupConfirmYes()",
                            onNo: "DeletePopupConfirmNo()"
                    }
                	//alert("after");
                );
        	alert("after");
        }
        function DeletePopupConfirmYes() {
        	alert("yes");
        	connection.close();
        }

        function DeletePopupConfirmNo() {
           ModalPopups.Close("jsAlert1");
        } */
        var endSess = document.getElementById('end-session');
         endSess.onclick=function(){
        	// alert("in end");
        	//document.getElementById("exit-session").style.visibility = "visible";
        	 //EndSession_Popup();
        	 connection.close();
 window.location.href="../JSP/FacultyView.jsp";
 
 return false ;
       }
         
        
         
         
        
         
         
         
         
         
        var setupNewSession = document.getElementById('setup-new-session');
      
        setupNewSession.onclick = function() {
        	 var userid= document.startsession.username.value;
        	 var session_id=document.startsession.sesid.value;
         saveTime(session_id,userid);
	var totaluser=document.startsession.totaluser.value;
	
				document.getElementById("end-session").style.visibility = "visible";
				document.getElementById("stopwatch-duration").style.visibility = "visible";
				
				  	ClassRoomIn();
        	 
            setupNewSession.disabled = true;
          
            var direction = document.getElementById('direction').value;
            var _session = document.getElementById('session').value;
            var splittedSession = _session.split('+');
          
            var session = {};
            
            for (var i = 0; i < splittedSession.length; i++) {
            	
                session[splittedSession[i]] = true;
            }
            var maxParticipantsAllowed = 256;
            if (direction == 'one-to-one') maxParticipantsAllowed = 1;
            if (direction == 'one-to-many') session.broadcast = true;
            if (direction == 'one-way') session.oneway = true;
            var sessionName = document.getElementById('session-name').value;
            connection.extra = {
                'session-name': sessionName || 'Anonymous'
            };
           
            connection.session = session;
            connection.maxParticipantsAllowed = maxParticipantsAllowed;
           
            connection.sessionid = sessionName || 'Anonymous';
            connection.open();
            
            
            document.getElementById("time-bar").style.visibility = "visible";		
          <%--   var test= "<%=stTime%>";		
        	var split = test.split(':');		
        	var hr=split[0];		
        	var min1=split[1];		
        	var sec1="00";		
        	//alert(test);		
        			
        	  var d = new Date(); // for now       			
   	d.getHours(); // => 9		
   	d.getMinutes(); // =>  30		
   	d.getSeconds(); // => 51		
   	 var min=d.getMinutes();		
   	 var sec=d.getSeconds();		
   			
   	 min=min-min1;		
   	 sec=sec-sec1;		 --%>
        	 		
        			
            //for timer		
            function add() {		
                seconds++;		
                if (seconds >= 60) {		
                    seconds = 0;		
                    minutes++;		
                    if (minutes >= 60) {		
                        minutes = 0;		
                        hours++;		
                    }		
                }		
             		
                h1.textContent = (hours ? (hours > 9 ? hours : "0" + hours) : "00") + ":" + (minutes ? (minutes > 9 ? minutes : "0" + minutes) : "00") + ":" + (seconds > 9 ? seconds : "0" + seconds);		
                timer();		
            }		
            		
            function timer() {		
                t = setTimeout(add, 1000);		
              }		
            timer();
        };
    
var exitSess = document.getElementById('exit-session');
        exitSess.onclick=function(){
        	//alert("in back");
        	window.location.href="../JSP/ParticipantViewHeader.jsp";
        	return false;
        } 

        function chatNotify(msg,duration) { //function added by Manish
            var el = document.createElement("div");
      //  alert(msg+duration);
            el.setAttribute("style","position:absolute;top:120px;left:100px;background-color:#59BE3A;color:#fff;padding:0px 13px;font-size:14px;");// added by Krishna for notification            el.innerHTML = msg;
            //el.setAttribute("style","position:absolute;top:40%;left:4%;background-color:green;");
            el.innerHTML = msg;
            setTimeout(function(){
             el.parentNode.removeChild(el);
            },duration);
            document.body.appendChild(el);
           }
        function screenNotify(msg,duration) { //function added by Manish
            var el = document.createElement("div");
      //  alert(msg+duration);
            el.setAttribute("style","position:absolute;top:124px;left:713px;background-color:#59BE3A;color:#fff;padding:0px 10px;font-size:12px;");// added by Krishna for notification            el.innerHTML = msg;
            //el.setAttribute("style","position:absolute;top:40%;left:4%;background-color:green;");
            el.innerHTML = msg;
            setTimeout(function(){
             el.parentNode.removeChild(el);
            },duration);
            document.body.appendChild(el);
           }
        
        function screenshareNotify(msg,duration) { //function added by Manish		
            var el = document.createElement("div");		
      //  alert(msg+duration);		
            el.setAttribute("style","position:absolute;top:124px;left:713px;background-color:#59BE3A;color:#fff;padding:0px 10px;font-size:12px;");// added by Krishna for notification            el.innerHTML = msg;		
            //el.setAttribute("style","position:absolute;top:40%;left:4%;background-color:green;");		
            el.innerHTML = msg;		
            setTimeout(function(){		
             el.parentNode.removeChild(el);		
            },duration);		
            document.body.appendChild(el);		
           }
        
        function chatWhitebord(msg,duration) { //function added by Manish     
        	var el = document.createElement("div");       
        el.setAttribute("style","position:absolute;top:140px;left:270px;background-color:#59BE3A;color:#fff;padding:0px 13px;font-size:14px;");// added by Krishna for notification 
        el.innerHTML = msg;   
        el.innerHTML = msg;   
        setTimeout(function(){  
        	el.parentNode.removeChild(el);   
        	},duration);     
        document.body.appendChild(el);   
        }

        function chatDoubt(msg,duration) { //function added by Manish     
        	var el = document.createElement("div");       
        	 el.setAttribute("style","position:absolute;top:124px;left:1181px;background-color:#C70039;color:#fff;padding:0px 10px;font-size:12px;");// added by Krishna for notification            el.innerHTML = msg; 
        el.innerHTML = msg;   
        el.innerHTML = msg;   
        setTimeout(function(){  
        	el.parentNode.removeChild(el);   
        	},duration);     
        document.body.appendChild(el);   
        }
        
        
        connection.onmessage = function(e) { // doubt as well as normal msg
        	// alert('onmessage');
        	 console.log('on message triggered');
             if(e.data === 'plz-sync-points') {
            	
             
                 console.log('plz-syn-points');
                 designer.sync();
                 if(globalPriv=='Student')   
            		 chatWhitebord("click on board!",20000);	 
                 return;
             }
  
             designer.syncData( e.data );
        	
        	
        	
        	var subString  = e.data;
         	var e_data =subString;
         	subString = subString.substring(0,7);
         //	alert(subString);
         	if(subString =='/%doubt'){ // this is for Doubt
         		
         		subString =e_data.substring(7,e_data.length);
         	//	alert(subString);
         		chatDoubt(subString,50000);
         		// appendDIV_doubt(subString);
        	} else if (subString =='/%white') {
         		        $('#present').trigger('click'); 
            
               var stringbits = e.data.split(/(\s+)/);
             if(stringbits[stringbits.length-5]=="\C7")
            	   appendtext(stringbits[stringbits.length-4]);
 else if(stringbits[stringbits.length-5]=="\F1")
                appendtext("\n");
               else if(stringbits[stringbits.length-5]!="/%white")
              appendtext(stringbits[stringbits.length-5]+stringbits[stringbits.length-4]);
         	}
         	else if (subString =='/%conve') {
         		//subStringc=e.data.substring(e.data.length-1);
         		//appendDIV(stringbits[stringbits.length-3]);
         		var value=e.data;
         		//alert("value="+value);
         		var first=value.substring(7);
         	//	alert("first="+first);
         		//alert("firstlll="+first);
         		var actual=first.substring(0,first.length - 8);
         		//alert("actual="+actual);
             appendDIV(actual); // Chatting 
             //Added by Manish
                 chatNotify("New message!",6000); 
            
             
            }
             console.debug(e.userid, 'posted', e.data);
             console.log('latency:', e.latency, 'ms');
            };
           
        connection.onclose = function(e) {
       	
           /*   appendDIV('Data connection is closed between you and '+ e.userid);  */
             
        	
        };
       
        connection.onleave = function(e) {
           <%--  appendDIV( <%= session.getAttribute("userid") %>+ '--- left the session.'); --%>
           /*  appendDIV(e.userid + '--- left the session.'); */
        };
        // on data connection gets open
        connection.onopen = function() {
        	//alert("hai");
            if (document.getElementById('chat-input')) document.getElementById('chat-input').disabled = false;
            if (document.getElementById('file')) document.getElementById('file').disabled = false;
            if (document.getElementById('open-new-session')) document.getElementById('open-new-session').disabled = true;
          //sathis changes starts here
            $(document).ready(function(){
            	//alert("in ready");
            	//ClassRoom slider controls starts here
           	 $('.classroom_control').click(function(){
           	 	var privilege=document.startsession.previlege.value;
        		//alert(privilege);
        		var view=document.getElementById("view");
        		if(view!=null){
        			if(privilege=="Student" || privilage=="student"){
//alert("in if"+document.getElementById("view").style.display);        			
        		if(document.getElementById("view").style.display=='block'){
//alert("in if1.....");        			
           		 	document.getElementById("view").style.display="none";
           		 };
        		}; 
        		}
        		//alert("alter alert");
           		$('.data-box').slideDown();
                 $("#blackboard_div").slideUp();
                 $("#share_file_area").slideUp();
                $("#presentation_wnd").slideUp();
                board='end';
           	 });
           	//classroom controls ends here
            	
             	//knowstore slider controls starts here
            	$('.knowstore').click(function()
            	 	{
            		 var privilege=document.startsession.previlege.value;
            		//alert(privilege);
 var session_id=document.startsession.sesid.value;
            		 //alert(session_id);
            		if(privilege=="Faculty")
            			{
            			//alert("in if");
            			faculty_classwork_popup(session_id);
            			}
            		else if(privilege=="Student")
            			{
            			//alert("in else");
            			student_classwork_popup(session_id);
            			}
            		var view=document.getElementById("view");
        		if(view!=null){
            		if(privilege=="Student" || privilage=="student"){
            		if(document.getElementById("view").style.display=='block'){
               		 	document.getElementById("view").style.display="none";
               		 };
            		}; 
        		}
            		$('#share_file_area').slideDown();
                    $(".data-box").slideUp();
                    $("#blackboard_div").slideUp();
                    $("#presentation_wnd").slideUp();
           
            	});
            	
            	//KnowStore slider controls ends here
            	
            	//Blackboard slider controls starts here
            	var i = 0;
            	$('.blackboard_control').click(function()
            			{
            		  var privilege=document.startsession.previlege.value;
            		//alert(privilege);
            		var view=document.getElementById("view");
        		if(view!=null){
            		if(privilege=="Student" || privilage=="student"){
            		if(document.getElementById("view").style.display=='block'){
               		 	document.getElementById("view").style.display="none";
               		 };
            		}; 
        		}
                	$('#blackboard_div').slideDown();
            		$(".data-box").slideUp();
                    $("#share_file_area").slideUp();
                   $("#presentation_wnd").slideUp();
                   if(i++ == 0) {
                       $('iframe').attr('src', $('iframe').attr('src'));
                   }
                  
                   if(designer.pointsLength <= 0) {
                       console.log('Sending the points');
                       // make sure that remote user gets all drawings synced.
                       setTimeout(function() {
                           connection.send('plz-sync-points');
                       }, 1000);
                   }
                   chat='end';
                   board='start';
           
            	});
            	//Blackboard slider controls Ends here	
            	
            		//Presentation slider controls starts here
            		
            	
            	/*  $('#doubt').click(function()
                  	 	{
            		
                  	 	}); */
           
 $('#presentation').click(function()
                 	 	{
     

	 document.getElementById("share-screen").style.visibility="visible";
            		
            		 /* $('#presentation_wnd').slideDown();
            		 $(".data-box").slideUp();
                     $("#blackboard_div").slideUp();
                     $("#share_file_area").slideUp(); */
                     
            		 console.log('privailage');
                     var privilage=document.startsession.previlege.value;
                     console.log('privailage ', privilage)
                     var sharescreen = document.getElementById("share-screen").disabled;
                           console.log(sharescreen)
                     $('#presentation_wnd').slideDown();
                     if(privilage == 'Faculty'&& sharescreen==true){
                     
                      document.getElementById("share-screen").disabled = false;
                      $('#videos-container').remove();
                      
                      
                      /*  document.getElementById("presentation").style.visibility = 'visible'; 
                      
                               var roomName = document.getElementById('room-name') || { };
                               roomName.disabled = true;
                               captureUserMedia(function() {
                                   conferenceUI.createRoom({
                                       roomName: (roomName.value || 'Anonymous') + ' shared his screen with you'
                                   });
                               });
                               this.disabled = true; */
                     
                     }
                     if(privilage == "Student" || privilage=="student") {
                      document.getElementById("share-screen").style.visibility = "hidden";
                     }
                     $(".data-box").slideUp();
                           $("#blackboard_div").slideUp();
                           $("#share_file_area").slideUp();
                   });
            	 $('#preview').click(function()
                         {
            		 document.getElementById("view").style.display = "block";
                         document.getElementById("share-screen").style.visibility = "hidden";
                        $(".data-box").slideUp();
                              $("#share_file_area").slideUp();
                             $("#presentation_wnd").slideDown();
                      $("#blackboard_div").slideUp();
                       });
            	//Presentation slider controls ends here
            });
            //sathis changes end here
            
    
            
        };
        var progressHelper = {};
        connection.autoSaveToDisk = false;
        connection.onFileProgress = function(chunk) {
            var helper = progressHelper[chunk.uuid];
            helper.progress.value = chunk.currentPosition || chunk.maxChunks || helper.progress.max;
            updateLabel(helper.progress, helper.label);
        };
        connection.onFileStart = function(file) {
            var div = document.createElement('div');
            div.title = file.name;
            div.innerHTML = '<label>0%</label> <progress> </progress>';
           appendDIV(div, fileProgress);
            progressHelper[file.uuid] = {
                div: div,
                progress: div.querySelector('progress'),
                label: div.querySelector('label')
                };
            progressHelper[file.uuid].progress.max = file.maxChunks;
        };
        connection.onFileEnd = function(file) {
            progressHelper[file.uuid].div.innerHTML = '<a href="' + file.url + '" target="_blank" download="' + file.name + '">' + file.name + '</a>'  <%-- + '<%= session.getAttribute("userid") %>' --%>;
        };
        function updateLabel(progress, label) {
            if (progress.position == -1) return;
            var position = +progress.position.toFixed(2).split('.')[1] || 100;
            label.innerHTML = position + '%';
        }
        function appendDIV(div, parent) {
       
            if (typeof div === 'string') 
            {
                var content = div;

               
             /*   if (content.contains(me)) {
            	   alert("left");
            	}
               else {
            	   alert("right");
            	} */
                div = document.createElement('div');
                div.innerHTML = content;
              }   
             if (!parent) chatOutput.before(div);//insertBefore(div, chatOutput.firstChild);
            else fileProgress.insertBefore(div, fileProgress.firstChild);         
             div.focus();
             div.tabIndex = 0;  
        }
       
        // For doubt 
        function appendDIV_doubt(div1, parent1) { 
        	//alert("second append div");
        	
            //if (typeof div1 === 'string') {
                var content1 = div1;
                content1 = content1.substring(0,7);
                if(content1 == '/%doubt'){
                	content1 = div1.substring(7,div1.length);
                }
                else{
                	content1 = div1;
                }
                //alert("after substring "+content1);
                 div1 = document.createElement('div'); 
                div1.innerHTML = content1;
                
               //	alert('div id has created');
           // }
            if (!parent1) doubtOutput.insertBefore(div1, doubtOutput.firstChild);
            else fileProgress.insertBefore(div1, fileProgress.firstChild);
            div1.tabIndex = 0;
            //div.tabIndex.style.color = blue;
            div1.focus();
        }
        document.getElementById('file').onClick = function() {
            connection.send(this.files[0]);
            
        };
        var chatInput = document.getElementById('chat-input');
        //alert("hello...."+chatInput);
        var chatOutput = document.getElementById('chat-output'),
        //alert("hello...."+chatOutput);
            fileProgress = document.getElementById('file-progress');
       
        var doubtOutput = document.getElementById('doubt-output');
        /* var whilteBoardInput = document.getElementById('blackboard_control_board');  */
        chatInput.onkeyup = function(e) {
        	 var text ;
        	 text = this.value;
            if (e.keyCode == 13)
            	{
            	var text = document.getElementById('chat-input').value;
                if (text == null || text == "") {
                    alert("Please enter the text");
                    return false;
                }
            else
            	{
            appendDIV("<div class='itsmechat_div'><p class='me_p'>Me</p><p class='itsmechat_msg'><span>" +text + "</span></p></div><div style='clear:both'></div>");
            /* 
             appendDIV("me: " +text); */
            // sending text message
            var textForConv="<div class='itsyouchat'><span>"+ "<%= session.getAttribute("username") %> : " + "</span>" +text + "</div>"+"<div style='clear:both'></div>";
            var conv="/%\conve"+textForConv+"conve .!";
                   console.log("conv="+conv);
           //        connection.send(white);
           <%-- connection.send("<div class='itsyouchat'><span>"+ "<%= session.getAttribute("username") %> : " + "</span>" +text + "</div>"+"<div style='clear:both'></div>conve .!"); --%>
           connection.send(conv);
            $(".firsttd").animate({ scrollTop: $(document).height() }, "slow");
           this.value = ''; 
           	}
           	}
            //document.getElementById('chat-input').value="";
            
        };
        $('#send_chat').click(function() {
        	var text = document.getElementById('chat-input').value;
             if (text == null || text == "" || text == " ") {
                 alert("Please enter the text");
                 return false;
             }
        	 
             else
            	 {
        	<%--  appendDIV("<div class='itsmechat_div'><p class='me_p'>Me</p><p class='itsmechat_msg'><span>" +text + "</span></p></div><div style='clear:both'></div>");
            // sending text message
        	 connection.send("<div class='itsyouchat'><span>"+ "<%= session.getAttribute("userid") %> : " + "</span>" +text + "</div>"+"<div style='clear:both'></div>");
        	 $(".firsttd").animate({ scrollTop: $(document).height() }, "slow");
            	 $('#chat-input').val(''); --%>
 appendDIV("<div class='itsmechat_div'><p class='me_p'>Me</p><p class='itsmechat_msg'><span>" +text + "</span></p></div><div style='clear:both'></div>");
                 
                 
                 var textForConv="<div class='itsyouchat'><span>"+ "<%= session.getAttribute("username") %> : " + "</span>" +text + "</div>"+"<div style='clear:both'></div>";
                    var conv="/%\conve"+textForConv+"conve .!";
                           console.log("conv="+conv);
                   //        connection.send(white);
                   <%-- connection.send("<div class='itsyouchat'><span>"+ "<%= session.getAttribute("username") %> : " + "</span>" +text + "</div>"+"<div style='clear:both'></div>conve .!"); --%>
                   connection.send(conv);
                   
                    $(".firsttd").animate({ scrollTop: $(document).height() }, "slow");
                 //  this.value = ''; 
                 //  text.val("");
                  // $(".chat-input").val("");
                  $("#chat-input").val("");
            	 }
           });
       
        function validateForm() {
/* alert("send");        	
           document.getElementById("chat-input").value=""; */
        }
        
        var question =null;
        function queryButton()
		{
        	//alert('hello');
        	question = "/%\doubt"+"<%=session.getAttribute("username") %> "+"has doubt .!" ;
        	
        	//chatNotify(question,5000);
        	
        	//alert(question);
        	//appendDIV_doubt(question);
         //   connection.onmessage(question);
          connection.send(question);
            
             this.value = '';
		};
        connection.connect();
        </script>
<script>
$(document).ready(function(){
    
	 designer.appendTo(document.getElementById("widget-container"));
	$(".close_chat").click(function(){
        $(".data-box").slideUp();
        //$('.cls').hide();
    });
    $(".close_blackboard").click(function(){
        //$('.cls').hide();
          $("#blackboard_div").slideUp();
          chat='start';
          board='end';
      });
    $(".close_share").click(function(){
	     //$('.cls').hide();
        $("#share_file_area").slideUp();
    });
 $(".close_presentation").click(function(){
	 //$('.cls').hide();
     $("#presentation_wnd").slideUp();
 });
 
});

</script>
     
  
       <script>
 function intallFirefoxScreenCapturingExtension() {
                    InstallTrigger.install({
                        'Foo': {
                            // URL: 'https://addons.mozilla.org/en-US/firefox/addon/enable-screen-capturing/',
                            URL: 'https://addons.mozilla.org/firefox/downloads/file/355418/enable_screen_capturing_in_firefox-1.0.006-fx.xpi?src=cb-dl-hotness',
                            toString: function() {
                                return this.URL;
                            }
                        }
                    });
                }

            
                var videosContainer = document.getElementById("videos-container") || document.body;
                var roomsList = document.getElementById('rooms-list');

                var screensharing = new Screen();

                screensharing.onscreen = function(_screen) {
                	screenNotify('Screen shared, please click PREVIEW.',4000);
                    var alreadyExist = document.getElementById(_screen.userid);
                    if (alreadyExist) return;

                    if (typeof roomsList === 'undefined') roomsList = document.body;

                    var tr = document.createElement('tr');

                    tr.id = _screen.userid;
                 //   tr.innerHTML = '<td>' + _screen.userid + ' shared his screen.</td>' +
                       tr.innerHTML = '<td><button class="viewscreen" id="view" style="display:none; position: absolute;top: 197px;"">View Screen</button></td>';
                    roomsList.insertBefore(tr, roomsList.firstChild);

                    var button = tr.querySelector('.viewscreen');
                    button.setAttribute('data-userid', _screen.userid);
                    button.setAttribute('data-roomid', _screen.roomid);
                   // var viewscreen = document.getElementById('view');
                    //alert(viewscreen);
                    button.onclick = function() {
                    	 document.getElementById("view").style.display = "none";                  	
                    	//alert("in");
                        var button = this;
                      //  button.disabled = true;

                        var _screen = {
                            userid: button.getAttribute('data-userid'),
                            roomid: button.getAttribute('data-roomid')
                        };
                        screensharing.view(_screen);
                    };
                };

                // on getting each new screen
                screensharing.onaddstream = function(media) {
                	
              	  var privilege=document.startsession.previlege.value;
                		
                    media.video.id = media.userid;

                    var video = media.video;
                    if(privilege=='Faculty')		
                	{
                    video.setAttribute('style', "height:0px;");		
                    screenshareNotify("Your screen has been shared",30000);		
                    	}		
                    else{		
                    video.setAttribute('controls', false);
                    video.setAttribute('style', "height:360px;");
                    }
                    videosContainer.insertBefore(video, videosContainer.firstChild);
                    video.play();
                   // rotateVideo(video);
                };

                // using firebase for signaling
                // screen.firebase = 'signaling';

                // if someone leaves; just remove his screen
                screensharing.onuserleft = function(userid) {
                    var video = document.getElementById(userid);
                    if (video && video.parentNode) video.parentNode.removeChild(video);
                };

                // check pre-shared screens
                screensharing.check();

               /*  document.getElementById('share-screen').onclick = function() {
                	alert('176');
                    screensharing.share();
                    this.disabled = true;
                }; */

                document.getElementById('share-screen').onclick = function() {
                	//alert('182');
                	document.getElementById("share-screen").style.visibility = "hidden";
                    //var username = document.getElementById('user-name');
                   // username.disabled = this.disabled = false;

                    screensharing.isModerator = true;
                   // screensharing.userid = username.value;

                    screensharing.share();
                   /*  var privilege=document.startsession.previlege.value;
                    if(privilege=="Student" || privilage=="student")
                    	screenNotify('Screen shared, please click PREVIEW.',4000); */
                };

          /*       function rotateVideo(video) {
                    video.style[navigator.mozGetUserMedia ? 'transform' : '-webkit-transform'] = 'rotate(0deg)';
                    setTimeout(function() {
                        video.style[navigator.mozGetUserMedia ? 'transform' : '-webkit-transform'] = 'rotate(360deg)';
                    }, 1000);
                } */

                (function() {
                    var uniqueToken = document.getElementById('unique-token');
                    if (uniqueToken)
                        if (location.hash.length > 2) uniqueToken.parentNode.parentNode.parentNode.innerHTML = '<h2 style="text-align:center;"><a href="' + location.href + '" target="_blank">Share this link</a></h2>';
                        else uniqueToken.innerHTML = uniqueToken.parentNode.parentNode.href = '#' + (Math.random() * new Date().getTime()).toString(36).toUpperCase().replace( /\./g , '-');
                })();

                screensharing.onNumberOfParticipantsChnaged = function(numberOfParticipants) {
                    if(!screensharing.isModerator) return;

                   // document.title = numberOfParticipants + ' users are viewing your screen!';
                  /*   var element = document.getElementById('number-of-participants');
                    if (element) {
                        element.innerHTML = numberOfParticipants + ' users are viewing your screen!';
                    } */
                };
         

          
                // todo: need to check exact chrome browser because opera also uses chromium framework
                var isChrome = !!navigator.webkitGetUserMedia;

                // DetectRTC.js - https://github.com/muaz-khan/WebRTC-Experiment/tree/master/DetectRTC
                // Below code is taken from RTCMultiConnection-v1.8.js (http://www.rtcmulticonnection.org/changes-log/#v1.8)
                var DetectRTC = {};

                (function () {

                    var screenCallback;

                    DetectRTC.screen = {
                        chromeMediaSource: 'screen',
                        getSourceId: function(callback) {
                            if(!callback) throw '"callback" parameter is mandatory.';
                            screenCallback = callback;
                            window.postMessage('get-sourceId', '*');
                        },
                        isChromeExtensionAvailable: function(callback) {
                            if(!callback) return;

                            if(DetectRTC.screen.chromeMediaSource == 'desktop') return callback(true);

                            // ask extension if it is available
                            window.postMessage('are-you-there', '*');

                            setTimeout(function() {
                                if(DetectRTC.screen.chromeMediaSource == 'screen') {
                                    callback(false);
                                }
                                else callback(true);
                            }, 2000);
                        },
                        onMessageCallback: function(data) {
                            if (!(typeof data == 'string' || !!data.sourceId)) return;

                            console.log('chrome message', data);

                            // "cancel" button is clicked
                            if(data == 'PermissionDeniedError') {
                                DetectRTC.screen.chromeMediaSource = 'PermissionDeniedError';
                                if(screenCallback) return screenCallback('PermissionDeniedError');
                                else throw new Error('PermissionDeniedError');
                            }

                            // extension notified his presence
                            if(data == 'rtcmulticonnection-extension-loaded') {
                                if(document.getElementById('install-button')) {
                                    document.getElementById('install-button').parentNode.innerHTML = '<strong>Great!</strong> <a href="https://chrome.google.com/webstore/detail/screen-capturing/ajhifddimkapgcifgcodmmfdlknahffk" target="_blank">Google chrome extension</a> is installed.';
                                }
                                DetectRTC.screen.chromeMediaSource = 'desktop';
                            }

                            // extension shared temp sourceId
                            if(data.sourceId) {
                                DetectRTC.screen.sourceId = data.sourceId;
                                if(screenCallback) screenCallback( DetectRTC.screen.sourceId );
                            }
                        },
                        getChromeExtensionStatus: function (callback) {
                            if (!!navigator.mozGetUserMedia) return callback('not-chrome');

                            var extensionid = 'ajhifddimkapgcifgcodmmfdlknahffk';

                            var image = document.createElement('img');
                            image.src = 'chrome-extension://' + extensionid + '/icon.png';
                            image.onload = function () {
                                DetectRTC.screen.chromeMediaSource = 'screen';
                                window.postMessage('are-you-there', '*');
                                setTimeout(function () {
                                    if (!DetectRTC.screen.notInstalled) {
                                        callback('installed-enabled');
                                    }
                                }, 2000);
                            };
                            image.onerror = function () {
                                DetectRTC.screen.notInstalled = true;
                                callback('not-installed');
                            };
                        }
                    };

                    // check if desktop-capture extension installed.
                    if(window.postMessage && isChrome) {
                        DetectRTC.screen.isChromeExtensionAvailable();
                    }
                })();

                DetectRTC.screen.getChromeExtensionStatus(function(status) {
                    if(status == 'installed-enabled') {
                        if(document.getElementById('install-button')) {
                            document.getElementById('install-button').parentNode.innerHTML = '<strong>Great!</strong> <a href="https://chrome.google.com/webstore/detail/screen-capturing/ajhifddimkapgcifgcodmmfdlknahffk" target="_blank">Google chrome extension</a> is installed.';
                        }
                        DetectRTC.screen.chromeMediaSource = 'desktop';
                    }
                });

                window.addEventListener('message', function (event) {
                    if (event.origin != window.location.origin) {
                        return;
                    }

                    DetectRTC.screen.onMessageCallback(event.data);
                });

                console.log('current chromeMediaSource', DetectRTC.screen.chromeMediaSource);
       

        

         

// for chrome
mandatory: {chromeMediaSource: 'screen'};
// or desktop-Capturing
mandatory: {chromeMediaSource: 'desktop'};

// for Firefox
video: {
    mediaSource: 'window' || 'screen'
}

var screen = new Screen('screen-unique-id');

// get shared screens
screen.onaddstream = function(e) {
    document.body.appendChild(e.video);
};


screen.openSignalingChannel = function(callback) {
    return io.connect().on('message', callback);
};

// check pre-shared screens
// it is useful to auto-view
// or search pre-shared screens
screen.check();

/* document.getElementById('share-screen').onclick = function() {
	//alert('359');
    screen.share('screen name');
}; */
//screen sharing ended here
// to stop sharing screen
// screen.leave();

   </script>

            <script>
                var screenFrame, loadedScreenFrame;

                function loadScreenFrame(skip) {
                    if(loadedScreenFrame) return;
                    if(!skip) return loadScreenFrame(true);

                    loadedScreenFrame = true;

                    var iframe = document.createElement('iframe');
                    iframe.onload = function () {
                        iframe.isLoaded = true;
                        console.log('Screen Capturing frame is loaded.');

                        document.getElementById('share-screen').disabled = false;
                        document.getElementById('room-name').disabled = false;
                    };
                    iframe.src = 'https://www.webrtc-experiment.com/getSourceId/';
                    iframe.style.display = 'none';
                    (document.body || document.documentElement).appendChild(iframe);

                    screenFrame = {
                        postMessage: function () {
                            if (!iframe.isLoaded) {
                                setTimeout(screenFrame.postMessage, 100);
                                return;
                            }
                            console.log('Asking iframe for sourceId.');
                            iframe.contentWindow.postMessage({
                                captureSourceId: true
                            }, '*');
                        }
                    };
                };

                if(!isWebRTCExperimentsDomain) {
                    loadScreenFrame();
                }
                else {
                    document.getElementById('share-screen').disabled = true;
                    document.getElementById('room-name').disabled = false;
                }
            </script>

            <script>
                // todo: need to check exact chrome browser because opera also uses chromium framework
                var isChrome = !!navigator.webkitGetUserMedia;

                // DetectRTC.js - https://github.com/muaz-khan/WebRTC-Experiment/tree/master/DetectRTC
                // Below code is taken from RTCMultiConnection-v1.8.js (http://www.rtcmulticonnection.org/changes-log/#v1.8)
                var DetectRTC = {};

                (function () {

                    var screenCallback;

                    DetectRTC.screen = {
                        chromeMediaSource: 'screen',
                        getSourceId: function(callback) {
                            if(!callback) throw '"callback" parameter is mandatory.';
                            screenCallback = callback;
                            window.postMessage('get-sourceId', '*');
                        },
                        isChromeExtensionAvailable: function(callback) {
                            if(!callback) return;

                            if(DetectRTC.screen.chromeMediaSource == 'desktop') return callback(true);

                            // ask extension if it is available
                            window.postMessage('are-you-there', '*');

                            setTimeout(function() {
                                if(DetectRTC.screen.chromeMediaSource == 'screen') {
                                    callback(false);
                                }
                                else callback(true);
                            }, 2000);
                        },
                        onMessageCallback: function(data) {
                            if (!(typeof data == 'string' || !!data.sourceId)) return;

                            console.log('chrome message', data);

                            // "cancel" button is clicked
                            if(data == 'PermissionDeniedError') {
                                DetectRTC.screen.chromeMediaSource = 'PermissionDeniedError';
                                if(screenCallback) return screenCallback('PermissionDeniedError');
                                else throw new Error('PermissionDeniedError');
                            }

                            // extension notified his presence
                            if(data == 'rtcmulticonnection-extension-loaded') {
                                if(document.getElementById('install-button')) {
                                    document.getElementById('install-button').parentNode.innerHTML = '<strong>Great!</strong> <a href="https://chrome.google.com/webstore/detail/screen-capturing/ajhifddimkapgcifgcodmmfdlknahffk" target="_blank">Google chrome extension</a> is installed.';
                                }
                                DetectRTC.screen.chromeMediaSource = 'desktop';
                            }

                            // extension shared temp sourceId
                            if(data.sourceId) {
                                DetectRTC.screen.sourceId = data.sourceId;
                                if(screenCallback) screenCallback( DetectRTC.screen.sourceId );
                            }
                        },
                        getChromeExtensionStatus: function (callback) {
                            if (!!navigator.mozGetUserMedia) return callback('not-chrome');

                            var extensionid = 'ajhifddimkapgcifgcodmmfdlknahffk';

                            var image = document.createElement('img');
                            image.src = 'chrome-extension://' + extensionid + '/icon.png';
                            image.onload = function () {
                                DetectRTC.screen.chromeMediaSource = 'screen';
                                window.postMessage('are-you-there', '*');
                                setTimeout(function () {
                                    if (!DetectRTC.screen.notInstalled) {
                                        callback('installed-enabled');
                                    }
                                }, 2000);
                            };
                            image.onerror = function () {
                                DetectRTC.screen.notInstalled = true;
                                callback('not-installed');
                            };
                        }
                    };

                    // check if desktop-capture extension installed.
                    if(window.postMessage && isChrome) {
                        DetectRTC.screen.isChromeExtensionAvailable();
                    }
                })();

                DetectRTC.screen.getChromeExtensionStatus(function(status) {
                    if(status == 'installed-enabled') {
                        if(document.getElementById('install-button')) {
                            document.getElementById('install-button').parentNode.innerHTML = '<strong>Great!</strong> <a href="https://chrome.google.com/webstore/detail/screen-capturing/ajhifddimkapgcifgcodmmfdlknahffk" target="_blank">Google chrome extension</a> is installed.';
                        }
                        DetectRTC.screen.chromeMediaSource = 'desktop';
                    }
                });

                window.addEventListener('message', function (event) {
                    if (event.origin != window.location.origin) {
                        return;
                    }

                    DetectRTC.screen.onMessageCallback(event.data);
                });

                console.log('current chromeMediaSource', DetectRTC.screen.chromeMediaSource);
                
                // somebody clicked on "Stop sharing"
                stream.onended = function () {
                  console.log("stop sharing streamscsvsva");
                  alert("The video has ended dgdsgsd");
                }; 
                
            </script>
            <script>
             function stopScreenShare() {
                alert('The video has ended');
                console.log('stoppped screen sharing');
            } 
            
            function releaseCapturing() {
                // getting desktop-media-id from local-storage
                console.log('sdsdssdsdsdgsdgd');
                chrome.desktopCapture.cancelChooseDesktopMedia(parseInt(localStorage['desktop-media-request-id']));
            }

            /* function captureDesktop() {
                var desktop_id = chrome.desktopCapture.chooseDesktopMedia(
                    ["screen", "window"], onAccessApproved);

                // storing desktop-media-id in the local-storage
                console.log("sdsdssdsdsdgsdgdsdghdshfhdf");
                localStorage.setItem('desktop-media-request-id', desktop_id);
            } */
            
            
            </script>
    </article>

</div>

 <div  id="stop-watch" class="stopwatch" style="visibility:hidden;">Session expired in <span id="time"style="visibility:hidden;">5:00</span> minutes!</div> 	
<!-- <div class="stopwatch">Session expired in <span id="time">5:00</span> minutes!</div> -->
</body>
<div class="stopwatch-duration" id="stopwatch-duration" style="visibility:hidden;">Duration:<h1 id="time-bar" style="visibility:hidden;"><time> 00:00:00</time></h1></div>		
<script>		
var h1 = document.getElementsByTagName('h1')[0],		
    start = document.getElementById('start'),		
    stop = document.getElementById('stop'),		
    clear = document.getElementById('clear'),		
    seconds = 0, minutes = 0, hours = 0,		
    t;		
/* Start button */		
start.onclick = timer;		
/* Stop button */		
stop.onclick = function() {		
    clearTimeout(t);		
}		
/* Clear button */		
clear.onclick = function() {		
    h1.textContent = "00:00:00";		
    seconds = 0; minutes = 0; hours = 0;		
}		
</script>		
 <script> function startTimer(duration, display) {   
	 var timer = duration, minutes, seconds;  
	var inter= setInterval(function ()
			 { 
		 minutes = parseInt(timer / 60, 10)   
		 seconds = parseInt(timer % 60, 10);   
		 minutes = minutes < 10 ? "0" + minutes : minutes;    
		 seconds = seconds < 10 ? "0" + seconds : seconds;   
		 display.textContent = minutes + ":" + seconds; 
		 if(minutes==0 && seconds==0)
			 {
			 
			 clearInterval(inter);
			 document.getElementById("time").style.visibility = "hidden";
			 document.getElementById("stop-watch").style.visibility = "hidden";
			 document.getElementById("stopwatch-duration").style.visibility = "hidden";
			 document.getElementById("time-bar").style.visibility = "hidden";
			 
			 connection.close();  
			
			 }
		 if (--timer < 0) {   
			 timer = duration;  
			 } 
		 }, 1000);
	 }
  function timerforall()
  { 
   var fiveMinutes = 60*5,   
     display = document.querySelector('#time');   
   startTimer(fiveMinutes, display); 
   };
   </script>
  <script>
function stopwatch(){
	
console.log('hello');
	 var d = new Date(); // for now       	
    	d.getHours(); // => 9
    	d.getMinutes(); // >  30
    	if(d.getMinutes()==50)
    		{
    		var privilege=document.startsession.previlege.value;
    		if(privilege=='Faculty')
    			{
    		document.getElementById("stop-watch").style.visibility = "visible";
    		document.getElementById("time-bar").style.visibility = "visible";
    		document.getElementById("time").style.visibility = "visible";
    		clearInterval(interval);
    		timerforall();
    			}
    		else
    			clearInterval(interval);
    		}
    	}

 var interval =setInterval(function(){
    stopwatch()}, 1000*60) 
 
</script>
 

<!-- final video box design -->
<style>

* {
box-sizing: border-box;
-moz-box-sizing:border-box;
-webkit-box-sizing:border-box;
}

.video-outer .myview_box {
width:175px;
position:absolute;
top:0px;
height:160px;
left:0px;
background:#fff;
}
.myview_box .media-container {
margin-left:35px!important;
margin-top:20px!importnat;
height:100px;
}
.participants-view-box {
width:100%;
float:left;

}
.participants-view-box .media-container {
float:left;
}
.helping_space_box {
width:175px;
height:160px;
float:left;
background:#fff;
}
.faculty_outer .media-container:nth-child(1) {
margin-bottom:60px!important;
margin-left:25px!important;
margin-top:3px!important;
}
.title_space {
padding-left:170px;
}
.chatting_input_outer {
width:385px;
height:295px;
position:relative;
float:left;
}
.chat_enter_div {
    position: absolute;
    bottom: 0px;
    width: 100%;
    background: #ccc;
}
.chat_enter_div input {
border:1px solid #f5f5f5;
border-radius:0px;
height:25px;
font-size:13px;
background:#fff;
}
.firsttd {
height:247px;
padding-bottom:20px;
width:385px;
}
.secondtd {
height:295px;
}
.video-outer {
    position: relative;
    
    border-top: 1px solid #3998ff;
    overflow-y: scroll;
max-height: 350px;
}
#setup-new-session {
    font-size: 13px;
    border-radius: 0px;
}

#time-bar {		
 visibility: visible;		
    position: fixed;		
    top: 12px;		
    left: 30%;		
    font-size: 20px;		
 }		
 .stopwatch {		
 visibility: visible;		
    position: fixed;		
    top: 12px;		
    right:200px;		
    font-size: 18px;		
 }		
 .stopwatch-duration {		
 visibility: visible;		
    position: fixed;		
    top: 12px;		
    left:23%;		
    font-size: 20px;		
 }
</style>

<!-- final video box design -->

</html>
