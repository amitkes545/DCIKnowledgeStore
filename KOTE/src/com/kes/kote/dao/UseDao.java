package com.kes.kote.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.kes.kote.domain.UseAccessDomain;
import com.kes.kote.domain.UseMailServerDomain;
import com.kes.kote.domain.UseNotificationDomain;
import com.kes.kote.domain.UseParamDomain;
import com.kes.kote.domain.UseRoleDomain;
import com.kes.kote.domain.UseRoleMapDomain;
import com.kes.kote.domain.UseUserDomain;

public interface UseDao {
	
	public int saveUserParam(List<UseParamDomain> useParam,HttpSession sess);

	public int saveMailServrDetails(List<UseMailServerDomain> requestDomain,HttpSession sess);

	public int saveUserRegistration(List<UseUserDomain> requestDomain,HttpSession sess);
	
	public int saveRoleManagementDetails(List<UseRoleDomain> requestDomain,HttpSession sess);

	public int saveRoleMapDetails(List<UseRoleMapDomain> requestDomain,HttpSession sess);

	public int saveRoleAccessDetails(List<UseAccessDomain> requestDomain,HttpSession sess);

	public int saveUserNotificationDetails(List<UseNotificationDomain> requestDomain,HttpSession sess);

}
