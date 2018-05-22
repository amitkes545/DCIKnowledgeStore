package com.kds.KODE_DEV.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kds.KODE_DEV.services.CommonService;

@SuppressWarnings("serial")
public class ControllerServlet extends HttpServlet {
	
       
   
    public ControllerServlet() {
        super();

    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
		
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		@SuppressWarnings("unused")
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		//out.println(session);
				
		Map actionMap = (Map) session.getAttribute("actionMap");
		
		//System.out.println("Session: "+ session.toString());
		//System.out.println("actionMap getAttribute: "+ actionMap);
		
		if(actionMap == null){
			
			actionMap = new HashMap();
			session.setAttribute("actionMap", actionMap);
						
		}
		
		ServletContext context = getServletContext(); //to get real path to map a URI
		
		//System.out.println("ServletContext: "+ context.toString());
		
		try{
			
			String pathInfo = request.getPathInfo();
			
			//System.out.println("PathInfo: "+ pathInfo);
			
			if(pathInfo == null)
				
				throw new ServletException("Invalid internal state-No path Found");
				
			CommonService cs = (CommonService) actionMap.get(pathInfo);
			
			if(cs == null){
				
				StringTokenizer st = new StringTokenizer(pathInfo,"/");
				
				if(st.countTokens() != 1)
					
					throw new ServletException("Invalid Internal State -Invalid Path");
					//while(st.hasMoreTokens()){
					//	System.out.println(st.nextTokeExceptionn());
					//}
				String name = st.nextToken(); //it identify the class whose calling me /Login/ControllerServlet/LoginService
				//name it store LoginService
				
				String className = "com.kds.KODE_DEV.services."+name;//it call to the LoginService class which is available in com.kds.logindemo.service
				
				try{
					
					Class actionClass = Class.forName(className);
					
					cs = (CommonService) actionClass.newInstance();////it create an instance of
					
				}catch(ClassNotFoundException e) {
					throw new ServletException("Could not load class"+className+":"+e.getMessage());
				}
				catch(InstantiationException e)	{
					throw new ServletException("Could Not Create Instance of a class "+className+":"+e.getMessage());
				}
				catch(IllegalAccessException e)	{
					throw new ServletException(className+":"+e.getMessage());
				}
				
				
				actionMap.put(pathInfo, cs);
				
			}//end if cs == null
			
			cs.setRequest(request);
			cs.setResponse(response);
			cs.setApplication(context); //ServletContext object that contains meta informaton about your web application (web.xml)
			cs.run(); //this calls the particular servlet and control transfer to abstract run() method (implementation)
			
		}catch(ServletException se){
		      //response.sendRedirect("../JSP/error.jsp");
			  out.println(se);
			
		} catch (Exception e) {
			//response.sendRedirect("../JSP/error.jsp");
			e.printStackTrace();
		}
		
	}
}