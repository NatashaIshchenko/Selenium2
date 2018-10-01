package package1;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class OtherTest extends BaseTest {

    @Test(priority = 1)
    public void chooseItemInInvisibleCombobox(){
        driver.get("http://cssglobe.com/lab/style_select/01.html");
        WebElement element = driver.findElement(By.cssSelector("select"));
        System.out.println("text = " + element.getText());
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].selectedIndex = 3;" +
                   "arguments[0].dispatchEvent(new Event('change'))",
                element);
        badSleep();
    }

    @Test(priority = 2)
    public void makeElementVisible(){
        driver.get("http://cssglobe.com/lab/style_select/01.html");
        WebElement element = driver.findElement(By.cssSelector("select"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.opacity=1", element);
        badSleep();
    }

    @Test(priority = 3)
    public void alertFunc(){
        driver.get("http://localhost/forselenium/site1.html");
        driver.findElement(By.id("alert1")).click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("alert text = " + alertText);
        alert.accept();  // либо alert.dismiss();

    }

    /*@Test
    public void windowsFunc(){
        String mainWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();
        ((JavascriptExecutor) driver).executeScript("window.open()"); // открывает новое окно
        // ожидание появления нового окна,
        // идентификатор которого отсутствует в списке oldWindows,
        // остаётся в качестве самостоятельного упражнения
        String newWindow = wait.until(thereIsWindowOtherThan(oldWindows));
        driver.switchTo().window(newWindow);
        driver.close();
        driver.switchTo().window(mainWindow);
    }*/

    @Test
    public void iframeFunc(){
        driver.get("http://localhost/forselenium/site1.html");
        WebElement iframe_element = driver.findElement(By.id("iframe1"));
        driver.switchTo().frame(iframe_element); //переключиться в iframe
        driver.switchTo().defaultContent(); //переключиться на основную страницу
        driver.switchTo().parentFrame(); // переключиться в родительский frame (если фреймы вложены)
    }
}