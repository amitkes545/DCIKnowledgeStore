package com.kds.KODE_DEV.dbconnection;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;

public class FTPConnection {

	public static FTPClient getConnection() {
		Properties props = new Properties();
		FileInputStream fis = null;
		FTPClient ftpConnection = null;
		try {
			fis = new FileInputStream("ftpconnection.properties");
			props.load(fis);

			// get an ftpClient object
			FTPClient ftpClient = new FTPClient();
			// pass directory path on server to connect
			ftpClient.connect("host");

			// pass username and password, returned true if authentication is
			// successful
			boolean login = ftpClient.login(props.getProperty("user"),
					props.getProperty("password"));

			if (login) {
				//System.out.println("Connection established...");
				//System.out.println("Status: " + ftpClient.getStatus());

				// logout the user, returned true if logout successfully
				boolean logout = ftpClient.logout();
				if (logout) {
					//System.out.println("Connection close...");
				}
				ftpClient.disconnect();
				
			} else {
				//System.out.println("Connection fail...");
			}
		} catch (Exception e) {
			//System.out.println(e);

		}
		
		return ftpConnection;
	}

	public FTPClient connect() {
		
		return null;
	}
}