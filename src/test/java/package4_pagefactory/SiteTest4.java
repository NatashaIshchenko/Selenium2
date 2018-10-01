package package4_pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class SiteTest4 extends BaseTest4 {

    @FindBy(name = "email")
    WebElement findby_email;

    @Test
    public void findbyFunc(){
        driver.get("http://localhost/litecart/en/");
        findby_email.sendKeys("test333@test.ttt");
        try{  Thread.sleep(1000);  } catch (Exception ex) {}
    }
}
