package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WebElementUtils;
import wrapper.WebDriverWrapper;

public class HomePage {

    WebDriver driver;

    @FindBy(xpath = "//a[@class='login']")
    WebElement startLogin;

    @FindBy(xpath = "//a[@title='Log me out'][@class='logout']")
    WebElement logout;

    public HomePage() {
        this.driver = WebDriverWrapper.getInstance();
        PageFactory.initElements(driver, this);
    }

    public void clickOnStartLogin() {
        WebElementUtils.clickOnElement(startLogin);
    }

    public void verifyUserSigned() {
        WebElementUtils.checkElementAvailability(logout);
    }
}
