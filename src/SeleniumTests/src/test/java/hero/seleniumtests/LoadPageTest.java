package hero.seleniumtests;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoadPageTest {

    private static WebDriver driver;

    public LoadPageTest() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();

    }

    @Test
    public void homePageLoadTest() {
        driver.get("http://localhost:13000");
        WebElement item = null;
        try {
            item = driver.findElement(By.className("text-block"));
        } catch (Exception e) {
            fail();
        }
        if (item == null) {
            fail();
        }
    }
    
    @Test
    public void searchPageLoadTest() {
        driver.get("http://localhost:13000/search");
        WebElement item = null;
        try {
            item = driver.findElement(By.id("localization"));
        } catch (Exception e) {
            fail();
        }
        if (item == null) {
            fail();
        }
    }
    
    @Test
    public void loginPageLoadTest() {
        driver.get("http://localhost:13000/login");
        WebElement email = null;
        WebElement password = null;
        try {
            email = driver.findElement(By.id("email"));
            password = driver.findElement(By.id("password"));
        } catch (Exception e) {
            fail();
        }
        if (email == null || password == null) {
            fail();
        }
    }

    @Test
    public void registerPageLoadTest() {
        driver.get("http://localhost:13000/register");
        WebElement email = null;
        WebElement password = null;
        WebElement repeatPassword = null;
        try {
            email = driver.findElement(By.id("email"));
            password = driver.findElement(By.id("password"));
            repeatPassword = driver.findElement(By.id("password2"));
        } catch (Exception e) {
            fail();
        }
        if (email == null || password == null || repeatPassword == null) {
            fail();
        }
    }
}
