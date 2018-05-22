package com.kes.kote.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class SessionUtil {
	
	  final Logger logger = Logger.getLogger(SessionUtil.class);
	
	private   String userID="";
	private   String userName="";
	private   String userOrganization="";
	private   String userEmail="";
	private   String userRole="";
	private   String SuccessMsg="";
	private   String FailureMsg="";
	private   String ConfigLayerbyManualorUpload="";
	private   String TeachingSource="";
	private   String ExcelPath="";
	private   String SheetName="";
	private   String[] SheetCols=new String[26]; 
	private   ArrayList<HashMap<String,String>> SheetDetails=new ArrayList<HashMap<String,String>>();
	private   int TotCol=0;
	private   int TotRow=0;
	private   String OrgID="";
	private   String OrgName="";
	private   String OrgAddress="";
	private   String OrgCurrencyCode="INR";
	private   String OrgDateFormat="dd/MM/yyyy";
	
	public 	 void setuserID(String userid) { userID=userid; }
	public   String getuserID() { return userID; }
	public   void resetuserID() { userID=""; }
	
	public   void setuserName(String username) { userName=username; }
	public   String getuserName() { return userName; }
	public   void resetuserName() { userName=""; }
	
	public   void setusrorg(String usrorg){ userOrganization=usrorg; }
	public   String getuserOrg() { return userOrganization; }
	public   void resetuserOrg() { userOrganization=""; }
	
	public   void setusremail(String usremail) { userEmail=usremail; }
	public   String getuseremail() { return userEmail; }
	public   void resetuseremail() { userEmail=""; }
	
	public   void setusrRole(String usrrole){ userRole=usrrole; }
	public   String getuserRole() { return userRole; }
	public   void resetuserRole() { userRole=""; }
	
	public   void setsuccessmsg(String msg) { resetfailedmsg(); SuccessMsg=msg; }
	public   String getsuccessmsg() { return SuccessMsg; }
	public   void resetsuccessmsg() { SuccessMsg=""; }
	
	public   void setfailedmsg(String msg) { resetsuccessmsg(); FailureMsg=msg; }
	public   String getfailedmsg() { return FailureMsg; }
	public   void resetfailedmsg() { FailureMsg=""; }
	
	public   void resetmsgs(){	resetsuccessmsg(); resetfailedmsg(); };
	
	public   void setConfigLayerbyManualorUpload(String msg) { resetConfigLayerbyManualorUpload(); ConfigLayerbyManualorUpload=msg; }
	public   String getConfigLayerbyManualorUpload() { return ConfigLayerbyManualorUpload; }
	public   void resetConfigLayerbyManualorUpload() { ConfigLayerbyManualorUpload=""; }
	
	public   void setTeachingSource(String msg) { resetTeachingSource(); TeachingSource=msg; }
	public   String getTeachingSource() { return TeachingSource; }
	public   void resetTeachingSource() { TeachingSource=""; }
	
	public   void setExcelPath(String msg) { resetExcelPath(); ExcelPath=msg; }
	public   String getExcelPath() { return ExcelPath; }
	public   void resetExcelPath() { ExcelPath=""; }
	
	public   void setSheetName(String msg) { resetSheetName(); SheetName=msg; }
	public   String getSheetName() { return SheetName; }
	public   void resetSheetName() { SheetName=""; }
	
	public   void setSheetCols(String[] msg) { resetSheetCols(); SheetCols=msg; TotCol=SheetCols.length;}
	public   String[] getSheetCols() { return SheetCols; }
	public   void resetSheetCols() { SheetCols=new String[26]; }
	
	public   void setSheetDetails(ArrayList<HashMap<String,String>> msg) { resetSheetDetails(); SheetDetails=msg; TotRow=SheetDetails.size(); }
	public   ArrayList<HashMap<String,String>> getSheetDetails() { return SheetDetails; }
	public   void resetSheetDetails() { SheetDetails=new ArrayList<HashMap<String,String>>(); }
	
	public   void resetSheetProp(){ resetSheetName(); resetSheetCols(); resetSheetDetails(); }
	
	public   int getTotCol(){ return TotCol; }
	public   void setTotCol(int ColCnt){ TotCol=ColCnt; }
	public   void resetTotCol() {	TotCol=0;}
	
	public   int getTotRow(){ return TotRow; }
	public   void setTotRow(int RowCnt){ TotRow=RowCnt; }
	public   void resetTotRow() {	TotRow=0;}
	
	public   void setOrgID(String msg) { resetOrgID(); OrgID=msg; }
	public   String getOrgID() { return OrgID; }
	public   void resetOrgID() { OrgID=""; }
	
	public   void setOrgName(String msg) { resetOrgName(); OrgName=msg; }
	public   String getOrgName() { return OrgName; }
	public   void resetOrgName() { OrgName=""; }
	
	public   void setOrgAddress(String msg) { resetOrgAddress(); OrgAddress=msg; }
	public   String getOrgAddress() { return OrgAddress; }
	public   void resetOrgAddress() { OrgAddress=""; }
	
	public   void setOrgCurrencyCode(String msg) { resetOrgCurrencyCode(); OrgCurrencyCode=msg; }
	public   String getOrgCurrencyCode() { return OrgCurrencyCode; }
	public   void resetOrgCurrencyCode() { OrgCurrencyCode="INR"; }
	
	public   void setOrgDateFormat(String msg)
	{ 
		resetOrgDateFormat();
		if(msg.trim().equalsIgnoreCase("dd/mm/yyyy")) OrgDateFormat="dd/MM/yyyy";
		if(msg.trim().equalsIgnoreCase("mm/dd/yyyy")) OrgDateFormat="MM/dd/yyyy";
		if(msg.trim().equalsIgnoreCase("yyyy/mm/dd")) OrgDateFormat="yyyy/MM/dd";
		if(msg.trim().equalsIgnoreCase("yyyy/dd/mm")) OrgDateFormat="yyyy/dd/MM";
		
		if(msg.trim().equalsIgnoreCase("dd-mm-yyyy")) OrgDateFormat="dd-MM-yyyy";
		if(msg.trim().equalsIgnoreCase("mm-dd-yyyy")) OrgDateFormat="MM-dd-yyyy";
		if(msg.trim().equalsIgnoreCase("yyyy-mm-dd")) OrgDateFormat="yyyy-MM-dd";
		if(msg.trim().equalsIgnoreCase("yyyy-dd-mm")) OrgDateFormat="yyyy-dd-MM";
	}
	public   String getOrgDateFormat() { return OrgDateFormat; }
	public   void resetOrgDateFormat() { OrgDateFormat="dd/MM/yyyy"; }
	
	public   void resetEverthing(){ resetuserID(); resetuserName(); resetuserOrg(); resetuserRole(); resetuseremail();
											resetTeachingSource(); resetConfigLayerbyManualorUpload(); resetExcelPath(); resetmsgs();
													resetSheetProp(); resetTotCol(); resetTotRow(); resetOrgID(); resetOrgName() ; resetOrgAddress();
														resetOrgCurrencyCode(); resetOrgDateFormat(); deleteExcelFile(); }

	
	public   void print(String text)
	{
		logger.info(text); 
		System.out.println(text);	
		
	} 
	public   void printStringArray(String[] arr)
	{
		if(arr.length>0)
		{	
			print("starts Printing Array in console");
			for(int i=0;i<arr.length;i++)
			{
				System.out.print(i+"="+arr[i]+"	; ");
				logger.info(i+"="+arr[i]+"	; ");
			}
			print("");
			print("ends Printing Array in console");
		}
			
	}
	public   void printsheetdata()
	{
		try
		{
			if(SheetDetails!=null && SheetDetails.size()>0)
			{
				for(int i=0;i<SheetDetails.size();i++)
				{
					HashMap<String,String> info=SheetDetails.get(i);
					System.out.print(i+"= ");
					for(int j=0;j<SheetCols.length;j++)
					{
						System.out.print(SheetCols[j]+"="+info.get(SheetCols[j])+"	");
					}
					print("");
				}
			}
			
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public   void deleteExcelFile()
	{
		if(getExcelPath()!=null && getExcelPath().length()>0)
		{
			File f=new File(getExcelPath());
			f.delete();
		}
		
	}
	@Override
	public String toString() {
		return "SessionUtil [userID=" + userID + ", userName=" + userName + ", userOrganization=" + userOrganization
				+ ", userEmail=" + userEmail + ", userRole=" + userRole + ", SuccessMsg=" + SuccessMsg + ", FailureMsg="
				+ FailureMsg + ", ConfigLayerbyManualorUpload=" + ConfigLayerbyManualorUpload + ", TeachingSource="
				+ TeachingSource + ", OrgID=" + OrgID + ", OrgName=" + OrgName + ", OrgAddress=" + OrgAddress
				+ ", OrgCurrencyCode=" + OrgCurrencyCode + "]";
	}


	
	
}
