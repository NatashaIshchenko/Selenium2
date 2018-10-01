package package2_log;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class SiteTest2 extends BaseTest2{

    @Test
    public void openSite(){
        driver.get("http://localhost/litecart/en/");
    }

    @Test(dependsOnMethods = "openSite")
    public void func1() {
        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("aaaa@test.ttt");
        seeLog();
    }
}
