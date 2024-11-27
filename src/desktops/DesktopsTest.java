package desktops;

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

/**
 * Create class “DesktopsTest”
 * Write the following Test:
 * 1. Test name verifyProductArrangeInAlphaBaticalOrder()
 * 1.1 Mouse hover on the Desktops Tab. and click
 * 1.2 Click on “Show AllDesktops”
 * 1.3 Select Sort By position "Name: Z to A"
 * 1.4 Verify the Product will be arranged in Descending order.
 * 2. Test name verifyProductAddedToShoppingCartSuccessFully()
 * 2.1 Mouse hover on the Currency Dropdown and click
 * 2.2 Mouse hover on the £Pound Sterling and click
 * 2.3 Mouse hover on the Desktops Tab.
 * 2.4 Click on the “Show AllDesktops”
 * 2.5 Select the Sort By position "Name: A to Z"
 * 2.6 Select product “HP LP3065”
 * 2.7 Verify the Text "HP LP3065"
 * 2.8 Select Delivery Date "2024-11-27"
 * 2.9.Enter Qty "1” using Select class.
 * 2.10 Click on the “Add to Cart” button
 * 2.11 Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
 * 2.12 Click on the link “shopping cart” displayed in a success message
 * 2.13 Verify the text "Shopping Cart"
 * 2.14 Verify the Product name "HP LP3065"
 * 2.15 Verify the Delivery Date "2024-11-27"
 * 2.16 Verify the Model "Product21"
 * 2.17 Verify the Total "£74.73"
 */
public class DesktopsTest extends Utility {

    String baseUrl = "https://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    public void selectMenu(String menu) {
        clickOnElement(By.linkText(menu));
    }

    @Test
    public void verifyProductArrangeInAlphabeticalOrder() {

        // Mouse hover on desktop
        mouseHoverToElement(By.xpath("//a[normalize-space()='Desktops']"));

        // click on all desktop
        selectMenu("Show AllDesktops");

        // Select dropdown sorting Name (Z - A)

        selectByVisibleTextDropdown(By.id("input-sort"), "Name (Z - A)");

        // Verify products are arranged in descending order
        List<String> actualProductNames = new ArrayList<>();
        for (WebElement product : getWebElements(By.xpath("//h4[@class='product-name']/a"))) {
            actualProductNames.add(product.getText().trim());
        }

        // Create a copy of the product names and sort it in descending order
        List<String> expectedProductNames = new ArrayList<>(actualProductNames);
        Collections.sort(expectedProductNames, Collections.reverseOrder());

        // Verify the products are sorted correctly
        Assert.assertEquals("Products are not sorted in descending order!", expectedProductNames, actualProductNames);
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {

        //  Mouse hover on the Currency Dropdown and click
        mouseHoverToElementAndClick(By.xpath("//span[text()='Currency']"));

        //Select £Pound Sterling
        mouseHoverToElementAndClick(By.xpath("//button[normalize-space()='£Pound Sterling']"));

        // Mouse hover on desktop
        mouseHoverToElement(By.xpath("//a[normalize-space()='Desktops']"));

        // click on all desktop
        selectMenu("Show AllDesktops");

        // Select the Sort By position "Name: A to Z"
        selectByVisibleTextDropdown(By.id("input-sort"), "Name (A - Z)");

        Thread.sleep(2000);

        //Select product “HP LP3065”#
        List<WebElement> list = getWebElements(By.xpath("//div[@class = 'product-layout product-grid col-lg-4 col-md-4 col-sm-6 col-xs-12']"));
       /* for (WebElement element : list){
            if (element.getText()..equals("HP LP3065")){
                System.out.println(element.getText());
                break;
            }
           System.out.println(element.getText());
        }*/


        clickOnElement(By.linkText("HP LP3065"));

        // Verify the Text "HP LP3065"
        Assert.assertEquals("Product text not matched", "HP LP3065", getTextFromElement(By.xpath("//h1[normalize-space()='HP LP3065']")));

        // Select Delivery Date "2024-11-27"
        sendTextToElementWithClearText(By.id("input-option225"), "2024-11-27");



        // Enter Qty "1”
        sendTextToElementWithClearText(By.id("input-quantity"), "1");

        // Click on the “Add to Cart” button
        clickOnElement(By.id("button-cart"));

        //  Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
        Assert.assertTrue("Success message mismatch!",
                getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).contains("Success: You have added HP LP3065 to your shopping cart!"));

        //  Click on the link “shopping cart” displayed in the success message
        clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));

        //Verify the text "Shopping Cart"
        Assert.assertTrue("Shopping Cart page title mismatch", getTextFromElement(By.xpath("//h1[contains(text(),'Shopping Cart')]")).contains("Shopping Cart"));

        // Verify the Product name "HP LP3065"
        Assert.assertEquals("Product name in cart mismatch!",
                "HP LP3065", getTextFromElement(By.xpath("(//a[contains(text(),'HP LP3065')])[2]")));

        // Verify the Delivery Date "2024-11-27"
       /* Assert.assertTrue("Delivery date mismatch!",
                getTextFromElement(By.xpath("//small[normalize-space()='Delivery Date: 2024-11-27']")).contains("2024-11-27"));*/

        // Verify the Model "Product21"
        Assert.assertEquals("Model mismatch!", "Product 21",
                getTextFromElement(By.xpath("//td[contains(text(),'Product 21')]")));

        // Verify the Total "£74.73"
        Assert.assertEquals("Total price mismatch!", "£74.73",
                getTextFromElement(By.xpath("(//td[contains(text(),'£74.73')])[4]")));


    }


    @After
    public void tearDown() {
        closeBrowser();
    }
}
