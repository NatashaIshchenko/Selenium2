package package3_proxy;

import net.lightbody.bmp.core.har.Har;
import org.testng.annotations.Test;

public class SiteTest3 extends BaseTest3 {

    @Test
    public void seeDataFromProxy(){
        proxy.newHar();
        driver.get("http://localhost/litecart/en/");
        Har har = proxy.endHar();
        har.getLog().getEntries().forEach(e -> System.out.println(e.getResponse().getStatus() + " " + e.getRequest().getUrl()));
    }

}
