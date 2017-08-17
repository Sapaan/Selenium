package Automation;

import static Automation1.TestClass.OR;

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
import org.openqa.selenium.Keys;
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

import Automation1.Utilities;

public class KeywordLibrary {

	static WebDriver driver;

	static String parentwin;
	static String result;
	static String childWindow;
	static boolean test;
	ExtentReports report = new ExtentReports(System.getProperty("user.dir") + "test-output\\report.html");
	static ExtentTest logger;

	public static void callMethods(Properties p, String TCname, String TCDesc, String Action, String ObjectType,
			String Object, String Data) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		Method methods = KeywordLibrary.class.getMethod(Action, Properties.class, String.class, String.class,
				String.class, String.class, String.class);

		methods.invoke(Action, p, TCname, TCDesc, ObjectType, Object, Data);
	}

	public static WebDriver openBrowser(Properties p, String TCname, String TCDesc, String ObjectType, String Object,
			String Data) throws Exception {

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
				driver.manage().window().maximize();
				break;

			}

			driver.get(p.getProperty(Object));

			logger.log(LogStatus.PASS, TCDesc, "is passed");

			result = "Pass";
		}

		catch (Exception e) {

			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
			String image = logger.addScreenCapture(Snapshot);

			logger.log(LogStatus.FAIL, "Unable to launch the app", image);
			System.out.println("Exact Error -->" + e);

			result = "Fail";

		}

		return driver;

	}

	public static void enterText(Properties p, String TCname, String TCDesc, String ObjectType, String Object,
			String Data) throws Exception {
		try {

			switch (ObjectType) {
			case "id":
				driver.findElement(By.id(p.getProperty(Object))).sendKeys(Data);
				break;
			case "name":
				driver.findElement(By.name(p.getProperty(Object))).sendKeys(Data);
				break;
			case "xpath":
				driver.findElement(By.xpath(p.getProperty(Object))).sendKeys(Data);
				break;
			}

			result = "Pass";
			logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");

		} catch (Exception e) {

			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
			String image = logger.addScreenCapture(Snapshot);

			logger.log(LogStatus.FAIL, "Unable to locate the element -->" + "" + Object, image);
			System.out.println(e);

			result = "Fail";
		}
	}

	
	public static void checkbox(Properties p, String TCname, String TCDesc, String ObjectType, String Object,
			String Data) throws Exception {
		try {

			switch (ObjectType) {
			case "id":
				driver.findElement(By.id(p.getProperty(Object))).sendKeys(Keys.SPACE);
				
				break;
			case "name":
				driver.findElement(By.name(p.getProperty(Object))).sendKeys(Keys.SPACE);
				break;
			case "xpath":
				
				driver.findElement(By.xpath(p.getProperty(Object))).sendKeys(Keys.SPACE);
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
	
	public static void buttonClick(Properties p, String TCname, String TCDesc, String ObjectType, String Object,
			String Data) throws Exception {
		try {

			switch (ObjectType) {
			case "id":
				driver.findElement(By.id(p.getProperty(Object))).click();
				break;
			case "name":
				driver.findElement(By.name(p.getProperty(Object))).click();
				break;
			case "xpath":
				driver.findElement(By.xpath(p.getProperty(Object))).click();
				break;
			}

			result = "Pass";
			logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");
		}

		catch (Exception e) {

			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
			String image = logger.addScreenCapture(Snapshot);

			logger.log(LogStatus.FAIL, "Unable to locate the element -->" + "" + Object, image);
			System.out.println(e);
			result = "Fail";

		}

	}

	public static void buttonClick1(Properties p, String TCname, String TCDesc, String ObjectType, String Object,
			String Data) throws Exception {
		try {

			switch (ObjectType) {
			case "id":
				driver.findElement(By.id(p.getProperty(Object))).click();
				break;
			case "name":
				driver.findElement(By.name(p.getProperty(Object))).click();
				break;
			case "xpath":
				driver.findElement(By.xpath(p.getProperty(Object))).click();
				break;
			}

			result = "Pass";
			logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");
		}

		catch (Exception e) {

			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
			String image = logger.addScreenCapture(Snapshot);

			logger.log(LogStatus.FAIL, "Unable to locate the element -->" + "" + Object, image);
			System.out.println(e);

			driver.close();
			result = "Fail";

		}

	}

	public static void radioClick(Properties p, String TCname, String TCDesc, String ObjectType, String Object,
			String Data) throws Exception {
		try {

			switch (ObjectType) {
			case "id":
				driver.findElement(By.id(p.getProperty(Object))).click();
				break;
			case "name":
				driver.findElement(By.name(p.getProperty(Object))).click();
				break;
			case "xpath":
				driver.findElement(By.xpath(p.getProperty(Object))).click();
				break;
			}

			result = "Pass";
			logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");
		}

		catch (Exception e) {

			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
			String image = logger.addScreenCapture(Snapshot);

			logger.log(LogStatus.FAIL, "Unable to locate the element -->" + "" + Object, image);
			System.out.println(e);
			driver.close();

			driver.switchTo().window(parentwin);

			result = "Fail";

		}
	}

	public static void validation(Properties p, String TCname, String TCDesc, String ObjectType, String Object,
			String Data) throws Exception {
		try {

			switch (ObjectType) {
			case "id":
				driver.findElement(By.id(p.getProperty(Object))).click();
				break;
			case "name":
				driver.findElement(By.name(p.getProperty(Object))).click();
				break;
			case "xpath":
				driver.findElement(By.xpath(p.getProperty(Object))).click();
				break;
			}

			result = "Pass";
			logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");
		}

		catch (Exception e) {

			BufferedImage image = new Robot()
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(image, "png", new File("C:\\Selenium Automation\\Screenshots\\bla.png"));

			// String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
			String image1 = logger.addScreenCapture(image.toString());

			logger.log(LogStatus.FAIL, "Unable to locate the element -->" + "" + Object, image1);
			System.out.println(e);

			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			driver.switchTo().defaultContent();

			result = "Fail";

		}

	}

	public static void linkClick(Properties p, String TCname, String TCDesc, String ObjectType, String Object,
			String Data) throws Exception

	{
		try {

			switch (ObjectType) {
			case "id":
				driver.findElement(By.id(p.getProperty(Object))).click();
				break;
			case "name":
				driver.findElement(By.name(p.getProperty(Object))).click();
				break;
			case "xpath":
				driver.findElement(By.xpath(p.getProperty(Object))).click();
				break;
			case "linkText":
				driver.findElement(By.linkText(p.getProperty(Object))).click();
				break;
			case "partiallinkText":
				driver.findElement(By.partialLinkText(p.getProperty(Object))).click();
				break;
			}

			result = "Pass";
			logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");
		}

		catch (Exception e) {

			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
			String image = logger.addScreenCapture(Snapshot);

			logger.log(LogStatus.FAIL, "Unable to locate the element -->" + "" + Object, image);
			System.out.println(e);
			result = "Fail";
		}

	}

	public static void dropDown(Properties p, String TCname, String TCDesc, String ObjectType, String Object,
			String Data) throws Exception

	{
		try {

			switch (ObjectType) {
			case "id":
				Select dropdown1 = new Select(driver.findElement(By.id(p.getProperty(Object))));
				dropdown1.selectByValue(Data);
				break;
			case "name":
				Select dropdown2 = new Select(driver.findElement(By.name(p.getProperty(Object))));
				dropdown2.selectByValue(Data);
				break;
			case "xpath":
				Select dropdown3 = new Select(driver.findElement(By.xpath(p.getProperty(Object))));
				dropdown3.selectByValue(Data);

				break;

			}

			result = "Pass";
			logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");
		}

		catch (Exception e) {
			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
			String image = logger.addScreenCapture(Snapshot);

			logger.log(LogStatus.FAIL, "Unable to locate the element -->" + "" + Object, image);
			System.out.println(e);

			result = "Fail";
		}

	}

	public static void windowHandles(Properties p, String TCname, String TCDesc, String ObjectType, String Object,
			String Data) throws Exception

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

			logger.log(LogStatus.FAIL, "Unable to get the window", image);
			System.out.println(e);
			result = "Fail";
		}

	}

	public static void closeWindow(Properties p, String TCname, String TCDesc, String ObjectType, String Object,
			String Data) throws Exception {

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

	public static void switchingParentWindow(Properties p, String TCname, String TCDesc, String ObjectType,
			String Object, String Data) throws Exception {

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

	public static void wait(Properties p, String TCname, String TCDesc, String ObjectType, String Object, String Data)
			throws Exception

	{
		try {
			Thread.sleep(2000);

			logger.log(LogStatus.PASS, TCDesc + "-->" + "is passed");

			result = "Pass";
		}

		catch (Exception e) {

			String Snapshot = Utilities.capturingScreenshot(driver, TCDesc);
			String image = logger.addScreenCapture(Snapshot);

			logger.log(LogStatus.FAIL, "Timeout error", image);

			result = "Fail";

		}

	}

	public static void switchingIframe(Properties p, String TCname, String TCDesc, String ObjectType, String Object,
			String Data) throws Exception

	{
		try {

			driver.switchTo().frame(p.getProperty(Object));

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

	public static void keybord(Properties p, String TCname, String TCDesc, String ObjectType, String Object,
			String Data) throws Exception {

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

	public static void saveDoc(Properties p, String TCname, String TCDesc, String ObjectType, String Object,
			String Data) throws Exception {

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
	


	public static void invalidcreds(Properties p, String TCname, String TCDesc, String ObjectType, String Object,
			String Data) throws Exception

	{
		try {

			switch (ObjectType) {
			case "id":
				driver.findElement(By.id(p.getProperty(Object)));
				break;
			case "name":
				driver.findElement(By.name(p.getProperty(Object)));
				break;
			case "xpath":
				driver.findElement(By.xpath(p.getProperty(Object)));
				break;
			}

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
	
	
}



