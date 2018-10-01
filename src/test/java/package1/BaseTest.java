package package1;

import additional.PropertiesInitClass;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class BaseTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeSuite
    public void beforeSuite(){
        PropertiesInitClass.propertisesInitFunc();
    }

    @BeforeClass
    public void beforeClass(){
        int r = new Random().nextInt(1) + 1;
        switch (r){
            case 1:
                ChromeOptions options1 = new ChromeOptions();
                options1.setCapability( "unexpectedAlertBehaviour", "dismiss");
                options1.addArguments("start-maximized");
                //options1.setCapability("unexpectedAlertBehaviour", "dismiss"); - закрывает алерты, выбрасывает эксепшен
                driver = new ChromeDriver(options1);
                break;
            case 2:
                FirefoxOptions options2 = new FirefoxOptions();
                options2.setCapability("unexpectedAlertBehaviour", "dismiss");
                driver = new FirefoxDriver(options2);
                break;
            case 3:
                EdgeOptions options3 = new EdgeOptions();
                options3.setCapability("unexpectedAlertBehaviour", "dismiss");
                driver = new EdgeDriver(options3);
                break;
            case 4:
                InternetExplorerOptions options4 = new InternetExplorerOptions();
                options4.setCapability("unexpectedAlertBehaviour", "dismiss");
                options4.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
                driver = new InternetExplorerDriver(options4);
                break;
        }
        System.out.println("Capabilities = " + ((HasCapabilities) driver).getCapabilities()); //получение всех Capabilities инстанса

        //задание неявного ожидания, которое будет юзаться при поиске всех элементов
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // при помощи этого объекта будут реализовываться явные ожидания
        wait = new WebDriverWait(driver,5);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
        driver = null;
    }

    /*************************** Cookies **************************/

    /**
     * Удаление всех кук у драйвера
     */
    public void clearCookies() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

    /**
     * Методы для работы с Cookie
     */
    public void cookiesFunc(){
        driver.manage().addCookie(new Cookie("test", "test"));
        Cookie testCookie = driver.manage().getCookieNamed("test");
        Set<Cookie> cookies = driver.manage().getCookies();
    }

    /***************************************************/

    public void badSleep(){
        try{
            Thread.sleep(1000);
        } catch (Exception ex) {}
    }
}
