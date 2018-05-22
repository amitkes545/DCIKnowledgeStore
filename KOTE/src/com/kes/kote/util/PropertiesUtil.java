package com.kes.kote.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	public static String getDBProperty(String key) {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			String filename = "dbconfig.properties";
			input = PropertiesUtil.class.getClassLoader().getResourceAsStream(
					filename);
			//System.out.println(input);
			if (input == null) {
				return null;
			}
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop.getProperty(key);

	}

	public static String getMessageProperty(String key) {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			String filename = "messages.properties";
			input = PropertiesUtil.class.getClassLoader().getResourceAsStream(
					filename);
			//System.out.println(input);
			if (input == null) {
				return null;
			}
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop.getProperty(key);

	}

	public static String getMailProperty(String key) {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			String filename = "maildetails.properties";
			input = PropertiesUtil.class.getClassLoader().getResourceAsStream(
					filename);
			//System.out.println(input);
			if (input == null) {
				return null;
			}
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop.getProperty(key);

	}
	
	public static String getExcelSheetProperty(String key) {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			String filename = "excelsheet.properties";
			input = PropertiesUtil.class.getClassLoader().getResourceAsStream(
					filename);
			//System.out.println(input);
			if (input == null) {
				return null;
			}
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop.getProperty(key);

	}
	
	public static String getImageProcessingProperty(String key) {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			String filename = "imageprocessing.properties";
			input = PropertiesUtil.class.getClassLoader().getResourceAsStream(
					filename);
			//System.out.println(input);
			if (input == null) {
				return null;
			}
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop.getProperty(key);

	}
}
