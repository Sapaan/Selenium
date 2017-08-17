package Automation1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utilities {

	public static String capturingScreenshot(WebDriver driver,
			String screenshotname) throws Exception

	{
try{
		TakesScreenshot sc = (TakesScreenshot) driver;
		File source = sc.getScreenshotAs(OutputType.FILE);

		String dest = "C:\\Selenium Automation\\Screenshots\\" + screenshotname
				+ ".png";

		File destination = new File(dest);

		FileUtils.copyFile(source, destination);

		System.out.println("Screenshot taken");

		return dest;
}

catch(Exception e){
	
	System.out.println("screnshot error" + e.getMessage());
}
return "" ;

	}

	public static String timestamp() {

		return new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date());
	}
}
	
