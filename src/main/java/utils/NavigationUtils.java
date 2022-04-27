package utils;

import org.openqa.selenium.WebDriver;
import wrapper.WebDriverWrapper;

public class NavigationUtils {

    public static final String URL_WEBSITE = "http://automationpractice.com/index.php";

    public void getBrowserInstance() {
        WebDriver driver = WebDriverWrapper.getInstance();
    }

    public void getPage() {
        WebDriver driver = WebDriverWrapper.getInstance();
        driver.get(URL_WEBSITE);
    }

    public void quitDriver() {
        WebDriver driver = WebDriverWrapper.getInstance();
        //driver.close();
        driver.quit();
    }
}
