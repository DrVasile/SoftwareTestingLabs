import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DemoTest {

    WebDriver driver;

    public void initDriver() {
        try {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\drume\\Downloads\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void login() {
        driver.get("http://localhost:8080/#/login");
        WebElement email = driver.findElement(By.xpath("//input[@name='email']"));
        email.sendKeys("vasile@gmail.com");

        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        password.sendKeys("vasile");

        WebElement button = driver.findElement(By.xpath("//button[@class='fluid ui primary button']"));
        button.click();
    }

    public static void main(String[] args) {
        DemoTest test = new DemoTest();
        test.initDriver();
        test.login();
    }
}