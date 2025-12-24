package automation.pages;

import automation.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    private WebDriver driver;
private SeleniumUtils seleniumutils;
    // Use non-static WebElement with PageFactory
    @FindBy(name = "firstname")
    private WebElement firstname;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        seleniumutils=new SeleniumUtils(driver);
        PageFactory.initElements(driver, this);
    }

    // Instance method to register a new user / enter first name
    public void enterFirstName(String name) {
        seleniumutils.type(firstname, name);
    }

    // Optional: convenience method for a default name
    public void newUserRegister() {
        enterFirstName("greeshma");
    }

    // Optional getter for verification
    public String getFirstNameValue() {
        return firstname.getAttribute("value");
    }
}
