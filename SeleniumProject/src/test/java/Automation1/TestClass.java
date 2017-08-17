package Automation1;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import org.apache.poi.ss.usermodel.Sheet;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import org.apache.poi.ss.usermodel.Sheet;


public class TestClass extends KeywordLibrary {

	public ReadExcel suiteXLS;
	public ReadExcel currentTestSuiteXLS;

	public int currentSuiteID;
	public int currentTestCaseID;
	public String CurrentTestCaseName;
	public String currentTestSuite;
	public int CurrentTestStepID;
	public String currentKeyword;
	public int currentTestDataSetID;
	public KeywordLibrary keywords;
	public Method method[];
    public Object  keyword_Execution_Result;
    public ArrayList<String> resultSet;
    public static Properties Config;
    public static Properties OR;
    public String data;
    public String object;
    public String objectType;
    public String TCDesc;
    

    

	public TestClass( ) {
		keywords = new KeywordLibrary();
		method = keywords.getClass().getMethods();
	
	
	}

	
	@Test
	public void FinalExecution()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		FileInputStream fs= new FileInputStream(System.getProperty("user.dir")+ "\\src/main/resources\\Config\\MCAdminOR.properties");
		Config= new Properties();
		Config.load(fs);
		
		FileInputStream fs1= new FileInputStream(System.getProperty("user.dir")+ "\\src/main/resources\\Config\\ObjectRepository.properties");
		OR= new Properties();
		OR.load(fs1);
	//	System.out.println(OR.getProperty("url"));	
	TestClass test = new TestClass();
	test.start();
	}
	public void start() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// logger.log(LogStatus.INFO, "Initialize suite.xls");
		suiteXLS = new ReadExcel(System.getProperty("user.dir") + "\\src/main/resources//Excels//Suite.xls");
		for (currentSuiteID = 2; currentSuiteID <= suiteXLS.getRowCount(Constants.TEST_SUIT_SHEET); currentSuiteID++)
		{
			System.out.println(suiteXLS.getCellData(Constants.TEST_SUIT_SHEET, Constants.TEST_SUIT_ID, currentSuiteID)
					+ "----"
					+ suiteXLS.getCellData(Constants.TEST_SUIT_SHEET, Constants.TEST_SUIT_RUNMODE, currentSuiteID));
			// test suite name= test suite excel file
			currentTestSuite = suiteXLS.getCellData(Constants.TEST_SUIT_SHEET, Constants.TEST_SUIT_ID, currentSuiteID);

			if (suiteXLS.getCellData(Constants.TEST_SUIT_SHEET, Constants.TEST_SUIT_RUNMODE, currentSuiteID)
					.equals(Constants.RUNMODE_YES)) {
				// execute the test case in suite
				System.out.println("****Executing the suite*****"
						+ suiteXLS.getCellData(Constants.TEST_SUIT_SHEET, Constants.TEST_SUIT_ID, currentSuiteID));
				currentTestSuiteXLS = new ReadExcel(
						System.getProperty("user.dir") + "\\src/main/resources//Excels//" + currentTestSuite + ".xls");
				// iterate through all test cases in suite

				for (currentTestCaseID = 2; currentTestCaseID <= currentTestSuiteXLS
						.getRowCount(Constants.TEST_CASE_SHEET); currentTestCaseID++)

				{

					System.out.println(currentTestSuiteXLS.getCellData(Constants.TEST_CASE_SHEET,
							Constants.TEST_CASE_ID, currentTestCaseID) + "---"
							+ currentTestSuiteXLS.getCellData(Constants.TEST_CASE_SHEET, Constants.TEST_CASE_RUNMODE,
									currentTestCaseID));

					CurrentTestCaseName = currentTestSuiteXLS.getCellData(Constants.TEST_CASE_SHEET,
							Constants.TEST_CASE_ID, currentTestCaseID);
				//	resultSet= new ArrayList<String>();

					if (currentTestSuiteXLS
							.getCellData(Constants.TEST_CASE_SHEET, Constants.TEST_CASE_RUNMODE, currentTestCaseID)
							.equals(Constants.RUNMODE_YES)) {
						System.out.println("*****Executing Test case*****" + CurrentTestCaseName);
						if (currentTestSuiteXLS.isSheetExist(CurrentTestCaseName)) {

							for (currentTestDataSetID = 2; currentTestDataSetID <= currentTestSuiteXLS
									.getRowCount(CurrentTestCaseName); currentTestDataSetID++) {

								System.out.println("Iteration number" + (currentTestDataSetID - 1));

								if (currentTestSuiteXLS.getCellData(CurrentTestCaseName, Constants.TEST_CASE_RUNMODE,
										currentTestDataSetID).equals(Constants.RUNMODE_YES)) {
									
									System.out.println("Executing Keywords");
									executeKeywords();
									//createXLSReport();

								}
							}

						} else {
							executeKeywords();
							//createXLSReport();
						}
					}
				}
			}
		}

	}

	public void executeKeywords() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for (CurrentTestStepID = 2; CurrentTestStepID <= currentTestSuiteXLS
				.getRowCount(Constants.TEST_STEP_SHEET); CurrentTestStepID++) {
			data=currentTestSuiteXLS.getCellData(Constants.TEST_STEP_SHEET, Constants.Data, CurrentTestStepID);
			if(data!=null){
			if(data.startsWith(Constants.Data_Start_Col)){
				data=currentTestSuiteXLS.getCellData(CurrentTestCaseName, data.split(Constants.Data_Split)[1], currentTestDataSetID);
				
				//System.out.println("Current data column value " + data);
						
			}
			}
                   object=currentTestSuiteXLS.getCellData(Constants.TEST_STEP_SHEET, Constants.Object, CurrentTestStepID);
                   
                   
                   objectType=currentTestSuiteXLS.getCellData(Constants.TEST_STEP_SHEET, Constants.ObjectType, CurrentTestStepID);
                   TCDesc=currentTestSuiteXLS.getCellData(Constants.TEST_STEP_SHEET, Constants.Description, CurrentTestStepID);
			//System.out.println("TCDesc: " + TCDesc);
			if (CurrentTestCaseName.equals(currentTestSuiteXLS.getCellData(Constants.TEST_STEP_SHEET,
					Constants.TEST_CASE, CurrentTestStepID))) {
				// System.out.println("CurrentTestCaseName" +
				// CurrentTestCaseName);

				currentKeyword = (currentTestSuiteXLS.getCellData(Constants.TEST_STEP_SHEET, Constants.TEST_KEYWORDS,
						CurrentTestStepID));

				// System.out.println("row count" +
				// currentTestSuiteXLS.getRowCount(Constants.TEST_STEP_SHEET));

				System.out.println(currentKeyword);
			
				

		try{
			for (int i = 0; i < method.length; i++){
				
					if (method[i].getName().equals(currentKeyword)) {
						
						System.out.println("Method name  " + method[i].getName());
						
						
						logger = report.startTest(TCDesc);
			
								 
					     method[i].invoke(keywords,TCDesc,objectType,object,data);
						
					     report.endTest(logger);
							report.flush();
						
						
					
					}
		}
		}
		
		catch(Exception e){ System.out.println("Error -->" + e.getMessage()); 
	
			}
		
		
}
			}		
		
		}
	public void createXLSReport(){
	
	if(resultSet.size()==0){
		currentTestSuiteXLS.setCellData(CurrentTestCaseName,Constants.Result,currentTestDataSetID,Constants.Keyword_Skip);
		return;
	}else{
		for(int i=0;i<resultSet.size();i++){
			if(!resultSet.get(i).equals(Constants.Keyword_Pass)){
				currentTestSuiteXLS.setCellData(CurrentTestCaseName,Constants.Result,currentTestDataSetID,Constants.Keyword_Fail);
				return;
			}
		}
	}
	currentTestSuiteXLS.setCellData(CurrentTestCaseName,Constants.Result,currentTestDataSetID,Constants.Keyword_Pass);
		
	}
}


