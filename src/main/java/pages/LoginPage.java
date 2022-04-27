package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WebElementUtils;
import wrapper.WebDriverWrapper;

public class LoginPage {

    WebDriver driver;

    @FindBy(xpath = "//input[@id='email']")
    WebElement getUserNameField;

    @FindBy(xpath = "//input[@id='passwd']")
    WebElement getPasswordField;

    @FindBy(xpath = "//button[@id='SubmitLogin']")
    WebElement getSigninButton;

    @FindBy(xpath = "//div[@class='alert alert-danger']")
    WebElement getSigninErrorAlert;

    public LoginPage() {
        this.driver = WebDriverWrapper.getInstance();
        PageFactory.initElements(driver, this);
    }

    public void fillUserName(String userName) {
        WebElementUtils.sendKeysToField(getUserNameField, userName);
    }

    public void fillPassword(String password) {
        WebElementUtils.sendKeysToField(getPasswordField, password);
    }

    public void clickOnSignIn() {
        WebElementUtils.clickOnElement(getSigninButton);
    }

    public void checkSignInErrorAlert() {
        WebElementUtils.checkElementAvailability(getSigninErrorAlert);
    }

    public boolean compareErrorMessageReceived(String expectedErrorMessage) {
        checkSignInErrorAlert();
        return getSigninErrorAlert.getText().trim().contains(expectedErrorMessage);
    }
}
