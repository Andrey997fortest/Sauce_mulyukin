package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.PropertyReader;
import utils.TestListener;
import java.util.concurrent.TimeUnit; //оставил на всякий случай

@Listeners(TestListener.class)
public class BaseTest {
    public WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    String user;
    String password;

    @Parameters({"browser"})
    @BeforeMethod
    @Step()
    public void setup(@Optional("chrome") String browser, ITestContext context) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--guest");
            options.addArguments("--disable-password-manager-reauthentication");
            options.addArguments("--disable-autofill-credit-card-save");
            options.addArguments("--disable-autofill-profile-import");
            options.addArguments("--disable-notifications");
            options.addArguments("--window-size=1200,800");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--guest");
            options.addArguments("--window-size=1200,800");
            driver = new EdgeDriver(options);
        }

        //driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        context.setAttribute("driver", driver);
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);

        System.setProperty("BASE_URL", PropertyReader.getProperty("sauce.url"));
        user = PropertyReader.getProperty("sauce.user");
        password = PropertyReader.getProperty("sauce.password");
    }

    @Step("Закрытие")
    @AfterMethod()
    public void close() {
        //driver.quit();
    }
}
