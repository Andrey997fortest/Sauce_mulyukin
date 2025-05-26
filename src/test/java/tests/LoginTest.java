package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {
    @DataProvider()
    public Object[][] incorrectLoginData() {
        return new Object[][] {
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
        };
    }

    @Test()
    public void correctLogin() {
        loginPage.open();
        loginPage.login(user, password);
        productsPage.isOpen();
    }


    @Test(dataProvider = "incorrectLoginData")
    public void incorrectLogin(String User, String Password, String errorMessage) {
        loginPage.open();
        loginPage.login(User, Password);
        assertEquals(loginPage.getErrorMessage(), errorMessage);

    }







}