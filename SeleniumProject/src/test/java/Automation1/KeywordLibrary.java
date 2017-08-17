package Automation1;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Set;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import static Automation1.TestClass.OR;
import static Automation1.TestClass.Config;

public class KeywordLibrary {

	static WebDriver driver;
	static String parentwin;
	static String result;
	static String childWindow;
	static boolean test;

	ExtentReports report = new ExtentReports(System.getProperty("user.dir") + "test-output\\report.html");
	
	static ExtentTest logger;
	

	public static WebDriver openBrowser(String TCDesc, String ObjectType, String Object, String data) throws Exception {

		try {

			switch (ObjectType) {

			case "Chrome":

				System.setProperty("webdriver.chrome.driver", "C:\\2.26\\chromedriver_win32\\chromedriver.exe");
				driver = new ChromeDriver();
				break;

			case "IE":

				System.setProperty("webdriver.ie.driver", "C:\\Users\\u1086963\\Desktop\\Jars\\IEDriverServer.exe");

				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				driver = new InternetExplorerDriver(caps);
				break;

			}

			driver.get(data);
			driver.manage().window().maximize();

			logger.log(LogStatus.PASS, TCDesc, "is passed");

			
		}

		catch (Exception e) {

			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
			String image = logger.addScreenCapture(Snapshot);

			logger.log(LogStatus.FAIL, "Unable to launch the app", image);
			System.out.println("Exact Error -->" + e);

			result = "Fail";
			
		driver.close();

		}

		return driver;

	}

	public static void enterText(String TCDesc, String ObjectType, String Object, String data) throws Exception {
		try {

			switch (ObjectType) {
			case "id":
		WebElement 	ele=	driver.findElement(By.id(OR.getProperty(Object)));
		ele.clear();
		ele.sendKeys(data);
				break;
			case "name":
				
				WebElement 	ele1=	driver.findElement(By.name(OR.getProperty(Object)));
				ele1.clear();
				ele1.sendKeys(data);
				break;
			case "xpath":
			
				WebElement 	ele2=	driver.findElement(By.xpath(OR.getProperty(Object)));
				ele2.clear();
				ele2.sendKeys(data);
				break;
			}

			result = "Pass";
			logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");

		} catch (Exception e) {

			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
			String image = logger.addScreenCapture(Snapshot);

			logger.log(LogStatus.FAIL, "Unable to locate the element -->" + "" + Object, image);
			System.out.println("Error " + e.getMessage());

			result = "Fail";
		}
	}
	
	

	public static void buttonClick(String TCDesc, String ObjectType, String Object, String data) throws Exception {
		try {

			switch (ObjectType) {
			case "id":
				driver.findElement(By.id(OR.getProperty(Object))).click();
				break;
			case "name":
				driver.findElement(By.name(OR.getProperty(Object))).click();
				break;
			case "xpath":
				driver.findElement(By.xpath(OR.getProperty(Object))).click();
				break;
			}

			
			logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");
		}

		catch (Exception e) {
			
			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
			 String image = logger.addScreenCapture(Snapshot);

			 logger.log(LogStatus.FAIL, "Unable to locate the element -->" +
			 "" + Object, image);
			System.out.println(e);
			result = "Fail";

		}
		
	}
		
		/*public static void login(String TCDesc, String ObjectType, String Object, String data) throws Exception {
			try {

				switch (ObjectType) {
				case "id":
					driver.findElement(By.id(OR.getProperty(Object))).click();
					break;
				case "name":
					driver.findElement(By.name(OR.getProperty(Object))).click();
					break;
				case "xpath":
					driver.findElement(By.xpath(OR.getProperty(Object))).click();
					break;
				}

				
				//logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");
			}

			catch (Exception e) {
				
				String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
				 String image = logger.addScreenCapture(Snapshot);

				 logger.log(LogStatus.FAIL, "Unable to locate the element -->" +
				 "" + Object, image);
				System.out.println(e);
				result = "Fail";

			}
			
			finally{
				
				WebElement  error= driver.findElement(By.xpath("//*[@id='errorMsgDiv']/strong"));
				String text= error.getText();
				
				if(text.equals("You have entered an invalid user name or password. Please try again."))
				{
					System.out.println("invalid creds");
					logger.log(LogStatus.WARNING, "invalid crds");
				}
				
				
				else
				{
					
					System.out.println(" login success");
					
					 logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");
				}
			}
			*/

	

	
	public static void checkbox(String TCDesc, String ObjectType, String Object, String data) throws Exception {
		try {

			switch (ObjectType) {
			case "id":
			driver.findElement(By.id(OR.getProperty(Object))).click();
				
				
				break;
			case "name":
				driver.findElement(By.name(OR.getProperty(Object))).click();


				break;
			case "xpath":
				
			 driver.findElement(By.xpath(OR.getProperty(Object))).click();


				break;
			}
			
			
			result = "Pass";
			 logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");
		}

		catch (Exception e) {

			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
			 String image = logger.addScreenCapture(Snapshot);

			 logger.log(LogStatus.FAIL, "Unable to locate the element -->" +
			 "" + Object, image);
			System.out.println(e);
			result = "Fail";

		}

	}
	
	
	public static void buttonClick1(String TCDesc, String ObjectType, String Object, String data) throws Exception {
		try {

			switch (ObjectType) {
			case "id":
				driver.findElement(By.id(OR.getProperty(Object))).click();
				break;
			case "name":
				driver.findElement(By.name(OR.getProperty(Object))).click();
				break;
			case "xpath":
				driver.findElement(By.xpath(OR.getProperty(Object))).click();
				break;
			}

			result = "Pass";
			logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");
		}

		catch (Exception e) {

			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
			String image = logger.addScreenCapture(Snapshot);

	 logger.log(LogStatus.FAIL, "Unable to locate the element -->" +
		 "" + Object, image);
			System.out.println(e);

			driver.close();
			result = "Fail";

		}

	}

	public static void radioClick(String TCDesc, String ObjectType, String Object, String data) throws Exception {
		try {

			switch (ObjectType) {
			case "id":
				driver.findElement(By.id(OR.getProperty(Object))).click();
				break;
			case "name":
				driver.findElement(By.name(OR.getProperty(Object))).click();
				break;
			case "xpath":
				driver.findElement(By.xpath(OR.getProperty(Object))).click();
				break;
			}

			result = "Pass";
		logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");
		}

		catch (Exception e) {

			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
			 String image = logger.addScreenCapture(Snapshot);

		logger.log(LogStatus.FAIL, "Unable to locate the element -->" +
		"" + Object, image);
			System.out.println(e);
			driver.close();

			driver.switchTo().window(parentwin);

			result = "Fail";

		}
	}

	public static void validation(String TCDesc, String ObjectType, String Object, String data) throws Exception {
		try {

			switch (ObjectType) {
			case "id":
				driver.findElement(By.id(OR.getProperty(Object))).click();
				break;
			case "name":
				driver.findElement(By.name(OR.getProperty(Object))).click();
				break;
			case "xpath":
				driver.findElement(By.xpath(OR.getProperty(Object))).click();
				break;
			}

			result = "Pass";
		 logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");
		}

		catch (Exception e) {

			BufferedImage image = new Robot().createScreenCapture(new
			Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			 ImageIO.write(image, "png", new File("C:\\Selenium Automation\\Screenshots\\"  
						+ "bla.png"));

		String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
		 String image1 = logger.addScreenCapture(image.toString());

			logger.log(LogStatus.FAIL, "Unable to locate the element -->" +
			 "" + Object, image1);
			System.out.println(e);

			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			driver.switchTo().defaultContent();
			result = "Fail";
		}

	}

	public static void linkClick(String TCDesc, String ObjectType, String Object, String data) throws Exception

	{
		try {

			switch (ObjectType) {
			case "id":
				driver.findElement(By.id(OR.getProperty(Object))).click();
				break;
			case "name":
				driver.findElement(By.name(OR.getProperty(Object))).click();
				break;
			case "xpath":
				driver.findElement(By.xpath(OR.getProperty(Object))).click();
				break;
			case "linkText":
				driver.findElement(By.linkText(OR.getProperty(Object))).click();
				break;
			case "partiallinkText":
				driver.findElement(By.partialLinkText(OR.getProperty(Object))).click();
				break;
			}

			result = "Pass";
	logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");
		}

		catch (Exception e) {

			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
			 String image = logger.addScreenCapture(Snapshot);

		logger.log(LogStatus.FAIL, "Unable to locate the element -->" +
			 "" + Object, image);
			System.out.println(e);
			result = "Fail";
		}

	}

	public static void dropDown(String TCDesc, String ObjectType, String Object, String data) throws Exception

	{
		try {

			switch (ObjectType) {
			case "id":
				Select dropdown1 = new Select(driver.findElement(By.id(OR.getProperty(Object))));
				dropdown1.selectByValue(data);
				break;
			case "name":
				Select dropdown2 = new Select(driver.findElement(By.name(OR.getProperty(Object))));
				dropdown2.selectByValue(data);
				break;
			case "xpath":
				Select dropdown3 = new Select(driver.findElement(By.xpath(OR.getProperty(Object))));
				dropdown3.selectByValue(data);
				break;

			}

			result = "Pass";
		 logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");
		}

		catch (Exception e) {
			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
			String image = logger.addScreenCapture(Snapshot);
		logger.log(LogStatus.FAIL, "Unable to locate the element -->" +
		 "" + Object, image);
			System.out.println(e);
			result = "Fail";
		}

	}

	public static void windowHandles(String TCDesc, String ObjectType, String Object, String data) throws Exception

	{
		try {

			parentwin = driver.getWindowHandle();

			Set<String> Allwin = driver.getWindowHandles();

			System.out.println("All handle" + Allwin);

			for (String temp : Allwin)

			{
				if (!temp.equals(parentwin)) {
					driver.switchTo().window(temp);
					System.out.println(temp);

				}
			}

			result = "Pass";
		 logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");
		}

		catch (Exception e) {
			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
		 String image = logger.addScreenCapture(Snapshot);

		logger.log(LogStatus.FAIL, "Unable to get the window" , image);
			System.out.println(e);
			result = "Fail";
		}

	}

	public static void closeWindow(String TCDesc, String ObjectType, String Object, String Data) throws Exception {

		try {
			driver.close();
			result = "Pass";
		logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");
		}

		catch (Exception e) {
			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
		 String image = logger.addScreenCapture(Snapshot);

			String error = e.getMessage();

			String exacterror = error.split("(")[0];

		 logger.log(LogStatus.FAIL, "Window not found", image);
			System.out.println(exacterror);
			result = "Fail";
		}

	}

	public static void switchingParentWindow(String TCDesc, String ObjectType, String Object, String data)
			throws Exception {

		try {
			driver.switchTo().window(parentwin);

			result = "Pass";
		 logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");
		}

		catch (Exception e) {
			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
		 String image = logger.addScreenCapture(Snapshot);

		logger.log(LogStatus.FAIL, "Unable to switch to window", image);
			System.out.println(e);
			result = "Fail";
		}

	}

	public static void pause(String TCDesc, String ObjectType, String Object, String data) throws Exception

	{
		try {

		//long time=(long)Double.parseDouble(Object);
			Thread.sleep(3000);

			System.out.println("wait is working");

			logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");

			result = "Pass";
		}

		catch (Exception e) {
			
	
			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
			 String image = logger.addScreenCapture(Snapshot);

		logger.log(LogStatus.FAIL, "Timeout error", image);
		System.out.println(e);

			result = "Fail";

		}

	}

	public static void switchingIframe(String TCDesc, String ObjectType, String Object, String data) throws Exception

	{
		try {

			driver.switchTo().frame(OR.getProperty(Object));

			result = "Pass";
		logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");

		}

		catch (Exception e) {

			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
			String image = logger.addScreenCapture(Snapshot);

			logger.log(LogStatus.FAIL, "Unable to switch to frame", image);
			System.out.println(e);

			result = "Fail";

		}

	}

	public static void keybord(String TCDesc, String ObjectType, String Object, String data) throws Exception {

		try {

			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_ENTER);

			robot.keyRelease(KeyEvent.VK_ENTER);

			result = "Pass";
		logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");
		}

		catch (Exception e) {
			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
		String image = logger.addScreenCapture(Snapshot);

			logger.log(LogStatus.FAIL, "Failed to press enter", image);
			System.out.println(e);
			result = "Fail";
		}

	}

	public static void saveDoc(String TCDesc, String ObjectType, String Object, String data) throws Exception {

		try {

			driver.switchTo().activeElement();
			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_ALT);

			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_S);

			robot.keyRelease(KeyEvent.VK_S);

			robot.keyRelease(KeyEvent.VK_ALT);

			System.out.println("Able to save the doc");
			result = "Pass";
			 logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");
		}

		catch (Exception e) {
			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
			String image = logger.addScreenCapture(Snapshot);

			logger.log(LogStatus.FAIL, "Unable to save the doc", image);
			System.out.println(e);

			System.out.println("Unable to save the doc");

			result = "Fail";
		}
	}
	
	public static void quit(String TCDesc, String ObjectType, String Object, String data) throws Exception {

		try {

			driver.quit();
			 logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");
		}

		catch (Exception e) {
			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
			String image = logger.addScreenCapture(Snapshot);

			logger.log(LogStatus.FAIL, "Unable to save the doc", image);
			System.out.println(e);

			
		}
	}

	
	public static void Verifytitlepresent(String TCDesc, String ObjectType, String Object, String data) throws Exception {

		try {
			
			String actual= driver.getTitle();
			
			System.out.println("Title of home page - >" + actual);
			String expected= OR.getProperty(data);
			if (actual.equals(expected))
				
            logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");
			
			else
					
	        logger.log(LogStatus.FAIL, TCDesc + "-->" + "is Failed");	
		}
		
		
		catch (Exception e) {
			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
			String image = logger.addScreenCapture(Snapshot);

			logger.log(LogStatus.FAIL, "Unable to verify the title", image);
			System.out.println(e);
		}
		
	}
	
	
	public static void Verifyelementpresent(String TCDesc, String ObjectType, String Object, String data) throws Exception {

		try {
			
			String actual= driver.findElement(By.xpath(OR.getProperty(Object))).getText();
			
			System.out.println("Link - >" + actual);
			String expected= OR.getProperty(data);
			if (actual.equals(expected))
				
            logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");
			
			else
					
	        logger.log(LogStatus.FAIL, TCDesc + "-->" + "is Failed");	
		}
		
		
		catch (Exception e) {
			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
			String image = logger.addScreenCapture(Snapshot);

			logger.log(LogStatus.FAIL, "Unable to verify the title", image);
			System.out.println(e);
		}
		
	}
	
	
	
	
	


}
