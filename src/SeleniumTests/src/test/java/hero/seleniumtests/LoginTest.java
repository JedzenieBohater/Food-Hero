package hero.seleniumtests;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {

    private static WebDriver driver;

    public LoginTest() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();

    }

    @Test
    public void loginTest() {
        driver.get("http://localhost:13000/login");
        WebElement email = null;
        WebElement password = null;
        WebElement submit = null;
        try {
            email = driver.findElement(By.id("email"));
            password = driver.findElement(By.id("password"));
            submit = driver.findElement(By.className("btn-blue"));
        } catch (Exception e) {
            fail();
        }
        if (email == null || password == null || submit == null) {
            fail();
        }
        
        email.sendKeys("a@pw.edu.pl");
        password.sendKeys("admin");
        submit.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("user-header"))); 
    }
}
