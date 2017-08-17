package Automation;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadObject {

	Properties p = new Properties();

	public Properties getObjectRepository() throws IOException

	{

		try {
			String ORpath = System.getProperty("user.dir")
					+ "\\src/main/resources\\Config\\ObjectRepository.properties";
			InputStream stream = new FileInputStream(ORpath);
			p.load(stream);

			return p;

		}

		catch (Exception e) {
			System.out.println("Message- >" + e.getMessage());
		}
		return p;

	}
}
