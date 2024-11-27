package homepage;
/**
 * create a class "TopMenuTest"
 * 1.1 create a method with the name "selectMenu()" It has one parameter name "menu"
 * of type string
 * 1.2 This method should click on the menu whatever name is passed as a parameter.
 * Write the following Test in the same class “TopMenuTest”:
 * 1. verifyUserShouldNavigateToDesktopsPageSuccessfully()
 * 1.1 Mouse hover on the “Desktops” Tab and click
 * 1.2 call the selectMenu() method and pass the menu = “Show AllDesktops”
 * 1.3 Verify the text ‘Desktops’
 * 2. verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully()
 * 2.1 Mouse hover on the “Laptops & Notebooks” Tab and click
 * 2.2 call selectMenu() method and pass the menu = “Show AllLaptops & Notebooks”
 * 2.3 Verify the text ‘Laptops & Notebooks’
 * 3. verifyUserShouldNavigateToComponentsPageSuccessfully()
 * 3.1 Mouse hover on the “Components” Tab and click
 * 3.2 call the selectMenu() method and pass the menu = “Show AllComponents”
 * 3.3 Verify the text ‘Components’
 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TopMenuTest extends Utility {

    String baseUrl = "https://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }
    public void selectMenu(String menu){
        clickOnElement(By.linkText(menu));
    }


    @Test
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully(){

        // Mouse hover on desktop
        mouseHoverToElement(By.xpath("//a[normalize-space()='Desktops']"));

        // click on all desktop
        selectMenu("Show AllDesktops");

        // Verify desktop text
        Assert.assertEquals("Desktops is not matched!","Desktops",
                getTextFromElement(By.tagName("h2")));

    }

    @Test
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {

        // Hover on Laptops & Notebooks Tab
        mouseHoverToElement(By.linkText("Laptops & Notebooks"));

        // click on "Show All Laptops & Notebooks"
        selectMenu("Show AllLaptops & Notebooks");

        // Verify the page title Laptops & Notebooks
        Assert.assertEquals("Laptops & Notebooks text is not matched.", "Laptops & Notebooks" ,
                getTextFromElement(By.tagName("h2")));
    }


    @Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully() {

        // Hover on Components Tab
        mouseHoverToElement(By.linkText("Components"));

        // click on Components
        selectMenu("Show AllComponents");

        // Verify the page title Laptops & Notebooks
        Assert.assertEquals("Components text is not matched.", "Components" ,
                getTextFromElement(By.tagName("h2")));
    }


    @After
    public void tearDown(){
        closeBrowser();
    }
}
