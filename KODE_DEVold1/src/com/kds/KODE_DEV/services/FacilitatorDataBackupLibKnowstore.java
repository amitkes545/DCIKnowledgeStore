package com.kds.KODE_DEV.services;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dbconnection.DBTransaction;

@SuppressWarnings("serial")
public class FacilitatorDataBackupLibKnowstore extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(FacilitatorDataBackupLibKnowstore.class);

	@SuppressWarnings("unused")
	@Override
	public void run() throws ServletException, IOException,
			FileUploadException, Exception {

		String UPLOAD_DIRECTORY = "";
		PrintWriter pw = response.getWriter();
		// usual database connection part
		LOGGER.info("database connection");

		FileWriter fw;
		String s = System.getProperty("os.name");
		LOGGER.info("os name= " + s);
		String[] win = s.split(" ");
		String winname = win[0];
		LOGGER.info("windows name= " + winname);
		String download_path = System.getProperty("user.home") + "/Downloads";
		String uploadPath;
		try {
			
			Connection connection = DBTransaction.connect();
			Statement st = connection.createStatement();
			LOGGER.info("connection is " + connection);
            String userId = (String)session.getAttribute("userid");
			ResultSet resultSet = st.executeQuery("select * from knowledgestorelib_info where user_id='"+session.getAttribute(userId)+"'");
			LOGGER.info("the quary is:" + resultSet);
			int colunmCount = getColumnCount(resultSet);
			download_path = System.getProperty("user.home") + "/Downloads";
			try {
				// String users_info = null;
				// fw = new FileWriter("/home/paramvir/sample.csv");
				// String s =System.getProperty ("os.name");
				LOGGER.info("os name= " + s);
				win = s.split(" ");
				winname = win[0];
				LOGGER.info("windows name= " + winname);

				if (winname.equalsIgnoreCase("Windows")) {
					UPLOAD_DIRECTORY = download_path;
					uploadPath = "/" + download_path;
					LOGGER.info("upload path:" + uploadPath);
				} else {
					UPLOAD_DIRECTORY = "/home";
					uploadPath = "/" + download_path;
					LOGGER.info("uploadpath in linux:" + uploadPath);
				}

				fw = new FileWriter(uploadPath + "/sample.csv");
				// this loop is used to add column names at the top of file , if
				// you do not need it just comment this loop
				for (int i = 1; i <= colunmCount; i++) {
					fw.append(resultSet.getMetaData().getColumnName(i));
					fw.append(",");
					// LOGGER.info("the column value is:"+i);

				}

				fw.append(System.getProperty("line.separator"));

				while (resultSet.next()) {
					for (int i = 1; i <= colunmCount; i++) {

						// you can update it here by using the column type but i
						// am fine with the data so just converting
						// everything to string first and then saving
						if (resultSet.getObject(i) != null) {
							String data = resultSet.getObject(i).toString();
							// LOGGER.info("the string value is:"+data);
							fw.append(data);
							fw.append(",");
						} else {
							String data = "null";
							fw.append(data);
							fw.append(",");
						}

					}
					// new line entered after each row
					fw.append(System.getProperty("line.separator"));
				}

				fw.flush();
				fw.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
			//con.close();
		} catch (ClassNotFoundException e) {
			System.err.println("Could not load JDBC driver");
			e.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.err.println("SQLException information");
		}/* catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		}*/

		if (winname.equalsIgnoreCase("Windows")) {
			UPLOAD_DIRECTORY = download_path;
			uploadPath = "/" + download_path;
			LOGGER.info("upload path:" + uploadPath);
			String dataStatus = "Data is Stored in" + uploadPath +"folder";
			request.setAttribute("FacultySuccess", dataStatus);
			RequestDispatcher rd = request
					.getRequestDispatcher("../JSP/Home.jsp");
			rd.forward(request, response);
		} else {
			UPLOAD_DIRECTORY = "/home";
			uploadPath = "/" + download_path;
			LOGGER.info("uploadpath in linux:" + uploadPath);
			String dataStatus = "Data is Stored in" + uploadPath + " "
					+ "folder";
			request.setAttribute("FacultySuccess", dataStatus);
			RequestDispatcher rd = request
					.getRequestDispatcher("../JSP/Home.jsp");
			rd.forward(request, response);
		}
	}

	// to get numbers of rows in a result set
	public static int getRowCount(ResultSet res) throws SQLException {
		res.last();
		int numberOfRows = res.getRow();
		res.beforeFirst();
		return numberOfRows;
	}

	// to get no of columns in result set

	public static int getColumnCount(ResultSet res) throws SQLException {
		return res.getMetaData().getColumnCount();
	}
}