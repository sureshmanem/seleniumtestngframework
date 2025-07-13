// src/test/java/com/mycompany/automation/pages/PracticeFormPage.java
package com.suresh.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class PracticeFormPage {

    private WebDriver driver;

    // Locators
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
        this.driver = driver;
    }

    // Methods to interact with elements
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void selectGender(String gender) {
        selectRadioOrCheckbox(genderRadio, gender);
    }

    public void selectExperience(String years) {
        selectRadioOrCheckbox(experienceRadio, years);
    }
    
    public void selectProfession(String profession) {
        selectRadioOrCheckbox(professionCheckbox, profession);
    }
    
    public void selectAutomationTool(String tool) {
        selectRadioOrCheckbox(automationToolCheckbox, tool);
    }

    public void selectContinent(String continent) {
        Select dropdown = new Select(driver.findElement(continentsDropdown));
        dropdown.selectByVisibleText(continent);
    }
    
    public void selectSeleniumCommand(String command) {
        Select dropdown = new Select(driver.findElement(seleniumCommandsDropdown));
        dropdown.selectByVisibleText(command);
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }
    
    private void selectRadioOrCheckbox(By locator, String value) {
        List<WebElement> options = driver.findElements(locator);
        for (WebElement option : options) {
            if (option.getAttribute("value").equalsIgnoreCase(value)) {
                option.click();
                break;
            }
        }
    }
}
