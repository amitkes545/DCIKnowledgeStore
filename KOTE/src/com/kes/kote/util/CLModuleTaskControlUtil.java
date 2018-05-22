package com.kes.kote.util;

public class CLModuleTaskControlUtil {

	public static String CHOOSETEACHINGSOURCE="cfl001";
	public static String cfl001="teaching-source.jsp";
	
	// -------------------CINE Start -------------------------
	public static String CINEORGDETAILS="cfl002";
	public static String cfl002="cine-setup-middle-layer.jsp";
	
	public static String CINESPOCS="cfl003";
	public static String cfl003="cine-spocs.jsp";
	// --------------------- CINE End -------------------------
	
	//------------------ USE Start ----------------------------
	public static String USEPARAM="cfl004";
	public static String cfl004="user-system-parameters-use.jsp";
	
	public static String MAILSERVER="cfl005";
	public static String cfl005="msd-mail-server-details-use.jsp";
	
	public static String USER="cfl006";
	public static String cfl006="urem-user-registration-management-use.jsp";
	
	public static String ROLE="cfl007";
	public static String cfl007="urole-user-role-management-use.jsp";
	
	public static String USERROLEMAP="cfl008";
	public static String cfl008="urom-user-role-mapping-use.jsp";
	
	public static String ACCESSRIGHTS="cfl009";
	public static String cfl009="rtmm-role-to-menu-mapping-use.jsp";
	
	public static String UNT="cfl010";
	public static String cfl010="unom-user-notification-management-use.jsp";
	//------------------ USE End ----------------------------
	
	
	// ----------------- STAMP start
	public static String STAMPEFW="cfl011";
	public static String cfl011="epw-enrollment-process-workflow-stamp.jsp";
	
	public static String STAMPENROLLFORM="cfl027";
	public static String cfl027="enf-enrollment-form-attributes-stamp.jsp";
	
	public static String STAMPACT="cfl012";
	public static String cfl012="act-administration-communication-template-stamp.jsp";
	
	public static String STAMPANT="cfl013";
	public static String cfl013="ant-administration-notification-template-stamp.jsp";
	//-------------------------STAMP ends -----------------------------
	
	
	// --------------------- STACS Start ----------------------------------------
	public static String STACSCACS="cfl014";
	public static String cfl014="cacs-course-catalog-system-stacs.jsp";
	
	public static String STACSCORS="cfl015";
	public static String cfl015="cors-course-subjects-stacs.jsp";
	
	public static String STACSTOPS="cfl016";
	public static String cfl016="tops-topics-and-sub-topics-stacs.jsp";
	
	public static String STACSCOSMaP="cfl017";
	public static String cfl017="cosmap-course-subject-mapping-stacs.jsp";
	
	public static String STACSCosTST="cfl018";
	public static String cfl018="costst-course-subject-topic-and-sub-topic-mapping-stacs.jsp";
	
	public static String STACSTIMETABLE="cfl028";
	public static String cfl028="timetable-stacs.jsp";
	
	public static String STACSSGPACGPA="cfl029";
	public static String cfl029="gpa-letter-grade-stacs.jsp";
		
	public static String STACSCORCR="cfl019";
	public static String cfl019="cocr-course-credit-system-stacs.jsp";
	
	public static String STACSAssignType="cfl030";
	public static String cfl030="assignment-types-stacs.jsp";
		
	public static String STACSCCSE="cfl020";
	public static String cfl020="ccse-course-certificate-setup-stacs.jsp";
	// ----------------------- STACS End ---------------------------------------------
	
	
	// ----------------------- FISE Start ------------------------------------------
	public static String FISEFEEMAP="cfl021";
	public static String cfl021="fms-institution-fee-mapping-fise.jsp";
	
	public static String FISECOURSEFEEMAP="cfl022";
	public static String cfl022="cfa-course-fee-mapping-fise.jsp";
	
	public static String FISEMEMBERFEES="cfl023";
	public static String cfl023="mfm-member-fee-mapping-fise.jsp";
	
	public static String FISEFPM="cfl024";
	public static String cfl024="fpm-fee-payment-system-fise.jsp";
	
	public static String FISEFCS="cfl025";
	public static String cfl025="fcms-fee-collection-management-system-fise.jsp";
	// ------------------------ FISE End ----------------------------------------------
	
	
	// ---------------------- Image Upload Start ------------------------------------
	public static String IMAGE="cfl026";
	public static String cfl026="image-image-loading.jsp";
	// -------------------- Image Upload End --------------------------------------
	
	public static String thankyou="thankyou.jsp";
	
	public static String getNxtTaskFile(String compTaskId)
	{
		String ret=cfl002;
		try
		{
			if(compTaskId.trim().equalsIgnoreCase(CINEORGDETAILS)) return cfl003;
			if(compTaskId.trim().equalsIgnoreCase(CINESPOCS)) return cfl004;
			
			if(compTaskId.trim().equalsIgnoreCase(USEPARAM)) return cfl005;
			if(compTaskId.trim().equalsIgnoreCase(MAILSERVER)) return cfl006;
			if(compTaskId.trim().equalsIgnoreCase(USER)) return cfl007;
			if(compTaskId.trim().equalsIgnoreCase(ROLE)) return cfl008;
			if(compTaskId.trim().equalsIgnoreCase(USERROLEMAP)) return cfl009;
			if(compTaskId.trim().equalsIgnoreCase(ACCESSRIGHTS)) return cfl010;
			if(compTaskId.trim().equalsIgnoreCase(UNT)) return cfl011;
			
			if(compTaskId.trim().equalsIgnoreCase(STAMPEFW)) return cfl027;
			if(compTaskId.trim().equalsIgnoreCase(STAMPENROLLFORM)) return cfl012;
			if(compTaskId.trim().equalsIgnoreCase(STAMPACT)) return cfl013;
			if(compTaskId.trim().equalsIgnoreCase(STAMPANT)) return cfl014;
			
			if(compTaskId.trim().equalsIgnoreCase(STACSCACS)) return cfl015;
			if(compTaskId.trim().equalsIgnoreCase(STACSCORS)) return cfl016;
			if(compTaskId.trim().equalsIgnoreCase(STACSTOPS)) return cfl017;
			if(compTaskId.trim().equalsIgnoreCase(STACSCOSMaP)) return cfl018;
			if(compTaskId.trim().equalsIgnoreCase(STACSCosTST)) return cfl028;
			if(compTaskId.trim().equalsIgnoreCase(STACSTIMETABLE)) return cfl029;
			if(compTaskId.trim().equalsIgnoreCase(STACSSGPACGPA)) return cfl019;
			if(compTaskId.trim().equalsIgnoreCase(STACSCORCR)) return cfl030;
			if(compTaskId.trim().equalsIgnoreCase(STACSAssignType)) return cfl020;
			if(compTaskId.trim().equalsIgnoreCase(STACSCCSE)) return cfl021;
			
			if(compTaskId.trim().equalsIgnoreCase(FISEFEEMAP)) return cfl022;
			if(compTaskId.trim().equalsIgnoreCase(FISECOURSEFEEMAP)) return cfl023;
			if(compTaskId.trim().equalsIgnoreCase(FISEMEMBERFEES)) return cfl024;
			if(compTaskId.trim().equalsIgnoreCase(FISEFPM)) return cfl025;
			if(compTaskId.trim().equalsIgnoreCase(FISEFCS)) return cfl026;
			
			if(compTaskId.trim().equalsIgnoreCase(IMAGE)) return thankyou;
			
		}catch(Exception ex){ret="cfl001"; ex.printStackTrace();}
		
		return ret;
	}
}
