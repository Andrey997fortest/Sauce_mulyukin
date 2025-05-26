package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static user.UserFactory.withAdminPermission;

public class AddToCartTest extends BaseTest {
    @Epic("Модуль логина интернет-магазина")
    @Feature("")
    @Story("STG")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Mulyukin")
    @TmsLink("")
    @Issue("2")
    @Test(description="Проверяем, что товары добавлены в корзину")
    @Flaky
    public void checkGoodsInCart() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.isOpen();
        productsPage.addToCart(0);
        productsPage.addToCart(2);
        productsPage.addToCart(3);
        productsPage.openCart();
        assertTrue(cartPage.getProductsNames().contains("Sauce Labs Backpack"));
        assertEquals(cartPage.getProductsNames().size(), 3);
        assertFalse(cartPage.getProductsNames().isEmpty());
    }
}