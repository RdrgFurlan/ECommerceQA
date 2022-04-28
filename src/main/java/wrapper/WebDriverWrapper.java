package wrapper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class WebDriverWrapper {

    public static String remote_url_browser = "http://localhost:4444/wd/hub";

    private static WebDriver driver;

    public static WebDriver getInstance() {
        if (driver == null) {
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
//            FirefoxOptions options = new FirefoxOptions();
//            options.addArguments("browserName","firefox");
//            options.addArguments("browserVersion","99.0");
//            options.addArguments("platformName","Linux");

            URL url = null;
            try {
                url = new URL(remote_url_browser);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            driver  = new RemoteWebDriver(url, desiredCapabilities);
        }
        return driver;
    }

    public static void quit() {
        //driver.close();
        driver.quit();
        driver = null;
    }
}