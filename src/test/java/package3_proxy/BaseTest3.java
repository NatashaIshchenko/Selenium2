package package3_proxy;

import additional.PropertiesInitClass;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest3 {

    public WebDriver driver;
    public BrowserMobProxy proxy;

    @BeforeSuite
    public void beforeSuite(){
        PropertiesInitClass.propertisesInitFunc();
    }

    @BeforeClass
    public void beforeClass(){
        // start the proxy
        proxy = new BrowserMobProxyServer();
        proxy.start(0);

        // get the Selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        // configure it as a desired capability
        ChromeOptions capabilities = new ChromeOptions();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        // start the browser up
        driver = new ChromeDriver(capabilities);

//        Второй вариант
//        Proxy proxy2 = new Proxy();
//        proxy2.setHttpProxy("localhost:8888");
//        ChromeOptions caps = new ChromeOptions();
//        caps.setCapability("proxy", proxy2);
//        WebDriver driver = new ChromeDriver(caps);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
        driver = null;
    }
}
