package com.kds.KODE_DEV.services;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dbconnection.DBTransaction;

@SuppressWarnings("serial")
public class AdminDBackupKStoreServlet extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(AdminDBackupKStoreServlet.class);

	@Override
	public void run() throws Exception {
		PrintWriter pw = response.getWriter();
		// usual database connection part

		FileWriter filewriter;
		try {
			//DBTransaction transaction = new DBTransaction();
			Connection connection = DBTransaction.connect();

			Statement statement = connection.createStatement();

			ResultSet resultset = statement
					.executeQuery("select * from knowledgestore_info");

			LOGGER.info("the query is:" + resultset);
			int colunmCount = getColumnCount(resultset);

			try {
				// String users_info = null;
				filewriter = new FileWriter("/home/paramvir/Adminsample.csv/");

				// this loop is used to add column names at the top of file , if
				// you do not need it just comment this loop
				for (int i = 1; i <= colunmCount; i++) {
					filewriter.append(resultset.getMetaData().getColumnName(i));
					filewriter.append(",");
					LOGGER.info("the column value is:" + i);

				}

				filewriter.append(System.getProperty("line.separator"));

				while (resultset.next()) {
					for (int i = 1; i <= colunmCount; i++) {

						// you can update it here by using the column type but i
						// am fine with the data so just converting
						// everything to string first and then saving
						if (resultset.getObject(i) != null) {
							String data = resultset.getObject(i).toString();
							LOGGER.info("the string value is:" + data);
							filewriter.append(data);
							filewriter.append(",");
						} else {
							String data = "null";
							filewriter.append(data);
							filewriter.append(",");
						}

					}
					// new line entered after each row
					filewriter.append(System.getProperty("line.separator"));
				}

				filewriter.flush();
				filewriter.close();

			} catch (IOException e) {

				e.printStackTrace();
			}

			//connection.close();

		}

		catch (ClassNotFoundException e) {
			System.err.println("Could not load JDBC driver");
			e.printStackTrace();

		} catch (SQLException ex) {
			ex.printStackTrace();

			System.err.println("SQLException information");
		} /*catch (IllegalAccessException e1) {

			e1.printStackTrace();
		} catch (InstantiationException e1) {

			e1.printStackTrace();
		}
*/
		pw.println("data is stored in home folder sample file");

	}

	// to get numbers of rows in a result set
	public static int getRowCount(ResultSet resultset1) throws SQLException {
		resultset1.last();
		int numberOfRows = resultset1.getRow();
		resultset1.beforeFirst();
		return numberOfRows;
	}

	// to get no of columns in result set
	public static int getColumnCount(ResultSet res) throws SQLException {
		return res.getMetaData().getColumnCount();
	}
}