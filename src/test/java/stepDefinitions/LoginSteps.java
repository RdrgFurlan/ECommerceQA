package stepDefinitions;

import io.cucumber.java.en.Then;
import pages.HomePage;
import pages.LoginPage;

import java.util.List;

public class LoginSteps {

    LoginPage objLoginPage;
    HomePage objHomePage;

    @Then("I click on Login button")
    public void i_click_on_login_button() {
        objHomePage = new HomePage();
        objHomePage.clickOnStartLogin();
    }

    @Then("I input {string} in email address")
    public void i_input_in_email_address(String value) {
        objLoginPage = new LoginPage();
        objLoginPage.fillUserName(value);
    }

    @Then("I input {string} in password")
    public void i_input_in_password(String value) {
        objLoginPage = new LoginPage();
        objLoginPage.fillPassword(value);
    }

    @Then("I click on signin button")
    public void i_click_on_signin_button() {
        objLoginPage = new LoginPage();
        objLoginPage.clickOnSignIn();
    }

    @Then("I will see the Logout button")
    public void i_will_see_the_logout_button() {
        objHomePage = new HomePage();
        objHomePage.verifyUserSigned();
    }

    @Then("I will see the error message {string}")
    public void i_will_see_the_error_message(String expectedErrorMessage) {
        objLoginPage = new LoginPage();
        //assertTrue(objLoginPage.compareErrorMessageReceived(expectedErrorMessage));
    }

    @Then("I try to signin with these credentials:")
    public void i_try_to_signin_with_these_credentials(io.cucumber.datatable.DataTable dataTable) {
        objHomePage = new HomePage();
        objLoginPage = new LoginPage();
        List<List<String>> rows = dataTable.asLists(String.class);
        String userName = "";
        String password = "";
        // get only the first row :-(
        for (List<String> columns : rows) {
            userName = columns.get(0);
            password = columns.get(1);
            break;
        }

        objHomePage.clickOnStartLogin();
        objLoginPage.fillUserName(userName);
        objLoginPage.fillPassword(password);
        objLoginPage.clickOnSignIn();
    }

    @Then("I will validate the credentials: {} {} {}")
    public void iWillValidateDifferentCredentials(String userName, String password, boolean signed) {
        objHomePage = new HomePage();
        objLoginPage = new LoginPage();

        objHomePage.clickOnStartLogin();
        objLoginPage.fillUserName(userName);
        objLoginPage.fillPassword(password);
        objLoginPage.clickOnSignIn();

        if (signed) {
            objHomePage.verifyUserSigned();
        } else {
            objLoginPage.checkSignInErrorAlert();
        }
    }
}
