package org.example;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class ECommerceApplication
{
    public static final String URL_WEBSITE = "http://automationpractice.com/index.php";

    public static void main( String[] args ) throws MalformedURLException {

        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();

        URL url = new URL("http://localhost:4444/wd/hub");
        RemoteWebDriver driver  = new RemoteWebDriver(url, desiredCapabilities);

        driver.get(URL_WEBSITE);
        System.out.println( "Title of the page: " + driver.getTitle());

        driver.quit();
    }
}
