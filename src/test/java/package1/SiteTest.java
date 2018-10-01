package package1;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class SiteTest extends BaseTest {

    @Test
    public void openSite(){
        driver.get("http://localhost/litecart/en/");
    }

    /**
     *  getAttribute() возвращает Property объекта, а не Attribute
     */
    @Test(dependsOnMethods = "openSite")
    public void getPropertiesOfElement(){
        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("aaaa@test.ttt");

        String email_value = email.getAttribute("value");
        System.out.println("email_value = " + email_value);

        WebElement a_element = driver.findElement(By.xpath("//a[@class='fancybox zoomable']"));
        String href_a_element = a_element.getAttribute("href");
        System.out.println("href_a_element = " + href_a_element); //возвращает абсолютный url, хотя в DOM он отображается относительным
    }

    /**
     * .getText() - возвращает значение только видимое пользователю
     * getAttribute("textContent") - возвращает значения видимые и невидимые пользователю
     *
     *  !Для форм нужно использовать только getAttribute("value")
     */
    @Test(dependsOnMethods = "openSite")
    public void getTextFunc(){
        String visibler_text = driver.findElement(By.name("login")).getText();
        String all_text = driver.findElement(By.name("login")).getAttribute("textContent");
        System.out.println("visible_text = " + visibler_text);
        System.out.println("all_text = " + all_text);
    }

    @Test(dependsOnMethods = "openSite")
    public void getSizes(){
        WebElement home_block = driver.findElement(By.id("footer-wrapper"));

        Point location = home_block.getLocation();
        System.out.println("location = " + location);

        Dimension size = home_block.getSize();
        System.out.println("size = " + size);

        // "два в одном" -- новый способ, который пока не все драйверы поддерживают
        Rectangle rect = home_block.getRect();
        System.out.println("rect height = " + rect.height + " rect width = " + rect.width + " rect x y = " + rect.x +" " + rect.y);
    }

    @Test(dependsOnMethods = "openSite")
    public void clickFunc(){
        WebElement main_element = driver.findElement(By.id("footer"));
        WebElement a_element = main_element.findElement(By.cssSelector("table tr td a"));
        a_element.click(); //селениум прокручивает страницу до нужного элемента и после кликает
    }

     @Test(dependsOnMethods = "openSite")
    public void fillAndClearField(){
        driver.findElement(By.name("email")).sendKeys("ttttt@test.ttt" + Keys.ENTER);
        // если в поле есть маска -- надо перед вводом текста перейти в начало
        driver.findElement(By.name("email")).sendKeys(Keys.HOME + "yyyyy");
        badSleep();
        driver.findElement(By.name("email")).clear(); // Нельзя использовать команду clear для очистки файловых полей ввода!
        badSleep();
    }

    @Test(dependsOnMethods = "openSite")
    public void actionFunc(){
        WebElement email = driver.findElement(By.name("email"));
        Actions action1 = new Actions(driver);
        action1.click(email)
                .sendKeys(Keys.NUMPAD7)
                .sendKeys("ABC")
                .perform();
        badSleep();

        WebElement image = driver.findElement(By.id("slider-wrapper"));
        action1
                .clickAndHold(image)
                .moveByOffset(1000, 500)
                .release()
                .perform();
    }

    @Test(dependsOnMethods = "openSite")
    public void explicityWaitFunc(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email"))).sendKeys("test@ttt.tt");
        wait.until((WebDriver d) -> d.findElement(By.name("password"))).sendKeys("pass");
        badSleep();
    }

    @Test(dependsOnMethods = "openSite")
    public void staleElementFunc(){
        WebElement element = driver.findElement(By.name("email"));
        driver.navigate().refresh();
        wait.until(ExpectedConditions.stalenessOf(element)); //проверяет исчезновение элемента
    }

    @Test(dependsOnMethods = "openSite")
    public void visibilityOElementFunc(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))); //проверяет видимость элемента по локатору
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("email")))); //проверяет видимость элемента
    }

    /**
     * применяется для тестирования адаптивной верстки
     */
    @Test(dependsOnMethods = "openSite")
    public void changeWindowSize(){
        WebDriver.Window window = driver.manage().window();
        window.maximize();
        window.setSize(new Dimension(800, 600));
        badSleep();
    }
}