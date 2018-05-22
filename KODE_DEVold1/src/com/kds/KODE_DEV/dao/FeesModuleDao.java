package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.kds.KODE_DEV.dbconnection.DBTransaction;

public class FeesModuleDao {
	
Connection con = null;
	
	PreparedStatement preparedStatement = null;
	
	public ArrayList<HashMap<String,String>> getAllFeesComponentsByOder(String AsceORDesc)
	{
		ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
		try
		{
			con = DBTransaction.connectKnoWStore();
			
			ResultSet resultSet = null;
			String sql="select fee_code, fee_name from mst_fee_components where status = 'Active' order by fee_code";
			if(AsceORDesc.trim().length()>0 && AsceORDesc.trim().equalsIgnoreCase("DESC"))
				sql=sql+" DESC";
			
			preparedStatement = con.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) 
			{

				String fee_code=resultSet.getString("fee_code").toString().trim();
				String fee_name=resultSet.getString("fee_name").toString().trim();
				String fullname=fee_name +" ("+fee_code+")";
				/*String creation_date=resultSet.getString("creation_date").toString().trim();
				String last_update=resultSet.getString("last_update").toString().trim();
				String user_id=resultSet.getString("user_id").toString().trim();
				String status=resultSet.getString("status").toString().trim();*/
				
				HashMap<String,String> htinfo=new HashMap<String,String>();
				htinfo.put("fee_code", fee_code);
				htinfo.put("fee_name", fee_name);
				htinfo.put("FullName", fullname);
				
				/*htinfo.put("creation_date", creation_date);
				htinfo.put("last_update", last_update);
				htinfo.put("user_id", user_id);
				htinfo.put("status", status);
				*/
				ret.add(htinfo);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		
		return ret;
	}

	public ArrayList<HashMap<String,String>> getAllInstitutes()
	{
		ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
		try
		{
			con = DBTransaction.connect();
			
			ResultSet resultSet = null;
			String sql="select organization_id, organization_name from org_details where length(organization_id) = 9 order by organization_name";
			
			
			preparedStatement = con.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) 
			{

				String organization_id=resultSet.getString("organization_id").toString().trim();
				String organization_name=resultSet.getString("organization_name").toString().trim();
				
				String FullName=organization_name +" ("+organization_id+" )";
				
				HashMap<String,String> htinfo=new HashMap<String,String>();
				htinfo.put("organization_id", organization_id);
				htinfo.put("organization_name", organization_name);
				htinfo.put("FullName", FullName);
				
				ret.add(htinfo);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		
		return ret;
	}

	public ArrayList<HashMap<String,String>> getFeesInstitutes()
	{
		ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
		try
		{
			con = DBTransaction.connectKnoWStore();
			
			ResultSet resultSet = null;
			String sql="select a.organization_id, a.middle_layer_teaching_source_name "
					+ "from transaction_middle_layer_teaching_source a, mst_mltsource_x_fee_components b"
					+ " where b.mlt_id = a.organization_id group by a.organization_id, a.middle_layer_teaching_source_name"
					+ " order by a.middle_layer_teaching_source_name ";
			
			
			preparedStatement = con.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) 
			{

				String organization_id=resultSet.getString("organization_id").toString().trim();
				String organization_name=resultSet.getString("middle_layer_teaching_source_name").toString().trim();
				
				String FullName=organization_name +" ("+organization_id+" )";
				
				HashMap<String,String> htinfo=new HashMap<String,String>();
				htinfo.put("organization_id", organization_id);
				htinfo.put("organization_name", organization_name);
				htinfo.put("FullName", FullName);
				
				ret.add(htinfo);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		
		return ret;
	}

	public ArrayList<HashMap<String,String>> getAllFeesIDByOwner()
	{
		ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
		try
		{
			con = DBTransaction.connectKnoWStore();
			
			ResultSet resultSet = null;
			String sql="select fee_code, fee_name from mst_fee_components where status = 'Active' order by fee_name";
			
			
			preparedStatement = con.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) 
			{

				String fee_code=resultSet.getString("fee_code").toString().trim();
				String fee_name=resultSet.getString("fee_name").toString().trim();
				
				String FullName=fee_name +" ("+fee_code+" )";
				
				HashMap<String,String> htinfo=new HashMap<String,String>();
				htinfo.put("fee_code", fee_code);
				htinfo.put("fee_name", fee_name);
				htinfo.put("FullName", FullName);
				
				ret.add(htinfo);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		
		return ret;
	}


	public ArrayList<HashMap<String,String>> getAllCoursesByOrg(String OrgID)
	{
		ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
		try
		{
			con = DBTransaction.connectKnoWStore();
			
			ResultSet resultSet = null;
			String sql="select a.course_discription_id, a.course_id_declared_by_teaching_source, b.department_name "
					+ "from transaction_course_description_info a, transaction_department_info b, transaction_middle_layer_teaching_source c "
					+ "where c.organization_id = '"+OrgID+"' and c.middle_layer_teaching_source_id = a.middle_layer_teaching_source_id and a.course_id = b.department_id "
					+ "order by a.course_id_declared_by_teaching_source";

			
			
			preparedStatement = con.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) 
			{

				String course_id_declared_by_teaching_source=resultSet.getString("course_id_declared_by_teaching_source").toString().trim();
				String course_name=resultSet.getString("department_name").toString().trim();
				String course_discription_id=resultSet.getString("course_discription_id").toString().trim();
				
				
				String FullName=course_name +" ("+course_id_declared_by_teaching_source+" )";
				
				HashMap<String,String> htinfo=new HashMap<String,String>();
				htinfo.put("course_id_declared_by_teaching_source", course_id_declared_by_teaching_source);
				htinfo.put("course_name", course_name);
				htinfo.put("course_discription_id", course_discription_id);
				htinfo.put("FullName", FullName);
				
				ret.add(htinfo);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		
		return ret;
	}
	public ArrayList<HashMap<String,String>> getFeesCoursesByOrg(String OrgID)
	{
		ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
		try
		{
			con = DBTransaction.connectKnoWStore();
			
			ResultSet resultSet = null;
			String sql="select a.course_discription_id, a.course_id_declared_by_teaching_source, b.department_name "
					+ "from transaction_course_description_info a, transaction_department_info b, mst_mltsource_fees_x_mltsource_course c "
					+ "where c.mlt_id = '"+OrgID+"' and c.grouped_feecode = 'Yes' and c.mlt_course = a.course_discription_id and b.department_id = a.course_id "
					+ "group by a.course_discription_id, a.course_id_declared_by_teaching_source, b.department_name "
					+ "order by b.department_name ";

			
			
			preparedStatement = con.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) 
			{

				String course_id_declared_by_teaching_source=resultSet.getString("course_id_declared_by_teaching_source").toString().trim();
				String course_name=resultSet.getString("department_name").toString().trim();
				String course_discription_id=resultSet.getString("course_discription_id").toString().trim();
				
				
				String FullName=course_name +" ("+course_id_declared_by_teaching_source+" )";
				
				HashMap<String,String> htinfo=new HashMap<String,String>();
				htinfo.put("course_id_declared_by_teaching_source", course_id_declared_by_teaching_source);
				htinfo.put("course_name", course_name);
				htinfo.put("course_discription_id", course_discription_id);
				htinfo.put("FullName", FullName);
				
				ret.add(htinfo);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		
		return ret;
	}
	
	public ArrayList<HashMap<String,String>> getAllFeesIDByOrgID(String OrgID)
	{
		ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
		try
		{
			con = DBTransaction.connectKnoWStore();
			
			ResultSet resultSet = null;
			String sql="select * from mst_mltsource_x_fee_components where mlt_id = '"+OrgID+"' and status = 'Active' order by mlt_fee_name";
			
			
			preparedStatement = con.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) 
			{

				String mlt_fee_code=resultSet.getString("mlt_fee_code").toString().trim();
				String mlt_fee_name=resultSet.getString("mlt_fee_name").toString().trim();
				String fee_code=resultSet.getString("fee_code").toString().trim();
				
				String FullName=mlt_fee_name +" ("+mlt_fee_code+" )";
				
				HashMap<String,String> htinfo=new HashMap<String,String>();
				htinfo.put("mlt_fee_code", mlt_fee_code);
				htinfo.put("mlt_fee_name", mlt_fee_name);
				htinfo.put("fee_code", fee_code);
				htinfo.put("FullName", FullName);
				
				ret.add(htinfo);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		
		return ret;
	}

	// select * from mst_mltsource_fees_x_mltsource_course where mlt_id='UniTNOSof' and mlt_course='cdid25'
	
	public ArrayList<HashMap<String,String>> getAllGroupFeesIDByOrgIDAndByCourseID(String OrgID,String CourseID)
	{
		ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
		try
		{
			con = DBTransaction.connectKnoWStore();
			
			ResultSet resultSet = null;
			String sql="select a.mlt_course_fee_code, b.mlt_fee_name from mst_mltsource_fees_x_mltsource_course a, mst_mltsource_x_fee_components b "
					+ "where a.mlt_id = '"+OrgID+"' and a.mlt_course = '"+CourseID+"' and a.grouped_feecode = 'Yes' and a.mlt_fee_code = b.mlt_fee_code "
					+ "order by b.mlt_fee_name";
			
		//	System.out.println(sql);
			preparedStatement = con.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) 
			{

				String mlt_course_fee_code=resultSet.getString("mlt_course_fee_code").toString().trim();
				String mlt_fee_name=resultSet.getString("mlt_fee_name").toString().trim();
				
				String FullName=mlt_fee_name +" ("+mlt_course_fee_code+" )";
				
				HashMap<String,String> htinfo=new HashMap<String,String>();
				htinfo.put("mlt_course_fee_code", mlt_course_fee_code);
				htinfo.put("mlt_fee_name", mlt_fee_name);
				htinfo.put("FullName", FullName);
				
				ret.add(htinfo);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		
		return ret;
	}
	
public ArrayList<HashMap<String,String>> getAllMemberFeesIDByOrgID(String OrgID,String CourseID)
	{
		ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
		try
		{
			con = DBTransaction.connectKnoWStore();
			
			ResultSet resultSet = null;
			String sql="select mlt_fee_code, mlt_fee_name from mst_mltsource_x_fee_components where mlt_id = '"+OrgID+"' and status = 'Active' and mlt_fee_code not in "
					+ "(select mlt_fee_code from mst_mltsource_fees_x_mltsource_course where mlt_id = '"+OrgID+"' and mlt_course = '"+CourseID+"' and grouped_feecode = 'Yes') "
					+ "order by mlt_fee_name";
			
			
			preparedStatement = con.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) 
			{

				String mlt_fee_code=resultSet.getString("mlt_fee_code").toString().trim();
				String mlt_fee_name=resultSet.getString("mlt_fee_name").toString().trim();
				
				String FullName=mlt_fee_name +" ("+mlt_fee_code+" )";
				
				HashMap<String,String> htinfo=new HashMap<String,String>();
				htinfo.put("mlt_fee_code", mlt_fee_code);
				htinfo.put("mlt_fee_name", mlt_fee_name);
				htinfo.put("FullName", FullName);
				
				ret.add(htinfo);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		
		return ret;
	}

public ArrayList<HashMap<String,String>> getFeeReceiptTemp(String OrgID,String CourseID,String TemplateType)
{
	ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
	try
	{
		con = DBTransaction.connectKnoWStore();
		
		ResultSet resultSet = null;
		String sql="select line_type,line_no,text_seq,text_type,text_value,data_table,data_column from receipt_configuration where mlt_id = '"+OrgID+"' and mlt_course = '"+CourseID+"' and template_name='"+TemplateType+"' ";
		
		 System.out.println("select temp  :: "+sql);
		preparedStatement = con.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{

			String line_type=""; if(resultSet.getString("line_type")!=null)line_type=resultSet.getString("line_type").toString().trim();
			String line_no=""; if(resultSet.getString("line_no")!=null)line_no=resultSet.getString("line_no").toString().trim();
			String text_seq=""; if(resultSet.getString("text_seq")!=null)text_seq=resultSet.getString("text_seq").toString().trim();
			String text_type=""; if(resultSet.getString("text_type")!=null)text_type=resultSet.getString("text_type").toString().trim();
			String text_value=""; if(resultSet.getString("text_value")!=null)text_value=resultSet.getString("text_value").toString().trim();
			String data_table=""; if(resultSet.getString("data_table")!=null)data_table=resultSet.getString("data_table").toString().trim();
			String data_column=""; if(resultSet.getString("data_column")!=null)data_column=resultSet.getString("data_column").toString().trim();
			
			
			HashMap<String,String> htinfo=new HashMap<String,String>();
			htinfo.put("line_type", line_type);
			htinfo.put("line_no", line_no);
			htinfo.put("text_seq", text_seq);
			htinfo.put("text_type", text_type);
			htinfo.put("text_value", text_value);
			htinfo.put("data_table", data_table);
			htinfo.put("data_column", data_column);
			
			ret.add(htinfo);
		}
		
	}catch(Exception ex){ex.printStackTrace();}
	finally
	   {
		   try{
			   con.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	
	return ret;
}


public ArrayList<HashMap<String,String>> getGroupedOptions()
{
	ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
	try
	{
		con = DBTransaction.connectKnoWStore();
		
		ResultSet resultSet = null;
		String sql="select 'Yes' as group_option union select 'No' as group_option ";
		
		
		preparedStatement = con.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{

			String group_option=resultSet.getString("group_option").toString().trim();
			
			HashMap<String,String> htinfo=new HashMap<String,String>();
			htinfo.put("group_option", group_option);
			
			ret.add(htinfo);
		}
		
	}catch(Exception ex){ex.printStackTrace();}
	finally
	   {
		   try{
			   con.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	
	return ret;
}

public ArrayList<HashMap<String,String>> getFeesTypeOptions()
{
	ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
	try
	{
		con = DBTransaction.connectKnoWStore();
		
		ResultSet resultSet = null;
		String sql="select 'Recurring' as fee_type union select 'One Time' as fee_type ";
		
		
		preparedStatement = con.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{

			String fee_type=resultSet.getString("fee_type").toString().trim();
			
			HashMap<String,String> htinfo=new HashMap<String,String>();
			htinfo.put("fee_type", fee_type);
			
			ret.add(htinfo);
		}
		
	}catch(Exception ex){ex.printStackTrace();}
	finally
	   {
		   try{
			   con.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	
	return ret;
}

public ArrayList<HashMap<String,String>> getMemberFeesTypeOptions()
{
	ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
	try
	{
		con = DBTransaction.connectKnoWStore();
		
		ResultSet resultSet = null;
		String sql="select 'Actual' as member_fee_type union select 'Percentage' as member_fee_type ";
		
		
		preparedStatement = con.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{

			String member_fee_type=resultSet.getString("member_fee_type").toString().trim();
			
			HashMap<String,String> htinfo=new HashMap<String,String>();
			htinfo.put("member_fee_type", member_fee_type);
			
			ret.add(htinfo);
		}
		
	}catch(Exception ex){ex.printStackTrace();}
	finally
	   {
		   try{
			   con.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	
	return ret;
}

public ArrayList<HashMap<String,String>> getTemplateTypeOptions()
{
	ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
	try
	{
		con = DBTransaction.connectKnoWStore();
		
		ResultSet resultSet = null;
		String sql="select 'Cash' as template_name union select 'Cheque' as template_name union select 'Demand Draft' as template_name union select 'Transfer' as template_name ";
		
		
		preparedStatement = con.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{

			String template_name=resultSet.getString("template_name").toString().trim();
			
			HashMap<String,String> htinfo=new HashMap<String,String>();
			htinfo.put("template_name", template_name);
			
			ret.add(htinfo);
		}
		
	}catch(Exception ex){ex.printStackTrace();}
	finally
	   {
		   try{
			   con.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	
	return ret;
}

public ArrayList<HashMap<String,String>> getLineTypeOptions()
{
	ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
	try
	{
		con = DBTransaction.connectKnoWStore();
		
		ResultSet resultSet = null;
		String sql="select 'Header' as line_type union select 'Body' as line_type union select 'Breakup' as line_type union select 'Footer' as line_type ";
		
		
		preparedStatement = con.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{

			String line_type=resultSet.getString("line_type").toString().trim();
			
			HashMap<String,String> htinfo=new HashMap<String,String>();
			htinfo.put("line_type", line_type);
			
			ret.add(htinfo);
		}
		
	}catch(Exception ex){ex.printStackTrace();}
	finally
	   {
		   try{
			   con.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	
	return ret;
}

public ArrayList<HashMap<String,String>> getTextTypeOptions()
{
	ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
	try
	{
		con = DBTransaction.connectKnoWStore();
		
		ResultSet resultSet = null;
		String sql="select 'Text' as text_type union select 'Data' as text_type union select 'Convert' as text_type ";
		//select 'Rejected' as Status union select 'Accepted' as Status union select 'Received' as Status union select 'Failed' as Status
		
		preparedStatement = con.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{
			String text_type=resultSet.getString("text_type").toString().trim();
			
			HashMap<String,String> htinfo=new HashMap<String,String>();
			htinfo.put("text_type", text_type);
			
			ret.add(htinfo);
		}
		
	}catch(Exception ex){ex.printStackTrace();}
	finally
	   {
		   try{
			   con.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	
	return ret;
}

public ArrayList<HashMap<String,String>> getAllDBTableNames()
{
	ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
	try
	{
		con = DBTransaction.connectKnoWStore();
		
		ResultSet resultSet = null;
		String sql="select table_name from information_schema.tables  where table_schema='public' and table_type='BASE TABLE' order by table_name ";
		
		
		preparedStatement = con.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{
			String table_name=resultSet.getString("table_name").toString().trim();
			
			HashMap<String,String> htinfo=new HashMap<String,String>();
			htinfo.put("table_name", table_name);
			
			ret.add(htinfo);
		}
		
	}catch(Exception ex){ex.printStackTrace();}
	finally
	   {
		   try{
			   con.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	
	return ret;
}

public ArrayList<HashMap<String,String>> getColumnNamesByTableName(String TableName)
{
	ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
	try
	{
		con = DBTransaction.connectKnoWStore();
		
		ResultSet resultSet = null;
		String sql="select column_name from information_schema.columns where table_schema = 'public'  and table_name = '"+TableName+"' ";
		
		
		preparedStatement = con.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{
			String column_name=resultSet.getString("column_name").toString().trim();
			
			HashMap<String,String> info=new HashMap<String,String>();
			info.put("column_name",column_name);
			
			ret.add(info);
		}
		
	}catch(Exception ex){ex.printStackTrace();}
	finally
	   {
		   try{
			   con.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	
	return ret;
}

public ArrayList<HashMap<String,String>> getReceiptTemplateDatas(String OrgID,String CourseID,String TemplateType)
{
	ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
	try
	{
		con = DBTransaction.connectKnoWStore();
		
		ResultSet resultSet = null;
		String sql="select * from receipt_configuration where mlt_id = '"+OrgID+"'  and mlt_course = '"+CourseID+"' and template_name = '"+TemplateType+"'";
		
		
		preparedStatement = con.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{

			String mlt_id=resultSet.getString("mlt_id").toString().trim();
			String mlt_course=resultSet.getString("mlt_course").toString().trim();
			String template_name=resultSet.getString("template_name").toString().trim();
			
			String line_type=resultSet.getString("line_type").toString().trim();
			String line_no=resultSet.getString("line_no").toString().trim();
			String text_seq=resultSet.getString("text_seq").toString().trim();
			String text_type=resultSet.getString("text_type").toString().trim();
			String text_value=resultSet.getString("text_value").toString().trim();
			String data_table=resultSet.getString("data_table").toString().trim();
			String data_column=resultSet.getString("data_column").toString().trim();
			
			HashMap<String,String> htinfo=new HashMap<String,String>();
			htinfo.put("mlt_id", mlt_id);
			htinfo.put("mlt_course", mlt_course);
			htinfo.put("template_name", template_name);
			htinfo.put("line_type", line_type);
			htinfo.put("line_no", line_no);
			htinfo.put("text_seq", text_seq);
			htinfo.put("text_type", text_type);
			htinfo.put("text_value", text_value);
			htinfo.put("data_table", data_table);
			htinfo.put("data_column", data_column);
			
			
			ret.add(htinfo);
		}
		
	}catch(Exception ex){ex.printStackTrace();}
	finally
	   {
		   try{
			   con.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	
	return ret;
}
public HashMap<String,String> getCourseDescID(String EnrID,String OrgID)
{
	HashMap<String,String> ret=new HashMap<String,String>();
	try
	{
		
		con = DBTransaction.connect();
		
		String course_id="";
		ResultSet resultSet = null;
		String sql="select course_id from users_info where user_id='"+EnrID+"'";
		preparedStatement = con.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{
			course_id=resultSet.getString("course_id").toString().trim();
		}
		resultSet.close();
		preparedStatement.close();
		con.close();
		
		con=DBTransaction.connectKnoWStore();
		sql="select a.course_discription_id,a.course_id,b.department_name,c.organization_id from transaction_course_description_info a,transaction_department_info b,transaction_middle_layer_teaching_source c "
				+ " where a.course_id_declared_by_teaching_source='"+course_id+"' and a.course_id=b.department_id and a.middle_layer_teaching_source_id=c.middle_layer_teaching_source_id ";
		
		preparedStatement = con.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{
			String course_discription_id=resultSet.getString("course_discription_id").toString().trim();
			String course_id2=resultSet.getString("course_id").toString().trim();
			String department_name=resultSet.getString("department_name").toString().trim();
			String organization_id=resultSet.getString("organization_id").toString().trim();
			if(organization_id.trim().equalsIgnoreCase(OrgID))
			{
				ret.put("course_discription_id", course_discription_id);
				ret.put("department_id", course_id2);
				ret.put("department_name", department_name);
				ret.put("course_id", course_id);
				
			}
		}
		
		resultSet.close();
		preparedStatement.close();
		
		con.close();
	}catch(Exception ex){ex.printStackTrace(); ret=new HashMap<String,String>(); }
	
	return ret;
}

public ArrayList<HashMap<String,String>> getStudentPendingFees(String EnrID,String OrgID)
{
	ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
	try
	{
		con = DBTransaction.connectKnoWStore();
		
		
		ResultSet resultSet = null;
		String sql="select a.mlt_course_fee_code, c.mlt_fee_name,a.fee_amount,a.fee_sequence from txn_fee_structure a, mst_mltsource_fees_x_mltsource_course b, mst_mltsource_x_fee_components c "
				+ "where a.mlt_id='"+OrgID+"' and a.enrolment_id = '"+EnrID+"' and a.status in ('Pending','Failed','Rejected') and b.mlt_course_fee_code = a.mlt_course_fee_code and c.mlt_fee_code = b.mlt_fee_code";
		
		
		preparedStatement = con.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{

			String mlt_course_fee_code=resultSet.getString("mlt_course_fee_code").toString().trim();
			String mlt_fee_name=resultSet.getString("mlt_fee_name").toString().trim();
			String fee_amount=resultSet.getString("fee_amount").toString().trim();
			String fee_sequence=resultSet.getString("fee_sequence").toString().trim();
			
			HashMap<String,String> htinfo=new HashMap<String,String>();
			htinfo.put("mlt_course_fee_code", mlt_course_fee_code);
			htinfo.put("mlt_fee_name", mlt_fee_name);
			htinfo.put("fee_amount", fee_amount);
			htinfo.put("fee_sequence", fee_sequence);
			
			ret.add(htinfo);
		}
		
	}catch(Exception ex){ex.printStackTrace();}
	finally
	   {
		   try{
			   con.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	
	return ret;
}

public ArrayList<HashMap<String,String>> getDisciplineListByOrg(String mltid)
{
	ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
	try
	{
		con = DBTransaction.connectKnoWStore();
		
		ResultSet resultSet = null;
		String sql="select distinct(a.discipline_id), a.discipline_name "
				+ "from transaction_discipline_info a, transaction_program_info b, transaction_course_info c, transaction_department_info d, transaction_course_description_info e "
				+ "where e.middle_layer_teaching_source_id = '"+mltid+"' and d.department_id = e.course_id and c.course_id = d.course_id and "
				+ "b.program_id = c.program_id and a.discipline_id = b.discipline_id order by a.discipline_id ";
		
		// System.out.println(sql);
		preparedStatement = con.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{

			String discipline_id=resultSet.getString("discipline_id").toString().trim();
			String discipline_name=resultSet.getString("discipline_name").toString().trim();
			
			HashMap<String,String> htinfo=new HashMap<String,String>();
			htinfo.put("discipline_id", discipline_id);
			htinfo.put("discipline_name", discipline_name);
			
			ret.add(htinfo);
		}
		
	}catch(Exception ex){ex.printStackTrace();}
	finally
	   {
		   try{
			   con.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	
	return ret;
}

public ArrayList<HashMap<String,String>> getProgramListByOrg(String OrgID)
{
	ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
	try
	{
		con = DBTransaction.connectKnoWStore();
		
		ResultSet resultSet = null;
		String sql="select distinct(a.program_id), a.program_name from transaction_program_info a, transaction_course_info b, transaction_department_info c, transaction_course_description_info d "
				+ "where d.middle_layer_teaching_source_id = '"+OrgID+"' and c.department_id = d.course_id and b.course_id = c.course_id and "
				+ "a.program_id = b.program_id order by a.program_id ";
		
		// System.out.println(sql);
		preparedStatement = con.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{

			String program_id=resultSet.getString("program_id").toString().trim();
			String program_name=resultSet.getString("program_name").toString().trim();
			
			HashMap<String,String> htinfo=new HashMap<String,String>();
			htinfo.put("program_id", program_id);
			htinfo.put("program_name", program_name);
			
			ret.add(htinfo);
		}
		
	}catch(Exception ex){ex.printStackTrace();}
	finally
	   {
		   try{
			   con.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	
	return ret;
}

public ArrayList<HashMap<String,String>> getCourseListByOrg(String OrgID)
{
	ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
	try
	{
		con = DBTransaction.connectKnoWStore();
		
		ResultSet resultSet = null;
		String sql="select distinct(a.course_id), a.course_name from transaction_course_info a, transaction_department_info b, transaction_course_description_info c "
				+ "where c.middle_layer_teaching_source_id = '"+OrgID+"' and b.department_id = c.course_id and a.course_id = b.course_id  order by a.course_id ";
		
	//	System.out.println(sql);
		preparedStatement = con.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{

			String course_id=resultSet.getString("course_id").toString().trim();
			String course_name=resultSet.getString("course_name").toString().trim();
			
			HashMap<String,String> htinfo=new HashMap<String,String>();
			htinfo.put("course_id", course_id);
			htinfo.put("course_name", course_name);
			
			ret.add(htinfo);
		}
		
	}catch(Exception ex){ex.printStackTrace();}
	finally
	   {
		   try{
			   con.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	
	return ret;
}

public ArrayList<HashMap<String,String>> getDeptListByOrg(String OrgID)
{
	ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
	try
	{
		con = DBTransaction.connectKnoWStore();
		
		ResultSet resultSet = null;
		String sql="select distinct a.dept_id, c.department_id, c.department_name ,b.course_id_declared_by_teaching_source "
				+ "from payment_management a, transaction_course_description_info b, transaction_department_info c "
				+ "where a.mlt_id = '"+OrgID+"' and a.status not in ('Pending','Received') and b.course_discription_id = a.dept_id and c.department_id = b.course_id ";
		
		System.out.println(sql);
		preparedStatement = con.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{

			String department_id=resultSet.getString("department_id").toString().trim();
			String department_name=resultSet.getString("department_name").toString().trim();
			String dept_id=resultSet.getString("dept_id").toString().trim();
			String course_id_declared_by_teaching_source=resultSet.getString("course_id_declared_by_teaching_source").toString().trim();
			
			HashMap<String,String> htinfo=new HashMap<String,String>();
			htinfo.put("department_id", department_id);
			htinfo.put("department_name", department_name);
			htinfo.put("dept_id", dept_id);
			htinfo.put("course_id_declared_by_teaching_source", course_id_declared_by_teaching_source);
			
			ret.add(htinfo);
		}
		
	}catch(Exception ex){ex.printStackTrace();}
	finally
	   {
		   try{
			   con.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	
	return ret;
}

public ArrayList<HashMap<String,String>> getPaymentDetailsByDept(String DeptID)
{
	ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
	try
	{
		con = DBTransaction.connectKnoWStore();
		
		ResultSet resultSet = null;
		String sql="select payer_id, mlt_course_fee_code, fee_sequence, payment_mode, instrument_number, instrument_date, issuer_account_number, issuing_bank, "
				+ "expiry_month, expiry_year, amount_paid, txn_reference_no, txn_datetime from  payment_management where dept_id = '"+DeptID+"' ";
		
		System.out.println("get payment details : "+sql);
		preparedStatement = con.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{

			String payer_id=resultSet.getString("payer_id").toString().trim();
			 String mlt_course_fee_code=resultSet.getString("mlt_course_fee_code").toString().trim();
			String fee_sequence=resultSet.getString("fee_sequence").toString().trim();
			/* String payment_mode=resultSet.getString("payment_mode").toString().trim();
			String instrument_number=resultSet.getString("instrument_number").toString().trim();
			String instrument_date=resultSet.getString("instrument_date").toString().trim();
			String issuer_account_number=resultSet.getString("issuer_account_number").toString().trim();
			String issuing_bank=resultSet.getString("issuing_bank").toString().trim();
			String expiry_month=resultSet.getString("expiry_month").toString().trim();
			String expiry_year=resultSet.getString("expiry_year").toString().trim();
			String amount_paid=resultSet.getString("amount_paid").toString().trim();
			*/
			String txn_reference_no=resultSet.getString("txn_reference_no").toString().trim();
			String txn_datetime=resultSet.getString("txn_datetime").toString().trim();
			
			HashMap<String,String> htinfo=new HashMap<String,String>();
			htinfo.put("payer_id", payer_id);
			 htinfo.put("mlt_course_fee_code", mlt_course_fee_code);
			htinfo.put("fee_sequence", fee_sequence);
			/* htinfo.put("payment_mode", payment_mode);
			htinfo.put("instrument_number", instrument_number);
			htinfo.put("instrument_date", instrument_date);
			htinfo.put("issuer_account_number", issuer_account_number);
			htinfo.put("issuing_bank", issuing_bank);
			htinfo.put("expiry_month", expiry_month);
			htinfo.put("expiry_year", expiry_year);
			htinfo.put("amount_paid", amount_paid);
			*/
			
			htinfo.put("txn_reference_no", txn_reference_no);
			htinfo.put("txn_datetime", txn_datetime);
			
			ret.add(htinfo);
		}
		
	}catch(Exception ex){ex.printStackTrace();}
	finally
	   {
		   try{
			   con.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	
	return ret;
}

public ArrayList<HashMap<String,String>> getStudentListforPaymentByDeptID(String DeptID,String OrgID)
{
	ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
	try
	{
		con = DBTransaction.connectKnoWStore();
		
		ResultSet resultSet = null;
		String sql="select distinct a.payer_id, b.full_name from payment_management a, transaction_enrollment_process b "
				+ "where a.payer_id=b.enrollment_process_id and a.mlt_id = '"+OrgID+"' and a.status not in ('Pending','Received') and a.dept_id = '"+DeptID+"' ";
		
		System.out.println(sql);
		preparedStatement = con.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{

			String payer_id=resultSet.getString("payer_id").toString().trim();
			String full_name=resultSet.getString("full_name").toString().trim();
				
			HashMap<String,String> htinfo=new HashMap<String,String>();
			htinfo.put("payer_id", payer_id);
			htinfo.put("payer_name", full_name);
			
			ret.add(htinfo);
		}
		
	}catch(Exception ex){ex.printStackTrace();}
	finally
	   {
		   try{
			   con.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
		
	return ret;
}

public ArrayList<HashMap<String,String>> getPayModeByDeptIDandEnrID(String DeptID,String EnrID,String OrgID)
{
	ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
	try
	{
		con = DBTransaction.connectKnoWStore();
		
		ResultSet resultSet = null;
		String sql="select distinct payment_mode from payment_management "
				+ "where mlt_id = '"+OrgID+"' and status not in ('Pending','Received') and dept_id = '"+DeptID+"' and payer_id = '"+EnrID+"' ";
		
		System.out.println(sql);
		preparedStatement = con.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{

			String payment_mode=resultSet.getString("payment_mode").toString().trim();
			
			HashMap<String,String> htinfo=new HashMap<String,String>();
			htinfo.put("payment_mode", payment_mode);
			
			ret.add(htinfo);
		}
		
	}catch(Exception ex){ex.printStackTrace();}
	finally
	   {
		   try{
			   con.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }

	
	return ret;
}


public ArrayList<HashMap<String,String>> getFeesListfromPaymentByDeptIDandEnrID(String DeptID,String EnrID,String OrgID)
{
	ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
	try
	{
		con = DBTransaction.connectKnoWStore();
		
		ResultSet resultSet = null;
		String sql="select a.mlt_course_fee_code, a.fee_sequence, c.mlt_fee_name "
				+ "from payment_management a, mst_mltsource_fees_x_mltsource_course b, mst_mltsource_x_fee_components c "
				+ "where a.mlt_id = '"+OrgID+"' and a.status not in ('Pending','Received') and a.dept_id = '"+DeptID+"' and a.payer_id = '"+EnrID+"' "
				+ "and b.mlt_course_fee_code = a.mlt_course_fee_code and c.mlt_fee_code = b. mlt_fee_code order by a.fee_sequence ";
		
		System.out.println(sql);
		preparedStatement = con.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{

			String mlt_course_fee_code=resultSet.getString("mlt_course_fee_code").toString().trim();
			String fee_sequence=resultSet.getString("fee_sequence").toString().trim();
			String mlt_fee_name=resultSet.getString("mlt_fee_name").toString().trim();
			
			mlt_fee_name=mlt_fee_name+" ("+mlt_course_fee_code+")";
			
			HashMap<String,String> htinfo=new HashMap<String,String>();
			htinfo.put("mlt_course_fee_code", mlt_course_fee_code);
			htinfo.put("fee_sequence", fee_sequence);
			htinfo.put("mlt_fee_name", mlt_fee_name);
			
			ret.add(htinfo);
		}
		
	}catch(Exception ex){ex.printStackTrace();}
	finally
	   {
		   try{
			   con.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }

	
	return ret;
}

public HashMap<String,String> getFeesPaymentDetails(String OrgID,String DeptID,String EnrID,String FeesCode,String FeesSeq)
{
	HashMap<String,String> ret=new HashMap<String,String>();
	try
	{
		con = DBTransaction.connectKnoWStore();
		
		ResultSet resultSet = null;
		String sql="select * from payment_management where mlt_id = '"+OrgID+"' and status not in ('Pending','Received') and dept_id = '"+DeptID+"' and payer_id = '"+EnrID+"' "
				+ "and mlt_course_fee_code = '"+FeesCode+"' and fee_sequence = '"+FeesSeq+"'  ";
		
		 System.out.println(sql);
		preparedStatement = con.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{

			String payer_id=resultSet.getString("payer_id").toString().trim();
			String mlt_course_fee_code=resultSet.getString("mlt_course_fee_code").toString().trim();
			String fee_sequence=resultSet.getString("fee_sequence").toString().trim();
			String payment_mode=resultSet.getString("payment_mode").toString().trim();
			String instrument_number="";
			String instrument_date="";
			String issuer_account_number="";
			String issuer_name="";
			String issuing_bank="";
			String expiry_month="";
			String expiry_year="";
			if(!payment_mode.trim().equalsIgnoreCase("Cash"))
			{
				if(resultSet.getObject("instrument_number")!=null)
					instrument_number=resultSet.getString("instrument_number").toString().trim();
				
				if(resultSet.getObject("instrument_date")!=null)
					instrument_date=resultSet.getString("instrument_date").toString().trim();
				
				if(resultSet.getObject("issuer_account_number")!=null)
					issuer_account_number=resultSet.getString("issuer_account_number").toString().trim();
				
				if(resultSet.getObject("issuer_name")!=null)
					issuer_name=resultSet.getString("issuer_name").toString().trim();
				
				if(resultSet.getObject("issuing_bank")!=null)
					issuing_bank=resultSet.getString("issuing_bank").toString().trim();
				
				if(resultSet.getObject("expiry_month")!=null)
					expiry_month=resultSet.getString("expiry_month").toString().trim();
				
				if(resultSet.getObject("expiry_year")!=null)
					expiry_year=resultSet.getString("expiry_year").toString().trim();
			}
			
			String amount_paid=resultSet.getString("amount_paid").toString().trim();
			String txn_reference_no=resultSet.getString("txn_reference_no").toString().trim();
			String txn_datetime=resultSet.getString("txn_datetime").toString().trim();
			
			HashMap<String,String> htinfo=new HashMap<String,String>();
			htinfo.put("payer_id", payer_id);
			htinfo.put("mlt_course_fee_code", mlt_course_fee_code);
			htinfo.put("fee_sequence", fee_sequence);
			htinfo.put("payment_mode", payment_mode);
			htinfo.put("instrument_number", instrument_number);
			htinfo.put("instrument_date", instrument_date);
			htinfo.put("issuer_account_number", issuer_account_number);
			htinfo.put("issuer_name", issuer_name);
			htinfo.put("issuing_bank", issuing_bank);
			htinfo.put("expiry_month", expiry_month);
			htinfo.put("expiry_year", expiry_year);
			htinfo.put("amount_paid", amount_paid);
			htinfo.put("txn_reference_no", txn_reference_no);
			htinfo.put("txn_datetime", txn_datetime);
			
			ret=htinfo;
		}
		
	}catch(Exception ex){ex.printStackTrace();}
	finally
	   {
		   try{
			   con.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }

	
	return ret;
}

public HashMap<String,String> getPaymentDetailsfromPaymentByDeptIDandEnrIDandFeesCodeandFeeSeq(String OrgID,String DeptID,String EnrID,String FeesCode,String FeesSeq)
{
	HashMap<String,String> ret=new HashMap<String,String>();
	try
	{
		ArrayList<HashMap<String,String>> PaymentDetails=getPaymentDetailsByDept(DeptID);
		HashMap<String,String> stuinfo=getStudentListByDept(DeptID);
		
		if(PaymentDetails!=null &&  PaymentDetails.size()>0)
		{
			for(int i=0;i<PaymentDetails.size();i++)
			{
				HashMap<String,String> info=PaymentDetails.get(i);
				
				
				String payer_id=info.get("payer_id").toString().trim();
				
				String payer_name="";
				if(stuinfo.containsKey(payer_id))
					payer_name=stuinfo.get(payer_id).toString().trim();
				
				String mlt_course_fee_code=info.get("mlt_course_fee_code").toString().trim();
				String fee_sequence=info.get("fee_sequence").toString().trim();
				
				if(EnrID.trim().equalsIgnoreCase(payer_id) && FeesCode.trim().equalsIgnoreCase(mlt_course_fee_code) && FeesSeq.trim().equalsIgnoreCase(fee_sequence))
				{
					info.put("payer_name", payer_name);
					ret=info;
				}
				
			}
		}
	}catch(Exception ex){ex.printStackTrace();}
	
	return ret;
}

public HashMap<String,String> getStudentListByDept(String CourseDeptID)
{
	HashMap<String,String> ret=new HashMap<String,String>();
	try
	{
		con = DBTransaction.connectKnoWStore();
		
		ResultSet resultSet = null;
		String sql="select enrollment_process_id,full_name from transaction_enrollment_process where course_id='"+CourseDeptID+"' ";
		
		
		preparedStatement = con.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{

			String enrollment_process_id=resultSet.getString("enrollment_process_id").toString().trim();
			String full_name=resultSet.getString("full_name").toString().trim();
			
			ret.put(enrollment_process_id, full_name);
		}
		
	}catch(Exception ex){ex.printStackTrace();}
	finally
	   {
		   try{
			   con.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	
	return ret;
}

public ArrayList<HashMap<String,String>> getPaymentStatusOptions()
{
	ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
	try
	{
		con = DBTransaction.connectKnoWStore();
		
		ResultSet resultSet = null;
		String sql="select 'Rejected' as Status union select 'Accepted' as Status union select 'Received' as Status union select 'Failed' as Status ";
		
		
		preparedStatement = con.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) 
		{
			String Status=resultSet.getString("Status").toString().trim();
			
			HashMap<String,String> htinfo=new HashMap<String,String>();
			htinfo.put("Status", Status);
			
			ret.add(htinfo);
		}
		
	}catch(Exception ex){ex.printStackTrace();}
	finally
	   {
		   try{
			   con.close();
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	
	return ret;
}

public String getMLTIDByOrgID_NotUsed(String OrgID)
{
	String ret="";
	try
	{

		PreparedStatement ps = null;
		ResultSet rs=null;
		try{
		con =  DBTransaction.connectKnoWStore();
		
		String query = "select middle_layer_teaching_source_id from transaction_middle_layer_teaching_source where organization_id='"+OrgID+"'";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			ret = rs.getString("middle_layer_teaching_source_id");
					}
	}
	catch(Exception e)
	{
			e.printStackTrace();
	}
	finally{
			ps.close();
			con.close();
			rs.close();
	}
	}catch(Exception ex){}
return ret;
	
}

//select * from mst_mltsource_fees_x_mltsource_course where mlt_id='UniTNOSof' and mlt_course='cdid25'

	public ArrayList<HashMap<String,String>> checkCourseFeesIDByOrgIDAndByCourseID(String OrgID,String CourseID)
	{
		ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
		try
		{
			con = DBTransaction.connectKnoWStore();
			
			ResultSet resultSet = null;
			String sql="select * from mst_mltsource_fees_x_mltsource_course where mlt_id = '"+OrgID+"' and mlt_course = '"+CourseID+"' ";
			
		//	System.out.println(sql);
			preparedStatement = con.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) 
			{

				String mlt_id=resultSet.getString("mlt_id").toString().trim();
				String mlt_course=resultSet.getString("mlt_course").toString().trim();
				String fee_sequence=resultSet.getString("fee_sequence").toString().trim();
				String mlt_course_fee_code=resultSet.getString("mlt_course_fee_code").toString().trim();
								
				HashMap<String,String> htinfo=new HashMap<String,String>();
				htinfo.put("mlt_id", mlt_id);
				htinfo.put("mlt_course", mlt_course);
				htinfo.put("fee_sequence", fee_sequence);
				htinfo.put("mlt_course_fee_code", mlt_course_fee_code);
				
				ret.add(htinfo);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		
		return ret;
	}
	
	public ArrayList<HashMap<String,String>> checkFeesGroupIDByCrsFeeCodeAndByFeeCode(String GroupFeesID,String MemberFeeID)
	{
		ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
		try
		{
			con = DBTransaction.connectKnoWStore();
			
			ResultSet resultSet = null;
			String sql="select * from mst_mltsource_fees_group where mlt_course_fee_code = '"+GroupFeesID+"' and mlt_fee_code = '"+MemberFeeID+"' ";
			
		//	System.out.println(sql);
			preparedStatement = con.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) 
			{

				String mlt_course_fee_code=resultSet.getString("mlt_course_fee_code").toString().trim();
				String mlt_fee_code=resultSet.getString("mlt_fee_code").toString().trim();
								
				HashMap<String,String> htinfo=new HashMap<String,String>();
				htinfo.put("mlt_fee_code", mlt_fee_code);
				htinfo.put("mlt_course_fee_code", mlt_course_fee_code);
				
				ret.add(htinfo);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		
		return ret;
	}
	
public String getNxtFeesID()
	{
		String ret="FEE_001";
		String cnt="001";
		String FeeID="";
		try
		{
			con = DBTransaction.connectKnoWStore();
			
			ResultSet resultSet = null;
			String sql="select nextval('feeid') as feeid";
			
			preparedStatement = con.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) 
			{
				FeeID=resultSet.getString("feeid").toString().trim();
				// System.out.println("FeeID == "+FeeID);
			}
			
			if(FeeID.length()==1)
				FeeID="00"+FeeID;
			if(FeeID.length()==2)
				FeeID="0"+FeeID;
			ret="FEE_"+FeeID;
		}catch(Exception ex){ex.printStackTrace();}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		
		/*try
		{
			ArrayList<HashMap<String,String>> FeesList=getAllFeesComponentsByOder("DESC");
			if(FeesList!=null && FeesList.size()>0)
			{
				HashMap<String,String> info=FeesList.get(0);
				String fee_code=info.get("fee_code").toString().trim();
				String[] vals=fee_code.split("_");
				int val=Integer.parseInt(vals[1]);
				val=val+1;
				if(val<10)
					cnt="00"+val;
				else if(val>10 && val<99)
					cnt="0"+val;
				else
					cnt=""+val;
			}
			
			ret="FEE_"+cnt;
			
		}catch(Exception ex){ex.printStackTrace();}
	*/	
		return ret;
	}
	
	public int insertFeeComponents(String FeesID,String FeesName,String User)
	{
		int ret=0;
		try
		{
			con=DBTransaction.connectKnoWStore();
			
			String sql="insert into mst_fee_components(fee_code,fee_name,creation_date,last_update,user_id,status) values(?,?,?,?,?,?)";
			
								
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, ""+FeesID);
			preparedStatement.setString(2, ""+FeesName);
			preparedStatement.setTimestamp(3, getTimeStamp());
			preparedStatement.setTimestamp(4, getTimeStamp());
			preparedStatement.setString(5, User);
			preparedStatement.setString(6, "Active");
			
			ret= preparedStatement.executeUpdate();
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		
		return ret;
	}
	public int insertInstituteFeesConfig(String OrgID,String FeesID,String Prefix,String Body,String Suffix,String FeesDesc,String InstFeesID,String User)
	{
		int ret=0;
		try
		{
			con=DBTransaction.connectKnoWStore();
			
			String sql="insert into mst_mltsource_x_fee_components values(?,?,?,?,?,?,?,?,?,?,?)";
			
								
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, ""+OrgID);
			preparedStatement.setString(2, ""+FeesID);
			preparedStatement.setString(3, Prefix);
			preparedStatement.setString(4, Body);
			preparedStatement.setString(5, Suffix);
			preparedStatement.setString(6, FeesDesc);
			preparedStatement.setString(7, InstFeesID);
			
			preparedStatement.setTimestamp(8, getTimeStamp());
			preparedStatement.setTimestamp(9, getTimeStamp());
			preparedStatement.setString(10, User);
			preparedStatement.setString(11, "Active");
			
			ret= preparedStatement.executeUpdate();
		
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return ret;
	}

	public int insertCourseFeesConfig(String OrgID,String CourseID,String Sequence,String FeesID,String Group,String FeesAmount,String CourseFeesID,String FeesType,String FeesFreq,String User)
	{
		int ret=0;
		try
		{
			con=DBTransaction.connectKnoWStore();
			
			String sql="insert into mst_mltsource_fees_x_mltsource_course(mlt_id,mlt_course,fee_sequence,mlt_fee_code,mlt_course_fee_code,grouped_feecode,"
					+ "creation_date,last_update,user_id,status,fee_amount,fee_type,recurring_frequency) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
								
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, ""+OrgID);
			preparedStatement.setString(2, ""+CourseID);
			preparedStatement.setInt(3, Integer.parseInt(Sequence)); 
			preparedStatement.setString(4, FeesID);
			preparedStatement.setString(5, CourseFeesID);
			preparedStatement.setString(6, Group);
			preparedStatement.setTimestamp(7, getTimeStamp());
			preparedStatement.setTimestamp(8, getTimeStamp());
			preparedStatement.setString(9, User);
			preparedStatement.setString(10, "Active");
			preparedStatement.setDouble(11, Double.parseDouble(FeesAmount)); // .setString(11, FeesAmount);
			preparedStatement.setString(12, FeesType);
			preparedStatement.setInt(13, Integer.parseInt(FeesFreq)); 
			
			ret= preparedStatement.executeUpdate();
		
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		
		return ret;
	}
	
	public int insertFeesGroupConfig(String OrgID,String CourseID,String GroupFeesID,String GroupSequence,String MemberFeeID,String MemberFeesValue,String MemberFeesType,String User)
	{
		int ret=0;
		try
		{
			con=DBTransaction.connectKnoWStore();
			
			String sql="INSERT INTO mst_mltsource_fees_group"
					+ "(mlt_course_fee_code, mlt_fee_code, group_sequence, member_fee_value,member_fee_value_type, creation_date, last_update, user_id, status,mlt_id)"
					+ " VALUES ( ?, ?, ?, ?,?, ?, ?, ?, ?, ?)";
			 
								
			preparedStatement = con.prepareStatement(sql);
			/*preparedStatement.setString(1, ""+OrgID);
			preparedStatement.setString(2, ""+CourseID);*/
			preparedStatement.setString(1, ""+GroupFeesID);
			preparedStatement.setString(2, ""+MemberFeeID); 
			preparedStatement.setInt(3, Integer.parseInt(GroupSequence));
			preparedStatement.setDouble(4, Double.parseDouble(MemberFeesValue));
			preparedStatement.setString(5, MemberFeesType);
			preparedStatement.setTimestamp(6, getTimeStamp());
			preparedStatement.setTimestamp(7, getTimeStamp());
			preparedStatement.setString(8, User);
			preparedStatement.setString(9, "Active");
			preparedStatement.setString(10, ""+OrgID);
			ret= preparedStatement.executeUpdate();
		
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		   {
			   try{
				  con.close();
				   preparedStatement.close();
				   
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return ret;
		
	}
	
	public void insertFeesReceiptTemplate(String OrgID,String CourseID,String TemplateType,ArrayList<HashMap<String,String>> TemplateDataRows,String User)
	{
		try
		{
			con=DBTransaction.connectKnoWStore();
			Statement st=con.createStatement();
			
			ArrayList<HashMap<String,String>> extinfo=getReceiptTemplateDatas(OrgID, CourseID, TemplateType);
			if(extinfo!=null && extinfo.size()>0)
			{
				Connection connn=null;
				PreparedStatement preparedStmt =null;
				try
				{
					connn=DBTransaction.connectKnoWStore();
					String query = "delete from receipt_configuration where mlt_id= ? and mlt_course= ? and template_name= ?";
					preparedStmt = connn.prepareStatement(query);
					preparedStmt.setString(1, OrgID);
					preparedStmt.setString(2, CourseID);
					preparedStmt.setString(3, TemplateType);
					preparedStmt.executeUpdate();
					
				}catch(Exception ex){}
				finally
				{
					connn.close();
					preparedStmt.close();
				}
				
			}
			
			if(TemplateDataRows!=null && TemplateDataRows.size()>0)
	        {
	        	for(int i=0;i<TemplateDataRows.size();i++)
	        	{
	        		HashMap<String,String> info=TemplateDataRows.get(i);
	        		String line_type=info.get("line_type").toString().trim();
	        		String line_no=info.get("line_no").toString().trim();
	        		String text_seq=info.get("text_seq").toString().trim();
	        		String text_type=info.get("text_type").toString().trim();
	        		String text_value=info.get("text_value").toString().trim();
	        		String data_table=info.get("data_table").toString().trim();
	        		String data_column=info.get("data_column").toString().trim();
	        		
	        		if(line_type.trim().length()>0 && line_no.trim().length()>0 && text_seq.trim().length()>0 && 
	        				text_type.trim().length()>0)
	        		{
	        			String sql="INSERT INTO receipt_configuration(mlt_id, mlt_course, template_name, line_type, line_no, text_seq,text_type, text_value, data_table, data_column,creation_date, last_update, user_id, status) "
		        				+ "VALUES ('"+OrgID+"','"+CourseID+"','"+TemplateType+"','"+line_type+"','"+line_no+"','"+text_seq+"','"+text_type+"','"+text_value+"','"+data_table+"','"+data_column+"','"+getTimeStamp()+"','"+getTimeStamp()+"','"+User+"','Active' )";
		        	
		        		// System.out.println("sql == "+sql);
		        		st.addBatch(sql);
		        		
	        		}
	        	}
	        }
			
			int[] result=st.executeBatch();
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		   {
			   try{
				  con.close();
				  preparedStatement.close();
				  
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		
	}
	
	public void insertFeesPaymentByStudent(String OrgID,String CourseID,String ENRID,String FeeName,String PaymentMode,String InstrumentNo,String AccountNo,String ExpMonth,String CourseName,String FeeAmount,String BankName,String InstrumentDate,String IssuerName,String ExpYear,String FeeSeq)
	{
		try
		{
			con=DBTransaction.connectKnoWStore();
			
			String Seq="select nextval('paymtref') as nxtSeq";
			preparedStatement = con.prepareStatement(Seq);
			ResultSet result=null;
			
			result = preparedStatement.executeQuery();
			String nxtSeq="";
			while(result.next()) 
			{
				nxtSeq=result.getString("nxtSeq").toString().trim();
			}
			String TxtRefNo=OrgID+"_"+CourseID+"_"+nxtSeq;
			preparedStatement.close();
			result.close();
			
			boolean recordcheck=false;
			String sqlcheck="select * from payment_management where payer_id='"+ENRID+"' and mlt_course_fee_code='"+FeeName+"' and fee_sequence='"+FeeSeq+"'";
			
			System.out.println("sqlcheck == "+sqlcheck);
			preparedStatement=con.prepareStatement(sqlcheck);
			result=preparedStatement.executeQuery();
			if(result.next())
			{
				recordcheck=true;
				
			}else
			{
				recordcheck=false;
			}
			result.close();
			preparedStatement.close();
			
			InstrumentNo="'"+InstrumentNo+"'";
			InstrumentDate="'"+InstrumentDate+"'";
			ExpMonth="'"+ExpMonth+"'";
			ExpYear="'"+ExpYear+"'";
			AccountNo="'"+AccountNo+"'";
			
			if(PaymentMode.trim().equalsIgnoreCase("Cash"))
			{
				InstrumentNo=null;
				InstrumentDate=null;
				ExpMonth=null;
				ExpYear=null;
				AccountNo=null;  
			}
			else if(PaymentMode.trim().equalsIgnoreCase("Transfer"))
			{
				InstrumentDate=null;
				ExpMonth=null;
				ExpYear=null;
				
			}
			
			String sql2="INSERT INTO payment_management(mlt_id, txn_reference_no, txn_datetime, payer_id, payment_mode,"
					+ "instrument_number, instrument_date, issuing_bank, issuer_name,expiry_month, expiry_year, amount_paid,"
					+ "txn_status, creation_date, last_update, user_id, status, issuer_account_number,dept_id, mlt_course_fee_code, fee_sequence)"
					+ "VALUES ('"+OrgID+"','"+TxtRefNo+"','"+getTimeStamp()+"','"+ENRID+"','"+PaymentMode+"',"+InstrumentNo+","+InstrumentDate+""
							+ ",'"+BankName+"','"+IssuerName+"',"+ExpMonth+","+ExpYear+",'"+FeeAmount+"',' ','"+getTimeStamp()+"'"
									+ ",'"+getTimeStamp()+"','"+ENRID+"','Submitted',"+AccountNo+",'"+CourseID+"','"+FeeName+"','"+FeeSeq+"')";
			System.out.println("sql2 == "+sql2);					
			preparedStatement = con.prepareStatement(sql2);
			
			if(!recordcheck)
				{
					preparedStatement.executeUpdate();
					changestatusforfeestruc(FeeName,ENRID,FeeSeq,"Submitted");
				}
		
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		   {
			   try{
				  con.close();
				   preparedStatement.close();
				   
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		
	}
	
	public void changestatusforfeestruc(String mlt_course_fee_code,String enrolment_id,String fee_sequence,String FeeStatus)
	{
		try
		{
			con=DBTransaction.connectKnoWStore();
			String sql2="UPDATE txn_fee_structure set  status='"+FeeStatus+"' where mlt_course_fee_code='"+mlt_course_fee_code+"'"
					+ " and enrolment_id='"+enrolment_id+"' and fee_sequence='"+fee_sequence+"'";
			System.out.println("sql2 == "+sql2);					
			
			preparedStatement = con.prepareStatement(sql2);
		
		
			int Status= preparedStatement.executeUpdate();
	
		
		}catch(Exception ex){ex.printStackTrace();}
		finally
		{	
		   try{
			  con.close();
			   preparedStatement.close();
			   
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }

}
	public void insertFeesPaymentByAdmin(HashMap<String,String> info)
	{
		try
		{
			con=DBTransaction.connectKnoWStore();
			
			/*String payer_id=info.get("payer_id").toString().trim();
			String mlt_course_fee_code=info.get("mlt_course_fee_code").toString().trim();
			String fee_sequence=info.get("fee_sequence").toString().trim();
			String payment_mode=info.get("payment_mode").toString().trim();
			String instrument_number=info.get("instrument_number").toString().trim();
			String instrument_date=info.get("instrument_date").toString().trim();
			String issuer_account_number=info.get("issuer_account_number").toString().trim();
			String issuing_bank=info.get("issuing_bank").toString().trim();
			String expiry_month=info.get("expiry_month").toString().trim();
			String expiry_year=info.get("expiry_year").toString().trim();
			String amount_paid=info.get("amount_paid").toString().trim();
			*/
			
			String txn_reference_no=info.get("txn_reference_no").toString().trim();
			String txn_datetime=info.get("txn_datetime").toString().trim();
			String PaymentStatus=info.get("PaymentStatus").toString().trim();
			
			
			String sql2="update payment_management set status='"+PaymentStatus+"' where txn_reference_no='"+txn_reference_no+"' and txn_datetime='"+txn_datetime+"'";
			System.out.println("sql2 == "+sql2);					
			preparedStatement = con.prepareStatement(sql2);
			
			
			int Status= preparedStatement.executeUpdate();
		
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		   {
			   try{
				  con.close();
				   preparedStatement.close();
				   
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		
	}
	
	public java.sql.Timestamp getTimeStamp()
	{
		java.sql.Timestamp ret=null;
		try
		{
			Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			ret = new java.sql.Timestamp(now.getTime());
			
		}catch(Exception ex){ex.printStackTrace();}
		
		return ret;
	}
	@SuppressWarnings("unchecked")
	public HashMap<String,String> getStudentsInfo(String userID)
	{
		System.out.println("in student info method");
		//HashMap<String, String> ret=new HashMap<String,String>();
		HashMap<String,String> htinfo=new HashMap<String,String>();
		try
		{
			con = DBTransaction.connectKnoWStore();
			
			ResultSet resultSet = null;
			String sql="select UI.email,password, username, course_id,course_name,organization_name from users_info UI left join org_details OD on UI.organization_id=OD.organization_id "     
			+ "left join course_description CD on OD.organization_id=CD.customer_id  and CD.course_id_defined_by_teaching_source=UI.course_id where user_id='"+userID+"' ";
			System.out.println("sql in fee dao="+sql);
			
			preparedStatement = con.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
		
			while(resultSet.next()) 
			{
				String email=resultSet.getString("email").toString().trim();
				String password=resultSet.getString("password").toString().trim();
				String username=resultSet.getString("username").toString().trim();
				String course_id=resultSet.getString("course_id").toString().trim();
				String course_name=resultSet.getString("course_name").toString().trim();  
				String org_name=resultSet.getString("organization_name").toString().trim();
				
				System.out.println("course_id="+course_id);
				
				htinfo.clear();
				htinfo.put("email1", email);
				htinfo.put("password", password);
				htinfo.put("username", username);
				htinfo.put("course_id", course_id);
				System.out.println("email="+email);
				htinfo.put("org_name", org_name); 
				htinfo.put("course_name", course_name);
				//((List<HashMap<String, String>>) ret).add(htinfo);
				//ret.put(htinfo,"jj");
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		
		return htinfo;
	}

}
