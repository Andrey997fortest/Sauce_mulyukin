package tests;

import org.testng.annotations.Test;
import pages.ProductsPage;


public class AddToCartTest extends BaseTest{
    @Test
    public void addToCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.isOpen();
        ProductsPage.addToCart(0);
        ProductsPage.addToCart(2);

    }

    @Test
    public void removeItemFromCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.isOpen();
        ProductsPage.addToCart(0);
        ProductsPage.addToCart(2);
        ProductsPage.addToCart(3);
        cartPage.openCartPage();
        cartPage.removeFromCart();
    }

    @Test
    public void openItemFromCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.isOpen();
        cartPage.openCartPage();
    }

    @Test
    public void checkoutButton() {}

    @Test
    public void continueShoppingButton() {}





}


