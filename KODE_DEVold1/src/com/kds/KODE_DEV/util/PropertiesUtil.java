package com.kds.KODE_DEV.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	public static String getProperty(String key) {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			String filename = "utilities.properties";
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

	/*public static void main(String[] args) {
		String value = PropertiesUtil.getProperty("database");
		System.out.println(value);
	}*/
}
