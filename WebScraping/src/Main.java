import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    WebDriver driver;
    WebDriverWait driverWait;

    public void initDriver() {
        try {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\drume\\Downloads\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driverWait = new WebDriverWait(driver, 30);

            driver.navigate().to("http://www.galabra.co.il");

            String aboutButton = "//*[@id='about']div/a";
            driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(aboutButton)));
            driver.findElement(By.xpath(aboutButton)).click();
            String languagesParagraph = "//*[@id='page1']/div[2]/div[5]";
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(languagesParagraph)));

            List<WebElement> languageNamesList = driver.findElements(By.xpath("//*[@id='page1']/div[2]/div[5]/p"));
            for (WebElement lang : languageNamesList) {
                System.out.println(lang.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main test = new Main();
        test.initDriver();
        //test.invokeBrowser();
    }
}
