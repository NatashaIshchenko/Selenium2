package package2_log;


import additional.PropertiesInitClass;
import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.ProtocolHandshake;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BaseTest2 {

    /**
     * Класс переопределяющий методы WebDriverEventListener
     * позволяет задать действие перед выбранным событием драйвера
     */
    public static class MyListener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by +  " found");
        }

        /**
         * cнятие скриншота при эксепшене
         */
        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
            File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(tempFile, new File("screen.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public EventFiringWebDriver driver;

    @BeforeSuite
    public void beforeSuite(){
        PropertiesInitClass.propertisesInitFunc();
    }

    @BeforeClass
    public void beforeClass(){
        // установка уровня логирования для ProtocolHandshake
        Logger LOG = Logger.getLogger(ProtocolHandshake.class.getName());
        LOG.setLevel(Level.WARNING);
        ChromeOptions capability = new ChromeOptions();
        //Enable логи консоли браузера
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        capability.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        capability.setCapability("enableVNC", true); //эта строчка добавляется для удаленных браузеров

        driver = new EventFiringWebDriver(new ChromeDriver(capability));
        driver.register(new MyListener());
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
        driver = null;
    }

    /**
     * Вывод лога из консоли браузера
     */
    public void seeLog(){
        driver.manage().logs().get("browser").getAll().forEach(e -> System.out.println(e));
//        driver.manage().logs().get("browser").getAll()
//                .forEach(entry -> System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage()));
    }
}