package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.FeesModuleDao;

@SuppressWarnings("serial")
public class FeesModuleService extends CommonService{

	static final Logger LOGGER = Logger.getLogger(MISReportListService.class);
	String organizationId;
	String userid;
	String User;
	FeesModuleDao dao=null;
	
	@Override
	public void run() throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession();
		
		organizationId=(String)session.getAttribute("orgid");
		userid=(String)session.getAttribute("userid");
		User=(String)session.getAttribute("userid");
		
		dao=new FeesModuleDao();
		
		String PartName=request.getParameter("PartName").toString().trim();
		if(PartName.trim().equalsIgnoreCase("CreateFees"))
			actionCreateFeeComponent();
		if(PartName.trim().equalsIgnoreCase("InstituteFeesConfig"))
			actionInstituteFeesConfig();
		if(PartName.trim().equalsIgnoreCase("CourseFeesConfig"))
			actionCourseFeesConfig();
		if(PartName.trim().equalsIgnoreCase("FeesGroupConfig"))
			actionFeesGroupConfig();
		if(PartName.trim().equalsIgnoreCase("FeesReceiptTemplate"))
			actionFeesReceiptTemplate();
		if(PartName.trim().equalsIgnoreCase("FeesPaymentByStudent"))
			actionFeesPaymentByStudent();
		if(PartName.trim().equalsIgnoreCase("FeesAckByAdmin"))
			actionFeesAcknowledgeByAdmin();
	}

	void emptysessionmsgs()
	{
		if(session.getAttribute("FeeFailureMsg")!=null)
			session.removeAttribute("FeeFailureMsg");
		if(session.getAttribute("FeeSuccessMsg")!=null)
			session.removeAttribute("FeeSuccessMsg");
	}
	public void actionCreateFeeComponent()
	{
		try
		{
			String FeesID=request.getParameter("FeesID").toString().trim();
			String FeesName=request.getParameter("FeesName").toString().trim();
			
			request.setAttribute("FeesID", FeesID);
			request.setAttribute("FeesName", FeesName);
			
			boolean recFound=false;
			
			ArrayList<HashMap<String,String>> list=dao.getAllFeesComponentsByOder("");
			if(list!=null && list.size()>0)
			{
				for(int i=0;i<list.size();i++)
				{
					HashMap<String,String> info=list.get(i);
					String DBFeesName=info.get("fee_name").toString().trim();
					if(DBFeesName.trim().equalsIgnoreCase(FeesName))
					{
						recFound=true;
						i=list.size();
					}
				}
			}
			
			if(recFound || FeesName.trim().length()==0)
			{
				emptysessionmsgs();
				session.setAttribute("FeeFailureMsg", "Fees Name Already Exist");	
			}else
			{
				request.removeAttribute("FeesID");
				request.removeAttribute("FeesName");
				
				int status=dao.insertFeeComponents(FeesID, FeesName, User);
				
				emptysessionmsgs();
				if(status==1)
					session.setAttribute("FeeSuccessMsg", "Fees Component Inserted");
				else
					session.setAttribute("FeeFailureMsg", "Failed to Create Fees Name");
				
				
			}
			
			// System.out.println(FeesID +" = = "+FeesName);
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/Fees_Creation.jsp");
			dispatcher.forward(request, response); 
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public void actionInstituteFeesConfig()
	{
		try
		{
			String OrgID=request.getParameter("instid").toString().trim();
			String FeesID= request.getParameter("kesfeesid").toString().trim();
			String Prefix=request.getParameter("Prefix").toString().trim();
			String Body=request.getParameter("Body").toString().trim();
			String Suffix=request.getParameter("Suffix").toString().trim();
			String FeesDesc=request.getParameter("FeesDesc").toString().trim();
			String InstFeesID=request.getParameter("institutefeesid").toString().trim();
			
			if(session.getAttribute("failedmsg")!=null)
				session.removeAttribute("failedmsg");
			
			if(OrgID.length()>0 && FeesID.length()>0 && Prefix.length()>0 && Body.length()>0 && Suffix.length()>0 && FeesDesc.length()>0 && InstFeesID.length()>0 && InstFeesID.length()>0)
			{
				//System.out.println(OrgID+ FeesID+ Prefix+ Body+ Suffix+ FeesDesc+ InstFeesID+ InstFeesID);
				boolean recordfound=false;
				
				ArrayList<HashMap<String,String>> feesinfo=dao.getAllFeesIDByOrgID(OrgID);
				if(feesinfo!=null && feesinfo.size()>0)
				{
					for(int i=0;i<feesinfo.size();i++)
					{
						HashMap<String,String> info=feesinfo.get(i);
						String fee_code=info.get("fee_code").toString().trim();
						// System.out.println(FeesID+" == "+fee_code);
						if(FeesID.trim().equalsIgnoreCase(fee_code))
						{
							recordfound=true;
							i=feesinfo.size();
						}
					}
				}
				if(recordfound)
				{
					session.setAttribute("FeeFailureMsg", "Institute Fees Config Already Exist");
				}else
				{
					int status=dao.insertInstituteFeesConfig(OrgID, FeesID, Prefix, Body, Suffix, FeesDesc, InstFeesID, User);
					request.setAttribute("OrgID", OrgID);		
					if(status==1)
						session.setAttribute("FeeSuccessMsg", "Institute Fees Config Inserted");
					else
						session.setAttribute("FeeFailureMsg", "Failed to save Institute Fees Config");
				}
				
			}else
			{
				request.setAttribute("OrgID", OrgID);
				session.setAttribute("FeeFailureMsg", "Please fill all the fields");
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/Fees_Config.jsp");
			dispatcher.forward(request, response); 
		}catch(Exception ex){ex.printStackTrace();}
	}
	public void actionCourseFeesConfig()
	{
		try
		{
			String hiddenCurrentStatus=request.getParameter("hiddenCurrentStatus").toString().trim();
			if(hiddenCurrentStatus.trim().equalsIgnoreCase("OrgIDChanged"))
			{
				String hiddenOrgID=request.getParameter("hiddenOrgID").toString().trim();	
				ArrayList<HashMap<String,String>> CourseList=dao.getAllCoursesByOrg(hiddenOrgID);
				ArrayList<HashMap<String,String>> OrgFeesList=dao.getAllFeesIDByOrgID(hiddenOrgID);
				
				request.setAttribute("OrgID", hiddenOrgID);
				request.setAttribute("CourseList", CourseList);
				request.setAttribute("OrgFeesList", OrgFeesList);
				
			}else if(hiddenCurrentStatus.trim().equalsIgnoreCase("CourseFeesIDFilled"))
			{
				String hiddenOrgID=request.getParameter("hiddenOrgID").toString().trim();
				String hiddenCourseID=request.getParameter("hiddenCourseID").toString().trim();
				String Sequence=request.getParameter("Sequence").toString().trim();
				String hiddenFeesID=request.getParameter("hiddenFeesID").toString().trim();
				String hiddenGroup=request.getParameter("hiddenGroup").toString().trim();
				String FeesAmount=request.getParameter("FeesAmount").toString().trim();
				String hiddenCourseFeesID=request.getParameter("hiddenCourseFeesID").toString().trim();
				String FeeType=request.getParameter("FeeType").toString().trim();
				String FeeFreq=request.getParameter("FeeFreq").toString().trim();
				
				if(hiddenOrgID.length()>0 && hiddenCourseID.length()>0 && Sequence.length()>0 &&  hiddenFeesID.length()>0 
						&& hiddenGroup.length()>0 &&  FeesAmount.length()>0 && hiddenCourseFeesID.length()>0 && FeeType.length()>0 && FeeFreq.length()>0)
				{
					//mlt_id, mlt_course, fee_sequence
					
					ArrayList<HashMap<String,String>> crsfeesList=dao.checkCourseFeesIDByOrgIDAndByCourseID(hiddenOrgID, hiddenCourseID);
					boolean recordfound=false;
					if(crsfeesList!=null && crsfeesList.size()>0)
					{
						for(int i=0;i<crsfeesList.size();i++)
						{
							HashMap<String,String> info=crsfeesList.get(i);
							String fee_sequence=info.get("fee_sequence").toString().trim();
							if(Sequence.trim().equalsIgnoreCase(fee_sequence))
							{
								recordfound=true;
								i=crsfeesList.size();
							}
						}
					}
					request.setAttribute("OrgID", hiddenOrgID); //hiddenCourseID
					request.setAttribute("CourseID", hiddenCourseID);
					
					if(recordfound)
					{
						session.setAttribute("FeeFailureMsg", "Record Already Exist for Fees-Sequence");
					}else
					{
						int status=dao.insertCourseFeesConfig(hiddenOrgID, hiddenCourseID, Sequence, hiddenFeesID, hiddenGroup, FeesAmount, hiddenCourseFeesID,FeeType,FeeFreq, User);
						if(status==1)
							session.setAttribute("FeeSuccessMsg", "Course Fees Config Inserted");
						else
							session.setAttribute("FeeFailureMsg", "Failed to save Course Fees Config");
					}
					
				}else
					session.setAttribute("FeeFailureMsg", "Please fill all fields");
				
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/Coursewise_Fees.jsp");
			dispatcher.forward(request, response); 
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public void actionFeesGroupConfig()
	{
		try
		{
			String hiddenCurrentStatus=request.getParameter("hiddenCurrentStatus").toString().trim();
			// System.out.println(hiddenCurrentStatus); 
			if(hiddenCurrentStatus.trim().equalsIgnoreCase("OrgIDChanged"))
			{
				String hiddenOrgID=request.getParameter("hiddenOrgID").toString().trim();	
				ArrayList<HashMap<String,String>> CourseList=dao.getAllCoursesByOrg(hiddenOrgID);
				//ArrayList<HashMap<String,String>> MemberFeesList=dao.getAllMemberFeesIDByOrgID(hiddenOrgID);
				
				request.setAttribute("OrgID", hiddenOrgID);
				session.setAttribute("hiddenOrgID", hiddenOrgID);
				request.setAttribute("CourseList", CourseList);
				//request.setAttribute("MemberFeesList", MemberFeesList);
				
			}else if(hiddenCurrentStatus.trim().equalsIgnoreCase("CourseIDChanged"))
			{
				
				String hiddenOrgID=session.getAttribute("hiddenOrgID").toString().trim();
				String hiddenCourseID=request.getParameter("hiddenCourseID").toString().trim();
				
				// System.out.println(hiddenOrgID +" inside CourseIDChanged" + hiddenCourseID);
				
				ArrayList<HashMap<String,String>> GroupFeesList=dao.getAllGroupFeesIDByOrgIDAndByCourseID(hiddenOrgID, hiddenCourseID);
				ArrayList<HashMap<String,String>> MemberFeesList=dao.getAllMemberFeesIDByOrgID(hiddenOrgID,hiddenCourseID);
				
				System.out.println(hiddenOrgID+hiddenCourseID+GroupFeesList.size());
				request.setAttribute("OrgID", hiddenOrgID);
				request.setAttribute("CourseID", hiddenCourseID);
				session.setAttribute("CourseID", hiddenCourseID);
				ArrayList<HashMap<String,String>> CourseList=dao.getAllCoursesByOrg(hiddenOrgID);
				request.setAttribute("CourseList", CourseList);
				request.setAttribute("GroupFeesList", GroupFeesList);
				request.setAttribute("MemberFeesList", MemberFeesList);
				
				
			}
			else if(hiddenCurrentStatus.trim().equalsIgnoreCase("MemberFeesTypeChanged"))
			{
				String hiddenOrgID=request.getParameter("hiddenOrgID").toString().trim();
				String hiddenCourseID=request.getParameter("hiddenCourseID").toString().trim();
				String hiddenGroupFeesID=request.getParameter("hiddenGroupFeesID").toString().trim();
				String GroupSequence=request.getParameter("GroupSequence").toString().trim();
				String hiddenMemberFeeID=request.getParameter("hiddenMemberFeeID").toString().trim();
				String MemberFeesValue=request.getParameter("MemberFeesValue").toString().trim();
				String hiddenMemberFeesType=request.getParameter("hiddenMemberFeesType").toString().trim();
				
				//System.out.println(hiddenOrgID+hiddenCourseID+hiddenGroupFeesID+GroupSequence+hiddenMemberFeeID+MemberFeesValue+hiddenMemberFeesType);
				
				if(hiddenOrgID.length()>0 && hiddenCourseID.length()>0 && hiddenGroupFeesID.length()>0 && GroupSequence.length()>0 && hiddenMemberFeeID.length()>0 && MemberFeesValue.length()>0 && hiddenMemberFeesType.length()>0)
				{
					// mlt_course_fee_code, mlt_fee_code
					
					ArrayList<HashMap<String,String>> grpfeeslist=dao.checkFeesGroupIDByCrsFeeCodeAndByFeeCode(hiddenGroupFeesID, hiddenMemberFeeID);
					
					if(grpfeeslist!=null && grpfeeslist.size()>0)
					{
						session.setAttribute("FeeFailureMsg", "Fees Group Config Already Exist");	
					}else
					{
						int status=dao.insertFeesGroupConfig(hiddenOrgID, hiddenCourseID, hiddenGroupFeesID, GroupSequence, hiddenMemberFeeID, MemberFeesValue, hiddenMemberFeesType, User);
						if(status==1)
							session.setAttribute("FeeSuccessMsg", "Fees Group Config Inserted");
						else
							session.setAttribute("FeeFailureMsg", "Failed to save Fees Group Config");
					}
					
					request.setAttribute("OrgID", hiddenOrgID);
					request.removeAttribute("CourseList");
					request.removeAttribute("MemberFeesList");
					request.removeAttribute("GroupFeesList");
				
					
				}else
				{
					request.setAttribute("OrgID", hiddenOrgID);
					session.setAttribute("FeeFailureMsg", "Please fill all fields");
				}
				
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/Fee_Group_Config.jsp");
			dispatcher.forward(request, response); 
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public void actionFeesReceiptTemplate()
	{
		try
		{
			String hiddenCurrentStatus=request.getParameter("hiddenCurrentStatus").toString().trim();
			if(hiddenCurrentStatus.trim().equalsIgnoreCase("OrgIDChanged"))
			{
				String hiddenOrgID=request.getParameter("hiddenOrgID").toString().trim();	
				ArrayList<HashMap<String,String>> CourseList=dao.getAllCoursesByOrg(hiddenOrgID);
								
				request.setAttribute("OrgID", hiddenOrgID);
				request.setAttribute("CourseList", CourseList);
				request.removeAttribute("TemplateDataRows");
			}else if(hiddenCurrentStatus.trim().equalsIgnoreCase("LoadTemplateDatas"))
			{
				String hiddenOrgID=request.getParameter("hiddenOrgID").toString().trim();
				String hiddenCourseID=request.getParameter("hiddenCourseID").toString().trim();
				String hiddenTemplateType=request.getParameter("hiddenTemplateType").toString().trim();
				
				System.out.println(hiddenOrgID + hiddenCourseID  + hiddenTemplateType );
				ArrayList<HashMap<String,String>> TemplateDataRows=dao.getFeeReceiptTemp(hiddenOrgID, hiddenCourseID, hiddenTemplateType);
				
				 System.out.println("TemplateDataRows.size() == "+TemplateDataRows.size());
				
				ArrayList<HashMap<String,String>> CourseList=dao.getAllCoursesByOrg(hiddenOrgID);
				
				request.setAttribute("OrgID", hiddenOrgID);
				request.setAttribute("CourseList", CourseList);
				request.setAttribute("CourseID", hiddenCourseID);
				request.setAttribute("TemplateType", hiddenTemplateType);
				
				if(request.getAttribute("TemplateDataRows")!=null)
					request.removeAttribute("TemplateDataRows");
				
				request.setAttribute("TemplateDataRows", TemplateDataRows);
				
			}else if(hiddenCurrentStatus.trim().equalsIgnoreCase("AddRow"))
			{
				String hiddenOrgID=request.getParameter("hiddenOrgID").toString().trim();
				String hiddenCourseID=request.getParameter("hiddenCourseID").toString().trim();
				String hiddenTemplateType=request.getParameter("hiddenTemplateType").toString().trim();
				String hiddenRowCount=request.getParameter("hiddenRowCount").toString().trim();
				ArrayList<HashMap<String,String>> CourseList=dao.getAllCoursesByOrg(hiddenOrgID);
				
				request.setAttribute("OrgID", hiddenOrgID);
				request.setAttribute("CourseList", CourseList);
				request.setAttribute("CourseID", hiddenCourseID);
				request.setAttribute("TemplateType", hiddenTemplateType);
				
				ArrayList<HashMap<String,String>> TemplateDataRows=new ArrayList<HashMap<String,String>>();
				int rowcnt=Integer.parseInt(hiddenRowCount);
			if(rowcnt>1)
				for(int i=0;i<rowcnt-1;i++)
				{
					String LineTypeIDName="LineType_"+i;
	        		String LineNoIDName="LineNo_"+i;
	        		String TextSeqIDName="TextSeq_"+i;
	        		String TextTypeIDName="TextType_"+i;
	        		String TextValueIDName="TextValue_"+i;
	        		String DataTableIDName="DataTable_"+i;
	        		String DataColumnIDName="DataColumn_"+i;
	        		
					String LineType=request.getParameter(LineTypeIDName).toString().trim();
					String LineNo=request.getParameter(LineNoIDName).toString().trim();
					String TextSeq=request.getParameter(TextSeqIDName).toString().trim();
					String TextType=request.getParameter(TextTypeIDName).toString().trim();
					String TextValue="";
					String DataTable="";
					String DataColumn="";
					if(TextType.trim().equalsIgnoreCase("Text"))
					{
						if(request.getParameter(TextValueIDName)!=null)
							TextValue=request.getParameter(TextValueIDName).toString().trim();	
					}else
					{
						if(request.getParameter(DataTableIDName)!=null)
							DataTable=request.getParameter(DataTableIDName).toString().trim();
						
						if(request.getParameter(DataColumnIDName)!=null)
							DataColumn=request.getParameter(DataColumnIDName).toString().trim();
					}
					
			//		System.out.println(i+" = "+LineType+LineNo+TextSeq+TextType+DataTable+DataColumn);
					HashMap<String,String> htinfo=new HashMap<String,String>();
					htinfo.put("line_type", LineType);
					htinfo.put("line_no", LineNo);
					htinfo.put("text_seq", TextSeq);
					htinfo.put("text_type", TextType);
					htinfo.put("text_value", TextValue);
					htinfo.put("data_table", DataTable);
					htinfo.put("data_column", DataColumn);
					
					TemplateDataRows.add(htinfo);
				}
				HashMap<String,String> htinfo=new HashMap<String,String>();
				htinfo.put("line_type", "");
				htinfo.put("line_no", "");
				htinfo.put("text_seq", "");
				htinfo.put("text_type", "");
				htinfo.put("text_value", "");
				htinfo.put("data_table", "");
				htinfo.put("data_column", "");
				
				if(hiddenOrgID.trim().length()>0 && hiddenCourseID.trim().length()>0 && hiddenTemplateType.trim().length()>0)
					TemplateDataRows.add(htinfo);
				
				request.setAttribute("TemplateDataRows", TemplateDataRows);
			}else if(hiddenCurrentStatus.trim().equalsIgnoreCase("CallSaveAction"))
			{
				String hiddenOrgID=request.getParameter("hiddenOrgID").toString().trim();
				String hiddenCourseID=request.getParameter("hiddenCourseID").toString().trim();
				String hiddenTemplateType=request.getParameter("hiddenTemplateType").toString().trim();
				String hiddenRowCount=request.getParameter("hiddenRowCount").toString().trim();
				ArrayList<HashMap<String,String>> CourseList=dao.getAllCoursesByOrg(hiddenOrgID);
				
				request.setAttribute("OrgID", hiddenOrgID);
				request.setAttribute("CourseList", CourseList);
				request.setAttribute("CourseID", hiddenCourseID);
				// request.setAttribute("TemplateType", hiddenTemplateType);
				
				ArrayList<HashMap<String,String>> TemplateDataRows=new ArrayList<HashMap<String,String>>();
				int rowcnt=Integer.parseInt(hiddenRowCount);
			if(rowcnt>1)
				for(int i=0;i<rowcnt-1;i++)
				{
					String LineTypeIDName="LineType_"+i;
	        		String LineNoIDName="LineNo_"+i;
	        		String TextSeqIDName="TextSeq_"+i;
	        		String TextTypeIDName="TextType_"+i;
	        		String TextValueIDName="TextValue_"+i;
	        		String DataTableIDName="DataTable_"+i;
	        		String DataColumnIDName="DataColumn_"+i;
	        		
					String LineType=request.getParameter(LineTypeIDName).toString().trim();
					String LineNo=request.getParameter(LineNoIDName).toString().trim();
					String TextSeq=request.getParameter(TextSeqIDName).toString().trim();
					String TextType=request.getParameter(TextTypeIDName).toString().trim();
					String TextValue="";
					String DataTable="";
					String DataColumn="";
					if(TextType.trim().equalsIgnoreCase("Text"))
					{
						if(request.getParameter(TextValueIDName)!=null)
							TextValue=request.getParameter(TextValueIDName).toString().trim();	
					}else
					{
						if(request.getParameter(DataTableIDName)!=null)
							DataTable=request.getParameter(DataTableIDName).toString().trim();
						if(request.getParameter(DataColumnIDName)!=null)
							DataColumn=request.getParameter(DataColumnIDName).toString().trim();
							
					}
					
				//	System.out.println("inside save === "+i+" = "+LineType+LineNo+TextSeq+TextType+DataTable+DataColumn);
					HashMap<String,String> htinfo=new HashMap<String,String>();
					htinfo.put("line_type", LineType);
					htinfo.put("line_no", LineNo);
					htinfo.put("text_seq", TextSeq);
					htinfo.put("text_type", TextType);
					htinfo.put("text_value", TextValue);
					htinfo.put("data_table", DataTable);
					htinfo.put("data_column", DataColumn);
					
					if(TextType.trim().equalsIgnoreCase("Text"))
					{
						htinfo.put("data_table", "");
						htinfo.put("data_column", "");
					}
					if(TextType.trim().equalsIgnoreCase("Data") || TextType.trim().equalsIgnoreCase("Convert"))
					{
						htinfo.put("text_value", "");
					}
					try{int Lno=Integer.parseInt(LineNo); int TSeq=Integer.parseInt(TextSeq); TemplateDataRows.add(htinfo);}catch(Exception ex){}
					
				}
			
			// call dao to store
				dao.insertFeesReceiptTemplate(hiddenOrgID, hiddenCourseID, hiddenTemplateType, TemplateDataRows, User);
				session.setAttribute("FeeSuccessMsg", "Fees Receipt Config Inserted");
			}
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/Fees_Receipt_Configuration.jsp");
			dispatcher.forward(request, response); 
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public void actionFeesPaymentByStudent()
	{
		try
		{
		
			String OrgID=(String)session.getAttribute("orgid");
			String ENRID=(String)session.getAttribute("userid");
			
			HashMap<String,String> CourseInfo=dao.getCourseDescID(userid, OrgID);
			String course_discription_id="";
			String department_id="";
			String department_name="";
			String course_id="";

			if(CourseInfo!=null)
			{
				course_discription_id=CourseInfo.get("course_discription_id");
				department_id=CourseInfo.get("department_id");
				department_name=CourseInfo.get("department_name");
				course_id=CourseInfo.get("course_id");
			}
			
			String CourseID=request.getParameter("CourseID").toString().trim();
			String FeeName=request.getParameter("FeeCode").toString().trim();
			String PaymentMode=request.getParameter("PaymentMode").toString().trim();
			String InstrumentNo=request.getParameter("InstrumentNo").toString().trim();
			String AccountNo=request.getParameter("AccountNo").toString().trim();
			String ExpMonth=request.getParameter("ExpMonth").toString().trim();
			String CourseName=request.getParameter("CourseName").toString().trim();
			String FeeAmount=request.getParameter("FeeAmount").toString().trim();
			String BankName=request.getParameter("BankName").toString().trim();
			String InstrumentDate=request.getParameter("InstrumentDate").toString().trim();
			String IssuerName=request.getParameter("IssuerName").toString().trim();
			String ExpYear=request.getParameter("ExpYear").toString().trim();
			
			String FeesSeq=request.getParameter("FeeSeq").toString().trim();
			System.out.println(course_discription_id + FeeName + PaymentMode + InstrumentNo + AccountNo + ExpMonth + CourseName + FeeAmount + BankName + InstrumentDate + IssuerName + ExpYear + FeesSeq);
			
			/*String OrgID=(String)session.getAttribute("mltid").toString().trim();
			String ENRID=(String)session.getAttribute("EnrID").toString().trim();
			*/			
			/*if(course_discription_id.length()>0 && FeeName.length()>0 && PaymentMode.length()>0 && InstrumentNo.length()>0 && AccountNo.length()>0
					&& ExpMonth.length()>0 && CourseName.length()>0 && FeeAmount.length()>0 && BankName.length()>0 && InstrumentDate.length()>0 &&
					IssuerName.length()>0 && ExpYear.length()>0 && FeesSeq.length()>0)
			{*/
			dao.insertFeesPaymentByStudent(OrgID, course_discription_id, ENRID,FeeName, PaymentMode, InstrumentNo, AccountNo, ExpMonth, CourseName, FeeAmount, BankName, InstrumentDate, IssuerName, ExpYear,FeesSeq);
				
			session.setAttribute("FeeSuccessMsg", "Payment Inserted");
				
			/*}else
			{
				session.setAttribute("FeeFailureMsg", "Please fill all fields");
			}
			*/
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/Fee_Payment_ByStudent.jsp");
			dispatcher.forward(request, response); 
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public void actionFeesAcknowledgeByAdmin()
	{
		try
		{
			String orgId=session.getAttribute("orgId").toString().trim();
			String DeptID=request.getParameter("DeptID").toString().trim();
			String Enrolment=request.getParameter("Enrolment").toString().trim();
			
			String[] vals=DeptID.split("~");
			String OrgID=vals[0];
			DeptID=vals[1];
			String DeptName=vals[2];
			String cdid=vals[3];
			
			String ENRID=Enrolment;
			
			String[] stuval=ENRID.split("~");
			String stuid=stuval[1];
			String stuname=stuval[0];
			
			/*String PaymentMode=request.getParameter("PaymentMode").toString().trim();
			String InstrumentNo=request.getParameter("InstNo").toString().trim();
			String AccountNo=request.getParameter("IssuerAccNo").toString().trim();
			String ExpMonth=request.getParameter("ExpMonth").toString().trim();
			String ExpYear=request.getParameter("ExpYear").toString().trim();
			String TxnRefNo=request.getParameter("TxnRefNo").toString().trim(); */
			String PaymentStatus=request.getParameter("PaymentStatus").toString().trim();
			
			String FeeName=request.getParameter("FeeName").toString().trim();
			String feeinfo[]=FeeName.split("~");
			/*String InstrumentDate=request.getParameter("InstDate").toString().trim();
			String IssuerName=request.getParameter("IssuerName").toString().trim();
			String FeeAmount=request.getParameter("FeeAmt").toString().trim();
			String TxnRefDate=request.getParameter("TxnRefDate").toString().trim();
			
			String BankName=request.getParameter("BankName").toString().trim();
			*/
			
			HashMap<String,String> info=dao.getPaymentDetailsfromPaymentByDeptIDandEnrIDandFeesCodeandFeeSeq(orgId,cdid, stuid, feeinfo[0], feeinfo[1]);
			info.put("PaymentStatus", PaymentStatus);
			
			String fee_sequence=info.get("fee_sequence").toString().trim();
			//String Payment_Status= info.get("PaymentStatus").toString().trim();
			System.out.println("PaymentStatus="+PaymentStatus);
			System.out.println("fee_sequence="+fee_sequence);
			
			if(PaymentStatus.equalsIgnoreCase("Received"))
				System.out.println("in revied");
			if(fee_sequence.equalsIgnoreCase("1"))
				System.out.println("in seq");
			
			dao.insertFeesPaymentByAdmin(info);
			dao.changestatusforfeestruc(feeinfo[0], stuid, feeinfo[1], PaymentStatus);
			
			boolean status=true;
			if(PaymentStatus.equalsIgnoreCase("Received") && fee_sequence.equalsIgnoreCase("1"))
			{
				try
				{
					System.out.println("in if of fee service");
				HashMap<String,String> studentInfo=dao.getStudentsInfo(stuid);
				System.out.println("after method="+studentInfo);
				String to = studentInfo.get("email1").toString().trim();
				System.out.println("to-=="+to);
				String username = studentInfo.get("username").toString().trim();
				String course_id = studentInfo.get("course_id").toString().trim();
				String password = studentInfo.get("password").toString().trim();
				String org_name = studentInfo.get("org_name").toString().trim(); 
				String course_name = studentInfo.get("course_name").toString().trim();
				
				String subject = "Enrollment Id";
//				String msg = "Hello : "+userName+" " +"you have successfully enrolled for "
//						+courseName+" "+ "in  "+universityName+". "+"Your DCI User Id: "
//					    +enrollmentId+" "+ "Your DCI password: "+password;
				 String message="Hi " + username + ","+"<br/><br/>" + "You have successfully enrolled for "+course_name+" in "+org_name+". Your DCI login credentials are provided below:"+"<br/><br/>"+"<strong>"+"User ID - "+"</strong>"+stuid+"<br/> "+"<strong>"+"Password - "+"</strong>"+password+"<br/> "
			  		      +"Please click the URL to start accessing the Digital Classroom Infrastructure: "+"<br/>"+"<a href='https://www.kompacdigit.com:8443/KODE_DEV'>https://www.kompacdigit.com:8443/KODE_DEV</a>"+
			  		        "<br/><br/>" + "Thanks and Regards" + "<br/>" + "Knowledge Store Technical Team"+"<br/><br/>"+"For any technical assistance please contact on the details provided below:" + "<br/><br/>"
			  		     +   "<strong>"+"Toll Free No:"+"</strong>"+" 1800123456"+"<br/>"+"<strong>"+"SMS:"+"</strong>"+" 1234567891"+"<br/>"+"<strong>"+"Email ID:"+"</strong>"+" <a href='mailto:technicalsupport@kompaceducation.com'>technicalsupport@kompaceducation.com</a>";
			    
				System.out.println("message="+message);
				System.out.println("to="+to);
				
				Mailer.send(to, subject, message);
				
			}catch(Exception ex){
				status=false;
				session.setAttribute("FeeFailureMsg", "Failed to send Mail but Payment Acknowledge Saved");
				
			}
			
			}
			if(status)
				session.setAttribute("FeeSuccessMsg", "Payment Acknowledge Inserted");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/Fees_Payment_Acknowledge_ByAdmin.jsp");
			dispatcher.forward(request, response); 
		}catch(Exception ex){ex.printStackTrace();}
	}
}
