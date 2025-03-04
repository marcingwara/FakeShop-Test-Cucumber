package fakeshop.shop;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class UserStep {
    WebDriver driver;


    @Given("I am on the homepage of the FakeStore website")
    public void userGoToShopPage(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        driver.get("https://fakestore.testelka.pl");
    }

    @When("I search for {string}")
    public void iSearchFor(String book) {
        WebElement searchFiled = driver.findElement(By.id("woocommerce-product-search-field-0"));
        searchFiled.sendKeys(book);
        searchFiled.sendKeys(Keys.RETURN);
    }

    @And("I click on the {string} button")
    public void iClickOnTheButton(String addToCart) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('.woocommerce-store-notice').style.display='none';");
        driver.findElement(By.cssSelector("button.single_add_to_cart_button")).click();

    }

    @Then("the product {string} should be added to my cart")
    public void theProductShouldBeAddedToMyCart(String expectedProductName) {
        WebElement cartMessage = driver.findElement(By.cssSelector(".woocommerce-message")); // Dopasuj selektor do swojego komunikatu
        String actualMessage = cartMessage.getText();
        Assert.assertTrue("Produkt nie został poprawnie dodany do koszyka!", actualMessage.contains(expectedProductName));
    }

    @And("the cart should show {int} item")
    public void theCartShouldShowItem(Integer expectedQuantity) {
        driver.get("https://fakestore.testelka.pl/koszyk/");
        WebElement quantityInput = driver.findElement(By.cssSelector("input.qty"));
        String actualQuantity = quantityInput.getAttribute("value");
        Assert.assertEquals("Liczba produktów w koszyku jest nieprawidłowa!",
                expectedQuantity.toString(), actualQuantity);
    }

}
