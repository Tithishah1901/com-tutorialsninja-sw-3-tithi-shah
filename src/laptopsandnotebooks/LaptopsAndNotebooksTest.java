package laptopsandnotebooks;

/**
 * 1. Test name verifyProductsPriceDisplayHighToLowSuccessfully()
 * 1.1 Mouse hover on the Laptops & Notebooks Tab. and click
 * 1.2 Click on “Show AllLaptops & Notebooks”
 * 1.3 Select the Sort By "Price (High > Low)"
 * 1.4 Verify the Product price will be arranged in High to Low orders.
 * 2. Test name verifyThatUserPlaceOrderSuccessfully()
 * 2.1 Mouse hover on the Laptops & Notebooks Tab and click
 * 2.2 Click on the “Show AllLaptops & Notebooks”
 * 2.3 Select Sort By "Price (High > Low)"
 * 2.4 Select Product “MacBook”
 * 2.5 Verify the text “MacBook”
 * 2.6 Click on the ‘Add To Cart’ button
 * 2.7 Verify the message “Success: You have added MacBook to your shopping cart!”
 * 2.8 Click on the link “shopping cart” display into the success message
 * 2.9 Verify the text "Shopping Cart"
 * 2.10 Verify the Product name "MacBook"
 * 2.11 Change the Quantity "2"
 * 2.12 Click on the “Update” Tab
 * 2.13 Verify the message “Success: You have modified your shopping cart!”
 * 2.14 Verify the Total £737.45
 * 2.15 Click on the “Checkout” button
 * 2.16 Verify the text "Checkout"
 * 2.17 Verify the Text “New Customer”
 * 2.18 Click on the “Guest Checkout” radio button
 * 2.19 Click on “Continue” tab
 * 2.20 Fill the mandatory fields
 * 2.21 Click on “Continue” Button
 * 2.22 Add Comments About your order into the text area
 * 2.23 Check the Terms & Conditions check box
 * 2.24 Click on the “Continue” button
 * 2.25 Verify the message “Warning: Payment method required!”
 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LaptopsAndNotebooksTest extends Utility {
    String baseUrl = "https://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() {

        // Mouse hover on the Laptops & Notebooks Tab
        mouseHoverToElement(By.linkText("Laptops & Notebooks"));

        //Click on “Show AllLaptops & Notebooks”
        mouseHoverToElementAndClick((By.linkText("Show AllLaptops & Notebooks")));

        // Select the Sort By "Price (High > Low)"
        selectByVisibleTextDropdown(By.id("input-sort"), "Price (High > Low)");

        //  Verify the Product price will be arranged in High to Low order
        List<Double> actualPrices = new ArrayList<>();
        for (WebElement priceElement : getWebElements(By.xpath("//p[@class='price']/span[1]"))) {
            String priceText = priceElement.getText().replace("£", "").replace(",", "").replace("Ex Tax:", "").replace("$", "").trim();
            actualPrices.add(Double.parseDouble(priceText));
        }

        // Create a copy of the prices list and sort it in descending order
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices, Collections.reverseOrder());

        // verify the prices are displayed in descending order
        Assert.assertEquals("Product prices are not sorted in High to Low order!", expectedPrices, actualPrices);

    }

    @Test
    public void verifyThatUserPlaceOrderSuccessfully() {


        //  Mouse hover on the Currency Dropdown and click
        mouseHoverToElementAndClick(By.xpath("//span[text()='Currency']"));

        //Select £Pound Sterling
        mouseHoverToElementAndClick(By.xpath("//button[normalize-space()='£Pound Sterling']"));

        // Mouse hover on the Laptops & Notebooks Tab
        mouseHoverToElement(By.linkText("Laptops & Notebooks"));

        // click on all Laptops & Notebooks
        mouseHoverToElementAndClick(By.linkText("Laptops & Notebooks"));

        //Click on “Show AllLaptops & Notebooks”
        mouseHoverToElementAndClick((By.linkText("Show AllLaptops & Notebooks")));

        // Select the Sort By "Price (High > Low)"
        selectByVisibleTextDropdown(By.id("input-sort"), "Price (High > Low)");

        // get product list and click on it
        for (WebElement webElement : getWebElements(By.xpath("//h4"))) {
            System.out.println("Available Product: " + webElement.getText());
            if (webElement.getText().equalsIgnoreCase("MacBook")) {
                // Find and click on "MacBook"
                clickOnElement(By.linkText(webElement.getText()));
                break;
            }
        }

        // Verify product name on the product details page
        Assert.assertEquals("Product name mismatch!", "MacBook",
                getTextFromElement(By.xpath("//h1[normalize-space()='MacBook']")));

        // Add the product to the cart
        clickOnElement(By.id("button-cart"));

        // Verify success message
        Assert.assertTrue("Success message mismatch!",
                getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).contains("Success: You have added MacBook to your shopping cart!"));

        // Navigate to the shopping cart
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));

        // Verify shopping cart text
        WebElement shoppingCartTitle = driver.findElement(By.tagName("h1"));
        Assert.assertTrue("Shopping Cart title mismatch!",
                getTextFromElement(By.xpath("//h1[contains(text(),'Shopping Cart')]")).contains("Shopping Cart"));

        // Verify the Product name "MacBook"
        Assert.assertEquals("Product name in cart mismatch!",
                "MacBook", getTextFromElement(By.xpath("(//a[contains(text(),'MacBook')])[2]")));

        // Change the Quantity "2"
        sendTextToElementWithClearText(By.xpath("//input[starts-with(@name,'quantity')]"), "2");

        // Click on the “Update” Tab
        clickOnElement(By.xpath("//i[@class='fa fa-refresh']"));

        // Verify the message “Success: You have modified your shopping cart!”
        Assert.assertTrue("Update cart success message mismatch!",
                getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).contains("Success: You have modified your shopping cart!"));

        // Verify the Total £737.45
        Assert.assertEquals("Prise is not matched.", "737.45",
                getTextFromElement(By.xpath("//tbody//tr//td[6]")).replace("$", "").replace("£",""));

        // Click on the “Checkout” button
        clickOnElement(By.linkText("Checkout"));

        // Verify the text “Checkout”

    }

    @After
    public void tearDown() {
        closeBrowser();
    }

}
