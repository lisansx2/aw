package cn.com.cslc.aw.tools.core.common.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

	public static Properties loadProps(String filePath) {
		Properties properties = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			properties.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * 读取配置文件
	 * 
	 * @param props
	 *            配置文件
	 * @param key
	 * @return
	 */
	public static String getString(Properties properties, String key) {
		return properties.getProperty(key);
	}

	public static void updateProperty(Properties properties, String filePath, String keyname, String keyvalue) {
		try {
			properties.setProperty(keyname, keyvalue);
			FileOutputStream outputFile = new FileOutputStream(filePath);
			properties.store(outputFile, null);
			outputFile.flush();
			outputFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}