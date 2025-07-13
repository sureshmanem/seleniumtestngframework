// src/test/java/com/mycompany/automation/pages/PracticeFormPage.java
package com.suresh.automation.pages;

import com.aventstack.extentreports.Status;
import com.suresh.automation.utils.ExtentManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class PracticeFormPage extends BasePage {

    private By firstNameInput = By.name("firstname");
    private By lastNameInput = By.name("lastname");
    private By genderRadio = By.name("sex");
    private By experienceRadio = By.name("exp");
    private By professionCheckbox = By.name("profession");
    private By automationToolCheckbox = By.name("tool");
    private By continentsDropdown = By.id("continents");
    private By seleniumCommandsDropdown = By.id("selenium_commands");
    private By submitButton = By.id("submit");

    public PracticeFormPage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstName) {
        sendKeys(firstNameInput, firstName);
        ExtentManager.getTest().log(Status.INFO, "Entered First Name: " + firstName);
    }

    public void enterLastName(String lastName) {
        sendKeys(lastNameInput, lastName);
        ExtentManager.getTest().log(Status.INFO, "Entered Last Name: " + lastName);
    }

    public void selectGender(String gender) {
        selectRadioOrCheckbox(genderRadio, gender);
        ExtentManager.getTest().log(Status.INFO, "Selected Gender: " + gender);
    }

    public void selectExperience(String years) {
        selectRadioOrCheckbox(experienceRadio, years);
        ExtentManager.getTest().log(Status.INFO, "Selected Years of Experience: " + years);
    }
    
    public void selectProfession(String profession) {
        selectRadioOrCheckbox(professionCheckbox, profession);
        ExtentManager.getTest().log(Status.INFO, "Selected Profession: " + profession);
    }
    
    public void selectAutomationTool(String tool) {
        selectRadioOrCheckbox(automationToolCheckbox, tool);
        ExtentManager.getTest().log(Status.INFO, "Selected Automation Tool: " + tool);
    }

    public void selectContinent(String continent) {
        WebElement dropdownElement = waitForVisibilityOfElement(continentsDropdown);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(continent);
        ExtentManager.getTest().log(Status.INFO, "Selected Continent: " + continent);
    }
    
    public void selectSeleniumCommand(String command) {
        WebElement dropdownElement = waitForVisibilityOfElement(seleniumCommandsDropdown);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(command);
        ExtentManager.getTest().log(Status.INFO, "Selected Selenium Command: " + command);
    }

    public void clickSubmit() {
        click(submitButton);
        ExtentManager.getTest().log(Status.INFO, "Clicked Submit button");
    }
    
    private void selectRadioOrCheckbox(By locator, String value) {
        List<WebElement> options = waitForPresenceOfAllElements(locator);
        for (WebElement option : options) {
            if (option.getAttribute("value").equalsIgnoreCase(value)) {
                jsClick(option);
                break;
            }
        }
    }
}