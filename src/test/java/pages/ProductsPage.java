package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {
    private final By title_css = By.cssSelector("[class='title']");
    private final By title_xpath = By.xpath("//*[text()='Products']");
    private static final String ADD_TO_CART_BUTTON_PATTERN
            = "//div[text()='%s']//ancestor::div[@class='inventory_item']//button";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверка названия товара")
    public String getTitle() {
        return driver.findElement(title_css).getText();
    }

    @Step("Проверка отображения заголовка страницы")
    public boolean titleIsDisplayed() {
        return driver.findElement(title_xpath).isDisplayed();
    }

    public void addToCart(String favNames) {
        By addToCart = By.xpath(String.format(ADD_TO_CART_BUTTON_PATTERN, favNames));
        driver.findElement(addToCart).click();
    }

    @Step("Добавление товара в корзину")
    public void addToCart(int index) {
        driver.findElements(By.xpath("//*[text()='Add to cart']")).get(index).click();
    }

    @Step("Ожидаем загрузки карточек")
    public void isOpen() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Add to cart']")));
    }

    @Step("Открытие корзины")
    public void openCart() {
        driver.findElement(By.xpath("//*[@data-test='shopping-cart-link']")).click();
    }
}