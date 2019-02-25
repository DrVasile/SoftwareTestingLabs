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

    public void invokeBrowser() {
        try {
            driver.get("http://edureka.co");
            searchCourse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchCourse() {
        driver.findElement(By.id("search-inp")).sendKeys("Java");
        driver.findElement(By.id("search-button-top")).click();
    }

    public void setEmail() {
        driver.get("http://demo.guru99.com/");
        WebElement element = driver.findElement(By.xpath("//input[@name='emailid']"));
        element.sendKeys("abc@gmailcom");

        WebElement button = driver.findElement(By.xpath("//input[@name='btnLogin']"));
        button.click();
    }

    public static void main(String[] args) {
        DemoTest test = new DemoTest();
        test.initDriver();
        test.setEmail();
    }
}
