package utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import services.DataManagerServices;
import wrapper.WebDriverWrapper;

import java.time.Duration;

public final class WebElementUtils {

    public static WebDriverWait getDriverWait() {
        WebDriver driver = WebDriverWrapper.getInstance();
        int timeMillis = 10000;
        WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(timeMillis));
        return wait;
    }

    public static void checkElementAvailability(WebElement element) {
        WebElement webElement = getDriverWait().until(ExpectedConditions.elementToBeClickable(element));
        //assertNotNull(webElement);
    }

    public static void sendKeysToField(WebElement element, String value) {
        checkElementAvailability(element);
        element.click();
        element.sendKeys(Keys.DELETE);
        element.sendKeys(value);
        element.sendKeys(Keys.TAB);
    }

    public static void clickOnElement(WebElement element) {
        checkElementAvailability(element);
        element.click();
    }

    public static boolean waitValueInElement(WebElement element, String expectedValue) {
        Boolean elementFound = getDriverWait().until((ExpectedCondition<Boolean>) a -> element != null
                && element.getAttribute("value").trim().equalsIgnoreCase(expectedValue));

        return elementFound;
    }

    public static String getElementValue(WebElement webElement) {
        checkElementAvailability(webElement);
        return webElement.getText().trim();
    }

    public static void storeElementValueOnDataManager(String storeKey, WebElement storeElement) {
        checkElementAvailability(storeElement);
        DataManagerServices.setStoredVariable(storeKey, storeElement.getText().trim());
    }
}
