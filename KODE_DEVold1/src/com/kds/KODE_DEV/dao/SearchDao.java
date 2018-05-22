package com.kds.KODE_DEV.dao;

//This class Naming Convention and coding standered changed by Paramvir Singh     

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AssessmentDomain;
import com.kds.KODE_DEV.domain.CreateDomain;

public class SearchDao {
	
	ResultSet resultSet = null;
	PreparedStatement preparedStatement=null;
	Connection connection= null;
	CreateDomain createDomain= new CreateDomain();
	
	ArrayList<CreateDomain> arrayListForOrgDetails = new ArrayList<CreateDomain>();
	public ArrayList<CreateDomain> getOrg_id( ){
		
	try{
		
		connection=DBTransaction.connect();
		String sql= "select organization_id,organization_name from org_details";
		preparedStatement=connection.prepareStatement(sql);
		resultSet=preparedStatement.executeQuery();
		
		while(resultSet.next())
		{
		 CreateDomain cDomain= new CreateDomain();
		 cDomain.setOrg_id(resultSet.getString("organization_id"));
		 cDomain.setOrg_name(resultSet.getString("organization_name"));
		 arrayListForOrgDetails.add(cDomain);
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	   {
		   try{
			   connection.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	return arrayListForOrgDetails;	
	} 
	
	//This Method is for getting sequence value for assessment_id(Table- assessment_info) 
	public String getSequenceValue(){
		
		String sequenceValueAssessment = "";
		
		try{			
			connection=DBTransaction.connect();
			String sql= "select nextval('assessment_id_seq')";

			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			//System.out.println("assessment id query in dao:"+preparedStatement);
			while(resultSet.next())
			{
				sequenceValueAssessment=resultSet.getString(1);
			 
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return sequenceValueAssessment;	
		
		} 
	
	//This method is for getting sequence for assignment_id(Table -assignment_info)
	public String getAggignSequenceValue(){
		
		String sequenceValueAssignment = "";
		
		try{
				
			
			connection=DBTransaction.connect();
			
			String sql= "select nextval('assignment_id_seq')";

			preparedStatement = connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			//System.out.println("assignment id query in dao:"+preparedStatement);
			
			while(resultSet.next())
			{
				sequenceValueAssignment=resultSet.getString(1);
			 
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return sequenceValueAssignment;	
		} 
	
	
	public CreateDomain getOrganizationDetails(String orgid){
		
	try{
	
		connection = DBTransaction.connect();
		
		String sql= "select * from org_details where organization_id='"+orgid+"'";
		preparedStatement=connection.prepareStatement(sql);
		resultSet=preparedStatement.executeQuery();
		//System.out.println("select orgid query:"+sql);
		while(resultSet.next())
		{
		// CreateDomain createDomain= new CreateDomain();
		 createDomain.setOrg_id(resultSet.getString("organization_id"));
		 createDomain.setOrg_name(resultSet.getString("organization_name"));
		 createDomain.setOrg_type(resultSet.getString("org_type"));
		 createDomain.setAddress(resultSet.getString("address"));
		 createDomain.setCity(resultSet.getString("city"));
		 createDomain.setCountry(resultSet.getString("country"));
		 createDomain.setPcode(resultSet.getString("postal_code"));
		 createDomain.setPhone(resultSet.getString("telephone"));
		 createDomain.setFax(resultSet.getString("fax"));
		 createDomain.setEcno(resultSet.getString("emergency_contact_no"));
		 createDomain.setEmail_id(resultSet.getString("email"));
		 createDomain.setUrl(resultSet.getString("url"));
		 createDomain.setLogo(resultSet.getString("logo"));
		 createDomain.setYof(resultSet.getString("year_of_foundation"));
		 createDomain.setDate(resultSet.getTimestamp("date_time"));
		 createDomain.setState(resultSet.getString("state"));
		 //organizationValues.add(createDomain);
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}		
	finally
	   {
		   try{
			   connection.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	return createDomain;	
	} 
	
	// This method designed by Paramvir Singh for getting RecipientTypeForAssessment on 2ndfeb 16
	
	ArrayList<AssessmentDomain> arrayListForAssessment = new ArrayList<AssessmentDomain>();
	public ArrayList<AssessmentDomain> getRecipientType(AssessmentDomain assessment , String orgId, String userId){
		System.out.println("It is DAO of getRecipientType");
		
		AssessmentDomain assessmentDomain=null;
		
		try {
			
			 connection = DBTransaction.connect();
			String assessment_id=assessment.getAssessmentId();
	
			String tempQuery="SELECT distinct AI.assessment_id,AI.due_date,AI.assessment_type,AI.assessment_description,assessment_name,ASI.marks,status,remarks,path_of_file,path,user_id,username,AI.uploaded_by"
					+ ", (case when ASI.uploaded_by is not null and path_of_file is not null then 'Yes' else 'No' end) completed "
					+ "from (SELECT distinct AI.due_date,AI.assessment_type,AI.assessment_id,AI.assessment_description,assessment_name,path_of_file,user_id,username,AI.uploaded_by "
					+ " FROM assessment_info AI left join student_groups SG on AI.recipient_type=SG.group_name and AI.organization_id = SG.organization_id "
					+ "cross join users_info UI where privilege='Student' and (CASE WHEN AI.recipient_type = 'All' then TRUE when UI.user_id IN (SELECT regexp_split_to_table(student_id,E'\\#+')) then TRUE "
					+ " else UI.user_id IN (SELECT regexp_split_to_table(recipient_type,E'\\#+'))  end) "
					+ " AND AI.assessment_id like '"+assessment.getAssessmentId()+"' and UI.organization_id='"+orgId+"' and AI.uploaded_by='"+userId+"' )"
					+ " AI LEFT JOIN assessment_student_info ASI ON AI.assessment_id = ASI.assessment_id and AI.user_id = ASI.uploaded_by ";
			
		String query=" select a.assessment_id, a.assessment_name, a.course_id, f.department_name, a.subject_id, b.subname, a.topic_id, c.topicname, a.subtopic_id, d.subtopicname, a.recipient_type, a.marks, a.path_of_file from" 
+" assessment_info a, subject_description b, topic_description c, sub_topic_description d, mst_course_catalogue e, transaction_department_info f where"
+" a.organization_id = '"+orgId+"' and"
+" a.uploaded_by = 'SRMSRM04004' and"
+" b.subject_id = a.subject_id and c.topic_id = a.topic_id and d.sub_topic_id = a.subtopic_id and"
+" e.organization_id = a.organization_id and e.deptid_by_ts = a.course_id and"
+" f.department_id = e.dept_id";
		
		
		String query1="select a.assessment_id,a.course_id,a.due_date,a.assessment_description,a.assessment_type,a.subject_id, a.assessment_name, g.uploaded_by as enrno, h.full_name, a.marks as total_marks, g.marks, g.status, g.remarks, g.path, g.uploaded_date from" 
+" assessment_info a,assessment_student_info g, transaction_enrollment_process h where"
+" a.organization_id = '"+orgId+"' and"
+" a.uploaded_by = '"+userId+"' and"
+" a.course_id='"+assessment.getCourseId()+"' and"
+" subject_id='"+assessment.getSubjectId()+"'";
			
			preparedStatement = connection.prepareStatement(query1);

			System.out.println("the query is:" + query1);
			//preparedStatement = connection.prepareStatement(tempQuery);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				
				 assessmentDomain = new AssessmentDomain();
				 System.out.println("path="+resultSet.getString("path"));
				//String recipientType = resultSet.getString("recipient_type");
				// String enrno=resultSet.getString("enrno");
				assessmentDomain.setRecipientType(resultSet.getString("enrno"));
				assessmentDomain.setPathOfFile(resultSet.getString("path"));
				assessmentDomain.setAssessmentId(resultSet.getString("assessment_id"));
				assessmentDomain.setAssessmentName(resultSet.getString("assessment_name"));
				assessmentDomain.setUserId(resultSet.getString("full_name"));
				assessmentDomain.setDueDate(resultSet.getString("due_date"));
				assessmentDomain.setAssessmentType(resultSet.getString("assessment_type"));
				assessmentDomain.setTotalMarks(resultSet.getString("total_marks"));
				assessmentDomain.setDescrition(resultSet.getString("assessment_description"));
				
				if(resultSet.getString("marks")==null)
					assessmentDomain.setMark("");
				else
					assessmentDomain.setMark(resultSet.getString("marks"));
				if(resultSet.getString("status")==null)
					assessmentDomain.setStatus("");
				else
					assessmentDomain.setStatus(resultSet.getString("status"));
				if(resultSet.getString("remarks")==null)
					assessmentDomain.setReMarks("");
				else
					assessmentDomain.setReMarks(resultSet.getString("remarks"));
				
				arrayListForAssessment.add(assessmentDomain);
				//System.out.println("arrayListForAssessment"+arrayListForAssessment);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayListForAssessment;
	
	}
	
	ArrayList<AssessmentDomain> arrayListForAssessmentMarks = new ArrayList<AssessmentDomain>();
	public ArrayList<AssessmentDomain> getRecipientTypeMarks(AssessmentDomain assessment , String orgId, String userId){
		
		AssessmentDomain assessmentDomain=null;
		
		try {
			
			 connection = DBTransaction.connect();
			String assessment_id=assessment.getAssessmentId();
		//	String sql = " select recipient_type from assessment_info where assessment_id= '"+assessment.getAssessmentId()+"' and uploaded_by='"+userId+"' and organization_id= '"+orgid+"'";
							
			 	String tempQuery="SELECT distinct AI.assessment_id,assessment_name,ASI.marks,status,remarks,path_of_file,path,user_id,username,ASI.uploaded_by, (case when ASI.uploaded_by is not null and path_of_file is not null then 'Yes' else 'No' end) completed FROM assessment_info AI LEFT JOIN assessment_student_info ASI ON AI.assessment_id = ASI.assessment_id , users_info UI where (CASE WHEN AI.recipient_type = 'All' then TRUE else UI.user_id IN (SELECT regexp_split_to_table(recipient_type,E'\\#+'))  end) AND AI.assessment_id like '"+assessment_id+"' and UI.organization_id='"+orgId+"' and ASI.uploaded_by='"+userId+"' and user_id='"+userId+"'";
			 //String tempQuery="SELECT *, (case when (ASI.uploaded_by is not null and path is not null) then 'yes' else 'no' end) as completed FROM assessment_info AI JOIN assessment_student_info ASI ON AI.assessment_id = ASI.assessment_id , users_info UI where UI.user_id in (select user_id from users_info where user_id in (select regexp_split_to_table(recipient_type,E'\\#+') as recipient_type from assessment_info where assessment_id='"+assessment.getAssessmentId()+"' and organization_id='"+orgId+"' and uploaded_by='"+userId+"')) and AI.assessment_id='"+assessment.getAssessmentId()+"'";
			
			preparedStatement = connection.prepareStatement(tempQuery);

			System.out.println("the tempQuery query is:" + tempQuery);
			preparedStatement = connection.prepareStatement(tempQuery);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				
				 assessmentDomain = new AssessmentDomain();
				 
				//String recipientType = resultSet.getString("recipient_type");
				assessmentDomain.setRecipientType(resultSet.getString("user_id"));
				assessmentDomain.setPathOfFile(resultSet.getString("path"));
				assessmentDomain.setAssessmentId(resultSet.getString("assessment_id"));
				assessmentDomain.setAssessmentName(resultSet.getString("assessment_name"));
				assessmentDomain.setUserId(resultSet.getString("username"));
				
				if(resultSet.getString("marks")==null)
					assessmentDomain.setMark("");
				else
					assessmentDomain.setMark(resultSet.getString("marks"));
				if(resultSet.getString("status")==null)
					assessmentDomain.setStatus("");
				else
					assessmentDomain.setStatus(resultSet.getString("status"));
				if(resultSet.getString("remarks")==null)
					assessmentDomain.setReMarks("");
				else
					assessmentDomain.setReMarks(resultSet.getString("remarks"));
				
				arrayListForAssessmentMarks.add(assessmentDomain);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayListForAssessmentMarks;
	
	}
	
	ArrayList<AssessmentDomain> arrayListForAllAssessmentMarks = new ArrayList<AssessmentDomain>();
	public ArrayList<AssessmentDomain> getAllRecipientTypeMarks(AssessmentDomain assessment , String orgId, String userId){
		
		AssessmentDomain assessmentDomain=null;
		
		try {
			
			 connection = DBTransaction.connect();
			String assessment_id=assessment.getAssessmentId();
		//	String sql = " select recipient_type from assessment_info where assessment_id= '"+assessment.getAssessmentId()+"' and uploaded_by='"+userId+"' and organization_id= '"+orgid+"'";
							
			 	String tempQuery="SELECT distinct AI.assessment_id,assessment_name,ASI.marks,status,remarks,path_of_file,path,user_id,username,ASI.uploaded_by, (case when ASI.uploaded_by is not null and path_of_file is not null then 'Yes' else 'No' end) completed FROM assessment_info AI LEFT JOIN assessment_student_info ASI ON AI.assessment_id = ASI.assessment_id , users_info UI where (CASE WHEN AI.recipient_type = 'All' then TRUE else UI.user_id IN (SELECT regexp_split_to_table(recipient_type,E'\\#+'))  end) and UI.organization_id='"+orgId+"' and ASI.uploaded_by='"+userId+"' and user_id='"+userId+"'";
			 //String tempQuery="SELECT *, (case when (ASI.uploaded_by is not null and path is not null) then 'yes' else 'no' end) as completed FROM assessment_info AI JOIN assessment_student_info ASI ON AI.assessment_id = ASI.assessment_id , users_info UI where UI.user_id in (select user_id from users_info where user_id in (select regexp_split_to_table(recipient_type,E'\\#+') as recipient_type from assessment_info where assessment_id='"+assessment.getAssessmentId()+"' and organization_id='"+orgId+"' and uploaded_by='"+userId+"')) and AI.assessment_id='"+assessment.getAssessmentId()+"'";
			
			preparedStatement = connection.prepareStatement(tempQuery);

			System.out.println("the query is:" + tempQuery);
			preparedStatement = connection.prepareStatement(tempQuery);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				
				 assessmentDomain = new AssessmentDomain();
				 
				//String recipientType = resultSet.getString("recipient_type");
				assessmentDomain.setRecipientType(resultSet.getString("user_id"));
				assessmentDomain.setPathOfFile(resultSet.getString("path"));
				assessmentDomain.setAssessmentId(resultSet.getString("assessment_id"));
				assessmentDomain.setAssessmentName(resultSet.getString("assessment_name"));
				assessmentDomain.setUserId(resultSet.getString("username"));
				
				if(resultSet.getString("marks")==null)
					assessmentDomain.setMark("");
				else
					assessmentDomain.setMark(resultSet.getString("marks"));
				if(resultSet.getString("status")==null)
					assessmentDomain.setStatus("");
				else
					assessmentDomain.setStatus(resultSet.getString("status"));
				if(resultSet.getString("remarks")==null)
					assessmentDomain.setReMarks("");
				else
					assessmentDomain.setReMarks(resultSet.getString("remarks"));
				
				arrayListForAllAssessmentMarks.add(assessmentDomain);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayListForAllAssessmentMarks;
	
	}
	
	ArrayList<AssessmentDomain> arrayListForCertificationAssessment = new ArrayList<AssessmentDomain>();
	public ArrayList<AssessmentDomain> getCertificationRecipientType(AssessmentDomain assessment , String orgId, String userId){
		
		AssessmentDomain assessmentDomain=null;
		
		try {
			
			 connection = DBTransaction.connect();
			String assessment_id=assessment.getAssessmentId();
		//	String sql = " select recipient_type from assessment_info where assessment_id= '"+assessment.getAssessmentId()+"' and uploaded_by='"+userId+"' and organization_id= '"+orgid+"'";
							
			 	String tempQuery="SELECT distinct AI.assessment_id,assessment_name,ASI.marks,status,remarks,path_of_file,path,user_id,username,AI.uploaded_by, (case when ASI.uploaded_by is not null and path_of_file is not null then 'Yes' else 'No' end) completed FROM assessment_info AI LEFT JOIN assessment_student_info ASI ON AI.assessment_id = ASI.assessment_id , users_info UI where (CASE WHEN AI.recipient_type = 'All' then TRUE else UI.user_id IN (SELECT regexp_split_to_table(recipient_type,E'\\#+'))  end) AND AI.assessment_id like '"+assessment_id+"' and UI.organization_id='"+orgId+"' and AI.uploaded_by='"+userId+"'";
			 //String tempQuery="SELECT *, (case when (ASI.uploaded_by is not null and path is not null) then 'yes' else 'no' end) as completed FROM assessment_info AI JOIN assessment_student_info ASI ON AI.assessment_id = ASI.assessment_id , users_info UI where UI.user_id in (select user_id from users_info where user_id in (select regexp_split_to_table(recipient_type,E'\\#+') as recipient_type from assessment_info where assessment_id='"+assessment.getAssessmentId()+"' and organization_id='"+orgId+"' and uploaded_by='"+userId+"')) and AI.assessment_id='"+assessment.getAssessmentId()+"'";
			
			preparedStatement = connection.prepareStatement(tempQuery);

			//System.out.println("the query is:" + tempQuery);
			//preparedStatement = connection.prepareStatement(tempQuery);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				
				 assessmentDomain = new AssessmentDomain();
				 
				//String recipientType = resultSet.getString("recipient_type");
				assessmentDomain.setRecipientType(resultSet.getString("user_id"));
				assessmentDomain.setPathOfFile(resultSet.getString("path"));
				assessmentDomain.setAssessmentId(resultSet.getString("assessment_id"));
				assessmentDomain.setAssessmentName(resultSet.getString("assessment_name"));
				assessmentDomain.setUserId(resultSet.getString("username"));
				
				if(resultSet.getString("marks")==null)
					assessmentDomain.setMark("");
				else
					assessmentDomain.setMark(resultSet.getString("marks"));
				if(resultSet.getString("status")==null)
					assessmentDomain.setStatus("");
				else
					assessmentDomain.setStatus(resultSet.getString("status"));
				if(resultSet.getString("remarks")==null)
					assessmentDomain.setReMarks("");
				else
					assessmentDomain.setReMarks(resultSet.getString("remarks"));
				
				arrayListForCertificationAssessment.add(assessmentDomain);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayListForCertificationAssessment;
	
	}
	
	ArrayList<AssessmentDomain> arrayListForAllAssessment = new ArrayList<AssessmentDomain>();
	public ArrayList<AssessmentDomain> getAllRecipientType(AssessmentDomain assessment , String orgId, String userId){
		
		AssessmentDomain assessmentDomain=null;
		
		try {
			
			 connection = DBTransaction.connect();
			String assessment_id=assessment.getAssessmentId();
	
			String tempQuery="SELECT AI.assessment_id,AI.due_date,AI.assessment_type,AI.assessment_description,assessment_name,ASI.marks,status,remarks,path_of_file,path,user_id,username,AI.uploaded_by"
					+ ", (case when ASI.uploaded_by is not null and path_of_file is not null then 'Yes' else 'No' end) completed "
					+ "from (SELECT distinct AI.assessment_id,AI.due_date,AI.assessment_type,AI.assessment_description,assessment_name,path_of_file,user_id,username,AI.uploaded_by "
					+ " FROM assessment_info AI left join student_groups SG on AI.recipient_type=SG.group_name and AI.organization_id = SG.organization_id "
					+ "cross join users_info UI where privilege='Student' and (CASE WHEN AI.recipient_type = 'All' then TRUE when UI.user_id IN (SELECT regexp_split_to_table(student_id,E'\\#+')) then TRUE "
					+ " else UI.user_id IN (SELECT regexp_split_to_table(recipient_type,E'\\#+'))  end) "
					+ "/* AND AI.assessment_id like '"+assessment.getAssessmentId()+"'*/ and UI.organization_id='"+orgId+"' and AI.uploaded_by='"+userId+"' )"
					+ " AI LEFT JOIN assessment_student_info ASI ON AI.assessment_id = ASI.assessment_id and AI.user_id = ASI.uploaded_by ";
	
			String query="select a.assessment_id, a.assessment_name,a.due_date,a.assessment_type,a.assessment_description, g.uploaded_by as enrno, h.full_name, a.marks as total_marks, g.marks, g.status, g.remarks, g.path, g.uploaded_date from" 
+" assessment_info a,assessment_student_info g, transaction_enrollment_process h where"
+" a.organization_id = '"+orgId+"' and"
+" a.uploaded_by = '"+userId+"'";



			
			
			
			
		
			
			preparedStatement = connection.prepareStatement(query);

			System.out.println("the query in search dao:" + query);
			//preparedStatement = connection.prepareStatement(tempQuery);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				System.out.println("path="+resultSet.getString("path"));
				 assessmentDomain = new AssessmentDomain();
				 
				//String recipientType = resultSet.getString("recipient_type");
			assessmentDomain.setRecipientType(resultSet.getString("enrno"));
				assessmentDomain.setPathOfFile(resultSet.getString("path"));
				assessmentDomain.setAssessmentId(resultSet.getString("assessment_id"));
				assessmentDomain.setAssessmentName(resultSet.getString("assessment_name"));
			//	assessmentDomain.setUserId(resultSet.getString("username"));
				assessmentDomain.setTotalMarks(resultSet.getString("total_marks"));
				
				assessmentDomain.setDueDate(resultSet.getString("due_date"));
				assessmentDomain.setAssessmentType(resultSet.getString("assessment_type"));
				
				System.out.println("marks="+resultSet.getString("marks"));
				assessmentDomain.setDescrition(resultSet.getString("assessment_description"));
				
				if(resultSet.getString("marks")==null)
					assessmentDomain.setMark("");
				else
					assessmentDomain.setMark(resultSet.getString("marks"));
				if(resultSet.getString("status")==null)
					assessmentDomain.setStatus("");
				else
					assessmentDomain.setStatus(resultSet.getString("status"));
				if(resultSet.getString("remarks")==null)
					assessmentDomain.setReMarks("");
				else
					assessmentDomain.setReMarks(resultSet.getString("remarks"));
				
				arrayListForAllAssessment.add(assessmentDomain);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayListForAllAssessment;
	
	}
	
	// This method designed by Paramvir Singh for getting RecipientTypeForAssignment on 3rdfeb 16
	
	ArrayList<AssessmentDomain> arrayListForAssignment = new ArrayList<AssessmentDomain>();
	public ArrayList<AssessmentDomain> getRecipientTypeForAssignment(AssessmentDomain assignment , String orgId, String userId){
		
		AssessmentDomain assessmentDomain=null;
		
		try {
			 connection = DBTransaction.connect();
			 String assignment_id=assignment.getAssessmentId();
		//	String sql = " select recipient_type from assessment_info where assessment_id= '"+assessment.getAssessmentId()+"' and uploaded_by='"+userId+"' and organization_id= '"+orgid+"'";
							
	    	//String tempQuery="SELECT *, (case when (ASI.uploaded_by is not null and ASI.path_of_file is not null) then 'yes' else 'no' end) as completed FROM assignment_info AI JOIN assignment_student_info ASI ON AI.assignment_id = ASI.assignment_id , users_info UI where UI.user_id in (select user_id from users_info where user_id in (select regexp_split_to_table(recipient_type,E'\\#+') as recipient_type from assignment_info where assignment_id='"+assignment.getAssessmentId()+"' and organization_id='"+orgId+"' and uploaded_by='"+userId+"')) and AI.assignment_id='"+assignment.getAssessmentId()+"'";
			String tempQuery="SELECT distinct AI.assignment_id,assignment_name,ASI.marks,status,remarks,ASI.path_of_file,user_id,username,AI.uploaded_by, (case when ASI.uploaded_by is not null and ASI.path_of_file is not null then 'Yes' else 'No' end) completed FROM assignment_info AI LEFT JOIN assignment_student_info ASI ON AI.assignment_id = ASI.assignment_id , users_info UI where (CASE WHEN AI.recipient_type = 'All' then TRUE else UI.user_id IN (SELECT regexp_split_to_table(recipient_type,E'\\#+'))  end) AND AI.assignment_id like '"+assignment_id+"' and UI.organization_id='"+orgId+"' and AI.uploaded_by='"+userId+"'";
			preparedStatement = connection.prepareStatement(tempQuery);

			//System.out.println("the query for assignment:" + tempQuery);
			//preparedStatement = connection.prepareStatement(tempQuery);
			resultSet = preparedStatement.executeQuery();
			//System.out.println("the query is assignment:" + preparedStatement);
			while(resultSet.next()) {
				
				assessmentDomain = new AssessmentDomain();
				
				//String recipientType = resultSet.getString("recipient_type");
				assessmentDomain.setRecipientType(resultSet.getString("user_id"));
				assessmentDomain.setPathOfFile(resultSet.getString("path_of_file"));
				assessmentDomain.setAssessmentId(resultSet.getString("assignment_id"));
				assessmentDomain.setAssessmentName(resultSet.getString("assignment_name"));
				assessmentDomain.setUserId(resultSet.getString("username"));
				
				if(resultSet.getString("marks")==null)
					assessmentDomain.setMark("");
				else
					assessmentDomain.setMark(resultSet.getString("marks"));
				if(resultSet.getString("status")==null)
					assessmentDomain.setStatus("");
				else
					assessmentDomain.setStatus(resultSet.getString("status"));
				if(resultSet.getString("remarks")==null)
					assessmentDomain.setReMarks("");
				else
					assessmentDomain.setReMarks(resultSet.getString("remarks"));
				
				arrayListForAssignment.add(assessmentDomain);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayListForAssignment;
   }
	
	ArrayList<AssessmentDomain> arrayListForAssignmentMarks = new ArrayList<AssessmentDomain>();
	public ArrayList<AssessmentDomain> getRecipientTypeForAssignmentMarks(AssessmentDomain assignment , String orgId, String userId){
		
		AssessmentDomain assessmentDomain=null;
		
		try {
			 connection = DBTransaction.connect();
			 String assignment_id=assignment.getAssessmentId();
		//	String sql = " select recipient_type from assessment_info where assessment_id= '"+assessment.getAssessmentId()+"' and uploaded_by='"+userId+"' and organization_id= '"+orgid+"'";
							
	    	//String tempQuery="SELECT *, (case when (ASI.uploaded_by is not null and ASI.path_of_file is not null) then 'yes' else 'no' end) as completed FROM assignment_info AI JOIN assignment_student_info ASI ON AI.assignment_id = ASI.assignment_id , users_info UI where UI.user_id in (select user_id from users_info where user_id in (select regexp_split_to_table(recipient_type,E'\\#+') as recipient_type from assignment_info where assignment_id='"+assignment.getAssessmentId()+"' and organization_id='"+orgId+"' and uploaded_by='"+userId+"')) and AI.assignment_id='"+assignment.getAssessmentId()+"'";
			String tempQuery="SELECT distinct AI.assignment_id,assignment_name,ASI.marks,status,remarks,ASI.path_of_file,user_id,username,ASI.uploaded_by, (case when ASI.uploaded_by is not null and ASI.path_of_file is not null then 'Yes' else 'No' end) completed FROM assignment_info AI LEFT JOIN assignment_student_info ASI ON AI.assignment_id = ASI.assignment_id , users_info UI where (CASE WHEN AI.recipient_type = 'All' then TRUE else UI.user_id IN (SELECT regexp_split_to_table(recipient_type,E'\\#+'))  end) AND AI.assignment_id like '"+assignment_id+"' and UI.organization_id='"+orgId+"' and ASI.uploaded_by='"+userId+"'";
			preparedStatement = connection.prepareStatement(tempQuery);

			//System.out.println("the query for assignment:" + tempQuery);
			//preparedStatement = connection.prepareStatement(tempQuery);
			resultSet = preparedStatement.executeQuery();
			//System.out.println("the query is assignment:" + tempQuery);
			while(resultSet.next()) {
				
				assessmentDomain = new AssessmentDomain();
				
				//String recipientType = resultSet.getString("recipient_type");
				assessmentDomain.setRecipientType(resultSet.getString("user_id"));
				assessmentDomain.setPathOfFile(resultSet.getString("path_of_file"));
				assessmentDomain.setAssessmentId(resultSet.getString("assignment_id"));
				assessmentDomain.setAssessmentName(resultSet.getString("assignment_name"));
				assessmentDomain.setUserId(resultSet.getString("username"));
				
				if(resultSet.getString("marks")==null)
					assessmentDomain.setMark("");
				else
					assessmentDomain.setMark(resultSet.getString("marks"));
				if(resultSet.getString("status")==null)
					assessmentDomain.setStatus("");
				else
					assessmentDomain.setStatus(resultSet.getString("status"));
				if(resultSet.getString("remarks")==null)
					assessmentDomain.setReMarks("");
				else
					assessmentDomain.setReMarks(resultSet.getString("remarks"));
				
				arrayListForAssignmentMarks.add(assessmentDomain);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayListForAssignmentMarks;
   }
	
	ArrayList<AssessmentDomain> arrayListForAllAssignment = new ArrayList<AssessmentDomain>();
	public ArrayList<AssessmentDomain> getAllRecipientTypeForAssignment(AssessmentDomain assignment , String orgId, String userId){
		
		AssessmentDomain assessmentDomain=null;
		
		try {
			 connection = DBTransaction.connect();
			 String assignment_id=assignment.getAssessmentId();
		//	String sql = " select recipient_type from assessment_info where assessment_id= '"+assessment.getAssessmentId()+"' and uploaded_by='"+userId+"' and organization_id= '"+orgid+"'";
							
	    	//String tempQuery="SELECT *, (case when (ASI.uploaded_by is not null and ASI.path_of_file is not null) then 'yes' else 'no' end) as completed FROM assignment_info AI JOIN assignment_student_info ASI ON AI.assignment_id = ASI.assignment_id , users_info UI where UI.user_id in (select user_id from users_info where user_id in (select regexp_split_to_table(recipient_type,E'\\#+') as recipient_type from assignment_info where assignment_id='"+assignment.getAssessmentId()+"' and organization_id='"+orgId+"' and uploaded_by='"+userId+"')) and AI.assignment_id='"+assignment.getAssessmentId()+"'";
			String tempQuery="SELECT distinct AI.assignment_id,assignment_name,ASI.marks,status,remarks,ASI.path_of_file,user_id,username,AI.uploaded_by, (case when ASI.uploaded_by is not null and ASI.path_of_file is not null then 'Yes' else 'No' end) completed FROM assignment_info AI LEFT JOIN assignment_student_info ASI ON AI.assignment_id = ASI.assignment_id , users_info UI where (CASE WHEN AI.recipient_type = 'All' then TRUE else UI.user_id IN (SELECT regexp_split_to_table(recipient_type,E'\\#+'))  end) and UI.organization_id='"+orgId+"' and AI.uploaded_by='"+userId+"'";
			preparedStatement = connection.prepareStatement(tempQuery);

			//System.out.println("the query for assignment:" + tempQuery);
			//preparedStatement = connection.prepareStatement(tempQuery);
			resultSet = preparedStatement.executeQuery();
			//System.out.println("the query is assignment:" + preparedStatement);
			while(resultSet.next()) {
				
				assessmentDomain = new AssessmentDomain();
				
				//String recipientType = resultSet.getString("recipient_type");
				assessmentDomain.setRecipientType(resultSet.getString("user_id"));
				assessmentDomain.setPathOfFile(resultSet.getString("path_of_file"));
				assessmentDomain.setAssessmentId(resultSet.getString("assignment_id"));
				assessmentDomain.setAssessmentName(resultSet.getString("assignment_name"));
				assessmentDomain.setUserId(resultSet.getString("username"));
				
				if(resultSet.getString("marks")==null)
					assessmentDomain.setMark("");
				else
					assessmentDomain.setMark(resultSet.getString("marks"));
				if(resultSet.getString("status")==null)
					assessmentDomain.setStatus("");
				else
					assessmentDomain.setStatus(resultSet.getString("status"));
				if(resultSet.getString("remarks")==null)
					assessmentDomain.setReMarks("");
				else
					assessmentDomain.setReMarks(resultSet.getString("remarks"));
				
				arrayListForAllAssignment.add(assessmentDomain);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayListForAllAssignment;
   }
	ArrayList<AssessmentDomain> arrayListForAllAssignmentMarks = new ArrayList<AssessmentDomain>();
	public ArrayList<AssessmentDomain> getAllRecipientTypeForAssignmentMarks(AssessmentDomain assignment , String orgId, String userId){
		
		AssessmentDomain assessmentDomain=null;
		
		try {
			 connection = DBTransaction.connect();
			 String assignment_id=assignment.getAssessmentId();
		//	String sql = " select recipient_type from assessment_info where assessment_id= '"+assessment.getAssessmentId()+"' and uploaded_by='"+userId+"' and organization_id= '"+orgid+"'";
							
	    	//String tempQuery="SELECT *, (case when (ASI.uploaded_by is not null and ASI.path_of_file is not null) then 'yes' else 'no' end) as completed FROM assignment_info AI JOIN assignment_student_info ASI ON AI.assignment_id = ASI.assignment_id , users_info UI where UI.user_id in (select user_id from users_info where user_id in (select regexp_split_to_table(recipient_type,E'\\#+') as recipient_type from assignment_info where assignment_id='"+assignment.getAssessmentId()+"' and organization_id='"+orgId+"' and uploaded_by='"+userId+"')) and AI.assignment_id='"+assignment.getAssessmentId()+"'";
			String tempQuery="SELECT distinct AI.assignment_id,assignment_name,ASI.marks,status,remarks,ASI.path_of_file,user_id,username,ASI.uploaded_by, (case when ASI.uploaded_by is not null and ASI.path_of_file is not null then 'Yes' else 'No' end) completed FROM assignment_info AI LEFT JOIN assignment_student_info ASI ON AI.assignment_id = ASI.assignment_id , users_info UI where (CASE WHEN AI.recipient_type = 'All' then TRUE else UI.user_id IN (SELECT regexp_split_to_table(recipient_type,E'\\#+'))  end) and UI.organization_id='"+orgId+"' and ASI.uploaded_by='"+userId+"'";
			preparedStatement = connection.prepareStatement(tempQuery);

			//System.out.println("the query for assignment:" + tempQuery);
			//preparedStatement = connection.prepareStatement(tempQuery);
			resultSet = preparedStatement.executeQuery();
			//System.out.println("the query is assignment:" + tempQuery);
			while(resultSet.next()) {
				
				assessmentDomain = new AssessmentDomain();
				
				//String recipientType = resultSet.getString("recipient_type");
				assessmentDomain.setRecipientType(resultSet.getString("user_id"));
				assessmentDomain.setPathOfFile(resultSet.getString("path_of_file"));
				assessmentDomain.setAssessmentId(resultSet.getString("assignment_id"));
				assessmentDomain.setAssessmentName(resultSet.getString("assignment_name"));
				assessmentDomain.setUserId(resultSet.getString("username"));
				
				if(resultSet.getString("marks")==null)
					assessmentDomain.setMark("");
				else
					assessmentDomain.setMark(resultSet.getString("marks"));
				if(resultSet.getString("status")==null)
					assessmentDomain.setStatus("");
				else
					assessmentDomain.setStatus(resultSet.getString("status"));
				if(resultSet.getString("remarks")==null)
					assessmentDomain.setReMarks("");
				else
					assessmentDomain.setReMarks(resultSet.getString("remarks"));
				
				arrayListForAllAssignmentMarks.add(assessmentDomain);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arrayListForAllAssignmentMarks;
   }
	
	ArrayList<CreateDomain> arl2=new ArrayList<CreateDomain>();
	public ArrayList<CreateDomain> fetchAllOrgValue(CreateDomain aDomain) {
		
		
		try {
			connection = DBTransaction.connect();
			
			//PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			String sql = " select * from org_details left join users_info on org_details.organization_id=users_info.organization_id where created_by='owner@kds'";
			preparedStatement = connection.prepareStatement(sql);

			//System.out.println("the query is all:" + sql);
			//preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				CreateDomain rDomain1=new CreateDomain();
				//String OrgId = rs.getString("organization_id");

				////System.out.println("OrgId is:" + OrgId);
                rDomain1.setOrg_id(rs.getString("organization_id"));
				rDomain1.setOrg_name(rs.getString("username"));
       		 	rDomain1.setOrg_type(rs.getString("org_type"));
       		 	rDomain1.setOrg_Type_Name(rs.getString("organization_name"));
       		 rDomain1.setAddress(rs.getString("address"));
       		 rDomain1.setCity(rs.getString("city"));
       		 rDomain1.setCountry(rs.getString("country"));
       		 rDomain1.setPcode(rs.getString("postal_code"));
       		 rDomain1.setPhone(rs.getString("telephone"));
       		 rDomain1.setFax(rs.getString("fax"));
       		 rDomain1.setEcno(rs.getString("emergency_contact_no"));
       		 rDomain1.setEmail_id(rs.getString("email"));
       		 rDomain1.setUrl(rs.getString("url"));
       		 rDomain1.setLogo(rs.getString("logo"));
       		 rDomain1.setYof(rs.getString("year_of_foundation"));
       		 rDomain1.setDate(rs.getTimestamp("date_time"));
       		 rDomain1.setState(rs.getString("state"));
				arl2.add(rDomain1);
			}
			//System.out.println("the return value is:" + aDomain);

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   connection.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return arl2;
	}

	ArrayList<CreateDomain> arl=new ArrayList<CreateDomain>();
	public ArrayList<CreateDomain> fetchOrgId(CreateDomain aDomain) {
			
			
			try {
				connection = DBTransaction.connect();
				
				//PreparedStatement preparedStatement = null;
				ResultSet rs = null;
				String sql = " select * from org_details left join users_info on org_details.organization_id=users_info.organization_id where org_details.organization_id = '"+ aDomain.getOrg_id() + "' and created_by='owner@kds'";
				preparedStatement = connection.prepareStatement(sql);

				//System.out.println("the query is:" + sql);
				preparedStatement = connection.prepareStatement(sql);
				rs = preparedStatement.executeQuery();
				while(rs.next()) {
					CreateDomain rDomain1=new CreateDomain();
					String Org_Id = rs.getString("organization_id");

					////System.out.println("Org_Id is:" + Org_Id);
	                rDomain1.setOrg_id(rs.getString("organization_id"));
	                rDomain1.setOrg_name(rs.getString("username"));
	       		 	rDomain1.setOrg_type(rs.getString("org_type"));
	       		 	rDomain1.setOrg_Type_Name(rs.getString("organization_name"));
	       		 rDomain1.setAddress(rs.getString("address"));
	       		 rDomain1.setCity(rs.getString("city"));
	       		 rDomain1.setCountry(rs.getString("country"));
	       		 rDomain1.setPcode(rs.getString("postal_code"));
	       		 rDomain1.setPhone(rs.getString("telephone"));
	       		 rDomain1.setFax(rs.getString("fax"));
	       		 rDomain1.setEcno(rs.getString("emergency_contact_no"));
	       		 rDomain1.setEmail_id(rs.getString("email"));
	       		 rDomain1.setUrl(rs.getString("url"));
	       		 rDomain1.setLogo(rs.getString("logo"));
	       		 rDomain1.setYof(rs.getString("year_of_foundation"));
	       		 rDomain1.setDate(rs.getTimestamp("date_time"));
	       		 rDomain1.setState(rs.getString("state"));
					arl.add(rDomain1);
				}
			//	//System.out.println("the return value is:" + rDomain1);

			} catch (Exception e) {
				e.printStackTrace();
			}
			finally
			   {
				   try{
					   connection.close();
					   preparedStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			return arl;
		}
}