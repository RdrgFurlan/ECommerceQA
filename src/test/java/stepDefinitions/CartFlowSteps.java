package stepDefinitions;

import io.cucumber.java.en.Then;
import pages.CartPage;
import services.DataManagerServices;

public class CartFlowSteps {

    CartPage objCartPage;

    @Then("I search for a product {string}")
    public void i_search_for_a_product(String prodDescription) {
        objCartPage = new CartPage();
        objCartPage.searchProduct(prodDescription);
    }

    @Then("I visualize the first product on page")
    public void i_visualize_the_first_product_on_page() {
        objCartPage = new CartPage();
        objCartPage.openProductPreview();
    }

    @Then("I click on Add to cart")
    public void i_click_on_add_to_cart() {
        objCartPage = new CartPage();
        objCartPage.addProductToCart();
    }

    @Then("I close the cart preview")
    public void i_close_the_cart_preview() {
        objCartPage = new CartPage();
        objCartPage.closeCartPreview();
    }

    @Then("I open the full cart shopping")
    public void i_open_the_full_cart_shopping() {
        objCartPage = new CartPage();
        objCartPage.viewShoppingCart();
    }

    @Then("I will validate the results: {} {} {}")
    public void i_will_validate_the_results_accepted(String changeValueTo, String finalValue, String expectedResult) throws Exception {
        objCartPage = new CartPage();
        objCartPage.changeProductQuantity(changeValueTo);

        switch (expectedResult) {
            case "accepted":
            case "refused":
                org.junit.Assert.assertTrue(objCartPage.checkValueInElement(finalValue));
                break;
            case "removed":
                org.junit.Assert.assertTrue(objCartPage.checkEmptyCartMessage());
                break;
            default:
                throw new Exception("Expected result not valid.");
        }
    }

    @Then("I store the product price")
    public void i_store_the_product_price() {
        objCartPage = new CartPage();
        objCartPage.storeProductPrice();
    }
    @Then("I confirm that the product price was not changed")
    public void i_confirm_that_the_product_price_was_not_changed() {
        objCartPage = new CartPage();
        String productPrice = DataManagerServices.getStoredVariable("productPrice");
        String cartProductPrice = objCartPage.getCartProductPrice();

        org.junit.Assert.assertTrue(productPrice.equalsIgnoreCase(cartProductPrice));
    }

}
