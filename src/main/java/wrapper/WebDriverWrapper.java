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

    public static String remote_url_chrome = "http://localhost:4444/wd/hub";

    public static WebDriver driver;

    public static WebDriver getInstance() {
        if (driver == null) {
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
            URL url = null;
            try {
                url = new URL("http://localhost:4444/wd/hub");
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            driver  = new RemoteWebDriver(url, desiredCapabilities);
        }
        return driver;
    }

}