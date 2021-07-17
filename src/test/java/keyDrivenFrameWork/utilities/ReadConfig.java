package keyDrivenFrameWork.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties prop;

	public ReadConfig() {
		File src = new File("./Configuration/config.properties");

		try {
			FileInputStream fStream=new FileInputStream(src);
			prop = new Properties();
			prop.load(fStream);

		} catch (Exception e) {
			System.out.println("Exception is:"+e.getMessage());
		}

	}

	public String getURL() {

		String url= prop.getProperty("baseURL");
		return url;

	}
	public String username() {

		String username=prop.getProperty("username");
		return username;
	}
	public String password() {

		String password=prop.getProperty("password");
		return password;
	}
	public String chromepath() {

		String chromepath=prop.getProperty("chromepath");
		return chromepath;
	}
	public String firefoxpath() {

		String firefoxpath=prop.getProperty("firefoxpath");
		return firefoxpath;
	}

}
