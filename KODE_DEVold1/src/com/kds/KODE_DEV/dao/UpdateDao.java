package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.CreateDomain;

/**
 * Servlet implementation class UpdateDao
 */
//@WebServlet("/UpdateDao")
public class UpdateDao   {
	Connection con = null;
	PreparedStatement ps = null;
	public String updateValues(CreateDomain cDomain) {

		//DBTransaction dbT = new DBTransaction();
		// boolean ret = false;
		
		String status=null;

		try {
                    con = DBTransaction.connect();
					// PreparedStatement ps = null;
					// ResultSet rs = null;
					String sql ="update org_details set " +"organization_name='"+cDomain.getOrg_name()+"'"+","+"org_type='"+cDomain.getOrg_type()+"'"+","+"address='"+cDomain.getAddress()+"'"+","+"city='"+cDomain.getCity()+"'"+","+"country='"
					+cDomain.getCountry()+"'"+","+"postal_code='"+cDomain.getPcode()+"'"+","+"telephone='"+cDomain.getPhone()+"'"+","+"fax='"
					+cDomain.getFax()+"'"+","+"emergency_contact_no='"+cDomain.getEcno()+"'"+","+"email='"+cDomain.getEmail_id()+"'"+","+"url='"+cDomain.getUrl()+"'"+","+"year_of_foundation='"+cDomain.getYof()+"'"+","+"belongs='"+cDomain.getBelongs()
				    		+"'"+","+"Date_time='"+cDomain.getDate()+"'"+ "where organization_id='"+cDomain.getOrg_id()+"'";
					ps = con.prepareStatement(sql);
					
					//System.out.println("the quary is:"+sql);
					int i = ps.executeUpdate();
					//System.out.println(i+"row is updated successfully");
					
					if (i==1)
						status = "success";
					else
						status = "failure";
				}catch(Exception e){
					e.printStackTrace();
				}
		finally
		   {
			   try{
				   con.close();
				   ps.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
				return status;
			}
		

	}

