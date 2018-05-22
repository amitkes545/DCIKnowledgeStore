package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.LoginDao;
import com.kds.KODE_DEV.dbconnection.DBTransaction;

public class SaveTimeForTimer extends CommonService {
	
	private static final long serialVersionUID = 1L;
	Connection con = null;
	PreparedStatement ps = null;
public void run() throws ServletException, IOException {
	session=request.getSession();
	String userid=request.getParameter("userid");
	String sessionId=request.getParameter("sessionid");
	LoginDao ldo=new LoginDao();
	int n=ldo.savedata(userid,sessionId);
	System.out.println("dta inserted "+n);
	
	}
}