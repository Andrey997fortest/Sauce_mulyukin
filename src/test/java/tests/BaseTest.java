package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.PropertyReader;


public class BaseTest {
    public WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    String user;
    String password;

    @Parameters({"browser"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
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
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        System.setProperty("BASE_URL", PropertyReader.getProperty("sauce.url"));
        user = PropertyReader.getProperty("sauce.user");
        password = PropertyReader.getProperty("sauce.password");
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        //driver.quit();
    }
}