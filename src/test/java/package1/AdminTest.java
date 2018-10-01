package package1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class AdminTest extends BaseTest {

    @Test
    public void openAdmin(){
        //driver.navigate().to("http://localhost/litecart/admin");
        driver.get("http://localhost/litecart/admin");
    }

    @Test(dependsOnMethods = "openAdmin")
    public void loginToAdmin(){
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test(dependsOnMethods = "loginToAdmin")
    public void openMenuItems(){
        int menu_size  = driver.findElements(By.xpath("//*[@id='box-apps-menu']/li")).size();
        for(int i=1; i<=menu_size; i++) {
            driver.findElement(By.xpath("//*[@id='box-apps-menu']/li["+i+"]")).click();
            badSleep();
        }
    }

    @Test(dependsOnMethods = "openMenuItems")
    public void openFirstMenuItem() {
        WebElement first_item = (WebElement) ((JavascriptExecutor) driver)
                .executeScript("return $$('#box-apps-menu li:first-child')");
        first_item.click();
        badSleep();
    }


    @Test(dependsOnMethods = "loginToAdmin")
    public void comboboxFunc(){
        driver.findElement(By.xpath("//*[@id='box-apps-menu']/li[2]")).click();
        driver.findElement(By.id("doc-csv")).click();
        WebElement element = driver.findElement(By.name("currency_code"));
        Select selement = new Select(element);
        selement.selectByVisibleText("Euros");
        badSleep();
    }
}
