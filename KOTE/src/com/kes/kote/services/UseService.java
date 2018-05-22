package com.kes.kote.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;

import com.kes.kote.dao.UseDao;
import com.kes.kote.dao.impl.UseDaoImpl;
import com.kes.kote.domain.UseAccessDomain;
import com.kes.kote.domain.UseMailServerDomain;
import com.kes.kote.domain.UseNotificationDomain;
import com.kes.kote.domain.UseParamDomain;
import com.kes.kote.domain.UseRoleDomain;
import com.kes.kote.domain.UseRoleMapDomain;
import com.kes.kote.domain.UseUserDomain;
import com.kes.kote.util.PropertiesUtil;
import com.kes.kote.util.SessionUtil;

public class UseService extends CommonService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UseDao dao;

	@Override
	public void run() throws ServletException, IOException, FileUploadException, Exception {
		
		session=request.getSession(true);
		
		SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
		
		dao = new UseDaoImpl();
		String fromParam = getRequestType();
		if(fromParam.equalsIgnoreCase("useDetails")) {
			List<UseParamDomain> requestDomain = createUseParamRequest(request);
			int result = dao.saveUserParam(requestDomain,session);
			if(result==1)
			{
				util.setsuccessmsg(PropertiesUtil.getMessageProperty("usp.success"));
				response.sendRedirect("../JSP/msd-mail-server-details-use.jsp");
			}else
			{
				util.setfailedmsg(PropertiesUtil.getMessageProperty("usp.failed"));
				response.sendRedirect("../JSP/user-system-parameters-use.jsp");
			}
		}else if(fromParam.equalsIgnoreCase("mailServer")) {
			List<UseMailServerDomain> requestDomain = createUseMailServerRequest(request);
			int result = dao.saveMailServrDetails(requestDomain,session);
			if(result==1)
			{
				util.setsuccessmsg(PropertiesUtil.getMessageProperty("msd.success"));
				response.sendRedirect("../JSP/urem-user-registration-management-use.jsp");
			}else
			{
				util.setfailedmsg(PropertiesUtil.getMessageProperty("msd.failed"));
				response.sendRedirect("../JSP/msd-mail-server-details-use.jsp");
			}
		}else if(fromParam.trim().equalsIgnoreCase("usersReg"))
		{
			List<UseUserDomain> requestDomain = createUseRegistrationRequest(request);
			int result = dao.saveUserRegistration(requestDomain,session);
			if(result==1)
			{
				util.setsuccessmsg(PropertiesUtil.getMessageProperty("urem.success"));
				response.sendRedirect("../JSP/urole-user-role-management-use.jsp");
			}else
			{
				util.setfailedmsg(PropertiesUtil.getMessageProperty("urem.failed"));
				response.sendRedirect("../JSP/urem-user-registration-management-use.jsp");
			}
			
			
		}else if(fromParam.equalsIgnoreCase("roleMang")) {
			List<UseRoleDomain> requestDomain = createUserRoleManagementRequest(request);
			int result = dao.saveRoleManagementDetails(requestDomain,session);
			if(result==1)
			{
				util.setsuccessmsg(PropertiesUtil.getMessageProperty("role.success"));
				response.sendRedirect("../JSP/urom-user-role-mapping-use.jsp");
			}else
			{
				util.setfailedmsg(PropertiesUtil.getMessageProperty("role.failed"));
				response.sendRedirect("../JSP/urole-user-role-management-use.jsp");
			}
		}else if(fromParam.equalsIgnoreCase("roleMap")) {
			List<UseRoleMapDomain> requestDomain = createUserRoleMapRequest(request);
			int result = dao.saveRoleMapDetails(requestDomain,session);
			if(result==1)
			{
				util.setsuccessmsg(PropertiesUtil.getMessageProperty("usrrolemap.success"));
				response.sendRedirect("../JSP/rtmm-role-to-menu-mapping-use.jsp");
			}else
			{
				util.setfailedmsg(PropertiesUtil.getMessageProperty("usrrolemap.failed"));
				response.sendRedirect("../JSP/urom-user-role-mapping-use.jsp");
			}
		}else if(fromParam.equalsIgnoreCase("useAccess")) {
			List<UseAccessDomain> requestDomain = createUserAccessRequest(request);
			int result = dao.saveRoleAccessDetails(requestDomain,session);
			if(result==1)
			{
				util.setsuccessmsg(PropertiesUtil.getMessageProperty("accessrights.success"));
				response.sendRedirect("../JSP/unom-user-notification-management-use.jsp");
			}else
			{
				util.setfailedmsg(PropertiesUtil.getMessageProperty("accessrights.failed"));
				response.sendRedirect("../JSP/rtmm-role-to-menu-mapping-use.jsp");
			}
		}else if(fromParam.equalsIgnoreCase("usenom")) {
			List<UseNotificationDomain> requestDomain = createUserNotificationRequest(request);
			int result = dao.saveUserNotificationDetails(requestDomain,session);
			if(result==1)
			{
				util.setsuccessmsg(PropertiesUtil.getMessageProperty("unom.success"));
				response.sendRedirect("../JSP/epw-enrollment-process-workflow-stamp.jsp");
			}else
			{
				util.setfailedmsg(PropertiesUtil.getMessageProperty("unom.failed"));
				response.sendRedirect("../JSP/unom-user-notification-management-use.jsp");
			}
		}

	}

	private List<UseNotificationDomain> createUserNotificationRequest(HttpServletRequest request) {
		SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
		int rowCount = util.getTotRow();
		List<UseNotificationDomain> useNomList = new ArrayList<UseNotificationDomain>(); 
		for(int i=0; i<rowCount; i++) {
			UseNotificationDomain domain = new UseNotificationDomain();
			domain.setNotificationType(request.getParameter("noti"+i));
			domain.setSubject(request.getParameter("subject"+i));
			domain.setHeader(request.getParameter("header"+i));
			domain.setBody(request.getParameter("body"+i));
			domain.setFooter(request.getParameter("footer"+i));
			domain.setUidSender(request.getParameter("userId"+i));
			if(domain.isValid())
			{
				//util.print(domain.toString());
				useNomList.add(domain);
			}
		}
		return useNomList;
	}

	private List<UseAccessDomain> createUserAccessRequest(HttpServletRequest request) {
		SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
		int rowCount = util.getTotRow();
		List<UseAccessDomain> useUseAccessList = new ArrayList<UseAccessDomain>(); 
		try
		{
			for(int i=0; i<rowCount; i++) {
				UseAccessDomain domain = new UseAccessDomain();
				domain.setRoleId(request.getParameter("roleId"+i));
				domain.setModuleId(request.getParameter("moduleId"+i));
				domain.setMenuNamelevel1(request.getParameter("menulevel1"+i));
				domain.setMenuNamelevel2(request.getParameter("menulevel2"+i));
				domain.setMenuNamelevel3(request.getParameter("menulevel3"+i));
				if(domain.isValid())
				{
					//util.print(domain.toString());
					useUseAccessList.add(domain);
				}
			}	
		}catch(Exception ex){ex.printStackTrace();}
		
		return useUseAccessList;
	}

	private List<UseRoleMapDomain> createUserRoleMapRequest(HttpServletRequest request) {
		SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
		
		int rowCount = util.getTotRow();
		List<UseRoleMapDomain> useUseRoleMapList = new ArrayList<UseRoleMapDomain>(); 
		for(int i=0; i<rowCount; i++) {
			UseRoleMapDomain domain = new UseRoleMapDomain();
			domain.setUserId(request.getParameter("userId"+i));
			domain.setRoleId(request.getParameter("roleId"+i));
			if(domain.isValid())
				{
					//util.print(domain.toString());
					useUseRoleMapList.add(domain);
				}
		}
		return useUseRoleMapList;
	}

	private List<UseRoleDomain> createUserRoleManagementRequest(HttpServletRequest request) {
		SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
		
		int rowCount = util.getTotRow();
		List<UseRoleDomain> useUseRoleList = new ArrayList<UseRoleDomain>(); 
		for(int i=0; i<rowCount; i++) {
			UseRoleDomain domain = new UseRoleDomain();
			domain.setRoleId(request.getParameter("roleId"+i));
			domain.setDescription(request.getParameter("desc"+i));
			domain.setType(request.getParameter("type"+i));
			if(domain.isValid())
				{
				//util.print(domain.toString());
				useUseRoleList.add(domain);
				}
		}
		return useUseRoleList;
	}
	private List<UseUserDomain> createUseRegistrationRequest(HttpServletRequest request)
	{
		SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
		
		int rowCount=util.getTotRow();
		List<UseUserDomain> useParamList=new ArrayList<UseUserDomain>();
		for(int i=0; i<rowCount; i++) {
			UseUserDomain useParam=new UseUserDomain();
			
			useParam.setUserId(request.getParameter("userid"+i));
    		useParam.setFirstName(request.getParameter("userfname"+i));
    		useParam.setLastName(request.getParameter("userlname"+i));
    		useParam.setDesignation(request.getParameter("designation"+i));
    		useParam.setDepartment(request.getParameter("department"+i));
    		useParam.setAddress(request.getParameter("address"+i));
    		useParam.setCity(request.getParameter("city"+i));
    		useParam.setState(request.getParameter("state"+i));
    		useParam.setCountry(request.getParameter("country"+i));
    		useParam.setPostal(request.getParameter("postalcode"+i));
    		useParam.setDob(request.getParameter("dateofbirth"+i));
    		useParam.setGender(request.getParameter("gender"+i));
    		useParam.setMobile(request.getParameter("mobilenumber"+i));
    		useParam.setLandline(request.getParameter("landlinenumber"+i));
    		useParam.setExtension(request.getParameter("extension"+i));
    		useParam.setEmail(request.getParameter("email"+i));
    		useParam.setMailServer(request.getParameter("mailserverid"+i));
    		useParam.setSpace(request.getParameter("space"+i));
    		useParam.setUom(request.getParameter("spaceuom"+i));
			
    		if(useParam.getAddress()!=null && useParam.getAddress().trim().length()==0)
    			useParam.setAddress(util.getOrgAddress());
    		
			if(useParam.isValid())
			{
				//util.print(useParam.toString());
				useParamList.add(useParam);	
			}
			
		}
		
		return useParamList;
	}

	private List<UseMailServerDomain> createUseMailServerRequest(HttpServletRequest request) {
		
		SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
		
		int rowCount = util.getTotRow();
		List<UseMailServerDomain> useParamList = new ArrayList<UseMailServerDomain>(); 
		for(int i=0; i<rowCount; i++) {
			UseMailServerDomain domain = new UseMailServerDomain();
			domain.setName(request.getParameter("name"+i));
			domain.setAddress(request.getParameter("address"+i));
			domain.setHost(request.getParameter("host"+i));
			domain.setPort(request.getParameter("port"+i));
			if(domain.isValid())
				{
				//util.print(domain.toString());
				useParamList.add(domain);
				}
		}
		return useParamList;
	}

	

	private List<UseParamDomain> createUseParamRequest(HttpServletRequest request) {
		
		SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
		
		int rowCount = util.getTotRow();
		List<UseParamDomain> useParamList = new ArrayList<UseParamDomain>(); 
		for(int i=0; i<rowCount; i++) {
			UseParamDomain domain = new UseParamDomain();
			domain.setDescription(request.getParameter("paramDesc"+i));
			domain.setKey(request.getParameter("paramKey"+i));
			domain.setValue(request.getParameter("paramValue"+i));
			domain.setType(request.getParameter("paramType"+i));
			if(domain.isValid())
				{
				//util.print(domain.toString());
				useParamList.add(domain);
				}
		}
		return useParamList;
	}
	
	private String getRequestType() {
		String fromParam = null;
		if(request.getParameter("From") != null) {
			fromParam = request.getParameter("From").trim();
		}
		return fromParam;
	}

}
