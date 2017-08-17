package Automation;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;


import org.testng.annotations.Test;

public class TestClass1 extends KeywordLibrary {

//@Test
	public void main() throws Exception {

		ReadExcel obj5 = new ReadExcel(Constants.Test_Case_sheet);
		ReadObject object = new ReadObject();

		

		Properties allobjects = object.getObjectRepository();

		System.out.println(allobjects);

		HashMap<Integer, List<String>> map3 = obj5.readExcel(
				Constants.Test_Case_sheet, Constants.TEST_STEP_SHEET1);

		for (int i = 2; i < (map3.size() + 2); i++) {
			List<String> templist = map3.get(i);

			// System.out.println("test2" + templist.get(0));
			logger = report.startTest(templist.get(1));

			System.out.println(templist);

			KeywordLibrary.callMethods(allobjects, templist.get(0).trim(),
					templist.get(1).trim(), templist.get(2).trim(), templist
					.get(3).trim(), templist.get(4).trim(), templist.get(5).trim());
			// obj5.writeExcel(i ,KeywordLibrary.result);

			report.endTest(logger);
			report.flush();
		}

		driver.get(System.getProperty("user.dir") + "test-output\\report.html");
		


	}
	
	
	}
	


