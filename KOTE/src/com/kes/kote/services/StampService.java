package com.kes.kote.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import org.apache.commons.fileupload.FileUploadException;
import com.kes.kote.domain.StampCMTDomain;
import com.kes.kote.domain.StampEPWorkFlowDomain;
import com.kes.kote.domain.StampEnrollFormDomain;
import com.kes.kote.domain.StampNMTDomain;
import com.kes.kote.interfaces.StampInterface;
import com.kes.kote.interfaces.StampInterfaceImpl;
import com.kes.kote.util.SessionUtil;

public class StampService extends CommonService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	StampInterface StampIF=new StampInterfaceImpl();
	
	@Override
	public void run() throws ServletException, IOException, FileUploadException, Exception {
		// TODO Auto-generated method stub
	
		session=request.getSession(true);
		
		String From=request.getParameter("From");
		if(From.trim().equalsIgnoreCase("EPWrokFlow"))
			actionEPWrokFlow();
		else if(From.trim().equalsIgnoreCase("EnrollForm"))
			actionEnrollForm();
		else if(From.trim().equalsIgnoreCase("CMT"))
			actionCMT();
		else if(From.trim().equalsIgnoreCase("NMT"))
			actionNMT();
	}

	public void actionEPWrokFlow()
	{
		try
		{
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			int TotRow=util.getTotRow();
			ArrayList<StampEPWorkFlowDomain> EPWorkFlowDetails =new ArrayList<StampEPWorkFlowDomain>();
			if(TotRow>0)
			{
				for(int i=0;i<TotRow;i++)
				{
					String ItemName="ItemName"+i;
					String StageName="StageName"+i;
					String ApprovUID="ApprovUID"+i;
					
					ItemName=request.getParameter(ItemName).toString().trim();
					StageName=request.getParameter(StageName).toString().trim();
					ApprovUID=request.getParameter(ApprovUID).toString().trim();
					
					StampEPWorkFlowDomain domain=new StampEPWorkFlowDomain();
					domain.setItemName(ItemName);
					domain.setStageName(StageName);
					domain.setApprovedUsrID(ApprovUID);
					
					if(ItemName.trim().length()>0 && StageName.trim().length()>0 && ApprovUID.trim().length()>0)
						EPWorkFlowDetails.add(domain);
				}
			}
			int status=StampIF.saveEPWrokFlow(EPWorkFlowDetails,session);
			if(status==1)
				response.sendRedirect("../JSP/enf-enrollment-form-attributes-stamp.jsp");
			else
				response.sendRedirect("../JSP/epw-enrollment-process-workflow-stamp.jsp");
			
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public void actionEnrollForm()
	{
		try
		{
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			int TotRow=util.getTotRow();
			ArrayList<StampEnrollFormDomain> enrollFormDetails=new ArrayList<StampEnrollFormDomain>();
			if(TotRow>0)
			{
				for(int i=0;i<TotRow;i++)
				{
					
					String attributeId="attributeId"+i;
					String attributeName="attributeName"+i;
					String isRequired="isRequired"+i;
					String captionId="captionId"+i;
					String captionDesc="captionDesc"+i;
					String groupNo="groupNo"+i;
					String groupTitle="groupTitle"+i;
					String arrtibuteSeqinGrp="arrtibuteSeqinGrp"+i;
					String tabNo="tabNo"+i;
					String tabTitle="tabTitle"+i;
					String attributeSeqinTab="attributeSeqinTab"+i;
					String groupSeqinTab="groupSeqinTab"+i;
					String gridNo="gridNo"+i;
					String attributeSeqinGrid="attributeSeqinGrid"+i;
					String seqinScreen="seqinScreen"+i;
					String textBoxSize="textBoxSize"+i;
					String captionFont="captionFont"+i;
					String captionSize="captionSize"+i;
					String captionColor="captionColor"+i;
					String boldedCaption="boldedCaption"+i;
					String italicCaption="italicCaption"+i;
					String textFont="textFont"+i;
					String textSize="textSize"+i;
					String textColor="textColor"+i;
					String boldedText="boldedText"+i;
					String italicText="italicText"+i;
					String isMandatory="isMandatory"+i;
					String isEnabled="isEnabled"+i;
										
					attributeId=request.getParameter(attributeId).toString().trim();
					attributeName=request.getParameter(attributeName).toString().trim();
					isRequired=request.getParameter(isRequired).toString().trim();
					captionId=request.getParameter(captionId).toString().trim();
					captionDesc=request.getParameter(captionDesc).toString().trim();
					groupNo=request.getParameter(groupNo).toString().trim();
					groupTitle=request.getParameter(groupTitle).toString().trim();
					arrtibuteSeqinGrp=request.getParameter(arrtibuteSeqinGrp).toString().trim();
					tabNo=request.getParameter(tabNo).toString().trim();
					tabTitle=request.getParameter(tabTitle).toString().trim();
					attributeSeqinTab=request.getParameter(attributeSeqinTab).toString().trim();
					groupSeqinTab=request.getParameter(groupSeqinTab).toString().trim();
					gridNo=request.getParameter(gridNo).toString().trim();
					attributeSeqinGrid=request.getParameter(attributeSeqinGrid).toString().trim();
					seqinScreen=request.getParameter(seqinScreen).toString().trim();
					textBoxSize=request.getParameter(textBoxSize).toString().trim();
					captionFont=request.getParameter(captionFont).toString().trim();
					captionSize=request.getParameter(captionSize).toString().trim();
					captionColor=request.getParameter(captionColor).toString().trim();
					boldedCaption=request.getParameter(boldedCaption).toString().trim();
					italicCaption=request.getParameter(italicCaption).toString().trim();
					textFont=request.getParameter(textFont).toString().trim();
					textSize=request.getParameter(textSize).toString().trim();
					textColor=request.getParameter(textColor).toString().trim();
					boldedText=request.getParameter(boldedText).toString().trim();
					italicText=request.getParameter(italicText).toString().trim();
					isMandatory=request.getParameter(isMandatory).toString().trim();
					isEnabled=request.getParameter(isEnabled).toString().trim();
					
					
					StampEnrollFormDomain row=new StampEnrollFormDomain();
					row.setAttributeId(attributeId);
					row.setAttributeName(attributeName);
					row.setIsRequired(isRequired);
					row.setCaptionId(captionId);
					row.setCaptionDescription(captionDesc);
					row.setGroupNo(groupNo);
					row.setGroupTitle(groupTitle);
					row.setArrtibuteSeqinGrp(arrtibuteSeqinGrp);
					row.setTabNo(tabNo);
					row.setTabTitle(tabTitle);
					row.setAttributeSeqinTab(attributeSeqinTab);
					row.setGroupSeqinTab(groupSeqinTab);
					row.setGridNo(gridNo);
					row.setAttributeSeqinGrid(attributeSeqinGrid);
					row.setSeqinScreen(seqinScreen);
					row.setTextBoxSize(textBoxSize);
					row.setCaptionFont(captionFont);
					row.setCaptionSize(captionSize);
					row.setCaptionColor(captionColor);
					row.setBoldedCaption(boldedCaption);
					row.setItalicCaption(italicCaption);
					row.setTextFont(textFont);
					row.setTextSize(textSize);
					row.setTextColor(textColor);
					row.setBoldedText(boldedText);
					row.setItalicText(italicText);
					row.setIsMandatory(isMandatory);
					row.setIsEnabled(isEnabled);
										
					if(row.isValid())
						enrollFormDetails.add(row);
					
				}
			}
			
			int status=StampIF.saveEnrollForm(enrollFormDetails,session);
			if(status==1)
				response.sendRedirect("../JSP/act-administration-communication-template-stamp.jsp");
			else
				response.sendRedirect("../JSP/enf-enrollment-form-attributes-stamp.jsp");
			
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public void actionCMT()
	{
		try
		{
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			int TotRow=util.getTotRow();
			ArrayList<StampCMTDomain> CMTDetails =new ArrayList<StampCMTDomain>();
			if(TotRow>0)
			{
				for(int i=0;i<TotRow;i++)
				{
					String CommType="CommType"+i;
					String Subject="Subject"+i;
					String Header="Header"+i;
					String Body="Body"+i;
					String Footer="Footer"+i;
					String UIDofSender="UID"+i;
					String KeyWords="KeyWords"+i;
					
					
					CommType=request.getParameter(CommType).toString().trim();
					Subject=request.getParameter(Subject).toString().trim();
					Header=request.getParameter(Header).toString().trim();
					Body=request.getParameter(Body).toString().trim();
					Footer=request.getParameter(Footer).toString().trim();
					UIDofSender=request.getParameter(UIDofSender).toString().trim();
					KeyWords=request.getParameter(KeyWords).toString().trim();
					
					StampCMTDomain domain=new StampCMTDomain();
					domain.setCommunicationType(CommType);
					domain.setSubject(Subject);
					domain.setHeader(Header);
					domain.setBody(Body);
					domain.setFooter(Footer);
					domain.setUIDOfSender(UIDofSender);
					domain.setKeyValForRef(KeyWords);
					
					if(CommType.trim().length()>0 && Subject.trim().length()>0 &&
							Header.trim().length()>0 && Body.trim().length()>0 &&
							Footer.trim().length()>0 && UIDofSender.trim().length()>0)
					CMTDetails.add(domain);
				}
			}
			int status=StampIF.saveCMT(CMTDetails,session);
			if(status==1)
				response.sendRedirect("../JSP/ant-administration-notification-template-stamp.jsp");
			else
				response.sendRedirect("../JSP/act-administration-communication-template-stamp.jsp");
			
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public void actionNMT()
	{
		try
		{
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			int TotRow=util.getTotRow();
			ArrayList<StampNMTDomain> NMTDetails =new ArrayList<StampNMTDomain>();
			if(TotRow>0)
			{
				for(int i=0;i<TotRow;i++)
				{
					String NotifyType="NotifyType"+i;
					String Subject="Subject"+i;
					String Header="Header"+i;
					String Body="Body"+i;
					String Footer="Footer"+i;
					String UIDofSender="UID"+i;
					String KeyWords="KeyWords"+i;
					
					
					NotifyType=request.getParameter(NotifyType).toString().trim();
					Subject=request.getParameter(Subject).toString().trim();
					Header=request.getParameter(Header).toString().trim();
					Body=request.getParameter(Body).toString().trim();
					Footer=request.getParameter(Footer).toString().trim();
					UIDofSender=request.getParameter(UIDofSender).toString().trim();
					KeyWords=request.getParameter(KeyWords).toString().trim();
					
					StampNMTDomain domain=new StampNMTDomain();
					domain.setNotificationType(NotifyType);
					domain.setSubject(Subject);
					domain.setHeader(Header);
					domain.setBody(Body);
					domain.setFooter(Footer);
					domain.setUIDOfSender(UIDofSender);
					domain.setKeyValForRef(KeyWords);
					
					
					if(NotifyType.trim().length()>0 && Subject.trim().length()>0 &&
							Header.trim().length()>0 && Body.trim().length()>0 &&
							Footer.trim().length()>0 && UIDofSender.trim().length()>0)
						NMTDetails.add(domain);
				}
			}
			int status=StampIF.saveNMT(NMTDetails,session);
			if(status==1)
				response.sendRedirect("../JSP/cacs-course-catalog-system-stacs.jsp");
			else
				response.sendRedirect("../JSP/ant-administration-notification-template-stamp.jsp");
			
		}catch(Exception ex){ex.printStackTrace();}
	}
}
