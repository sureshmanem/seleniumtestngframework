// src/test/java/com/mycompany/automation/tests/FormSubmissionTest.java
package com.suresh.automation.tests;

import com.aventstack.extentreports.Status;
import com.suresh.automation.base.BaseTest;
import com.suresh.automation.pages.PracticeFormPage;
import com.suresh.automation.utils.ExcelUtil;
import com.suresh.automation.utils.ExtentManager;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class FormSubmissionTest extends BaseTest {

    @Test(dataProvider = "formData")
    public void submitFormTest(Map<String, String> testData) {
        PracticeFormPage formPage = new PracticeFormPage(driver);
        
        ExtentManager.getTest().log(Status.INFO, "Test Data for this run: " + testData.toString());
        
        formPage.enterFirstName(testData.get("FirstName"));
        formPage.enterLastName(testData.get("LastName"));
        formPage.selectGender(testData.get("Gender"));
        formPage.selectExperience(testData.get("Experience"));
        formPage.selectProfession(testData.get("Profession"));
        formPage.selectAutomationTool(testData.get("AutomationTool"));
        formPage.selectContinent(testData.get("Continent"));
        formPage.selectSeleniumCommand(testData.get("SeleniumCommands"));
    }

    @DataProvider(name = "formData")
    public Object[][] getFormData() throws IOException {
        String filePath = Paths.get(System.getProperty("user.dir"), "testdata", "testdata.xlsx").toString();
        List<Map<String, String>> testDataList = ExcelUtil.getTestData(filePath, "FormData");
        
        Object[][] data = new Object[testDataList.size()][1];
        for (int i = 0; i < testDataList.size(); i++) {
            data[i][0] = testDataList.get(i);
        }
        return data;
    }
}
