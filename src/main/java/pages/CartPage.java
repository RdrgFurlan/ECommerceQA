package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WebElementUtils;
import wrapper.WebDriverWrapper;

public class CartPage {

    WebDriver driver;

    @FindBy(xpath = "//input[@id='search_query_top']")
    WebElement searchProductField;

    @FindBy(xpath = "//button[@name='submit_search']")
    WebElement searchProductButton;

    @FindBy(xpath = "//a[@class='product_img_link']")
    WebElement productPreviewButton;

    @FindBy(xpath = "//button[@name='Submit']")
    WebElement addProductCartButton;

    @FindBy(xpath = "//span[@class='ajax_block_products_total']")
    WebElement cartProductPriceLabel;

    @FindBy(xpath = "//span[@id='our_price_display']")
    WebElement productPriceLabel;

    @FindBy(xpath = "//span[@title='Close window']")
    WebElement closeModalCartPreviewButton;

    @FindBy(xpath = "//a[@title='View my shopping cart']")
    WebElement viewShoppingCartButton;

    @FindBy(xpath = "//a[@title='Delete'][@id='1_1_0_0']")
    WebElement removeFirstProductFromCartButton;

    @FindBy(xpath = "//p[@class='alert alert-warning']")
    WebElement emptyCartAfterRemovalLabel;

    @FindBy(xpath = "//input[@name='quantity_1_1_0_0'][@class='cart_quantity_input form-control grey']")
    WebElement productCartQuantityField;

    @FindBy(xpath = "//input[@name='quantity_1_1_0_0_hidden']")
    WebElement productCartQuantityHiddenLabel;


    public CartPage() {
        this.driver = WebDriverWrapper.getInstance();
        PageFactory.initElements(driver, this);
    }

    public void searchProduct(String prodDescription) {
        WebElementUtils.sendKeysToField(searchProductField, prodDescription);
        WebElementUtils.clickOnElement(searchProductButton);
    }

    public void openProductPreview() {
        WebElementUtils.clickOnElement(productPreviewButton);
    }

    public void addProductToCart() {
        WebElementUtils.clickOnElement(addProductCartButton);
    }

    public void closeCartPreview() {
        WebElementUtils.clickOnElement(closeModalCartPreviewButton);
    }

    public void viewShoppingCart() {
        WebElementUtils.clickOnElement(viewShoppingCartButton);
    }

    public void changeProductQuantity(String changeValueTo) {
        WebElementUtils.sendKeysToField(productCartQuantityField, changeValueTo);
    }

    public boolean checkValueInElement(String expectedValue) {
        return WebElementUtils.waitValueInElement(productCartQuantityHiddenLabel, expectedValue);
    }

    public boolean checkEmptyCartMessage() {
        WebElementUtils.checkElementAvailability(emptyCartAfterRemovalLabel);
        return true;
    }

    public void storeProductPrice() {
        WebElementUtils.storeElementValueOnDataManager("productPrice", productPriceLabel);
    }

    public String getCartProductPrice() {
        return WebElementUtils.getElementValue(cartProductPriceLabel);
    }
}
