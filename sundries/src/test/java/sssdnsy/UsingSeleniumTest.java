package sssdnsy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author pengzh
 * @desc
 * @class UsingSeleniumTest
 * @since 2024-04-22
 */
public class UsingSeleniumTest {


    WebDriver driver;

    @Before
    public void setup() {
        System.out.println(System.getProperties());
        driver = new ChromeDriver();
    }

    @Test
    public void eightComponents() {

        driver.manage().timeouts().implicitlyWait(500L, TimeUnit.MICROSECONDS);
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = driver.getTitle();
        assertEquals("Web form", title);

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        assertEquals("Received!", value);

    }

    @After
    public void teardown() {
        driver.quit();
    }


}
