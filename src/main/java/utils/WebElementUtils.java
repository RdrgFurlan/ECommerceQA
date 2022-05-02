package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import services.DataManagerServices;
import wrapper.WebDriverWrapper;

public final class WebElementUtils {

    public static WebDriverWait getDriverWait() {
        WebDriver driver = WebDriverWrapper.getInstance();
        int timeMillis = 10000;
        WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(timeMillis));
        return wait;
    }

//    public static void checkElementAvailability(WebElement element) {
//        WebElement webElement = getDriverWait().until(ExpectedConditions.elementToBeClickable(element));
//        if (webElement == null){
//            throw new RuntimeException("Element " + element.getText() + " is null");
//        }
//    }
    public static void waitUntilBeVisible(WebElement element) {
        WebDriverWait wait = getDriverWait();
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static void waitUntilBeVisibleAndClickable(WebElement element) {
        WebDriverWait wait = getDriverWait();
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void sendKeysToField(WebElement element, String value) {
        waitUntilBeVisibleAndClickable(element);
        element.click();
        element.sendKeys(Keys.DELETE);
        element.sendKeys(value);
        element.sendKeys(Keys.TAB);
    }

    public static void clickOnElement(WebElement element) {
        waitUntilBeVisibleAndClickable(element);
        JavascriptExecutor js = (JavascriptExecutor) WebDriverWrapper.getInstance();
        js.executeScript("arguments[0].click();", element);
        //element.click();
    }

    public static boolean waitValueInElement(WebElement element, String expectedValue) {
        return getDriverWait().until((ExpectedCondition<Boolean>) a -> element != null
                && element.getAttribute("value").trim().equalsIgnoreCase(expectedValue));
    }

    public static String getElementValue(WebElement webElement) {
        waitUntilBeVisible(webElement);
        return webElement.getText().trim();
    }

    public static void storeElementValueOnDataManager(String storeKey, WebElement storeElement) {
        waitUntilBeVisible(storeElement);
        DataManagerServices.setStoredVariable(storeKey, storeElement.getText().trim());
    }
}
