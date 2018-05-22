package com.kes.kote.util;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.kes.kote.domain.CineOrgDetailsDomain;
import com.kes.kote.domain.CineSpocsDomain;
import com.kes.kote.domain.FiseCourseFeeMapDomain;
import com.kes.kote.domain.FiseFCSDomain;
import com.kes.kote.domain.FiseFPMDomain;
import com.kes.kote.domain.FiseFeeMapDomain;
import com.kes.kote.domain.FiseMemberFeesDomain;
import com.kes.kote.domain.StacsAssignmentTypesDomain;
import com.kes.kote.domain.StacsCOSMapDomain;
import com.kes.kote.domain.StacsCOSTSTMapDomain;
import com.kes.kote.domain.StacsCertConfigDomain;
import com.kes.kote.domain.StacsCourseCatelogDomain;
import com.kes.kote.domain.StacsCourseCreditDomain;
import com.kes.kote.domain.StacsSGPACGPADomain;
import com.kes.kote.domain.StacsSubjectsDomain;
import com.kes.kote.domain.StacsTimeTableDomain;
import com.kes.kote.domain.StacsTopicsSubTopicDomain;
import com.kes.kote.domain.StampCMTDomain;
import com.kes.kote.domain.StampEPWorkFlowDomain;
import com.kes.kote.domain.StampEnrollFormDomain;
import com.kes.kote.domain.StampNMTDomain;
import com.kes.kote.domain.UseAccessDomain;
import com.kes.kote.domain.UseMailServerDomain;
import com.kes.kote.domain.UseNotificationDomain;
import com.kes.kote.domain.UseParamDomain;
import com.kes.kote.domain.UseRoleDomain;
import com.kes.kote.domain.UseRoleMapDomain;
import com.kes.kote.domain.UseUserDomain;

public class ExcelSheetUtil {

	//CINE START
public static final String 	ORGDETAILS="Organization Details"; // PropertiesUtil.getExcelSheetProperty("cineorg.sheetname");
public static final int 	ORGDETAILS_NO=0;	//Integer.parseInt(PropertiesUtil.getExcelSheetProperty("cineorg.sheetno"));
public static final int		ORGCOLUMNCOUNT=15;	//Integer.parseInt(PropertiesUtil.getExcelSheetProperty("cineorg.columncount"));
public static final int		ORGROWCOUNT=2; 		//Integer.parseInt(PropertiesUtil.getExcelSheetProperty("cineorg.rowcount"));

public static final String 	SPOCDETAILS= "SPOC Details"; // SPOC Details
public static final int 	SPOCDETAILS_NO= 1;
public static final int		SPOCCOLUMNCOUNT=8;
public static final int		SPOCROWCOUNT=5;
//CINE END

//USE START
public static final String 	USEPARAM="USE Param";
public static final int 	USEPARAM_NO=2;
public static final int		USEPARAMCOLUMNCOUNT=4;
public static final int		USEPARAMROWCOUNT=13;

public static final String 	MAILSERVER="Mail Server";
public static final int 	MAILSERVER_NO=3;
public static final int		MAILSERVERCOLUMNCOUNT=4;
public static final int		MAILSERVERROWCOUNT=5;

public static final String 	USER="USE - User";
public static final int 	USER_NO=4;
public static final int		USERCOLUMNCOUNT=18;
public static final int		USERROWCOUNT=501;

public static final String 	ROLE="Role";
public static final int 	ROLE_NO=5;
public static final int		ROLECOLUMNCOUNT=3;
public static final int		ROLEROWCOUNT=21;

public static final String 	USERROLEMAP="UserRole Map";
public static final int 	USERROLEMAP_NO=6;
public static final int		USERROLEMAPCOLUMNCOUNT=2;
public static final int		USERROLEMAPROWCOUNT=501;

public static final String 	ACCESSRIGHTS="AccessRights";
public static final int 	ACCESSRIGHTS_NO=7;
public static final int		ACCESSRIGHTSCOLUMNCOUNT=5;
public static final int		ACCESSRIGHTSROWCOUNT=501;

public static final String 	UNT="USE UNOM";
public static final int 	UNT_NO=8;
public static final int		UNTCOLUMNCOUNT=7;
public static final int		UNTROWCOUNT=7;
//USE END

//STAMP START
public static final String 	ENROLLWORKFLOW="Enrollment Process Work Flow";
public static final int 	ENROLLWORKFLOW_NO=9;
public static final int		ENROLLWORKFLOWCOLUMNCOUNT=4;
public static final int		ENROLLWORKFLOWROWCOUNT=101;

public static final String 	ENROLLFORM="Enrollment Form";
public static final int 	ENROLLFORM_NO=10;
public static final int		ENROLLFORMCOLUMNCOUNT=27;
public static final int		ENROLLFORMROWCOUNT=88;

public static final String 	ACT="Communication Mail Template";
public static final int 	ACT_NO=11;
public static final int		ACTCOLUMNCOUNT=7;
public static final int		ACTROWCOUNT=13;

public static final String 	ANT="Notification Mail Template";
public static final int 	ANT_NO=12;
public static final int		ANTCOLUMNCOUNT=7;
public static final int		ANTROWCOUNT=9;
//STAMP END

//STACS START
public static final String 	COURSECATALOG="CourseCatalog";
public static final int 	COURSECATALOG_NO=13;
public static final int		COURSECATALOGCOLUMNCOUNT=16;
public static final int		COURSECATALOGROWCOUNT=301;

public static final String 	SUBJECTS="Subjects";
public static final int 	SUBJECTS_NO=14;
public static final int		SUBJECTSCOLUMNCOUNT=2;
public static final int		SUBJECTSROWCOUNT=501;

public static final String 	TOPICSUBTOPIC="TopicSubTopic";
public static final int 	TOPICSUBTOPIC_NO=15;
public static final int		TOPICSUBTOPICCOLUMNCOUNT=5;
public static final int		TOPICSUBTOPICROWCOUNT=501;

public static final String 	COSMAP="COSMap";
public static final int 	COSMAP_NO=16;
public static final int		COSMAPCOLUMNCOUNT=11;
public static final int		COSMAPROWCOUNT=501;

public static final String 	COSTSTMAP="COSTSTMap";
public static final int 	COSTSTMAP_NO=17;
public static final int		COSTSTMAPCOLUMNCOUNT=13;
public static final int		COSTSTMAPROWCOUNT=501;

public static final String 	TIMETABLE="TimeTable";
public static final int 	TIMETABLE_NO=17;
public static final int		TIMETABLECOLUMNCOUNT=18;
public static final int		TIMETABLEROWCOUNT=501;

public static final String 	SGPACGPA="SGPA-CGPA";
public static final int 	SGPACGPA_NO=17;
public static final int		SGPACGPACOLUMNCOUNT=5;
public static final int		SGPACGPAROWCOUNT=51;

public static final String 	COURSECREDIT="CourseCredit";
public static final int 	COURSECREDIT_NO=18;
public static final int		COURSECREDITCOLUMNCOUNT=13;
public static final int		COURSECREDITROWCOUNT=100;

public static final String ASSIGNTYPES="Assignment_Types";
public static final int 	ASSIGNTYPES_NO=19;
public static final int		ASSIGNTYPESCOLUMNCOUNT=4;
public static final int		ASSIGNTYPESROWCOUNT=20;


public static final String 	CERTCONFIG="CertConfig.";
public static final int 	CERTCONFIG_NO=19;
public static final int		CERTCONFIGCOLUMNCOUNT=12;
public static final int		CERTCONFIGROWCOUNT=501;
//STACS END

//FISE START
public static final String 	FEEMAP="FeeMap";
public static final int 	FEEMAP_NO=20;
public static final int		FEEMAPCOLUMNCOUNT=7;
public static final int		FEEMAPROWCOUNT=25;

public static final String 	CFMAP="CFMap";
public static final int 	CFMAP_NO=21;
public static final int		CFMAPCOLUMNCOUNT=8;
public static final int		CFMAPROWCOUNT=501;

public static final String 	MEMBERFEE="MemberFee";
public static final int 	MEMBERFEE_NO=22;
public static final int		MEMBERFEECOLUMNCOUNT=6;
public static final int		MEMBERFEEROWCOUNT=501;

public static final String 	FPM="FISE-FPM";
public static final int 	FPM_NO=23;
public static final int		FPMCOLUMNCOUNT=4;
public static final int		FPMROWCOUNT=10;

public static final String 	FCMS="FCS";
public static final int 	FCMS_NO=24;
public static final int		FCMSCOLUMNCOUNT=3;
public static final int		FCMSROWCOUNT=9;
//FISE END

//upload or manual
public static final String MANUAL="Manual";
public static final String UPLOAD="Upload";

public static CineOrgDetailsDomain getOrgDetails(HttpSession sess)
{
	CineOrgDetailsDomain ret=null;
	try
	{
		ret=CineExcelReading.getOrgDetails(sess); 
				
	}catch(Exception ex){ex.printStackTrace();}
	return ret;
}

public static List<CineSpocsDomain> getSpocsDetails(HttpSession sess)
{
	List<CineSpocsDomain> ret=null;
	try
	{
		ret=CineExcelReading.getSpocsDetails(sess);
		
	}catch(Exception ex){ex.printStackTrace();}
	return ret;
}

public static List<UseParamDomain> getUserParamDetails(HttpSession sess) {
	List<UseParamDomain> result = null;
	try {
		result = UseExcelReading.getUseParamDetails(sess);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public static List<UseMailServerDomain> getUseMailServerDetails(HttpSession sess) {
	List<UseMailServerDomain> result = null;
	try {
		result = UseExcelReading.getUseMailServerDetails(sess);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public static List<UseUserDomain> getUseUsers(HttpSession sess) {
	List<UseUserDomain> result = null;
	try {
		result = UseExcelReading.getUseUsers(sess);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}


public static List<UseRoleDomain> getUseRoles(HttpSession sess) {
	List<UseRoleDomain> result = null;
	try {
		result = UseExcelReading.getUseRoles(sess);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public static List<UseRoleMapDomain> getUseRoleMap(HttpSession sess) {
	List<UseRoleMapDomain> result = null;
	try {
		result = UseExcelReading.getUseRoleMap(sess);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public static List<UseAccessDomain> getUseAccessRights(HttpSession sess) {
	List<UseAccessDomain> result = null;
	try {
		result = UseExcelReading.getUseAccessRights(sess);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public static List<UseNotificationDomain> getUseNotification(HttpSession sess) {
	List<UseNotificationDomain> result = null;
	try {
		result = UseExcelReading.getUseNotification(sess);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}


public static List<StampEPWorkFlowDomain> getEPWorkFlowDetails(HttpSession sess)
{
	List<StampEPWorkFlowDomain> ret=null;
	try
	{
		ret=StampExcelReading.getEPWorkFlowDetails(sess);
		
	}catch(Exception ex){ex.printStackTrace();}
	return ret;
}

public static List<StampEnrollFormDomain> getEnrollFormDetails(HttpSession sess)
{
	List<StampEnrollFormDomain> ret=null;
	try
	{
		ret=StampExcelReading.getEnrollFormDetails(sess);
		
	}catch(Exception ex){ex.printStackTrace();}
	return ret;
}

public static List<StampCMTDomain> getCMTDetails(HttpSession sess)
{
	List<StampCMTDomain> ret=null;
	try
	{
		ret=StampExcelReading.getCMTDetails(sess);
	}catch(Exception ex){ex.printStackTrace();}
	return ret;
}

public static List<StampNMTDomain> getNMTDetails(HttpSession sess)
{
	List<StampNMTDomain> ret=null;
	try
	{
		ret=StampExcelReading.getNMTDetails(sess);
	}catch(Exception ex){ex.printStackTrace();}
	return ret;
}

public static List<StacsCourseCatelogDomain> getCourseCatelogDetails(HttpSession sess)
{
	List<StacsCourseCatelogDomain> ret=null;
	try
	{
		ret=StacsExcelReading.getcourseCatelogDetails(sess);
	}catch(Exception ex){ex.printStackTrace();}
	return ret;
}

public static List<StacsSubjectsDomain> getSubjectDetails(HttpSession sess)
{
	List<StacsSubjectsDomain> ret=null;
	try
	{
		ret=StacsExcelReading.getSubjectDetails(sess);
	}catch(Exception ex){ex.printStackTrace();}
	
	return ret;
}

public static List<StacsCOSMapDomain> getCOSMapDetails(HttpSession sess)
{
	List<StacsCOSMapDomain> ret=null;
	try
	{
		ret=StacsExcelReading.getCOSMapDetails(sess);
	}catch(Exception ex){ex.printStackTrace();}
	return ret;
}

public static List<StacsTopicsSubTopicDomain> getTopicsSubTopicDetails(HttpSession sess)
{
	List<StacsTopicsSubTopicDomain> ret=null;
	try
	{
		ret=StacsExcelReading.getTopicsSubTopicDetails(sess);
	}catch(Exception ex){ex.printStackTrace();}
	return ret;
}

public static List<StacsCOSTSTMapDomain> getCOSTSTMapDetails(HttpSession sess)
{
	List<StacsCOSTSTMapDomain> ret=null;
	try
	{
		ret=StacsExcelReading.getCOSTSTMapDetails(sess);
	}catch(Exception ex){ex.printStackTrace();}
	return ret;
}

public static List<StacsTimeTableDomain> getTimeTableDetails(HttpSession sess)
{
	List<StacsTimeTableDomain> ret=null;
	try
	{
		ret=StacsExcelReading.getTimeTableDetails(sess);
	}catch(Exception ex){ex.printStackTrace();}
	return ret;
}
public static List<StacsSGPACGPADomain> getSgpaCgpaDetails(HttpSession sess)
{
	List<StacsSGPACGPADomain> ret=null;
	try
	{
		ret=StacsExcelReading.getSgpaCgpaDetails(sess);
	}catch(Exception ex){ex.printStackTrace();}
	return ret;
}

public static List<StacsCourseCreditDomain> getCourseCreditDetails(HttpSession sess)
{
	List<StacsCourseCreditDomain> ret=null;
	try
	{
		ret=StacsExcelReading.getCourseCreditDetails(sess);
	}catch(Exception ex){ex.printStackTrace();}
	return ret;
}

public static List<StacsAssignmentTypesDomain> getAssignTypesDetails(HttpSession sess)
{
	List<StacsAssignmentTypesDomain> ret=null;
	try
	{
		ret=StacsExcelReading.getAssignTypesDetails(sess);
	}catch(Exception ex){ex.printStackTrace();}
	return ret;
}

public static List<StacsCertConfigDomain> getCertificateDetails(HttpSession sess)
{
	List<StacsCertConfigDomain> ret=null;
	try
	{
		ret=StacsExcelReading.getCertificateDetails(sess);
	}catch(Exception ex){ex.printStackTrace();}
	return ret;
}

public static List<FiseFeeMapDomain> getFeeMapDetails(HttpSession sess)
{
	List<FiseFeeMapDomain> ret=null;
	try
	{
		ret=FiseExcelReading.getFeeMapDetails(sess);
	}catch(Exception ex){ex.printStackTrace();}
	
	return ret;
}

public static List<FiseCourseFeeMapDomain> getCrsFeeMapDetails(HttpSession sess)
{
	List<FiseCourseFeeMapDomain> ret=null;
	try
	{
		ret=FiseExcelReading.getCrsFeeMapDetails(sess);
	}catch(Exception ex){ex.printStackTrace();}
	
	return ret;
}

public static List<FiseMemberFeesDomain> getMemberFeeDetails(HttpSession sess)
{
	List<FiseMemberFeesDomain> ret=null;
	try
	{
		ret=FiseExcelReading.getMemberFeeDetails(sess);
	}catch(Exception ex){ex.printStackTrace();}
	
	return ret;
}

public static List<FiseFPMDomain> getFPMDetails(HttpSession sess)
{
	List<FiseFPMDomain> ret=null;
	try
	{
		ret=FiseExcelReading.getFPMDetails(sess);
	}catch(Exception ex){ex.printStackTrace();}
	
	return ret;
}

public static List<FiseFCSDomain> getFCSDetails(HttpSession sess)
{
	List<FiseFCSDomain> ret=new  ArrayList<FiseFCSDomain>();
	try
	{
		ret=FiseExcelReading.getFCSDetails(sess);
	}catch(Exception ex){ex.printStackTrace();}
	
	return ret;
}

public static int getRowCountVal(String SheetName)
{
		int counter=10000;
		if(SheetName.trim().equalsIgnoreCase(ORGDETAILS)) counter= ORGROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(SPOCDETAILS)) counter= SPOCROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(USEPARAM)) counter= USEPARAMROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(MAILSERVER)) counter= MAILSERVERROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(USER)) counter= USERROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(ROLE)) counter= ROLEROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(USERROLEMAP)) counter= USERROLEMAPROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(ACCESSRIGHTS)) counter= ACCESSRIGHTSROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(UNT)) counter= UNTROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(ENROLLWORKFLOW)) counter= ENROLLWORKFLOWROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(ENROLLFORM)) counter= ENROLLFORMROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(ACT)) counter= ACTROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(ANT)) counter= ANTROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(COURSECATALOG)) counter= COURSECATALOGROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(SUBJECTS)) counter= SUBJECTSROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(TOPICSUBTOPIC)) counter= TOPICSUBTOPICROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(COSMAP)) counter= COSMAPROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(COSTSTMAP)) counter= COSTSTMAPROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(TIMETABLE)) counter= TIMETABLEROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(SGPACGPA)) counter= SGPACGPAROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(COURSECREDIT)) counter= COURSECREDITROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(ASSIGNTYPES)) counter= ASSIGNTYPESROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(CERTCONFIG)) counter= CERTCONFIGROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(FEEMAP)) counter= FEEMAPROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(CFMAP)) counter= CFMAPROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(MEMBERFEE)) counter= MEMBERFEEROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(FPM)) counter= FPMROWCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(FCMS)) counter= FCMSROWCOUNT;
		
		
		return counter;
	}
public static int getColCountVal(String SheetName)
{
		int counter=31;
		if(SheetName.trim().equalsIgnoreCase(ORGDETAILS)) counter= ORGCOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(SPOCDETAILS)) counter= SPOCCOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(USEPARAM)) counter= USEPARAMCOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(MAILSERVER)) counter= MAILSERVERCOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(USER)) counter= USERCOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(ROLE)) counter= ROLECOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(USERROLEMAP)) counter= USERROLEMAPCOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(ACCESSRIGHTS)) counter= ACCESSRIGHTSCOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(UNT)) counter= UNTCOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(ENROLLWORKFLOW)) counter= ENROLLWORKFLOWCOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(ENROLLFORM)) counter= ENROLLFORMCOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(ACT)) counter= ACTCOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(ANT)) counter= ANTCOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(COURSECATALOG)) counter= COURSECATALOGCOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(SUBJECTS)) counter= SUBJECTSCOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(TOPICSUBTOPIC)) counter= TOPICSUBTOPICCOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(COSMAP)) counter= (COSMAPROWCOUNT*COSMAPCOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(COSTSTMAP)) counter= COSTSTMAPCOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(TIMETABLE)) counter= TIMETABLECOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(SGPACGPA)) counter= SGPACGPACOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(COURSECREDIT)) counter= COURSECREDITCOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(ASSIGNTYPES)) counter= ASSIGNTYPESCOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(CERTCONFIG)) counter= CERTCONFIGCOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(FEEMAP)) counter= FEEMAPCOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(CFMAP)) counter= CFMAPCOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(MEMBERFEE)) counter= MEMBERFEECOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(FPM)) counter= FPMCOLUMNCOUNT;
		else if(SheetName.trim().equalsIgnoreCase(FCMS)) counter= FCMSCOLUMNCOUNT;
		
		
		return counter;
	}

public static int getCounterVal(String SheetName)
{
		int counter=130013;
		if(SheetName.trim().equalsIgnoreCase(ORGDETAILS)) counter= (ORGROWCOUNT*ORGCOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(SPOCDETAILS)) counter= (SPOCROWCOUNT*SPOCCOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(USEPARAM)) counter= (USEPARAMROWCOUNT*USEPARAMCOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(MAILSERVER)) counter= (MAILSERVERROWCOUNT*MAILSERVERCOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(USER)) counter= (USERROWCOUNT*USERCOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(ROLE)) counter= (ROLEROWCOUNT*ROLECOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(USERROLEMAP)) counter= (USERROLEMAPROWCOUNT*USERROLEMAPCOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(ACCESSRIGHTS)) counter= (ACCESSRIGHTSROWCOUNT*ACCESSRIGHTSCOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(UNT)) counter= (UNTROWCOUNT*UNTCOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(ENROLLWORKFLOW)) counter= (ENROLLWORKFLOWROWCOUNT*ENROLLWORKFLOWCOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(ACT)) counter= (ACTROWCOUNT*ACTCOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(ANT)) counter= (ANTROWCOUNT*ANTCOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(COURSECATALOG)) counter= (COURSECATALOGROWCOUNT*COURSECATALOGCOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(SUBJECTS)) counter= (SUBJECTSROWCOUNT*SUBJECTSCOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(TOPICSUBTOPIC)) counter= (TOPICSUBTOPICROWCOUNT*TOPICSUBTOPICCOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(COSMAP)) counter= (COSMAPROWCOUNT*COSMAPCOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(COSTSTMAP)) counter= (COSTSTMAPROWCOUNT*COSTSTMAPCOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(TIMETABLE)) counter= (TIMETABLEROWCOUNT*TIMETABLECOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(SGPACGPA)) counter= (SGPACGPAROWCOUNT*SGPACGPACOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(COURSECREDIT)) counter= (COURSECREDITROWCOUNT*COURSECREDITCOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(ASSIGNTYPES)) counter= (ASSIGNTYPESROWCOUNT*ASSIGNTYPESCOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(CERTCONFIG)) counter= (CERTCONFIGROWCOUNT*CERTCONFIGCOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(FEEMAP)) counter= (FEEMAPROWCOUNT*FEEMAPCOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(CFMAP)) counter= (CFMAPROWCOUNT*CFMAPCOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(MEMBERFEE)) counter= (MEMBERFEEROWCOUNT*MEMBERFEECOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(FPM)) counter= (FPMROWCOUNT*FPMCOLUMNCOUNT);
		else if(SheetName.trim().equalsIgnoreCase(FCMS)) counter= (FCMSROWCOUNT*FCMSCOLUMNCOUNT);
		
		
		return counter;
	}
}
