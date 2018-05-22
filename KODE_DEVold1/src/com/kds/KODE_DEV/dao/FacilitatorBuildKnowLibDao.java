package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.FacilitatorBuildKnowLibDomain;
import com.kds.KODE_DEV.services.FacilitatorBuildKnowServlet1;
import com.kds.KODE_DEV.util.Utilities;

public class FacilitatorBuildKnowLibDao {
	Connection con=null;
	PreparedStatement pstmt = null;
	
	static final Logger LOGGER = Logger.getLogger(FacilitatorBuildKnowLibDao.class);
	public String insertLibInformation(FacilitatorBuildKnowLibDomain fDomain) {

		String status = null;

		try {
LOGGER.info("in try");
			con = DBTransaction.connect();
			//PreparedStatement pstmt = null;
			// ResultSet rs = null;
			String sql = "insert into knowledgestorelib_info(ksid, name_of_lib, size_of_lib, user_id, organization_id,space_uom) values(?,?,?,?,?,?)";
			LOGGER.info("in try sql="+sql);
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, fDomain.getKsId());
			pstmt.setString(2, fDomain.getNewName());
			pstmt.setInt(3, fDomain.getLibSize());
			pstmt.setString(4, fDomain.getUserId());
			pstmt.setString(5, fDomain.getOrgId());
			 pstmt.setString(6, fDomain.getSpaceUom());
			LOGGER.info("pstmt="+pstmt);
			//System.out.println("the query is:" + pstmt);
			int n = pstmt.executeUpdate();
			//System.out.println("query is inserted successfully");
			LOGGER.info(sql);
			if (n != 0)
				status = "valid";
			else
				status = "notvalid";
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
			   con.close();
			   pstmt.close();
			  
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return status;
	}
	
	public static void main(String args[])
	{
		FacilitatorBuildKnowLibDao fdao=new FacilitatorBuildKnowLibDao();
		String libSize=fdao.selectAllLibSpaceOnSpaceUom("Anugre454");
		System.out.println(libSize);
	}
	
	public  String selectAllLibSpaceOnSpaceUom(String userId) {
		
		String allLibKnowStoreSpace = "";
		String TB="";
		String GB="";
		String MB="";
		String KB="";
		long libSizeInt=0l;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			// String createdBy = aksDomain.getUserId();

			con = DBTransaction.connect();
			
			//String selectAllLibSize = "select case when sum(size_of_lib) is null then 0 else sum(size_of_lib) end from knowledgestorelib_info where user_id ='"+userId+"'";
			String selectAllLibSize = "select space_uom,sum(size_of_lib) from knowledgestorelib_info where user_id=? group by space_uom ";
			System.out.println("The query is:" + selectAllLibSize);
			preparedStatement = con.prepareStatement(selectAllLibSize);
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			
		
			
			// System.out.println(libSizeInt);
			while (resultSet.next()) {
				
				String spaceUom = resultSet.getString(1);

				String allSize = resultSet.getString(2);
				if("TB".equals(spaceUom))
				{
					TB=allSize;
					libSizeInt+= 109951162777600l*(new Long(TB));
				}
				if("GB".equals(spaceUom))
				{
					GB=allSize;
					libSizeInt+= 1073741824*(new Long(GB));
				}
				if("MB".equals(spaceUom))
				{
					MB=allSize;
					libSizeInt+= 1048576*(new Long(MB));
				}
				if("KB".equals(spaceUom))
				{
					KB=allSize;
					libSizeInt+= 1024*(new Long(KB));
				}
				
                  System.out.println(spaceUom+":"+allSize);
				//System.out.println("all library know Store Space is : "	+ allSize);
				//allLibKnowStoreSpace = allSize;
			}
			// //System.out.println("The return value is:" + rDomain);

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
			  
			   preparedStatement.close();
			   con.close();
			  
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		
		allLibKnowStoreSpace=""+libSizeInt;
		return allLibKnowStoreSpace;
	
	}
	
	public String getAvailableLibSpaceForFaculty(String userId,String orgId)
	{
		System.out.println(".....Inside Available Space ....");
		
		String utilizedSpace=selectAllLibSpaceOnSpaceUom(userId);
		System.out.println(" Utilized Space: "+utilizedSpace);
		
		Long utilizedSpaceLong=new Long(utilizedSpace);
		
		FacilitatorBuildKnowLibDomain fbkld= selectFacultyKnowSpace(orgId,userId);
		int knowStoreSize=fbkld.getLibSize();
		String spaceUom=fbkld.getSpaceUom();
		System.out.println(" Know Store Space: "+knowStoreSize);
		System.out.println(" Space UOM : "+spaceUom);
		
		
		String totalAvailableSpace=Utilities.getBytes(knowStoreSize, spaceUom);
		Long totalAvailableSpaceLong=new Long(totalAvailableSpace);
		System.out.println(totalAvailableSpaceLong);
		
		long availableSpace=totalAvailableSpaceLong-utilizedSpaceLong;
		System.out.println(availableSpace);
		String availableSpaceStr=Utilities.convert(availableSpace);
		
		return availableSpaceStr;
	}
	

	public String selectAllLibSpace(String userId) {

		String allLibKnowStoreSpace = "";

		try {

			// String createdBy = aksDomain.getUserId();

			con = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String selectAllLibSize = "select case when sum(size_of_lib) is null then 0 else sum(size_of_lib) end from knowledgestorelib_info where user_id ='"+userId+"'";

			System.out.println("The query is:" + selectAllLibSize);
			pstmt = con.prepareStatement(selectAllLibSize);
			resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				String allSize = resultSet.getString(1);
				System.out.println("all library know Store Space is : "	+ allSize);
				allLibKnowStoreSpace = allSize;
			}
			// //System.out.println("The return value is:" + rDomain);

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
			 
			   pstmt.close();
			   con.close();
			  
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return allLibKnowStoreSpace;

	}

	public int selectOwnerKnowSpace() {
		int OwnerKnowSpace=0;
		//int facultyknowStoreSpace = 0;//"";
		try {

			// String sessionUser = aksDomain.getUserId();

			con = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String selectFacultySize = "select total_space from knowledge_store_owner_storage_space";

			System.out.println("The query is:...." + selectFacultySize);
			pstmt = con.prepareStatement(selectFacultySize);
			resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {

				OwnerKnowSpace = resultSet.getInt("total_space");

				//System.out.println("Faculty know Store Space is : " + facultyKnowSpace);
				//facultyknowStoreSpace = facultyKnowSpace;

			}
			// //System.out.println("The return value is:" + rDomain);

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
			   con.close();
			   pstmt.close();
			  
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return OwnerKnowSpace;

	}
	
	public int selectAllKnowSpace() {
		int AllKnowSpace=0;
		//int facultyknowStoreSpace = 0;//"";
		try {
			// String sessionUser = aksDomain.getUserId();
			con = DBTransaction.connect();
			//PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String selectAllKnowSize = "select sum(knowledge_store_space) AllKnowSpace from knowledgestore_info";
			System.out.println("The query is:...." + selectAllKnowSize);
			pstmt = con.prepareStatement(selectAllKnowSize);
			resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				AllKnowSpace = resultSet.getInt("AllKnowSpace");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
			   con.close();
			   pstmt.close();
			  
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return AllKnowSpace;

	}

	public int selectFacultyKnowSpace(String orgId) {
		
	
		
	
		int facultyknowStoreSpace = 0;//"";
		try {
			con = DBTransaction.connect();
			ResultSet resultSet = null;
			String selectFacultySize = "select knowledge_store_space from knowledgestore_info where organization_id ='"+orgId+"'";

			System.out.println("The query is:...." + selectFacultySize);
			pstmt = con.prepareStatement(selectFacultySize);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				int facultyKnowSpace = resultSet.getInt("knowledge_store_space");
				
			

				//System.out.println("Faculty know Store Space is : " + facultyKnowSpace);
				facultyknowStoreSpace = facultyKnowSpace;

			}
			// //System.out.println("The return value is:" + rDomain);

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
			
			   pstmt.close();
			   con.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return facultyknowStoreSpace;

	}
	
	
	
	
public FacilitatorBuildKnowLibDomain selectFacultyKnowSpace(String orgId,String userId) {
		
		FacilitatorBuildKnowLibDomain fbkld=new FacilitatorBuildKnowLibDomain();
		
	
		int facultyknowStoreSpace = 0;//"";
		try {
			con = DBTransaction.connect();
			ResultSet resultSet = null;
			String selectFacultySize = "select a.knowledge_store_space,b.space_uom from knowledgestore_info a,knowledgestore_teachingsource_info b where a.ts_space_id=b.ts_space_id and a.user_id='"+userId+"' and a.organization_id ='"+orgId+"'";

			System.out.println("The query is:...." + selectFacultySize);
			pstmt = con.prepareStatement(selectFacultySize);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				int facultyKnowSpace = resultSet.getInt("knowledge_store_space");
				
				String spaceUom=resultSet.getString("space_uom");
				
				fbkld.setLibSize(facultyKnowSpace);
				fbkld.setSpaceUom(spaceUom);
				

				//System.out.println("Faculty know Store Space is : " + facultyKnowSpace);
				facultyknowStoreSpace = facultyKnowSpace;

			}
			// //System.out.println("The return value is:" + rDomain);

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
			
			   pstmt.close();
			   con.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return fbkld;

	}
	
	public boolean libDuplicate(FacilitatorBuildKnowLibDomain adomain) {

		//PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//String ksId = adomain.getKsId();
		String libName = adomain.getNewName();
		boolean result = false;

		try {
			con = DBTransaction.connect();
			String query = "select name_of_lib from knowledgestorelib_info where name_of_lib='"+libName+"'";

			pstmt = con.prepareStatement(query);
			//System.out.println("the duplicate query is :"+query);
			//preparedStatement.setString(1, userId);
			resultSet = pstmt.executeQuery();
			if(resultSet.next())
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
			 
			   pstmt.close();
			   con.close();
			  
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return result;
	}

}