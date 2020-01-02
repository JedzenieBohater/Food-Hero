package hero.seleniumtests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTest {
    private WebDriver driver;
    
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }
    
    @Test
    public void googleExampleTest(){
        driver.get("http://www.google.com");
        String title = driver.getTitle();
        driver.quit();
        assertEquals("Google", title);
        
    }

}
