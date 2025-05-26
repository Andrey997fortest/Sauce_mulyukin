package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Objects;


public class ProductsPage extends BasePage{
    private final By title = By.cssSelector("//*[@class='title']");
    private final By title2 = By.xpath("//*[text()='Products']");
    private static final By addToCartTitle = By.xpath("//*[text()='Add to cart']");
    private final By isOpenTitle = By.xpath("//*[text()='Add to cart']");
    private final By openCartTitle = By.xpath("//*[data-test='shopping-cart-link']");


    private static final String ADD_TO_CARD_BUTTON_PATTERN
            = "//div[text() = '%s']//ancestor::div[@class='inventory_item']//button";


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    public boolean getUrlName() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("inventory");
    }

    public boolean titleIsDisplayed() {
        return driver.findElement(title2).isDisplayed();
    }

    public void addToCart2(String goodsName) {
        By addToCard = By.xpath(String.format(ADD_TO_CARD_BUTTON_PATTERN, goodsName));
    }

    public static void addToCart(int index) {
        driver.findElements(addToCartTitle).get(index).click();
    }

     public void isOpen(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(isOpenTitle));
     }

     public void openCart() {
        driver.findElement(openCartTitle).click();
     }

}