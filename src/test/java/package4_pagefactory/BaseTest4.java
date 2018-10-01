package package4_pagefactory;


import additional.PropertiesInitClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest4 {

    public WebDriver driver;

    @BeforeSuite
    public void beforeSuite(){
        PropertiesInitClass.propertisesInitFunc();
    }

    @BeforeClass
    public void beforeClass(){

        // configure it as a desired capability
        ChromeOptions capabilities = new ChromeOptions();

        // start the browser up
        driver = new ChromeDriver(capabilities);

        PageFactory.initElements(driver, this);

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
        driver = null;
    }

}
