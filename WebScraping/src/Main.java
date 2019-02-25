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
    List<WebElement> languageNamesList;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getList() {
        String aboutButton = "//*[@id='about']/child::div/a";
        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(aboutButton)));
        driver.findElement(By.xpath(aboutButton)).click();
        String languagesParagraph = "//*[@id='page1']/child::div[2]/div[5]";
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(languagesParagraph)));

        languageNamesList = driver.findElements(By.xpath("//*[@id='page1']/child::div[2]/div[5]/p"));
    }

    public void printList() {
        for (WebElement lang : languageNamesList) {
            System.out.println(lang.getText());
        }
    }

    public static void main(String[] args) {
        Main test = new Main();
        test.initDriver();
        test.getList();
        test.printList();
    }
}
