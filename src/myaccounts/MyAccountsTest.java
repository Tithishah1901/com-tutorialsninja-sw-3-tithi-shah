package myaccounts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

/**
 * 1. Create the class MyAccountsTest
 * 1.1 create a method with the name "selectMyAccountOptions()" It has one parameter
 * name "option" of type string
 * 1.2 This method should click on the options of whatever name is passed as a parameter.
 * (Hint: Handle List of Element and Select options)
 * Write the following test
 * 1. Test name verifyUserShouldNavigateToRegisterPageSuccessfully()
 * 1.1 Click on the My Account Link.
 * 1.2 Call the method “selectMyAccountOptions()” method and pass the
 * parameter “Register”
 * 1.3 Verify the text “Register Account”.
 * 2. Test name verifyUserShouldNavigateToLoginPageSuccessfully()
 * 2.1 Click on the My Account Link.
 * 2.2 Call the method “selectMyAccountOptions()” method and pass the
 * parameter “Login”
 * 2.3 Verify the text “Returning Customer
 * Test name verifyThatUserRegisterAccountSuccessfully()
 * 3.1 Click on the My Account Link.
 * 3.2 Call the method “selectMyAccountOptions()” method and pass the
 * parameter “Register”
 * 3.3 Enter the First Name
 * 3.4 Enter the Last Name
 * 3.5 Enter the Email
 * 3.6 Enter the Telephone
 * 3.7 Enter the Password
 * 3.8 Enter the Password Confirm
 * 3.9 Select Subscribe Yes radio button
 * 3.10 Click on the Privacy Policy check box
 * 3.11 Click on the Continue button
 * 3.12 Verify the message “Your Account Has Been Created!”
 * 3.13 Click on the Continue button
 * 3.14 Click on the My Account Link.
 * 3.15 Call the method “selectMyAccountOptions()” method and pass the
 * parameter “Logout”
 * 3.16 Verify the text “Account Logout”
 * 3.17 Click on the Continue button
 * 4. Test name verifyThatUserShouldLoginAndLogoutSuccessfully()
 * 4.1 Click on the My Account Link.
 * 4.2 Call the method “selectMyAccountOptions()” method and pass the
 * parameter “Login”
 * 4.3 Enter the Email address
 * 4.4 Enter the Last Name
 * 4.5 Enter the Password
 * 4.6 Click on the Login button
 * 4.7 Verify text “My Account”
 * 4.8 Click on the My Account Link.
 * 4.9 Call the method “selectMyAccountOptions()” method and pass the
 * parameter “Logout”
 * 4.10 Verify the text “Account Logout”
 * 4.11 Click on the Continue button
 *
 */
public class MyAccountsTest extends Utility {
    String baseUrl = "https://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }


    public void selectMyAccountOptions(String name){
        for (WebElement element : getWebElements(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a"))) {
            if (element.getText().equalsIgnoreCase(name)) {
                element.click();
                return;
            }
        }
    }

    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {

        //Click on the My Account Link
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));

        // Call selectMyAccountOptions() and pass "Register"
        selectMyAccountOptions("Register");

        // Verify the text “Register Account”
        Assert.assertEquals("Page title mismatch!","Register Account",
                getTextFromElement(By.xpath("//h1[normalize-space()='Register Account']")));
    }

    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {

        //Click on the My Account Link
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));

        //  Call selectMyAccountOptions() and pass "Login"
        selectMyAccountOptions("Login");

        //  Verify the text “Returning Customer”
        Assert.assertEquals("Page title mismatch!","Returning Customer",
                getTextFromElement(By.xpath("//h2[normalize-space()='Returning Customer']")));
    }

    @Test
    public void verifyThatUserRegisterAccountSuccessfully() {

        //Click on the My Account Link
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));

        // Call selectMyAccountOptions() and pass "Register"
        selectMyAccountOptions("Register");

        //  Fill in the registration form
        sendTextToElement(By.id("input-firstname"), "prime");
        sendTextToElement(By.id("input-lastname"), "test");
        sendTextToElement(By.id("input-email"), "primetest1@prime.com");
        sendTextToElement(By.id("input-telephone"), "07586434567");
        sendTextToElement(By.id("input-password"), "Prime@123");
        sendTextToElement(By.id("input-confirm"), "Prime@123");

        //Select Subscribe Yes radio button
        clickOnElement(By.xpath("//label[normalize-space()='Yes']"));

        // Click on the Privacy Policy checkbox
        clickOnElement(By.xpath("//input[@name='agree']"));

        // Click on the Continue button
        clickOnElement(By.xpath("//input[@class= 'btn btn-primary']"));

        //  Verify the message “Your Account Has Been Created!”
        Assert.assertEquals("Account creation message mismatch!","Your Account Has Been Created!",
                getTextFromElement(By.xpath("//h1[normalize-space()='Your Account Has Been Created!']")));

        //  Click on the Continue button
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));

        //Click on the My Account Link
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));

        //  click on log out
        selectMyAccountOptions("Logout");

        //  Verify the logout text
        Assert.assertEquals("Log out message mismatch!","Account Logout",
                getTextFromElement(By.xpath("//h1[normalize-space()='Account Logout']")));

        // Click on the Continue button
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));
    }

    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() {

        //Click on the My Account Link
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));

        // Call selectMyAccountOptions() and pass "Register"
        selectMyAccountOptions("Login");

        // Fill in the login form
        sendTextToElement(By.id("input-email"), "primetest1@prime.com");
        sendTextToElement(By.id("input-password"), "Prime@123");

        // Click on the login button
        clickOnElement(By.xpath("//input[@value='Login']"));

        //  Verify the message “Your Account Has Been Created!”
        Assert.assertEquals("My Account text mismatch!","My Account",
                getTextFromElement(By.xpath("//h2[normalize-space()='My Account']")));

        //Click on the My Account Link
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));

        //  click on log out
        selectMyAccountOptions("Logout");

        //  Verify the logout text
        Assert.assertEquals("Log out message mismatch!","Account Logout",
                getTextFromElement(By.xpath("//h1[normalize-space()='Account Logout']")));

        // Click on the Continue button
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));

    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
