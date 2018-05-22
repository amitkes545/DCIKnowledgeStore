 package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;


@SuppressWarnings("serial")
//@WebServlet("/CommonService")
public abstract class CommonService implements Serializable{
 
 protected transient HttpServletRequest request;
 protected transient HttpServletResponse response;
 protected transient ServletContext application; //ServletContext loads web.xml configuration dynamically
 protected transient HttpSession session;
 
 public abstract void run() throws ServletException, IOException,FileUploadException, Exception;
  
 public HttpSession getSes(){   
  return session;
 }
 
 public void setSes(HttpSession ses){  
  this.session = ses;
 }
 
 public void setRequest(HttpServletRequest request){  
  this.request = request;
 }
 
 public void setResponse(HttpServletResponse response){
  this.response = response;
 }
 
 public void setApplication(ServletContext application){
  this.application = application;
 }

	/*public ServletContext getServletContext()
	{
		return this.application;
	}*/
}