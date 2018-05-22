package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.ActivateStudentDao;
import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;

@SuppressWarnings("serial")
public class StudentToCourseServlet extends CommonService{
	
	String userID="",username="",pwd="",courseID="",courseID_name="",coursename="",email="";
		/*String emailid="";
	String password="";
	String host="" ;
	String start_date,end_date="";
	String pop="";
	int    port;*/
	AdminDomain adomain=null;
	ArrayList<String> group_member_email_array_list=new ArrayList<String>();
	String teachingSourceName;
	public void run() throws ServletException,IOException, AddressException, MessagingException{
		group_member_email_array_list.clear();
		session=request.getSession(true);
		// int successrow=0;
		teachingSourceName=(String)session.getAttribute("organizationName");
		  ActivateStudentDao studentDao=new ActivateStudentDao();
		 ArrayList<AdminDomain> arrayList=new ArrayList<AdminDomain>();
			String organizationId=(String)session.getAttribute("orgid");
		
		 int counter=Integer.parseInt(request.getParameter("counter"));
		// System.out.println("counter:"+counter);
		//String[] course = request.getParameterValues("course");
		 //System.out.println("course values:"+course);
		 
			        for(int i=1; i<=counter; i++) {
			        	adomain=new AdminDomain();
	          // System.out.println(values[i]);
	            username=request.getParameter("studName"+i);
	            userID=request.getParameter("studID"+i);
	            email=request.getParameter("email"+i);
	            courseID_name=request.getParameter("course"+i);
	            pwd=request.getParameter("password"+i);
	            
	            if(courseID_name!=null){
	           	 if(courseID_name.contains("�")){
	     	            String cIDname[]=courseID_name.split("�");
	     	          //  System.out.println("username="+username);
	     	            //System.out.println("courseID="+courseID);
	     	            courseID=cIDname[0];
	     	            coursename=cIDname[1];
	     	            }
	           	 else
	           		 {
	           		 courseID=null;
	     	            coursename=null;
	           		 }
	           		 } else
	           		 {
	           		 courseID=null;
	     	            coursename=null;
	           		 }
	            System.out.println("courseID_name="+courseID_name);
	            if(courseID!=null){
	            if(!courseID.equalsIgnoreCase("") && !courseID.equalsIgnoreCase("null")){
	            	//System.out.println("courseID_name="+courseID_name);
	            	//System.out.println("in if="+courseID);
	            
	            adomain.setAdminId(userID);
	            adomain.setAdminName(username);
	            adomain.setEmail(email);
	            adomain.setCity(courseID);
	            adomain.setPwd(pwd);
	            adomain.setOrgid(organizationId);   
	            adomain.setCountry(coursename);
	            
	            arrayList.add(adomain);
	            }
	            }
			        }
					//System.out.println("arrayList="+arrayList);
					//System.out.println("arrayList size="+arrayList.size());
					int count=studentDao.updateCourseToStudent(arrayList);
					
			        System.out.println("count in service:"+count);
			       // System.out.println("successrow in service:"+successrow);
			       // if(status.equalsIgnoreCase("success")){
			        if(count>0){
			        ListIterator<AdminDomain> al = arrayList.listIterator();
			    		while(al.hasNext()){
			    			AdminDomain ad = al.next();
			    			String username = ad.getAdminName(), userID = ad.getAdminId(), pwd = ad.getPwd(), email = ad.getEmail(),coursename=ad.getCountry();
			        String subject="Student Credentials";
			        String 	message = "Hi "+username+",<br/><br/>"
		        			+"You have been successfully mapped for "+coursename+" course in "+teachingSourceName+". Your DCI login credentials are provided below;"+"<br/>"+"<Strong>User ID - </Strong>"+userID+"<br/>"+"<Strong>Password - </Strong>"+pwd+""
		        					+ "<br/>Please click the URL to start accessing the Digital Classroom Infrastructure: <a href='https://www.kompacdigit.com:8443/KODE_DEV/'>https://www.kompacdigit.com:8443/KODE_DEV/</a>"+"<br/><br/>" + "Thanks and Regards" + "<br/>" + teachingSourceName+"<br/><br/>"+"For any technical assistance please contact on the details provided below:" + "<br/><br/>"
        					+   "<strong>Toll Free No:</strong> 1800123456"+"<br/>"+"<strong>SMS:</strong> 1234567891"+"<br/>"+"<strong>Email ID:</strong> <a href='mailto:technicalsupport@kompaceducation.com'>technicalsupport@kompaceducation.com</a>";
       
			        System.out.println("messge="+message);
			      //  Mailer.send(email, subject, message);	
			    		}
			        
			        String studentStatus=count+" Students mapped successfully.";
		  			request.setAttribute("StudentSuccess",studentStatus);
		  			request.setAttribute("studentDetails", arrayList);
			        }else 
			        	{
			        	String studentStatus="Unsuccessful student mapping.";
			  			request.setAttribute("StudentFailure",studentStatus);
			        }
			        RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/create-student-file.jsp");
		  			requestDispatcher.forward(request, response);
}
	}

