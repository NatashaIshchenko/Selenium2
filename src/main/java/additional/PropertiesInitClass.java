package additional;

public class PropertiesInitClass {

    public static void propertisesInitFunc(){
        System.setProperty("webdriver.chrome.driver","C:\\Tools\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver","C:\\Tools\\geckodriver.exe");
        System.setProperty("webdriver.edge.driver","C:\\Tools\\MicrosoftWebDriver.exe");
        System.setProperty("webdriver.ie.driver","C:\\Tools\\IEDriverServer.exe");
    }
}
